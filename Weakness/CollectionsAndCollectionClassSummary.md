# Collections

Collections는 컬렉션과 관련된 메서드를 제공한다. fill(), copy(), sort(), binarySearch() 등의 메서드는 Arrays와 Collections 클래스에 모두 포함되어 있으며 같은 기능을 한다.

- java.util.Colloection은 인터페이스이고, java.util.Collections는 클래스이다.

 

## 1. 컬렉션의 동기화

멀티 쓰레드(multi-thread) 프로그래밍에서는 하나의 객체를 여러 쓰레드가 동시에 접근할 수 있기 때문에 데이터의 일관성(consistency)을 유지하기 위해서는 공유되는 객체에 동기화(synchronization)가 필요하다.

Vector와 Hashtable과 같은 구버전(JDK1.2 이전)의 클래스들은 자체적으로 동기화 처리가 되어 있으나 새로 추가된 ArrayList와 HashMap과 같은 컬렉션은 동기화를 자체적으로 처리하지 않고 필요한 경우에만 java.util.Collections 클래스의 동기화 메서드를 이용해서 동기화처리가 가능하도록 변경하였다.

 

**Collections클래스에서 제공하고 있는 동기화 메서드**

```java
static Collection synchronizedCollection(Collection c)
static List synchronizedList(List list)
static Set synchronizedSet(Set s)
static Map synchronizedMap(Map m)
static SortedSet synchronizedSortedSet(SortedSet s)
static SortedMap synchronizedSortedMap(SortedMap m)


//사용 예시
List synchList = Collections.synchronizedList(new ArrayList(...));
```

 

## 2. 변경불가 컬렉션 만들기

컬렉션에 저장된 데이터를 보호하기 위해서 컬렉션을 변경할 수 없게, 즉 읽기전용으로 만들어야 할 때가 있다. 주로 멀티 쓰레드 프로그래밍에서 여러 쓰레드가 하나의 컬렉션을 공유하다 보면 데이터가 손상 될 수 있는데, 이를 방지하기 위한 메서드가 있다.

```java
static Collection unmodifiableCollection(Collection c)
static List unmodifiableList(List list)
static Set unmodifiableSet(Set s)
static Map unmodifiableMap(Map m)
static NavigableSet unmodifiableNavigableSet(NavigableSet s)
static SortedSet unmodifiableSortedSet(SortedSet s)
static NavigableMap unmodifiableNavigableMap(NavigableMap m)
static SortedMap unmodifiableSortedMap(SortedMap m)
```

 

## 3. 싱글톤 컬렉션 만들기

인스턴스를 new연산자가 아닌 메서드를 통해서만 생성할 수 있게 함으로써 생성할 수 있는 인스턴스의 개수를 제한하는 기능을 제공하는 것이 'singleton'으로 시작하는 메서드이다. 매개변수로 저장할 요소를 지정하면, 해당 요소를 저장하는 컬렉션을 반환한다. 반환된 컬렉션은 변경할 수 없다.

```java
static List singletonList(Object o)
static Set singleton(Object o)	//singletonSet이 아님
static Map singletonMap(Object key, Object value)
```

 

## 4. 한 종류의 객체만 저장하는 컬렉션 만들기

컬렉션에는 모든 종류의 객체를 저장할 수 있다. 하지만 대부분의 경우 한 종류의 객체를 저장하며, 컬렉션에 지정된 종류의 객체만 저장할 수 있도록 제한하고 싶을 때 사용하는 메서드가 있다.

```java
static Collection checkedCollection(Collection c, Class type)
static List checkedList(List list, Class type)
static Set checkedSet(Set s, Class type)
static Map checkedMap(Map m, Class keyType, Class valueType)
static Queue checkedQueue(Queue queue, Class type)
static NavigableSet checkedNavigableSet(NavigableSet s, Class type)
static SortedSet checkedSortedSet(SortedSet s, Class type)
static NavigableMap checkedNavigableMap(NavigableMap m, Class keyType, Class valueType)
static SortedMap checkedSortedMap(SortedMap m, class keyType, Class valueType)


//사용 예시
//두 번째 매개변수에 저장할 객체의 클래스 지정
List list = new ArrayList();
List checkedList = checkedList(list, String.class);	//String만 저장가능
checkedList.add("abc");	//OK.
checkedList.add(new Integer(3));	//에러. ClassCastException 발생
```





## 5. 컬렉션 클래스 정리 & 요약

<img src="https://blog.kakaocdn.net/dn/ZHdzw/btrqM4DpT8K/wO4KaJiZ6WkZz4jFDmbyWK/img.png" alt="img" style="zoom:52%;" />



## 6. 컬렉션 클래스의 특징

| 컬렉션                      | 특징                                                         |
| --------------------------- | ------------------------------------------------------------ |
| ArrayList                   | 배열 기반. 데이터의 추가와 삭제에 불리. 순차적인 추가/삭제는 제일 빠름. 임의의 요소에 대한 접근성(accessibility)이 뛰어남. |
| LinkedList                  | 연결 기반. 데이터의 추가와 삭제에 유리. 임의의 요소에 대한 접근성이 좋지 않음. |
| HashMap                     | 배열과 연결이 결합된 형태. 추가, 삭제, 검색, 접근성이 모두 뛰어남. 검색에는 최고성능을 보임. |
| TreeMap                     | 연결 기반. 정렬과 검색(특히 범위 검색)에 적합. 검색 성능은 HashMap보다 떨어짐. |
| Stack                       | Vector를 상속받아 구현 (LIFO)                                |
| Queue                       | LinkedList가 Queue인터페이스를 구현 (FIFO)                   |
| Properties                  | Hashtable을 상속받아 구현 (String, String)                   |
| HashSet                     | Hashmap을 이용해서 구현                                      |
| TreeSet                     | TreeMap을 이용해서 구현                                      |
| LinkedHashMap LinkedHashSet | HashMap과 HashSet에 저장 순서 유지 기능을 추가               |

 

 