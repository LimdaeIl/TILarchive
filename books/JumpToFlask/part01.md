# 플라스크 개발 준비!



> **1장 학습 목적**
>
> 1. 파이썬과 플라스크를 설치하고 개발 환경을 준비
>
> 2. 플라스크 프로젝트 생성하고 첫 프로그램을 구현
>
> 3. 플라스크 서버를 실행하고 실행 결과를 확인



### 1. 플라스크(Flask)

- 2004년 오스트리아의 오픈소스 개발자 아르민 로나허(Armin Ronacher)가 만든 웹 프레임워크
- 마이크로 웹 프레임워크



#### 파이썬에 플라스크 설치하기

1. pip 최신 버전으로 설치하고 플라스크 설치를 진행해야 합니다.

```power
python -m pip install --upgrade pip
```

```powershell
pip install flask
```

- **환경 변수 설정은 별도로 정리하지 않습니다!**



#### 첫 번째 애플리케이션 만들기

- `c:projects/myproject/pybo.py`

```py	
from flask import Flask

app = Flask(__name__)

@app.route("/")
def hello_pybo():
    return "Hello, Pybo!"
```

`app = Flask(__name__)`는 플라스크 애플리케이션을 생성하는 코드입니다. `__name__` 변수에 모듈명이 담기는데, `pybo.py` 를 실행하면 `pybo.py`라는 모듈이 실행되는 것이므로 `__name__` 변수에는 `pybo` 라는 문자열이 담기게 됩니다. `@app.route`는 특정 주소에 접속하면 바로 다음 줄에 있는 함수를 호출하는 플라스크의 데코레이터입니다.



#### 플라스크 서버 실행하기

- 가상 환경에 접속한 상태에서 `flask run` 명령으로 실행할 수 있습니다. 그러나 Error 가 발생하게 되는데, 환경 변수를 설정해야 합니다. 환경 변수 설정은 `FLASK_APP` 으로 실행할 플라스크 애플리케이션을 지정할 수 있습니다. 

```powershell
(base) c:/.../myproject> flask run

Error: ...
```

플라스크는 FLASK_APP 환경 변수가 지정되지 않은 경우 자동으로 `app.py`로 기본 애플리케이션으로 인식합니다. 따라서 앞의 `pybo.py` 파일명으로 `app.py`로 지었다면 `FLASK_APP` 환경 변수를 별도로 지정하지 않아도 됩니다. 여기에서는 `FLASK_APP` 환경 변ㅅ누에 `pybo.py`를 지정하는 방법으로 환경 변수 문제를 해결할 것입니다. 그 이유에 간단하게 말하자면 순환 참조 문제를 해결하기 위해 입니다.



```powershell
(base) c:/.../myproject> set FLASK_APP=pybo
(base) c:/.../myproject> set FLASK_ENV=development

(base) c:/.../myproject> flask run
```

