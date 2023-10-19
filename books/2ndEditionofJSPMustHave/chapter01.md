# 01 JSP 기본

## 1.0 JSP 기본 용어 정리

**JSP(Java Server Pages)**

- 동적인 웹 페이지를 개발하기 위한 웹 프로그래밍 기술
- 자바(Java) 언어를 사용하여 서버(Server) 측에서 웹 페이지들(Paegs)을 생성해 웹 브라우저로 전송

**JSP 장점**

1. 짧은 코드로 동적인 웹 페이지를 생성
2. 기본적인 예외는 자동으로 처리
3. 많은 확장 라이브러리를 사용
4. 스레드 기반으로 실행되어 시스템 자원을 절약

**JSP 활용 사례**: JSP는 기업용 자바 기술의 집합체인 Java EE(Java Platform, Enterprise Edition)의 핵심 요소이며, Java EE는 대한민국 정부 표준 프레임워크입니다. 따라서 정부나 공기업 주도의 사업 등 대규모 기업용 시스템 구축에 주로 사용됩니다.

**서버(Server)**: 웹에서 서비스를 제공하는 컴퓨터 시스템입니다.

**웹 서버(Web Server)**: 사용자로부터 HTTP를 통해 요청을 받거나, 웹 컨테이너가 전달해준 결과물을 정적인 페이지로 생성하여 사용자에게 응답해주는 소프트웨어입니다.(웹 페이지는 주로 HTML, CSS, JS 등으로 구성됩니다.)

**웹 컨테이너(Web Container)**: 웹 서버가 전송해준 요청을 기초로 동적인 페이지를 생성하여 웹 서버로 전송합니다. 사용자마다 다른 결과를 응답할 수 있기 때문에 동적인 역할을 수행합니다. 대표적으로 로그인 기능이 있습니다.

**WAS(Web Application Server)**: 웹 애플리케이션이 실행될 수 있는 환경을 제공하는 소프트웨어로, 컴퓨터에서 운영체제와 비슷한 역할을 수행하는 소프트웨어라고 할 수 있습니다. 웹 서버와 웹 컨테이너를 포함한 개념으로 대표적으로 톰캣(Tomcat)이 있으며, 웹로직(WebLogic), 웹스피어(WebShpere) 등의 제품이 있습니다.

**HTTP(Hyper Text Transfer Protocol) / HTTPS(HTTP Secure)**: 보통 www라고 줄여 쓰는 월드 와이드 웹(World Wide Web)에서 웹 서버와 사용자 사이의 통신을 위해 사용하는 통신 프로토콜입니다. 사용자가 요청하면 웹 서버가 응답하는 단순한 구조의 프로토콜로, HTTPS는 암호화된 HTTP입니다.

**프로토콜(Protocol)**: 네트워크를 통해 컴퓨터들이 정보를 주고받는 절차 혹은 통신 규약입니다. HTTP 또한 프로토콜의 한 종류이고, FTP, SMTP 등이 있습니다.

**포트(Port)**: 컴퓨터 사이에서 데이터를 주고받을 수 있는 통로를 의미합니다. 인터넷에서는 IP 주소를 통해 서버 컴퓨터의 위치를 파악합니다. 그 이후에 서버 컴퓨터에서 제공하는 수많은 서비스 중에서 요청에 알맞은 특정 서비스는 포트 번호를 통해 알 수 있습니다. 따라서 인터넷 상의 모든 서비스는 IP주소와 함께 포트 번호까지 지정해야 제대로 요청을 전달할 수 있습니다. 대표적으로 HTTP는 80번 포트를, 보안이 적용된 HTTPS는 443번 포트를 사용합니다.



## 1.1 동적 웹 페이지로의 여정과 JSP

### 1.1.1 정적 웹 페이지와 동적 웹 페이지

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231011143513485.png" alt="image-20231011143513485" style="zoom:80%;" />

**정적 웹 페이지(Static Web Page)**
웹 서버에 '저장되어 있는 파일 그대로' 웹 프라우저에 전송해 출력하는 가장 기본적인 웹 페이지입니다. 
클라이언트가 어떠한 형태로 요청을 하더라도 같은 페이지는 항상 동일한 모습을 보여주기 때문에 '정적'이란 수식어가 붙습니다.

**동적 웹 페이지(Dynamic Web Ppage)**
동일한 페이지더라도 요청에 따라 내용에 달라질 수 있는 웹 페이지입니다. 서버가 클라이언트의 요청을 해석하여 가장 적절한 웹 페이지를 생성해 보내주는 기술입니다. 따라서 클라이언트는 요청한 계정, 시각, 지역, 언어 등의 입력값에 따라 다른 페이지를 받습니다. 동적 웹 페이지 기술은 앞으로 학습할 JSP와 서블릿이 있고, 다른 기술로는 ASP와 PHP 등이 있습니다.



### 1.1.2 애플릿, 동적 웹을 향한 자바의 첫걸음

동적 웹 페이지 기술로 자바 애플릿(Java Applet)이 있었습니다. 더이상 지원하지 않습니다.



### 1.1.3 서블릿, 자바 웹 기술의 새 지평을 열다

애플릿 이후 서블릿이 등장하게 됩니다. 서블릿(Servlet)은 클라이언트의 요청을 받으면 서버에서 처리한 후, 응답으로는 결과값만 보내주는 구조입니다. 서블릿은 **자바 파일(.java)을 컴파일한 클래스 파일(.class)형태**입니다. **서블릿 파일을 실행하고 관리해주는 런타임을 서블릿 컨테이너**라고 합니다. 대표적인 서블릿 컨테이너는 **아파치 톰캣(Apache Tomcat)**이 있습니다.



### 1.1.4 JSP, 자바 웹 기술의 최종 진화

서블릿으로 작성하면 너무 많은 코드가 필요하다는 단점이 있습니다. 그래서 기본을 HTML으로 하고 필요한 부분만 자바 코드를 삽입하는 형태인 JSP가 탄생하게 됩니다. JSP의 구동 방식은 다소 복잡해 보이지만, 알고 보면 JSP 파일을 서블릿으로 변환하여 서블릿을 실행하는 방식입니다. 한 번 서블릿으로 컴파일된 JSP 파일은 캐시되므로 실직적인 성능 저하 없이 개발 생산성과 유지보수 편의성을 모두 얻을 수 있습니다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231011144005377.png" alt="image-20231011144005377" style="zoom:80%;" />

**서블릿과 JSP의 주요 차이**

| 서블릿                                    | JSP                                                          |
| ----------------------------------------- | ------------------------------------------------------------ |
| 자바 코드 안에서 전체 HTML 페이지를 생성  | HTML 코드 안에서 필요한 부분만 자바 코드를 스크립트 형태로 추가 |
| 변수 선언 및 초기화가 반드시 선행         | 자주 쓰이는 기능을 내장 객체로 제공하여 즉시 사용이 가능     |
| **컨트롤러(Controller)**를 생성할 때 사용 | 처리된 결과를 보여주는 **뷰(View)**를 만들 때 사용           |



### 1.1.5 오늘날의 웹 사이트

정적 웹 페이지와 동적 웹페이즈를 혼합된 형태로 개발합니다.



## 1.2 JSP 파일 기본 구조

JSP를 통해 생성한 HelloJSP.jsp 파일의 전체 코드입니다. HTML 파일에 몇 가지 요소가 추가된 형태입니다. 크게 보면 지시와 스크립트 요소가 있으며, 스크립트 요소는 다시 세 가지(선언부, 표현식, 스크립틀릿)으로 나뉩니다. 지시어는 해당 JSP 페이지의 처리 방법을 JSP 엔진에 '지시'해주는 역할을 하며, 스크립트 요소는 HTML 파일 중간에 자바 코드를 삽입할 때 사용합니다.

**JSP로 작성된 코드**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%!
String str1 = "JSP";
String str2 = "안녕하세요..!!";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HelloJSP</title>
</head>
<body>
    <h2>처음 만들어보는 <%= str1 %></h2>
    <p>
        <%
        out.println(str2 + str1 + "입니다. 열공합시다^^*");
        %>
    </p>
</body>
</html>

```



**지시어(`<%@ 지시어 %>`) **

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
```



**스크립트 요소(선언부, `<%! 선언부 %>`) **

```jsp
<%!
String str1 = "JSP";
String str2 = "안녕하세요..!!";
%>
```



**스크립트 요소(표현식, `<%= 표현식 %>`)**

```jsp
<h2>처음 만들어보는 <%= str1 %></h2>
```



**스크립트 요소(스크립틀릿, `<% 스크립틀릿 %>`)**

```jsp
<%
out.println(str2 + str1 + "입니다. 열공합시다^^*");
%>
```



## 1.3 지시어(Directive)

지시어는 JSP 페이지를 자바(서블릿) 코드로 변환하는 데 필요한 정보를 JSP 엔진에 알려줍니다. 주로 스크립트 언어나 인코딩 방식 등을 설정합니다. 지시자 혹은 디렉티브라고 부르기도 합니다. 기본 구문은 다음과 같습니다. 

**지시어 기본 구문**

```jsp
<%@ 지시어종류 속성1="값1" 속성2="값2" ... %>
```



**지시어종류**

- `<%@ page 지시어 %>` : JSP 페이지에 대한 정보를 설정
- `<%@ include 지시어 %>` : 외부 파일을 현재 JSP 페이지에 포함
- `<%@ taglib 지시어 %>` : 표현 언어에서 사용할 자바 클래스나 JSTL을 선언



### 1.3.1 page 지시어

page 지시어는 JSP 페이지에 대한 정보를 설정합니다. 예를 들어 문서의 타입, 에러 페이지, MIME 타입과 같은 정보를 설정합니다. MIME(Multipurpose Internet Mail Extensions)은 이메일과 함께 동봉할 파일을 텍스트 문자로 전환해서 이메일 시스템을 통해 전달하기 위해 개발되었기 때문에 이름에 Internet Mail Extension가 포함되었습니다. 간단하게 MIME은 **전자 우편을 위한 인터넷 표준 포맷**입니다.



**page 지시어의 속성들**

| 속성                            | 내용                                                         | 기본값     |
| ------------------------------- | ------------------------------------------------------------ | ---------- |
| info                            | 페이지에 대한 설명을 입력합니다.                             | 없음       |
| language                        | 페이지에서 사용할 스크립팅 언어를 지정합니다.                | Java       |
| contentType                     | 페이지에서 생성할 MIME 타입을 지정합니다.                    | 없음       |
| pageEncoding                    | charset과 같이 인코딩을 지정합니다.                          | ISO-8859-1 |
| import                          | 페이지에서 사용할 자바 패키지와 클래스를 지정합니다.         | 없음       |
| session                         | 세션 사용 여부를 지정합니다.                                 | true       |
| buffer                          | 출력 버퍼의 크기를 지정합니다.(사용 안하면 "none")           | 8KB        |
| autoFlush                       | 출력 버퍼가 모두 채워졌을 때 자동으로 비울 지를 결정합니다.<br />buffer 속성이 none일 때, autoFlush를 false로 지정하면 에러가 발생합니다. | true       |
| trimDirective <br />Whitespaces | 지시어 선언으로 인한 공백을 제거할지 여부를 지정합니다.      | false      |
| errorPage                       | 해당 페이지에서 에러가 발생했을 때 에러 발생 여부를 보여줄 페이지를 지정합니다. | 없음       |
| isErrorPage                     | 해당 페이지가 에러를 처리할지 여부를 지정합니다.             | false      |



**language, contentType, pageEncoding 속성**

캐릭터셋과 인코딩의 기본값은 ISO-8859-1 으로 영어와 서유럽어 문자만 포함하고 있기 때문에 한글은 제대로 출력되지 않습니다. 
따라서 한글 표기를 위해 다국어를 지원하는 UTF-8 을 주로 사용합니다. 



**page 지시어 기본 구조**

```jsp
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
```

- `language` : 스크립팅 언어는 자바를 사용
- `contentTpye` : 문서의 타입. (MIME 타입은 `text/html`, 캐릭터셋은 `UTF-8`)
- `pageEncoding` : 소스 코드의 인코딩 방식은 `UTF-8`



**import 속성**
자바에서 외부 클래스를 사용하기 위해 사용하는 것처럼 JSP 파일에서도 필요한 외부 클래스를 사용하기 위해 import를 사용합니다. 



**import 속성으로 외부 클래스 불러오기**

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%!
    String str1 = "JSP";
    String str2 = "안녕하세요..";
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>지시어 - import 속성</title>
</head>
<body>
<%
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String todayStr = dateFormat.format(date);
    out.println("오늘 날짜 : " + todayStr);
%>
</body>
</html>
```

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231011150947616.png" alt="image-20231011150947616" style="zoom:80%;" />



**errorPage, isErrorPage 속성**

자세한 내용은 생략하고 넘어갑니다. 어떠한 내용인지만 확인하세요.

| 속성          | 내용                                                         | 기본값 |
| ------------- | ------------------------------------------------------------ | ------ |
| `errorPage`   | 해당 페이지에서 에러가 발생했을 때 에러 발생 여부를 보여줄 페이지를 지정 | 없음   |
| `isErrorPage` | 해당 페이지가 에러를 처리할 지 여부를 지정                   | false  |

###  

### 1.3.2 include 지시어

반복되는 부분을 별도의 파일에 작성해두고 필요한 페이지에서 include 지시어로 포함이 가능합니다.

```jsp
<%@ include file="포함할 파일의 경로" %>
```

 

**공통 UI 요소를 담은 JSP 파일(포함될 파일)**

아래 코드는 **IncludeFile.jsp** 안에 작성된 JSP 파일입니다. 모든 웹 페이지에 오늘 날짜와 내일 날짜를 웹 브라우저에 출력하기 위해 공통적으로 출력될 수 있도록 파일을 분리했습니다. 오늘 날짜, 내일 날짜를 출력하고 싶은 웹 페이지에 include 지시어로 접근이 가능합니다.

```jsp
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.LocalDate" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
LocalDate today = LocalDate.now(); // 오늘 날짜
LocalDateTime tomorrow = LocalDateTime.now().plusDays(1); // 내일 날짜
%>
```



**다른 JSP 파일을 포함하는 JSP 파일**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="IncludeFile.jsp" %> 

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>include 지시어</title>
    </head>
    <body>
        <%
        out.println("오늘 날짜 : " + today);
        out.println("<br/>");
        out.println("내일 날짜 : " + tomorrow);
        %>
    </body>
</html>
```

##  

## 1.4 스크립트 요소(Script Elements)

스크립트 요소는 JSP 에서 자바 코드를 직접 작성할 수 있도록 합니다. 용도에 따라 선언부, 스크립틀릿, 표현식이 있습니다.

### 1.4.1 선언부(Declaration)

**선언부에서는 스크립틀릿이나 표현식에서 사용할 멤버 변수나 메서드를 선언합니다.**
서블릿으로 변환 시 `__jspService()` 메서드의 '외부'에 선언됩니다.

```jsp
<%! 메서드 선언 %>
```



### 1.4.2 스크립틀릿(Scriptlet)

**JSP 페이지가 요청을 받을 때 실행돼야 할 자바 코드를 작성하는 영역입니다.**
서블릿으로 변환 시 `__jspService()` 메서드 '내부'에 그대로 선언됩니다.

```jsp
<% 자바 코드 %>
```



### 1.4.3 표현식(Expression)

**주로 변수의 값을 간단하게 출력할 때 사용합니다.**

```jsp
<%= 자바 표현식 %>
```