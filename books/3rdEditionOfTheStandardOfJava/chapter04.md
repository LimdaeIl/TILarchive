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

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/2e94d617-8c87-4b02-8442-6143cf2b19d4)


![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/613f19b2-6bb1-4145-9b00-82dffcbaff49)




## 2. 중첩 if문

- if문 안에 또 다른 if문을 중첩해서 넣을 수 있다.
-  if문의 중첩횟수에는 거의 제한이 없다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/63f63d62-a388-4b2a-bf96-4a2790910f87)


![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/8853531d-e23a-4249-9a5f-412d43eb75ec)




## 3. switch 

- if문의 조건식과 달리, 조건식의 계산결과가 int범위 이하의 정수만 가능
- 조건식의 계산결과와 일치하는 case문으로 이동 후 break문을 만날 때까지 문장들을 수행한다.(break문이 없으면 switch문의 끝까지 진행한다.)
- 일치하는 case문의 값이 없는 경우 default문으로 이동한다.(default문 생략 가능합니다.)
- case문의 값으로 변수를 사용할 수 없다.(리터럴, 상수만 가능합니다.)

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/cb05c92e-18d0-43bd-b50d-286e6cc12c50)


![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/19e80ef0-41c1-4d21-b83a-2c8bd8c91780)




## 4. 중첩 switch문

- switch문 안에 또 다른 switch문을 중첩해서 넣을 수 있다.
- switch문의 중첩횟수에는 거의 제한이 없다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/f90fb4e2-96a1-45d2-a438-a4a54f2b47c9)




## 5. if문과 switch문의 비교

- if문이 주로 사용되며, 경우의 수가 많은 경우 switch문을 사용할 것을 고려한다.
- 모든 switch문은 if문으로 변경이 가능하지만, if문은 switch문으로 변경할 수 없는 경우가 많다.
- if문 보다 switch문이 더 간결하고 효율적이다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/f9e72e3e-2a59-458c-9c0f-cd81816a996f)




## 6. Math.random()

- Math클래스에 정의된 난수(亂數) 발생함수
- 0.0과 1.0 사이의 double값을 반환한다.(0.0 <= Math.random() < 1.0)

 **예) 1~10범위의 임의의 정수를 얻는 식 만들기**

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/bcfd5102-90c1-4cf3-abda-1f48fdf51cc3)




## 7. 반복문 – for, while, do-while

- 문장 또는 문장들을 반복해서 수행할 때 사용
- 조건식과 수행할 블럭`{ }` 또는 문장으로 구성
- 반복 횟수가 중요한 경우에 for문을 그 외에는 while문을 사용한다.
- for문과 while문은 서로 변경가능하다.
- do-while문은 while문의 변형으로 블럭{}이 최소한 한 번은 수행될 것을 보장한다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/0c7eea7c-3414-4899-b06f-5c38728c7149)


## 8. for문

- 초기화, 조건식, 증감식 그리고 수행할 블럭`{ }` 또는 문장으로 구성

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/36b26b9e-f5cd-4fb9-8ff6-6d8ad4ddf52b)


![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/00cd4d6d-c48a-4357-81dc-4fc3400c3da0)




## 9. 중첩for문

- for문 안에 또 다른 for문을 포함시킬 수 있다.
- for문의 중첩횟수에는 거의 제한이 없다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/540cee7f-200a-47a0-b4af-b6869ac7a68f)




## 10. while문

- 조건식과 수행할 블럭`{ }` 또는 문장으로 구성
-  while문 안에 또 다른 while문을 포함시킬 수 있다.
-  while문의 중첩횟수에는 거의 제한이 없다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/71a29437-7904-42e3-98d4-fcf17f8fb5e7)


![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/895cfab3-6a08-43b4-9431-53d15cee0b4c)




## 11. do-while문

- while문의 변형. 블럭{}을 먼저 수행한 다음에 조건식을 계산한다.
-   블럭`{ }`이 최소한 1번 이상 수행될 것을 보장한다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/b9ef5dec-af50-4186-841d-be2c8c091de2)




## 12. break문

- 자신이 포함된 하나의 반복문 또는 switch문을 빠져 나온다.
- 주로 if문과 함께 사용해서 특정 조건을 만족하면 반복문을 벗어나게 한다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/363ed64b-a5ff-4f65-b826-8dc2080d46f7)




## 13. continue문

- 자신이 포함된 반복문의 끝으로 이동한다.(다음 반복으로 넘어간다.)
-   continue문 이후의 문장들은 수행되지 않는다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/75eb7fa6-1b41-46bd-9bf3-50c69e25483a)




## 14. 이름 붙은 반복문과 break, continue

- 반복문 앞에 이름을 붙이고, 그이름을 break, continue와 같이 사용한다.
- 둘 이상의 반복문을 벗어나거나 반복을 건너뛰는 것이 가능하다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/a957fb6a-f134-4ffc-a4d6-202d2149aba3)
