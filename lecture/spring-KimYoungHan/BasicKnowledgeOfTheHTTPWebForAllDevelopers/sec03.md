# 섹션3. HTTP 기본

## **1. 모든 것이 HTTP**

#### **HTTP(HyperText Transfer Protocol)**

문서 간의 링크를 통해서 하이퍼텍스트 문서를 통해서 연결하는 프로토콜이다. HTTP 프토토콜에 HTML, TEXT, IMAGE, 음성, 영상, 파일, JSON, XML (API) 등 모든 형태의 데이터를 담아서 전송이 가능하다. 심지어 서버간에 데이터를 주고 받을 때도 사용한다. 

 

#### **HTTP 역사**

1. HTTP/0.9 (1991년) : GET 메서드만 지원, HTTP 헤더X
2. HTTP/1.0 (1996년) : 메서드, 헤더 추가
3. HTTP/1.1 (1997년) : 가장 많이 사용하고 우리에게 가장 중요한 버전이다. 

\* RFC2068 (1997년) ➡️ RFC2616 (1999년) ➡️ RFC7230~7235 (2014년)

4. HTTP/2 (2015년) : 성능 개선
5. HTTP/3 (진행중) : TCP 대신에 UDP 사용, 성능 개선

 

#### **HTTP 기반 프로토콜**

HTTP/1.1, HTTP/2 같은 경우에는 TCP 프로토콜 위에서 동작을 한다. HTTP/3은 UDP 프로토콜 기반으로 개발이 되어 있다. TCP는 3 way handshake도 해야 되고 기본적으로 데이터도 많고 매커니즘 자체가 속도가 느린 편이다. 그래서 UDP 프로토콜 위에 애플리케이션 단계에서 성능을 최적화하도록 새로 설계한다. 



![img](https://blog.kakaocdn.net/dn/xWbw1/btruvuxjVjJ/gnqR5GrOk4kg88pxiQOJ61/img.png)개발자 도구에서 Network 탭의 Protocol에서 어떤 버전의 HTTP를 사용하는지 알 수 있다.



앞으로 HTTP/2, HTTP/3도 굉장히 급속도로 커지고 있다. 

 

#### **HTTP 특징**

HTTP 프로토콜은 기본적으로 클라이언트 서버 구조로 동작한다. 무상태 프로토콜(Stateless)이고 비연결성이라는 특징이 있다. HTTP 메시지를 통해서 통신을 하고 단순하고 확장 가능하다.

------

## **2. 클라이언트 서버 구조**

#### **Request Response 구조**



![img](https://blog.kakaocdn.net/dn/Gsuxt/btruqFNos6c/ciPzz1t2Iks9Z0ALr5u4kK/img.png)



단순하기 때문에 클라이언트와 서버 구조로 되어 있다. HTTP는 클라이언트가 HTTP 메시지를 통해서 서버에 요청을 보내고 클라이언트는 서버에 응답이 올 때 기다린다. 서버가 요청에 대한 결과를 만들어서 응답이 오면 그 응답 결과를 열어서 클라이언트가 동작한다. 

 

#### **역할**

원래는 클라이언트와 서버가 하나로 되었다가 개념적으로 분리가 되면서 클라이언트는 UI, 사용성에 집중하고 서버는 비즈니스 로직이랑 데이터에 집중한다. 이슈가 발생해도 서로의 역할이 달라 이슈에 대한 영향을 미치지 않고 독립적으로 이슈를 대응하면서 진화할 수 있다.  

------

## **3. Stateful, Stateless**

#### **상태 유지 (Stateful)**



![img](https://blog.kakaocdn.net/dn/w9nSu/btrulS0odlZ/boEAlEmPKkVIdk36HQwylk/img.png)![img](https://blog.kakaocdn.net/dn/bVwPVN/btruujixQpJ/Yuk0qOuEr6t65Ipkor7Euk/img.png)



서버가 클라이언트의 상태를 보존한다. 클라이언트가 상품을 구입할 때 상품 정보와 결제 정보를 매칭된 서버로 계속 유지해야 되서 서버를 늘릴 수가 없다. 중간에 유지해야할 서버가 장애가 발생하면 다른 서버로 바뀌게 되면 클라이언트가 다시 정보를 요청을 해야 된다. 

#### **무상태 (Stateless)**



![img](https://blog.kakaocdn.net/dn/rofnm/btruwHQLvH9/gLIQmffjOFEf90PzLoGgI0/img.png)![img](https://blog.kakaocdn.net/dn/dqUx7U/btruxiXJKL4/lCc5aqczrngwNRJkHCYGx1/img.png)



서버가 클라이언트의 상태를 보존하지 않는다. 클라이언트가 상품을 구입할 때 애초에 필요한 상품 정보와 결제 정보를 담아서 요청을 하면 서버에서는 상태를 보존하지 않고 응답만 한다. 중간에 서버가 장애가 발생해도 클라이언트가 필요한 정보들을 이미 담아 있어서 다른 서버에 요청 할 수 있다. 

 

#### **무상태 스케일 아웃** 



![img](https://blog.kakaocdn.net/dn/cxVaFv/btrup8Pacb9/fRk0P9jFwJ6Bv5KD6MqGlk/img.png)



로그인 없이 검색만 할 경우 검색 서버에 트래픽이 많이 올라가도 검색 서버에 클라이언트의 상태를 유지되지 않아서 서버를 많이 늘릴 수 있다. 클라이언트, 서버 아키텍처에서는 엄청난 확장성이 가져와 무한한 서버 증식을 할 수 있다. 

#### **상태 유지와 무상태의 한계**

1. 상태 유지의 한계

- 로그인 해야 되는 경우는 로그인한 사용자가 로그인했다는 상태를 서버에 유지해야 한다.
- 브라우저에서 쿠키와 서버의 세션을 같이 조합해서 상태를 유지하는 기능을 쓴다. 서버에 세션이 날아가거나 세션 서버가 죽어버리면 전체적으로 로그인이 풀려버리게 된다.
- 상태유지는 최소한으로만 사용한다. 

 

2. 무상태의 한계

- 로그인할 필요 없는 단순한 소개 페이지일 때는 상태를 유지할 필요가 없어서 설계하기가 쉽다.
- 클라이언트가 전송할 때 필요한 정보를 담아야 되서 데이터량이 많다.

 

------

## **4. 비 연결성(connectionless)**

#### **연결을 유지하는 모델**



![img](https://blog.kakaocdn.net/dn/bcAd1t/btruqFGOKq9/tQrP1zFHUZGZ1mFiCZOb41/img.png)



여러 클라이언트에서 서버로 응답을 요청하면 서버는 요청이 들어온 클라이언트 마다 연결을 유지해서 상태를 저장한다. 클라이언트가 많아 질수록 연결을 유지하는 서버의 자원이 계속 소모되는 단점이 있다.

 

#### **연결을 유지하지 않는 모델**



![img](https://blog.kakaocdn.net/dn/stW6v/btrupIcENtz/webEvC2VGT7k9kaaL0T3DK/img.png)



클라이언트가 요청할 때마다 서버는 응답만 보내주고 연결을 종료하기 때문에 서버가 최소한의 자원으로 유지할 수 있다.

 

#### **비연결성**

HTTP는 기본적으로 연결을 유지하지 않는 모델이다. 왜냐하면 초 단위에의 이하의 빠른 속도로 응답을 한다. 1시간 동안 수천명이 서비스를 사용해도 실제 서버에서 동시에 처리하는 요청은 별로 없기 때문에 서버 입장에서는 자원의 가용성이 훨씬 높다.

 

#### **비연결성의 한계**

TCP/IP 연결을 새로 맺을 때 마다 3 way handshake 시간 추가가 되서 클라이언트 입장에서는 느리다.

웹 브라우저로 사이트를 요청하면 HTML, CSS, Javascript, 추가 이미지 등 수많은 자원이 함께 다운로드할 때 연결하고 끊고 또 연결하고 또 끊고 하면 비효율적이다. 

 

#### **비연결성의 극복**

HTTP는 기본적으로 지속 연결(Persistent Connection)로 문제 해결한다. HTTP/2, HTTP/3 에서는 더 많은 최적화되어 있다. 

 

#### **HTTP 초기**



![img](https://blog.kakaocdn.net/dn/X7lqF/btrurUKs6pC/Kq19khPBDR9hkERrND6Hm0/img.png)



연결하고 요청, 응답 하고 종료 또 연결하고 요청, 응답 하고 종료 반복적으로 하면 속도가 느리다.

 

#### **HTTP 지속 연결(Persistent Connections)**



![img](https://blog.kakaocdn.net/dn/bfnQSL/btruqFfIYzQ/amyD16ekkwceJDHqH4jKck/img.png)



연결을 유지 하면서 종료하기 전까지 내부 매커니즘으로 요청하고 응답을 다 하고 종료하면 속도가 빠르다. 

 

#### **Stateless를 기억하자**

서버 개발자들이 어려워하는 업무가 같은 타이밍에 발생하는 대용량 트래픽발생할 때가 어렵다. 이럴 때는 Sateless하게 설계하는 게 제일 중요하다. 

------

## **5. HTTP 메시지**

#### **HTTP 메시지 구조**



![img](https://blog.kakaocdn.net/dn/cPQKdi/btruomgJwW8/DTNk2NY9fKuyqxbiEWEwSk/img.png)



HTTP 메시지 구조에서 start-line, header, empty line(CRLF), message body로 4가지 구조로 나눈다.

 

#### **HTTP 요청 메시지**

**start-line : request-line**

> GET /search?q=hello&hl=ko HTTP/1.1
> method SP request-target SP HTTP-version CRLF
> Host: www.google.com
> field-name ":" OWS field-value OWS

- **method (메서드)**

 

HTTP method의 종류가 GET, POST, PUT, DELETE 등이 있고 서버가 수행해야 할 동작을 지정한다.

- GET : 서버에게 리소스 조회
- POST : 서버가 리소스를 요청 내역 처리 

 

- **request-target (요청 대상)**

> /absolute-pate[?query]
> /절대경로[?쿼리]

보통 절대경로로 ' / '로 시작하고 쿼리를 합친다.

\* http://...?x=y 같이 다른 유형의 경로지정 방법도 있다.

 

-  **HTTP-version (HTTP 버전)**

 

#### **HTTP 응답 메시지**

**start-line : status-line**

> HTTP/1.1 200 OK
> HTTP-version SP status-code SP reason-phrase CRLF

-  **HTTP-version (HTTP 버전)**
- **status-code (HTTP 상태 코드)**

클라이언트가 보낸 요청이 성공했는지 실패했는지 나타내는 상태이다. 

- 200 : 성공
- 400 : 클라이언트 요청 오류
- 500 : 서버 내부 오류

 

- **reason-phrase (이유 문구)**

사람이 이해할 수 있는 짧은 상태 코드를 읽을 수 있는 글이다.

 

**header**

> Content-Type: text/html;charset=UTF-8
> Content-Length: 3423
> field-name ":" OWS field-value OWS

HTTP 헤더의 용도는 HTTP 전송에 필요한 메시지 바디의 내용, 메시지 바디의 크기, 압축, 인증, 요청 클라이언트(브라우저) 정보, 서버 애플리케이션 정보, 캐시 관리 정도 등 모든 부가 정보가 들어가 있다. 표준 헤더가 많다. 필요하면 임의의 헤더도 추가 가능할 수 있다. 

 

- **field-name**

대소문자 구분이 없다. 

 

- **field-value**

대소문자 구분이 있다.

 

**body**

> <html>
>   <body> ... </body>
> </html>

실제 전송할 데이터가 담아있다. HTML 문서, 이미지, 영상, JSON 등 byte로 표현할 수 있는 모든 데이터 전송이 가능하다.