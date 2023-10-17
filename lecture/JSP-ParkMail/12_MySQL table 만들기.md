# 12_MySQL table 만들기

cmd 창에서 MySQL 을 실행하고, kr.web.db 패키지 아래에 member.sql 파일을 생성합니다.

![image-20231013131328919](C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231013131328919.png)

Data Source Ex... 이동합니다.커넥션 프로파일은 다음과 같이 MySQL 을 선택하고 데이터베이스는 이전에 생성한 test 를 선택합니다.

![image-20231013131237912](C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231013131237912.png)

member 테이블을 생성하고 값을 추가합니다. 코드는 다음과 같이 추가하고, 코드 실행은 실행할 텍스트를 선택합니다. 그리고 우클릭해서 Execute Selected Text 를 선택해서 실행하거나 실행 단축키인 Alt + X 를 누르면 됩니다.

![image-20231013131901098](C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231013131901098.png)

```sql
-- member(회원) table
create table member(
num int primary key auto_increment,
id varchar(20) not null,
pass varchar(20) not null,
name varchar(30) not null,
age int not null,
email varchar(30) not null,
phone varchar(30) not null
);

select * from member;

insert into member(id, pass, name, age, email, phone) 
values('admin', 'admin', '관리자', 40, 'admin@naver.com', '010-1111-1111');
```

이 방법으로 데이터베이스에서 CRUD를 수행합니다. 