# 문자열 메서드



## String 클래스 메서드 모음

```java
public class Main {
    public static void main(String[] args) {
        // 1. String(String s) 
        // 주어진 문자열(s)을 갖는 String 인스턴스를 생성한다.
        String s1 = new String("Hello");
        System.out.println("s1 = " + s1); // s1 = Hello

        // 2. String(char[] value)
        // 주어진 문자열(value)을 갖는 String인스턴스를 생성한다.
        char[] c1 = {'H', 'e', 'l', 'l', 'o'};
        String s2 = new String(c1);
        System.out.println("s2 = " + s2); // s2 = Hello

        // 3. String(StringBuffer buf)
        // StringBuffer 인스턴스가 갖고 있는 문자열과 같은 내용의 String인스턴스를 생성한다.
        StringBuffer sb1 = new StringBuffer("Hello");
        String s3 = new String(sb1);
        System.out.println("sb1 = " + sb1 + " s3 = " + s3); // sb1 = Hello s3 = Hello

        // 4. char charAt(int index)
        // 지정된 위치(index)에 있는 문자를 알려준다. (index는 0부터 시작)
        String s4 = "Hello";
        String n1 = "0123456";
        char c2 = s4.charAt(1);
        char c3 = n1.charAt(1);
        System.out.println("c2 = " + c2 + " c3 = " + c3); // c2 = e c3 = 1

        // 5. int compareTo(String str)
        // 문자열(str)과 사전순서로 비교한다. 같으면 0을, 사전순으로 이전이면 음수를, 이후면 양수를 반환한다.
        int i1 = "aaa".compareTo("aaa");
        int i2 = "aaa".compareTo("bbb");
        int i3 = "bbb".compareTo("aaa");
        System.out.println("i1 = " + i1 + " i2 = " + i2 + " i3 = " + i3); // i = 0 i2 = -1 i3 = 1
        int i4 = "aaa".compareTo("ddd");
        int i5 = "eee".compareTo("aaa");
        System.out.println("i4 = " + i4 + " i5 = " + i5); // i4 = -3 i5 = 4

        // 6. String concat(String str)
        // 문자열(str)을 뒤에 덧붙인다.
        String s5 = "Hello";
        String s6 = s5.concat(" World");
        System.out.println("s6 = " + s6); // s6 = Hello World

        // 7. boolean contains(CharSequence s)
        // 지정된 문자열(s)이 포함되었는지 검사한다.
        String s7 = "abcdefg";
        boolean b1 = s7.contains("bc");
        System.out.println("b1 = " + b1); // b1 = true

        // 8. boolean endWith(String suffix)
        // 지정된 문자열(suffix)로 끝나는지 검사한다.
        String file1 = "Hello.txt";
        boolean b2 = file1.endsWith("txt");
        System.out.println("b2 = " + b2); // b2 = true

        // 9. boolean equals(Object obj)
        // 매개변수로 받은 문자열(obj)과 String인스턴스의 문자열을 비교한다. obj가 String이 아니거나 문자열이 다르면 false를 반환한다.
        String s8 = "Hello";
        boolean b3 = s8.equals("Hello");
        boolean b4 = s8.equals("hello");
        System.out.println("b3 = " + b3 + " b4 = " + b4); // b3 = true b4 = false

        // 10. boolean equalsIgnoreCase(String str)
        // 문자열과 String인스턴스의 문자열을 대소문자 구분없이 비교한다.
        String s9 = "Hello";
        boolean b5 = s9.equalsIgnoreCase("HELLO");
        boolean b6 = s9.equalsIgnoreCase("hello");
        System.out.println("b5 = " + b5 + " b6 = " + b6); // b5 = true b6 = true

        // 11. int indexOf(int ch)
        // 주어진 문자(ch)가 문자열에 존재하는지 확인하여 위치(index)를 알려준다. 
        // 못찾으면 -1을 반환한다. (index는 0부터 시작)
        String s10 = "Hello";
        int idx1 = s10.indexOf('o');
        int idx2 = s10.indexOf('k');
        System.out.println("idx1 = " + idx1 + " idx2 = " + idx2); // idx1 = 4 idx2 = -1

        // 12. int indexOf(int ch, int pos)
        // 주어진 문자(ch)가 문자열에 존재하는지 지정된 위치(pos)부터 확인하여 위치(index)를 알려준다. 
        // 못찾으면 -1을 반환한다. (index는 0부터 시작)
        String s11 = "Hello";
        int idx3 = s11.indexOf('e', 0);
        int idx4 = s11.indexOf('e', 2);
        System.out.println("idx3 = " + idx3 + " idx4 = " + idx4); // idx3 = 1 idx4 = -1

        // 13. int indexOf(String str)
        // 주어진 문자열이 존재하는지 확인하여 그 위치(index)를 알려준다. 없으면 -1을 반환한다. (index는 0부터 시작)
        String s12 = "ABCDEFG";
        int idx5 = s12.indexOf("CD");
        System.out.println("idx = " + idx5); // idx = 2

        // 14. String intern()
        // 문자열을 상수풀(constant pool)에 등록한다. 
        // 이미 상수풀에 같은 내용의 문자열이 있을 경우 그 문자열의 주소값을 반환한다.
        String s13 = new String("abc");
        String s14 = new String("abc");
        boolean b7 = (s13 == s14);
        boolean b8 = s13.equals(s14);
        boolean b9 = (s13.intern() == s14.intern());
        System.out.println("b7 = " + b7 + " b8 = " + b8 + " b9 = " + b9); // b7 = false b8 = true b9 = true

        // 15. int lastIndexOf(int ch)
        // 지정된 문자 또는 문자코드를 문자열의 오른쪽 끝에서부터 찾아서 위치(index)를 알려준다. 못찾으면 -1을 반환한다.
        String s15 = "java.lang.Object";
        int idx6 = s15.lastIndexOf('.');
        int idx7 = s15.indexOf('.');
        System.out.println("idx6 = " + idx6 + " idx7 = " + idx7); // idx6 = 9 idx7 = 4

        // 16. int lastIndexOf(String str)
        // 지정된 문자열을 인스턴스의 문자열 끝에서부터 찾아서 위치(index)를 알려준다. 못찾으면 -1을 반환한다.
        String s16 = "java.lang.java";
        int idx8 = s16.lastIndexOf("java");
        int idx9 = s16.indexOf("java");
        System.out.println("idx8 = " + idx8 + " idx9 = " + idx9); // idx8 = 10 idx9 = 0

        // 17. int length()
        // 문자열의 길이를 알려준다.
        String s17 = "Hello";
        int length = s17.length();
        System.out.println("length = " + length); // length = 5

        // 18. String replace(char old, char nw)
        // 문자열 중의 문자(old)를 새로운 문자(nw)로 바꾼 문자열을 반환한다.
        String s18 = "Hello";
        String s19 = s18.replace('H', 'C');
        System.out.println("s19 = " + s19); // s19 = Cello

        // 19. String replace(CharSequence old, CharSequence nw)
        // 문자열 중의 문자열(old)을 새로운 문자열(nw)로 모두 바꾼 문자열을 반환한다.	 
        String s20 = "Hellollo";
        String s21 = s20.replace("ll", "LL");
        System.out.println("s21 = " + s21); // s21 = HeLLoLLo


        // 20 .String replaceAll(String regex, String replacement)
        // 문자열 중에서 지정된 문자열(regex)과 일치하는 것을 새로운 문자열(replacement)로 모두 변경한다.
        String ab1 = "AABBAABB";
        String r1 = ab1.replaceAll("BB", "bb");
        System.out.println("r1 = " + r1); // r1 = AAbbAAbb

        // 21. String replaceFirst(String regex, String replacement)
        // 문자열 중에서 지정된 문자열(regex)과 일치하는 것 중, 첫 번째 것만 새로운 문자열(replacement)로 변경한다.
        String ab2 = "AABBAABB";
        String r2 = ab2.replaceFirst("BB", "bb");
        System.out.println("r2 = " + r2); // r2 = AAbbAABB

        // 22. String[] split(String regex)
        // 문자열을 지정된 분리자(regex)로 나누어 문자열 배열에 담아 반환한다.
        String animals1 = "dog,cat,bear";
        String[] arr1 = animals1.split(",");
        for (String s : arr1) {
            System.out.printf("%s ", s); // dog cat bear
        }

        System.out.println();

        // 23. String[] split(String regex, int limit)
        // 문자열을 지정된 분리자(regex)로 나누어 문자열배열에 담아 반환한다. 단, 문자열 전체를 지정된 수(limit)로 자른다.
        String animals2 = "dog,cat,bear";
        String[] arr2 = animals2.split(",", 2);
        for (String s : arr2) {
            System.out.printf("%s ", s); // dog cat,bear
        }

        // 24. boolean startsWith(String prefix)
        // 주어진 문자열(prefix)로 시작하는지 검사한다.
        String s22 = "java.lang.Object";
        boolean b10 = s22.startsWith("java");
        boolean b11 = s22.startsWith("lang");
        System.out.println("b10 = " + b10 +  " b11 = " + b11); // dog cat,bear b10 = true b11 = false

        // 25.  String substring(int begin), String substring(int begin, int end)
        // 주어진 시작위치(begin)부터 끝 위치(end)범위에 포함된 문자열을 얻는다. 이 때, 시작위치의 문자는 범위에 포함되지만, 끝 위치의 문자는 포함되지 않는다.
(begin <= x < end)
        String s23 = "java.lang.Object";
        String c4 = s23.substring(10);
        String p1 = s23.substring(5, 9);
        System.out.println("c4 = " + c4 + " p1 = " + p1); // c4 = Object p1 = lang

        // 26. String toLowerCase()
        // String인스턴스에 저장되어 있는 모든 문자열을 소문자로 변환하여 반환한다.
        String s24 = "Hello";
        String s25 = s24.toLowerCase();
        System.out.println("s25 = " + s25); // s25 = hello

        // 27. String toString()
        // String인스턴스에 저장되어 있는 문자열을 반환한다.
        String s26 = "Hello";
        String s27 = s26.toString();
        System.out.println("s27 = " + s27); // s27 = Hello

        // 28. String toUpperCase()
        // String인스턴스에 저장되어있는 모든 문자열을 대문자로 변환하여 반환한다.
        String s28 = "Hello";
        String s29 = s28.toUpperCase();
        System.out.println("s29 = " + s29); // s29 = HELLO

        // 29. String trim()
        // 문자열의 왼쪽 끝과 오른쪽 끝에 있는 공백을 없앤 결과를 반환한다. 이 때 문자열 중간에 있는 공백은 제거되지 않는다.
        String s30 = "   Hello World   ";
        String s31 = s30.trim();
        System.out.println("s31 = " + s31); // s31 = Hello World

        // 30. static String valueOf(boolean b)
        //      static String valueOf(char c)
        //      static String valueOf(int i)
        //      static String valueOf(long l)
        //      static String valueOf(float f)
        //      static String valueOf(double d)
        //      static String valueOf(Object o)
        String b12 = String.valueOf(true);
        String c5 = String.valueOf('a');
        String i6 = String.valueOf(100);
        String l1 = String.valueOf(100L);
        String f1 = String.valueOf(10f);
        String d1 = String.valueOf(10.0);
        System.out.println("b12 = " + b12 + " c5 = " + c5 + " i6 = " + i6 + " l1 = " + l1 +  " f1 = " + f1 +  " d1 = " + d1);
        // b12 = true c5 = a i6 = 100 l1 = 100 f1 = 10.0 d1 = 10.0
        java.util.Date dd = new java.util.Date();
        String date = String.valueOf(dd);
        System.out.println("date = " + date); // date = Wed Nov 01 16:55:07 KST 2023
    }
}
```

- CharSequence는 JDK1.4부터 추가된 인터페이스로 String, StringBuffer 등의 클래스가 구현합니다.
- `contains(CharSequence s), replace(CharSequence old, CharSequence nw)`는 JDK1.5부터 추가되었습니다.
- ` java.util.Date dd = new java.util.Date();`에서 생성된 Date인스턴스는 현재 시간을 갖습니다.

 

## join()과 StringJoiner 클래스

join()은 여러 문자열 사이에 구분자를 넣어서 결합합니다. 구분자로 문자열을 자르는 split()과 반대의 작업을 수행한다고 생각하면 이해하기 쉽습니다. join() 메서드와 StringJoiner 클래스는 다음과 같습니다.

**join() 메서드:**

- `String` 클래스의 `join()` 메서드는 Java 8부터 도입된 메서드로, 문자열 배열 또는 컬렉션의 요소를 구분자(delimiter)로 연결하여 하나의 문자열로 반환하는 기능을 제공합니다.
- `String.join(CharSequence delimiter, CharSequence... elements)` 형식으로 사용됩니다.
  `delimiter`는 요소 사이에 삽입할 구분자이며, `elements`는 연결할 문자열 요소들을 나타냅니다.
- 아래는 `join()` 메서드의 간단한 사용 예제입니다:

```java
String[] words = {"Hello", "World", "Java"};
String result = String.join(" ", words); // "Hello World Java"
```



**StringJoiner 클래스:**

- `StringJoiner`는 Java 8부터 도입된 클래스로, 요소를 구분자로 연결하여 문자열을 구성하는데 사용됩니다. 
  이 클래스는 문자열을 더하고 요소를 결합하며 필요한 경우 접두사(prefix) 및 접미사(suffix)를 지정할 수 있습니다.
- `StringJoiner` 클래스는 다음과 같은 생성자를 제공합니다.
   `StringJoiner(CharSequence delimiter)`. `delimiter`는 각 요소를 구분할 때 사용할 문자열입니다.
- `add(CharSequence element)` 메서드를 사용하여 요소를 추가하고, `toString()` 메서드를 호출하여 최종 문자열을 얻을 수 있습니다.
- 아래는 `StringJoiner`의 간단한 사용 예제입니다:

```java
StringJoiner sj = new StringJoiner(", ", "[", "]");
sj.add("Apple").add("Banana").add("Cherry");
String result = sj.toString(); // "[Apple, Banana, Cherry]"
```

`join()` 메서드와 `StringJoiner` 클래스는 문자열을 효과적으로 결합하고 형식화하는 데 유용하며, 코드의 가독성을 높이는 데 도움을 줍니다. 이러한 도구를 사용하여 문자열 연결 작업을 수행할 때 코드를 간결하고 명확하게 작성할 수 있습니다.



## 유니코드의 보충문자

String클래스의 메서드 중에는 매개변수의 타입이 char가 아닌 int인 것들도 있습니다. 그 이유는 확장된 유니코드를 다루기 때문입니다.**유니코드는 원래 2byte, 즉 16비트 문자체계이지만 20비트로 확장하게 되었습니다**. 그래서 하나의 문자를 char타입으로 다루지 못하고 int타입으로 다룰 수밖에 없습니다. 확장에 의해 새로 추가된 문자들을 보충 문자(supplementary characters)라고 하는데, String클래스의 메서드 중에서는 보충 문자를 지원하는 것이 있고 지원하지 않는 것도 있습니다.

매개변수가 'int ch'인 것들은 보충문자를 지원하는 것이고, 'char ch'인 것들은 지원하지 않는 것들 입니다. 보충 문자를 사용할 일은 거의 없기 때문에 이정도만 학습하면 됩니다. 확장된 유니코드(20 bit)가 적용된 것은 JDK1.5부터입니다.



## 문자 인코딩 변환

getBytes(String charsetName)를 사용하면, 문자열의 문자 인코딩을 다른 인코딩으로 변경할 수 있습니다. 자바가 UTF-16을 사용하지만, 문자열 리터럴에 포함되는 문자들은 OS의 인코딩을 사용합니다. 한글 윈도우즈의 경우 문자 인코딩으로 CP949를 사용하며, UTF-8로 변경할 수 있습니다. 참고로, 사용가능한 문자 인코딩의 목록은 System.out.println(java.nio.charset.Charset.availableCharsets());로 모두 출력할 수 있습니다. 

서로 다른 문자 인코딩을 사용하는 컴퓨터 간에 데이터를 주고받을 때는 적절한 문자 인코딩이 필요합니다.

```java
byte[] utf8_str = "가".getBytes("UTF-8");	//문자열을 UTF-8로 변환
String str = new String(utf8_str, "UTF-8");	//byte배열을 문자열로 변환
```

 

**String.format()**

format()은 형식화된 문자열을 생성합니다. **사용법은 printf()와 동일합니다.**

```java
String str = String.format("%d 더하기 %d는 %d입니다.", 3, 5, 3+5);
System.out.println(str);	//3 더하기 5는 8입니다.
```

 

**기본형 값을 String으로 변환**

- 방법 1: **숫자에 빈 문자열"" 더해주기**
- 방법 2: **valueOf() 사용**

```java
int i = 100;
String str1 = i + "";			   //100을 "100"으로 변환하는 방법 1
string str2 = String.valueOf(i);	//100을 "100으로 변환하는 방법 2
```

참조변수에 String을 더하면, 참조변수가 가리키고 있는 인스턴스의 toString()을 호출하여 String을 얻은 다음 결합합니다.

 

**String을 기본형 값으로 변환**

- 방법 1 : **valueOf() 사용**
- 방법 2 : **parseInt() 사용**

```java
int i = Integer.parseInt("100");	//"100"을 100으로 변환하는 방법1
int i2 = Integer.valueOf("100");	//"100"을 100으로 변환하는 방법2

/*
	valueof()의 반환 타입은 int가 아니라 Integer인데,
    오토박싱(auto-boxing)에 의해 Integer가 int로 자동 변환된다.
*/
Integer i2 = Integer.valueof("100");	//원래는 반환 타입이 integer
```

valueOf(String s)는 메서드 내부에서 parseInt(String s)를 호출하므로, parseInt()와 valueOf()는 반환 타입만 다를 뿐 같은 메서드이다.



```java
public static Integer valueOf(String s) throws NumberFormatException
{
	return Integer.valueOf(parseInt(s, 10));	//10은 10진수를 의미
    //parseInt(s, 10)은 parseInt(s)와 같다.
}
```



**기본형과 문자열간의 변환방법**

- Boolean, Byte와 같이 기본형 타입의 첫 글자가 대문자인 것은 래퍼 클래스(wrapper class)입니다.
- 래퍼 클래스는 **기본형 값을 감싸고 있는 클래스로, 기본형을 클래스로 표현한 것**입니다.

<img src="https://blog.kakaocdn.net/dn/cUGWC8/btrfTG8fjRz/L2WMPYGHEKmXVEMXfQizW0/img.png" alt="img" style="zoom:80%;" />

parseInt()나 parseFloat()같은 메서드는 문자열에 공백 또는 문자가 포함되어 있는 경우 변환 시 예외(NumberFormatException)가 발생할 수 있습니다. 그래서 문자열 양끝의 공백을 제거해주는 trim()을 같이 사용하기도 합니다.

그러나 부호를 의미하는 +나 소수점을 의미하는 .와 float형 값을 뜻하는 f와 같은 자료형 접미사는 허용됩니다. 
단, 자료형에 알맞은 변환을 하는 경우에만 허용됩니다.

```java
int val = Integer.parseInt(" 123 ".trim());		//문자열 양 끝의 공백을 제거 후 변환
```

만일 1.0f를 int형 변환 메서드인 Integer.parseInt(String s)를 사용해 변환하려하면 예외가 발생하지만, Float.parseFloat(String s)를 사용하면 예외가 발생하지 않습니다. +가 포함된 문자열이 parseInt()로 변환가능하게 된 것은 JDK1.7부터입니다.

Integer클래스의 static int parseInt(String s, int radix)를 사용하면 16진수 값으로 표현된 문자열도 변환할 수 있기 때문에, 대소문자 구별 없이 a, b, c, d, e, f도 사용할 수 있습니다. int result = Integer.parseInt("a", 16);의 경우 result에는 정수값 10이 저장됩니다. (16진수 a = 10진수 10)

문자열의 index와 관련된 메서드의 사용 예와 결과

```java
String str = "Hello.java";
int index = str.indexOf('.');		//index = 5
int index2 = str.lastIndexOf('.');	//index2 = 5
String a = str.substring(0, 5);		//a = "Hello"
String b = str.substring(6, 10);	//b = "java"
```

 

## StringBuffer클래스와 StringBuilder클래스

String클래스는 인스턴스를 생성할 때 지정된 문자열을 변경할 수 없지만, StringBuffer클래스는 변경이 가능합니다.

내부적으로 문자열 편집을 위한 버퍼(buffer)를 가지고 있으며, StringBuffer인스턴스를 생성할 때 크기를 지정할 수 있다. 이 때, 편집할 문자열의 길이를 고려하여 버퍼의 길이를 충분히 잡아주는 것이 좋다. 편집 중인 문자열이 버퍼의 길이를 넘어서게 되면 버퍼의 길이를 늘려주는 작업이 추가로 수행되어야하기 때문에 작업효율이 떨어진다.

```java
/*
	StringBuffer클래스는 String클래스와 같이 문자열을 저장하기 위한
    char형 배열의 참조변수를 인스턴스변수로 선언해 놓고 있다.
    StringBuffer인스턴스가 생성될 때,
    char형 배열이 생성되며 이 때 생성된 char형 배열을 인스턴스변수 value가 참조하게 된다.
*/
public final class StringBuffer implements java.io.Serializable
{
	private char[] value;
}
```

 

**StringBuffer의 생성자**

StringBuffer클래스의 인스턴스를 생성할 때, 적절한 길이의 char형 배열이 생성되고, 이 배열은 문자열을 저장하고 편집하기 위한 공간(buffer)으로 사용됩니다. StringBuffer인스턴스를 생성할 때는 생성자 StringBuffer(int length)를 사용해서 StringBuffer인스턴스에 저장될 문자열의 길이를 고려하여 충분히 여유있는 크기로 지정하는 것이 좋습니다.

만일, StringBuffer인스턴스를 생성할 때, 버퍼의 크기를 지정해주지 않으면 16개의 문자를 저장할 수 있는 크기의 버퍼를 생성합니다.

```java
public StringBuffer(int length)
{
	value = new char[length];
    shared = false;
}

public StringBuffer()
{
	//버퍼의 크기를 지정하지 않으면 버퍼의 크기는 16이 된다.
	this(16);
}

public StringBuffer(String str)
{
	//지정한 문자열의 길이보다 16이 더 크게 버퍼를 생성한다.
	this(str.length() + 16);
    append(str);
}
```

 

StringBuffer인스턴스로 문자열을 다루는 작업을 할 때, 버퍼의 크기가 작업하려는 문자열의 길이보다 작을 때는 내부적으로 버퍼의 크기를 증가시키는 작업이 수행되는데, 배열의 길이는 변경될 수 없으므로 새로운 길이의 배열을 생성한 후에 이전 배열의 값을 복사한 뒤 StringBuffer클래스의 인스턴스변수 value는 길이가 증가된 새로운 배열을 참조하게 됩니다.

```java
/* 버퍼의 크기 변경 */

//새로운 길이(newCapacity)의 배열을 생성한다. newCapacity는 정수값이다.
char newValue[] = new char[newCapacity];

//배열 value의 내용을 배열 newValue로 복사한다.
System.arraycoopy(value, 0, newValue, 0, count);	//count는 문자열의 길이
value = newValue;	//새로 생성된 배열의 주소를 참조변수 value에 저장
```

 

**StringBuffer의 변경**

String과 달리 StringBuffer는 내용을 변경할 수 있습니다. append()는 반환타입이 StringBuffer인데 자신의 주소를 반환합니다. 

```java
//StringBuffer 생성
StringBuffer sb = new StringBuffer("abc");

//sb에 문자열 "123" 추가 -> sb의 내용 뒤에 "123"을 추가한다.
sb.append("123");

//sb에 새로운 문자열이 추가되고 sb자신의 주소를 반환하여 sb2에는 sb의 주소가 저장된다.
//즉, sb와 sb2는 같은 StringBuffer 인스턴스를 참조한다.
StringBuffer sb2 = sb.append("ZZ");		//sb의 내용 뒤에 "ZZ"를 추가
System.out.println(sb);		//abc123ZZ
System.out.println(sb2);	//abc123ZZ

//하나의 StringBuffer인스턴스에 대해 연속적으로 append() 호출 가능
//append()의 반환타입이 StringBuffer이기 때문에 가능하다.
StringBuffer sb = new StringBuffer("abc");
sb.append("123").append("ZZ");
```

StringBuffer클래스에는 append()처럼 객체 자신을 반환하는 메서드들이 있습니다.

 

**StringBuffer의 비교**

String 클래스에서는 equals 메서드를 오버라이딩해서 문자열의 내용을 비교하도록 구현되어 있지만, StringBuffer클래스는 equals메서드를 오버라이딩하지 않아서 StringBuffer클래스의 equals메서드를 사용해도 등가비교연산자(==)로 비교한 것과 같은 결과를 얻습니다.

StringBuffer클래스는 toString메서드를 오버라이딩해서 StringBuffer 인스턴스에 toString()을 호출하면 담고 있는 문자열을 String으로 반환합니다.

그래서 StringBuffer인스턴스에 담긴 문자열을 비교하기 위해서는 StringBuffer인스턴스에 toString()을 호출해서 String인스턴스를 얻은 다음, equals메서드를 사용해서 비교합니다.

```java
StringBuffer sb = new StringBuffer("abc");
StringBuffer sb2 = new StringBuffer("abc");

System.out.println(sb == sb2);		//false
System.out.println(sb.equals(sb2));	//false

//-------------------------------------------------------------
String s = sb.toString();
String s2 = sb2.toString();

System.out.println(s.equals(s2));	//true
```



##  StringBuffer 클래스 메서드 모음

```java
public class Main {
    public static void main(String[] args) {
        // 1. StringBuffer()
        // 16문자를 담을 수 있는 버퍼를 가진 StringBuffer 인스턴스를 생성한다.
        StringBuffer sb1 = new StringBuffer();
        System.out.println("sb1 = " + sb1); // sb1 =

        // 2. StringBuffer(int length)
        // 지정된 개수의 문자를 담을 수 있는 버퍼를 가진 StringBuffer 인스턴스를 생성한다.
        StringBuffer sb2 = new StringBuffer(10);
        System.out.println("sb2 = " + sb2); // sb2 = ""

        // 3. StringBuffer(String str)
        // 지정된 문자열 값(str)을 갖는 StringBuffer 인스턴스를 생성한다.
        StringBuffer sb3 = new StringBuffer("Hi");
        System.out.println("sb = " + sb3); // sb = Hi

        // 4. StringBuffer append(boolean b)
        //      StringBuffer append(char c)
        //      StringBuffer append(char[] str)
        //      StringBuffer append(double d)
        //      StringBuffer append(float f)
        //      StringBuffer append(int i)
        //      StringBuffer append(long l)
        //      StringBuffer append(Object obj)
        //      StringBuffer append(String str)
        // 매개변수로 입력된 값을 문자열로 변환하여 StringBuffer 인스턴스가 저장하고 있는 문자열의 뒤에 덧붙인다.
        StringBuffer sb4 = new StringBuffer("abc");
        StringBuffer sb5 = sb4.append(true);
        System.out.println("sb4 = " + sb4 + " sb5 = " + sb5); // sb4 = abctrue sb5 = abctrue
        sb4.append(10.0f);
        StringBuffer b6 = sb4.append("ABC").append(123);
        System.out.println("b6 = " + b6); // b6 = abctrue10.0ABC123

        // 5. int capacity()
        // StringBuffer 인스턴스의 버퍼 크기를 알려준다.
        // length()는 버퍼에 담긴 문자열의 길이를 알려준다.
        StringBuffer sb6 = new StringBuffer(100);
        sb6.append("abcd");
        int bufferSize = sb6.capacity();
        int stringSize = sb6.length();
        System.out.println("bufferSize = " + bufferSize); // bufferSize = 100
        System.out.println("stringSize = " + stringSize); // stringSize = 4

        // 6. char charAt(int index)
        // 지정된 위치(index)에 있는 문자를 반환한다.
        StringBuffer sb7 = new StringBuffer("abc");
        char c1 = sb7.charAt(2);
        System.out.println("c1 = " + c1); // c1 = c

        // 7. StringBuffer delete(int start, int end)
        // 시작위치(start)부터 끝 위치(end) 사이에 있는 문자를 제거한다. 단, 끝 위치의 문자는 제외
        StringBuffer sb8 = new StringBuffer("0123456");
        StringBuffer sb9 = sb8.delete(3, 6);
        System.out.println("sb9 = " + sb9); // sb9 = 0126

        // 8. StringBuffer deleteCharAt(int index)
        // 지정된 위치(index)의 문자를 제거한다.
        StringBuffer sb10 = new StringBuffer("0123456");
        sb10.deleteCharAt(3);
        System.out.println("sb10 = " + sb10); // sb10 = 012456

        // 9. StringBuffer insert(int pos, boolean b)
        //      StringBuffer insert(int pos, char c)
        //      StringBuffer insert(int pos, char[] str)
        //      StringBuffer insert(int pos, double d)
        //      StringBuffer insert(int pos, float f)
        //      StringBuffer insert(int pos, int i)
        //      StringBuffer insert(int pos, long l)
        //      StringBuffer insert(int pos, Object obj)
        //      StringBuffer insert(int pos, String str)
        // 두 번째 매개 변수로 받은 값을 문자열로 변환하여 지정된 위치(pos)에 추가한다. pos는 0부터 시작한다.
        StringBuffer sb11 = new StringBuffer("0123456");
        sb11.insert(4, '.');
        System.out.println("sb11 = " + sb11); // sb11 = 0123.456

        // 10. int length()
        // StringBuffer 인스턴스에 저장되어 있는 문자열의 길이를 반환한다.
        StringBuffer sb12 = new StringBuffer("0123456");
        int length1 = sb12.length();
        System.out.println("length = " + length1); // length = 7

        // 11. StringBuffer replace(int start, int end, String str)
        // 지정된 범위(start~end)의 문자들을 주어진 문자열로 바꾼다. end위치의 문자는 범위에 포함되지 않음.
        //(start <= x < end)
        StringBuffer sb13 = new StringBuffer("0123456");
        sb13.replace(3, 6, "AB");
        System.out.println("sb13 = " + sb13); // sb13 = 012AB6

        // 12. StringBuffer reverse()
        // StringBuffer인스턴스에 저장되어 있는 문자열의 순서를 거꾸로 나열한다.
        StringBuffer sb14 = new StringBuffer("0123456");
        sb14.reverse();
        System.out.println("sb14 = " + sb14); // sb14 = 6543210

        // 13. void setCharAt(int index, char ch)
        // 지정된 위치의 문자를 주어진 문자(ch)로 바꾼다.
        StringBuffer sb15 = new StringBuffer("0123456");
        sb15.setCharAt(5, 'o');
        System.out.println("sb15 = " + sb15); // sb15 = 01234o6

        // 14. void setLength(int newLength)
        // 지정된 길이로 문자열의 길이를 변경한다. 길이를 늘리는 경우에 나머지 빈 공간을 널문자 '\u0000'로 채운다.
        StringBuffer sb16 = new StringBuffer("0123456");
        sb16.setLength(5);
        System.out.println("sb16 = " + sb16); // sb16 = 01234
        StringBuffer sb17 = new StringBuffer("0123456");
        sb17.setLength(10);
        System.out.println("sb17 = " + sb17); // sb17 = 0123456
        String str1 = sb17.toString().trim();
        System.out.println("str1 = " + str1); // str1 = 0123456

        // 15. String toString()
        // StringBuffer 인스턴스의 문자열을 String으로 반환한다.
        StringBuffer sb18 = new StringBuffer("0123456");
        String str2 = sb18.toString();
        System.out.println("str2 = " + str2); // str2 = 0123456

        // 16. String substring(int start)
        //      String substring(int start, int end)
        // 지정된 범위 내의 문자열을 String으로 뽑아서 반환한다. 시작위치(start)만 지정하면 시작위치부터 문자열 끝까지 뽑아서 반환한다.
        StringBuffer sb19 = new StringBuffer("0123456");
        String str3 = sb19.substring(3);
        String str4 = sb19.substring(3, 5);
        System.out.println("str3 = " + str3); // str3 = 3456
        System.out.println("str4 = " + str4); // str4 = 34

    }
}
```



## StringBuilder

StringBuffer는 멀티쓰레드에 안전(thread safe)하도록 동기화되어 있습니다. 멀티쓰레드로 작성된 프로그램이 아닌 경우, StringBuffer의 동기화는 불필요하게 성능만 떨어뜨리게 된다. 그래서 StringBuffer에서 쓰레드의 동기화만 뺀 StringBuilder가 새로 추가되었습니다.

StringBuffer와 StringBuilder는 완전히 똑같은 기능입니다.

```java
StringBuffer sb;
sb = new StringBuffer();
sb.append("abc");

//---------------------------------------------->

StringBuilder sb;
sb = new StringBuilder();
sb.append("abc");
```





---

# Math클래스

Math클래스는 기본적인 수학계산에 유용한 메서드로 구성되어 있습니다. (임의의 수 random(), 반올림 round()) Math클래스의 생성자는 접근 제어자가 private이기 때문에 다른 클래스에서 Math인스턴스를 생성할 수 없습니다. 그 이유는 클래스 내에 인스턴스 변수가 하나도 없어서 인스턴스를 생성할 필요가 없기 때문입니다.

Math클래스의 메서드는 모두 static이며, 2개의 상수만을 정의해 놓고 있다.

```java
public static final double E = 2.7182818284590452354;	//자연로그의 밑
public static final double PI = 3.14159265358979323846;	//원주율
```

 

## 올림, 버림, 반올림

**round()** 

- 소수점 n번째 자리에서 반올림한 값을 반환하며, 항상 소수점 첫째자리에서 반올림을 해서 정수값(long)을 결과로 돌려줍니다.
- 소수점 넷째자리에서 반올림된 소수점 세 자리 값을 얻으려면 1000으로 곱하고 1000.0으로 나눕니다.
- 반올림이 필요하지 않다면 round()를 사용하지 않고 1000으로 곱하고 1000.0으로 나눕니다.

```java
//1. 원래 값에 100을 곱한다.
90.7552 * 100	//9075.52

//2. 위의 결과에 Math.round()를 사용한다.
Math.round(9075.52)	//9076

//3. 위의 결과를 다시 100.0으로 나눈다.
9076 / 100.0	//90.76
9076 / 100	//90	-> 정수형 값인 100 또는 100L로 나눌 경우 결과는 정수형 값이 나온다.
//정수형간의 연산에서는 반올림이 이루어지지 않는다.
```



**rint()** 

- round()처럼 소수점 첫째자리에서 반올림하지만, 반환값이 double입니다.

```java
out.printf("round(%3.1f) = %d%n", 1.5, round(1.5));		//반환값이 int
out.printf("rint(%3.1f) = %f%n", 1.5, round(1.5));		//반환값이 double

//매개변수의 값이 음수일 때
out.printf("round(%3.1f) = %d%n", -1.5, round(-1.5));		//-1
//round()는 소수점 첫째자리가 5 이하일 때, 더 큰 값으로 반올림한다.
out.printf("rint(%3.1f) = %f%n", -1.5, round(-1.5));		//-2.0
//rint()는 소수점 첫째자리가 5미만일 때, 더 큰 값으로 반올림한다.

//음수에서는 양수와 달리 -1.5를 버림(floor)하면 -2.0이 된다
out.printf("ceil(%3.1f) = %f%n", -1.5, ceil(-1.5));		//-1.0
out.printf("floor(%3.1f) = %f%n", -1.5, floor(-1.5));	//-2.0
```

 

## 예외를 발생시키는 메서드

JDK1.8부터 메서드 이름에 Exact가 포함된 메서드들이 새로 추가되었습니다. Exact는 정수형간의 연산에서 발생할 수 있는 오버플로우(overflow)를 감지하기 위한 것으로, 오버플로우가 발생하면 예외(ArithmeticException)를 발생시킵니다. 연산자의 경우, 결과만을 반환할 뿐 오버플로우가 발생했는지에 대해 알려주지 않습니다.

```java
int addExact(int x, int y)		//x + y
int subtractExact(int x, int y)	//x - y
int multiplyExact(int x, int y)	//x * y
int incrementExact(int a)	//a++
int decrementExact(int a)	//a--
int negateExact(int a)	//-a	-> 매개변수의 부호를 반대로 바꿔준다.
int toIntExact(long value)	//(int)value - int로의 형변환
```

 

## 삼각함수와 지수, 로그

- sqrt() : 제곱근 계산
- pow() : n제곱 계산
- atan2() : 직각 삼각형에서 두 변의 길이 a, b를 알면 끼인각을 구해준다.

```java
/* 두 점 (x1, y1), (x2, y2)간의 거리 c */
double c = sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));
//x1, y1 = 1
//x2, y2 = 2
double c = sqrt(pow(2-1, 2) + pow(2-1, 2));
double c = sqrt(pow(1, 2) + pow(1, 2));		//pow(1, 2)는 1의 2제곱
double c = sqrt(1.0 + 1.0);
double c = sqrt(2.0);
double c = 1.414214;


/* 두 점 (x1, y1)과 (x2, y2)을 이은 직선을 빗변으로 하는 삼각형 */
//두 변 a, b = 1
//끼인각
a = c * sin(끼인각)
b = c * cos(끼인각)
double a = c * sin(PI/4);	//PI/4 radian = 45 degree(끼인각)
double b = c * cos(PI/4);
//삼각함수는 매개변수의 단위가 라디안(radian)이므로
//45도를 라디안단위의 값으로 변환해주어 한다.
//double b = c * cos(toRadians(45));	//각도를 라디안으로 변환

out.printf("angle = %f rad%n", atan2(a, b));
out.printf("angle = %f degree%n%n", atan2(a, b) * 180 / PI);
//out.printf("angle = %f degree%n%n", toDegrees(atan2(a, b)));

//atan2메서드의 결과값은 단위가 라디안이므로 도(degree)단위로 변환하려면
//180/PI를 곱하거나 toDegrees(double angrad)를 이용한다.
```

<img src="https://blog.kakaocdn.net/dn/csFX8p/btrfZqiYseF/rq4qKKlDCDgIgA3fVvr6Ik/img.png" alt="img" style="zoom:80%;" />



## StrictMath클래스

Math클래스는 최대한의 성능을 얻기 위해 JVM이 설치된 OS의 메서드를 호출해서 사용합니다. 즉, OS에서 의존적인 계산을 합니다. 예를 들어 부동소수점 계산의 경우 반올림의 처리방법 설정이 OS마다 다를 수 있기 때문에 컴퓨터마다 결과가 다를 수 있습니다. 이러한 차이를 없애기 위해 성능은 다소 포기하는 대신, 어떤 OS에서 실행되어도 항상 같은 결과를 얻도록 Math클래스를 새로 작성한 것이 StrictMath 클래스입니다.

```java
public class Main {
    public static void main(String[] args) {
        // 1. static double abs(double a)
        //      static float abs(float f)
        //      static int abs(int f)
        //      static long abs(long f)
        // 주어진 값의 절대값을 반환한다.
        int i1 = Math.abs(-10);
        double d1 = Math.abs(-10.0);
        System.out.println(" i1 = " + i1 + " d1 = " + d1); //  i1 = 10 d1 = 10.0

        // 2. static double ceil(double a)
        // 주어진 값을 올림하여 반환한다.
        double d2 = Math.ceil(10.1);
        double d3 = Math.ceil(-10.1);
        double d4 = Math.ceil(10.0000015);
        System.out.println("d2 = " + d2 + " d3 = " + d3 + " d4 = " + d4); // d2 = 11.0 d3 = -10.0 d4 = 11.0


        // 3. static double floor(double a)
        // 주어진 값을 버림하여 반환한다.
        double d5 = Math.floor(10.8);
        double d6 = Math.floor(-10.8);
        System.out.println("d5 = " + d5 + " d6 = " + d6); // d5 = 10.0 d6 = -11.0

        // 4. static double max(double a, double b)
        //      static float max(float a, float b)
        //      static int max(int a, int b)
        //      static long max(long a, long b)
        // 주어진 두 값을 비교하여 큰 쪽을 반환한다.
        double d7 = Math.max(9.5, 9.50001);
        int i2 = Math.max(0, -1);
        System.out.println("d7 = " + d7 + " i2 = " + i2); // d7 = 9.50001 i2 = 0


        // 5. static double min(double a, double b)
        //      static float min(float a, float b)
        //      static int min(int a, int b)
        //      static long min(long a, long b)
        double d8 = Math.min(9.5, 9.50001);
        int i3 = Math.min(0, -1);
        System.out.println("d8 = " + d8 + " i3 = " + i3); // d8 = 9.5 i3 = -1

        // 6. static double random()
        // 0.0 ~ 1.0범위의 임의의 double값을 반환한다.
        // (1.0은 범위에 포함되지 않는다.)
        double d9 = Math.random();
        int i4 = (int)(Math.random() * 10) + 1;
        System.out.println("d9 = " + d9 + " i4  = " + i4); // d9 = 0.48526749990551155 i4  = 5

        // 7. static double rint(double a)
        // 주어진 double값과 가장 가까운 정수값을 double형으로 반환한다.
        double d10 = Math.rint(5.5);
        double d11 = Math.rint(5.1);
        double d12 = Math.rint(-5.5);
        double d13 = Math.rint(-5.1);
        System.out.println("d10 = " + d10 + " d11 = " + d11 + " d12 = " + d12 + " d13 = " + d13); 
        // d10 = 6.0 d11 = 5.0 d12 = -6.0 d13 = -5.0

        // 8. static long round(double a)
        //      static long round(float a)
        // 소수점 첫째자리에서 반올림한 정수값(long)을 반환한다. 
        // 매개변수의 값이 음수인 경우, round()와 rint()의 결과가 다르다.
        long l1 = Math.round(5.5);
        long l2 = Math.round(5.11);
        long l3 = Math.round(-5.5);
        long l4 = Math.round(-5.1);
        System.out.println("l1 = " + l1 + " l2 = " + l2 + " l3 = " + l3 + " l4 = " + l4); 
        // l1 = 6 l2 = 5 l3 = -5 l4 = -5
        double d14 = 90.7552;
        double d15 = Math.round(d14 * 100) / 100.0;
        System.out.println("d15 = " + d15); // d15 = 90.76

    }
}
```



