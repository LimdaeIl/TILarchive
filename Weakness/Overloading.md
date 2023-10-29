# 1000 핵심 포인트

> 1. 오버로딩(Overloading)
> 2. 오버라이딩(Overriding)
> 3. this, this()
> 4. super, super()
> 5. 제어자(Modifier)



## 1. 오버로딩(Overloading)

자바에서는 한 클래스 내에 이미 사용하려는 이름과 같은 이름을 가진 메서드가 있더라도 매개변수의 개수 또는 타입이 다르면, 같은 이름을 사용해서 메서드를 정의할 수 있습니다. 이처럼, 한 클래스 내에 같은 이름의 메서드를 여러 개 정의하는 것을 "메서드 오버로딩(Method Overloading)" 또는 간단히 "오버로딩(Overloading)"이라고 합니다. 오버로딩(Overloading)의 사전적 의미는 '과적하다' 즉, 많이 싣는 것을 뜻합니다. 일반적으로 하나의 메서드 이름에 하나의 기능만을 구현해야하는데, 하나의 메서드 이름으로 여러 기능을 구현하기 때문에 붙여진 이름이라고 생각할 수 있습니다. 

<br />

### 1.1 오버로딩의 조건

같은 이름의 메서드를 정의한다고 해서 무조건 오버로딩인 것은 아닙니다. 오버로딩이 성립하기 위해서는 다음의 두 조건을 반드시 만족해야 합니다. 만족하지 못할 경우에 메서드 중복 정의로 간주되어 컴파일 시에 에러가 발생하게 됩니다.

1. **메서드 이름이 같아야 한다.**
2. **매개변수의 개수 또는 타입이 달라야 한다.**

오버로딩된 메서드들은 매개변수에 의해서만 구별될 수 있습니다. 따라서 **반환 타입은 오버로딩을 구현하는데 어떠한 영향을 주지 못한다는 점에 주의**합니다.

<br />

### 1.2 오버로딩의 예

가장 대표적인 오버로딩의 예는 println 메서드입니다. println 메서드를 호출할 때 매개변수로 지정하는 값의 타입에 따라서 호출되는 println 메서드가 달라집니다. PrintStream 클래스에는 어떤 종류의 매개변수를 지정해도 출력할 수 있도록 다음과 같이 10개의 오버로딩된 println 메서드를 정의해놓고 있습니다. println 메서드를 호출할 때 매개변수로 넘겨주는 값의 타입에 따라서 오버로딩된 메서드들 중의 하나가 선택되어 실행되는 것입니다.

```java
void println()
void println(boolean x)
void println(char x)
void println(char[] x)
void println(double x)
void println(float x)
void println(int x)
void println(long x)
void println(Object x)
void println(String x)
```

<br />

**매개변수명이 다르다고 오버로딩이 성립되지 않습니다.**

다음 두 메서드는 매개변수의 이름만 다를 뿐 매개변수의 타입이 같이 때문에 오버로딩이 성립되지 않습니다. 매개변수의 이름이 다르면 메서드 내에서 사용되는 변수의 이름이 달라질 뿐, 어떠한 의미가 없습니다. 그래서 다음 두 메서드는 정확히 동일한 메서드로 오버로딩이 아닌 예제입니다.

```java
int add(int a, int b) { return a+b; }
int add(int x, int y) { return x+y; }
```

<br />

**리턴타입이 다르다고 오버로딩이 성립되지 않습니다.**

다음 두 메서드는 리턴타입만 다른 경우입니다. 매개변수의 타입과 개수가 일치하기 때문에 add(3, 3)과 같잉 호출하였을 때 어떤 메서드가 호출될 것인지 결정할 수 없기 때문에 오버로딩으로 간주되지 않습니다. 이 경우 역시 컴파일하면 'add(int, int) is already defind(이미 동일한 메서드가 정의되어 있습니다.)'라는 메시지가 출력됩니다.

```java
int add(int a, int b) { return a+b; }
long add(int x, int y) { return (long)x+y; }
```

<br />

**매개변수의 타입이 다르면서 서로 순서가 다른 경우 오버로딩이 성립됩니다.**

다음 두 메서드는 int형과 long형 매개변수가 한 개씩 선언되어 있지만, 서로 순서가 다른 경우입니다. 이 겨웅에는 호출 시 매개변수의 값에 의한 호출될 메서드가 구분될 수 있기 때문에 중복된 메서드 정의가 아닌, 오버로딩으로 성립됩니다. 이처럼 단지 매개변수의 순서만을 다르게 하여 오버로딩을 구현하면, 사용자가 매개변수의 순서를 외우지 않아도 된다는 장점이 있지만, 오히려 단점이 될 수도 있기 때문에 주의해야 합니다. 

```java
long add(int a, long b) { return a+b; }
long add(long a, int b) { return a+b; }
```

<br />

### 1.3 오버로딩의 장점

1. 메서드의 이름으로 기능을 쉽게 예측할 수 있습니다.
2. 메서드의 이름을 절약할 수 있습니다.

println 메서드와 동일하게 하나의 이름으로 여러 개의 메서드를 정의할 수 있습니다. 메서드의 이름을 짓는데 고민을 덜 수 있는 동시에 사용되었어야 할 메서드 이름을 다른 메서드의 이름으로 사용할 수 있기 때문입니다. 다음 예제는 add 메서드를 오버로딩한 예제 입니다.

```java
public class Main {
    // 정수형 매개변수를 받는 메서드
    public int add(int a, int b) {
        return a + b;
    }

    // 실수형 매개변수를 받는 메서드
    public double add(double a, double b) {
        return a + b;
    }

    // 문자열을 이어붙이는 메서드
    public String add(String str1, String str2) {
        return str1 + str2;
    }

    public static void main(String[] args) {
        Main calculator = new Main();

        int result1 = calculator.add(2, 3);                    // int 버전의 add 메서드 호출
        double result2 = calculator.add(2.5, 3.5);              // double 버전의 add 메서드 호출
        String result3 = calculator.add("Hello, ", "World!");   // String 버전의 add 메서드 호출

        System.out.println("Result 1: " + result1);
        System.out.println("Result 2: " + result2);
        System.out.println("Result 3: " + result3);
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=54779:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
Result 1: 5
Result 2: 6.0
Result 3: Hello, World!

종료 코드 0(으)로 완료된 프로세스
```

<br />

### 1.4 가변인자(varargs)와 오버로딩

JDK1.5부터 동적으로 매개변수의 개수를 동적으로 지정할 수 있습니다. 가변인자(Variable Arguments)는 **'타입...변수명'**과 같은 형식으로 선언하며, PrintStream 클래스의 printf()가 대표적입니다. `public PrintStream printf(String format, Object ... args) {...}` 으로 가변인자 외에도 매개변수가 더 있다면, 가변인자를 매개변수 중에서 제일 마지막에 선언해야 합니다. 그렇지 않으면, 컴파일 에러가 발생하게 됩니다. 가변인자인지 아닌지를 구별할 방법이 없기 때문에 허용하지 않는 것입니다.

```java
// 컴파일 에러 발생 - Object ... args 가변인자는 항상 마지막 매개변수이어야 합니다.
public PrintStream printf(Object ... args, String format) {...}
```

<br />

가변인자의 예제로 문자열을 하나로 결합하여 반환하는 concatenate 메서드를 구현합니다. 다음과 같이 매개변수의 개수를 다르게 해서 여러 개의 메서드를 작성해야 합니다. 이때, 가변인자를 사용하면 메서드를 하나로 간단하게 대체할 수 있습니다. 가변인자를 사용한concatenate 메서드는 인자가 아예 없어도 되고, 배열도 인자가 가능합니다. 단, 가변인자는 불필요한 공간 낭비를 발생합니다. 가변인자는 내부적으로 배열을 사용하며, 선언된 메서드를 호출할 때마다 배열이 새로 생성됩니다. 가변인자가 편리하지만, 비효율이 숨어있으므로 꼭 필요한 경우에만 가변인자를 사용해야 합니다.

```java
String concatenate(String s1, String s2) { ... }
String concatenate(String s1, String s2, String s3) { ... }
String concatenate(String s1, String s2, String s3, String s4) { ... }

String concatenate(String ... str) { ... }
```

<br />

그렇다면, 직접 매개변수 타입을 배열로 사용하는 방법과 가변인자의 차이점은 무엇인지 알아보겠습니다. 가장 큰 차이점은 배열은 반드시 인자를 지정해야 합니다. 그래서 다음과 같이 null 혹은 길이가 0인 배열을 인자로 지정해줘야 하는 불편함이 있습니다. 주의해야 할 점으로 가변인자를 선언한 메서드를 오버로딩하면, 메서드를 호출했을 때 구별되지 못하는 경우가 발생하기 쉽기 때문에 오버로딩하지 않아야 합니다.

```java
String concatenate(String[] str) { ... }

String result = concatenate(new String[0]); // 인자로 배열을 지정
String result = concatenate(null); // 인자로 null 을 지정
String result = concatenate(); // 에러 발생. 인자가 필요
```



<br />

## 2. 오버라이딩(Overriding)

조상 클래스로부터 상속받은 메서드의 내용을 변경하는 것을 오버라이딩이라고 합니다. 상속받은 메서드를 그대로 사용하기도 하지만, 자식 클래스 자신에 맞게 변경해야하는 경우가 많습니다. 이때 조상의 메서드를 오버라이딩합니다. 오버라이딩의 사전적 의미는 '~위에 덮어쓰다(overwrite)'입니다. 

<br />

### 2.1 오버라이딩의 조건

오버라이딩은 메서드의 내용만 새로 작성하는 것입니다. 따라서 메서드의 선언부는 조상의 메서드와 모두 동일해야 합니다. 따라서 오버라이딩이 성립하기 위해 자손 클래스에서 오버라이딩하는 메서드는 조상 클래스의 메서드와 다음 세가지 조건을 반드시 만족해야 합니다. (단, JDK 1.5부터 '공변 반환타입(covariant return type)'이 추가되었습니다. 반환타입을 자손 클래스의 타입으로 변경하는 것이 가능하도록 조건이 완화된 점을 기억하세요.)

1. **이름이 같아야 한다.**
2. **매개변수가 같아야 한다.**
3. **반환타입이 같아야 한다.**

오버라이딩의 조건을 정리하면, 선언부가 모두 동일해야 합니다. 단, 접근 제어자(Access Modifier)와 예외(Exception)는 제한된 조건 하에서만 오버라이딩된 메서드를 다르게 변경할 수 있습니다.

<br />

### 2.2 오버라이딩 메서드 변경

1. **접근 제어자는 조상 클래스의 메서드보다 좁은 범위로 변경 할 수 없습니다.**

   조상 클래스 메서드의 접근 제어자가 protected일때, 오버라이딩하는 자식 클래스 메서드는 접근 제어자가 protected 또는 public이어야 합니다. 일반적으로 동일한 접근 제어자를 사용합니다. 

2. **조상 클래스의 메서드보다 많은 수의 예외를 선언할 수 없습니다.**

   조상 클래스 메서드에 IOException 선언하고, 자식 클래스 메서드에 Exception 선언은 불가능합니다.

3. **인스턴스 메서드를 static 메서드 또는 static 메서드를 인스턴스 메서드로 변경할 수 없습니다.**

   인스턴스 메서드와 static 메서드의 생성 시점이 다르기 때문입니다.

<br />

**조상 클래스 static 메서드를 자식 클래스 static 메서드로 정의할 수 있습니다.**

각 클래스에 별개의 static 메서드를 정의한 것으로 오버라이딩은 아닙니다. 각 메서드는 클래스 이름으로 구별되고 호출할 떄는 '참조변수.메서드명()' 대신 '클래스명.메서드명()'으로 호출하는 것이 바람직합니다. static 멤버들은 자신들이 정의된 클래스에 묶여있다고 생각하세요.

<br />

### 2.3 오버로딩 vs. 오버라이딩

오버로딩은 이전에 없는 새로운 메서드를 추가하는 것이고, 오버라이딩은 조상으로부터 상속받은 메서드의 내용을 변경하는 것입니다.

- **오버로딩(Overloading): 기존에 없는 새로운 메서드를 정의(new)**
- **오버라이딩(Overriding): 상속받은 메서드의 내용을 변경(change, midify)**

```java
class Parent {
    void parentMethod() {}
}

class Child extends Parent {
    void parentMethod() {} // 오버라이딩
    void parentMethod(int i) {} // 오버로딩
    
    void childMethod() {}
    void childMethod(int i) {} // 오버로딩
    void childMethod() {} // 에러. 중복 정의됨
}
```



<br />

## 3. this, this()

같은 클래스의 멤버들 간에 서로 호출할 수 있는 것처럼 생성자 간에도 서로 호출이 가능합니다. 다음 두 조건은 만족시켜야 합니다.

1. **생성자의 이름으로 클래스 이름 대신 this를 사용합니다.**
2. **한 생성자에서 다른 생성자를 호출할 때는 반드시 첫 줄에만 호출이 가능합니다.**

<br />

**this는 '참조 변수'이고, this()는 '생성자'입니다.**

this는 인스턴스 자신을 가리키는 참조변수로 인스턴스의 주소가 저장되어 있습니다. this(), this(매개변수)는 생성자로, 같은 클래스의 다른 생성자를 호출할 때 사용합니다. 서로 비슷하게 생겼을 뿐 완전히 다르다는 점에 주의해야 합니다.

<br />

**반드시 첫번째 줄에서 this() 키워드로 호출합니다.**

다음 예제는 생성자를 작성할 때 지켜야하는 두 조건을 모두 만족시키지 않습니다. 다른 생성자를 호출할 때는 반드시 첫 번째 줄에 작성하고, 클래스 이름인 'Car' 대신 'this'를 사용하지 않았습니다. 따라서 에러가 발생하게 됩니다.

```java
Car(String color) {
    door = 5; 
    Car(color, "auto", 4); // 에러1. 생성자의 두 번째 줄에서 다른 생성자 호출
} 						 // 에러2. this(color, "auto", 4); 작성해야 함 
```

<br />

**this는 현재 객체(인스턴스)를 가리키는 키워드입니다.**

일반적으로 this는 인스턴스 변수와 메서드를 참조할 때 사용됩니다. 이는 클래스 내부에서 현재 객체의 인스턴스 변수와 메서드에 접근할 때 유용합니다. Myclass 클래스 생성자의 매개변수는 필드 매개변수의 타입과 이름이 동일합니다. 이때 this 키워드로 필드의 매개변수인지 생성자의 매개변수인지 구분할 수 있습니다. this 키워드와 함께 사용된 매개변수는 필드의 매개변수를 의미하고 this 키워드가 없는 매개변수는 지역변수인 생성자의 매개변수입니다.

```java
public class MyClass {
    private int value;

    public MyClass(int value) {
        // 현재 객체의 value 인스턴스 변수에 인자로 전달된 값을 할당
        this.value = value;
    }

    public void printValue() {
        // 현재 객체의 value 인스턴스 변수를 출력
        System.out.println(this.value);
    }
}
```

<br />

**this()는 같은 클래스 내에서 다른 생성자를 호출할 때 사용합니다.**

중복되는 초기화 코드를 피하고 코드 재사용성을 높일 수 있습니다. 주로 다음과 같이 사용됩니다.

```java
public class MyClass {
    private int value;

    // 첫 번째 생성자
    public MyClass() {
        this(0); // 다른 생성자 호출
    }

    // 두 번째 생성자
    public MyClass(int value) {
        this.value = value;
    }
}
```

<br />

**static 메서드에서는 this 키워드는 사용할 수 없습니다.**

static 메서드는 인스턴스를 생성하지 않고도 호출될 수 있으므로 static 메서드가 호출된 시점에 인스턴스가 존재하지 않을 수 있습니다. static 메서드는 인스턴스와 관련 없는 작업을 수행하기 때문에 인스턴스에 대한 정보가 필요없습니다.



## 4. super, super()

super 키워드는 자식 클래스에서 조상 클래스로부터 상속받은 멤버를 참조하는데 사용되는 변수입니다. 멤버변수와 지역변수의 이름이 같을 때 this 키워드로 구별한 것처럼 상속받은 멤버와 자신의 멤버 이름이 동일하다면 super 키워드로 구별할 수 있습니다.



**super 키워드는 자식 클래스에서 부모 클래스의 멤버(필드 또는 메서드)에 접근할 때 사용합니다.** 

같은 이름의 멤버가 자식 클래스와 부모 클래스에 있는 경우, super 키워드를 사용하여 부모 클래스의 멤버에 접근할 수 있습니다. 예를 들어 다음 예제에서 `super.speak()`는 부모 클래스인 `Animal`의 `speak` 메서드를 호출하며, `super.name`은 부모 클래스의 `name` 필드에 접근합니다.

```java
class Animal {
    String name = "동물";

    void speak() {
        System.out.println("동물이 소리를 내고 있습니다.");
    }
}

class Dog extends Animal {
    String name = "강아지";

    void speak() {
        super.speak(); // 부모 클래스의 speak 메서드 호출
        System.out.println("강아지가 짖고 있습니다.");
    }

    void printNames() {
        System.out.println(super.name); // 부모 클래스의 name 필드에 접근
        System.out.println(this.name);   // 자식 클래스의 name 필드에 접근
    }
}

class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.speak();
        dog.printNames();

        Animal animal = new Animal();
        animal.speak();
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=56647:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
동물이 소리를 내고 있습니다.
강아지가 짖고 있습니다.
동물
강아지
동물이 소리를 내고 있습니다.

종료 코드 0(으)로 완료된 프로세스
```

<br />

**super()는 자식 클래스의 생성자에서 부모 클래스의 생성자를 호출하는 데 사용됩니다.** 

자식 클래스 객체를 생성할 때, 먼저 부모 클래스의 생성자를 호출하여 초기화 작업을 수행합니다. 이를 통해 상속 관계에서 부모 클래스의 초기화 코드를 실행할 수 있습니다. 다음 예제에서 `Dog` 클래스의 생성자에서 `super(name)`을 호출하여 부모 클래스인 `Animal`의 생성자를 호출하고, 부모 클래스의 필드를 초기화합니다. 그런 다음 자식 클래스의 필드도 초기화됩니다.

```java
class Animal {
    String name;

    Animal(String name) {
        this.name = name;
    }
}

class Dog extends Animal {
    String breed;

    Dog(String name, String breed) {
        super(name); // 부모 클래스의 생성자 호출
        this.breed = breed;
    }
}

class Main {
    public static void main(String[] args) {
        Dog dog = new Dog("망고", "말티즈");
        System.out.println("dog.breed = " + dog.breed);
        System.out.println("dog.name = " + dog.name);

        Animal animal = new Animal("보리");
        System.out.println("animal = " + animal.name);
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=56762:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
dog.breed = 말티즈
dog.name = 망고
animal = 보리

종료 코드 0(으)로 완료된 프로세스
```



## 5. 제어자(Modifier)

제어자(Modifier)는 클래스, 메서드, 필드 및 다른 구성 요소의 특성과 동작을 변경하거나 제어하는 키워드입니다. 제어자는 객체 지향 프로그래밍에서 중요한 역할을 하며, 코드의 가시성, 상속 가능성, 접근 권한 등을 조절합니다. 제어자의 종류는 크게 접근 제어자와 그 외의 다른 제어자로 구분할 수 있습니다.

- **접근 제어자: public, protected, default, private**
- **다른 제어자: static, final, abstract, native, transient, synchronized, volatile, stricptfp**

제어자는 클래스나 멤버변수에 주로 사용되며, 하나의 대상에 대해서 여러 제어자를 조합하여 사용하는 것이 가능합니다. 단, 접근 제어자는 한 번에 네 가지 중 하나만 선택해서 사용할 수 있습니다. 예를 들어 하나의 대상에 대해서 public과 private을 함께 사용하는 것은 불가능합니다. 제어자들 간의 순서는 관계가 없습니다. 일반적으로 접근 제어자를 가장 왼쪽에 작성합니다.



### 5.1 static

static 키워드는 멤버변수, 메서드, 초기화 블럭,임포트, 상수에 사용됩니다. static 키워드가 붙은 멤버변수, 메서드는 객체가 생성되지 않아도 사용할 수 있습니다.

1. **정적 변수 (Static Variables)**:

   - 클래스 수준에 선언된 변수를 정적 변수 또는 클래스 변수라고 합니다. 이 변수는 클래스의 모든 인스턴스(객체) 간에 공유됩니다. 객체가 생성되지 않아도 사용할 수 있으며, 일반적으로 클래스 이름을 통해 접근됩니다.

   ```java
   class MyClass {
       static int staticVar = 10;
   
       public static void main(String[] args) {
           System.out.println(MyClass.staticVar);
       }
   }
   ```

2. **정적 메서드 (Static Methods)**:

   - 클래스 수준에 선언된 메서드를 정적 메서드 또는 클래스 메서드라고 합니다. 정적 메서드는 인스턴스를 생성하지 않고 클래스 이름을 통해 직접 호출할 수 있습니다.

   ```java
   class MathUtil {
       static int add(int a, int b) {
           return a + b;
       }
   
       public static void main(String[] args) {
           int sum = MathUtil.add(5, 3);
           System.out.println("Sum: " + sum);
       }
   }
   ```

3. **정적 블록 (Static Blocks)**:

   - 정적 초기화 블록은 클래스가 로드될 때 실행되는 코드 블록으로, 클래스 변수의 초기화나 클래스 레벨에서의 기타 초기화 작업에 사용됩니다. 클래스가 메모리에 로드될 때 단 한 번만 수행됩니다.

   ```java
   class MyClass {
       static {
           System.out.println("This is a static block.");
       }
   }
   ```

4. **정적 임포트 (Static Import)**:

   - `import static` 구문을 사용하여 클래스의 정적 멤버(변수 또는 메서드)를 클래스 이름 없이 직접 사용할 수 있도록 합니다.

   ```java
   import static java.lang.Math.PI;
   
   public class CircleArea {
       public static void main(String[] args) {
           double radius = 5.0;
           double area = PI * radius * radius;
           System.out.println("Circle Area: " + area);
       }
   }
   ```

5. **정적 상수 (Static Constants)**:

   - `static final` 키워드를 사용하여 클래스 수준의 상수를 정의할 수 있습니다. 이러한 상수는 수정할 수 없는 값을 나타내며, 주로 대문자로 명명됩니다.

   ```java
   class Constants {
       static final int MAX_VALUE = 100;
   }
   ```



### 5.2 final

final은 자바 프로그래밍 언어에서 사용되는 키워드로, 다양한 컨텍스트에서 다양한 의미를 가집니다. final 키워드가 사용되는 주요 상황과 의미를 아래에서 설명하겠습니다.

1. **final 변수 (Final Variables)**:

   - `final`로 선언된 변수는 상수(constant)로 취급됩니다. 이 변수는 한 번 할당되면 변경할 수 없으며, 반드시 초기값을 할당해야 합니다. 주로 대문자로 명명하며, 변경되지 않는 상수 값을 나타내기 위해 사용됩니다.

   ```java
   final int MAX_VALUE = 100;
   ```

2. **final 메서드 (Final Methods)**:

   - `final`로 선언된 메서드는 **하위 클래스에서 오버라이딩(재정의)할 수 없습니다.** 즉, 해당 메서드의 동작을 변경할 수 없습니다. 이를 통해 메서드의 동작을 고정하고 상속 시 변경을 방지할 수 있습니다.

   ```java
   class Parent {
       final void finalMethod() {
           // 이 메서드는 하위 클래스에서 오버라이딩할 수 없음
       }
   }
   ```

3. **final 클래스 (Final Classes)**:

   - `final`로 선언된 클래스는 **상속할 수 없는 클래스로 간주됩니다.** 즉, 이 클래스의 하위 클래스를 생성할 수 없습니다. 이러한 클래스는 수정될 수 없는 클래스로 사용될 때 유용합니다.

   ```java
   final class FinalClass {
       // 이 클래스는 상속할 수 없음
   }
   ```

4. **final 매개변수 (Final Parameters)**:

   - `final`로 선언된 매개변수는 메서드나 생성자에서 한 번 할당되면 변경할 수 없습니다. 이를 통해 매개변수의 불변성(Immutability)을 보장하고, 코드의 안정성을 높일 수 있습니다.

   ```java
   void process(final int value) {
       // value는 이 메서드 내에서 변경할 수 없음
   }
   ```

5. **final 참조 (Final References)**:

   - `final` 키워드를 사용하여 참조 변수를 final로 선언하면 해당 참조 변수는 다른 객체를 참조할 수 없으며, 참조된 객체 자체에 대한 변경은 허용됩니다. 이를 통해 참조 변수의 불변성을 보장할 수 있습니다.

   ```java
   final List<String> names = new ArrayList<>();
   names.add("Alice");  // 가능
   names = new ArrayList<>();  // 불가능
   ```

`final`을 사용하는 주요 이점은 코드의 안정성, 가독성, 효율성을 향상시키고, 오버라이딩을 통한 부작용을 방지하는 등 다양합니다. `final`을 적절하게 활용하면 코드의 예측 가능성과 유지 관리성을 향상시킬 수 있습니다.