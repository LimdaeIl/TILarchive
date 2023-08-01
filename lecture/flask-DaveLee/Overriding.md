# Overriding



## 메서드 오버라이딩 사용하기

이번에는 파생 클래스에서 기반 클래스의 메서드를 새로 정의하는 메서드 오버라이딩에 대해 알아보겠습니다. 다음과 같이 `Person`의 `greeting` 메서드가 있는 상태에서 `Student`에도 `greeting` 메서드를 만듭니다.



```python
class Person:
    def greeting(self):
        print('안녕하세요.')
 
class Student(Person):
    def greeting(self):
        print('안녕하세요. 저는 파이썬 코딩 도장 학생입니다.')
 
james = Student()
james.greeting()
```

```
안녕하세요. 저는 파이썬 코딩 도장 학생입니다.
```

`james.greeting()`처럼 `Student`의 `greeting` 메서드를 호출하니 '안녕하세요. 저는 파이썬 코딩 도장 학생입니다.'가 출력되었습니다.

오버라이딩(overriding)은 무시하다, 우선하다라는 뜻을 가지고 있는데 말 그대로 **기반 클래스의 메서드를 무시하고 새로운 메서드를 만든다는 뜻입니다.** 여기서는 `Person` 클래스의 `greeting`메서드를 무시하고 `Student` 클래스에서 새로운 `greeting` 메서드를 만들었습니다.

그럼 메서드 오버라이딩은 왜 사용할까요? 보통 프로그램에서 어떤 기능이 같은 메서드 이름으로 계속 사용되어야 할 때 메서드 오버라이딩을 활용합니다. 만약 `Student` 클래스에서 인사하는 메서드를 `greeting2`로 만들어야 한다면 모든 소스 코드에서 메서드 호출 부분을 `greeting2`로 수정해야겠죠?

다시 `Person` 클래스의 `greeting` 메서드와 `Student` 클래스의 `greeting` 메서드를 보면 '안녕하세요.'라는 문구가 중복됩니다.

```python
    def greeting(self):
        print('안녕하세요.')
    def greeting(self):
        print('안녕하세요. 저는 파이썬 코딩 도장 학생입니다.')
```

이럴 때는 기반 클래스의 메서드를 재활용하면 중복을 줄일 수 있습니다. 다음과 같이 오버라이딩된 메서드에서 `super()`로 기반 클래스의 메서드를 호출해봅니다.



```python
class Person:
    def greeting(self):
        print('안녕하세요.')
 
class Student(Person):
    def greeting(self):
        super().greeting()    # 기반 클래스의 메서드 호출하여 중복을 줄임
        print('저는 파이썬 코딩 도장 학생입니다.')
 
james = Student()
james.greeting()
```

```
안녕하세요.
저는 파이썬 코딩 도장 학생입니다.
```

`Student`의 `greeting`에서 `super().greeting()`으로 `Person`의 `greeting`을 호출했습니다.
 즉, 중복되는 기능은 파생 클래스에서 다시 만들지 않고, 기반 클래스의 기능을 사용하면 됩니다.

이처럼 메서드 오버라이딩은 원래 기능을 유지하면서 새로운 기능을 덧붙일 때 사용합니다.



---



## 다중 상속 사용하기

다중 상속은 여러 기반 클래스로부터 상속을 받아서 파생 클래스를 만드는 방법입니다. 다음과 같이 클래스를 만들 때 `( )`(괄호) 안에 클래스 이름을 `,`(콤마)로 구분해서 넣습니다.

```
class 기반클래스이름1:
    코드
 
class 기반클래스이름2:
    코드
 
class 파생클래스이름(기반클래스이름1, 기반클래스이름2):
    코드
```

그럼 사람 클래스와 대학교 클래스를 만든 뒤 다중 상속으로 대학생 클래스를 만들어보겠습니다.



```python
class Person:
    def greeting(self):
        print('안녕하세요.')
 
class University:
    def manage_credit(self):
        print('학점 관리')
 
class Undergraduate(Person, University):
    def study(self):
        print('공부하기')
 
james = Undergraduate()
james.greeting()         # 안녕하세요.: 기반 클래스 Person의 메서드 호출
james.manage_credit()    # 학점 관리: 기반 클래스 University의 메서드 호출
james.study()            # 공부하기: 파생 클래스 Undergraduate에 추가한 study 메서드
```

```
안녕하세요.
학점 관리
공부하기
```

먼저 기반 클래스 `Person`과 `University`를 만들었습니다. 그다음에 파생 클래스 `Undergraduate`를 만들 때 `class Undergraduate(Person, University):`와 같이 괄호 안에 `Person`과 `University`를 콤마로 구분해서 넣었습니다. 이렇게 하면 두 기반 클래스의 기능을 모두 상속받습니다.

즉, 다음과 같이 `Undergraduate` 클래스의 인스턴스로 `Person`의 `greeting`과 `University`의 `manage_credit`을 호출할 수 있습니다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20230801122144961.png" alt="image-20230801122144961" style="zoom:67%;" />



### 1. 다이아몬드 상속

그럼 조금 복잡한 클래스 상속을 해보겠습니다. 여기서는 편의상 클래스 이름을 A, B, C, D로 만들겠습니다.



```python
class A:
    def greeting(self):
        print('안녕하세요. A입니다.')
 
class B(A):
    def greeting(self):
        print('안녕하세요. B입니다.')
 
class C(A):
    def greeting(self):
        print('안녕하세요. C입니다.')
 
class D(B, C):
    pass
 
x = D()
x.greeting()    # 안녕하세요. B입니다.
```

```
안녕하세요. B입니다.
```

기반 클래스 A가 있고, B, C는 A를 상속받습니다. 그리고 다시 D는 B, C를 상속받습니다. 이 관계를 그림으로 나타내면 다음과 같은 모양이 됩니다.

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20230801122211958.png" alt="image-20230801122211958" style="zoom:67%;" />

클래스 간의 관계가 다이아몬드 같이 생겼죠? 그래서 객체지향 프로그래밍에서는 이런 상속 관계를 다이아몬드 상속이라 부릅니다.

여기서는 클래스 A를 상속받아서 B, C를 만들고, 클래스 B와 C를 상속받아서 D를 만들었습니다. 그리고 A, B, C 모두 greeting이라는 같은 메서드를 가지고 있다면 D는 어떤 클래스의 메서드를 호출해야 할까요? 조금 애매합니다.

프로그래밍에서는 이렇게 명확하지 않고 애매한 상태를 좋아하지 않습니다. 프로그램이 어떨 때는 A의 메서드를 호출하고, 또 어떨 때는 B 또는 C의 메서드를 호출한다면 큰 문제가 생깁니다. 만약 이런 프로그램이 우주선 발사에 쓰인다면 정말 끔찍합니다. 그래서 다이아몬드 상속은 문제가 많다고 해서 죽음의 다이아몬드라고도 부릅니다.



### 2. 메서드 탐색 순서 확인하기

많은 프로그래밍 언어들이 다이아몬드 상속에 대한 해결책을 제시하고 있는데 파이썬에서는 **메서드 탐색 순서(Method Resolution Order, MRO)를 따릅니다.**

다음과 같이 클래스 D에 메서드 mro를 사용해보면 메서드 탐색 순서가 나옵니다(**`클래스.__mro__`** 형식도 같은 내용).

- **클래스.mro()**

```python
y>>> D.mro()
[<class '__main__.D'>, <class '__main__.B'>, <class '__main__.C'>, <class '__main__.A'>, <class 'object'>]
```

MRO에 따르면 D의 메서드 호출 순서는 자기 자신 D, 그 다음이 B입니다. 따라서 D로 인스턴스를 만들고 greeting을 호출하면 B의 greeting이 호출됩니다( D는 greeting 메서드가 없으므로).



```python
x = D()
x.greeting()    # 안녕하세요. B입니다.
```

파이썬은 다중 상속을 한다면 class D(B, C):의 클래스 목록 중 왼쪽에서 오른쪽 순서로 메서드를 찾습니다. 그러므로 같은 메서드가 있다면 B가 우선합니다. 만약 상속 관계가 복잡하게 얽혀 있다면 MRO를 살펴보는 것이 편리합니다.



**참고 |** **object 클래스**

파이썬에서 `object`는 **모든 클래스의 조상**입니다. 그래서 int의 MRO를 출력해보면 int 자기 자신과 object가 출력됩니다.

```
>>> int.mro()
[<class 'int'>, <class 'object'>]
```

파이썬 3에서 모든 클래스는 object 클래스를 상속받으므로 기본적으로 object를 생략합니다. 다음과 같이 클래스를 정의한다면

```python
class X:
    pass
```

괄호 안에 object를 넣은 것과 같습니다.

```python
class X(object):
    pass
```

파이썬 2에서는 class X:가 old-style 클래스를 만들고, class X(object):가 new-style 클래스를 만들었습니다. 그래서 파이썬 2에서는 이 둘을 구분해서 사용해야 했지만, 파이썬 3에서는 old-style 클래스가 삭제되었고 class X:와 class X(object): 모두 new-style 클래스를 만듭니다. 따라서 파이썬 3에서는 괄호 안에 object를 넣어도 되고 넣지 않아도 됩니다.



---



## 추상 클래스 사용하기



파이썬은 추상 클래스(abstract class)라는 기능을 제공합니다. 
**추상 클래스는 메서드의 목록만 가진 클래스이며 상속받는 클래스에서 메서드 구현을 강제하기 위해 사용합니다.**

먼저 추상 클래스를 만들려면 `import`로 `abc` 모듈을 가져와야 합니다( **abc는 abstract base class의 약자입니다**). 그리고 클래스의 `( )`(괄호) 안에 `metaclass=ABCMeta`를 지정하고, 메서드를 만들 때 위에 `@abstractmethod`를 붙여서 추상 메서드로 지정합니다.

```python
from abc import *
 
class 추상클래스이름(metaclass=ABCMeta):
    @abstractmethod
    def 메서드이름(self):
        코드
```

여기서는 `from abc import *`로 abc 모듈의 모든 클래스와 메서드를 가져왔습니다. 만약 `import abc`로 모듈을 가져왔다면 `abc.ABCMeta, @abc.abstractmethod`로 사용해야 합니다(import 사용 방법은 '[44.1 import로 모듈 가져오기](https://dojang.io/mod/page/view.php?id=2441)' 참조).

그럼 학생 추상 클래스 `StudentBase`를 만들고, 이 추상 클래스를 상속받아 학생 클래스 `Student`를 만들어보겠습니다.



```python
from abc import *
 
class StudentBase(metaclass=ABCMeta):
    @abstractmethod
    def study(self):
        pass
 
    @abstractmethod
    def go_to_school(self):
        pass
 
class Student(StudentBase):
    def study(self):
        print('공부하기')
 
james = Student()
james.study()
```

```python
Traceback (most recent call last):
  File "C:\project\class_abc_error.py", line 16, in <module>
    james = Student()
TypeError: Can't instantiate abstract class Student with abstract methods go_to_school 
```

실행을 해보면 에러가 발생합니다. 왜냐하면 추상 클래스 StudentBase에서는 추상 메서드로 study와 go_to_school을 정의했습니다. 하지만 StudentBase를 상속받은 Student에서는 study 메서드만 구현하고, go_to_school 메서드는 구현하지 않았으므로 에러가 발생합니다.

따라서 추상 클래스를 상속받았다면 @abstractmethod가 붙은 추상 메서드를 모두 구현해야 합니다. 다음과 같이 Student에서 go_to_school 메서드도 구현해줍니다.



```
from abc import *
 
class StudentBase(metaclass=ABCMeta):
    @abstractmethod
    def study(self):
        pass
 
    @abstractmethod
    def go_to_school(self):
        pass
 
class Student(StudentBase):
    def study(self):
        print('공부하기')
 
    def go_to_school(self):
        print('학교가기')
 
james = Student()
james.study()
james.go_to_school()
```

```
공부하기
학교가기
```

모든 추상 메서드를 구현하니 실행이 잘 됩니다.

StudentBase는 학생이 반드시 해야 하는 일들을 추상 메서드로 만들었습니다. 그리고 Student에는 추상 클래스 StudentBase의 모든 추상 메서드를 구현하여 학생 클래스를 작성했습니다. 이처럼 추상 클래스는 파생 클래스가 반드시 구현해야 하는 메서드를 정해줄 수 있습니다.

참고로 추상 클래스의 추상 메서드를 모두 구현했는지 확인하는 시점은 파생 클래스가 인스턴스를 만들 때입니다. 따라서 james = Student()에서 확인합니다(구현하지 않았다면 TypeError 발생).

### 36.6.1 추상 메서드를 빈 메서드로 만드는 이유

그리고 또 한 가지 중요한 점이 있는데 추상 클래스는 인스턴스로 만들 수가 없다는 점입니다. 다음과 같이 추상 클래스 StudentBase로 인스턴스를 만들면 에러가 발생합니다.

```
>>> james = StudentBase()
Traceback (most recent call last):
  File "<pyshell#3>", line 1, in <module>
    james = StudentBase()
TypeError: Can't instantiate abstract class StudentBase with abstract methods go_to_school, study
```



그래서 지금까지 추상 메서드를 만들 때 pass만 넣어서 빈 메서드로 만든 것입니다. 왜냐하면 추상 클래스는 인스턴스를 만들 수 없으니 추상 메서드도 호출할 일이 없기 때문이죠.

```
    @abstractmethod
    def study(self):
        pass    # 추상 메서드는 호출할 일이 없으므로 빈 메서드로 만듦
 
    @abstractmethod
    def go_to_school(self):
        pass    # 추상 메서드는 호출할 일이 없으므로 빈 메서드로 만듦
```

정리하자면 추상 클래스는 인스턴스로 만들 때는 사용하지 않으며 오로지 상속에만 사용합니다. 그리고 파생 클래스에서 반드시 구현해야 할 메서드를 정해 줄 때 사용합니다.

지금까지 상속에 대해 알아보았는데 내용이 다소 어려웠습니다. 여기서는 클래스를 상속받는 방법과 메서드 오버라이딩 방법 정도만 기억하면 됩니다. 그리고 상속은 같은 종류이면서 동등한 기능일 때 사용한다는 점이 중요합니다. 다중 상속과 추상 클래스는 나중에 필요할 때 다시 돌아와서 찾아보세요.