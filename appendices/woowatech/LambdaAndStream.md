# 깃짱, 이리내의 람다와 스트림

## 람다

### 람다식이 뭐야?

- 메서드를 하나의 식으로 표현한 것



**두 수의 합을 반환하는 `addOperation` 메서드**

```java
int addOperation(int a, int b) {
    return a + b;
}
```



**두 수의 합을 반환하는 람다식**

```java
(int a, int b) -> a + b;
```



**매개변수 타입 생략이 가능한 람다식**

```java
(int a, int b) -> a * b; // 매개변수 타입 생략 전

(a, b) -> a * b; // 매개변수 타입 생략 후
```



**파라미터의 괄호 생략이 가능한 람다식**

```java
(a) -> a * a; // 파라미터의 괄호 생략 전

a -> a * a; // 파라미터의 괄호 생략 후 
```



**바디의 중괄호, 세미콜론 생략이 가능한 람다식**

```java
(String name, int i) -> { // 중괄호 생략 전
    System.out.println(name + "=" + i); // 세미콜론 생략 전
}

(String name, int i) -> // 중괄호 생략 후
    System.out.println(name + "=" + i) // 세미콜론 생략 후
```





### 람다식은 함수형 인터페이스를 구현한 인스턴스라고?

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20230602181441690.png" alt="image-20230602181441690" style="zoom:70%;" />

람다식을 변수로 추출하면 `Runnable` 함수형 인터페이스가 등장합니다. 함수형 인터페이스는 추상 메서드가 딱 1개만 있는 인터페이스를 의미합니다. 이 때 `default` 메서드와 `static` 메서드는 존재할 수 있습니다.



**함수형 인터페이스**

```java
package bridge;

@FunctionalInterface // 함수형 인터페이스
public interface BridgeNumberGenerator {
    int generate(); // 추상 메서드
}
```



**함수형 인터페이스의 인스턴스 생성하기**

`generate()` 추상메서드를 오버라이딩해서 클래스를 생성합니다. 이 클래스로부터 인스턴스를 생성할 수 있습니다.

```java
public class BridgeRandomNumberGenerator implements BridgeNumberGenerator {
    @Override
    public int generate() {
        return new Random().nextInt();
    }
}
```



**람다식을 활용한 인스턴스 생성하기**

`BridgeNumberGenerator` 인터페이스 안에 선언한 `generate()` 추상메서드는 `() -> new Random().nextInt()` 람다식에 의해 인스턴스 생성할 수 있습니다. 따라서 함수형 인터페이스의 딱 1개의 `generate()`  추상메서드는 `new Random().nextInt()` 람다식과 1:1 매칭이 됩니다. 

```java
BridgeNumberGenerator bridgeRandomNumberGenerator = () -> new Random().nextInt();
int randomNumber = bridgeRandomNumberGenerator.generate();
```

그럼 람다식을 쓰려면 매 번 함수형 인터페이스를 정의해야할까요? 생산성이 매우 떨어지게 될 것입니다. 
그래서 **표준 함수형 인터페이스를 사용하는 것을 권장**하고 있습니다.



### 남이 잘 만들어놓은 표준 함수형 인터페이스를 쓰는걸 추천해

매개변수와 반환값의 유무에 따라 함수형 인터페이스를 적절하게 사용하면 됩니다. 아래 표는 자주 사용하는 인터페이스입니다.
 `java.util.function` 패키지 안에 표준 함수형 인터페이스들이 이미 많이 생성되어 있습니다.

| 함수형 인터페이스 | 매개변수 |       메서드        |   반환값    |
| :---------------: | :------: | :-----------------: | :---------: |
|    `Runnable`     |    X     |    `void run()`     |      X      |
|   `Supplier<T>`   |    X     |      `T get()`      |    O (T)    |
|   `Consumer<T>`   |  O (T)   | `void accept(T t)`  |      X      |
| `Function<T, R>`  |  O (T)   |   `R apply(T t)`    |    O (R)    |
|  `Predicate<T>`   |  O (T)   | `boolean test(T t)` | O (boolean) |



### 메서드 참조를 하면 좀더 코드가 깔끔해질걸?

표준 함수형 인터페이스를 사용하면 디폴트 메서드를 제공해서 다른 코드와 상호운용성이 좋아집니다. 예를 들어서 `Predicate` 에서 `and(), or(), negate()` 으로 조건을 조합해 하나의 새로운 `Predicate` 로 결합이 가능합니다.

```java
public interface Predicate<T> {
    boolean test(T t);
    
    default Predicate<T> and(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }
    
    default Predicate<T> negate() {
        return (t) -> !test(t);
    }
    
    default Predicate<T> or(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) || other.test(t);
    }
    
    static <T> Predicate<T> isEqual(Object targetRef) {
        return (null == targeRef)
            ? Objects::isNull
            : object -> targetRef.equals(object);
    }
}
```



람다식이 하나의 메서드만 호출하는 경우에는 메서드 참조로 람다식을 간략히 할 수 있습니다. 
`클래스 이름::메서드 이름` 또는 `참조변수::메서드 이름`으로 변경할 수 있습니다.

