# 10 표현 언어(EL: Expression Language)

표현 언어는 모델2 방식으로 웹 애플리케이션을 개발할 때 주로 사용됩니다. 4가지 영역(page, request, session, application)에 저장된 속성에 접근할 때 사용하므로, 순수 JSP에서 사용하는 것과 다릅니다. 



<br />



## 10.1 표현 언어란?

**표현 언어(Expression Language)는 변수의 값을 출력할 때 사용하는 스크립트 언어**입니다. 표현식 `<% =>`만으로도 충분히 값을 출력할 수 있지만, 표현식과 표현 언어의 역할은 조금 다릅니다. 표현 언어는 4가지 영역에 저장된 값을 출력할 때 사용합니다. 또한 사용법이 매우 간결하고, 예외와 형변환에 관대하다는 특징이 있습니다. 예를 들어 자바에서 값이 null인 변수를 사용하면 예외가 발생하지만 표현 언어를 사용하면 예외가 발생하지 않습니다. 표현 언어는 다음과 같은 기능을 제공합니다.

- JSP 내장 객체의 영역에 담긴 속성 숑이 가능
- 산술 연산, 비교 연산, 논리 연산이 가능
- 자바 클래스에 정의된 메서드 호출이 가능
- 표현 언어만의 객체를 통해 JSP와 동일한 기능을 수행



<br />



### 10.1.1 기본 사용법

EL 문법의 속성은 **영역에 저장된 속성을 의미**합니다. 변수나 값을 바로 쓸 수 있는 표현식과 다릅니다. 만약 JSP에서 생성한 변수에 접근하려면 반드시 영역에 저장하고 사용해야 합니다. EL의 기본 사용법은 다음과 같습니다. 

```jsp
${ 속성 }
```



<br />



request 영역에 저장했다면 다음과 같이 출력할 수 있습니다.

```jsp
<h2> ${ requestScope.saveVar } </h2>
```



<br />



EL은 HTML 태그, JS, CSS 어디에서든 사용이 가능합니다. 또한 액션태그, JSTL의 속성값으로도 사용할 수 있습니다.

```jsp
<c:set var="elVar" value="${ elVar }" /> <!-- 액션 태그와 함께 사용 -->
<jsp:include page="${ pathVar }" /> <!-- JSTL과 함께 사용 -->
```



<br />



EL은 JSP 스크립트 요소(선언부, 표현식, 스크립틀릿)에서는 사용할 수 없습니다.

```jsp
<%!
    void myMethod(${ errorVar })
    // 코드 ...
%> <!-- 선언부에 EL을 사용하면 에러 발생 -->

<%@ include file="${ errorVar }" %> <!-- 스크립틀릿에서 EL을 사용하면 에러 발생 -->
<%= ${ errorVar } %> <!-- 표현식에서 EL을 사용하면 에러 발생 -->
```



<br />



### 10.1.2 객체 표현 방식

EL에서 객체를 표현할 때는 점 `.` 또는 대괄호`[]` 를 사용합니다.

```jsp
${ param.name }
${ param["name"] }
${ param['name'] }
```



<br />



속성명에 특수 기호 또는 한글이 포함될 때에는 대괄호만 사용할 수 있습니다.

```jsp
${ header["user-agent"] } <!-- 가능 -->
${ header.user-agent } <!-- 에러 발생 -->
${ King['한글'] } <!-- 가능 -->
${ King.한글 } <!-- 에러 발생 -->
```



<br />



## 10.2 EL의 내장 객체

### 10.2.1 4가지 영역에 속성값 저장하고 읽어오기

- **pageScope**: pageContext 내장 객체와 같이 page 영역에 저장된 속성값을 읽어옵니다.
- **requesetScope**: request 내장 객체와 같이 request 영역에 저장된 속성값을 읽어옵니다.
- **sessionScope**: session 내장 객체와 같이 session 영역에 저장된 속성값을 읽어옵니다.
- **applicationScope**: application 내장 객체와 같이 aaplication 영역에 저장된 속성값을 읽어옵니다.

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
pageContext.setAttribute("scopeValue", "페이지 영역");
request.setAttribute("scopeValue", "리퀘스트 영역");
session.setAttribute("scopeValue", "세션 영역");
application.setAttribute("scopeValue","애플리케이션 영역");
%>     
<html>
<meta charset="UTF-8">
<head><title>표현 언어(EL) - 내장 객체</title></head>
<body>
    <h2>ImplicitObjMain 페이지</h2>
    <h3>각 영역에 저장된 속성 읽기</h3>
    <ul>
        <li>페이지 영역 : ${ pageScope.scopeValue }</li>
        <li>리퀘스트 영역 : ${ requestScope.scopeValue }</li>
        <li>세션 영역 : ${ sessionScope.scopeValue }</li>
        <li>애플리케이션 영역 : ${ applicationScope.scopeValue }</li>
    </ul>

    <h3>영역 지정 없이 속성 읽기</h3>
    <ul>
        <li>${ scopeValue }</li> 
    </ul>
    
    <jsp:forward page="ImplicitForwardResult.jsp" />
</body>
</html>
```

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231021200655379.png" alt="image-20231021200655379" style="zoom:80%;" />

내장 객체의 영역 중 page 영역은 포워드되면 소멸되고 새로 만들어진다고 학습했습니다. 따라서 포워드된 이후에는 새로운 page 영역이 만들어져서 기존 page 영역에 저장해둔 값은 읽을 수 없습니다. 같은 이유로 **영역을 지정하지 않고 출력하면 request 영역의 속성값이 출력**됩니다. 따라서 **현재의 page 영역에는 scopeValue라는 속성이 없으므로, 그 다음으로 범위가 좁은 request 영역에서 속성을 읽어온 것입니다.** 



<br />



### 10.2.2 폼값 처리하기

JSP에서는 전송 방식(GET/POST)에 상관없이 request.getParameter()로 폼값을 받을 수 있습니다. EL 또한 마찬가지입니다. EL에서 폼값을 처리하기 위한 내장 객체는 다음과 같습니다.

- **param**: request.getParameter("매개변수명")과 동일하게 요청 매개변수의 값을 받아옵니다.
- **paramValues**: requestParameterValues("매개변수명")과 동일하게 요청 매개변수의 값을 문자열 배열로 받아옵니다. 주로 다중 선택이 가능한 checkbox를 통해 전달된 폼값을 받을 때 사용합니다.



<br />



**폼값 전송용 개인정보 입력폼 예제**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<meta charset="UTF-8">
<head><title>표현 언어(EL) - 폼값 처리</title></head>
<body>
    <h2>폼값 전송하기</h2>
    <form name="frm" method="post" action="FormResult.jsp">
        이름 : <input type="text" name="name" /><br />
        성별 : <input type="radio" name="gender" value="Man" />남자
               <input type="radio" name="gender" value="Woman" />여자<br />
        학력 :
            <select name="grade">
                <option value="ele">초딩</option>
                <option value="mid">중딩</option>
                <option value="high">고딩</option>
                <option value="uni">대딩</option>
            </select><br />
        관심 사항 : 
            <input type="checkbox" name="inter" value="pol" />정치
            <input type="checkbox" name="inter" value="eco" />경제
            <input type="checkbox" name="inter" value="ent" />연예
            <input type="checkbox" name="inter" value="spo" />운동<br />
        <input type="submit" value="전송하기" />
    </form>
</body>
</html>
```



<br />



**전송된 폼값 확인용 페이지 예제**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<meta charset="UTF-8">
<head><title>표현 언어(EL) - 폼값 처리</title></head>
<body>
    <h3>EL로 폼값 받기</h3>
    <ul>
        <li>이름 : ${ param.name }</li>
        <li>성별 : ${ param.gender }</li>
        <li>학력 : ${ param.grade }</li>
        <li>관심사항 : ${ paramValues.inter[0] } 
            ${ paramValues.inter[1] }
            ${ paramValues.inter[2] }
            ${ paramValues.inter[3] }</li> 
    </ul>
</body>
</html>
```

text.radio 타입의 `<input>` 태그나 `<select>` 태그는 값이 하나만 전송되므로 EL의 내장 객체인 param으로 값을 받을 수 있습니다. 타입이 checkbox일때 다수의 값을 전송할 수 있으므로 EL 내장 객체의 paramValues를 통해 배열로 값을 받습니다. JSTL의 forEach 태그를 사용하면 좀 더 쉽게 출력할 수 있습니다. 단, EL은 null을 출력해도 예외가 발생하지 않으므로 앞의 예처럼 null 체크 없이 간단히 쓸 수 있다는 장점이 있습니다.



<br />



### 10.2.3 객체 전달하기

문자열은 앞의 예제처럼 폼으로 전송할 수 있습니다. 하지만 전송할 대상이 객체라면 어떻게 해야 할까요? 폼으로는 불가능합니다. 객체를 전달하기 위해서는 영역을 사용해야 합니다. 객체를 영역에 저장한 후, 내장 객체의 영역이 공유된다는 특징으로 전송하고자 하는 페이지로 전달하면 됩니다.

**객체 전달**

```jsp
<%@ page import="common.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<meta charset="UTF-8">
<head><title>표현 언어(EL) - 객체 매개변수</title></head>
<body>
    <%
    request.setAttribute("personObj", new Person("홍길동", 33));
    request.setAttribute("stringObj", "나는 문자열");
    request.setAttribute("integerObj", new Integer(99));
    %>
    <jsp:forward page="ObjectResult.jsp">
        <jsp:param value="10" name="firstNum" />
        <jsp:param value="20" name="secondNum" />
    </jsp:forward>
</body>
</html>
```

Person 객체, 문자열(String 객체), Integer 객체를 생성한 후 request 영역에 저장합니다. 그리고 액션 태그를 이용해 OjbectResult.jsp로 포워드합니다. 이때 10과 20도 포워드된 페이지로 함께 전달됩니다.



<br />



**전달받은 객체 확인**

```jsp
<%@ page import="common.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<meta charset="UTF-8">
<head><title>표현 언어(EL) - 객체 매개변수</title></head>
<body>  
    <h2>영역을 통해 전달된 객체 읽기</h2>
    <ul>
        <li>Person 객체 => 이름 : ${ personObj.name }, 나이 : ${ personObj.age }</li>
        <li>String 객체 => ${ requestScope.stringObj }</li>
        <li>Integer 객체 => ${ integerObj }</li> 
    </ul>
    <h2>매개변수로 전달된 값 읽기</h2>
    <ul>
        <li>${ param.firstNum + param['secondNum'] }</li>  
        <li>${ param.firstNum } + ${param["secondNum"] }</li> 
    </ul>
</body>
</html>
```

request 영역을 통해 전달받은 객체를 출력합니다. 매개변수를 통해 전달된 값을 출력합니다. 속성명을 지정할 때 점과 대괄호를 모두 사용할 수 있습니다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231021203213492.png" alt="image-20231021203213492" style="zoom:80%;" />

JSP 코드와 EL 코드를 비교를 통해 얼마나 간단하게 구현할 수 있는지 알 수 있습니다. request 영역에 저장된 Person 객체에서 JSP 코드를 통해 읽는다면 다음처럼 해야 합니다. 영역에는 모든 객체가 Object 타입으로 저장되어 있으므로 읽을 때는 반드시 형변환 후 사용해야 하고, 게터로 멤버 변수의 값을 가져옵니다. 한편 EL을 사용하면 이러한 번거러운 절차를 생략할 수 있습니다. 형변환이 필요 없고, 게터 호출 대신 멤버 변수 이름만 쓰면 바로 원하는 값을 출력할 수 있습니다. 

객체를 영역에 저장해 전달하는 방식은 서블릿에서 자주 사용합니다. 서블릿 코드는 자바로 작성하고 결과 출력은 JSP에서 합니다. 이때 서블릿에서 처리한 내용을 영역에 저장한 후 JSP 파일로 포워드해 출력합니다. 

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231021203420585.png" alt="image-20231021203420585" style="zoom:80%;" />



<br />



### 10.2.4 쿠키, HTTP 헤더, 컨텍스트 초기화 매개변수 출력하기

LE은 쿠키나 헤더값을 읽을 수 있도록 다음 내장 객체를 제공합니다.

- **cookie** : 쿠키를 읽을 때 사용합니다.
- **header** : request.getHeader(헤더명)와 동일하게 헤더값을 읽을 때 사용합니다.
- **headerValues** : request.getHeaders(헤더명)와 동일하게 헤더값을 배열 형태로 읽을 때 사용합니다.
- **initParam** : web.xml에 설정한 컨텍스트 초기화 매개변수를 읽을 때 사용합니다.
- **pageContext** : JSP의 pageContext 내장 객체와 동일한 역할을 합니다.



<br />



**쿠키, HTTP 헤더, 컨텍스트 초기화 매개변수 출력하기**

```jsp
<%@ page import="utils.CookieManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
CookieManager.makeCookie(response, "ELCookie", "EL좋아요", 10);
%>    
<html>
<meta charset="UTF-8">
<head><title>표현 언어(EL) - 그 외 내장 객체</title></head>
<body>
    <h3>쿠키값 읽기</h3>
    <li>ELCookie값 : ${ cookie.ELCookie.value }</li>
    
    <h3>HTTP 헤더 읽기</h3>
    <ul>
        <li>host : ${ header.host }</li>
        <li>user-agent : ${ header['user-agent'] }</li>
        <li>cookie : ${ header.cookie }</li>
    </ul>
    
    <h3>컨텍스트 초기화 매개변수 읽기</h3>
    <li>OracleDriver : ${ initParam.OracleDriver }</li>

    <h3>컨텍스트 루트 경로 읽기</h3>
    <li>${ pageContext.request.contextPath }</li>
</body>
</html>
```



<br />



**4장에서 학습한 CookieManager 클래스**

```java
package utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieManager {
    // 명시한 이름, 값, 유지 기간 조건으로 새로운 쿠키를 생성합니다.
    public static void makeCookie(HttpServletResponse response, String cName,
            String cValue, int cTime) {
        Cookie cookie = new Cookie(cName, cValue); // 쿠키 생성
        cookie.setPath("/");         // 경로 설정
        cookie.setMaxAge(cTime);     // 유지 기간 설정
        response.addCookie(cookie);  // 응답 객체에 추가
    }

    // 명시한 이름의 쿠키를 찾아 그 값을 반환합니다.
    public static String readCookie(HttpServletRequest request, String cName) {
        String cookieValue = "";  // 반환 값

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                String cookieName = c.getName();
                if (cookieName.equals(cName)) {
                    cookieValue = c.getValue();  // 반환 값 갱신
                }
            }
        }
        return cookieValue;
    }

    // 명시한 이름의 쿠키를 삭제합니다.
    public static void deleteCookie(HttpServletResponse response, String cName) {
        makeCookie(response, cName, "", 0);
    }
}
```



<br />



**새로고침 전**

![image-20231022143127518](C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231022143127518.png)



<br />



**새로고침 후**

![image-20231022143205588](C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231022143205588.png)



<br />



## 10.3 컬렉션 사용하기

EL을 통해 컬렉션을 사용하면 자바 코드보다 훨씬 더 간결하게 이용할 수 있습니다. 여러 가지 매개변수를 저장할 때는 Map 컬렉션을 사용하고 동일한 데이터인 경우에는 List 컬렉션을 사용합니다. 이 두 가지 컬렉션을 EL과 함께 사용하는 방법에 대해 학습합니다.

```JSP 
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="common.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<meta charset="UTF-8">
<head><title>표현 언어(EL) - 컬렉션</title></head>
<body>
<h2>List 컬렉션</h2>
<%
List<Object> aList = new ArrayList<Object>();
aList.add("청해진");
aList.add(new Person("장보고", 28));
pageContext.setAttribute("Ocean", aList);
%>
<ul>
    <li>0번째 요소 : ${ Ocean[0] }</li>
    <li>1번째 요소 : ${ Ocean[1].name }, ${ Ocean[1].age }</li>
    <li>2번째 요소 : ${ Ocean[2] }<!--출력되지 않음--></li>
</ul>
<h2>Map 컬렉션</h2>
<%
Map<String, String> map = new HashMap<String, String>();
map.put("한글", "훈민정음");
map.put("Eng", "English");
pageContext.setAttribute("King", map);
%>
<ul>
    <li>영문 Key : ${ King["Eng"] }, ${ King['Eng'] }, ${ King.Eng }</li>
    <li>한글 Key : ${ King["한글"] }, ${ King['한글'] }, \${King.한글 }<!--에러--></li>
</ul>
</body>
</html>
```

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231022143739724.png" alt="image-20231022143739724" style="zoom:80%;" />



<br />



## 10.4 EL의 연산자들

**할당 연산자**

EL 3.0 이상부터는 `=` 연산자를 써서 변수에 값을 할당할 수 있습니다. 단, 할당과 동시에 출력되기 때문에 할다만을 하고 싶다면 세미콜론과 작은따옴표를 함께 작성해야 합니다.

```jsp
${ numberVar = 10 } <!-- 할당과 동시에 출력 -->
${ numberVar = 10;'' } <!-- 할당만 되고 출력은 되지 않음 -->
```



<br />



**산술 연산자**

- `+, -, *`  : 덧셈, 뺄샘, 곱셈
- `/, div` : 나눗셈
- `%, mod` : 나머지



<br />



**비교 연산자**

- `>, gt` : Greater Than(~보다 크다.)
- `>=, ge` : Greater Than of Equal(~보다 크거나 같다.) 
- `<, lt` : Less than(~보다 작다.)
- `<=, le` : Less than or Equal(~보다 작거나 같다.)
- `==, eq` : EQual(같다.)
- `!=, ne` : Not Equal(같지 않다.)



<br />



**논리 연산자**

- `&&, and` : 논리 AND 
- `||, or` :  논리 OR
- `!, not` :  논리 NOT



<br />



**각종 연산자 사용 예 1**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 예시에서 사용할 변수 선언
int num1 = 3;
pageContext.setAttribute("num2", 4);
pageContext.setAttribute("num3", "5");  
pageContext.setAttribute("num4", "8");  
%>    
<html>
<meta charset="UTF-8">
<head><title>표현 언어(EL) - 연산자</title></head>
<body>
    <h3>변수 선언 및 할당</h3> 
    스크립틀릿에서 선언한 변수 : ${ num1 } <br />
    page 영역에 저장된 변수 : ${ num2 } <br />
    변수 할당 및 즉시 출력 : ${ num1 = 7 } <br />
    변수 할당 및 별도 출력 : ${ num2 = 8;'' } => ${ num2 } <br />
    num1 = ${ num1 }, num2 = ${ num2 }, num3 = ${ num3 }, num4 = ${ num4 }
    
    <h3>산술 연산자</h3>
    num1 + num2 : ${ num1 + num2 } <br />
    num1 - num2 : ${ num1 - num2 } <br />
    num1 * num2 : ${ num1 * num2 } <br />
    num3 / num4 : ${ num3 / num4 } <br />
    num3 div num4 : ${ num3 div num4 } <br />
    num3 % num4 : ${ num3 % num4 } <br />
    num3 mod num4 : ${ num3 mod num4 }
   
    <h3>+ 연산자는 덧셈만 가능</h3>  
    num1 + "34" : ${ num1 + "34" } <br /> 
    num2 + "이십" : \${num2 + "이십" }<!-- 에러 발생(주석 처리) --> <br />
    "삼십" + "사십" : \${"삼십" + "사십" }<!-- 에러 발생(주석 처리) -->
    
    <h3>비교 연산자</h3>
    num4 > num3 : ${ num4 gt num3 } <br />
    num1 < num3 : ${ num1 lt num3 } <br />
    num2 >= num4 : ${ num2 ge num4 } <br />
    num1 == num4 : ${ num1 eq num4 } 

    <h3>논리 연산자</h3>
    num3 <= num4 && num3 == num4 : ${ num3 le num4 and num3 eq num4 } <br />
    num3 >= num4 || num3 != num4 : ${ num3 ge num4 or num3 ne num4 }
</body>
</html>
```

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231022144435312.png" alt="image-20231022144435312" style="zoom:80%;" />



<br />



**empty 연산자**

값이 없을 때 true를 반환하는 연산자입니다.

- null
- 빈 문자열
- 길이가 0인 배열
- size가 0인 컬렉션



<br />



**삼항 연산자**

자바의 삼항 연산자와 사용법이 동일합니다.

```jsp
${ 조건 ? "true일 때 선택" : "false일 때 선택" }
```



<br />



```jsp
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 예시에서 사용할 변수 선언
pageContext.setAttribute("num1", 9);
pageContext.setAttribute("num2", "10");

pageContext.setAttribute("nullStr", null);
pageContext.setAttribute("emptyStr", "");
pageContext.setAttribute("lengthZero", new Integer[0]);
pageContext.setAttribute("sizeZero", new ArrayList<Object>());
%>    
<html>
<meta charset="UTF-8">
<head><title>표현 언어(EL) - 연산자</title></head>
<body>
    <h3>empty 연산자</h3>   
    empty nullStr : ${ empty nullStr } <br />
    empty emptyStr : ${ empty emptyStr } <br />
    empty lengthZero : ${ empty lengthZero } <br />
    empty sizeZero : ${ empty sizeZero }
    
    <h3>삼항 연산자</h3>
    num1 gt num2 ? "참" : "거짓" => ${ num1 gt num2 ? "num1이 크다" : "num2가 크다" }
    
    <h3>null 연산</h3>
    <!-- 이클립스는 null과의 연산을 에러로 표시하므로 주석으로 처리했습니다. 
    실행에는 아무런 문제가 없음을 알려드립니다. 실습시 주석을 제거해주세요.-->
    <%-- 
    null + 10 : ${ null + 10 } <br />
    nullStr + 10 : ${ nullStr + 10 } <br />
    param.noVar > 10 : ${ param.noVar > 10 } 
    --%>
</body>
</html>
```



<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231022144720900.png" alt="image-20231022144720900" style="zoom:80%;" />



<br />



## 10.5 인스턴스 메서드 호출

### 10.5.1 호출할 메서드 준비

**EL로 호출할 자바 클래스**

```java
package el;

public class MyELClass {
    // 주민번호를 입력받아 성별을 반환합니다
    public String getGender(String jumin) {
        String returnStr = "";
        int beginIdx = jumin.indexOf("-") + 1;
        String genderStr = jumin.substring(beginIdx, beginIdx + 1);
        int genderInt = Integer.parseInt(genderStr);
        if (genderInt == 1 || genderInt == 3)
            returnStr = "남자";
        else if (genderInt == 2 || genderInt == 4) 
            returnStr = "여자";
        else
            returnStr = "주민번호 오류입니다.";
        return returnStr;
    }

    // 입력받은 문자열이 숫자인지 판별해줍니다.
    public static boolean isNumber(String value) {
        char[] chArr = value.toCharArray();
        for (int i = 0; i < chArr.length; i++) {
            if (!(chArr[i] >= '0' && chArr[i] <= '9')) {
                return false;
            }
        }
        return true;
    }

    // 입력받은 정수까지의 구구단을 HTML 테이블로 출력해줍니다.
    public static String showGugudan(int limitDan) {
        StringBuffer sb = new StringBuffer();
        try {
            sb.append("<table border='1'>");
            for (int i = 2; i <= limitDan; i++) {
                sb.append("<tr>");
                for (int j = 1; j <= 9; j++) {
                    sb.append("<td>" + i + "*" + j + "=" + (i * j) + "</td>");
                }
                sb.append("</tr>");
            }
            sb.append("</table>");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
```



<br />



### 10.5.2 메서드 호출하기

**EL에서 메서드 호출하기**

```jsp
<%@ page import="el.MyELClass"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="mytag" uri="/WEB-INF/MyTagLib.tld" %>
<%
MyELClass myClass = new MyELClass(); // 객체 생성
pageContext.setAttribute("myClass", myClass); // page 영역에 저장
%>  
<html>
<head><title>표현 언어(EL) - 메서드 호출</title></head>
<body>
    <h3>영역에 저장 후 메서드 호출하기</h3>
    001225-3000000 => ${ myClass.getGender("001225-3000000") } <br />
    001225-2000000 => ${ myClass.getGender("001225-2000000") }

    <h3>클래스명을 통해 정적 메서드 호출하기</h3>
    ${ MyELClass.showGugudan(7) }
    
    <h3>TLD 파일 등록 후 정적 메서드 호출하기</h3>
    <ul>
        <li>mytag:isNumber("100") => ${ mytag:isNumber("100") }</li>
        <li>mytag:isNumber("이백") => ${ mytag:isNumber("이백") }</li>
    </ul> 
</body> 
</html>
```



<br />



**tld(Tag Library Descriptor)**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<taglib version="2.0"
    xmlns="http://java.sun.com/xml/ns/j2ee"
    xmlns:xml="http://www.w3.org/XML/1998/namespace"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee 
        http://java.sun.com/xml/ns/j2ee/web-jsptaglibrary_2_0.xsd ">
    <tlib-version>0.0</tlib-version>
    <short-name>NMTOKEN</short-name>
    <function>
        <name>isNumber</name>
        <function-class>el.MyELClass</function-class>
        <function-signature>boolean isNumber(java.lang.String)</function-signature>
    </function>
</taglib>
```



<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231022145035826.png" alt="image-20231022145035826" style="zoom:80%;" />
