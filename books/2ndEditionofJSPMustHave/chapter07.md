# 07 액션 태그(Action Tag)

액션 태그는 일반 JSP 코드보다 HTML에 더 조화롭게 스며들고 간결합니다. 액션 태그에 익숙해지고 잘 활용하면 JSP 프로그래밍이 한결 깔끔해집니다. 



## 7.1 액션 태그란?

액션 태그(Action Tag)는 JSP 표준 태그로, **페이지 사이에서 이동을 제어하거나 자바빈을 생성할 때 주로 사용**됩니다. 특별한 선언 없이 `<jsp:태그명 />` 형태로 사용합니다. 태그처럼 사용하지만 뒤에서는 JSP가 수행됩니다. 즉, JSP 코드와 마찬가지로 웹 애플리케이션 서버(WAS)에서 처리된 후 결과만 출력되어 웹 브라우저에서 소스 보기를 해도 액션 태그는 보이지 않습니다. 액션 태그의 특징은 다음과 같습니다.

- XML 문법을 따릅니다.
- 반드시 종료 태그를 사용해야 합니다.
- 액션 태그 사이에 주석을 사용하면 에러가 발생합니다.
- 액션 태그에 속성값을 부여할 때는 표현식 `<%= %>`을 사용할 수 있습니다.

액션 태그는 용도에 따라 다음과 같이 크게 네 가지로 나눠볼 수 있습니다.

- **`<jsp:include>`**: 외부 파일을 현재 파일에 포함
- **`<jsp:forward>`** : 다른 페이지로 요청을 전달
- **`<jsp:useBean>, <jsp:setProperty>, <jsp:getProperty> `**: 자바빈즈를 생성하고 값을 설정 및 추출
- **`<jsp:param>`** : 다른 페이지로 매개변수를 전달(`<jsp:include>, <jsp:forward>` 액션 태그와 함께 사용합니다.)



## 7.2 `<jsp:include>`

`<jsp:include>` 액션 태그는 외부 JSP 파일을 현재 JSP 파일로 포함시키는 기능을 합니다. include 지시어와 비슷한 역할이지만 동작 방식에 약간의 차이가 있습니다. 이번 절에서는 둘을 비교해가며 `<jsp:include>` 액션 태그에 대해 학습합니다.



### 7.2.1 include 지시어와 `<jsp:include>` 액션 태그

| 구분         | 지시어                                                     | 액션 태그                                                    |
| ------------ | ---------------------------------------------------------- | ------------------------------------------------------------ |
| 형식         | `<%@ include file="포함할 파일의 경로">`                   | `<jsp:include page="포함할 파일의 경로" />`                  |
| 표현식       | 표현식 사용 불가                                           | 표현식 사용 가능                                             |
| 포함 방식    | 페이지 자체를 현재 페이지에 포함시킨 후<br />컴파일을 진행 | 실행의 흐름을 포함시킬 페이지로 이동시킨 후<br /> 실행한 결과를 현재 페이지에 포함시킴 |
| 변수         | 포함시킨 파일에서 생성한 변수 사용 가능                    | 포함시킨 파일에서 생성한 변수 사용 불가                      |
| page 영역    | 공유됨                                                     | 공유되지 않음                                                |
| request 영역 | 공유됨                                                     | 공유됨                                                       |

지시어와 액션 태그의 메커니즘을 자세히 살펴보면 다음과 같습니다.



**include 지시어의 동작 메커니즘**

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/0d6c6562-0466-4565-89a8-136f4f747b25)


- A.jsp 를 실행할 때, include 지시어가 가장 먼저 실행되어 B.jsp 가 포함됩니다. 그 이후에 A.jsp 코드가 실행됩니다.

include 지시어는 **페이지를 원본 그대로 현재 페이지에 먼저 포함시킨 후 컴파일**합니다. 즉, JSP 코드만 서로 다른 페이지로 **모듈화**하는 것입니다. 따라서 **include 지시어는 동일한 페이지로 인식**이 됩니다.



**`<jsp:include>` 액션 태그의 동작 매커니즘**

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/0b5af513-17ac-4f79-ab11-aba1c31264ea)


- A.jsp가 실행을 진행하다가 액션 태그를 만나면 B.jsp 이동하고 실행됩니다. B.jsp의 컴파일 결과값만 포함하고 A.jsp에게 반환되고 이어서 실행을 계속 진행합니다.

액션 태그를 사용하면 포함시킬 페이지로 요청의 흐름이 이동되고 컴파일합니다. 컴파일된 결과값은 현재 페이지에 반환됩니다. 웹 서버에서 컴파일된 JSP 코드는 모두 HTML 형식으로 변환됩니다. 그러므로 포함시킬 페이지에서 생성한 변수는 현재 페이지에서 사용할 수 없게 됩니다. **다른 페이지로 인식하므로 page 영역 역시 공유되지 않습니다.**



### 7.2.2 포함될 외부 파일 준비

두 개의 JSP 파일을 준비합니다. 변수에 저장되는 값만 다르고 모두 동일한 코드입니다.

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OuterPage</title>
</head>
<body>
    <h2>외부 파일 1</h2>
    <%
    String newVar1 = "고구려 세운 동명왕";
    %>
    <ul>
        <li>page 영역 속성 : <%= pageContext.getAttribute("pAttr") %></li>
        <li>request 영역 속성 : <%= request.getAttribute("rAttr") %></li>
    </ul>
</body>
</html>
```



```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OuterPage</title>
</head>
<body>
    <h2>외부 파일 2</h2>
    <%
    String newVar2 = "백제 온조왕";
    %>
    <ul>
        <li>page 영역 속성 : <%= pageContext.getAttribute("pAttr") %></li>
        <li>request 영역 속성 : <%= request.getAttribute("rAttr") %></li>
    </ul>
</body>
</html>
```



### 7.2.3 포함 방식에 따른 차이 확인

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
// 포함할 파일의 경로를 변수에 저장
String outerPath1 = "./inc/OuterPage1.jsp";
String outerPath2 = "./inc/OuterPage2.jsp";

// page 영역과 request 영역에 속성 저장 
pageContext.setAttribute("pAttr", "동명왕");
request.setAttribute("rAttr", "온조왕");
%>
<html>
<head><title>지시어와 액션 태그 동작 방식 비교</title></head>
<body>
    <h2>지시어와 액션 태그 동작 방식 비교</h2>
    <!-- 지시어 방식 -->
    <h3>지시어로 페이지 포함하기</h3>
    <%@ include file="./inc/OuterPage1.jsp"%>
    <%--@ include file="<%=outerPath1OuterPage1%>" --%>
    <p>외부 파일에 선언한 변수 : <%=newVar1%></p>

    <!-- 액션 태그 방식 -->
    <h3>액션 태그로 페이지 포함하기</h3>
    <jsp:include page="./inc/OuterPage2.jsp" />
    <jsp:include page="<%=outerPath2%>" />
    <p>외부 파일에 선언한 변수 : <%--=newVar2 --%></p>
</body>
</html>

```



다음 결과 그림으로 지시어와 액션 태그 동작 방식에 대해 이해할 수 있습니다. 포함된 외부 파일 두 개와 출력하는 파일을 각 특징에 따라  학습합니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/747f3f73-6947-4f4e-9d7e-9326a3f97bc2)






## 7.3  `<jsp:forward>`

포워드는 **현재 페이지에 들어온 요청을 다음 페이지로 보내는 기능**입니다. 예를 들어 **RequestDispatcher** 객체의 forward() 메서드는 다음과 같이 사용합니다. 

```java
RequestDispatcher reqeustDispatcher = request.getRequestDispatcher("포워드할 파일의 경로");
requestDispatcher.forward(request, response);
```

`<jsp:forward>` 액션 태그 또한 RequestDispatcher 객체와 동일한 기능을 수행합니다. 버퍼를 **buffer="none"으로 설정해 버퍼를 사용하지 않도록 한다면 포워드를 사용할 수 없습니다.** 또한 포워드는 다음 페이지로 요청을 전달하는 것이 목적이므로 이동된 페이지와 **request 영역을 공유**합니다. 그리고 **URL이 변경되지 않는 특징**이 있습니다. 다음 예제는 page 영역과 request 영역에 설정한 속성이 포워드된 페이지에도 공유되는지 확인합니다.



```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
pageContext.setAttribute("pAttr", "김유신"); 
request.setAttribute("rAttr", "계백"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>액션 태그 - forward</title>
</head>
<body>
    <h2>액션 태그를 이용한 포워딩</h2> 
    <jsp:forward page="/07ActionTag/ForwardSub.jsp" /> 
</body>
</html>
```



**`<jsp:forward page="/07ActionTag/ForwardSub.jsp" /> `**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>액션 태그 - forward</title>
</head>
<body>
    <h2>포워드 결과</h2>
    <ul>
        <li>
            page 영역 : <%= pageContext.getAttribute("pAttr") %>
        </li>
        <li>
            request 영역 : <%= request.getAttribute("rAttr") %> 
        </li>
    </ul>
</body>
</html>
```

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/18a184bd-0aa1-487d-ac15-0f740c7eda4e)




포워드된 페이지는 이전 페이지와는 서로 다른 페이지이므로, 페이지별로 생성되는 page 영역은 공유되지 않습니다. 포워드는 요청을 전달하므로 request 영역은 공유됩니다. 정리하자면, page 영역은 페이지별로 고유하게 생성됩니다. request 영역은 하나의 요청을 공유하는 모든 페이지에 공유됩니다. 포워드는 요청을 전달하는 매커니즘으로 포워드로 연결된 페이지들은 모두 같은 요청을 공유합니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/87574688-0221-4a1d-8f76-9c5857d1cd82)




## 7.4 `<jsp:useBean>, <jsp:setProperty>, <jsp:getProperty>`

`<jsp:useBean>` 액션 태그는 자바빈즈(JavaBeans)를 생성하거나 설정할 때 사용합니다. 자바빈즈는 데이터를 저장하기 위한 멤버 변수(속성)와 게터/세터 메서드로만 이루어진 클래스를 말합니다. 지난 시간에 학습한 자바빈즈 개발 규약은 다음과 같습니다.

- 자바빈즈는 기본(default) 패키지 이외의 패키지에 속해 있어야 합니다.
- 멤버 변수(속성, 프로퍼티)의 접근 지정자는 private으로 선언합니다.
- 기본 생성자가 있어야 합니다.
- 멤버 변수에 접근할 수 있는 게터/세터 메서드가 있어야 합니다.
- 게터/세터 메서드의 접근 지정자는 public으로 선언합니다.



### 7.4.1 자바빈즈 생성

`<jsp:useBean>`으로 자바빈즈를 생성할 때는 기본 생성자를 호출합니다. 따라서 해당 클래스에 기본 생성자가 없으면 오류가 발생합니다. 기본 생성자는 자바빈즈 규약에서 필수입니다. `<jsp:useBean>` 액션 태그의 사용법은 다음과 같습니다.

```jsp
<jsp:useBean id="자바빈즈 이름" class="사용할 패키지와 클래스명" scope="저장될 영역" />
```

- `id`: 자바빈즈 객체의 이름을 지정(같은 id로 이미 생성된 객체가 있다면 해당 객체를 사용하고, 없다면 새로 생성합니다.)
- `class`: 사용하려는 자바빈즈 객체의 실제 패키지명과 클래스명을 지정
- `scope`: 자바빈즈가 저장될 내장 객체 영역을 지정(생략한다면 기본값으로 page 영역이 지정됩니다.)

 

### 7.4.2 멤버 값 설정/추출

`<jsp:setProperty>` 는 생성된 자바빈즈에 멤버 변수의 값을 설정할 수 있습니다. 

```jsp
<jsp:setProperty name="자바빈즈 이름" property="속성명(멤버 변수)" value="설정할 값" />
```

- `name`: `<jsp:useBean>`의 id 속성에 지정한 자바빈즈의 이름을 지정(인스턴스 변수를 지정하는 것과 동일합니다.)
- `property`: 자바빈즈의 멤버 변수명을 지정(이름을 명시하는 대신 `property="*"`라고 쓰면 form 하위 요소와 일치하는 자바빈즈의 모든 속성에 사용자가 전송한 값이 설정됩니다. 이 때에는 value 속성을 생략할 수 있습니다.)
- `value`: 멤버 변수에 설정할 값을 지정합니다.



자바빈즈의 값을 추출할 때는 `<jsp:getProperty>`를 사용합니다. 속성은 `<jsp:setProperty>`와 동일합니다.

```jsp
<jsp:getProperty name="자바빈즈 이름" property="속성명(멤버 변수)" />
```



액션 태그로 자바빈즈를 생성할 때는 기본 생성자를 사용하고, 값을 설정할 때는 세터, 값을 추출할 때는 게터 메서드를 사용한다는 점을 꼭 기억하세요. 멤버 변수 값을 설정하고 추출하는 예제입니다. 

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>액션 태그 - UseBean</title>
</head>
<body>
    <h2>useBean 액션 태그</h2>
    <h3>자바빈즈 생성하기</h3>
    <jsp:useBean id="person" class="common.Person" scope="request" />

    <h3>setProperty 액션 태그로 자바빈즈 속성 지정하기</h3>
    <jsp:setProperty name="person" property="name" value="임꺽정" /> 
    <jsp:setProperty name="person" property="age" value="41" /> 

    <h3>getProperty 액션 태그로 자바빈즈 속성 읽기</h3>
    <ul>
        <li>이름 : <jsp:getProperty name="person" property="name" /></li> 
        <li>나이 : <jsp:getProperty name="person" property="age" /></li> 
    </ul>
</body>
</html>
```

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/1e010bd1-ab17-4e33-bc07-29260187361e)




### 7.4.3 와일드카드로 폼값을 한 번에 설정하기

자바빈즈에 값을 설정할 때 property 속성에 와일드카드(`*`)를 사용하면 `<form>` 태그를 통해 전송되는 모든 폼값을 한 번에 자바비늦에 입력할 수 있습니다.

**폼값을 전송하는 페이지**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>액션 태그 - UseBean</title></head>
<body>
    <h2>액션 태그로 폼값 한 번에 받기</h2> 
    <form method="post" action="UseBeanAction.jsp"> 
        이름 : <input type="text" name="name" /> <br /> 
        나이 : <input type="text" name="age" /> <br /> 
        <input type="submit" value="폼값 전송" />
    </form>
</body>
</html>
```



**폼값을 받는 페이지**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head><title>액션 태그 - UseBean</title></head>
<body>
    <h3>액션 태그로 폼값 한 번에 받기</h3>
    <jsp:useBean id="person" class="common.Person" />  
    <jsp:setProperty property="*" name="person" />  
    <ul>
        <li>이름 : <jsp:getProperty name="person" property="name" /></li>  
        <li>나이 : <jsp:getProperty name="person" property="age" /></li> 
    </ul>
</body>
</html>
```

`<jsp:useBean>` 액션 태그를 사용하여 Person 클래스로 자바빈즈를 생성합니다. scope 속성이 없으므로 기본값으로 page 영역에 저장됩니다. `<jsp:setProperty>`로 폼값을 자바빈즈에 설정했습니다. 그런데 property 속성에 멤버 변수 이름 대신 와일드카드(`*`)를 사용합니다. 이렇게 하면 전송된 폼 값이 자바빈즈에 한 번에 저장됩니다.

폼값은 항상 input 태그의 name 속성에 지정한 이름을 통해 전송됩니다. name 속성에 지정한 이름과 Person 클래스의 멤버 변수 이름이 같았기 때문에 폼값을 한 번에 저장할 수 있는 것입니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/115eebc4-bb64-4578-b7a7-238f4943d774)


![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/628117fc-5958-4f6a-afda-cafb51b4341c)


![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/af0484c0-e17a-4874-93b5-b112dfd331ea)




### 7.4.4 한글 인코딩 문제 해결

post 방식으로 전송된 한글이 깨졌을 때 **request 내장 객체의 setCharacterEncoding()으로 인코딩 처리**를 한 적이 있습니다. 이 방식은 폼값을 받는 모든 페이지에서 반복 설정해야 하므로 불편합니다. 그래서 이번에는 한 번의 설정으로 모든 페이지에 적용할 수 있는 방법을 학습합니다. web.xml에 필터를 다음과 같이 작성합니다.

```xml
  <filter>
    <filter-name>SetCharEncoding</filter-name>
    <filter-class>org.apache.catalina.filters.SetCharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>utf-8</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>SetCharEncoding</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
```

`<filter>` 태그 안에는 필터 이름, 사용할 클래스, 클래스에 전달할 매개변수를 설정합니다. 위의 코드에서 필터의 이름은 SetCharEncoding입니다. 인코딩 방식은 UTF-8입니다. `<filter-mapping>` 태그로 요청 URL과 `<filter>` 태그를 매핑합니다. URL 패턴을 `/*`으로, 해당 웹 애플리케이션으로 들어오는 모든 요청에 SetCharEncoding 이름의 필터를 적용하라는 뜻입니다. web.xml을 저장하고 서버를 재시작하면 필터 적용이 됩니다.



## 7.5 `<jsp:param>`

`<jsp:param>`는 **`<jsp:include>, <jsp:forward>`를 사용할 때 다른 페이지에 값을 전달해주는 액션 태그**입니다. 전달할 수 있는 값은 String뿐 입니다. 다른 타입의 객체를 전달할 때는 내장 객체의 영역을 이용해야 합니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/fc93bccc-d06f-4a92-a359-5d4b03aa4d3c)


포워드와 포함 관계로 연결된 총 세 개의 페이지를 만들 것이며, 이 페이지들 사이에서 데이터를 공유하는 다양한 방법을 학습합니다. 자바빈즈 객체를 request 영역에 생성하는 방법으로 이전 시간에 학습한 방법입니다. `<jsp:param>` 액션 태그로 활용하는 방법에 대해 학습습을 진행합니다.



### 7.5.1 포워드되는 페이지로 매개변수 전달하기 +  7.5.2 인클루드 페이지로 매개변수 전달하기

**메인 페이지(포워드하는 페이지)**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//request.setCharacterEncoding("UTF-8");
String pValue = "방랑시인";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>액션 태그 - param</title>
</head>
<body>
    <jsp:useBean id="person" class="common.Person" scope="request" />
    <jsp:setProperty name="person" property="name" value="김삿갓" /> 
    <jsp:setProperty name="person" property="age" value="56" /> 
    <jsp:forward page="ParamForward.jsp?param1=김병연"> 
        <jsp:param name="param2" value="경기도 양주" />
        <jsp:param name="param3" value="<%=pValue%>" />
    </jsp:forward> 
</body>
</html>
```



**포워드되는 페이지(`<jsp:forward page="ParamForward.jsp?param1=김병연"> `)**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>액션 태그 - param</title>
</head>
<body>
    <jsp:useBean id="person" class="common.Person" scope="request" /> 
    <h2>포워드된 페이지에서 매개변수 확인</h2>
    <ul>
        <li><jsp:getProperty name="person" property="name" /></li> 
        <li>나이 : <jsp:getProperty name="person" property="age" /></li>
        <li>본명 : <%= request.getParameter("param1") %></li> 
        <li>출생 : <%= request.getParameter("param2") %></li>
        <li>특징 : <%= request.getParameter("param3") %></li>
    </ul>
    <jsp:include page="inc/ParamInclude.jsp">
        <jsp:param name="loc1" value="강원도 영월" />
        <jsp:param name="loc2" value="김삿갓면" />
    </jsp:include>
</body>
</html>
```

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/1dcf4dc9-fb20-4835-b487-a1ba4692e284)


포워드 이전의 내용은 삭제되고, 포워드된 ParamForward.jsp의 내용이 화면에 출력됩니다. reuqest 영역은 포워드된 페이지도 공유가 가능합니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/9fb6fd65-521b-4e1d-9765-81c377084a7c)


전달 방식과 상관없이 매개변수들이 모두 request 영역에 생서되어 항상 request.getParameter() 메서드로 값을 가져오고 있습니다. rquest 영역이란 하나의 요청을 처리하는 과정에서 거쳐가는 모든 페이지에서 공유하는 공간입니다. 페이지 사이의 매개변수는 모두 request 영역에 생성됩니다. 

**핵심 요약**

- **`<jsp:include>`**
  - 특정 페이지를 현재 페이지에 포함시킬 때 사용합니다. 
    include 지시어와 비슷한 기능이지만 동작 방식에 차이가 있으므로 사용에 주의해야 합니다.
- **`<jsp:include>`**
  - 요청을 전달하는 포워드에 사용됩니다.
- **`<jsp:include>, <jsp:setProperty>, <jsp:getProperty>`**
  - 자바빈즈를 생성하거나 값을 설정 및 출력할 때 사용됩니다. 
    특히 와일드카드 `*` 를 사용하면 전송되는 폼값을 한 번에 받을 수 있습니다.
- **`<jsp:include>`**
  - 인클루드나 포워드 시 매개변수를 넘길 때 사용합니다.
