### 2. 플라스크 기초 공사

> **2장 학습 목표**
>
> 1. 블루프린트를 이용해 라우트 함수를 관리
> 2. 플라스크 ORM을 이용해 데이터베이스를 제어
> 3. 파이보 게시판에 질문 목록과 상세 조회 기능을 구현



#### 플라스크 프로젝트 구조 

- 플라스크 프로젝트 구조에 어떠한 규칙은 없지만, 권장하는 패턴은 존재합니다. 차후에 정리합니다.

- `c:/projects/myproject`

```powershell
|-pybo/
|	 |- __init__.py
|	 |- models.py
|	 |- forms.py
|	 |- views/
|	 |      |- main_views.py      
|	 |- static/
|	 |       |- style.css
|	 |- templates/
|	            |- index.html
|- config.py
```



#### 데이터베이스를 처리하는 `models.py`

- 파이보 프로젝트는 ORM(Object Relational Mapping)을 지원하는 파이썬 데이터베이스 도구인 `SQLAlchemy`를 사용
- `SQLAlchemy`는 모델 기반으로 데이터 베이스를 처리
- 파이보 프로젝트에서 **"모델 클래스들을 정의할 파일"** 담당



#### 서버로 전송된 폼을 처리하는 `forms.py`

- `WTForms` 라이브러리는 모델 기반으로 폼을 처리
- 폼 클래스를 정의할 `forms.py` 



#### 화면을 구성하는 `views`

- `views` 디렉터리에는 함수들이 작성된 여러 가지 뷰 파일을 저장



#### CSS, JS, 이미지 파일을 저장하는 `static`

- `static` 디렉터리는 파이보 프로젝트의 `.css, .js` 그리고 이미지 파일인 `.jpg, jpng, ...` 등을 저장



#### 파이보 프로젝트를 설정하는 `config.py`

- `config.py` 파일은 파이보 프로젝트를 설정
- 파이보 프로젝트의 환경 변수, 데이터베이스등의 설정을 이 파일에 저장



#### 애플리케이션 팩토리 사용하기

위에서 `app` 객체를 사용하지 않는 이유는 순환 참조(circular import)가 발생해서 `FLASK_APP` 환경 변수에 `pybo.py` 를 등록하고 플라스크를 실행했습니다. 그 이유는 `app` 객체를 전역으로 사용하면 프로젝트 규모가 커질수록 문제가 발생할 확률이 높아지기 때문입니다. 따라서 플라스크 공식 홈페이지에서 권장하는 **"애플리케이션 팩토리(Application Factory)"**를 사용합니다.



#### `pybo.py` 를 `__init__.py` 파일로 변경하기

- `pybo/__init__.py` 변경 혹은 생성하고 애플리케이션 팩토리를 사용합니다.

```python
from flask import Flask

def create_app():
    app = FLASK_APP(__name__)
    
    @app.route("/")
    def hello_pybo():
        return "Hello, Pybo!"
   	return app
```



#### 블루프린트로 라우트 함수 관리하기

- `@app.route("/")` 를 라우트 함수라고 부르며 매개변수는 URL 경로를 의미합니다. `@app.route("/")`  URL 에서 `/`에 매핑을 의미합니다. 그리고 바로 아래에 함수인 `hello_pybo` 는 `/` 호출될 때 실행해야 할 내용들을 함수 안에 작성합니다.

  새로운 URL이 생길 때 마다 라우트 함수를 `create_app` 함수 안에 계속 추가해야 하는 불편함을  극복하기 위해 사용하는 방법이 바로 블루프린트(Blueprint)라고 부르는 클래스를 사용합니다. 



#### 블루프린트 사용하기

- `pybo/views/main_views.py` 에 Blueprint 객체를 생성합니다.

```python
from flask import Flask

bp = Blueprint("main", __name__, url_prefix="/")

@bp.route("/")
def hello_pybo():
    return "Hello, Pybo!"
```

`@app.route` 애너테이션이 블루프린트 클래스로 생성한 객체를 담고있는 `@bp.route` 애너테이션으로 변경되었습니다. 블루프린트 클래스로 객체를 생성할 때는 위의 코드와 동일하게**`blueprint("이름", 모듈명, URL prefix)`** 값을 전달해야 합니다.

URL prefix는 특정 파일(`main_views.py`)에 있는 함수의 애너테이션 URL 앞에 기본으로 붙일 접두어 URL을 의미합니다. 예를 들어 `main_views.py` 파일의 URL 프리픽스에 `url_prefix="/"` 대신 `url_prefix="/main"` 이라고 입력한다면ㄴ `hello_pybo` 함수를 호출하는 URL은 `localhost:5000`이 아니라 `localhost:5000/main/` 이 됩니다. URL prefix에 대해 좀 더 구체적으로는 나중에 설명하겠습니다.



#### 플라스크 앱 생성 시 블루프린트 적용하기

- `pybo/__init__.py` 에 블루프린트 객체 dp 등록하기
- `from .views import main_views` 를 임포트하는 경로가 안된다면, 임포트에 대해 파이썬 기본 문법을 다시 복습해서 본인에게 적합한 경로를 작성해도 됩니다!

```python
from flask import Flask

def create_app():
    app = Flask(__name__)
 
	from .views import main_views
	app.register_blueprint(main_views.bp)

	return app
```

`create_app` 함수 안에 `hello_pybo` 함수 대신에 블루프린트를 사용했습니다. `main_veiws.py` 에 작성한 블루프린트 객체를 `create_app` 함수 안에 임포트하고 `app.register_blueprint(main_views.bp)` 으로 블루프린트 객체 `bp`를 `main_views.bp` 처럼 매개변수로 전달해서 등록하면 됩니다.



#### 라우트 함수 등록하기

블루프린트를 적용했습니다. 등록된 블루프린트를 URL에 등록하고 사용하기 위해 매핑 함수를 `main_views.py` 파일에 작성합니다.

- `pybo/views/main_views.py`

```python
from flask import Blueprint

bp = Blueprint("main", __name__, url_prefix="/")

@bp.route("/hello")
def hello_pybo():
    return "hello, Pybo!"

@bp.route("/")
def index():
    return "Pybo index"
```

실행해서 블루프린트를 적용한 URL 경로가 성공되는지 확인합니다.



#### 모델로 데이터 처리하기

SQL 쿼리 작성 대신 ORM(Object Relational Mapping)을 이용합니다. ORM을 활용하면 파이썬 문법만으로도 데이터베이스를 다룰 수 있습니다. 개발자가 쿼리를 직접 작성하지 않아도 데이터베이스의 데이터를 처리를 할 수 있습니다.



- 쿼리를 이용한 새 데이터 삽입 예

```sql
insert into qeustion(subject, content) values ("안녕하세요.", "가입 인사드립니다^^")
insert into qeustion(subject, content) values ("질문 있습니다.", "ORM이 궁금합니다")
```



- ORM을 이용한 새 데이터 삽입 예

  코드에서 `Question`은 파이썬 클래스이며, 데이터를 관리하는 데 사용하는 ORM 클래스를 모델이라고 합니다.

```python
question1 = Question(subject="안녕하세요", content="가입 인사드립니다 ^^")
db.session.add(question1)

question2 = Question(subject="질문 있습니다", content="ORM이 궁금합니다")
ad.session.add(question2)
```



#### ORM 라이브러리 사용하기

플라스크의 대표적인 ORM 라이브러리인 SQLAlchemy 를 사용합니다. 또한 **파이썬 모델을 이용해 테이블을 생성하고 컬럼을 추가하는 등의 작업을 할 수 있게 해주는 Flask-Migrate 라이브러리를 함께 사용**합니다. 다음과 같이 가상 환경에서 설치를 진행합니다.

```powershell
(base) c:/.../myproject> pip install Flask-Migrate
```



#### 설정 파일 추가하기

파이보에 ORM 적용을 위해 설정을 해야 하는데  `config.py` 파일 안에 작성합니다. 먼저 루트 디렉터리에 `config.py` 파일을 생성하고 다음과 같이 작성합니다.

```python
import os

BASE_DIR = os.path.dirname(__file__)

SQLALCHMY_DATEBASE_URI = "sqlite:///{}".format(os.path.join(BASE_DIR, "pybo.db"))
SQLALCHMY_TRACK_MODIFICATIONS = False
```

`SQLALCHMY_DATEBASE_URI` 는 데이터베이스 접속 주소를 의미합니다. `SQLALCHMY_TRACK_MODIFICATIONS` 는 SQLAlchemy의 이벤트를 처리하는 옵션입니다. 파이보에 필요하지 않으므로 `False`로 비활성화했습니다. 작성된 내용은 `pybo.db`라는 데이터베이스 파일을 프로젝트의 루트 디렉터리에 저장하는 것을 의미합니다.



#### ORM 적용하기

- `pybo/__init__.py` 파일 안에 플라스크를 실행하면 `Migrate, SQLAlchemy`를 실행할 수 있도록 임포트합니다.

```python
from flask import Flask
from flask_migrate import Migrate
from flask_sqlalchemy import SQLAlchemy

import config

db = SQLAlchemy()
migrate = Migrate()

def create_app():
    app = Flask(__name__)
    app.config.from_object(config)
    
    # ORM
	db.init_app(app)
    migrate.init_app(app, db)
    
    # 블루프린트
    from .views import main_views
    app.register_blueprint(main_views.bp)
    
    return app
```

`config.py` 파일에 작성한 내용을 `app.config` 환경 변수로 호출해야 합니다. 따라서 `app.config.from_object(config)` 작성하여 `config.py` 파일 안의 내용이 적용될 수 있도록 추가했습니다. 그리고 전역 변수인 `db, migrate`는 모두 `init_app()` 메서드로`SQLAlchemy, Migrate` 를 초기화 합니다.

해당 위의 코드는 플라스크에서 자주 사용되는 패턴입니다. `db` 객체를 `create_app` 함수 안에서 생성하면 블루프린트와 같은 다른 모듈에서 불러올 수 없습니다. 따라서 `db, migrate`와 같은 객체를 `create_app` 함수 밖에서 생성하고, 실제 객체 초기화는`create_app` 함수에서 수행합니다.



#### 데이터베이스 초기화하기

- ORM 사용할 준비를 마쳤다면, `flask db init` 명령으로 데이터베이스를 초기화합니다.

```powershell
(base) c:/.../myproject> flask db init
```

해당 명령을 수행하면 데이터베잇를 관리하는 초기 파일들을 `migrations` 라는 디렉터리에 자동으로 생성합니다. 이 때 생성되는 파일들은 `Flask-Migrate` 라이브러리에서 사용됩니다.  데이터베이스 초기화하는 `flask db init` 명령은 최 한 번만 수행됩니다. 

내부 동작에 대해 모르더라도 문제가 없으므로 넘어가고, 주로 아래 두 개의 명령을 통해서 데이터베이스에 접근합니다.

| 명령어             | 설명                                                  |
| ------------------ | ----------------------------------------------------- |
| `flask db migrate` | 모델을 새로 생성하거나 변경할 때 사용                 |
| `flask db upgrade` | 모델의 변경 내용을 실제 데이터베이스에 적용할 때 사용 |



#### 모델 만들기

- 질문 모델 속성

| 속성명        | 설명                    |
| ------------- | ----------------------- |
| `id`          | 질문 데이터의 고유 번호 |
| `subject`     | 질문 제목               |
| `content`     | 질문 내용               |
| `create_date` | 질문 작성일시           |



- 답변 모델 속성

| 속성명        | 설명                    |
| ------------- | ----------------------- |
| `id`          | 답변 데이터의 고유 번호 |
| `question_id` | 질문 데이터의 고유 번호 |
| `content`     | 답변 내용               |
| `create_date` | 답변 작성일시           |



- `c:/.../pybo/models.py`

```python
from pybo import db


class Question(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    subject = db.Column(db.String(200), nullable=False)
    content = db.Column(db.Text(), nullable=False)
    create_date = db.Column(db.DateTime(), nullable=False)


class Answer(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    question_id = db.Column(db.Integer, db.ForeignKey('question.id', ondelete='CASCADE'))
    question = db.relationship('Question', backref=db.backref('answer_set'))
    content = db.Column(db.Text(), nullable=False)
    create_date = db.Column(db.DateTime(), nullable=False)

```







