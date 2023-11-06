# 제네릭(Generics)



## 1. 제네릭란?

지네릭스는 다양한 타입의 객체들을 다루는 메서드나 컬렉션 클래스에 컴파일 시의 타입 체크(compile-time type check)를 해주는 기능입니다. 객체의 타입을 컴파일 시에 체크하면 객체의 타입 안정성을 높이고 형변환의 번거로움을 줄일 수 있습니다.

타입 안정성을 높인다는 것은 의도하지 않은 타입의 객체가 저장되는 것을 막고, 저장된 객체를 꺼내 올 때 원래의 타입과 다른 타입으로 잘못 형변환되어 발생할 수 있는 오류를 줄여준다는 뜻입니다. 즉, 다룰 객체의 타입을 미리 명시해줌으로써 번거로운 형변환을 줍니다. 요약하자면, 제네릭을 사용하는 가장 큰 이유 두가지는 다음과 같습니다.

1. **클래스나 메서드 내부에서 사용되는 객체의 타입 안정성을 높일 수 있습니다**
2. **반환값에 대한 타입 변환 및 타입 검사에 들어가는 노력을 줄일 수 있습니다.**



## 2 제네릭 클래스의 선언

```java
//클래스 Box
class Box {
	Object item;
    void setitem(Object item) { this.item = item; }
    Object getItem() { return item; }
}

// 지네릭 클래스로 변경
// 클래스 옆에 <T> 추가
// Object -> T 로 변경
class Box<T> {	//지네릭 타입 T를 선언
	T item;
    
    void setItem(T item) { this.item = item; }
    T getItem() { return item; }
}
```

`Box<T>`에서 T를 타입 변수(type variable)라고 하며, Type의 첫 글자입니다. 타입 변수는 T가 아닌 다른 것을 사용해도 됩니다. `ArrayList<E>`의 경우, 타입 변수 E는 Element(요소)의 첫 글자를 따서 사용했습니다. 타입 변수가 여러 개인 경우에는 `Map<K, V>`와 같이 콤마'`,`'를 구분자로 나열하면 된다. K는 Key(키)를 의미하고, V는 Value(값)을 의미합니다. 지금까지 모두 기호의 종류만 다를 뿐 모두 **'임의의 참조형 타입'**을 의미합니다.

기존에는 다양한 종류의 타입을 다루는 메서드의 매개변수나 리턴타입으로 Object타입의 참조변수를 많이 사용했고, 그로 인해 형변환이 불가피했지만, 지네릭을 이용하면 Object 타입 대신 원하는 타입을 지정하기만 하면 됩니다.

 

## 3. 제네릭 클래스 예제

```java
// 제네릭 클래스가 된 Box 클래스의 객체를 생성할 때는
// 참조변수와 생성자에 타입 T 대신에 사용될 실제 타입을 지정해주어야 한다.
Box<String> b = new Box<String>();	// 타입 T 대신 실제 타입 String 지정
b.setItem(new Object());	// 에러. String 이외의 타입은 지정불가
b.setItem("ABC");	// OK. String 타입이므로 가능
String item = b.getItem();	// 형변환이 필요 없다 -> String item = (String)b.getItem(); 아님.

// T 대신 String 타입 지정 -> 지네릭 클래스 Box<T>의 정의
// 만일 Box 클래스에 String만 담을 거라면, 타입 변수를 선언하지 않고 아래와 같이 직접 타입을 적어줄 수 있다.
// 단, Box<String> 클래스는 String 타입만 담을 수 있다.
// 반면, Box<T>클래스는 어떤 타입이든 한 가지 타입을 정해서 담을 수 있다.
class Box<String> {	// 지네릭 타입을 String 으로 지정
	String item;
    
    void setItem(String item) { this.item = item; }
    String getItem() { return item; }
}

// 제네릭이 도입되기 이전의 코드와 호환을 위해,
// 제네릭 클래스인데도 예전의 방식으로 객체를 생성하는 것이 허용된다.
// 다만 지네릭 타입을 지정하지 않아서 안전하지 않다는 경고가 발생한다.
Box b = new Box();	// OK. T는 Object로 간주된다.
b.setItem("ABC");	// 경고. unchecked or unsafe operation
b.setItem(new Object());	// 경고. unchecked or unsafe operation

// 타입 변수 T에 Object 타입을 지정하면, 
// 타입을 지정하지 않은 것이 아니라 알고 적은 것이므로 경고가 발생하지 않는다.
Box<Object> b = new Box<Object>();
b.setItem("ABC");
b.setItem(new Object());
```

 

 

## 4. 지네릭스의 용어

```java
//지네릭 클래스 Box
class Box<T>{}

//Box : 원시타입
//Box<T> : 지네릭 클래스
```

- `Box<T>`: 지네릭 클래스. T의 Box 또는 T Box 라고 읽습니다.
- `T`: 타입 변수 또는 타입 매개변수 (T는 타입 문자)
- `Box`: 원시 타입(raw type)

```java
Box<String> b = new Box<String>();

// <String> : 대입된 타입(매개변수화된 타입, parameterized type)
// Box<String> : 지네릭 타입 호출 (타입 매개변수에 타입을 지정하는 것)
```

`Box<String>`과 `Box<Integer>`는 지네릭 클래스 `Box<T>`에 서로 다른 타입을 대입하여 호출한 것일뿐, 이 둘이 별개의 클래스를 의미하는 것은 아닙니다. 컴파일 후에 `Box<String>`과 `Box<Integer>`는 모두 이들의 원시 타입인 Box로 바뀐다. 즉, 지네릭 타입이 제거된다.

 

## 5. 제네릭의 제한

제네릭는 인스턴스별로 다르게 동작하도록 만든 기능이기 때문에, 지네릭 클래스 Box의 객체를 생성할 때 객체별로 다른 타입을 지정하는 것이 적절합니다.

```java
Box<Apple> appleBox = new Box<Apple>();	//OK. Apple객체만 저장 가능
Box<Grape> grapeBox = new Box<Grape>();	//OK. Grape객체만 저장 가능
```



**static 멤버에 제네릭은 사용할 수 없습니다.**

```java
class Box<T> {
	static T item;	//에러
	static int compare(T t1, T t2) {}	//에러
}
```

 모든 객체에 대해 동일하게 동작해야하는 static 멤버에 타입 변수 T를 사용할 수는 없습니다. T는 인스턴스변수로 간주되기 때문입니다. 이전에 학습한 내용처럼 static 멤버는 인스턴스 변수를 참조할 수 없습니다. static 멤버는 타입 변수에 지정된 타입, 즉 대입된 타입의 종류에 관계없이 동일한 것이어야 합니다. 즉, `Box<Apple>.item`과 `Box<Grape>.item`이 다른 것이어서는 안된다.



**배열에 제네릭 타입을 사용할 수 없습니다.**

```java
class Box<T> {
	T[] itemArr;	//OK. T타입의 배열을 위한 참조변수
    
    T[] toArray() {
    	T[] tmpArr = new T[itemArr.length];	//에러. 지네릭 배열 생성 불가
        return tmpArr;
    }
}
```

 제네릭 타입의 배열을 생성하는 것도 허용하지 않습니다. 제네릭 배열 타입의 참조변수를 선언하는 것은 가능하지만, new T[10]과 같이 배열을 생성하는 것은 안됩니다. **제네릭 배열을 생성할 수 없는 것은 new 연산자** 때문인데, 이 연산자는 컴파일 시점에 타입 T가 뭔지 정확히 알아야 합니다. 하지만 지네릭 클래스인 `Box<T>`클래스를 컴파일하는 시점에는 T가 어떤 타입이 될지 전혀 알 수 없습니다. 따라서 instanceof 연산자도 new 연산자와 같은 이유로 T를 피연산자로 사용할 수 없습니다.

 제네릭 배열을 생성해야할 필요가 있을 때는 new 연산자 대신 **Reflection API의 newInstance()와 같이 동적으로 객체를 생성하는 메서드로 배열을 생성**하거나, **Object 배열을 생성해서 복사한 다음에 T[]로 형변환하는 방법 등을 사용**합니다.

 

## 6. 지네릭 클래스의 객체 생성과 사용

```java
// Box<T>의 객체에는 한 가지 종류, 즉 T 타입의 객체만 저장할 수 있다.
// ArrayList를 이용해서 여러 객체를 저장할 수 있도록 함.
class Box<T> {
	ArrayList<T> list = new ArrayList<T>();
    
    void add(T item) { list.add(item); }
    T get(int i) { return list.get(i); }
    ArrayList<T> getList() { return list; }
    int size() { return list.size(); }
    public String toString() { return list.toString(); }
}

// Box<T>의 객체 생성
// 참조변수와 생성자에 대입된 타입(매개변수화된 타입)이 일치해야 한다.
Box<Apple> appleBox = new Box<Apple>();	// OK
Box<Apple> appleBox = new Box<Grape>();	// 에러

// 두 타입이 상속관계에 있어도 마찬가지이다.
// Apple이 Fruit의 자손일 경우
Box<Fruit> appleBox = new Box<Apple>();	// 에러. 대입된 타입이 다르다.

// 단, 두 지네릭 클래스의 타입이 상속관계에 있고, 대입된 타입이 같은 것은 괜찮다.
// FruitBox가 Box의 자손일 경우
Box<Apple> appleBox = new FruitBox<Apple>();	// OK. 다형성
```

JDK1.7부터는 추정이 가능한 경우 타입을 생략할 수 있습니다. 참조변수의 타입으로부터 Box가 Apple타입의 객체만 저장한다는 것을 알 수 있기 때문에, 생성자에 반복해서 타입을 지정해주지 않아도 됩니다.

```java
Box<Apple> appleBox = new Box<Apple>();
Box<Apple> appleBox = new Box<>();	//OK. JDK1.7부터 생략 가능
```

 

생성된 `Box<T>`의 객체에 void add(T item)으로 객체를 추가할 때, 대입된 타입과 다른 타입의 객체는 추가할 수 없습니다.

```java
Box<Apple> appleBox = new Box<Apple>();
appleBox.add(new Apple());	//OK
appleBox.add(new Grape());	//에러. Box<Apple>에는 Apple객체만 추가 가능.
```

 

타입 T가 Fruit인 경우 void add(Fruit item)가 되므로 Fruit의 자손들은 이 메서드의 매개변수가 될 수 있다.

```java
//Apple이 Fruit의 자손일 경우
Box<Fruit> fruitBox = new Box<Fruit>();
fruitBox.add(new Fruit());	//OK
fruitBox.add(new Apple());	//OK. void add(Fruit item)
```

 

## 7. 제한된 지네릭 클래스

타입 매개변수 T에 지정할 수 있는 타입의 종류 제한합니다. 지네릭 타입에 extends를 사용하면, 특정 타입의 자손들만 대입할 수 있게 제한할 수 있습니다.

```java
FruitBox<Toy> fruitBox = new FruitBox<Toy>();
fruitBox.add(new Toy());	//OK. 과일상자에 장난감을 담을 수 있다.

//extends
class FruitBox<T extends Fruit> {	//Fruit의 자손만 타입으로 지정 가능
	ArrayList<T> list = new ArrayList<T>();
}

//여전히 한 종류의 타입만 담을 수 있지만, Fruit클래스의 자손들만 담을 수 있다는 제한이 추가되었다.
FruitBox<Apple> appleBox = new FruitBox<Apple>();	//OK
FruitBox<Toy> toyBox = new FruitBox<Toy>();	//에러. Toy는 Fruit의 자손이 아니다.

//add()의 매개변수의 타입 T도 Fruit과 그 자손 타입이 될 수 있으므로,
//여러 과일을 담을 수 있는 상자를 만들 수 있다.
//다형성에서 조상타입의 참조변수로 자손타입의 객체를 가리킬 수 있는 것처럼,
//매개변수화된 타입의 자손 타입도 가능하다.
//타입 매개변수 T에 Object를 대입하면, 모든 종류의 객체를 저장할 수 있게 된다.
FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
fruitBox.add(new Apple());	//OK. Apple이 Fruit의 자손
fruitBox.add(new Grape());	//OK. Grape가 Fruit의 자손
```

 

클래스가 아니라 인터페이스를 구현해야 한다는 제약이 필요하다면, 이때도 implements를 사용하지 않고 **extends**를 사용합니다.

```java
interface Eatable {}
class FruitBox<T extends Eatable> {}
```

 

클래스 Fruit의 자손이면서 Eatable인터페이스도 구현해야 한다면 '`&`' 기호로 연결할 수 있습니다.

```java
//FruitBox에는 Fruit의 자손이면서 Eatable을 구현한 클래스만 타입 매개변수 T에 대입될 수 있다.
class FruitBox<T extends Fruit & Eatable> {}
```

 

## 8. 와일드 카드

```java
// 매개변수에 과일박스를 대입하면 주스를 만들어서 반환하는 Juicer 클래스
// Juicer 클래스는 제네릭 클래스가 아니며, 
// static메서드에는 타입 매개변수 T를 매개변수에 사용할 수 없으므로
// 타입 매개변수 대신 특정 타입을 지정해주어야 한다. <Fruit>으로 지정
class Juicer {
	// 과일을 주스로 만들어서 반환하는 makeJuice()라는 static 메서드
	static Juice makeJuice(FruitBox<Fruit> box) {	//<Fruit>으로 지정
    	String tmp = "";
        for(Fruit f : box.getList()) tmp += f + " ";
        return new Juice(tmp);
    }
}

FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
FruitBox<Apple> appleBox = new FruitBox<Apple>();
System.out.println(Juicer.makeJuice(fruitBox));	// OK. FruitBox<Fruit>
System.out.println(Juicer.makeJuice(appleBox));	// 에러. FruitBox<Apple>

// 제네릭 타입을 FruitBox<Fruit>으로 고정해 놓으면
// FruitBox<Apple>타입의 객체는 makeJuice()의 매개변수가 될 수 없으므로
// 다음과 같이 여러 가지 타입의 매개변수를 갖는 makeJuice()를 만들 수 밖에 없다.
static Juice makeJuice(FruitBox<Fruit> box) {
    String tmp = "";
    for(Fruit f : box.getList()) tmp += f + " ";
    return new Juice(tmp);
}

static Juice makeJuice(FruitBox<Apple> box) {
    String tmp = "";
    for(Fruit f : box.getList()) tmp += f + " ";
    return new Juice(tmp);
}
// 그러나 위와 같이 오버로딩 하면, 컴파일 에러가 발생한다.
// 제네릭 타입이 다른 것만으로는 오버로딩이 성립하지 않기 때문이다.
// 제네릭 타입은 컴파일 할때만 사용하고 제거해버린다.
// 그래서 위의 두 메서드는 오버로딩이 아니라 메서드 중복 정의이다.
```

와일드 카드는 기호 '`?`'로 표현하는데, 어떠한 타입도 될 수 있습니다. '`?`'만으로는 Object타입과 다를 게 없으므로, **extends와 super로 상한(upper bound)과 하한(lower bound)을 제한할 수 있습니다.** 지네릭 클래스와 달리 와일드 카드에는 '`&`'을 사용할 수 없습니다. 즉, `<? extends T & E>`와 같이 할 수 없습니다.

```java
<? extends T>	//와일드 카드의 상한 제한. T와 그 자손들만 가능
<? super T>		//와일드 카드의 하한 제한. T와 그 조상들만 가능
<?>		//제한 없음. 모든 타입이 가능. <? extends Object>와 동일
```

 

```java
// 이 메서드의 매개변수로 FruitBox<Fruit>뿐만 아니라,
// FruitBox<Apple>, FruitBox<Grape>도 가능하다.
// 매개변수의 타입을 FruitBox<? extends Object>로 하면 모든 종류의 FruitBox가 매개변수로 가능하다.
static Juice makeJuice(FruitBox<? extends Fruit> box) {
    String tmp = "";
    for(Fruit f : box.getList()) tmp += f + " ";
    return new Juice(tmp);
}

FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
FruitBox<Apple> appleBox = new FruitBox<Apple>();

System.out.println(Juicer.makeJuice(fruitBox));	// OK. FruitBox<Fruit>
System.out.println(Juicer.makeJuice(appleBox));	// OK. FruitBox<Apple>

// 매개변수의 타입을 FruitBox<? extends Object>로 하면 모든 종류의 FruitBox가 매개변수로 가능하다.
// 대신 box의 요소가 Fruit의 자손이라는 보장이 없으므로
// for문에서 box에 저장된 요소를 Fruit타입의 참조변수로 받을 수 없다.
static Juice makeJuice(FruitBox<? extends Object> box) {
    String tmp = "";
    for(Fruit f : box.getList()) tmp += f + " ";	// 에러. Fruit이 아닐 수 있음.
    return new Juice(tmp);
}

// 그러나 실제로 문제없이 컴파일 되는데, 그 이유는 바로 지네릭 클래스 FruitBox를 제한했기 때문이다.
class FruitBox<T extends Fruit> extends Box<T> {}
```

 

## 9. 제네릭 메서드

- 제네릭 메서드의 의미는  메서드의 선언부에 지네릭 타입이 선언된 메서드
- 제네릭 메서드는 지네릭 클래스가 아닌 클래스에도 정의 가능

Collections.sort()가 바로 제네릭 메서드이며, 지네릭 타입의 선언 위치는 반환타입 바로 앞 입니다.

```java
static <T> void sort(List<T> list, Comparator<? supr T> c)
```

 

static 멤버에는 타입 매개변수를 사용할 수 없지만, 메서드에 지네릭 타입을 선언하고 사용하는 것은 가능합니다. 메서드가 `static`인 경우 클래스 수준에서 정의되므로 제네릭 타입 매개변수를 사용하는 것은 문제가 없습니다. 제네릭 타입 매개변수 `T`가 메서드의 로컬 변수로 선언되었고 메서드의 시그니처에 있는 `T`는 클래스 수준에서 선언된 `T`와는 별개의 것입니다. 따라서 `static <T>`는 메서드 내에서 사용되는 지역적인 제네릭 타입 매개변수를 선언하는 것이며, 클래스 수준의 `T`와 충돌하지 않습니다.

```java
class FruitBox<T> {
	static <T> void sort(List<T> list, Comparator<? super T> c) {}
}
```

 

```java
// static 메서드 -> 지네릭 메서드
static Juice makeJuice(FruitBox<? extends Fruit> box) {
	String tmp = "";
    for (Fruit f : box.getList()) tmp += f + " ";
    return new Juice(tmp);
}

static <T extends Fruit> Juice makeJuice(FruitBox<T> box) {
	String tmp = "";
    for (Fruit f : box.getList()) tmp += f + " ";
    return new Juice(tmp);
}

// 제네릭 메서드를 호출할 때, 타입 변수에 타입을 대입해야 한다.
FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
FruitBox<Apple> appleBox = new FruitBox<Apple>();
System.out.println(Juicer.<Fruit>makeJuice(fruitBox));
System.out.println(Juicer.<Apple>makeJuice(appleBox));

// 대부분의 경우 컴파일러가 타입을 추정할 수 있기 때문에 생략 가능하다.
System.out.println(Juicer.makeJuice(fruitBox));	//대입된 타입을 생략할 수 있다.
System.out.println(Juicer.makeJuice(appleBox));

// 제네릭 메서드를 호출할 때, 
// 대입된 타입을 생략할 수 없는 경우에는 참조변수나 클래스 이름을 생략할 수 없다.
System.out.println(<Fruit>makeJuice(fruitBox));	//에러. 클래스 이름 생략 불가
System.out.println(this.<Fruit>makeJuice(fruitBox));	//OK
System.out.println(Juicer.<Fruit>makeJuice(fruitBox));	//OK
```

같은 클래스 내에 있는 멤버들끼리는 참조변수나 클래스 이름, 즉 'this.'나 '클래스이름.'을 생략하고 메서드 이름만으로 호출이 가능하지만, 대입된 타입이 있을 때는 반드시 써줘야 합니다.

 

제네릭 메서드는 매개변수의 타입이 복잡할 때도 유용합니다.

```java
public static void printAll(ArrayList<? extends Product> list,
					ArrayList<? extends Product> list2) {
	for (Unit u : list) {
    	System.out.println(u);
    }                            
}

public static <T extends Product> void printAll(ArrayList<T> list,
						ArrayList<T> list2) {
	for (Unit u : list) {
    	System.out.println(u);
    }                            
}

//Collections클래스의 sort() -> 하나의 매개변수
public static <T extends Comparable<? super T>> void sort (List<T> list)

//와일드 카드 걷어내기
//List<T>의 요소가 Comparable인터페이스를 구현한 것이어야 한다.
//인터페이스라고 해서 implements를 쓰지 않는다.
public static <T extends Comparable<T>> void sort(List<T> list)

//와일드 카드 추가
public static <T extends Comparable<? super T>> void sort (List<T> list)
//List<T> : 타입 T를 요소로 하는 List를 매개변수로 혀용한다.
//<T extends Comparable<? super T>>
//: T는 Comparable을 구현한 클래스이어야 하며 <T extends Comparable>,
//T 또는 그 조상의 타입을 비교하는 Comparable이어야 한다는 것(Comparable<? super T>)을 의미한다.
//만일 T가 Student이고, Person의 자손이라면
//<? super T>는 Student, Person, Object가 모두 가능하다.
```

 

## 10. 지네릭 타입의 형변환

```java
// 지네릭 타입과 원시 타입(primitive type)간의 형변환
// 지네릭 타입과 넌지네릭(non-generic) 타입간의 형변환은 경고가 발생하지만 항상 가능하다.
Box box = null;
Box<Object> objBox = null;

box = (Box) objBox;	// OK. 지네릭 타입 -> 원시 타입. 경고 발생
objBox = (Box<Object>) box;	// OK. 원시 타입 -> 지네릭 타입. 경고 발생

// 대입된 타입이 다른 지네릭 타입 간의 형변환은 불가능하다.
Box<Object> objBox = null;
Box<String> strBox = null;

objBox = (Box<Object>) strBox;	// 에러. Box<String> -> Box<Object>
strBox = (Box<String>) objBox;	// 에러. Box<Object> -> Box<String>

// Box<Object> objBox = (Box<Object>) new Box<String>();
Box<Object> objBox = new Box<String>();	//에러. 형변환 불가능

// Box<String> -> Box<? extends Object> 형변환
Box<? extends Object> wBox = new Box<String>();
```