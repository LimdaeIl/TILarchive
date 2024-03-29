# 7 클래스 내부 구성 요소



## 7.1 필드



### 7.1.1 필드와 지역 변수의 구분

- **필드(Field)** : 클래스에 포함된 변수
- **지역 변수(Local Variable)** : 메서드에 포함된 변수

클래스 중괄호 안에 선언된 변수는 필드, 메서드의 중괄호 안에 선언된 변수를 지역 변수입니다. 필드와 지역 변수의 가장 큰 차이점은 생성되는 메모리 위치입니다. **필드는 힙 메모리의 객체 내부에 생성**되고 **지역 변수는 스택 메모리에 생성**됩니다. 스택 메모리에 저장되는 변수는 JVM에 의해 자동으로 삭제되지만, 힙 메모리의 객체 안에 저장되는 필드는 객체가 사라지지 않는 한 절대로 삭제되지 않습니다.



### 7.1.2 필드와 지역 변수의 초기값

필드와 지역 변수의 또 다른 차이점은 초기값입니다. 필드는 직접 초기화하지 않아도 강제로 초기화가 됩니다. 반면에, 지역 변수는 직접 치기화하지 않으면 저장 공간이 빈 공간이므로 값을 출력할 때 오류가 발생합니다. 이유는 필드와 지역 변수가 위치하는 메모리 영역의 특징 때문입니다. 힙 메모리에는 빈 공간이 저장될 수 없기 때문에 힙 메모리에 위치한 필드는 강제로 초기화되는 것이고, 스택 메모리는 강제로 초기화하지 않기 때문입니다.



## 7.2 메서드



### 7.2.1 메서드 정의하기

- 메서드는 클래스의 기능에 해당하는 요소

```java
접근지정자 제어자 반환타입 메서드명(입력매개변수) {
    메서드 내용
}

public static int sum(int a, int b) {
    // 메서드 내용
}
```



### 7.2.2 여러 리턴 타입의 메서드 살펴보기

- void 리턴 타입은 리턴하지 않는다는 것을 의미하지만, 내부에 return 키워드를 사용할 수 있습니다. 이 때 return 키워드는 메서드를 종료한다는 의미입니다.

```java
// 리턴 타입이 void이고, 입력매개변수가 없는 메서드
void print() {
    System.out.println("안녕");
}

// 리턴 타입이 int이고, 입력매개변수가 없는 메서드
int data() {
    return 3;
}

// 리턴 타입이 double이고, 입력매개변수가 2개인 메서드
double sum(int a, double b){
    return a + b;
}
```



### 7.2.3 메서드 호출하기

1. **클래스 외부에서 메서드 호출하기**

- 인스턴스를 통해서 메서드 호출이 가능합니다.



2. **클래스 내부에서 메서드 호출하기**

- 클래스 내부에 있는 메서드끼리는 객체를 생성하지 않고 서로를 호출할 수 있습니다. 즉, 같은 멤버끼리는 클래스 내부에서 얼마든지 객체를 생성하지 않고 서로를 호출할 수 있습니다.
- 단, static이 붙어 있을 때는 static이 붙은 필드 또는 메서드만 호출이 가능합니다.



3. **기본 자료형 입력매개변수와 참조 자료형 입력매개변수의 차이**

- 참조 자료형이 입력매개변수로 넘겨질 때 실제 객체가 전달되는 것이 아니라 객체의 위치값이 전달
- 기본 자료형을 입력매개변수로 전달하면 전달받은 메서드는 값을 복사해 사용



### 7.2.4 오버로딩된 메서드

- 메서드명이 동일하더라도 입력매개변수의 개수나 자료형이 다른 여러 개의 동일한 이름을 지닌 메서드를 정의

```java
리턴타입 메서드명(자료형 변수명, 자료형 변수명, ...) {
    
}

int sum(int a, int b){
    return 3;
}

// 입력매개변수에 따라 4개의 메서드로 오버로딩된 메서드의 예
public static void main(String[] arg) {
    pirnt();
    pirnt(3);
    pirnt(5.8);
    pirnt(2, 5);
}

public static void print(){ System.out.println("데이터가 없습니다");}
public static void print(int a){ System.out.println(a);}
public static void print(double a){ System.out.println(a);}
public static void print(int a, int b){ System.out.println(a + ", " + b);}
```



### 7.2.5 가변 길이 배열 입력매개변수 메서드

```java
리턴타입 메서드명(자료형 ... 참조변수명) {
    ...
}

public static void method1(int ... values){
    System.out.println("입력매개변수 길이 : " + values.length);
}
```



## 7.3 생성자



### 7.3.1 생성자의 특징

- **생성자(Constructor)** :  객체를 생성하는 역할을 지닌 클래스의 내부 구성 요소
- 생성자는 반드시 클래스명과 동일해야 합니다.
- 생성자는 리턴 타입이 없습니다.(리턴하지 않는다는 의미의 void 와 전혀 다릅니다.)

```java
클래스명 (입력매개변수) {
    ...
}

class A {
    A() {
        // ...
    }
}
```



### 7.3.2 기본 생성자의 자동 추가

- 생성자를 포함하지 않는 클래스에게 컴파일러가 기본 생성자를 자동으로 추가



### 7.3.3 생성자와 객체의 생성 방법

생성자의 모양에 따라 객체를 생성하는 방법은 다릅니다. 생성자 또한 메서드와 동일하게 오버로딩이 가능하기 때문에 여러 개의 생성자를 정의할 수 있습니다.





## 7.4 this 키워드와 this() 메서드

클래스 내부에서는 객체를 생성하지 않고 바로 필드와 메서드를 사용할 수 있는 이유는 컴파일러가 tihs 키워드를 자동으로 붙여주기 때문입니다. 

```java
// 명시적으로 this 를 사용해야 하는 예
class B {
    int m;
    int n;
    void init(int m, int n){ // 지역 변수와 필드명이 동일한 경우 this 를 사용
        this.m = m;
        tihs.n = n;
    }
}

B b = new B();
b.init(3, 4);
System.out.print(b.m);	// 3
System.out.print(b.n);	// 4
```



### 7.4.2 클래스 내 다른 생성자를 호출하는 this() 메서드

- this() 메서드는 자신이 속한 클래스 내부의 다른 생성자를 호출하는 명령어입니다. 
- this() 메서드는 생성자의 내부에서만 사용이 가능
- this() 메서드는 생성자의 첫 줄에 반드시 위치

```java
// this() 메서드 사용하는 이유 - (this() 사용 전)

class A {
    int m1, m2, m3, m4;
    
    A(){
        m1 = 1;
        m2 = 2;
        m3 = 3;
        m4 = 4;
    }
    
    A(int a) {
        m1 = a;
        m2 = 2;
        m3 = 3;
        m4 = 4;       
    }
    
    A(int a, int b) {
        m1 = a;
        m2 = b;
        m3 = 3;
        m4 = 4;       
    }
}
```



```java
// this() 메서드 사용하는 이유 - (this() 사용)

class A {
    int m1, m2, m3, m4;
    
    A(){
        m1 = 1;
        m2 = 2;
        m3 = 3;
        m4 = 4;
    }
    
    A(int a) {
        this();
        m1 = a;       
    }
    
    A(int a, int b) {
        this(a);
        m2 = b;      
    }
}
```











