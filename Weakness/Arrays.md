# Arrays

Arrays 클래스에는 배열을 다루는데 유용한 메서드가 정의되어 있습니다. 같은 기능의 메서드가 배열의 타입만 다르게 오버로딩되어 있이서 많아 보이지만, 실제로는 그리 많지 않습니다. 아래는 Arrays에 정의된 toString()인데, 모든 기본형 배열과 참조형 배열 별로 하나씩 정의되어 있습니다. Arrays에 정의된 메서드는 모두 static 메서드입니다.

```java
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



## 1. 배열의 복사 - copyOf(), copyOfRange()

- **copyOf()**: 배열 전체를 복사해서 새로운 배열을 만들어 반환합니다.
- **copyOfRange()**: 배열의 일부를 복사해서 새로운 배열을 만들어 반환합니다. 지정된 범위의 끝은 포함되지 않습니다.

```java
int[] arr = {0, 1, 2, 3, 4};
int[] arr2 = Arrays.copyOf(arr, arr.length);	//arr2 = [0, 1, 2, 3, 4]
int[] arr3 = Arrays.copyOf(arr, 3);		//arr3 = [0, 1, 2]
int[] arr4 = Arrays.copyOf(arr, 7);		//arr4 = [0, 1, 2, 3, 4, 0, 0]

int[] arr5 = Arrays.copyOfRange(arr, 2, 4);	//arr5 = [2, 3]	<- 4는 포함되지 않음
int[] arr6 = Arrays.copyOfRange(arr, 0, 7);	//arr6 = [0, 1, 2, 3, 4, 0, 0]
```



## 2. 배열 채우기 - fill(), setAll()

- **fill()**: 배열의 모든 요소를 지정된 값으로 채웁니다.
- **setAll()**: 배열을 채우는데 사용할 함수형 인터페이스를 매개변수로 받습니다. 이 메서드를 호출할 때는 함수형 인터페이스를 구현한 객체를 매개변수로 지정하던가 아니면 람다식(lambda expression)을 지정해야 합니다.

```java
int[] arr = new int[5];
Arrays.fill(arr, 9);	//arr = [9, 9, 9, 9, 9]
Arrays.setAll(arr, i -> (int)(Math.random()*5) + 1);	//arr = [1, 5, 2, 1, 1]
```



## 3. 배열의 정렬과 검색 - sort(0, binarySearch()

- **sort()**: 배열을 정렬할 때 사용합니다.
- **binarySearch()**: 배열에 저장된 요소를 검색할 때 사용합니다. 배열에서 지정된 값이 저장된 위치(index)를 찾아서 반환하는데, 반드시 배열이 정렬된 상태이어야 올바른 결과를 얻습니다. 그리고 만일 검색한 값과 일치하는 요소들이 여러 개 있다면, 이 중에서 어떤 것의 위치가 반환될지는 알 수 없습니다.

```java
int[] arr = {3, 2, 0, 1, 4};	//정렬되지 않은 배열
int idx = Arrays.binarySearch(arr, 2);	//idx = -5 <- 잘못된 결과

Arrays.sort(arr);	//배열 arr을 정렬한다.
System.out.println(Arrays.toString(arr));	//[0, 1, 2, 3, 4]
int idx = Arrays.binarySearch(arr, 2);	//idx = 2 <- 올바른 결과
```

- **linear search (순차 검색)**: 배열의 첫 번째 요소부터 순서대로 하나씩 검색하는 것으로, 배열이 정렬되어 있을 필요는 없지만 배열의 요소를 하나씩 비교하기 때문에 시간이 많이 걸립니다.

- **binary search(이진 검색)**: 배열의 검색 범위를 반복적으로 절반씩 줄여가면서 검색하기 때문에 검색 속도가 빠릅니다. 배열의 길이가 10배 늘어나도 검색 횟수는 3~4회 밖에 늘어나지 않으므로 큰 배열의 검색에 유리합니다. 단, 배열이 정렬되어 있는 경우에만 사용할 수 있습니다.



## 4. 문자열의 비교와 출력 - equals(), toString(), deepEquals(), deepToString()

- **toString()**:  배열의 모든 요소를 문자열로 편하게 출력할 수 있다. 일차원 배열에만 사용할 수 있으므로, 다차원 배열에는 deepToString()을 사용해야 해야합니다.
- **deepToString()**: 배열의 모든 요소를 재귀적으로 접근해서 문자열을 구성하므로 2차원뿐만 아니라, 3차원 이상의 배열에 대해서도 동작한다.

```java
int[] arr = {0, 1, 2, 3, 4};
int[][] arr2D = {{11, 12}, {21, 22}};

System.out.println(Arrays.toString(arr));	//[0, 1, 2, 3, 4]
System.out.println(Arrays.deepToString(arr2D));	//[[11, 12], [21, 22]]
```

 

- **equals()**: 두 배열에 저장된 모든 요소를 비교해서 같으면 true, 다르면 false를 반환합니다. 일차원 배열에만 사용가능하므로, 다차원 배열에는 deepEquals()를 사용해야 합니다.

```java
String[][] str2D = new String[][]{{"aaa", "bbb"}, {"AAA", "BBB"}};
String[][] str2D2 = new String[][]{{"aaa", "bbb"}, {"AAA", "BBB"}};

//2차원 String배열을 equals()로 비교하면 배열에 저장된 내용이 같은데도 false를 결과로 얻는다.
//다차원 배열은 '배열의 배열'의 형태로 구성하기 때문에
//equals()로 비교하면, 문자열을 비교하는 것이 아니라 '배열에 저장된 배열의 주소'를 비교하게 된다.
//서로 다른 배열은 항상 주소가 다르므로 false를 결과로 얻는다.
System.out.println(Arrays.equals(str2D, str2D2));	//false
System.out.println(Arrays.deepEquals(str2D, str2D2));	//true
```

 

## 5. 배열을 List로 변환 - asList(Object... a)

- **asList()**: 배열을 List에 담아서 반환합니다. 매개변수의 타입이 가변 인수라서 배열 생성없이 저장할 요소들만 나열하는 것도 가능합니다.

```java
List list = Arrays.asList(new Integer[]{1, 2, 3, 4, 5});	//list = [1, 2, 3, 4, 5]
List list = Arrays.asList(1, 2, 3, 4, 5);	//list = [1, 2, 3, 4, 5]

//asList()가 반환한 List의 크기는 변경할 수 없다.
//즉, 추가 또는 삭제가 불가능하다.
//저장된 내용은 변경 가능하다.
list.add(6);	//UnsupportedOperationException 예외 발생. list의 크기를 변경할 수 없음.

//크기를 변경할 수 있는 List가 필요할 경우
List list = new ArrayList(Arrays.asList(1, 2, 3, 4, 5));	//변경 가능한 ArrayList 생성
```



## 6. parallelXXX(), spliterator(), stream()

- **parallel로 시작하는 이름의 메서드**:보다 빠른 결과를 얻기 위해 여러 쓰레드가 작업을 나누어 처리하도록 합니다.
- **spliterator()** - 여러 쓰레드가 처리할 수 있게 하나의 작업을 여러 작업으로 나누는 Spliterator를 반환합니다.
- **stream()** - 컬렉션을 스트림으로 변환합니다.



## 7. Comparator와 Comparable

Comparaotr와 Comparable은 모두 인터페이스로 컬렉션을 정렬하는데 필요한 메서드를 정의하고 있습니다. Comparable을 구현하고 있는 클래스들은 같은 타입의 인스턴스끼리 서로 비교할 수 있는 클래스들, 주로 Integer와 같은 wrapper클래스와 String, Date, File과 같은 것들이며 기본적으로 오름차순, 즉 작은 값에서부터 큰 값의 순으로 정렬되도록 구현되어 있습니다. 따라서, Comparable을 구현한 클래스는 정렬이 가능하다는 것을 의미합니다.

Comparable은 java.lang패키지에 있고, Comparator는 java.util패키지에 있습니다. compare()와 compareTo()는 선언 형태와 이름이 약간 다를 뿐 두 객체를 비교하는 기능을 합니다. compareTo()의 반환값은 int이지만 실제로는 비교하는 두 객체가 같으면 0, 비교하는 값보다 작으면 음수, 크면 양수를 반환하도록 구현해야 합니다. compare()도 객체를 비교해서 음수, 0, 양수 중의 하나를 반환하도록 구현해야 합니다.

```java
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

Comparable을 구현한 클래스들이 기본적으로 오름차순으로 정렬되어 있지만, 내림차순으로 정렬한다던가 아니면 다른 기준에 의해서 정렬되도록 하고 싶을 때 Comparator를 구현해서 정렬기준을 제공할 수 있습니다.

- **Comparable**: 기본 정렬기준을 구현하는데 사용.
- **Comparator**: 기본 정렬기준 외에 다른 기준으로 정렬하고자 할 때 사용.

````java
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        String[] strArr = {"cat", "Dog", "lion", "tiger"};

        Arrays.sort(strArr); // String의 Comparable 구현에 의한 정렬
        System.out.println("strArr = " + Arrays.toString(strArr));

        Arrays.sort(strArr, String.CASE_INSENSITIVE_ORDER); // 대소문자 구분안함
        System.out.println("strArr = " + Arrays.toString(strArr));

        Arrays.sort(strArr, new Descending());
        System.out.println("strArr = " + Arrays.toString(strArr));
    }
}
class Descending implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof Comparable && o2 instanceof Comparable) {
            Comparable c1 = (Comparable) o1;
            Comparable c2 = (Comparable) o2;
            return c1.compareTo(c2) * -1; // -1을 곱해서 기본 정렬방식의 역으로 변경한다.
                                          // 또는 c2.compareTo(c1)와 같이 순서를 바꿔도 된다.
        }
        return -1;
    }
}
````

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=62969:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
strArr = [Dog, cat, lion, tiger]
strArr = [cat, Dog, lion, tiger]
strArr = [tiger, lion, cat, Dog]

종료 코드 0(으)로 완료된 프로세스
```

