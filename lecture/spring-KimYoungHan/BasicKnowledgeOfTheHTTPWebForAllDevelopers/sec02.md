# 섹션2. URI와 웹 브라우저 요청 흐름

## **1. URI**

#### **URI? URL? URN?** 



![img](https://blog.kakaocdn.net/dn/kPGOp/btruiRt1cgH/0vfLnjsJ3oeLBNzbbC0YAK/img.png)![img](https://blog.kakaocdn.net/dn/PkVTg/btrurTLa2ZV/opBefuHEFePSVcVIseeKJk/img.png)

URI 내부에 URL, URN이 포함되어 있다.



> URI는 로케이터(Locator), 이름(Name) 또는 둘다 추가로 분류될 수 있다. 

#### **URI (Uniform Resource Identifier)** 

- **Uniform** : 리소스 식별하는 통일된 방식이다. 
- **Resource** : URI로 식별할 수 있는 모든 걸 자원이라고 한다. 웹 브라우저에 있는 HTML의 파일 것만 자원을 뜻하는 게 아니라 실시간 교통 정보 등등 이런것도 자원이라고 한다.
- I**denrifier** : 다른 항목과 구분하는 데 필요한 정보이다. 사람을 식별할 때 주민등록번호를 식별 하는 것처럼 말한다.

 

#### **URL (Uniform Resource Locator)** 

- **Locator** : 리소스가 있는 위치를 지정한다. 

 

#### **URN (Uniform Resource Name)**

- **Name** : 리소스에 이름을 부여한다. 

 

위치는 변할 수 있지만 이름은 변하지 않는다. URN이 이름으로 실제 리소스가 결과 나오는게 매핑 되어야 하는데 찾기가 어렵다.

 

#### **URI 전체 문법**

> scheme://[userinfo@]host[:port][/path][?query][#fragment]
> https://www.google.com:443/search?q=hello&hl=ko

- **scheme**

 

주로 프로토콜 사용한다. 어떤 방식으로 자원에 접근할 것인가 하는 클라이언트와 서버 간의 약속 규칙이라고 보면 된다. 

http : 80 / https : 443 / ftp : 20, 21 주로 사용한다.

\* https는 http에 강력한 보안 추가로 적용된거다. (HTTP Secure)

 

- **userinfo**

 

URL에 사용자정보를 포함해서 인증해야 할 때 있는데 거의 사용하지 않는다.

 

- **host**

 

호스트명이라고 한다. 보통 도메인명이나 IP 주소를 직접 사용 가능하다.

 

- **port**

 

접속 포트이다. 일반적은 웹 브라우저에서는 생략을 많이 하지만 특정 서버에 따로 접근 할 때는 port를 입력을 한다.

- **path**

 

> /home/file1.jpg  ➡️  home 이라는 경로에 file1.jpg가 있다.
> /members  ➡️  회원들에 대한 정보를 보여주는 경로이다.
> /members/100, /items/iphone12  ➡️ 100번의 회원의 정보, 아이템 중에 아이폰12 정보 경로이다.

리소스가 있는 경로이자 계층적 구조로 되어 있다. 

 

- **query**

 

key와 value 형태로 데이터가 들어가 있다.

> **?**keyA=valueA**&**keyB=valueB

query는 ?로 시작하고 &로 추가적으로 query string를 입력한다. 숫자를 적어도 다 문자 형태로 넘긴다 해서 query string이라고 부른다. 

 

- **fragment**

 

> https://docs.spring.io/spring-boot/docs/current/reference/html/getting-
> started.html**#getting-started-introducing-spring-boot**

HTML 내부에서 중간에 이동하고 싶을 때 북마크 등에 사용한다. 잘 사용하지 않고 서버에 전송하는 정보 아니다.

------

## **2. 웹 브라우저 요청 흐름**

#### **웹 브라우저가 어떻게 동작을 할까?**



![img](https://blog.kakaocdn.net/dn/l4uCZ/btrul2pccd6/b0sR1YbNKkxytflzUKumzK/img.png)



1. URL을 입력한다. 
2. DNS 서버로 IP를 찾아내고 생략된 PORT는 scheme로 찾아낸다. 
3. 웹 브라우저가 HTTP 요청 메시지가 생성된다. 



![img](https://blog.kakaocdn.net/dn/4rsaX/btruvviyacp/7ZXnLsK7SBsJzwCdv6BP81/img.png)



4. SOCKET 라이브러리를 통해서 TCP/IP로 IP와 PORT 정보를 찾은 거를 3 way handshake 방식으로 서버랑 연결을 한다.
5. HTTP 요청 메시지는 OS에 있는 TCP/IP 계층으로 전달한다.
6. TCP/IP 계층에서 HTTP 요청 메시지에 패킷으로 감싼다.



![img](https://blog.kakaocdn.net/dn/bZaEBe/btruolBKBHO/NejGzfQ140hlOqo8lSWvKk/img.png)



7. 웹 브라우저가 만든 요청 패킷을 서버에서 도착하면 패킷을 열어서 HTTP 요청 메시지를 확인해서 서버가 해석한다.



![img](https://blog.kakaocdn.net/dn/4bJ5v/btruhwjpEJn/f7SIVTLPgRLksUN2UKrtX1/img.png)



8. 서버가 HTTP 응답 메시지를 만들어서 TCP/IP 패킷을 감싸서 클라이언트에게 도착하면 패킷을 열여서 HTTP 응답 메시지를 확인해서 클라이언트가 해석한다. 



![img](https://blog.kakaocdn.net/dn/bbtnze/btrul3n4mhG/fukW864ylc8q1JQ3kcMM5K/img.png)



9. 웹 브라우저가 HTML 렌더링을 해서 클라이언트가 HTML 결과를 볼 수 있다.