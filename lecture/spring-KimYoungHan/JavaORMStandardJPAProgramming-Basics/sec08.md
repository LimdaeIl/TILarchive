# 자바 ORM 표준 JPA 프로그래밍 - 기본편 / 섹션 8 프록시와 연관관계 관리



# 1.프록시(요약)

- 프록시는 JPA에서 사용되는 중요한 개념 중 하나입니다. 프록시는 **실제 엔티티 객체 대신에 사용되는 가상 객체**입니다.
- JPA에서는 **지연 로딩(lazy loading)**을 지원하기 위해 프록시를 사용합니다. 
- 엔티티 객체가 프록시로 대체되면, **해당 객체의 실제 데이터는 필요한 순간에만 데이터베이스에서 로드**됩니다. 
- 프록시를 통해 **성능 개선**과 **메모리 절약 효과**를 얻을 수 있습니다.



## 1.1 프록시 특징

프록시는 대상 엔티티 객체와 동일한 인터페이스를 가지고 있으며, 프록시 객체를 사용하는 클라이언트는 실제로는 프록시를 다루는 것을 모르고 일반 엔티티 객체와 마찬가지로 사용할 수 있습니다. 클라이언트가 프록시 객체의 메서드를 호출하면, 프록시는 실제 엔티티 객체로의 요청을 전달하고 결과를 반환합니다.

프록시는 주로 **연관 관계에 사용**되며, 연관된 엔티티를 로딩하는 시점을 제어할 수 있습니다. 예를 들어, A 엔티티와 B 엔티티가 일대다 관계를 가지고 있을 때, A 엔티티를 조회할 때 B 엔티티를 함께 로딩하지 않고, **필요한 시점에 B 엔티티를 로딩할 수 있습니다.**

프록시는 JPA에서 투명하게 동작하며, 개발자가 별도로 프록시를 생성하거나 다룰 필요는 없습니다. JPA가 자동으로 프록시를 생성하여 사용합니다. 다만, 프록시를 다룰 때는 지연 로딩과 관련된 주의사항을 알고 있어야 합니다. 예를 들어, 프록시 객체를 반환하는 메서드를 호출한 후에는 프록시 객체의 초기화가 완료되었는지 확인해야 합니다.

이렇게 프록시는 JPA에서 데이터 접근과 성능 최적화를 위해 사용되는 중요한 개념입니다. 



## 1.2 프록시 주의사항

프록시를 사용하여 지연 로딩을 구현할 때 주의해야 할 몇 가지 사항이 있습니다. 이를테면 다음과 같습니다:

1. **프록시 초기화**
   **프록시 객체를 사용하기 전에 반드시 초기화**해야 합니다. 초기화하지 않은 프록시를 사용하면 `LazyInitializationException`과 같은 예외가 발생할 수 있습니다. **프록시를 초기화하려면 해당 프록시 객체의 메서드를 호출**하면 됩니다.

2. **프록시 범위**
   프록시 객체는 **영속성 컨텍스트의 범위 내에서만 유효**합니다. 영속성 컨텍스트를 벗어나면 프록시 객체를 사용할 수 없습니다. 
   따라서 프록시를 사용하는 동안에는 영속성 컨텍스트가 유지되도록 주의해야 합니다.

3. **직렬화**
   프록시 객체는 **직렬화가 가능한지 확인**해야 합니다. 만약 프록시 객체를 직렬화해야 하는 상황이라면, 프록시 객체의 상태를 직렬화할 수 있도록 해야 합니다. 그렇지 않으면 `NotSerializableException`과 같은 예외가 발생할 수 있습니다.

4. **프록시와 상속**
   프록시 객체는 **클래스를 상속받은 객체에 대해서는 지원되지 않을 수 있습니다.** 프록시 객체는 주로 인터페이스를 기반으로 생성되기 때문에, 클래스에 직접 상속 받은 필드나 메서드에 대해서는 프록시 동작이 제대로 이루어지지 않을 수 있습니다.

5. **프록시와 equals/hashCode**
   프록시 객체는 equals()와 hashCode() 메서드를 재정의하지 않으면, 프록시 객체 간의 동등성 비교가 원하는 대로 이루어지지 않을 수 있습니다. 따라서 **프록시 객체의 equals()와 hashCode() 메서드를 적절히 구현**해야 합니다.

이러한 주의사항을 고려하여 프록시를 사용하면, 지연 로딩을 효과적으로 구현할 수 있습니다. 프록시를 올바르게 이해하고 사용하는 것은 JPA를 효과적으로 활용하는 데 중요한 요소입니다.



## 1.3 프록시 예제 1

프록시를 사용하기 위해서는 JPA에서 자동으로 생성되도록 설정해야 합니다. 이를 위해 JPA에서는 `FetchType.LAZY` 옵션을 사용하여 프록시를 활성화할 수 있습니다. 프록시 활성화는 fetch 속성 이외에 다른 방법들도 있지만, fetch 속성을 사용하여 프록시를 설정하는 것이 가장 일반적인 방법입니다.

예를 들어, `@ManyToOne` 또는 `@OneToOne` 등의 연관 관계에 `fetch = FetchType.LAZY` 옵션을 설정하면 해당 엔티티의 연관된 엔티티는 프록시로 처리됩니다.

아래는 예제 코드입니다.

```java
@Entity
public class Book {
    @Id
    private Long id;
    
    private String title;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;
    
    // 생성자, getter, setter 등 생략
}

@Entity
public class Author {
    @Id
    private Long id;
    
    private String name;
    
    // 생성자, getter, setter 등 생략
}
```

위의 예제에서 `Book` 엔티티는 `Author` 엔티티와 다대일 관계를 가지고 있습니다. `Book` 엔티티에서 `author` 필드는 프록시로 설정되어 있습니다. 이를 통해 `author` 필드에 접근할 때 실제로 데이터베이스에서 데이터를 로드할 수 있습니다.

```java
EntityManager em = // EntityManager를 얻어온다고 가정합니다.

// Book 엔티티 조회
Book book = em.find(Book.class, 1L);

// 프록시를 통한 접근
Author author = book.getAuthor(); // author는 프록시 객체입니다.

// 프록시 초기화
author.getName(); // 프록시를 통해 실제 데이터를 로드합니다.

System.out.println(author.getName()); // 실제 저자의 이름을 출력합니다.
```

위의 예제에서 `book.getAuthor()`를 호출하면 `author` 필드는 프록시로 반환됩니다. 이때는 실제로 데이터베이스에서 저자 정보를 로드하지 않습니다. 그러나 `author.getName()`을 호출하면 프록시가 초기화되고, 실제로 데이터베이스에서 저자 정보를 로드하여 저자의 이름을 반환합니다.

이와 같이 프록시는 필요한 순간에 데이터를 로드하여 성능을 최적화하고, 메모리를 절약하는 데 도움을 줍니다. 개발자는 프록시를 사용하는 클라이언트 코드에서는 일반 엔티티 객체와 동일하게 다룰 수 있습니다.





# 2. 프록시 

다음 그림에서 Member를 조회할 때 Team도 함께 조회해야 할까요? 

<img src="https://user-images.githubusercontent.com/44339530/139010071-18dd102f-7185-4233-9b65-ddc6a79574c0.png" alt="image" style="zoom:67%;" />

객체 관점으로 생각할때 Member와 Team은 서로가 참조값을 가지고 있는 것이 특별하게 느껴지지 않습니다. 그러나 데이터베이스 테이블 관점에서는 불필요한 조회가 발생하게 됩니다. 

Member가 Team의 참조값을 가질때 호출 시점에서 데이터베이스 테이블 관점은 어쩔수 없이 팀의 테이블도 함께 조회해야 합니다. PK와 FK로 연결이 되어있는 Member와 Team 관계에서 두 테이블을 함께 읽기 위해서는 조인 쿼리를 사용하면서 Team을 안본다 하더라도 객체적으로 참조값이 있기 때문에 불필요한 SQL 쿼리가 조회를 수행됩니다. 

이처럼 엔티티를 조회할 때 연관된 엔티티들이 항상 사용되는 것은 아닙니다. 연관 관계의 엔티티는 비즈니스 로직에 따라 사용될 때도 있지만 그렇지 않을 때도 있습니다. JPA는 해당 문제를 해결하기 위해 엔티티가 실제 사용될 때까지 데이터베이스 조회를 지연하는 방법을 제공하는데 이것을 지연 로딩(lazy loading)이라 합니다. 그런데 지연 로딩을 사용하려면 실제 엔티티 객체 대상에 데이터베이스 조회를 지연할 수 있는 가짜 객체가 필요한데 이것을 프록시 객체라고 합니다.

하이버네이트는 지연 로딩을 지원하기 위해 프록시를 사용하는 방법과 바이트코드를 수정하는 두 가지 방법을 제공하는데 바이트코드를 수정하는 방법은 설정이 복잡합니다. 하이버네이트 공식 사이트를 참고합니다.

```java
// CASE 1. 회원과 팀 함께 출력 - Member, Team 객체 조회 필요
public void printUserAndTeam(String memberId) {
	Member member = em.find(Member.class, memberId);
	Team team = member.getTeam();
	System.out.println("회원 이름: " + member.getUsername());
	System.out.println("소식팀: " + team.getName()); // team 객체 조회
}

// CASE 2. 회원만 출력 - Member 객체 조회 필요
public void printUser(String memberId) {
	Member member = em.find(Member.class, memberId);
	Team team = member.getTeam();
	System.out.println("회원 이름: " + member.getUsername());
}
```





## 2.1 프록시 기초

EntityManager.find()를 사용하면 영속성 컨텍스트에 엔티티가 없으면 데이터베이스를 조회합니다. 
따라서 find() 메서드를 쓰게 되면 우리는 실제 엔티티 객체를 조회합니다.

```java
Member member = em.find(Member.class, "member1");
```



<img src="https://user-images.githubusercontent.com/44339530/139011017-faaf2da6-fedb-4ff9-9ea3-ace07d9245af.png" alt="image" style="zoom:42%;" />

```java
Member findMember = em.getReference(Member.class, member.getId()); // DB 쿼리 질의 안함
System.out.println("findMember = " + findMember.getClass()); // Hibernate가 강제로 만들어낸 proxy 클래스 (가짜 객체)
System.out.println("findMember.id = " + findMember.getId()); // id는 paramter로 넣은거라 DB에서 안가져와도 알고 있음
System.out.println("findMember.name = " + findMember.getUsername()); // 실제 객체의 멤버변수에 접근할때 DB에 쿼리 질의
```

하이버네이트는 내부의 라이브러리를 써서 프록시라는 가짜 엔티티를 반환합니다. 프록시는 껍데기는 똑같지만 텅텅 비어있습니다. 
내부에는 참조(target)이란게 있는데 실제 엔티티를 가리킵니다.

엔티티를 실제 사용하는 시점까지 데이터베이스 조회를 미루고 싶으면 EntityManager.getReference() 메서드를 사용하면 됩니다.  EntityManager.getReference() 메서드를 호출하면 데이터베이스 접근을 위임한 프록시 객체를 반환합니다.

```java
Member member = em.getReference(Member.class, "member1");
```

프록시 객체는 실제 객체에 대한 참조(target)를 보관합니다. 그리고 프록시 객체의 메서드를 호출하면 프록시 객체는 실제 객체의 메서드를 호출합니다. 이를 프록시 객체 초기화라고 합니다.



## 2.2 프록시 특징

- 프록시 객체는 처음 사용할 때 한 번만 초기화됩니다.
- 프록시 객체를 초기화한다고 프록시 객체가 실제 엔티티로 바뀌는 것은 아닙니다. 
- 프록시 객체가 초기화되면 프록시 객체를 통해서 실제 엔티티에 접근할 수 있다.
- 프록시 객체는 원본 엔티티를 상속받은 객체이므로 타입 체크 시에 주의해서 사용해야 합니다.
- 영속성 컨텍스트에 찾는 엔티티가 이미 있으면 데이터베이스를 조회할 필요가 없습니다.
  `em.getReference()`를 호출해도 프록시가 아닌 실제 엔티티를 반환합니다.
- 초기화는 영속성 컨텍스트의 도움을 받아야 가능합니다. 따라서 영속성 컨텍스트의 도움을 받을 수 없는 준영속 상태의 프록시를 초기화하면 문제가 발생합니다. 하이버네이트는 `org.hibernate.LazyInitializationException` 예외를 발생시킵니다.



## 2.3 프록시 객체의 초기화

```java
Member member = em.getReference(Member.Class, "id1");
member.getName();
```

<img src="https://velog.velcdn.com/images/yssgood/post/d345fd65-5422-4047-bb5b-29f7b865ca6c/image.png" alt="img" style="zoom:62%;" />

JPA 에서 프록시는 다음과 같은 단계로 수행됩니다.

1. 프록시로 객체 생성, getName() 메서드 호출합니다.
2. 프록시 객체를 호출하면 사실 실제 객체의 메서드를 호출해야 하기때문에 먼저 영속성 컨텍스트에 접근합니다.
3. 영속성 컨텍스트에 존재하지 않음으로 DB 조회 후 영속성 컨텍스트 1차 캐시에 등록합니다.
4. 실제 Entity 생성합니다.
5. 실제 객체가 생성되었기 때문에 프록시 객체의 getName() 메서드는 실제 객체의 getname()이 같이 호출하여 이름 반환합니다.





# 3. 즉시 로딩과 지연 로딩

회원 엔티티를 조회할 때 연관된 팀 엔티티도 함께 데이터베이스에서 조회하는 것이 좋을까요? 아니면 회원 엔티티만 조회해 두고 팀 엔티티는 실제 사용하는 시점에 데이터베이스에서 조회하는 것이 좋을까요? 정답은 없습니다. 상황에 따라 다를 수 있습니다.

**즉시 로딩** 

- 엔티티를 조회할 때 연관된 엔티티도 함께 조회합니다.
- `@ManyToOne(fetch = FetchType.EAGER)`



**지연 로딩**

- 연관된 엔티티를 실제 사용할 때 조회합니다.
- `@ManyToOne(getch = FetchType.LAZY)`



## 3.1 즉시 로딩

즉시 로딩을 최적화하기 위해 가능하면 조인 쿼리를 사용합니다. 
여기서는 회원과 팀을 조인해서 쿼리 한번으로 두 엔티티를 모두 조회합니다.

```sql
SELECT 
	M.MEMBER_ID AS MEMBER_ID,
	M.TEAM_ID AS TEAM_ID,
	M.USERNAME AS USERNAME,
	T.TEAM_ID AS TEAM_ID,
	T.NAME AS NAME
FROM MEMBER M  
LEFT OUTER JOIN TEAM T 
		ON M.TEAM一ID=T.TEAM一ID
WHERE 
	M.MEMBER_ID='member1'
```



## 3.2 NULL 제약조건과 JPA 조인 전략

- 현재 회원 테이블에 `TEAM_ID` 외래 키는 `NULL` 값을 허용하고 있습니다. 따라서 팀에 소속되지 않은 회원이 있을 가능성이 있습니다. **팀에 소속하지 않은 회원과 팀을 내부 조인 하면 팀은 물론이고 회원 데이터도 조회할 수 없습니다.** 하지만 외부 조인보다 내부 조인이 성능과 최적화에서 더 유리합니다.

**내부 조인을 사용하려면 어떻게 해야 할까요?**

외래 키에 `NOT NULL` 제약 조건을 설정하면 값이 있는 것을 보장합니다. `NOT NULL`을 표현하는 방법은 두 가지가 있습니다.

```java
@JoinColumn(name = "TEAM_ID", nullable = false)
@ManyToOne(fetch = FetchType.EAGER, optional = false)
```

정리하자면 JPA는 선택적 관계면 외부 조인을 사용하고 필수 관계면 내부 조인을 사용합니다.



## 3.3 지연 로딩

조회 대상이 영속성 컨텍스트에 이미 있으면 프록시 객체를 사용할 이유가 없습니다. 따라서 프록시가 아닌 실제 객체를 사용합니다. 
예를 들어 team1 엔티티가 영속성 컨텍스트에 이미 로딩되어 있으면 프록시가 아닌 실제 team1 엔티티를 사용합니다.

하이버네이트는 엔티티를 영속 상태로 만들 때 엔티티에 컬렉션이 있으면 컬렉션을 추적하고 관리할 목적으로 원본 컬렉션을 하이버네이트가 제공하는 내장 컬렉션으로 변경하는데 이것을 컬렉션 래퍼라고 합니다.

```java
@Entity
public class Member {
	@Id
	private String id;
    
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Order> orders;
...
}
```



## 3.4 JPA 기본 페치 전략

- `@ManyToOne, @OneToOne: 즉시 로딩(FetchType.EAGER)`
- ``@OneToMany, @ManyToMany: 지연 로딩(FetchType.LAZY)`

JPA의 기본 페치 전략은 연관된 엔티티가 하나면 즉시 로딩을, 컬렉션이면 지연 로딩을 사용합니다. 
**하지만 필자가 추천하는 방법은 모든 연관관계에 지연 로딩 사용입니다.**



**컬렉션에 FetchType.EAGER 사용 시 주의점**

**컬렉션을 하나 이상 즉시 로딩하는 것은 권장하지 않습니다.** 예를 들어 A 테이블을 N, M 두 테이블과 일대다 조인하면 SQL 실행 결과가 N 곱하기 M이 되면서 너무 많은 데이터를 반환할 수 있고 결과적으로 애플리케이션 성능이 저하될 수 있습니다. 따라서 2개 이상의 컬렉션을 즉시 로딩으로 설정하는 것은 권장하지 않습니다.

컬렉션 즉시 로딩은 항상 외부 조인을 사용합니다. 데이터베이스 제약조건으로 내부 조인으로 인해 검색이 되지 않는 상황을 막을 수는 없습니다. 따라서 JPA는 일대다 관계를 즉시 로딩할 때 항상 외부 조인을 사용합니다.



**FetchType.EAGER 설정과 조인 전략**

`@ManyToOne, @OneToOne`

- (optional = false) : 내부 조인
- (optional = true) : 외부 조인

`@OneToMany, @ManyToMany`

- (optional = false) : 외부 조인
- (optional = true) : 외부 조인



실제로는 Lazy 전략이 필수로 사용됩니다. Eager 전략은 선택적으로 사용하는 것을 강의에서는 더 강조했는데, 이건 나중에 JPQL의 fetch 조인을 활용해서 Eager 전략과 같은 효과를 낼 수 있다고 합니다.

마지막에 즉시 로딩은 상상하지 못한 쿼리가 나간다고 강조했는데 이 부분은 N+1 문제를 얘기하는 것입니다. N+1 문제는 즉시 로딩 전략을 사용하면서 JPQL 을 사용하게 되면 원래는 한번만 날라가야 하는 SQL 쿼리문이 즉시 로딩으로 인해 N 번이 더 처리된다 해서 N+1 문제라고 합니다.

원래는 이해하지 못했지만 강의를 여러번 들으면서 이해가 됐다. 추가적으로, X to One 관계 같은경우 기본이 즉시 로딩이기 때문에 신경써서 Lazy로 변경해야 합니다.

N+1은 10 분 테코톡에도 커버한 내용이 있습니다. https://www.youtube.com/watch?v=ni92wUkAmQI



## 4. 영속성 전이: CASCADE

영속성 컨텍스트에 등록을 하기 위해서는 persist() 함수를 호출 해줘야 하는데 일일이 다 persist()를 호출하기에는 번거로울 때가 있습니다. 연관된 엔티티도 함께 영속 상태로 만들고 싶을때 CASCADE 를 활성화 시켜주면 됩니다.

<img src="https://velog.velcdn.com/images/yssgood/post/fbc718dd-d177-4cf0-9941-c16d096ed7a6/image.png" alt="img" style="zoom:50%;" />

특정 엔티티를 영속 상태로 만들 때 연관된 엔티티도 함께 영속 상태로 만들고 싶으면 영속성 전이(`transitive persistence`) 기능을 사용하면 됩니다. `JPA`는 `CASCADE` 옵션으로 영속성 전이를 제공합니다. 영속성 전이를 사용하면 부모 엔티티를 저장할 때 자식 엔티티도 함께 저장할 수 있습니다.

```java
private static void saveNoCascade(EntityManager em) {
	// 부모 저장
	Parent parent = new Parent();
	em.persist(parent) ;

	// 1번 자식 저장
	Child child1 = new Child();
	child1.setParent(parent); //자식 -> 부모 연관관계 설정
	parent.getChildren().add(childl) ; //부모 -> 자식
	em.persist(childl);

	// 2번 자식 저장
	Child child2 = new Child();
	child2.setParent(parent); //자식 -> 부모 연관관계 설정
	parent.getChildren().add(child2); //부모 -> 자식
	em.persist(child2);
}
```

**JPA에서 엔티티를 저장할 때 연관된 모든 엔티티는 영속 상태이여야 합니다.** 따라서 예제를 보면 부모 엔티티, 자식 엔티티 각각 영속 상태로 만듭니다. 이럴 때 영속성 전이를 사용하면 부모만 영속 상태로 만들어서 연관된 자식까지 한 번에 영속 상태로 만들 수 있습니다.



### 4.1 영속성 전이: 저장

```java
@Entity
public class Parent {
	...
	@OneToMany(mappedBy = "parent", cascade = CascadeType.PERSIST)
	private List<Child> children = new ArrayList<Child>();

}

private static void saveWithCascade(EntityManager em) {
	Child child1 = new Child();
	Child child2 = new Child();

	Parent parent = new Parent();
	childl.setParent(parent) ; //연관관계 추가
	child2.setParent(parent) ; //연관관계 추가

	parent.getChildren().add(child1);
	parent.getChildren().add(child2);

	//부모저장, 연관된자식들저장
	em.persist(parent);

}
```

영속성 전이는 연관 관계를 매핑하는 것과는 아무 관련이 없습니다. **단지 엔티티를 영속화할 때 연관된 엔티티도 같이 영속화하는 편리함을 제공할 뿐입니다.**



### 4.2 CASCADE의 종류

```java
public enum CascadeType {
	ALL, //모두 적용
	PERSIST, //영속
	MERGE, //병합
	REMOVE, //삭제
	REFRESH, //REFRESH
	DETACH //DETACH
}
```

참고로 `CascadeType.PERSIST,` `CascadeType.REMOVE`는 `em.persist()`, `em.remove()`를 실행할 때 바로 전이가 발생하지 않고 플러시를 호출할 때 전이가 발생합니다.



## 5. 고아 객체

JPA는 부모 엔티티와 연관관계가 끊어진 자식 엔티티를 자동으로 삭제하는 기능을 제공하는데 이것을 고아 객체 제거라 합니다. 부모 엔티티의 컬렉션에서 자식 엔티티의 참조만 제거하면 자식 엔티티가 자동으로 삭제되도록 작성합니다.

```java
@Entity
public class Parent {
	@Id @GeneratedValue
	private Long id;
 
	@OneToMany(mappedBy = "parent", orphanRemoval = true)
	private List<Child> children = new ArrayList<Child>();
	...
}
```

고아 객체 제거 기능은 영속성 컨텍스트를 플러시할 때 적용되므로 플러시 시점에 DELETE SQL이 실행됩니다.

```java
Parent parent1 = em.find(Parent.class, id);
parent1.getChildren().remove(0); //자식 엔티티를 컬렉션에서 제거
```

참조가 제거된 엔티티는 다른 곳에서 참조하지 않는 고아 객체로 보고 삭제하는 기능입니다. 만약 삭제한 엔티티를 다른 곳에서도 참조한다면 문제가 발생할 수 있습니다. 이런 이유로 `orphanRemovel`은 `@OneToOne`, `@OneToMany`에만 사용할 수 있습니다.

부모를 제거하면 자식도 같이 제거할 수 있다. `CadecadeType.REMOVE`를 설정하면 가능합니다.



### 5.1 영속성 전이 + 고아 객체, 생명주기

`CascadeType.ALL` + `orphanRemoval = true`를 동시에 사용하면 어떻게 될까요?

일반적으로 엔티티는 `EntityManager.persist()`를 통해 영속화되고 `EntityManager.remove()`를 통해 제거됩니다. 이것은 엔티티 스스로 생명주기를 관리한다는 뜻입니다. 그런데 두 옵션을 모두 활성화하면 부모 엔티티를 통해서 자식의 생명주기를 관리할 수 있습니다.

영속성 전이는 DDD의 Aggregate Root개념을 구현할 때 사용하면 편리합니다.