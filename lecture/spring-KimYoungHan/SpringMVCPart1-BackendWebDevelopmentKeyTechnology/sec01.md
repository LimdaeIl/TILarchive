# 섹션1. 웹 애플리케이션 이해



**웹 - HTTP 기반**

- 클라이언트에서 서버로 데이터를 보내거나 반대로 서버에서 클라이언트로 데이터를 보낼 때 HTTP을 이용한다.
-  HTML, TEXT, 이미지, 음성, 영상 등 모든게 HTTP를 통해서 데이터를 주고 받는다.

**웹 서버(Web Server)**

- HTTP 기반으로 동작하는 서버로 정적 리소스(HTML, CSS, JS, 이미지, ...)를 제공하는 서버다.
- 예) nginx, apache

**웹 애플리케이션 서버(WAS - Web Application Server)**

- 웹 서버 기능 포함 + 정적 리소스 제공 가능
-  프로그램 코드(.class)를 실행해서 애플리케이션 로직 수행
-  동적 HTML, HTTP API(JSON), 서블릿, JSP, 스프링 MVC
- 예) 톰캣(Tomcat) Jetty, Undertow

**웹 서버 vs 웹 애플리케이션 서버**

- WS는 정적 리소스, WAS는 애플리케이션 로직
-  사실은 둘의 경계는 모호함
-  WS도 프로그램 코드를 실행하는 기능 포함
-  WAS는 WS 기능을 포함
-  자바는 서블릿 컨테이너 기능을 제공하면 WAS (언어마다 다름)
-  WAS는 애플리케이션 코드를 실행하는데 특화. 

 

**웹 시스템 구성 방법 1 - WAS, DB (비권장)**

- WAS, DB 만으로 시스템 구성 가능. (WAS가 정적 리소스, 애플리케이션 로직 모두 제공이 가능하기 때문)
- 여기서 WAS 역할이 많음, 서버 과부화 우려, WAS 장애시 오류 화면도 노출 불가능해짐. 

![img](https://blog.kakaocdn.net/dn/bb2kmG/btq74DLfnGY/RlNpYCsdIcjHMPmtfnwd51/img.png)



 

**웹 시스템 구성 방법 2- WS, WAS, DB**

- 정적 리소스 웹 서버가 처리
- 웹 서버는 동적인 처리가 필요하면 WAS에 요청
- WAS는 중요한 애플리케이션 로직만 전담

![img](https://blog.kakaocdn.net/dn/z1V5I/btq74gWQJX4/NXIfCXTD2lZJDWyXK59TC1/img.png)



- 효율적인 리소스 관리 가능
- 정적 리소스와 동적 리소스 구분에 따른 서버 증설 가능
-  WAS, DB 에러 시 정적 리소스로 오류화면 노출가능



---



## 서블릿

**HTML 데이터 전송**

- form 태그에 감싼 input 태그에 값을 입력하고 전송하면 웹브라우저는 HTTP 규격에 맞는 메시지를 만들고 전송한다.

![img](https://blog.kakaocdn.net/dn/bFQ7C2/btq77uNeokw/4TN3InEYxL2MEo6e2HVKZK/img.png)



**서버에서 처리해야하는 업무**

- HTTP 규격에 맞는 데이터를 받으면 서버에서는 이를 받기위해 서버 TCP/IP 연결 대기부터~~~~
- 요청을 확인하기 위한 많은 처리 과정을 진행해야한다.
- 초록 영역이 가장 의미있는 과정 

![img](https://blog.kakaocdn.net/dn/bK1WE9/btq77u7xUjt/9qy98kg6vm91mTtmpJuFMk/img.png)

- 단순히 이름과 나이를 저장하기 위해 데이터를 보냈을 뿐인데 로직을 실행하기 위한 전후처리 과정이 엄청나다. 
- 여기서 서블릿이 등장하게 되는데 서블릿은 위의 의미있는 비즈니스 로직 전후처리를 모두 지원해준다.

 

**서블릿**

```java
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 애플리케이션 로직
    }
}
```

- 서블릿은 단순하다. 위의 코드처럼 구성되었으며 urlPatterns에 맞게 호출이 된다.
- HTTP **요청이면 HttpServletRequest로 사용**한다.
- HTTP **응답이면 HttpServletResponse로 제공**한다. 
- HTTP 스펙을 편리하게 사용할 수 있다. (기본적인 HTTP 스펙을 알고 있어야한다.)

 

**서블릿 흐름**

![img](https://blog.kakaocdn.net/dn/oG1Ng/btq77t8K1IF/mtOYkc5E05qCDa3bIPd2kk/img.png)



**HTTP 요청, 응답 흐름**

1. WAS는 Request, Response 객체를 생성해서 서블릿 객체를 호출
2. 개발자는 Request 객체에서 HTTP 요청 정보를 꺼내서 사용
3. 개발자는 Response 객체에 HTTP 응답 정보를 입력
4. WAS는 Response 객체의 내용으로 HTTP 응답 정보를 생성

 

**서블릿 컨테이너**

- 톰캣처럼 서블릿을 지원하는 WAS를 서블릿 컨테이너라고 함
- 서블릿 컨테이너는 서블릿 객체를 생성, 초기화, 호출, 종료하는 생명주기를 관리
-  서블릿 객체는 싱글톤으로 관리
-  요청마다 생성은 낭비
-  최초 로딩 시점에 객체를 생성해 놓고 재활용
-  고객의 모든 요청은 동일한 인스턴스에 접근
-  공유 변수 사용 주의
-  서블릿 컨테이너 종료 시 함께 종료
- JSP도 서블릿으로 변환 되어서 사용
-  동시에 요청을 위한 멀티 쓰레드 처리 지원



---



## 동시 요청 - 멀티 쓰레드

**쓰레드**

- 애플리케이션 코드를 순차적으로 실행하는 건 쓰레드
-  자바 메인 메서드를 실행하면 main 쓰레드가 실행

```java
public static void main(String[] args) { }
```

- 쓰레드가 없다면 애플리케이션 실행이 불가능
-  쓰레드는 한번에 하나의 코드 라인만 수행
-  동시 처리가 필요하면 쓰레드가 추가로 생성

 

**단일 요청 - 쓰레드 하나 사용**

![img](https://blog.kakaocdn.net/dn/byOuUr/btq746Uy3fJ/t5DgggKEo2TWUCwjYKKMu1/img.png)

![img](https://blog.kakaocdn.net/dn/bjAGC7/btq79En2hCC/Bv8szK35VLgnbTRnwbGPw0/img.png)

- 요청이 들어오면 쓰레드에 연결되고 쓰레드는 서블릿을 호출한다. 서블릿에서 작업 후 응답을 보낸다.
-  하지만 여러 요청이 들어온다면?

 

**다중 요청 - 쓰레드 하나 사용**

![img](https://blog.kakaocdn.net/dn/bbEeVG/btq79LHqYfA/OO1zkisy3nf9efZeGRKpak/img.png)



- 요청1이 진행 중에 요청2가 들어오면 요청2는 요청1이 끝날때까지 대기한다.
- 하지만 요청1 작업이 길어지거나 문제가 생기면 요청1,2 모두 죽게되는 상황이 발생한다. (타임아웃 등)



![img](https://blog.kakaocdn.net/dn/cZHixE/btq79jq3NHs/46u2kWbjDf57HIubrLRwlK/img.png)



 

**요청마다 쓰레드 생성**

위의 상황을 방지하고자 요청마다 쓰레드를 생성한다.

장점

1. 동시 요청 처리 가능
2. 리소스(CPU, 메모리)가 허용할 때 까지 처리가능
3. 하나의 쓰레드가 지연되어도 다른 쓰레드는 문제없이 동작

단점

1. 쓰레드 생성비용이 비싸고, 고객 요청마다 생성하면 응답 속도가 느려진다.
2. 컨텍스트 스위치 비용이 발생
3. 쓰레드 생성 제한이 없다. 요청이 많으면 리소스 초과로 서버가 죽을 수 있다.



![img](https://blog.kakaocdn.net/dn/dJVWNx/btq75DEv71f/KpOerJ15BEaMytufHSjjLK/img.png)



 

**쓰레드 풀 (실무 팁)**

- WAS 튜닝 포인트는 최대 쓰레드(max thread)다
- 이 값이 너무 작으면 서버 리소스는 여유있지만 클라이언트는 응답 지연 받게 된다.
- 이 값이 너무 높으면 서버 리소스 임계점 초과로 서버 다운이 될 수 있다.

장애 발생시

- 클라우드면 서버를 늘리고 튜닝하면 된다.(서비스는 지속되어야한다)
- 클라우드가 아니면 열심히 튜닝해야한다.(죽으면 서비스는 중단된다.)

 

**쓰레드 풀 적정 숫자**

- 애플리케이션 로직의 복잡도, CPU, 메모리, IO 리소스 상황마다 모두 다르다.
- 성능 테스트로 파악할 수 있다.

 

**WAS의 멀티 쓰레드 지원 (핵심)**

- WAS가 멀티 쓰레드 부분을 처리한다.
- 개발자가 멀티 쓰레드 관련 코드를 신경쓰지 않아도 된다.
-  개발자는 마치 싱글 쓰레드처럼 편리하게 개발한다.
-  단, 싱글톤 객체를 주의해서 사용해야 한다. 



---



## HTML, HTTP API, CSR, SSR







