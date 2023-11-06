# 열거형 (enums)



 ##  1. 열거형이란?

열거형은 JDK1.5부터 새로 추가되었습니다.

```java
class Card {
	static final int CLOVER = 0;
    static final int HEART = 1;
    static final int DIAMOND = 2;
    static final int SPADE = 3;
    
    static final int TWO = 0;
    static final int THREE = 1;
    static final int FOUR = 2;
    
    final int kind;
    final int num;
}

//-------------------------------------------------->
class Card {
	enum Kind { CLOVER, HEART, DIAMOND, SPADE }	//열거형 Kind를 정의
    enum Value { TWO, THREE, FOUR }	//열거형 Value를 정의
    
    final Kind kind;	//타입이 int가 아닌 Kind
    final Value value;
}
```

 기존의 언어들에서는 타입이 달라도 값이 같으면 조건식 결과가 참(true)였으나, **자바의 타입에 안전한 열거형(typesafe enum)에서는 실제 값이 같아도 타입이 다르면 조건식의 결과가 false**가 됩니다. **값 뿐만 아니라 타입까지 체크하기 때문에 타입에 안전**합니다.

```java
if (Card.CLOVER == Card.TWO)	//true지만 false이어야 의미상 맞다.
if (Card.Kind.CLOVER == Card.Value.TWO)	//false. 같은 값이지만 타입이 다르다.
```

상수의 값이 바뀌면 해당 상수를 참조하는 모든 소스를 다시 컴파일해야 하지만, 열거형 상수를 사용하면 기존의 소스를 다시 컴파일하지 않아도 됩니다.

 

## 2. 열거형의 정의와 사용

**열거형 사용 방법**

```java
//열거형 정의 -> 괄호{} 안에 상수의 이름을 나열한다.
enum 열거형이름 { 상수명1, 상수명2, ... }

//동서남북 정의
enum Driection { EAST, SOUTH, WEST, NORTH }

//열거형 사용 -> 열거형이름.상수명
class Unit {
	int x, y;	//유닛의 위치
    Direction dir;	//열거형을 인스턴스 변수로 선언
    
    void init() {
    	dir = Direction.EAST;	//유닛의 방향을 EAST로 초기화
    }
}

// 열거형 상수간의 비교 -> ==
// <, >와 같은 비교 연산자는 사용할 수 없고, compareTo() 사용.
// compareTo()는 두 비교대상이 같으면 0, 왼쪽이 크면 양수, 오른쪽이 크면 음수를 반환한다.
if (dir == Direction.EAST) {
	x++;
} else if (dir > Direction.WEST) {	//에러. 열거형 상수에 비교연산자 사용 불가
} else if (dir.compareTo(Direction.WEST) > 0) {}	//OK. compareTo()는 사용 가능

//switch문의 조건식에 사용
void move() {
	switch(dir) {
    	case EAST : x++;	//Direction.EAST라고 쓰면 안된다.
        	break;
        case WEST : x--;
        	break;
        case SOUTH : y++;
        	break;
        case NORTH : y--;
        	break;
    }
}
```

 

**열거형 예제**

```java
public class Main {
    // 계절을 나타내는 열거형 정의
    enum Season {
        SPRING, SUMMER, FALL, WINTER
    }

    public static void main(String[] args) {
        // 열거형 상수 사용
        Season currentSeason = Season.SUMMER;
        System.out.println("Current season is: " + currentSeason);

        // 열거형 상수 비교
        if (currentSeason == Season.SUMMER) {
            System.out.println("It's a great time for outdoor activities!");
        }

        // switch 문으로 열거형 사용
        switch (currentSeason) {
            case SPRING:
                System.out.println("Spring is the season of renewal.");
                break;
            case SUMMER:
                System.out.println("Summer is all about fun in the sun.");
                break;
            case FALL:
                System.out.println("Fall brings colorful leaves and cooler weather.");
                break;
            case WINTER:
                System.out.println("Winter is the time for snow and holiday festivities.");
                break;
        }

        // 열거형의 모든 상수 순회
        System.out.println("All seasons:");
        for (Season season : Season.values()) {
            System.out.println(season);
        }
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=57005:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
Current season is: SUMMER
It's a great time for outdoor activities!
Summer is all about fun in the sun.
All seasons:
SPRING
SUMMER
FALL
WINTER

종료 코드 0(으)로 완료된 프로세스
```





## 3. 모든 열거형의 조상 - java.lang.Enum

```java
//열거형 Direction에 정의된 모든 상수를 출력
Direction[] dArr = Direction.values();

for (Direction d : dArr)	//for (Direction d : Direction.values())
	System.out.printf("%s=%d%n", d.name(), d.ordinal());
    
//values() : 열거형의 모든 상수를 배열에 담아 반환. 
//이 메서드는 모든 열거형이 가지고 있는 것으로 컴파일러가 자동으로 추가해준다.
//ordinal() : 열거형 상수가 정의된 순서(0부터 시작)를 정수로 반환한다.
//이 메서드는 든 열거형의 조상인 java.lang.Enum클래스에 정의되어있다.
```

 

**Enum클래스에 정의된 메서드**

| 메서드                                      | 설명                                                      |
| ------------------------------------------- | --------------------------------------------------------- |
| `Class<E> getDeclaringClass()`              | 열거형의 Class객체를 반환한다.                            |
| `String name()`                             | 열거형 상수의 이름을 문자열로 반환한다.                   |
| `int ordinal()`                             | 열거형 상수가 정의된 순서를 반환한다. (0부터 시작)        |
| `T valueOf(Class<T> enumType, String name)` | 지정된 열거형에서 name과 일치하는 열거형 상수를 반환한다. |

 

```java
//values()처럼 컴파일러가 자동적으로 추가해주는 메서드
static E values()
static E valueOf(String name)

//이 메서드는 열거형 상수의 이름으로 문자열 상수에 대한 참조를 얻을 수 있게 해준다.
Direction d = Direction.valueOf("WEST");

System.out.println(d);	//WEST
System.out.println(Direction.WEST == Direction.valueOf("WEST"));	//true
```

 

## 4. 열거형에 멤버 추가하기

열거형 상수의 값이 **불규칙적인 경우에는 열거형 상수의 이름 옆에 원하는 값을 괄호()와 함께 작성**합니다.

```java
enum Direction { EAST(1), SOUTH(5), WEST(-1), NORTH(10) }

// 지정된 값을 저장할 수 있는 인스턴스 변수와 생성자를 새로 추가해주어야 한다.
// 먼저 열거형 상수를 모두 정의한 다음에 다른 멤버들을 추가해준다.
enum Direction {
	EAST(1), SOUTH(5), WEST(-1), NORTH(10);	//끝에 ';'를 추가해 주어야 한다.
    
    // 열거형의 인스턴스 변수는 반드시 final이어야 한다는 제약은 없지만,
    // value는 열거형 상수의 값을 저장하기 위한 것이므로 final을 붙였다.
    private final int value;	//정수를 저장할 필드(인스턴스 변수)를 추가
    // private Direction(int value)
    Direction(int value) { this.value = value; }	//생성자 추가
    
    // 외부에서 값을 얻을 수 있다.
    public int getValue() { return value; }
}

// 하나의 열거형 상수에 여러 값을 지정할 수도 있다.
// 다만 그에 맞게 인스턴스 변수와 생성자 등을 새로 추가해주어야 한다.
enum Direction {
	EAST(1, ">"), SOUTH(2, "V"), WEST(3, "<"), NORTH(4, "^");
    
    private final int value;
    private final String symbol;
    
    Direction(int value, String symbol){	// 접근 제어자 private이 생략됨
    	this.value = value;
        this.symbol = symbol;
    }
    
    public int getValue()	{ return value; }
    public String getSymbol() { return symbol; }
}
```

 

## 5. 열거형에 추상 메서드 추가하기

```java
// 열거형 Transportation은 운송 수단의 종류 별로 상수를 정의하고 있다.
// 각 운송 수단에는 기본요금(BASIC_FARE)이 책정되어 있다.
enum Transprotation {
	BUS(100), TRAIN(150), SHIP(100), AIRPLANE(300);
    
    private final int BASIC_FARE;
    
    private Transportation(int basicFare) {
    	BASIC_FARE = basicFare;
    }
    
    int fare() {	// 운송 요금을 반환
    	return BASIC_FARE;
    }
}


// 거리에 따라 요금은 계산하는 방식이 각 운송수단마다 다르기 때문에
// 열거형에 추상 메서드 fare(int distance)를 선언하고
// 각 열거형 상수는 이 추상 메서드를 반드시 구현해야 한다.
enum Transprotation {
	BUS(100) {
    	int fare(int distance) { return distance * BASIC_FARE; }
    }, 
    TRAIN(150) {
    	int fare(int distance) { return distance * BASIC_FARE; }
    }, 
    SHIP(100) {
    	int fare(int distance) { return distance * BASIC_FARE; }
    }, 
    AIRPLANE(300) {
    	int fare(int distance) { return distance * BASIC_FARE; }
    };
    
    abstract int fare(int distance);	// 거리에 따라 요금을 계산하는 추상 메서드
    
    protected final int BASIC_FARE;		// protected로 해야 각 상수에서 접근 가능
    
    Transportation(int basicFare) {
    	BASIC_FARE = basicFare;
    }
    
    public int getBasicFare() {	
    	return BASIC_FARE;
    }
}
```

 

 

## 6.  열거형의 이해

```java
//열거형 상수 하나하나가 Direction 객체이다.
enum Direction { EAST, SOUTH, WEST, NORTH }

//클래스로 정의
class Direction {
	//Direction클래스의 static 상수 EAST, SOUTH, WEST, NORTH의 값은 객체의 주소이고
    //이 값은 바뀌지 않는 값이므로 ==로 비교가 가능하다.
	static final Direction EAST = new Direction("EAST");
    static final Direction SOUTH = new Direction("SOUTH");
    static final Direction WEST = new Direction("WEST");
    static final Direction NORTH = new Direction("NORTH");
    
    private String name;
    
    private Direction(String name) {
    	this.name = name;
    }
}
```

 

**모든 열거형은 추상 클래스 Enum의 자손이다.**

```java
// MyEnum<T extends MyEnum<T>> -> 타입 T가 MyEnum<T>의 자손이어야 한다는 의미
// 타입 T가 MyEnum의 자손이므로 ordinal()이 정의되어 있는 것이 분명하므로,
// 형변환 없이도 에러가 나지 않는다.
//
// Comparable 인터페이스를 구현해서 열거형 상수간의 비교가 가능하다.
abstract class MyEnum<T extends MyEnum<T>> implements Comparable<T> {
	static int id = 0;	// 객체에 붙일 일련번호(0부터 시작)
    
    int ordinal;
    String name = "";
    
    public int ordinal() { return ordinal; }
    
    MyEnm (String name) {
    	this.name = name;
        // 객체가 생성될 때마다 번호를 붙여서 인스턴스 변수 ordinal에 저장
        ordinal = id++;	// 객체를 생성할 때마다 id의 값을 증가시킨다.
    }
    
    // 두 열거형 상수의 ordinal값을 서로 빼는 것으로 비교
    public int compareTo(T t) {
    	return ordinal - t.ordinal();
    }
}

// 만일 클래스를 MyEnum<T>와 같이 선언하였다면,
// compareTo()를 위와 같이 간단히 작성할 수 없었을 것이다.
// 타입 T에 ordinal()이 정의되어 있는지 확인할 수 없기 때문이다.
abstract class MyEnum<T> implements Comparable<T> {
	public int compareTo(T t) {
    	return ordinal - t.ordinal();	// 에러. 타입 T에 ordinal()이 있는지 확인 불가
    }
}
```

 

추상 메서드를 새로 추가하면, 클래스 앞에도 abstract를 붙여줘야 하고, 각 static 상수들도 추상 메서드를 구현해주어야 한다.

```java
//익명 클래스의 형태로 추상 메서드 구현
abstract class Direction extends MyEnum {
	static final Direction EAST = new Direction("EAST") {
    	Point move(Point p) {}
    };
    static final Direction SOUTH = new Direction("SOUTH") {
    	Point move(Point p) {}
    };
    static final Direction WEST = new Direction("WEST") {
    	Point move(Point p) {}
    };
    static final Direction NORTH = new Direction("NORTH") {
    	Point move(Point p) {}
    };
    
    private String name;
    
    private Direction(String name) {
    	this.name = name;
    }
    
    abstract Point move(Point p);
}
```