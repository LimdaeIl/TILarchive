# 플라스크 환경 구축

> 로컬 환경에서 개발을 진행하며 가상 환경은 venv 모듈을 사용합니다.
>
> 윈도우를 기준으로 환경을 구성합니다. 모든 작업 디렉토리 구조는 실습 예제와 동일하게 진행합니다.



### 1. 가상 환경 설정하기

- 다음 명령어를 파워셸(Windows PowerShell)에서 실행하여 실행 정책을 변경합니다.

```powershell
> PowerShell Set-ExcutionPolicy RemoteSigned CurrentUser
```



- 정책을 변경하고 다음 명령어를 수행합니다. 가상 환경을 생성하는 명령입니다.

```powershell
py -m venv venv
```



- 가상 환경 생성에 성공했다면, 가상 환경을 활성화를 해야 합니다. 다음 명령어를 사용합니다. 가상 환경이 활성화되면 코드의 파일 경로 앞쪽에 `(venv)` 표시가 나타납니다. 이것은 앞으로 `venv`라는 이름의 가상 환경에 진입했다는 의미를 나타냅니다.

```powershell
> venv/Scripts/Activate.ps1
```



- 가상 환경을 비활성화하여 가상 환경 사용을 중단할 수 있습니다. 이후 실습을 하려면 이 명령어는 지금 입력하지 마세요.

```powershell
deactivate
```



### 2. 플라스크 설치하기

- 실습 예제용 작업 디렉토리를 구성과 가상 환경을 설정하도록 합니다. 다음 명령은 파워셸에서 입력해야 합니다.

```powershell
> mkdir flaskbook
> cd flaskbook
> py -m venv venv
> venv/Scripts/Activate.ps1
```



- 작업 디렉토리를 생성하고 가상 환경을 활성화했다면 플라스크를 설치해야 합니다. 플라스크 버전2를 기준으로 진행합니다.

```powershell
(venv) $ pip install flask
```



### 3. 플라스크 기본 명령어

- `pip list`
  플라스크가 의존하고 있는 패키지 목록은 `pip list` 명령으로 확인할 수 있습니다. 
  설치한 시점의 의존 관계로 정해져 있는 최신판이 출력됩니다.

```powershell
(venv) PS C:...\flaskbook> pip list
Package      Version
------------ -------
blinker      1.6.2
click        8.1.6
colorama     0.4.6
Flask        2.3.2
itsdangerous 2.1.2
Jinja2       3.1.2
MarkupSafe   2.1.3
pip          23.2.1
setuptools   65.5.0
Werkzeug     2.3.6
```



- `flask run`
  플라스크의 내장 서버를 실행하는 명령어입니다. `flask run --help` 명령어로 많은 옵션을 확인할 수 있습니다. 
  대표적으로 `--host`와 `--port` 옵션으로 호스트(서버)와 포트를 지정해서 실행할 수 있습니다.



- `flask routes` 
  앱의 라우팅 정보를 출력합니다. **라우팅**이란 요청한 곳의 URL과 실제로 처리하는 함수를 연결하는 작업을 의미합니다. 

  | 항목       | 설명                                                         |
  | ---------- | ------------------------------------------------------------ |
  | `Endpoint` | URL 에 접근할 때 실행할 함수 또는 지정한 이름 `.static`은 정적 파일용의 엔드포인트로, 항상 고정으로 존재 |
  | `Methods`  | 사용할 HTTP 메서드. 지정이 없는 경우는 GET이 기본으로 된다.  |
  | `Rule`     | 사용할 URL 의 규칙                                           |



- `flask shell`

  플라스크 앱의 **컨텍스트(=실행 환경)**에서 파있너 인터랙티브 셸을 사용하고 싶은 경우에 이용합니다.

```powershell
(venv) $ flask shell
```



이것으로 실습 환경 구축은 모두 성공적으로 마무리되었습니다. 이제 다음장에서 작업 디렉터리를 생성하여 실습을 진행합니다.