# Chapter 04 조건문과 반복문



## 1.조건문 – if, switch

- 조건문은 조건식과 실행될 하나의 문장 또는 블럭`{ }`으로 구성
- Java에서 조건문은 if문과 switch문 두 가지 뿐이다.
- if문이 주로 사용되며, 경우의 수가 많은 경우 switch문을 사용할 것을 고려한다.
-  모든 switch문은 if문으로 변경이 가능하지만, if문은 switch문으로 변경할 수 없는 경우가 많다.
- if문은 if, if-else, if-else if의 세가지 형태가 있다.
- 조건식의 결과는 반드시 true 또는 false 이어야 한다.

```java
if(조건식) { 문장들 }
```

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231023171501067.png" alt="image-20231023171501067" style="zoom:80%;" />

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231023171602558.png" alt="image-20231023171602558" style="zoom:80%;" />



## 2. 중첩 if문

- if문 안에 또 다른 if문을 중첩해서 넣을 수 있다.
-  if문의 중첩횟수에는 거의 제한이 없다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231023172250851.png" alt="image-20231023172250851" style="zoom:60%;" />

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231023172309278.png" alt="image-20231023172309278" style="zoom:67%;" />



## 3. switch 

- if문의 조건식과 달리, 조건식의 계산결과가 int범위 이하의 정수만 가능
- 조건식의 계산결과와 일치하는 case문으로 이동 후 break문을 만날 때까지 문장들을 수행한다.(break문이 없으면 switch문의 끝까지 진행한다.)
- 일치하는 case문의 값이 없는 경우 default문으로 이동한다.(default문 생략 가능합니다.)
- case문의 값으로 변수를 사용할 수 없다.(리터럴, 상수만 가능합니다.)

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231023181525001.png" alt="image-20231023181525001" style="zoom:80%;" />

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231023181704799.png" alt="image-20231023181704799" style="zoom:80%;" />



## 4. 중첩 switch문

- switch문 안에 또 다른 switch문을 중첩해서 넣을 수 있다.
- switch문의 중첩횟수에는 거의 제한이 없다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231023181753726.png" alt="image-20231023181753726" style="zoom:80%;" />



## 5. if문과 switch문의 비교

- if문이 주로 사용되며, 경우의 수가 많은 경우 switch문을 사용할 것을 고려한다.
- 모든 switch문은 if문으로 변경이 가능하지만, if문은 switch문으로 변경할 수 없는 경우가 많다.
- if문 보다 switch문이 더 간결하고 효율적이다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231023181900140.png" alt="image-20231023181900140" style="zoom:80%;" />



## 6. Math.random()

- Math클래스에 정의된 난수(亂數) 발생함수
- 0.0과 1.0 사이의 double값을 반환한다.(0.0 <= Math.random() < 1.0)

 **예) 1~10범위의 임의의 정수를 얻는 식 만들기**

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231023183645267.png" alt="image-20231023183645267" style="zoom:80%;" />



## 7. 반복문 – for, while, do-while

- 문장 또는 문장들을 반복해서 수행할 때 사용
- 조건식과 수행할 블럭`{ }` 또는 문장으로 구성
- 반복 횟수가 중요한 경우에 for문을 그 외에는 while문을 사용한다.
- for문과 while문은 서로 변경가능하다.
- do-while문은 while문의 변형으로 블럭{}이 최소한 한 번은 수행될 것을 보장한다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231023184807751.png" alt="image-20231023184807751" style="zoom:77%;" />

## 8. for문

- 초기화, 조건식, 증감식 그리고 수행할 블럭`{ }` 또는 문장으로 구성

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231023185140169.png" alt="image-20231023185140169" style="zoom:80%;" />

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231023185209659.png" alt="image-20231023185209659" style="zoom:80%;" />



## 9. 중첩for문

- for문 안에 또 다른 for문을 포함시킬 수 있다.
- for문의 중첩횟수에는 거의 제한이 없다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231023185259491.png" alt="image-20231023185259491" style="zoom:80%;" />



## 10. while문

- 조건식과 수행할 블럭`{ }` 또는 문장으로 구성
-  while문 안에 또 다른 while문을 포함시킬 수 있다.
-  while문의 중첩횟수에는 거의 제한이 없다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231023185325320.png" alt="image-20231023185325320" style="zoom:80%;" />

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231023185409387.png" alt="image-20231023185409387" style="zoom:80%;" />



## 11. do-while문

- while문의 변형. 블럭{}을 먼저 수행한 다음에 조건식을 계산한다.
-   블럭`{ }`이 최소한 1번 이상 수행될 것을 보장한다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231023185448886.png" alt="image-20231023185448886" style="zoom:80%;" />



## 12. break문

- 자신이 포함된 하나의 반복문 또는 switch문을 빠져 나온다.
- 주로 if문과 함께 사용해서 특정 조건을 만족하면 반복문을 벗어나게 한다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231023185515626.png" alt="image-20231023185515626" style="zoom:80%;" />



## 13. continue문

- 자신이 포함된 반복문의 끝으로 이동한다.(다음 반복으로 넘어간다.)
-   continue문 이후의 문장들은 수행되지 않는다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231023185608335.png" alt="image-20231023185608335" style="zoom:80%;" />



## 14. 이름 붙은 반복문과 break, continue

- 반복문 앞에 이름을 붙이고, 그이름을 break, continue와 같이 사용한다.
- 둘 이상의 반복문을 벗어나거나 반복을 건너뛰는 것이 가능하다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231023185651194.png" alt="image-20231023185651194" style="zoom:80%;" />