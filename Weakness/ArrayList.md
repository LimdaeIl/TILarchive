# ArrayList

ArrayList는 List 인터페이스를 구현하기 때문에 **중복 요소를 허용하고 인덱스를 통해 요소에 접근**합니다. ArrayList는 Vector를 개선한 클래스로 Vector와 구현원리가 기능적인 측면에서 동일하다고 할 수 있습니다. 이전 코드와 호환성 문제가 없다면 ArrayList 사용을 권장합니다.

ArrayList는 **Object 배열을 이용해서 데이터를 순차적으로 저장**합니다. 배열에 더 이상 저장할 공간이 없다면 보다 큰 새로운 배열을 생성해서 기존의 배열에 저장된 내용을 새로운 배열로 복사한 다음에 저장됩니다. 인덱스는 0부터 시작합니다. 다음 코드는 ArrayList의 소스코드의 일부분입니다.

```java
public class ArrayList extends AbstractList 
		implements List, RandomAccess, Cloneable, java.io.Serializable {
	// Object 배열을 멤버변수로 선언하고 있다.
    // 선언된 배열의 타입이 모든 객체의 최고조상인 Object이기 때문에
    // 모든 종류의 객체를 담을 수 있다.
	transient Object[] elementData;	//Object 배열
}
```

transient는 직렬화(Serialization)와 관련된 제어자입니다. 직렬화에 대해 배우려먼 멀었으므로 넘어갑니다.



**ArrayList에서 제공하는 메서드**

| 메서드                                                | 설명                                                         |
| ----------------------------------------------------- | ------------------------------------------------------------ |
| ArrayList()                                           | 크기가 0인 ArrayList를 생성                                  |
| ArrayList(Collection c)                               | 주어진 컬렉션이 저장된 ArrayList를 생성                      |
| ArrayList(int initialCapacity)                        | 지정된 초기용량을 갖는 ArrayList를 생성                      |
| ArrayList(int initialCapacity, int capacityIncrement) | 지정된 초기용량과 용량의 증분을 갖는 ArrayList를 생성        |
| boolean add(Object o)                                 | ArrayList의 마지막 객체를 추가. 성공하면 true                |
| void add(int index, Object element)                   | 지정된 위치(index)에 객체를 저장                             |
| boolean addAll(Collection c)                          | 주어진 컬렉션의 모든 객체를 저장한다.                        |
| boolean addAll(int index, Collection c)               | 지정된 위치부터 주어진 컬렉션의 모든 객체를 저장한다.        |
| void clear()                                          | ArrayList를 완전히 비운다.                                   |
| Object clone()                                        | ArrayList를 복제한다.                                        |
| boolean contains(Object o)                            | 지정된 객체(o)가 ArrayList에 포함되어 있는지 확인            |
| void ensureCapacity(int minCapacity)                  | ArrayList의 용량이 최소한 minCapacity가 되도록 한다.         |
| Object get(int index)                                 | 지정된 위치(index)에 저장된 객체를 반환한다.                 |
| int indexOf(Object o)                                 | 지정된 객체가 저장된 위치를 찾아 반환한다.                   |
| boolean isEmpty()                                     | ArrayList가 비어있는지 확인한다.                             |
| Iterator iterator()                                   | ArrayList의 iterator객체를 반환                              |
| int lastIndexOf(Object o)                             | 객체(o)가 저장된 위치를 끝부터 역방향으로 검색해서 반환      |
| ListIterator listIterator()                           | ArrayList의 ListIterator를 반환                              |
| ListIterator listIterator(int index)                  | ArrayList의 지정된 위치부터 시작하는 ListIterator를 반환     |
| Object remove(int index)                              | 지정된 위치(index)에 있는 객체를 제거한다.                   |
| boolean remove(Object o)                              | 지정한 객체를 제거한다. (성공하면 true, 실패하면 false)      |
| boolean removeAll(Collection c)                       | 지정한 컬렉션에 저장된 것과 동일한 객체들을 ArrayList에서 제거한다. |
| boolean retainAll(Collection c)                       | ArrayList에 저장된 객체 중에서 주어진 컬렉션과 공통된 것들만을 남기고 나머지는 삭제한다. |
| Object set(int index, Object element)                 | 주어진 객체(element)를 지정된 위치(index)에 저장한다.        |
| int size()                                            | ArrayList에 저장된 객체의 개수를 반환한다.                   |
| void sort(Comparator c)                               | 지정된 정렬기준(c)으로 ArrayList를 정렬                      |
| List subList(int fromIndex, int toIndex)              | fromIndex부터 toIndex사이에 저장된 객체를 반환한다.          |
| Object[] toArray()                                    | ArrayList에 저장된 모든 객체들을 객체배열로 반환한다.        |
| Object[] toArray(Object[] a)                          | ArrayList에 저장된 모든 객체들을 객체배열 a에 담아 반환한다. |
| void trimToSize()                                     | 용량을 크기에 맞게 줄인다.(빈 공간을 없앤다.)                |







## 1. ArrayList 특징

<img src="https://blog.kakaocdn.net/dn/c0MSfB/btrP8ZxeJng/KDkKZ7Shkmd1IbIznKRVk0/img.png" alt="java-arraylist" style="zoom:60%;" />

- 연속적인 데이터의 리스트 (데이터는 연속적으로 리스트에 들어있어야 하며 중간에 빈공간이 있으면 안된다)
- ArrayList 클래스는 내부적으로 `Object[]` 배열을 이용하여 요소를 저장
- 배열을 이용하기 때문에 **인덱스를 이용해 요소에 빠르게 접근**할 수 있다.
- 크기가 고정되어있는 배열과 달리 데이터 적재량에 따라 **가변적으로 공간을 늘리거나 줄인다.**
- 그러나 배열 공간이 꽉 찰때 마다 **배열을 copy하는 방식으로 늘리므로 이 과정에서 지연이 발생**
- 데이터를 리스트 중간에 삽입/삭제 할 경우, 중간에 빈 공간이 생기지 않도록 **요소들의 위치를 앞뒤로 자동으로 이동**시키기 때문에 **삽입/삭제 동작은 느리다.**
- 따라서 조회를 많이 하는 경우에 사용하는 것이 좋다



## 2. ArrayList vs 배열

<img src="https://blog.kakaocdn.net/dn/KKbBY/btrP3AKyqeW/OKaeVLgNIVavvQJvCzatz0/img.png" alt="java-arraylist" style="zoom:50%;" />

### 2.1 배열 장단점

- 처음 선언한 배열의 **크기(길이)는 변경할 수 없다.** 이를 정적 할당(static allocation)이라고 한다.
- 데이터 크기가 정해져있을 경우 메모리 관리가 편하다.
- 메모리에 연속적으로 나열되어 할당하기 때문에 index를 통한 색인(접근)속도가 빠르다.
- index에 위치한 하나의 데이터(element)를 삭제하더라도 해당 index에는 빈공간으로 계속 남는다. 
- 배열의 크기를 변경할 수 없기 때문에, 처음에 너무 큰 크기로 설정해주었을 경우 메모리 낭비가 될수 있고, 반대로 너무 작은 크기로 설정해주었을 경우 공간이 부족해지는 경우가 발생 할 수 있다.

```java
Number[] r = new Number[5]; // 정적 할당(static allocation)
r[0] = 10;
r[1] = 20;
r[2] = 30;
r[3] = 40;
r[4] = 50;

r[3] = null; // 배열은 삭제 메서드가 없어서 null을 이용해 객체 요소를 삭제
System.out.println(Arrays.toString(r)); // [10, 20, 30, null, 50]
```

<img src="https://blog.kakaocdn.net/dn/CvWX5/btrP3jbpLAX/XlxgcXX0rkn2CzyaBueJrk/img.png" alt="java-array" style="zoom:67%;" />



### 2.2 ArrayList 장단점

- 리스트의 길이가 **가변적이다.** 이를 **동적 할당(dynamic allocation)**이라고 한다.
- 배열과 달리 메모리에 연속적으로 나열되어있지 않고 주소로 연결되어있는 형태이기 때문에 index를 통한 색인(접근)속도가 배열보다는 느리다.
- 데이터(element) 사이에 빈 공간을 허용하지 않는다.
- 객체로 데이터를 다루기 때문에 적은양의 데이터만 쓸 경우 배열에 비해 차지하는 메모리가 커진다.

>  Tip
>
> primitive type인 int 타입일 경우 크기는 4Byte 이다.
> 반면에 Wrapper 클래스인 Integer는 32bit JVM 환경에서는, 객체의 헤더(8Byte), 원시 필드(4Byte), 패딩(4Byte)으로 '최소 16Byte 크기를 차지한다. 또한 이러한 객체 데이터들을 다시 주소로 연결하기 때문에 16 + α 가 된다.

```java
List<Number> l = new ArrayList<>(); // 동적 할당(dynamic allocation)
l.add(10);
l.add(20);
l.add(30);
l.add(40);
l.add(50);

l.remove(3);
System.out.println(l); // [10, 20, 30, 50]
```

<img src="https://blog.kakaocdn.net/dn/J7w7r/btrP4uJLgHb/yExyu8YACaZz8uDIrTkYz1/img.png" alt="java-arraylist" style="zoom:67%;" />



## 3. ArrayList 객체 생성

ArrayList를 사용하기 위해선 상단에 패키지를 명시하여 가져와야 한다.

```java
import java.util.ArrayList;
```

| **메서드**                     | **설 명**                               |
| ------------------------------ | --------------------------------------- |
| ArrayList()                    | 크기가 10인 ArrayList를 생성            |
| ArrayList(Collection c)        | 주어진 컬렉션이 저장된 ArrayList를 생성 |
| ArrayList(int initialCapacity) | 지정된 초기용량을 갖는 ArrayList를 생성 |

```java
// 타입설정 Integer 객체만 적재가능
ArrayList<Integer> members = new ArrayList<>();

// 초기 용량(capacity)지정
ArrayList<Integer> num3 = new ArrayList<>(10);

// 배열을 넣어 생성
ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1,2,3));

// 다른 컬렉션으로부터 그대로 요소를 받아와 생성 (ArrayList를 인자로 받는 API를 사용하기 위해서 Collection 타입 변환이 필요할 때 많이 사용)
ArrayList<Integer> list3 = new ArrayList<>(list2);
```

ArrayList 생성 문법을 보면 꺾쇠 괄호 `<>` 기호를 이용해 타입을 지정함을 볼 수 있다. 저 **꺾쇠 괄호**가 바로 **제네릭**이다. 만일 꺾쇠 괄호 안에 String 타입명을 기재하면 ArrayList 클래스 자료형의 타입은 String 타입으로 지정되어 문자열 데이터만 리스트에 적재할 수 있게 된다.

아래 그림과 같이 배열과 리스트의 선언문 형태를 비교해보면 이해하기 쉬울 것이다. 선언하는 키워드나 문법 순서가 다를뿐, 결국 자료형명을 선언하고 자료형의 타입을 지정한다는 점은 같다고 볼 수 있다.

<img src="https://blog.kakaocdn.net/dn/ElmoW/btrP9Gxa4Xc/fIhqUKHuhFw3FZ8b9Q42B0/img.png" alt="java-arraylist" style="zoom:67%;" />



### 3.1 ArrayList 요소 추가

ArrayList에 요소를 추가할때 제네릭 타입 파라미터로 명시된 타입의 데이터만 추가가 가능하다.

그리고 ArrayList를 처음 접할때 용량(capacity)과 크기(size)에 대한 용어 차이가 모호할 수 있는데, **capacity는 리스트의 공간 용량**라고 보면되고, **size는 리스트 안에 들어있는 요소들의 총 갯수**라고 이해하면 된다.

| **메서드**                | **설 명**                                                    |
| ------------------------- | ------------------------------------------------------------ |
| boolean add(Object obj)   | ArrayList의 마지막에 객체를 추가한다. 추가에 성공하면 true를 반환 |
| void addAll(Collection c) | 주어진 컬렉션의 모든 객체를 저장한다.(마지막 index의 뒤로 붙임) |

```java
ArrayList<String> list = new ArrayList<>(10); // 용량(capacity)를 10으로 설정

list.add("A");
list.add("B");
list.add("C");
list.add("D");
list.add("E");
list.add("F");

list.size(); // 크기(size)는 6 (들어있는 요소의 총 개수)
```

<img src="https://blog.kakaocdn.net/dn/ekVEEM/btrPPvXV8Rw/BN6M8oL8icJ7Yi16ijoEO0/img.png" alt="java-arraylist" style="zoom:67%;" />

또한 addAll() 메서드를 통해 일일이 요소를 추가하는게 아닌 컬렉션 자체를 그대로 받아와 추가도 가능하다.

```java
ArrayList<String> list1 = new ArrayList<>();
list1.add("1");
list1.add("2");

ArrayList<String> list2 = new ArrayList<>();
list2.add("3");
list2.add("4");

list1.addAll(list2); // list1에 list2의 내용을 추가한다.
 
System.out.println(list1); // [1, 2, 3, 4]
```



### 3.2 ArrayList 요소 삽입

리스트에 데이터를 추가하되 추가되는 위치를 지정하여 삽입할 수 있다.

이때 지정된 위치에 요소를 넣을수 있게 기존의 요소들이 한칸씩 뒤로 이동되면서 빈공간을 만들어준다. 유의할 점은 한칸씩 데이터들을 **뒤로 밀어내는 동작은 꽤나 비용이 크기 때문에** ArrayList의 사이즈가 커질 수록 비효율적이 된다. (이는 ArrayList 컬렉션의 단점이기도 하다.)

| **메서드**                           | **설 명**                                                    |
| ------------------------------------ | ------------------------------------------------------------ |
| void add(int index, Object element)  | 지정된 위치(index)에 객체를 저장한다. <br />자리에 있던 기존의 데이터는 뒤로 밀려나기만 할 뿐 삭제되지 않는다. |
| void addAll(int index, Collection c) | 지정한 위치부터 주어진 컬렉션의 데이터를 저장한다. <br />자리에 있던 기존의 데이터는 뒤로 밀려나기만 할 뿐 삭제되지 않는다. |

```java
ArrayList<String> list = new ArrayList<>(8); 

list.add("1");
list.add("2");
list.add("3");
list.add("4");
list.add("5");

// 3번째 인덱스 자리에 요소 삽입
list.add(3, "A");

System.out.println(list); // [1, 2, 3, A, 4, 5]
```

<img src="https://blog.kakaocdn.net/dn/dnL0fn/btrP3Ua5Zh5/CwABBrZOX35N4aXRrFtiLK/img.png" alt="java-arraylist" style="zoom:67%;" />



### 3.3 ArrayList 삽입 주의점

위치를 지정하여 삽입할때 인덱스가 리스트의 capacity를 넘지 않도록 조절이 필요하다.

만일 다음과 같이 썡뚱맞게 100번째 인덱스 위치에 요소를 넣으려고 한다면 IndexOutOfBoundsException 예외가 발생하게 된다.

```java
list.add(100, "OMG");Copy
```

당연히 리스트의 용량 보다 넘어선 인덱스로 삽입하게 되면 에러가 나는 것 쯤은 쉽게 이해가 가능하다.

하지만 리스트의 용량에 맞춰서도 적재된 요소의 마지막 위치(size 값)에서 벗어나도 IndexOutOfBoundsException이 발생한다.

예를 들어 다음 그림과 같이 리스트 용량에 맞춰서 인덱스를 지정했지만, 추가되는 위치를 요소 5 바로 다음이 아닌 건너뛰어 추가되는 상황을 들 수 있다.

<img src="https://blog.kakaocdn.net/dn/bTd2F3/btrP4tqIw8B/7gV21RoCGbbWmXuslmuFyk/img.png" alt="java-arraylist" style="zoom:67%;" />

용량에 맞춰 삽입하는데 무슨 문제가 있느냐 싶겠지만, 위에서 ArrayList의 특징에 대해 다뤘듯이 ArrayList는 데이터가 연속된 자료구조 라는 규칙이 정해져 있기 때문에 이러한 행위는 불가능하다.

즉, 리스트의 **물리적인 공간의 크기(capacity)**는 8이므로 충분하더라도 **논리적인 공간(size)**은 5이기 때문에 7번째 공간에 값 삽입은 논리적인 공간(size)을 넘을 수 없어 불가능다는 해석이 된다. 따라서 논리적인 공간을 넘어 접근할 경우 IndexOutOfBoundsException이 발생하는 것이다.





## 4.ArrayList 요소 삭제

요소의 삭제 역시 중간에 위치한 요소를 제거할경우, 나머지 요소들이 빈 공간을 채우려 앞쪽으로 이동되게 된다.

| **메서드**                      | **설 명**                                                    |
| ------------------------------- | ------------------------------------------------------------ |
| Object remove(int index)        | 지정된 위치(index)에 있는 객체를 제거한다.                   |
| boolean remove(Object obj)      | 지정된 객체를 제거한다.(성공하면 true)                       |
| boolean removeAll(Collection c) | 지정한 컬렉션에 저장된 것과 동일한 객체들을 ArrayList에서 제거한다. |
| void clear()                    | ArrayList를 완전히 비운다.                                   |
| boolean retainAll(Collection c) | ArrayList에 저장된 객체 중에서 주어진 컬렉션과 공통된 것들만 남기고 제거한다.<br /> (removeAll 반대 버전) |

```java
ArrayList<String> list = new ArrayList<>(8); 

list.add("1");
list.add("2");
list.add("3");
list.add("4");
list.add("5");

// 2번째 인덱스 자리의 요소 삭제
list.remove(2);

System.out.println(list); // [1, 2, 4, 5]
```

<img src="https://blog.kakaocdn.net/dn/davg4x/btrP35qfJyh/WRSmkczKWesFjQ0wk4c2yk/img.png" alt="java-arraylist" style="zoom:67%;" />

 

만일 모든 값을 싹 제거하려면, 일일히 반복문을 돌려 제거하지말고 간단히 clear() 메소드를 사용하면 된다.

```java
ArrayList<String> list1 = new ArrayList<>();
list1.add("1");
list1.add("2");
list1.add("3");
 
list1.clear(); // list1의 데이터를 모두 비운다.

System.out.println(list1); // []
```

```java
/* list2에서 list1과 공통되는 요소들을 찾아서 삭제하기 */
for(int i = list2.size() - 1; i >= 0; i--) {
	if (list1.contains(list2.get(i)))
    	list2.remove(i);
}
/*
	list2의 각 요소를 접근하기 위해 get(int index)메서드와 for문 사용
    for문의 변수 i를 list2.size()-1부터 감소시키면서 거꾸로 반복시켰다.
    만일 변수 i를 증가시켜가면서 삭제하면, 한 요소가 삭제될 때마다 빈 공간을 채우기 위해
    나머지 요소들이 자리이동을 하기 때문에 올바른 결과를 얻을 수 없다.
*/
```







## 5.  ArrayList 요소 검색

| **메서드**                   | **설 명**                                                    |
| ---------------------------- | ------------------------------------------------------------ |
| boolean isEmpty()            | ArrayList가 비어있는지 확인한다.                             |
| boolean contains(Object obj) | 지정된 객체(obj)가 ArrayList에 포함되어 있는지 확인한다.     |
| int indexOf(Object obj)      | 지정된 객체(obj)가 저장된 위치를 찾아 반환한다.              |
| int lastIndexOf(Object obj)  | 지정된 객체(obj)가 저장된 위치를 뒤에서 부터 역방향으로 찾아 반환한다. |

```java
ArrayList<String> list1 = new ArrayList<>();
list1.add("A");
list1.add("B");
list1.add("C");
list1.add("A");

// list에 A가 있는지 검색 : true
list1.contains("A"); 

// list에 D가 있는지 순차적으로 검색하고 index를 반환 (만일 없으면 -1)
list1.indexOf("A"); // 0

// list에 D가 있는지 역순으로 검색하고 index를 반환 (만일 없으면 -1)
list1.lastIndexOf("A"); // 3
```



## 6.  ArrayList 요소 얻기

| **메서드**                               | **설 명**                                           |
| ---------------------------------------- | --------------------------------------------------- |
| Object get(in index)                     | 지정된 위치(index)에 저장된 객체를 반환한다.        |
| List subList(int fromIndex, int toIndex) | fromIndex부터 toIndex사이에 저장된 객체를 반환한다. |

개별 단일 요소를 얻고자 하면 get 메서드로 얻어올 수 있다.

```java
ArrayList<String> list = new ArrayList<>(18); 

list.add("1");
list.add("2");
list.add("3");
list.add("4");
list.add("5");

list.get(0); // "1"
list.get(3); // "4"
```

만약 **범위** 요소를 얻고자 하면 List subList(int fromIndex, int toIndex) 메서드로 가져올 수 있다. 이 메서드는 **fromIndex 부터 toIndex - 1 사이**에 저장된 객체를 반환한다.

```java
ArrayList<String> list = new ArrayList<>(18); 

list.add("P");
list.add("r");
list.add("o");
list.add("g");
list.add("r");
list.add("a");
list.add("m");

// list[0] ~ list[6] 범위 반환
list.subList(0, 7); // [P, r, o, g, r, a, m]

// list[3] ~ list[6] 범위 반환
list.subList(3, 7); // [g, r, a, m]

// list[3] ~ list[5] 범위 반환
list.subList(3, 6); // [g, r, a]
```

<img src="https://blog.kakaocdn.net/dn/dbbZGK/btrP0HjGCnJ/WFAnJEunOSRDWWHAWpIor1/img.png" alt="java-sublist" style="zoom:67%;" />



## 7. ArrayList 요소 변경

| **메서드**                        | **설 명**                                                    |
| --------------------------------- | ------------------------------------------------------------ |
| Object set(int index, Object obj) | 주어진 객체(obj)를 지정한 위치(index)에 저장한다. <br />자리에 있던 기존의 데이터는 삭제되고 새로운 데이터가 대체되어 들어간다. |

```java
ArrayList<String> list1 = new ArrayList<>();

list1.add("list1");
list1.add("list1");
list1.add("list1");
 
// index 1번의 데이터를 문자열 "setData"로 변경한다.
list1.set(1, "setData"); 

System.out.println(list1); // [list1, setData, list1]
```



## 8. ArrayList 용량 확장

ArrayList는 생성할때 용량이 정할 수 있지만, 데이터가 추가되면서 자동으로 용량(Capacity)을 늘려준다.

만일 정해진 용량보다 넘게 데이터를 적재할 경우, 자체적으로 내부 배열을 큰 사이즈로 새로 만들고 기존의 배열에서 요소들을 복사함으로써, 간접적으로 리스트의 용량을 확장시키게 된다. 하지만 이러한 가변적인 동작은 리스트를 다루는데에는 편하지만, 배열 복사 동작 자체가 성능이 그리 좋지않아 오버헤드(Overhead)를 발생 시키게 된다.

| **메서드**                           | **설 명**                                            |
| ------------------------------------ | ---------------------------------------------------- |
| int size()                           | ArrayList에 저장된 객체의 개수를 반환한다.           |
| void ensureCapacity(int minCapacity) | ArrayList의 용량이 최소한 minCapacity가 되도록 한다. |
| void trimToSize()                    | 용량의 크기에 맞게 줄인다 (빈 공간을 없앤다.)        |

```java
ArrayList<String> list = new ArrayList<>(10); // 용량(capacity)를 10으로 설정

// 용량 10을 넘은 요소 13개 추가
list.add("A");
list.add("B");
list.add("C");
list.add("D");
list.add("E");
list.add("F");
list.add("G");
list.add("H");
list.add("I");
list.add("J");
list.add("K");
list.add("L");
list.add("M");

list.size(); // 크기(size)는 13 : 자동으로 용량이 증가되어 데이터를 적재함
```

따라서 사용될 데이터의 개수를 미리 알고 있는 경우라면 애초에 ArrayList를 만들 때부터 큰 값으로 만들어주면 된다. 그러면 배열이 복사하면서 발생하는 오버헤드를 피할 수 있어 성능저하를 방지할 수 있게 된다.

혹은 ensureCapacity() 메서드를 이용해 리스트의 최소 용량을 재지정함으로써 실행 중간에 리스트의 용량을 늘릴수도 있다.

```java
ArrayList<String> list = new ArrayList<>(5); 

list.add("1");
list.add("2");
list.add("3");
list.add("4");
list.add("5");

list.ensureCapacity(10); // 최소 용량이 5에서 10으로 재지정

list.add("6");
list.add("7");

System.out.println(list); // [1, 2, 3, 4, 5, 6, 7]
```

따라서 사용될 데이터의 개수를 미리 알고 있는 경우라면 애초에 ArrayList를 만들 때부터 큰 값으로 만들어주면 된다. 그러면 배열이 복사하면서 발생하는 오버헤드를 피할 수 있어 성능저하를 방지할 수 있게 된다.

혹은 ensureCapacity() 메서드를 이용해 리스트의 최소 용량을 재지정함으로써 실행 중간에 리스트의 용량을 늘릴수도 있다.

```java
ArrayList<String> list = new ArrayList<>(5); 

list.add("1");
list.add("2");
list.add("3");
list.add("4");
list.add("5");

list.ensureCapacity(10); // 최소 용량이 5에서 10으로 재지정

list.add("6");
list.add("7");

System.out.println(list); // [1, 2, 3, 4, 5, 6, 7]
```



## 9. ArrayList 복사

| **메서드**     | **설 명**             |
| -------------- | --------------------- |
| Object clone() | ArrayList를 복제한다. |

```java
ArrayList<Integer> number = new ArrayList<>();
number.add(1);
number.add(3);
number.add(5);

// ArrayList는 내부적으로 Object[] 배열로 저장하기 때문에 형변환이 필요함
ArrayList<Integer> cloneNumber = (ArrayList<Integer>) number.clone();

System.out.println("ArrayList: " + number); // [1, 3, 5]
System.out.println("Cloned ArrayList: " + cloneNumber); // [1, 3, 5]
```



## 10. ArrayList 배열 변환

| **메서드**                        | **설 명**                                                    |
| --------------------------------- | ------------------------------------------------------------ |
| Object[] toArray()                | ArrayList에 저장된 모든 객체들을 배열로 반환한다.            |
| Object[] toArray(Obejct[] objArr) | ArrayList에 저장된 모든 객체들을 배열 objArr에 담아 반환한다. |

```java
ArrayList<String> languages= new ArrayList<>();
languages.add("Java");
languages.add("Python");
languages.add("C");

/* ArrayList<String> 을 String[] 배열로 변환 */

// 방법 1 : 배열로 변환하고 반환
String[] arr1 = languages.toArray();

// 방법 1 : 매개변수로 지정된 배열에 담아 반환
String[] arr2 = new String[languages.size()]; // 먼저 리스트 사이즈에 맞게 배열 생성
languages.toArray(arr2);
```



## 11. ArrayList 정렬

ArrayList를 정렬할때 주의할점은 sort() 메서드는 정렬된 값을 반환하는 것이 아닌, 원본 리스트 자체를 변경 시킨다.

| **메서드**              | **설 명**                                    |
| ----------------------- | -------------------------------------------- |
| void sort(Comparator c) | 지정된 정렬기준(c)으로 ArrayList를 정렬한다. |

```java
ArrayList list1 = new ArrayList();
list1.add("3");
list1.add("2");
list1.add("1");
 
// 오름차순 정렬
list1.sort(Comparator.naturalOrder());
System.out.println(list1); // [1, 2, 3]

// 내림차순 정렬
list1.sort(Comparator.reverseOrder());
System.out.println(list1); // [3, 2, 1]
```



## 12. ArrayList 순회

보통 ArrayList의 요소들을 순회할 일이 있다면 다음과 같이 for문으로 처리하는 것이 일반적일 것이다.

```java
ArrayList<Integer> list = new ArrayList<Integer>();
list.add(1);
list.add(2);
list.add(3);
list.add(4);

for(Integer i : list) { 
    System.out.println(i);
}
```

 

### 12.1 ArrayList 이터레이터

다만 몇몇 컬렉션에서는 저장된 요소를 Iterator 인터페이스로 읽어오도록 하는 순회 패턴을 지향하기도 한다.

| **메서드**                           | **설 명**                                                    |
| ------------------------------------ | ------------------------------------------------------------ |
| Iterator iterator()                  | ArrayList의 Iterator객체를 반환한다.                         |
| ListIterator listIterator()          | ArrayList의 ListIterator를 반환한다.                         |
| ListIterator listIterator(int index) | ArrayList의 지정된 위치부터 시작하는 ListIterator를 반환한다. |

Collection 인터페이스에서는 Iterator 인터페이스를 구현한 클래스의 인스턴스를 반환하는 iterator() 메소드를 정의하여 각 요소에 접근하도록 정의 하고 있다. 따라서 Collection 인터페이스를 상속받는 List나 Set 인터페이스에서도 iterator() 메소드를 사용할 수 있다. (Map은 X)

```java
// 이터레이터 객체 반환
Iterator<Integer> iter = lnkList.iterator();

// 만일 다음 요소가 있을 경우 반복
while(iter.hasNext()) {
    System.out.println(iter.next()); // 요소를 출력하고 반복 위치를 이동
}
```

또한 ArrayList에는 Iterator 뿐만 아니라 리스트 전용 이터레이터 객체인 **ListIterator**도 지원한다.

ListIterator 인터페이스는 Iterator 인터페이스를 상속받아 여러 기능을 추가한 인터페이스로서, Iterator는 컬렉션의 요소에 접근할 때 단 방향으로만 이동할 수 있는 반면, ListIterator 인터페이스는 컬렉션 요소의 대체, 추가 그리고 인덱스 검색 등을 위한 작업에서 양방향으로 이동하는 것을 지원하여 더욱 쓰임새가 넓다.

그리고 Iterator는 Collection 인터페이스를 구현한 컬렉션에서 모두 사용할수 있는 반면, ListIterator는 오로지 List 컬렉션에서만 사용이 가능하다.

```java
// ListIterator 객체 반환
ListIterator<Integer> iter = lnkList.listIterator();

// 만일 다음 요소가 있다면 반복
while (iter.hasNext()) {
    System.out.println(iter.next()); // 요소를 출력하고 반복 위치를 뒤로 이동
} 

// -- 리스트를 끝까지 순회한 상태

// 만일 이전 요소가 있다면 반복
while (iter.hasPrevious()) {
    System.out.println(iter.previous()); // 요소를 출력하고 반복 위치를 앞으로 이동
}
```



---



## ArrayList 를 마치면서

ArrayList 구현을 나중에 직접 구현하는 시간을 가질 수 있도록 합니다!



