# Iterator, ListIterator, Enumeration

- Iterator, ListIterator, Enumeration은 모두 컬렉션에 저장된 요소를 접근하는데 사용되는 인터페이스
- Enumeration은 Iterator의 구버전의 인터페이스
- ListIterator는 Iteraotr의 기능을 향상시킨 인터페이스



## 1. Iterator

- 자바는 컬렉션에 저장된 각 요소에 접근하는 기능을 가진 Iterator 인터페이스를 정의
- Collection 인터페이스에는 'Iterator'를 반환하는 iterator를 정의

```java
public interface Iterator {
    boolean hasNext();
    Object next();
    void remove();
}
public interface Collection {
    ...
    public Iterator iterator();
    ...
}
```

iterator()는 Collection 인터페이스에 정의된 메서드입니다. 따라서 Collection 인터페이스의 자손인 List와 Set에도 포함되어 있습니다. 그래서 List나 Set 인터페이스를 구현하는 컬렉션은 iterator()가 각 컬렉션의 특징에 알맞게 작성되어 있습니다. 컬렉션 클래스에 대해 iterator()를 호출하여 Iterator를 얻은 후에 반복문으로 요소를 읽을 수 있습니다. 반복문은 주로 while 문을 사용합니다.

| 메서드            | 설명                                                         |
| ----------------- | ------------------------------------------------------------ |
| boolean hasNext() | 읽어 올 요소가 남아있는지 확인.<br />있으면 true, 없으면 false를 반환합니다. |
| Object next()     | 다음 요소를 읽기.<br />next()를 호출하기 전, hasNext()를 호출해서 읽어 올 요소가 남아있는지 확인하는 것이 안전합니다. |
| void remove()     | next로 읽어 온 요소를 삭제.<br />next()를 호출한 다음에 remove()를 호출해야 합니다.(선택적 가능) |



ArrayList에 저장된 요소들을 출력하기 위한 코드는 다음과 같이 작성할 수 있습니다.

```java
Collection c = new ArrayList();
Iterator it = c.iterator();

while(it.hasNext()) {
    System.out.println(it.next());
}
```

ArrayList 대신 Collection 인터페이스를 구현한 다른 컬렉션 클래스에 대해서도 동일한 코드로 요소를 출력할 수 있습니다. 첫 줄에서 ArrayList 대신 Collection 인터페이스를 구현한 다른 컬렉션 객체를 생성하도록 변경하기만 하면 됩니다. 이처럼 Iterator를 이용해서 컬렉션 요소를 읽어오는 방법을 표준화했기 때문에 코드의 재사용성을 높이는 것이 가능합니다. 

공통 인터페이스를 정의해서 표준을 정의하고 구현하여 표준을 따르도록 함으로써 코드의 일관성을 유지하여 재사용성을 극대화하는 것이 객체지향 프로그래밍의 중요한 목적 중의 하나입니다.



**예제 코드에서 참조변수의 타입을 ArrayList 타입이 아니라 Collection 타입으로 한 이유는 무엇일까요?**

Collection에 없고 ArrayList에만 있는 메서드를 사용하는게 아니라면, Collection 타입의 참조변수로 선언하는 것이 좋습니다. 만일 Collection 인터페이스를 구현한 다른 클래스, 예를 들어 LinkedList로 바꿔야 한다면 선언문 하나만 변경하면 나머지 코드는 검토하지 않아도 됩니다. 참조변수 타입이 Collection 이므로 Collection에 정의되지 않은 메서드는 사용되지 않았을 것이 확실하니까요. 그러나 참조변수 타입을 ArrayList로 했다면, 선언문 이후의 문장들을 검토해야 합니다. Collection에 정의되지 않은 메서드를 호출했을 수 있기 때문입니다.



Map 인터페이스를 구현한 컬렉션 클래스는 키(Key)와 값(Value)을 쌍(pair)으로 저장하고 있기 때문에 iterator()를 직접 호출할 수 없고, 그 대신 keySet()이나 entrySet()과 같은 메서드를 통해서 키와 값을 각각 따로 Set의 형태로 얻어 온 후에 다시 iterator()를 호출해야 Iteraotr를 얻을 수 있습니다. 

```java
Map map = new HashMap();
...
Iterator it = map.entrySet().iterator();
```



## 2. ArrayList 요소들을 iterator로 출력

List 클래스들은 저장순서를 유지하기 때문에 Iterator를 이용해서 읽어 온 결과 역시 저장 순서와 동일하지만, Set 클래스들은 각 요소간의 순서가 유지 되지 않기 때문에 Iterator를 이용해서 저장된 요소들을 읽어 와도 처음에 저장된 순서와 같지 않습니다.

```java
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        Iterator it = list.iterator();

        while(it.hasNext()) {
            Object obj = it.next();
            System.out.println(obj);
        }

    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=61138:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
1
2
3
4
5

종료 코드 0(으)로 완료된 프로세스
```



## 3. ListIterator와 Enumeration

Enumeration은 컬렉션 프레임워크가 만들어지기 전에 사용한 인터페이스입니다. 따라서 Iterator의 구버전이라고 생각하면 됩니다. 이전 버전인 Enumeration으로 작성된 소스 코드와의 호완성을 위해서 남겨 두고 있을 뿐이므로 가능하면 Enumeration 대신 Iterator를 사용합니다.

ListIterator는 Iterator를 상속받아서 기능을 추가한 것입니다. 컬렉션의 요소에 접근할 때 Iterator는 단방향으로만 이동할 수 있지만, ListIterator는 양방향으로 이동이 가능합니다. 단, ArrayList, LinkedList처럼 List 인터페이스를 구현한 컬렉션에서만 사용할 수 있습니다.

- **Enumeration**: Iterator의 구버전
- **ListIterator**: Iterator에 양방향 조회 기능을 추가(List 인터페이스를 구현한 경우에만 사용)



Enumeration, Iterator, ListIterator 의 공통적인 메서드는 다음과 같습니다.

**Enumeration의 메서드**

| 메서드                    | 설명                                                         |
| ------------------------- | ------------------------------------------------------------ |
| boolean hasMoreElements() | 읽어 올 요소가 남아있는지 확인.<br />있으면 true, 없으면 false를 반환합니다. Iterator의 hasNext()와 동일합니다. |
| Object nextElement()      | 다음 요소를 읽기. nextElement()를 호출하기 전, hasMoreElements()를 호출해서<br />읽어올 요소가 남아있는지 확인하는 것이 안전합니다. Iterator의 next()와 동일합니다. |



**ListIterator의 메서드**

| 메서드                | 설명                                                         |
| --------------------- | ------------------------------------------------------------ |
| void add(Object o)    | 컬렉션에 새로운 객체(o)를 추가. (선택적 기능)                |
| boolean hasNext()     | 읽어 올 다음 요소가 남아있는지 확인. <br />있으면 true, 없으면 false를 반환합니다. |
| boolean hasPrevious() | 읽어 올 이전 요소가 남아있는지 확인. <br />있으면 true, 없으면 false를 반환합니다. |
| Object next()         | 다음 요소를 읽기. <br />next()를 호출하기 전, hasNext()를 호출해서 읽어 올 요소가 있는지 확인하는 것이 안전합니다. |
| Object previous()     | 이전 요소를 읽기. <br />previous()를 호출하기 전, hasPrevious()를 호출해서 읽어 올 요소가 있는지 확인하는 것이 안전합니다. |
| int nextIndex()       | 다음 요소의 index를 반환.                                    |
| int previousIndex()   | 이전 요소의 index를 반환.                                    |
| void remove()         | next() 또는 previous()로 읽어 온 요소를 삭제. <br />반드시 next()나 previous()를 먼저 호출한 다음에 이 메서드를 호출해야 합니다. (선택적 기능) |
| void set(Object o)    | next() 또는 previous()로 읽어 온 요소를 지정된 객체(o)로 변경. <br />반드시 next()나 previous()를 먼저 호출한 다음에 이 메서드를 호출해야 합니다. (선택적 기능) |



**ListIterator 메서드 예제**

ListIterator는 단방향으로만 이동하기 때문에 컬렉션의 마지막 요소에 도착하면 더 이상 사용할 수 없지만, ListIterator는 양방향으로 이동하기 때문에 각 요소간의 이동이 자유롭습니다. 단, 이동하기 전에 반드시 hasNext() 또는 hasPrevious()를 호출해서 이동할 수 있는지 확인해야 합니다.

```java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        ListIterator it = list.listIterator();

        while(it.hasNext()) {
            System.out.print(it.next()); // 순방향으로 진행하면서 읽어온다.
        }
        System.out.println();

        while(it.hasPrevious()) {
            System.out.print(it.previous()); // 역방향으로 진행하면서 읽어온다.
        }
        System.out.println();
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=62273:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
12345
54321

종료 코드 0(으)로 완료된 프로세스
```



**Iterator remove 메서드 예제**

```java
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        ArrayList original = new ArrayList(10);
        ArrayList copy1 = new ArrayList(10);
        ArrayList copy2 = new ArrayList(10);

        for (int i = 0; i < 10; i++) {
            original.add(i+"");
        }

        Iterator it = original.iterator();

        while (it.hasNext()) {
            copy1.add(it.next());
        }

        System.out.println("= Original에서 copy1로 복사(copy) =");
        System.out.println("original:" + original);
        System.out.println("copy1 = " + copy1);
        System.out.println();

        it = original.iterator(); // Iterator는 재사용이 안되므로, 다시 얻어와야 합니다.

        while(it.hasNext()) {
            copy2.add(it.next());
            it.remove();
        }

        System.out.println("= Original에서 copy2로 이동(move) =");
        System.out.println("orginal:" + original);
        System.out.println("copy2 = " + copy2);
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=62417:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
= Original에서 copy1로 복사(copy) =
original:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
copy1 = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

= Original에서 copy2로 이동(move) =
orginal:[]
copy2 = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

종료 코드 0(으)로 완료된 프로세스
```