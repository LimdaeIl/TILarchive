# 자바 제어자 1



## 9.1 접근 지정자



### 9.1.1 멤버 및 생성자의 접근 지정자

- **자바 제어자(Modifier)** : 클래스, 필드, 메서드, 생성자 등에게 어떠한 특징을 부여하는 문법 요소
- **접근 지정자** : 자바 제어자의 한 종류로, 클래스, 멤버, 생성자 앞에 위치할 수 있으며, 사용 범위를 정의하는 역할을 수행

멤버 및 생성자에는 `public protected, default(또는 package), private` 라는 4가지 종류의 접근 지정자를 사용할 수 있습니다. 접근 지정자는 필드나 메서드 또는 생성자 앞에 위치하는데, 아무것도 작성하지 않으면 default 접근 지정자가 자동으로 설정됩니다.



| 접근 지정자 | 사용 가능 범위                                               |
| ----------- | ------------------------------------------------------------ |
| `public`    | 동일 패키지의 모든 클래스 + 다른 패키지의 **모든 클래스**에서 사용 가능 |
| `protected` | 동일 패키지의 모든 클래스 + 다른 패키지의 **자식 클래스**에서 사용 가능 |
| `default`   | 동일 패키지의 모든 클래스에서 사용 가능                      |
| `private`   | 동일 클래스에서 사용 가능                                    |

<center> 
    멤버 및 생성자에서 사용하는 4가지 접근 지정자의 사용 가능 범위
</center>



```java
// package abc 안에 클래스 A, B
// package bcd 안에 클래스 C, D

package abc;

public class A{
    public int a;
    protected int b;
    int c;
    private int d;
    void abc(){
        // a, b, c, d 사용 가능
    }
}

/* --------------------------------- */
package abc;
class B{
    // a, b, c 사용 가능
}

/* --------------------------------- */
package bcd;
class C{
    // a 사용 가능
}

class C extends A {
    // a, b 사용 가능
}
```



### 9.1.2 클래스의 접근 지정자

클래스에서는 `public, default` 접근 지정자만 사용 가능합니다. 클래스를 default로 정의하면 다른 패키지에서 임포트가 불가능합니다.



### 9.1.3 클래스 접근 지정자와 생성자 접근 지정자의 연관성

클래스 접근 지정자와 생성자 접근 지정자는 매우 밀접한 관련이 있습니다. 클래스에 생성자가 없을 때 컴파일러는 기본 생성자를 자동으로 추가한다고 했습니다. 이 때 자동으로 추가되는 생성자의 접근 지정자는 클래스의 접근 지정자에 따라 결정이 됩니다. 



## 9.2 static 제어자

- **static** : 클래스의 멤버(필드, 메서드, 이너 클래스)에 사용하는 제어자
- **인스턴스 멤버(instance member)** : static 이 붙어 있지 않은 멤버로, 객체를 생성해야 사용이 가능
- **정적 멤버(static member)** : static 이 붙어 있는 멤버로, 바로 사용이 가능

정적 필드의 특징의 핵심은 정적 필드는 객체 간 공유 변수의 성질이 있습니다.

```java
package sec02_staticmodifier.EX01_StaticField_1;

class A{
    int m = 3;
    static int n = 5;
}

public class StaticField_1 {
    public static void main(String[] args){
        A a1 = new A();
        A a2 = new A();
        
        a1.m = 5;
        a2.m = 6;
        System.out.println(a1.m); // 5
        System.out.println(a2.m); // 6
        
        a1.n = 7;
        a2.n = 8;
        System.out.println(a1.n); // 8
        System.out.println(a2.n); // 8
        
        A.n = 9;
        System.out.println(a1.n); // 9
        System.out.println(a2.n); // 9
    }
}
```



### 9.2.2 인스턴스 메서드와 정적 메서드

인스턴스 메서드는 반드시 객체를 생성한 후에 사용할 수 있지만, 정적 메서드는 클래스명으로도 바로 접근이 가능합니다.

```java
// 인스턴스 메서드와 정적 메서드를 1개씩 포함하고 있는 클래스의 예

class A{
    void abc(){ // 객체를 생성한 후에 사용 가능
        System.out.println("instance 메서드");
    }
    static void bcd() { // 객체 생성 없이 사용 가능
        System.out.printn("static 메서드");
    }
}

// 인스턴스 메서드의 활용 방법
A a = new A();
a.abc();

// 정적 메서드의 활용 방법 1
A.bcd();

// 정적 메서드의 활용 방법 2(권장하지 않음)
A a = new A();
a.bcd();
```



### 9.2.3 정적 메서드 안에서 사용할 수 있는 필드와 메서드

정적 메서드 안에서는 정적 필드 또는 정적 메서드만 사용할 수 있습니다.

```java
class A{
    int a;
    static int b;
    void abc(){
        // a, b, bcd(), cde() 사용 가능
    }
    static void bcd(){
        // b, cde() 사용 가능
    }
    static void cde() {
        // b, bcd() 사용 가능
    }
}
```



### 9.2.4 정적 초기화 블록

보통 인스턴스 필드의 초기화는 객체가 생성되는 시점에서 수행됩니다. 그러나 정적 필드는 객체의 생성 이전에도 사용할 수 있어야 하므로 생성자가 호출되지 않은 상태에서도 초기화를 할 수 있어야 합니다. 다시 말해, 생성자에서는 정적 필드를 초기화할 수 없다는 것입니다. 그래서 정적 필드를 초기화하기 위한 문법을 별도로 제공하는데, 이것이 정적 초기화 블록입니다.

```java
static {
    // 클래스가 메모리에 로딩될 때 실행되는 내용
}
```



### 9.2.5 static main() 메서드

지금까지 작성한 public static void main(String[] args) 또한 정적 메서드입니다. 