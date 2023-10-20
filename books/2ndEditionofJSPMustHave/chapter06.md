# 06 세션(Session)

HTTP 요청은 기본적으로 독립적이라서 여러 요청에 걸친 복잡한 기능을 구현하려면 별도의 기술이 필요합니다. 그 중 하나가 4장에서 배운 쿠키지만, 정보를 클라이언트에 저장한 후 네트워크로 주고받기 때문에 민감한 정보를 다루기엔 위험합니다. 한편 세션은 서버가 직접 관리하므로 민감한 정보를 다루기에 더 적합합니다. 이번 장에서 다루는 '로그인' 정보 유지가 대표적입니다.



<br />



## 6.1 세션이란?

'클라이언트가 웹 브라우저를 통해 서버에 접속한 후 용무를 처리하고 웹 브라우저를 다아 서버와의 접속을 종료하는 하나의 단위'를  세션(Session)이라고 합니다. 즉 **세션은 클라이언트가 서버에 접속해 있는 동안 그 상태를 유지하는 것이 목적**입니다.



<br />



## 6.2 세션 설정, 확인, 삭제

### 6.2.1 유지 시간 설정

세션은 웹 브라우저를 실행할 때마다 새롭게 생성합니다. 세션은 설정된 유지 시간 동안 유지되며, 유지 시간이 만료되기 전에 새로운 요청이 들어오면 수명이 계속 연장됩니다. 그리고 만료 때까지 클라이언트가 어떠한 요청을 하지 않거나 웹 브라우저를 닫으면 삭제됩니다.

세션의 유지 시간을 설정하는 방법은 두 가지입니다. 첫 번째는 /WEB-INF/web.xml에서 설정하는 방법입니다. web.xml 에서는 유지 시간을 분 단위로 설정합니다. 따라서 다음 코드는 20분으로 설정한 코드입니다.

```xml
  ...
  <session-config>
    <session-timeout>20</session-timeout>
  </session-config>
</web-app>
```



<br />



두 번째 방법으로 JSP 파일에서 session 내장 객체가 제공하는 setMaxInactiveInterval()을 사용할 수도 있습니다. 이 메서드의 시간 단위는 초입니다. 따라서 30분으로 설정한 것을 의미합니다. 두 가지 설정 방법만 다를 뿐 동일하게 적용됩니다.

```jsp
<%
  session.setMaxInactiveInterval(1800);
%>
```



<br />



### 6.2.2 설정값 확인

세션에는 유지 시간 뿐만 아니라 몇 가지 속성이 더 있습니다. 가장 먼저 다른 세션과 구분하기 위한 아이디, 최초 요청 시각(생성 시각)과 마지막 요청 시각 등의 속성이 있습니다. 다음은 세션 설정값을 확인해볼 수 있는 JSP 페이지입니다.

```jsp
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");  // 날짜 표시 형식

long creationTime = session.getCreationTime();  // 최초 요청(세션 생성) 시각
String creationTimeStr = dateFormat.format(new Date(creationTime));

long lastTime = session.getLastAccessedTime();  // 마지막 요청 시각
String lastTimeStr = dateFormat.format(new Date(lastTime));
%>
<html>
<head><title>Session</title></head>
<body>
    <h2>Session 설정 확인</h2>
    <ul>
        <li>세션 유지 시간 : <%= session.getMaxInactiveInterval() %></li>
        <li>세션 아이디 : <%= session.getId() %></li>
        <li>최초 요청 시각 : <%= creationTimeStr %></li>
        <li>마지막 요청 시각 : <%= lastTimeStr %></li>
    </ul>
</body>
</html>

```

SimpleDateFormat 클래스를 활용해 날짜 표시 형식을 '시:분:초' 형태로 지정한 후, 세션의 최초 요청 시각(세션 생성 시각)과 마지막 요청 시각을 구해, 방금 지정한 날짜 표시 형식에 맞는 문자열로 변경합니다. web.xml에서 설정한 세션 유지 시간은 getMaxInactiveInterval() 메서드로 출력합니다. 웹 브라우저에 생성된 세션 ID를 출력합니다. 



<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231020211723937.png" alt="image-20231020211723937" style="zoom:80%;" />



<br />



최초 요청 시각과 마지막 요청 시각은 처음 실행하면 동일하게 출력될 것이고, 잠시 후 새로고침을 수행하면 마지막 요청 시각만 현재 시각으로 변하게 됩니다. 

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231020211820930.png" alt="image-20231020211820930" style="zoom:80%;" />



<br />



### 6.2.3 세션 삭제

세션은 웹 브라우저를 닫으면 종료됩니다. 또는 웹 브라우저의 인터넷 검색 기록에서 삭제가 가능합니다.



<br />



## 6.3 세션과 DB를 이용한 로그인 구현

방금 배운 세션을 이용해서 로그인 기능을 구현합니다. 5장에서 생서해둔 member 테이블에 입력된 회원 정보를 활용합니다.



<br />



### 6.3.1 로그인 페이지 작성

```jsp
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head><title>Session</title></head>
<body>
	<jsp:include page="../Common/Link.jsp" />
    <h2>로그인 페이지</h2>
    <span style="color: red; font-size: 1.2em;"> 
        <%= request.getAttribute("LoginErrMsg") == null ?
                "" : request.getAttribute("LoginErrMsg") %>
    </span>
    <%
    if (session.getAttribute("UserId") == null) {  // 로그인 상태 확인
        // 로그아웃 상태
    %>
    <script>
    function validateForm(form) {
        if (!form.user_id.value) {
            alert("아이디를 입력하세요.");
            return false;
        }
        if (form.user_pw.value == "") {
            alert("패스워드를 입력하세요.");
            return false;
        }
    }
    </script>
    <form action="LoginProcess.jsp" method="post" name="loginFrm"
        onsubmit="return validateForm(this);">
        아이디 : <input type="text" name="user_id" /><br />
        패스워드 : <input type="password" name="user_pw" /><br />
        <input type="submit" value="로그인하기" />
    </form>
    <%
    } else { // 로그인된 상태
    %>
        <%= session.getAttribute("UserName") %> 회원님, 로그인하셨습니다.<br />
        <a href="Logout.jsp">[로그아웃]</a>
    <%
    }
    %>
</body>
</html>
```

request 내장 객체 영역에 LoginErrMsg 속성이 있는지 확인 후 그 내용을 출력합니다. 나중에 회원 인증에 실패한다면 request 영역에 이 속성을 저장한 후 포워드할 것입니다.

session 영역에 사용자 아이디(UserId)가 저장되어 있는지 확인합니다. 값이 null이면 저장되지 않은 것이므로 로그아웃 상태를 뜻합니다. 결과에 따라 로그아웃 상태라면 폼 태그가 실행되고, 로그인 상태라면 로그인 성공 문구가 실행됩니다.

회원인지 인증하려면 아이디와 패스워드를 반드시 입력해야 합니다. validateForm()은 자바스크립트로 작성한 유형성 검사 함수로, 아이디와 패스워드 중 빈 값이 있다면 경고창을 띄웁니다.

form 태그와 input 태그로 작성한 로그인 폼입니다. 폼값 전송 시 onsubmit 이벤트 핸들러가 validateForm()을 호출하도록 설정했습니다. 유효성 검사에서 통과하면 폼에 입력한 값이 post 방식으로 LoginProecss.jsp에 전송됩니다.

검사 결과 session 영역에 사용자 아이디가 저장되어 있는 경우에 실행됩니다. 로그인한 사용자의 이름과 로그아웃 버튼을 보여줍니다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231020212534405.png" alt="image-20231020212534405" style="zoom:80%;" />



<br />



   ### 6.3.2 DB 연동

데이터를 주고받기 위한 DTO 클래스와 테이블에 접근하기 위한 DAO 클래스를 생성합니다.

**DTO(Data Transfer Object)는 계층 사이에서 데이터를 교환하기 위해 생성하는 객체**입니다. 별다른 로직 없이 속성(멤버 변수)과 그 속성에 접근하기 위한 **게터/세터 메서드만 갖춘 게 특징**입니다. 그래서 **값 객체(Value Object, VO)라고도 합니다.**

**DAO(Data Access Object)는 데이터베이스의 데이터에 접근하기 위한 객체**입니다. 보통 JDBC를 통해 구현하며, 하나의 테이블에서 수행할 수 있는 CRUD를 전담합니다**. CRUD란 Create(생성), Read(읽기), Update(수정), Delete(삭제) 작업을 의미**합니다.



<br />



**회원 정보용 DTO 준비**

데이터 전달을 위한 DTO 클래스는 일반적으로 테이블당 하나씩 생성하며, 테이블의 컬럼과 동일한 멤버 변수를 작성합니다. 5장에서 회원 정보 관리용으로 member 테이블을 생성하였습니다. 이 테이블에 맞게 DTO 클래스를 정의합니다. 

Java Resources/src/main/java에서 마우스 우클릭 -> [New] -> [Class]를 클릭하여 membership 패키지에 MemberDTO 클래스를 추가합니다. 이 클래는 DTO답게 멤버 변수와 게터/세터로만 구성되어 있습니다.

```java
package membership;

public class MemberDTO {
    // 멤버 변수 선언
    private String id;
    private String pass;
    private String name;
    private String regidate;

    // 멤버 변수별 게터와 세터
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRegidate() {
        return regidate;
    }
    public void setRegidate(String regidate) {
        this.regidate = regidate;
    }
}
```



<br />



**회원 정보 테이블용 DAO 준비**

DB에 연결하고 CRUD에 해당하는 SQL 쿼리문을 실행합니다. CRUD 결과를 앞서 만든 DTO 객체에 담아 반환하는 일을 합니다.

```java
package membership;

import common.JDBConnect;

public class MemberDAO extends JDBConnect {
    // 명시한 데이터베이스로의 연결이 완료된 MemberDAO 객체를 생성합니다.
    public MemberDAO(String drv, String url, String id, String pw) {
        super(drv, url, id, pw);
    }

    // 명시한 아이디/패스워드와 일치하는 회원 정보를 반환합니다.
    public MemberDTO getMemberDTO(String uid, String upass) {
        MemberDTO dto = new MemberDTO();  // 회원 정보 DTO 객체 생성
        String query = "SELECT * FROM member WHERE id=? AND pass=?";  // 쿼리문 템플릿

        try {
            // 쿼리 실행
            psmt = con.prepareStatement(query); // 동적 쿼리문 준비
            psmt.setString(1, uid);    // 쿼리문의 첫 번째 인파라미터에 값 설정
            psmt.setString(2, upass);  // 쿼리문의 두 번째 인파라미터에 값 설정
            rs = psmt.executeQuery();  // 쿼리문 실행

            // 결과 처리
            if (rs.next()) {
                // 쿼리 결과로 얻은 회원 정보를 DTO 객체에 저장
                dto.setId(rs.getString("id"));
                dto.setPass(rs.getString("pass"));
                dto.setName(rs.getString(3));
                dto.setRegidate(rs.getString(4));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return dto;  // DTO 객체 반환
    }
}

```



<br />



**로그인 처리 JSP 구현**

사용자로부터 받은 폼값(아이디와 패스워드)으로 로그인을 처리할 JSP를 만들어보겠습니다. 
폼값은 로그인 화면에서 전달받고, DB 상의 정보는 방금 만든 DAO를 이용해 가져옵니다.

```jsp
<%@ page import="membership.MemberDTO"%>
<%@ page import="membership.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
// 로그인 폼으로부터 받은 아이디와 패스워드
String userId = request.getParameter("user_id"); 
String userPwd = request.getParameter("user_pw");  

// web.xml에서 가져온 데이터베이스 연결 정보
String oracleDriver = application.getInitParameter("OracleDriver");
String oracleURL = application.getInitParameter("OracleURL");
String oracleId = application.getInitParameter("OracleId");
String oraclePwd = application.getInitParameter("OraclePwd");

// 회원 테이블 DAO를 통해 회원 정보 DTO 획득
MemberDAO dao = new MemberDAO(oracleDriver, oracleURL, oracleId, oraclePwd);
MemberDTO memberDTO = dao.getMemberDTO(userId, userPwd);
dao.close();

// 로그인 성공 여부에 따른 처리
if (memberDTO.getId() != null) {
    // 로그인 성공
    session.setAttribute("UserId", memberDTO.getId()); 
    session.setAttribute("UserName", memberDTO.getName()); 
    response.sendRedirect("LoginForm.jsp");
}
else {
    // 로그인 실패
    request.setAttribute("LoginErrMsg", "로그인 오류입니다."); 
    request.getRequestDispatcher("LoginForm.jsp").forward(request, response);
}
%>
```



<br />



### 6.3.3 로그아웃 처리

로그아웃은 session 영역에 저장된 로그인 관련 속성을 모두 지워주기만 하면 됩니다. 속성을 지우는 방법은 두 가지입니다. 참고로 invalidate() 메서드를 사용하면 서버는 세션 정보를 더 이상 유지할 필요가 없으므로 부담이 줄어듭니다. 따라서 로그아웃 시에는 invalidate() 를 사용하는 것이 좋습니다.

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
// 방법 1: 회원인증정보 속성 삭제 
session.removeAttribute("UserId");
session.removeAttribute("UserName");

// 방법 2: 모든 속성 한꺼번에 삭제 
session.invalidate();

// 속성 삭제 후 페이지 이동 
response.sendRedirect("LoginForm.jsp");
%>
```



<br />



### 6.3.4 공통 링크 추가

세션을 사용해서 로그인, 로그아웃 기능까지 모두 구현했습니다. 앞으로 이동을 좀 더 편하게 하기 위해 다음과 같은 공통 링크를 로그인 페이지 상단에 추가하겠습니다.

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table border="1" width="90%"> 
    <tr>
        <td align="center">
        <!-- 로그인 여부에 따른 메뉴 변화 -->
        <% if (session.getAttribute("UserId") == null) { %>
            <a href="../06Session/LoginForm.jsp">로그인</a>
        <% } else { %>
            <a href="../06Session/Logout.jsp">로그아웃</a>
        <% } %>
            <!-- 8장과 9장의 회원제 게시판 프로젝트에서 사용할 링크 -->
            &nbsp;&nbsp;&nbsp; 
            <a href="../08Board/List.jsp">게시판(페이징X)</a>
            &nbsp;&nbsp;&nbsp; 
            <a href="../09PagingBoard/List.jsp">게시판(페이징O)</a>
        </td>
    </tr>
</table>

```



<br />



로그인 폼(LoginForm.jsp) 상단에 공통 링크를 추가합니다.

```jsp
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head><title>Session</title></head>
<body>
	<jsp:include page="../Common/Link.jsp" />
    <h2>로그인 페이지</h2>
    <span style="color: red; font-size: 1.2em;"> 
        <%= request.getAttribute("LoginErrMsg") == null ?
                "" : request.getAttribute("LoginErrMsg") %>
    </span>
    <%
    if (session.getAttribute("UserId") == null) {  // 로그인 상태 확인
        // 로그아웃 상태
    %>
    <script>
    function validateForm(form) {
        if (!form.user_id.value) {
            alert("아이디를 입력하세요.");
            return false;
        }
        if (form.user_pw.value == "") {
            alert("패스워드를 입력하세요.");
            return false;
        }
    }
    </script>
    <form action="LoginProcess.jsp" method="post" name="loginFrm"
        onsubmit="return validateForm(this);">
        아이디 : <input type="text" name="user_id" /><br />
        패스워드 : <input type="password" name="user_pw" /><br />
        <input type="submit" value="로그인하기" />
    </form>
    <%
    } else { // 로그인된 상태
    %>
        <%= session.getAttribute("UserName") %> 회원님, 로그인하셨습니다.<br />
        <a href="Logout.jsp">[로그아웃]</a>
    <%
    }
    %>
</body>
</html>
```



<br />



## 6.4 쿠키 vs 세션

로그인은 세션으로 구현해야 합니다. 그 이유를 세션과 쿠키의 차이점을 알아보면서 설명합니다.

| 구분           | 쿠키                                                         | 세션                                                         |
| -------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 저장 위치/형식 | 클라이언트 PC에 텍스트로 저장                                | 웹 서버에 Object 타입으로 저장                               |
| 보안           | 보안에 취약                                                  | 보안에 안전                                                  |
| 자원/속도      | 서버 자원을 사용하지 않으므로 세션보다 빠름                  | 서버 자원을 사용하므로 쿠키보다 느림                         |
| 용량           | 용량의 제한이 있음                                           | 서버가 허용하는 한 제한이 없음                               |
| 유지 시간      | 쿠키 생성 시 설정<br />단, 설정된 시간이 경과되면 무조건 삭제됨 | 서버의 web.xml에서 설정<br />설정된 시간 내라도 동작이 있다면 삭제되지 않고 유지됨 |



<br />



**핵심 요약**

- 세션은 클라이언트가 웹 브라우저를 통해 서버에 접속한 후 웹 브라우저를 닫을 때까지의 단위를 의미
- 클라이언트가 서버에 접속한 동안 상태를 유지하기 위해 세션 영역을 이용해 상태 정보를 저장
- 세션의 유지 시간 설정은 web.xml을 이용하는 것이 편리
- 설정된 유지 시간 동안 아무런 동작이 없다면 세션은 소멸됨(동작이 있다면 계속 유지됩니다.)
- 세션 영역은 다른 페이지와도 공유되므로 클라이언트별 상태 정보를 관리하기에 아주 유용한 수단

