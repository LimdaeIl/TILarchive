# 람다식 (Lambda expression)

자바가 1996년에 처음 등장한 이후로 두 번의 큰 변화가 있었는데, 한번은 JDK1.5부터 추가된 지네릭스(generics)의 등장이고, 또 한번은 JDK1.8부터 추가된 람다식(lambda expression)의 등장이다. 람다식의 도입으로 인해 자바는 객체지향언어인 동시에 함수형 언어가 되었다.



## 1. 람다식이란?

람다식(Lambda expression)은 메서드를 하나의 식(expression)으로 표현한 것이다. 람다식은 함수를 간략하면서도 명확한 식으로 표현할 수 있게 해준다. 메서드를 람다식으로 표현하면 메서드의 이름과 반환값이 없어지므로, 람다식을 **익명 함수(anonymous function)**이라고도 한다.

```java
int[] arr = new int[5];
Arrays.setAll(arr, i -> (int)(Math.random() * 5) + 1);	//arr = [1, 5, 2, 1, 1]

//i -> (int)(Math.random() * 5) + 1
int method(int i) {
	return (int)(Math.random() * 5) + 1;
}
```

모든 메서드는 클래스에 포함되어야 하므로 클래스도 새로 만들어야 하고, 객체도 생성해야만 비로소 이 메서드를 호출할 수 있다. 그러나 람다식은 이 모든 과정없이 오직 람다식 자체만으로도 메서드의 역할을 대신할 수 있다. 

람다식은 메서드의 매개변수로 전달되어지는 것이 가능하고, 메서드의 결과로 반환될 수도 있다. 람다식으로 인해 메서드를 변수처럼 다루는 것이 가능해진 것이다.

 

**메서드와 함수의 차이**

객체지향개념에서는 함수(function) 대신 객체의 행위나 동작을 의미하는 메서드(method)라는 용어를 사용한다. 메서드는 함수와 같은 의미이지만, 특정 클래스에 반드시 속해야 한다는 제약이 있기 때문에 기존의 함수와 같은 의미의 다른 용어를 선택해서 사용해 왔다. 그러나 이제 람다식을 통해 메서드가 하나의 독립적인 기능을 하기 때문에 함수라는 용어를 사용하게 되었다.

 

## 2. 람다식 작성하기

람다식은 익명 함수이므로 메서드에서 **이름과 반환타입을 제거**하고 매개변수 선언부와 몸통`{}` 사이에 `->` 를 추가한다.

```java
반환타입 메서드이름(매개변수 선언) {
	문장들
}

/* 반환타입 메서드이름 */ (매개변수 선언) -> {
	문장들
}

/* Example : 두 값 중에서 큰 값을 반환하는 메서드 max */
int max(int a, int b) {
	retrun a > b ? a : b;
}

/* int max */ (int a, int b) -> {
	return a > b ? a : b;
}

/* 
	반환값이 있는 메서드의 경우, return문 대신 식(expression)으로 대신할 수 있다. 
	식의 연산결과가 자동적으로 반환값이 된다.
    이때는 문장(statement)이 아닌 식이므로 끝에 ;을 붙이지 않는다.
*/

(int a, int b) -> { return a > b ? a : b; }
(int a, int b) -> a > b ? a : b

/*
	람다식에 선언된 매개변수의 타입은 추론이 가능한 경우는 생략할 수 있는데,
    대부분의 경우에 생략 가능하다.
	(int a, b) -> a > b ? a : b
    와 같이 두 매개변수 중 어느 하나의 타입만 생략하는 것은 허용되지 않는다.
*/
    
(int a, int b) -> a > b ? a : b
(a, b) -> a > b ? a : b

/*
	선언된 매개변수가 하나뿐인 경우에는 괄호()를 생략할 수 있다.
    단, 매개변수의 타입이 있으면 괄호()를 생략할 수 없다.
*/
    
(a) -> a * a
(int a) -> a * a
a -> a * a	//OK
int a -> a * a	//에러

/*
	괄호{} 안의 문장이 하나일 때는 괄호{}를 생략할 수 있다.
    이 때 문장의 끝에 ;을 붙이지 않는다.
*/
    
(String name, int i) -> {
	System.out.println(name + " = " + i);
}
(String name, int i) ->
	System.out.println(name + " = " + i)
    
/*
	괄호{} 안의 문장이 return문일 경우 괄호{}를 생략할 수 없다.
*/
    
(int a, int b) -> { return a > b ? a : b; }	//OK
(int a, int b) -> return a > b ? a : b	//에러
```

 



## 3. 메서드를 람다식으로 변환한 예

```java
//메서드1
int max(int a, int b) {
	return a > b ? a : b;
}

//람다식1
(int a, int b) -> { retrun a > b ? a : b; }
(int a, int b) -> a > b ? a : b
(a, b) -> a > b ? a : b


//메서드2
void printVal(String name, int i) {
	System.out.println(name + " = " + i);
}

//람다식2
(String name, int i) -> { System.out.println(name + " = " + i); }
(name, i) -> { System.out.println(name + " = " + i); }
(name, i) -> System.out.println(name + " = " + i)


//메서드3
int square(int x) {
	return x * x;
}

//람다식3
(int x) -> x * x
(x) -> x * x
x -> x * x

//메서드4
int roll() {
	return (int)(Math.random() * 6);
}

//람다식4
() -> { return (int)(Math.random() * 6); }
() -> (int)(Math.random() * 6)


//메서드5
int sumArr(int[] arr) {
	int sum = 0;
    for (int i : arr)
    	sum += i;
    return sum;
}

//람다식5
(int[] arr) -> {
	int sum = 0;
    for (int i : arr)
    	sum += i;
    return sum;
}
```





## 4. 함수형 인터페이스(Functional Interface)

람다식은 익명 클래스의 객체와 동등하다.

```java
(int a, int b) -> a > b ? a : b

new Object() {
	int max(int a, int b) {
    	return a > b ? a : b;
    }
}
```

 

함수형 인터페이스(functional interface) - 람다식을 다루기 위한 인터페이스

```java
@FunctionalInterface
interface MyFunction {	//함수형 인터페이스 MyFunction을 정의
	public abstract int max(int a, int b);
}
```

 

함수형 인터페이스에는 오직 하나의 추상 메서드만 정의되어야 한다는 제약이 있다. 그래야 람다식과 인터페이스의 메서드가 1:1로 연결될 수 있기 때문이다. 반면에 static메서드와 default메서드의 개수에는 제약이 없다.

- @FunctionalInterface를 붙이면, 컴파일러가 함수형 인터페이스를 올바르게 정의하였는지 확인해준다.

 

```java
//인터페이스의 메서드 구현
List<String> list = Arrays.asList("abc", "aaa", "bbb", "ddd", "aaa");

Colldections.sort(list, new Comparator<String>() {
	public int compare(String s1, String s2) {
    	return s2.compareTo(s1);
    }
});

//람다식
List<String> list = Arrays.asList("abc", "aaa", "bbb", "ddd", "aaa");
Collections.sort(list, (s1, s2) -> s2.compareTo(s1));
```

 

**함수형 인터페이스 타입의 매개변수와 반환타입**

```java
//함수형 인터페이스 MyFunction 정의
@FunctionalInterface
interface MyFunction {
	void myMethod();	//추상 메서드
}
```

 

```java
//메서드의 매개변수가 MyFunction타입이면, 
//이 메서드를 호출할 때 람다식을 참조하는 참조변수를 매개변수로 지정해야 한다는 뜻이다.
void aMethod(MyFunction f) {	//매개변수의 타입이 함수형 인터페이스
	f.myMethod();	//MyFunction에 정의된 메서드 호출
}

MyFunction f = () -> System.out.println("myMethod()");
aMethod(f);

//또는 참조변수 직접 람다식을 매개변수로 지정하는 것도 가능하다.
aMethod(() -> System.out.println("myMethod()"));	//람다식을 매개변수로 지정

//메서드의 반환타입이 함수형 인터페이스타입이라면,
//이 함수형 인터페이스의 추상메서드와 동등한 람다식을 가리키는 참조변수를 반환하거나
//람다식을 직접 반환할 수 있다.
MyFunction myMethod() {
	MyFunction f = () -> {};
    return f;	//return () -> {};
}
```

람다식을 참조변수로 다룰 수 있다는 것은 메서드를 통해 람다식을 주고받을 수 있다는 것을 의미한다. 즉, 변수처럼 메서드를 주고받는 것이 가능해진 것이다.

 

## 5. 람다식의 타입과 형변환

함수형 인터페이스로 람다식을 참조할 수 있는 것일 뿐, 람다식의 타입이 함수형 인터페이스의 타입과 일치하는 것은 아니다. 람다식은 익명 객체이고 익명 객체는 타입이 없다. 정확히는 타입은 있지만 컴파일러가 임의로 이름을 정하기 때문에 알 수 없다. 그래서 대입 연산자의 양변의 타입을 일치시키기 위해 형변환이 필요하다.

```java
//interface MyFunction{void method();}
MyFunction f = (MyFunction)(() -> {});	//양변의 타입이 다르므로 형변환 필요 
```



람다식은 이름이 없을 뿐 객체인데도 분명하고 Object타입으로 형변환 할 수 없다. 람다식은 오직 함수형 인터페이스로만 형변환이 가능하다.

```java
Object obj = (Object)(() -> {});	//에러. 함수형 인터페이스로만 형변환 가능

//Object타입으로 형변환하려면, 먼저 함수형 인터페이스로 변환해야 한다.
Object obj = (Object)(MyFunction)(() -> {});
String str = ((Object)(MyFunction)(() -> {})).toString();
```

일반적인 익명 객체라면, 컴파일러가 객체의 타입을 '외부클래스이름$번호'와 같은 형식으로 만들어내지만, 람다식의 타입은 '외부클래스이름$$Lambda$번호'와 같은 형식으로 만들어낸다.

 **외부 변수를 참조하는 람다식**

람다식도 익명 객체, 즉 익명 클래스의 인스턴스이므로 람다식에서 외부에 선언된 변수에 접근하는 규칙은 익명 클래스와 동일하다.

 

## 6.  java.util.function패키지

java.util.function패키지에 일반적으로 자주 쓰이는 형식의 메서드를 함수형 인터페이스로 미리 정의해 놓았다. 매번 새로운 함수형 인터페이스를 정의하지 말고, 가능하면 이 패키지의 인터페이스를 활용하는 것이 좋다.

 

**java.util.function패키지의 주요 함수형 인터페이스**

![img](https://blog.kakaocdn.net/dn/ZRryQ/btrsku0nsxe/x9WKe08kLIYEzlILgAKn7K/img.png)

매개변수와 반환값의 유무에 따라 4개의 함수형 인터페이스가 정의되어 있고, Function의 변형으로 Predicate가 있는데, 반환값이 boolean이라는 것만 제외하면 Function과 동일하다. Predicate는 조건식을 함수로 표현하는데 사용된다.

- 타입 문자 T는 Type을, R은 Return Type을 의미한다.

 

**조건식의 표현에 사용되는 Predicate**

Predicate는 Function의 변형으로, 반환타입이 boolean이라는 것만 다르다. Predicate는 조건식을 람다식으로 표현하는데 사용된다.

```java
Predicate<String> isEmptySTr = s -> s.length() == 0;
String s = "";

if (isEmptyStr.test(s))	//if(s.length() == 0)
	System.out.println("This is an empty String.");
```

 

**매개변수가 두 개인 함수형 인터페이스**

매개변수의 개수가 2개인 함수형 인터페이스는 이름 앞에 접두사 Bi가 붙는다.

![img](https://blog.kakaocdn.net/dn/bNsSvF/btrskFALgKd/tmaKDg1gGdqX21zseKkbHk/img.png)

매개변수의 타입으로 보통 T를 사용하므로, 알파벳에서 T의 다음 문자인 U, V, W를 매개변수의 타입으로 사용하는 것일 뿐 별다른 의미는 없다. 두 개 이상의 매개변수를 갖는 함수형 인터페이스가 필요하다면 직접 만들어 써야 한다.

```java
//3개의 매개변수를 갖는 함수형 인터페이스 선언
@FunctionalInterface
interface TriFunction<T, U, V, R> {
	R apply(T t, U u, V v);
}
```

 

**UnaryOperator와 BinaryOperator**

Function의 또 다른 변형으로, 매개변수의 타입과 반환타입의 타입이 모두 일치한다는 점만 제외하고는 Function과 같다.

- UnaryOperator와 BinaryOperator의 조상은 각각 Function과 BiFunction이다.

![img](https://blog.kakaocdn.net/dn/eob88u/btrr7pGQzwn/KIkX8fuCIHCRPTGSgjbvP1/img.png)



**컬렉션 프레임웍과 함수형 인터페이스**

컬렉션 프레임웍의 인터페이스에 다수의 디폴트 메서드가 추가되었는데, 그 중의 일부는 함수형 인터페이스를 사용한다.

| 인터페이스                                       | 메서드                                     | 설명                           |
| ------------------------------------------------ | ------------------------------------------ | ------------------------------ |
| Collection                                       | boolean removeIf(Predicate<E> fliter)      | 조건에 맞는 요소를 삭제        |
| List                                             | void replaceAll(UnaryOperator<E> operator) | 모든 요소를 변환하여 대체      |
| Iterable                                         | void forEach(Consumer<T> action)           | 모든 요소에 작업 action을 수행 |
| Map                                              | V compute(K key, BiFunction<K, V, V> f)    | 지정된 키의 값에 작업 f를 수행 |
| V computeIfAbsent(K key, Function<K, V> f)       | 키가 없으면, 작업 f 수행 후 추가           |                                |
| V computeIfPresent(K key, BiFunction<K, V, V> f) | 지정된 키가 있을 때, 작업 f 수행           |                                |
| V merget(K key, V value, BiFunction<V, V, V> f)  | 모든 요소에 병합작업 f를 수행              |                                |
| void forEach(BiConsumer<K, V> action)            | 모든 요소에 작업 action을 수행             |                                |
| void replaceAll(BiFunction<K, V, V> f)           | 모든 요소에 치환작업 f를 수행              |                                |

 

**기본형을 사용하는 함수형 인터페이스**

![img](https://blog.kakaocdn.net/dn/n8Oqy/btrshyQdt9Z/W9qYhzK2kCer6CnZeu9t1k/img.png)



 

## 7. Function의 합성과 Predicate의 결합

java.util.function패키지의 함수형 인터페이스에는 추상메서드 외에도 디폴트 메서드와 static메서드가 정의되어 있다.

```java
//Function
default <V> Function<T, V> andThen(Function<? super R, ? extends V> after)
default <V> Function<V, R> compose(Function<? super V, ? extends T> before)
static <T> Function<T, T> identity()

//Predicate
default Predicate<T> and(Predicate<? super T> other)
default Predicate<T> or(Predicate<? super T> other)
default Predicate<T> negate()
static<T> Predicate<T> isEqual(Object targetRef)
```

\* 원래 Function인터페이스는 반드시 두개의 타입을 지정해 줘야하기 때문에, 두 타입이 같아도 Function<T> 라고 쓸 수 없다. Function<T, T>라고 써야한다.

 

**Function의 합성**

두 람다식을 합성해서 새로운 람다식을 만들 수 있다.

![img](https://blog.kakaocdn.net/dn/bFcm7M/btrskt1WVf4/AgYWQCDeVn9i6b8B9TaiBK/img.png)



```java
/*
	문자열을 숫자로 변환하는 함수 f와 숫자를 2진 문자열로 변환하는 함수 g를
    andThen()으로 합성하여 새로운 함수 h 만들기
*/
Function<String, Integer> f = (s) -> Integer.parseInt(s, 16);	//s를 16진수로 인식
Function<Integer, String> g = (i) -> Integer.toBinaryString(i);
//함수 h의 지네릭 타입이 <String, String>이므로 String을 입력받아서 String을 결과로 반환한다.
Function<String, String> h = f.andThen(g);

/*
	compose()를 이용해 두 함수를 반대의 순서로 합성
*/
Function<Integer, String> g = (i) -> Integer.toBinaryString(i);	//i를 2진 문자열로 반환
Function<String, Integer> f = (s) -> Integer.parseInt(s, 16);	//s를 16진수로 인식해서 변환
//함수 h의 지네릭 타입이 <Integer, Integer>이다.
Function<Integer, Integer> h = f.compose(g);

/*
	identity()는 함수를 적용하기 이전과 이후가 동일한 항등 함수가 필요할 때 사용한다.
    이 함수를 람다식으로 표현하면 x -> x 이다.
*/
Function<String, String> f = x -> x;
//Function<String, String> f = Function.identity();	//위의 문장과 동일

System.out.println(f.apply("AAA"));	//AAA가 그대로 출력됨
```

\* 항등 함수는 함수에 x를 대입하면 결과가 x인 함수를 말한다. f(x) = x

 

 

**Predicate의 결합**

여러 Predicate를 and(), or(), negate()로 연결해서 하나의 새로운 Predicate로 결합할 수 있다.

```java
Predicate<Integer> p = i -> i < 100;
Predicate<Integer> q = i -> i < 200;
Predicate<Integer> r = i -> i % 2 == 0;
Predicate<Integer> notP = p.negate();	//i >= 100

Predicate<Integer> all = notP.and(q).or(r);	//100 <= i && i < 200 || i % 2 == 0
System.out.println(all.test(150);	//true

//람다식을 직접 넣기
Predicate<Integer> all = notP.and(i -> i < 200).or(i -> i % 2 == 0);
```

\* Predicate의 끝에 negate()를 붙이면 조건식 전체가 부정이 된다.

 

static메서드인 isEqual()은 두 대상을 비교하는 Predicate를 만들 때 사용한다.

먼저, isEqual()의 매개변수로 비교대상을 하나 지정하고, 또 다른 비교대상은 test()의 매개변수로 지정한다.

```java
Predicate<String> p = Predicate.isEqual(str1);
boolean result = p.test(str2);	//str1과 str2가 같은지 비교하여 결과를 반환

//위 두 문장 합친 하나의 문장
boolean result = Predicate.isEqual(str1).test(str2);	//str1과 str2가 같은지 비교
```

 

## 8. 메서드 참조

람다식이 하나의 메서드만 호출하는 경우에는 메서드 참조(method reference) 라는 방법으로 람다식을 간략히 할 수 있다.

```java
//문자열을 정수로 변환하는 람다식
Function<String, Integer> f = (String s) -> Integer.parseInt(s);

//메서드
Integer wrapper(String s) {	//이 메서드의 이름은 의미없다.
	return Integer.parsetInt(s);
}

//메서드를 빼고 Integer.parseInt()를 직접 호출
//람다식의 일부가 생략되었지만,
//컴파일러는 생략된 부분을 우변의 parseInt메서드의 선언부로부터,
//또는 좌변의 Function인터페이스에 지정된 지네릭 타입으로부터 쉽게 알아낼 수 있다.
Function<String, Integer> f = Integer::parseInt;	//메서드 참조

//another Example
BiFunction<String, String, Boolean> f = (s1, s2) -> s1.equals(s2);

//참조변수 f의 타입을 봤을 때 람다식이 두 개의 String타입의 매개변수를 받는다는 것을 알 수 있다.
//그러므로, 람다식의 매개변수들은 없어도 된다.
//매개변수 s1과 s2를 생략하면 equals만 남는데,
//두 개의 String을 받아서 Boolean을 반환하는 equals라는 이름의 메서드는 다른 클래스에도
//존재할 수 있기 때문에 equals앞에 클래스 이름은 반드시 필요하다.
BiFunction<String, String, Boolean> f = String::equals;	//메서드 참조

//이미 생성된 객체의 메서드를 람다식에서 사용한 경우에는
//클래스 이름 대신 그 객체의 참조변수를 적어줘야 한다.
MyClass obj = new MyClass();
Function<String, Boolean> f = (x) -> obj.equals(x);	//람다식
Function<String, Boolean> f2 = obj::equals;	//메서드 참조
```

 

**람다식을 메서드 참조로 변환하는 방법**

| 종류                          | 람다                       | 메서드 참조       |
| ----------------------------- | -------------------------- | ----------------- |
| static메서드 참조             | (x) -> ClassName.method(x) | ClassName::method |
| 인스턴스메서드 참조           | (obj, x) -> obj.method(x)  | ClassName::method |
| 특정 객체 인스턴스메서드 참조 | (x) -> obj.method(x)       | obj::method       |

- 하나의 메서드만 호출하는 람다식은 '클래스이름::메서드이름' 또는 '참조변수::메서드이름' 으로 바꿀 수 있다.

 

**생성자의 메서드 참조**

생성자를 호출하는 람다식도 메서드 참조로 변환할 수 있다.

```java
Supplier<MyClass> s = () -> new MyClass();	//람다식
Supplier<MyClass> s = MyClass::new;	//메서드 참조
```

 

매개변수가 있는 생성자라면, 매개변수의 개수에 따라 알맞은 함수형 인터페이스를 사용하면 된다.

```java
Function<Integer, MyClass> f = (i) -> new MyClass(i);	//람다식
Function<Integer, MyClass> f2 = MyClass::new;	//메서드 참조

BiFunction<Integer, String, MyClass> bf = (i, s) -> new MyClass(i, s);	//람다식
BiFunction<Integer, String, MyClass> bf2 = MyClass::new;	//메서드 참조

//배열 생성
Function<Integer, int[]> f = x -> new int[x];	//람다식
Function<Integer, int[]> f2 = int[]::new;	//메서드 참조 
```

메서드 참조는 람다식을 마치 static변수처럼 다룰 수 있게 해준다.