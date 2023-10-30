# 객체지향 기본 다지기

> 1. 오버로딩(Overloading)
> 2. 오버라이딩(Overriding)
> 3. this, this()
> 4. super, super()
> 5. 제어자(Modifier)
> 6. abstract
> 7. 접근 제어자(Access Modifier)
> 8. 다형성(Polymorphism)
> 9. 인터페이스(Interface)
> 10. 내부 클래스(Inner class)

<br />


## 1. 오버로딩(Overloading)

자바에서는 한 클래스 내에 이미 사용하려는 이름과 동일한 이름을 가진 메서드가 있더라도 매개변수 개수 또는 타입이 다르면, 같은 이름을 사용해서 메서드를 정의할 수 있습니다. 이처럼, 한 클래스 내에 같은 이름의 메서드를 여러 개 정의하는 것을 "메서드 오버로딩(Method Overloading)" 또는 간단히 "오버로딩(Overloading)"이라고 합니다. 오버로딩(Overloading)의 사전적 의미는 '과적하다' 즉, 많이 싣는 것을 뜻합니다. 일반적으로 하나의 메서드 이름에 하나의 기능만을 구현해야하는데, 하나의 메서드 이름으로 여러 기능을 구현하기 때문에 붙여진 이름이라고 생각할 수 있습니다. 

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

다음 두 메서드는 매개변수의 이름만 다를 뿐 매개변수의 타입이 동일하기 때문에 오버로딩이 성립되지 않습니다. 매개변수의 이름이 다르면 메서드 내에서 사용되는 변수의 이름이 달라질 뿐, 어떠한 의미가 없습니다. 그래서 다음 두 메서드는 정확히 동일한 메서드로 오버로딩이 아닌 예제입니다. 다음 예제를 작성하면 컴파일 에러가 발생합니다.

```java
int add(int a, int b) { return a+b; }
int add(int x, int y) { return x+y; }
```

<br />

**리턴타입이 다르다고 오버로딩이 성립되지 않습니다.**

다음 두 메서드는 리턴타입만 다른 경우입니다. 매개변수의 타입과 개수는 일치합니다. add(3, 3)과 같이 호출하였을 때 어떤 메서드가 호출될 것인지 결정할 수 없기 때문에 오버로딩으로 간주되지 않습니다. 이 경우 역시 컴파일하면 'add(int, int) is already defind(이미 동일한 메서드가 정의되어 있습니다.)'라는 메시지가 출력됩니다.

```java
int add(int a, int b) { return a+b; }
long add(int x, int y) { return (long)x+y; }
```

<br />

**매개변수의 타입이 다르면서 서로 순서가 다른 경우 오버로딩이 성립됩니다.**

다음 두 메서드는 int형과 long형 매개변수가 한 개씩 선언되어 있지만, 서로 순서가 다른 경우입니다. 이 경우에는 호출 시 매개변수의 값에 의한 호출될 메서드가 구분될 수 있기 때문에 중복된 메서드 정의가 아닌, 오버로딩으로 성립됩니다. 이처럼 단지 매개변수의 순서만을 다르게 하여 오버로딩을 구현하면, 사용자가 매개변수의 순서를 외우지 않아도 된다는 장점이 있지만, 오히려 에러가 발생한 시점을 찾기 어려운 단점이 될 수도 있기 때문에 주의해야 합니다. 

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

오버라이딩은 메서드의 내용만 새로 작성하는 것입니다. 따라서 메서드의 선언부는 조상의 메서드와 모두 동일해야 합니다. 따라서 오버라이딩이 성립하기 위해 자손 클래스에서 오버라이딩하는 메서드는 조상 클래스의 메서드와 다음 세가지 조건을 반드시 만족해야 합니다. (단, JDK 1.5부터 '공변 반환타입(covariant return type)'이 추가되었습니다. 반환타입을 자식 클래스의 타입으로 변경하는 것이 가능하도록 조건이 완화된 점을 기억하세요.)

1. **이름이 같아야 한다.**
2. **매개변수가 같아야 한다.**
3. **반환타입이 같아야 한다.**

오버라이딩의 조건을 정리하면, 선언부가 모두 동일해야 합니다. 단, 접근 제어자(Access Modifier)와 예외(Exception)는 제한된 조건 하에서만 오버라이딩된 메서드를 다르게 변경할 수 있습니다.

<br />

### 2.2 오버라이딩된 메서드의 변경

1. **접근 제어자는 조상 클래스의 메서드보다 좁은 범위로 변경 할 수 없습니다.**

   조상 클래스 메서드의 접근 제어자가 protected일때, 오버라이딩하는 자식 클래스 메서드는 접근 제어자가 protected 또는 public이어야 합니다. 일반적으로 동일한 접근 제어자를 사용합니다. 

2. **조상 클래스의 메서드보다 많은 수의 예외를 선언할 수 없습니다.**

   조상 클래스 메서드에 IOException 선언하고, 자식 클래스 메서드에 Exception 선언은 불가능합니다.

3. **인스턴스 메서드를 static 메서드 또는 static 메서드를 인스턴스 메서드로 변경할 수 없습니다.**

   인스턴스 메서드와 static 메서드의 생성 시점이 다르기 때문입니다.

<br />

**조상 클래스의 static 메서드를 자식 클래스의 static 메서드로 정의할 수 있습니다.**

각 클래스에 별개의 static 메서드를 정의한 것으로 오버라이딩은 아닙니다. 각 메서드는 클래스 이름으로 구별됩니다. 호출할 때는 '참조변수.메서드명()' 대신 '클래스명.메서드명()'으로 호출하는 것이 바람직합니다. static 멤버들은 자신들이 정의된 클래스에 묶여있다고 생각하세요.

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

<br />

## 4. super, super()

super 키워드는 자식 클래스에서 조상 클래스로부터 상속받은 멤버를 참조하는데 사용되는 변수입니다. 멤버변수와 지역변수의 이름이 같을 때 this 키워드로 구별한 것처럼 상속받은 멤버와 자신의 멤버 이름이 동일하다면 super 키워드로 구별할 수 있습니다.

<br />

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

<br />

## 5. 제어자(Modifier)

제어자(Modifier)는 클래스, 메서드, 필드 및 다른 구성 요소의 특성과 동작을 변경하거나 제어하는 키워드입니다. 제어자는 객체 지향 프로그래밍에서 중요한 역할을 하며, 코드의 가시성, 상속 가능성, 접근 권한 등을 조절합니다. 제어자의 종류는 크게 접근 제어자와 그 외의 다른 제어자로 구분할 수 있습니다.

- **접근 제어자: public, protected, default, private**
- **다른 제어자: static, final, abstract, native, transient, synchronized, volatile, stricptfp**

제어자는 클래스나 멤버변수에 주로 사용되며, 하나의 대상에 대해서 여러 제어자를 조합하여 사용하는 것이 가능합니다. 단, 접근 제어자는 한 번에 네 가지 중 하나만 선택해서 사용할 수 있습니다. 예를 들어 하나의 대상에 대해서 public과 private을 함께 사용하는 것은 불가능합니다. 제어자들 간의 순서는 관계가 없습니다. 일반적으로 접근 제어자를 가장 왼쪽에 작성합니다.

<br />

### 5.1 static

static 키워드는 멤버변수, 메서드, 초기화 블럭,임포트, 상수에 사용됩니다. static 키워드가 붙은 멤버변수, 메서드는 객체가 생성되지 않아도 사용할 수 있습니다. static 키워드의 사용 예제는 다음과 같습니다.

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

<br />

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

<br />

## 6. abstract

abstract는 '추상적인'의 의미를 가지고 있습니다. 메서드의 선언부만 작성하고 실제 수행내용은 구현하지 않은 추상 메서드를 선언하는데 사용됩니다. 클래스에서도 사용하여 클래스 내에 추상 메서드가 존재한다는 것을 쉽게 알 수 있게 합니다.

**추상 클래스 (Abstract Class):**

1. **객체를 직접 생성할 수 없는 미완성 클래스:**
   추상 클래스는 미완성 클래스로, 객체를 직접 생성할 수 없습니다. 즉, 추상 클래스의 인스턴스를 생성할 수 없습니다. 대신, 추상 클래스는 다른 클래스들에게 상속을 통해 기본 구조와 동작을 상속하도록 하는 목적으로 사용됩니다.

2. **추상 메서드 포함:**
   추상 클래스는 하나 이상의 추상 메서드를 포함할 수 있습니다. 추상 메서드는 메서드 시그니처만을 가지고 있고 실제 메서드 본문은 제공하지 않습니다. 이러한 추상 메서드는 하위 클래스에서 구현되어야 합니다. 따라서 추상 클래스는 하위 클래스에게 특정 메서드를 구현하도록 강제할 수 있습니다.

3. **상속과 다형성:** 
   추상 클래스는 **다른 클래스에게 공통된 특성과 동작을 상속하기 위한 용도로 사용**됩니다. 이것은 상속을 통해 클래스 계층 구조를 만들고 다형성을 지원합니다. 추상 클래스를 상속하는 하위 클래스는 추상 클래스의 추상 메서드를 구체화하고 나만의 동작을 추가할 수 있습니다.

4. **인스턴스 변수와 구현된 메서드 포함 가능:** 
   추상 클래스는 **추상 메서드 외에도 일반 메서드와 인스턴스 변수를 포함할 수 있습니다.** 이렇게 하위 클래스에서 공통적으로 사용되는 메서드나 상태를 제공할 수 있습니다.

5. **예시:** 
   예를 들어, "동물"을 나타내는 추상 클래스를 만들 수 있으며, 이 클래스에서 "먹다"라는 메서드와 "소리를 내다"라는 추상 메서드를 정의할 수 있습니다. 이 추상 클래스를 상속받는 구체적인 동물 클래스들(예: 개, 고양이)은 "소리를 내다" 메서드를 자신의 방식으로 구현하고 "먹다" 메서드를 공유할 수 있습니다.

   ```java
   javaCopy codeabstract class Animal {
       void eat() {
           System.out.println("먹다");
       }
   
       abstract void makeSound(); // 추상 메서드
   }
   ```

추상 클래스는 코드의 재사용성과 유지보수를 강화하며, 공통된 동작을 정의하고 하위 클래스에서 이러한 동작을 구체화할 수 있도록 합니다. 이것은 객체 지향 프로그래밍에서 중요한 설계 원칙 중 하나인 "상속"을 구현하는 데 사용됩니다.

<br />

**추상 메서드(Abstract Class):**

1. **메서드 시그니처만 정의:** 
   추상 메서드는 메서드의 이름, 매개변수를 정의하지만 메서드의 실제 구현 내용을 포함하지 않습니다. 따라서 추상 메서드의 본문(메서드 내용)이 없습니다. 추상 메서드를 정의할 때는 메서드 선언 끝에 세미콜론을 사용하여 메서드 본문을 생략합니다.

```java
abstract void myAbstractMethod();
```

2. **하위 클래스에서 구현:** 추상 메서드는 추상 클래스나 인터페이스에 정의됩니다. 하위 클래스에서는 이러한 추상 메서드를 구현해야 합니다. 즉, 하위 클래스에서 메서드 본문을 제공하여 메서드를 실제로 실행할 내용을 정의해야 합니다.

   ```java
   class MyConcreteClass extends MyAbstractClass {
       void myAbstractMethod() {
           // 실제 메서드 구현 내용
       }
   }
   ```

3. **다형성 지원:** 추상 메서드를 사용하면 다형성을 지원합니다. 다형성은 동일한 메서드 이름을 가진 다양한 하위 클래스 객체에 대해 다양한 동작을 수행할 수 있게 해줍니다. 상위 클래스에서 정의한 추상 메서드를 하위 클래스에서 구체적으로 구현하면 다양한 하위 클래스에서 이 메서드를 호출할 때 각각의 하위 클래스의 동작이 실행됩니다.

   ```java
   MyAbstractClass obj1 = new MyConcreteClass1();
   MyAbstractClass obj2 = new MyConcreteClass2();
   
   obj1.myAbstractMethod(); // MyConcreteClass1의 구현이 실행
   obj2.myAbstractMethod(); // MyConcreteClass2의 구현이 실행
   ```

4. **강제성과 설계 유연성:** 추상 메서드를 사용하면 하위 클래스가 특정 메서드를 반드시 구현해야 하는 강제성을 부여할 수 있습니다. 이것은 프로그램의 구조를 더 강력하고 안정적으로 만들어주며, 코드의 유지보수와 확장을 용이하게 합니다.

추상 메서드는 주로 유사한 동작을 가진 다양한 클래스를 만들 때 사용되며, 이러한 클래스 간의 공통된 인터페이스를 정의하는 데 유용합니다. 예를 들어, 그림자형, 사각형, 원형 클래스에서 모두 "그리기" 메서드를 가지고 있을 때, 추상 메서드를 사용하여 각 도형 클래스에서 "그리기" 메서드를 구현할 수 있습니다. 이렇게 하면 코드의 재사용성이 증가하고 일관성 있는 인터페이스를 유지할 수 있습니다.



<br />



## 7. 접근 제어자(Access Modifier)

자바에서 접근 제어자(Access Modifiers)는 **클래스, 인터페이스, 메서드, 변수 등의 멤버에 대한 접근 권한을 제어하는 데 사용됩니다.** 이러한 제어자는 코드의 가시성과 보안을 조절하는데 도움이 되며, 다른 클래스나 패키지에서 멤버에 접근하는 권한을 결정합니다.

다음은 자바에서 사용되는 주요 접근 제어자와 그 설명입니다:

1. **public:**
   가장 넓은 범위의 접근 제어자입니다. public으로 선언된 멤버는 모든 클래스와 패키지에서 접근 가능합니다. 예를 들어, public 메서드는 어떤 클래스에서도 호출할 수 있습니다. **접근 제한이 전혀 없습니다.**
2. **protected:** 
   protected로 선언된 멤버는 같은 패키지에 있는 클래스들과 상속 관계에 있는 클래스에서만 접근 가능합니다. 다른 패키지의 클래스는 접근할 수 없습니다. **같은 패키지 내에서. 그리고 다른 패키지의 자식 클래스에서 접근이 가능합니다.**
3. **default (package-private):** 접근 제어자를 명시하지 않으면, 멤버는 패키지 내에서만 접근 가능합니다. 다른 패키지의 클래스에서는 접근할 수 없습니다. 이것은 아무런 접근 제어자를 사용하지 않았을 때의 기본 설정입니다. **같은 패키지 내에서만 접근이 가능합니다.**
4. **private:** private으로 선언된 멤버는 같은 클래스 내에서만 접근 가능합니다. 다른 클래스에서는 직접적으로 접근할 수 없으며, 이 멤버에 접근하기 위해 public 메서드를 사용하는 것이 일반적입니다. **같은 클래스 내에서만 접근이 가능합니다.**

예를 들어, 다음은 접근 제어자를 사용한 멤버 선언의 예입니다:

```java
public class MyClass {
    public int publicVar; // 다른 클래스에서 접근 가능

    protected int protectedVar; // 같은 패키지 및 하위 클래스에서 접근 가능

    int defaultVar; // 같은 패키지에서만 접근 가능 (package-private)

    private int privateVar; // 같은 클래스 내에서만 접근 가능
}
```

접근 제어자는 클래스와 멤버의 가시성을 관리하고, 코드의 캡슐화와 보안을 유지하는 데 도움이 됩니다. 클래스와 멤버에 적절한 접근 제어자를 선택하면 다른 개발자들과 협업하거나 라이브러리를 제공할 때 코드를 더 안전하고 관리하기 쉽게 만들 수 있습니다.

<br />

### 7.1 제어자(Modifier)의 조합

제어자가 사용될 수 있는 대상을 중심으로 제어자를 정리합니다. 제어자의 기본적인 의미와 그 대상에 딸느 의미 변화를 다시 한 번 되새겨 보는 시간입니다. 대상에 따라 사용할 수 있는 제어자는 다음과 같습니다.

| 대상     | 사용 가능한 제어자                        |
| -------- | ----------------------------------------- |
| 클래스   | public, (default), final, abstract        |
| 메서드   | 모든 접근 제어자, final, abstract, static |
| 멤버변수 | 모든 접근 제어자, final, static           |
| 지역변수 | final                                     |

1. **메서드에 static과 abstract를 함께 사용할 수 없습니다.**

   static 메서드는 몸통이 있는 메서트에만 사용할 수 있습니다.

2. **클래스에 abstract와 final을 동시에 사용할 수 없습니다.**

   클래스에 사용되는 final은 클래스를 확장할 수 없다는 의미이고 abstract는 상속을 통해서 완성되어야 한다는 의미이므로 서로 모순되기 때문입니다.

3. **abstract 메서드의 접근 제어자가 private일 수 없습니다.**

   abstract 메서드는 자식 클래스에서 구현해주어야 하는데 접근 제어자가 private이면 자식 클래스에서 접근할 수 없기 때문입니다.

4. **메서드에 private과 final을 같이 사용할 필요는 없습니다.**

   접근 제어자가 private인 메서드는 오버라이딩 될 수 없기 때문입니다. 이 둘 중 하나만 사용해도 의미가 충분합니다.



<br />



## 8. 다형성(Polymorphism)

다형성(Polymorphism)은 **여러 클래스 객체를 동일한 인터페이스를 통해 다루는 능력을 나타냅니다.** 다형성은 다양한 객체를 일반화된 방식으로 처리하여 코드의 재사용성과 유지보수성을 향상시키는 데 기여합니다. 다형성의 주요 특징과 개념은 다음과 같습니다.

1. **다양한 객체 다루기:** 
   다형성은 여러 클래스의 객체를 동일한 인터페이스나 슈퍼클래스의 참조로 다룰 수 있는 능력을 나타냅니다. 이것은 서로 다른 클래스의 객체들을 일반화하여 하나의 인터페이스를 공유하게 함으로써 객체의 유연한 다루기를 가능하게 합니다.
2. **메서드 다형성:** 
   다형성의 주요 형태 중 하나는 메서드 다형성입니다. 이것은 상속 관계에 있는 클래스에서 **오버라이딩(Overriding)**을 통해 동일한 메서드 이름을 사용하면서, 각 클래스의 특정 구현을 제공하는 능력을 의미합니다. 이렇게 하면 슈퍼클래스의 참조를 사용하면서 실제 객체의 타입에 따라 적절한 메서드 버전이 실행됩니다.
3. **인터페이스 다형성:** 
   인터페이스는 다형성을 구현하는 데 중요한 역할을 합니다. 여러 클래스가 동일한 인터페이스를 구현할 때, 이 인터페이스의 참조를 사용하여 해당 클래스의 객체를 동일한 방식으로 다룰 수 있습니다. 이것은 클래스 간의 결합도를 낮추고 코드 재사용을 촉진합니다.
4. **동적 바인딩:** 
   다형성은 동적 바인딩 또는 런타임 다형성을 사용하여 실행 중에 객체의 실제 타입을 확인하고 해당 타입의 메서드를 호출하는 기능을 제공합니다. 이것은 프로그램의 실행 중에 객체의 동작을 결정하는 데 사용됩니다.
5. **상속과 계층 구조:** 
   다형성은 클래스 간의 상속 관계와 계층 구조에서 발생합니다. 슈퍼클래스와 서브클래스 간의 다형성을 구현하려면 서브클래스에서 슈퍼클래스의 메서드를 오버라이딩해야 합니다.
6. **코드 재사용성:** 
   다형성을 사용하면 동일한 인터페이스를 가진 객체를 동일한 방식으로 다룰 수 있으므로 코드의 재사용성이 향상됩니다. 이것은 유지보수를 간단하게 만들고 새로운 클래스를 추가할 때 기존 코드에 영향을 주지 않고 확장할 수 있도록 합니다.

다형성의 예제를 주석과 함께 자세히 설명하겠습니다. 이 예제에서는 도형(Shape) 클래스와 원(Circle), 사각형(Rectangle), 삼각형(Triangle) 클래스를 사용하여 다형성을 보여줍니다.

```java
// 도형을 나타내는 슈퍼클래스
class Shape {
    public double calculateArea() {
        return 0.0; // 기본 면적은 0으로 설정
    }
}

// 원 클래스, Shape 클래스를 상속받음
class Circle extends Shape {
    private double radius; // 원의 반지름
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    // 부모 클래스의 calculateArea 메서드를 오버라이딩
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius; // 원의 면적을 계산
    }
}

// 사각형 클래스, Shape 클래스를 상속받음
class Rectangle extends Shape {
    private double width;
    private double height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    // 부모 클래스의 calculateArea 메서드를 오버라이딩
    @Override
    public double calculateArea() {
        return width * height; // 사각형의 면적을 계산
    }
}

// 삼각형 클래스, Shape 클래스를 상속받음
class Triangle extends Shape {
    private double base;
    private double height;
    
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    
    // 부모 클래스의 calculateArea 메서드를 오버라이딩
    @Override
    public double calculateArea() {
        return 0.5 * base * height; // 삼각형의 면적을 계산
    }
}

public class PolymorphismExample {
    public static void main(String[] args) {
        // 도형 객체를 다형성으로 다루기
        Shape[] shapes = new Shape[3];
        shapes[0] = new Circle(5.0);
        shapes[1] = new Rectangle(4.0, 6.0);
        shapes[2] = new Triangle(3.0, 4.0);
        
        // 모든 도형의 면적 출력
        for (Shape shape : shapes) {
            System.out.println("면적: " + shape.calculateArea());
        }
    }
}
```

이 예제에서, 다형성을 통해 다양한 도형 객체를 `Shape` 클래스 배열에 저장하고, `calculateArea` 메서드를 호출하여 각 도형의 면적을 계산합니다. `Shape` 클래스의 `calculateArea` 메서드를 오버라이딩한 하위 클래스의 구현이 각각 호출되어, 각 도형의 특정 면적이 출력됩니다. 이것은 동일한 인터페이스(부모 클래스)를 사용하여 서로 다른 객체를 다루는 다형성의 예제입니다.



<br />



### 8.1 참조변수의 형변환

다형성을 이해하고 활용하는 과정에서 **참조 변수의 형변환이 중요한 역할**을 합니다. 참조 변수의 형변환은 다양한 객체를 다루는 데 필요한 작업 중 하나로, 다형성과 함께 사용하여 객체를 정확하게 다루고 제어할 수 있게 합니다.

참조 변수의 형변환은 크게 두 가지 유형으로 업캐스팅과 다운캐스팅으로 나눌 수 있습니다



<br />



### 8.2 업캐스팅(Upcasting)

업캐스팅(Upcasting)은 **객체 지향 프로그래밍에서 중요한 개념 중 하나로, 서브클래스(하위 클래스) 객체를 슈퍼클래스(상위 클래스)의 참조 변수로 할당하는 작업을 의미**합니다. 이것은 다형성(Polymorphism)을 구현하는 중요한 부분 중 하나입니다. 업캐스팅은 다음과 같이 동작합니다.

1. **서브클래스 객체 생성:** 
   먼저, 서브클래스의 객체를 생성합니다. 이 객체는 슈퍼클래스의 하위 타입(subtype)입니다.
2. **슈퍼클래스 참조 변수 할당:** 
   생성한 서브클래스 객체를 슈퍼클래스의 참조 변수에 할당합니다. 이때 슈퍼클래스 참조 변수는 서브클래스 객체를 가리킬 수 있습니다.

업캐스팅은 자바에서 자동으로 이루어집니다. 이것은 슈퍼클래스 참조 변수가 서브클래스 객체를 가리킬 수 있고, 슈퍼클래스의 인터페이스를 통해 서브클래스의 객체를 다룰 수 있게 해줍니다. 업캐스팅의 주요 특징은 다음과 같습니다.

- **슈퍼클래스의 인터페이스 사용:** 
  업캐스팅된 객체는 슈퍼클래스의 인터페이스를 따르므로 슈퍼클래스에 정의된 메서드를 사용할 수 있습니다. 서브클래스에서 **오버라이딩(Overriding)한 메서드는 슈퍼클래스 참조 변수를 통해 호출될 때 실제 서브클래스의 메서드가 실행**됩니다.
- **서브클래스 특정 기능 숨김:** 
  업캐스팅을 통해 서브클래스의 특정 기능은 **슈퍼클래스 참조 변수를 통해 접근할 수 없게 됩니다.** 이것은 객체를 일반화하여 특정 동작을 숨기는 데 도움이 됩니다.

예를 들어, 다음은 업캐스팅의 간단한 예제입니다:

```java
class Animal {
    public void makeSound() {
        System.out.println("동물 소리");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("멍멍");
    }

    public void fetch() {
        System.out.println("공을 가져옵니다.");
    }
}

public class UpcastingExample {
    public static void main(String[] args) {
        Animal myDog = new Dog(); // 업캐스팅: Dog 객체를 Animal 참조로 할당
        myDog.makeSound(); // 다형성으로 인해 Dog 클래스의 makeSound 메서드 실행
        // myDog.fetch(); // 에러: Animal 참조로는 fetch 메서드에 접근 불가
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=61904:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
멍멍

종료 코드 0(으)로 완료된 프로세스
```

위의 예제에서 `myDog`는 `Dog` 객체를 `Animal` 참조로 업캐스팅한 것입니다. 이로써 `makeSound` 메서드는 `Dog` 클래스의 버전이 실행되지만 `fetch` 메서드에는 접근할 수 없습니다. 업캐스팅을 통해 `Dog` 객체를 일반적인 `Animal` 객체로 다룰 수 있습니다.



<br />



### 8.3 다운캐스팅 (Downcasting) 

다운캐스팅은 **슈퍼클래스(상위 클래스)의 참조 변수를 서브클래스(하위 클래스)의 참조 변수로 변환하는 작업을 의미**합니다. 다운캐스팅은 업캐스팅과는 반대로, **서브클래스에서 상속된 고유한 메서드나 속성을 사용하기 위해 슈퍼클래스 참조 변수를 다시 서브클래스 참조 변수로 변환합니다.** 다운캐스팅은 다음과 같이 동작합니다.

1. **슈퍼클래스 참조 변수를 서브클래스 참조 변수로 변환:** 
   슈퍼클래스의 참조 변수를 서브클래스의 참조 변수로 변환합니다. 이 작업은 명시적으로 수행되어야 합니다.
2. **서브클래스의 메서드 및 속성 사용:** 
   다운캐스팅된 참조 변수를 사용하여 서브클래스에서 상속한 메서드나 속성을 호출하거나 접근합니다.

다운캐스팅은 주로 업캐스팅된 객체를 원래의 서브클래스 타입으로 변환하여 특정 서브클래스 메서드나 속성을 사용해야 할 때 필요합니다. 그러나 다운캐스팅은 주의해서 사용해야 하며, **안전하게 수행하기 위해 `instanceof` 연산자를 함께 사용해야 합니다.** 다운캐스팅의 주요 특징은 다음과 같습니다.

- **명시적 형변환:** 
  다운캐스팅은 명시적으로 형변환을 수행해야 합니다. 이것은 `(Type)` 형태로 표현됩니다.
- **타입 확인 (instanceof):** 
  다운캐스팅하기 전에 `instanceof` 연산자를 사용하여 객체의 타입을 확인하는 것이 안전합니다. 이 연산자는 객체가 특정 클래스 또는 인터페이스의 인스턴스인지 확인합니다.

다음은 다운캐스팅의 예제입니다:

```java
class Animal {
    public void makeSound() {
        System.out.println("동물 소리");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("멍멍");
    }

    public void fetch() {
        System.out.println("공을 가져옵니다.");
    }
}

public class DowncastingExample {
    public static void main(String[] args) {
        Animal myDog = new Dog(); // 업캐스팅: Dog 객체를 Animal 참조로 할당
        
        // 다운캐스팅하기 전에 instanceof 연산자로 객체 타입 확인
        if (myDog instanceof Dog) {
            Dog dog = (Dog) myDog; // 다운캐스팅: Animal 참조를 Dog 참조로 변환
            dog.makeSound(); // Dog 클래스의 makeSound 메서드 실행
            dog.fetch(); // Dog 클래스의 fetch 메서드 실행
        }
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=62013:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
멍멍
공을 가져옵니다.

종료 코드 0(으)로 완료된 프로세스
```

위의 예제에서 `myDog`는 `Dog` 객체를 `Animal` 참조로 업캐스팅한 것입니다. 그 후 `instanceof` 연산자로 객체의 타입을 확인한 뒤, 다운캐스팅을 수행하여 `Animal` 참조 변수를 `Dog` 참조 변수로 변환합니다. 그러면 `Dog` 클래스의 메서드인 `makeSound` 및 `fetch`를 호출할 수 있습니다. 다운캐스팅은 객체의 타입을 확실하게 확인한 후에 수행해야 합니다.



<br />



## 9. 인터페이스(Interface)

인터페이스는 클래스가 특정 동작을 제공하기 위한 일종의 계약(Contract) 또는 추상 틀을 정의하는데 사용됩니다. 인터페이스는 **메서드의 선언, 상수, 디폴트 메서드, 정적 메서드 등을 정의**할 수 있으며, 클래스가 해당 인터페이스를 구현(Implements)하면 인터페이스에서 정의한 동작을 구현해야 합니다.

다음은 인터페이스의 주요 특징과 용도에 대한 설명입니다:

1. **추상 메서드 정의:** 
   인터페이스는 메서드의 선언을 포함하며, 이 메서드들은 구현부가 없는 추상 메서드로 정의됩니다. **구현은 해당 인터페이스를 구현하는 클래스에서 제공되어야 합니다.**
2. **다중 상속:** 
   자바에서는 클래스는 하나의 슈퍼클래스만 상속할 수 있지만, 인터페이스는 여러 개를 구현할 수 있습니다. 이를 통해 다중 상속의 일부 형태를 구현할 수 있습니다.
3. **계약(Contract):** 
   인터페이스는 클래스에 특정 동작을 제공하는 계약을 정의합니다. 클래스가 특정 인터페이스를 구현하면, 그 클래스는 해당 동작을 구현해야 합니다. 이것은 클래스 간의 표준화된 상호작용을 제공하고, 코드의 일관성을 유지하는 데 도움이 됩니다.
4. **유연한 설계:** 
   인터페이스를 사용하면 유연하고 확장 가능한 설계를 할 수 있습니다. 새로운 클래스가 특정 동작을 구현하기 위해 기존 인터페이스를 구현할 수 있으며, 이로써 기존 코드를 변경하지 않고도 새로운 동작을 추가할 수 있습니다.
5. **디폴트 메서드와 정적 메서드:**
   자바 8부터 인터페이스는 디폴트 메서드와 정적 메서드를 정의할 수 있게 되었습니다. **디폴트 메서드는 메서드의 기본 구현을 제공하며, 구현하는 클래스에서 오버라이**딩할 수 있습니다. **정적 메서드는 인터페이스 자체에 속하며 인스턴스를 필요로하지 않는 메서드를 정의**할 수 있습니다.

인터페이스는 객체 지향 설계의 핵심 요소 중 하나이며, 코드의 재사용성과 확장성을 높이는 데 큰 도움을 줍니다. 또한, 인터페이스는 다형성을 구현하는 데 중요한 역할을 합니다. 클래스가 여러 인터페이스를 구현하면 각 인터페이스의 메서드를 호출할 수 있으며, 다양한 동작을 수행할 수 있습니다.



<br />



### 9.1 인터페이스의 작성

인터페이스를 작성하는 것은 클래스를 작성하는 것과 동일합니다. 다만, class 키워드 대신 interface를 사용한다는 것만 다릅니다. 그리고 interface 또한 클래스와 동일하게 접근 제어자로 public 또는 default 사용이 가능합니다. 일반적인 클래스의 멤버들과 다르게 인터페이스의 멤버들은 다음과 같은 제약사항이 있습니다. 편의상 생략된 제어자는 컴파일 시에 자동적으로 추가가 됩니다.

- 모든 멤버변수는 public static final 이어야 하며, 이를 생략할 수 있다.
- 모든 메서드는 public abstract 이어야 하며, 이를 생략할 수 있다.(단, JDK1.8 이상부터 static 메서드와 default 메서드는 예외입니다.) 

```java
interface 인터페이스명 {
    public static final 타입 상수명 = 값;
    public abstract 메서드명(매개변수);
}
```



<br />



### 9.2 인터페이스 예제

**인터페이스 기본 구현 예제**

인터페이스를 이해하는 데 도움이 될 수 있는 간단한 예제를 통해 자세히 설명해드리겠습니다. 이 예제에서는 "도형"을 나타내는 인터페이스를 정의하고, 이 인터페이스를 구현하는 클래스들을 만들어 보겠습니다.

1. **인터페이스 정의:**
   도형을 나타내는 인터페이스 `Shape`를 정의합니다. 이 인터페이스는 `getArea` 메서드를 선언합니다.

```java
public interface Shape {
    double getArea();
}
```

<br />

2. **클래스 구현:**
   `Shape` 인터페이스를 구현하는 구체적인 클래스들을 만듭니다. 여기서는 원과 사각형을 나타내는 클래스를 만들어 보겠습니다.

```java
public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}
```

```java
public class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }
}
```

<br />

3. **활용:**
   이제 다양한 도형 객체를 생성하고 `Shape` 인터페이스를 통해 면적을 계산할 수 있습니다.

```java
public class ShapeExample {
    public static void main(String[] args) {
        Shape circle = new Circle(5.0);
        Shape rectangle = new Rectangle(4.0, 6.0);

        double circleArea = circle.getArea();
        double rectangleArea = rectangle.getArea();

        System.out.println("원의 면적: " + circleArea);
        System.out.println("사각형의 면적: " + rectangleArea);
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=62263:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
원의 면적: 78.53981633974483
사각형의 면적: 24.0

종료 코드 0(으)로 완료된 프로세스
```

이 예제에서, `Circle` 클래스와 `Rectangle` 클래스는 모두 `Shape` 인터페이스를 구현했습니다. 이로써 다양한 도형 객체를 `Shape` 인터페이스로 다룰 수 있습니다. 각 객체의 `getArea` 메서드가 호출될 때 해당 도형의 면적을 계산하게 됩니다.

인터페이스를 사용하면 동일한 메서드 이름을 가진 다양한 클래스를 일관된 방식으로 다룰 수 있으며, 이것은 코드의 재사용성과 유지보수성을 향상시키는 데 도움이 됩니다.

<br />

**인터페이스 다형성 구현 예제**

인터페이스를 사용하여 다형성을 구현하는 예제를 주석과 함께 자세히 설명하겠습니다.

```java
// 1. 도형을 나타내는 인터페이스 정의
interface Shape {
    // 면적을 계산하는 추상 메서드 선언
    double getArea();
}

// 2. 원을 구현하는 Circle 클래스
class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        // 원의 면적을 계산하여 반환
        return Math.PI * radius * radius;
    }
}

// 3. 사각형을 구현하는 Rectangle 클래스
class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        // 사각형의 면적을 계산하여 반환
        return width * height;
    }
}

public class PolymorphismWithInterfaces {
    public static void main(String[] args) {
        // 4. 인터페이스를 활용한 다형성
        Shape circle = new Circle(5.0); // Circle 객체를 Shape 인터페이스 참조로 업캐스팅
        Shape rectangle = new Rectangle(4.0, 6.0); // Rectangle 객체를 Shape 인터페이스 참조로 업캐스팅

        // 5. 다형성을 통해 면적 출력
        double circleArea = circle.getArea(); // 다형성으로 Circle의 getArea 메서드 호출
        double rectangleArea = rectangle.getArea(); // 다형성으로 Rectangle의 getArea 메서드 호출

        System.out.println("원의 면적: " + circleArea);
        System.out.println("사각형의 면적: " + rectangleArea);
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=62422:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
원의 면적: 78.53981633974483
사각형의 면적: 24.0

종료 코드 0(으)로 완료된 프로세스
```

이 예제에서 주요한 점은 다음과 같습니다:

1. 인터페이스 `Shape`을 정의합니다. 이 인터페이스는 도형을 나타내며 `getArea`라는 추상 메서드를 선언합니다.
2. `Circle` 클래스와 `Rectangle` 클래스는 각각 `Shape` 인터페이스를 구현합니다. 이 두 클래스는 `getArea` 메서드를 오버라이딩하여 원과 사각형의 면적을 계산합니다.
3. `PolymorphismWithInterfaces` 클래스에서 다형성을 활용합니다. `Circle` 객체와 `Rectangle` 객체를 각각 `Shape` 인터페이스 참조로 생성하고, 이를 이용해 다양한 도형 객체를 동일한 방식으로 다룹니다.
4. `Shape` 인터페이스를 구현한 클래스 객체를 `Shape` 참조 변수에 할당하면 업캐스팅이 이루어집니다.
5. 다형성을 활용하여 각 도형의 면적을 계산하고 출력합니다.

이것은 인터페이스를 사용하여 다형성을 구현하는 간단한 예제로, 동일한 인터페이스를 구현하는 다양한 클래스를 일관된 방식으로 다룰 수 있음을 보여줍니다.

<br />

## 10. 내부 클래스(Inner class)

내부 클래스는 자바에서 다른 클래스 내부에 정의된 클래스를 가리킵니다. **내부 클래스는 다른 클래스의 멤버로서 존재하며, 주로 특정 클래스와 밀접하게 관련된 기능을 구현하는 데 사용됩니다.** 내부 클래스의 주요 특징과 사용법을 설명하겠습니다:

1. **내부 클래스의 종류:**
   - **멤버 내부 클래스 (Member Inner Class):** 
     다른 클래스 내부에서 정의되는 클래스로, 멤버 변수처럼 인스턴스 멤버로 취급됩니다.
   - **정적 내부 클래스 (Static Nested Class):** 
     내부 클래스가 `static` 키워드로 정의되며, 외부 클래스의 인스턴스와 독립적으로 생성됩니다.
   - **지역 내부 클래스 (Local Inner Class):** 
     메서드 내부에서 정의되는 클래스로, 메서드 내에서만 사용할 수 있습니다.
   - **익명 내부 클래스 (Anonymous Inner Class):** 
     이름을 가지지 않는 내부 클래스로, 주로 인터페이스 또는 추상 클래스의 인스턴스를 생성할 때 사용됩니다.
2. **내부 클래스의 장점:**
   - **캡슐화**: 
     내부 클래스는 외부 클래스의 멤버 변수와 메서드에 쉽게 접근할 수 있습니다.
   - **코드의 논리적 그룹화**: 
     밀접한 관련이 있는 클래스와 인터페이스를 논리적으로 그룹화하여 코드의 가독성을 향상시킵니다.
   - **외부 클래스와의 높은 결합도**: 
     내부 클래스는 외부 클래스와 높은 결합도를 가질 수 있어, 외부 클래스의 멤버에 쉽게 접근할 수 있습니다.
3. **내부 클래스의 사용 예:** 
   내부 클래스는 주로 다음과 같은 상황에서 사용됩니다:
   - **캡슐화**: 내부 클래스를 사용하여 외부 클래스의 멤버를 보호하거나 숨길 수 있습니다.
   - **이벤트 핸들링**: GUI 프로그래밍에서 이벤트 핸들러를 구현하기 위해 내부 클래스가 자주 사용됩니다.
   - **컬렉션 프레임워크**: 자바의 컬렉션 프레임워크에서 내부 클래스를 사용하여 컬렉션의 특정 메서드를 구현합니다.



<br />



### 10.1 내부 클래스 예제

**내부 클래스 기본 예제**

```java
// 외부 클래스 정의
public class Main {
    private int outerField = 10; // 외부 클래스의 멤버 변수

    // 멤버 내부 클래스 정의
    class InnerClass {
        void displayOuterField() {
            System.out.println("outerField: " + outerField); // 외부 클래스의 멤버 변수에 접근
        }
    }

    public static void main(String[] args) {
        // 외부 클래스 객체 생성
        Main outer = new Main();

        // 내부 클래스 객체 생성
        InnerClass inner = outer.new InnerClass(); // 외부 클래스 객체를 이용하여 내부 클래스 객체 생성

        // 내부 클래스 메서드 호출
        inner.displayOuterField(); // 내부 클래스의 메서드에서 외부 클래스의 멤버 변수에 접근
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=62546:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
outerField: 10

종료 코드 0(으)로 완료된 프로세스
```

이 예제에서는 다음과 같은 주요 내용이 포함되어 있습니다:

1. `OuterClass`라는 외부 클래스를 정의합니다. 이 클래스에는 `outerField`라는 멤버 변수가 있습니다.
2. `InnerClass`라는 멤버 내부 클래스를 정의합니다. 이 내부 클래스는 외부 클래스의 멤버 변수 `outerField`에 접근할 수 있습니다.
3. `main` 메서드에서 다음 단계를 수행합니다:
   - 외부 클래스의 객체 `outer`를 생성합니다.
   - 내부 클래스 객체 `inner`를 `outer`를 통해 생성합니다. 이것은 내부 클래스를 외부 클래스의 인스턴스와 연결하는 방법입니다.
   - `inner.displayOuterField()`를 호출하여 내부 클래스의 메서드에서 외부 클래스의 멤버 변수에 접근하고 출력합니다.

내부 클래스를 사용하여 외부 클래스의 멤버 변수 및 메서드에 접근하고 활용할 수 있으며, 이것은 코드의 모듈화와 캡슐화에 도움을 줍니다.



<br />



**내부 클래스 종류별 예제**

1. **멤버 내부 클래스 (Member Inner Class):** 
   멤버 내부 클래스는 외부 클래스의 멤버로 선언되는 클래스입니다. 내부 클래스는 외부 클래스의 멤버 변수 및 메서드에 접근할 수 있습니다.

```java
public class OuterClass {
    private int outerField = 10;

    // 멤버 내부 클래스
    class InnerClass {
        void display() {
            System.out.println("outerField: " + outerField);
        }
    }

    public static void main(String[] args) {
        OuterClass outer = new OuterClass();
        OuterClass.InnerClass inner = outer.new InnerClass();
        inner.display();
    }
}
```



<br />



2. **정적 내부 클래스 (Static Nested Class):** 
   정적 내부 클래스는 `static` 키워드로 선언된 내부 클래스로, 외부 클래스의 인스턴스와 독립적으로 생성됩니다.

```java
public class OuterClass {
    private static int staticOuterField = 20;

    // 정적 내부 클래스
    static class StaticInnerClass {
        void display() {
            System.out.println("staticOuterField: " + staticOuterField);
        }
    }

    public static void main(String[] args) {
        OuterClass.StaticInnerClass inner = new OuterClass.StaticInnerClass();
        inner.display();
    }
}
```



<br />



3. **지역 내부 클래스 (Local Inner Class):** 
   지역 내부 클래스는 메서드 내에서 선언되는 클래스로, 메서드 내에서만 사용할 수 있습니다.

```java
public class OuterClass {
    private int outerField = 10;

    void display() {
        int localVariable = 5;

        // 지역 내부 클래스
        class LocalInnerClass {
            void displayOuterField() {
                System.out.println("outerField: " + outerField);
            }

            void displayLocalVariable() {
                System.out.println("localVariable: " + localVariable);
            }
        }

        LocalInnerClass inner = new LocalInnerClass();
        inner.displayOuterField();
        inner.displayLocalVariable();
    }

    public static void main(String[] args) {
        OuterClass outer = new OuterClass();
        outer.display();
    }
}
```



<br />



4. **익명 내부 클래스 (Anonymous Inner Class):** 
   익명 내부 클래스는 이름이 없는 클래스로, 주로 인터페이스 또는 추상 클래스의 인스턴스를 생성할 때 사용됩니다.

```java
public class AnonymousInnerClassExample {
    public static void main(String[] args) {
        // Runnable 인터페이스의 익명 내부 클래스
        Runnable runnable = new Runnable() {
            public void run() {
                System.out.println("익명 내부 클래스 실행");
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
```

위의 예제에서, `Runnable` 인터페이스의 익명 내부 클래스를 사용하여 스레드를 생성하고 실행합니다.

각 내부 클래스 유형은 특정한 상황 또는 요구사항에 맞게 선택됩니다. 멤버 내부 클래스는 외부 클래스의 인스턴스와 관련이 있으며, 정적 내부 클래스는 외부 클래스의 인스턴스와 독립적입니다. 지역 내부 클래스는 메서드 내에서 사용되며, 익명 내부 클래스는 간결한 인터페이스 구현을 위해 사용됩니다.