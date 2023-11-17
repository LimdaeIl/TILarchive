# 자바 ORM 표준 JPA 프로그래밍 - 기본편 / 섹션 7 고급 매핑



# 상속관계 매핑

- 상속 관계 매핑은 JPA(Java Persistence API)에서 **객체 간의 상속 구조를 데이터베이스 테이블에 매핑하는 방법을 제공**합니다. 
- 상속 구조를 가진 객체들을 효과적으로 저장하고 조회할 수 있습니다.



## 1. 상속관계 매핑 종류

JPA에서 상속 관계 매핑은 세 가지 방식으로 구현할 수 있습니다.
단일 테이블 전략, 구현 클래스마다 테이블 전략, 조인 테이블 전략입니다.



### 1.1 단일 테이블 전략

- 단일 테이블 전략(**Single Table Strategy**)은 **상속 구조를 가진 모든 엔티티를 하나의 테이블에 저장**합니다.
- 부모 클래스와 자식 클래스의 속성을 모두 포함한 단일 테이블을 생성하며, 구분 컬럼을 통해 객체의 타입을 식별합니다.
- 장점은 간단하고 조회 성능이 빠르지만, 테이블의 크기가 커질 수 있습니다.
- **@Inheritance(strategy = InheritanceType.SINGLE_TABLE)**



### 1.2 조인 테이블 전략

- 조인 테이블 전략(**Join Table Strategy**)은 상**속 구조를 가진 엔티티를 각각 별도의 테이블로 저장**하고, 
  부모 클래스와 자식 클래스를 조인 테이블을 통해 연결합니다.
- 부모 클래스와 자식 클래스 각각에 대한 테이블과 조인 테이블을 생성하고, 
  자식 클래스의 테이블은 조인 테이블과의 관계를 맺습니다.
- 장점은 데이터베이스의 논리적인 구조를 유지하면서 객체 간의 상속 관계를 매핑할 수 있습니다.
- **@Inheritance(strategy = InheritanceType.JOINED)**



### 1.3 구현 클래스마다 테이블 전략

- 구현 클래스마다 테이블 전략(**Table per Class Strategy**)은 **상속 구조를 가진 엔티티마다 별도의 테이블을 생성**합니다.
- 부모 클래스와 자식 클래스 각각에 대한 테이블을 생성하고, 자식 클래스의 테이블은 부모 클래스의 테이블과의 관계를 맺습니다.
- 장점은 객체 모델과 데이터베이스 모델이 일치하며, 테이블의 크기가 작습니다. 하지만 조회 성능이 저하될 수 있습니다.
- **실무에서 사용하지 않는 전략입니다.** 
- **@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)**

상속관계 매핑의 테이블 전략의 변경은 간단합니다.  엔티티 코드를 작성하고 @Inheritance 어노테이션의 strategy 전략만 변경하면 다른 방법으로 바로 바꿀수 있습니다. JPA의 편리성입니다. 전략 변경될때 코드를 다 다시 작성하는 것이 아니라, 적절하게 어노테이션만 변경하면 됩니다.



## 2. 조인 테이블 전략

- 조인 테이블 전략은 JPA에서 상속 구조를 가진 엔티티를 매핑하는 방법 중 하나입니다. 
-  **부모 클래스와 자식 클래스를 각각 별도의 테이블로 저장**하고, **조인 테이블을 사용하여 부모-자식 관계를 맺는 방식**입니다.



### 2.1 조인 테이블 전략 특징

조인 테이블은 부모 클래스와 자식 클래스 간의 관계를 표현하기 위한 중간 테이블로 사용됩니다. 이 테이블은 **부모 테이블과 자식 테이블의 기본키를 외래키로 참조하여 두 테이블을 연결**합니다. 이렇게 함으로써 부모 클래스와 자식 클래스 간의 관계를 유지하면서 상속 구조를 매핑할 수 있습니다.

조인 테이블 전략의 주요 특징은 다음과 같습니다:

1. **부모 클래스와 자식 클래스 각각에 대한 테이블을 생성합니다.**
   - 부모 클래스와 자식 클래스는 각각 별도의 테이블로 저장됩니다.
   - 부모 클래스의 테이블에는 부모 클래스 자체의 속성들이 저장되고, 자식 클래스의 테이블에는 자식 클래스 자체의 속성들이 저장됩니다.

2. **조인 테이블을 생성하여 부모-자식 관계를 맺습니다.**
   - 조인 테이블은 부모 테이블과 자식 테이블의 기본키를 외래키로 참조합니다.
   - 조인 테이블에는 부모 테이블과 자식 테이블의 기본 키가 저장되며, 이를 통해 어떤 부모가 어떤 자식을 가지는지를 나타냅니다.

3. **부모-자식 관계를 통해 상속 구조를 표현합니다.**
   - 조인 테이블을 통해 부모-자식 관계가 맺어지므로, 객체 간의 상속 구조를 데이터베이스에 매핑할 수 있습니다.
   - 부모 클래스의 데이터를 조회할 때는 조인을 사용하여 조인 테이블과 부모 테이블을 연결하고, 자식 클래스의 데이터를 조회할 때는 조인 테이블과 자식 테이블을 연결하여 가져옵니다.

조인 테이블 전략은 **데이터베이스의 논리적인 구조를 유지하면서 상속 구조를 매핑할 수 있는 장점**이 있습니다. 하지만 **조인을 사용하여 데이터를 조회하기 때문에 성능이 다소 저하**될 수 있습니다. 따라서 데이터베이스의 크기와 성능 요구사항을 고려하여 적절한 매핑 전략을 선택해야 합니다.



### 2.2 DTYPE 

- DTYPE은 JPA에서 조인 테이블 전략을 사용할 때 자주 사용되는 **컬럼 이름**입니다.
- DTYPE은 **"Discriminator Type"의 약자로, 객체의 타입을 구분하기 위해 사용**됩니다.

조인 테이블 전략에서는 부모 클래스와 자식 클래스를 각각 별도의 테이블로 저장하고, 조인 테이블을 사용하여 부모-자식 관계를 맺습니다. 이때, 조인 테이블에는 부모 테이블과 자식 테이블의 기본 키를 외래 키로 참조하는 열들이 있을 것입니다. 그리고 **DTYPE 컬럼은 이 조인 테이블에 추가로 포함되는 컬럼으로, 객체의 타입을 식별하기 위해 사용**됩니다.

DTYPE 컬럼은 일반적으로 **문자열 형태로 정의**되며, 부모 클래스와 자식 클래스의 객체 타입을 구분하기 위해 사용됩니다. 예를 들어, DTYPE 컬럼의 값이 "부모"인 경우 해당 행은 부모 클래스의 객체를 의미하고, "자식"인 경우 해당 행은 자식 클래스의 객체를 의미합니다. 이를 통해 JPA는 조인 테이블을 조회할 때 DTYPE 컬럼을 확인하여 어떤 객체의 타입인지를 식별할 수 있습니다.

DTYPE 컬럼은 JPA에서 자동으로 관리되는 컬럼이며, 일반적으로 사용자가 직접 조작할 필요는 없습니다. JPA는 객체의 타입을 자동으로 DTYPE 컬럼에 저장하고, 조회 시에는 DTYPE 컬럼을 사용하여 객체 타입을 식별합니다.

따라서 DTYPE 컬럼은 조인 테이블 전략에서 객체의 타입을 식별하기 위해 사용되는 중요한 컬럼입니다.



### 2.3 DTYPE 특징

- 조인 테이블 전략을 사용할 때는 JPA의 어노테이션을 활용하여 DTYPE 컬럼을 정의하고 사용할 수 있습니다. 
- 대표적으로 `@Inheritance`과 `@DiscriminatorColumn` 어노테이션을 사용합니다.

1. **`@Inheritance` **

   - **상속 구조를 가진 엔티티 클래스에 `@Inheritance` 어노테이션을 사용하여 상속 전략을 설정**합니다.
   - `@Inheritance` 어노테이션에는 **`strategy` 속성을 통해 상속 전략(조인 테이블 전략)을 설정**할 수 있습니다.

   

2. **`@DiscriminatorColumn` **

   - **부모 클래스에 `@DiscriminatorColumn` 어노테이션을 사용하여 DTYPE 컬럼을 정의**합니다.
   - `@DiscriminatorColumn` 어노테이션에는 **`name` 속성을 통해 DTYPE 컬럼의 이름을 지정**할 수 있습니다.

3.  **`@DiscriminatorValue`** 
   - JPA에서 조인 테이블 전략을 사용할 때 **자식 클래스의 DTYPE 컬럼 값(객체의 타입을 식별하는 값)을 지정하는 데 사용**됩니다.
   - 자식 클래스에 적용되며, 특정 자식 클래스의 DTYPE 컬럼 값에 해당하는 값을 지정합니다. 
   - 이 값을 통해 JPA는 조인 테이블을 조회할 때 해당 DTYPE 컬럼 값과 일치하는 객체 타입의 자식 클래스를 인식하고 매핑합니다.



### 2.4 DTYPE 예제 1

아래는 조인 테이블 전략에서 어노테이션을 사용하는 예시입니다:

```java
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
public class 부모클래스 {
    // 부모 클래스의 속성들
}

@Entity
@DiscriminatorValue("자식")
public class 자식클래스 extends 부모클래스 {
    // 자식 클래스의 속성들
}
```

위 예시에서 `@Inheritance` 어노테이션을 통해 조인 테이블 전략을 설정하고, `@DiscriminatorColumn` 어노테이션을 통해 DTYPE 컬럼의 이름을 "DTYPE"로 지정합니다. 자식 클래스인 `자식클래스`는 별도의 어노테이션을 사용하지 않고, 부모 클래스인 `부모클래스`의 속성들을 상속받습니다.

이렇게 어노테이션을 사용하여 조인 테이블 전략을 정의하면 JPA가 자동으로 DTYPE 컬럼을 생성하고 관리합니다. 따라서 개발자는 별도의 조작 없이 상속 구조를 가진 엔티티를 조인 테이블로 매핑할 수 있습니다.

`@DiscriminatorValue` 어노테이션을 사용하여 `자식클래스`의 DTYPE 컬럼 값을 "자식"으로 지정합니다. 이렇게 설정된 자식 클래스에서는 DTYPE 컬럼 값으로 "자식"이 사용됩니다.

`@DiscriminatorValue` 어노테이션을 사용하면 JPA가 조인 테이블을 조회할 때 DTYPE 컬럼 값과 일치하는 자식 클래스를 식별하여 매핑합니다. 따라서 JPA는 "자식"이라는 DTYPE 컬럼 값과 일치하는 객체를 `자식클래스`로 매핑합니다.

`@DiscriminatorValue` 어노테이션은 자식 클래스마다 개별적으로 설정할 수 있으므로, 상속 구조에 여러 자식 클래스가 있는 경우 각각의 자식 클래스에 대해 다른 DTYPE 컬럼 값을 설정할 수 있습니다. 이를 통해 JPA는 조인 테이블을 조회할 때 정확히 어떤 자식 클래스의 객체인지를 식별하여 매핑할 수 있습니다.





### 2.5 조인 테이블 예제 1

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231117192306661.png" alt="image-20231117192306661" style="zoom:80%;" />

```java
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn // DTYPE: 엔티티 명이 들어간다
public abstract class Item {
    ...
}
```



```java
@Entity
@DiscriminatorValue("M") // DTYPE: 지정하고 싶은 경우
public class Movie extends Item {
    ...
}
```

**조인 테이블의 장점**

- 테이블이 정규화된다.
- 외래 키 참조 무결성 제약조건을 활용할 수 있다.
- 저장 공간을 효율적으로 사용한다.



**조인 테이블의 단점**

- 조회할 때 조인이 많이 사용되므로 성능이 저하될 수 있다.
- 조회 쿼리가 복잡하다.
- 데이터를 등록할 INSERT SQL을 두 번 실행한다.





## 3. 단일 테이블 전략

- 단일 테이블 전략은 JPA에서 상속 구조를 가진 엔티티를 매핑하는 방법 중 하나입니다. 
- 이 전략은 **상속 구조를 가진 모든 엔티티를 하나의 테이블에 저장하는 방식**입니다.



### 3.1 단일 테이블 전략 특징

단일 테이블 전략의 주요 특징은 다음과 같습니다:

1. **하나의 테이블에 모든 엔티티를 저장합니다.**
   - 부모 클래스와 자식 클래스의 속성을 모두 포함한 단일 테이블을 생성합니다.
   - 이 테이블에는 부모 클래스와 자식 클래스의 속성들이 모두 저장되며, 구분 컬럼을 통해 객체의 타입을 식별합니다.

2. **구분 컬럼을 사용하여 객체의 타입을 식별합니다.**
   - 단일 테이블에 저장된 데이터를 조회할 때는 구분 컬럼을 사용하여 객체의 타입을 식별합니다.
   - 구분 컬럼은 부모 클래스와 자식 클래스를 구분하기 위한 열로, 일반적으로는 열거형(Enum) 또는 문자열로 표현됩니다.

3. **조회 성능이 빠르지만 테이블의 크기가 커질 수 있습니다.**
   - 단일 테이블에 모든 엔티티를 저장하기 때문에 조회 성능이 좋습니다.
   - 하지만 상속 구조를 가진 엔티티가 많은 경우에는 테이블의 크기가 커질 수 있어서 관리가 어려워질 수 있습니다.

단일 테이블 전략은 **간단하고 조회 성능이 빠르다는 장점**이 있습니다. 하나의 테이블에 모든 엔티티가 저장되기 때문에 객체 모델과 데이터베이스 모델이 일치하게 됩니다. 하지만 **상속 구조를 가진 엔티티가 많은 경우에는 테이블의 크기가 커질 수 있으므로**, 데이터베이스의 크기와 성능 요구사항을 고려하여 적절한 매핑 전략을 선택해야 합니다.



### 3.2 단일 테이블 전략 예제 1

<img src="https://blog.kakaocdn.net/dn/dl6IUc/btrWUHcmmgT/HL8PzaGKeqEKekL7t9qsEK/img.png" alt="img" style="zoom:67%;" />

```java
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn // SINGLE_TABLE은 DTYPE이 필수이므로 생략 가능
public abstract class Item {
    ...
}
```



<img src="https://blog.kakaocdn.net/dn/LwctJ/btrWZXee2v0/lPWqvDEIZXC4gOAY74nh1k/img.png" alt="img" style="zoom:80%;" />

```sql
create table Item (
    DTYPE varchar(31) not null,
    id bigint not null,
    name varchar(255),
    price integer not null,
    artist varchar(255),
    author varchar(255),
    isbn varchar(255),
    actor varchar(255),
    director varchar(255),
    primary key (id)
)
```





## 4. @MappedSuperclass

- JPA에서 상속 구조를 가진 엔티티 클래스들 사이에서 **공통으로 사용되는 매핑 정보를 정의하는 데 사용**됩니다.



### 4.1 @MappedSuperclass 특징

`@MappedSuperclass` 어노테이션이 적용된 클래스는 **실제로 데이터베이스 테이블과 매핑되지 않습니다.** 대신, 해당 클래스를 상속받는 자식 클래스들이 테이블과 매핑되는 것입니다. 즉, `@MappedSuperclass` 어노테이션은 공통 매핑 정보를 부모 클래스에 정의하고, 자식 클래스들은 이를 상속받아 사용합니다.

`@MappedSuperclass` 어노테이션을 사용하여 정의된 부모 클래스는 다음과 같은 특징을 가집니다:

1. `@MappedSuperclass` 어노테이션은 **추상 클래스에 적용**해야 합니다.
   - `@MappedSuperclass` 어노테이션은 실제로 테이블과 매핑되지 않는 추상 클래스에 적용되어야 합니다. 인스턴스를 생성할 수 없는 추상 클래스로 정의해야 합니다.

2. `@MappedSuperclass` 어노테이션을 통해 **상속되는 필드와 매핑 정보는 자식 클래스에서 재정의할 수 없습니다.**
   - `@MappedSuperclass` 어노테이션을 사용하여 정의된 필드와 매핑 정보는 자식 클래스에서 재정의할 수 없습니다. 
   - 즉, 자식 클래스에서 같은 이름의 필드를 정의하더라도 매핑 정보는 부모 클래스에서 상속받은 것을 사용합니다.

3. `@MappedSuperclass` 어노테이션을 사용하는 클래스는 **공통으로 사용되는 매핑 정보를 포함**해야 합니다.
   - `@MappedSuperclass` 어노테이션을 사용하는 클래스는 자식 클래스에서 공통으로 사용되는 필드나 매핑 정보를 포함해야 합니다. 
   - 그렇지 않으면 `@MappedSuperclass` 어노테이션을 사용하는 의미가 상실될 수 있습니다.

4. `@MappedSuperclass` 어노테이션을 **사용하는 클래스는 테이블과 매핑되지 않습니다.**
   - `@MappedSuperclass` 어노테이션을 사용하여 정의된 클래스는 실제로 테이블과 매핑되지 않습니다.
   - 따라서 해당 클래스에는 `@Entity` 어노테이션이 존재하지 않아야 합니다.

`@MappedSuperclass` 어노테이션을 올바르게 사용하고 이러한 주의사항을 지키면, 공통으로 사용되는 필드와 매핑 정보를 효과적으로 관리할 수 있습니다. 이를 통해 코드의 재사용성을 높이고 중복을 피할 수 있습니다.



### 4.2 @MappedSuperclass 예제 1

아래는 `@MappedSuperclass` 어노테이션을 사용하는 예시입니다:

```java
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 공통으로 사용되는 다른 필드들
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
}

@Entity
public class EntityA extends BaseEntity {
    // EntityA의 필드들
}

@Entity
public class EntityB extends BaseEntity {
    // EntityB의 필드들
}
```

위 예시에서 `BaseEntity` 클래스에 `@MappedSuperclass` 어노테이션이 적용되었습니다. 이 클래스는 실제로 테이블과 매핑되지 않지만, 자식 클래스인 `EntityA`와 `EntityB`에서 공통으로 사용되는 `id` 필드를 상속받을 수 있습니다.

`@MappedSuperclass` 어노테이션을 사용하면 중복된 매핑 정보를 제거하고 상속 관계를 통해 매핑 정보를 공유할 수 있습니다. 이를 통해 코드의 재사용성과 유지보수성을 향상시킬 수 있습니다.



## 5. 실전예제 - 4. 상속관계 매핑



### 5.1 요구사항 추가

- 상품의 종류는 음반, 도서, 영화가 있고 이후 더 확장될 수 있다. 
- 모든 데이터는 등록일과 수정일이 필수다



### 5.2 도메인 모델

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231117194943766.png" alt="image-20231117194943766" style="zoom:80%;" />



### 5.3 도메인 모델 상세

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231117195009994.png" alt="image-20231117195009994" style="zoom:70%;" />



### 5.4 테이블 설계

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231117195037377.png" alt="image-20231117195037377" style="zoom:67%;" />