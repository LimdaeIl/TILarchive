# 05 데이터베이스

> 오라클 설치 및 계정 관련 설정은 넘어갑니다.



<br />



## 5.5 테이블 및 시퀸스 생성

회원제 게시판을 생성하도록 합니다.



<br />



### 5.5.1 테이블 생성

**member 테이블 정의**

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



<br />



### 5.5.2 외래키로 테이블 사이의 관계 설정

**board 테이블 정의**

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

member 테이블의 기본키로 사용 중인 id 컬럼을 외래키로 지정했습니다. 각 게시글을 특정 회원과 연결한 이유는 **회원이 아닌 사람은 글을 게시할 수 없도록 DBMS가 보장**하기 때문입니다.



<br />



### 5.5.3 일련번호용 시퀸스 객체 생성

```SQL
/*
설명 : 먼저 만들어둔 테이블을 삭제하고 새로 생성하려는 경우 실행해주세요.
*/
drop sequence seq_board_num;

--[예제 5-4] 시퀀스 생성
create sequence seq_board_num 
    increment by 1
    start with 1
    minvalue 1
    nomaxvalue
    nocycle
    nocache;
```



<br />



### 5.5.4 테이블 스페이스 설정

오라클 21c 에서는 데이터를 삽입하기 전, 테이블 스페이스부터 설정해야 합니다. 테이블 스페이스(Table Space)는 디스크 공간을 소비하는 테이블과 뷰 같은 데이터베이스 객체들이 저장되는 장소입니다. 즉, 데이터를 저장할 물리적 공간이라 말할 수 있습니다. 테이블 스페이스를 확인하려면 system 계정으로 접속해야 합니다. system 계정에 접속한 상태에서 다음 코드를 실행합니다.

```sql
select TABLESPACE_NAME, status, contents from dba_tablespaces;
```



![img](https://blog.kakaocdn.net/dn/bDpf6m/btsxg36oe4C/IbQcxtyFM3IXvIoksToAm0/img.png)



<br />



테이블 스페이스를 조회하는 코드입니다. SYSTEM, USERS 와 같은 테이블 스페이스가 생성된 것을 확인할 수 있습니다.

```sql
select TABLESPACE_NAME, sum(BYTES), max(BYTES) from DBA_FREE_SPACE group by TABLESPACE_NAME;
```



![img](https://blog.kakaocdn.net/dn/bc5ZBa/btsxkFDQDrQ/9ZfzLhaiMzk215DoD6kyh0/img.png)



<br />



테이블 스페이스별 가용 공간을 확인할 수 있습니다.

```sql
select username, default_Tablespace from dba_users where username in upper('c##sqld');
```



![img](https://blog.kakaocdn.net/dn/c6VWM7/btsxtWjSxTl/4OP1yQm6RCVkscUpWj2E0k/img.png)



<br />



C##SQLD 계정은 USRS 테이블 스페이스를 사용하는 것을 확인할 수 있습니다.

```sql
alter user c##sqld quota 5m on users;
```

c##sqld 계정으로 user 테이블 스페이스에 데이터를 입력할 수 있도록 5MB 용량을 할당합니다. 이상으로 테이블 스페이스 설정을 마쳤습니다. 지금부터는 데이터 입력이 가능합니다.



<br />



### 5.5.5 동작 확인

```sql
--[예제 5-5] 더미 데이터 입력
insert into member (id, pass, name) values ('musthave', '1234', '머스트해브');
insert into board  (num, title, content, id, postdate, visitcount)
    values (seq_board_num.nextval, '제목1입니다', '내용1입니다', 'musthave', sysdate, 0);
```

처음 레코드를 입력하면 오라클은 입력된 레코드를 내부의 임시 테이블에 저장합니다. 따라서 오라클 내부에선느 레코드를 조회할 수 있으나, 외부에서는 입력된 레코드를 조회할 수 없는 상태입니다. 커밋은 임시 테이블에 저장된 레코드를 실제 테이블에 적용하는 명령입니다.



<br />



## 5.6 JDBC 설정 및 데이터베이스 연결

JDBC(Java DataBase Connectivity)란 자바로 데이터베이스 연결 및 관리 작업을 할 때 사용하는 API 입니다. JDBC API 를 사용하기 위해서는 JDBC 드라이버가 있어야 합니다. 각 DBMS 에 맞는 JDBC 드라이버를 설치한 후 설정하면 DMBS 종류에 상관없이 동일한 방식으로 프로그래밍이 가능합니다. Oracle XE 를 기준으로 진행합니다.



![img](https://blog.kakaocdn.net/dn/cyVwXJ/btsxq10z8Uh/fK3yVfdLhURkbSDnBAfsA0/img.png)



<br />



### 5.6.1 JDBC 드라이버 설정

JDBC 로 오라클을 이용하기 위해 오라클이 제공하는 JDBC 드라이버를 설치합니다. ojdbc11.jar 파일은 오라클 공식 홈페이지에서 설치할 수 있습니다. 설치된 ojdbc11.jar 파일은 톰캣이 설치된 파일 안의 lib 하위 폴더 안에 저장하는 방식과 실습 프로젝트 폴더 안의 WEB-INF / lib 폴더 안에 저장하는 방식이 있습니다. 저는 후자의 방식으로 진행합니다.



![img](https://blog.kakaocdn.net/dn/bM5tM3/btsxh9LZjdz/uiaKD1lxEJ4DvwvUYWZxE1/img.png)



<br />



### 5.6.2 연결 관리 클래스 작성

```java
package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletContext;

public class JDBConnect {
    public Connection con;
    public Statement stmt;
    public PreparedStatement psmt;
    public ResultSet rs;

    // 기본 생성자
    public JDBConnect() {
        try {
            // JDBC 드라이버 로드
            Class.forName("oracle.jdbc.OracleDriver");

            // DB에 연결
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String id = "c##sqld";
            String pwd = "sqld";
            con = DriverManager.getConnection(url, id, pwd);

            System.out.println("DB 연결 성공(기본 생성자)");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 연결 해제(자원 반납)
    public void close() {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (psmt != null) psmt.close();
            if (con != null) con.close();

            System.out.println("JDBC 자원 해제");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

`Connection` 외에는 당장 사용하지 않지만, 데이터베이스 작업에 흔히 사용하는 객체들이기 때문에 미리 선언했습니다.

- `Connection`
  **데이터베이스와 연결을 담당**합니다.
- `Statement`
  **인파라미터가 없는 정적 쿼리문을 실행할 때 사용**합니다. 인파라미터는 쿼리문 작성 시 매개변수로 전달된 값을 설정할 때 사용합니다. 보통 물음표(`?`)로 표현하는데, 다음 예제에서 설명합니다.
- `PreparedStatement`
  **인파라미터가 있는 동적 쿼리문을 실행할 때 사용**합니다.
- `ResultSet`
  **SELECT 쿼리문의 결과를 저장할 때 사용**합니다.



<br />



**JDBConnect() 생성자**

- **JDBC 드라이버 로드**

  ```java
  Class.forName("oracle.jdbc.OracleDriver")
  ```

  JDBC 드라이버 로드를 위해 Class 클래스의 **forName() 은 new 키워드 대신 클래스명을 통해 직접 객체를 생성한 후 메모리에 로드하는 메서드**입니다. 오라클 데이터베이스를 사용하기 때문에 오라클 드라이버 클래스명을 인수로 작성합니다. JDBC 드라이버로 사용될 클래스는 DB 종류에 따라 클래스명을 다르게 지정해야 합니다.



<br />



- **DB 연결**

  접속할 DB 연결 정보를 입력해야 합니다. **URL 은 "오라클 프로토콜@IP주소:포트번호:SID" 형식으로 구성되고, ID, PWD 는 DB 계정과 동일하게 작성**하면 됩니다. SID 는 설치 환경에 따라 달라지는데, 오라클 인스턴스의 식별자를 의미합니다. 작성한 URL, ID, PWD 는 `DriverManager.getConnection(url, id, pwd)` 객체로 전달합니다. 연결에 성공하면 Connection 객체가 반환됩니다.

  ```sql
  jdbc:oracle:thin:@localhost:1521:xe
  ```

  - `jdbc:oracle:thin` : 오라클 프로토콜
  - `localhost` : 호스트명(혹은 IP 주소)
  - `1521` : 포트 번호
  - `xe` : SID

  ```java
  DriverManager.getConnection(url, id, pwd)
  ```



<br />



- **DB 연결 해제**

  DB 관련 작업 수행을 모두 마쳤다면 자원을 절약하기 위해 연결을 해제해야 합니다. `close()` 메서드를 통해 연결 해제합니다.





<br />



### 5.6.3 동작 확인

```jsp
<%@ page import="common.JDBConnect"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head><title>JDBC</title></head>
<body>
    <h2>JDBC 테스트 1</h2>
    <%
    JDBConnect jdbc1 = new JDBConnect(); 
    jdbc1.close(); 
    %>
</body>
</html>
```



<br />



### 5.6.4 연결 설정 개선

서버 환경과 관련된 정보들을 한 곳에서 관리할 수 있도록 변경합니다. 주로 web.xml 에 입력하고 필요할 때마다 application 내장 객체를 통해 얻어옵니다. web.xml 에 컨텍스트 초기화 매개변수 `<(context-param)>` 로 입력합니다.

```xml
  <!-- 오라클 데이터베이스 접속 정보(예제 5-8) -->
  <!-- 1. 드라이버명 -->
  <context-param>
    <param-name>OracleDriver</param-name>
    <param-value>oracle.jdbc.OracleDriver</param-value>
  </context-param>

  <!-- 2. 접속 URL -->
  <context-param>
    <param-name>OracleURL</param-name>
    <param-value>jdbc:oracle:thin:@localhost:1521:xe</param-value>
  </context-param>

  <!-- 3. 계정 아이디 -->
  <cont3ext-param>
    <param-name>OracleId</param-name>
    <param-value>c##sqld</param-value>
  </context-param>

  <!-- 4. 패스워드 -->
  <context-param>
    <param-name>OraclePwd</param-name>
    <param-value>sqld</param-value>
  </context-param>
```

오라클에 접속하기 위한 드라이버명, 접속 URL, 계정 아이디, 비밀번호를 web.xml 파일 안에 접속 정보를 작성했습니다.
 작성된 접속 정보를 바탕으로 데이터베이스에 연결을 위한 생성자를 새롭게 작성해야 합니다.



<br />



```java
    public JDBConnect(String driver, String url, String id, String pwd) {
        try {
            // JDBC 드라이버 로드
            Class.forName(driver);

            // DB에 연결
            con = DriverManager.getConnection(url, id, pwd);

            System.out.println("DB 연결 성공(인수 생성자 1)");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
```

**web.xml 파일 안에 작성한 URL, 아이디, 비밀번호를 매개변수로 받는 JDBConnect 생성자를 생성합니다.** 이처럼 매개변수만 다르게 해서 생성자 혹은 메서드를 추가하는 것을 오버로딩(Overloading) 이라고 부릅니다. 작성한 생성자는 JSP 파일 내에서 호출해야 합니다.



<br />



```jsp
    <h2>JDBC 테스트 2</h2>
    <%
    String driver = application.getInitParameter("OracleDriver"); 
    String url = application.getInitParameter("OracleURL");
    String id = application.getInitParameter("OracleId");
    String pwd = application.getInitParameter("OraclePwd");

    JDBConnect jdbc2 = new JDBConnect(driver, url, id, pwd); 
    jdbc2.close();
    %>
```

**application 내장 객체의 getInitParameter() 메서드로 web.xml 안에 작성된 컨텍스트 초기화 매개변수를 가져올 수 있습니다.**



<br />



### 5.6.5 연결 설정 개선 2

JSP 에서 가져오는 방식이 아닌, 컨텍스트 초기화 매개변수를 JDBConnect 생성자에서 직접 가져올 수 있도록 작성합니다.

```java
    // 세 번째 생성자
    public JDBConnect(ServletContext application) {
        try {
            // JDBC 드라이버 로드
            String driver = application.getInitParameter("OracleDriver"); 
            Class.forName(driver); 

            // DB에 연결
            String url = application.getInitParameter("OracleURL"); 
            String id = application.getInitParameter("OracleId");
            String pwd = application.getInitParameter("OraclePwd");
            con = DriverManager.getConnection(url, id, pwd);

            System.out.println("DB 연결 성공(인수 생성자 2)"); 
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
```

접속 정보를 직접 읽어오는 생성자를 추가합니다. JDBConnect 생성자의 매개변수는 application 내장 객체로, web.xml 안에 작성된 컨텍스트 초기화 매개변수를 가져오는 것은 동일합니다. 그리고 application 내장 객체 타입인 ServletContext 를 사용하기 위해 임포트합니다.



<br />



```jsp
    <h2>JDBC 테스트 3</h2>
    <%
    JDBConnect jdbc3 = new JDBConnect(application); 
    jdbc3.close();
    %>
```

JSP 파일 내에서 application 내장 객체를 인수로 전달합니다. 이 방식은 DB 에 접속할 때마다 JSP 에서 컨텍스트 초기화 매개변수를 읽어오지 않아도 되기 때문에 코드가 훨씬 간결합니다.



<br />



## 5.7 커넥션 풀로 성능 개선

**커넥션 풀(Connection Pool)은 Connection 객체를 미리 생성해 풀(Pool)에 넣어놓고, 요청이 있을 때 이미 생성된(Connection) 객체를 가져다 사용하는 기법입니다. 다 사용한 객체는 연결을 해제하는 것이 아닌, 풀에 반납하여 필요할 때 재사용합니다.**

커넥션 풀을 사용하는 이유는 요청이 있을 때마다 DB 와 새로 연결하고 해제를 반복해야 합니다. DB 작업을 위해 웹 서버는 Connection 객체를 생성할 때마다 네트워크 통신이 발생하고 사용자는 인증 같은 시간이 오래 걸리게 됩니다. 따라서 빈번한 연결과 해제는 시스템 성능에 큰 영향을 미치게 됩니다. 이 문제의 해법으로 가장 널리 쓰이는 방식이 커넥션 풀입니다.



### 5.7.1 커넥션 풀과 JNDI

JSP 프로그래밍 시 커넥션 풀은 직접 만들어 사용하지 않고 WAS가 제공하는 것을 이용하는 것이 좋습니다. WAS 하나에 여러 개의 웹 애플리케이션을 구동시키는 경우가 많은데, 애플리케이션마다 자원을 따로 관리하면 낭비도 심하고 관리하기도 어렵기 때문입니다.

대부분의 WAS는 커넥션 풀을 비롯한 여러 자원을 JNDI 서비스로 제공합니다. **JNDI(Java Naming and Directory Interface)는 자바 소프트웨어에서 객체나 데이터의 전체 경로를 몰라도 '이름'만으로 찾아 쓸 수 있는 디렉터리 서비스**입니다. 이름과 실제 객체와의 연결은 외부의 설정 파일에서 관리하므로 다른 객체로 교체하거나 세부 설정을 변경할 때도 소스 코드를 수정하고 다시 컴파일할 필요가 없습니다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231020151538902.png" alt="image-20231020151538902" style="zoom:67%;" />

JNDI 와 비슷한 예로 DNS(Domain Name Service)가 있습니다. 웹 브라우저에서 도메인을 입력하면 DNS를 통해 웹 서버의 IP 주소를 얻어와 해당 주소로 접속합니다. DNS가 도메인과 IP 주소를 연결해주는 듯이 **JNDI도 객체 이름과 실제 객체를 연결해주는 역할을 수행**합니다. 다음은 WAS의 JNDI를 통해 커넥션 풀을 사용하는 개략적인 절차입니다. 

1. WAS(톰캣)이 시작할 때 server.xml과 context.xml에 설정한 대로 커넥션 풀을 생성합니다.
2. JSP 코드에서 JNDI 서버(WAS가 제공)로부터 데이터소스 객체를 얻어옵니다.
3. 데이터소스로부터 커넥션 객체를 가져옵니다.
4. DB 작업을 수행합니다.
5. 모든 작업이 끝나면 커넥션 객체를 풀로 반환합니다.

지금까지 설명하지 않은 server.xml과 context.xml에 설정하는 부분과 데이터소스 객체가 있습니다. **두 .xml 파일들은 커넥션 풀의 구체적인 속성을 정의하고 JNDI 서비스에 등록하는 역할**을 수행합니다. **데이터소스는 단순히 JDBC 연결을 생성해 제공해주는 객체**입니다.

커넥션 풀을 사용하면 WAS가 시작될 때 미리 생성한 커넥션 객체를 사용하므로 웹 애플리케이션 실행 속도가 빨라지고, 클라이언트의 동시 요청이 많아지더라도 좀 더 수월하게 응답할 수 있습니다. 성능 효과가 크기 때문에 웹 뿐만 아니라 게임 등에서도 많이 사용합니다.



### 5.7.2 커넥션 풀 설정

- 톰캣이 설치된 경로의 conf 디렉터리 하위에 server.xml, context.xml 파일을 수정
- server.xml: 서버 전체와 관련한 설정을 담은 파일
- context.xml: 각 웹 애플리케이션마다 하나씩 주어지는 자원을 설정

server.xml에 커넥션 풀을 전역 자원으로 선언하고 context.xml에서 이를 참조하는 링크를 추가합니다.



**server.xml 수정**

`<GlobalNamingResources>` 태그는 전역 자원을 등록하는 영역입니다. 따라서 이 태그 안에 등록한 자원은 이 서버에서 구동되는 모든 웹 애플리케이션에서 사용할 수 있습니다. `<GlobalNamingResources>` 태그 안의 기존 코드는 수정하지 않고, 닫히는 태그 윗부분에 다음 코드를 추가합니다. 

```xml
  <GlobalNamingResources>
      ...
     <Resource auth="Container"
              driverClassName="oracle.jdbc.OracleDriver"
              type="javax.sql.DataSource" 
              initialSize="0"
              minIdle="5"
              maxTotal="20"
              maxIdle="20"
              maxWaitMillis="5000"
              url="jdbc:oracle:thin:@localhost:1521:xe"
              name="dbcp_myoracle"
              username="musthave"
              password="1234" />
  </GlobalNamingResources>
```

`<Resource>` 태그 안에는 다양한 속성이 있습니다. 각 속성의 의미는 다음과 같습니다.

- `auth`: 데이터베이스 연결 인증 방식을 지정( `"Container"` 값은 서버 컨테이너(예: Apache Tomcat)에서 관리되는 인증을 사용한다는 것을 나타냅니다.)
- `driverClassName`: JDBC 드라이버의 클래스명
- `type`: 데이터소스로 사용할 클래스명
- `initialSize` : 풀의 최초 초기화 과정에서 미리 만들어놓을 연결의 개수(기본값은 0)
- `minIdle` : 최소한으로 유지할 연결 개수(기본값은 0)
- `maxTotal` : 동시에 사용할 수 있는 최대 연결 개수(기본값은 8)
- `maxIdle` : 풀에 반납할 때 최대로 유지할 수 있는 연결 개수(기본값은 8)
- `maxWaitMillis` : 새로운 요청이 들어왔을 때 얼마나 대기할지를 밀리초 단위로 기술
- `url` : 오라클 연결을 위한 URL
- `name ` : 생성할 자원(여기서는 풀)의 이름
- `username` : 계정 아이디
- `password` : 계정 패스워드

쉽게 정리하자면, type 속성으로 지정한 javax.sql.DataSource는 물리적으로 데이터 소스와의 연결을 생성해주는 자바 표준 인터페이스이며, driverClassName 속성으로 지정한 oracle.jdbc.OracleDriver 클래스가 이 인터페이스를 구현하고 있습니다. 즉, 오라클이 제공하는 OracleDriver 클래스가 커넥션 풀 기능을 구현하며, 우리는 자바 표준 인터페이스인 DataSource 형태로 받아 이용하는 것입니다.



**context.xml 수정**

`<Context>` 태그를 찾아 다음 내용을 추가합니다.

```xml
<Context>
...
    <ResourceLink global="dbcp_myoracle" name="dbcp_myoracle" 
                  type="javax.sql.DataSource"/>
</Context>
```



`<Context>` 태그는 풀의 이름과 데이터 소스로 사용할 클래스명을 기술합니다. 여기서 global 속성에는 앞서 server.xml에서 등록한 커넥션 풀 전역 자원의 이름을 명시해야 합니다. 즉, 다음 그림과 같은 관계가 형성됩니다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231020153924204.png" alt="image-20231020153924204" style="zoom:77%;" />



### 5.7.3 커넥션 풀 동작 검증

Server 탭에서 등록된 Tomcat 10.1 을 삭제하고 새로운 서버를 등록합니다. 동일하게 Tomcat 10.1 을 선택합니다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231020154150420.png" alt="image-20231020154150420" style="zoom:80%;" />

정상적으로 Tomcat 서버가 실행되는 것을 확인하고 마칩니다. 

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231020202106234.png" alt="image-20231020202106234" style="zoom:80%;" />

이제 커넥션 풀을 실제로 이용하기 위해 연결 클래스 코드를 다음과 같이 작성합니다.

```java
package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnPool {
    public Connection con;
    public Statement stmt;
    public PreparedStatement psmt;
    public ResultSet rs;

    // 기본 생성자
    public DBConnPool() {
        try {
            // 커넥션 풀(DataSource) 얻기
            Context initCtx = new InitialContext();
            Context ctx = (Context)initCtx.lookup("java:comp/env");
            DataSource source = (DataSource)ctx.lookup("dbcp_myoracle");

            // 커넥션 풀을 통해 연결 얻기
            con = source.getConnection();

            System.out.println("DB 커넥션 풀 연결 성공");
        }
        catch (Exception e) {
            System.out.println("DB 커넥션 풀 연결 실패");
            e.printStackTrace();
        }
    }

    // 연결 해제(자원 반납)
    public void close() {
        try {            
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (psmt != null) psmt.close();
            if (con != null) con.close();  // 자동으로 커넥션 풀로 반납됨

            System.out.println("DB 커넥션 풀 자원 반납");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

JDBConnect와 차이가 나는 부분은 바로 생성자입니다. JDBConnect가 JDBC 드라이버를 로드하고 DB와 연겨를 직접 만들었다면, **DBConnPool은 JNDI로부터 데이터 소스를 찾은 후 데이터 소스로부터 연결**을 얻습니다. 

다음 코드에서는 InitialContext 객체를 생성합니다. JNDI에서 이름과 실제 객체를 연결해주는 개념이 Context이며, InitialContext는 네이밍 서비스를 이용하기 위한 시작점입니다. 이 객체의 lookup() 메서드에 이름을 건네 원하는 객체를 찾아올 수 있습니다.

```java
Context initCtx = new InitialContext();
```



`"javac:comp/env"`라는 이름을 인수로 Context 객체를 얻습니다. `"java:comp/env"`는 현재 웹 애플리케이션의 루트 디렉터리라고 생각하면 됩니다. 즉, 현재 웹 애플리케이션이 사용할 수 있는 모든 자원은 `"java:comp/env"` 아래에 위치합니다.

```java
Context ctx = (Context)initCtx.lookup("java:comp/env");
```



`"java:comp/env"`아래에 위치한 `"dbcp_myoracle"`자원을 얻어옵니다. 이 자원이 바로 앞서 설정한 데이터 소스(커넥션 풀)입니다. 여기서 **`"dbcp_myoracle"`은 context.xml 파일에 추가한 `<ResourceLink>`에 있는 name 속성의 값**입니다.

```java
DataSource source = (DataSource)ctx.lookup("dbcp_myoracle");
```



마지막으로 데이터 소스를 통해 풀에 생성되어 있는 연결 객체를 어덩와 멤버 변수에 저장하면 생성 과정이 마무리 됩니다.

```java
con = source.getConnection();
```



다 사용된 연결의 반납은 아주 간단합닌다. 풀을 사용하지 않을 때와 동일하게 Connection 객체의 close()만 호출해주면 자동으로 풀이 반납됩니다. 

```java
    // 연결 해제(자원 반납)
    public void close() {
        try {            
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (psmt != null) psmt.close();
            if (con != null) con.close();  // 자동으로 커넥션 풀로 반납됨

            System.out.println("DB 커넥션 풀 자원 반납");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
```



커넥션 풀 테스트 코드를 다음과 같이 작성합니다. 새로 만든 클래스인 DBConnPool을 사용해야 하므로 임포트도 추가합니다.

```jsp
<%@ page import="common.JDBConnect"%> 
<%@ page import="common.DBConnPool"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head><title>JDBC</title></head>
<body>
... 
    <h2>커넥션 풀 테스트</h2>
    <%
    DBConnPool pool = new DBConnPool();
    pool.close();
    %>
</body>
</html>
```



## 5.8 간단한 쿼리 작성 및 실행

JDBC에서 쿼리문은 **java.sql.Statement** 인터페이스로 표현되며, **Statement** 객체는 **Connection** 객체를 통해 얻어오도록 되어 있습니다. Statement 계열의 인터페이스는 다음과 같이 세 가지가 있습니다.

- **Statement** : 인파라미터가 없는 정적 쿼리를 처리할 때 사용
- **PreparedStatement** : 인파라미터가 있는 동적 쿼리를 처리할 때 사용
- **CallableStatement** : 프로시저(Procedure)나 함수(Function)를 호출할 때 사용

인파라미터(IN Parameter)는 미리 작성해둔 쿼리문에서 일부 값을 나중에 결정할 수 있게 해주는 매개변수입니다. 쿼리문에서 물음표`?`로 표현합니다. Statement 계열의 객체로 쿼리문을 실행할 때는 다음의 두 메서드를 이용합니다.

- **executeUpdate()** : INSERT, UPDATE, DELETE 쿼리문을 실행할 때 사용합니다. 기존 레코드를 변화시키거나 새로운 레코드를 입력하는 쿼리문들입니다. 따라서 **실행 후 영향을 받은 행의 개수가 int 형으로 반환**됩니다.
- **executeQuery()** : SELECT 쿼리문을 실행할 때 사용합니다. SELECT 는 기존 레코드를 조회하는 쿼리문입니다. **조회한 레코드들의 집합인 ResultSet** 객체를 반환합니다.

단, Statement와 PreparedStatement의 사용 방법에는 약간의 차이가 있습니다. 그 차이는 잠시 후에 확인합니다.



### 5.8.1 동적 쿼리문으로 회원 추가

회원 테이블에 새로운 회원을 추가하는 프로그램을 작성합니다. 일부 값을 나중에 명시할 수 있는 동적 쿼리문을 사용해볼 것이며, 자바에서는 PreparedStatement로 동적 쿼리문을 표현합니다.

```jsp
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head><title>JDBC</title></head>
<body>
    <h2>회원 추가 테스트(executeUpdate() 사용)</h2>
    <%
    // DB에 연결
    JDBConnect jdbc = new JDBConnect();
    
    // 테스트용 입력값 준비 
    String id = "test1";
    String pass = "1111";
    String name = "테스트1회원";

    // 쿼리문 생성
    String sql = "INSERT INTO member VALUES (?, ?, ?, sysdate)";  
    PreparedStatement psmt = jdbc.con.prepareStatement(sql);  
    psmt.setString(1, id);
    psmt.setString(2, pass);
    psmt.setString(3, name);

    // 쿼리 수행
    int inResult = psmt.executeUpdate(); 
    out.println(inResult + "행이 입력되었습니다.");

    // 연결 닫기
    jdbc.close(); 
    %>
</body>
</html>

```

작업 순서는 DB 연결 -> 입력값 준비 -> 쿼리문 생성 -> 연결 닫기 순으로 이루어집니다.

1. 먼저 앞서 준비한 JDBConnect 객체를 생성
2. 테이블에 입력할 값을 준비
3. Coonection 객체를 통해 PreparedStatement 객체를 생성
4. 인파라미터에 실제 값을 대입
5. 쿼리문 실행
6. DB 연결 해제

PreparedStatement는 먼저 쿼리문의 틀을 준비하고 인파리미터를 사용하는 방식으로 동작합니다. 인파라미터 설정 시에는 데이터 타입에 맞는 set 메서드를 사용하면 됩니다. set 메서드는 타입별로 다양하게 준비되어 있지만 거의 대부분은 다음 세 메서드로 처리할 수 있습니다.

- void **setInt**(int Index, **int** value)
- void **setDate**(int index, **Date** value)
- void **setString**(int index, **String** value)



### 5.8.2  정적 쿼리문으로 회원 조회

회원 목록을 JSP를 통해 조회를 수행합니다. 정적 쿼리문은 모든 내용이 처음부터 완벽하게 정의되어 더는 변할 게 없는 쿼리를 의미합니다.

```jsp
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
       pageEncoding="UTF-8"%>  
<html>
<head><title>JDBC</title></head>
<body>
    <h2>회원 목록 조회 테스트(executeQuery() 사용)</h2>
    <%
    // DB에 연결
    JDBConnect jdbc = new JDBConnect();

    // 쿼리문 생성   
    String sql = "SELECT id, pass, name, regidate FROM member";  
    Statement stmt = jdbc.con.createStatement();  

    // 쿼리 수행
    ResultSet rs = stmt.executeQuery(sql);  

    // 결과 확인(웹 페이지에 출력)
    while (rs.next()) { 
        String id = rs.getString(1);
        String pw = rs.getString(2);
        String name = rs.getString("name");
        java.sql.Date regidate = rs.getDate("regidate");
        
        out.println(String.format("%s %s %s %s", id, pw, name, regidate) + "<br/>"); 
    }

    // 연결 닫기
    jdbc.close();
    %>
</body>
</html>
```

DB 연결 부분은 회원 추가와 큰 차이는 없으며 쿼리문 생성 부분이 훨씬 간결합니다. SQL문에는 인파라미터가 젼혀 없으며 PreparedStatement가 아닌 **Statement**를 생성했습니다. 이때 이용한 메서드도 prepareStatement()가 아닌 **createStatement()**입니다. 

쿼리 수행에는 **executeQuery()** 메서드를 이용합니다.(앞 예제에서는 executeUpdate() 메서드를 사용했다는 점을 기억하세요.) 결과는 **ResultSet** 객체를 받았습니다. **ResultSet 객체는 조회 결과를 담고 있는 집합**입니다.

**next() 메서드는 ResultSet 객체에 다음 행(레코드)를 반환**합니다. 반환된 행에서 ID, 패스워드, 이름, 가입 날짜를 차례로 읽어 웹 페이지에 출력합니다. 값을 가져올 때 사용하는 set 계열 메서드들에서 컬럼을 지정할 때는 인덱스와 컬럼명 둘 다 사용할 수 있습니다. 단, **컬럼명을 사용하면 차후 테이블에서 새로운 컬럼이 추가되거나 순서가 변경되어도 소스 코드 수정 없이 사용할 수 있는 이점**이 있습니다.

예제를 통해 알아보았듯이 JDBC 프로그래밍은 다음과 같은 수서로 진행합니다.

1. **JDBC 드라이버 로드**
2. **데이터베이스 연결**
3. **쿼리문 작성**
4. **쿼리문(Statement 계열) 객체 생성**
5. **쿼리 실행**
6. **실행 결과 처리**
7. **연결 해제**



**핵심 요약**

- 오라클을 사용하려면 새로운 사용자 계정을 생성한 후 적절한 권한을 부여합니다.
- 이클립스, 톰캣, 오라클 등 많은 도구를 사용하니 설정 시 세심한 주의가 필요하며, 각종 설정 파일 위치와 역할을 익혀두면 좋습니다.
- 커넥션 풀을 이용하면 자원을 더욱 효율적으로 이용할 수 있습니다.
- 쿼리문 내용이 고정된 정적 쿼리문(Statement)과 일부 내용을 나중에 바꿀 수 있는 동적 쿼리문(PreparedStatement)이 있습니다.
- 쿼리문 객체는 Connection 객체로부터 얻어, SQL문으로 내용을 채운 후 사용합니다.
- 테이블에 저장된 레코드를 변경하지 않는 SELECT 문은 executeQuery() 메서드로 실행하고, 레코드를 변경하는 INSERT, UPDATE, DELETE 문은 executeUpdate() 메서드로 실행합니다.