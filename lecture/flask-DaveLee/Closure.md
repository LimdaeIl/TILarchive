# Closure



### 1. 지역 변수와 전역 변수

```python
x = 10	# 전역 변수

def foo(): # 전역 변수를 출력하는 foo 함수
    print(x)

foo() # 10
print(x) # 10
```

```python
10
10
```

`foo` 함수에서 함수 바깥에 있는 변수 `x`의 값을 출력했습니다. 물론 함수 바깥에서도 `x`의 값을 출력할 수 있습니다. 
이처럼 함수를 포함하여 스크립트 전체에서 접근할 수 있는 변수를 **전역 변수(global variable)**라고 부릅니다.
특히 **전역 변수에 접근할 수 있는 범위를 전역 범위(global scope)**라고 합니다.



그렇다면 `x`를 함수 `foo` 함수 안에서 만들면 어떻게 될까요?

```python
def foo():
    x = 10      # foo의 지역 변수
    print(x)    # foo의 지역 변수 출력
 
foo()
print(x)        # 에러. foo의 지역 변수는 출력할 수 없음
```

```
10
Traceback (most recent call last):
  File "C:\project\local_variable.py", line 6, in <module>
    print(x)        # 에러. foo의 지역 변수는 출력할 수 없음
NameError: name 'x' is not defined
```

실행을 해보면 `x`가 정의되지 않았다는 에러가 발생합니다. 왜냐하면 변수 `x`는 함수 `foo` 안에서 만들었기 때문에 `foo`의 **지역 변수(local variable)**입니다. 따라서 지역 변수는 변수를 만든 함수 안에서만 접근할 수 있고, 함수 바깥에서는 접근할 수 없습니다. 특히 **지역 변수를 접근할 수 있는 범위를 지역 범위(local scope)**라고 합니다.



### 2. 함수 안에서 전역 변수 변경하기

만약 함수 안에서 전역 변수의 값을 변경하면 어떻게 될까요?

```python
x = 10          # 전역 변수

def foo():
    x = 20      # x는 foo의 지역 변수
    print(x)    # foo의 지역 변수 출력
 
foo()
print(x)        # 전역 변수 출력
```

```
20
10
```

분명 함수 `foo` 안에서 `x = 20`처럼 `x`의 값을 `20`으로 변경했습니다. 하지만 함수 바깥에서 `print`로 `x`의 값을 출력해보면 `10`이 나옵니다. 겉으로 보기에는 `foo` 안의 `x`는 전역 변수인 것 같지만 실제로는 **`foo`의 지역 변수**입니다. 즉, 전역 변수 `x`가 있고, `foo`에서 지역 변수 `x`를 새로 만들게 됩니다. 이 둘은 이름만 같을 뿐 서로 다른 변수입니다.

**함수 안에서 전역 변수의 값을 변경하려면 `global` 키워드를 사용해야 합니다.** 다음과 같이 함수 안에서 `global`에 전역 변수의 이름을 지정해줍니다.



```python
x = 10          # 전역 변수

def foo():
    global x    # 전역 변수 x를 사용하겠다고 설정
    x = 20      # x는 전역 변수
    print(x)    # 전역 변수 출력
 
foo()
print(x)        # 전역 변수 출력
```

```
20
20
```

이제 함수 안에서 `x`를 `20`으로 변경하면 함수 바깥에서 `x`를 출력했을 때 `20`이 나옵니다. 이렇게 함수 안에서 변수를 `global`로 지정하면 전역 변수를 사용하게 됩니다.

만약 전역 변수가 없을 때 함수 안에서 `global`을 사용하면 해당 변수는 전역 변수가 됩니다.

```python
# 전역 변수 x가 없는 상태
def foo():
    global x    # x를 전역 변수로 만듦
    x = 20      # x는 전역 변수
    print(x)    # 전역 변수 출력
 
foo()
print(x)        # 전역 변수 출력
```



**참고 |** **네임스페이스**

파이썬에서 변수는 네임스페이스(`namespace`, 이름공간)에 저장됩니다. 
다음과 같이 locals 함수를 사용하면 현재 네임스페이스를 딕셔너리 형태로 출력할 수 있습니다.

```python
>>> x = 10
>>> locals()
{'__name__': '__main__', '__doc__': None, '__package__': None, '__loader__': <class '_frozen_importlib.BuiltinImporter'>, '__spec__': None, '__annotations__': {}, '__builtins__': <module 'builtins' (built-in)>, 'x': 10}
```

출력된 네임스페이스를 보면 `'x': 10`처럼 변수 x와 값 10이 저장되어 있습니다. 여기서는 전역 범위에서 네임스페이스를 출력했으므로 전역 네임스페이스를 가져옵니다.

마찬가지로 함수 안에서 locals를 사용할 수도 있습니다.

```python
>>> def foo():
...     x = 10
...     print(locals())
...
>>> foo()
{'x': 10}
```

네임스페이스를 보면 `'x': 10`만 저장되어 있습니다. 이때는 지역 범위에서 네임스페이스를 출력했으므로 지역 네임스페이스를 가져옵니다.



### 3. 함수 안에서 함수 만들기

이번에는 함수 안에서 함수를 만드는 방법을 알아보겠습니다. 
다음과 같이 `def`로 함수를 만들고 그 안에서 다시 `def`로 함수를 만들면 됩니다.

```python
def 함수이름1():
    코드
    def 함수이름2():
        코드
```



간단하게 함수 안에서 문자열을 출력하는 함수를 만들고 호출해봅니다.

```python
def print_hello():
    hello = 'Hello, world!'
    
    def print_message():
        print(hello)
        
    print_message()
    
print_hello()
```

```
Hello, world!
```

함수 `print_hello` 안에서 다시 `def`로 함수 `print_message`를 만들었습니다. 그리고 `print_hello` 안에서 `print_message()`처럼 함수를 호출했습니다. 하지만 아직 함수를 정의만 한 상태이므로 아무것도 출력되지 않습니다.

두 함수가 실제로 동작하려면 바깥쪽에 있는 `print_hello`를 호출해주어야 합니다. 즉, `print_hello` :arrow_right: `print_message` 순으로 실행됩니다.



#### 지역 변수의 범위

그럼 `print_hello` 함수와 `print_message` 함수에서 지역 변수의 범위를 살펴보겠습니다. 안쪽 함수 `print_message`에서는 바깥쪽 함수 `print_hello`의 지역 변수 `hello`를 사용할 수 있습니다.

```python
def print_hello():
    hello = 'Hello, world!'
    def print_message():
        print(hello)    # 바깥쪽 함수의 지역 변수를 사용
```

즉, 바깥쪽 함수의 지역 변수는 그 안에 속한 모든 함수에서 접근할 수 있습니다.



#### 지역 변수 변경하기

지금까지 바깥쪽 함수의 지역 변수를 안쪽 함수에서 사용해봤습니다. 그럼 바깥쪽 함수의 지역 변수를 안쪽 함수에서 변경하면 어떻게 될까요? 다음과 같이 안쪽 함수 B에서 바깥쪽 함수 A의 지역 변수 x를 변경해봅니다.



```python
def A():
    x = 10        # A의 지역 변수 x
    def B():
        x = 20    # x에 20 할당
 
    B()
    print(x)      # A의 지역 변수 x 출력
 
A()
```

```
10
```

실행을 해보면 `20`이 나와야 할 것 같은데 `10`이 나왔습니다. 왜냐하면 겉으로 보기에는 바깥쪽 함수 `A`의 지역 변수 `x`를 변경하는 것 같지만, 실제로는 안쪽 함수 `B`에서 이름이 같은 지역 변수 `x`를 새로 만들게 됩니다. 즉, 파이썬에서는 함수에서 변수를 만들면 항상 현재 함수의 지역 변수가 됩니다.



```python
def A():
    x = 10        # A의 지역 변수 x
    def B():
        x = 20    # B의 지역 변수 x를 새로 만듦
```

현재 함수의 바깥쪽에 있는 지역 변수의 값을 변경하려면 nonlocal 키워드를 사용해야 합니다. 다음과 같이 함수 안에서 nonlocal에 지역 변수의 이름을 지정해줍니다.

- **nonlocal** **지역변수**

```python
def A():
    x = 10        # A의 지역 변수 x
    def B():
        nonlocal x    # 현재 함수의 바깥쪽에 있는 지역 변수 사용
        x = 20        # A의 지역 변수 x에 20 할당
 
    B()
    print(x)      # A의 지역 변수 x 출력
 
A()
```

```
20
```

이제 함수 B에서 함수 A의 지역 변수 x를 변경할 수 있습니다. 
**즉, `nonlocal`은 현재 함수의 지역 변수가 아니라는 뜻이며 바깥쪽 함수의 지역 변수를 사용합니다.**



#### nonlocal이 변수를 찾는 순서

`nonlocal`은 현재 함수의 바깥쪽에 있는 지역 변수를 찾을 때 가장 가까운 함수부터 먼저 찾습니다. 
이번에는 함수의 단계를 A, B, C로 만들었습니다.

```python
def A():
    x = 10
    y = 100
    def B():
        x = 20
        def C():
            nonlocal x
            nonlocal y
            x = x + 30
            y = y + 300
            print(x)
            print(y)
        C()
    B()
 
A()
```

```
50
400
```

함수 `C`에서 `nonlocal x`를 사용하면 바깥쪽에 있는 함수 `B`의 지역 변수`x = 20`을 사용하게 됩니다. 따라서 `x = x + 30`은 `50`이 나옵니다. 그리고 함수 `C`에서 `nonlocal y`를 사용하면 바깥쪽에 있는 함수의 지역 변수 `y`를 사용해야 하는데 함수 `B`에는 `y`가 없습니다. 이때는 한 단계 더 바깥으로 나가서 함수 `A`의 지역 변수 `y`를 사용하게 됩니다. 즉, 가까운 함수부터 지역 변수를 찾고, 지역 변수가 없으면 계속 바깥쪽으로 나가서 찾습니다.

실무에서는 이렇게 여러 단계로 함수를 만들 일은 거의 없습니다. 
그리고 함수마다 이름이 같은 변수를 사용하기 보다는 변수 이름을 다르게 짓는 것이 좋습니다.



#### global로 전역 변수 사용하기

특히, 함수가 몇 단계든 상관없이 global 키워드를 사용하면 무조건 전역 변수를 사용하게 됩니다.

```python
x = 1
def A():
    x = 10
    def B():
        x = 20
        def C():
            global x
            x = x + 30
            print(x)
        C()
    B()
 
A()
```

```
31
```

함수 `C`에서 `global x`를 사용하면 전역 변수 `x = 1`을 사용하게 됩니다. 따라서 `x = x + 30`은 `31`이 나옵니다.

파이썬에서 `global`을 제공하지만 함수에서 값을 주고받을 때는 매개변수와 반환값을 사용하는 것이 좋습니다. 특히 전역 변수는 코드가 복잡해졌을 때 변수의 값을 어디서 바꾸는지 알기가 힘듭니다. 따라서 전역 변수는 가급적이면 사용하지 않는 것을 권장합니다.



## 4. 클로저 사용하기



이제 함수를 클로저 형태로 만드는 방법을 알아보겠습니다. 다음은 함수 바깥쪽에 있는 지역 변수 `a, b`를 사용하여 `a * x + b`를 계산하는 함수 `mul_add`를 만든 뒤에 함수 `mul_add` 자체를 반환합니다.

```python
def calc():
    a = 3
    b = 5
    def mul_add(x):
        return a * x + b    # 함수 바깥쪽에 있는 지역 변수 a, b를 사용하여 계산
    return mul_add          # mul_add 함수를 반환
 
c = calc()
print(c(1), c(2), c(3), c(4), c(5))
```

```
8 11 14 17 20
```



먼저 `calc`에 지역 변수 `a`와 `b`를 만들고 `3`과 `5`를 저장했습니다. 그다음에 함수 `mul_add`에서 `a`와 `b`를 사용하여 `a * x + b`를 계산한 뒤 반환합니다.

```python
def calc():
    a = 3
    b = 5
    def mul_add(x):
        return a * x + b    # 함수 바깥쪽에 있는 지역 변수 a, b를 사용하여 계산
```



함수 `mul_add`를 만든 뒤에는 이 함수를 바로 호출하지 않고 `return`으로 함수 자체를 반환합니다.
(함수를 반환할 때는 함수 이름만 반환해야 하며 `( )`(괄호)를 붙이면 안 됩니다).

```python
return mul_add          # mul_add 함수를 반환
```



이제 클로저를 사용해보겠습니다. 다음과 같이 함수 `calc`를 호출한 뒤 반환값을 `c`에 저장합니다. `calc`에서 `mul_add`를 반환했으므로 `c`에는 함수 `mul_add`가 들어갑니다. 그리고 `c`에 숫자를 넣어서 호출해보면 `a * x + b` 계산식에 따라 값이 출력됩니다.

```python
c = calc()
print(c(1), c(2), c(3), c(4), c(5))    # 8 11 14 17 20
```

잘 보면 함수 `calc`가 끝났는데도 `c`는 `calc`의 지역 변수 `a, b`를 사용해서 계산을 하고 있습니다. 이렇게 **함수를 둘러싼 환경(지역 변수, 코드 등)을 계속 유지하다가, 함수를 호출할 때 다시 꺼내서 사용하는 함수를 클로저(closure)라고 합니다.** **여기서는 `c`에 저장된 함수가 클로저**입니다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20230731184538748.png" alt="image-20230731184538748" style="zoom:60%;" />

이처럼 클로저를 사용하면 프로그램의 흐름을 변수에 저장할 수 있습니다. 즉, **클로저는 지역 변수와 코드를 묶어서 사용하고 싶을 때 활용합니다**. 또한, **클로저에 속한 지역 변수는 바깥에서 직접 접근할 수 없으므로 데이터를 숨기고 싶을 때 활용합니다.**



#### lambda로 클로저 만들기

클로저는 다음과 같이 `lambda`로도 만들 수 있습니다.

```python
def calc():
    a = 3
    b = 5
    return lambda x: a * x + b    # 람다 표현식을 반환
 
c = calc()
print(c(1), c(2), c(3), c(4), c(5))
```

```
8 11 14 17 20
```

`return lambda x: a * x + b`처럼 람다 표현식을 만든 뒤 람다 표현식 자체를 반환했습니다. 이렇게 람다를 사용하면 클로저를 좀 더 간단하게 만들 수 있습니다.

보통 클로저는 람다 표현식과 함께 사용하는 경우가 많아 둘을 혼동하기 쉽습니다. 람다는 이름이 없는 익명 함수를 뜻하고, 클로저는 함수를 둘러싼 환경을 유지했다가 나중에 다시 사용하는 함수를 뜻합니다.



### 클로저의 지역 변수 변경하기

지금까지 클로저의 지역 변수를 가져오기만 했는데, 클로저의 지역 변수를 변경하고 싶다면 nonlocal을 사용하면 됩니다. 다음은 a * x + b의 결과를 함수 calc의 지역 변수 total에 누적합니다.

```python
def calc():
    a = 3
    b = 5
    total = 0
    def mul_add(x):
        nonlocal total
        total = total + a * x + b
        print(total)
    return mul_add
 
c = calc()
c(1)
c(2)
c(3)
```

```
8
19
33
```

지금까지 전역 변수, 지역 변수, 변수의 범위, 클로저에 대해 알아보았습니다. 클로저는 다소 어려운 개념이므로 지금 당장 완벽하게 이해하지 않아도 상관없습니다. 나중에 파이썬에 익숙해지면 자연스럽게 익히게 됩니다.



### 호출 횟수를 세는 함수 만들기



다음 소스 코드를 완성하여 함수 c를 호출할 때마다 호출 횟수가 출력되게 만드세요. 여기서는 함수를 클로저로 만들어야 합니다.

```python
def counter():
    i = 0
    def count():
        nonlocal i
        i += 1
        return i
    return count
        
c = counter()
for i in range(10):
    print(c(), end=' ')
```

함수 `counter`를 호출해서 반환값을 `c`에 저장한 뒤에 `c`를 호출하고 있습니다. 
그리고 `c`를 호출할 때마다 값이 계속 유지되게 하려면 함수를 클로저로 만들어야 합니다.

함수 `counter`에서는 지역 변수 `i`에 `0`이 할당되어 있고, 함수 `count`가 만들어져 있습니다. 따라서 `count`에서 `i`에 `1`을 더한 값을 저장한 뒤 i를 반환합니다. 이때 `nonlocal`을 사용하여 함수 바깥쪽의 지역 변수 `i`를 변경할 수 있도록 만들어야 합니다.

마지막으로 함수 `counter`에서 함수 `count`를 반환하면 됩니다(함수를 반환할 때는 함수 이름만 반환해야 하며 `( )`(괄호)를 붙이면 안 됩니다).



## 5.  핵심정리



**람다 표현식**

람다 표현식은 간단한 식으로 함수를 만들 때 사용합니다. 특히 람다 표현식으로 만든 함수는 이름이 없어서 익명 함수라고 부르기도 합니다. 람다 표현식 자체를 호출하려면 람다 표현식을 `( )`(괄호)로 묶은 뒤 다시 `( )`를 붙이고 인수를 넣어서 호출합니다.

```python
lambda 매개변수1, 매개변수2: 반환값                    # 람다 표현식으로 함수를 만듦
(lambda 매개변수1, 매개변수2: 반환값)(인수1, 인수2)    # 람다 표현식 자체를 호출
 
lambda 매개변수1, 매개변수2: 식1 if 조건식 else 식2    # 람다 표현식에서 조건부 표현식 사용
lambda x: str(x) if x % 3 == 0 else x
 
lambda 매개변수1, 매개변수2: 식1 if 조건식1 else 식2 if 조건식2 else 식3  # if를 여러 개 사용
lambda x: str(x) if x == 1 else float(x) if x == 2 else x + 10
```



**변수의 사용 범위**

전역 변수는 스크립트 전체에서 접근할 수 있으며 지역 변수는 해당 함수 안에서만 접근할 수 있습니다. 
만약 함수 안에서 전역 변수를 사용하려면 `global` 에 변수 이름을 지정해줍니다.

```python
x = 10    # 전역 변수
 
def foo():
    global x   # 전역 변수 x를 사용하겠다고 설정
    y = 10     # foo의 지역 변수
```

만약 전역 변수가 없을 때 함수 안에서 `global`을 사용하면 해당 변수는 전역 변수가 됩니다.



**함수 안에서 함수 사용하기**

파이썬에서는 `def`로 함수를 만들고 다시 `def`로 함수를 만들 수 있습니다.

```python
def 함수이름1():
    코드
    def 함수이름2():
        코드
```



함수 안에 함수를 만들었을 때 안쪽 함수에서 바깥쪽 함수의 지역 변수를 변경하려면 `nonlocal`에 변수 이름을 지정해줍니다.

```python
def A():
    x = 10        # A의 지역 변수 x
    def B():
        nonlocal x    # 현재 함수에서 바깥쪽에 있는 지역 변수를 사용
        x = 20        # A의 지역 변수 x에 20 할당
```



**클로저**

**클로저는 함수를 둘러싼 환경(지역 변수, 코드 등)을 계속 유지하다가 함수를 호출할 때 다시 꺼내서 사용하는 함수**를 뜻합니다. 따라서 클로저는 지역 변수와 코드를 묶어서 사용하고 싶을 때 활용합니다. 또한, 클로저에 속한 지역 변수는 바깥에서 직접 접근할 수 없으므로 데이터를 숨기고 싶을 때 활용합니다.

```python
def calc():    # calc 함수 안에 mul_add 함수를 만듦
    a = 3
    b = 5
    def mul_add(x):
        return a * x + b    # 함수 바깥쪽에 있는 지역 변수 a, b를 사용하여 계산
    return mul_add          # mul_add 함수를 반환
 
c = calc()    # c에 저장된 함수가 클로저
print(c(1), c(2), c(3), c(4), c(5))    # 8 11 14 17 20
```



클로저는 람다 표현식으로도 만들 수 있습니다.

```python
def calc():
    a = 3
    b = 5
    return lambda x: a * x + b    # 람다 표현식을 반환
```



**람다와 클로저**

보통 클로저는 람다와 함께 사용하는 경우가 많아 둘을 혼동하기 쉽습니다. 람다는 이름이 없는 익명 함수를 뜻하고, 클로저는 함수를 둘러싼 환경을 유지했다가 나중에 다시 사용하는 함수를 뜻합니다.





