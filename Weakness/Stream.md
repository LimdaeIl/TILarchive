# 스트림(Stream)



## 1. 스트림이란?

Collection, Iterator와 같은 인터페이스를 이용해서 컬렉션을 다루는 방식을 표준화하기는 했지만, 각 컬렉션 클래스에는 같은 기능의 메서드들이 중복정의 되어 있습니다. 예를 들어 List를 정렬할 때는 Collection.sort()를 사용해야하고, 배열을 정렬할 때는 Arrays.sort()를 사용해야 합니다. 이러한 문제점을 해결하기 위해 스트림(Stream)이 등장했습니다.

스트림은 데이터 소스를 추상화하고, 데이터를 다루는데 자주 사용되는 메서드들을 정의해 놓았습니다. 데이터 소스를 추상화했다는 의미는 데이터 소스가 무엇이던 간에 동일한 방식으로 다룰 수 있게 되었다는 것과 코드의 재사용성이 높아진다는 것을 의미합니다. 

문자열 배열을 저장하는 List가 다음과 같이 있습니다.

```java
String[] strArr = {"aaa", "bbb", "ccc"};
List<String> strList = Arrays.asList(strArr);
```



이 두 데이터 소스를 기반으로 하는 스트림은 다음과 같이 작성합니다.

```java
Stream<String> strStream1 = strList.stream(); // 스트림을 생성
Stream<String> strStream2 = Arrays.stream(strArr); // 스트림을 생성
```



이 두 스트림으로 데이터 소스의 데이터를 읽어서 정렬하고 화면에 출력하는 방법은 다음과 같습니다. 
**데이터 소스가 정렬되는 것은 아니라는 것에 유의해야 합니다.**

```java
strStream1.sorted().forEach(System.out::println);
strStream2.sorted().forEach(System.out::println);
```



두 스트림의 데이터 소스는 서로 다르지만, 정렬하고 출력하는 방법은 완전히 동일합니다. 예전에는 아래와 같이 코드를 작성했다는 점에서, **스트림을 사용한 코드가 간결하고 이해하기 쉬우며 재사용성도 높다는 것을 알 수 있습니다.**

```java
Arrays.sort(strArr);
Collection.sort(strList);

for(String str : strArr)
    System.out.println(str);

for(String str : strList)
    System.out.println(str);
```



## 2. 스트림의 특징

1. **스트림은 데이터 소스를 변경하지 않는다.**

   스트림은 데이터 소스로부터 데이터를 읽기만할 뿐, 데이터 소스를 변경하지 안흔다는 차이가 있습니다. 
   필요하다면, 정렬된 결과를 컬렉션이나 배열에 담아서 반환할 수도 있습니다.

   ```java
   // 정렬된 결과를 새로운 List에 담아서 반환합니다.
   List<String> sortedList = strStream2.sorted().collect(Collectors.toList());
   ```



2. **스트림은 일회용이다.**

   스트림은 Iterator처럼 일회용입니다. Iterator로 컬렉션의 요소를 모두 읽고 나면 다시 사용할 수 없는 것처럼, 스트림도 한번 사용하면 닫혀서 다시 사용할 수 없습니다. 필요하다면 스트림을 다시 생성해야 합니다.

   ```java
   strStream1.sorted().forEach(System.out::println);
   int numOfStr = strStream1.count(); // 에러. 스트림이 이미 닫힌 상태
   ```



3. **스트림은 작업을 내부 반복으로 처리한다.**

   스트림을 이용한 작업이 간결할 수 있는 비결중의 하나가 바로 '내부 반복'입니다. 내부 반복이라는 것은 **반복문을 메서드의 내부에 숨길 수 있다는 것을 의미**합니다. forEach()는 스트림에 정의된 메서드 중의 하나로 매개변수에 대입된 람다식을 데이터 소스의 모든 요소에 적용합니다.

   ```java
   // List 향상된 for문 출력
   for(String str : strList)
       System.out.println(str);
   
   // stream forEach문 출력
   stream.forEach(System.out::println);
   ```

   참고로, 메서드 참조 `System.out::println`을 람다식으로 표현하면 `(str) -> System.out.println(Str)`과 동일합니다.

**forEach()는 메서드 안으로 for문을 넣은 것**입니다. 수행할 작업은 매개변수로 받습니다.

```java
void forEach(Consumer<? super T> action) {
    Objects.requireNonNull(action); // 매개변수의 널 체크
    
    for(T t : src) { action.accept(T); } // 내부 반복
}
```



## 3. 스트림의 연산

스트림이 제공하는 다양한 연산을 이용해서 복잡한 작업들을 간단히 처리할 수 있습니다. 마치 데이터베이스에 SELECT 문으로 질의(쿼리, Query)하는 것과 동일한 느낌입니다. 스트림에 정의된 메서드 중에서 **데이터 소스를 다루는 작업을 수행하는 것을 연산(Operation)**이라고 합니다.

스트림이 제공하는 연산은 중간 연산과 최종 연산으로 분류할 수 있는데, 중간 연산은 연산결과를 스트림으로 반환하기 때문에 중간 연산을 연속해서 연결할 수 있습니다. 반면에 최종 연산은 스트림의 요소를 소모하면서 연산을 수행하므로 단 한 번만 연산이 가능합니다.

> **중간 연산**: 연산 결과가 스트림인 연산. 스트림에 연속해서 중간 연산할 수 있음
>
> **최종 연산**: 연산 결과가 스트림이 아닌 연산. 스트림의 요소를 소모하므로 단 한번만 가능

<img src="https://blog.kakaocdn.net/dn/c7SkXC/btrsiF9bOTp/Kw9XS9ArLxfg38vQWPcmc1/img.png" alt="img" style="zoom:23%;" />



모든 중간 연산의 결과는 스트림이지만, 연산 전의 스트림과 동일한 것은 아닙니다. 
위의 문자과 달리 모든 스트림 연산을 나누어 코드로 작성하면 다음과 같습니다. 각 연산의 반환타입을 중점으로 살펴봅니다.

```java
// 모든 스트림 연산을 나누어 쓸 경우
String[] strArr = { "dd", "aaa", "CC", "cc", "b" };
Stream<String> stream = Stream.of(strArr);	//문자열 배열이 소스인 스트림
Stream<String> filteredStream = stream.filter();	//걸러내기(중간 연산)
Stream<String> distinctedStream = stream.distinct();	//중복제거(중간 연산)
Stream<String> sortedStream = stream.sort();	//정렬(중간 연산)
Stream<String> limitedStream = stream.limit(5);	//스트림 자르기(중간 연산)
int total = stream.count();	//요소 개수 세기(최종연산)
```



Stream에 정의된 중간 연산과 최종 연산을 정리하면 다음과 같습니다. 
각 중간 연산에 대해 지금은 어떤 것들이 있는지 가볍게 확인하고 넘어갑니다.

**스트림의 중간 연산 목록**

| 중간 연산                                                    | 설명                      |
| ------------------------------------------------------------ | ------------------------- |
| `Stream<T> distinct()`                                       | 중복을 제거               |
| `Stream<T> filter(Predicate<T> predicate)`                   | 조건에 안 맞는 요소 제외  |
| `Stream<T> limit(long maxSize)`                              | 스트림의 일부를 잘라낸다. |
| `Stream<T> skip(long n)`                                     | 스트림의 일부를 건너뛴다. |
| `Stream<T> peek(Consumer<T> action)`                         | 스트림의 요소에 작업수행  |
| `Stream<T> sorted() Stream<T> sorted(Comparator<T> comparator)` | 스트림의 요소를 정렬한다. |
| `Stream<R> map(Function<T, R> mapper)` <br />`DoubleStream mapToDouble(ToDoubleFunction<T> mapper)` <br />`IntStream mapToInt(ToIntFunction<T> mapper)`  <br />`Stream<R> flatMap(Function<T, Stream<R>> mapper)`<br />`DoubleStream flatMapToDouble(Function<T, DoubleStream> m)`<br />`IntStream flatMapToInt(Function<T, IntStream> m)` <br />`LongStream flatMapToLong(Function<T, LongStream> m)` | 스트림의 요소를 변환한다. |

 

**스트림의 최종 연산 목록**

| 최종 연산                                                    | 설명                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| `void forEach(Consumer<? super T> action)`<br /> `void forEachOrdered(Consumer<? super T> action)` | 각 요소에 지정된 작업 수행                                   |
| `long count()`                                               | 스트림의 요소의 개수 반환                                    |
| `Optional<T> max(Comparator<? super T> comparator)`<br /> `Optional<T> min(Comparator<? super T> comparator)` | 스트림의 최대값/최소값을 반환                                |
| `Optional<T> findAny()` // 아무거나 하나<br />`Optional<T> findFirst()` // 첫 번째 요소 | 스트림의 요소 하나를 반환                                    |
| `boolean allMatch(Predicate<T> p)` // 모두 만족하는지<br />`boolean anyMatch(Predicate<T> p)` // 하나라도 만족하는지<br /> `boolean noneMatch(Predicate<> p)` //모두 만족하지 않는지 | 주어진 조건을 모든 요소가 만족시키는지, <br />만족시키지 않는지 확인 |
| `Object[] toArray A[] toArray(IntFunction<A[]> generator)`   | 스트림의 모든 요소를 배열로 반환                             |
| `Optional<T> reduce(BinaryOperator<T> accumulator)`<br />`T reduce(T identity, BinaryOperator<T> accumulator)`<br />`U reduce(U identity, BiFunction<U, T, U> accumulator,`<br />`BinaryOperator<U> combiner)` | 스트림의 요소를 하나씩 줄여가면서(리듀싱) 계산한다.          |
| `R collect(Collector<T, A, R> collector)`<br />`R collect(Supplier<R> supplier, BiConsumer<R, T>`<br /> `accumulator, BiConsumer<R, R> combiner)` | 스트림의 요소를 수집한다. <br />주로 요소를 그룹화하거나,<br />분할한 결과를 컬렉션에 담아 반환하는데 사용된다. |

중간 연산은 `map(), flatMap()`, 최종 연산은 `reduce(), collect()`가 핵심입니다. Optional은 일종의 래퍼 클래스(wrapper class)로 내부에 하나의 객체를 저장할 수 있습니다.