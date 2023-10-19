# 02 내장 객체(Implicit Object)



## 2.1 내장 객체란?

JSP에서 내장 객체는 **기본적인 요청과 응답, 화면 출력 등을 수행할 수 있는 객체를 의미**합니다. 예를 들어, 클라이언트가 서버로 요청을 보낸다고 가정합니다. 서버는 요청에 해당하는 응답을 클라이언트로 전송합니다. 이때 JSP의 내장 객체로 인해 요청과 응답 혹은 HTTP 헤더 등의 정보를 쉽게 다룰 수 있습니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/14f34ebd-538f-4160-9ffc-8760921c6b6c)


내장 객체는 **JSP 페이지가 실행될 때 JSP 컨테이너가 자동으로 생성**합니다. JSP는 실행될 때 자바 파일인 서블릿으로 변환될 때 **_jspService() 메서드가 생성되고 컴파일을 수행**합니다. 생성된 _jspService() 메서드 안에는 컨테이너가 미리 선언해놓은 참조 변수에 의해 생성되는 것을 확인할 수 있습니다.

`_jspService()` 메서드 내부는 다음과 같이 생성됩니다.

```java
public void _jspService() {
    // ... 생략 ...
    final jakarta.servlet.jsp.PageContext pageContext;
    jakarta.servlet http.HttpSession session = null;
    final jakrata.servlet.ServletContext application;
    final jakarta.servlet.ServletConfig config;
    // ...
}
```

내장 객체의 특징은 아래와 같습니다.

- 컨테이너가 미리 선언해놓은 참조 변수를 이용해 사용
- 별도의 객체 생성 없이 각 내장 객체의 메서드 사용이 가능
- `<% 스크립틀릿 %>, <%= 표현식 %>` 에서만 사용이 가능
- `<%! 선언부 %>` 에서는 즉시 사용이 불가능하고, 매개변수로 전달하여 사용 가능



**내장 객체의 종류(9 가지)**

| 내장 객체     | 타입                   | 설명                                              |
| ------------- | ---------------------- | ------------------------------------------------- |
| `request`     | `.HttpServletRequest`  | 클라이언트의 요청 정보를 저장                     |
| `response`    | `.HttpServletResponse` | 클라이언트의 요청에 대한 응답 정보를 저장         |
| `out`         | `JspWriter`            | JSP 페이지에 출력할 내용을 담는 출력 스트림       |
| `session`     | `HttpSession`          | 웹 브라우저 정보를 유지하기 위한 세션 정보를 저장 |
| `application` | `ServletContext`       | 웹 애플리케이션 관련 컨텍스트 정보를 저장         |
| `pageContext` | `PageContext`          | JSP 페이지에 대한 정보를 저장                     |
| `page`        | `Object`               | JSP 페이지를 구현한 자바 클래스의 인스턴스        |
| `config`      | `ServletConfig`        | JSP 페이지에 대한 설정 정보를 저장                |
| `exception`   | `Throwable`            | 예외가 발생한 경우에 사용                         |



## 2.2 request 객체

request 내장 객체는 JSP 에서 가장 많이 사용하는 객체입니다. **클라이언트(주로 웹 브라우저)가 전송한 요청 정보를 담고 있는 객체**입니다. 주요 기능은 다음과 같습니다.

1. 클라이언트와 서버에 대한 정보 읽기
2. 클라이언트가 전송한 요청 매개변수에 대한 정보 읽기
3. 요청 헤더 및 쿠키 정보 읽기



### 2.2.1 클라이언트와 서버에 대한 정보 읽기

클라이언트는 웹 브라우저를 통해 서버 측으로 요청을 합니다. 이때 요청은 **GET 혹은 POST 으로 구분**되고, **요청 URL, 포트 번호, 쿼리스트링 등을 명시**할 수 있습니다. JSP 에서는 request 내장 객체로 해당 정보들을 얻어올 수 있습니다.

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>내장 객체 - request</title>
</head>
<body>
<h2>1. 클라이언트와 서버의 환경 정보 읽기</h2>
<ul>
    <li>데이터 전송 방식 : <%=request.getMethod()%></li>
    <li>URL : <%= request.getRequestURL()%></li>
    <li>URI : <%=request.getRequestURI()%></li>
    <li>프로토콜 : <%= request.getProtocol()%></li>
    <li>서버명 : <%= request.getServerName()%></li>
    <li>서버 포트 : <%= request.getServerPort()%></li>
    <li>클라이언트 IP 주소 : <%= request.getRemoteAddr()%></li>
    <li>쿼리스트링 : <%=request.getQueryString()%></li>
    <li>전송된 값 1 : <%= request.getParameter("eng")%></li>
    <li>전송된 값 2 : <%= request.getParameter("han")%></li>
</ul>
</body>
</html>
```

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/58eb7c27-2464-4ac6-97b2-46434b34502b)


- GET 방식은 주소표시줄에 매개변수가 노출되고, 매개변수가 쿼리스트링으로 전달됩니다.
- POST 방식은 주소표시줄에는 경로 외에는 어떠한 것도 출력되지 않고, 쿼리스트링은 null 이 됩니다.

### 2.2.2 클라이언트의 요청 매개변수 읽기

`<from>` 태그 하위 요소를 통해 입력한 값들을 서버로 전송할 수 있습니다. 서버로 전송된 값은 서버에서 읽은 후 변수에 저장하고, 적절한 처리를 위해 컨트롤러(Controller)나 모델(Model)로 전달됩니다. 대표적으로 회원가입이나 로그인 등을 예로 들 수 있습니다.

- `request.getParameter()` 는 **전송되는 값이 하나인 경우 사용**합니다.
  주로 type 속성이 text, radio, password 인 경우 사용되고, checkbox 인 경우에 선택값이 하나인 경우에 사용합니다.
- `request.getParameterValues()` 는 **전송되는 값이 여러 개인 경우 사용**합니다.
  checkbox 인 경우 대부분 값을 여러 개 선택하기 위해 사용하고, 보통 값이 2개 이상이므로 String 배열을 반환합니다.
- `textarea` 태그는 텍스트에 여러 줄을 입력할 수 있습니다.
  그래서 출력에서는 `<br>` 태그로 변환이 필요하기 때문에 입력된 엔터키 `\r\n` 를 `<br>` 태그로 변경합니다.

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>내장 객체 - request </title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    String id = request.getParameter("id");
    String sex = request.getParameter("sex");
    String[] favo = request.getParameterValues("favo");
    String favoStr = "";
    if(favo != null) {
        for (int i = 0; i < favo.length; i++) {
            favoStr += favo[i] + " ";
        }
    }
    String intro = request.getParameter("intro").replace("\r\n", "<br/>");
%>
<ul>
    <li>아이디 : <%= id %></li>
    <li>성별 : <%= sex %></li>
    <li>관심사항 : <%= favoStr %></li>
    <li>자기소개 : <%= intro %></li>
</ul>
</body>
</html>
```



### 2.2.3 HTTP 요청 헤더 정보 읽기

HTTP 프로토콜은 헤더에 부가적인 정보를 담도록 하고 있습니다. 웹 브라우저의 종류나 선호하는 언어 등 일반적인 HTML 문서 데이터 외의 추가 정보를 서버와 클라이언트가 교환할 수 있도록 문서의 선두에 삽입할 수 있습니다.

- `getHeaderName()` 메서드는 모든 요청 헤더의 이름을 반환(반환 타입은 `Enumeration` 타입)
- `hasMoreElements()` 메서드로 출력할 요청 헤더명이 있는지 확인이 가능
- `nextElement()` 메서드로 요청 헤더의 이름을 얻을 수 있고,
  `request.getHeader()` 메서드에 헤더의 이름을 매개변수로 전달해서 헤더값을 얻을 수 있음

```jsp
<%@ page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head><title>내장 객체 - request</title></head>
<body>
    <h2>3. 요청 헤더 정보 출력하기</h2>
    <%
    Enumeration headers = request.getHeaderNames();  
    while (headers.hasMoreElements()) {  
        String headerName = (String)headers.nextElement();  
        String headerValue = request.getHeader(headerName); 
        out.print("헤더명 : " + headerName + ", 헤더값 : " + headerValue + "<br/>");
    }
    %>
    <p>이 파일을 직접 실행하면 referer 정보는 출력되지 않습니다.</p>
</body>
</html>
```



![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/bb5f7352-abda-4160-90f0-43cce564216e)




- **user-agent** : 웹 브라우저의 종류 확인이 가능합니다.
- **referer** : 웹을 서핑하면서 링크를 통해 다른 사이트로 방문 시 남는 흔적을 의미합니다.
  보통 리퍼러는 웹 사이트 방문객이 어떤 경로로 접속하였는지 알아볼 때 유용합니다.
- **cookie** : 요청 헤더를 통해 쿠키를 확인할 수 있습니다.



## 2.3 response 객체

request 내장 객체가 클라이언트의 요청 정보를 저장하는 역할을 수행했다면, response 내장 객체는 그와 반대로 요청에 대한 응답을 웹 브라우저로 보내주는 역할을 수행합니다. 주요 기능으로는 **페이지 이동을 위한 리다이렉트(redirect)와 HTTP 헤더에 응답 헤더 추가**가 있습니다. 이 두 기능 외에도 몇 가지가 더 있지만 JSP 에서는 거의 사용하지 않습니다. 

### 2.3.1 sendRedirect() 로 페이지 이동하기

페이지 이동을 위해 HTML은 `<a`> 태그를, JS 에서는 `location` 객체를 사용합니다.
JSP 에서는 **response 내장 객체의 `sendRedirect()` 메서드를 사용**합니다.

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head><title>내장 객체 - response</title></head>
<body>
<h2>1. 로그인 폼</h2>
<%
    String loginErr = request.getParameter("loginErr");
    if (loginErr != null) out.print("로그인 실패");
%>
<form action="./ResponseLogin.jsp" method="post">
    아이디 : <input type="text" name="user_id" /><br />
    패스워드 : <input type="text" name="user_pwd" /><br />
    <input type="submit" value="로그인" />
</form>

<h2>2. HTTP 응답 헤더 설정하기</h2>
<form action="./ResponseHeader.jsp" method="get">
    날짜 형식 : <input type="text" name="add_date" value="2022-12-20 09:00" /><br />
    숫자 형식 : <input type="text" name="add_int" value="8282" /><br />
    문자 형식 : <input type="text" name="add_str" value="홍길동" /><br />
    <input type="submit" value="응답 헤더 설정 & 출력" />
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head><title>내장 객체 - Response ResponseLogin.jsp</title></head>
<body>
<%
    String id = request.getParameter("user_id");
    String pwd = request.getParameter("user_pwd");
    if (id.equalsIgnoreCase("must") && pwd.equalsIgnoreCase("1234")) {
        response.sendRedirect("ResponseWelcome.jsp");
    }
    else {
        request.getRequestDispatcher("ResponseMain.jsp?loginErr=1")
                .forward(request, response);
    }
%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head><title>내장 객체 - response ResponseWelcome.jsp</title></head>
<body>
<h2>로그인 성공</h2>
</body>
</html>
```



### 2.3.2 HTTP 헤더에 응답 헤더 추가하기

response 내장 객체는 **응답 헤더에 정보를 추가하는 기능을 제공**합니다. 정보 추가용 메서드는 **add 계열과 set 계열**이 있습니다.

- **add 계열**은 헤더값을 새로 추가할 때 사용
- **set 계열**은 기존의 헤더를 수정할 때 사용

```jsp
<%@ page import="java.util.Collection"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    // 응답 헤더에 추가할 값 준비 
    SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    long add_date = s.parse(request.getParameter("add_date")).getTime();
    int add_int = Integer.parseInt(request.getParameter("add_int"));
    String add_str = request.getParameter("add_str");

// 응답 헤더에 값 추가
    response.addDateHeader("myBirthday", add_date);
    response.addIntHeader("myNumber", add_int);
    response.addIntHeader("myNumber", 1004); // 추가
    response.addHeader("myName", add_str);
    response.setHeader("myName", "안중근");  // 수정
%>
<html>
<head><title>내장 객체 - response ResponseHeader.jsp</title></head>
<body>
<h2>응답 헤더 정보 출력하기</h2>
<%
    Collection<String> headerNames = response.getHeaderNames();
    for (String hName : headerNames) {
        String hValue = response.getHeader(hName);
%>
<li><%= hName %> : <%= hValue %></li>
<%
    }
%>

<h2>myNumber만 출력하기</h2>
<%
    Collection<String> myNumber = response.getHeaders("myNumber");
    for (String myNum : myNumber) {
%>
<li>myNumber : <%= myNum %></li>
<%
    }
%>
</body>
</html>
```



## 2.4 out 객체

out 내장 객체는 **웹 브라우저에 변수 등의 값을 출력할 때 주로 사용**합니다. 그러나 **보통 표현식 `<%= %>` 으로 작성이 더 편리합니다.**  보통 사용하는 경우는 out 내장 객체를 보통 스크립틀릿 내에서 변수를 웹 브라우저에 출력해야 할때 사용합니다.

