# 12_MySQL table 만들기

cmd 창에서 MySQL 을 실행하고, kr.web.db 패키지 아래에 member.sql 파일을 생성합니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/02e17051-9f52-4973-9139-941ad2c3d496)


Data Source Ex... 이동합니다.커넥션 프로파일은 다음과 같이 MySQL 을 선택하고 데이터베이스는 이전에 생성한 test 를 선택합니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/f09746db-712a-4edd-9b8c-59b405edede7)


member 테이블을 생성하고 값을 추가합니다. 코드는 다음과 같이 추가하고, 코드 실행은 실행할 텍스트를 선택합니다. 그리고 우클릭해서 Execute Selected Text 를 선택해서 실행하거나 실행 단축키인 Alt + X 를 누르면 됩니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/2f9cb677-e695-4c05-bee8-37df71d08df6)


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
