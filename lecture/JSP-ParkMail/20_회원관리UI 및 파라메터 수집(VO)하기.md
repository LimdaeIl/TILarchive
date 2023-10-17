# 20_회원관리UI 및 파라메터 수집(VO)하기

이번 학습은 데이터베이스에 생성한 테이블을 기준으로 회원의 정보를 입력할 수 있는 HTML 페이지를 생성합니다.

**kr.bit.db/member.sql**

```sql
-- member(회원) table
create table member(
 num int primary key auto_increment,
 id varchar(20) not null,
 pass varchar(20) not null,
 name varchar(30) not null,
 age int not null,
 email varchar(30) not null,
 phone varchar(30) not null,
 unique key(id)
);
-- SQL(CRUD), JDBC
-- 검색
select * from member;kr

-- insert(저장)
insert into member(id, pass, name, age, email, phone)
values('admin','admin','관리자',40,'bit@naver.com','010-1111-1111');

-- update(수정)
update member set age=45, phone='010-1111-0000' where id='admin';

-- delete(삭제)
delete from member where id='admin';

drop table member;
```

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/e95de7b7-0f3e-4c6d-b981-2b977f2f5dd8)


 

HTML으로 테이블 구조를 미리 생성해서 확인할 수 있는 사이트입니다. `https://www.tablesgenerator.com/` 

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/0458ec41-318b-4b30-b28d-14bf37c9c281)




데이터베이스의 테이블의 속성명과 동일하게 input 태그 name 속성명을 작성하는 것이 좋습니다. 먼저 데이터베이스의 테이블 속성명과 동일하게 테이블 타입과 input 속성명을 작성하고 폼 태그로 감싸줍니다. 폼 태그 액션명 '/MVC01/memberInsert.do'에 해당하는 컨트롤러(서블릿)에 전송됩니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/23c2fcef-ca3f-4dd1-b6d3-fc34f0921d86)




**webapp/member/memberRegister.html**

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
회원가입화면
<form action="/MVC01/memberInsert.do" method="post">
<table class="table table-bordered">
  <tr>
    <td>아이디</td>
    <td><input type="text" name="id"/></td>
  </tr>
  <tr>
    <td>패스워드</td>
    <td><input type="password" name="pass"/></td>
  </tr>
  <tr>
    <td>이름</td>
    <td><input type="text" name="name"/></td>
  </tr>
  <tr>
    <td>나이</td>
    <td><input type="text" name="age"/></td>
  </tr>
  <tr>
    <td>이메일</td>
    <td><input type="text" name="email"/></td>
  </tr>
  <tr>
    <td>전화번호</td>
    <td><input type="text" name="phone"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center">
      <input type="submit" value="가입" class="btn btn-primary"/>
      <input type="reset" value="취소" class="btn btn-warning"/>
    </td>
  </tr>
</table>
</form>
</body>
</html>
```



파라미터 수집의 가장 기본적인 방법은 setter 메서드가 일반적입니다. VO 객체의 생성자 매개변수로 파라미터를 전달해서 생성하는 방식으로도 가능하지만, setter 메서드로 접근하는 방법은 스프링으로 계속 이어지는 방법입니다.

```java
// (X) -> MemberVO vo=new MemberVO(id, pass, name, age, email, phone);

// (O) -> setter 메서드로 파라미터 수집하기
MemberVO vo=new MemberVO();
vo.setId(id);
vo.setPass(pass);
vo.setName(name);
vo.setAge(age);
vo.setEmail(email);
vo.setPhone(phone);
```



**kr.bit.controller.MemberInsertController.java**

```java
package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;
@WebServlet("/memberInsert.do")
public class MemberInsertController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			                                          throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 1. 파라메터수집(VO)
		String id=request.getParameter("id");
		String pass=request.getParameter("pass");
		String name=request.getParameter("name");
		int age=Integer.parseInt(request.getParameter("age")); // "40"->40
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		//파라메터수집(VO)
		//MemberVO vo=new MemberVO(id, pass, name, age, email, phone);
		MemberVO vo=new MemberVO();
		vo.setId(id);
		vo.setPass(pass);
		vo.setName(name);
		vo.setAge(age);
		vo.setEmail(email);
		vo.setPhone(phone);
		
		//System.out.println(vo); // vo.toString()
		// Model과 연동부분
	    MemberDAO dao=new MemberDAO();
	    int cnt=dao.memberInsert(vo);
	    //PrintWriter out=response.getWriter();
	    if(cnt>0) {
	    	// 가입성공
	        //out.println("insert success");	// 다시 회원리스트 보기로 가야된다.(/MVC01/memberList.do)
	    	response.sendRedirect("/MVC01/memberList.do");
	    }else {
	    	// 가입실패-> 예외객체를 만들어서  WAS에게 던지자.
	    	throw new ServletException("not insert");	    	
	    }		
	}
}

```

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/bc409928-f701-4979-bcfc-e7df512a27cf)

