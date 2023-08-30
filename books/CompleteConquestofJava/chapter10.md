# 클래스의 상속과 다형성



## 10.1 클래스 상속의 개념과 문법적 특징



### 10.1.1 상속의 개념

- **상속** : 부모 클래스의 멤버(필드, 메서드, 이너 클래스)를 내려받아 자식 클래스 내부에 포함



### 10.1.2 상속의 장점

- 코드의 중복성이 제거
- 다형적 표현이 가능 :arrow_right: 다형성(Polymorphism)



### 10.1.3 상속 문법

클래스를 상속할 때는 extends 키워드를 사용하며, 클래스명 다음에 `extends 부모클래스` 를 표기합니다.

- 자바의 클래스는 단 하나의 부모클래스만 상속이 가능하며, 다중 상속이 불가능합니다.

```java
class 자식클래스 extends 부모클래스 {
    ...
}
```



### 10.1.4 상속할 떄의 메모리 구조

상속받은 자식클래스는 부모클래스의 모든 멤버를 내려받습니다. 상속받은 자식 클래스의 객체가 생성되는 메모리의 구조를 살펴보면 자연스럽게 이해될 수 있습니다.

```java
class A{
    int m;
    void abc(){...}
}

class B extends A {
    int n;
    void bcd(){...}
}

B b = new B();
```

클래스 영역에는 선언된 자료형의 클래스와 그 부모클래스가 모두 로딩됩니다. 이 때, 참조변수 b는 B 자료형으로 힙 메모리에 있는 B 타입 객체만을 가리킬 수 있게 됩니다. 자바 가상 머신은 자식 클래스의 객체를 생성할 때 가장 먼저 부모 클래스의 객체를 생성합니다. 이후 자식 클래스에서 추가한 필드와 메서드가 객체에 추가됨으로써 클래스 B의 전체 객체가 완성됩니다.

정리하자면, 자식클래스 객체의 내부에는 부모클래스 객체가 포함돼 있으므로 자식클래스 객체에서 부모클래스의 멤버를 사용할 수 있습니다.



### 10.1.5 생성자의 상속 여부

상속을 수행하면 부모의 모든 멤버를 사용할 수 있습니다. 멤버는 클래스의 4가지 내부 구성 요소 중 생성자를 제외한 필드, 메서드, 이너 클래스를 의미합니다. 생성자는 자식클래스로 상속되지 않습니다. 



### 10.1.6 객체의 다형적 표현

- 자식 클래스의 객체를 부모 클래스 타입으로 선언하는 모든 다형적 표현이 가능

```java
A a1 = new A(); // A는 A이다.
A a2 = new B(); // B는 A이다.
```

```java
// 클래스 A, B, C, D의 상속 구조

class A {}
class B extends A {}
class C extends B {}
class D extends B {}

// 다형적 표현의 올바른 사용 예
A a = new A();	// (O)
B b = new B();	// (O)
C c = new C();	// (O)
D d = new D();	// (O)

A a1 = new B();	// (O)
A a2 = new C();	// (O)
A a3 = new D();	// (O)

B b1 = new C();	// (O)
B b2 = new D();	// (O)

// 다형적 표현의 잘못된 사용 예
B b1 = new A();	// (X)

C c1 = new A();	// (X)
C c2 = new B();	// (X)

D d1 = new A();	// (X)
D d2 = new B();	// (X)
D d3 = new C();	// (X)
```





## 10.2 객체의 타입 변환

자료형이 서로 다를 떄는 컴파일러가 자동으로 타입을 변환해 주거나 개발자가 직접 명시적으로 타이블 변환해 줘야 합니다. 객체에서도 이러한 타입 변환이 발생하는데, 이를 업캐스팅, 다운캐스팅이라고 합니다.



### 10.2.1 객체의 업캐스팅과 다운캐스팅

- **업캐스팅** : 범위가 좁은 쪽에서 넓은 쪽으로 캐스팅으로 자식클래스에서 부모클래스 쪽으로 변환하는 것을 의미
- **다운캐스팅** : 범위가 넓은 쪽에서 좁은 쪽으로 캐스팅으로 부모클래스에서 자식클래스  쪽으로 변환하는 것을 의미

객체는 항상 업캐스팅할 수 있으므로 명시적으로 적어 주지 않아도 컴파일러가 대신 넣어줍니다. 하지만 다운캐스팅은 개발자가 직접 명시적으로 넣어 줘야 합니다. 그런데 객체의 다운캐스팅에는 기본 자료형에는 없는 새로운 문제가 있습니다. 기본 자료형에서 다운캐스팅할 때는 넓은 범위의 값이 좁은 범위로 바뀌기 때문에 오차가 발생하긴 하지만, 문법적으로는 항상 가능했습니다. 하지만 객체는 명시적으로 적어 준다고 해도 다운캐스팅 자체가 안되는 경우가 있습니다. 잘못된 다운캐스팅을 수행하면 ClassCastException 이라는 예외가 발생되고, 프로그램이 종료됩니다.



```java
class A {}
class B extends A {}
class C extends B {}

// 자동 타입 변환(업캐스팅)
B b1 = new B();
A a1 = b1;

C c2 = new C();
B b2 = c2; // 컴파일러가 자동으로 B b2 = (B)C2
A a2 = c2; // 컴파일러가 자동으로 A a2 = (A)C2

// 수동 타입 변환(다운캐스팅)
A a1 = new A();
B b1 = a1; // 예외 발생! B b1 = (B)a1;

A a2 = new B();
B b2 = (B)a2; // (O)
C c2 = (C)a2; // 예외 발생
```

다운캐스팅은 컴파일러가 자동으로 추가하지 않으므로 등호를 중심으로 좌우의 자료형이 동일하도록 명시적으로 캐스팅을 수행해야 문법적으로 오류가 발생하지 않습니다. 캐스팅의 가능 여부는 무슨 타입으로 선언돼 있는지는 중요하지 않으며 어떤 생성자로 생성됐는지가  중요합니다. 

### 10.2.3 선언 타입에 따른 차이점

```java
class A{
    int m = 3;
    void abc() {
        System.out.println("A");
    }
}

class B extends A{
    int n = 4;
    void bcd() {
        System.out.println("B");
    }
}

// B의 객체를 B 타입으로 선언했을 때
B b = new B();
System.out.println(b.m); // (O)
System.out.println(b.n); // (O)
b.abc(); // (O)
b.bcd(); // (O)

// B의 객체를 A 타입으로 선언했을 때
A a = new B();
System.out.println(a.m); // (O)
System.out.println(a.n); // (X)
a.abc(); // (O)
a.bcd(); // (X)
```



### 10.2.4 캐스팅 가능 여부를 확인하는 instanceof 키워드

캐스팅할 수 있는지를 확인하려면 실제 객체를 어떤 생성자로 만들었는지와 클래스 사이의 상속 관계를 확인해야 합니다.이 때 캐스팅 가능 여부를 불리언 타입으로 확인할 수 있는 문법 요소를 제공하고 있는데, 바로 instanceof 입니다.

```java
// 캐스팅 가능 여부 확인
참조변수 instanceof 타입 
```





## 10.3 메서드 오버라이딩



### 10.3.1 메서드 오버라이딩의 개념과 동작

메서드 오버라이딩(Method Overriding)은 부모 클래스에게 상속받은 메서드와 동일한 이름의 메서드를 재정의하는 것으로, 부모의 메서드를 자신이 마든 메서드로 덮어쓰는 개념입니다.

- 부모클래스의 메서드와 매개변수와 리턴 타입이 동일해야 합니다.
- 부모클래스의 메서드보다 접근 지정자의 범위가 같거나 넓어야 합니다.

```java
// 클래스 A와 B의 상속 관계 및 print() 메서드의 오버라이딩
class A{
    void print() {
        System.out.println("A 클래스");
    }
}

class B extends A{
    void print() {
        System.out.println("B 클래스");
    }
}
```



```java
// 메서드 오버라이딩의 기본 동작

class A {
    void print() {
        System.out.println("A 클래스");
    }
}

class B {
    void print() {
        System.out.printnln("B 클래스");
    }
}

public class MethodOverriding_1 {
    public static void main(String[] args) {
        A aa = new A();
        aa.print(); // A 클래스
        
        B bb = new B();
        bb.print(); // B 클래스
        
        A ab = new B();
        ab.print(); // B 클래스
    }
}
```



### 10.3.2 메서드 오버라이딩을 사용하는 이유

모든 객체를 부모 타입 하나로 선언하면 다음처럼 배열로 한 번에 관리할 수 있다는 장점이 있다.

```java
Animal[] animals = new Animal[]{ new Bird(), new Cat(), new Dog()};
for (Animal animal : animals) {
    animal.cry();
} // 짹짹, 야옹, 멍멍
```



### 10.3.3 메서드 오버라이딩과 메서드 오버로딩

```java
// 메서드 오버라이딩과 메서드 오버로딩
class A {
    void print1() {
        System.out.println("A 클래스 print1");
    }
    
    void print2() {
        System.out.println("A 클래스 print2");
    }
}

class B extends A {
    void print1() { // 메서드 오버라이딩
        System.out.println("B 클래스 print1");
    }
    
    void print2(int a) { // 메서드 오버로딩
        System.out.println("B 클래스 print2");
    }
}
```



### 10.3.4 메서드 오버라이딩과 접근 지정자

자식클래스가 부모클래스의 메서드를 오버라이딩할 때는 반드시 상속받은 메서드의 접근 지정자와 범위가 같거나 넓은 접근 지정자를 사용해야 합니다. 따라서 접근 지정자의 범위를 좁힐 수 없습니다. 예를 들어 부모 클래스의 메서드가 default 접근 지정자를 포함하고 있을 때 자식 클래스는 default 접근 지정자와 같거나 큰 범위의 접근 지정자인 protected, public, default 접근 지정만 사용할 수 있습니다.

| 부모 클래스 메서드의 접근 지정자 | 메서드 오버라이딩을 할 때 사용할 수 있는 접근 지정자 |
| -------------------------------- | ---------------------------------------------------- |
| public                           | public                                               |
| protected                        | public, protected                                    |
| default                          | public, protected, default                           |
| private                          | public, protected, default, private                  |

<center>
    메서드 오버라이딩할 때 사용할 수 있는 접근 지정자
</center>



## 10.4 인스턴스 필드와 정적 멤버의 중복

인스턴스 필드는 상속받은 필드와 동일한 이름으로 자식 클래스에서 정의해도 각각의 저장 공간에 저장되므로 오버라이딩은 발생하지 않습니다.

```java
class A {
    int m = 3;
}

class B extends A {
    int m = 4;
}

public class OverlapInstanceField {
    public static void main(String[] args) {
        A aa = new A();
        B bb = new B();
        A ab = new B();
        
        System.out.println(aa.m); // 3
        System.out.println(bb.m); // 4
        System.out.println(ab.m); // 3
        
    }
}
```



정적 필드명을 중복해 정의해도 저장 공간이 분리돼 있으므로 오버라이딩은 발생하지 않습니다.

```java
class A {
    static int m = 3;
}

class B extends A {
    static int m = 4;
}

public class OverlapInstanceField {
    public static void main(String[] args) {
        System.out.println(A.m); // 3
        System.out.println(B.m); // 4
        System.out.println();
        
        // 객체 생성
        A aa = new A();
        B bb = new B();
        A ab = new B();
        
        // 생성한 객체로 정적 필드 호출
        System.out.println(aa.m); // 3
        System.out.println(bb.m); // 4
        System.out.println(ab.m); // 3
        
    }
}
```



### 10.4.3 정적 메서드의 중복

인스턴스 메서드가 오버라이딩됐던 이유는 동일한 공간에 동일한 이름의 메서드를 저장했기 때문입니다. 하지만 정적 메서드는 정적 필드와 마찬가지로 각자의 클래스 내부에 존재합니다. 즉, 다른 공간에 저장되는 것입니다.

```java
class A {
    static void print(){
        System.out.println("A");
    }
}

class B extends A {
    static void print() {
        System.out.println("B")
    }
}

public class OverlapStaticMetnhod {
    public static void main(String[] args) {
        A.print(); // A
        B.print(); // B
        System.out.println();
        
        A aa = new A();
        B bb = new B();
        A ab = new B();
        
        aa.print(); // A
        bb.print(); // B
        ab.print(); // A
    }
}
```



### 10.4.4 인스턴스 멤버와 정적 멤버의 중복 정리

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20230827121156382.png" alt="image-20230827121156382" style="zoom:75%;" />

여기에서 기준점은 '값을 읽을 때의 기준'을 의미합니다. 예를 들어 인스턴스 필드 m이 클래스 A와 클래스 B에 모두 있다면 `A a = new B()`와 같이 표현했을 때 인스턴스 필드의 기준점은 선언 타입이므로 `a.m`은 클래스 A의 필드값을 가리키는 것입니다. 멤버도 이와 마찬가지로 이해하면 됩니다. 즉, 인스턴스 메서드는 객체가 어떤 생성자로 생성됐는지, 나머지는 어떤 타입으로 선언됐는지가 기준이 되는 것입니다.



## 10.5 super 키워드와 super() 메서드



**this 키워드와 this() 메서드 복습하기**

 this 키워드와 this() 메서드는 클래스 자신의 내부 구성 요소를 호출하는 문법 요소입니다.  this는 자신의 객체, this()는 자신의 생성자를 의미합니다. `this` 자기 객체를 가리키는 참조 변수명으로 인스턴스 메서드 내부에서 필드를 사용하거나 메서드를 호출할 때 참조 변수명으로 사용하고, 생략했을 때 컴파일러가 자동으로 추가합니다. `this()` 메서드는 자신의 또 다른 생성자를 호출하고, 생성자 내에서만 사용할 수 있으며, 항상 첫 줄에 위치해야 합니다.

학습하게 될 super 와 super() 는 모두 부모 클래스와 관련이 있으므로 상속 관계에서만 사용이 가능합니다.



### 10.5.1 부모의 객체를 가리키는 super  키워드

- super 키워드는 부모의 객체를 가리키는 것을 의미합니다.
- super 키워드는 필드명의 중복 또는 메서드 오버라이딩으로 가려진 부모의 필드 또는 메서드를 호출하기 위해 사용합니다.

```java
// 멤버 앞에 있는 참조 변수를 생략(this.)했을 때의 메서드 호출

class A {
    void abc() {
        System.out.println("A 클래스의 abc()");
    }
}

class B extends A {
    void abc() {
        System.out.println("B 클래스의 abc()");
    }
    
    void bcd() {
        abc();	// this.abc();
    }
}

public class SuperKeyword_1 {
    public static void main(String[] args) {
        B bb = new B();
        bb.bcd();
    }
}
```

```java
/* 실행 결과 */
B 클래스의 abc()
```





```java
// 멤버 앞에 있는 super 키워드를 사용했을 때의 메서드 호출

class A {
    void abc() {
        System.out.println("A 클래스의 abc()");
    }
}

class B extends A {
    void abc() {
        System.out.println("B 클래스의 abc()");
    }
    
    void bcd() {
        super.abc();	// this.abc();
    }
}

public class SuperKeyword_1 {
    public static void main(String[] args) {
        B bb = new B();
        bb.bcd();
    }
}
```

```java
/* 실행 결과 */
A 클래스의 abc()
```



**부모의 메서드를 호출해야 하는 경우**

자식 클래스에서 부모 클래스의 초기화 기능에 화면 출력 기능만 있는 코드 1줄만 추가하고 싶다고 가정합니다. 이때 super 키워드가 없다면 자식 클래스에는 부모 클래스의 100여 줄과 추가할 코드 1줄까지 모두 작성해야 합니다. 반면 super 키워드를 사용하면 자식 클래스의 메서드에서 부모 클래스의 메서드를 호출하고, 추가할 코드 1줄만 따로 작성하면 됩니다.

```java
class A {
    void init() {
        // 메모리 할당, 화면 세팅, 변수 초기화 등의 코드 100줄
    }
}

class B extends A {
    void init() {
        // 메모리 할당, 화면 세팅, 변수 초기화 등의 코드 100줄
        // 화면 출력 코드 1줄
    }
}

class C extends A {
    void init() {
        super.init();
        // 화면 출력 코드 1줄, 총 2줄
    }
}
```



### 10.5.2 부모 클래스의 생성자를 호출하는 super() 클래스

```java
class A {
    A() {
        System.out.println("A 생성자");
    }
}

class B extends A {
    B() {
        super();
        System.out.println("B 생성자");
    }
}
```

```java
/* 실행 결과 */
A 생성자
B 생성자
```

모든 생성자의 첫 줄에는 반드시 this() 또는 super() 가 있어야 합니다. 만일 아무것도 써 주지 않으면 컴파일러는 super()를 자동으로 삽입합니다. 즉, 생성자를 호출할 때는 항상 부모 클래스의 생성자가 한 번은 호출된다는 것 입니다. 이게 바로 자식 클래스의 생성자로 객체를 생성할 때 부모 클래스의 객체가 만들어지는 이유입니다.



**컴파일러에 의해 자동으로 super() 메서드가 추가되는지  확인하기**

```java
class A {
    A(int a) {
        System.out.println("A 생성자");
    }
}

class B extends A {
    // 오류 발생!
}
```

B 클래스 안에는 생성자가 없으므로 컴파일러가 기본 생성자를 자동으로 삽입합니다. 또한 모든 생성자의 첫 줄에는 this() 또는 super() 가 있어야 하므로 컴파일러는 추가로 기본 생성자의 첫 줄에 super() 메서드를 추가합니다. 앞에서 설명한 것처럼 super()는 부모의 기본 생성자, 즉 A()를 호출하라는 의미입니다. 

하지만 A 클래스 안에는 기본 생성자를 포함하고 있지 않으므로 오류가 발생합니다. 이를 해결하기 위해서는 B 클래스에 생성자를 직접 작성하고 첫 줄에 super(3)과 같이 정수를 입력받는 부모의 생성자를 명시적으로 호출해야 합니다.



**this() 메서드와 super() 메서드 활용하기**

- 자식 클래스를 호출하면 부모 클래스의 기본 생성자가 호출된다는 점을 기억하세요.

```java
// this() 메서드와 super() 메서드의 혼용

class A {
    A() {
        this(3);
        System.out.println("A 생성자 1");
    }
    
    A(int a) {
        System.out.println("A 생성자 2");
    }
}

class B extends A {
    B() {
        this(3);
        Sysetm.out.println("B 생성자 1");
    }
    B(int a) {
        System.out.println("B 생성자 2");
    }
}

public class SuperMethod_2 {
    public static void main(String[] args){
        A aa = new A();
        System.out.println();
        A aa2 = new A(3);
        System.out.println();
        
        B bb1 = new B();
        System.out.println();
        B bb2 = new B(3);
        
    }
}
```

```java
/* 실행 결과 */
A 생성자 2
A 생성자 1

A 생성자 2

A 생성자 2
A 생성자 1
B 생성자 2
B 생성자 1

A 생성자 2
A 생성자 1
B 생성자 2
```

위의 실행 결과는 클래스 A의 두 번째 생성자의 첫 줄에는 this()도 super()도 없으므로 역시 super()가 들어가야 하는데, A 클래스는 아무것도 상속받지 않았습니다. 지금까지 학습한 내용으로 본다면, 오류가 발생하는 것이 맞을까요? 이 질문의 답은 최상위 클래스인 Object 클래스에서 배울 수 있습니다.



## 10.6 최상위 클래스 Object

- Ojbect 클래스는 자바의 모든 클래스가 상속받는 클래스로 자바의 최상위 클래스이다.
- 어떠한 클래스도 상속하지 않으면 컴파일러가 자동으로 extends Object 를 삽입해 Object 클래스를 상속한다.
- 자바의 모든 클래스는 어떤 객체로 생성하든지 Object 타입으로 선언이 가능하다.

```java
// 컴파일 전
class A {
    
}
class B extends A {
    
}
```

```java
// 컴파일 후(자동으로 Object 클래스 상속)
class A extends Object {
    
}
clss B extends A {
    
}
```



**임의의 클래스를 Object 타입으로 선언하기**

```java
// 임의의 클래스를 Object 타입으로 선언하는 예
Ojbect oa = new A();
Ojbect ob = new B();
```

자바의 모든 클래스는 어떠한 객체로 생성하더라도 Object 타입으로 선언이 가능하다는 것은 매우 큰 장점입니다. 메서드 오버로딩에서 설명한 println() 메서드는 다양한 타입을 출력하기 위해 여러 개의 입력매개변수 타입으로 오버로딩돼 있다는 것을 확인했습니다. 10개의 타입을 출력하는 기능을 부여하려면 10개의 메서드로 오버로딩으로 작성해야 한다는 것을 의미합니다. 

하지만 이상한 점은 System.out.println(new A())와 같이 사용자가 직접 만든 클래스 타입도 출력이 가능하다는 것입니다. 사용자가 만들 타입을 미리 생각해서 오버로딩을 자동으로 수행하는 것은 아닙니다. 사용자가 어떤 클래스 타입의 객체를 생성하더라도 오버로딩한 System.out.println(Object x) 때문에 입력매개변수로 모든 타입의 객체를 받아들일 수 있는 것입니다.



### 10.6.1 Ojbect 클래스의 주요 메서드

| 반환 타입 | 메서드명                                                     | 주요 내용                                                    |
| --------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| String    | `toString()`                                                 | - Object 객체의 정보 `패캐지.클래스명@해시코드`<br />- 일반적으로 오버라이딩해서 사용 |
| boolean   | `eqauls(Object obj)`                                         | - 입력매개변수 obj 객체와 stack 메모리값(번지) 비교<br />- 등가 비교 연산자 ==와 동일한 결과 |
| int       | `hashCode()`                                                 | - 객체의 hashCode() 값 리턴. Hashtable, HashMap 등의 동등 비교에 사용<br />- 위치값을 기반으로 생성된 고유값 |
| void      | `wait()`<br />`wait(long timeout)`<br />`wait(long timeout, int nanos)` | - 현재의 쓰레드를 일시정지(waiting/timed-waiting) 상태로 전환<br />- 보통 notify() 또는 notifyAll()로 일시정지 해제<br />- 동기화 블록에서만 사용 가능 |
| void      | `notify()`<br />`notifyAll()`                                | - wait()를 이용해 일시정지 상태의 1개의 쓰레드(notify()) 또는 <br />  전체 쓰레드(notifyAll())의 일시정지 해제<br />- 동기화 블록에서만 사용 가능 |

- toString() : 객체 정보를 문자열로 출력하는 메서드
- equals(Object obj) : 등가 비교 연산자(==)와 동일하게 스택 메모리값을 비교
- hashCode() : 객체의 위 정보와 관련된 것으로 Hashtable이나 HashMap에서 동일 객체 여부를 판단할 때 사용
- wait() : 현재의 쓰레드를 일시정지하는 명령
- notify() : 일시정지 중인 쓰레드를 다시 동작시키는 명령



**toString - 객체 정보를 문자열로 출력**

Object 클래스의 toString() 메서드는 객체 정보를 문자열로 리턴하는 메서드입니다. 여기서 객체 정보는 **패키지명.클래스명@해시코드**로 나타냅니다. 해시코드는 객체가 저장된 위치와 관련된 값입니다. 실제 객체의 정보를 표현하고자 할 때는 대부분 클래스명이나 숫자로 나열된 해시코드보다는 객체에 포함돼 있는 필드값을 출력합니다. 따라서 이때 자식 클래스에서는 toString() 메서드를 오버라이딩해 사용합니다.

```java
class A {
    int a = 3;
    int b = 4;
}

A aa = new A();
System.out.printf("%x\n", aa.hashCode());	// 70dea4e3
System.out.printf(aa);	// 패키지.클래스명@해시코드
```

또한 println() 메서드는 객체를 출력하면 자동으로 객체 내의 toString() 메서드를 호출합니다. 따라서 System.out.println(aa)는 System.out.println(aa.toString())과 동일한 표현잉ㅂ니다. 앞에서 언급한 것처럼 toString()의 출력 결과인 '패키지명.클래스명@해시코드'는 객체의 직관적인 정보를 제공하지 못합니다. 그래서 클래스 B처럼 자식클래스에서 toString() 메서드를 오버라이딩해 사용하는 것이 일반적입니다.

```java
class B {	// toString() overriding
    int a = 3;
    int b = 4;
    @Override
    public String toString(){
        return "필드값: a = " + a + ", b = " + b;
    }
}

B bb = new B();
System.out.println(bb);	// bb.toString()이 자동으로 실행
```

```java
/* 실행 결과 */
필드값: a = 3, b = 4
```



**Object 클래스의 toString() 메서드**

```java
package sec06.Ex01;

// Object 클래스의 toString() 메서드

class A {
    int a = 3;
    int b = 4;
}

class B {	// toString() overriding
    int a = 3;
    int b = 4;
    
    public String toString(){
        return "필드값: a = " + a + ", b = " + b;
    }
}

public class ObjectMethod_toString {
    public static void main(String[] args){
        A a = new A();
        B b = new B();
        
        System.out.println("%x\n", a.hashCode());	// 7852e922
        System.out.println(a.toString());	// sec06.Ex01.A@7852e922;
        System.out.println(b);	// 필드값(a, b) = 3 4
    }
}
```



**equals(Object obj) - 스택 메모리의 값 비교**

- 기본 자료형이 아닌 객체의 스택 메모리값을 비교하므로 실제 데이터의 값이 아닌 실제 데이터의 위치(번지)를 비교한다.
- 등가 비교 연산자(==)와 완벽하게 동일한 기능을 수행한다.

```java
// 객체 내부의 값은 동일하지만, 스택 메모리값이 다른지 확인하기
class A {
    String name;
    A(String name) {
        this.name = name;
    }
}

A aa1 = new A("안녕");
A aa2 = new A("안녕");
System.out.println(aa1 == aa2);	// false
System.out.println(aa1.equals(aa2)); // flase
```





```java
class B {
    String name;
    B(String name) {
        this.name = name;
    }
    @Override
    public boolean equals(Ojbect obj) {
        if(obj instanceof B) {
            if (this.name == ((B) obj).name)
                return true;
        }
        return false;
    }
}

B bb1 = new B("안녕");
B bb2 = new B("안녕");
System.out.println(bb1==bb2); // false
System.out.println(bb1.equals(bb2)); // true
```

메서드 내부에서는 자신의 name 값과 입력받은 객체의 name 값을 비교해 동일하면 true, 동일하지 않으면 false를 리턴합니다. 이 과정에서 자신의 객체 타입을 일치시키기 위해 캐스팅을 할 수 있는지를 확인하는 instanceof 키워드와 다운캐스팅을 사용했습니다. 이렇게 되면 클래스 B의 equals()는 이제 위치값이 아닌 내용으로 비교하게 됩니다. 따라서 bb1==bb2는 여전히 false가 나오지만, bb1.equlas(bb2)는 true를 리턴하게 됩니다.



**hashCode() - 객체의 위치와 연관된 값**

- hashCode() 메서드는 객체의 위치와 관련된 값이지만 실제 위치를 나타내는 값은 아니다.
- 객체의 위치값을 기준으로 생성된 고유값으로 생각하는 것이 적절하다.

일반적으로 두 객체의 내용을 비교하기 위해서는 equals() 메서드를 오버라이딩하는 것만으로도 충분합니다. 하지만 Hashtable, HashMap 등에서 동등 비교를 하고자 할 때는 hashCode()까지 오버라이딩해야 합니다.



**HashMap**

HashMap 자료 구조는 데이터를 (Key, Value)의 쌍으로 저장하며, Key 값은 중복되지 않습니다. 따라서 Key 값이 서로 같은지를 확인해야 하는데, 이 과정은 2단계로 구성돼 있습니다.

1. 두 객체의 hashCode() 값을 비교한다. 
   두 객체의 hashCode() 값이 동일할 때 eqauls() 메서드를 호출하며, 이 값이 true이면 동일한 객체로 인식합니다. 이를 정리하면 HashMap 관점에서 두 객체가 동일하기 위해서는 hashCode() 값이 동일해야 하고, equals() 메서드가 true를 리턴해야 합니다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20230827141635898.png" alt="image-20230827141635898" style="zoom:55%;" >













