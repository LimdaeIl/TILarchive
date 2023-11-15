# 자바 ORM 표준 JPA 프로그래밍 - 기본편 / 섹션 4 엔티티 매핑



**시작하기 전**

1. 객체와 테이블 매핑: `@Entity, @Table`
2. 필드와 컬럼 매핑: `@Column`
3. 기본키 매핑: `@Id`
4. 연관관계 매핑: `@ManyToOne, @JoinColumn`



---

# 객체와 테이블 매핑



## 1. **`@Entity`**

JPA에서 `@Entity`는 **객체를 데이터베이스 테이블과 매핑하기 위해 사용되는 어노테이션**입니다. 
즉, `@Entity` 어노테이션을 클래스에 붙이면 **해당 클래스의 객체는 데이터베이스의 테이블로 저장되거나 조회**될 수 있습니다.



### 1.1 `@Entity` 기능

1. **객체-관계 매핑** 
   `@Entity` 붙은 클래스는 **데이터베이스의 테이블과 매핑**됩니다. **클래스의 필드는 테이블의 컬럼**으로, **객체의 인스턴스는 테이블의 레코드로 저장**됩니다. 이를 통해 **객체 지향 프로그래밍과 관계형 데이터베이스를 연결할 수 있습니다.**

2. **기본 생성자**
   `@Entity` 붙은 클래스는 **반드시 기본 생성자를 가져야 합니다.** **JPA는 기본 생성자를 사용하여 객체를 생성하고 초기화**합니다.

3. **식별자**
   `@Entity`는 **주로 클래스의 식별자를 지정하는 데 사용**됩니다. `@Id`가 붙은 필드는 **데이터베이스 테이블의 기본 키(primary key)로 사용**됩니다.



### 1.2 `@Entity` 예제

`@Entity`을 사용하기 위해 다음과 같은 단계를 따를 수 있습니다:

1. 클래스에 `@Entity`을 추가합니다.
2. 필요에 따라 `@Table`을 사용하여 테이블의 이름을 지정합니다. **기본적으로 클래스의 이름과 동일한 이름의 테이블이 생성**됩니다.
3. `@Id`을 사용하여 식별자 필드를 지정합니다. **필드의 타입은 데이터베이스 테이블의 기본 키 타입과 일치해야 합니다.**

예를 들어, 다음은 `@Entity`을 사용하여 User 클래스를 매핑하는 예입니다:

```javascript
@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;
    private String name;
    private String email;
    // ...
}
```

위의 예시에서 User 클래스는 "users"라는 이름의 테이블과 매핑됩니다.  `@id` 필드는 기본 키로 사용되며, name과 email 필드는 테이블의 컬럼으로 매핑됩니다. 이렇게 `@Entity`을 사용하여 JPA에서 객체와 데이터베이스의 매핑을 설정할 수 있습니다. 



## 2. `@Table`

JPA에서 `@Table`은 **엔티티 클래스와 매핑되는 데이터베이스 테이블의 세부 정보를 지정하기 위해 사용되는 어노테이션**입니다. 
`@Table`을 사용하여 **테이블의 이름, 스키마, 인덱스 등을 설정할 수 있습니다.**



### 2.1 @Table 속성

`@Table` 어노테이션의 주요 속성은 다음과 같습니다:

1. **name**
   **테이블의 이름을 지정**합니다. 기본적으로 엔티티 클래스의 이름과 동일한 이름의 테이블이 자동으로 생성되지만, **name 속성을 사용하여 다른 이름을 지정**할 수 있습니다.

2. **schema**
   **테이블이 속한 스키마의 이름을 지정**합니다. 스키마는 **데이터베이스 내에서 테이블을 그룹화하는 데 사용되는 개념**입니다. 일부 데이터베이스에서는 스키마가 지원되지 않을 수도 있습니다.

3. **catalog**
   **테이블이 속한 카탈로그의 이름을 지정**합니다. 카탈로그는 **데이터베이스 내에서 스키마를 그룹화하는 데 사용되는 개념**입니다. 일부 데이터베이스에서는 카탈로그가 지원되지 않을 수도 있습니다.

4. **indexes**
   **테이블에 대한 인덱스를 지정**합니다. 인덱스는 **테이블의 데이터를 빠르게 검색하기 위해 사용되는 데이터베이스 구조**입니다. @Index 어노테이션을 사용하여 인덱스를 세부적으로 설정할 수 있습니다.

5. **uniqueConstraints**

   **테이블에 대한 고유 제약 조건(unique constraint)을 설정하는 데 사용**됩니다. 이 속성을 사용하여 **테이블의 여러 열을 결합하여 고유한 값으로 제약 조건을 설정**할 수 있습니다.



### 2.2 @Table 속성 예제

uniqueConstraints 속성은 다음과 같은 형식으로 사용됩니다:

```java
@Table(name = "table_name", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"column1", "column2"}),
    @UniqueConstraint(columnNames = {"column3", "column4"})
})
```

위의 예시에서는 "table_name"이라는 이름의 테이블에 대해 uniqueConstraints 속성을 사용하여 두 개의 고유 제약 조건을 설정하고 있습니다. 첫 번째 고유 제약 조건은 "column1"과 "column2" 두 열을 결합하여 고유한 값으로 설정되며, 두 번째 고유 제약 조건은 "column3"과 "column4" 두 열을 결합하여 고유한 값으로 설정됩니다.

**고유 제약 조건은 테이블에 중복된 값을 허용하지 않도록 하는데 사용**됩니다. 즉, **특정 열 또는 열의 조합이 고유한 값을 가져야 함을 나타냅니다.** 예를 들어, 사용자 테이블에서 이메일 주소는 중복되지 않아야 한다는 제약 조건을 설정할 수 있습니다.

uniqueConstraints 속성을 사용하여 고유 제약 조건을 설정할 때는 **columnNames 속성을 사용하여 고유 제약 조건에 해당하는 열을 지정**해야 합니다. **여러 개의 고유 제약 조건을 설정하려면 배열로 여러 UniqueConstraint 어노테이션을 지정**하면 됩니다.

uniqueConstraints 속성을 사용하여 고유 제약 조건을 설정하면 데이터베이스에서 중복된 값을 허용하지 않도록 할 수 있습니다. 이를 통해 데이터의 일관성과 정합성을 유지할 수 있습니다. 고유 제약 조건은 데이터베이스 스키마 생성 시에도 적용됩니다.

다음은 @Table 어노테이션을 사용하여 User 클래스와 "users"라는 이름의 테이블을 매핑하는 예입니다:

```java
@Entity
@Table(name = "users")
public class User {
    // ...
}
```

위의 예시에서는 @Table 어노테이션의 name 속성을 사용하여 "users"라는 이름의 테이블과 User 클래스를 매핑하고 있습니다.

`@Table` 어노테이션을 사용하여 JPA에서 테이블과 엔티티 클래스를 매핑할 수 있습니다. 추가적으로 @Table 어노테이션은 다양한 옵션을 제공하므로, 필요에 따라 공식 문서나 참고 자료를 참조하시면 도움이 될 것입니다.



---

# 데이터베이스 스키마 자동 생성

데이터베이스 스키마 자동 생성은 **JPA를 사용하여 정의한 엔티티 클래스를 기반으로 데이터베이스의 테이블과 관련된 스키마(테이블, 컬럼, 제약 조건 등)를 자동으로 생성하는 기능**을 말합니다. 이를 통해 개발자는 별도의 SQL 스크립트 작성 없이도 엔티티 클래스를 기반으로 데이터베이스 스키마를 자동으로 생성할 수 있습니다.



## 1. 데이터베이스 스키마 자동 생성 방법

JPA에서 데이터베이스 스키마 자동 생성을 사용하려면, 
주로 **persistence.xml 또는 Java Config와 같은 설정 파일에서 다음과 같은 설정을 추가**해야 합니다:

1. **ddl-auto 속성**
   이 속성은 hibernate.hbm2ddl.auto 속성의 **Hibernate 버전에 따른 단축된 표현**입니다. 예를 들어, Hibernate 5.x 버전 이상에서는 "create-drop", "create-only", "validate", "update" 등의 값을 사용할 수 있습니다.  

2. **hibernate.hbm2ddl.auto 속성**
   **이 속성을 사용하여 데이터베이스 스키마 자동 생성 모드를 설정**합니다. 일반적으로 "create" 또는 "update" 값을 사용합니다.
   - "create": 엔티티 클래스를 기반으로 **데이터베이스 스키마를 처음부터 생성**합니다. **기존의 스키마는 삭제**됩니다.
   - "create-drop": create와 동일하게 동작을 수행하고, **종료시점에 기존의 스키마를 삭제**합니다.
   - "update": 엔티티 클래스를 기반으로 **데이터베이스 스키마를 변경 또는 업데이트**합니다. **기존의 스키마는 유지**됩니다.
   - "vaildate": 엔티티 클래스를 기반으로 **엔티티와 테이블이 정상 매핑되었는지 확인**합니다.
   - "none": 속성을 사용하지 않는 경우에 관례상 작성합니다.

데이터베이스 스키마 자동 생성은 개발 및 테스트 환경에서 유용하게 사용될 수 있습니다. 그러나 **실제 운영 환경에서는 주의가 필요**합니다. 운영 환경에서는 스키마 변경을 신중하게 수행해야 하며, 기존 데이터의 보존과 데이터베이스 관리 정책을 고려해야 합니다. 권장 방법으로 **운영 장비에는 절대 create, create-drop, update 사용하면 안됩니다.**

또한, 데이터베이스 스키마 자동 생성은 JPA 구현체에 따라 지원되는 기능이며, 모든 데이터베이스에서 완벽하게 동작하지는 않을 수 있습니다. 이 경우, 수동으로 데이터베이스 스키마를 생성하고 관리해야 할 수도 있습니다.

따라서 데이터베이스 스키마 자동 생성을 사용할 때에는 신중하게 설정하고, 개발 및 테스트 단계에서 사용하는 것이 좋습니다. 운영 환경에서는 **수동으로 스키마를 관리하는 것이 안전하고 권장되는 방법**입니다. **개발 초기 단계는 create 또는 update**, **테스트 서버는 update 또는 vaildate**, **스테이징과 운영 서버는 vaildate 또는 none 을 사용**합니다. 

매핑 어노테이션은 엔티티 클래스와 데이터베이스 테이블 간의 매핑을 정의하는 데 사용되는 어노테이션입니다. 예를 들어, **`@Entity`, `@Table`, `@Column`, `@JoinColumn` 등이 매핑 어노테이션의 일부**입니다. 이러한 매핑 어노테이션을 사용하여 엔티티 클래스와 데이터베이스 스키마 간의 매핑을 세밀하게 제어할 수 있습니다.



### 1.1 DDL 생성 기능 예제

DDL 생성 기능은 DDL을 자동 생성할 때만 사용되고 JPA 동작에 영향을 주지 않습니다.

```java
// 제약조건 추가: 회원 이름은 필수. 10자 초과 불가능
@column(nullable = false, length = 10)

// 유니크 제약조건 추가
@Table(uniqueConstraints = {
    @UniqueConstraint(name = "NAME_AGE_UNIQUE", columnNames={"NAMES", "AGE"})
})
```



---



# 필드와 컬럼 매핑

**요구사항 추가**

1. 회원을 일반 회원과 관리자로 구분해야 합니다.
2. 회원 가입일과 수정일이 있어야 합니다.
3. 회원을 설명할 수 있는 필드가 있어야 합니다. 이 필드는 길이 제한이 없습니다.

```java
Package hellojpa; 
import javax.persistence.*; 
import java.time.LocalDate; 
import java.time.LocalDateTime; 
import java.util.Date; 

@Entity 
public class Member { 
 	@Id 
 	private Long id; 
    
 	@Column(name = "name") 
 	private String username; 
    
 	private Integer age; 
    
	@Enumerated(EnumType.STRING) 
 	private RoleType roleType; 
    
 	@Temporal(TemporalType.TIMESTAMP) 
 	private Date createdDate; 
    
 	@Temporal(TemporalType.TIMESTAMP) 
	 private Date lastModifiedDate; 
    
 	@Lob 
 	private String description; 
 	//Getter, Setter… 
} 
```



## 1. @Column

`@Column` 어노테이션은 JPA에서 사용되는 어노테이션 중 하나로, 엔티티 클래스의 필드를 데이터베이스 테이블의 컬럼과 매핑할 때 사용됩니다. 이 어노테이션을 사용하여 컬럼의 이름, 길이, NULL 여부 등을 설정할 수 있습니다.



### 1.1 @Column 속성

`@Column` 어노테이션은 다양한 속성을 가지고 있습니다. 주요 속성들은 다음과 같습니다:

- `name`
  **컬럼의 이름을 지정**합니다. 기본적으로는 필드의 이름과 동일한 값으로 매핑됩니다. 
  들어, `@Column(name = "username")`과 같이 사용할 수 있습니다.

- `length`
  **컬럼의 길이를 지정**합니다. 
  **문자열 타입의 필드에 사용**될 수 있으며, 데이터베이스에서 컬럼의 길이를 제한하고 싶을 때 유용합니다.

- `nullable`
  **컬럼이 NULL 값을 허용하는지 여부를 설정**합니다. 기본값은 `true`로 NULL을 허용합니다.
  `false`로 설정하면 NULL 값을 허용하지 않는 컬럼으로 매핑됩니다.

- `unique`
  **컬럼의 값이 고유해야 하는지 여부를 설정**합니다. 기본값은 `false`로 중복된 값이 허용됩니다.
  `true`로 설정하면 고유 제약 조건이 적용된 컬럼으로 매핑됩니다.

- `precision` 및 `scale`
  **숫자 타입의 컬럼에 사용되는 속성**으로, 숫자의 전체 자릿수와 소수 자릿수를 지정합니다.

- `insertable`
  **해당 컬럼이 INSERT 작업에 참여할지 여부를 설정하는 속성**입니다. 기본값은 `true`로, 엔티티를 데이터베이스에 저장할 때 해당 컬럼의 값을 INSERT에 포함시킵니다. `false`로 설정하면 해당 컬럼은 INSERT 작업에서 제외됩니다.

- `updatable`
  **해당 컬럼이 UPDATE 작업에 참여할지 여부를 설정하는 속성**입니다. 기본값은 `true`로, 엔티티의 값을 변경하여 데이터베이스에 업데이트할 때 해당 컬럼의 값을 UPDATE에 포함시킵니다. `false`로 설정하면 해당 컬럼은 UPDATE 작업에서 제외됩니다.

- `columnDefinition`
  **컬럼의 정의를 직접 지정할 수 있는 속성**입니다. 이 속성을 사용하면 데이터베이스에서 사용되는 컬럼의 데이터 타입이나 제약 조건을 직접 설정할 수 있습니다. 예를 들어, `@Column(columnDefinition = "VARCHAR(255) NOT NULL")`와 같이 사용할 수 있습니다.

`insertable` 속성은 해당 컬럼이 INSERT 작업에 참여할지를 결정하며, `updatable` 속성은 해당 컬럼이 UPDATE 작업에 참여할지를 결정합니다. 이를 통해 엔티티의 특정 필드가 특정 작업에서 제외되도록 할 수 있습니다. 예를 들어, **생성일자 필드는 INSERT 작업에만 참여하도록 설정하여 업데이트 시에는 변경되지 않도록 할 수 있습니다.**

`columnDefinition` 속성은 컬럼의 정의를 직접 지정하는 데 사용됩니다. 이를 통해 데이터베이스에서 사용되는 컬럼의 데이터 타입이나 제약 조건을 세밀하게 제어할 수 있습니다. 주로 **특정 데이터베이스의 기능을 활용하거나 데이터 타입을 정확하게 맞추기 위해 사용**됩니다.

`@Column` 어노테이션은 **엔티티 클래스의 필드와 데이터베이스 테이블의 컬럼 간의 매핑을 정의하는 데 사용**됩니다. 이를 통해 데이터베이스의 스키마를 세밀하게 제어할 수 있습니다. 예를 들어, 컬럼의 이름이나 길이, NULL 여부 등을 설정하여 데이터베이스에 맞춤형 스키마를 생성할 수 있습니다.

`@Column` 어노테이션은 JPA의 기본 매핑 어노테이션 중 하나이며, 엔티티 클래스의 필드에 직접 적용됩니다. 이를 통해 필드와 컬럼 간의 매핑을 명시적으로 지정할 수 있습니다.



## 2. @Enumerated

`@Enumerated`은 **열거형(enum) 타입의 필드를 데이터베이스 컬럼과 매핑할 때 사용**됩니다. 
이 어노테이션을 사용하여 열거형 값의 매핑 방식을 설정할 수 있습니다.



### 2.1 @Enumerated 속성

- `value`
  **열거형 값의 매핑 방식을 설정**합니다. 기본값은 `EnumType.ORDINAL`로, 열거형의 순서에 따라 정수 값으로 매핑됩니다. 예를 들어, 첫 번째 열거형 상수는 0으로 매핑되고, 두 번째 열거형 상수는 1로 매핑됩니다. **단, `EnumType.ORDINAL` 을 사용하면 안됩니다.** 
- `EnumType.STRING`
  **이 값으로 설정하면 열거형 상수의 이름으로 매핑**됩니다. 데이터베이스 컬럼에는 열거형 상수의 이름이 저장됩니다.



### 2.2 @Enumerated 예제

예를 들어, 다음과 같이 `@Enumerated` 어노테이션을 사용하여 열거형 필드를 매핑할 수 있습니다:

```java
public enum Role {
    ADMIN,
    USER,
    GUEST
}

@Entity
public class User {
    // ...
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
    // ...
}
```

위의 예시에서 `Role` 열거형 필드는 `@Enumerated(EnumType.STRING)` 어노테이션을 사용하여 문자열로 매핑되도록 설정되었습니다. 이 경우, 데이터베이스 컬럼에는 열거형 상수의 이름인 "ADMIN", "USER", "GUEST" 중 하나가 저장됩니다.

`@Enumerated` 어노테이션을 사용하여 열거형 필드를 매핑하면, 엔티티 클래스의 열거형 필드와 데이터베이스 컬럼 간의 매핑을 세밀하게 제어할 수 있습니다. **열거형 상수의 순서를 사용하는 `EnumType.ORDINAL` 매핑 방식보다는 `EnumType.STRING` 매핑 방식을 선호하는 것이 데이터베이스 스키마의 유연성을 높일 수 있습니다.**



## 3. @Temporal

`@Temporal`은 **날짜와 시간 타입의 필드를 데이터베이스 컬럼과 매핑할 때 사용**됩니다. 
필드의 매핑 방식과 데이터베이스에서 사용되는 타입을 설정할 수 있습니다.



### 3.1 @Temporal 속성

- `value`
  **날짜와 시간 타입의 매핑 방식을 설정**합니다. 
  기본값은 `TemporalType.TIMESTAMP`으로, 날짜와 시간을 모두 나타내는 필드에 사용됩니다.

- `TemporalType.DATE`
  **날짜만을 나타내는 필드에 사용**됩니다. 데이터베이스 컬럼에는 날짜 정보만 저장됩니다. 

- `TemporalType.TIME`
  **시간만을 나타내는 필드에 사용**됩니다. 데이터베이스 컬럼에는 시간 정보만 저장됩니다. 

- `TemporalType.TIMESTAMP`
  **날짜와 시간을 모두 나타내는 필드에 사용**됩니다. 데이터베이스 컬럼에는 날짜와 시간 정보가 모두 저장됩니다



### 3.2 @Temporal 예제

예를 들어, 다음과 같이 `@Temporal` 어노테이션을 사용하여 날짜와 시간 필드를 매핑할 수 있습니다:

```java
@Entity
public class Event {
    // ...
    @Temporal(TemporalType.DATE)
    @Column(name = "event_date")
    private Date eventDate;

    @Temporal(TemporalType.TIME)
    @Column(name = "event_time")
    private Date eventTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "event_timestamp")
    private Date eventTimestamp;
    // ...
}
```

위의 예시에서 `eventDate` 필드는 `@Temporal(TemporalType.DATE)` 어노테이션을 사용하여 날짜 정보만을 나타내도록 설정되었습니다. 이 경우, 데이터베이스 컬럼에는 날짜 정보가 저장됩니다. 마찬가지로 `eventTime` 필드는 `@Temporal(TemporalType.TIME)` 어노테이션을 사용하여 시간 정보만을 나타내도록 설정되었고, `eventTimestamp` 필드는 `@Temporal(TemporalType.TIMESTAMP)` 어노테이션을 사용하여 날짜와 시간 정보를 모두 나타내도록 설정되었습니다.

`@Temporal` 어노테이션을 사용하여 날짜와 시간 필드를 매핑하면, 엔티티 클래스의 필드와 데이터베이스 컬럼 간의 매핑을 세밀하게 제어할 수 있습니다. 이를 통해 날짜와 시간 타입의 데이터를 데이터베이스에 적절하게 저장하고 조회할 수 있습니다.



## 4. @ Lob

@Lob은 **Large Object를 매핑하기 위해 사용**됩니다. 엔티티 클래스의 필드에 적용되며, **해당 필드가 대용량 데이터를 저장하는 컬럼에 매핑되도록 지정**합니다. @Lob 어노테이션은 **주로 문자열이나 바이너리 데이터와 같은 대용량의 데이터를 매핑할 때 사용**됩니다. 이를 통해 엔티티 클래스의 필드를 사용하여 대용량 데이터를 효율적으로 관리할 수 있습니다.



### 4.1 @Lob 기능

@Lob을 사용하여 매핑된 필드는 일반적으로 **BLOB(Binary Large Object) 또는 CLOB(Character Large Object) 데이터 타입과 매핑**됩니다. **BLOB는 이진 데이터를 저장**하는데 사용되며, **CLOB는 문자열 데이터를 저장**하는데 사용됩니다. @Lob에 별도로 지정하는 속성은 없습니다.



### 4.2 @Lob 예제

예를 들어, 다음과 같이 @Lob 어노테이션을 사용하여 바이트 배열로 대용량 이미지 데이터를 매핑할 수 있습니다:

```java
@Entity
public class Product {
    @Id
    private Long id;
    
    private String name;
    
    @Lob
    private byte[] image;
    
    // ...
}
```

위의 예시에서는 Product 엔티티 클래스의 image 필드에 @Lob 어노테이션을 적용하여, 대용량 이미지 데이터를 저장할 수 있도록 설정하였습니다. 이렇게 @Lob 어노테이션을 사용하면 JPA는 해당 필드를 대용량 데이터로 처리하고, 데이터베이스에 적절한 형식으로 매핑하여 저장하게 됩니다.



## 5. @Transient

@Transient 은 **엔티티 클래스의 필드를 데이터베이스와 매핑하지 않고, 영속성 컨텍스트에만 존재하도록 지정하는 용도로 사용**됩니다. 즉, **해당 필드는 데이터베이스 테이블의 컬럼으로 생성되지 않습니다.**



### 5.1 @Transient 기능

@Transient가 적용된 필드는 JPA가 엔티티를 영속화(Persistence)할 때 무시되며, 데이터베이스에 저장되지 않습니다. 
**특정 필드를 일시적으로 제외하고자 할 때 유용합니다.**

일반적으로 @Transient 어노테이션은 다음과 같은 상황에서 사용됩니다:

- **특정 필드를 데이터베이스에 저장하지 않고, 메모리에서만 사용하고자 할 때**
- **계산된 필드를 표현하고자 할 때**
- **특정 필드를 직렬화(Serialization)에서 제외하고자 할 때**



### 5.2 @Transient 예제

예를 들어, 다음과 같이 @Transient 어노테이션을 사용하여 계산된 필드를 나타낼 수 있습니다:

```java
@Entity
public class Product {
    @Id
    private Long id;
    
    private String name;
    
    private double price;
    
    @Transient
    private double discountedPrice;
    
    // ...
    
    public double getDiscountedPrice() {
        // 할인 가격 계산 로직
        return price * 0.9;
    }
}
```

위의 예시에서는 Product 엔티티 클래스의 discountedPrice 필드에 @Transient 어노테이션을 적용하여, **할인된 가격을 계산하는 필드를 나타냈습니다. 이 필드는 데이터베이스에 저장되지 않고, 메모리에서만 사용됩니다.** 

따라서 @Transient 어노테이션을 사용하여 엔티티 클래스의 특정 필드를 데이터베이스와 매핑에서 제외할 수 있습니다.



---

# 기본키 매핑

기본키 매핑은 JPA에서 **엔티티 클래스의 필드를 데이터베이스의 기본키(primary key)와 매핑하는 것을 의미**합니다. 
기본키는 엔티티를 고유하게 식별하기 위해 사용되는 값으로, 주로 데이터베이스 테이블에서 기본키 컬럼으로 정의됩니다.



## 1. 기본키 매핑 방법

JPA에서 기본키 매핑을 하기 위해 다음과 같이 `@Id, @GeneratedValue` 어노테이션을 사용합니다.



### 1.1 `@Id`

- @Id 어노테이션을 **기본키로 사용할 필드 또는 게터 메서드에 적용**합니다.
- 해당 필드는 **엔티티와 데이터베이스의 기본키 컬럼과 매핑**됩니다.



### 1.2 `@GeneratedValue` 

- @GeneratedValue 어노테이션은 **기본키 값을 자동으로 생성하는 방법을 지정할 때 사용**됩니다.
- **주로 자동 증가하는 기본키 값을 생성하기 위해 사용**됩니다.
- 주로 **@Id 어노테이션과 함께 사용**됩니다.



### 1.3 @GeneratedValue 속성

1. **strategy**
   - strategy 속성은 **기본키 값을 자동 생성하는 전략을 설정**합니다.
   - 주요 전략으로는 **IDENTITY, SEQUENCE, TABLE**이 있습니다.
   - 기본값은 **AUTO로, 데이터베이스에 따라 자동으로 전략이 선택**됩니다.



### 1.4 **IDENTITY** 전략

- IDENTITY 전략은 **데이터베이스의 자동 증가(auto-increment) 기능을 사용하여 기본키 값을 생성하는 전략**입니다.
- 주로 **MySQL**, SQL Server, PostgreSQL 등에서 사용됩니다. 주로  MySQL의 AUTO_ INCREMENT를 사용합니다.
- 데이터베이스가 기본키 값을 자동으로 생성하므로, 애플리케이션에서는 **기본키 값을 할당하지 않아도 됩니다.**

```java
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    // ...
}
```



### 1.5 **SEQUENCE **전략

- SEQUENCE 전략은 **데이터베이스의 시퀀스(sequence) 객체를 사용하여 기본키 값을 생성하는 전략**입니다.
- 시퀀스는 **일련번호를 생성하는 객체로, 데이터베이스에서 독립적으로 관리**됩니다.
- 주로 **Oracle**, PostgreSQL, DB2 등에서 사용됩니다.
- 데이터베이스 시퀀스를 사용하여 기본키 값을 생성하므로, 애플리케이션에서는 시퀀스의 다음 값을 조회하여 할당합니다.



**@SequenceGenerator 속성**

1. **name**
   - name 속성은 **시퀀스 생성기(sequence generator)의 이름을 지정**합니다.
   - 시퀀스 생성기는 **데이터베이스에서 시퀀스를 생성하고 관리하는 데 사용**됩니다.
2. **sequenceName**
   - sequenceName 속성은 **데이터베이스에서 사용할 시퀀스의 이름을 지정**합니다.
   - 주로 데이터베이스에서 **미리 생성한 시퀀스의 이름을 지정**합니다.
3. **initialValue**
   - initialValue 속성은 **시퀀스의 초기값을 설정**합니다. 기본값은 1입니다.
4. **allocationSize**
   - allocationSize 속성은 **시퀀스에서 미리 할당할 값의 개수를 설정**합니다. 기본값은 50입니다.
   - JPA는 이 값을 사용하여 메모리에서 기본키 값을 생성합니다.



 **@SequenceGenerator 예제**

예를 들어, 다음은 @SequenceGenerator 어노테이션을 사용하여 시퀀스를 정의하고 매핑하는 예시입니다:

```java
@Entity
@SequenceGenerator(name = "seq_generator", sequenceName = "product_seq", initialValue = 1, allocationSize = 1)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
    private Long id;
    
    private String name;
    
    // ...
}
```

위의 예시에서는 @SequenceGenerator 어노테이션을 사용하여 seq_generator라는 이름의 **시퀀스 생성기를 정의**하고, product_seq라는 이름의 **시퀀스와 매핑**했습니다. 그리고 @GeneratedValue 어노테이션의 strategy 속성을 GenerationType.SEQUENCE로 설정하고, **generator 속성을 "seq_generator"로 지정하여 시퀀스 생성기를 사용하여 기본키 값을 자동으로 생성하도록 설정**했습니다.

이와 같이 @SequenceGenerator 어노테이션을 사용하여 SEQUENCE 전략을 사용할 때 시퀀스를 정의하고 매핑할 수 있습니다. 시퀀스의 이름, 초기값, 할당 크기 등을 적절하게 설정하여 데이터베이스와의 일관성을 유지하고 효율적인 기본키 생성을 수행할 수 있습니다.





### 1.6  **TABLE** 전략

- TABLE 전략은 **별도의 키 생성용 테이블을 사용하여 기본키 값을 생성하는 전략**입니다.
- 키 생성용 테이블은 데이터베이스에 의해 관리되는 특별한 테이블로, **기본키 값을 저장하고 관리**합니다.
- 주로 **모든 데이터베이스에서 사용**할 수 있습니다. 단, 성능이 떨어집니다.
- 키 생성용 테이블을 사용하여 기본키 값을 생성하므로, 애플리케이션에서는 테이블에서 다음 값을 조회하여 할당합니다.



**TABLE 전략 사용 방법**

TABLE 전략을 사용하기 위해 다음과 같은 방법을 사용합니다:

1. **@TableGenerator**
   - @TableGenerator을 사용하여 **키 생성용 테이블을 정의하고 매핑**합니다.
   - 테이블 생성기(table generator)는 **데이터베이스에서 키 값을 생성하고 관리하는 데 사용**됩니다.
2. **@GeneratedValue**
   - @GeneratedValue의 **strategy 속성을 GenerationType.TABLE로 설정하여 TABLE 전략을 사용하도록 지정**합니다.
   - generator 속성에는 **@TableGenerator의 name 속성과 동일한 값을 지정**합니다.



**@TableGenerator 속성**

1. **name**
   - name 속성은 **테이블 생성기(table generator)의 이름을 지정**합니다.
   - 이 이름은 **@GeneratedValue 어노테이션의 generator 속성과 연결**됩니다.
2. **table**
   - table 속성은 **키 생성용 테이블의 이름을 지정**합니다.
   - 이 테이블은 실제로 **데이터베이스에 생성되어 키 값을 관리**합니다.
3. **pkColumnName**
   - pkColumnName 속성은 **테이블에서 기본키 이름을 저장하는 컬럼의 이름을 지정**합니다.
   - 이 컬럼은 키 생성용 테이블에서 기본키 이름을 식별하는 데 사용됩니다.
4. **pkColumnValue**
   - pkColumnValue 속성은 **테이블에서 사용할 기본키 이름의 값(value)을 지정**합니다.
   - 이 값은 키 생성용 테이블에서 실제로 사용될 기본키 이름을 식별하는 데 사용됩니다.
5. **valueColumnName**
   - valueColumnName 속성은 **테이블에서 기본키 값을 저장하는 컬럼의 이름을 지정**합니다.
   - 이 컬럼은 키 생성용 테이블에서 실제로 기본키 값을 저장하는 데 사용됩니다.
6. **initialValue**
   - initialValue 속성은 **테이블 생성기가 생성할 기본키 값의 초기값을 설정**합니다.
7. **allocationSize**
   - allocationSize 속성은 **테이블 생성기가 한 번에 미리 할당할 기본키 값의 개수를 설정**합니다. 기본값은 50입니다.



**TABLE 전략 예제**

예를 들어, 다음은 @TableGenerator 어노테이션과 @GeneratedValue 어노테이션을 사용하여 TABLE 전략을 설정하는 예시입니다:

```java
@Entity
@TableGenerator(name = "table_generator", table = "id_generator", pkColumnName = "id_name", pkColumnValue = "product_id", valueColumnName = "id_value")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "table_generator")
    private Long id;
    
    private String name;
    
    // ...
}
```

위의 예시에서는 @TableGenerator 어노테이션을 사용하여 table_generator라는 이름의 테이블 생성기를 정의하고, id_generator라는 이름의 키 생성용 테이블과 매핑했습니다. 그리고 @GeneratedValue 어노테이션의 strategy 속성을 GenerationType.TABLE로 설정하고, generator 속성을 "table_generator"로 지정하여 TABLE 전략을 사용하여 기본키 값을 자동으로 생성하도록 설정했습니다.

TABLE 전략은 별도의 키 생성용 테이블을 사용하여 기본키 값을 생성하므로, 애플리케이션에서는 테이블에서 다음 값을 조회하여 할당합니다. 이 방법을 사용하면 데이터베이스와의 독립성을 유지하고, 기본키 값의 중복을 방지할 수 있습니다.



### 1.7 권장하는 식별자 전략

- **기본 키 제약 조건**: null 불가능, 유일, **변하면 안된다.**
- 미래까지 이 조건을 만족하는 자연키는 찾기 어렵다. 
- 대리키(대체키)를 사용하자. 
- 예를 들어 주민등록번호도 기본 키로 적절하기 않다. 
- 권장: **Long형 + 대체키 + 키 생성전략 사용**



---

# 실전 예제 - 1. 요구사항 분석과 기본 매핑





## 1. 요구사항 분석 

- 회원은 상품을 주문할 수 있다.  
- 주문 시 여러 종류의 상품을 선택할 수 있다

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231115150103573.png" alt="image-20231115150103573" style="zoom:67%;" />



## 2. 도메인 모델 분석

- **회원과 주문의 관계**
  1. 회원은 여러 번 주문할 수 있다. (일대다) 
- **주문과 상품의 관계**
  1. 주문할 때 **여러 상품을 선택**할 수 있다. 
  2. 반대로 같은 **상품도 여러 번 주문**될 수 있다. 
  3. 주문상품 이라는 모델을 만들어서 **다대다 관계를 일다대, 다대일 관계로 풀어냄**

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231115150342905.png" alt="image-20231115150342905" style="zoom:67%;" />



## 3. 테이블 설계

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231115150427633.png" alt="image-20231115150427633" style="zoom:67%;" />



## 4. 엔티티 설계와 매핑

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231115150444486.png" alt="image-20231115150444486" style="zoom:67%;" />



## 5. 데이터 중심 설계의 문제점

- 현재 방식은 **객체 설계를 테이블 설계에 맞춘 방식**
- 테이블의 외래키를 객체에 그대로 가져온다.
- 객체 그래프 탐색이 불가능하다.
- 참조가 없으므로 UML도 잘못된 설계다.