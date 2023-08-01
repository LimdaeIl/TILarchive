# Class



## 클래스 속성과 정적, 클래스 메서드 사용하기

지금까지 간단하게 클래스를 만들고 속성과 메서드를 사용해봤습니다. 이번에는 클래스에 속해 있는 클래스 속성에 대해 알아보겠습니다. 그리고 인스턴스를 만들지 않고 클래스로 호출하는 정적 메서드와 클래스 메서드도 사용해보겠습니다.



### 1. 클래스 속성과 인스턴스 속성 알아보기

'[34.2 속성 사용하기](https://dojang.io/mod/page/view.php?id=2373)'에서 클래스의 속성을 사용해봤는데, 사실 속성에는 **클래스 속성**과 **인스턴스 속성** 두 가지 종류가 있습니다.
`__init__` 메서드에서 만들었던 속성은 **인스턴스 속성**입니다.



### 2. 클래스 속성 사용하기

그럼 이번에는 클래스 속성을 사용해보겠습니다. 클래스 속성은 다음과 같이 클래스에 바로 속성을 만듭니다.

```python
class 클래스이름:
    속성 = 값
```



이제 간단하게 사람 클래스에 클래스 속성으로 가방 속성을 넣고 사용해보겠습니다. 다음과 같이 `Person` 클래스에 바로 `bag` 속성을 넣고, `put_bag` 메서드를 만듭니다. 그리고 인스턴스 두 개를 만든 뒤 각각 `put_bag` 메서드를 사용합니다.

```python
class Person:
    bag = []
 
    def put_bag(self, stuff):
        self.bag.append(stuff)
 
james = Person()
james.put_bag('책')
 
maria = Person()
maria.put_bag('열쇠')
 
print(james.bag)
print(maria.bag)
```

```
['책', '열쇠']
['책', '열쇠']
```

가방에 물건을 넣는 간단한 동작을 만들었습니다. 그런데 결과가 좀 이상하죠? `james`와 `maria` 인스턴스를 만들고 각자 `put_bag` 메서드로 물건을 넣었는데, `james.bag`과 `maria.bag`을 출력해보면 넣었던 물건이 합쳐져서 나옵니다. 즉, 클래스 속성은 클래스에 속해 있으며 모든 인스턴스에서 공유합니다.



<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20230801104926485.png" alt="image-20230801104926485" style="zoom:67%;" />





`put_bag` 메서드에서 클래스 속성 `bag`에 접근할 때 `self`를 사용했습니다. 
사실 `self`는 현재 인스턴스를 뜻하므로 클래스 속성을 지칭하기에는 조금 모호합니다.

```python
class Person:
    bag = []
 
    def put_bag(self, stuff):
        self.bag.append(stuff)
```



그래서 클래스 속성에 접근할 때는 다음과 같이 **클래스 이름으로 접근하면 좀 더 코드가 명확**해집니다.

- **클래스.속성**

```python
class Person:
    bag = []
 
    def put_bag(self, stuff):
        Person.bag.append(stuff)    # 클래스 이름으로 클래스 속성에 접근
```



`Person.bag`이라고 하니 클래스 `Person`에 속한 `bag` 속성이라는 것을 바로 알 수 있습니다. 
마찬가지로 클래스 바깥에서도 다음과 같이 **클래스 이름으로 클래스 속성에 접근하면 됩니다.**

```python
print(Person.bag)
```



**참고 |** **속성, 메서드 이름을 찾는 순서**

파이썬에서는 속성, 메서드 이름을 찾을 때 **인스턴스, 클래스 순으로 찾습니다.** 그래서 인스턴스 속성이 없으면 클래스 속성을 찾게 되므로 `james.bag, maria.bag`도 문제 없이 동작합니다. 겉보기에는 **인스턴스 속성을 사용하는 것 같지만 실제로는 클래스 속성입니다.**

인스턴스와 클래스에서 `__dict__` 속성을 출력해보면 현재 인스턴스와 클래스의 속성을 딕셔너리로 확인할 수 있습니다.



**인스턴스.`__dict__`**

**클래스.`__dict__`**

```python
>>> james.__dict__
{}
>>> Person.__dict__
mappingproxy({'__module__': '__main__', 'bag': ['책', '열쇠'], 'put_bag': <function Person.put_bag at 0x028A32B8>, '__dict__': <attribute '__dict__' of 'Person' objects>, '__weakref__': <attribute '__weakref__' of 'Person' objects>, '__doc__': None})
```



`james.bag`을 사용했을 때 클래스 속성을 찾는 과정은 다음과 같습니다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20230801105419415.png" alt="image-20230801105419415" style="zoom:67%;" />



### 3. 인스턴스 속성 사용하기

그럼 가방을 여러 사람이 공유하지 않으려면 어떻게 해야 할까요? 그냥 `bag`을 인스턴스 속성으로 만들면 됩니다.

```python
class Person:
    def __init__(self):
        self.bag = []
 
    def put_bag(self, stuff):
        self.bag.append(stuff)
 
james = Person()
james.put_bag('책')
 
maria = Person()
maria.put_bag('열쇠')
 
print(james.bag)
print(maria.bag)
```

```
['책']
['열쇠']
```

`james.bag`과 `maria.bag`을 출력해보면 각자 넣은 물건만 출력됩니다. 
즉, 인스턴스 속성은 인스턴스별로 독립되어 있으며 서로 영향을 주지 않습니다.



이제 클래스 속성과 인스턴스 속성의 차이점을 정리해보겠습니다.

- **클래스 속성**: 모든 인스턴스가 공유. 인스턴스 전체가 사용해야 하는 값을 저장할 때 사용
- **인스턴스 속성**: 인스턴스별로 독립되어 있음. 각 인스턴스가 값을 따로 저장해야 할 때 사용



### 4. 비공개 클래스 속성 사용하기

클래스 속성도 비공개 속성을 만들 수 있습니다. 클래스 속성을 만들 때 `__`속성과 같이` __`(밑줄 두 개)로 시작하면 비공개 속성이 됩니다. 따라서 **클래스 안에서만 접근할 수 있고, 클래스 바깥에서는 접근할 수 없습니다.**

```python
class 클래스이름:
    __속성 = 값    # 비공개 클래스 속성
```



즉, 클래스에서 공개하고 싶지 않은 속성이 있다면 비공개 클래스를 사용해야 합니다. 
예를 들어 기사 게임 캐릭터는 아이템을 최대 10개까지만 보유할 수 있다고 하죠.

```python
class Knight:
    __item_limit = 10    # 비공개 클래스 속성
 
    def print_item_limit(self):
        print(Knight.__item_limit)    # 클래스 안에서만 접근할 수 있음
 
 
x = Knight()
x.print_item_limit()    # 10
 
print(Knight.__item_limit)    # 클래스 바깥에서는 접근할 수 없음
```

```python
10
Traceback (most recent call last):
  File "C:\project\class_private_class_attribute_error.py ", line 11, in <module>
    print(Knight.__item_limit)    # 클래스 바깥에서는 접근할 수 없음
AttributeError: type object 'Knight' has no attribute '__item_limit' 
```

실행을 해보면 클래스 `Knight`의 비공개 클래스 속성 `__item_limit`는 클래스 안의 `print_item_limit` 메서드에서만 접근할 수 있고, 클래스 바깥에서 접근하면 에러가 발생합니다. 아이템의 보유 제한이 10개인데, 이 클래스를 사용하는 사람이 마음대로 `__item_limit = 1000`으로 수정하면 곤란하겠죠?

이처럼 비공개 클래스 속성은 클래스 바깥으로 드러내고 싶지 않은 값에 사용합니다.



**참고 |** **클래스와 메서드의 독스트링 사용하기**

함수와 마찬가지로 클래스와 메서드도 독스트링을 사용할 수 있습니다. 다음과 같이 클래스와 메서드를 만들 때 `:`(콜론) 바로 다음 줄에 `""" """`(큰따옴표 세 개) 또는 `''' '''`(작은따옴표 세 개)로 문자열을 입력하면 됩니다. 그리고 클래스의 독스트링은 **`클래스.__doc__`** 형식으로 사용하고, 메서드의 독스트링은 **`클래스.메서드.__doc__`** 또는 **`인스턴스.메서드.__doc__`** 형식으로 사용합니다.

```python
class Person:
    '''사람 클래스입니다.'''
    
    def greeting(self):
        '''인사 메서드입니다.'''
        print('Hello')
 
print(Person.__doc__)             # 사람 클래스입니다.
print(Person.greeting.__doc__)    # 인사 메서드입니다.
 
maria = Person()
print(maria.greeting.__doc__)     # 인사 메서드입니다.
```



---



## 정적 메서드 사용하기



지금까지 클래스의 메서드를 사용할 때 인스턴스를 통해서 호출했습니다. 
이번에는 **인스턴스를 통하지 않고 클래스에서 바로 호출할 수 있는 정적 메서드와 클래스 메서드**에 대해 알아보겠습니다.

먼저 정적 메서드입니다. 정적 메서드는 다음과 같이 메서드 위에 `@staticmethod`를 붙입니다. 
이때, 정적 메서드는 매개변수에 `self`를 지정하지 않습니다.

```python
class 클래스이름:
    @staticmethod
    def 메서드(매개변수1, 매개변수2):
        코드
```

`@staticmethod`처럼 앞에 `@`이 붙은 것을 **데코레이터라고 하며 메서드(함수)에 추가 기능을 구현할 때 사용합니다.** 

그럼 간단하게 덧셈과 곱셈을 하는 클래스를 만들어보겠습니다.



```python
class Calc:
    @staticmethod
    def add(a, b):
        print(a + b)
 
    @staticmethod
    def mul(a, b):
        print(a * b)
 
Calc.add(10, 20)    # 클래스에서 바로 메서드 호출
Calc.mul(10, 20)    # 클래스에서 바로 메서드 호출
```

```
30
200
```

`Calc` 클래스에서 `@staticmethod`를 붙여서 `add` 메서드와 `mul` 메서드를 만들었습니다. 
정적 메서드를 호출할 때는 다음과 같이 클래스에서 바로 메서드를 호출하면 됩니다.



- **클래스.메서드()**

```python
Calc.add(10, 20)    # 클래스에서 바로 메서드 호출
Calc.mul(10, 20)    # 클래스에서 바로 메서드 호출
```

정적 메서드는 `self`를 받지 않으므로 **인스턴스 속성에는 접근할 수 없습니다.** 
그래서 보통 **정적 메서드는 인스턴스 속성, 인스턴스 메서드가 필요 없을 때 사용**합니다.



여기서 만든 `Calc` 클래스에 들어있는 `add, mul` 메서드는 숫자 두개를 받아서 더하거나 곱할 뿐 인스턴스의 속성은 필요하지 않습니다.

그럼 무엇을 정적 메서드로 만들어야 할까요? 정적 메서드는 **메서드의 실행이 외부 상태에 영향을 끼치지 않는 순수 함수(pure function)를 만들 때 사용합니다.** 순수 함수는 부수 효과(side effect)가 없고 입력 값이 같으면 언제나 같은 출력 값을 반환합니다. 즉, 정적 메서드는 인스턴스의 상태를 변화시키지 않는 메서드를 만들 때 사용합니다.



**참고 |** **파이썬 자료형의 인스턴스 메서드와 정적 메서드**

파이썬의 자료형도 인스턴스 메서드와 정적, 클래스 메서드로 나뉘어져 있습니다. 예를 들어 세트에 요소를 더할 때는 인스턴스 메서드를 사용하고, 합집합을 구할 때는 정적 메서드를 사용하도록 만들어져 있습니다.

```python
>>> a = {1, 2, 3, 4}
>>> a.update({5})    # 인스턴스 메서드
>>> a
{1, 2, 3, 4, 5}
>>> set.union({1, 2, 3, 4}, {5})    # 정적(클래스) 메서드
{1, 2, 3, 4, 5}
```

이처럼 인스턴스의 내용을 변경해야 할 때는 `update`와 같이 인스턴스 메서드로 작성하면 되고, 인스턴스 내용과는 상관없이 결과만 구하면 될 때는 `set.union`과 같이 정적 메서드로 작성하면 됩니다.



---



## 클래스 메서드 사용하기



이번에는 정적 메서드와 비슷하지만 약간의 차이점이 있는 클래스 메서드를 사용해보겠습니다.

클래스 메서드는 다음과 같이 메서드 위에 `@classmethod`를 붙입니다. 이때 클래스 메서드는 첫 번째 매개변수에 `cls`를 지정해야 합니다(`cls`는 `**cl**a**s**s에서 따왔습니다).

```python
class 클래스이름:
    @classmethod
    def 메서드(cls, 매개변수1, 매개변수2):
        코드
```



그럼 사람 클래스 `Person`을 만들고 인스턴스가 몇 개 만들어졌는지 출력하는 메서드를 만들어보겠습니다.

```python
class Person:
    count = 0    # 클래스 속성
 
    def __init__(self):
        Person.count += 1    # 인스턴스가 만들어질 때
                             # 클래스 속성 count에 1을 더함
 
    @classmethod
    def print_count(cls):
        print('{0}명 생성되었습니다.'.format(cls.count))    # cls로 클래스 속성에 접근
 
james = Person()
maria = Person()
 
Person.print_count()    # 2명 생성되었습니다.
2명 생성되었습니다.
```

먼저 인스턴스가 만들어질 때마다 숫자를 세야 하므로 `__init__` 메서드에서 클래스 속성 `count`에 `1`을 더해줍니다. 
물론 클래스 속성에 접근한다는 것을 명확하게 하기 위해 `Person.count`와 같이 만들어줍니다.



이제 `@classmethod`를 붙여서 클래스 메서드를 만듭니다. 클래스 메서드는 첫 번째 매개변수가 `cls`인데 여기에는 현재 클래스가 들어옵니다. 따라서 `cls.count`처럼 `cls`로 클래스 속성 `count`에 접근할 수 있습니다.

```python
    @classmethod
    def print_count(cls):
        print('{0}명 생성되었습니다.'.format(cls.count))    # cls로 클래스 속성에 접근
```



`Person`으로 인스턴스를 두 개 만들었으므로 `print_count`를 호출해보면 '2명 생성되었습니다.'가 출력됩니다.
 물론 `print_count`는 클래스 메서드이므로 `Person.print_count()`처럼 클래스로 호출해줍니다.

```python
james = Person()
maria = Person()
 
Person.print_count()    # 2명 생성되었습니다.
```



클래스 메서드는 **정적 메서드처럼 인스턴스 없이 호출할 수 있다는 점은 같습니다.** 
**하지만 클래스 메서드는 메서드 안에서 클래스 속성, 클래스 메서드에 접근해야 할 때 사용합니다.**

특히 `cls`를 사용하면 메서드 안에서 현재 클래스의 인스턴스를 만들 수도 있습니다. 
즉, `cls`는 클래스이므로 `cls()`는 `Person()`과 같습니다.

```python
    @classmethod
    def create(cls):
        p = cls()    # cls()로 인스턴스 생성
        return p
```



---



## 연습문제: 날짜 클래스 만들기

다음 소스 코드에서 `Date` 클래스를 완성하세요. 
`is_date_valid`는 문자열이 올바른 날짜인지 검사하는 메서드입니다. 날짜에서 월은 12월까지 일은 31일까지 있어야 합니다.

```python
class Date:
                                                                
    ...
                                                                
 
if Date.is_date_valid('2000-10-31'):
    print('올바른 날짜 형식입니다.')
else:
    print('잘못된 날짜 형식입니다.')
```

```
올바른 날짜 형식입니다.
```





정답

```python
class Date:
    
    @staticmethod
    def is_date_valid(date_string):
        year, month, day = map(int, date_string.split('-'))
        return month <= 12 and day <= 31
    
    
if Date.is_date_valid('2000-10-31'):
    print('올바른 날짜 형식입니다.')
else:
    print('잘못된 날짜 형식입니다.')
```

`is_date_valid` 메서드는 `Date.is_date_valid`처럼 호출하고 있지만, 문자열이 올바른 날짜인지 검사만 하면 되고, 클래스에 접근할 필요는 없습니다. 그러므로 정적 메서드로 만듭니다. 먼저 메서드 위에 `@staticmethod`를 붙여준 뒤 첫 번째 매개변수로 날짜 문자열 `date_string`을 지정합니다. 메서드 안에서는 `year, month, day = map(int, date_string.split('-'))`와 같이 `'-'`로 문자열을 분리한 뒤 `int`로 변환해서 각 변수에 넣어줍니다. 그다음에는 `return month <= 12 and day <= 31`과 같이 월이 12 이하이면서 일이 31일 이하인지 검사하고 결과를 반환하도록 만들면 됩니다. 즉, 월, 일 모두 만족하면 `True`가 반환되고 하나라도 만족하지 않으면 `False`가 반환됩니다.





>  표준 입력으로 시:분:초 형식의 시간이 입력됩니다. 다음 소스 코드에서 Time 클래스를 완성하여 시, 분, 초가 출력되게 만드세요. from_string은 문자열로 인스턴스를 만드는 메서드이며 is_time_valid는 문자열이 올바른 시간인지 검사하는 메서드입니다. 시간은 24시까지, 분은 59분까지, 초는 60초까지 있어야 합니다. 정답에 코드를 작성할 때는 class Time:에 맞춰서 들여쓰기를 해주세요.

```python
class Time:
    def __init__(self, hour, minute, second):
        self.hour = hour
        self.minute = minute
        self.second = second
 
    __________________________________________________________
    __________________________________________________________
    __________________________________________________________
    __________________________________________________________
    __________________________________________________________
    __________________________________________________________
    __________________________________________________________
    __________________________________________________________
    __________________________________________________________
 
time_string = input()
 
if Time.is_time_valid(time_string):
    t = Time.from_string(time_string)
    print(t.hour, t.minute, t.second)
else:
    print('잘못된 시간 형식입니다.')
```

```python
예

입력
23:35:59

결과
23 35 59

입력
12:62:43

결과
잘못된 시간 형식입니다.
```





정답

```python
class Time:
    def __init__(self, hour, minute, second):
        self.hour = hour
        self.minute = minute
        self.second = second
    
    @staticmethod
    def is_time_valid(times_string):
        hour, minute, second = map(int, times_string.split(':'))
        return hour <= 24 and minute <= 59 and second <= 60
    
    @classmethod
    def from_string(cls, times_string):
        hour, minute, second = map(int, times_string.split(':'))
        time = cls(hour, minute, second)
        return time
    
 
time_string = input()
 
if Time.is_time_valid(time_string):
    t = Time.from_string(time_string)
    print(t.hour, t.minute, t.second)
else:
    print('잘못된 시간 형식입니다.')
```



---



## 상속 관계와 포함 관계 알아보기

지금까지 기반 클래스를 상속하여 새로운 클래스를 만들어 보았습니다. 그런데 클래스 상속은 정확히 어디에 사용해야 할까요?



### 1. 상속 관계

앞에서 만든 `Student` 클래스는 `Person` 클래스를 상속받아서 만들었습니다.

```python
class Person:
    def greeting(self):
        print('안녕하세요.')
 
class Student(Person):
    def study(self):
        print('공부하기')
```

여기서 학생 `Student`는 사람 `Person`이므로 같은 종류입니다. 이처럼 상속은 명확하게 같은 종류이며 동등한 관계일 때 사용합니다. 즉, "학생은 사람이다."라고 했을 때 말이 되면 동등한 관계입니다. 그래서 상속 관계를 영어로 is-a 관계라고 부릅니다(Student is a Person).



### 2. 포함 관계

하지만 학생 클래스가 아니라 사람 목록을 관리하는 클래스를 만든다면 어떻게 해야 할까요? 
다음과 같이 리스트 속성에 `Person` 인스턴스를 넣어서 관리하면 됩니다.

```python
class Person:
    def greeting(self):
        print('안녕하세요.')
 
class PersonList:
    def __init__(self):
        self.person_list = []    # 리스트 속성에 Person 인스턴스를 넣어서 관리
 
    def append_person(self, person):    # 리스트 속성에 Person 인스턴스를 추가하는 함수
        self.person_list.append(person)
```

여기서는 상속을 사용하지 않고 속성에 인스턴스를 넣어서 관리하므로 `PersonList`가 `Person`을 포함하고 있습니다. 이러면 사람 목록 `PersonList`와 사람 `Person`은 **동등한 관계가 아니라 포함 관계입니다.** 즉, "사람 목록은 사람을 가지고 있다."라고 말할 수 있습니다. 그래서 포함 관계를 영어로 has-a 관계라고 부릅니다(PersonList has a Person).

**정리하자면 같은 종류에 동등한 관계일 때는 상속을 사용하고, 그 이외에는 속성에 인스턴스를 넣는 포함 방식을 사용하면 됩니다.**



---



## 기반 클래스의 속성 사용하기



이번에는 기반 클래스에 들어있는 인스턴스 속성을 사용해보겠습니다. 다음과 같이 `Person` 클래스에 `hello` 속성이 있고, `Person` 클래스를 상속받아 `Student` 클래스를 만듭니다. 그다음에 `Student`로 인스턴스를 만들고 `hello` 속성에 접근해봅니다.

```python
class Person:
    def __init__(self):
        print('Person __init__')
        self.hello = '안녕하세요.'
 
class Student(Person):
    def __init__(self):
        print('Student __init__')
        self.school = '파이썬 코딩 도장'
 
james = Student()
print(james.school)
print(james.hello)    # 기반 클래스의 속성을 출력하려고 하면 에러가 발생함
```

```python
Student __init__
파이썬 코딩 도장
Traceback (most recent call last):
  File "C:\project\class_inheritance_attribute_error.py", line 14, in <module>
    print(james.hello)
AttributeError: 'Student' object has no attribute 'hello' 
```

실행을 해보면 에러가 발생합니다. 왜냐하면 기반 클래스 `Person`의 `__init__ `메서드가 호출되지 않았기 때문입니다. 
실행 결과를 잘 보면 `'Student __init__'`만 출력되었습니다.

즉, `Person`의 `__init__ `메서드가 호출되지 않으면 `self.hello = '안녕하세요.'`도 실행되지 않아서 속성이 만들어지지 않습니다.



### 1. super()로 기반 클래스 초기화하기

이때는 `super()`를 사용해서 기반 클래스의 `__init__` 메서드를 호출해줍니다. 
다음과 같이 `super()` 뒤에 `.`(점)을 붙여서 메서드를 호출하는 방식입니다.

- `super().메서드()`

```python
class Person:
    def __init__(self):
        print('Person __init__')
        self.hello = '안녕하세요.'
 
class Student(Person):
    def __init__(self):
        print('Student __init__')
        super().__init__()                # super()로 기반 클래스의 __init__ 메서드 호출
        self.school = '파이썬 코딩 도장'
 
james = Student()
print(james.school)
print(james.hello)
```

```python
Student __init__
Person __init__
파이썬 코딩 도장
안녕하세요.
```

실행을 해보면 기반 클래스 `Person`의 속성인 `hello`가 잘 출력됩니다. `super().__init__()`와 같이 기반 클래스 `Person`의 `__init__` 메서드를 호출해주면 기반 클래스가 초기화되어서 속성이 만들어집니다. 
실행 결과를 보면 `'Student __init__'과 'Person __init__'`이 모두 출력되었습니다.
기반 클래스 `Person`의 속성 `hello`를 찾는 과정을 그림으로 나타내면 다음과 같은 모양이 됩니다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20230801121428161.png" alt="image-20230801121428161" style="zoom:67%;" />



### 2. 기반 클래스를 초기화하지 않아도 되는 경우

만약 파생 클래스에서 `__init__` 메서드를 생략한다면, 기반 클래스의 `__init__`이 자동으로 호출되므로 `super()`는 사용하지 않아도 됩니다.

```python
class Person:
    def __init__(self):
        print('Person __init__')
        self.hello = '안녕하세요.'
 
class Student(Person):
    pass
 
james = Student()
print(james.hello)
```

```
Person __init__
안녕하세요.
```

이처럼 파생 클래스에 `__init__` 메서드가 없다면 기반 클래스의 `__init__`이 자동으로 호출되므로 기반 클래스의 속성을 사용할 수 있습니다.



**참고 |** **좀 더 명확하게 super 사용하기**

`super`는 다음과 같이 파생 클래스와 `self`를 넣어서 현재 클래스가 어떤 클래스인지 명확하게 표시하는 방법도 있습니다. 물론 `super()`와 기능은 같습니다.

`super(파생클래스, self).메서드`

```python
class Student(Person):
    def __init__(self):
        print('Student __init__')
        super(Student, self).__init__()     # super(파생클래스, self)로 기반 클래스의 메서드 호출
        self.school = '파이썬 코딩 도장'
```