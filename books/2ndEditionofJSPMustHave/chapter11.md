# 11 JSP 표준 태그 라이브러리(JSTL)

## 11.1 JSTL이란?

JSTL(JSP Standard Tag Library)은 JSP에서 빈번하게 사용되는 조건문, 반복문 등을 처리해주는 태그를 모아 표준으로 만들어 놓은 라이브러리입니다. JSTL을 사용하면 스크립틀릿 없이 태그만으로 작성할 수 있기 때문에 코드가 간결해지고 읽기 편해집니다. JSTL은 5가지 종류의 태그를 지원합니다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231022150738890.png" alt="image-20231022150738890" style="zoom:80%;" />



**JSTL에서 제공하는 태그 종류**

| 태그 종류  | 기능                               | 접두어 | URI                    |
| ---------- | ---------------------------------- | ------ | ---------------------- |
| Core       | 변수 선언, 조건문/반복문, URL 처리 | c      | jakarta.tags.core      |
| Forammting | 숫자, 날짜, 시간 포맷 지정         | fmt    | jakarta.tags.fmt       |
| XML        | XML 파싱                           | x      | jakarta.tags.xml       |
| Function   | 컬렉션, 문자열 처리                | fn     | jakarta.tags.functions |
| SQL        | 데이터베이스 연결 및 쿼리 실행     | sql    | jakarta.tags.sql       |



JSTL을 사용하기 위해 taglib 지시어로 추가합니다. 이때 접두어와 URI가 사용됩니다. 예를 들어 Core 태그를 사용하기 위해 다음과 같이 작성합니다. 

```jsp
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
```



## 11.2 JSTL 사용 설정

JSTL은 확장 태그입니다. JSP의 기본 태그가 아니기 때문에 사용하기 위해서는 별도 라이브러리가 필요합니다. 메이븐 저장소에서 JSTL 라이브러리를 설치받을 수 있습니다. 다운로드한 jar 파일은 webapp/WEB-INF/lib 폴더 안에 저장합니다.



## 11.3 코어(Core) 태그

JSTL의 Core 태그는 프로그래밍 언어에서 가장 기본이 되는 변수 선언, 조건문, 반복문 등을 대체하는 태그를 제공합니다. 코어 태그의 종류는 다음과 같습니다.

**코어 태그의 종류**

| 태그명    | 기능                                                         |
| --------- | ------------------------------------------------------------ |
| set       | EL에서 사용할 변수를 설정(setAttribute() 메서드와 동일한 기능입니다.) |
| remove    | 설정한 변수를 제거(removeAttribue() 메서드와 동일한 기능입니다.) |
| if        | 단일 조건문을 주로 처리(else문이 없다는 단점이 있습니다.)    |
| choose    | 다중 조건문을 처리할 때 사용(하위에 when ~ otherwise 태그가 있습니다.) |
| forEach   | 반복문을 처리할 때 사용(for문과 향상된 for문 두 가지 형태가 있습니다.) |
| forTokens | 구분자로 분리된 각 토큰을 처리할 때 사용(StringTokenizer 클래스와 동일한 기능입니다.) |
| import    | 외부 페이지를 삽입할 때 사용                                 |
| redirect  | 지정한 경로로 이동(sendRedirect() 메서드와 동일한 기능입니다.) |
| url       | 경로를 설정할 때 사용                                        |
| out       | 내용을 출력할 때 사용                                        |
| catch     | 예외 처리에 사용                                             |



### 11.3.1 `<c:set>` 태그

`<c:set>` 태그는 EL에서 사용할 변수나 자바빈즈를 생성할 때 사용합니다. jsp 영역에 속성을 저장할 때 사용하는 setAttribute() 메서드와 동일한 역할입니다. 일반적인 변수를 생성하는 방법은 다음과 같습니다.

```jsp
<c:set var="변수명" value="값" scope="영역" />

<c:set var="변수명" scope="영역">
  value 속성에 들어갈 값
</c:set>
```



**`<c:Set>` 태그의 속성**

| 속성명   | 기능                                            |
| -------- | ----------------------------------------------- |
| var      | 변수명을 설정                                   |
| value    | 변수에 할당한 값                                |
| scope    | 변수를 생성할 영역을 지정(page가 기본값입니다.) |
| target   | 자바빈즈를 설정                                 |
| property | 자바빈즈의 속성, 즉 멤버 변수의 값을 지정       |



자바빈즈나 컬렉션을 생성할 때는 target과 property 속성을 사용합니다.

```jsp
<c:set var="변수명" value="저장할 객체 혹은 컬렉션" scope="영역" />
<c:target target="var로 설정한 변수명" value="객체의 속성명" value="속성값" />
```



**`<c:set>` 태그로 변수와 자바빈즈 사용하기**

```jsp
<%@ page import="java.util.Date"%>
<%@ page import="common.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>JSTL - set 1</title></head>
<body>
    <!-- 변수 선언 -->
    <c:set var="directVar" value="100" />
    <c:set var="elVar" value="${ directVar mod 5}" />
    <c:set var="expVar" value="<%= new Date() %>" />
    <c:set var="betweenVar">변수값 요렇게 설정</c:set>

    <h4>EL을 이용해 변수 출력</h4>
    <ul>
        <li>directVar : ${ pageScope.directVar }</li>
        <li>elVar : ${ elVar }</li>
        <li>expVar : ${ expVar }</li>
        <li>betweenVar : ${ betweenVar }</li>
    </ul>
    
    <h4>자바빈즈 생성 1 - 생성자 사용</h4>
    <c:set var="personVar1" value='<%= new Person("박문수", 50) %>'
           scope="request" />
    <ul>
        <li>이름 : ${ requestScope.personVar1.name }</li>
        <li>나이 : ${ personVar1.age}</li>
    </ul>
 
    <h4>자바빈즈 생성 2 - target, property 사용</h4>
    <c:set var="personVar2" value="<%= new Person() %>" scope="request" />
    <c:set target="${personVar2 }" property="name" value="정약용" />
    <c:set target="${personVar2 }" property="age" value="60" />
    <ul>
        <li>이름 : ${ personVar2.name }</li>
        <li>나이 : ${ requestScope.personVar2.age }</li>
    </ul>
</body>
</html>
```

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231022153535444.png" alt="image-20231022153535444" style="zoom:80%;" />



**`<c:set>` 태그로 컬렉션 사용하기**

```jsp
<%@ page import="common.Person"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>JSTL - set 2</title></head>
<body>
    <h4>List 컬렉션 이용하기</h4>
    <%
    ArrayList<Person> pList = new ArrayList<Person>();
    pList.add(new Person("성삼문", 55));
    pList.add(new Person("박팽년", 60));
    %>  
    <c:set var="personList" value="<%= pList %>" scope="request" />
    <ul>
        <li>이름 : ${ requestScope.personList[0].name }</li>
        <li>나이 : ${ personList[0].age }</li>
    </ul>

    <h4>Map 컬렉션 이용하기</h4>
    <%
    Map<String, Person> pMap = new HashMap<String, Person>();
    pMap.put("personArgs1", new Person("하위지", 65));
    pMap.put("personArgs2", new Person("이개", 67));
    %>
    <c:set var="personMap" value="<%= pMap %>" scope="request" />
    <ul>
        <li>아이디 : ${ requestScope.personMap.personArgs2.name }</li>
        <li>비번 : ${ personMap.personArgs2.age }</li>
    </ul>
</body>
</html>
```

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231022153912476.png" alt="image-20231022153912476" style="zoom:80%;" />



### 11.3.2 `<c:remove>` 태그