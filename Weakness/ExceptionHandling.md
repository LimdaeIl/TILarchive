# 예외 처리(Exception Handling)

<br />

## 1. 프로그램 오류

프로그램이 실행 중 어떤 원인에 의해서 오작동을 하거나 비정상적으로 종료되는 경우가 있습니다. 이러한 결과를 초래하는 원인을 프로그램 오류 또는 오류라고 합니다. 프로그램 오류는 발생 시점에 따라 '컴파일 에러(Compile Time Error)'와 '런타임 에러(Runtime Error)'로 나눌 수 있습니다. 그리고 컴파일과 실행은 정상적이지만 의도와 다른 동작을 의미하는 '논리적 에러'가 있습니다.

1. 컴파일 에러: 컴파일 시에 발생하는 에러
2. 런타임 에러: 실행 시에 발생하는 에러
3. 논리적 에러: 실행은 되지만, 의도와 다르게 동작하는 에러

자바에서는 실행(Runtime) 중에 발생할 수 있는 프로그램 오류를 '에러(Error)'와 '예외(Exception)'로 구분합니다. 에러가 발생하면 비정상적인 종료를 막을 방법이 없지만, 예외는 발생하더라도 프로그래머가 이에 대한 적절한 코드를 미리 작성해놓으면 프로그램의 비정상적인 종료를 막을 수 있습니다.

- **에러(Error):** 프로그램 코드에 의해서 수습될 수 없는 심각한 오류
- **예외(Exception):** 프로그램 코드에 의해서 수습될 수 있는 다소 미약한 오류

<br />

## 2. 예외 클래스의 계층구조

자바에서 예외 처리는 `java.lang.Throwable` 클래스를 기반으로 하는 계층 구조를 가지고 있습니다. 이 계층 구조는 예외와 에러를 구분하고, 다양한 예외 유형을 처리할 수 있도록 도와줍니다. 아래는 자바 예외 클래스의 계층 구조를 설명합니다:

<img src="https://www.tcpschool.com/lectures/img_java_exception_class_hierarchy.png" alt="예외 클래스 계층도" style="zoom:80%;" />

- `java.lang.Throwable`: 
  모든 예외와 에러의 슈퍼클래스입니다.

1. `java.lang.Error`: 
   일반적으로 프로그램에서 복구하기 어렵거나 불가능한 심각한 문제를 나타내는 예외 유형입니다. 개발자가 직접 처리하기보다는 시스템 레벨에서 관리됩니다. 일반적인 에러 유형으로는 `OutOfMemoryError`, `StackOverflowError` 등이 있습니다.

2. `java.lang.Exception:`
   일반적인 예외를 나타내는 슈퍼클래스입니다.
   - **검사 예외 (Checked Exception):** 
     컴파일러가 확인하고 처리를 강제하는 예외입니다. 개발자는 `try-catch` 블록을 사용하거나 예외를 선언하도록 요구됩니다. 대표적인 예로 `IOException`, `SQLException` 등이 있습니다.
   - **비검사 예외 (Unchecked Exception):** 
     컴파일러가 확인하지 않는 예외로, `RuntimeException` 클래스를 상속받는 예외입니다. 개발자가 처리를 강제하지 않으며, 예외 발생 시 예외 처리는 선택 사항입니다. 대표적인 예로 `NullPointerException`, `ArithmeticException` 등이 있습니다.
   - **사용자 정의 예외 (User-Defined Exception):** 
     개발자가 직접 정의한 예외 클래스로, 프로그램 특정 부분에서 예외를 발생시키고 처리하기 위해 사용됩니다.

예외 처리에서 `Error`는 일반적으로 시스템 레벨의 문제를 나타내며, 개발자가 직접 처리하지 않습니다. `Exception`은 개발자가 예외를 처리하고 복구할 수 있는 상황을 나타내며, 검사 예외와 비검사 예외로 나누어집니다. 사용자 정의 예외는 개발자가 프로그램의 특정 요구사항에 맞게 예외 클래스를 정의하여 사용할 수 있습니다.

예외 처리는 자바 프로그래밍에서 중요한 부분이며, 프로그램의 안정성과 신뢰성을 높이는 데 기여합니다.

<br />

## 3. try-catch 문

`try-catch` 블록은 **자바에서 예외 처리를 위해 사용되는 구문**입니다. 이 구문을 사용하여 예외가 발생할 수 있는 코드 블록을 묶어 예외를 처리하거나 예외가 발생했을 때 대처 방법을 정의할 수 있습니다. `try-catch` 블록은 다음과 같이 사용됩니다:

```java
try {
    // 예외가 발생할 수 있는 코드
} catch (예외클래스1 변수1) {
    // 예외클래스1에 해당하는 예외를 처리하는 코드
} catch (예외클래스2 변수2) {
    // 예외클래스2에 해당하는 예외를 처리하는 코드
} finally {
    // 선택적으로, 예외 발생 여부와 상관없이 항상 실행되는 코드
}
```

자세한 설명을 제공하겠습니다:

1. **`try` 블록:** 
   `try` 키워드로 시작하며, 예외가 발생할 수 있는 코드를 이 블록에 포함시킵니다. 예외를 발생시킬 수 있는 부분입니다.
2. **`catch` 블록:** 
   `catch` 키워드와 함께 예외 클래스와 변수를 선언합니다. `catch` 블록은 예외가 발생했을 때 해당 예외 클래스와 일치하는 예외를 처리하는 데 사용됩니다. 여러 개의 `catch` 블록을 사용하여 다양한 예외를 처리할 수 있습니다.
3. **`finally` 블록 (선택사항):** 
   `finally` 키워드를 사용하여 `finally` 블록을 정의할 수 있습니다. 이 블록은 예외가 발생하든 발생하지 않든 항상 실행되는 코드를 포함합니다. 주로 리소스의 해제나 정리 작업에 사용됩니다.

예제를 통해 `try-catch` 블록을 자세히 설명하겠습니다:

```java
public class TryCatchExample {
    public static void main(String[] args) {
        int[] numbers = { 1, 2, 3, 4, 5 };
        try {
            // 배열의 길이를 벗어난 인덱스에 접근하여 예외 발생
            int result = numbers[10] / 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            // ArrayIndexOutOfBoundsException 예외를 처리하는 코드
            System.out.println("배열 인덱스 오류: " + e.getMessage());
        } catch (ArithmeticException e) {
            // ArithmeticException 예외를 처리하는 코드
            System.out.println("산술 오류: " + e.getMessage());
        } finally {
            // 항상 실행되는 코드
            System.out.println("예외 처리 완료");
        }
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=64108:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
배열 인덱스 오류: Index 10 out of bounds for length 5
예외 처리 완료

종료 코드 0(으)로 완료된 프로세스
```

위의 예제에서:

1. `try` 블록 내에서 배열의 길이를 벗어난 인덱스에 접근하고, 0으로 나누는 코드가 있습니다. 예외를 발생시킬 수 있는 부분입니다.
2. `catch` 블록은 `ArrayIndexOutOfBoundsException` 예외와 `ArithmeticException` 예외를 처리하는 코드를 제공합니다.
3. 예외가 발생하면 해당 예외 클래스와 일치하는 `catch` 블록이 실행됩니다. 예외의 정보는 `getMessage()` 메서드로 출력됩니다.
4. `finally` 블록은 항상 실행되며, 예외가 발생하든 발생하지 않든 코드가 실행됩니다.
5. `try-catch` 블록을 사용하여 예외를 처리하면 프로그램이 비정상적으로 종료되는 것을 방지하고, 예외에 대한 대처 방법을 정의할 수 있습니다.

`try-catch` 블록을 사용하여 예외를 처리하면 프로그램이 비정상적으로 종료되는 것을 방지하고, 예외에 대한 대처 방법을 정의할 수 있습니다.

<br />

### 3.1 printStackTrace()와 getMessage()

예외가 발생했을 때 생성되는 예외 클래스의 인스턴스에는 발생한 예외에 대한 정보가 담겨 있습니다. printStackTrace()와 getMessage()를 통해 예외에 대한 정보들을 얻을 수 있습니다. catch 블럭의 괄호() 안에 선언된 참조변수를 통해 이 인스턴스에 접근이 가능합니다. 이 참조변수는 선언된 catch 블럭 내에서만 사용이 가능합니다. 

<br />

**`printStackTrace()` 메서드:**

- `printStackTrace()` 메서드는 예외의 스택 트레이스(StackTrace) 정보를 출력하는 데 사용됩니다.
- 스택 트레이스는 **예외가 발생한 곳부터 예외를 처리하려는 지점까지의 메서드 호출 경로를 포함**합니다. 이것은 어떤 메서드에서 어떤 메서드를 호출했는지를 보여주며, 예외가 발생한 원인을 파악하는 데 도움이 됩니다.
- `printStackTrace()`는 주로 디버깅 용도로 사용되며, 예외가 어디서 발생했는지 추적하고 문제 해결에 도움이 됩니다.

```java
try {
    int result = 10 / 0; // ArithmeticException 발생
} catch (ArithmeticException e) {
    e.printStackTrace(); // 스택 트레이스 출력
}
```

<br />

**`getMessage()` 메서드:**

- `getMessage()` 메서드는 **예외 객체의 간단한 에러 메시지를 반환**합니다.
- 이 메시지는 예외가 발생한 이유나 상황에 대한 간략한 설명을 담고 있습니다.
- 주로 사용자에게 예외 정보를 전달하거나 로그에 기록할 때 유용합니다.

```java
try {
    int result = 10 / 0; // ArithmeticException 발생
} catch (ArithmeticException e) {
    String errorMessage = e.getMessage(); // 에러 메시지를 얻음
    System.out.println("예외 발생: " + errorMessage);
}
```



<br />



###  3.2 printStackTrace()와 getMessage() 예제

**`printStackTrace()` 메서드 예제:**

`printStackTrace()` 메서드는 예외의 스택 트레이스 정보를 자세하게 출력합니다. 이를 통해 예외가 발생한 메서드 호출 경로를 확인할 수 있습니다. 다음은 `printStackTrace()`를 사용한 예제 코드와 설명입니다:

```java
public class ExceptionHandlingExample {
    public static void main(String[] args) {
        try {
            int result = divide(10, 0); // 0으로 나누는 연산은 ArithmeticException 발생
        } catch (ArithmeticException e) {
            // 스택 트레이스 출력
            e.printStackTrace();
        }
    }

    public static int divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("0으로 나눌 수 없습니다.");
        }
        return dividend / divisor;
    }
}
```

- `divide` 메서드에서 0으로 나누는 경우 `ArithmeticException`을 던집니다.
- `main` 메서드에서 이 예외를 잡고 `printStackTrace()`를 호출하여 스택 트레이스를 출력합니다.

<br />

출력 결과:

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=64246:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
java.lang.ArithmeticException: 0으로 나눌 수 없습니다.
	at Main.divide(Main.java:13)
	at Main.main(Main.java:4)

종료 코드 0(으)로 완료된 프로세스
```

스택 트레이스에서 볼 수 있듯이, 예외가 `divide` 메서드의 13번째 줄에서 발생했으며, 이 메서드는 `main` 메서드의 4번째 줄에서 호출되었습니다.

<br />

**2. `getMessage()` 메서드 예제:**

`getMessage()` 메서드는 예외 객체에 연결된 간단한 메시지를 반환합니다. 이 메시지는 예외의 이유나 상황을 설명하는 데 사용됩니다. 다음은 `getMessage()`를 사용한 예제 코드와 설명입니다:

```java
public class ExceptionHandlingExample {
    public static void main(String[] args) {
        try {
            int result = divide(10, 0); // 0으로 나누는 연산은 ArithmeticException 발생
        } catch (ArithmeticException e) {
            // 에러 메시지 얻기
            String errorMessage = e.getMessage();
            System.out.println("예외 발생: " + errorMessage);
        }
    }

    public static int divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("0으로 나눌 수 없습니다.");
        }
        return dividend / divisor;
    }
}
```

- `divide` 메서드에서 0으로 나누는 경우 `ArithmeticException`을 던지고 메시지를 설정합니다.
- `main` 메서드에서 이 예외를 잡고 `getMessage()`를 호출하여 에러 메시지를 얻은 후 출력합니다.

출력 결과:

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=64289:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
예외 발생: 0으로 나눌 수 없습니다.

종료 코드 0(으)로 완료된 프로세스
```

`getMessage()`를 사용하면 예외의 원인을 간단하게 설명하고 사용자에게 이해하기 쉬운 메시지를 제공할 수 있습니다.



<br />



## 4. throw 문

`throw`는 자바에서 예외를 강제로 발생시키는 키워드입니다. `throw`를 사용하여 사용자 정의 예외를 만들거나 특정 조건에서 예외를 발생시킬 수 있습니다. `throw`의 주요 용도는 다음과 같습니다:

1. **사용자 정의 예외 생성:** 
   프로그래머는 특정 상황에서 예외를 발생시켜서 이를 자신만의 사용자 정의 예외 클래스로 정의할 수 있습니다. 이러한 사용자 정의 예외는 예외의 특정 유형을 표현하고 예외 처리를 개선하는 데 사용됩니다. 사용자 정의 예외 생성 예제는 다음과 같습니다.

   ```java
   public class CustomException extends Exception {
       public CustomException(String message) {
           super(message);
       }
   }
   
   public void doSomething() throws CustomException {
       // 특정 조건에서 예외를 발생시킴
       if (/* 조건 */) {
           throw new CustomException("사용자 정의 예외 발생");
       }
   }
   ```

2. **예외 처리:** 
   `throw`를 사용하여 특정 조건을 만족할 때 예외를 발생시켜 예외 처리를 유도할 수 있습니다. 이것은 예외를 명시적으로 처리하도록 개발자에게 알립니다. 예외 처리 예제는 다음과 같습니다.

   ```java
   public void divide(int dividend, int divisor) throws ArithmeticException {
       if (divisor == 0) {
           throw new ArithmeticException("0으로 나눌 수 없습니다.");
       }
       // 나눗셈 연산 수행
   }
   ```

3. **예외 전파:** 
   메서드 내에서 처리하지 않은 예외를 상위 호출자로 전파하는 데 사용됩니다. 이를 통해 상위 메서드에서 예외를 처리하거나 호출자에게 예외 처리 책임을 넘길 수 있습니다.예외 전파 예제는 다음과 같습니다.

   ```java
   public void performOperation() throws CustomException {
       try {
           // 어떤 작업 수행
       } catch (Exception e) {
           // 예외를 다시 던짐
           throw new CustomException("사용자 정의 예외 발생");
       }
   }
   ```

`throw`를 사용하면 예외를 명시적으로 발생시킬 수 있으며, 이를 통해 코드의 안전성과 가독성을 향상시킬 수 있습니다. 예외는 예외 상황을 처리하고, 문제의 근본 원인을 식별하는 데 중요한 도구입니다.



<br />



### 4.1 throw 문 예제

**예제 1: 사용자 정의 예외 발생**
이 예제에서는 `throw`를 사용하여 사용자 정의 예외를 발생시키고 처리합니다.

```java
class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            // 특정 조건에서 사용자 정의 예외 발생
            int age = -5;
            if (age < 0) {
                throw new CustomException("나이는 음수일 수 없습니다.");
            }
        } catch (CustomException e) {
            // 사용자 정의 예외 처리
            System.out.println("예외 발생: " + e.getMessage());
        }
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=64412:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled Main
예외 발생: 나이는 음수일 수 없습니다.

종료 코드 0(으)로 완료된 프로세스
```

- `CustomException`은 사용자 정의 예외 클래스로, 예외 메시지를 받는 생성자를 정의합니다.
- `age` 변수가 음수인 경우, `CustomException`을 던져 예외를 발생시킵니다.
- `catch` 블록에서 `CustomException`을 처리하고 예외 메시지를 출력합니다.



<br />



**예제 2: 예외 전파**

이 예제에서는 `throw`를 사용하여 예외를 전파하고 상위 호출자에서 처리합니다.

```java
class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }
}

public class ExceptionPropagationExample {
    public static void main(String[] args) {
        try {
            performOperation();
        } catch (CustomException e) {
            System.out.println("상위 메서드에서 예외 처리: " + e.getMessage());
        }
    }

    public static void performOperation() throws CustomException {
        try {
            // 어떤 조건에서 예외 발생시키기
            int age = -5;
            if (age < 0) {
                throw new CustomException("나이 음수");
            }
        } catch (Exception e) {
            // 예외를 다시 던짐
            throw e;
        }
    }
}
```

```java
C:\Users\piay8\.jdks\corretto-11.0.21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=64719:C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\github\TILarchive\Weakness\untitled\out\production\untitled ExceptionPropagationExample
상위 메서드에서 예외 처리: 나이 음수

종료 코드 0(으)로 완료된 프로세스
```

예제 코드는 `ExceptionPropagationExample` 클래스와 `CustomException` 클래스로 이루어져 있습니다.

1. `main` 메서드에서 프로그램이 시작됩니다.
2. `main` 메서드에서 `performOperation()` 메서드를 호출합니다.
3. `performOperation()` 메서드가 실행됩니다.
4. `performOperation()` 메서드 내에서 `age` 변수가 -5로 설정되어 있으므로, `if (age < 0)` 조건이 참이 됩니다.
5. 조건이 참일 때, `CustomException`을 생성하고 예외 메시지를 "나이 음수"로 설정합니다. 그리고 이 예외를 발생시킵니다.
6. `CustomException`이 발생하면, 해당 예외가 현재 메서드인 `performOperation()` 내부에서 잡힙니다.
7. `catch` 블록에서는 예외를 다시 던지고, `throw e;` 구문을 통해 예외를 다시 던집니다.
8. 예외가 다시 던져지면 호출자인 `main` 메서드에서 해당 예외를 잡습니다.
9. `main` 메서드의 `catch` 블록에서 `CustomException`을 처리하고, `e.getMessage()`를 사용하여 예외 메시지를 얻고 출력합니다.
10. 프로그램이 종료됩니다.

이렇게 실행 순서를 요약하면, `performOperation()` 메서드 내에서 예외가 발생하고, 이 예외가 메서드 내부에서 잡히고 다시 던져집니다. 그런 다음, 호출자인 `main` 메서드에서 예외를 처리하게 됩니다. 출력 결과는 "상위 메서드에서 예외 처리: 나이 음수"로 나타날 것입니다.

`throw`를 사용하면 예외를 명시적으로 발생시키고, 예외 처리를 개선하거나 예외 상황을 처리할 수 있습니다. 이를 통해 코드의 안정성을 높이고 예외에 대한 명확한 대응을 제공할 수 있습니다.



<br />



## 5. try-with-resources 문

`try-with-resources`는 자바 7부터 도입된 자원 관리를 간편하게 하는 기능입니다. 주로 입출력(IO) 또는 데이터베이스 연결과 같은 자원을 사용할 때 자원을 명시적으로 해제할 필요가 없도록 도와줍니다. 이것은 코드를 보다 간결하게 작성하고 예외 상황에서 자원을 안전하게 해제할 수 있도록 해줍니다.

`try-with-resources`는 `AutoCloseable` 인터페이스를 구현하는 클래스의 인스턴스를 사용합니다. `AutoCloseable` 인터페이스는 `close()` 메서드를 정의하며, 이 메서드는 자원을 닫거나 해제하는 데 사용됩니다.

`try-with-resources` 구문은 다음과 같은 형태를 가집니다:

```java
try (ResourceType resource = new ResourceTypeInitialization()) {
    // 자원을 사용하는 코드
} catch (Exception e) {
    // 예외 처리 코드
}
```

여기에서 `ResourceType`은 `AutoCloseable` 인터페이스를 구현한 클래스의 이름이며, `ResourceTypeInitialization`은 `ResourceType` 인스턴스를 초기화하는 코드입니다.

`try-with-resources`를 사용하면 다음과 같은 이점이 있습니다:

1. **자원 해제:** `try` 블록이 실행을 마치면 자동으로 `close()` 메서드가 호출되어 자원을 해제합니다.
2. **코드 간결성:** 자원을 명시적으로 해제하는 코드를 작성할 필요가 없으므로 코드가 더 간결해집니다.
3. **예외 처리:** 자원 해제와 관련된 예외 처리를 보다 쉽게 관리할 수 있습니다.

예를 들어, 파일을 읽는 작업을 `try-with-resources`로 수행하는 코드는 다음과 같이 작성할 수 있습니다:

```java
try (FileReader reader = new FileReader("example.txt")) {
    int data;
    while ((data = reader.read()) != -1) {
        System.out.print((char) data);
    }
} catch (IOException e) {
    // 파일 읽기 도중 예외 처리
}
// 자원은 자동으로 해제됨
```

`FileReader` 클래스는 `AutoCloseable` 인터페이스를 구현하고 있으므로 `try-with-resources`로 사용할 수 있습니다. 파일을 열고 읽는 작업이 끝나면 `try` 블록을 빠져나가면서 `close()` 메서드가 자동으로 호출되어 파일 자원을 해제합니다.

이렇게 `try-with-resources`를 사용하면 자원 관리를 간편하게 처리할 수 있으며, 코드의 가독성과 안정성을 높일 수 있습니다.



<br />



### 5.1 try-with-resources 예제

`try-with-resources`를 사용한 간단한 파일 읽기 예제를 주석과 함께 작성하겠습니다. 이 예제에서는 파일을 열고 읽은 후 자원을 자동으로 해제하는 방법을 보여줍니다.

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResourcesExample {
    public static void main(String[] args) {
        // 파일 읽기를 위한 BufferedReader를 선언합니다.
        // BufferedReader는 AutoCloseable 인터페이스를 구현하므로 try-with-resources 로 사용 가능합니다.
        try (BufferedReader reader = new BufferedReader(new FileReader("example.txt"))) {
            // 파일을 한 줄씩 읽어서 출력합니다.
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            // 파일 읽기 도중 예외 처리
            e.printStackTrace();
        }
        // try-with-resources 블록을 빠져나가면 BufferedReader 자원이 자동으로 해제됩니다.
    }
}
```

이 코드에서 다음을 주목해주세요:

1. `BufferedReader`를 `try` 블록 내에 선언하고 초기화합니다. `BufferedReader`는 `AutoCloseable` 인터페이스를 구현하므로 `try-with-resources`로 사용 가능합니다.
2. `try` 블록 내에서 파일을 한 줄씩 읽어서 출력합니다.
3. `catch` 블록에서 파일 읽기 도중 발생하는 예외를 처리합니다. 여기에서는 예외 정보를 출력합니다.
4. `try-with-resources` 블록을 빠져나가면 `BufferedReader` 자원이 자동으로 해제됩니다. 파일을 닫고 관련 리소스를 해제하는 코드를 명시적으로 작성할 필요가 없습니다.

이 코드는 파일 읽기를 보다 간단하게 처리하며, 예외 처리와 자원 관리를 개선합니다.



<br />



## 6. Exception re-throwing 

Exception re-throwing은 예외를 다시 던지는 것을 의미합니다. 예외를 잡아서 처리하는 대신, **현재 메서드나 블록에서 예외를 다시 던지는 것을 말합니다.** 이러한 기술은 **예외를 호출자에게 전파하여 해당 예외를 처리할 수 있도록 하는 데 사용됩니다.**

예외 re-throwing은 다음과 같은 상황에서 유용하게 쓰일 수 있습니다:

1. **예외 전파:** 예외를 현재 메서드에서 처리하는 대신, 호출자에게 예외를 알리고 예외 처리를 위임합니다.
2. **예외 변환:** 예외를 다른 예외 유형으로 변환하여 예외 계층을 조절하거나 더 구체적인 예외 정보를 제공합니다.

예를 들어, 다음은 예외 re-throwing의 간단한 예제입니다:

```java
public void doSomething() throws CustomException {
    try {
        // 어떤 동작을 수행
        // 예외가 발생하면 예외를 잡아서 처리하거나, 다른 예외로 변환할 수 있음
    } catch (IOException e) {
        // IOException을 CustomException으로 변환하여 예외 전파
        throw new CustomException("CustomException 발생", e);
    }
}
```

위의 코드에서 `doSomething` 메서드는 `CustomException`을 던질 수 있으며, 예외가 발생할 때 `IOException`을 `CustomException`으로 변환하고 예외를 다시 던집니다. 이렇게 하면 호출자가 `CustomException`을 처리하도록 전파되며, 예외의 정보가 유지됩니다.

Exception re-throwing은 예외 처리를 보다 유연하게 만들어주고, 예외 처리 전략을 조정하거나 예외 정보를 보다 상세하게 전달하는 데 도움을 줍니다.



<br />



## 7. Chained exception

Chained exception은 **하나의 예외를 다른 예외와 연결하여 예외 정보를 연쇄적으로 추적할 수 있도록 해주는 개념**입니다. 이것은 예외 처리에서 디버깅 및 로깅 작업을 보다 강력하게 만들어줍니다.

일반적으로 chained exception은 다음 두 가지 주요 요소로 구성됩니다:

1. **Primary Exception:** 주요 예외 또는 원인 예외로, 주로 발생한 예외를 나타냅니다.
2. **Secondary Exception:** 주요 예외로부터 파생되거나 연관된 예외로, 추가적인 정보를 제공하거나 원인 예외에 대한 더 자세한 설명을 제공합니다.

Chained exception을 사용하면 원인 예외와 그에 따른 연쇄적인 예외를 관리하며, 이로 인해 예외 처리 및 디버깅 과정이 간소화됩니다. 보통 다음과 같은 방법으로 chained exception을 사용합니다:

1. **원인 예외와 연쇄 예외 생성:** 주요 예외와 이와 연관된 부가 정보를 담은 연쇄 예외를 생성합니다.
2. **연쇄 예외 던지기:** 연쇄 예외를 발생시키고, 원인 예외를 연결합니다.
3. **연쇄 예외 처리:** 연쇄 예외를 잡아서 원인 예외와 연쇄 예외를 분석하거나 처리합니다.

Java에서는 `Throwable` 클래스를 상속받은 `Exception` 및 `Error` 클래스를 사용하여 chained exception을 구현할 수 있습니다. **일반적으로 `getCause()` 메서드를 사용하여 원인 예외와 연쇄 예외를 차례로 추적할 수 있습니다.**

다음은 Java에서 chained exception을 사용하는 예제 코드입니다:

```java
public class ChainedExceptionExample {
    public static void main(String[] args) {
        try {
            // 원인 예외 발생
            throw new IllegalArgumentException("원인 예외");
        } catch (IllegalArgumentException primaryException) {
            // 연쇄 예외 생성
            RuntimeException chainedException = new RuntimeException("연쇄 예외", primaryException);
            // 연쇄 예외 던지기
            throw chainedException;
        }
    }
}
```

이 코드에서는 `IllegalArgumentException`를 원인 예외로 발생시키고, 그 원인 예외를 포함하는 `RuntimeException`을 연쇄 예외로 던집니다. 이렇게 하면 원인 예외와 연쇄 예외가 연결되어 예외 정보를 추적할 수 있게 됩니다.
