# 자바 ORM 표준 JPA 프로그래밍 - 기본편 / 섹션 9 값 타입



## 1. JPA의 데이터 타입 분류 

- **엔티티 타입**
  - 엔티티 타입은 @Entity로 정의하는 객체로 @Entity를 붙여서 관리하는 클래스입니다.
  - PK값으로 관리가 되기때문에 데이터가 변해도 식별자로 지속적으로 추적이 가능하고 관리도 편리합니다.
  - 예) 회원 엔티티의 키나 나이 값을 변경해도 식별자로 인식 가능합니다.

- **값 타입**
  - int, Integer, String처럼 단순히 값으로 사용하는 자바 기본 타입이나 객체입니다.
  - 식별자가 없고 값만 있으므로 변경시 추적이 불가능합니다.
  - 예) 숫자 100을 200으로 변경하면 완전히 다른 값으로 대체됩니다.



## 2. 값 타입 분류

### 2.1기본값 타입

- JPA(Java Persistence API)에서 기본값 타입은 엔터티가 아닌 **일반적인 자바 데이터 타입**을 말합니다. 
- 기본값 타입은 엔터티의 속성으로 사용되지만, 엔터티가 아니기 때문에 생명주기나 식별자를 가지지 않습니다. 
- 간단히 말해, 기본값 타입은 **단순히 값 자체를 나타내며 엔터티의 일부로만 사용**됩니다.



### 2.2 기본값 타입의 특징

1. **생명주기 관리**
   - 기본값 타입은 엔터티와는 달리 자체적인 생명주기를 갖지 않습니다. 
   - 따라서 엔터티가 영속성 컨텍스트에서 관리하지 않습니다.
   - 기본값 타입은 생명주기를 엔터티에 의존합니다.
   - 예) 회원을 삭제하면 이름, 나이 필드도 함께 삭제됩니다.

2. **식별자가 없음**
   - 기본값 타입은 별도의 식별자를 갖지 않습니다.
   - 따라서 엔터티와 달리 데이터베이스에서 자체적으로 식별되지 않습니다.

3. **값 타입 컬럼 매핑**
   - 기본값 타입은 일반적으로 단일 컬럼에 매핑되며, 값이 필드에 저장됩니다. 
   - 컬럼은 엔터티의 테이블에 속하게 됩니다.
   - 값 타입은 공유하면 안됩니다.
   - 예) 회원 이름 변경시 다른 회원의 이름도 함께 변경되면 안됩니다.

4. **불변성 유지**
   - 기본값 타입의 객체는 불변(immutable)해야 합니다. 즉, 한 번 생성된 값은 변경되지 않아야 합니다. 
   - 이는 값 타입이 엔터티의 변경 추적이나 영속성 컨텍스트의 일관성을 보장하지 않기 때문입니다.

기본값 타입의 예시로는 **자바의 기본 데이터 타입(int, double, boolean 등)**이나 **불변 클래스들(String, LocalDate 등)**이 있습니다. JPA에서는 **`@Embeddable` 어노테이션을 사용하여 엔터티에 포함되는 값 타입을 정의**하고, **`@Embedded` 어노테이션을 사용하여 엔터티의 속성으로 값 타입을 매핑**할 수 있습니다.

- **자바 기본 타입(int, double)**: 항상 값을 복사하기 때문에 절대 공유되지 않습니다.
- **래퍼 클래스(Integer, Long)**: 공유 가능한(참조값을 공유하므로) 객체이지만 변경할 수 있는 방법이 없습니다.
- **String**



### 2.3 기본값 타입 예제 1

```java
int a = 10;
int b = a;

a = 20; // a의 값만 변경될 뿐, b의 값은 변경되지 않는다.
```

```java
Integer a = new Integer(10);
Integer b = a;

// a.setValue(20); 이런 메서드가 있으면 가능할 것이다
```

 

### 2.4 기본값 타입 예제 2

- 기본값 타입의 예시로 `@Embeddable` 어노테이션을 사용하여 **매핑된 복합 값 타입(composite value type)**을 살펴보겠습니다. 
- 복합 값 타입은 **여러 개의 기본값 타입을 묶어서 사용하는 것을 말합니다.**

예를 들어, 주소 정보를 갖는 `Address` 클래스가 있다고 가정해봅시다. 이 클래스는 단순히 주소 정보만을 포함하고 있어서 엔터티가 아닌 값 타입으로 적합합니다.

```java
@Embeddable
public class Address {
    private String street;
    private String city;
    private String zipCode;

    // 생성자, getter, setter 등 필요한 메서드들을 구현
}
```

그리고 이 주소 정보를 가지고 있는 엔터티인 `Person` 클래스에서는 `Address`를 값 타입으로 사용할 수 있습니다.

```java
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Address address;

    // 생성자, getter, setter 등 필요한 메서드들을 구현
}
```

위의 코드에서 `@Embedded` 어노테이션은 **`Person` 엔터티의 `address` 필드가 값 타입인 `Address` 클래스를 포함한다는 것을 나타냅니다.** 이렇게 함으로써 `Person` 엔터티는 주소 정보를 가질 수 있게 되고, 해당 주소 정보는 `Person` 테이블의 컬럼으로 매핑됩니다.

이제 `Person` 엔터티를 저장하면, 해당 엔터티의 주소 정보도 함께 저장되며, 조회할 때는 엔터티를 로드할 때 주소 정보도 함께 로드됩니다. 이렇게 기본값 타입을 사용함으로써 코드를 보다 간결하고 의미론적으로 풍부하게 만들 수 있습니다.





## 3. 임베디드 타입

- 임베디드 타입은 JPA에서 엔터티에 속한, 그러나 **별도의 테이블을 가지지 않고 엔터티의 테이블에 합쳐져 저장되는 타입**입니다.
- 즉, 임베디드 타입은 **여러 기본값 타입이나 다른 임베디드 타입을 담고 있는 복합 타입**입니다. 
- 임베디드 타입을 사용함으로써 코드의 재사용성을 높이고 복잡한 구조를 간소화할 수 있습니다.



### 3.1 임베디드 타입의 특징

1. **복합 타입 지원**
   - 임베디드 타입은 여러 기본 데이터 타입이나 다른 임베디드 타입을 묶어서 복합 타입을 표현할 수 있습니다.
   - 즉, 여러 속성을 단일 객체로 묶어서 사용할 수 있습니다.

2. **코드 재사용성**
   - 임베디드 타입을 정의하면 여러 엔터티에서 해당 타입을 재사용할 수 있습니다. 
   - 코드의 중복을 줄이고 유지보수성을 향상시킬 수 있습니다.

3. **데이터베이스 테이블에 포함**
   - 임베디드 타입은 해당 엔터티의 테이블에 컬럼으로 포함되어 저장됩니다. 
   - 따로 별도의 테이블을 생성하지 않고, 엔터티의 테이블에 결합됩니다.

4. **객체의 라이프사이클에 종속적**
   - 임베디드 타입은 포함하는 엔터티의 라이프사이클에 종속적입니다. 
   - 엔터티가 저장, 수정, 삭제될 때 임베디드 타입도 같이 처리됩니다.

5. **값 타입의 불변성 유지**
   - 임베디드 타입의 객체는 불변(immutable)해야 합니다. 
   - 이는 값 타입이 엔터티의 변경 추적이나 영속성 컨텍스트의 일관성을 보장하기 위함입니다.

6. **@AttributeOverride**
   - 임베디드 타입을 사용하는 경우 동일한 타입이 여러 번 사용될 때, 
     각각의 사용에 대한 매핑 정보를 재정의하기 위해 `@AttributeOverride` 어노테이션을 사용할 수 있습니다.

7. **Null 가능성**
   - 임베디드 타입은 값이 없는 경우(null)가능하며, 
     엔터티의 속성으로 선언되었을 때 해당 엔터티의 값이 없는 경우에는 해당 임베디드 타입도 null이 됩니다.

8. **기본 생성자 필요**
   - 임베디드 타입 클래스에는 JPA에서 요구하는 규칙에 따라 기본 생성자가 반드시 있어야 합니다.



### 3.2 임베디드 타입 예제 1

임베디드 타입을 정의하려면 `@Embeddable` 어노테이션을 사용합니다. 이 어노테이션은 해당 클래스가 임베디드 타입으로 사용될 수 있다는 것을 나타냅니다.  **`@Embeddable` 어노테이션은 값 타입을 정의하는 클래스에 표시**하며, **`@Embedded` 어노테이션은 값 타입을 사용하는 엔터티 클래스의 속성에 표시**합니다.

예를 들어, 주소 정보를 담고 있는 `Address` 임베디드 타입을 정의해보겠습니다.

```java
@Embeddable
public class Address {
    private String street;
    private String city;
    private String zipCode;

    // 생성자, getter, setter 등 필요한 메서드들을 구현
}
```

그리고 이 임베디드 타입을 사용하는 엔터티인 `Person` 클래스에서는 `@Embedded` 어노테이션을 사용하여 임베디드 타입을 필드로 지정합니다.

```java
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Address address;

    // 생성자, getter, setter 등 필요한 메서드들을 구현
}
```

`Person` 엔터티 클래스에서는 `@Embedded` 어노테이션을 사용하여 `Address`를 값 타입으로 사용하고 있습니다. 이는 JPA에게 해당 필드가 값 타입임을 알려줍니다. 이렇게 함으로써 `Person` 엔터티의 테이블에는 `Address`의 필드인 `street`, `city`, `zipCode`가 컬럼으로 매핑되어 저장됩니다.

임베디드 타입을 사용하면 데이터베이스 스키마가 단순해지고, 코드의 가독성이 향상되며, 유지보수가 쉬워집니다. 또한, 임베디드 타입을 재사용하여 여러 엔터티에서 동일한 구조의 정보를 사용할 수 있습니다.



### 3.3 임베디드 타입 예제 2

```java
// 기간
private LocalDateTime startDate;
private LocalDateTime endDate;

// 주소
private String city;
private String street;
private String zipcode;

@Embedded
private Period workPeriod;

@Embedded
private Address HomeAddress;

@Embeddable
public class Period {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    // 기본 생성자 필수
    // Getter, Setter ...
}
```

 

## 4. @AttributeOverride

- `@AttributeOverride`는 Java Persistence API (JPA)에서 사용되는 어노테이션 중 하나입니다. 
- 엔터티 클래스에서 매핑한 **속성(필드 또는 메서드)의 매핑 설정을 재정의할 때 사용**됩니다.



### 4.1  @AttributeOverride 예제 1

기본적으로 JPA는 엔터티 클래스의 필드 또는 메서드 이름을 사용하여 데이터베이스 테이블의 열과 속성을 매핑합니다. 그러나 때로는 데이터베이스 테이블의 열 이름을 변경하거나 다른 속성을 사용해야 할 경우가 있습니다. 이런 경우에 `@AttributeOverride` 어노테이션을 사용할 수 있습니다.

예를 들어, 다음과 같은 엔터티 클래스가 있다고 가정해 봅시다:

```java
@Entity
public class Employee {
    @Id
    private Long id;
    
    private String name;
    
    @Embedded
    private Address address;
    
    // 다른 필드 및 메서드들...
}
```

여기서 `Employee` 엔터티 클래스는 `Address`라는 임베디드 타입을 포함하고 있습니다. 
이제 `Address` 클래스가 다음과 같이 정의되어 있다고 가정해 봅시다:

```java
@Embeddable
public class Address {
    private String street;
    private String city;
    private String zipCode;
    
    // 다른 필드 및 메서드들...
}
```

이때, `Address` 클래스의 각 속성이 데이터베이스 테이블의 열로 매핑됩니다. 
그러나 경우에 따라서는 **각각의 열에 대해 이름을 지정하고 싶을 수 있습니다. 이때 `@AttributeOverride`를 사용할 수 있습니다.**

```java
@Entity
public class Employee {
    @Id
    private Long id;
    
    private String name;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "street", column = @Column(name = "home_street")),
        @AttributeOverride(name = "city", column = @Column(name = "home_city")),
        @AttributeOverride(name = "zipCode", column = @Column(name = "home_zip_code"))
    })
    private Address address;
    
    // 다른 필드 및 메서드들...
}
```

위의 예제에서는 `@AttributeOverrides`를 사용하여 `Address` 클래스의 각 속성에 대한 열 이름을 재정의하고 있습니다. 이렇게 하면 `Employee` 테이블이 생성될 때, `home_street`, `home_city`, `home_zip_code`와 같은 열 이름이 사용됩니다.

 

## 5.값 타입과 불변 객체

 

### 5.1 값 타입 공유 참조

- 임베디드 타입 같은 값 타입을 여러 엔터티에서 공유하면 위험 -> 부작용(Side Effect) 발생
- 값 타입의 실제 인스턴스인 값을 공유하는 것음 위험
- 값(인스턴스)를 복사해서 사용해야 함 -> 새로운 객체에다가 값을 get해온다.



### 5.2 객체 타입의 한계

- 값을 복사해서 사용하면 공유 참조의 부작용을 막을 수 있다.
- 임베디드 타입과 같이 직접 정의한 값 타입은 객체 타입이므로 참조값을 직접 대입하는 것을 막을 방법이 없다. 
  -> 공유 참조를 피할 수 없다.



### 5.3 불변 객체

- 생성 시점 이후 절대 값을 변경할 수 없는 객체
- 값 타입을 불변 객체로 설계하여 부작용을 차단해야 한다.
  - 생성자로만 값을 설정하고 Setter를 만들지 않으면 된다.
- Integer, String은 자바가 제공하는 불변 객체





------

 

## 6. 값 타입의 비교

- 값 타입은 인스턴스가 달라도 그 안에 값이 같으면 같은 것으로 봐야 한다. 
- 값 타입은 `a.equals(b)`를 사용해서 동등성 비교를 해야 한다. 
- 값 타입의 `equals()` 메소드를 적절하게 재정의한다.

- 동일성(identity) 비교: 인스턴스의 참조 값을 비교한다, `== `사용
- 동등성(equivalence) 비교: 인스턴스의 값을 비교한다, `equals` 사용

```typescript
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Address address = (Address) o;
    return Objects.equals(city, address.city) &&
            Objects.equals(street, address.street) &&
            Objects.equals(zipcode, address.zipcode);
}

@Override
public int hashCode() {
    return Objects.hash(city, street, zipcode);
}
```

 



### 6.1 값 타입 컬렉션

<img src="https://blog.kakaocdn.net/dn/bvV5W7/btrXIknYWhv/ixvtMOmLlHAoVuXmAVcDUK/img.png" alt="img" style="zoom:67%;" />

별도의 식별자 Id 값을 두면 값 타입이 아닌 엔티티가 됩니다. 실무에서는 상황에 따라 값 타입 컬렉션 대신에 일대다 관계를 고려합니다. 일대다 관계를 위한 엔티티를 만들고, 여기에 값 타입을 사용합니다. 영속성 전이와 고아 객체 제거를 사용해서 값 타입 컬렉션처럼 사용합니다. (값 타입을 엔티티로 승급합니다.)



### 6.2 @ElementCollection, @CollctionTable

- 값 타입을 하나 이상 저장할 때 사용합니다. 
- 데이터베이스는 컬렉션을 같은 테이블에 저장할 수 없으므로 컬렉션을 저장하기 위한 별도의 테이블이 필요합니다. 
- 값 타입 컬렉션도 지연 로딩 전략을 사용합니다.

Member의 username 필드와 같이 취급되기 때문에 Member 엔티티와 생명주기를 함께 합니다. 
영속성 전이(Cascade) + 고아 객체 제거 기능을 필수로 가집니다.

```java
@Embedded
private Address homeAddress;

@ElementCollection
@CollectionTable(name = "FAVORITE_FOOD", joinColumns =
    @JoinColumn(name = "MEMBER_ID")
)
@Column(name = "FOOD_NAME")
private Set<String> favoriteFoods = new HashSet<>();

@ElementCollection
@CollectionTable(name = "ADDRESS", joinColumns =
    @JoinColumn(name = "MEMBER_ID")
)
private List<Address> addressHistory = new ArrayList<>();
Member member = new Member();
member.setUsername("member1");
member.setHomeAddress(new Address("homeCity", "street", "10000"));

member.getFavoriteFoods().add("치킨");
member.getFavoriteFoods().add("족발");
member.getFavoriteFoods().add("피자");

member.getAddressHistory().add(new Address("old1", "street", "10000"));
member.getAddressHistory().add(new Address("old2", "street", "10000"));

em.persist(member);
```

![img](https://blog.kakaocdn.net/dn/njkOm/btrXM4RUfS4/mZSTZF7FvJFZ5blGRktKn1/img.png)

 

## 7.  제약사항

- 값 타입은 엔티티와 다르게 식별자 개념이 없습니다. 
- 또한 값은 변경하면 추적이 어렵습니다. 
- 값 타입 컬렉션에 변경이 발생하면, 주인 엔티티와 연관된 모든 데이터를 삭제하고, 값 타입 컬렉션에 있는 현재 값을 모두 다시 저장합니다. (값을 수정 및 삭제할 때 현재 MEMBER_ID와 관련된 데이터를 전부 지웁니다.)
- 값 타입 컬렉션을 매핑하는 테이블은 모든 컬럼을 묶어서 기본 키를 구성해야 합니다. (null 입력 X, 중복 저장 X)



### 7.1 값 타입 수정

값을 업데이트 할 때 사이드 이펙트 발생을 주의해서 set을 통해 수정하지 않고, 삭제 후 추가합니다.

```java
findMember.getFavoriteFoods().remove("치킨");
findMember.getFavoriteFoods().add("한식");
```





### 7.2 값 타입 삭제

```java
findMember.getAddressHistory().remove(new Address("old1", "street", "10000"));
findMember.getAddressHistory().add(new Address("newCity1", "street", "10000"));
```

delete 쿼리 한 번, insert 쿼리 한 번이 날아갈 것이라고 예상했으나, insert가 두 번(ord1, newCity1) 발생합니다.

```sql
Hibernate: 
    /* delete collection hellojpa.Member.addressHistory */ delete 
        from
            ADDRESS 
        where
            MEMBER_ID=?
Hibernate: 
    /* insert collection
        row hellojpa.Member.addressHistory */ insert 
        into
            ADDRESS
            (MEMBER_ID, city, street, zipcode) 
        values
            (?, ?, ?, ?)
Hibernate: 
    /* insert collection
        row hellojpa.Member.addressHistory */ insert 
        into
            ADDRESS
            (MEMBER_ID, city, street, zipcode) 
        values
            (?, ?, ?, ?)
```

실무에서는 값 타입 컬렉션 대신에 일대다 관계를 고려해야 합니다.