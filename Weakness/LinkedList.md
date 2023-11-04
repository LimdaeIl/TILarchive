# LinkedList

자바의 Linked List는 ArrayList와 같이 인덱스로 접근하여 조회 / 삽입이 가능하지만 내부 구조는 완전히 다르게 구성되어 있다는 점이 특징이다. ArrayList는 내부적으로 배열을 이용하여 메서드로 이리저리 조작이 가능하게 만든 컬렉션이라면, Linked List는 **노드(객체)** 끼리의 **주소** 포인터를 서로 가리키며 **링크(참조)**함으로써 이어지는 구조이다.

<img src="https://blog.kakaocdn.net/dn/bkxHdO/btrQEHXJbyj/btRCV8Frqi0GUJhFNOWSoK/img.png" alt="jcf-linkedlist" style="zoom:87%;" />

위 그림을 보면 LinkedList는 각기 노드마다 화살표로 연결되어 리스트 형태로 나열되어 있는 것을 볼 수 있다. 여기서 노드는 하나의 객체라고 보면된다. 즉, 객체를 만들면 객체의 주소가 생기게 되는데, 노드마다 각기 객체의 주소를 서로 참조함으로서 **연결 형태**를 구성하는 것이다.

단일 노드를 그림과 코드로 표현한다면 다음과 같이 될 것이다.

![jcf-linkedlist](https://blog.kakaocdn.net/dn/coWzBA/btrQE4ZBOoM/LaFN5iNjuGXyfhtFPKAYH0/img.png)

```java
class Node {
    Node next; // 다음 노드 주소를 저장하는 필드
    int data; // 데이터를 저장하는 필드
};
```



## 1. LinkedList 종류 

### 1.1 단방향 연결 리스트 (singly linked list)

<img src="https://blog.kakaocdn.net/dn/vkPPh/btrQIupSkI8/7qSoN2ETJ9fEnvXs4EZXN1/img.png" alt="singly linked list" style="zoom:80%;" />

위에서 보았듯이, 다음 노드를 가리키기 위한 포인터 필드 next만을 가지고 있는 링크드 리스트를 **singly linked list**라고 한다. 
하지만 단일 연결 리스트는 현재 요소에서 이전 요소로 접근해야 할때 매우 부적합한 특징이 있다.

예를들어 LinkedList에 저장된 데이터가 10000개라면 9999 번째 데이터에 접근하려면 Node를 9999번 이동해야 하기 때문이다. 
이를 극복한 것이 doubly linked list 구조 이다.

 

### 1.2 양방향 연결 리스트 (doubly linked list)

<img src="https://blog.kakaocdn.net/dn/Ny74t/btrQJcWvNzo/KkBHrSVwqjwaXZqfsKi2a1/img.png" alt="doubly linked list" style="zoom:80%;" />

```java
class Node {
    Node next; // 다음 노드 주소를 저장하는 필드
    Node prev; // 이전 노드 주소를 저장하는 필드
    int data; // 데이터를 저장하는 필드
};
```

이중 연결이라서 대단한건 아니고, 기존의 단일 연결 노드 객체에서 이전 노드 주소를 담고 있는 필드 prev가 추가된 형태를 doubly linked list라고 부른다.

singly linked list는 이동 방향이 단방향이기 때문에, 이를 보완하여 역순으로도 검색이 가능하도록 한 것이다. 
따라서 doubly linked list는 singly linked list보다 각 요소에 대한 접근과 이동이 쉽기 때문에 기본적으로 많이 사용된다.

<img src="https://blog.kakaocdn.net/dn/bdmhPO/btrQJovLPk4/XNxTVzpim1tDjJPKlQpuRK/img.png" alt="doubly linked list" style="zoom:52%;" />

> Tip
>
> 실제로 Java의 컬렉션 프레임워크에 구현된 LinkedList 클래스는 doubly linked list로 구현 되어 있다.



### 1.3 양방향 원형 연결 리스트 (doubly circular linked list)

<img src="https://blog.kakaocdn.net/dn/c3NZmJ/btrYjDflf4Z/k5rfwLkSA8kU4pr9Bc8sz0/img.png" alt="doubly circular linked list" style="zoom:80%;" />

추가로 doubly linked list 보다 접근성이 더 개선된 doubly circular linked list도 있다. 
이 역시 대단한건 아니고 단순히 첫번째 노드와 마지막 노드를 각각 연결시켜, **마치 원형 리스트** 처럼 만들었다고 보면 된다.

이러한 구조는 티비 채널을 순회하거나 오디오 플레이어와 같이 데이터를 순차적 방식으로 처리하다 마지막 요소를 만나면 다시 처음 요소로 되돌아가는 애플리케이션에서 사용된다고 보면 된다.

> Tip
>
> 단일 연결 리스트를 원형으로 연결한 singly circular linked list도 있지만 잘 쓰이지 않아 소개하지는 않는다.

### **LinkedList vs ArrayList 특징 비교**

| 구분             | ArrayList                                                    | LinkedList                                                   |
| ---------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 컬렉션 구성      | 배열을 이용                                                  | 노드를 연결                                                  |
| 데이터 접근 시간 | 모든 데이터 상수 시간 접근                                   | 위치에 따라 이동시간 발생                                    |
| 삽입 / 삭제 시간 | 삽입/삭제 자체는 상수 시간<br />삽입/삭제 시 데이터 이동이 필요한 경우 추가시간 발생 | 삽입/삭제 자체는 상수 시간<br />삽입/삭제 위치에 따라 그 위치까지 이동하는 시간 발생 |
| 데이터 검색      | 최악의 경우 리스트에 있는 아이템 수 만큼 확인                | 최악의 경우 리스트에 있는 아이템 수 만큼 확인                |
| CPU Cache        | 캐시 이점을 활용                                             | -                                                            |

자세한 내용은 https://inpa.tistory.com/entry/JCF-%F0%9F%A7%B1-ArrayList-vs-LinkedList-%ED%8A%B9%EC%A7%95-%EC%84%B1%EB%8A%A5-%EB%B9%84%EA%B5%90 정리합니다.



## 2. LinkedList 사용법

LinkedList 클래스 역시 List 인터페이스를 구현하므로, ArrayList 클래스와 사용할 수 있는 메소드가 거의 같다.

다만 LinkedList는 List 자료구조 외에도 Stack이나 Queue 자료구조로서도 이용이 가능하기 때문에 이를 위한 메서드들도 구현되어 있어 내부 메서드 갯수가 컬렉션중에 가장 많다고 보면 된다.



------



### 2.1 LinkedList 객체 생성

LinkedList를 사용하기 위해선 상단에 패키지를 명시하여 가져와야 한다.

```java
import java.util.LinkedList;
```

| **생성자**               | **설명**                                       |
| ------------------------ | ---------------------------------------------- |
| LinkedList()             | LinkedList객체를 생성                          |
| LinkedList(Collection c) | 주어진 컬렉션을 포함하는 LinkedList객체를 생성 |

```java
// 타입설정 int 타입만 적재 가능
LinkedList<Integer> list = new LinkedList<>();

// 생성시 초기값 설정
LinkedList<Integer> list2 = new LinkedList<Integer>(Arrays.asList(1,2));Copy
```

LinkedList의 선언은 ArrayList와 동일하지만, ArrayList처럼 초기 값을 미리 지정하는 기능은 제공되지 않는다. 내부 데이터 집합 구조가 배열처럼 미리 공간을 할당하고 사용하는 방식이 아니라 데이터가 추가될 때마다 노드(객체)들이 생성되어 동적으로 추가되는 방식이기 때문이다.



------



### 2.2 LinkedList 요소 추가 / 삽입

ArrayList 와 달리 LinkedList에는 add 메서드 종류가 4가지이다. 기본 add() 메소드는 addLast()와 동일하다. 

| **메서드**                           | **설명**                                               |
| ------------------------------------ | ------------------------------------------------------ |
| void addFirst(Object obj)            | LinkedList의 맨 앞에 객체(obj)를 추가                  |
| void addLast(Objec obj)              | LinkedList의 맨 뒤에 객체(obj)를 추가                  |
| boolean add(Object obj)              | LinkedList의 마지막에 객체를 추가한다. (성공하면 true) |
| void add(int index, Object element)  | 지정된 위치(index)에 객체를 저장한다.                  |
| void addAll(Collection c)            | 주어진 컬렉션의 모든 객체를 저장한다. (마지막에 추가)  |
| void addAll(int index, Collection c) | 지정한 위치부터 주어진 컬렉션의 데이터를 저장한다.     |

LinkedList의 가장 큰 특징은, ArrayList와 다르게 중간에 데이터가 추가되거나 중간에 있는 데이터가 삭제되어도 앞으로 땡기거나 뒤로 미는 동작이 없다는 점이다. 추가되거나 삭제될 노드 위치의 바로 앞뒤쪽에 있는 노드의 참조를 변경하기만하면 된다. 또한 LinkedList는 배열과 달리 할당이 고정된 크기 개념이 아니기 때문에 메모리가 충분하는한 무한정으로 요소를 저장 할 수 있다.

```java
LinkedList<String> list = new LinkedList<>(Arrays.asList("A", "B", "C")));

// 가장 앞에 데이터 추가
list.addFirst("new");
```

<img src="https://blog.kakaocdn.net/dn/cGx3oZ/btrQN8zLeLQ/weI3628z7t3PnK42RkAuaK/img.png" alt="linkedlist-add" style="zoom:80%;" />



```java
LinkedList<String> list = new LinkedList<>(Arrays.asList("A", "B", "C")));

// 가장 뒤에 데이터 추가
list.addLast("new");
```

<img src="https://blog.kakaocdn.net/dn/ln4hz/btrQOBBrjG1/bjApnHI9SbVW9KAjWHkpcK/img.png" alt="linkedlist-add" style="zoom:80%;" />



```java
LinkedList<String> list = new LinkedList<>(Arrays.asList("A", "B", "C")));

// index 1에 중간 위치에 데이터 10 추가
list.add(1, "new");
```

<img src="https://blog.kakaocdn.net/dn/1LGmF/btrQOncoOy9/YLTBVO5EJspsHix6F1ua00/img.png" alt="linkedlist-add" style="zoom:80%;" />

> Tip
>
> addFirst() 와 addLast() 는 요소를 첫번째, 마지막에 추가하는 것이기 때문에 O(1) 의 시간이 걸린다.
> 그러나 중간 삽입일 경우 중간에 삽입할 위치까지의 탐색을 필요하기 때문에 O(N)의 시간이 걸린다



### 2.3 LinkedList 요소 삭제

remove 메서드 역시 add 메서드 처럼 removeFirst() 와 removeLast() 는 O(1), 그외에는 탐색 시간이 필요하기에 O(N)이 걸린다. 
만일 값을 전부 제거하려면 clear() 메소드를 사용하면 된다.

| **메서드**                      | **설명**                                                     |
| ------------------------------- | ------------------------------------------------------------ |
| Obejct removeFirst()            | 첫번째 노드를 제거                                           |
| Object removeLast()             | 마지막 노드를 제거                                           |
| Object remove()                 | LinkedList의 첫 번째 요소를 제거                             |
| Object remove(int index)        | 지정된 위치(index)에 있는 객체를 제거한다.                   |
| boolean remove(Object obj)      | 지정된 객체를 제거한다. (성공하면 true)                      |
| boolean removeAll(Collection c) | 지정한 컬렉션에 저장된 것과 동일한 노드들을 LinkedList에서 제거한다. |
| boolean retainAll(Collection c) | LinkedList에 저장된 객체 중에서 주어진 컬렉션과 공통된 것들만 남기고 제거한다. |
| void clear()                    | LinkedList를 완전히 비운다.                                  |



```java
LinkedList<String> list = new LinkedList<>(Arrays.asList("A", "B", "C")));

// 첫 번째 값 제거
list.removeFirst();
```

<img src="https://blog.kakaocdn.net/dn/dNVNRv/btrQN96y3PI/OkKq6J1jxKwfqUZYmuwhVk/img.png" alt="linkedlist-remove" style="zoom:80%;" />



```java
LinkedList<String> list = new LinkedList<>(Arrays.asList("A", "B", "C")));

// 마지막 데이터 제거
list.removeLast();Copy
```

<img src="https://blog.kakaocdn.net/dn/TfxVF/btrQOOtN0DJ/iHsIKxc70TFOZhNNDA9611/img.png" alt="linkedlist-remove" style="zoom:80%;" />



```java
LinkedList<String> list = new LinkedList<>(Arrays.asList("A", "B", "C")));

// index 1 중간 위치 제거
list.remove(1);
```

<img src="https://blog.kakaocdn.net/dn/cO3G8H/btrQK72M8vt/vBRspCgVb7gdj7shzjQLu0/img.png" alt="linkedlist-remove" style="zoom:80%;" />



```java
// 모든 값 제거
list.clear();
```

참고로 모든 값들을 제거 한다고 해서 단번에 노드들이 제거되는 것이 아닌, 참조 체인을 따라가면서 일일히 null로 설정해주기 때문에 O(N)의 시간이 걸린다.



### 2.4 LinkedList 요소 검색

리스트에서 요소가 있는지 없는지 검색하는 방법은 요소 자체가 리스트에 있는지 검사하는 contains() 메서드와 인덱스 위치도 반환해주는 indexOf() 메서드가 존재한다.

| **메서드**                        | **설명**                                                     |
| --------------------------------- | ------------------------------------------------------------ |
| int size()                        | LinkedList에 저장된 객체의 개수를 반환한다.                  |
| boolean isEmpty()                 | LinkedList가 비어있는지 확인한다.                            |
| boolean contains(Object obj)      | 지정된 객체(obj)가 LinkedList에 포함되어 있는지 확인한다.    |
| boolean containsAll(Collection c) | 지정된 컬렉션의 모든 요소가 포함되었는지 알려줌.             |
| int indexOf(Object obj)           | 지정된 객체(obj)가 저장된 위치를 찾아 반환한다.              |
| int lastIndexOf(Object obj)       | 지정된 객체(obj)가 저장된 위치를 뒤에서 부터 역방향으로 찾아 반환한다. |

```java
LinkedList<String> list = new LinkedList<>(Arrays.asList("A", "B", "C")));

// 해당 값을 가지고 있는 요소 위치를 반환 (앞에서 부터 검색) 
list1.indexOf("B"); // 1

// 해당 값을 가지고 있는 요소 위치를 반환 (뒤에서 부터 검색) 
list1.lastIndexOf("D"); // -1 : 찾고자 하는 값이 없을 때 -1 반환
```

```java
LinkedList list1 = new LinkedList();
list1.add("1");
list1.add("2");

// list1안에 "1" 값이 있는지 확인
list1.contains("1"); // true

LinkedList list2 = new LinkedList();
list2.add("1");
list2.add("2");
 
// list1에 list2의 모든 노드가 포함되어 있는지 확인
list1.containsAll(list2); // trueC
```



---

###  

### 2.5 LinkedList 요소 얻기

개별 단일 요소를 얻고자 하면 get 메서드로 얻어올 수 있다. 단, LinkedList는 ArrayList와 달리 만일 100번째 노드를 확인하기 위해서는 첫 번째 노드부터 100번째 노드까지 참조를 따라서 일일히 이동해야 하기 때문에 탐색 성능은 좋지 않은 편이다.

| **메서드**                               | **설명**                                                   |
| ---------------------------------------- | ---------------------------------------------------------- |
| Object get(in index)                     | 지정된 위치(index)에 저장된 객체를 반환한다.               |
| List subList(int fromIndex, int toIndex) | fromIndex부터 toIndex사이에 저장된 객체를 List로 반환한다. |



```java
list.get(0); // 0번째 index 요소의 값 출력
```



------



### 2.6 LinkedList 요소 변경

set 메서드의 주의점은 아무리 LinkedList라고 해도 기본적으로 리스트 이기 때문에 리스트의 크기(size)를 넘기는 인덱스를 할당하게 된다면 배열과 같이 IndexOUtOfBoundsException 예외가 발생한다.

| **메서드**                        | **설명**                                          |
| --------------------------------- | ------------------------------------------------- |
| Object set(int index, Object obj) | 지정한 위치(index)의 객체를 주어진 객체로 바꾼다. |

```java
LinkedList<String> list = new LinkedList<>();
list1.add("10");
list1.add("20");
list1.add("30");
 
list1.set(1, "A"); // index 1번의 데이터를 문자열 "A"로 변경한다.
System.out.println(list1); // // [10, A, 30]
```



### 2.7 LinkedList 배열 변환

LinkedList는 배열은 아니지만 리스트 컬렉션이기 때문에 연속된 값으로서 배열로 변환이 가능하다.

| **메서드**                        | **설명**                                                     |
| --------------------------------- | ------------------------------------------------------------ |
| Object[] toArray()                | LinkedList에 저장된 모든 객체들을 객체배열로 반환한다.       |
| Object[] toArray(Obejct[] objArr) | LinkedList에 저장된 모든 객체들을 객체배열 objArr에 담아 반환한다. |

```java
LinkedList<Number> list1 = new LinkedList<>();
list1.add(1);
list1.add(2);
list1.add(3);
list1.add(4);
 
Number[] arr = (Number[]) list1.toArray();
System.out.println(Arrays.toString(arr)); // [1, 2, 3, 4]
```

```java
LinkedList<Number> list1 = new LinkedList<>();
list1.add(1);
list1.add(2);

Object[] tmp = {0, 1, 2, 3, 4, 5}; // 통째로 추가할 배열

Number[] arr = (Number[]) list1.toArray(tmp);
System.out.println(Arrays.toString(arr)); // [1, 2, null, 3, 4, 5]
```

> Tip
>
> toArray(Obejct[] objArr) 메서드의 결과값 배열 출력에서 null이 삽입되어 있는 이유는 자바 메서드 스펙이다.
> javadoc에 따르면 삽입된 리스트의 길이를 알리기 위해서 일부로 null을 넣는다고 한다.

------

### 2.8 LinkedList 순회 (이터레이터)

보통 ArrayList의 요소들을 순회할 일이 있다면 다음과 같이 for문으로 처리하는 것이 일반적일 것이다.

```java
LinkedList<String> list = new LinkedList<>();

// for 문을 이용한 순회
for (String data : list) {
    System.out.println(data);
}
```

 다만 몇몇 컬렉션에서는 저장된 요소를 Iterator 인터페이스로 읽어오도록 하는 순회 패턴을 지향하기도 한다.

| **메서드**                           | **설명**                                                     |
| ------------------------------------ | ------------------------------------------------------------ |
| Iterator iterator()                  | LinkedList의 Iterator객체를 반환한다.                        |
| ListIterator listIterator()          | LinkedList의 ListIterator를 반환한다.                        |
| ListIterator listIterator(int index) | LinkedList의 지정된 위치부터 시작하는 ListIterator를 반환한다. |

Collection 인터페이스에서는 **Iterator 인터페이스를 구현한 클래스의 인스턴스**를 반환하는 iterator() 메소드를 정의하여 각 요소에 접근하도록 정의 하고 있다. 따라서 Collection 인터페이스를 상속받는 List나 Set 인터페이스에서도 iterator() 메소드를 사용할 수 있다. (Map은 X)

```java
Iterator it = list.iterator();
 
while(it.hasNext()) {
	Object obj = it.next();
	System.out.println(obj);
}
```

또한 LinkedList 메서드를 보면 Iterator 뿐만 아니라 **ListIterator**도 지원하는걸 볼 수 있는데, Iterator는 Collection 인터페이스를 구현한 컬렉션에서 모두 사용할수 있는 반면, ListIterator는 오로지 List 컬렉션에서만 사용이 가능하다.

ListIterator 인터페이스는 Iterator 인터페이스를 상속받아 여러 기능을 추가한 인터페이스로서, Iterator는 컬렉션의 요소에 접근할 때 단 방향으로만 이동할 수 있는 반면, ListIterator 인터페이스는 컬렉션 요소의 대체, 추가 그리고 인덱스 검색 등을 위한 작업에서 **양방향**으로 이동하는 것을 지원하여 더욱 쓰임새가 넓다.

```java
ListIterator it = list.listIterator(); // LinkedList의 ListIterator를 반환한다
 
while(it.hasNext()) {
	System.out.print(it.next());
}

while(it.hasPrevious()) {
	System.out.print(it.previous());
}
```



### 2.9 LinkedList 스택 & 큐 지원

LinkedList는 리스트 용도로서 뿐만 아니라, 구조 특성상 Stack이나 Queue 로서도 이용이 가능하다. 
그래서 아래와 같이 스택과 큐에 관한 전용 메서드를 별도로 제공한다.

| **메서드**                                 | **설명**                                                     |
| ------------------------------------------ | ------------------------------------------------------------ |
| Object element()                           | LinkedList에 첫 번째 노드를 반환                             |
| boolean offer(Obejct obj)                  | 지정된 객체(obj)를 LinkedList의 끝에 추가. 성공하면 true 실패하면 false |
| Object peek()                              | LinkedList의 첫 번째 요소를 반환                             |
| Object poll()                              | LinkedList의 첫 번째 요소를 반환 LInkedList의 요소에서는 제거된다. |
| void push(Object obj)                      | 맨 앞에 객체(obj)를 추가 (addFirst와 동일)                   |
| Iterator descendingItorator()              | 역순으로 조회하기 위한 DescendingItorator를 반환             |
| Object getFrist()                          | LinkedList의 첫번째 노드를 반환                              |
| Object getLast()                           | LinkedList의 마지막 노드를 반환                              |
| boolean offerFirst(Object obj)             | 지정된 객체(obj)를 LinkedList의 맨 앞에 추가, 성공하면 ture  |
| boolean offerLast(Object obj)              | 지정된 객체(obj)를 LinkedList의 맨 뒤에 추가, 성공하면 ture  |
| Object peakFirst()                         | 첫번째 노드를 반환                                           |
| Object peakLast()                          | 마지막 노드를 반환                                           |
| Object pollFirst()                         | 첫번째 노드를 반환하면서 제거                                |
| Object pollLast()                          | 마지막 노드를 반환하면서 제거                                |
| Object pop()                               | 첫번째 노드를 제거 (removeFirst와 동일)                      |
| boolean removeFirstOccurrence( Obejct obj) | 첫번째로 일치하는 객체를 제거                                |
| boolean removeLastOccurrence( Obejct obj)  | 마지막으로 일치하는 객체를 제거                              |





------



### 2.10LinkedList 동기화 처리

멀티 쓰레드 환경에서 동시에 컬렉션에 접근해 문제가 발생하는 것을 방지하기 위해 동기화 처리된 리스트를 반환받아 사용할 수 있다.

```java
/* ArrayList 동기화 처리 */
List<String> l1 = Collections.synchronizedList(new ArrayList<>());

/* LinkedList 동기화 처리 */
List<String> l2 = Collections.synchronizedList(new LinkedList<>());
```



### 2.11 LinkedList 와 ArrayList 차이

1. **순차적으로 추가/삭제하는 경우에는 ArrayList가 LinkedList보다 빠릅니다.**

   순차적으로 삭제한다는 것은 마지막 데이터부터 역순으로 삭제해나간다는 것을 의미하며, ArrayList는 마지막 데이터부터 삭제할 경우 각 요소들의 재배치가 필요하지 않기 때문에 상당히 빠릅니다. ArrayList 요소 삭제는 요소의 값을 null로만 변경하면 됩니다.

2. **중간 데이터를 추가/삭제하는 경우에는 LinkedList가 ArrayList보다 빠릅니다.**

   LinkedList는 각 요소간의 연결만 변경해주면 되기 떄문에 처리속도가 상당히 빠릅니다. 반면에 ArrayList는 각 요소들이 재배치하여 추가할 공간을 확보하거나 빈 공간을 채워야하기 때문에 처리속도가 느립니다.



------



## 3. LinkedList 직접 구현해보기

LinedList의 메서드 구성과 예제 사용법만 보는 것보다, 한번 직접 자료구조를 구현해보면 자바 컬렉션의 내부 동작에 대해 확실히 습득 할 수 있다. 특히나 LinkedList는 트리나 그래프 ..등의 복잡한 자료구조의 기본 전신이기 때문에, 앞으로 복잡한 자료 구조를 공부할 계획이 있다면 LinkedList의 내부 구조에 대해 반드시 파악하고 있어야 한다. 

추후에 정리하도록 합니다! https://inpa.tistory.com/entry/JAVA-%E2%98%95-LinkedList-%EA%B5%AC%EC%A1%B0-%EC%82%AC%EC%9A%A9%EB%B2%95-%EC%99%84%EB%B2%BD-%EC%A0%95%EB%B3%B5%ED%95%98%EA%B8%B0







