# 03 내장 객체의 영역(Scope)

## 3.1 내장 객체의 영역이란?

내장 객체의 영역 구분의 기준은 **각 객체가 저장되는 메모리의 유효기간**이라고 이해하면 됩니다.
**내장 객체의 영역은 총 4가지가 있습니다.**

1. **page 영역**: 동일한 페이지에서만 공유됩니다. 페이지를 벗어나면 소멸됩니다.
2. **request 영역**: 하나의 요청에 의해 호출된 페이지와 포워드(요청 전달)된 페이지까지 공유됩니다.
3. **session 영역**: 클라이언트가 처음 접속한 후 웹 브라우저를 닫을 때까지 공유됩니다.
4. **application 영역**: 한 번 저장되면 웹 애플리케이션이 종료될 때까지 유지됩니다.

 

**내장 객체의 영역별 접근 범위 및 포함 관계**

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FlYZ1n%2Fbtst0dwy5XV%2FesmB0HIPK4DkHthRd4vPh1%2Fimg.png" alt="img" style="zoom:70%;" />



내장 객체의 **네 가지 영역은 모두 사용법(API)이 동일**합니다. 가장 대표적인 세 가지 메서드는 다음과 같습니다.

```java
void setAttribute(String name, Object value)
```

- **각 영역에 속성을 저장**합니다.
- 첫 번째 인수는 **속성명**, 두 번째 인수는 **저장할 값**입니다.
- **값의 타입은 Object 이므로 모든 타입의 객체를 저장**할 수 있습니다.

 

```java
Object getAttriubte(String name)
```

- **영역에 저장된 속성을 얻어옵니다.**
- Object 타입으로 자동 형변환되어 저장되므로 원래 타입으로 **형변환 후 사용**해야 합니다.

 

```java
void removeAttribute(String name)
```

- 영역에 저장된 속성을 **삭제**합니다.
- **삭제할 속성명이 존재하지 않더라도 에러는 발생하지 않습니다.**

 

## 3.2 데이터 전송 객체(DTO) 준비

데이터 전송 객체(Data, Transfer Object, **DTO**)는 **주로 데이터를 저장하거나 전송하는 데 쓰이는 객체**로, **다른 로직 없이 순수하게 데이터만을 담고** 있습니다. 데이터만을 가지고 있는 객체라 하여 값 객체(Value Object, **VO**) 라고도 합니다. DTO 는 자바빈즈(JavaBeans) 규약에 따라 작성합니다.

자바빈즈는 **자바로 작성한 소프트웨어 컴포넌트**로, 다음의 **규약을 따르는 자바 클래스를 의미**합니다. DTO 는 일반적인 자바 클래스로 자바 파일입니다. JSP 파일이 아닙니다.

1. 자바빈즈는 기본(default) 패키지 이외의 패키지에 속해야 합니다.
2. 멤버 변수(속성)의 접근 지정자는 private 으로 선언해야 합니다.
3. 기본 생성자가 있어야 합니다.
4. 멤버 변수에 접근할 수 있는 getter / setter 메서드가 있어야 합니다.
5. getter / setter 메서드의 접근 지정자는 public 으로 선언합니다.

 

## 3.3 page 영역

page 영역은 기본적으로 **클라이언트의 요청을 처리하는 데 관여하는 JSP 페이지마다 하나씩 생성**됩니다. 그리고 이때 **각 JSP 페이지는 page 영역을 사용하기 위한 pageContext 객체를 할당** 받게 됩니다. **pageContext 객체에 저장된 정보는 해당 페이지에서만 사용할 수 있고 페이지를 벗어나면 소멸**됩니다. include 지시어로 포함한 파일은 하나의 페이지로 통합되므로 page 영역이 공유됩니다.

```jsp
<%
// 속성 저장
pageContext.setAttribute("pageInteger", 1000);
pageContext.setAttribute("pageString", "페이지 영역의 문자열");
pageContext.setAttribute("pagePerson", new Person("한석봉", 99)); 
%>
```

pageContext 객체를 통해 page 영역에 속성값을 저장할 수 있습니다. **객체가 아닌 int, float 등 기본 타입 값들은 래퍼(wrapper) 클래스로 오토박싱(auto boxing)된 후 저장**이 됩니다. 예를 들어 int 타입은 Integer, float 타입은 Float 으로 저장됩니다.



```jsp
    <%
    // 속성 읽기
    int pInteger = (Integer)(pageContext.getAttribute("pageInteger"));
    String pString = pageContext.getAttribute("pageString").toString();
    Person pPerson = (Person)(pageContext.getAttribute("pagePerson"));
    %>
    <ul>
        <li>Integer 객체 : <%= pInteger %></li>
        <li>String 객체 : <%= pString %></li>
        <li>Person 객체 : <%= pPerson.getName() %>, <%= pPerson.getAge() %></li>
    </ul>
```

pageContext 객체를 통해 page 영역에서 속성값을 얻어올 수 있습니다. 단, **모든 속성이 Object 타입으로 저장되어 있으므로 다시 원래의 타입으로 형변환**합니다. 예를 들어 처음 String 타입인 경우 toString() 메서드를 통해 문자열로 변환하여 출력할 수도 있습니다.

 

```jsp
<h2>include된 파일에서 page 영역 읽어오기</h2>
<%@ include file="PageInclude.jsp" %>
<%@ page import="common.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h4>Include 페이지</h4>
<%
int pInteger2 = (Integer)(pageContext.getAttribute("pageInteger"));
//String pString2 = pageContext.getAttribute("pageString").toString();
Person pPerson2 = (Person)(pageContext.getAttribute("pagePerson"));
%>
<ul>
    <li>Integer 객체 : <%= pInteger2 %></li>
    <li>String 객체 : <%= pageContext.getAttribute("pageString") %></li>
    <li>Person 객체 : <%= pPerson2.getName() %>, <%= pPerson2.getAge() %></li>
</ul>
```

include 지시어로 다른 JSP 파일을 포함시키는 것 또한 가능합니다. 포함된 페이지는 '같은 페이지'가 되고, page 영역이 그대로 유지됩니다. 동일하게 이전 페이지에서 만든 page 영역은 소멸됩니다. 주의해야 할 점은 page 지시어를 제외한 나머지 HTML 코드를 모두 삭제한 후 작성하도록 합니다. include 는 **문서 안에 또 다른 문서를 포함하는 형태이므로 태그가 중복될 수 있습니다.**

 

## 3.4 request 영역

클라이언트가 요청을 할 때마다 새로운 request 객체가 생성되고, 같은 요청을 처리하는 데 사용되는 모든 JSP 페이지가 공유됩니다. 따라서 **request 영역에 저장된 정보는 현재 페이지와 포워드된 페이지까지 공유**할 수 있습니다. 단, 페이지 이동 시에는 소멸되어 사용할 수 없게 됩니다. **request 영역은 하나의 요청에 대한 응답이 완료될 때 소멸하게 되므로 page 영역보다 접근 범위가 조금 더 넓습니다.**

```jsp
<%
  request.setAttribute("requestString", "request 영역의 문자열");
  request.setAttribute("requestPerson", new Person("안중근", 31)); 
%>
```

request 객체를 통해 **request 영역에 속성값을 저장**할 수 있습니다.

 

```jsp
<%
  request.removeAttribute("requestString"); 
  request.removeAttribute("requestInteger"); // 에러 없음
%>
```

request 객체를 통해 **request 영역에 저장된 속성을 삭제**합니다.

 

```jsp
<%
  Person rPerson = (Person)(request.getAttribute("requestPerson"));
%>
<ul>
  <li>String 객체 : <%= request.getAttribute("requestString") %></li>
  <li>Person 객체 : <%= rPerson.getName() %>, <%= rPerson.getAge() %></li>
</ul>
```

request 객체를 통해 **request 영역에서 속성값을 얻어올 수 있습니다.** page 영역에서 속성값을 얻어오는 것과 동일하게 request 영역에서 속성값을 얻어오는 경우에는 반드시 **형변환**이 필요합니다.

 

```jsp
<%
  request.getRequestDispatcher("RequestForward.jsp?paramHan=한글&paramEng=English")  
  .forward(request, response);
%>
request.getRequestDispatcher("포워드할 파일 경로").forward(request, response)
```

이번 장의 핵심 내용이 되는 부분으로 **포워드**입니다. request 객체를 통해 request 영역에서 포워드를 수행할 수 있습니다. 이때 새롭게 등장하는 **request.getRequestDispatcher 메서드는 request 내장 객체를 통해 실제로 포워드를 수행합니다.** 포워드는 **한 JSP 페이지에서 다른 JSP 페이지로 제어를 전달하는 것을 의미합니다.** .

1. **페이지 이동**: 포워드를 사용하여 현재 JSP 페이지에서 다른 JSP 페이지로 이동할 수 있습니다. 예를 들어, 사용자가 어떤 액션을 수행한 후 결과를 보여주기 위해 다른 JSP 페이지로 이동할 때 사용됩니다.
2. **재사용성**: 포워드를 통해 JSP 페이지 간에 코드의 재사용성을 높일 수 있습니다. 공통 요소가 있는 경우, 이를 따로 JSP 페이지로 분리하고 다른 페이지에서 포워드하여 재사용할 수 있습니다.
3. **모듈화**: 웹 애플리케이션을 더 모듈화하고 관리하기 위해 포워드를 사용할 수 있습니다. 각 모듈은 별도의 JSP 페이지로 구성되며, 필요한 경우 이러한 모듈을 포워드하여 페이지를 동적으로 구성할 수 있습니다.

포워드는 서버 측에서 이루어지므로 클라이언트는 이를 인식하지 않습니다. 다시 말해, **포워드된 JSP 페이지는 동일한 요청과 응답 객체를 공유하며, 클라이언트의 브라우저는 이 프로세스에 대한 자세한 정보를 알 수 없습니다.**

getRequestDispatcher() 의 반환 타입은 **RequestDispatcher** 타입입니다. **RequestDispatcher 객체는 요청을 다른 페이지로 넘겨주는 기능을 수행하는 객체**입니다.

```jsp
<%
  request.setCharacterEncoding("UTF-8");
  out.println(request.getParameter("paramHan"));
  out.println(request.getParameter("paramEng"));
%>
```

`request.getRequestDispatcher("RequestForward.jsp?paramHan=한글&paramEng=English").forward(request, response);`을 통해 포워드된 파일 경로로 이동한 파일의 코드 일부분 입니다. 포워드로 전달받은 매개변수에 한글이 포함되어 있다면 반드시 **setCharacterEncoding("UTF-8")** 로 인코딩해야 합니다. 포워드된 페이지는 request 객체를 통해 request 영역에서 속성값을 얻어올 수 있습니다.

 

## 3.5 session 영역

**클라이언트가 웹 브라우저를 최초로 열고난 후 닫을 때까지 요청되는 모든 페이지는 session 객체를 공유할 수 있습니다.** 세션(session)은 **클라이언트가 서버에 접속해 있는 상태 혹은 단위를 의미**합니다. 예를 들어 회원인증 후 로그인 상태를 유지하는 처리에 세션이 사용됩니다.

```jsp
<%
ArrayList<String> lists = new ArrayList<String>();
lists.add("리스트");
lists.add("컬렉션");
session.setAttribute("lists", lists);
%>   
```

ArrayList 컬렉션을 생성하고 두 개의 String 객체를 저장합니다.
그리고 **session 객체를 통해 session 영역에 ArrayList 객체를 저장**했습니다.

 

```jsp
    <%
    ArrayList<String> lists = (ArrayList<String>)session.getAttribute("lists"); 
    for (String str : lists)
        out.print(str + "<br/>");
    %> 
```

session 객체를 통해 session 영역에 저장된 속성값을 읽어올 때에도 ArrayList 컬렉션과 동일하게 **형변환**하고 출력합니다. 페이지가 이동되어도 session 영역에 저장된 속성값을 정상적으로 출력됩니다. **session 영역의 속성값을 삭제하기 위해서는 반드시 웹 브라우저 전체를 닫아야 합니다.**

 

## 3.6 application 영역

웹 애플리케이션은 **단 하나의 application 객체만 생성**하고, **클라이언트가 요청하는 모든 페이지가 application 객체를 공유**하게 됩니다. 또한 **application 객체는 웹 서버를 시작할 때 생성되며, 웹 서버를 내릴 때 삭제**됩니다.

따라서 **application 영역에 한 번 저장된 정보는 페이지를 이동하거나, 웹 브라우저를 닫았다가 새롭게 접속해도 삭제되지 않습니다.**

 

```jsp
<%
  Map<String, Person> maps = new HashMap<>();
  maps.put("actor1", new Person("이수일", 30));
  maps.put("actor2", new Person("심순애", 28));
  application.setAttribute("maps", maps);
%>
```

HashMap 컬렉션을 생성한 후 두 개의 Person 객체를 저장합니다.
그리고 application 객체를 통해 application 영역에 Map 컬렉션을 저장합니다.

 

```jsp
<%
  Map<String, Person> maps = (Map<String, Person>)application.getAttribute("maps");
  Set<String> keys = maps.keySet(); 
  for (String key : keys) {
      Person person = maps.get(key);
      out.print(String.format("이름 : %s, 나이 : %d<br/>", 
              person.getName(), person.getAge()));
  }  
%>
```

application 객체를 통해 application 영역에 저장된 속성값을 읽어올 때도 형변환합니다. Map 컬렉션 객체에 담긴 키(Key)를 통해 값을 출력합니다.

지금까지 학습한 page 영역, request 영역, session 영역, application 영역을 어디에 활용하는 지에 대해서 현재로서는 명확한 답을 드리기 어렵습니다. 이유는 뒤에 더 학습할 EL, JSTL, Servlet 을 설명하면서 제대로 된 활용법을 학습할 수 있습니다.