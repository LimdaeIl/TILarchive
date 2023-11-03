# 컬렉션 프레임워크(Collections Framework)



**컬렉션 프레임워크(Collections Framwork)**

- 데이터 구조를 관리하고 조작하기 위한 자바 프로그래밍 언어의 API(응용 프로그래밍 인터페이스)
- 데이터를 저장, 검색, 정렬 및 필터링하기 위한 다양한 자료 구조와 알고리즘을 제공
- Java 컬렉션 프레임워크는 자바 표준 라이브러리의 일부로 포함되어 있으며, 데이터를 효율적으로 관리하는 데 도움



**컬렉션 프레임워크의 주요 특징**

1. **다양한 컬렉션 타입**
   컬렉션 프레임워크는 다양한 데이터 구조를 제공합니다. 일부는 **리스트(List), 세트(Set), 큐(Queue), 맵(Map)** 등이 포함됩니다.
2. **제네릭스(Generics)** 
   컬렉션 프레임워크는 제네릭 타입을 사용하여 타입 안정성을 제공합니다. 
   이로써 컴파일 시에 타입 안전성을 보장하며, **런타임 에러를 방지**합니다.
3. **반복자(Iterators)**
   컬렉션 프레임워크는 반복자를 통해 컬렉션의 요소를 순회할 수 있는 기능을 제공합니다.
4. **정렬과 검색**
   컬렉션 프레임워크는 요소를 정렬하고 검색하는 데 유용한 메서드와 알고리즘을 제공합니다.
5. **스레드 안전성**
   컬렉션 프레임워크에서는 스레드 안전한 컬렉션 클래스를 제공하여 **병렬 프로그래밍 환경에서 안전하게 사용**할 수 있습니다.
6. **컬렉션의 크기와 성능 관리**
   컬렉션 프레임워크는 컬렉션의 크기를 조회하거나 성능을 최적화하는 메서드를 제공합니다.

일반적으로 자바 프로그래밍에서 데이터를 다루거나 저장해야 할 때 컬렉션 프레임워크를 사용하며, 다양한 요구사항에 맞게 적절한 컬렉션 타입을 선택합니다. 이를 통해 코드의 가독성을 높이고 효율적인 데이터 관리를 할 수 있습니다.

 

**컬렉션 프레임워크의 핵심 인터페이스**

![img](https://blog.kakaocdn.net/dn/byJ78f/btriaP2T43k/k5tgQzoIC9igqUAVbOoGd1/img.png)

**컬렉션 프레임워크의 핵심 인터페이스는 `Collection`, `List`, `Set`, `Map` 네 가지 인터페이스**입니다. 
이들은 Java 프로그래밍 언어에서 컬렉션(데이터 그룹)을 다루는데 사용되는 중요한 인터페이스입니다.

1. **`Collection` 인터페이스**
   - 모든 컬렉션 타입의 기본 인터페이스
   - 요소들을 그룹화하고 다양한 컬렉션 타입을 처리하는 메소드를 정의

2. **`List` 인터페이스**
   - 순서가 있는 요소들의 컬렉션 
   - 중복 요소를 허용하고 인덱스를 통해 요소에 접근
   - List 인터페이스의 구현 클래스: `ArrayList, LinkedList, Stack, Vector` 등
   - 예) 대기자 명단

3. **Set 인터페이스**
   - 중복을 허용하지 않는 요소들의 컬렉션
   - 요소는 순서 없이 저장되며, 각 요소는 유일
   - Set 인터페이스의 구현 클래스: `HashSet, TreeSet` 등
   - 예) 양의 정수집합, 소수의 집합 

4. **`Map` 인터페이스**
   - 키-값(key-value) 쌍을 저장하는 컬렉션
   - 다 각 키는 고유해야 하며, 키를 통해 연관된 값을 검색이 가능 
   - Map 인터페이스의 구현 클래스: `HashMap, TreeMap, Hashtable` 등
   - 예) 우편번호, 지역번호(전화번호)

이렇게 네 가지 인터페이스는 다양한 데이터 구조를 다루는데 사용되며, Java 컬렉션 프레임워크의 핵심을 형성합니다. 이 중에서 **List와 Set 인터페이스는 모두 Collection 인터페이스를 상속**받지만, 구조상의 차이로 인해 **Map 인터페이스는 별도로 정의**됩니다. 따라서 List 인터페이스와 Set 인터페이스의 공통된 부분을 Collection 인터페이스에서 정의하고 있습니다.  자바 컬렉션 프레임워크의 주요 인터페이스에 대한 더 자세한 사항은 다음 페이지를 참고하면 됩니다. [Java Documentation : The Collections Framework =>](https://docs.oracle.com/javase/8/docs/technotes/guides/collections/index.html)



**주요 인터페이스 간의 상속 관계**

자바에서 컬렉션 프레임워크를 구성하고 있는 인터페이스 간의 상속 관계는 다음 그림과 같습니다. 
다음 그림에서 `<E>`나 `<K, V>`라는 것은 컬렉션 프레임워크를 구성하는 모든 클래스가 제네릭으로 표현되어 있음을 알려줍니다.

<img src="https://www.tcpschool.com/lectures/img_java_collection_interface_diagram.png" alt="컬렉션 프레임워크 인터페이스 다이어그램" style="zoom:80%;" />



**주요 인터페이스의 간략한 특징**

자바에서 컬렉션 프레임워크를 구성하고 있는 주요 인터페이스의 간략한 특징은 다음과 같습니다.

| 인터페이스  | 설명                                                         | 구현 클래스                                       |
| :---------- | :----------------------------------------------------------- | :------------------------------------------------ |
| `List<E>`   | 순서가 있는 데이터의 집합으로, 데이터의 중복을 허용함.       | Vector, ArrayList, <br />LinkedList, Stack, Queue |
| `Set<E>`    | 순서가 없는 데이터의 집합으로, 데이터의 중복을 허용하지 않음. | HashSet, TreeSet                                  |
| `Map<K, V>` | 키와 값의 한 쌍으로 이루어지는 데이터의 집합으로, 순서가 없음. <br />이때 키는 중복을 허용하지 않지만, 값은 중복될 수 있음. | HashMap, TreeMap, <br />Hashtable, Properties     |



**컬렉션 클래스(collection class)**

컬렉션 프레임워크에 속하는 인터페이스를 구현한 클래스를 컬렉션 클래스(collection class)라고 합니다. **컬렉션 프레임워크의 모든 컬렉션 클래스는 List와 Set, Map 인터페이스 중 하나의 인터페이스를 구현**하고 있습니다. 또한, 클래스 이름에도 구현한 인터페이스의 이름이 포함되므로 바로 구분할 수 있습니다. Vector나 Hashtable과 같은 컬렉션 클래스는 예전부터 사용해 왔으므로, 기존 코드와의 호환을 위해 아직도 남아 있습니다. 

하지만 기존에 사용하던 컬렉션 클래스를 사용하는 것보다는 **새로 추가된 ArrayList나 HashMap 클래스를 사용하는 것이 성능 면에서도 더 나은 결과**를 얻을 수 있습니다. 다음 예제는 ArrayList 클래스를 이용하여 리스트를 생성하고 조작하는 예제입니다.

```java
import java.util.*;

public class Collection01 {
    public static void main(String[] args) {
        // 리스트 생성
        ArrayList<String> arrList = new ArrayList<String>();
        // 리스트에 요소의 저장
        arrList.add("넷");
        arrList.add("둘");
        arrList.add("셋");
        arrList.add("하나");

        // 리스트 요소의 출력
        for(int i = 0; i < arrList.size(); i++) {
            System.out.println(arrList.get(i));
        }
    }
}
```

```java
넷
둘
셋
하나
```



## Collection 인터페이스

`List, Set`의 조상인 Collection 인터페이스에는 다음과 같은 메서드들이 정의되어 있습니다.

| 메서드                                                       | 설명                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| boolean add(Object o)<br />boolean addAll(Collection c)      | 지정된 객체(o) 또는 Collection(c)의 객체들을 Collection에 추가한다. |
| void clear()                                                 | Collection의 모든 객체를 삭제한다.                           |
| boolean contains(Object o)<br />boolean containsAll(Collection c) | 지정된 객체(o) 또는 Collection의 객체들이 Collection에 포함되어 있는지 확인한다. |
| boolean equals(Object o)                                     | 동일한 Collection인지 비교한다.                              |
| int hashCode()                                               | Collection의 hash code를 반환한다.                           |
| boolean isEmpty()                                            | Collection이 비어있는지 확인한다.                            |
| Iterator iterator()                                          | Collection의 Iterator를 얻어서 반환한다.                     |
| boolean reamove(Object o)                                    | 지정된 객체를 삭제한다.                                      |
| boolean removeAll(Collection c)                              | 지정된 Collection에 포함된 객체들을 삭제한다.                |
| boolean retainAll(Collection c)                              | 지정된 Collection에 포함된 객체만을 남기고 다른 객체들은 Collection에서 삭제한다. <br />이 작업으로 인해 Collection에 변화가 있으면 true, 그렇지 않으면 false를 반환한다. |
| Object[] toArray()                                           | Collection에 저장된 객체를 객체배열(Object[])로 반환한다.    |
| Object[] toArray(Object[] a)                                 | 지정된 배열에 Collection의 객체를 저장해서 반환한다.         |





## List인터페이스

- **순서가 있는 요소들의 컬렉션**으로 **중복 요소를 허용하고 인덱스를 통해 요소에 접근**합니다. List 인터페이스의 구현 클래스는 **`ArrayList, LinkedList, Stack, Vector`** 으로 예를 들어 **대기자 명단**이 있습니다. List의 상속계층도는 다음과 같습니다.

<img src="https://blog.kakaocdn.net/dn/b6vtWa/btricEAqJeG/72Lh1BcXjH9DfZ9E2UVoaK/img.png" alt="img" style="zoom:80%;" />

List 인터페이스에 정의된 메서드는 다음과 같습니다.

| 메서드                                                       | 설명                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| void add(int index, Object element) <br />boolean addAll(int index, Collection c) | 지정된 위치(index)에 객체(element) 또는 컬렉션에 포함된 객체들을 추가한다. |
| Object get(int index)                                        | 지정된 위치(index)에 있는 객체를 반환한다.                   |
| int indexOf(Object o)                                        | 지정된 객체의 위치(index)를 반환한다. <br />(List의 첫 번째 요소부터 순방향으로 찾는다.) |
| int lastIndexOf(Object o)                                    | 지정된 객체의 위치(index)를 반환한다. <br />(List의 마지막 요소부터 역방향으로 찾는다.) |
| ListIterator listIterator() <br />ListIterator listIterator(int index) | List의 객체에 접근할 수 있는 ListIterator를 반환한다.        |
| Object remove(int index)                                     | 지정된 위치(index)에 있는 객체를 삭제하고 삭제된 객체를 반환한다. |
| Object set(int index, Object element)                        | 지정된 위치(index)에 객체(element)를 저장한다.               |
| void sort(Comparator c)                                      | 지정된 비교자(comparator)로 List를 정렬한다.                 |
| List subList(int fromIndex, int toIndex)                     | 지정된 범위(fromIndex부터 toIndex)에 있는 객체를 반환한다.   |



## Set 인터페이스

**중복을 허용하지 않는 요소들의 컬렉션**으로 **요소는 순서 없이 저장되며, 각 요소는 유일**합니다. Set 인터페이스의 구현 클래스는 **`HashSet, TreeSet, SortedSet`** 으로 예를 들어 **양의 정수집합, 소수의 집합**이 있습니다. Set의 상속계층도는 다음과 같습니다.

![img](https://blog.kakaocdn.net/dn/Ibwok/btrigCalZ8A/HOCQRmfj92GyTpKKMJCNvk/img.png)



## Map 인터페이스

- **키-값(key-value) 쌍을 저장하는 컬렉션**으로 다 **각 키는 고유해야 하며, 키를 통해 연관된 값을 검색이 가능**합니다.  Map 인터페이스의 구현 클래스는 **`HashMap, TreeMap, Hashtable, LinkedHashMap, SortedMap`** 이며 예를 들어 **우편번호, 지역번호(전화번호)** 입니다. Map의 상속계층도는 다음과 같습니다.

<img src="https://blog.kakaocdn.net/dn/pkbW3/btrikMwc9cf/GqMwJsposYISC3pm0lOaKk/img.png" alt="img" style="zoom:80%;" />

Map인터페이스에 정의된 메서드는 다음과 같습니다. Map인터페이스에서 값(value)은 중복을 허용하기 때문에 Collection 타입으로 반환하고, 키(Key)는 중복을 허용하지 않기 때문에 Set 타입으로 반환합니다.

| 메서드                               | 설명                                                         |
| ------------------------------------ | ------------------------------------------------------------ |
| void clear()                         | Map의 모든 객체를 삭제한다.                                  |
| boolean containsKey(Object key)      | 지정된 key객체와 일치하는 Map의 key객체가 있는지 확인한다.   |
| boolean containsValue(Object value)  | 지정된 value객체와 일치하는 Map의 value객체가 있는지 확인한다. |
| Set entrySet()                       | Map에 저장되어 있는 key-value쌍을 Map.Entry타입의 객체로 저장한 Set으로 반환한다. |
| boolean equals(Object o)             | 동일한 Map인지 비교한다.                                     |
| Object get(Object key)               | 지정한 key객체에 대응하는 value객체를 찾아서 반환한다.       |
| int hashCode()                       | 해시코드를 반환한다.                                         |
| boolean isEmpty()                    | map이 비어있는지 확인한다.                                   |
| Set keySet()                         | Map에 저장된 모든 key객체를 반환한다.                        |
| Object put(Object key, object value) | Map에 value객체를 key객체에 연결(mapping)하여 저장한다.      |
| void putAll(Map t)                   | 지정된 Map의 모든 key-value쌍을 추가한다.                    |
| Object remove(Object key)            | 지정한 key객체와 일치하는 key-value객체를 삭제한다.          |
| int size()                           | Map에 저장된 key-value쌍의 개수를 반환한다.                  |
| Collection values()                  | Map에 저장된 모든 value객체를 반환한다.                      |



**Map.Entry인터페이스**

Map.Entry인터페이스는 Map인터페이스의 내부 인터페이스(inner interface)입니다. 내부 인터페이스는 인터페이스 안에 인터페이스를 정의한 것을 의미합니다. Map에 저장되는 key-value쌍을 다루기 위해 내부적으로 Entry인터페이스를 다음과 같이 정의되어 있습니다.

```java
public interface Map {
	interface Entry {
    	Obejct getKey();
        Object getValue();
        Object setValue(Object value);
        boolean equals(Object o);
        int hashCode();
    }
}
```

Map.Entry 인터페이스의 메서드입니다.

| 메서드                        | 설명                                      |
| ----------------------------- | ----------------------------------------- |
| boolean equals(Object o)      | 동일한 Entry인지 비교한다.                |
| Object getKey()               | Entry의 key객체를 반환한다.               |
| Object getValue()             | Entry의 value객체를 반환한다.             |
| int hashCode()                | Entry의 해시코드를 반환한다.              |
| object setValue(Object value) | Entry의 value객체를 지정된 객체로 바꾼다. |



