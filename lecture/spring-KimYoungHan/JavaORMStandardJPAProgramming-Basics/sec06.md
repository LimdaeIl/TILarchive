# 자바 ORM 표준 JPA 프로그래밍 - 기본편 / 섹션 6



**시작하기 전에**

1. **관계**

   관계(Relationship)는 **사물과 사물 사이의 연관성을 표현하는 것을 의미**하고, 관계의 종류는 연관 관계, 집합 관계, 포함 관계, 일반화 관계, 의존 관계, 실체화 관계가 있습니다. 다양한 관계 중에서 연관 관계에 대해 학습을 진행합니다. 연관 관계와 관련된 자세한 내용은 https://velog.io/@limdae/UMLUnified-Modeling-Language 에서 학습할 수 있습니다.

2. **참조**

   "참조"는 프로그래밍에서 **한 객체가 다른 객체를 사용하거나 접근하는 것을 의미**합니다. 객체 간의 참조는 한 객체가 다른 객체의 메서드를 호출하거나, 필드 값을 읽거나 변경하는 등의 상호 작용을 가능하게 합니다.

   JPA에서의 "참조"는 주로 엔티티 간의 관계를 나타냅니다. 예를 들어, 학생 엔티티가 학과 엔티티를 참조한다는 것은 **학생 엔티티가 해당 학과의 정보를 사용할 수 있다는 것을 의미**합니다. 이는 객체 지향 프로그래밍에서의 연관 관계와 관련이 있습니다.

   또한, JPA에서 엔티티 간의 참조는 데이터베이스에서 **외래 키(Foreign Key)**와 관련이 있습니다. 예를 들어, 학생 엔티티가 학과 엔티티를 참조하면, 이는 데이터베이스에서 **학생 테이블의 외래 키로 학과 테이블의 기본 키를 사용하겠다는 것을 의미**합니다. 이러한 관계를 통해 데이터베이스에서도 두 엔티티 간의 관계를 유지할 수 있습니다.

   요약하면, "참조"는 JPA에서 엔티티 간의 관계를 표현하고, 이를 통해 객체 지향 프로그래밍의 개념을 유지하면서 데이터베이스에서도 관련 정보를 효과적으로 관리할 수 있게 합니다.



# 단방향 연관관계

- 단방향 연관관계는 엔티티 간의 관계를 의미한다. 
- 한 엔티티가 다른 엔티티를 참조할 수 있는 관계를 나타낸다. 
- 단방향 연관관계에서는 **한 엔티티에서 다른 엔티티로의 참조가 가능하지만, 반대 방향으로는 참조가 불가능**하다.



## 1. 단방향 연관관계 예제 1

예를 들어, 학생(Student)과 학과(Department)라는 두 엔티티가 있다고 가정해보겠습니다. 만약 학생 엔티티가 학과를 참조할 수 있다면, 이는 단방향 연관관계입니다. 학생은 학과에 속할 수 있지만 학과는 학생을 참조할 필요가 없는 경우가 이에 해당합니다.

다음은 단방향 연관관계를 나타내는 간단한 예제입니다. 여기서는 학생이 학과를 참조하고 있습니다.

```java
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne // 다대일 관계, 여러 학생이 하나의 학과에 속할 수 있음
    @JoinColumn(name = "department_id") // 외래키 설정
    private Department department;

    // getter, setter, 기타 메서드
}

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // 다른 필드 및 메서드

    // getter, setter, 기타 메서드
}
```

위 예제에서 `Student` 엔티티는 `Department` 엔티티를 참조하고 있습니다. 이것이 단방향 연관관계의 예입니다. 
반대로 `Department` 엔티티에서 `Student` 엔티티를 참조하지 않습니다. 이것이 바로 단방향의 특징입니다.



# 양방향 연관관계

- 양방향 연관관계는 두 엔티티 간의 관계가 서로를 참조하는 것을 의미한다. 
- **한 엔티티가 다른 엔티티를 참조하고 동시에 그 엔티티도 다시 처음의 엔티티를 참조할 수 있는 상태를 의미**한다.



## 1. 양방향 연관관계 특징

- 양방향 연관관계를 구현하기 위해서는 서로를 참조하는 필드를 두 엔티티에 추가해야 한다. 
- 이러한 관계에서는 주로 양쪽 엔티티 중 하나를 주인(Owner)으로 선택하고, 이를 통해 연관관계를 관리한다. 
- **주인은 외래키를 관리**하고, 양방향 매핑에서는 **주인 쪽에서 mappedBy 속성을 사용하여 반대쪽 엔티티의 매핑 정보를 제공**한다.



## 2. 양방향 연관관계 예제 1

다시 앞서의 학생(Student)과 학과(Department) 예제를 양방향 연관관계로 확장해보겠습니다.

```java
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // getter, setter, 기타 메서드

    // 양방향 연관관계를 위한 추가 코드
    @OneToMany(mappedBy = "department")
    private List<Student> students = new ArrayList<>();
}

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // getter, setter, 기타 메서드

    // 양방향 연관관계를 위한 추가 코드
    @OneToMany(mappedBy = "department")
    private List<Student> students = new ArrayList<>();
}
```

이 예제에서는 `Student` 엔티티와 `Department` 엔티티가 양방향 연관관계를 가지고 있습니다. `Student` 엔티티의 `department` 필드는 단방향 연관관계를 표현하고 있고, 추가로 `students` 필드를 통해 반대 방향인 `Department` 엔티티를 참조합니다. 마찬가지로 `Department` 엔티티도 `students` 필드를 통해 `Student` 엔티티를 참조합니다.

양방향 연관관계를 사용할 때는 상호 참조가 제대로 설정되어야 하므로, 양쪽 엔티티에 모두 관련된 정보를 설정하는 것이 중요합니다. 이를 통해 JPA는 연관관계를 올바르게 유지하고 데이터베이스에 적절한 외래키를 관리할 수 있습니다.





# 객체와 테이블의 연관관계

JPA는 객체와 관계형 DB 두 기둥 위에 있는 기술이라고 불리는데, 그 이유는 객체와 관계형 DB를 이어주는 기술이기 때문입니다. 따라서 연관관계는 객체와 관계형 DB 모두에서 정말 중요합니다. 그러나 둘은 다릅니다. 아래 사진을 봅시다.

<img src="https://blog.kakaocdn.net/dn/NbHS5/btrsxhzUxpx/ByRuJliK4Bj7huK3PdZsPk/img.png" alt="img" style="zoom:65%;" />

자바에서 객체는 **참조**(주소)를 사용해서 관계를 맺습니다. 회원(Member) 객체는 Member.team 필드로 팀(Team) 객체와 연관관계를 맺습니다. 또한, 회원 객체와 팀 객체는 **단방향 관계**입니다. 단방향 관계는 회원 -> 팀, 팀 -> 회원 둘 중 한쪽만 참조하는 것을 의미합니다. 여기서는 회원 -> 팀으로 회원만 팀을 참조하고, 팀은 회원을 참조하지 않습니다. 

데이터베이스에서 테이블은 **외래 키**를 사용해서 관계를 맺습니다. 회원(Meber) 테이블은 TEAM_ID 외래 키로 팀(Team) 테이블과 연관관계를 맺습니다. 회원 테이블과 팀 테이블은 **양방향 관계**입니다. 양방향 관계는 회원 -> 팀, 팀 -> 회원 양쪽 모두 서로를 참조하는 것을 의미합니다. **두 테이블은 join을 통해 서로를 참조할 수 있습니다.** 

이때 JPA는 **객체의 단방향 연관관계 만으로 테이블의 양방향 연관관계를 매핑**합니다. 



## 1. 연관관계 매핑

[객체 연관관계]의 단방향 연관관계와 [테이블 연관관계]의 외래 키 기반 양방향 연관관계를 매핑해봅시다. 
가장 중요한 것은 Member 객체의 team **참조**를 MEMBER 테이블의 TEAM_ID **외래 키**에 매핑하는 것입니다. 

<img src="https://blog.kakaocdn.net/dn/DDyfm/btrsnmXxj9o/wnsvuylXKRtVfpktb7OINK/img.png" alt="img" style="zoom:65%;" />

위의 그림을 코드로 나타내면 다음과 같습니다:

**멤버 엔티티**

```java
@Getter @Setter
@Entity
public class Member {
    @Id
    @Column(name="MEMBER_ID")
    private String id;
    private String username;
 
    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    private Team team;
 
    public Member() {}
 
    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }
}
```

 

**팀 엔티티**

```java
@Getter @Setter
@Entity
public class Team {
    @Id
    @Column(name="TEAM_ID")
    private String id;
    private String name;
    
    public Team() {}
    
    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
```

 

 가장 중요한 것은 멤버 엔티티 9~11번째 줄의 코드입니다. 

```java
@ManyToOne
@JoinColumn(name="TEAM_ID")
private Team team;
```

@ManyToOne은 이름 그대로 **다대일(N:1) 관계라는 매핑 정보**입니다. 멤버 엔티티 입장에서 팀과 N:1 관계입니다. 연관관계를 매핑할 때 이렇게 **다중성을 나타내는 어노테이션을 필수로 사용해서 JPA에 두 엔티티의 관계를 알려야 합니다.** 

@JoinColumn은 **객체와 외래 키를 매핑**합니다. **team 객체를 TEAM_ID라는 외래 키로 매핑**합니다. JPA가 알아서 team 객체의 id 값을 저장합니다. JoinColumn은 단어에서부터 조인하는 컬럼, 즉 외래 키라는 의미를 나타냅니다. 





## 2. 연관관계 저장

연관관계를 매핑한 엔티티를 어떻게 저장하는지 알아봅시다. 

```java
Team team1 = new Team("team1", "팀1");
em.persist(team1);
 
Member member1 = new Member("member1", "회원1");
member1.setTeam(team1);
em.persist(member1);
 
Member member2 = new Member("member2", "회원2");
member2.setTeam(team1);
em.persist(member2);
```

member1.setTeam(team1)을 해서 **회원 엔티티는 팀 엔티티를 참조**합니다. 이후 em.persist(member1)를 해서 저장하면 JPA는 팀의 식별자(Team.id)를 회원 테이블의 외래 키값으로 입력합니다.

DB에 값이 잘 저장된 것을 확인할 수 있습니다. 

![img](https://blog.kakaocdn.net/dn/N39UT/btrsrnaHjlh/eT3SnndQ5z2kPQLmJAqKs0/img.png)

![img](https://blog.kakaocdn.net/dn/cr7R3Y/btrsrQQVkCA/j8SVgVOqj0QYAFq5TwSXX0/img.png)



주의할 점은, **JPA에서 엔티티를 저장할 때 모든 연관된 엔티티는 영속 상태여야 합니다.**  팀 객체를 먼저 persist하고, 멤버에 set 해야 합니다. 만약 먼저 persist하지 않으면 에러가 납니다. 

> TransientPropertyValueException: object references an unsaved transient instance - save the transient instance before flushing : hellojpa.start.Member.team -> hellojpa.start.Team]

'개체가 저장되지 않은 임시 인스턴스를 참조합니다. 플러시하기 전에 임시 인스턴스를 저장하세요.' 라는 에러 메시지가 뜹니다. **transient instance는 비영속(영속 상태에 들어가지 않은) 객체를 의미**합니다. team 객체가 비영속 객체이기 때문에 에러가 뜬 것입니다. JPA 영속성 관리를 살펴보면 더 이해될 것입니다.



## 3. 조회

연관관계가 있는 엔티티를 조회하는 방법은 크게 2가지입니다. 객체 그래프 탐색, 객체지향 쿼리 사용입니다. 한 개씩 알아봅시다.

1. **객체 그래프 탐색**
   - 객체 그래프 탐색은 **객체 연관관계를 사용한 조회를 의미**합니다.
   - 객체를 DB에서 찾아서 **getTeam으로 연관된 엔티티를 조회**할 수 있습니다. 

```java
Member member = em.find(Member.class, "member1"); 
Team team = member.getTeam();
System.out.println("팀 이름 = " + team.getName()); // 팀 이름 = 팀1
```

 

2. **객체 지향 쿼리 사용**
   - 객체 지향 쿼리인 **JPQL로도 연관관계가 있는 엔티티를 조회**할 수 있습니다.
   - 예를 들어서 **팀1에 소속된 회원을 조회하려면 조인해서 검색**합니다. 

```java
String jpql = "select m from Member m join m.team t where "+ "t.name=:teamName";
List<Member> resultList = em.createQuery(jpql, Member.class)
        		.setParameter("teamName", "팀1")
        		.getResultList();
                            
for (Member member : resultList) {
    System.out.println("[Query] member.username= " + member.getUsername());
    // [Query] member.username= 회원1
    // [Query] member.username= 회원2
}
```



## 4. 수정

다음으로 연관관계를 어떻게 수정하는지 알아봅시다. 

member.setTeam(team2)으로 객체의 참조 대상을 변경하면 DB에 자동으로 반영됩니다.

```java
Team team2 = new Team("team2", "팀2");
em.persist(team2);
 
Member member = em.find(Member.class, "member1");
member.setTeam(team2);
```



member1의 TEAM_ID가 team2로 잘 변경된 것을 확인할 수 있습니다. 

![img](https://blog.kakaocdn.net/dn/Dg3Cz/btrssCd4Zys/TFIuz2jXKlLzbUrOirlCH1/img.png)



## 5. 연관관계 제거

연관관계를 null로 설정하면 됩니다.

```java
Member member = em.find(Member.class, "member1");
member.setTeam(null); // 연관관계 제거
```



![img](https://blog.kakaocdn.net/dn/OmZ9t/btrspJLOvZJ/DLgDpWR1xhZ6F4Bh4MZNnK/img.png)



## 6. 연관된 엔티티 삭제

연관된 엔티티를 삭제하려면 **기존의 연관관계를 먼저 제거**해야 합니다. 그렇지 않으면 외래 키 제약조건으로 인해 오류가 발생합니다. 

예를 들어서 팀1을 삭제하고 싶은데 회원1이 소속되어있다면 에러가 납니다.

> ERROR: Referential integrity constraint violation: "FKL7WSNY760HJY6X19KQNDUASBM: PUBLIC.MEMBER FOREIGN KEY(TEAM_ID) REFERENCES PUBLIC.TEAM(TEAM_ID) ('team1')"; 

 

먼저 연관관계를 제거하고 팀을 삭제해야 합니다. 아래 코드처럼 작성하면 됩니다. 

```java
Team team = em.find(Team.class, "team1");
Member member = em.find(Member.class, "member1");
member.setTeam(null);
em.remove(team);
```





# 양방향 연관관계

두 객체가 서로를 참조할 때, 양방향 연관관계라고 합니다. 두 객체가 서로의 정보를 찾는 코드가 많을 때, 양방향 연관관계를 갖게 설계하면 코드를 간결하게 사용할 수 있습니다.  아래 사진은 멤버 객체도 팀 객체를 참조하고, 팀 객체도 멤버 객체를 참조하는 경우입니다. 

<img src="https://blog.kakaocdn.net/dn/ck2jT8/btrstK36z9L/8urcrkeAKSfuYHdS59Iti1/img.png" alt="img" style="zoom:65%;" />

테이블은 외래 키 하나로 양방향으로 조회합니다. **객체가 단방향 연관관계를 가질 때도, 테이블은 양방향 연관관계**였습니다. 따라서 **객체가 양방향 연관관계를 갖는다고 해서, DB에 추가할 내용은 전혀 없습니다.**

 

## 1. 양방향 연관관계 매핑

엔티티가 양방향 연관 관계를 가질 때, 어떻게 매핑하는지 알아봅시다. 



**회원 엔티티**

```java
@Getter @Setter
@Entity
public class Member {
    @Id
    @Column(name="MEMBER_ID")
    private String id;
    private String username;
 
    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    private Team team;
 
    public Member() {}
 
    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }
}
```

회원 엔티티는 단방향일 때와 동일합니다.

 

**팀 엔티티**

```java
@Getter @Setter
@Entity
public class Team {
    @Id
    @Column(name="TEAM_ID")
    private String id;
    private String name;
 
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
 
    public Team() {}
 
    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
```



9~10번째 줄의 코드가 추가되었습니다:

```java
@OneToMany(mappedBy = "team")
private List<Member> members = new ArrayList<>();
```

팀 입장에서는 여러 명의 회원이 연관될 수 있으므로 팀과 회원은 일대다 관계입니다. 따라서 팀은 멤버 리스트를 갖습니다. @OneToMany는 일대다 관계임을 나타내기 위해서 사용합니다.  **mappedBy 속성은 양방향 매핑일 때 사용하며, 반대쪽 엔티티의 필드 이름을 값으로 주면 됩니다**. Member.team 객체와 매핑되므로 team을 값으로 주었습니다. 

 

이제 팀 객체에서도 멤버 리스트를 조회할 수 있습니다.

```java
public void findMembers(){
    Team team1 = new Team("team1", "팀1");
    em.persist(team1);
 
    Member member1 = new Member("member1", "회원1");
    member1.setTeam(team1);
    em.persist(member1);
 
    Member member2 = new Member("member2", "회원2");
    member2.setTeam(team1);
    em.persist(member2);
 
    em.flush();
    em.clear();
 
    Team team = em.find(Team.class, "team1");
    List<Member> members = team.getMembers();
    for (Member member : members) {
        System.out.println("member.getUsername() = " + member.getUsername());
        // member.getUsername() = 회원1
        // member.getUsername() = 회원2
    }
}
```

 



## 2. 연관관계 주인: 외래 키를 관리하는 참조

@OneToMany(mappedBy = "team")에서, mappedBy는 무슨 역할을 할까요? 질문에 답하기 위해서는 연관관계의 주인이라는 개념을 먼저 알아야 합니다. 연관 관계 주인은 JPA 계의 포인터 어려운 개념이므로, 하나씩 천천히 알아보겠습니다. 

 먼저 객체와 테이블이 연관관계를 맺는 차이를 이해해야 합니다.

<img src="https://blog.kakaocdn.net/dn/GIIJK/btrsswSwtzi/9FwcHsIOneLsKuWRBoMza1/img.png" alt="img" style="zoom:67%;" />

위의 사진을 보면, 객체도 테이블도 양방향으로 연관 관계를 맺는 것 같지만 사실은 그렇지 않습니다. **객체에는 양방향 연관관계라는 것이 없습니다.**  객체는 기본적으로 단방향 연관관계이고, 양방향 연관관계는 단방향 연관관계 2개를 설명하기 위해 사용하는 표현입니다.즉 멤버 -> 팀, 팀 -> 멤버라는 단방향 연관관계가 2개인 것입니다.

그러나 테이블은 PK값만으로 양방향 연관관계를 갖고, 회원 <-> 팀은 양방향 연관관계 1개를 갖습니다. 

 그렇다면 어려움이 있습니다. 멤버 객체의 team필드와, 팀 객체의 members 필드 중 무엇을 참고해서 FK 값을 변경해야 할까요? 두 객체의 정보가 맞지 않으면 DB 데이터에 오류가 생길 수 있습니다. 따라서 양방향 매핑을 할 때는, 객체의 두 관계 중 하나를 연관관계의 주인으로 지정해서 **연관관계의 주인만 외래 키를 관리하고**(등록, 수정) **주인이 아닌 쪽은 읽기만 지원**합니다.

주인에는 mappedBy 속성을 사용하지 않고, 주인이 아직 쪽에만 mappedBy 속성을 사용합니다. 

 그렇다면 멤버와 팀 중 무엇을 주인으로 정해야 할까요? 규칙은 **외래 키가 있는 곳**을 주인으로 정하는 것입니다. 보통 외래 키를 갖는 @ManyToOne이 주인이 됩니다. 외래 키가 있는 곳의 객체에서 값이 변경될 때 DB에 반영합니다.

만약 외래 키가 아닌 곳을 주인으로 정하면, 주인의 필드에 값이 바뀔 때 외래 키가 있는 다른 테이블의 값을 변경해야 하는데 이것은 어색합니다. 외래 키가 있는 곳을 주인으로 정해서, 주인의 필드에 값이 바뀌면 주인과 연결된 테이블의 외래 키 값을 변경합니다.



예를 들어서 멤버와 팀의 관계에서는, **외래 키를 갖는 멤버가 연관관계의 주인**입니다. 

<img src="https://blog.kakaocdn.net/dn/S9uOQ/btrsrVelpty/TgdkUqijuyRyHG3JqkKes0/img.png" alt="img" style="zoom:67%;" />

연관관계의 주인이 아니면 읽기만 가능하기 때문에, 팀의 members에 값을 넣어도 DB에 변경 사항이 반영되지 않습니다. 팀에 연결된 멤버 정보를 바꾸고 싶으면, 연관관계의 주인인 멤버의 team 필드가 변경되어야 DB에 update 쿼리가 전송됩니다. 

 

## 3. 양방향 연관관계의 주의점 

연관관계의 주인에 값을 입력하지 않으면 데이터가 반영되지 않습니다. 이 실수는 양방향 매핑 시 가장 많이 하는 실수입니다. 

코드를 함께 봅시다. 연관관계의 주인이 아닌 Team의 members에만 값을 저장했습니다. 

```java
public void findMembersError(){
    Member member1 = new Member("member1", "회원1");
    em.persist(member1);
 
    Member member2 = new Member("member2", "회원2");
    em.persist(member2);
 
    Team team1 = new Team("team1", "팀1");
    team1.getMembers().add(member1);
    team1.getMembers().add(member2);
    System.out.println("team1 members size = "+team1.getMembers().size()); // team1 members size = 2
    em.persist(team1);
 
    em.flush();
    em.clear();
 
    Team findTeam = em.find(Team.class, "team1");
    System.out.println("findTeam members size = "+findTeam.getMembers().size()); // findTeam members size = 0
}
```

 

객체를 양방향 연관관계로 매핑했으니 팀의 members 값만 지정되어도 멤버의 team 값이 반영되어야 할 것 같지만, 그렇지 않습니다. 팀의 members는 연관관계의 주인이 아니기 때문에 외래 키의 값을 변경할 수 없고, 원하는 대로 동작하지 않습니다. 

![img](https://blog.kakaocdn.net/dn/QBBix/btrsrUfoltC/gKbI6KcqY9rAqVgBi4f1ck/img.png)



양방향 매핑 시에는 연관 관계의 주인에 값을 입력해야 합니다. 연관 관계의 주인에만 값을 저장해도, 양방향으로 매핑됩니다.

아래 코드를 봅시다.

```java
public void findMembers(){
    Team team1 = new Team("team1", "팀1");
    em.persist(team1);
 
    Member member1 = new Member("member1", "회원1");
    member1.setTeam(team1);
    em.persist(member1);
 
    Member member2 = new Member("member2", "회원2");
    member2.setTeam(team1);
    em.persist(member2);
 
    System.out.println("team1 members size = "+team1.getMembers().size()); // team1 members size = 0
 
    em.flush();
    em.clear();
 
    Team findTeam = em.find(Team.class, "team1");
    System.out.println("findTeam members size = "+findTeam.getMembers().size()); // findTeam members size = 2
    System.out.println("team1 members size = "+team1.getMembers().size()); // team1 members size = 0
}
```

Member에만 team을 저장하고 Team에는 members를 저장하지 않았지만 em.find로 팀을 찾으면 멤버 2명이 조회됩니다.  JPA가 mappedBy를 보고 멤버에 select문을 날려 team_id 값으로 멤버 엔티티를 찾아 객체를 만듭니다.

DB의 멤버 테이블에 TEAM_ID도 잘 저장됩니다. 

![img](https://blog.kakaocdn.net/dn/dNuT5w/btrssB7NwRm/63oehLkOOHLPqov3VWSwKk/img.png)



연관관계의 주인에만 값을 저장한 위의 코드도 잘 동작하지만, 순수 객체 상태를 고려해서 항상 양쪽에 값을 설정하는 것이 좋습니다. 위의 코드에서 team1 객체의 멤버 수는 2인데, DB에서 찾아온 findTeam 객체의 멤버 수는 0입니다. 

이런 경우 JPA를 사용하지 않는 순수한 객체 상태에서 심각한 문제가 발생할 수 있기 때문에, 양방향인 경우 연관 관계를 저장할 때 양쪽 객체 모두에 값을 저장하는 것이 바람직합니다.

```java
private static void 양방향(){
 
    Team team1 = new Team("team1", "팀1");
    em.persist(team1);
 
    Member member1 = new Member("member1", "회원1");
    member1.setTeam(team1); // 연관관계의 주인
    team1.getMembers().add(member1); // 연관관계의 주인이 아님. 저장 시 사용되지 않음
    em.persist(member1);
 
    Member member2 = new Member("member2", "회원2");
    member2.setTeam(team1); // 연관관계의 주인
    team1.getMembers().add(member2); // 연관관계의 주인이 아님. 저장 시 사용되지 않음
    em.persist(member2);
 
    System.out.println("team1 members size = "+team1.getMembers().size()); // team1 members size = 2
 
    em.flush();
    em.clear();
 
    Team findTeam = em.find(Team.class, "team1");
    System.out.println("findTeam members size = "+findTeam.getMembers().size()); // findTeam members size = 2
    System.out.println("team1 members size = "+team1.getMembers().size()); // team1 members size = 2
}
```

 

## 4. 연관관계 편의 메소드

따라서 양방향 연관관계에는 양쪽 객체에 값을 입력하기 위해서 두 메소드를 함께 이용하는 경우가 많습니다. 

```java
member1.setTeam(team1); // 연관관계의 주인
team1.getMembers().add(member1); // 연관관계의 주인이 아님. 저장 시 사용되지 않음
```



문제는 실수로 둘 중 하나만 호출하면 양방향이 깨질 수 있습니다. 따라서 두 코드를 하나인 것처럼 사용하는 것이 안전합니다. 

Member 클래스에 메소드를 추가해봅시다. 

```java
@Entity
public class Member {
    public void changeTeam(Team team){
        if(this.team != null){
            this.team.getMembers().remove(this);
        }
        this.team = team;
        team.getMembers().add(this);
    }
}
```

 

기존에 연결된 팀이 있었다면 연관관계를 삭제하고, Member의 team에도, Team의 members에도 값을 반영합니다. 이렇게 양방향 연관관계를 한번에 저장하는 메소드를 연관관계 편의 메소드라고 합니다. 양방향 연관관계를 사용할 때는, 꼭 연관관계 편의 메소드를 사용합시다. 

 

## 5. 무한 루프

양방향 매핑 시에는 무한 루프를 조심해야 합니다. toString()이나 JSON 생성 라이브러리를 사용할 때 주의해야 합니다.

예를 들어서 멤버와 팀 클래스에 모두 ToString 어노테이션을 붙여봅시다.

```java
@Entity
@ToString
public class Member {}
 
@Entity
@ToString
public class Team {}
 
System.out.println(member1);
```

 

멤버를 출력하면 무슨 일이 일어날까요?  멤버를 출력하기 위해서 팀 필드를 출력하려고 하면, 팀 객체를 출력해야 합니다. 그러나 팀 객체에도 toString이 있으면 팀 객체의 멤버 필드를 출력해야 하고, 무한 루프가 발생해 스택오버플로우 에러가 납니다. 따라서 양방향 매핑 시에는 toString과 JSON 생성 라이브러리를 사용하면 안 됩니다.

 

## 6. 양방향 매핑 정리

단방향 매핑만으로도 이미 연관관계 매핑은 완료됩니다.  양방향 매핑은 반대 방향으로 조회(객체 그래프 탐색) 기능이 추가된 것 뿐, 테이블을 바꾸지 않습니다.  처음 설계는 단방향 매핑으로 하고, 개발 시 양방향을 고려합니다.

**JPQL 쿼리를 작성할 때 역방향으로 탐색할 일이 많거나, 반대 방향 조회가 정말 필요할 때만 양방향 매핑을 추가**합시다.