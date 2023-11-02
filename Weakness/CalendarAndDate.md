# 날짜와 시간



## Calendar, Date

- Date는 날짜와 시간을 다룰 목적으로 JDK1.0부터 제공되어온 클래스이며 java.util 패키지 안에 포함되어 있습니다.
- Calendar는 Date 클래스를 보완해 JDK1.1부터 제공되어온 클래스이다.
- java.time 패키지는 Calendar를 보완해 JDK1.8부터 제공되어왔다.



**Calendar와 GregorianCalendar**

Calendar는 추상클래스이기 때문에 직접 객체를 생성할 수 없고, 메서드를 통해 완전히 구현된 클래스의 인스턴스를 얻어야 합니다.

```java
Calendar cal = new Calendar();	//에러. 추상클래스는 인스턴스를 생성할 수 없다.

//getInstance()메서드는 Calendar클래스를 구현한 클래스의 인스턴스를 반환한다.
Calendar cal = Calendar.getInstance();
```

Calendar를 상속받아 완전히 구현한 클래스로는 **GregorianCalendar**와 **BuddhistCalendar**가 있습니다. getInstance()는 시스템의 국가와 지역설정을 확인해서 태국인 경우에는 BuddhistCalendar의 인스턴스를 반환하고, 그 외에는 GregorianCalendar의 인스턴스를 반환합니다.  GregorianCalendar는 Calendar를 상속받아 오늘날 전세계 공통으로 사용하고 있는 그레고리력에 맞게 구현한 것으로 태국을 제외한 나머지 국가에서는 GregorianCalendar를 사용하면 됩니다.

인스턴스를 직접 생성해서 사용하지 않고 이처럼 메서드를 통해서 인스턴스를 반환받게 하는 이유는 최소한의 변경으로 프로그램이 동작할 수 있도록 하기 위한 것입니다.

```java
/*
	다른 종류의 역법(calendar)을 사용하는 국가에서 실행한다던가,
    새로운 역볍이 추가된다던가 하는 경우,
    즉 다른 종류의 인스턴스를 필요로 하는 경우에 MyApplication을 변경해야 한다.
*/
class MyApplication {
	public static void main(String args[]){ 
        Calendar cal = new GregorianCalendar(); 		//경우에 따라 이 부분을 변경해야 한다.
    }
}

/*
	메서드를 통해 인스턴스를 얻어오도록 하면 MyApplication을 변경하지 않아도 된다.
*/
class MyApplication {
	public static void main(String args[]) {
    	Calendar cal = Calendar.getInstance();
    }
}
```

getInstance() 메서드가 static인 이유는 메서드 내의 코드에서 인스턴스 변수를 사용하거나 인스턴스 메서드를 호출하지 않기 때문이며, 또 다른 이유는 getInstance()가 static이 아니라면 객체를 생성한 다음에 호출해야 하는데 Calendar는 추상클래스이기 때문에 객체를 생성할 수 없기 때문입니다.

 

## Date와 Calendar간의 변환

Calendar가 새로 추가되면서 **Date는 대부분의 메서드가 deprecated되었으므로 잘 사용되지 않습니다.** 그럼에도 불구하고 여전히 Date를 필요로 하는 메서드들이 존재하기 때문에 Calendar를 Date로 또는 그 반대로 변환할 일이 생깁니다.

```java
//1. Calendar를 Date로 변환
Calendar cal = Calendar.getInstance();
Date d = new Date(cal.getTimeInMillis());	//Date(long date)

//2. Date를 Calendar로 변환
Date d = new Date();
Calendar cal = Calendar.getInstance();
cal.setTime(d);
```

- getInstance()를 통해서 얻은 인스턴스는 기본적으로 **현재 시스템의 날짜와 시간에 대한 정보**를 담고 있습니다.
- 원하는 날짜나 시간으로 설정하려면 set메서드를 사용합니다.
- get메서드의 매개변수로 사용되는 int값들은 Calendar에 정의된 static상수입니다.

```java
//날짜와 시간을 원하는 값으로 변경
void set(int field, int value)
void set(int year, int month, int date)
void set(int year, int month, int date, int hourOfDay, int minute)
void set(int year, int month, int date, int hourOfDay, int minute, int second)
```

clear()는 모든 필드의 값을, clear(int field)는 지정된 필드의 값을 기본값으로 초기화 합니다.

**add(int field, int amount)** 
지정한 필드의 값을 원한는 만큼 증가 또는 감소시킬 수 있기 때문에 add메서드를 이용하면 특정 날짜 또는 시간을 기점으로 해서 일정 기간 전후의 날짜와 시간을 알아낼 수 있습니다.

**roll(int field, int amount)**
 지정한 필드의 값을 증가 또는 감소시킬 수 있는데, add메서드와의 차이점은 다른 필드에 영향을 미치지 않는다는 것입니다.

**getActualMaximum(Calendar.DATE)**
해당 월의 마지막 날을 알 수 있습니다.



## 형식화 클래스

 java.text 패키지에 포함되어 있으며 숫자, 날짜, 텍스트 데이터를 일정한 형식에 맞게 표현할 수 있는 방법을 객체지향적으로 설계하여 표준화하였습니다. 형식화 클래스는 형식화에 사용될 패턴을 정의하는데, 데이터를 정의된 패턴에 맞춰 형식화 할 수 있을 뿐만 아니라 역으로 형식화된 데이터에서 원래의 데이터를 얻어낼 수도 있습니다. 

형식화된 데이터의 패턴만 정의해주면 복잡한 문자열에서도 substring()을 사용하지 않고 원하는 값을 얻어낼 수 있습니다.

 

## DecimaFormat

DecimalFormat는 형식화 클래스 중에서 숫자를 형식화 하는데 사용합니다. 숫자 데이터를 정수, 부동소수점, 금액 등의 다양한 형식으로 표현할 수 있으며, 반대로 일정한 형식의 텍스트 데이터를 숫자로 변환할 수 있습니다.

![img](https://blog.kakaocdn.net/dn/bDiKJB/btrhN4q1QVp/tlIinVWYCAbIR5zuHW5fu0/img.png)

![img](https://blog.kakaocdn.net/dn/cIGrBo/btrhMeBo2JI/KXBIsZysuR9y28VZk680Y0/img.png)

원하는 출력형식의 패턴을 작성하여 DecimalFormat인스턴스를 생성한 다음, 출력하고자 하는 문자열로 format메서드를 호출하면 원하는 패턴에 맞게 변환된 문자열을 얻게 됩니다.

```java
double number = 1234567.89;
DecimalFormat df = new DecimalFormat("#.#E0");
String result = df.format(number);
```



**parse(String source):**  DecimalFormat의 조상인 NumberFormat에 정의된 메서드입니다.

```java
public Number parse(String source) throws ParseException
```

Number클래스는 Integer, Double과 같은 숫자를 저장하는 래퍼 클래스의 조상이며, doubleValue()는 Number에 저장된 값을 double형의 값으로 변환하여 반환합니다. Number클래스에는 intValue(), floatValue()등의 메서드가 정의되어 있습니다.

 

## SimpleDateFormat

DateFormat은 추상클래스로 SimpleDateFormat의 조상입니다. DateFormat은 추상클래스이므로 인스턴스를 생성하기 위해서는 getDateInstance()와 같은 static메서드를 이용해야 합니다. getDateInstance()에 의해서 반환되는 것은 DateFormat을 상속받아 완전하게 구현한 SimpleDateFormat인스턴스입니다.

![img](https://blog.kakaocdn.net/dn/ucCIf/btrhX1oiOj7/giPeWJojRG2V9XFzE2VB1K/img.png)

원하는 출력형식의 패턴을 작성하여 SimpleDateFormat인스턴스를 생성한 다음, 출력하고자 하는 Date인스턴스를 가지고 format(Date d)를 호출하면 지정한 출력형식에 맞게 변환된 문자열을 얻게 됩니다.

```java
Date today = new Date();
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

//오늘 날짜를 yyyy-MM-dd형태로 변환하여 반환한다.
String result = df.format(today);
```

홑따옴표(')는 escape기호이기 때문에 패턴 내에서 홑따옴표를 표시하기 위해서는 홑따옴표를 연속적으로 두 번 사용해야 합니다.

 

## ChoiceFormat

ChoiceFormat은 특정 범위에 속하는 값을 문자열로 변환해줍니다. 연속적 또는 불연속적인 범위의 값들 처리하는데 있어서 ChoiceFormat을 사용할 수 있습니다.

 

## MessageFormat

MessageFormat는 데이터를 정해진 양식에 맞게 출력할 수 있도록 도와줍니다. 데이터가 들어갈 자리를 마련해 놓은 양식을 미리 작성하고 프로그램을 이용해서 다수의 데이터를 같은 양식으로 출력할 때 사용하면 좋습니다. SimpleDateFormat의 parse처럼 MessageFormat의 parse를 이용하면 지정된 양식에서 필요한 데이터만을 손쉽게 추출해 낼 수 있습니다.



---

# java.time패키지

JDK1.8부터 java.time패키지가 추가되었습니다. 새롭게 추가된 java.time 패키지와 서브 패키지입니다.

| 패키지             | 설명                                                         |
| ------------------ | ------------------------------------------------------------ |
| java.time          | 날짜와 시간을 다루는데 필요한 핵심 클래스들을 제공           |
| java.time.chrono   | 표준(ISO)이 아닌 달력 시스템을 위한 클래스들을 제공          |
| java.time.format   | 날짜와 시간을 파싱하고, 형식화하기 위한 클래스들을 제공      |
| java.time.temporal | 날짜와 시간의 필드(field)와 단위(unit)를 위한 클래스들을 제공 |
| java.time.zone     | 시간대(time-zone)와 관련된 클래스들을 제공                   |

위의 패키지들에 속한 클래스들의 가장 큰 특징은 String클래스처럼 불변(immutable)이라는 것입니다. 날짜나 시간을 변경하는 메서드들은 기존의 객체를 변경하는 대신 항상 변경된 새로운 객체를 반환합니다. 기존 Calendar클래스는 변경 가능하므로, 멀티 쓰레드 환경에서 안전하지 못합니다. 멀티 쓰레드 환경에서는 동시에 여러 쓰레드가 같은 객체에 접근할 수 있기 때문에, 변경 가능한 객체를 데이터가 잘못될 가능성이 있으며, 이를 쓰레드에 안전(thread-safe)하지 않다고 합니다.

 

## java.time 패키지의 핵심 클래스

- Calendar 클래스: 날짜와 시간을 하나로 표현 (날짜 + 시간 + 시간대)
- java.time 패키지: 날짜와 시간을 별도의 클래스로 분리
- LocalTime 클래스: 시간을 표현
- LocalDate 클래스: 날짜를 표현
- LocalDateTime 클래스: 날짜와 시간 모두 표현 (LocalDate + LocalTime)
- ZonedDateTime 클래스: 시간대(time-zone)를 다뤄야 할 경우 (LocalDateTime + 시간대)
- Instant 클래스 : 날짜와 시간을 초 단위(나노초)로 표현 (Date와 유사). 날짜와 시간을 초단위로 표현한 값을 타임스탬프(time-stamp)라고 부르는데, 이 값은 날짜와 시간을 하나의 정수로 표현할 수 있습니다.
- Year 클래스
- YearMonth 클래스
- MonthDay 클래스
- Period와 Duration: 날짜와 시간의 간격을 표현하는 클래스
  - Period : 두 날짜간의 차이를 표현 (날짜 - 날짜)
  - Duration : 시간의 차이를 표현 (시간 - 시간)

 

## 객체 생성하기 - now(), of()

java.time패키지에 속한 클래스의 객체를 생성하는 가장 기본적인 방법은 now()와 of()를 사용하는 것입니다.

**now()** : 현재 날짜와 시간을 저장하는 객체 생성

```java
LocalDate date = LocalDate.now();	//2015-11-23
LocalTime time = LocalTime.now();	//21:54:01.875
LocalDateTime dateTime = LocalDateTime.now();	//2015-11-23T21:54:01.875
ZonedDateTime dateTimeInKr = ZonedDateTime.now();	//2015-11-23T21:54:01.875+09:00[Asia/Seoul]
```



**of()** : 해당 필드의 값을 순서대로 지정. 각 클래스마다 다양한 종류의 of()가 정의되어 있습니다.

```java
LocalDate date = LocalDate.of(2015, 11, 23);	//2015년 11월 23일
LocalTime time = LocalTime.of(23, 59, 59);	//23시 59분 59초

LocalDateTime dateTime = LocalDateTime.of(date, time);
ZonedDateTime zDateTime = ZonedDateTime.of(dateTime, ZoneId.of("Asia/Seoul"));
```

 

## Temporal과 TemporalAmount

LocalDate, LocalTime, LocalDateTime, ZonedDateTime등 날짜와 시간을 표현하기 위한 클래스들은 모두 Temporal, TemporalAccessor, TemporalAdjuster인터페이스를 구현했고, Duration과 Period는 TemporalAmount인터페이스를 구현하였습니다.

> Temporal, TemporalAccessor, TemporalAdjuster를 구현한 클래스
> \- LocalDate, LocalTime, LocalDateTime, ZonedDateTime, Instant 등
>
> TemporalAmount를 구현한 클래스
> \- Period, Duration

 

**TemporalUnit과 TemporalField**

- TemporalUnit인터페이스 : 날짜와 시간의 단위를 정의
- 열거형 ChronoUnit : TemporalUnit인터페이스를 구현
- TemporalField : 년, 월, 일 등 날짜와 시간의 필드를 정의
- 열거형 ChronoField : TemporalField 인터페이스를 구현

```java
LocalTime now = LocalTime.now();	//현재 시간
int minute = now.getMinute();	//현재 시간에서 분(minute)만 뽑아낸다.
//int minute = now.get(ChronoField.MINUTE_OF_HOUR);	//위 문장과 동일
```



- get() 메서드 (또는 get으로 시작하는 이름의 메서드) : 날짜와 시간에서 특정 필드의 값만을 얻을 때 사용

- 특정 날짜와 시간에서 지정된 단위의 값을 더하거나 뺄 경우 plus() 또는 minus()에 값과 함께 열거형 ChronoUnit 사용

```java
LocalDate todday = LocalDate.now();	//오늘
LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);	//오늘에 1일을 더한다.
//LocalDate tomorrow = today.plusDays(1);	//위 문장과 동일
//get() 정의
int get(TemporalField field)

//plus() 정의
LocalDate plus(long amountToAdd, TemporalUnit unit)
```

특정 TemporalField나 TemporalUnit을 사용할 수 있는지 확인하는 메서드 (날짜와 시간을 표현하는 데 사용하는 모든 클래스에 포함되어 있습니다.)

```java
boolean isSupported(TemporalUnit unit)	//Temporal에 정의
boolean isSupported(TemporalField field)	//TemporalAccessor에 정의
```

 

## LocalDate와 LocalTime

LocalDate와 LocalTime은 java.time패키지의 가장 기본이 되는 클래스이며, 나머지 클래스들은 이들의 확장입니다.

객체를 생성하는 방법은 현재의 날짜와 시간을 LocalDate와 LocalTime으로 각각 반환하는 now()와 지정된 날짜와 시간으로 LocalDate와 LocalTime객체를 생성하는 of()가 있다. 둘 다 static메서드입니다.

```java
LocalDate today = LocalDate.now();	//오늘의 날짜
LocalTime now = LocalTime.now();	//현재 시간

LocalDate birthDate = LocalDate.of(1999, 12, 31);	//1999년 12월 31일
LocalTime birthTime = LocalTime.of(23, 59, 59);	//23시 59분 59초
```

 

of()

```java
static LocalDate of(int year, Month month, int dayOfMonth)
static LocalDate of(int year, int month, int dayOfMonth)

static LocalTime of(int hour, int minute)
static LocalTime of(int hour, int minute, int second)
static LocalTime of(int hour, int minute, int second, int nanoOfSecond)
```

 

```java
//일 단위, 초 단위로 지정 가능
//1999년 365번째 날(마지막 날)
LocalDate birthDate = LocalDate.ofYearDay(1999, 365);	//1999년 12월 31일
//0시 0분 0초부터 86399초(하루는 86400초)가 지난 시간(23시 59분 59초)
LocalTime birthTime = LocalTime.ofSecondDay(86399);		//23시 59분 59초
```

 

parse() : 문자열을 날짜와 시간으로 변환

```java
LocalDate birthDate = LocalDate.parse("1999-12-31");	//1999년 12월 31일
LocalTime birthTime = LocalTime.parse("23:59:59");	//23시 59분 59초
```

 

## 특정 필드의 값 가져오기 - get(), getXXX()

- LocalDate와 LocalTime의 객체에서 특정 필드의 값을 가져올 때 사용하는 메서드
- Calendar와 달리 월(month)의 범위가 1~12, 요일은 월요일이 1, ..., 일요일은 7이다.
- Calendar는 1월을 0으로 표현하고, 일요일은 1, ..., 토요일은 7이다.

![img](https://blog.kakaocdn.net/dn/bw659b/btrhX2ASNLD/8xENmvqhG2YO8kkGHRJ58K/img.png)



get()과 getLong() : 원하는 필드를 직접 지정할 수 있습니다.

필드의 범위가 int타입의 범위를 넘을 경우 get() 대신 getLong()을 사용합니다. getLong()을 사용해야 하는 필드는 이름 옆에 '*'표시가 되어 있습니다.

```java
int get(TemporalField field)
long getLong(TemporalField field)
```



![img](https://blog.kakaocdn.net/dn/bb2NIE/btrhUVu8c8n/eXy5FD6M4fGofP5afelHZK/img.png)

![img](https://blog.kakaocdn.net/dn/JEwby/btrhVDgGS7S/mtKuiwlMBDcPP7SqA14V5K/img.png)



사용할 수 있는 필드는 클래스마다 다릅니다. 만일 해당 클래스가 지원하지 않는 필드를 사용하면, UnsupportedTemporalTypeException이 발생합니다.

```java
LocalDate today = LocalDate.now();	//오늘의 날짜
System.out.println(today.get(ChronoField.MINUTE_OF_HOUR));	//예외 발생
```

특정 필드가 가질 수 있는 값의 범위 알아내기

```java
System.out.println(ChronoField.CLOCK_HOUR_OF_DAY.range());	//1-24(밤 12시)
System.out.println(ChronoField.HOUR_OF_DAY.range());	//0(밤 12시)-23
```

 

## 필드의 값 변경하기 - with(), plus(), minus()

날짜와 시간에서 특정 필드 값을 변경하려면, **with**로 시작하는 메서드를 사용합니다.

```java
LocalDate withYear(int year)
LocalDate withMont(int month)
LocalDate withDayOfMonth(int dayOfMonth)
LocalDate withDayOfYear(int dayOfYear)

LocalTime withHour(int hour)
LocalTime withMinute(int minute)
LocalTime withSecond(int second)
LocalTime withNano(int nanoOfSecond)

//with()를 사용하면, 원하는 필드를 직접 지정할 수 있다.
LocalDate with(TemporalField field, long newValue()
```

 

필드를 변경하는 메서드들은 항상 새로운 객체를 생성해서 반환하므로 대입 연산자를 같이 사용해야 합니다.

```java
date = date.withYear(2000);		//년도를 2000년으로 변경
time = time.withHour(12);	//시간을 12시로 변경
```

 

특정필드에 값을 더하거나 빼는 plus(), minus()

```java
LocalTime plus(TemporalAmount amountToAdd)
LocalTime plus(long amountToAdd, TemporalUnit unit)

LocalDate plus(TemporalAmount amountToAdd)
LocalDate plus(long amountToAdd, TemporalUnit unit)

//plus()로 만든 메서드
LocalDate plusYears(long yearsToAdd)
LocalDate plusMonths(long monthsToAdd)
LocalDate plusDays(long daysToAdd)
LocalDate plusWeeks(long weeksToAdd)

LocalTime plusHours(long hoursToAdd)
LocalTime plusMinutes(long minutesToAdd)
LocalTime plusSeconds(long secondsToAdd)
LocalTime plusNanos(long nanosToAdd)
```

 

truncatedTo() : LocalTime의 truncatedTo()는 지정된 필드보다 작은 단위의 필드 값을 0으로 만듭니다.

```java
LocalTime time = LocalTime.of(12, 34, 56);	//12시 34분 56초
time = time.truncatedTo(chronoUnit.HOURS);	//시(hour)보다 작은 단위를 0으로.
System.out.println(time);	//12:00
```

 

LocalDate의 필드인 년, 월, 일은 0이 될 수 없기 대문에 truncatedTo()가 없습니다.

![img](https://blog.kakaocdn.net/dn/bbBmDZ/btrhTMMg0n7/AH49snNLYfgmlx6k78nkk1/img.png)



 

날짜와 시간의 비교 - isAfter(), isBefore(), isEqual()

LocalDate와 LocalTime에는 compareTo()가 오버라이딩되어 있어서 비교가 가능합니다.

```java
int result = date1.compareTo(date2);	//같으면 0, date1이 이전이면 -1, 이후면 1

boolean isAfter(ChronoLocalDate other)
boolean isBefore(ChronoLocalDate other)
boolean isEqual(ChronoLocalDate other)	//LocalDate에만 있음 
```

- equals()가 있는데도, isEqual()을 제공하는 이유는 연표(chronology)가 다른 두 날짜를 비교하기 위해서 입니다.
- 모든 필드가 일치해야하는 equals()와 달리 isEqual()은 오직 날짜만 비교합니다.
- 그래서 대부분의 경우 equals()와 isEqual()의 결과는 같습니다.

```java
LocalDate kDate = LocalDate.of(1999, 12, 31);
JapaneseDate jDate = JapaneseDate.of(1999, 12, 31);

System.out.println(kDate.equals(jDate));	//false 연대가 다름
System.out.println(kDate.isEqual(jDate));	//true
```

 

##  Instant

Instant는 에포크 타임(EPOCH TIME, 1970-01-01 00:00:00 UTC)부터 경과된 시간을 나노초 단위로 표현합니다. 단일진법으로 다루기 때문에 계산에 편리합니다.

```java
//Instant 생성 - now(), ofEpochSecond()
Instant now = Instant.now();
Instant now2 = Instant.ofEpochSecond(now.getEpochSecond());
Instant no3 = Instant.ofEpochSecond(now.getEpochSecond(), now.getNano));

//필드에 저장된 값 가져오기
long epochSec = now.getEpochSecond();
int nano = now.getNano();
```

 

Instant는 시간을 초 단위와 나노초 단위로 나누어 저장합니다. 오라클 데이터베이스의 타임스탬프(timestamp)처럼 밀리초 단위의 EPOCH TIME을 필요로 하는 경우를 위해 toEpochMilli()가 정의되어 있습니다.

```java
long toEpochMilli()
```

 

Instant는 항상 UTC(+00:00)를 기준으로 하기 때문에, LocalTime과 차이가 있을 수 있습니다. **한국은 시간대가 '+09:00'이므로 Instant와 LocalTime간에는 9시간의 차이가 있습니다.** 시간대를 고려해야하는 경우 OffsetDateTime을 사용할 수도 있습니다. UTC는 'Coordinated Universal Time'의 약어로 세계 협정시라고 하며, 1972년 1월 1일부터 시행된 국제 표준시입니다. 이전에 사용되던 GMT(Greenwich Mean Time)와 UTC는 거의 같지만, UTC가 좀 더 정확합니다.

 

## Instant와 Date간의 변환

Instant는 기존의 java.util.Date를 대체하기 위한 것이며, JDK1.8부터 Date에 Instant로 변환할 수 있는 새로운 메서드가 추가되었습니다.

```java
static Date from(Instant instant)	//Instant -> Date
Instant toInstant()		//Date -> Instant
```

 

## LocalDateTime과 ZonedDateTime

- LocalDateTime = LocalDate + LocalTime
- ZonedDateTime = LocalDateTime + 시간대

 

**LocalDate와 LocalTime으로 LocalDateTime 만들기**

```java
LocalDate date = LocalDate.of(2015, 12, 31);
LocalTime time = LocalTime.of(12, 34, 56);

LocalDateTime dt = LocalDateTime.of(date, time);
LocalDateTime dt2 = date.atTime(time);
LocalDateTime dt3 = time.atDate(date);
LocalDateTime dt4 = date.atTime(12, 34, 56);
LocalDateTime dt5 = time.atDate(LocalDate.of(2015, 12, 31));
LocalDateTime dt6 = date.atStartOfDay();	//dt6 = date.atTime(0, 0, 0);
```



**LocalDateTime**

```java
//2015년 12월 31일 12시 34분 56초
LocalDateTime dateTime = LocalDateTime.of(2015, 12,31, 12,34, 56);
LocalDateTime today = LocalDateTime.now();
```

 

**LocalDateTime의 변환**

LocalDateTime을 LocalDate 또는 LocalTime으로 변환할 수 있습니다.

```java
LocalDateTime dt = LocalDateTime.of(2015, 12, 31, 12, 34, 56);
LocalDate date = dt.toLocalDate();	//LocalDateTime -> LocalDate
LocalTime time = dt.toLocalTime();	//LocalDateTime -> LocalTime
```

 

**LocalDateTime으로 ZonedDateTime 만들기**

- LocalDateTime에 시간대(time-zone)를 추가하면, ZonedDateTime이 됩니다.
- 시간대를 다루기 위해 ZoneId라는 클래스를 사용합니다.
- ZoneId는 일광 절약시간(DST, Daylight Saving Time)을 자동적으로 처리해줍니다.

 LocalDate에 시간 정보를 추가하는 atTime()을 쓰면 LocalDateTime을 얻을 수 있는 것처럼, LocalDateTime에 atZone()으로 시간대 정보를 추가하면, ZonedDateTime을 얻을 수 있습니다. 사용가능한 ZoneId의 목록을 ZoneId.getAvailableZoneIds()로 얻을 수 있습니다.

```java
ZoneId zid = ZoneId.of("Asia/Seoul");
ZonedDateTime zdt = dateTime.atZone(zid);
System.out.println(zdt);	//2015-11-27T17:47:50.451+09:00[Asia/Seoul]
```

 

**atStartOfDay() 메서드** : LocalDate의 메서드로, 매개변수로 ZoneId를 지정해 ZonedDateTime을 얻을 수 있습니다. 시간이 0시 0분 0초로 되어 있습니다.

```java
ZonedDateTime zdt = LocalDate.now().atStartOfDay(zid);
System.out.println(zdt);	//2015-11-27T00:00+09:00[Asia/Seoul]

//현재 특정 시간대의 시간
ZoneId nyId = ZoneId.of("America/New_York");
//now() 대신 of()를 사용하면 날짜와 시간을 지정할 수 있다.
ZonedDateTime nyTime = ZonedDateTime.now().withZoneSameInstant(nyId);
```

 

**ZoneOffset**

UTC로부터 얼만큼 떨어져 있는지 ZoneOffSet으로 표현합니다.

```java
//서울은 UTC보다 9시간(32400초=60*60*9)이 빠르다. '+9'
ZoneOffset krOffset = ZonedDateTime.now().getOffset();
//ZoneOffset krOffset = ZoneOffset.of("+9");
int krOffsetInSec = krOffset.get(ChronoField.OFFSET_SECONDS);	//32400초
```

 

**OffsetDateTime**

- ZonedDateTime은 ZoneId로 구역을 표현합니다.
- OffsetDateTime은 ZoneId가 아닌 ZoneOffset을 사용합니다.

ZoneId는 일광절약시간처럼 시간대와 관련된 규칙들을 포함하고 있는데, ZoneOffset은 단지 시간대를 시간의 차이로만 구분합니다. 같은 지역 내의 컴퓨터간에 데이터를 주고받을 때, 전송시간을 표현하기에 LocalDateTime이면 충분하지만, 서로 다른 시간대에 존재하는 컴퓨터간의 통신에는 offsetDateTime이 필요합니다.

```java
ZonedDateTime zdt = ZondedDateTime.of(date, time, zid);
OffsetDateTime odt = OffsetDateTime.of(date, time, krOffset);

//ZonedDateTime -> OffsetDateTime
OffsetDateTime odt = zdt.toOffsetDateTime();
```

- OffsetDateTime = LocalDate + LocalTime + ZoneOffset 
- OffsetDateTime = ZonedDateTime + toOffsetDateTime() 호출

 

**ZonedDateTime의 변환**

날짜와 시간에 관련된 다른 클래스로 변환하는 메서드

```java
LocalDate toLocalDate()
LocalTime toLocalTime()
LocalDateTime toLocalDateTime()
OffsetDateTime toOffsetDateTime()
long toEpochSecond()
Instant toInstant()
```

 

ZonedDateTime은 GregorianCalendar와 유사합니다.

```java
GregorianCalendar from(ZonedDateTime zdt)	//ZonedDateTime -> GregorianCalendar
ZonedDateTime toZonedDateTime()	//GregorianCalendar -> ZonedDateTime
```

 

## TemporalAdjusters

**TemporalAdjusters클래스** : 자주 쓰일만한 날짜 계산들을 대신 해주는 메서드를 정의해놓은 클래스입니다.

```java
//다음 주 월요일의 날짜를 계산하기 위해
//TemporalAdjusters에 정의된 next() 사용
LocalDate today = LocalDate.now();
LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY))
```

![img](https://blog.kakaocdn.net/dn/bd9Jx8/btrhVr2xZRr/QXVZBw80H94r4lYgZyMHP0/img.png)



**TemporalAdjuster직접 구현하기**

**LocalDate의 with()** : TemporalAdjuster인터페이스를 구현한 클래스의 객체를 매개변수로 제공해야 합니다.

```
//LocalDate의 with() 정의
LocalDate with(TemporalAdjuster adjuster)
```

**with()는 LocalTime, LocalDateTime, ZonedDateTime, Instant 등 대부분의 날짜와 시간에 관련된 클래스에 포함**되어 있습니다. TemporalAdjuster인터페이스는 추상 메서드 하나만 정의되어 있으며, adjustInto 메서드만 구현하면 됩니다.

```java
@FunctionalInterface
public interface TemporalAdjuster
{
	Temporal adjustInto(Temporal temporal);
}
```

실제로 구현해야 하는 것은 adjustInto()지만, TemporalAdjuster와 같이 사용해야 하는 메서드는 with()입니다. adjustInto()는 내부적으로 사용할 의도로 작성된 것입니다. 날짜와 시간에 관련된 대부분의 클래스는 Temporal인터페이스를 구현하였으므로 adjustInto()의 매개변수가 될 수 있습니다.

```java
//특정 날짜로부터 2일 후의 날짜를 계산하는 DayAfterTomorrow
class DayAfterTomorrow implements TemporalAdjuster
{
	@Override
    public Temporal adjustInto(Temporal temporal)
    {
    	return temporal.plus(2, ChronoUnit.DAYS);	//2일을 더한다.
    }
}
```

 

##  Period와 Duration

- Period : 날짜의 차이 (날짜 - 날짜)
- Duration : 시간의 차이 (시간 - 시간)
- between() : 두 날짜 date1과 date2의 차이를 나타내는 Period와 시간의 차이를 나타내는 Duration

```java
/* Period */
LocalDate date1 = LocalDate.of(2014, 1, 1);
LocalDate date2 = LocalDate.of(2015, 12, 31);

//date1이 date2보다 날짜 상으로 이전이면 양수, 이후면 음수로 Period에 저장된다.
Period pe = Period.between(date1, date2);

/* Duration */
LocalTime time1 = LocalTime.of(00,00,00);
LocalTime time2 = LocalTime.of(12,34,56);	//12시 34분 56초

Duration du = Duration.between(time1, time2);
```

 

get() : 특정 필드의 값을 얻을 때 사용

```java
long year = pe.get(ChronoUnit.YEARS);	//int getYears()
long month = pe.get(ChronoUnit.MONTHS);	//int getMonths()
long day = pe.get(ChronoUnit.DAYS);	//int getDays()

long sec = du.get(ChronoUnit.SECONDS);	//long getSeconds()
int nano = du.get(ChronoUnit.NANOS);	//int getNano()
```

 

getUnits() : get()에 사용할 수 있는 ChronoUnit의 종류를 확인할 수 있다.

```java
 System.out.println(pe.getUnits());	//[Years, Months, Days]
 System.out.println(du.getUnits());	//[Seconds, Nanos]
```

 

Duration을 LocalTime으로 변환한 다음, LocalTime이 가지고 있는 get메서드 이용하여 특정 필드의 값 얻기

```java
LocalTime tmpTime = LoalTime.of(0,0).plusSeconds(du.getSeconds());

int hour = tmpTime.getHour();
int min = tmpTime.getMinute();
int sec = tmpTime.getSecond();
int nano = du.getNano();
```

 

## between()과 until()

- between() : static 메서드
- until() : 인스턴스 메서드

```java
//Period pe = Period.between(today, myBirthDay);
Period pe = today.until(myBirthDay);
long dday = today.until(myBirthDay, ChronoUnit.DAYS);
```

 

Period는 년월일을 분리해서 저장하기 때문에, D-day를 구할 경우 두 개의 매개변수를 받는 until()을 사용합니다. 날짜가 아닌 시간에도 until()을 사용할 수 있지만, Duration을 반환하는 until()은 없습니다.

```java
long sec = LocalTime.now().until(endTime, ChronoUnit.SECONDS);
```

 

## of(), with()

- Period : of(), ofYears(), ofMonths(), ofWeeks(), ofDays(), withYears()
- Duration : of(), ofDays(), ofHOurs(), ofMinutes(), ofSeconds(), withSeconds()

```java
Period pe = Period.of(1, 12, 31);	//1년 12개월 31일
Duration du = Duration.of(60, ChronoUnit.SECONDS);	//60초
//Duration du = Duration.ofSeconds(60);	//위 문장과 동일

//특정 필드값을 변경하는 with()
pe = pe.withYears(2);	//1년에서 2년으로 변경. withMonths(), withDays()
du = du.withSeconds(120);	//60초에서 120초로 변경. withNanos()
```

 

사칙연산, 비교연산, 기타 메서드

```
pe = pe.minusYears(1).multipliedBy(2);	//1년을 빼고, 2배를 곱한다.
du = du.plusHours(1).dividedBy(60);		//1시간을 더하고 60으로 나눈다.
```

 

- isNegative() : 음수인지 확인
- isZero() : 0인지 확인

```java
boolean sameDate = Period.between(date1, date2).isZero();
boolean isBefore = Duration.between(time1, time2).isNegative();
```

 

- negated() : 부호를 반대로 변경
- abs() : 부호 제거

```java
du = du.abs();
if(du.isNegative())
	du = du.negated();
```

 

normalized() : 월(month)의 값이 12를 넘지 않게 1년 13개월을 2년 1개월로 바꿔준다. 일(day)의 길이는 일정하지 않으므로 그대로 놔둔다.

```java
pe = Period.of(1,13,32).normalized();	//1년 13개월 32일 -> 2년 1개월 32일
```

 

**다른 단위로 변환 - toTotalMonths(), toDays(), toHours(), toMinutes()**

- 이름이 to로 시작하는 메서드들은 Period와 Duration을 다른 단위의 값으로 변환하는데 사용됩니다.
- get()은 특정 필드의 값을 그대로 가져오지만, to로 시작하는 메서드들은 특정 단위로 변환한 결과를 반환합니다.
-  이 메서드들의 반환타입은 모두 정수(long타입)인데, 지정된 단위 이하의 값들은 버려진다는 뜻입니다.

![img](https://blog.kakaocdn.net/dn/bHfBYT/btrh9McY26D/ILKgkjrCaw9njYEcSJtEg0/img.png)



LocalDate의 toEpochDay()는 Epoch Day인 1970-01-01부터 날짜를 세어서 반환합니다.

```java
LocalDate date1 = LocalDate.of(2015, 11, 28);
LocalDate date2 = LocalDate.of(2015, 11, 29);

long period = date2.toEpochDay() - date1.toEpochDay();	//1

//LocalTime의 메서드
int toSecondOfDay()
long toNanoOfDay()
```

 

## 파싱과 포맷

형식화(formatting)와 관련된 클래스들은 java.time.format패키지에 들어있는데, 이 중 핵심은 **DateTimeFormatter**입니다.

```java
LocalDate date = LocalDate.of(2016, 1, 2);
String yyyymmdd = DateTimeFormatter.ISO_LOCAL_DATE.format(date);	//"2016-01-02"
String yyyymmdd = date.format(DateTimeFormatter.ISO_LOCAL_DATE);	//"2016-01-02"
```

format()은 DateTimeFormatter, LocalDate, LocalTime같은 클래스에도 있습니다.

![img](https://blog.kakaocdn.net/dn/dGWqzX/btrh8C9WQHb/Ks73vo3AplKGRnqa42tWrk/img.png)



 

**로케일에 종속된 형식화**

DateTimeFormatter의 static메서드 ofLocalizedDate(), ofLocalizedTime(), ofLocalizedDateTime()은 로케일(locale)에 종속적인 포맷터를 생성합니다.

```java
DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
String shortFormat = formatter.format(LocalDate.now());
```

![img](https://blog.kakaocdn.net/dn/ceF4KU/btriacbBRmd/5hAaI8TH47WTjRd7WlzzY0/img.png)



**출력형식 직접 정의하기**

DateTimeFormatter의 ofPattern() : 원하는 출력형식을 직접 작성

```java
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
```

![img](https://blog.kakaocdn.net/dn/sOIBB/btrh8DOv04T/MZKxsT87valvJGxkHhclT1/img.png)



 

**문자열을 날짜와 시간으로 파싱하기**

- 문자열을 날짜 또는 시간으로 변환하려면 static메서드 parse()를 사용하면 됩니다.
- 날짜와 시간을 표현하는데 사용되는 클래스에는 이 메서드가 거의 다 포함되어 있습니다.

```java
//parse()는 오버로딩된 메서드가 여러 개 있는데, 그 중에서 다음의 2개가 자주 쓰인다.
static LocalDateTime parse(CharSequence text)
static LocalDateTime parse(CharSequence text, DateTimeFormatter formatter)

//DateTimeFormatter에 상수로 정의된 형식을 사용할 때
LocalDate date = LocalDate.parse("2016-01-02", DateTimeFormatter.ISO_LOCAL_DATE);

//자주 사용되는 기본적인 형식의 문자열은 ISO_LOCAL_DATE와 같은 형식화 상수를
//사용하지 않고도 파싱이 가능하다.
LocalDate newDate = LocalDate.parse("2001-01-01");
LocalTime newTime = LocalTime.parse("23:59:59");
LocalDateTime newDateTime = LocalDateTime.parse("2001-01-01T23:59:59");

//ofPattern()을 이용한 파싱
DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
LocalDateTime endOfYear = LocalDateTime.parse("2015-12-31 23:59:59", pattern);
```