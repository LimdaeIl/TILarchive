# 01장부터 02장 정리(1/2)

### 1. 파일 구조

- 1장부터 2장까지의 실습을 모두 성공적으로 마치게 될 때, 구성된 디렉터리 구조입니다.  단, 최상위 디렉터리인 root 디렉터리는 어떠한 이름을 가져도 상관이 없습니다. 각 디렉터리를 기준으로 관계가 있는 내용을 위주로 설명을 진행합니다.

```python
|-root/
|	 |- migrations/
|	 |- pybo/
|    |      |- static/
|    |              |- bootstrap.min.css
|    |              |- style.css
|    |              |- templates/
|    |                        |- question/
|    |                        |         |- question-detail.html
|    |                        |         |- question-form.html
|    |                        |         |- question-list.html
|    |                        |- base.html
|	 |- views/
|	 |      |- main_views.py
|    |      |- answer_views.py
|    |      |- question_views.py
|	 |- __init__.py 
|	 |- forms.py
|	 |- models.py
|- config.py
|- .env
|- .gitgnore
|- pybo.db
```



### 2. migrations 디렉터리

ORM을 사용하는 경우, `flask db init` 명령으로 데이터베이스를 초기화히면 `migrations` 디렉터리가 생성됩니다. 현재 프로젝트에서는 `SQLAlchemy, Migrate` 두 가지의 라이브러리를 기준으로 데이터베이스를 구성하고 있습니다.  `flask db init` 명령은 최초 한 번만 수행하면 됩니다.



**데이터베이스 관리 명령어 정리하기**

| 명령어             | 설명                                                  |
| ------------------ | ----------------------------------------------------- |
| `flask db migrate` | 모델을 새로 생성하거나 변경할 때 사용                 |
| `flask db upgrade` | 모델의 변경 내용을 실제 데이터베이스에 적용할 때 사용 |



#### 2-1. 모델 만들기

파이보에서 사용할 모델을 생성합니다. 파이보는 질문 답변 게시판이므로 질문과 답변에 해당하는 모델을 생성합니다.

**1. 질문 모델 속성**

| 속성명        | 설명                    |
| ------------- | ----------------------- |
| `id`          | 질문 데이터의 고유 번호 |
| `subject`     | 질문 제목               |
| `content`     | 질문 내용               |
| `create_date` | 질문 작성일시           |



**2. 답변 모델 속성**

| 속성명        | 설명                    |
| ------------- | ----------------------- |
| `id`          | 답변 데이터의 고유 번호 |
| `question_id` | 질문 데이터의 고유 번호 |
| `content`     | 답변 내용               |
| `create_date` | 답변 작성일시           |

`question_id`는 어떤 질문에 달린 답변인지 알아야 하므로 질문 데이터의 고유 번호가 필요로 합니다.



### 3. models.py 작성하기

- 작성한 모델을 기반으로 `Question, Answer` 두 개의 클래스로 구분합니다.
  각 클래스가 하나의 릴레이션에 해당되고, 선언한 변수들은 애트리뷰트라고 생각하면 이해하기 쉬울 것입니다. 

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



1. `pybo` 디렉터리에 `db` 를 임포트합니다. 왜냐하면, `__init__.py` 파일 안에 선언한 `db = SQLAlchemy()` 객체를 통해서 데이터베이스에 작성된 모델을 생성하기 위해서입니다.

```python
from pybo import db
```



2. `db.Column` 클래스에 어떤 속성을 전달하는지 살펴보면서 각 속성의 특징을 살펴봅니다.

```python
id = db.Column(db.Integer, primary_key=True)
```

- `db.Integer` :arrow_right: 속성의 데이터 타입 지정*
- `primary_key=True` :arrow_right: 속성을 기본 키로 지정

- `nullable=False` :arrow_right: 속성에 빈값을 허용하지 않음



**속성의 데이터 타입 지정*은 크게 4가지 속성이 있습니다. 아래의 표를 참고하세요.**

| 속성          | 설명                                               |
| ------------- | -------------------------------------------------- |
| `DB.Integer`  | 고유 번호와 같은 숫자값에 사용                     |
| `DB.String`   | 제목처럼 글자 수가 제한된 텍스트에 사용            |
| `DB.Text`     | 글 내용처럼 글자 수를 제한할 수 없는 텍스트에 사용 |
| `DB.DateTime` | 날짜와 시각에 해당할 때 사용                       |

데이터베이스에서는 `id`와 같은 특징을 가진 속성을 기본키(Primary key)라고 합니다. 플라스크는 **데이터타입이 `db.Integer`이고 기본키로 지정한 속성은 자동으로 증가하는 특징도 있어서 데이터를 저장할 때 해당 속성값을 지정하지 않아도 1씩 자동으로 증가하여 저장합니다.**



3. `question_id` 속성은 질문 모델과 연결하기 위해 추가합니다. 답변 모델은 어떤 질문에 대한 답변인지 표시해야 하므로 2단계에서 생성한 질문 모델과 연결된 속성을 포함해야 합니다. 이처럼 어떤 속성을 기존 모델과 연결하려면 `db.ForeignKey` 를 사용해야 합니다.

```python
question_id = db.Column(db.Integer, db.ForeignKey("question.id", ondelete="CASCADE"))
```

`db.ForeignKey(모델명.속성명)` 으로 연결할 모델의 속성명을 작성합니다. 따라서 `quesiton_id` 속성은 질문 모델의 `id` 속성과 연결되고 `ondelete="CASCADE"`에 의해 데이터베이스에서 쿼리를 이용하여 질문을 삭제하면 해당 질문에 달린 답변도 함께 삭제가 됩니다.



4. `question` 속성은 답변 모델에서 질문 모델을 참조하려고 추가했습니다. 예를 들어 답변 모델 객체에서 질문 모델 객체의 제목을 참조한다면, `answer.question.subject` 처럼 할 수 있습니다. 이렇게 하려면 속성을 추가할 때 `db.Column` 이 아닌 `db.relationship`을 사용해야 합니다.

```python
question = db.relationship("Question", backref=db.bvackref("answer_set"))
```

`db.relationship("Question")` 은 참조할 모델이고 `db.relationship("Question", backref=db.bvackref("answer_set"))` 에서 `backref=db.bvackref("answer_set")` 은 질문에 달린 답변을 역참조하기 위한 설정입니다. 예를 들어 어떤 질문에 해당하는 객체가 `a_qusetion`이라면 `a_question.answer_set`와 같은 코드로 해당 질문에 달린 답변을 참조할 수 있습니다.





