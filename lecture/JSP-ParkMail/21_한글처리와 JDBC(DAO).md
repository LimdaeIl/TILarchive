# 21_한글처리와 JDBC(DAO)

request 객체에 저장된 클라이언트의 패킷 정보 안에 한글 데이터를 깨지지 않게 읽어오기 위한 방법에 대해 학습합니다. request 객체로부터 파라미터 값을 가져오기 전에 UTF-8 인코딩을 수행해야 합니다. request 객체의 setCharacterEncoding 메서드의 매개변수로 UTF-8을 설정한 `request.setCharacterEncoding("utf-8");` 코드를 다음과 같이 파라미터 값을 가저오기 전에 작성하면 한글을 깨지지 않고 사용할 수 있습니다.

**kr.bit.controller.MemberInsertController.java**

```java
package kr.bit.controller;

...

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

        ...
}

```



JDBC 에 대해 학습하기 전에 class 객체와 드라이버에 대해 이해가 필요합니다.

데이터베이스와 연결하기 위한 객체를 생성합니다. 데이터베이스에 접속하기 위해 첫 번째로 URL 정보, 유저 이름, 유저 비밀번호가 필요합니다. 그 다음으로 접속하는 데이터베이스의 드라이버가 필요합니다. 

**kr.bit.model.MmeberDAO.java**

```java
package kr.bit.model;
// JDBC->myBatis, JPA
import java.sql.*;
import java.util.ArrayList;
public class MemberDAO {
   private Connection conn;
   private PreparedStatement ps;
   private ResultSet rs;
   
   // 데이터베이스 연결객체 생성(Connection)
   public void getConnect() {
	   //데이터베이스접속URL
	   String URL="jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&serverTimeZone=UTC";
	   String user="root";
	   String password="admin12345";
	   // MySQL Driver Loading
	  try {
 		  //동적로딩(실행시점에서 객체를 생성하는 방법)
		  Class.forName("com.mysql.jdbc.Driver");		  
		  conn=DriverManager.getConnection(URL, user, password);
	   } catch (Exception e) {
		  e.printStackTrace();
	  }		   
   }   
...
}
```

![image-20231014170607342](C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231014170607342.png)





