# TreeSet

- TreeSet은 **이진 검색 트리(Binary search tree)**라는 자료구조 형태로 데이터를 저장하는 컬렉션 클래스
- 이진 검색 트리는 정렬, 검색, 범위 검색(Range Search)에 높은 성능을 보이는 자료구조
- TreeSet은 이진 검색 트리의 성능을 향상시킨 **'레드-블랙 트리(Red-Black Tree)'로 구현**

이진 트리(Binary tree)는 LinkedList처럼 여러 개의 노드(node)가 서로 연결된 구조로, 각 노드에 최대 2개의 노드를 연결할 수 있으며 '루트(root)'라고 불리는 하나의 노드에서부터 시작해서 계속 확장해 나갈 수 있습니다. 위 아래로 연결된 두 노드를 '부모-자식관계'에 있다고 하며 노드를 부모 노드, 아래의 노드를 자식 노드라고 합니다. 부모-자식관계는 상대적이며 하나의 부모 노드는 최대 두 개의 자식 노드와 연결될 수 있습니다.

<img src="https://blog.kakaocdn.net/dn/W2xbI/btrqIsQzJvn/HJGSHDNKBfomI1DAya7lz1/img.png" alt="img" style="zoom:30%;" />



```java
//트리의 노드 표현
//데이터를 저장하기 위한 Object타입의 참조변수 하나와 두 개의 노드를 참조하기 위한 두 개의 참조변수 선언
class TreeNode {
	TreeNode left;	//왼쪽 자식노드
    Object element;	//객체를 저장하기 위한 참조변수
    TreeNode right;	//오른쪽 자식노드
}
```

이진 검색 트리(binary search tree)의 부모노 드의 왼쪽에는 부모노드의 값보다 작은 값의 자식노드를, 오른쪽에는 큰 값의 자식노드를 저장하는 이진 트리입니다. 예를 들어 데이터를 5, 1, 7 순서로 저장한 이진 트리의 구조는 아래와 같이 표현할 수 있습니다. 실제로는 오른쪽 그림과 같이 표현해야하나, 앞으로 간단히 왼쪽과 같이 표현합니다.

<img src="https://blog.kakaocdn.net/dn/NRPMh/btrqFj1DFx9/gIthkjO6wwM6rTxmHVeKkK/img.png" alt="img" style="zoom:20%;" />



## 1. 이진 검색 트리에 값을 저장하는 과정

이진 검색 트리에 값을 저장할 때, 첫 번째로 저장되는 값은 루트가 되고, 두 번째 값은 트리의 루트부터 시작해서 값의 크기를 비교하면서 트리를 따라 내려갑니다. 왼쪽 마지막 레벨이 제일 작은 값이 되고 오른쪽 마지막 레벨의 값이 제일 큰 값이 됩니다. 컴퓨터는 알아서 값을 비교하지 못하므로, TreeSet에 저장되는 객체가 Comparable을 구현하던가 아니면, Comparator를 제공해서 두 객체를 비교할 방법을 알려줘야 합니다. 그렇지 않으면 TreeSet에 두 번째 객체를 저장할 때 예외가 발생합니다.

왼쪽 마지막 값에서부터 오른쪽 값까지 값을 '왼쪽 노드 -> 부모 노드 -> 오른쪽 노드' 순으로 읽어오면 오름차순으로 정렬된 순서를 얻을 수 있습니다. TreeSet은 이처럼 정렬된 상태를 유지하기 때문에 단일 값 검색과 범위검색(range search)이 매우 빠릅니다. 저장된 값의 개수에 비례해서 검색시간이 증가하긴 하지만 값의 개수가 10배 증가해도 특정 값을 찾는데 필요한 비교횟수가 3~4번만 증가할 정도로 검색효율이 뛰어난 자료구조입니다.

트리는 데이터를 순차적으로 저장하는 것이 아니라 저장위치를 찾아서 저장해야 하고, 삭제하는 경우 트리의 일부를 재구성해야 하므로 링크드 리스트보다 데이터의 추가/삭제시간은 더 걸립니다. 그 대신 배열이나 링크드 리스트에 비해 검색과 정렬기능이 더 뛰어납니다.

 

> \- 모든 노드는 최대 두 개의 자식노드를 가질 수 있다.
> \- 왼쪽 자식노드의 값은 부모노드의 값보다 작고, 오른쪽 자식노드의 값은 부모노드의 값보다 커야한다.
> \- 노드의 추가 삭제에 시간이 걸린다. (순차적으로 저장하지 않으므로)
> \- 검색(범위 검색)과 정렬에 유리하다.
> \- 중복된 값을 저장하지 못한다.



**TreeSet의 생성자와 메서드**

| 생성자 또는 메서드                                           | 설명                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| TreeSet()                                                    | 기본 생성자                                                  |
| TreeSet(Collection c)                                        | 주어진 컬렉션을 저장하는 TreeSet을 생성                      |
| TreeSet(Comparator comp)                                     | 주어진 정렬조건으로 정렬하는 TreeSet을 생성                  |
| TreeSet(SortedSet s)                                         | 주어진 SortedSet을 구현한 컬렉션을 저장하는 TreeSet을 생성   |
| boolean add(Object o)<br />boolean addAll(Collection c)      | 지정된 객체(o) 또는 Collection(c)의 객체들을 Collection에 추가 |
| Object ceiling(Object o)                                     | 지정된 객체와 같은 객체를 반환. <br />없으면 큰 값을 가진 객체 중 제일 가까운 값의 객체를 반환. 없으면 null |
| void clear()                                                 | 저장된 모든 객체를 삭제한다.                                 |
| Object clone()                                               | TreeSet을 복제하여 반환한다.                                 |
| Comparator comparator()                                      | TreeSet의 정렬기준(Comparator)를 반환한다.                   |
| boolean contains(Object o)<br />boolean containsAll(Collection c) | 지정된 객체(o) 또는 Collection의 객체들이 포함되어 있는지 확인한다. |
| NavigableSet descendingSet()                                 | TreeSet에 저장된 요소들을 역순으로 정렬해서 반환             |
| Object first()                                               | 정렬된 순서에서 첫 번째 객체를 반환한다.                     |
| Object floor(Object o)                                       | 지정된 객체와 같은 객체를 반환. <br />없으면 작은 값을 가진 객체 중 제일 가까운 값의 객체를 반환. 없으면 null |
| SortedSet headSet(Object toElement)                          | 지정된 객체보다 작은 값의 객체들을 반환한다.                 |
| NavigableSet headSet(Object toElement, boolean inclusive)    | 지정된 객체보다 작은 값의 객체들을 반환. <br />inclusive가 true이면, 같은 값의 객체도 포함 |
| Object higher(Object o)                                      | 지정된 객체보다 큰 값을 가진 객체 중 제일 가까운 값의 객체를 반환. 없으면 null |
| boolean isEmpty()                                            | TreeSet이 비어있는지 확인한다.                               |
| Iterator iterator()                                          | TreeSet의 Iterator를 반환한다.                               |
| Object last()                                                | 정렬된 순서에서 마지막 객체를 반환한다.                      |
| Object lower(Object o)                                       | 지정된 객체보다 작은 값을 가진 객체 중 제일 가까운 값의 객체를 반환. 없으면 null |
| Object pollFirst()                                           | TreeSet의 첫번째 요소(제일 작은 값의 객체)를 반환.           |
| Object pollLast()                                            | TreeSet의 마지막 번째 요소(제일 큰 값의 객체)를 반환.        |
| boolean remove(Object o)                                     | 지정된 객체를 삭제한다.                                      |
| boolean retainAll(Collection c)                              | 주어진 컬렉션과 공통된 요소만을 남기고 삭제한다. (교집합)    |
| int size()                                                   | 저장된 객체의 개수를 반환한다.                               |
| Spliterator spliterator()                                    | TreeSet의 spliterator를 반환                                 |
| SortedSet subSet(Object fromElement, Object toElement)       | 범위 검색(fromElement와 toElement사이)의 결과를 반환한다. <br />(끝 범위인 toElement는 범위에 포함되지 않음) |
| NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) | 범위 검색(fromElement와 toElement사이)의 결과를 반환한다. (fromInclusive가 true면 시작값이 포함되고, toInclusive가 true면 끝값이 포함된다. |
| SortedSet tailSet(Object fromElement)                        | 지정된 객체보다 큰 값의 객체들을 반환한다.                   |
| Object[] toArray()                                           | 저장된 객체를 객체배열로 반환한다.                           |
| Object[] toArray(Object[] a)                                 | 저장된 객체를 주어진 객체배열에 저장하여 반환한다.           |

문자열의 경우 정렬순서는 문자의 코드값이 기준이 되므로, 오름차순 정렬의 경우 코드값의 크기가 작은 순서, 즉 공백, 숫자, 대문자, 소문자 순으로 정렬되고 내림차순의 경우 그 반대가 된다.



## 2. TreeSet 예제

```java
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        TreeSet set = new TreeSet();
        int[] score = {80, 95, 50, 35, 45, 65, 10, 100};

        for (int i = 0; i < score.length; i++) {
            set.add(new Integer(score[i]));
        }
        System.out.println("50보다 작은 값: " + set.headSet(new Integer(50)));
        System.out.println("50보다 큰 값: " + set.tailSet(new Integer(50)));
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=53540:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
50보다 작은 값: [10, 35, 45]
50보다 큰 값: [50, 65, 80, 95, 100]

종료 코드 0(으)로 완료된 프로세스
```



