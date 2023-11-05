# 스택, 큐, 데크



## 1. Stack 컬렉션

스택(Stack)의 사전적 정의는 '쌓다', '더미' 로서 접시 스택처럼 접시를 쌓아놓은 것을 말한다. 즉, 상자에 물건을 쌓아 올리듯이 데이터를 쌓는 자료 구조라고 할 수 있다. 

아래 그림과 같이 스택은 마지막에 저장한 데이터를 가장 먼저 꺼내게 되는 구조 특징이 있는데, 이러한 자료의 구조를 **LIFO(Last In First Out)** 구조라고 말한다. 함께 많이 사용되는 Queue(큐)의 경우 먼저 추가된 데이터가 먼저 나오는 FIFO(First In First Out) 동작을 갖는것과 비교된다.

<img src="https://blog.kakaocdn.net/dn/SG5Se/btrQ2SR6kUl/zyrY2SXzca9wx6gkCAZMek/img.png" alt="jcf-stack" style="zoom:80%;" />



---



## 2.Stack 사용처

스택의 쓰임새의 대표적인 예로 총기류의 탄창을 들 수 있다. 마지막에 넣은 탄알이 가장 먼저에 발사되어 꺼내어지며, 
결국에는 맨 처음에 넣은 탄환이 마지막에 발사되는 걸 떠올리면 된다.

<img src="https://blog.kakaocdn.net/dn/bOBmVt/btrX30CoSBF/WQJKCC0clfykHhpVfOFaQ0/img.png" alt="jcf-stack" style="zoom:80%;" />



이밖에도 스택은 수식계산, 수식 괄호 검사, undo/redo, 웹브라우저의 뒤로 / 앞으로 등에 구현되기도 한다.

자바 가상 머신(JVM)을 배우면서 Runtime Data Area에 Stack 메모리를 들은적이 있을텐데, 마지막으로 사용이 끝난 지역변수를 바로바로 쳐내버리는 Stack은 매우 효율적으로 메모리를 사용하는 방법이기 때문에 스택의 구조 개념이 프로그래밍 메모리 영역에 그대로 쓰여지기도 한다.



---



## 3. 자바의 Stack

자바의 Stack 클래스는 Vector 클래스를 상속(extends)받기에 **Thread-Safe** 하다는 특징을 가지고 있다.



<img src="https://blog.kakaocdn.net/dn/ohmOM/btrRa2TKYu8/1mlIZpa8sEHQ7NXXGZd2W1/img.png" alt="jcf-stack" style="zoom:80%;" />



------

## 4. Stack 사용법

자바는 java.util.Stack클래스를 통해 Stack(스택) 동작을 제공하고 있다. 일반적으로 스택에 데이터를 추가하는 동작은 **push**라고 하며, 스택에서 데이터를 빼는 동작은 **pop**이라고 한다.

```java
import java.util.Stack;
```

| **메서드**               | **설 명**                                                    |
| ------------------------ | ------------------------------------------------------------ |
| boolean empty()          | Stack이 비어있는지 알려준다.                                 |
| Object peek()            | Stack의 맨 위에 저장된 객체를 반환.  pop과 달리 Stack에서 객체를 꺼내지는 않는다. <br />비어있을 경우 EmptyStackException 발생 |
| Object pop()             | Stack의 맨 위에 저장된 객체를 꺼낸다.<br />비어있을 경우 EmptyStackException 발생 |
| Object push(Object item) | Stack에 객체(item)를 저장한다.                               |
| int search(Object o)     | Stack에서 주어진 객체(o)를 찾아서 그 위치를 반환  못 찾을 경우 -1을 반환  <br />배열과 달리 위치는 0이 아닌 1부터 시작 |



---



## 5. Stack 요소 넣기

```java
Stack<Number> stack = new Stack<>();
stack.push(1);
stack.push(2);
stack.push(3);

System.out.println(stack); // [1, 2, 3]
```

<img src="https://blog.kakaocdn.net/dn/k6pXC/btrQ5lM1pGq/XzFwkmrSVN4GN6dEkFoVJk/img.png" alt="jcf-stack" style="zoom:80%;" />



---



## 6. Stack 요소 꺼내기

Stack은 **나중에 넣은것이 먼저 나오는** LIFO(Last In First Out) 구조이기 때문에 넣은 순서와 반대로 꺼내지게 된다.

```java
System.out.println(stack.pop()); // 3

System.out.println(stack); // [1, 2]

System.out.println(stack.pop()); // 2
System.out.println(stack.pop()); // 1

System.out.println(stack); // []Copy
```

<img src="https://blog.kakaocdn.net/dn/1sdJo/btrQ27u0Owg/yEn6iRq1jnktQpOSHAkack/img.png" alt="jcf-stack" style="zoom:80%;" />



------

## 7. stack 최상단 요소 가져오기

스택에 들어있는 요소들 중, 최상단 값을 확인 만하고 스택에서 제거하고 싶지 않은 경우도 있을 것이다. 이 경우 peek() 메소드를 호출하면, 스택의 최상단(Top)에 있는 데이터를 확인만하고 스택에서 제거하지 않게 된다.

```java
Stack<Number> stack = new Stack<>();
stack.push(1);
stack.push(2);
stack.push(3);

// 마지막에 넣은 값만 확인하고 제거하지는 않는다
System.out.println(stack.peek()); // 3

System.out.println(stack); // [1, 2, 3]Copy
```

<img src="https://blog.kakaocdn.net/dn/ZbRDM/btrRbZ3kmh0/hiAqSqB3mulfJuuLk9AOl1/img.png" alt="jcf-stack" style="zoom:80%;" />



------



## 8. Stack 공간 비어있는지 확인

만약에 스택에 데이터가 존재하지 않는 상황에서, pop이나 peek을 하게 되면 EmptyStackException 예외가 발생하게 된다. 
따라서 항상 메서드 호출 시에 스택에 데이터가 존재하는지 확인해야 한다.

```java
Stack<Number> stack = new Stack<>();
stack.push(1);

// 스택이 비어있지 않다면 안전하게 요소를 제거
if(!stack.isEmpty() {
	stack.pop();
}
```



---



## 9. Stack 요소 위치 확인

스택에 특정 데이터가 존재하는지 확인하기 위해 search() 메서드를 사용할 수 있다. 리스트의 indexOf() 메서드와 결을 같이한다고 생각하면 된다. 데이터가 존재하면 순서를 반환하고 존재하지 않는 다면 -1을 반환한다.

이때 스택의 search() 메서드를 사용함에 있어 주의할 점이 있는데, 일반적인 배열 인덱스 처럼 0부터 시작해 그 위치를 반환하는 것이 아니라, pop() 메서드를 호출했을 때 몇 번째 순서로 나오는지에 대한 인덱스를 반환한다. **즉, 인덱스가 0이 아닌 1부터 시작한다 라고 보면된다. 그래서 만일 반환값이 1이면 가장 먼저 pop 되는 요소라고 보면 된다.**

```java
Stack<String> stack = new Stack<>();
stack.push("HI");
stack.push("HELLO");
stack.push("EARTH");
stack.push("SINGLE");
stack.push("BUS");

System.out.println(stack); // [HI, HELLO, EARTH, SINGLE, BUS]

// 이때의 인덱스는 배열처럼 0부터 시작하는 기준이 아닌, 몇번째로 꺼내지는지에 대한 인덱스 표시이다.
System.out.println(stack.search("BUS")); // 1 → 첫번째로 꺼내질 것임
System.out.println(stack.search("HI")); // 5C
```







------



## 10. Stack 사이즈

Stack 클래스는 Vector 클래스를 상속했으며, Vector 클래스는 List 인터페이스를 구현하므로 size() 메서드를 사용할 수 있다.

```java
Stack<Number> stack = new Stack<>();
stack.push(1);
stack.push(2);
stack.push(3);
stack.push(4);

System.out.println(stack.size()); // 4C
```

> Tip
>
> Stack 클래스의 경우 ArrayList 처럼 동적 공간이라 별도의 사이즈를 명시하지 않아도 된다.
> 처음 스택이 생성되었을때 10개의 데이터를 저장할 수 있는 공간이 할당된다. 그리고 push() 메소드를 통해 요소가추가되어 10개가 넘어가면, 현재 스택 사이즈의 2배에 해당하는 공간을 할당하고 기존 데이터를 복사하게 된다.





------



## 11. Stack 클래스는 deprecated 되었다



우선 결론부터 말하자면 만일 어플리케이션에 스택 자료 구조를 사용해야 할 상황일때, 자바의 스택 클래스는 쓰기를 지양하여야 한다. 왜냐하면 Vector 컬렉션을 상속을 받아 구현되었기 때문인데, 이 Vector 클래스 자체가 컬렉션 프레임워크라는 개념이 나오기도 전에 자바 버전1 서부터 있었던 굉장히 오래된 클래스이기 때문에 여러 방면으로 취약점이 많고, 또한 상속으로 인한 부모 메서드 공유 문제 때문에 사용자가 잘못되게 사용될 수 있다는 큰 문제점이 있기 때문이다.

즉, 자식 클래스에게는 부적합한 부모 클래스의 메소드가 상속되기 때문에 자식 클래스 인스턴스의 상태가 불안정해지게 된다는 문제점이 있다.

<img src="https://blog.kakaocdn.net/dn/cNBWiM/btrQ4WAhkOX/DVEjtcympqdX5Y1DaWrtLK/img.png" alt="jcf-stack" style="zoom:80%;" />

Stack 클래스는 Vector 클래스를 상속하도록 설계 되어 있다.

예를 들어 Stack의 대표적인 동작은 push, pop 이지만, **상속한 Vector 클래스의 add 메소드 또한 외부로 노출되게 된다.** 그러면서 아래와 같이 개발자가 add 메소드도 스택 클래스에서 사용할수 있는 메소드인줄 알고 사용했다가, 의도치 않은 동작이 실행되면서 오류를 범하게 된다.



```java
Stack<String> stack = new Stack<>();
stack.push("one");
stack.push("two");
stack.push("three");

stack.add(0, "four"); // add 메소드를 호출함으로써 stack의 의미와는 다르게 특정 인덱스의 값이 추가

// 마지막에 넣은 요소는 "four" 인데 스택은 "three"로 뽑혀진다
String str = stack.pop(); // three
System.out.println(str.equals("four")); // false
```





------



## 12. (권장 방식) Stack 대신 Deque 사용

따라서 자바 공식 문서에 보면 애초부터 상속을 잘못하여 잘못 설계된 Stack 클래스보다 **Deque 클래스를 사용하여 구현할 것을 권장**하고 있다.

<img src="https://blog.kakaocdn.net/dn/dBWRWs/btrRcQypOfZ/QqiNRF0Hip7uWdWIIW5RGK/img.png" alt="jcf-stack-dequeue" style="zoom:80%;" />

Deque(덱)를 아직 배우지 않아 생소할지 모르겠지만, 양방향으로 넣고 꺼낼수 있는 향상된 큐(queue) 자료구조 쯤으로 이해하면 된다. 
그래서 상황에 따라서 큐처럼 사용할 수도 있고 스택 처럼 유연하게 사용할수 있다.

<img src="https://blog.kakaocdn.net/dn/b8xIR5/btrRb8l2ZJp/7xQym00kE28huCOBy1D50k/img.png" alt="jcf-stack-dequeue" style="zoom:80%;" />



```java
/* Stack 처럼 사용하기 */
Deque<String> stack = new ArrayDeque<>();

stack.push("a");
stack.push("b");
stack.push("c");
stack.push("d");
stack.push("e");

System.out.println(stack); // [a, b, c, d, e]

System.out.println(stack.pop()); // e
System.out.println(stack.pop()); // d

System.out.println(stack); // [a, b, c]
```

```java
/* Queue 처럼 사용하기 */
Deque<String> queue = new ArrayDeque<>();

queue.offer("a");
queue.offer("b");
queue.offer("c");
queue.offer("d");
queue.offer("e");

System.out.println(queue); // [a, b, c, d, e]

System.out.println(queue.poll()); // a
System.out.println(queue.poll()); // b

System.out.println(queue); // [c, d, e]
```

