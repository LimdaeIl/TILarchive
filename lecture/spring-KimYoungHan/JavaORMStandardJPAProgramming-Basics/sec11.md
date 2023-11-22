# 자바 ORM 표준 JPA 프로그래밍 - 기본편 / 섹션 11 객체지향 쿼리 언어2 - 중급 문법



## 1. JPQL 경로 표현식

- JPQL(Java Persistence Query Language)는 객체 지향 데이터베이스를 다루기 위한 쿼리 언어입니다.
- JPQL은 JPA(Java Persistence API)에서 사용됩니다. **경로 표현식은 JPQL에서 객체 그래프를 탐색하는 데 사용되는 표현식**입니다
- 경로 표현식은 객체 그래프의 필드 또는 속성을 나타냅니다.
-  **객체 그래프는 엔터티 간의 관계를 나타내며, 경로 표현식을 사용하여 이러한 관계를 따라갈 수 있습니다.**



### 1.1 경로 표현식 예제 1

다음은 간단한 예제를 통해 경로 표현식을 설명하겠습니다. 가정하고 있는 엔터티 클래스는 다음과 같습니다:

```java
@Entity
public class Department {
    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
    // getter and setter methods
}

@Entity
public class Employee {
    @Id
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    // getter and setter methods
}
```

이 경우, Department 엔터티와 Employee 엔터티 사이에는 일대다(OneToMany) 및 다대일(ManyToOne) 관계가 있습니다.

이제 JPQL을 사용하여 경로 표현식을 사용하는 쿼리를 살펴보겠습니다:

```sqlite
SELECT e FROM Employee e WHERE e.department.name = 'IT'
```

이 쿼리에서 "e.department.name"은 경로 표현식입니다. 여기서:

- "e"는 Employee 엔터티를 나타냅니다.
- "department"는 Employee 엔터티의 department 필드를 나타냅니다.
- "name"은 Department 엔터티의 name 필드를 나타냅니다.

따라서 이 쿼리는 'IT' 부서에 속한 모든 직원을 검색합니다.

경로 표현식은 엔터티 간의 관계를 사용하여 복잡한 쿼리를 작성하는 데 유용하며, 객체 그래프를 따라 탐색하여 필요한 정보를 추출할 수 있습니다.



### 1.2 경로 표현식 사용법

- 경로 표현식에서는 **점 (`.`)을 사용하여 엔터티의 관계를 따라가면서 필드에 접근**합니다. 
- 이를 통해 객체 그래프를 탐색하며 필요한 데이터에 접근할 수 있습니다.

```java
select m.username -> 상태필드
  from Member m
  join m.team t -> 단일 값 연관필드
  join m.orders o -> 컬렉션 값 연관필드
 where t.name = '팀A'
```

- 상태필드 : 단순히 값을 저장하기 위한 필드

- 연관필드 : 연관관계를 위한 필드

  \- 단일 값 연관필드 : @ManyToOne, @OneToOne, 대상이 엔티티(m.team)

  - 컬렉션 값 연관필드 : @OneToMany, @ManyToMany, 대상이 컬렉션(m.orders)


경로 표현식에서 상태 필드, 단일 값 연관 필드, 컬렉션 값 연관 필드는 엔터티의 다양한 종류의 필드에 대한 접근을 나타냅니다. 
각각에 대한 설명은 다음과 같습니다:

1. **상태 필드 (State Field):**

   - **설명:** 상태 필드는  **엔터티의 데이터를 저장하는 데 사용되는 일반적인 필드**입니다. 

   - 즉, **해당 필드가 엔터티의 상태를 나타냅니다.** 

   - 예를 들어, 엔터티의 이름, 나이, 또는 다른 기본 데이터 타입의 필드가 상태 필드에 해당합니다.

   - 상태 필드 예제:

     ```java
     SELECT e FROM Employee e WHERE e.name = 'John'
     ```

2. **단일 값 연관 필드 (Single-Valued Association Field):**

   - 단일 값 연관 필드는 엔터티 간의 단일 관계를 나타내는 필드입니다. 

   - 이 필드는 다른 엔터티를 참조하며, 연결된 엔터티의 상태를 가져올 수 있습니다.

   - 단일 값 연관 필드 예제:

     ```java
     SELECT e FROM Employee e WHERE e.department.name = 'IT'
     ```

     이 경우,

     ```jav
     e.department
     ```

     는 단일 값 연관 필드이며, 이를 통해 Employee 엔터티와 Department 엔터티 간의 관계를 사용하여 부서의 이름에 접근합니다.

3. **컬렉션 값 연관 필드 (Collection-Valued Association Field):**

   - 컬렉션 값 연관 필드는 엔터티 간의 일대다 또는 다대다 관계를 나타내는 필드입니다. 

   - 이 필드를 통해 여러 개의 연결된 엔터티에 접근할 수 있습니다.

   - 컬렉션 값 연관 필드 예제:

     ```sql
     SELECT d FROM Department d WHERE SIZE(d.employees) > 10
     ```

     이 쿼리에서,

     ```java
     d.employees
     ```

     는 컬렉션 값 연관 필드로, Department 엔터티와 Employee 엔터티 간의 일대다 관계를 나타냅니다.

     ```java
     SIZE(d.employees)
     ```

     를 사용하여 부서에 속한 직원의 수를 조건으로 사용하고 있습니다.

이러한 경로 표현식을 사용하면 객체 그래프를 따라가면서 엔터티의 관련된 데이터에 쉽게 접근할 수 있습니다.



### 1.3 상태 경로

상태 경로(State Path)는 **경로 탐색의 끝으로 간주**됩니다. 이는 JPQL이나 Criteria 쿼리에서 상태 경로를 사용한 경로 표현식을 사용할 때, 해당 경로로 **더 이상 탐색을 진행하지 않는다는 의미**입니다. **경로 탐색은 주로 엔터티 간의 관계를 따라가며 필드에 접근하는 것을 의미하는데, 상태 필드는 그 자체가 데이터를 나타내기 때문에 더 이상 탐색이 필요하지 않다고 간주**됩니다.

간단한 예제를 통해 설명하겠습니다. 가정해 봅시다:

```java
@Entity
public class Department {
    @Id
    private Long id;
    private String name;

    // 다른 상태 필드들이 있을 수 있음
    // getter and setter methods
}
```

이 경우 `Department` 엔터티에는 `name`과 같은 상태 필드가 있습니다. 그리고 다음과 같은 JPQL 쿼리가 있다고 가정해 봅시다:

```sql
SELECT d.name FROM Department d WHERE d.name = 'IT'
```

여기서 `d.name`은 상태 필드에 해당합니다. 이 쿼리에서는 `Department` 엔터티의 `name` 필드만 선택되어 반환됩니다. 
상태 필드인 `name`은 이미 값 자체를 나타내기 때문에 이어지는 더 깊은 경로로의 탐색이 필요하지 않습니다.

반면에, 만약 `Department` 엔터티에 다른 엔터티와의 관계가 있다면 경로 탐색을 통해 더 많은 데이터를 가져올 수 있습니다. 
하지만 상태 필드인 `name` 자체는 더 이상의 경로가 필요 없다고 간주됩니다.



### 1.4 단일 값 연관경로

단일 값 연관경로(Single-Valued Association Path)는 엔터티 간의 단일 값 연관을 나타내는 경로를 말합니다. 
이것은 **한 엔터티에서 다른 엔터티로 이동하는 경로를 의미**하며, **이를 통해 관련된 엔터티의 속성에 접근**할 수 있습니다.

간단한 예제를 통해 설명하겠습니다. 다음은 `Department` 엔터티와 `Employee` 엔터티 간의 단일 값 연관이 있는 경우입니다:

```java
@Entity
public class Department {
    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    // getter and setter methods
}

@Entity
public class Employee {
    @Id
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // getter and setter methods
}
```

여기서 `Employee` 엔터티의 `department` 필드는 단일 값 연관이 있는 경로입니다. 이를 통해 `Employee` 엔터티에서 `Department` 엔터티로 이동할 수 있습니다.

JPQL을 사용하여 단일 값 연관경로를 쿼리에 적용할 수 있습니다. 다음은 예제 쿼리입니다:

```sql
SELECT e.department FROM Employee e WHERE e.name = 'John'
```

이 쿼리에서 `e.department`는 단일 값 연관경로이며, `Employee` 엔터티와 `Department` 엔터티 간의 관계를 나타냅니다. 결과적으로 `John`이라는 직원의 부서에 대한 정보를 가져올 수 있습니다.



### 1.5 묵시적 내부 조인

- 묵시적 내부 조인은 JPQL에서 **단일 값 연관경로를 사용할 때 발생하는 개념**입니다. 
- 이것은 JPA가 **엔터티 간의 관계를 사용하여 필요한 조인을 자동으로 생성하는 것을 의미**합니다.

다시 말해, 단일 값 연관경로를 사용하여 엔터티 간의 관계를 따라가면서 필드에 접근할 때, JPA는 이 관계에 대한 내부 조인을 암시적으로 생성합니다.

간단한 예제를 통해 설명하겠습니다. 다음은 `Department` 엔터티와 `Employee` 엔터티 간의 단일 값 연관이 있는 경우입니다:

```java
@Entity
public class Department {
    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    // getter and setter methods
}

@Entity
public class Employee {
    @Id
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // getter and setter methods
}
```

이제 다음과 같은 **JPQL 쿼리**를 생각해보겠습니다:

```sql
SELECT e.department 
FROM Employee e 
WHERE e.name = 'John'
```

`e.department`는 단일 값 연관경로입니다. `Employee` 엔터티의 `department` 필드가 단일 값 연관 필드로 설정되어 있으며, 이를 통해 `Employee` 엔터티와 `Department` 엔터티 간의 관계를 나타냅니다.

그러나 이 쿼리에는 명시적으로 조인을 지정하지 않았으므로, **묵시적 내부 조인이 발생**합니다. JPA는 `Employee` 엔터티의 `department` 필드와 관련된 테이블 간의 조인을 자동으로 생성하여 쿼리를 실행합니다. 이 경우, 묵시적 내부 조인을 통해 `Employee`와 `Department` 테이블 간의 관계를 사용하여 `Department` 엔터티의 정보를 가져옵니다.

즉, 단일 값 연관경로를 사용할 때 JPA는 엔터티 간의 관계를 기반으로 필요한 조인을 자동으로 생성하므로 개발자가 명시적으로 조인을 작성하지 않아도 됩니다.

위의 JPQL 쿼리를 **ANSI SQL 쿼리**로 나타내면 다음과 같습니다:

```sql
SELECT e.department_id
FROM Employee e
JOIN Department d ON e.department_id = d.id
WHERE e.name = 'John';
```

- `e.department_id`는 Employee 테이블에서 부서 ID를 나타냅니다.
- `Department d`는 Department 테이블을 나타냅니다.
- JOIN 절은 Employee 테이블과 Department 테이블 간의 조인을 나타냅니다. 
  여기서 `e.department_id`와 `d.id`를 사용하여 부서 정보에 대한 조인을 수행합니다.
- WHERE 절은 이름이 `'John'`인 직원에 해당하는 조건을 추가합니다.



**경로탐색을 사용한 묵시적 조인시 주의사항**

- 항상 내부 조인을 수행합니다.
- 컬렉션은 경로탐색의 끝, 명시적 조인을 통해 별칭 얻어야 합니다.
- 경로탐색은 주로 SELECT, WHERE 절에서 사용하지만 묵시적 조인으로 인해 SQL의 FROM(JOIN)절에 영향을 줍니다.





### 1.6 명시적 내부 조인

- 명시적 내부 조인(Explicit Inner Join)은 JPQL에서 **개발자가 직접 조인을 지정**하는 것을 말합니다. 
- 이것은 묵시적 내부 조인과는 달리 JPA가 자동으로 조인을 생성하지 않고, 
  개발자가 쿼리에서 직접 필요한 조인을 명시적으로 작성해야 합니다.

간단한 예제를 통해 명시적 내부 조인을 설명하겠습니다. 가정해 보겠습니다:

```java
@Entity
public class Department {
    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    // getter and setter methods
}

@Entity
public class Employee {
    @Id
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // getter and setter methods
}
```

이제 명시적 내부 조인을 사용하여 부서 이름이 'IT'인 직원들을 가져오는 JPQL 쿼리를 생각해보겠습니다:

```sql
SELECT e FROM Employee e
JOIN e.department d
WHERE d.name = 'IT'
```

이 쿼리에서 `JOIN e.department d` 부분이 **명시적 내부 조인**입니다. 여기서 `e.department`는 단일 값 연관경로이며, 개발자가 `JOIN` 키워드를 사용하여 명시적으로 조인하고 있습니다. `JOIN e.department d`는 'Employee' 테이블과 'Department' 테이블 간의 조인을 수행합니다.

명시적 내부 조인을 사용하면 개발자는 쿼리에서 필요한 조인을 직접 지정할 수 있습니다. 이는 성능을 최적화하거나 특정한 조인 전략을 사용할 때 유용합니다. 그러나 묵시적 내부 조인을 사용하는 경우보다 더 많은 쿼리 작성이 필요할 수 있습니다.



위의 JPQL 쿼리를 **ANSI SQL로 변환**하면 다음과 같습니다:

```sql
SELECT e.*
FROM Employee e
JOIN Department d ON e.department_id = d.id
WHERE d.name = 'IT';
```

- `e.*`는 `Employee` 테이블의 모든 열을 나타냅니다.
- `Department d`는 `Department` 테이블을 나타냅니다.
- `JOIN` 절은 `Employee` 테이블과 `Department` 테이블 간의 조인을 나타냅니다. 
  여기서 `e.department_id`와 `d.id`를 사용하여 부서 정보에 대한 조인을 수행합니다.
- `WHERE` 절은 부서 이름이 'IT'인 직원에 해당하는 조건을 추가합니다.



### 1.7 조인 변환

명시적 내부 조인을 사용하여 부서 이름이 'IT'인 직원들을 가져오는 JPQL 쿼리입니다. 
다음 쿼리를 묵시적 내부 조인으로 변경해보겠습니다.

```sql
SELECT e FROM Employee e
JOIN e.department d
WHERE d.name = 'IT'
```



묵시적 내부 조인을 사용하려면 JPQL에서 **엔터티 간의 관계를 직접 활용**하면 됩니다. 
아래는 명시적 내부 조인이 묵시적으로 변경된 JPQL 쿼리입니다:

```sql
SELECT e FROM Employee e
WHERE e.department.name = 'IT';
```

이 쿼리에서 `e.department.name`는 **묵시적 내부 조인**을 나타냅니다. `Employee` 엔터티와 `Department` 엔터티 간의 단일 값 연관 경로를 사용하여 부서 이름에 접근합니다. `JOIN` 키워드를 명시적으로 사용하지 않고도 JPA는 엔터티 간의 관계를 활용하여 내부적으로 조인을 생성합니다.

이렇게 묵시적 내부 조인을 사용하면 명시적으로 조인을 기술하지 않고도 엔터티 간의 관계를 사용하여 필요한 데이터에 접근할 수 있습니다.



### 1.8 EntityManager

JPQL 경로 표현식을 사용하려면 다음과 같이 **EntityManager를 사용하여 쿼리를 작성하고 실행**해야 합니다. 
아래 예제에서는 EntityManager를 사용하여 위에서 제공한 JPQL 쿼리를 실행하는 방법을 보여줍니다:

```java
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EmployeeDAO {
    private EntityManager entityManager;

    public EmployeeDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Employee> getEmployeesInITDepartment() {
        // JPQL 쿼리를 작성
        String jpql = "SELECT e FROM Employee e WHERE e.department.name = 'IT'";

        // Query 객체 생성
        Query query = entityManager.createQuery(jpql);

        // 쿼리 실행 및 결과 반환
        List<Employee> resultList = query.getResultList();

        return resultList;
    }

    public static void main(String[] args) {
        // EntityManager 생성 및 가져오기 (이 부분은 프레임워크 또는 JPA 구현에 따라 다를 수 있음)
        EntityManager entityManager = // EntityManager를 가져오는 코드;

        // EmployeeDAO 생성
        EmployeeDAO employeeDAO = new EmployeeDAO(entityManager);

        // JPQL 쿼리 실행
        List<Employee> employeesInITDepartment = employeeDAO.getEmployeesInITDepartment();

        // 결과 출력 또는 활용
        for (Employee employee : employeesInITDepartment) {
            System.out.println("Employee Name: " + employee.getName());
        }
    }
}
```

위 코드에서 **`entityManager`는 JPA에서 엔터티 관리를 담당하는 핵심 객체**입니다. `createQuery` 메서드를 사용하여 JPQL 쿼리를 실행할 수 있습니다. 그 후 `getResultList` 메서드를 호출하여 쿼리의 결과를 가져옵니다.

실제 애플리케이션에서는 `EntityManager`를 적절히 관리하고, 트랜잭션 내에서 쿼리를 실행하는 등의 추가적인 고려 사항이 있을 수 있습니다. 이는 사용 중인 JPA 구현 및 프레임워크에 따라 달라질 수 있습니다.



**실무조언**

- **가급적 묵시적 조인 대신 명시적 조인 사용합니다.**
- 조인은 SQL튜닝에 중요 포인트입니다.
- 묵시적 조인은 조인이 일어나는 상황을 한눈에 파악하기 어렵습니다.





## 2. 페치 조인

페치 조인(Fetch Join)은 **관계형 데이터베이스에서 데이터를 검색할 때 발생하는 N+1 쿼리 문제를 해결**하기 위한 JPA의 강력한 기능 중 하나입니다. N+1 쿼리 문제는 엔터티와 관련된 컬렉션 데이터를 검색할 때, 추가로 N개의 쿼리가 발생하여 성능을 저하시키는 문제를 말합니다.

페치 조인은 이러한 문제를 해결하기 위해 **연관된 엔터티 또는 컬렉션을 함께 로딩하도록 JPA에게 명시하는 방법**입니다. 이를 통해 한 번의 쿼리로 필요한 모든 데이터를 로딩할 수 있습니다.



### 2.1 페치 조인 특징

페치 조인은 JPA에서 데이터 로딩을 최적화하기 위한 강력한 기능입니다. 
다음은 페치 조인의 주요 특징에 대한 설명입니다:

1. **연관된 엔터티 로딩:**
   - 페치 조인을 사용하면 기본적으로 **연관된 엔터티나 컬렉션을 함께 로딩**할 수 있습니다. 
     이는 N+1 쿼리 문제를 해결하여 성능을 향상시킵니다.
2. **컬렉션 페치 조인:**
   - 페치 조인은 단일 값 연관 경로뿐만 아니라 컬렉션 값 연관 경로에도 적용될 수 있습니다. 
     따라서 부모 엔터티와 연관된 모든 자식 엔터티를 함께 로딩할 수 있습니다.
3. **N+1 문제 해결:**
   - 페치 조인은 연관된 엔터티들을 함께 로딩하기 때문에 N+1 문제를 해결합니다. 
     N+1 문제는 부모 엔터티를 조회할 때마다 관련된 자식 엔터티를 추가로 로딩해야 하는 문제를 나타냅니다.
4. **FETCH 키워드 사용:**
   - 페치 조인은 JPQL에서 `FETCH` 키워드를 사용하여 명시적으로 표현됩니다. 
     **`JOIN FETCH`를 사용하여 연관된 엔터티나 컬렉션을 함께 로딩**합니다.
5. **EAGER 로딩과의 차이:**
   - 페치 조인은 EAGER 로딩과는 다릅니다. EAGER 로딩은 항상 연관된 엔터티를 함께 로딩하지만, 페치 조인은 필요할 때만 적용됩니다. 따라서 페치 조인을 사용하면 로딩 전략을 더 유연하게 제어할 수 있습니다.
6. **내부 조인의 활용:**
   - 페치 조인은 내부 조인을 사용하여 데이터를 로딩합니다. 
     내부 조인은 SQL에서 INNER JOIN과 유사하게 작동하며, 연관된 엔터티 간의 관계를 사용하여 조인을 수행합니다.
7. **조건 필터링:**
   - 페치 조인에서는 연관된 엔터티나 컬렉션을 가져올 때 조건을 추가로 지정할 수 있습니다.
      예를 들어, `WHERE` 절을 통해 특정 조건을 만족하는 데이터만 로딩할 수 있습니다.

페치 조인은 데이터베이스 쿼리의 성능을 향상시키는 유용한 기법이지만, 사용하는 경우에는 주의가 필요합니다. 특히 연관된 엔터티의 수가 많거나, 깊은 계층 구조를 가지는 경우에는 데이터베이스 성능에 부담을 줄 수 있습니다.



### 2.2 페치 조인 예제 1

간단한 예제를 통해 페치 조인을 설명하겠습니다. 가정해 봅시다:

```java
@Entity
public class Department {
    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Employee> employees;

    // getter and setter methods
}

@Entity
public class Employee {
    @Id
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    // getter and setter methods
}
```

위의 코드에서 `Department`와 `Employee`은 일대다 관계를 가집니다. `Department` 엔터티는 여러 개의 직원을 가지고 있고, `Employee` 엔터티는 하나의 부서에 속해 있습니다.

페치 조인을 사용하여 부서와 함께 직원을 로딩하는 JPQL 쿼리는 다음과 같습니다:

```sql
SELECT d FROM Department d JOIN FETCH d.employees WHERE d.name = 'IT'
```

이 쿼리에서 `JOIN FETCH d.employees`는 페치 조인을 나타냅니다. 이를 통해 'IT' 부서에 속한 부서와 직원들이 한 번의 쿼리로 로딩됩니다. 이렇게 하면 N+1 쿼리 문제를 피할 수 있습니다.

페치 조인은 성능 최적화를 위한 강력한 도구이지만, 필요한 경우에만 사용하는 것이 좋습니다. 특히 연관된 엔터티의 데이터가 많거나 깊은 계층 구조를 가진 경우에는 주의하여 사용해야 합니다.



### 2.3 페치 조인 예제 2

페치 조인은 JPQL 쿼리에서 **연관된 엔터티나 컬렉션을 함께 로딩하는 기법**입니다. 
다음은 페치 조인을 작성하는 방법과 주의사항에 대한 설명입니다.

1. **기본 페치 조인:**

   가장 기본적인 페치 조인은 다음과 같이 작성됩니다:

   ```sql
   SELECT e 
   FROM Employee e JOIN FETCH e.department 
   WHERE e.name = 'John'
   ```

   - 이 쿼리에서 `JOIN FETCH e.department` 는 페치 조인을 나타냅니다. 
   - 이렇게 하면 **'John'이라는 직원과 관련된 부서 정보가 함께 로딩**됩니다.

2. **컬렉션 페치 조인:**

   컬렉션 값을 가진 연관 경로에 대한 페치 조인은 다음과 같이 작성됩니다

   ```sql
   SELECT d 
   FROM Department d JOIN FETCH d.employees 
   WHERE d.name = 'IT'
   ```

이 쿼리에서 `JOIN FETCH d.employees` 는 **'IT' 부서와 관련된 모든 직원들을 함께 로딩하는 페치 조인**입니다.

3. **여러 연관 경로 페치 조인:**

   여러 개의 연관 경로를 함께 로딩하려면 다음과 같이 작성할 수 있습니다:

   ```sql
   SELECT e 
   FROM Employee e 
   	JOIN FETCH e.department 
   	JOIN FETCH e.projects 
   WHERE e.name = 'John'
   ```

이 쿼리에서 `JOIN FETCH e.department`와 `JOIN FETCH e.projects` 는 **각각 부서와 프로젝트에 대한 페치 조인**입니다.

4. **조건을 추가한 페치 조인:**

   페치 조인에 조건을 추가하여 로딩할 데이터를 필터링할 수 있습니다:

```sql
SELECT d 
FROM Department d 
	JOIN FETCH d.employees e 
WHERE d.name = 'IT' 
AND e.salary > 50000
```

이 쿼리에서 `JOIN FETCH d.employees e` 는 **'IT' 부서에 속한 월급이 50000 이상인 직원들을 함께 로딩하는 페치 조인**입니다.

5. **경로 탐색과 함께 사용:**

   페치 조인은 경로 탐색과 함께 사용될 수 있습니다:

```sql
SELECT d FROM Department d 
JOIN FETCH d.employees e 
JOIN FETCH e.projects p 
WHERE d.name = 'IT' AND p.status = 'ACTIVE'
```

이 쿼리에서 `JOIN FETCH d.employees e` 와 `JOIN FETCH e.projects p` 는 각각 부서, 직원, 프로젝트에 대한 페치 조인이며,
 'IT' 부서에 속하면서 프로젝트 상태가 'ACTIVE'인 데이터를 함께 로딩합니다.

**주의사항:**

- 페치 조인은 성능 최적화를 위한 강력한 도구이지만, 필요한 경우에만 사용해야 합니다. 
  과도한 페치 조인은 데이터베이스 부하를 초래할 수 있습니다.
- 페치 조인은 연관된 엔터티나 컬렉션에 대해 LAZY 로딩이 설정되어 있을 때 유용합니다. 
  EAGER 로딩이 이미 설정되어 있다면 페치 조인을 사용할 필요가 없습니다.
- 페치 조인은 엔터티 간의 조인을 생성하기 때문에, 데이터 모델을 잘 이해하고 적절하게 사용해야 합니다.



### 2.4 [LEFT[OUTER]|INNER] JOIN FETCH

`[LEFT[OUTER]|INNER] JOIN FETCH`는 JPA에서 사용되는 페치 조인의 일종으로, 데이터베이스 쿼리에서 연관된 엔터티나 컬렉션을 함께 로딩하는데 사용됩니다. 이 구문에서 `[LEFT[OUTER]|INNER]`는 조인의 타입을 나타내며, `JOIN FETCH`는 페치 조인을 나타냅니다.



1. **LEFT OUTER JOIN FETCH:**

- 왼쪽 외부 조인은 왼쪽 테이블의 모든 레코드와 일치하는 오른쪽 테이블의 레코드를 가져옵니다. 만약 일치하는 오른쪽 테이블의 레코드가 없다면, 결과에는 해당 엔터티의 인스턴스가 null인 경우가 포함됩니다. 따라서 `LEFT OUTER JOIN FETCH`를 사용하면 왼쪽 테이블의 모든 엔터티와 연관된 엔터티를 함께 로딩하게 됩니다.

- 왼쪽 외부 조인을 사용하여 부서와 관련된 모든 직원을 가져오는 예제입니다. 
  만약 부서에 속한 직원이 없는 경우에도 해당 부서는 결과에 포함됩니다.

  ```sql
  SELECT d FROM Department d LEFT OUTER JOIN FETCH d.employees WHERE d.name = 'IT'
  ```

  이 쿼리는 'IT' 부서와 관련된 모든 직원을 함께 로딩합니다. 만약 'IT' 부서에 속한 직원이 없다면, 해당 부서는 여전히 결과에 포함되며, 부서의 `employees` 컬렉션은 비어있을 것입니다.



2. **INNER JOIN FETCH:**

- 내부 조인은 왼쪽과 오른쪽 테이블 간의 일치하는 레코드만을 가져옵니다. 결과에는 해당 엔터티의 인스턴스가 null인 경우가 포함되지 않습니다. `INNER JOIN FETCH`를 사용하면 오른쪽 테이블과 일치하는 엔터티만 함께 로딩됩니다.

- 내부 조인을 사용하여 부서와 관련된 모든 직원을 가져오는 예제입니다. 
  부서에 속한 직원이 하나도 없는 경우 해당 부서는 결과에 포함되지 않습니다.

  ```sql
  SELECT d FROM Department d INNER JOIN FETCH d.employees WHERE d.name = 'IT'
  ```

  이 쿼리는 'IT' 부서와 관련된 모든 직원을 함께 로딩합니다. 하지만 'IT' 부서에 속한 직원이 하나도 없다면, 해당 부서는 결과에 포함되지 않습니다.



### 2.5 엔티티 페치 조인

- 엔티티 페치 조인은 **JPQL 쿼리나 Criteria API에서 `JOIN FETCH`를 사용하여 연관된 엔터티를 함께 로딩하는 것을 의미**합니다. 
- 이를 통해 특정 엔터티와 그와 관련된 엔터티들을 단일 쿼리로 로딩할 수 있습니다.



### 2.6 엔티티 페치 조인 예제 1

간단한 예제를 통해 엔티티 페치 조인을 설명하겠습니다. 가정하에 `Department`와 `Employee` 엔터티가 있다고 가정해 봅시다.

```java
@Entity
public class Department {
    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Employee> employees;

    // getter and setter methods
}

@Entity
public class Employee {
    @Id
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    // getter and setter methods
}
```

이제 페치 조인을 사용하여 부서와 함께 직원을 로딩하는 JPQL 쿼리를 살펴봅시다.

```sql
SELECT d 
FROM Department d JOIN FETCH d.employees 
WHERE d.name = 'IT'
```

위의 쿼리에서 `JOIN FETCH d.employees`는 엔티티 페치 조인을 나타냅니다. 이를 실행하면 'IT' 부서와 관련된 모든 직원들이 함께 로딩되어 쿼리 한 번으로 필요한 데이터를 가져올 수 있습니다.



```java
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // try-with-resources 사용하여 EntityManagerFactory 및 EntityManager 자동으로 닫기
        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
             EntityManager entityManager = entityManagerFactory.createEntityManager()) {

            // 트랜잭션 시작
            entityManager.getTransaction().begin();

            // JPQL 쿼리 실행
            String jpql = "SELECT d FROM Department d JOIN FETCH d.employees WHERE d.name = 'IT'";
            List<Department> departments = entityManager.createQuery(jpql, Department.class).getResultList();

            // 결과 출력
		   departments.forEach(department -> {
		        System.out.println("Department: " + department.getName());
		        department.getEmployees().forEach(employee ->
  		            System.out.println("  Employee: " + employee.getName()));
			});

            // 트랜잭션 커밋
            entityManager.getTransaction().commit();
        } // try-with-resources를 사용하면 별도로 close() 호출이 필요 없음
    }
}
```





### 2.7 엔티티 페치 조인 예제 2

```sql
[JPQL]
select m 
from Member m join fetch m.team
// 즉시로딩과 비슷한데 m.team 처럼 내가 지정을 딱 할 수 있는것이다.
[SQL]
select M.*, T.* 
FROM MEMBER M INNER JOIN TEAM T ON M.TEAM_ID = T.ID
```

![img](https://velog.velcdn.com/images/cheshirehyun/post/541e349d-3c9e-4a33-9144-df2a58272a2d/image.png)



**페치조인 사용코드**

```java
//팀과 멤버를 셋팅한 상태에서
Member member2 = new Member();
member2.setUsername("회원2");
member2.setUser(teamA);
em.persist();

Member member3 = new Member();
member3.setUsername("회원2");
member3.setTeam(teamB);
em.persist(member3);

em.flush();
em.clear();

String query = "select m From Member m";


//다음을 실행하면
for(Member member : members) {
	System.out.println( "username = "+member.getUsername()+", "+
    	"team = "+member.getTeam().name() );
}
```

<img src="https://velog.velcdn.com/images/cheshirehyun/post/d40df569-75a9-4dca-9e76-33197c5970c0/image.png" alt="img" style="zoom:50%;" />



member를 가져오고 team이 프록시였으니까 영속성컨텍스트에 요청해서 팀 정보도 가져오게 합니다. 
따라서 회원1의 팀A는 1차캐시로 가져오고, 회원2의 팀A는 1차캐시에서 가져옵니다.

<img src="https://velog.velcdn.com/images/cheshirehyun/post/0906e088-49c6-45c4-bd5c-cb10875e1f63/image.png" alt="img" style="zoom:50%;" />

팀B에 대한 정보도 없기 때문에 쿼리를 날려서 회원3에 대한 결과를 반환합니다.

이때, 모든 회원이 다 다른 소속이면 매번 쿼리를 날려줘야 합니다.
-> N+1 문제.(즉시로딩, 지연로딩 모두 발생하는 문제입니다.)

이때 페치조인을 사용한다면?

```java
String query = "select m From Member m join fetch m.team";
```

<img src="https://velog.velcdn.com/images/cheshirehyun/post/81f97cb3-d167-4c11-964a-8ab8fafbe6ff/image.png" alt="img" style="zoom:95%;" />

멤버와 팀의 데이터를 조인해서 가져옵니다. 즉 프록시를 사용하지 않습니다.



## 3. 컬렉션 페치 조인

- 컬렉션 페치 조인(Collection Fetch Join)은 연관된 엔터티의 컬렉션을 함께 로딩하는 기능을 제공합니다. 
- 이를 통해 N+1 쿼리 문제를 피하고 성능을 최적화할 수 있습니다.



### 3.1 컬렉션 페치 조인 특징

1. **컬렉션 로딩 최적화:**
   - 컬렉션 페치 조인은 연관된 엔터티의 컬렉션을 함께 로딩하여 N+1 쿼리 문제를 피할 수 있습니다. 
     일반적으로는 부모 엔터티와 연관된 모든 자식 엔터티들을 함께 로딩하는 데 사용됩니다.
2. **단일 쿼리로 데이터 로딩:**
   - 컬렉션 페치 조인을 사용하면 단일 쿼리로 필요한 데이터를 로딩할 수 있습니다. 
     이는 일반적으로 성능을 향상시키고, 여러 쿼리를 실행할 필요가 없으므로 데이터베이스 부하를 감소시킵니다.
3. **`FetchType.LAZY` 사용 가능:**
   - 연관된 엔터티의 컬렉션에 `FetchType.LAZY`를 사용하더라도 페치 조인을 통해 해당 컬렉션을 로딩할 수 있습니다. 
     이는 지연 로딩(Lazy Loading)을 유지하면서 필요한 시점에 적절한 방식으로 데이터를 로딩할 수 있도록 합니다.
4. **주의사항:**
   - 컬렉션 페치 조인을 사용할 때에도 주의가 필요합니다. 데이터베이스의 큰 테이블과 연관된 큰 컬렉션을 함께 로딩하면 성능 이슈가 발생할 수 있습니다. 필요한 데이터만 로딩하도록 쿼리를 최적화하고, 불필요한 데이터를 로딩하지 않도록 주의해야 합니다.

컬렉션 페치 조인은 특히 일대다(`@OneToMany`)나 다대다(`@ManyToMany`)와 같은 연관 관계에서 유용하게 활용됩니다.





### 3.2 컬렉션 페치 조인 예제 1

컬렉션 페치 조인은 주로 JPQL 쿼리나 Criteria API에서 `JOIN FETCH`를 사용하여 사용합니다. 아래는 간단한 예제를 통해 컬렉션 페치 조인을 설명합니다. 가정하에 `Department`와 `Employee` 엔터티가 있다고 가정합니다.

```java
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Employee> employees;

    // getter and setter methods
}

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    // getter and setter methods
}
```

이제 `Department` 엔터티의 `employees` 컬렉션을 함께 로딩하는 JPQL 쿼리를 작성해 보겠습니다.

```sql
SELECT d 
FROM Department d JOIN FETCH d.employees 
WHERE d.name = 'IT'
```

위의 쿼리에서 `JOIN FETCH d.employees`는 컬렉션 페치 조인을 나타냅니다. 이를 실행하면 'IT' 부서와 관련된 모든 직원들이 함께 로딩되어 쿼리 한 번으로 필요한 데이터를 가져올 수 있습니다.

컬렉션 페치 조인을 사용하면 쿼리 성능이 향상되며, 연관된 엔터티의 컬렉션을 추가 쿼리 없이 함께 로딩할 수 있습니다. 그러나 적절하게 사용해야 하며, 불필요한 데이터를 로딩하지 않도록 주의해야 합니다.





### 3.3 컬렉션 페치 조인과 엔터티 페치 조인의 차이

기본적으로 컬렉션 페치 조인은 컬렉션 객체를 로딩하고, 엔터티 페치 조인은 엔터티를 로딩하는 데 사용됩니다. 이는 각각의 페치 조인이 어떤 종류의 연관 관계를 로딩하는지에 따라서 결정됩니다. 컬렉션 페치 조인과 엔터티 페치 조인은 JPA에서 제공하는 페치 조인의 두 가지 주요 형태입니다. 각각의 특징과 차이를 살펴보겠습니다.

1. **엔터티 페치 조인(Entity Fetch Join):**

   - **용도:** 특정 엔터티와 그와 연관된 엔터티를 함께 로딩할 때 사용됩니다.

   - **사용 방법:** JPQL 쿼리나 Criteria API에서 `JOIN FETCH`를 사용하여 특정 엔터티의 연관된 엔터티를 함께 로딩합니다.

   - **예시:** `SELECT d FROM Department d JOIN FETCH d.employees WHERE d.name = 'IT'`

   - **설명:** 위의 예시에서는 'IT' 부서와 그 부서에 속한 모든 직원들을 함께 로딩합니다.

     

2. **컬렉션 페치 조인(Collection Fetch Join):**

   - **용도:** 특정 엔터티의 컬렉션을 함께 로딩할 때 사용됩니다.
   - **사용 방법:** JPQL 쿼리나 Criteria API에서 `JOIN FETCH`를 사용하여 특정 엔터티의 컬렉션을 함께 로딩합니다.
   - **예시:** `SELECT d FROM Department d JOIN FETCH d.employees WHERE d.name = 'IT'`
   - **설명:** 엔터티 페치 조인과 동일한 쿼리를 사용하여 'IT' 부서와 그 부서에 속한 모든 직원들의 컬렉션을 함께 로딩합니다.

주요 차이점은 주로 사용되는 연관 관계의 타입에 따라 나타납니다. 엔터티 페치 조인은 일대다(`@OneToMany`)나 다대일(`@ManyToOne`) 등의 **단일 값 연관 관계를 로딩**할 때 사용되고, 컬렉션 페치 조인은 일대다나 다대일과 같은 **컬렉션 값 연관 관계를 로딩**할 때 사용됩니다. 그러나 실제로는 이 두 페치 조인이 유사한 용도에서 사용될 수 있습니다.





### 컬렉션 페치 조인 예제 2

- 일대다 관계, 컬렉션 페치 조인

```sql
// JPQL
select t from Team t join fetch t.members where t.name='팀A'

// SQL
select t.*, m.* from team t inner join member m on t.id=m.team_id where t.name = '팀A'
```

이를 수행하면 Team은 하나지만 Member가 1개 이상일 수 있습니다. // 일대다 관계에선 데이터가 뻥튀기 될 수 있습니다.

![img](https://velog.velcdn.com/images%2Fsongs4805%2Fpost%2F05ac82e1-5155-48a0-86d7-61e6e9d16697%2F%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202022-02-10%20%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE%2011.50.04.png)

팀A는 1개지만, 그에 해당하는 멤버는 회원1, 회원2로 2개이기 때문에 조회 결과는 위 표처럼 2개의 row가 됩니다. 팀은 하나이기에 같은 주소값(0x100)을 가진 결과가 두개가 나오고 팀A의 입장에선 회원1, 회원2를 가집니다.
// 이것이 바로 결과상의 뻥튀기가 발생한 것입니다.

다음은 컬렉션 페치 조인 사용 코드 예시입니다.

```java
String query = "select t From Team t join fetch t.members";

List<Team> result = em.createQuery(query, Team.class)
        .getResultList();

for (Team team : result) {
    System.out.println("team = " + team.getName() + "|members=" + team.getMembers().size());
    for (Member member : team.getMembers()) {
        System.out.println("-> member = " + member);
    }
}
```

![img](https://velog.velcdn.com/images%2Fsongs4805%2Fpost%2F67fd39d0-fa9c-4459-81a5-6324acf74225%2F%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202022-02-10%20%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE%2010.36.42.png)

## 페치 조인과 DISTINCT

- SQL의 DINSTINCT는 중복된 결과를 제거하는 명령입니다.

- JPQL의 DISTINCT는 2가지 기능을 제공합니다

  1. SQL에 DISTINCT를 추가합니다.

  1. 애플리케이션에서 엔티티 중복 제거합니다.

```sql
select distinct t
from Team t join fetch t.members
where t.name = '팀A'
```

- SQL에 DISTINCT를 추가하지만 데이터가 다르므로 SQL결과 중복제거 실패합니다.
  ![img](https://velog.velcdn.com/images/cheshirehyun/post/d47998f7-048b-4602-9818-ff6cfe835a72/image.png)
  두 데이터가 완전히 같아야 하나로 판단합니다.
- DISTINCT가 추가로 어플리케이션에서 중복제거 시도 -> 같은 식별자를 가진 Team엔티티 제거합니다.

![img](https://velog.velcdn.com/images/cheshirehyun/post/f72eff02-1a7e-4034-a10c-17b6d44c18f3/image.png)

```null
String query = "select t From Team t join fetch t.members";
List<Team> result = em.createQuery(query, Team.class).getREsultList();
System.out.println("result = "+result.size()); 
```

팀 2개로 설정되어있지만 이 결과는 3이 나온다. 페치조인으로 인해 결과값이 뻥튀기 된것!

```java
String query = "select distinct t From Team t join fetch t.members";
List<Team> result = em.createQuery(query, Team.class).getREsultList();
System.out.println("result = "+result.size()); 

for(Team team : result){
	SYstem.out.println("team = "+team.getName()+"| members = "+team.getMembers().size());
    for(Member member : team.getMembers())) System.out.println("-> member = "+member);
}
```

![img](https://velog.velcdn.com/images/cheshirehyun/post/98b80d8b-e84f-4970-baf8-b628cb3213c3/image.png)
distinct를 사용하면 정상적으로 출력되는것을 볼 수 있다!

### 페치조인과 일반조인 차이

- 일반조인 실행시 연관된 엔티티를 함께 조회하지않음

- JPQL

  ```java
  select t
  from Team t join t.members m
  where t.name='팀A'
  ```

- SQL

  ```java
  SELECT T.*
  FROM TEAM T
  INNER JOIN MEMBER M IN T.ID = M.TEAM_ID
  WHERE T.NAME = '팀A'
  ```

- JPQL은 결과를 반환할때 연관관계를 고려하지 않는다. 단지 SELECT절에 지정한 엔티티만 조회할 뿐이다. 여기서는 팀 엔티티만 조회하고 회원엔티티는 조회하지 않는다. 오로지 페치조인을 사용할때만 연관된 엔티티도 함께 조회한다(즉시로딩).

- 페치조인은 객체그래프를 SQL 한번에 조회하는 개념이다.
  일반조인의 경우 t에 대한 쿼리만 나가고 members에 대한 정보는 프록시상태이다. 이후 member에 대한 정보를 요청해야만 쿼리가 나가게되는 지연로딩상태이다. 그러나 페치조인의 경우, 쿼리가 단 한번만 나가며 지연로딩없이 한번에 데이터를 불러올 수 있다.

## 페치조인2 - 한계

### 페치조인의 특징과 한계

- 페치조인 대상에는 별칭을 줄 수 없다.

  \- 하이버네이트는 가능하나 가급적 사용하지말자

  ~~~java
     // 이런거 다 안된다! 페치조인은 연관된걸 모두 끌고오는것이기 떄문에 이렇게 걸러서 가져오는건 불가능하다. 이렇게 하고싶으면 애초에 team에서 다섯개만 뽑아서 가져오기 같은걸 해야함.
     String query = "select t From Team t join fetch t.members as m";
     String query = "select t From Team t join fetch t.members as m where m.username"
     ```
  ~~~

- **둘 이상의 컬렉션은 페치조인 할 수 없다**

- 컬렉션을 페치조인하면 페이징API를 사용할 수 없다

  \- 일대일, 다대일 같은 단일 값 연관필드들은 페치조인해도 페이징 가능

  - 하이버네이트는 경고로그를 남기고 메모리에서 페이징(매우위험)

- 연관된 엔티티들을 SQL 한번으로 조회 - 성능 최적화

- 엔티티에 직접 적용하는 글로벌 로딩 전략보다 우선함
  \- @OneToMany(fetch=Fetch.Type.LAZY) //글로벌로딩 전략

- 실무에서 글로벌 로딩 전략은 모두 지연로딩

- 최적화가 필요한곳은 페치조인 적용

(CF) `@BatchSize()`옵션 -> 페치조인 성능 제격

### 페치조인 정리

모든것을 페치조인으로 해결할 수는 없다. 페치조인은 객체그래프를 유지할때 사용하면 효과적이다. 여러테이블을 조인해서 엔티티가 가진 모양이 아닌 전혀 다른 결과를 내야하면 페치조인보다는 일반조인을 사용하고 필요한 데이터만 조회해서 DTO로 반환하는것이 효과적이다.

## 다형성쿼리

![img](https://velog.velcdn.com/images/cheshirehyun/post/98f7cb7c-ae6d-480b-bf6d-a250c68f1ae0/image.png)
위와같은 상황에서 특정 기능들을 제공한다.

### TYPE

- 조회 대상을 특정 자식으로 한정한다,
  (EX) Item중에 Book, Movie를 조회하라.

```java
[JPQL]
select i from item i
where type(i) IN (Book, Movie)

[SQL]
select i from i
where i.DTYPE in ('B','M')
```

### TREAT(JPA2.1)

- 자바의 타입 캐스팅과 유사
- 상속구조에서 부모타입을 특정 자식타입으로 다룰때 사용
- FROM, WHERE, SELECT(하이버네이트 지원) 사용
  (EX) 부모인 Item과 자식 Book이 있다.

```java
[JPQL]
select i from Item i
where treat(i as Book).auther = 'kim'
[SQL]
select i.* from Item i
where i.DTYPE = 'B' and i.author = 'kim'
```

## 엔티티직접사용

### 기본키값

JPQL에서 엔티티를 직접사용하면 SQL에서 해당 엔티티의 기본 키 값을 사용

```java
[JPQL]
select count(m.id) from Member m //엔티티의 아이디를 직접사용
select count(m) from Member //엔티티를 직접사용

[SQL] JPQL둘 다 같은 다음 SQL 실행
select count(m.id) as cnt from Member m
```

#### 예시

```java
//엔티티를 파라미터로 전달
String jpql = "select m from Member m where m = :member";
List resultList = em.creaeteQuery(jpql)
					.setParameter("member",member)
                    .getResultList();
//식별자를 직접전달
String jpql = "select m from Member m where m.id = :memberId";
List resultList = em.creaeteQuery(jpql)
					.setParameter("member",memberId)
                    .getResultList();

//실행된 SQL - 파라미터나 식별자를 전달해도 똑같은 SQL이 실행된다.
select m.* from Member m where m.id = ?
```

### 외래키값

```java
Team team = em.find(Team.class, 1L);

String qlString = "select m from Member m where m.team = :team";
List resultList = em.creaeteQuery(qlString)
					.setParameter("team",team)
                    .getResultList();
                    
Team team = em.find(Team.class, 1L);

String qlString = "select m from Member m where m.team.id = :teamId";
List resultList = em.creaeteQuery(qlString)
					.setParameter("team",teamId)
                    .getResultList();
//실행된 SQL
select m.* from Member , where m.team_id = ?
```

## Named 쿼리

### 정적쿼리

- 미리 정의해서 이름을 붙여두고 사용하는 JPQL
- 어노테이션, XML에 정의
- 어플리케이션 로딩시점에 초기화 후 재사용
- 어플리케이션 로딩시점에 쿼리를 검증
  따라서 쿼리를 잘못 작성하면 어플리케이션 로딩시 컴파일에러가 뜬다.

### 어노테이션

```java
@Entity
@NamedQuery{
	name = "Member.findByUsername",
    query = "select m from Member m where m.username = :username" }
public class Member {
	...
}

List<Member> resultList = em.createNamedQuery("Member.findByUsername",Member.class)
	.getParameter("username","회원1")
    .getResultList();
```

### XML에 정의

```java
[META-INF/persistence.xml]
<persistence-unit name="jpabook" >
	<mapping-file>META-INF/ormMember.xml</mapping-file>
    
[META-INF/ormMember.xml]
<?xml version="1.0" encoding="UTF-8"?>
<entity-mappings xmlns="http://xmlns.jcp.org/xml/ns/persistence/orm" version="2.1">
	
    <named-query name="Member.findByUsername">
    	<query><![CDATA[
        	select m
            from Member m
            where m.username = :username
        ]]></query>
    <named-query>
    
    <named-query name="Member.count">
    	<query>select count(m) from Member m</query>
    </named-query>

</entity-mappings>
```

### Named쿼리 환경에 따른 설정

- xml이 항상 우선권을 가진다
- 어플리케이션 운영 환경에 따라 다른 XML을 배포할 수 있다.

> 사실은 이렇게 엔티티에 named쿼리를 사용하면 가독성도 떨어지니까 나중에 Spring-data를 사용하도록 할것이다.

## 벌크연산

- 재고가 10개 미만인 모든 상품의 가격을 10% 상승시키려면?
- JPA 변경감지 기능으로 실행하려면 너무 많은 SQL을 실행할것이다
  1.재고가 10개미만인 상품을 리스트로 조회한다.
  2.상품엔티티의 가격을 10%증가한다
  3.트랜잭션 커밋시점에 변경감지가 동작한다.
  -> 변경된 데이터가 100개면 100번의 UPDATE SQL을 실행함
- 따라서 벌크연산을 이용해 쿼리한번으로 여러테이블 로우 변경(엔티티)

### 예제

- executeUpdate()의 결과는 영향받은 엔티티수의 반환
- UPDATE, DELETE를 지원
- INSERT(insert into ...select, 하이버네이트 지원)

```java
// age=20으로 싹다 바꾸는 예제. flush()가 자동 호출된다.(자동호출조건 = commit/쿼리나갈때/자동호출시)
int resultCount = em.createQuery("update Member m set m.age=20").executeUpdate();
```

### 주의

- 벌크연산은 영속성 컨텍스트를 무시하고 DB에 직접쿼리한다.
  해결방법 1) 벌크연산을 먼저 실행
  **해결방법 2) 벌크연산 수행후 영속성 컨텍스트 초기화**

```java
int resultCount = em.createQuery("select Member m set m.age=20").executeUpdate();
System.out.println("resultCount = "+resultCount);

System.out.println("member1.getAge() = "+member1.getAge());
System.out.println("member2.getAge() = "+member2.getAge());
System.out.println("member3.getAge() = "+member3.getAge());
```

![img](https://velog.velcdn.com/images/cheshirehyun/post/fa2e43bd-bd5d-42c9-87d7-2f61c4002f21/image.png)
![img](https://velog.velcdn.com/images/cheshirehyun/post/43e2ecdf-2e6d-4c2b-8274-220dd6df11e7/image.png)
카운트는 3인데 Age는 다 0으로 출력되는 문제. DB엔 20으로 잘 들어왔는데도!

[순서](https://velog.io/@cheshirehyun/1) 영속성 컨텍스트에 createQuery 시점에 플러시가 되면서 세 age가 0으로 셋팅됐다가 20으로 재셋팅됨.
(2) 그런데 이제 영속성 컨텍스트엔 처음에 세팅했던 0,0,0이 남아있음(영속성 컨텍스트는 flush()에서 지워지는게 아니라 clear()에서 지워지니까)
(3) 따라서 벌크연산에선 데이터 정합성이 안맞기도 하다. 이때문에 해결방법 2를 사용한다.

```java
# try 1
int resultCount = em.createQuery("select Member m set m.age=20").executeUpdate();

Member findMember = em.find(Member.class, member1.getId());
System.out.println("findMember = "+findMember.getAge());

System.out.println("member1.getAge() = "+member1.getAge());
//위와같이 find()를 통해 가져와도 DB가 아닌 영속성 컨텍스트 내용을 반환하기 때문에....

# try 2
int resultCount = em.createQuery("select Member m set m.age=20").executeUpdate();

em.clear(); //꼭 클리어를 해주고 가져와야 제대로 된 결과 나옴!

Member findMember = em.find(Member.class, member1.getId());
System.out.println("findMember = "+findMember.getAge());

System.out.println("member1.getAge() = "+member1.getAge());
```