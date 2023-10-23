# Chapter 03 연산자



## 1. 연산자(Operator)

- 연산자(Operator): 어떠한 기능을 수행하는 기호(`+, -, *, /, ...`)
- 피연산자(Operand): 연산자의 작업 대상(변수, 상수, 리터럴, 수식)
- a + b에서 a, b는 피연산자, +는 연산자



## 2. 연산자의 종류

- 단항 연산자: `+, -, ++, --, ~, !`
- 이항 연산자
  - 산술 연산자: `+, -, *, /, %, <<, >>, >>>`
  - 비교 연산자: `>, <, >=, <=, ==, !=, instanceof`
  - 논리 연산자: `&&, ||, &, ^, |`
- 삼항 연산자: `? :`
- 대입 연산자: `=`



## 3. 연산자의 우선순위

**연산자 종류와 우선순위**

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/4f7e8379-0c14-4687-a9ba-fbe854e604eb)


연산자의 우선순위는 다음과 같습니다.

- 괄호의 우선순위가 가장 높습니다.
- 산술 > 비교 > 논리 > 대입
- 단항 > 이항 > 삼항
- 연산자의 연산 진행방향은 왼쪽에서 오른쪽**→**입니다. 단, 단항, 대입 연산자만 오른쪽에서 왼쪽**←**입니다.



**연산자 우선순위 예제**

-  -x + 3: 단항 > 이항
- x + 3 * y: 곱셈, 나눗셈 > 덧셈, 뺄셈
- x + 3 > y - 2: 산술 > 비교
- x > 3 && x < 5: 비교 > 논리
- int result = x + y * 3; 항상 대입은 맨 끝에



**연산자 주의사항**

1. `<<, >>, >>>`는 덧셈 연산자보다 우선순위가 낮다.
   -  x << 2 +1 의 수식과 x << (2+1)은 동일합니다.
2. `||, |(OR)는 &&, &(AND)보다 우선순위가 낮다.`
   - x < -1 || 3 && x < 5 의 수식과 x < -1 || (x > 3 && x < 5)와 같다.



## 4. 증감 연산자

- 증가 연산자(`++`): 피연산자의 값을 1 증가
- 감소 연산자(`--`): 피연산자의 값을 1 감소

```java
int i = 0;
int j = 0;
```



## 5. 부호연산자(+,-)와 논리부정연산자(!)

- 부호연산자(`+,-`) : `‘+’`는 피연산자에 1을 곱하고  `‘-’`는 피연산자에 -1을 곱한다.
- 논리부정연산자(`!`) : `true`는 `false`로, `false`는 `true`로 피연산자가 boolean일 때만 사용가능

```java
int i = -10;
i = +i;
i = -i;

boolean power = false;
power = !power; // true
power = !power; // false
```



## 6. 비트전환연산자 - `~`

정수를 2진수로 표현했을 때, 1을 0으로 0은 1로 바꾼다.(정수형에만 사용 가능합니다.)

**음수를 2진수로 표현하는 방법**

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/8365ffa1-0135-4011-b18b-db9379c375af)




## 7. 이항연산자의 특징

- 이항연산자는 연산을 수행하기 전에 피연산자의 타입을 일치시킨다.

- `int`보다 크기가 작은 타입은 `int`로 변환한다.(`byte, char, short → int` )

- 피연산자 중 표현범위가 큰 타입으로 형변환 한다.

- `byte + short → int + int → int `
  `char + int  → int + int → int `

  `float + int → float + float → float `
  `long + float → float + float → float `
  `float + double → double + double → double`

```java
byte a = 10;
byte b = 20;
byte c = a + b; // byte + byte → int + int → int

byte c = (byte)a + b; // 에러 발생!
byte c = (byte)(a + b); // Ok.

int a = 1000000;  // 1,000,000
int b = 2000000;  // 2,000,000
long c = a * b;   // c는 2,000,000,000,000? c는 -1454759936 !!! int * int → int 

long c = (long)a * b; // c는 2,000,000,000,000 ! long * int → long * long →  long

long a = 1000000 * 1000000;  // a는 -727,379,968
long b = 1000000 * 1000000L; // b는 1,000,000,000,000

int c = 1000000 * 1000000 / 1000000;  // c는 -727
int d = 1000000 / 1000000 * 1000000; // d는 1,000,000

char c1 = ‘a’; // ASCII 97
char c2 = c1 + 1;  // 에러
char c2 = (char)(c1 + 1);  // OK
char c2 = ++c1;  // OK

int i = ‘B’ – ‘A’;
int i = ‘2’ – ‘0’;

float pi = 3.141592f;
float shortPi = (int)(pi * 1000) / 1000f;
                (int)(3.141592f * 1000) / 1000f;
                (int)(3141.592f) / 1000f;
                         3141 / 1000f;
                         3141.0f / 1000f
                              3.141f

// * Math.round() : 소수점 첫째자리에서 반올림한 값을 반환
float pi = 3.141592f;
float shortPi = Math.round(pi * 1000) / 1000f;
         Math.round(3.141592f * 1000) / 1000f;
                Math.round(3141.592f) / 1000f;
                         3142 / 1000f;
                         3142.0f / 1000f
                              3.142f
```



## 8. 나머지연산자 - %

- 나누기한 나머지를 반환(홀수, 짝수 등 배수 검사에 주로 사용합니다.)

```java
int share = 10 / 8;
int remain = 10 % 8;

 10 %  8  →  2
 10 % -8  →  2
-10 %  8  → -2
-10 % -8  → -2
```



## 9. 쉬프트연산자 - <<, >>, >>>

- 2^n으로 곱하거나 나눈 결과를 반환(곱셈, 나눗셈보다 빠릅니다.)
- `x << n` 은 `x * 2^n`과 같다. 
  `x >> n` 은 `x / 2^n`과 같다.
- `8 << 2` 는 `8 * 22`과 같다. 
  `8 >> 2` 는 `8 / 22`과 같다.



## 10. 비교연산자  >, <, >=, <=, ==, !=

- 피연산자를 같은 타입으로 변환한 후에 비교한다.(결과값은 true 또는 false 입니다.)
- 기본형(boolean 제외)과 참조형에 사용할 수 있으나 참조형에는 `==`와 `!=` 만 사용할 수 있다.

**비교연산자의 연산결과**

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/26d50aff-c2fa-492f-b1e3-6e5656570df8)


```java
‘A’ < ‘B’ →  65 < 66 → true
‘0’ == 0  → 48 == 0 → false
‘A’ != 65  → 65 != 65 → false
10.0d == 10.0f  → 10.0d == 10.0d → true
0.1d == 0.1f  → 0.1d == 0.1d → true? false?
double d = (double)0.1f;
System.out.println(d);  // 0.10000000149011612
(float)0.1d == 0.1f → 0.1f == 0.1f → true
```



## 11. 비트연산자 &, |, ^

- 피연산자를 비트단위로 연산(실수형(float, double)을 제외한 모든 기본형에 사용 가능합니다.)
- ▶ OR연산자(`|`) : 피연산자 중 어느 한 쪽이 1이면 1이다.
- ▶ AND연산자(`&`) : 피연산자 양 쪽 모두 1이면 1이다. 
- ▶ XOR연산자(`^`) : 피연산자가 서로 다를 때 1이다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/281c37fb-6255-4896-a291-591c6e69cfcb)


**비트연산자의 연산결과**

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/685d973c-353c-4fe6-bb68-516c5b43ca1c)


![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/8c154407-13a3-4073-b328-70f0b3fad2ec)




## 12. 논리연산자 &&, ||

- 피연산자가 boolean이어야 하며 연산결과도 boolean.(&&가 || 보다 우선순위가 높습니다.. 같이 사용되는 경우 괄호를 사용합니다.)
- ▶ OR연산자(`||`) : 피연산자 중 어느 한 쪽이 true이면 true이다.
- ▶ AND연산자(`&&`) : 피연산자 양 쪽 모두 true이면 true이다. 

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/4c26d40c-1115-4645-97b0-d620970755db)


```java
int i = 7;
i > 3 && i < 5
i > 3  ||  i < 0
    
char x = ‘j’;
x >= ‘a’ && x <= ‘z’
(x >= ‘a’ && x <= ‘z’) || (x >= ‘A’ && x <= ‘Z’)
```



## 13. 삼항연산자 ? :

- 조건식의 연산결과가 true이면 ‘식1’의 결과를 반환하고 false이면 ‘식2’의 결과를 반환한다.
- (조건식) ? 식1 : 식2

```java
int x = -10;
int absX = x >= 0 ? x : -x;

if(x>=0) {
   absX = x;
} else {
   abxX = -x;
}

int score = 50;
char grade = score >= 90 ? ‘A’ : (score >= 80? ‘B’ : ’C’);
```



## 14. 대입연산자 = op=

- \- 오른쪽 피연산자의 값을 왼쪽 피연산자에 저장(단, 왼쪽 피연산자는 상수가 아니어야 합니다.)

```java
int i = 0;
i = i + 3;

final int MAX = 3;
MAX = 10;  // 에러
```

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/092fba8f-e811-4da4-8304-96eba3d0ad94)
