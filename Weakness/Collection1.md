# 컬렉션 프레임워크(Collections Framwork)

1. 컬렉션 프레임웍(Collection Framework)

컬렉션 프레임웍 : 데이터 군을 저장하는 클래스들을 표준화한 설계

컬렉션(Collection) : 다수의 데이터 (데이터 그룹)

프레임웍 : 표준화된 프로그래밍 방식

\* Java API문서에서는 컬렉션 프레임웍을 데이터 군을 다루고 표현하기 위한 단일화된 구조(architecture)라고 정의한다.

 

JDK1.2부터 컬렉션 프레임웍이 등장하면서 다양한 종류의 컬렉션 클래스가 추가되고, 모든 컬렉션 클래스를 표준화된 방식으로 다룰 수 있도록 체계화되었다.

 

1.1 컬렉션 프레임웍의 핵심 인터페이스



![img](https://blog.kakaocdn.net/dn/byJ78f/btriaP2T43k/k5tgQzoIC9igqUAVbOoGd1/img.png)



컬렉션 프레임웍에서는 컬렉션(데이터 그룹)을 크게 3가지 타입이 존재한다고 인식하고 각 컬렉션을 다루는데 필요한 기능을 가진 3개의 인터페이스를 정의하였다.

인터페이스 List, 인터페이스 Set, 인터페이스 Map

인터페이스 List와 Set의 공통된 부분을 뽑아 만듣 것이 인터페이스 Collection이다.

| 인터페이스                                               | 특징                                                         |
| -------------------------------------------------------- | ------------------------------------------------------------ |
| List                                                     | 순서가 있는 데이터의 집합. 데이터의 중복을 허용한다. 예 ) 대기자 명단 |
| 구현 클래스 : ArrayList, LinkedList, Stack, Vector 등    |                                                              |
| Set                                                      | 순서를 유지하지 않는 데이터의 집합. 데이터의 중복을 허용하지 않는다. 예 ) 양의 정수집합, 소수의 집합 |
| 구현 클래스 : HashSet, TreeSet 등                        |                                                              |
| Map                                                      | 키(key)와 값(value)의 쌍(pair)으로 이루어진 데이터의 집합. 순서는 유지되지 않으며, 키는 중복을 허용하지 않고, 값은 중복을 허용한다. 예 ) 우편번호, 지역번호(전화번호) |
| 구현 클래스 : HashMap, TreeMap, Hashtable, Properties 등 |                                                              |

\* 키(Key) : 데이터 집합 중에서 어떤 값(value)을 찾는데 열쇠(key)가 된다는 의미에서 붙여진 이름. 그래서 키(Key)는 중복을 허용하지 않는다.

 

컬렉션 프레임웍의 모든 컬렉션 클래스들은 List, Set, Map 중의 하나를 구현하고 있다.

그러나 Vector, Stack, Hashtable, Properties와 같은 클래스들은 컬렉션 프레임웍이 만들어지기 이전부터 존재하던 것이기 때문에 컬렉션 프레임웍의 명명법을 따르지 않는다.



![img](https://blog.kakaocdn.net/dn/cocybG/btrigseSVl4/WKe9Dg3ZVqGCZFDlnhP4j1/img.png)



 

Collection 인터페이스

List와 Set의 조상인 Collection인터페이스에 정의된 메서드

Collection인터페이스는 컬렉션 클래스에 저장된 데이터를 읽고, 추가하고 삭제하는 등 컬렉션을 다루는데 가장 기본적인 메서드들을 정의하고 있다.

반환타입이 boolean인 메서드들은 작업을 성공하거나 사실이면 true, 그렇지 않으면 false를 반환한다.

| 메서드                                                       | 설명                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| boolean add(Object o) boolean addAll(Collection c)           | 지정된 객체(o) 또는 Collection(c)의 객체들을 Collection에 추가한다. |
| void clear()                                                 | Collection의 모든 객체를 삭제한다.                           |
| boolean contains(Object o) boolean containsAll(Collection c) | 지정된 객체(o) 또는 Collection의 객체들이 Collection에 포함되어 있는지 확인한다. |
| boolean equals(Object o)                                     | 동일한 Collection인지 비교한다.                              |
| int hashCode()                                               | Collection의 hash code를 반환한다.                           |
| boolean isEmpty()                                            | Collection이 비어있는지 확인한다.                            |
| Iterator iterator()                                          | Collection의 Iterator를 얻어서 반환한다.                     |
| boolean reamove(Object o)                                    | 지정된 객체를 삭제한다.                                      |
| boolean removeAll(Collection c)                              | 지정된 Collection에 포함된 객체들을 삭제한다.                |
| boolean retainAll(Collection c)                              | 지정된 Collection에 포함된 객체만을 남기고 다른 객체들은 Collection에서 삭제한다. 이 작업으로 인해 Collection에 변화가 있으면 true, 그렇지 않으면 false를 반환한다. |
| Object[] toArray()                                           | Collection에 저장된 객체를 객체배열(Object[])로 반환한다.    |
| Object[] toArray(Object[] a)                                 | 지정된 배열에 Collection의 객체를 저장해서 반환한다.         |

 

List인터페이스

List인터페이스는 중복을 허용하면서 저장순서가 유지되는 컬렉션을 구현하는데 사용된다.



![img](https://blog.kakaocdn.net/dn/b6vtWa/btricEAqJeG/72Lh1BcXjH9DfZ9E2UVoaK/img.png)



List인터페이스에 정의된 메서드

| void add(int index, Object element) boolean addAll(int index, Collection c) | 지정된 위치(index)에 객체(element) 또는 컬렉션에 포함된 객체들을 추가한다. |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| Object get(int index)                                        | 지정된 위치(index)에 있는 객체를 반환한다.                   |
| int indexOf(Object o)                                        | 지정된 객체의 위치(index)를 반환한다. (List의 첫 번째 요소부터 순방향으로 찾는다.) |
| int lastIndexOf(Object o)                                    | 지정된 객체의 위치(index)를 반환한다. (List의 마지막 요소부터 역방향으로 찾는다.) |
| ListIterator listIterator() ListIterator listIterator(int index) | List의 객체에 접근할 수 있는 ListIterator를 반환한다.        |
| Object remove(int index)                                     | 지정된 위치(index)에 있는 객체를 삭제하고 삭제된 객체를 반환한다. |
| Object set(int index, Object element)                        | 지정된 위치(index)에 객체(element)를 저장한다.               |
| void sort(Comparator c)                                      | 지정된 비교자(comparator)로 List를 정렬한다.                 |
| List subList(int fromIndex, int toIndex)                     | 지정된 범위(fromIndex부터 toIndex)에 있는 객체를 반환한다.   |

 

Set인터페이스

Set인터페이스는 중복을 허용하지 않고 저장순서가 유지되지 않는 컬렉션 클래스를 구현하는데 사용한다.

Set인터페이스를 구현한 클래스로는 hashSet, TreeSet 등이 있다.



![img](https://blog.kakaocdn.net/dn/Ibwok/btrigCalZ8A/HOCQRmfj92GyTpKKMJCNvk/img.png)



 

Map인터페이스

Map인터페이스는 키(Key)와 값(value)을 하나의 쌍으로 묶어서 저장하는 컬렉션 클래스를 구현하는 데 사용된다.

키는 중복될 수 없지만 값은 중복을 허용한다.

기존에 저장된 데이터와 중복된 키와 값을 저장하면 기존의 값은 없어지고 마지막에 저장된 값이 남게 된다.

Map인터페이스를 구현한 클래스로는 hashtable, HashMap, LinkedHashMap, SortedMap, TreeMap 등이 있다.

\* Map이란 개념은 어떤 두 값을 연결한다는 의미에서 붙여진 이름이다.



![img](https://blog.kakaocdn.net/dn/pkbW3/btrikMwc9cf/GqMwJsposYISC3pm0lOaKk/img.png)



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

Map인터페이스에서 값(value)은 중복을 허용하기 때문에 Collection타입으로 반환하고, 키(Key)는 중복을 허용하지 않기 때문에 Set타입으로 반환한다.

 

Map.Entry인터페이스

Map.Entry인터페이스는 Map인터페이스의 내부 인터페이스이다.

내부 인터페이스(inner interface) : 인터페이스 안에 인터페이스를 정의

 

Map에 저장되는 key-value쌍을 다루기 위해 내부적으로 Entry인터페이스를 정의해놓았다.

```
public interface Map
{
	interface Entry
    {
    	Obejct getKey();
        Object getValue();
        Object setValue(Object value);
        boolean equals(Object o);
        int hashCode();
    }
}
```

| 메서드                        | 설명                                      |
| ----------------------------- | ----------------------------------------- |
| boolean equals(Object o)      | 동일한 Entry인지 비교한다.                |
| Object getKey()               | Entry의 key객체를 반환한다.               |
| Object getValue()             | Entry의 value객체를 반환한다.             |
| int hashCode()                | Entry의 해시코드를 반환한다.              |
| object setValue(Object value) | Entry의 value객체를 지정된 객체로 바꾼다. |

 

1.2 ArrayList

ArrayList : List인터페이스를 구현하기 때문에 데이터의 저장순서가 유지되고 중복을 허용한다.

기존의 Vector를 개선한 것으로 Vector와 구현원리와 기능적인 측면에서 동일하다.

ArrayList는 Object배열을 이용해서 데이터를 순차적으로 저장한다.

첫 번째로 저장한 객체는 Object배열의 0번째 위치에 저장되고 그 다음에 저장하는 객체는 1번째 위치에 저장된다.

계속 배열에 순서대로 저장되며, 배열에 더 이상 저장할 공간이 없으면 보다 큰 새로운 배열을 생성해서 기존의 배열에 저장된 내용을 새로운 배열로 복사한 다음에 저장된다.

```
public class ArrayList extends AbstractList 
		implements List, RandomAccess, Cloneable, java.io.Serializable
{
	//Object배열을 멤버변수로 선언하고 있다.
    //선언된 배열의 타입이 모든 객체의 최고조상인 Object이기 때문에
    //모든 종류의 객체를 담을 수 있다.
	transient Object[] elementData;	//Object배열
}
```

\* transient는 직렬화(serialization)와 관련된 제어자이다.

ArrayList에서 제공하는 메서드

| 메서드                                                | 설명                                                         |
| ----------------------------------------------------- | ------------------------------------------------------------ |
| ArrayList()                                           | 크기가 0인 ArrayList를 생성                                  |
| ArrayList(Collection c)                               | 주어진 컬렉션이 저장된 ArrayList를 생성                      |
| ArrayList(int initialCapacity)                        | 지정된 초기용량을 갖는 ArrayList를 생성                      |
| ArrayList(int initialCapacity, int capacityIncrement) | 지저오딘 초기용량과 용량의 증분을 갖는 ArrayList를 생성      |
| boolean add(Object o)                                 | ArrayList의 마지막 객체를 추가. 성공하면 treu                |
| void add(int index, Object element)                   | 지저오딘 위치(index)에 객체를 저장                           |
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

```
/* list2에서 list1과 공통되는 요소들을 찾아서 삭제 */
for(int i = list2.size() - 1; i >= 0; i--)
{
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

ArrayList는 생성할 때 지정한 크기보다 더 많은 객체를 저장하면 자동적으로 크기가 늘어나지만 처리 시간이 많이 소요될 수 있다.

 

ArrayList나 Vector같이 배열을 이용한 자료구조는 데이터를 읽어오고 저장하는 데는 효율이 좋지만,

용량을 변경해야할 때는 새로운 배열을 생성한 후 기존의 배열로부터 새로 생성된 배열로 데이터를 복사해야하기 때문에 상당히 효율이 떨어진다.

때문에 처음에 인스턴스를 생성할 때, 저장할 데이터의 개수를 잘 고려하여 충분한 용량의 인스턴스를 생성하는 것이 좋다.

 

\* 인터페이스를 구현할 때는 인터페이스에 정의된 모든 메서드를 구현해야 한다. 일부 메서드만 구현했다면 추상클래스로 선언해야한다. JDK1.8부터 List인터페이스에는 3개의 디폴트 메서드가 추가되었으며, 이 메서드들은 구현하지 않아도 된다.

 

```
public boolean contains(Object o) {return false;}
public boolean equals(object o) {return false;}
public Object set(int index, Object element) {return null;}
public void add(int index, Object element) {}
public int indexOf(Object o) {return -1;}
public int lastIndexOf(Object o) {return -1;}
public String toString() {return "";}
```

 

remove 메서드

```
/* 지정된 위치(index)에 있는 객체를 삭제하고 삭제한 객체를 반환한다. */
//삭제할 객체의 바로 아래에 있는 데이터를 한 칸씩 위로 복사해서 삭제할 객체를 덮어쓴다.
//만일 삭제할 객체가 마지막 데이터라면, 복사할 필요없이 단순히 null로 변경해준다.
public Object remove(int index)
{
	Object oldObj = null;
    
    if(index < 0 || index >= size)
    	throw new IndexOutOfBoundsException("범위를 벗어났습니다.");
    oldObj = data[index];
    
    if(index != size-1)
    {
    	//1. 삭제할 데이터의 아래에 있는 데이터를 한 칸씩 위로 복사해서 삭제할 데이터를 덮어쓴다.
    	System.arraycopy(data, index+1, data, index, size-index-1);
    }
    
    //2. 데이터가 모두 한 칸씩 위로 이동하였으므로 마지막 데이터는 null로 변경해야한다.
    data[size-1] = null;
    //3. 데이터가 삭제되어 데이터의 개수가 줄었으므로 size의 값을 1 감소시킨다.
    size--;
    
    return oldObj;
}
```

 

배열의 경우, 배열에 객체를 순차적으로 저장하거나 객체를 마지막에 저장된 것부터 삭제하면 System.arraycopy()를 호출하지 않기 때문에 작업시간이 짧지만,

배열의 중간에 위치한 객체를 추가하거나 삭제하는 경우 System.arraycopy()를 호출해서 다른 데이터의 위치를 이동시켜 줘야 하기 때문에 다루는 개수가 많을수록 작업시간이 오래걸린다.

 

1.3 LinkedList

배열은 가장 기본적인 형태의 자료구조로, 구조가 간단하며 사용하기 쉽고 데이터를 읽어오는데 걸리는 시간(접근시간, access time)이 가장 빠르다는 장점을 가지고 있다.

배열의 단점으로는,

1. 크기를 변경할 수 없다.

\- 크기를 변경할 수 없으므로 새로운 배열을 생성해서 데이터를 복사하는 작업이 필요하다.

\- 실행속도를 향상시키기 위해서는 충분히 큰 크기의 배열을 생성해야 하므로 메모리가 낭비된다.

 

2. 비순차적인 데이터의 추가 또는 삭제에 시간이 많이 걸린다.

\- 차례대로 데이터를 추가하고 마지막에서부터 데이터를 삭제하는 것은 빠르지만,

\- 배열의 중간에 데이터를 추가하려면, 빈자리를 만들기 위해 다른 데이터들을 복사해서 이동해야 한다.

 

이러한 배열의 단점을 보완하기 위해 링크드 리스트(linked list)라는 자료구조가 고안되었다.

배열은 모든 데이터가 연속적으로 존재하지만,

링크드 리스트는 불연속적으로 존재하는 데이터를 서로 연결(link)한 형태로 구성되어 있다.



![img](https://blog.kakaocdn.net/dn/dYW2hi/btriA46s446/rm6ECmP28keazkOeNuiIo1/img.png)



링크드 리스트의 각 요소(node)들은 자신과 연결된 다음 요소에 대한 참조(주소값)와 데이터로 구성되어 있다.

```
class Node
{
	Node next;	//다음 요소의 주소를 저장
    Object obj;	//데이터를 저장
}
```

링크드 리스트에서의 데이터 삭제는, 삭제하고자 하는 요소의 이전요소가 삭제하고자 하는 요소의 다음 요소를 참조하도록 변경하면 된다. 단 하나의 참조만 변경하면 삭제가 이루어지므로, 배열처럼 데이터를 이동하기 위해 복사하는 과정이 없기 때문에 처리속도가 빠르다.

새로운 데이터를 추가할 때는 새로운 요소를 생성한 다음 추가하고자 하는 위치의 이전 요소의 참조를 새로운 요소에 대한 참조로 변경해주고, 새로운 요소가 그 다음 요소를 참조하도록 변경한다.

 

링크드 리스트는 이동방향이 단방향이기 때문에 다음 요소에 대한 접근은 쉽지만, 이전 요소에 대한 접근은 어렵다.

이 점을 보완한 것이 더블 링크드 리스트(이중 연결리스트, doubly linked list)이다.

더블 링크드 리스트는 링크드 리스트에 참조변수를 하나 더 추가하여 다음 요소에 대한 참조뿐 아니라 이전 요소에 대한 참조가 가능하도록 한 것이다. 링크드 리스트보다 각 요소에 대한 접근과 이동이 쉽다.

```
class Node
{
	Node next;	//다음 요소의 주소를 저장
    Node previous;	//이전 요소의 주소를 저장
    Object obj;		//데이터를 저장
}
```

 

더블 써큘러 링크드 리스트(이중 원형 연결리스트, doubly circular linked list)

더블 링크드 리스트의 접근성을 보다 향상시킨 것으로, 더블 링크드 리스트의 첫 번째 요소와 마지막 요소를 서로 연결시킨 것이다.

마지막 요소의 다음 요소는 첫 번째 요소가 되고, 첫 번째 요소의 이전 요소는 마지막 요소가 된다.

 

LinkedList의 생성자와 메서드

| 생성자 또는 메서드                       | 설명                                                         |
| ---------------------------------------- | ------------------------------------------------------------ |
| LinkedList()                             | LinkedList객체를 생성                                        |
| LinkedList(Collection c)                 | 주어진 컬렉션을 포함하는 LinkedList객체를 생성               |
| boolean add(Object o)                    | 지정된 객체(o)를 LinkedList의 끝에 추가. 저장에 성공하면 true, 실패하면 false |
| void add(int index, Object element)      | 지정된 위치(index)에 객체(element)를 추가                    |
| boolean addAll(Collection c)             | 주어진 컬렉션에 포함된 모든 요소를 LinkedList의 끝에 추가한다. 성공하면 true, 실패하면 false |
| boolean addAll(int index, Collection c)  | 지정된 위치(index)에 주어진 컬렉션에 포함된 모든 요소를 추가. 성공하면 true, 실패하면 false |
| void clear()                             | LinkedList의 모든 요소를 삭제                                |
| boolean contains(Object o)               | 지정된 객체가 LinkedList에 포함되었는지 알려줌               |
| boolean containsAll(Collection c)        | 지정된 컬렉션의 모든 요소가 포함되었는지 알려줌              |
| Object get(int index)                    | 지정된 위치(index)의 객체를 반환                             |
| int indexOf(Object o)                    | 지정된 객체가 저장된 위치(앞에서 몇 번째)를 반환             |
| boolean isEmpty()                        | LinkedList가 비어있는지 알려준다. 비어있으면 true            |
| Iterator iterator()                      | Iterator를 반환한다.                                         |
| int lastIndexOf(Object o)                | 지정된 객체의 위치(index)를 반환(끝부터 역순검색)            |
| ListIterator listIterator()              | ListIterator를 반환한다.                                     |
| ListIterator listIterator(int index)     | 지정된 위치에서부터 시작하는 ListIterator를 반환             |
| Object remove(int index)                 | 지정된 위치(index)의 객체를 LinkedList에서 제거              |
| boolean remove(Object o)                 | 지정된 객체를 LinkedList에서 제거. 성공하면 true, 실패하면 false |
| boolean removeAll(Collection c)          | 지정된 컬렉션의 요소와 일치하는 요소를 모두 삭제             |
| boolean retainAll(Collection c)          | 지정된 컬렉션의 모든 요소가 포함되어 있는지 확인             |
| Object set(int index, Object element)    | 지정된 위치(index)의 객체를 주어진 객체로 바꿈               |
| int size()                               | LinkedList에 저장된 객체의 수를 반환                         |
| List subList(int fromIndex, int toIndex) | LinkedList의 일부를 List로 반환                              |
| Object[] toArray()                       | LinkedList에 저장된 객체를 배열로 반환                       |
| Object[] toArray(Object[] a)             | LinkedList에 저장된 객체를 주어진 배열에 저장하여 반환       |
| Object element()                         | LinkedList의 첫 번째 요소를 반환                             |
| boolean offer(Object o)                  | 지정된 객체(o)를 LinkedList의 끝에 추가. 성공하면 true, 실패하면 false |
| Object peek()                            | LinkedList의 첫 번째 요소를 반환                             |
| object poll()                            | LinkedList의 첫 번째 요소를 반환. LinkedList에서는 제거된다. |
| Object remove()                          | LinkedList의 첫 번째 요소를 제거                             |
| void addFirst(Object o)                  | LinkedList의 맨 앞에 객체(o)를 추가                          |
| void addLast(Object o)                   | LinkedList의 맨 끝에 객체(o)를 추가                          |
| Iterator descendingIterator()            | 역순으로 조회하기 위한 DescendingIterator를 반환             |
| Object getFirst()                        | LinkedList의 첫번째 요소를 반환                              |
| Object getLast()                         | LinkedList의 마지막 요소를 반환                              |
| boolean offerFirst(Object o)             | LinkedList의 맨 앞에 객체(o)를 추가. 성공하면 true           |
| boolean offerLast(Object o)              | LinkedList의 맨 끝에 객체(o)를 추가. 성공하면 true           |
| Object peekFirst()                       | LinkedList의 첫번째 요소를 반환                              |
| Object peekLast()                        | LinkedList의 마지막 요소를 반환                              |
| Object pollFirst()                       | LinkedList의 첫번째 요소를 반환하면서 제거                   |
| Object pollLast()                        | LinkedList의 마지막 요소를 반환하면서 제거                   |
| Object pop()                             | removeFirst()와 동일                                         |
| void push(Object o)                      | addFirst()와 동일                                            |
| Object removeFirst()                     | LinkedList의 첫번째 요소를 제거                              |
| object removeLast()                      | LinkedList의 마지막 요소를 제거                              |
| boolean removeFirstOccurrence(Object o)  | LinkedList에서 첫번째로 일치하는 객체를 제거                 |
| boolean removeLastOccurrence(Object o)   | LinkedList에서 마지막으로 일치하는 객체를 제거               |

 

ArrayList와 LinkedList의 성능차이 비교

1. 순차적으로 추가/삭제하는 경우에는 ArrayList가 LinkedList보다 빠르다.

만일 ArrayList의 크기가 충분하지 않으면, 새로운 크기의 ArrayList를 생성하고 데이터를 복사하는 일이 발생해 순차적으로 데이터를 추가해도 ArrayList보다 LinkedList가 더 빠를 수 있다.

순차적으로 삭제한다는 것은 마지막 데이터부터 역순으로 삭제해나간다는 것을 의미하며, ArrayList는 마지막 데이터부터 삭제할 경우 각 요소들의 재배치가 필요하지 않기 때문에 상당히 빠르다. 마지막 요소의 값을 null로만 바꾸면 되기 때문이다.

 

2. 중간 데이터를 추가/삭제하는 경우에는 LinkedList가 ArrayList보다 빠르다.

LinkedList는 각 요소간의 연결만 변경해주면 되기 때문에 처리속도가 빠르다.

반면에 ArrayList는 각 요소들을 재배치하여 추가할 공간을 확보하거나 빈 공간을 채워야하기 때문에 처리속도가 늦다.

 

n번째 원소의 값 얻기

> n번째 데이터의 주소 = 배열의 주소 + n * 데이터 타입의 크기

```
//Object배열
Object[] arr = new Object[5];

/*
	arr[2]에 저장된 값을 읽으려 한다면
    n은 2, 모든 참조형 변수의 크기는 4byte, 생성된 배열의 주소는 0x100이므로
    3번째 데이터가 저장되어 있는 주소는 0x100 + 2 * 4 = 0x108이 된다.
*/
```

 

배열은 각 요소들이 연속적으로 메모리상에 존재하기 때문에 간단한 계산으로 n번째 데이터의 주소를 얻어서 저장된 데이터를 곧바로 읽어올 수 있지만,

LinkedList는 불연속적으로 위치한 각 요소들이 서로 연결된 것이기 때문에 처음부터 n번째 데이터까지 차례대로 따라가야만 원하는 값을 얻을 수 있다. 그래서 LinkedList는 저장해야하는 데이터의 개수가 많아질수록 데이터를 읽어 오는 시간, 즉 접근시간(access time)이 길어진다는 단점이 있다.

| 컬렉션     | 읽기(접근시간) | 추가 / 삭제 | 비고                                                |
| ---------- | -------------- | ----------- | --------------------------------------------------- |
| ArrayList  | 빠르다         | 느리다      | 순차적인 추가삭제는 더 빠름. 비효율적인 메모리 사용 |
| LinkedList | 느리다         | 빠르다      | 데이터가 많을수록 접근성이 떨어짐                   |

 

1.4 Stack과 Queue

스택 (stack) : 마지막에 저장한 데이터를 가장 먼저 꺼내게 되는 LIFO(Last In First Out)구조

\- 한 방향으로만 뺄 수 있는 구조

\- 넣은 순서와 꺼낸 순서가 뒤집어진다

\- 순차적으로 데이터를 추가하고 삭제하는 스택 -> ArrayList와 같은 배열기반의 컬렉션 클래스가 적합

큐 (queue) : 처음에 저장한 데이터를 가장 먼저 꺼내게 되는 FIFO(First In First Out)구조

\- 한 방향으로 넣고, 다른 한 방향으로 빼는 구조

\- 순서의 변경 없이 먼저 넣은 것을 먼저 꺼낸다

\- 데이터를 꺼낼 때 항상 첫 번째 저장된 데이터를 삭제하는 큐 -> ArrayList와 같은 배열기반의 컬렉션 클래스를 사용하면 데이터를 꺼낼 때마다 빈 공간을 채우기 위해 데이터의 복사가 발생하므로 데이터의 추가/삭제가 쉬운 LinkedList가 적합



![img](https://blog.kakaocdn.net/dn/2QBEw/btriA3T4btW/pBdGZLlvtFGr144LciUZ40/img.png)



 

Stack의 메서드

| 메서드                   | 설명                                                         |
| ------------------------ | ------------------------------------------------------------ |
| boolean empty()          | Stack이 비어있는지 알려준다.                                 |
| Object peek()            | Stack의 맨 위에 저장된 객체를 반환. pop()과 달리 Stack에서 객체를 꺼내지는 않음.(비었을 때는 EmptyStackException발생) |
| Object pop()             | Stack의 맨 위에 저장된 객체를 꺼낸다. (비었을 때는 EmptyStackException발생) |
| Object push(Object item) | Stack에 객체(item)를 저장한다.                               |
| int search(Object o)     | Stack에 주어진 객체(o)를 찾아서 그 위치를 반환. 못찾으면 -1을 반환. (배열과 달리 위치는 0이 아닌 1부터 시작) |

 

Queue의 메서드

| 메서드                  | 설명                                                         |
| ----------------------- | ------------------------------------------------------------ |
| boolean add(Object o)   | 지정된 객체를 Queue에 추가한다. 성공하면 true를 반환. 저장공간이 부족하면 IllegalStateException발생 |
| Object remove()         | Queue에서 객체를 꺼내 반환. 비어있으면 NoSuchElementException발생 |
| Object element()        | 삭제없이 요소를 읽어온다. peek과 달리 Queue가 비었을 때 NoSuchElementException발생 |
| boolean offer(Object o) | Queue에 객체를 저장. 성공하면 true, 실패하면 false를 반환    |
| Object poll()           | Queue에서 객체를 꺼내서 반환. 비어있으면 null을 반환         |
| Object peek()           | 삭제없이 요소를 읽어온다. Queue가 비어있으면 null을 반환     |

 

자바에서는 스택을 Stack클래스로 구현하여 제공하고 있지만 큐는 Queue인터페이스로만 정의해 놓았을 뿐 별도의 클래스를 제공하고 있지 않다. 대신 Queue인터페이스를 구현한 클래스들이 있어서 이 들 중 하나를 선택해서 사용하면 된다.

```
Stack stack = new Stack();
Queue queue = new LinkedList();
```

 

스택과 큐의 활용

스택 - 수식계산, 수식괄호검사, 워드프로세서의 undo/redo, 웹브라우저의 뒤로/앞으로

큐 - 최근사용문서, 인쇄작업 대기목록, 버퍼(buffer)

 

PriorityQueue

Queue인터페이스의 구현체 중의 하나로, 저장한 순서에 관계없이 우선순위(priority)가 높은 것부터 꺼내게 된다. null은 저장할 수 없으며, null을 저장하면 NullPointerException이 발생한다.

PriorotyQueue는 저장공간으로 배열을 사용하며, 각 요소를 힙(heap)이라는 자료구조의 형태로 저장한다.

힙은 이진트리의 한 종류로 가장 큰 값이나 가장 작은 값을 빠르게 찾을 수 있다.

 

우선순위는 숫자가 작을수록 높다. Integer와 같은 Number의 자손들은 자체적으로 숫자를 비교하는 방법을 정의하고 있기 때문에 비교 방법을 지정해 주지 않아도 된다.

객체를 저장할 경우, 각 객체의 크기를 비교할 수 있는 방법을 제공해야한다.

 

Deque(Double-Ended Queue)

Queue의 변형으로, 한 쪽 끝으로만 추가/삭제 할 수 있는 Queue와 달리,

Deque(덱, 또는 디큐)은 양쪽 끝에 추가/삭제가 가능하다. Deque의 조상은 Queue이며, 구현체로는 ArrayDeque과 LinkedList 등이 있다.



![img](https://blog.kakaocdn.net/dn/czkSvD/btriFiXc23R/QqgaAs10laFprtcJATDpF0/img.png)



| Deque       | Queue   | Stack  |
| ----------- | ------- | ------ |
| offerLast() | offer() | push() |
| pollLast()  | -       | pop()  |
| pollFirst() | poll()  | -      |
| peekFirst() | peek()  |        |
| peekLast()  | -       | peek() |

 

1.5 Iterator, ListIterator, Enumeration

Iterator, ListIterator, Enumeration은 모두 컬렉션에 저장된 요소를 접근하는데 사용되는 인터페이스이다.

Enumeration은 Iterator의 구버젼이며, Listiterator는 Iterator의 기능을 향상시킨 것이다.

 

Iterator

컬렉션 프레임웍에서는 컬렉션에 저장된 요소들을 읽어오는 방법을 표준화하였다.

컬렉션에 저장된 각 요소에 접근하는 기능을 가진 Iterator인터페이스를 정의하고,

Collection인터페이스에는 Iterator(Iterator를 구현한 클래스의 인스턴스)를 반환하는 iterator()를 정의하고 있다.

```
public interface Iterator
{
	boolean hasNext();
    Object next();
    void remove();
}

public interface Collection
{
	public Iterator iterator();
}
```

 

iterator()는 Collection인터페이스에 정의된 메서드이므로 Collection인터페이스의 자손인 List와 Set에도 포함되어 있다.

컬렉션 클래스에 대해 iterator()를 호출하여 Iterator를 얻은 다음 반복문, 주로 while문을 사용해서 컬렉션 클래스의 요소들을 읽어 올 수 있다.

| 메서드            | 설명                                                         |
| ----------------- | ------------------------------------------------------------ |
| boolean hasNext() | 읽어 올 요소가 남아있는지 확인한다. 있으면 true, 없으면 false를 반환한다. |
| Object next()     | 다음 요소를 읽어온다. next()를 호출하기 전에 hasNext()를 호출해서 읽어 올 요소가 있는지 확인하는 것이 안전하다. |
| void remove()     | next()로 읽어 온 요소를 삭제한다. next()를 호출한 다음에 remove()를 호출해야한다.(선택적 가능) |

\* 선택적 기능(optional operation)이라고 표시된 것은 반드시 구현하지 않아도 된다는 뜻이다.

 

```
//ArrayList에 저장된 요소들 출력
List list = new ArrayList();
Iterator it = list.iterator();

while(it.hasNext())
{
	System.out.println(it.next());
}
```

 

Map인터페이스를 구현한 컬렉션 클래스는 키(key)와 값(value)을 쌍(pair)으로 저장하고 있기 때문에 iterator()를 직접 호출할 수 없고, keySet()이나 entrySet()과 같은 메서드를 통해서 키와 값을 각각 따로 Set의 형태로 얻어 온 후에 다시 iterator()를 호출해야 Iterator를 얻을 수 있다.

```
Map map = new HashMap();
Iterator it = map.keySet().iterator();

Set eSet = map.entrySet();
Iterator list = eSet.iterator();
//Iterator list = map.entrySet().iterator();
```



![img](https://blog.kakaocdn.net/dn/bSs5uh/btriMJzgyD0/CgKVKVjtJ2eU4LxfRzPNMk/img.png)



 

유사한 코드 StringBuffer

```
StringBuffer sb = new StringBuffer();
sb.append("A");
sb.append("B");
sb.append("C");

//append메서드가 수행결과로 StringBuffer를 리턴하기 때문에 가능
StringBuffer sb = new StringBuffer();
sb.append("A").append("B").append("C");	//StringBuffer append(String str)

//만일 void append(String str)와 같이 void를 리턴하도록 선언되어 있다면 위와같이 사용할 수 없다.
//append메서드의 호출결과가 void이기 때문에 void에 append메서드를 호출할 수 없기 때문이다.
```

 

List클래스들은 저장순서를 유지하기 때문에 Iterator를 이용해 읽어 온 결과 역시 저장순서와 동일하다.

Set클래스들은 각 요소간의 순서가 유지되지 않기 때문에 Iterator를 이용해서 저장된 요소들을 읽어와도 처음에 저장된 순서와 같지 않다.

 

ListIterator와 Enumeration

Enumeration은 컬렉션 프레임웍이 만들어지기 이전에 사용하던 것으로 Iterator의 구버전이라고 생각하면 된다.

이전 버전으로 작성된 소스와의 호환을 위해서 남겨 두고 있다.

 

ListIterator는 Iterator를 상속받아서 기능을 추가한 것으로, 컬렉션의 요소에 접근할 때 Iterator는 단방향으로만 이동할 수 있는 데 반해 ListIterator는 양방향으로의 이동이 가능하다. 다만, ArrayList나 LinkedList와 같이 List인터페이스를 구현한 컬렉션에서만 사용할 수 있다.

 

> Enumeration - Iterator의 구버전
> ListIterator.- Iterator에 양방향 조회기능추가 (List를 구현한 경우만 사용가능)

 

Enumeration인터페이스의 메서드

| 메서드                    | 설명                                                         |
| ------------------------- | ------------------------------------------------------------ |
| boolean hasMoreElements() | 읽어 올 요소가 남아있는지 확인한다. 있으면 true, 없으면 false를 반환 Iterator의 hasNext()와 같다. |
| Object nextElement()      | 다음 요소를 읽어온다. nextElement()를 호출하기 전에 hasMoreElelment()를 호출해서 읽어 올 요소가 남아있는지 확인하는 것이 안전하다. Iterator의 next() 와 같다. |

 

ListIterator의 메서드

| 메서드                | 설명                                                         |
| --------------------- | ------------------------------------------------------------ |
| void add(Object o)    | 컬렉션에 새로운 객체(o)를 추가한다. (선택적 기능)            |
| boolean hasNext()     | 읽어 올 다음 요소가 남아있는지 확인한다. 있으면 true, 없으면 false를 반환한다. |
| boolean hasPrevious() | 읽어 올 이전 요소가 남아있는지 확인한다. 있으면 true, 없으면 false를 반환한다. |
| Object next()         | 다음 요소를 읽어온다. next()를 호출하기 전에 hasNext()를 호출해서 읽어 올 요소가 있는지 확인하는 것이 안전하다. |
| Object previous()     | 이전 요소를 읽어온다. previou()를 호출하기 전에 hasPrevious()를 호출해서 읽어 올 요소가 있는지 확인하는 것이 안전하다. |
| int nextIndex()       | 다음 요소의 index를 반환한다.                                |
| int previousIndex()   | 이전 요소의 index를 반환한다.                                |
| void remove()         | next() 또는 previous()로 읽어 온 요소를 삭제한다. 반드시 next()나 previous()를 먼저 호출한 다음에 이 메서드를 호출해야 한다. (선택적 기능) |
| void set(Object o)    | next() 또는 previous()로 읽어 온 요소를 지정된 객체(o)로 변경한다. 반드시 next()나 previous()를 먼저 호출한 다음에 이 메서드를 호출해야 한다. (선택적 기능) |

 

Iterator는 단방향으로만 이동하기 때문에 컬렉션의 마지막 요소에 다다르면 더 이상 사용할 수 없지만, ListIterator는 양방향으로 이동하기 때문에 각 요소간의 이동이 자유롭다.

다만 이동하기 전에 반드시 hasNext()나 hasPrevious()를 호출해서 이동할 수 있는지 확인해야 한다.

 

선택적 기능(optional operation) : 반드시 구현하지 않아도 되는 것.

```
//예를 들어
//Iterator인터페이스를 구현하는 클래스에서 remove()는 선택적인 기능이므로 구현하지 않아도 된다.
//그렇지만 인터페이스로부터 상속받은 메서드는 추상메서드라 메서드의 몸통(body)을 반드시 만들어 주어야 한다.

public void remove() {
	throw new UnsupportedOperationException();
}

/* 
	단순히 public void remove(){};와 같이 구현하는 것보다
    예외를 던져 구현되지 않은 기능이라는 것을 메서드를 호출하는 쪽에 알리는 것이 좋다.
*/
```

 

Iterator의 remove()는 단독으로 쓰일 수 업속, next()와 같이 써야 한다.

특정 위치의 요소를 삭제하는 것이 아니라 읽어 온 것을 삭제한다. next()의 호출 없이 remove()를 호출하면 IllegalStateException이 발생한다.

 

1.6 Arrays

Arrays클래스에는 배열을 다루는데 유용한 메서드가 정의되어 있으며 Arrays에 정의된 메서드는 모두 static메서드이다.

```
//Arrays에 정의된 toString()
//모든 기본형 배열과 참조형 배열 별로 하나씩 정의되어 있다.
static String toString(boolean[] a)
static String toString(byte[] a)
static String toString(char[] a)
static String toString(short[] a)
static String toString(int[] a)
static String toString(long[] a)
static String toString(float[] a)
static String toString(double[] a)
static String toString(Object[] a)
```

 

배열의 복사 - copyOf(), copyOfRange()

copyOf() - 배열 전체를 복사해서 새로운 배열을 만들어 반환

copyOfRange() - 배열의 일부를 복사해서 새로운 배열을 만들어 반환. 지정된 범위의 끝은 포함되지 않는다.

```
int[] arr = {0, 1, 2, 3, 4};
int[] arr2 = Arrays.copyOf(arr, arr.length);	//arr2 = [0, 1, 2, 3, 4]
int[] arr3 = Arrays.copyOf(arr, 3);		//arr3 = [0, 1, 2]
int[] arr4 = Arrays.copyOf(arr, 7);		//arr4 = [0, 1, 2, 3, 4, 0, 0]

int[] arr5 = Arrays.copyOfRange(arr, 2, 4);	//arr5 = [2, 3]	<- 4는 포함되지 않음
int[] arr6 = Arrays.copyOfRange(arr, 0, 7);	//arr6 = [0, 1, 2, 3, 4, 0, 0]
```

 

배열 채우기 - fill(), setAll()

fill() - 배열의 모든 요소를 지정된 값으로 채운다.

setAll() - 배열을 채우는데 사용할 함수형 인터페이스를 매개변수로 받는다. 이 메서드를 호출할 때는 함수형 인터페이스를 구현한 객체를 매개변수로 지정하던가 아니면 람다식(lambda expression)을 지정해야 한다.

```
int[] arr = new int[5];
Arrays.fill(arr, 9);	//arr = [9, 9, 9, 9, 9]
Arrays.setAll(arr, i -> (int)(Math.random()*5) + 1);	//arr = [1, 5, 2, 1, 1]
```

 

배열의 정렬과 검색 - sort(0, binarySearch()

sort() - 배열을 정렬할 때 사용

binarySearch() - 배열에 저장된 요소를 검색할 때 사용. 배열에서 지정된 값이 저장된 위치(index)를 찾아서 반환하는데, 반드시 배열이 정렬된 상태이어야 올바른 결과를 얻는다. 그리고 만일 검색한 값과 일치하는 요소들이 여러 개 있다면, 이 중에서 어떤 것의 위치가 반환될지는 알 수 없다.

```
int[] arr = {3, 2, 0, 1, 4};	//정렬되지 않은 배열
int idx = Arrays.binarySearch(arr, 2);	//idx = -5 <- 잘못된 결과

Arrays.sort(arr);	//배열 arr을 정렬한다.
System.out.println(Arrays.toString(arr));	//[0, 1, 2, 3, 4]
int idx = Arrays.binarySearch(arr, 2);	//idx = 2 <- 올바른 결과
```

 

linear search (순차 검색) - 배열의 첫 번째 요소부터 순서대로 하나씩 검색하는 것으로, 배열이 정렬되어 있을 필요는 없지만 배열의 요소를 하나씩 비교하기 때문에 시간이 많이 걸린다.

 

binary search(이진 검색) - 배열의 검색 범위를 반복적으로 절반씩 줄여가면서 검색하기 때문에 검색 속도가 빠르다. 배열의 길이가 10배 늘어나도 검색 횟수는 3~4회 밖에 늘어나지 않으므로 큰 배열의 검색에 유리하다. 단, 배열이 정렬되어 있는 경우에만 사용할 수 있다.

 

문자열의 비교와 출력 - equals(), toString(), deepEquals(), deepToString()

toString() - 배열의 모든 요소를 문자열로 편하게 출력할 수 있다. 일차원 배열에만 사용할 수 있으므로, 다차원 배열에는 deepToString()을 사용해야 한다.

deepToString() - 배열의 모든 요소를 재귀적으로 접근해서 문자열을 구성하므로 2차원뿐만 아니라 3차원 이상의 배열에 대해서도 동작한다.

```
int[] arr = {0, 1, 2, 3, 4};
int[][] arr2D = {{11, 12}, {21, 22}};

System.out.println(Arrays.toString(arr));	//[0, 1, 2, 3, 4]
System.out.println(Arrays.deepToString(arr2D));	//[[11, 12], [21, 22]]
```

 

equals() - 두 배열에 저장된 모든 요소를 비교해서 같으면 true, 다르면 false를 반환한다. 일차원 배열에만 사용가능하므로, 다차원 배열에는 deepEquals()를 사용해야 한다.

```
String[][] str2D = new String[][]{{"aaa", "bbb"}, {"AAA", "BBB"}};
String[][] str2D2 = new String[][]{{"aaa", "bbb"}, {"AAA", "BBB"}};

//2차원 String배열을 equals()로 비교하면 배열에 저장된 내용이 같은데도 false를 결과로 얻는다.
//다차원 배열은 '배열의 배열'의 형태로 구성하기 때문에
//equals()로 비교하면, 문자열을 비교하는 것이 아니라 '배열에 저장된 배열의 주소'를 비교하게 된다.
//서로 다른 배열은 항상 주소가 다르므로 false를 결과로 얻는다.
System.out.println(Arrays.equals(str2D, str2D2));	//false
System.out.println(Arrays.deepEquals(str2D, str2D2));	//true
```

 

배열을 List로 변환 - asList(Object... a)

asList() - 배열을 List에 담아서 반환한다. 매개변수의 타입이 가변인수라서 배열 생성없이 저장할 요소들만 나열하는 것도 가능하다.

```
List list = Arrays.asList(new Integer[]{1, 2, 3, 4, 5});	//list = [1, 2, 3, 4, 5]
List list = Arrays.asList(1, 2, 3, 4, 5);	//list = [1, 2, 3, 4, 5]

//asList()가 반환한 List의 크기는 변경할 수 없다.
//즉, 추가 또는 삭제가 불가능하다.
//저장된 내용은 변경 가능하다.
list.add(6);	//UnsupportedOperationException 예외 발생. list의 크기를 변경할 수 없음.

//크기를 변경할 수 있는 List가 필요할 경우
List list = new ArrayList(Arrays.asList(1, 2, 3, 4, 5));	//변경 가능한 ArrayList 생성
```

 

parallelXXX(), spliterator(), stream()

parallel 로 시작하는 이름의 메서드 - 보다 빠른 결과를 얻기 위해 여러 쓰레드가 작업을 나누어 처리하도록 한다.

spliterator() - 여러 쓰레드가 처리할 수 있게 하나의 작업을 여러 작업으로 나누는 Spliterator를 반환

stream() - 컬렉션을 스트림으로 변환한다.

 

1.7 Comparator와 Comparable

Comparaotr와 Comparable은 모두 인터페이스로 컬렉션을 정렬하는데 필요한 메서드를 정의하고 있다.

Comparable을 구현하고 있는 클래스들은 같은 타입의 인스턴스끼리 서로 비교할 수 있는 클래스들, 주로 Integer와 같은 wrapper클래스와 String, Date, File과 같은 것들이며 기본적으로 오름차순, 즉 작은 값에서부터 큰 값의 순으로 정렬되도록 구현되어 있다.

따라서, Comparable을 구현한 클래스는 정렬이 가능하다는 것을 의미한다.

 

Comparable은 java.lang패키지에 있고, Comparator는 java.util패키지에 있다.

```
public interface Comparator {
	int compare(Object o1, Object o2);
    //equals메서드는 모든 클래스가 가지고 있는 공통적인 메서드이지만,
    //Comparator를 구현하는 클래스는 오버라이딩이 필요할 수도 있다는 것을 알리기 위해 정의한 것일 뿐,
    //compare(Object o1, Object o2)만 구현하면 된다.
    boolean equals(Object obj);
}

public interface Comparable {
	public int compareTo(Object o);
}
```

 

compare()와 compareTo()는 선언형태와 이름이 약간 다를 뿐 두 객체를 비교하는 기능을 한다.

compareTo()의 반환값은 int이지만 실제로는 비교하는 두 객체가 같으면 0, 비교하는 값보다 작으면 음수, 크면 양수를 반환하도록 구현해야 한다.

compare()도 객체를 비교해서 음수, 0, 양수 중의 하나를 반환하도록 구현해야 한다.

 

Comparable을 구현한 클래스들이 기본적으로 오름차순으로 정렬되어 있지만, 내림차순으로 정렬한다던가 아니면 다른 기준에 의해서 정렬되도록 하고 싶을 때 Comparator를 구현해서 정렬기준을 제공할 수 있다.

> Comparable - 기본 정렬기준을 구현하는데 사용
> Comparator - 기본 정렬기준 외에 다른 기준으로 정렬하고자 할 때 사용