## 6장

객체(Object)

- 사용할 수 있는 실체



클래스(Class)

- 객체를 만들기 위한 설계도(template)



**프로그램 문법 요소의 발전**

1. **변수(variable)**
   다양한 형태의 데이터를 저장하기 위해 각각의 데이터를 저장할 수 있는 변수라는 문법 요소를 사용

2. **배열(Array)**
데이터의 종류가 많아질수록 변수명을 짓거나 관리하는 일이 버거워지는 문제를 해결하기 위해 만든 문법 요소가 배열
동일한 자료형의 변수들을 묶어 1개의 새로운 자료형으로 관리할 수 있으므로 관리해야 할 변수의 개수를 현저하게 줄어듦

3. **구조체(struct)**
   동일한 자료형만 묶을 수 있는 배열의 한계점을 극복하고자 구조체가 등장
   서로 다른 자료형을 1개의 새로운 자료형으로 관리가 가능

4. **클래스(Class)**
   데이터를 하나로 묶어서 관리하는 구조체에서 데이터를 처리하는 다양한 기능까지 함께 관리하는 문법 요소



**절차지향형 프로그래밍(PP, Procedural Programming)**
순서에 맞춰 단계적으로 실행하도록 명령어를 나열하는 방식



**객체지향형 프로그래밍(Object Oriented Programming)**
프로그램을 객체 단위로 수행하는 방식



**자바에서 제공하는 객체지향 문법 요소**
자바에서 제공하는 객체지향 문법 요소는 크게 클래스와 인터페이스가 있고, 클래스는 다시 일반 클래스와 추상 클래스로 구



**클래스 외부 구성 요소**

1. **패키지(Package)**
   프로젝트를 생성할 떄 패키지를 지정했다면 이 구성 요소에 패키지명이 포함되며, 반드시 주석을 제외하고 첫 번째 줄에 위치해야 함.
   클래스의 생성 과정에서 패키지를 생성하지 않았다면, 즉 디폴트 패키지를 사용하면 생략됨.
2. **임포트(Import)**
   다른 패키지의 클래스를 사용하고자 할 때 포함됨.
3. **외부 클래스(External class)**
   클래스의 외부에 또 다른 클래스가 또 포함이 가능함. 즉, 1개의 `.java` 파일에 여러 개의 클래스가 포함되는 것이 가능.
   단, 외부 클래스에는 `public` 키워드를 붙이는 것은 불가능.



**클래스 내부 구성 요소**

1. **필드(field)**
   클래스의 특징(속성)을 나타내는 변수.
2. **메서드(method)**
   클래스가 지니고 있는 기능(함수)을 나타냄.
3. **생성자(constructor)**
   클래스의 객체를 생성하는 역할을 담당.
4. **이너 클래스(Inner class)**
   클래스의 내부에도 클래스가 포함될 수 있음.

생성자를 제외한 필드, 메서드, 이너 클래스를 **클래스의 멤버(Member)**라고 한다. 



**접근 지정자 `public` 이란 ❓**

`class` 키워드 앞에 있는 `public` 을 '접근 지정자'라고 한다. `.java` 파일 내에서 `public` 은 최대 1개의 클래스에만 사용할 수 있으며, `public` 이 붙은 클래스명이 파일명과 동일해야 한다.



**객체 생성하기**

객체는 `new` 키워드로 생성.

```java
A a = new A();
```

- 참조 변수 `a` :arrow_forward: 실제 데이터가 존재하는 힙 메모리의 주소값을 가리키는 변수
- `new` :arrow_forward: 생성자의 실행 결과인 객체를 힙 메모리에 저장

`A()` 생성자로 만든 객체를 힙 메모리에 넣고, `A` 타입의 참조 변수 `a` 에 저장한다는 것을 의미한다.





### 실력 상승! 연습 문제

1. 클래스 내부에 올 수 있는 4가지 구성 요소오 외부에 올 수 있는 3가지 구성 요소를 쓰시오.

- 클래스 내부 : **필드, 메서드, 생성자, 이너 클래스**
- 클래스 외부 : **패키지, 임포트, 외부 클래스**



2. 다음과 같이 클래스 A가 정의돼 있을 때 다음 코드를 작성하시오.

```java
class A {
    int m;
    void method() {
        so	
    }
}
```
