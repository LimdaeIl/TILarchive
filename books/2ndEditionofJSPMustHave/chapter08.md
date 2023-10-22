# 모델 1 방식의 회원제 게시판 만들기



## 8.1 프로젝트 구상

회원제 게시판의 동작 프로세스를 정의하고 필요한 데이터베이스 테이블과 시퀸스를 준비합니다.



### 8.1.1 회원제 게시판의 프로세스

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231021180254415.png" alt="image-20231021180254415" style="zoom:80%;" />

- 회원제 게시판
  - 첫 화면에서 게시물 목록을 보여준다.
  - 게시물 제목을 클릭하여 상세 보기로 이동할 수 있다.
  - 글쓰기 버튼을 누르면 글을 작성할 수 있다.
  - 비회원도 상세 보기로 이동할 수 있다.

- 유저
  - 비회원(로그아웃) 상태: 목록 보기, 상세 보기
  - 회원(로그인) 상태: 글쓰기, 수정하기, 삭제하기

- 이동
  - 글쓰기 후: 목록으로 이동
  - 수정 후: 상세 보기로 이동
  - 삭제 후: 목록으로 이동



### 8.1.2 테이블 및 시퀸스 생성

게시판은 작성한 게시물을 DB에 저장한 후 관리해야 하므로 JDBC 프로그래밍은 필수입니다. 지난 학습에 구현한 member, board 테이블을 그대로 사용합니다.

**회원 관리: member 테이블 정의**

| 컬럼명     | 데이터 타입  | null 허용 | 키     | 기본값  | 설명      |
| ---------- | ------------ | --------- | ------ | ------- | --------- |
| `id`       | varchar2(10) | N         | 기본키 |         | 아이디    |
| `pass`     | varchar2(10) | N         |        |         | 패스워드  |
| `name`     | varchar2(30) | N         |        |         | 이름      |
| `regidate` | date         | N         |        | sysdate | 가입 날짜 |

```sql
/*
설명 : 먼저 만들어둔 테이블을 삭제하고 새로 생성하려는 경우 실행해주세요.
*/
drop table member;

--[예제 5-1] 회원 테이블 생성
create table member (
    id varchar2(10) not null,
    pass varchar2(10) not null,
    name varchar2(30) not null,
    regidate date default sysdate not null,
    primary key (id)
);
```



**게시물 관리: board 테이블 정의서**

| 컬럼명       | 데이터 타입    | null 허용 | 키     | 기본값  | 설명                                       |
| ------------ | -------------- | --------- | ------ | ------- | ------------------------------------------ |
| `num`        | number         | N         | 기본키 |         | 일련번호, 기본키                           |
| `title`      | varchar2(200)  | N         |        |         | 게시물의 제목                              |
| `content`    | varchar2(2000) | N         |        |         | 내용                                       |
| `id`         | varchar2(10)   | N         | 외래키 |         | 작성자의 아이디. member.id 참조하는 외래키 |
| `postdate`   | date           | N         |        | sysdate | 작성일                                     |
| `visitcount` | number(6)      | Y         |        |         | 조회수                                     |

```sql
/*
설명 : 먼저 만들어둔 테이블을 삭제하고 새로 생성하려는 경우 실행해주세요.
*/
drop table board;

--[예제 5-2] 모델1 방식의 게시판 테이블 생성
create table board (
    num number primary key,
    title varchar2(200) not null,
    content varchar2(2000) not null,
    id varchar2(10) not null,
    postdate date default sysdate not null,
    visitcount number(6)
);

--[예제 5-3] 외래키 설정
alter table board
    add constraint board_mem_fk foreign key (id)
    references member (id);
```

member 테이블의 id 컬럼과 board 테이블의 id 컬럼은 외래키(forign key)로 엮어 있습니다. 만약 member 테이블에 없는 아이디로 게시물을 작성하려 하면 제약조건 위배로 에러가 발생합니다. 따라서 회원으로 가입된 사람만 글을 작성할 수 있습니다.



## 8.2 모델1 구조와 모델2 구조(MVC 패턴)

### 8.2.1 MVC 패턴

웹 애플리케이션은 사용자의 요청을 받아 처리한 후 응답하는 구조입니다. **MVC는 모델(Model), 뷰(View), 컨트롤러(Controller)**의 약자로 소프트웨어를 개발하는 방법론입니다. **데이터 처리를 담당하는 모델**과 **출력을 담당하는 뷰**, 그리고 **이 둘을 제어하는 컨트롤러**가 각자의 역할을 분담하여 사용자의 요청을 처리한 후 결과를 웹 브라우저에 출력하게 됩니다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231021181413503.png" alt="image-20231021181413503" style="zoom:80%;" />

- **모델**: 업무 처리 로직(비즈니스 로직) 혹은 데이터베이스와 관련된 작업을 담당
- **뷰**: JSP 페이지와 같이 사용자에게 보여지는 부분을 담당
- **컨트롤러**: 모델과 뷰를 제어하는 역할을 담당(사용자의 요청을 받아서 그 요청을 분석하고, 필요한 업무 처리 로직(모델)을 호출합니다. 결과값을 반환하면 출력할 뷰(JSP 페이지)를 선택한 후 전달합니다.)



### 8.2.2 모델 1 구조와 모델2 구조

**모델1 구조**

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231021181613373.png" alt="image-20231021181613373" style="zoom:67%;" />

모델1 방식에서는 사용자의 요청을 JSP가 받아 모델을 호출합니다. 모델이 요청을 처리한 후 결과를 반환하면 JSP를 통해 응답을 하게 됩니다. 즉, JSP에 뷰와 컨트롤러가 혼재되어 있습니다. 

모델 1방식은 이러한 구조 때문에 개발 속도가 빠르고 배우기 쉽다는 장점이 있지만, 컨트롤러 두 가지 기능 모두를 JSP에서 구현해야 하므로 코드가 복잡해지고 유지보수가 어렵습니다.



**모델2 구조**

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231021181914726.png" alt="image-20231021181914726" style="zoom:80%;" />

모델2 방식에서는 요청을 컨트롤러인 서블릿이 받습니다. 서블릿은 사용자의 요청을 분석한 후 모델을 호출합니다. 모델로부터 데이터를 받아 뷰로 전달하면 최종적으로 사용자는 요청에 대한 응답을 받을 수 있게 됩니다. 

모델, 뷰, 컨트롤러가 각자의 역할을 수행하므로 업무 분담이 명확해지고 코드가 간결해집니다. 자연스럽게 유지보수도 쉬워집니다. 하지만 구조가 복잡하여 익숙하지 않다면 개발 기간이 길어질 수 있어 규모가 작은 프로젝트에서는 적합하지 않을 수도 있습니다. 



## 8.3 목록 보기

페이징의 개념 없이 전체 게시물을 한꺼번에 출력하는 형태로 제작을 진행합니다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231021182248268.png" alt="image-20231021182248268" style="zoom:80%;" />



### 8.3.1 DTO와 DAO 준비

board 테이블에 데이터를 저장하거나 전송하기 위한 DTO 클래스를 생성하겠습니다. DTO(Data Transfer Object)는 주로 데이터 저장이나 전송에 사용되는, 로직을 가지고 있지 않은 객체를 말합니다. 



**게시글 목록용 DTO**

```java
package model1.board;

public class BoardDTO {
    // 멤버 변수 선언
    private String num;
    private String title;
    private String content;
    private String id;
    private java.sql.Date postdate;
    private String visitcount;
    private String name;

    // 게터/세터
    public String getNum() {
        return num;
    }
    public void setNum(String num) {
        this.num = num;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public java.sql.Date getPostdate() {
        return postdate;
    }
    public void setPostdate(java.sql.Date postdate) {
        this.postdate = postdate;
    }
    public String getVisitcount() {
        return visitcount;
    }
    public void setVisitcount(String visitcount) {
        this.visitcount = visitcount;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

```



**게시글 목록 CRUD용 DAO**

지난 학습에 생성한 JDBConnect 클래스를 상속받습니다. 생성자에서는 부모 클래스의 생성자를 호출합니다. 부모 클래스인 JDBConnect에는 총 3개의 생성자를 정의하였는데, 그 중 application 내장 객체를 받는 생성자를 이용했습니다. **이 생성자는 매개변수로 받은 application 내장 객체를 통해 web.xml에 정의한 오라클 접속 정보를 직접 가져와서 DB에 연결해줍니다.** 이로써 오라클에 연결하기 위한 준비를 모두 마칩니다.

```java
package model1.board;

import common.JDBConnect;
import jakarta.servlet.ServletContext;

public class BoardDAO extends JDBConnect {
    public BoardDAO(ServletContext application) {
        super(application);
    }
```



### 8.3.2 jsp 페이지 구현

BoardDAO 클래스에 게시물 목록을 보여주는 기능을 구현합니다. selectCount(), selectList() 두 개의 메서드를 추가합니다.

- **selectCount()**: board 테이블에 저장된 게시물의 개수를 반환합니다. 목록에서 번호를 출력하기 위해 사용됩니다.
- **selectList()**: board 테이블의 레코드를 가져와서 반환합니다. 이 메서드가 반환한 ResultSet 객체로부터 게시물 목록을 반복하여 출력하게 됩니다.



**게시물 개수 세기**

selectCount() 메서드부터 BoardDAO 클래스에 추가합니다.

```java
...
    // 검색 조건에 맞는 게시물의 개수를 반환합니다.
    public int selectCount(Map<String, Object> map) {
        int totalCount = 0; // 결과(게시물 수)를 담을 변수

        // 게시물 수를 얻어오는 쿼리문 작성
        String query = "SELECT COUNT(*) FROM board";
        if (map.get("searchWord") != null) {
            query += " WHERE " + map.get("searchField") + " "
                   + " LIKE '%" + map.get("searchWord") + "%'";
        }

        try {
            stmt = con.createStatement();   // 쿼리문 생성
            rs = stmt.executeQuery(query);  // 쿼리 실행
            rs.next();  // 커서를 첫 번째 행으로 이동
            totalCount = rs.getInt(1);  // 첫 번째 칼럼 값을 가져옴
        }
        catch (Exception e) {
            System.out.println("게시물 수를 구하는 중 예외 발생");
            e.printStackTrace();
        }

        return totalCount; 
    }
}
...
```

`public int selectCount(Map<String, Object> map)` 매개변수로 받은 Map 컬렉션에는 게시물 검색 조건(검색어)이 담겨 있습니다. Map 컬렉션에 "searchWord" 키로 저장된 값이 있을 때만 WHERE절을 추가하도록 했습니다. 



**게시물 목록 가져오기**

selectList() 메서드를 추가합니다.

```java
    ...
    // 검색 조건에 맞는 게시물 목록을 반환합니다.
    public List<BoardDTO> selectList(Map<String, Object> map) { 
        List<BoardDTO> bbs = new Vector<BoardDTO>();  // 결과(게시물 목록)를 담을 변수

        String query = "SELECT * FROM board "; 
        if (map.get("searchWord") != null) {
            query += " WHERE " + map.get("searchField") + " "
                   + " LIKE '%" + map.get("searchWord") + "%' ";
        }
        query += " ORDER BY num DESC "; 

        try {
            stmt = con.createStatement();   // 쿼리문 생성
            rs = stmt.executeQuery(query);  // 쿼리 실행

            while (rs.next()) {  // 결과를 순화하며...
                // 한 행(게시물 하나)의 내용을 DTO에 저장
                BoardDTO dto = new BoardDTO(); 

                dto.setNum(rs.getString("num"));          // 일련번호
                dto.setTitle(rs.getString("title"));      // 제목
                dto.setContent(rs.getString("content"));  // 내용
                dto.setPostdate(rs.getDate("postdate"));  // 작성일
                dto.setId(rs.getString("id"));            // 작성자 아이디
                dto.setVisitcount(rs.getString("visitcount"));  // 조회수

                bbs.add(dto);  // 결과 목록에 저장
            }
        } 
        catch (Exception e) {
            System.out.println("게시물 조회 중 예외 발생");
            e.printStackTrace();
        }

        return bbs;
    }
}
```



**게시물 목록 출력하기**

목록을 화면에 출력해주는 JSP 페이지를 생성합닌다.

```jsp
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="model1.board.BoardDAO"%>
<%@ page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
// DAO를 생성해 DB에 연결
BoardDAO dao = new BoardDAO(application);

// 사용자가 입력한 검색 조건을 Map에 저장
Map<String, Object> param = new HashMap<String, Object>(); 
String searchField = request.getParameter("searchField");
String searchWord = request.getParameter("searchWord");
if (searchWord != null) {
    param.put("searchField", searchField);
    param.put("searchWord", searchWord);
}

int totalCount = dao.selectCount(param);  // 게시물 수 확인
List<BoardDTO> boardLists = dao.selectList(param);  // 게시물 목록 받기
dao.close();  // DB 연결 닫기
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원제 게시판</title>
</head>
<body>
    <jsp:include page="../Common/Link.jsp" />  <!-- 공통 링크 -->

    <h2>목록 보기(List)</h2>
    <!-- 검색폼 --> 
    <form method="get">  
    <table border="1" width="90%">
    <tr>
        <td align="center">
            <select name="searchField"> 
                <option value="title">제목</option> 
                <option value="content">내용</option>
            </select>
            <input type="text" name="searchWord" />
            <input type="submit" value="검색하기" />
        </td>
    </tr>   
    </table>
    </form>
    <!-- 게시물 목록 테이블(표) --> 
    <table border="1" width="90%">
        <!-- 각 칼럼의 이름 --> 
        <tr>
            <th width="10%">번호</th>
            <th width="50%">제목</th>
            <th width="15%">작성자</th>
            <th width="10%">조회수</th>
            <th width="15%">작성일</th>
        </tr>
        <!-- 목록의 내용 --> 
<%
if (boardLists.isEmpty()) {
    // 게시물이 하나도 없을 때 
%>
        <tr>
            <td colspan="5" align="center">
                등록된 게시물이 없습니다^^*
            </td>
        </tr>
<%
}
else {
    // 게시물이 있을 때 
    int virtualNum = 0;  // 화면상에서의 게시물 번호
    for (BoardDTO dto : boardLists)
    {
        virtualNum = totalCount--;  // 전체 게시물 수에서 시작해 1씩 감소
%>
        <tr align="center">
            <td><%= virtualNum %></td>  <!--게시물 번호-->
            <td align="left">  <!--제목(+ 하이퍼링크)-->
                <a href="View.jsp?num=<%= dto.getNum() %>"><%= dto.getTitle() %></a> 
            </td>
            <td align="center"><%= dto.getId() %></td>          <!--작성자 아이디-->
            <td align="center"><%= dto.getVisitcount() %></td>  <!--조회수-->
            <td align="center"><%= dto.getPostdate() %></td>    <!--작성일-->
        </tr>
<%
    }
}
%>
    </table>
    <!--목록 하단의 [글쓰기] 버튼-->
    <table border="1" width="90%">
        <tr align="right">
            <td><button type="button" onclick="location.href='Write.jsp';">글쓰기
                </button></td>
        </tr>
    </table>
</body>
</html>

```



\* Model 1방식은 지금까지 정리한 부분만 살펴보고 Model 2 방식을 깊게 학습합니다. 
