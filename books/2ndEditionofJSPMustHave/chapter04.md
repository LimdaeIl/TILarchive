# 쿠키(Cookie)



<br />



## 4.1 쿠키란?

- 쿠키(Cookie)는 **클라이언트의 상태 정보를 클라이언트에 저장하여 상태 정보를 유지하는 기술**
- 상태 정보를 클라이언트(주로 웹 브라우저)에 **키(Key)와 값(Value) 형식으로 정보를 저장**
- 사용자 맞춤 서비스, 광고, 웹 로그 분석, 서비스 속도 개선 등의 요청 시에 저장된 쿠키를 함께 전송
- 웹 서버는 브라우저가 전송한 쿠키로부터 필요한 데이터를 읽기가 가능
- 쿠키 표준인 RFC 6255 는 다음과 같은 제약이 명시
  1. 3000개까지 만들 수 있습니다.
  2. 쿠키 하나의 최대 크기는 4096 바이트입니다.
  3. 하나의 호스트나 도메인에서 최대 50개까지 만들 수 있습니다.

따라서 쿠키로 저장할 수 있는 최대 용량은 대략 1.2MB 입니다. 하지만 모든 브라우저가 쿠키 표준보다 더 적게 지원하고 있습니다.



<br />



## 4.2 기본 동작 확인

### 4.2.1 동작 메커니즘

<img src="https://blog.kakaocdn.net/dn/c7zs3d/btsugRMF4bh/kPkHwIfhcdb4bE9tc8Y6c1/img.png" alt="img" style="zoom:67%;" />

1. **요청(처음 방문)**: 클라이언트가 서버에 요청을 전송
2. **응답(클라이언트로 쿠키 전송)**: 서버가 쿠키를 생성하여 HTTP 응답 헤더에 포함하고 클라이언트에게 전송
3. **쿠키 저장**: 클라이언트는 쿠키를 받아 저장(이때 서버는 아직 쿠키를 사용할 수 없습니다.)
4. **요청(재방문, 쿠키를 함께 전송)**: 클라이언트는 다시 요청을 보낼 때 저장해둔 쿠키를 HTTP 요청 헤더에 포함하여 서버로 전송
5. **쿠키를 읽어 작업 수행**: 서버는 쿠키의 정보를 읽은 후 필요한 작업을 수행

쿠키가 처음 생성되는 시점에서 서버는 쿠키를 읽을 수 없습니다. 클라이언트가 다시 요청을 보낼 때 HTTP 요청 헤더에 쿠키를 포함하여 전송하기 때문입니다. 따라서 **페이지를 새로 고치거나 다시 접속해야만 서버가 쿠키를 읽은 후 필요한 작업을 수행할 수 있습니다.** 서버에서 생성한 쿠키를 서버가 바로 읽을 수 없다는 점이 의아할 수 있습니다. 다음 내용을 살펴보면 그 이유를 알 수 있습니다.



<br />



### 4.2.2 속성과 API

쿠키를 구성하는 속성들은 다음과 같습니다.

- **이름(Name)** : 쿠키를 구별하는 이름
- **값(Value)** : 쿠키에 저장할 실제 데이터
- **도메인(Domain)** : 쿠키를 적용할 도메인
- **경로(Path)** : 쿠키를 적용할 경로
- **유지 기간(Max age)** : 쿠키를 유지할 기간

쿠키의 속성들을 설정하고 읽어오는 메서드들을 JSP 에서 제공합니다. **쿠키 설정과 관련된 메서드들**은 다음과 같습니다.

```java
void setValue(String value)
```

- **쿠키의 값을 설정**합니다.
- 쉼표(`,`), 세미콜론(`;`) 같은 문자는 포함할 수 없습니다.

 

<br />



```java
void setDomain(String domain)
```

- **쿠키에 적용할 도메인을 설정**합니다.
- 주 도메인만 적용하고 싶은 경우, `"도메인"` 형태로 기술합니다.
- 주 도메인 외에 서브 도메인에도 적용하고 싶은 경우, `".도메인"` 형태로 기술합니다.
- 예를 들어 `setDomain(".nakja.co.kr")` 로 설정하면 `www.nakja.co.kr` 은 물론, `mail.nakja.co.kr` 에서도 쿠키가 적용됩니다.

 

<br />



```java
void setPath(String path)
```

- **쿠키가 적용될 경로를 지정**합니다.
- **지정한 경로와 그 하위 경로에까지 적용**합니다.

 

<br />



```java
void setMaxAge(int expire_seconds)
```

- **쿠키가 유지될 기간을 초 단위로 설정**합니다.
- **기간을 설정하지 않으면 웹 브라우저가 닫힐 때 쿠키도 함께 삭제**됩니다.

 

<br />



쿠키 이름은 **생성자(Constructor)를 통해 생성**하고, 생성 후에는 더 이상 이름을 변경할 수 없기 때문에 **쿠키 이름을 지정하는 메서드는 없습니다.** 쿠키 생성자는 다음과 같습니다.

```java
new Cookie(String name, String value)
```

- **이름과 값을 통해 새로운 쿠키를 생성**합니다.

 

<br />



다음은 **쿠키 정보를 읽는 메서드들**입니다.

```java
String getName()
```

- **쿠키의 이름을 반환**합니다.

 

<br />



```java
String getValue()
```

- **쿠키의 값을 반환**합니다.

 

<br />



```java
Strnig getDomain()
```

- **쿠키가 적용되는 도메인을 반환**합니다.



<br />



```java
String getPath()
```

- **쿠키의 적용 경로를 반환**합니다. 단, `setPath()` 로 설정한 적이 없으면 `null` 을 반환합니다.

 

<br />



```java
int getMaxAge()
```

- **쿠키의 유지 기간을 반환**합니다. 단, `setMaxAge()` 로 설정한 적이 없다면 `-1` 을 반환합니다.

 

<br />



### 4.2.3 기본 조작법

```java
    <%
    Cookie cookie = new Cookie("myCookie", "쿠키맛나요");  // 쿠키 생성
    cookie.setPath(request.getContextPath());  // 경로를 컨텍스트 루트로 설정
    cookie.setMaxAge(3600);  // 유지 기간을 1시간으로 설정
    response.addCookie(cookie);  // 응답 헤더에 쿠키 추가
    %>
```

쿠키 이름은 "myCookie", 값은 "쿠키맛나요" 인 쿠키를 생성합니다. 그리고 경로와 유지 기간을 설정해 **응답 헤더에 추가**했습니다. request 내장 객체의 `getContextPath()` 메서드로 컨텍스트 루트를 얻습니다. 쿠키의 적용 경로를 컨텍스트 루트로 지정하면 **웹 애플리케이션 전체에서 쿠키를 사용하겠다는 의미**입니다.



<br />



```jsp
<%
  Cookie[] cookies = request.getCookies();  // 요청 헤더의 모든 쿠키 얻기
  if (cookies!=null) {
    for (Cookie c : cookies) {  // 쿠키 각각의
      String cookieName = c.getName();  // 쿠키 이름 얻기
      String cookieValue = c.getValue();  // 쿠키 값 얻기
      // 화면에 출력
      out.println(String.format("%s : %s<br/>", cookieName, cookieValue));
    }
  }
%>
```

요청 헤더에 담겨있는 모든 쿠키를 출력합니다. 이때 바로 위에서 생성한 "myCookie" 쿠키는 보이지 않고, 톰캣 컨테이너에서 세션을 유지하기 위해 발급하는 키인 JSESSIONID 라는 쿠키가 출력됩니다. JSESSIONID 는 새로운 웹 브라우저를 열면 자동으로 생성됩니다. 직접 생성한 쿠키는 새로고침 혹은 다시 접속해야 쿠키를 확인할 수 있습니다.

 

<br />



```jsp
<%
  Cookie[] cookies = request.getCookies();
  if (cookies != null) {
    for (int i = 0; i < cookies.length; i++) {
      String cookieName = cookies[i].getName();
      String cookieValue = cookies[i].getValue();
      out.println(String.format("쿠키명 : %s - 쿠키값 : %s<br/>", cookieName, cookieValue));
    }        
  }
%>
```

쿠키가 생성된 이후에 쿠키를 확인하면 "myCookie" 와 "쿠키맛나요" 가 정상적으로 출력됩니다. 이 쿠키는 setMaxAge() 로 설정한 1시간 동안은 웹 애플리케이션 전체에서 사용이 가능합니다. 쿠키를 삭제할 때는 쿠키를 빈 값으로 설정하고 유지 기간은 0 으로 부여하면 됩니다.



<br />



## 4.3 레이어 팝업창 제어

웹 애플리케이션을 개발할 때 팝업창을 많이 사용하게 됩니다. 팝업창은 회원가입 시 아이디 중복 체크나 간단한 공지사항을 띄어주는 용도로 자주 사용합니다. 이 기능을 쿠키를 이용해 구현이 가능합니다.

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String popupMode = "on";

Cookie[] cookies = request.getCookies();
if (cookies != null) {
    for (Cookie c : cookies) {
        String cookieName = c.getName();
        String cookieValue = c.getValue();
        if (cookieName.equals("PopupClose")) {
            popupMode = cookieValue; 
        }
    }
} 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키를 이용한 팝업 관리</title>
<style>
    div#popup{
        position: absolute; top:100px; left:100px; color:yellow;  
        width:300px; height:100px; background-color:gray;
    }
    div#popup>div{
        position: relative; background-color:#ffffff; top:0px;
        border:1px solid gray; padding:10px; color:black;
    }
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script>
$(function() {
    $('#closeBtn').click(function() {
        $('#popup').hide();
        var chkVal = $("input:checkbox[id=inactiveToday]:checked").val();
        if(chkVal==1){
            $.ajax({
                url : './PopupCookie.jsp',
                type : 'get',
                data : {inactiveToday : chkVal},
                dataType : "text",
                success : function(resData) {
                    if (resData != '') location.reload();
                }
            });
        }
    });
});
</script>
</head>
<body>
<h2>팝업 메인 페이지</h2>
<%
    for (int i = 1; i <= 10; i++) {
        out.print("현재 팝업창은 " + popupMode + " 상태입니다.<br/>");
    }
    if (popupMode.equals("on")) {
%>
    <div id="popup">
        <h2 align="center">공지사항 팝업입니다.</h2>
        <div align="right"><form name="popFrm">
            <input type="checkbox" id="inactiveToday" value="1" />
            하루 동안 열지 않음
            <input type="button" value="닫기" id="closeBtn" />
        </form></div>
    </div>
<%
    }
%>
</body>
</html>
```

위의 코드는 쿠키를 활용한 팝업창을 보여주는 코드입니다. 쿠키명이 "PopupClose" 인 쿠키가 존재하면 popupMode 변수의 값을 쿠키의 값으로 갱신합니다. 즉, 어딘가에서 PopupClose 쿠키의 값을 "on" 이 아닌 값으로 설정한다면 팝업창이 더 이상 뜨지 않습니다.

두 번째는 [닫기] 버튼을 누르면 호출되는 함수는 jQuery 코드에서 팝업창을 처리했습니다. [하루 동안 열지 않음] 을 체크한 유무에 따라서 쿠키를 설정하는 페이지인 PopupCookie.jsp 를 실행합니다.

 

<br />



```js
var chkVal = $("input:checkbox[id=inactiveToday]:checked").val();
```

위의 코드에서 chkVal 변수는 하루 동안 팝업창을 띄울 지, 말 지를 정하는 변수입니다. 코드의 의미는 id 가 "inactiveToday" 이면서 '체크된' 체크박스의 값을 읽어와서(`.val()`) chkVal 변수에 저장합니다. [하루 동안 열지 않음] 체크박스를 체크하면 chkVal 변수 안에 "1" 이 저장되고, 체크하지 않으면 어떠한 값도 저장되지 않습니다. 변수의 값이 1일 때만 ajax() 함수를 호출합니다.

 

<br />



```js
$.ajax({ // 비동기로 요청을 보냅니다.
    url : './PopupCookie.jsp', // PopupCookie.jsp 파일에
    type : 'get', // HTTP GET 방식으로
    data : {inactiveToday : chkVal}, // inactiveToday = <chkVal 변수의 값> 데이터를
    dataType : "text", // 응답 데이터의 타입은 일반 텍스트이며
    success : function(resData) { // 요청 성공 시
        if (resData != '')  // 응답 데이터가 있다면 
            location.reload(); // 페이지를 새로고칩니다.
        }
});
```

ajax() 는 **비동기 HTTP 요청을 보내는 jQuery 함수**입니다. 인수는 HTTP 요청을 구성하는 다양한 설정값을 받게 되는데, 지금까지 사용한 설정은 다음과 같습니다.

- `url` : **요청을 보낼 페이지의 URL** 입니다. 기본값은 현재 페이지입니다.
- `type` : **`get, post` 등 HTTP 메서드를 지정**합니다.
- `data` : **서버로 보낼 데이터**입니다.
- `dataType` : **서버로부터 받을 '응답' 데이터의 타입**입니다.
- `success` : **요청 성공 시 실행할 콜백 함수**입니다.

요청을 받는 PopupCookie.jsp 에서 "PopupClose" 쿠키를 적절하게 설정한 후 응답 객체에 추가하여 팝업창을 띄우지 않을 수 있도록 코드를 작성합니다. 다음 코드는 PopupCookie.jsp 파일의 코드입니다. 쿠키를 생성해 응답 객체에 추가합니다.

 

<br />



```jsp
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
  String chkVal = request.getParameter("inactiveToday");

  if (chkVal != null && chkVal.equals("1")) {
    Cookie cookie = new Cookie("PopupClose", "off");  // 쿠키 생성 
    cookie.setPath(request.getContextPath());  // 경로 설정
    cookie.setMaxAge(60*60*24);  // 유지 기간 설정
    response.addCookie(cookie);  // 응답 객체에 추가 
    out.println("쿠키 : 하루 동안 열지 않음");  
  }
%>
```

[오늘 하루 열지 않음] 체크 여부를 확인하기 위해 chkVal 변수에 "1" 이 저장되어 성공적으로 열지 않음 체크가 전달되었는지 확인합니다. chkVal 변수값이 "1" 이면 **이름이 "PopupClose", 값은 "off", 경로는 컨텍스트 루트, 유지 기간은 하루인 쿠키를 생성해 응답 객체에 추가**합니다. 그리고 문자열로 "쿠키 : 하루 동안 열지 않음" 을 콜백합니다.



<br />



## 4.4 로그인 아이디 저장

두 번째 응용 예로 로그인 페이지에서 아아디를 저장하는 기능을 구현하겠습니다.
쿠키를 이용한 아이디 저장 시나리오는 다음과 같습니다.

1. **로그인에 성공한 경우에만 쿠키를 생성 및 삭제**합니다.
2. 쿠키에 저장된 아이디가 있으면 **로그인 페이지에서는 아이디가 자동 입력**됩니다.
3. [아이디 저장하기] 체크박스를 해제하고 로그인에 성공하면 **쿠키가 삭제**됩니다.

<img src="https://blog.kakaocdn.net/dn/bc2Kz1/btst6Pwo7EV/SZqtgNj3hykbnmKaYljKLK/img.png" alt="img" style="zoom:67%;" />



<br />



### 4.4.1 편의 기능 구현하기 : 자바스크립트 코드 추가

메세지 알림창을 띄운 후 다음 페이지나 이전 페이지로 이동하는 자바스크립트 코드가 필요합니다. 로그인 성공 / 실패 여부에 따른 다음 동작을 처리하는 기능을 수행합니다. JSP 에서 자바스크립트를 사용하려면 **스크립틀릿 중간에 자바스크립트 코드를 넣어서 작성**합니다.

```jsp
<%
... JSP 코드(스크립틀릿)
%>
<script>
    alert('메시지');
    location.href='이동할 페이지 경로';
</script>
<%
...JSP 코드(스크립틀릿)
%>
package Utils;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.JspWriter;

public class JSFunction {
    // 메시지 알림창을 띄운 후 명시한 URL로 이동합니다.
    public static void alertLocation(String msg, String url, JspWriter out) {
        try {
            String script = "<script>"  // 삽입할 자바스크립트 코드
                    + "    alert('" + msg + "');"
                    + "    location.href='" + url + "';"
                    + "</script>";
            out.println(script);  // 자바스크립트 코드를 out 내장 객체로 출력(삽입)
        }
        catch (Exception e) {}
    }

    // 메시지 알림창을 띄운 후 이전 페이지로 돌아갑니다.
    public static void alertBack(String msg, JspWriter out) {
        try {
            String script = "<script>"
                    + "    alert('" + msg + "');"
                    + "    history.back();"
                    + "</script>";
            out.println(script);
        }
        catch (Exception e) {}
    }

    // 메시지 알림창을 띄운 후 명시한 URL로 이동합니다.
    public static void alertLocation(HttpServletResponse resp, String msg, String url) {
        try {
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            String script = "<script>"
                    + "    alert('" + msg + "');"
                    + "    location.href='" + url + "';"
                    + "</script>";
            writer.print(script);
        }
        catch (Exception e) {}
    }

    // 메시지 알림창을 띄운 후 이전 페이지로 돌아갑니다.
    public static void alertBack(HttpServletResponse resp, String msg) {
        try {
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            String script = "<script>"
                    + "    alert('" + msg + "');"
                    + "    history.back();"
                    + "</script>";
            writer.print(script);
        }
        catch (Exception e) {}
    }
}
```

JSP 코드를 나누고 스크립트 태크를 사용하는 귀찮은 작업을 피하기 위해 자바 코드로 자바스크립트 코드를 작성하여 out 내장 객체로 전달했습니다. JSP 에서 완성된 자바 파일을 사용하기 위해 다음과 같이 호출할 수 있습니다.

 

```jsp
<%
... JSP 코드(스크립틀릿)
JSFuntion.alertLocation("메시지", "이동할 페이지 경로", out);
... JSP 코드(스크립틀릿)
%>
```

또한 반복되는 기능이 있는 경우에 별도의 메서드로 정의하여 사용하면 편리합니다.

 

### 4.4.2 편의 기능 구현하기 : 쿠키 관리자

쿠키를 생성할 때는 객체 생성, 경로 및 유지 기간 설정 등이 필요합니다. 쿠키를 읽을 때는 쿠키를 배열로 가져오기 때문에 반복문과 조건문이 필요합니다. 사용하는 쿠키가 많아진다면 조건문은 계속 복잡해질 수 밖에 없습니다. 따라서 쿠키를 편리하게 사용할 수 있게 도와주는 클래스를 작성합니다.

```java
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



### 4.4.3 로그인 페이지 작성하기

```jsp
<%@ page import="Utils.CookieManager" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String loginId = CookieManager.readCookie(request, "loginId");
    String cookieCheck = "";
    if (!loginId.equals("")) {
        cookieCheck = "checked";
    }
%>
<html>
<head><title>Cookie - 아이디 저장하기</title></head>
<body>
<h2>로그인 페이지</h2>
<form action="IdSaveProcess.jsp" method="post">
    아이디 : <input type="text" name="user_id" value="<%= loginId %>"/>
    <input type="checkbox" name="save_check" value="Y" <%= cookieCheck %> />
    아이디 저장하기
    <br/>
    패스워드 : <input type="text" name="user_pw"/>
    <br/>
    <input type="submit" value="로그인하기"/>
</form>
</body>
</html>
```



<br />



### 4.4.4 로그인 및 아이디 저장 기능 구현하기

```jsp
<%@ page import="Utils.CookieManager"%>
<%@ page import="Utils.JSFunction"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String user_id = request.getParameter("user_id");
    String user_pw = request.getParameter("user_pw");
    String save_check = request.getParameter("save_check");

    System.out.println(user_id +"<>"+ user_pw);

    if ("must".equals(user_id) && "1234".equals(user_pw)) {
        // 로그인 성공
        if (save_check != null && save_check.equals("Y")) {
            CookieManager.makeCookie(response, "loginId", user_id, 86400);
        }
        else {
            CookieManager.deleteCookie(response, "loginId");
        }

        JSFunction.alertLocation("로그인에 성공했습니다.", "IdSaveMain.jsp", out);
    }
    else {
        // 로그인 실패
        JSFunction.alertBack("로그인에 실패했습니다.", out);
    }
%>
```

쿠키는 클라이언트의 상태를 저장할 수 있으므로 아이디 저장뿐 아니라 로그인 유지 용도로도 사용할 수 있습니다. 하지만 로그인 유지에는 쿠키보다 세션을 주로 사용합니다. 그 이유를 명확히 설명하려면 세션과의 차이를 이야기해야 합니다. 다다음 장에서 설명합니다.

 

<br />



**핵심 요약**

- 쿠키는 생성자를 통해서만 생성이 가능
- 생성된 쿠키값의 변경은 가능(단, 쿠키명은 변경이 불가능합니다.)
- `setPath()` 메서드로 적용할 경로를 설정
- `setMagAge()` 메서드로 쿠키의 유지 기간을 설정
- response 내장 객체의 `addCookie()` 메서드로 클라이언트에 쿠키를 저장
- 쿠키는 생성 직후 바로 사용이 불가능(클라이언트가 재요청을 했을 때부터 사용이 가능합니다.)

