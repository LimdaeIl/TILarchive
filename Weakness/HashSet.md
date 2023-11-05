# HashSet

- HashSet은 Set 인터페이스를 구현한 가장 대표적인 컬렉션
- Set 인터페이스의 특징대로 HashSet은 중복된 요소를 저장하지 않음

HashSet에 새로운 요소를 추가할 때는 **add 메서드**나 **addAll 메서드**를 사용합니다. 만일 HashSet에 이미 저장되어 있는 요소와 중복된 요소를 추가하고자 한다면 이 메서드들은 false를 반환함으로써 중복된 요소 추가에 실패했다는 것을 알립니다. 이와 같은 HashSet의 특징을 이용하면 컬렉션 안의 중복 요소들을 쉽게 제거할 수 있습니다.

ArrayList와 처럼 List 인터페이스를 구현한 컬렉션과 다르게 HashSet은 저장 순서를 유지하지 않습니다. **저장 순서를 유지하고자 한다면 LinkedHashSet을 사용해야 합니다.** HashSet은 내부적으로 HashMap을 이용해서 만들어졌으며, HashSet이란 이름은 해싱(Hashing)을 이용해서 구현했기 때문에 붙여진 이름입니다. 해싱(hashing)에 대한 자세한 내용은 HashMap에서 설명합니다.

**HashSet의 메서드**

| 생성자 또는 메서드                             | 설명                                                         |
| ---------------------------------------------- | ------------------------------------------------------------ |
| HashSet()                                      | HashSet 객체를 생성.                                         |
| HashSet(Collection c)                          | 주어진 컬렉션을 포함하는 HashSet 객체를 생성.                |
| HashSet(int initialCapacity)                   | 주어진 값을 초기용량으로 하는 HashSet객체를 생성.            |
| HashSet(int initialCapacity, float loadFactor) | 초기용량과 load factor를 지정하는 생성자.                    |
| boolean add(Object o)                          | 새로운 객체를 저장.                                          |
| boolean addAll(Collection c)                   | 주어진 컬렉션에 저장된 모든 객체들을 추가. (합집합)          |
| void clear()                                   | 저장된 모든 객체를 삭제.                                     |
| Object clone()                                 | HashSet을 복제해서 반환. (얕은 복사)                         |
| boolean contains(Object o)                     | 지정된 객체를 포함하고 있는지 여부 반환.                     |
| boolean containsAll(Collection c)              | 주어진 컬렉션에 저장된 모든 객체들을 포함하고 있는지 여부 반환. |
| boolean isEmpty()                              | HashSet이 비어있는지 여부 반환.                              |
| Iterator iterator()                            | Iterator를 반환.                                             |
| boolean remove(Object o)                       | 지정된 객체를 HashSet에서 삭제. <br />삭제를 성공하면 true, 실패하면 false 반환 합니다. |
| boolean removeAll(Collection c)                | 주어진 컬렉션에 저장된 모든 객체와 동일한 것들을 HashSet에서 모두 삭제.(차집합) |
| boolean retainAll(Collection c)                | 주어진 컬렉션에 저장된 객체와 동일한 것만 남기고 삭제.(교집합) |
| int size()                                     | 저장된 객체의 개수를 반환.                                   |
| Object[] toArray()                             | 저장된 객체들을 객체배열의 형태로 반환.                      |
| Object[] toArray(Object[] a)                   | 저장된 객체들을 주어진 객체배열(a)에 저장.                   |

load factor는 컬렉션 클래스에 저장 공간이 가득 차기 전에 미리 용량을 확보하기 위한 것으로, 이 값을 0.8으로 지정하면 저장 공간의 80%가 채워졌을 때 용량이 두 배로 늘어납니다. 기본값은 0.75입니다.



## 1. HashSet 예제

```java
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Object[] objArr = {"1", new Integer(1), "2", "2", "3", "3", "4", "4", "4"};
        Set set = new HashSet();

        for (int i = 0; i < objArr.length; i++) {
            set.add(objArr[i]); // HashSet에 objArr 요소들을 저장.
        }

        // HashSet에 저장된 요소들을 출력.
        System.out.println("set = " + set);
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=63363:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
set = [1, 1, 2, 3, 4]

종료 코드 0(으)로 완료된 프로세스
```

중복을 제거하는 동시에 저장한 순서를 유지하고자 한다면, HashSet 대신 LinkedHashSet을 사용합니다.



## 2. HashCode() 오버라이딩

```java
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        HashSet set = new HashSet();

        set.add(new String("abc"));
        set.add(new String("abc"));
        set.add(new Person2("David", 10));
        set.add(new Person2("David", 10));

        System.out.println("set = " + set);
    }
}

class Person2 {
    String name;
    int age;

    public Person2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Person2) {
            Person2 tmp = (Person2) obj;
            return name.equals(tmp.name) && age == tmp.age;
        }
        return false;
    }

    public int hashCode() {
        return (name + age).hashCode();
    }

    public String toString() {
        return name + ":" + age;
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=63543:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
set = [abc, David:10]

종료 코드 0(으)로 완료된 프로세스
```

HashSet의 add 메서드는 새로운 요소를 추가하기 전에 기존의 저장된 요소와 같은 것인지 판별하기 위해 추가하려는 요소의 equals()와 hashCode()를 호출하기 때문에 equals()와 hashCode()를 목적에 맞게 오버라이딩 해야 합니다. 

그래서 String 클래스에서 같은 내용의 문자열에 대한 equals() 의 호출결과가 true인 것과 같이, Person2 클래스에서도 두 인스턴스의 name과 age가 서로 같으면 true를 반환하도록 equals()를 오버라이딩 했습니다. 그리고 hashCode()는 String 클래스의 hashCode()를 이용하여 구현했습니다. String 클래스의 hashCode()는 잘 구현되어 있기 때문에 이를 활용하면 간단히 처리할 수 있습니다.

```java
public int hashCode() {
    return (name+age).hashCode();
}
```

위의 코드를 JDK1.8부터 추가된 java.util.Objects 클래스의 hash()를 이용해서 작성하면 다음과 같습니다. 이 메서드의 괄호 안에 클래스의 인스턴스 변수들을 넣으면 됩니다. 이전의 코드와 별반 다르지 않지만, 가능하면 아래의 코드를 작성합니다.

```java
public int hashCode() {
    return Objects.hash(name, age); // int hash(Object ... values)
}
```



오버라이딩을 통해 작성된 hashCode()는 다음의 세 가지 조건을 만족시켜야 합니다.

1. **실행 중인 애플리케이션 내의 동일한 객체에 대해서 여러 번 hashCode()를 호출해도 동일한 int 값을 반환해야 합니다. 하지만, 실행시마다 동일한 int값을 반환할 필요는 없습니다. (단, equals메서드의 구현에 사용된 멤버변수의 값이 바뀌지 않았다고 가정합니다.)**

   예를 들어 Person2 클래스의 equals 메서드에 사용된 멤버변수 name과 age의 값이 바뀌지 않는 한, 하나의 Person2 인스턴스에 대해 hashCode()를 여러 번 호출했을 때 항상 같은 int 값을 얻어야 합니다.

   ```java
   Person2 p = new Person2("David", 10);
   
   int hashCode1 = p.hashCode();
   int hashCode2 = p.hashCode();
   p.age = 20;
   int hashCode3 = p.hashCode();
   ```

   위의 코드에서 hashCode1의 값과 hashCode2의 값은 항상 일치해야합니다. 하지만 이 두 값이 매번 실행할 때마다 반드시 같은 값일 필요는 없습니다. hashCode3는 equals 메서드에 사용된 멤버변수 age를 변경한 후에 hashCode 메서드를 호출한 결과이므로 hashCode1이나 hashCode2와 달라도 됩니다.

\* String 클래스는 문자열의 내용으로 해시코드를 만들어 내기 때문에 내용이 같은 문자열에 대한 hashCode() 호출은 항상 동일한 해시코드를 반환합니다. 반면에 Object 클래스는 객체의 주소로 해시코드를 만들어 내기 때문에 실행할 때마다 해시코드값이 달라질 수 있습니다.

 

2. **equals메서드를 이용한 비교에 의해서 true를 얻은 두 객체에 대해 각각 hashCode()를 호출해서 얻은 결과는 반드시 같아야 합니다.**

   즉, 인스턴스 p1과 p2에 대해서 equals메서드를 이용한 비교의 결과인 변수 b(p1.equals(p2))의 값이 true라면, hashCode1(p1.hashCode())과 hashCode2(p2.hashCode())의 값은 같아야 한다는 뜻입니다.

   ```java
   Person p1 = new Person2("David", 10);
   Person p2 = new Person2("David", 10);
   
   boolean b = p1.equals(p2);
   
   int hashCode1 = p1.hashCode();
   int hashCode2 = p2.hashCode();
   ```

    

3. **equals 메서드를 호출했을 때 false를 반환하는 두 객체는 hashCode() 호출에 대해 같은 int 값을 반환하는 경우가 있어도 괜찮지만, 해싱(hashing)을 사용하는 컬렉션의 성능을 향상시키기 위해서는 다른 int값을 반환하는 것이 좋습니다.**

   위의 코드에서 변수 b의 값이 false일지라도 hashCode1과 hashCode2의 값이 같은 경우가 발생하는 것을 허용합니다. 하지만, 해시코드를 사용하는 Hashtable이나 HashMap과 같은 컬렉션의 성능을 높이기 위해서는 가능한 서로 다른 값을 반환하도록 hashCode()를 잘 작성해야 한다는 뜻입니다. 

   서로 다른 객체에 대해서 해시코드값이(hashCode()를 호출한 결과) 중복되는 경우가 많아지면 많아질수록 해싱을 사용하는 Hashtable, HashMap과 같은 컬렉션의 검색속도가 떨어집니다.

**두 객체에 대해 equals메서드를 호출한 결과가 true이면, 두 객체의 해시코드는 반드시 같아야 하지만, 두 객체의 해시코드가 같다고 해서 equals메서드의 호출결과가 반드시 true이어야 하는 것은 아닙니다.**



**HashSet 집합 관계 구현 예제**

```java
import java.util.HashSet;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        HashSet setA = new HashSet();
        HashSet setB = new HashSet();
        HashSet setHab = new HashSet();
        HashSet setIntersection = new HashSet();
        HashSet setDifference = new HashSet();

        setA.add("1");
        setA.add("2");
        setA.add("3");
        setA.add("4");
        setA.add("5");
        System.out.println("setA = " + setA);

        setB.add("4");
        setB.add("5");
        setB.add("6");
        setB.add("7");
        setB.add("8");
        System.out.println("setB = " + setB);

        Iterator it = setB.iterator();
        while(it.hasNext()) {
            Object tmp = it.next();
            if(setA.contains(tmp)) {
                setIntersection.add(tmp);
            }
        }

        it = setA.iterator();
        while(it.hasNext()){
            Object tmp = it.next();
            if(!setB.contains(tmp)){
                setDifference.add(tmp);
            }
        }

        it = setA.iterator();
        while(it.hasNext()) {
            setHab.add(it.next());
        }

        it= setB.iterator();
        while (it.hasNext()) {
            setHab.add(it.next());
        }

        System.out.println("A intersection B = " + setIntersection); //
        System.out.println("A union B = " + setHab);
        System.out.println("A difference B = " + setDifference);

    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=52269:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
setA = [1, 2, 3, 4, 5]
setB = [4, 5, 6, 7, 8]
A intersection B = [4, 5]
A union B = [1, 2, 3, 4, 5, 6, 7, 8]
A difference B = [1, 2, 3]

종료 코드 0(으)로 완료된 프로세스
```

Set은 중복을 허용하지 않으므로 HashSet의 메서드를 호출하는 것만으로도 간단하게 합집합(addAll), 교집합(retainAll), 차집합(removeAll)을 구할 수 있습니다. 위의 예제는 직접 구현하는 방법으로 HashSet의 사용 방법에 대해 간략하게 이해하는 시간을 가졌습니다.



