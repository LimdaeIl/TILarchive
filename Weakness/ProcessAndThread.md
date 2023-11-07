# 프로세스와 쓰레드



- 프로세스(process): 실행 중인 프로그램(program)
- 프로그램을 실행하면 OS로부터 실행에 필요한 자원(메모리)을 할당받아 프로세스가 된다.
- 프로세스는 프로그램을 수행하는 데 필요한 데이터와 메모리 등의 자원 그리고 쓰레드로 구성되어 있다.

- 쓰레드: 프로세스의 자원을 이용해서 실제로 작업을 수행하는 것
- 모든 프로세스에는 최소한 하나 이상의 쓰레드가 존재하며, 둘 이상의 쓰레드를 가진 프로세스를 멀티쓰레드 프로세스(multi-threaded process)라고 한다.

\* 쓰레드 = 프로세스라는 작업공간(공장)에서 작업을 처리하는 일꾼(worker)

 

하나의 프로세스가 가질 수 있는 쓰레드의 개수는 제한되어 있지 않으나 쓰레드가 작업을 수행하는데 개별적인 메모리 공간(호출스택)을 필요로 하기 때문에 프로세스의 메모리 한계에 따라 생성할 수 있는 쓰레드의 수가 결정된다.

 

멀티태스킹과 멀티쓰레딩

대부분의 OS는 멀티태스킹(multi-tasking, 다중작업)을 지원하기 때문에 여러 개의 프로세스가 동시에 실행될 수 있다.

멀티쓰레딩은 하나의 프로세스 내에서 여러 쓰레드가 동시에 작업을 수행하는 것이다. CPU의 코어(core)가 한 번에 단 하나의 작업만 수행할 수 있으므로, 실제로 동시에 처리되는 작업의 개수는 코어의 개수와 일치한다. 그러나 처리해야하는 쓰레드의 수는 언제나 코어의 개수보다 훨씬 많기 때문에 각 코어가 아주 짧은 시간 동안 여러 작업을 번갈아 가며 수행함으로써 여러 작업들이 모두 동시에 수행되는 것처럼 보이게 한다.

그래서 프로세스의 성능이 단순히 쓰레드의 개수에 비례하는 것은 아니다.

 

멀티쓰레딩의 장단점

> 멀티쓰레딩의 장점
> \- CPU의 사용률을 향상시킨다.
> \- 자원을 보다 효율적으로 사용할 수 있다.
> \- 사용자에 대한 응답성이 향상된다.
> \- 작업이 분리되어 코드가 간결해진다.
>
> 멀티쓰레딩의 단점
> \- 멀티쓰레드 프로세스는 여러 쓰레드가 같은 프로세스 내에서 자원을 공유하면서 작업을 하기 때문에 발생할 수 있는 동기화(synchronization), 교착상태(deadlock)와 같은 문제들이 발생할 수 있다.

 

\* 교착상태 - 두 쓰레드가 자원을 점유한 상태에서 서로 상대편이 점유한 자원을 사용하려고 기다리느라 진행이 멈춰있는 상태

 

2. 쓰레드의 구현과 실행

쓰레드를 구현하는 방법

\- Thread 클래스 상속

\- Runnable 인터페이스를 구현

 

Thread 클래스를 상속받으면 다른 클래스를 상속받을 수 없기 때문에, Runnable 인터페이스를 구현하는 방법이 일반적이다. 또한 Runnable 인터페이스를 구현하는 방법은 재사용성(reusability)이 높고 코드의 일관성(consistency)을 유지할 수 있기 때문에 보다 객체지향적인 방법이다.

 

```java
//1. Thread 클래스를 상속
class MyThread extends Thread {
	public void run(){}	//Thread 클래스의 run()을 오버라이딩
}

//2. Runnable 인터페이스를 구현
class MyThread implements Runnable {
	public void run(){}	//Runnable 인터페이스의 추상메서드 run()을 구현
}

/*
	Runnable 인터페이스는 run()만 정의되어 있는 간단한 인터페이스이다.
    Runnable 인터페이스를 구현하기 위해서는 추상메서드인 run()의 몸통{}을 만들어 주는 것이다.
*/
public interface Runnable {
	public abstract void run();
}
```

 

Thread 클래스를 상속받은 경우와 Runnable 인터페이스를 구현한 경우의 인스턴스 생성방법이 다르다.

```java
ThreadEx1_1 t1 = new ThreadEx1_1();	//Thread를 상속받은 클래스의 인스턴스를 생성

Runnable r = new ThreadEx1_2();	//Runnable을 구현한 클래스의 인스턴스를 생성
Thread t2 = new Thread(r);	//생성자 Thread(Runnable target)

Thread t2 = new Thread(new ThreadEx1_2());	//위 두 줄
```

 

Runnable 인터페이스를 구현한 경우

Runnable 인터페이스를 구현한 클래스의 인스턴스를 생성한 다음, 이 인스턴스를 Thread클래스의 생성자의 매개변수로 제공해야 한다.

```java
/*
	인스턴스변수로 Runnable 타입의 변수 r을 선언해 놓고
    생성자를 통해서 Runnable 인터페이스를 구현한 인스턴스를 참조
    run()을 호출하면 참조변수 r을 통해서 
    Runnable 인터페이스를 구현한 인스턴스의 run()이 호출된다.
    상속을 통해 run()을 오버라이딩하지 않고도 외부로부터 run()을 제공받을 수 있게 된다.
*/
public class Thread {
	private Runnable r;	//Runnable을 구현한 클래스의 인스턴스를 참조하기 위한 변수를 선언
    
    public Thread(Runnable r) {
    	this.r = r;
    }
    
    public void run() {
    	if (r != null)
        	r.run();	//Runnable 인터페이스를 구현한 인스턴스의 run()을 호출한다.
    }
}
```

 

Thread 클래스를 상속받은 경우, 자손 클래스에서 조상인 Thread 클래스의 메서들르 직접 호출할 수 있지만

Runnable을 구현하면 Thread 클래스의 static메서드인 currentThread()를 호출하여 쓰레드에 대한 참조를 얻어 와야만 호출이 가능하다.

> static Thread currentThread() - 현재 실행중인 쓰레드의 참조를 반환한다.
> String getName() - 쓰레드의 이름을 반환한다.

 

```java
class ThreadEx1_1 extends Thread {
	public void run() {
    	for (int i = 0; i < 5; i++) {
        	//조상인 Thread의 getName()을 호출
            System.out.println(getName());
        }
    }
}

class ThreadEx1_2 implements Runnable {
	public void run() {
    	for (int i = 0; i < 5; i++) {
        	//Thread.currendThread() - 현재 실행중인 Thread를 반환한다.
            System.out.println(Thread.currentThread().getName());
        }
    }
}
```

 

쓰레드의 이름은 다음과 같은 생성자나 메서드를 통해서 지정 또는 변경할 수 있다.

```java
//쓰레드의 이름을 지정하지 않으면 'Thread-번호' 형식으로 이름이 정해진다.
Thread(Runnable target, String name)
Thread(String name)
void setName(String name)

//System.out.println(Thread.currentThread().getName())
Thread t = Thread.currentThread();
String name = t.getName();
System.out.println(name);
```

 

쓰레드의 실행 - start()

쓰레드를 생성한 후 start()를 호출해야만 쓰레드가 실행된다.

```
t1.start();	//쓰레드 t1을 실행시킨다.
t2.start();	//쓰레드 t2을 실행시킨다.
```

start()가 호출되면 일단 실행대기 상태에 있다가 자신의 차례가 되면 실행된다. 실행대기중인 쓰레드가 하나도 없으면 곧바로 실행상태가 된다.

한 번 실행이 종료된 쓰레드는 다시 실행할 수 없다. 즉, 하나의 쓰레드에 대해 start()가 한 번만 호출될 수 있다. 만일 쓰레드의 작업을 한 번 더 수행해야 한다면 새로운 쓰레드를 생성한 다음에 start()를 호출해야 한다. 만일 하나의 쓰레드에 대해 start()를 두 번 이상 호출하면 실행시에 IllegalThreadStateException이 발생한다.

```
ThreadEx1_1 t1 = new ThreadEx1_1();
t1.start();
t1.start();		//예외 발생

ThreadEx1_1 t1 = new ThreadEx1_1();
t1.start()
t1 = new ThreadEx1_1();	//다시 생성
t1.start();	//OK
```