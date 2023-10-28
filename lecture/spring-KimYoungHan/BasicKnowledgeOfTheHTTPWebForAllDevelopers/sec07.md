# 섹션7. HTTP 헤더 (일반 헤더)

## **1. HTTP 헤더 개요**

#### **HTTP 헤더**

> header-field = field-name ":" OWS field-values OWS



![img](https://blog.kakaocdn.net/dn/MwPLy/btruAkh7Asi/mDNIynZXnuk0HNO1lESR7K/img.png)



HTTP 전송에 필요한 메시지 바디의 내용, 메시지 바디의 크기, 압축, 인증, 요청 클라이언트, 서버 정보, 캐시 관리 등 모든 부가 정보를 헤더에 넣는다. 표준 헤더가 굉장히 많다. 

\* 필요시 임의의 헤더 추가가 가능하다. 

 

#### **HTTP 헤더 - RFC2616**



![img](https://blog.kakaocdn.net/dn/clHQ5U/btruK5YIMxS/ngqRMFNkdPfVnGU3FsKCd0/img.png)



**헤더 분류**

- General 헤더 : 요청 메시지든 응답 메시시든 구분없이 메시지 전체에 적용되는 정보이다.

> Connection: close

 

- Request 헤더 : 요청 정보

> User-Agent: Mozilla/5.0 (Macintosh;..)

 

- Reponse 헤더 : 응답 정보

> Server: Apache

 

- Entity 헤더 : Entity 바디 정보 

> Content-Type: text/html, Content-Lenth: 3423

 

#### **HTTP BODY - RFC2616**



![img](https://blog.kakaocdn.net/dn/kET8g/btruWj3Bguw/LClIUF3BocIRnwHWlKIprk/img.png)



메시지 본문은 엔티티 본문의 요청이나 응답에서 실제 데이터를 전달 하는데 사용한다. 엔티티 헤더는 엔티티 본문의 데이터를 해석할 수 있는 데이터 유형, 데이터 길이, 압축 정보 등을 제공한다. 

------

## **2. 표현**

#### **HTTP 표준 - RFC723X 변화**

엔티티를 표현으로 바뀌게 된다. 메타 데이터와 표현 데이터를 합쳐서 표현이라고 한다. 

 

#### **HTTP BODY - RFC7230**



![img](https://blog.kakaocdn.net/dn/bGamXG/btruQluUKPI/DJF5HICkcl7qyNIkKS7mC1/img.png)



메시지 본문을 통해 요청이나 응답에서 전달할 실제 데이터를 표현 데이터 전달 하는데 사용한다. 메시지 본문을 페이로드라고 부른다. 

 

#### **Content-Type**



![img](https://blog.kakaocdn.net/dn/Rw9ec/btruQlBBPO0/R9hZkBcH3j3dABPZvOZtb1/img.png)



회원 리소스를 HTML 형식으로 전송할지 JSON 형식으로 전송할지 실제 리소스는 추상적이다. DB에 있을 수도 있고 Bytecode로 어딘가 저장되어 있을 수 있고 파일로 될 수 있다. 클라이언트랑 서버 간에 실제 주고 받을 때 이해할 수 있는 뭔가를 변환해서 데이터 전달해야 된다. 이때 헤더에다가 Content-Type을 사용한다.

> text/html; charset=utf-8
> application/json
> image/png

 

#### **Content-Encoding**



![img](https://blog.kakaocdn.net/dn/bNHC0T/btruXTciPjn/A9KtvQjr79OiJTYgybc5Kk/img.png)



표현 데이터를 압축하기 위해 사용한다. 서버에서 클라이언트를 보낼 때 Content-Encoding 부가 정보를 보내줘야 무엇으로 압축되는지 알 수 있다. 데이터 전달하는 곳에서 압축 후 인코딩 추가하고 데이터를 읽는 쪽에서 인코딩 헤더의 정보로 압축해제한다.

> gzip, deflate, identity

 

#### **Content-Language**



![img](https://blog.kakaocdn.net/dn/cAneE3/btruQlVU2PM/Rki5vyGQVE2BfrtNFU1QfK/img.png)



표현 데이터의 자연 언어를 표현한다.

> ko, en, en-US

####  

#### **Content-Length**



![img](https://blog.kakaocdn.net/dn/DwqId/btruXTDn0cJ/c6vI5njW2R6BVLDuUgTB91/img.png)



표현 데이터의 길이다. Transfer-Encoding을 사용하면 Content-Lenght를 사용하면 안된다. 

------

## **3. 콘텐츠 협상**

#### **협상**

클라이언트가 원하는 표현을 달라고 서버한테 요청을 하면 서버거 클라이언트가 원하는 우선순위에 맞춰서 표현 데이틑 만들어서 보낸다.

- Accept : 클라이언트가 선호하는 미디어 타입 전달
- Accept-Charset : 클라이언트가 선호하는 문자 인코딩
- Accept-Encoding : 클라이언트가 선호하는 압축 인코딩
- Accept-Language : 클라이언트가 선호하는 자연 언어

\* 협상 헤더는 요청시에만 사용한다.

 

#### **Accept-Language 적용 전**



![img](https://blog.kakaocdn.net/dn/bsnIb1/btruWkO3KgV/4f7xPhrQezF5TfBWsy6p2k/img.png)



한국어 브라우저를 사용하고 다중 언어 지원하는 서버를 사용한다고 가정하에 한국어 브라우저는 외국 사이트를 /event로 들어가면 내부적으로 우선순위가 기본적으로 영어를 보내고 한국어도 지원된다. 클라이언트에서 서버로 보낼 때 클라이언트가 한국어인지 영어인지 아무 정보가 없다. 그러면 서버는 기본값인 영어 관련된 내용으로 한국어 브라우저로 응답을 해준다. 

 

#### **Accept-Language 적용 후**



![img](https://blog.kakaocdn.net/dn/cx25O2/btruXUbgLII/udR0wFGl4KKnbgnuiYvni0/img.png)



클라이언트가 선호하는 언어를 한국어 정보를 입력해서 서버에게 전달한다. 서버는 기본언어가 영어지만 한국어도 지원하기 때문에 클라이언트가 원하는 한국어로 넣어서 데이터를 전달한다.

 

#### **Accept-Language 복잡한 예시**



![img](https://blog.kakaocdn.net/dn/FB7zE/btruXaFplKU/ky5z0oPu8AUiOCMaodVWIK/img.png)



클라이언트가 선호하는 언어를 한국어 정보를 입력해서 서버에게 전달하는데 서버가 기본이 독일어인데 영어를 지원한다. 다중 언어 지원 서버가 한국어 지원을 하지 않아서 독일어로 보내게 된다. 

 

#### **협상과 우선순위 1**

Quality Values(q) 값으로 사용한다. 0~1로 값이 클수록 우선순위가 높고 1은 생략이 가능하다.

> GET /event
> Accept-Language:**ko-KR**,**ko;q=0.9**,**en-US;q=0.8**,**en;q=0.7**

1. **ko-KR;q=1**
2. **ko;q=0.9**
3. **en-US;q=0.8**
4. **en;q=0.7**



![img](https://blog.kakaocdn.net/dn/b3C3Dw/btruQkv2rTY/lzgTNZkv8kQI8SvmTagzyK/img.png)



클라이언트의 브라우저에서 한국어를 선호하지만 다중 언어 지원서버로 기본이 독일어고 영어를 지원한다. 서버에서는 독일어보다는 영어를 선호한다는 거를 알고 영어로 보내게 된다.

 

#### **협상과 우선순위 2**

구체적일 수록 우선순위가 높다. 

> GET /event
> Accept: **text/\***, **text/plain**, **text/plain;format=flowed**, ***/\***

1. **text/plain;format=flowed**
2. **text/plain**
3. **text/\***
4. ***/\***

 

#### **협상과 우선순위 3**

구체적인 것을 기준으로 미디어 타입과 매칭하면 된다.

| **Media Type**    | **Quality** |
| ----------------- | ----------- |
| text/html;level=1 | 1           |
| text/html         | 0.7         |
| text/plain        | 0.3         |
| image/jpeg        | 0.5         |
| text/html;level=2 | 0.4         |
| text/html;level=3 | 0.7         |

> GET /event
> Accept: text/*;q=0.3, text/html;q=0.7, text/html;level=1, text/html;level=2;q=0.4, */*;q=0.5

------

## **4. 전송 방식**

#### **단순 전송 (Content-Length)**



![img](https://blog.kakaocdn.net/dn/9TIEy/btrvqjPdPPg/HeKY2tiKVNbmVwNY1fYEk1/img.png)



Content의 길이를 지정을 해서 한 번에 요청을 하고 응답을 한다.

 

#### **압축 전송 (Content-Encoding)**



![img](https://blog.kakaocdn.net/dn/KUsbI/btrvoBCO504/KKKJVBNN0cojtb3UlDHnkk/img.png)



Content를 압축할 때 무엇을 압축되어 있는지 알아야 클라이언트에서 알고 압축을 풀 수 있다.

 

#### **분할 전송 (Transfer-Encoding)**



![img](https://blog.kakaocdn.net/dn/b4ZLaV/btrvl7oXxPr/EX2tdpt1Zwy4CChkZfDQH0/img.png)



chunked는 덩어리라는 뜻이다. 덩어리로 쪼개서 전송을 한다. 5byte로 Hello를 서버에서 클라이언트로 보낸다. 또 5byte로 World를 보내고 마지막으로 0byte로 src를 보내면 끝이라는 걸 표현한다. 분할 전송할 때는 Content-Length를 넣으면 안된다.

#### **범위 전송 (Content-Range)**



![img](https://blog.kakaocdn.net/dn/bB2NOp/btrvkGd6p44/CADGv2PIk5eNBTbdnGlmBk/img.png)



이미지를 받았는데 중간에 끊길 경우 못 받은 범위를 지정해서 다시 요청을 한다.

 

------

## **5. 일반 정보**

#### **From**

유저 에이전트의 이메일 정보이다. 일반적으로 잘 사용하지 않는다. 검색 엔진 담당자한테 사이트에 대해서 정보를 알려줄 때 연락할 수 있는 방법이 필요할 때 요청할 때 사용한다.

 

#### **Referer**



![img](https://blog.kakaocdn.net/dn/cxBYXP/btrvQhyh9BD/WErkpOLw7BF812QJzPbDK1/img.png)



현재 요청된 페이지의 이전 웹 페이지 주소이다. A에서 B로 이동하는 경우 B를 요청할 때 Referer A를 포함해서 요청한다. Referer를 사용해서 데이터 분석 할 때 유입 경로 분석을 가능하다. 

\* referer는 referrer의 오타

 

#### **User-Agent**



![img](https://blog.kakaocdn.net/dn/bcOW3R/btrvPJf1i4d/DjRKNvUaktqtwbfaYjE3ik/img.png)



클라이언트의 애플리케이션 정보이다. 사용자들이 어떤 종류의 브라우저에서 장애가 발생하는지 파악이 가능하다.

 

#### **Server**



![img](https://blog.kakaocdn.net/dn/cWx16l/btrvMdPqPQZ/IMkFCQRbr6HHo63VKoT1E0/img.png)



요청을 처리하는 ORIGIN 서버의 소프트웨어 정보이다. ORIGIN 서버는 실제 HTTP 응답을 해주는 서버이다. 

 

#### **Date**



![img](https://blog.kakaocdn.net/dn/bce6CI/btrvSe8Ty0L/QfMfnZBAZLR506KSQAMvMk/img.png)



메시지가 발생한 날짜와 시간이다. 응답에서만 사용한다. 

 

------

## **6. 특별한 정보**

#### **Host**

요청에서 사용하고 필수값이다. 다른 헤더에서는 거의 없고 Host는 필수 헤더이다. 하나의 서버가 여러 도메인을 처리해야 할 때, 하나의 IP 주소에 여러 도메인이 적용되어 있을 때 구분해줘야 한다.



![img](https://blog.kakaocdn.net/dn/81Cyy/btrvqjPk5aV/kAENISnTatfNADtkYiJA81/img.png)



가상호스트를 통해서 하나의 서버가 지금 IP : 200.200.200.2 가 있는데 서버 안에 여러 개의 애플리케이션이 다른 도메인으로 구동되어 있다. 클라이언트가 /hello 로 GET 방식으로 요청을 했는데 서버 입장에서는 /hello 와 관련된 애플리케이션에 어디로 들어갈지 몰른다. 



![img](https://blog.kakaocdn.net/dn/cDchw7/btrvnfmTS2l/p4pM7GRmIXawhutkIzMomk/img.png)



TCP/IP 통신으로 Host 헤더로 입력하면 서버에서 Host 헤더가 있기 때문에 요청에 맞는 도메인주소를 들어갈 수 있다. 

 

#### **Location**

> 201(Created) : Location 값은 요청에 의해 생성된 리소스 URI이다. 
> 3xx(Redirection) : 응답의 결과에 Location 헤더가 있으면 Location 위치로 자동 리다이렉트로 한다.

 

#### **Allow**

허용 가능한 HTTP 메서드이다.

> 405(Method Not Allowed) : 오류를 내면서 응답에 포함한다. 

GET, HEAD, PUT만 지원을 한다. 서버에서 많이 구현되지는 않는다.

 

#### **Retry-After**

유저 에이전트가 다음 요청을 하기까지 기다려야 하는 시간이다. 

> 503(Service Unavaliable) : 서비스가 언제까지 불능인지 알려준다.
> Retry-After : 날짜 표기, 초단위 표기

 

------

## **7. 인증**

#### **Authorization**

클라이언트 인증 정보를 서버에 전달할 수 있다. 어떤 인증 메커니즘인지 상관없이 인증과 관련된 값을 헤더로 제공한다. 

 

#### **WWW-Authenticate**

리소스 접근 시 필요한 인증 방법 정의한다. 

> 401(Unauthorize) : 응답과 함께 사용한다.
> WWW-Authenticate: Newauth realm="apps", type=1, title="Login to \"apps"\"", Basic realm="simple"

------

## **8. 쿠키**

#### **Set-Cookie** 

서버에서 클라이언트로 쿠키를 전달한다.

#### **Cookie**

클라이언트가 서버에서 받은 쿠키를 저장하고 HTTP 요청시 서버로 전달한다.



![img](https://blog.kakaocdn.net/dn/dD5q1y/btrvXUOD6qy/Th9yIi9xHRExKLXOP18IXk/img.png)



웹 브라우저에서 로그인 안한 사용자가 /welcome 으로 웹 페이지로 접근하면 서버에서는 손님으로 들어오게 된다.



![img](https://blog.kakaocdn.net/dn/xBT1M/btrvPK0i0qy/pfZGPaAjyKYmOBD4qQ1mu1/img.png)



/login으로 유저 정보, 패스워드 등을 POST방식으로 보내서 로그인을 하면 서버에서는 홍길동으로 로그인했다고 응답을 준다. 



![img](https://blog.kakaocdn.net/dn/c4bFxI/btrvPJtAw1Y/i3J4x7sb1naV26dx5PIOe0/img.png)



이 상태에서 다시 /welcome으로 접근하면 서버입장에서 로그인한 사용자인지 아닌지 구분할 수 있는 방법이 모른다. 이러한 이유가HTTP가 무상태 프로토콜이기 때문이다. 기본적으로 클라이언트와 서버가 요청과 응답을 주고 받으면 연결이 끊어지고 클라이언트가 다시 요청한다해도 서버는 이전 요청을 기억하지 못한다. 그래서 클라이언트와 서버는 서로 상태를 유지하지 않는다.



![img](https://blog.kakaocdn.net/dn/yfaCf/btrvQhyp9uz/SihaRcFJSllgJhzEDqg4vK/img.png)



모든 요청에 사용자 정보를 포함해서 보내면 된다. 사용자 정보를 계속 서버에 주면 서버는 홍길동으로 응답해준다. 문제점은 모든 요청과 링크의 사용자 정보를 다 포함하면 보안의 문제, 개발하기 힘들다. 브라우저가 완전히 종료하고 다시 열면 웹 스토리지에 저장을 해놓고 다시 넘기면 개발하기 힘들다.

 

#### **Cookie**



![img](https://blog.kakaocdn.net/dn/crxIJX/btrvYwGZDTH/kvwlaIfLVPVgpv7Kh8Zx1k/img.png)



웹 브라우저가 POST로 로그인을 하면 서버에서는 Set-Cookie로 홍길도 정보를 만들어서 응답을 한다. 웹 브라우저 내부에는 쿠키 저장소가 있는데 서버가 응답에서 만든 Set-Cookie로 key는 user, value는 홍길동이라고 쿠키 저장소에 저장을 한다.



![img](https://blog.kakaocdn.net/dn/GSJHT/btrvQfUZF4B/bmdTI2kOccUyxwqPptCgF1/img.png)



로그인 이후에 웹 브라우저가 /welcome에 들어오면 자동으로 웹 브라우저는 서버에 요청을 보낼 때 마다 쿠키 저장소에서 조회를 해서 홍길동이라는 HTTP 헤더를 만들어서 서버에 보낸다. 서버는 쿠키를 열어보니 홍길동이라는 걸 인지해서 URL이나 파라미터를 넣을 필요가 없다. 



![img](https://blog.kakaocdn.net/dn/cqtk8W/btrvQf1LLMt/TcsyWju32Qi1KB61DQzthk/img.png)



지정한 서버에 쿠키는 모든 요청 정보에 쿠키 정보를 자동으로 포함을 한다. 

 

#### **쿠키**

> set-cookie: **sessionId=abcde1234**; **expires**=Sat, 26-Dec-2020 00:00:00 GMT; **path**=/; **domain**=.google.com; **Secure
> **

 

- 사용처 

> \- **사용자 로그인 세션 관리** : 로그인이 성공되면 세션 Id를 서버에서 만들어서 데이터베이스에 저장을 하고 서버에
>                          클라이언트에 반환을 한다. 클라이언트는 서버에 요청할 때 마다 세션 Id를 계속 보내고 서버는
>                          세션 Id를 파악할 수 있다.
> \- **광고 정보 트래킹**

 

- 쿠키 정보는 항상 서버에 전송됨

> 네트워크 트래픽 추가 유발해서 최소한의 정보만 사용 : 세션 Id, 인증 토큰
> 서버에 전송하지 않고 웹 브라우저 내부에 데이터를 저장 : 웹 스토리지 (local Storage, session Storage) 참고

 

- 주의사항

> 보안에 민감한 데이터인 주민번호, 신용카드 번호를 저장하면 안된다.

 

#### **쿠키 - 생명주기**

> Set-Cookie: **expires**=Sat, 26-Dec-2020 04:39:21 GMT

- **expires** : 쿠키를 무제한으로 보관할 수 없다. GMT기준으로 만료일이 되면 쿠키를 자동으로 삭제한다. 

 

> Set-Cookie: **max-age**=3600

- **max-age** : 초 단위로 구성되어 있고 0이나 음수를 지정하면 쿠키가 삭제한다. 

 

#### **쿠키 - 종류**

> **세션 쿠키** : 만료 날짜를 생략하면 브라우저가 종료할 때까지 유지한다. 
> **영속 쿠키** : 만료 날짜를 입력하면 해당 날짜까지 유지한다.

####  

#### **쿠키 - 도메인**

> **명시** : 도메인을 명시를 하면 명시한 문서 기준 도메인과 서브 도메인을 포함을 해서 다 전송을 한다.
> \- example.org 지정을 해서 쿠키를 생성하면 dev.example.org도 쿠키가 같이 접근할 수 있다.
> **생략** : example.org에서 쿠키를 생성했는데 도메인을 지정하는 걸 생략을 한다.
> \- example.org에서는 쿠키를 접근할 수 있도 하위 도메인인 dev.example.org는 쿠키를 접근할 수 없다.

 

#### **쿠키 - 경로**

경로를 포함한 하위 경로 페이지만 쿠키를 접근 할 수 있다. 일반적으로 path=/ 루트로 지정한다.

> **path=/home 지정**
> /home ➡️ **가능**
> /home/level1 ➡️ **가능**
> /home/level1/level2 ➡️ **가능**
> /hello ➡️ **불가능**

 

#### **쿠키 - 보안**

> **Secure** : 쿠키는 원래 http, https를 구분하지 않고 전송을 한다. Secure를 넣으면 https인 경우에만 클라이언트에서 서버로 key를 전송한다.
> **HttpOnly** : XXS 공격을 방지하는 거다. 자바스크립트에서 접근할 수 없다. 대신에 http 전송에서만 사용할 수 있다.
> **SameSite** : XSRF 공격을 방지하는 거다. 요청하는 도메인과 쿠키에 설정된 도메인이 같은 경우에만 쿠키를 전송할 수 있다. 