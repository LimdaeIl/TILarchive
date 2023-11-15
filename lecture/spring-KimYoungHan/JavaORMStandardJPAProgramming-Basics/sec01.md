# 섹션 1 JPA 소개



## 1. SQL 중심적인 개발의 문제점

- 관계형 데이터베이스는 데이터 중심으로 구조화되어 있고, 집학적인 사고를 요구한다. 
  그리고 객체지향에서 이야기하는 추상화, 상속, 다형성 같은 개념이 없다.
- 객체와 관계형 데이터베이스는 지향하는 목적이 서로 다르므로 둘의 기능과 표현 방법도 다르다. 
  이것을 객체와 관계형 데이터베이스의 패러다임 불일치 문제라 한다.
- **문제는 객체와 관계형 데이터베이스 사이의 패러다임 불일치 문제를 해결하는데 너무 많은 시간과 코드를 소비하는 데 있다.**



### 1.1 상속

객체는 상속이라는 기능을 가지고 있지만 테이블은 상속이라는 기능이 없다. 그나마 데이터베이스 모딜링에서 이야기하는 슈퍼타입 서브타입 관계를 사용하면 객체 상속과 가장 유사한 형태로 테이블을 설계할 수 있다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231114121849582.png" alt="image-20231114121849582" style="zoom:70%;" />

## [JPA와 상속](https://github.com/cheese10yun/TIL/blob/master/Spring/jpa/자바ORM표준JPA프로그래밍.md#jpa와-상속)

- JPA는 상속과 관련돤 패러다임의 불일치 문제를 개발자 대신 해결해준다. 개발자는 마치 자바 컬렉션에 객체를 저장하듯이 JPA에게 객체를 저장하면 된다.

## [연관관계](https://github.com/cheese10yun/TIL/blob/master/Spring/jpa/자바ORM표준JPA프로그래밍.md#연관관계)

- 객체는 참조를 사용해서 다른 객체와 연관관계를 가지고 참조에 접근해서 연관된 객체를 조회한다. 반면에 테이블은 외래 키를 사용해서 다른 테이블과 연관관계를 가지고 조인을 해서 연관된 테이블을 조회한다.

## [객체를 테이블에 맞추어 모델링](https://github.com/cheese10yun/TIL/blob/master/Spring/jpa/자바ORM표준JPA프로그래밍.md#객체를-테이블에-맞추어-모델링)

```
class Member {
    String id; // MEMBER_ID 칼럼사용
    Long teamId;  // TEAM_ID PK 칼럼사용
    String username; //USERNAME 칼럼사용
}

class Team {
    Loing id; //TEAM_ID PK 사용
    String name; //NAME 칼럼사용
}
```



- **관계형 데이터베이스는 조인이라는 기능이 있음으로 외래 키의 값을 그대로 보관해도 된다. 하지만 객체는 연관된 객체의 참조를 보관해야한다 다음 처럼 구조를 통해 연관돤 객체를 찾을 수 있다.**

## [객체지향 모델링](https://github.com/cheese10yun/TIL/blob/master/Spring/jpa/자바ORM표준JPA프로그래밍.md#객체지향-모델링)

```
class Member {
    String id; // MEMBER_ID 칼럼사용
    Team team; // 참조로 연관관계를 맺는다.
    String username; //USERNAME 칼럼사용
}

class Team {
    Loing id; //TEAM_ID PK 사용
    String name; //NAME 칼럼사용
}
```



- **외래 키의 값을 그대로 보관하는 것이 아니라 연관된 Team의 참조를 보관한다.**
- 그런데 이처럼 객체지향 모델링을 사용하면 객체를 테이블에 저장하거나 조회하기가 쉽지 않다.
  - Member 객체는 team 필드로 연관관계를 맺고 Member 테이블은 TEAM_ID 외래 키로 연관관계를 맺기 때문이다.
  - 반면에 테이블은 참조가 필요 없고 외래 키만 있으면 된다. 결국 개발자가 중간에 변화 역할을 해야한다.

### [저장](https://github.com/cheese10yun/TIL/blob/master/Spring/jpa/자바ORM표준JPA프로그래밍.md#저장)

- 객체를 데이터베이스에 저장하면 team 필드를 TEAM_ID 외래 키 값으로 변환 해야한다.
- 다음처럼 외래 키 값을 찾아서 INSERT SQL을 만들어야 한다.

```
member.getId(); //Member_ID PK에 저장
member.getTeam.getId(); //Team_ID PK에 저장
member.getUsername(); //Username 칼럼에 저장
```



### [조회](https://github.com/cheese10yun/TIL/blob/master/Spring/jpa/자바ORM표준JPA프로그래밍.md#조회)

- 조회할 때는 TEAM_ID 외래 키 값을 Member 객체의 team 참조로 변환해서 객체에 보관해야 한다.

```
SLELECT M.*, T.*
    FROM MEMBER M
    JOIN TEAM T ON M.TEAM_ID = T.TEAM_ID

public Member find(String memberId) {
    //SQL 실행
    Member member = new Member();
    ...
    
    //데이터베이스에 조회한 회원 관련 정보를 모두 입력
    Team team = new Team();

    member.setTeam(team);
    return member;
}
```



- **이러한 과정들은 모두 패러다임 불일치를 해결햐려고 소모하는 비용이다. 만약 자바 컬렉션에 회원 객체를 저장한다면 이런 비용이 전혀들지 않는다.**

## [JPA와 연관관계](https://github.com/cheese10yun/TIL/blob/master/Spring/jpa/자바ORM표준JPA프로그래밍.md#jpa와-연관관계)

- JPA는 연관관계와 관련된 패러다임의 불일치 문제를 해결해준다.

```
member.setTeam(team); //회원과 팀 연관관계 설정
jpa.persust(member); //회원과 연관관계 함께 저장
```



- 개발자는 회원과 팀의 관계를 설정하고 회원 객체를 저장하면 된다.
- JPA는 team의 참조를 외래 키로 변환해서 적적한 INSERT SQL을 데이터베이스에 절달한다.

## [객체 그래프 탐색](https://github.com/cheese10yun/TIL/blob/master/Spring/jpa/자바ORM표준JPA프로그래밍.md#객체-그래프-탐색)

- 객체에서 회원이 소속된 팀을 조회할 때는 다음처럼 참조를 사용해서 연관된 팀을 찾으면 되는데 이것을 객체 그래프 탐색이라고 한다.

```
Team team = member.getTeam(); // 객체를 참조해서 객체 그래프 탐색
member.getOrder().goetOderItem().... // 자율운 객체 그래프 탐색

member.getOder(); // null 인경우에는 객체 그래프 탐색을 할 수 가 없다.
```



- 에를들어 MemberDAO에서 member 객체를 조회할 때 이런 SQL을 실행해서 회원과 팀에 대한 데이터만 조회했다면 member.getTeam()은 성공하지만 다음처럼 다른 객체 그래프는 데이터가 없다면 탐색할 수 없다.
- **SQL을 직접 다루면 처음 실행하는 SQL에 따라 객체 그래프를 어디까지 탐색할 수 있을지 정해야한다.**
- **이것은 객체지향 개발자에겐 너무 큰 제약이다. 객체 그래프가 다른데 언제 끊어질지 모를 객체 그래프를 함부로 탐색할 수는 없기 때문이다.**

## [JPA와 객체 그래프 탐색](https://github.com/cheese10yun/TIL/blob/master/Spring/jpa/자바ORM표준JPA프로그래밍.md#jpa와-객체-그래프-탐색)

- JPA를 사용하면 객체 그래프를 마음껏 탐색할 수 있다.
- JPA는 연관된 객체를 사용하는 시점에서 적절한 SELECT SQL을 실행한다. 따라서 JPA를 사용하면 연관된 객체를 신뢰하고 마음껏 조회할 수 있다.
- 이 기능은 실제 객체를 사용하는 시점까지 데이터베이스 조회를 미룬다고 해서 지연 로딩이라고 한다.
- Member를 사용할 때 마다 Order를 함께 사용하면, 이렇게 한 테이블씩 조회하는 것보다 Member를 조회하는 시점에서 SQL 조인을 사용해서 Member와 Order를 함께 조회하는 것이 효과적이다.

## [비교](https://github.com/cheese10yun/TIL/blob/master/Spring/jpa/자바ORM표준JPA프로그래밍.md#비교)

- 데이터베이스는 기본키의 값으로 로우를 구분한다. 반면에 객체는 동일성 비교와 동등성 비교라는 두 가지 비교 방법이 있다.
- 동일성 비교는 == 비교다. 객체 인스턴스의 주소 값을 비교한다.
- 동등성 비교는 equals() 메서드를 사용해서 객체 내부의 값을 비교한다.

## [JPA란 무엇인가?](https://github.com/cheese10yun/TIL/blob/master/Spring/jpa/자바ORM표준JPA프로그래밍.md#jpa란-무엇인가)

- JPA는 자바 진영의 ORM 기술 표준이다.
- ORM은 객체와 관계형 데이터베이스를 매핑한다는 뜻이다. ORM 프레임워크는 객체와 테이블을 매핑해서 패러다임의 불일치 문제를 개발자 대신 해결해준다.
- **ORM 표준 프레임워크는 단순히 SQL을 개발자 대신 생성해서 데이터베이스에 전달해주는 것 뿐만아니라 앞서 이야기한 다양한 패러다임 의 불일치 문제를 해결해 준다.**

## [왜 JPA를 사용해야 하는가?](https://github.com/cheese10yun/TIL/blob/master/Spring/jpa/자바ORM표준JPA프로그래밍.md#왜-jpa를-사용해야-하는가)

### [생산성](https://github.com/cheese10yun/TIL/blob/master/Spring/jpa/자바ORM표준JPA프로그래밍.md#생산성)

- 자바 컬렉션에 객체를 저장하듯이 JPA에서 저장할 객체를 전달하면 된다
- 반복적인 코드와 CRUD용 SQL을 개발자가 직접 작성하지 않아도 된다.
- DDL 문을 자동으로 생성해주는 기능들도 데이터베이스 걸계 중심의 패러다임에서 객체 설계 중심으로 역전시킬 수 있다.

### [유지보수](https://github.com/cheese10yun/TIL/blob/master/Spring/jpa/자바ORM표준JPA프로그래밍.md#유지보수)

- SQL을 직접다루면 엔티티에 필드를 하나만 추가해도 관련된 등록, 수정, 조회 SQL과 결과를 매핑하기 위한 JDBC API 코드를 모두 변경해야 했다. 반면 JPA를 사용하면 이런 과정을 JPA가 대신 처리해줌으로 필드를 추가하거나 삭제해도 수정해야 할 코드가 줄어 든다.
- 객체지향 언어가 가진 장점들을 활용해서 유연하고 유지보수하기 좋은 도메인 모델을 편리하게 설계할 수 있다.

### [패러다임의 불일치 해결](https://github.com/cheese10yun/TIL/blob/master/Spring/jpa/자바ORM표준JPA프로그래밍.md#패러다임의-불일치-해결)

- JPA는 상속, 연관관계, 객체 그래프 탐색 비교하기가와 같은 패러다임의 불일치 문제를 해결해준다.

### [성능](https://github.com/cheese10yun/TIL/blob/master/Spring/jpa/자바ORM표준JPA프로그래밍.md#성능)

- JPA는 애플리케이션과 데이터베이스 사이에서 다양한 서능 최적화 기회를 제공한다.
- 예를들어 SELECT SQL을 한 번만 데이터베이스에 전달하고 두 번째는 조회한 회원 객체를 재사용한다.
- 그 밖에 다양한 캐싱 기술들이 있고 이것을 적극 활용하면 성능이 좋아질 수 있다.

### [데이터접근 추상화와 벤더 독립성](https://github.com/cheese10yun/TIL/blob/master/Spring/jpa/자바ORM표준JPA프로그래밍.md#데이터접근-추상화와-벤더-독립성)

- 관계형 데이터베이스는 같은 기능도 벤더마다 사용벙이 다른 경우가 많다. 단적인 예로 페이징 처리는 데이터베이스마다 달라서 사용법이 각각 배워야한다.
- 결국 애플리케이션은 처음 선택한 데이터베이스 기술에 종속되고 다른 데이터 베이스고 변경하기 매우 어렵다.
