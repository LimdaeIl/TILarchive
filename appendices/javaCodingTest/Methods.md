# 자바  메서드 모음 1



### 📚 String 메서드 

```java
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String str1 = "Hello Java!";
        String str2 = "안녕하세요! 반갑습니다.";

        // 1. length()
        System.out.println(str1.length()); // 12
        System.out.println(str2.length()); // 13

        // 2. charAt()
        System.out.println(str1.charAt(1)); // e
        System.out.println(str2.charAt(1)); // 녕

        // 3. indexOf(), lastIndexOf()
        System.out.println(str1.indexOf('a')); // 7, (앞부터)
        System.out.println(str1.lastIndexOf('a')); // 9, (뒤부터)

        System.out.println(str1.indexOf('a', 8)); // 9,  (8번 인덱스부터 앞으로)
        System.out.println(str1.lastIndexOf('a', 8)); // 7, (8번 인덱스부터 뒤로)..0.7

        System.out.println(str1.indexOf("Java")); // 6
        System.out.println(str1.lastIndexOf("Java")); // 6

        System.out.println(str2.indexOf("하세요")); // 2
        System.out.println(str2.lastIndexOf("하세요")); // 2

        System.out.println(str1.indexOf("Bye")); // -1, 해당 문자열이 없으면 -1
        System.out.println(str2.lastIndexOf("고맙습니다.")); // -1

        // 4. valueOf()
        String str3 = String.valueOf(2.3);
        String str4 = String.valueOf(false);

        System.out.println(str3); // 2.3, 문자열로 변환
        System.out.println(str4); // false, 문자열로 변환

        // 5. concat()
        String str5 = str3.concat(str4);
        System.out.println(str5); // 2.3false

        // 6. String.valueOf() + concat()
        String str6 = "안녕" + 3;
        System.out.println(str6); // 안녕3

        String str7 = "안녕".concat(String.valueOf(3));
        System.out.println(str7); // 안녕3

        // 7. getBytes(), Arrays.toString()
        String str8 = "Hello Java!";
        String str9 = "안녕하세요";

        byte[] array1 = str8.getBytes();
        byte[] array2 = str9.getBytes();
        System.out.println(Arrays.toString(array1)); // [72, 101, 108, 108, 111, 32, 74, 97, 118, 97, 33]
        System.out.println(Arrays.toString(array2)); // [-20, -107, -120, -21, -123, -107, -19, -107, -104, -20, -124, -72, -20, -102, -108]

        char[] array3 = str8.toCharArray();
        char[] array4 = str9.toCharArray();
        System.out.println(Arrays.toString(array3)); // [H, e, l, l, o,  , J, a, v, a, !]
        System.out.println(Arrays.toString(array4)); // [안, 녕, 하, 세, 요]


        // 8. toLowerCase(), toUpperCase()
        String str10 = "Java Study";
        System.out.println(str1.toLowerCase()); // hello java!
        System.out.println(str1.toUpperCase()); // HELLO JAVA!

        // 9. replace()
        System.out.println(str10.replace("Study", "공부")); // Java 공부

        // 10. substring()
        System.out.println(str1.substring(0, 5)); // Hello

        // 11. split()
        String[] strArray = "abc/def-ghi jkl".split("/|-| ");
        System.out.println(Arrays.toString(strArray)); // [abc, def, ghi, jkl]]

        // 12. trim()
        System.out.println("    abc    ".trim()); // abc

        // 13. ==, equals(), equalsIgnoreCase()
        String str11 = "java";
        String str12 = new String("java");
        String str13 = new String("java");

        System.out.println(str11 == str12); // false
        System.out.println(str12 == str13); // false

        System.out.println(str11.equals(str12)); // true
        System.out.println(str12.equals(str13)); // true
        System.out.println(str11.equalsIgnoreCase(str13)); // true
    }
}

```

- **`length()`** : 문자열의 길이를 반환
- **`charAt()`** : 문자열에서 특정 인덱스에 위치한 문자열을 반환
- **`indexOf()`** : 문자열에서 특정 문자나 특정 문자열을 앞에서부터 찾아 위치값을 반환
- **`lastindexOf()`** : 문자열에서 특정 문자나 특정 문자열을 뒤에서부터 찾아 위치값을 반환
- **`String.valueOf()`** : 기본 자료형을 문자열로 바꾸는 정적 메서드
- **`concat()`** : 2개의 문자열을 연결 
- **`getBytes()`** : 문자열을 byte 배열로 반환
- **`toCharArray()`** : 문자열을 char 배열로 반환
- **`toLowerCase()`** : 영문 문자를 모두 소문자로 변환
- **`toUpperCase()`** : 영문 문자를 모두 대문자로 변환
- **`replace()`** : 일부 문자열을 다른 문자열로 대체
- **`subString()`** : 문자열의 일부만을 포함하는 새로운 문자열 객체를 생성
- **`split()`** : 특정 기호를 기준으로 문자열을 분리
- **`trim()`** : 문자열의 좌우 공백을 제거
- **`equals()`** : 두 문자열의 위칫값이 아닌 실제 데이터값을 비교(대소문자 구분함)
- **`equalsIgnoreCase()`** : 두 문자열의 위칫값이 아닌 실제 데이터값을 비교(대소문자 구분하지 않음)

