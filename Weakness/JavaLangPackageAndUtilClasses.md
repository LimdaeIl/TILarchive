# java.lang 패키지 & util classes



## 1. java.lang 패키지

java.lang 패키지는 자바 프로그래밍에 가장 기본이 되는 클래스들을 포함하고 있습니다. 그래서 java.lang 패키지 안의 클래스들은 import 문 없이도 사용할 수 있습니다. 대표적인 클래스인 String 클래스, System 클래스를 import 문 없이 사용할 수 있는 이유가 바로 java.lang 패키지 안에 포함되어 있기 때문입니다. 



## 2. Object 클래스

Object 클래스는 **모든 클래스의 최고 조상**이기 때문에 Object 클래스의 멤버들은 모든 클래스에서 바로 사용이 가능합니다. Object 클래스의 메서드는 다음과 같습니다.

1. **`protected Object clone() `**
   객체 자신의 복사본을 반환합니다.
2. `public boolean equals(Object obj)`
   객체 자신과 객체 obj가 같은 객체 여부 반환합니다.
3. **`protected void finalize()`**
   객체가 소멸될 때 가비지 컬랙터에서 자동 호출되며 수행해야 할 코드가 있을 시 오버라이딩합니다. (거의 사용 X)
4. `public Class getClass()` 
   객체 자신의 클래스 정보를 담고 있는 Class 인스턴스 반환합니다.
5. `public int hashCode()` 
   객체 자신의 해시코드 반환합니다.
6. `public String toString()` 
   객체 자신의 정보를 문자열로 반환합니다.
7. `public void notify()` 
   객체 자신을 사용하려고 기다리는 쓰레드를 하나만 깨웁니다.
8. `public void notifyAll()`
   객체 자신을 사용하려고 기다리는 모든 쓰레드를 깨웁니다.
9. `public void wait(), public void wait(long timeout), public void wait(long timeout, int nanos)`
   다른 쓰레드가 notify()나 notifyAll()을 호출할 때까지 현재 쓰레드를 무한히 또는 지정된 시간 동안 기다리게 합니다.

Object 클래스는 멤버변수는 없고, 오직 **11개의 메서드**만 가지고 있습니다. 이 메서드들은 모든 인스턴스가 가져야 할 기본적인 메서드이며, 가장 중요한 몇 가지에 대해 살펴봅니다. (notify(), notifyAll(), wait()은 쓰레드(Thread)와 관련된 메서드로 쓰레드 정리글에서 자세히 설명하겠습니다.)



## 3. equals(Object obj)

equals(Object obj) 메서드는 자바에서 모든 클래스가 상속받는 메서드 중 하나입니다. **객체의 내용을 비교하여 두 객체가 동일한지 여부를 판단하는 데 사용**됩니다. 주로 두 객체가 같은지를 비교하는 데 쓰이며, 객체의 내용을 비교하고 같으면 true를 반환하고, 다르면 false를 반환합니다.

equals(Object obj) 메서드를 사용할 때, 일반적으로 클래스 내에서 이 메서드를 **오버라이드(Override)하여 해당 클래스의 객체들 간에 어떻게 비교할지를 정의**합니다. 이렇게 하면 **객체의 내용을 기반으로 동등성(equality)을 판단**할 수 있습니다.

예를 들어, 문자열(String) 클래스에서 equals(Object obj) 메서드는 문자열의 내용을 비교합니다. 따라서 **두 문자열 객체가 동일한 문자열을 가지고 있으면 true를 반환하고, 다른 경우에는 false를 반환**합니다.

다른 클래스에서도 equals(Object obj)를 오버라이드하여 객체의 내용을 비교하도록 할 수 있으며, 이것은 클래스가 정확하게 어떤 것을 동등성으로 정의하느냐에 따라 다를 것입니다.



### 3.1  equals(Object obj)  기본 예제

```java
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        // 1. 자기 자신과 비교하는지 확인
        if (this == obj) {
            return true;
        }
        
        // 2. 전달된 객체가 null이거나 Person 클래스의 인스턴스가 아닌지 확인
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        // 3. 전달된 객체를 Person으로 형변환 (casting)
        Person otherPerson = (Person) obj;
        
        // 4. 객체 내용을 비교하여 동등성 판단
        // 여기서는 이름과 나이를 비교
        return age == otherPerson.age && name.equals(otherPerson.name);
    }

    public static void main(String[] args) {
        Person person1 = new Person("Alice", 30);
        Person person2 = new Person("Bob", 25);
        Person person3 = new Person("Alice", 30);

        // equals 메서드를 사용하여 두 객체의 동등성을 판단
        boolean areEqual1 = person1.equals(person2); // false
        boolean areEqual2 = person1.equals(person3); // true

        System.out.println("Are person1 and person2 equal? " + areEqual1);
        System.out.println("Are person1 and person3 equal? " + areEqual2);
    }
}
```

이 예제에서는 `Person` 클래스를 정의하고 `equals(Object obj)` 메서드를 오버라이드하여 두 `Person` 객체를 이름과 나이로 비교합니다. 메서드의 주요 단계는 다음과 같습니다:

1. 자기 자신과 비교하는 경우를 먼저 확인합니다.
2. 전달된 객체가 null이거나 `Person` 클래스의 인스턴스가 아닌지 확인합니다.
3. 전달된 객체를 `Person`으로 형변환(casting)합니다.
4. 객체 내용을 비교하여 동등성을 판단합니다. 여기서는 이름과 나이를 비교하며, 두 객체의 이름과 나이가 동일한 경우에만 true를 반환합니다.

주석을 참조하여 각 단계를 자세히 이해하실 수 있을 것입니다. 위의 예제를 실행하면 `person1`과 `person2`의 동등성은 false이고, `person1`과 `person3`의 동등성은 true입니다.



### 3.2 equals(Object obj) 와 == 비교 예제

`equals(Object obj)` 메서드와 `==` 연산자는 객체 비교에 사용되는 두 가지 다른 개념입니다. 이 두 가지를 비교하는 예제를 아래에서 설명하겠습니다.

1. **`equals(Object obj)` 메서드:**
   - `equals(Object obj)` 메서드는 **객체의 내용을 비교하는 데 사용**됩니다.
   - 일반적으로 클래스 내에서 이 메서드를 오버라이드하여 해당 클래스의 객체들 간에 어떻게 비교할지를 정의합니다.
   - 객체의 내용을 비교하고 동일한 경우 true를 반환하고, 다른 경우 false를 반환합니다.
2. **`==` 연산자:**
   - `==` 연산자는 **참조 비교(Reference Comparison)를 수행**합니다. 즉, **비교하는 것은 객체의 메모리 주소**입니다.
   - 두 변수가 같은 객체를 참조하면 true를 반환하고, 서로 다른 객체를 참조하면 false를 반환합니다.

이제 두 가지를 비교하는 예제를 제공하겠습니다:

```java
public class ObjectComparisonExample {
    public static void main(String[] args) {
        // 두 객체를 생성하고 동일한 내용을 가지도록 함
        String str1 = new String("Hello");
        String str2 = new String("Hello");

        // 참조 비교 (==)
        boolean referenceComparison = (str1 == str2); // false, 서로 다른 객체를 참조

        // 내용 비교 (equals(Object obj))
        boolean contentComparison = str1.equals(str2); // true, 내용이 동일

        System.out.println("Reference Comparison (==): " + referenceComparison);
        System.out.println("Content Comparison (equals): " + contentComparison);
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=51771:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
Reference Comparison (==): false
Content Comparison (equals): true

종료 코드 0(으)로 완료된 프로세스
```

이 예제에서 `str1`과 `str2`는 내용은 동일하지만 서로 다른 객체를 참조합니다. 따라서 `==` 연산자를 사용하여 참조 비교를 하면 false가 반환됩니다. 반면에 `equals(Object obj)` 메서드를 사용하여 내용을 비교하면 true가 반환됩니다.

참조 비교와 내용 비교는 객체의 비교에 대해 다른 정보를 제공하며, 어떤 비교를 사용할지는 상황에 따라 다를 수 있습니다. 보통 객체 내용을 비교하는 데는 `equals(Object obj)` 메서드를 사용하고, 객체의 참조(메모리 주소)를 비교하는 데는 `==` 연산자를 사용합니다.



## 4. hashCode()

`hashCode()`는 자바에서 객체의 해시 코드(Hash Code)를 반환하는 메서드입니다. 이것은 해시 기반 컬렉션(HashMap, HashSet 등)에서 객체를 저장하고 검색하는 데 사용됩니다. 이해하기 어려울 수 있지만, 아래에서 자세히 설명하겠습니다.

1. **해시 코드란 무엇인가?**

   - 해시 코드는 객체를 식별하기 위한 정수값입니다.
   - 해시 코드는 객체가 메모리 어딘가에 저장되어 있는데, 이것을 효율적으로 검색하고 식별하는 데 사용됩니다.

2. **`hashCode()` 메서드의 역할:**

   - `hashCode()` 메서드는 객체의 내부 상태(데이터)를 기반으로 해시 코드를 생성합니다.
   - 이 메서드를 잘 구현하면, 동일한 내용의 객체는 동일한 해시 코드를 반환하게 됩니다.

3. **해시 코드와 해시 테이블:**

   - 해시 코드는 해시 테이블의 버킷(Bucket)에 매핑됩니다.
   - 해시 테이블은 해시 코드를 사용하여 객체를 빠르게 검색하는 자료구조입니다.
   - 객체의 해시 코드를 사용하여 해시 테이블에서 검색, 삽입 및 삭제 작업을 수행할 수 있습니다.

4. **`hashCode()`와 `equals()` 관계:**

   - `hashCode()`를 오버라이드할 때, `equals()` 메서드도 함께 오버라이드해야 합니다.
   - 두 객체가 `equals()` 메서드로 같다고 판단되면, 그 두 객체는 반드시 같은 해시 코드를 가져야 합니다.
   - 이것은 해시 테이블에서 객체를 검색할 때 중요한 역할을 합니다.

5. **`hashCode()` 예제:**

   - 아래는 `hashCode()` 메서드를 오버라이드한 예제입니다.

   ```java
   public class Person {
       private String name;
       private int age;
   
       // 생성자와 다른 메서드들...
   
       @Override
       public int hashCode() {
           // 이 예제에서는 간단하게 name과 age의 해시 코드를 합칩니다.
           return name.hashCode() + age;
       }
   }
   ```

   이렇게 `hashCode()` 메서드를 잘 구현하면, 동일한 내용의 `Person` 객체는 동일한 해시 코드를 반환할 것이며, 해시 기반 컬렉션에서 효과적으로 사용할 수 있게 됩니다.

`hashCode()`를 정확하게 구현하는 것은 중요하지만 어렵지 않습니다. 일반적으로 IDE (예: IntelliJ IDEA, Eclipse)가 이를 자동으로 생성해주며, 객체 내부의 필드 값을 이용하여 해시 코드를 생성하는 것이 일반적입니다.



### 4.1  hashCode() 기본 예제

물론, `hashCode()` 메서드의 예제를 보여드리겠습니다. 아래 예제는 `Person` 클래스를 정의하고, `hashCode()` 메서드를 오버라이드하여 이름(name)과 나이(age) 필드를 이용해 해시 코드를 생성하는 방법을 보여줍니다. 주석과 설명을 포함하겠습니다:

```java
public class Main {
    private String name;
    private int age;

    public Main(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // hashCode() 메서드 오버라이드
    @Override
    public int hashCode() {
        // 해시 코드를 생성할 때, 필드들을 조합하여 유니크한 값을 만듭니다.
        int result = 17; // 초기 해시 코드 값 (임의의 정수)
        result = 31 * result + name.hashCode(); // 이름 필드를 해시 코드에 더함
        result = 31 * result + age; // 나이 필드를 해시 코드에 더함
        return result;
    }

    public static void main(String[] args) {
        Main person1 = new Main("Alice", 30);
        Main person2 = new Main("Bob", 25);
        Main person3 = new Main("Alice", 30);

        // 해시 코드 출력
        System.out.println("Hash Code for person1: " + person1.hashCode());
        System.out.println("Hash Code for person2: " + person2.hashCode());
        System.out.println("Hash Code for person3: " + person3.hashCode());

        // 객체의 내용을 비교
        boolean areEqual = person1.equals(person3); // true
        System.out.println("Are person1 and person3 equal? " + areEqual);
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=51889:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
Hash Code for person1: 1963877775
Hash Code for person2: 2092277
Hash Code for person3: 1963877775
Are person1 and person3 equal? false

종료 코드 0(으)로 완료된 프로세스
```

해시 코드를 생성하기 위해 일반적으로 사용되는 패턴은 다음과 같습니다:

1. 초기 해시 코드 값을 설정합니다. 일반적으로 17과 같은 임의의 값으로 초기화합니다.
2. 필드의 해시 코드를 계산하고 초기 해시 코드 값에 더합니다.
3. 필드의 해시 코드를 더할 때마다 31과 같은 소수(prime number)를 곱하여 값을 혼합(mix)합니다. 이렇게 하면 해시 코드가 더 강력하게 분배되어 충돌이 줄어듭니다.

위 예제에서, `name.hashCode()`는 문자열의 `hashCode()` 메서드를 호출하여 문자열의 해시 코드를 얻고, 그 값을 혼합하여 최종 해시 코드를 생성합니다. 이것은 동일한 내용을 가지는 객체는 동일한 해시 코드를 반환할 것입니다.

`equals(Object obj)` 메서드와 `hashCode()` 메서드를 함께 사용하여 객체의 동등성을 판단하고 해시 기반 컬렉션에서 객체를 검색하는 데 도움이 됩니다.



### 4.2 identityHashCode(Object x) 예제

`identityHashCode(Object x)`는 **자바에서 객체의 식별 해시 코드(Identity Hash Code)를 반환하는 메서드**입니다. 이 메서드는 객체의 내용이 아닌 **객체의 실제 메모리 주소(참조)에 기반하여 해시 코드를 생성**합니다. 이것은 두 객체가 서로 다른 인스턴스일지라도, 같은 메모리 주소를 참조하는 경우에는 동일한 식별 해시 코드를 반환합니다.

주로 디버깅 목적으로 사용되며, 객체의 내용이 같더라도 두 객체가 서로 다른 메모리 위치에 저장되어 있을 때, 이 두 객체를 식별하는 데 도움이 됩니다. 일반적으로 개발자들은 `equals()` 메서드를 사용하여 객체의 내용을 비교하고, `identityHashCode()`는 객체의 참조를 비교하는 데 사용합니다.

다음은 `identityHashCode(Object x)` 메서드의 간단한 사용 예제입니다:

```java
public class IdentityHashCodeExample {
    public static void main(String[] args) {
        String str1 = new String("Hello");
        String str2 = new String("Hello");
        String str3 = str1; // str3는 str1과 같은 메모리를 참조

        int hashCode1 = System.identityHashCode(str1);
        int hashCode2 = System.identityHashCode(str2);
        int hashCode3 = System.identityHashCode(str3);

        System.out.println("Identity Hash Code for str1: " + hashCode1);
        System.out.println("Identity Hash Code for str2: " + hashCode2);
        System.out.println("Identity Hash Code for str3: " + hashCode3);

        // equals() 메서드를 사용하여 내용을 비교
        boolean contentEquals = str1.equals(str2); // true

        // 동일한 메모리를 참조하는지(identity) 비교
        boolean identityEquals = (str1 == str3); // true

        System.out.println("Content Equals: " + contentEquals);
        System.out.println("Identity Equals: " + identityEquals);
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=51909:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
Identity Hash Code for str1: 1239731077
Identity Hash Code for str2: 557041912
Identity Hash Code for str3: 1239731077
Content Equals: true
Identity Equals: true

종료 코드 0(으)로 완료된 프로세스
```

이 예제에서 `System.identityHashCode()`를 사용하여 문자열 객체의 식별 해시 코드를 얻습니다. `str1`과 `str2`는 동일한 내용을 가지지만 서로 다른 객체를 참조하므로 `identityHashCode()`는 다른 값을 반환합니다. `str3`는 `str1`과 동일한 메모리를 참조하므로 같은 식별 해시 코드를 반환합니다. `equals()` 메서드를 사용하여 내용을 비교하면 `str1`과 `str2`가 같음을 확인할 수 있고, `==` 연산자를 사용하여 메모리 참조를 비교하면 `str1`과 `str3`가 같음을 확인할 수 있습니다.



## 5. getClass()

`getClass()`는 **자바에서 객체의 클래스 정보를 가져오는 메서드**입니다. 이 메서드는 `Object` 클래스에서 상속받은 메서드로, 모든 객체에서 사용할 수 있습니다. `getClass()` 메서드를 호출하면 해당 객체의 클래스를 나타내는 `Class` 객체를 반환합니다.

여기서 몇 가지 주요한 포인트를 설명하겠습니다:

1. **`Class` 객체:**

   `Class` 객체는 자바에서 클래스를 나타내는 메타정보를 담고 있는 객체입니다. 이 객체는 클래스의 런타임 정보를 제공하며, 클래스의 이름, 메서드, 필드, 생성자, 상속 관계 등과 같은 정보를 제공합니다. `Class` 객체는 각 클래스당 딱 하나만 존재하며, 클래스로부터 객체를 생성할 때 클래스 로더(ClassLoader)에 의해 자동으로 생성됩니다.

   아래는 `Class` 객체의 몇 가지 중요한 특징과 설명입니다:

   1. **클래스당 하나의 `Class` 객체:** 
      각 클래스는 단 하나의 `Class` 객체를 가지며, 이 객체는 해당 클래스의 런타임 정보를 포함합니다. 따라서 동일한 클래스를 여러 번 로드하더라도 해당 클래스의 `Class` 객체는 오직 한 번만 생성됩니다.
   2. **`Class` 리터럴을 사용하여 얻기:** 
      `Class` 객체를 얻는 가장 흔한 방법은 **클래스 리터럴(Class Literal)**을 사용하는 것입니다. 예를 들어, `String` 클래스의 `Class` 객체를 얻으려면 다음과 같이 사용합니다: `String.class`. 이렇게 얻은 `Class` 객체를 통해 해당 클래스의 정보를 조사하고 사용할 수 있습니다.
   3. **리플렉션(Reflection)을 통한 활용:** 
      `Class` 객체는 리플렉션을 사용하여 클래스의 구조를 동적으로 검사하고 클래스의 메서드, 필드, 생성자, 주석 등을 다루는 데 사용됩니다. 리플렉션은 런타임에 클래스 정보를 검사하고 동적으로 객체를 생성하거나 메서드를 호출하는 등의 작업을 수행하는 데 유용합니다.

   아래는 `Class` 객체를 사용하여 클래스 정보를 얻는 간단한 예제입니다:

   ```java
   import java.lang.reflect.Method;
   
   public class Main {
       public static void main(String[] args) {
           // Class 리터럴을 사용하여 String 클래스의 Class 객체를 얻음
           Class<?> stringClass = String.class;
   
           // Class 객체를 이용하여 클래스 이름을 출력
           System.out.println("Class name: " + stringClass.getName());
   
           // Class 객체를 이용하여 클래스의 메서드 정보 출력
           Method[] methods = stringClass.getMethods();
           System.out.println("Number of methods: " + methods.length);
       }
   }
   ```

   ```java
   C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=52455:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
   Class name: java.lang.String
   Number of methods: 82
   
   종료 코드 0(으)로 완료된 프로세스
   ```

   위의 예제에서 `String.class`를 사용하여 `String` 클래스의 `Class` 객체를 얻고, 이 객체를 통해 클래스 이름과 클래스의 메서드 정보를 출력합니다.

   `Class` 객체와 리플렉션은 고급 프로그래밍 작업에서 사용되며, 클래스 정보를 동적으로 조사하고 다루는 데 유용합니다.

1. **`getClass()` 사용:**
   - `getClass()` 메서드는 객체의 클래스 정보를 얻을 때 사용됩니다.
   - 객체의 메서드, 필드 및 다른 클래스 관련 정보를 확인하거나 객체의 실제 클래스를 확인하는 데 사용됩니다.

예제로 설명하겠습니다:

```java
public class Main {
    public static void main(String[] args) {
        String str = "Hello, World";
        Integer number = 42;

        // getClass()를 사용하여 객체의 클래스 정보를 얻음
        Class<?> strClass = str.getClass();
        Class<?> numberClass = number.getClass();

        // 클래스 정보를 출력
        System.out.println("Class of str: " + strClass.getName()); // java.lang.String
        System.out.println("Class of number: " + numberClass.getName()); // java.lang.Integer
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=52440:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
Class of str: java.lang.String
Class of number: java.lang.Integer

종료 코드 0(으)로 완료된 프로세스
```

이 예제에서는 `getClass()` 메서드를 사용하여 `str`과 `number` 객체의 클래스 정보를 가져옵니다. `str`은 문자열(`java.lang.String`) 클래스의 인스턴스이며, `number`는 정수(`java.lang.Integer`) 클래스의 인스턴스입니다. `getName()` 메서드를 사용하여 클래스의 이름을 얻을 수 있습니다.

`getClass()`는 주로 리플렉션(Reflection)이라고 불리는 프로그래밍 기술에서 사용되며, 런타임 시에 객체의 클래스 정보를 조사하고 다양한 동적 작업을 수행하는 데 유용합니다.



## 5. String 클래스

`String` 클래스는 자바에서 매우 중요한 클래스 중 하나이며, 자주 사용되는 클래스 중 하나입니다. 아래는 `String` 클래스에 대해 학습할 때 중요한 몇 가지 개념과 기능입니다:

1. **문자열 생성과 초기화:** 
   문자열을 생성하고 초기화하는 방법을 이해해야 합니다. `String` 클래스는 문자열 리터럴을 사용하여 생성하거나 `new` 키워드를 사용하여 객체를 생성할 수 있습니다.
2. **문자열 연산:** 
   문자열 연산을 수행하는 방법을 알아야 합니다. 자바에서 문자열은 불변(immutable)하며, 문자열을 연결하거나 수정할 때 새로운 문자열 객체가 생성됩니다.
3. **문자열 메서드:** 
   `String` 클래스는 다양한 메서드를 제공하여 문자열 조작, 검색, 비교, 추출, 대/소문자 변환 등 다양한 문자열 작업을 수행할 수 있습니다. 몇 가지 중요한 메서드는 `length()`, `charAt()`, `substring()`, `equals()`, `startsWith()`, `endsWith()`, `indexOf()`, `replace()`, `toLowerCase()`, `toUpperCase()` 등이 있습니다.
4. **문자열 리터럴과 문자열 객체의 차이:** 
   문자열 리터럴과 문자열 객체의 차이를 이해해야 합니다. 문자열 리터럴은 상수 풀(Constant Pool)에 저장되며, 동일한 문자열 리터럴을 참조하는 변수들은 동일한 객체를 공유합니다.
5. **문자열 불변성:** 
   문자열이 불변(immutable)하다는 개념을 이해해야 합니다. 즉, 한 번 생성된 문자열은 변경할 수 없으며, 문자열 연산을 수행하면 새로운 문자열이 생성됩니다.
6. **문자열 풀과 문자열 상수:** 
   문자열 리터럴은 문자열 상수 풀에 저장되며, 같은 내용을 가진 문자열 리터럴은 하나의 인스턴스만 생성됩니다. 이것은 문자열 비교 시 주의해야 하는 부분 중 하나입니다.
7. **StringBuilder와 StringBuffer:** 
   문자열 연산을 수행할 때, `StringBuilder`와 `StringBuffer` 클래스를 사용하여 문자열을 효율적으로 조작하는 방법을 이해해야 합니다. 이 두 클래스는 가변(mutable)한 문자열을 다룰 때 사용됩니다.
8. **문자열 비교:** 
   문자열 비교는 `equals()` 메서드나 `compareTo()` 메서드를 이용하여 수행됩니다. 문자열 비교는 내용 비교와 참조(주소) 비교가 있으므로 이 둘의 차이를 이해해야 합니다.
9. **정규 표현식:** 
   정규 표현식(Regular Expression)은 문자열 패턴 매칭 및 검색에 사용되며, `String` 클래스와 함께 자주 활용됩니다.

`String` 클래스의 위의 개념과 기능을 이해하면 문자열을 효과적으로 다루고 문자열 처리와 검색에 필요한 기본 도구를 습득할 수 있습니다.



### 5.1 문자열 생성과 초기화

- 문자열은 자바에서 문자들의 시퀀스(열)로 표현됩니다. 문자열을 생성하고 초기화하는 방법은 다양합니다.
- 가장 일반적인 방법은 **문자열 리터럴(String Literal)**을 사용하는 것입니다. **문자열 리터럴은 큰따옴표(" ")로 둘러싸인 문자열**입니다. 예를 들어: `"Hello, World"`는 문자열 리터럴입니다.

```java
String str1 = "Hello, World"; // 문자열 리터럴을 사용하여 문자열 초기화
```

- 또한 `new` 키워드를 사용하여 문자열 객체를 생성하고 초기화할 수도 있습니다.

```java
String str2 = new String("Hello, World"); // new 키워드를 사용하여 문자열 초기화
```

- 문자열 리터럴을 사용하는 경우, **문자열 풀(String Pool)**이라고 불리는 공유 문자열 상수 풀(Constant Pool)에 저장되며, 동일한 문자열 리터럴을 사용하는 변수들은 같은 문자열 객체를 참조합니다. 이것은 **문자열 리터럴을 사용한 문자열의 공유성을 의미**합니다.

```java
String str3 = "Hello, World"; // 같은 문자열 리터럴을 참조
```

- 문자열 초기화 후에는 해당 문자열은 변경할 수 없습니다. 문자열은 불변(immutable)하며, 문자열을 변경하면 새로운 문자열이 생성됩니다.

이러한 방식으로 문자열을 생성하고 초기화할 수 있으며, 이해하면 자바에서 문자열을 다루는 데 중요한 출발점이 됩니다. 문자열 리터럴은 자주 사용되며, 문자열을 생성하고 비교할 때 주로 활용됩니다.



**문자열 풀(String Pool)**


문자열 풀(String Pool) 또는 공유 문자열 상수 풀(Constant Pool)은 자바에서 문자열 리터럴(String Literal)을 저장하는 특별한 메모리 영역을 가리킵니다. 이 영역은 문자열 리터럴을 공유하여 메모리 사용을 최적화하고 문자열 상수의 중복을 피하는 데 사용됩니다.

다음은 문자열 풀과 문자열 상수 풀에 대한 설명입니다:

1. **문자열 풀(String Pool):**
   - 문자열 풀은 자바 런타임 환경에서 문자열 리터럴을 저장하는 메모리 영역입니다.
   - 문자열 리터럴은 큰따옴표(" ")로 둘러싸인 문자열 상수로, 예를 들어 `"Hello, World"`와 같은 것이 문자열 리터럴입니다.
   - 문자열 풀은 문자열 리터럴이 저장되는 곳으로, 동일한 문자열 리터럴을 사용하는 여러 변수나 객체가 해당 문자열 리터럴을 참조하게 됩니다.
   - 이로 인해 동일한 문자열을 공유하게 되며, 메모리를 절약하고 문자열 상수의 중복을 방지합니다.
2. **공유 문자열 상수 풀(Constant Pool):**
   - 문자열 풀은 자바의 상수 풀(Constant Pool) 중 하나로, 상수 풀은 문자열 뿐만 아니라 다른 상수 값(정수, 부동 소수점, 클래스 및 인터페이스 등)도 저장하는 메모리 영역입니다.
   - 따라서 공유 문자열 상수 풀은 문자열 리터럴을 저장하는 한 부분이며, 문자열만이 아니라 다른 상수 값도 포함됩니다.

예를 들어, 다음과 같은 코드를 고려해 보겠습니다:

```java
public class Main {
    public static void main(String[] args) {
        String str1 = "Hello, World"; // 문자열 리터럴
        String str2 = "Hello, World"; // 같은 문자열 리터럴
        String str3 = new String("Hello, World");

        System.out.println("str1 == str2 : " + (str1 == str2)); // true
        System.out.println("str1 == str3 :  " + (str1 == str3)); // false
        System.out.println("str1.equals(str2) = " + str1.equals(str2)); // true
        System.out.println("str1.equals(str3) = " + str1.equals(str3)); // true
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=52517:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
str1 == str2 : true
str1 == str3 :  false
str1.equals(str2) = true
str1.equals(str3) = true

종료 코드 0(으)로 완료된 프로세스
```

이 코드에서 `str1`과 `str2`는 동일한 문자열 리터럴을 참조하므로 `==` 연산자를 사용하여 참조 비교를 하면 true가 됩니다. 이는 두 변수가 동일한 문자열 객체를 가리키고 있기 때문입니다. 이런 공유 문자열 상수 풀의 동작을 통해 문자열의 중복을 피하고 메모리를 효율적으로 사용할 수 있습니다.

문자열 풀과 상수 풀은 자바 메모리 관리에서 중요한 역할을 합니다. 문자열 리터럴을 사용하면 문자열을 공유하여 메모리 사용을 최적화할 수 있으며, 문자열 상수의 중복을 방지합니다.



### 5.2 문자열 연산

문자열 연산은 자바에서 문자열을 조작하고 결합하는 작업을 의미합니다. `String` 클래스는 문자열을 불변(immutable)하게 다루기 때문에 문자열을 연결하거나 수정할 때 새로운 문자열 객체를 생성합니다. 아래에서 문자열 연산에 대한 자세한 설명을 제공하겠습니다.



1. **문자열 연결(Concatenation):**

   - 문자열 연결은 문자열을 결합하는 작업을 의미합니다. 
     이를 수행할 때 `+` 연산자를 사용하거나 `concat()` 메서드를 호출할 수 있습니다.
   
   ```java
   public class Main {
       public static void main(String[] args) {
           String str1 = "Hello, ";
           String str2 = "World";
           String result = str1 + str2; // 문자열 연결
           System.out.println("result = " + result);
       }
   }
   ```
   
   ```java
   C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=52545:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
   result = Hello, World
   
   종료 코드 0(으)로 완료된 프로세스
   ```
   
   - 문자열 연결을 수행하면 새로운 문자열 객체가 생성되며, `result`는 `"Hello, World"`를 참조합니다.



2. **문자열 수정 및 변환:**
   - `String` 클래스는 문자열을 직접 수정하는 메서드가 제한적입니다. 
     문자열은 불변하므로 수정할 수 없습니다. 대신, 문자열을 수정하려면 새로운 문자열을 생성해야 합니다.

```java
public class Main {
    public static void main(String[] args) {
        String original = "Hello";
        String modified = original + ", World"; // 문자열 연결을 사용한 수정
        System.out.println("modified = " + modified);
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=52539:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
modified = Hello, World

종료 코드 0(으)로 완료된 프로세스
```



3. **`StringBuilder` 및 `StringBuffer` 사용:**
   - 문자열을 효율적으로 수정하려면 `StringBuilder` 또는 `StringBuffer` 클래스를 사용해야 합니다. 
     이 두 클래스는 가변 문자열을 다루기 위해 설계되었으며, 문자열 수정 시에 메모리를 효율적으로 관리할 수 있습니다.

```java
public class Main {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder("Hello");
        builder.append(", World"); // 문자열 추가
        String result = builder.toString(); // StringBuilder를 String으로 변환
        System.out.println("result = " + result);
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=52550:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
result = Hello, World

종료 코드 0(으)로 완료된 프로세스
```



4. **문자열 형식화(Formatting):**
   - 문자열 내에 변수 또는 값을 삽입하여 형식화된 문자열을 생성하려면 `String.format()` 메서드 또는 `MessageFormat` 클래스와 같은 도구를 사용할 수 있습니다.

```java
public class Main {
    public static void main(String[] args) {
        String name = "Alice";
        int age = 30;
        String formattedString = String.format("Name: %s, Age: %d", name, age);
        System.out.println("formattedString = " + formattedString);
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=52557:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
formattedString = Name: Alice, Age: 30

종료 코드 0(으)로 완료된 프로세스
```



5. **문자열 분할(Splitting) 및 추출(Substring):**
   - 문자열에서 부분 문자열을 추출하거나 특정 패턴을 기반으로 문자열을 분할하는 메서드를 사용할 수 있습니다. 
     예를 들어, `split()`, `substring()`, `substring(int beginIndex, int endIndex)` 메서드를 사용합니다.

```java
public class Main {
    public static void main(String[] args) {
        String sentence = "This is a sample sentence.";
        String[] words = sentence.split(" "); // 공백을 기준으로 문자열 분할
        String subString = sentence.substring(5, 10); // 5번째 문자부터 10번째 문자까지 추출
        for (String word : words) { System.out.println("word = " + word); }
        System.out.println("subString = " + subString);
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=52582:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
word = This
word = is
word = a
word = sample
word = sentence.
subString = is a 

종료 코드 0(으)로 완료된 프로세스
```

문자열 연산은 자바 프로그래밍에서 매우 일반적이며, 문자열을 다루는 데 필수적인 작업입니다. 문자열을 효과적으로 조작하고 조합하는 방법을 이해하면 다양한 문자열 처리 작업을 수행할 수 있습니다.



### 5.3 문자열 메서드

자바의 `String` 클래스는 다양한 문자열 메서드를 제공하여 문자열을 조작하고 분석하는 데 도움을 줍니다. 문자열 메서드는 별도로 정리를 따로 할 예정입니다.



### 5.4 문자열 리터럴과 문자열 객체의 차이 

문자열 리터럴과 문자열 객체는 Java에서 문자열을 표현하는 두 가지 주요 방법입니다. 이들의 주요 차이점은 다음과 같습니다:

1. **리터럴 vs. 객체**:
   - **문자열 리터럴**: 문자열 리터럴은 큰 따옴표(" ")로 둘러싸인 문자열 값입니다. 예를 들어, `"Hello, World!"`는 문자열 리터럴입니다. 문자열 리터럴은 컴파일 시에 이미 생성되며, Java의 문자열 상수 풀(constant pool)에 저장됩니다. 따라서 동일한 문자열 리터럴이 여러 번 사용되면 실제로는 하나의 인스턴스만 생성됩니다.
   - **문자열 객체**: 문자열 객체는 `String` 클래스의 인스턴스를 생성하여 문자열을 저장하는 방법입니다. `new String("Hello, World!")`와 같이 생성할 수 있으며, 매번 새로운 인스턴스가 생성됩니다. 문자열 객체를 생성할 때마다 새로운 메모리 공간을 할당받습니다.
2. **불변성(Immutability)**:
   - 문자열 리터럴은 불변(immutable)합니다. 한 번 생성되면 그 값을 변경할 수 없습니다. 따라서 문자열 리터럴을 수정하려면 새로운 문자열을 생성해야 합니다.
   - 문자열 객체 역시 불변성을 가지지만, `String` 클래스의 일부 메서드를 사용하여 새로운 문자열을 생성하면서 기존 문자열을 수정하는 것처럼 보일 수 있습니다. 그러나 실제로는 수정된 문자열을 나타내는 새로운 `String` 객체가 생성됩니다.
3. **성능**:
   - 문자열 리터럴은 미리 생성되어 있으므로 동일한 문자열 리터럴을 여러 번 사용해도 추가 메모리를 소비하지 않습니다.
   - 문자열 객체는 메모리를 더 많이 사용할 수 있으며, 문자열을 수정할 때마다 새로운 객체가 생성되므로 성능 상의 오버헤드가 발생할 수 있습니다.

일반적으로 문자열 리터럴을 사용하는 것이 더 효율적이며 권장됩니다. 문자열 객체를 명시적으로 생성해야 하는 경우가 아니라면 문자열 리터럴을 사용하는 것이 좋습니다.



### 5.5 문자열 불변성

문자열의 불변성(Immutability)은 **문자열 데이터가 생성된 후에 그 값을 변경할 수 없음을 의미**합니다. Java에서 문자열은 불변 객체(Immutable Object)로 구현되어 있습니다. 이것은 문자열 값이 한 번 설정되면 해당 값을 변경할 수 없다는 것을 의미합니다. 이러한 문자열 불변성에 대한 몇 가지 중요한 특성과 이점이 있습니다:

1. **보안**: 불변한 문자열은 보안상의 이점을 제공합니다. 문자열 값이 변경할 수 없으므로 암호화된 문자열 또는 해시 함수의 결과 값을 안전하게 저장하고 전송할 수 있습니다.
2. **스레드 안정성**: 불변한 문자열은 여러 스레드에서 안전하게 공유할 수 있습니다. 수정할 필요가 없으므로 동기화에 대한 걱정이 없어집니다.
3. **캐싱**: 문자열 리터럴은 Java의 문자열 상수 풀(constant pool)에 저장되며, 동일한 문자열 리터럴을 여러 변수가 참조해도 메모리를 공유합니다. 이로 인해 성능 향상이 가능하며, 메모리를 절약할 수 있습니다.
4. **코드 안정성**: 불변한 문자열은 코드의 안정성을 높이고 오류를 줄일 수 있습니다. 예를 들어, 메소드에서 문자열을 다루는 경우 원래 문자열을 수정하지 않으므로 예기치 않은 부작용이 발생하지 않습니다.
5. **문자열 연결 및 조합**: 불변 문자열은 문자열 연결이나 조합 시에 새로운 문자열을 생성하며 원래 문자열을 변경하지 않습니다. 이로 인해 문자열 조작에 대한 안정성이 높아집니다.
6. **문자열 풀링**: 문자열 리터럴은 자동으로 문자열 풀에 저장되어 동일한 문자열 값이 많은 변수에서 공유됩니다. 이로써 중복 문자열을 효과적으로 관리할 수 있습니다.

불변한 문자열의 주요 단점은 문자열을 수정할 때마다 새로운 문자열 객체가 생성된다는 점입니다. 따라서 문자열 수정이 빈번하게 발생하는 경우 성능 문제가 발생할 수 있습니다. 이런 경우 `StringBuilder` 또는 `StringBuffer`와 같은 가변 문자열을 사용하는 것이 더 효율적일 수 있습니다.



### 5.6 문자열 풀과 문자열 상수


문자열 풀(String Pool)과 문자열 상수(String Constant)는 **Java에서 문자열 리터럴을 관리하고 최적화하는 메커니즘**입니다. 
이 두 개념은 밀접하게 관련되어 있으며, 아래에서 각각을 설명하겠습니다:

1. **문자열 풀(String Pool):**
   - 문자열 풀은 **메모리 내의 문자열 리터럴들을 관리하는 풀(또는 테이블)**입니다. 
     문자열 풀은 JVM(Java Virtual Machine) 내에 위치하며, 각 문자열 리터럴은 단 하나의 인스턴스만 존재하도록 유지됩니다.
   - 문자열 리터럴은 코드에서 큰 따옴표(" ")로 감싸진 문자열 값입니다. 예를 들어, `"Hello, World!"`는 문자열 리터럴입니다. 이러한 리터럴은 컴파일 시에 문자열 풀에 저장되며, 코드에서 동일한 리터럴을 여러 번 사용해도 하나의 인스턴스만 생성됩니다.
   - 문자열 풀은 중복 문자열을 효과적으로 관리하여 메모리 공간을 절약하고 문자열 리터럴을 재사용할 수 있게 합니다.
2. **문자열 상수(String Constant):**
   - 문자열 상수는 Java에서 사용자 정의한 문자열 값이 아닌, **문자열 리터럴을 가리키는 상수(상수 변수)를 의미**합니다. 
     `final` 키워드를 사용하여 선언된 변수들 중에서 문자열 리터럴을 가리키는 변수가 문자열 상수입니다.
   - 문자열 상수를 사용하면 컴파일러가 최적화를 수행할 수 있으며, 문자열 리터럴을 변경하지 않도록 보장합니다.
      따라서 문자열 상수를 사용하면 불변성을 보다 확실히 유지할 수 있습니다.

예를 들어, 다음 코드에서 `MESSAGE`는 문자열 상수입니다:

```java
final String MESSAGE = "Hello, World!";
```

이러한 문자열 상수를 사용하면 코드의 가독성을 높이고, 문자열 리터럴을 변경하지 못하도록 보호합니다.

중요한 참고 사항은 문자열 풀은 문자열 리터럴에 대한 관리를 수행하지만, 동적으로 생성된 문자열 객체에는 적용되지 않습니다. 즉, `new String("Hello, World!")`와 같이 동적으로 문자열 객체를 생성하는 경우 문자열 풀에 저장되지 않고, 매번 새로운 인스턴스가 생성됩니다.





### 5.7 StringBuilder와 StringBuffer

`StringBuilder`와 `StringBuffer` 클래스는 **Java에서 문자열을 동적으로 조작할 수 있는 클래스**입니다. 이 두 클래스는 문자열을 변경 가능한 가변(mutable) 형태로 다룰 수 있도록 도와줍니다. 주요 차이점은 **스레드 동기화(thread-safety)**와 관련이 있습니다.

1. **StringBuilder**
   - `StringBuilder`는 Java 5부터 도입된 클래스로, 문자열을 가변하게 조작할 때 주로 사용됩니다.
   - `StringBuilder` 클래스는 스레드 동기화를 고려하지 않기 때문에 단일 스레드 환경에서 사용하기에 효율적입니다.
   - `StringBuilder`를 사용하면 문자열을 효율적으로 연결하거나 수정할 수 있습니다. 
     예를 들어, 문자열을 반복해서 연결할 때 사용하기 좋습니다.

```java
StringBuilder sb = new StringBuilder();
sb.append("Hello");
sb.append(", ");
sb.append("World!");
String result = sb.toString(); // "Hello, World!"
```



2. **StringBuffer**
   - `StringBuffer`는 오래된 Java 버전부터 제공되는 클래스로, 문자열 조작 시 스레드 동기화를 지원합니다.
   - `StringBuffer`는 다중 스레드 환경에서 안전하게 사용할 수 있도록 설계되었기 때문에 스레드 간의 동시 접근에 대한 문제를 방지합니다.
   - `StringBuffer`는 `StringBuilder`와 유사한 기능을 제공하지만, 스레드 동기화 오버헤드로 인해 단일 스레드 환경에서는 `StringBuilder`보다 느릴 수 있습니다.

```java
StringBuffer sb = new StringBuffer();
sb.append("Hello");
sb.append(", ");
sb.append("World!");
String result = sb.toString(); // "Hello, World!"
```

일반적으로 단일 스레드 환경에서는 `StringBuilder`를 사용하는 것이 더 효율적이며, 다중 스레드 환경에서 스레드 안전성을 고려해야 할 때 `StringBuffer`를 사용할 수 있습니다. 현재 대부분의 상황에서 `StringBuilder`가 선호되는 클래스이며, 스레드 동기화가 필요한 특별한 경우에만 `StringBuffer`를 사용하는 것이 권장됩니다. 지금 간단하게 살펴보고, 핵심 생성자와 메서드에 대해서는 별도로 문자열 메서드와 함께 정리합니다.



### 5.8 문자열 비교

5.1 문자열의 생성과 초기화의 문자열 풀의 내용과 동일합니다.



### 5.9 정규 표현식

정규 표현식은 생략하고 넘어갑니다.

