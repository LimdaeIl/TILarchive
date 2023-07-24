# PART 2 〈초급〉 SQL 기초 다지기



### 016 대소문자 변환 함수 배우기(UPPER, LOWER, INITCAP)

```sql
select UPPER(ename), LOWER(ename), INITCAP(ename) from emp;
```

- `UPPER()` :arrow_right: 대문자로 출력
- `LOWER()` :arrow_right: 소문차로 출력
- `ITITCAP` :arrow_right: 첫 번째 철자만 대문자로 출력



**함수(Function)**

- 함수는 다양한 데이터 검색을 위해 필요한 기능

| 함수의 종류 | 설명                                                         |
| ----------- | ------------------------------------------------------------ |
| 단일행 함수 | - 하나의 행을 입력받아 하나의 행을 반환하는 함수<br />- 문자함수, 숫자함수, 날짜함수, 변환함수, 일반함수 |
| 다중행 함수 | - 여러 개의 행을 입력받아 하나의 행을 반환하는 함수<br />- 그룹 함수 |



🐝이름이 `scott`인 사원의 이름과 월급을 조회하는 쿼리를 작성하시오.

```sql
select ename, sal
from emp
where lower(ename)='scott';
```

`LOWER(ename)`을 사용했기 때문에 모든 사원의 이름이 소문자로 변환되어 `scott`을 확실하게 검색할 수 있게 됩니다.



### 017 문자에서 특정 철자 추출하기(SUBSTR)

🐝영어 단어 `SMITH` 에서 `SMI`만 잘라내서 출력하시오.

```sql
SELECT SUBSTR('SMITH', 1, 3) FROM DUAL;
```

- `SUBSTR('문자열', 시작번호, 끝번호)` :arrow_right: 문자열에서 시작번호부터 끝번호까지 문자열을 추출



### 018 문자열의 길이를 출력하기(LENGTH)

🐝 이름을 출력하고 그 옆에 이름의 철자 개수를 출력하시오.

```sql
SELECT ename, LENGTH(ename) FROM emp;
```

- `LENGTH(문자열)` :arrow_right: 문자열의 길이를 출력
- `LENGTHB(문자열)` :arrow_right: 문자열의 바이트 길이를 출력



### 019 문자에서 특정 철자의 위치 출력하기(INSTR)

🐝사원 이름 `SMITH`에서 알파벳 철자 M이 몇 번째 자리에 있는지 출력하시오.

```SQL
SELECT INSTR('SMITH', 'M') FROM DUAL;
```

- `INSTR('문자열', '문자')` :arrow_right: 문자열에서 특정 문자의 위치를 출력



🐝`abcdefg@naver.com` 이메일에서 `naver.com` 만 추출하고 싶다면 `INSTR`과 `SUBSTR` 을 이용하면 추출할 수 있습니다. 먼저 `@`의 위치를 `INSTR`로 추출합니다.

```sql
SELECT SUBSTR('abcdefg@naver.com', INSTR('abcdefg@naver.com','@')+1) FROM DUAL;
```



🐝오른쪽에 `.com`을 잘라내고 `naver`만 출력하기

```sql
SELECT RTRIM(SUBSTR('abcdefg@naver.com', INSTR('abcdefg@naver.com','@')+1), '.com') FROM DUAL;
```





### 020 특정 철자를 다른 철자로 변경하기(REPLACE)

🐝이름과 월급을 출력하는데, 월급을 출력할 때 숫자 `0`을 `*`로 출력하기

```sql
SELECT ename, REPLACE(sal, 0, '*') FROM emp;
```

- `REPLACE('문자열', '기존 문자', '대체 문자')` :arrow_right: 특정 문자를 다른 문자로 변경



🐝월급의 숫자 0~3까지를 `*`로 출력하기

```sql
SELECT ename, REGEXP_REPLACE(sal, '[0-3]', '*') as SALARY
FROM emp;
```

- `REGEXP_REPLACE()` :arrow_right: 정규식 함수



### 🐜정규식(Regular Expression) 함수

```sql
REGEXP_REPLACE (source_char, pattern
                         [, replace_string
                         [, position
                         [, occurrence
                         [, match_param[[[
)
```

- 첫번째 인수 `Source_char` 
  :arrow_right:  원본데이터, 컬럼명이나, 문자열이 올수 있다.

- 두번쨰 인수 `pattern` 
  :arrow_right: 찾고자 하는 패턴을 의미

- 세번째 인수 `replace_string` 
  :arrow_right: 변환하고자 하는 형태

- 네번째 인수 `position` 
  :arrow_right: 검색 시작 위치를 지정, 아무런 값도 주지 않을 경우 기본값 : 1

- 다섯 번째 인수 `occurrence` 
  :arrow_right: 패턴과 일치가 발생하는 횟수를 의미, `0`은 모든 값을 대체, 다른 `n`이란 숫자를 주면 `n`번째 발생하는 문자열을 대체

- 여섯 번째 인수 `match_parameter`
  :arrow_right: 기본값으로 검색되는 옵션을 바꿀수 있다.
  `- c `: 대소문자를 구분해서 검색
  `- i` : 대소푼자를 구분하지 않고 검색
  `- m` : 검색 조건을 여러 줄로 줄 수 있음

  `- c`와 `i`가 중복으로 설정되면 마지막에 설정된 값을 사용. 예를 들어서 `ic`가 중복으로 설정되면  `c` 옵션 적용







### 021 특정 철자를 N개 만큼 채우기(LPAD, RPAD)

🐝이름과 월급을 출력하는데 월급 컬럼의 자릿수를 10자리로 하고, 월급을 출력하고 남은 나머지 자리에 `*`를 채워 출력하시오.
(**LPAD = LEFT PADDING**)

```sql
SELECT ename, LPAD(sal, 10, '*') as salary1, RPAD(sal, 10, '*') as salary2 FROM emp;
```

- `LPAD('문자열', '문자열 길이', '대체 문자')` :arrow_right: 왼쪽부터 `대체 문자`로 채워서 출력
- `RPAD('문자열', '문자열 길이', '대체 문자')` :arrow_right: 오른쪽부터 `대체 문자`로 채워서 출력



🐝이름과 월급을 출력하는데 월급 100을 `◼` 하나로 출력하시오.

```sql
SELECT ename, sal, LPAD('◼', round(sal/100), '◼') as bar_chart FROM emp;
```



### 022 특정 철자 잘라내기(TRIM, RTRIM, LTRIM)

🐝`smith` 철자를 출력하고 `s, h` 잘라서 출력하고, 양쪽에 `s`를 잘라서 출력하시오.

```sql
SELECT 'smith', LTRIM('smith', 's'), RTRIM('smith', 'h'), TRIM('s' from 'smiths') FROM dual;
```

- `LTRIM('문자열', '문자')` : 왼쪽에서 특정 문자 잘라서 출력
- `RTRIM('문자열', '문자')` : 오른쪽에서 특정 문자 잘라서 출력
- `TRIM('문자' from '문자열')` : 문자열의 시작과 마지막에서 특정 문자 잘라서 출력



🐝사원 이름 오른쪽에 있는 공백을 제거하고 검색하기

```sql
SELECT ename, sal
FROM emp
WHERE RTRIM(ename)='JACK';
```



### 023 반올림해서 출력하기(ROUND)

🐝 876.567 숫자를 출력하는데 소수점 두 번째 자리인 6에서 반올림해서 출력하시오.

```sql
SELECT '876.567' as 숫자, ROUND(876.567, 1) FROM dual;
```



### 024 숫자를 버리고 출력하기(TRUNC)

🐝876.567 숫자를 출력하는데 소수점 두 번째 자리인 6과 그 이후의 숫자들을 모두 버리고 출력하시오. (반올림 X)
(**trunc = truncate**)

```sql
SELECT '876.567' as 숫자, TRUNC(876.567, 1) FROM dual;
```



### 025 나눈 나머지 값 출력하기(MOD)

🐝숫자 10을 3으로 나눈 나머지값이 어떻게 되는지 출력하시오.

```sql
SELECT MOD(10, 3) FROM DUAL;
```



🐝사원 번호가 홀수이면 1, 짝수이면 0을 출력하시오.

```sql
SELECT empno, MOD(empno, 2) FROM emp;
```



🐝사원 번호가 짝수인 사원들의 사원 번호와 이름을  출력하시오.

```sql
SELECT empno, ename
FROM emp
WHERE MOD(empno, 2) = 0;
```



🐝10을 3으로 나눈 몫을 출력하시오.

```sql
SELECT FLOOR(10/3) FROM DUAL;
```



### 026 날짜 간 개월 수 출력하기(MONTHS_BETWEN)

- `sysdate` :arrow_right: 오늘 날짜를 확인하는 함수
- `MONTHS_BETWEEN(최신 날짜, 예전 날짜)` :arrow_right: 날짜를 다루는 함수로 날짜 값을 입력받아 숫자 값을 출력

🐝입사한 날짜부터 오늘까지 총 몇 달을 근무했는지 출력하시오.

```sql
SELECT ename, MONTHS_BETWEEN(sysdate, hiredate) FROM emp;
```



🐝`MONTHS_BETWEEN()` 함수를 이용하지 않고 `2023-07-24`부터 `2022-07-24`까지 총 일(day)수를 출력하시오.

```SQL
SELECT TO_DATE('2023-07-24', 'RRRR-MM-DD') - TO_DATE('2022-07-24', 'RRRR-MM-DD')
FROM dual;
```

- `TO_DATE('연도-월-일','RRRR-MM-DD')` :arrow_right: 연도(RRRR), 달(MM), 일(DD)을 출력



🐝`MONTHS_BETWEEN()` 함수를 이용하지 않고 `2023-07-24`부터 `2022-07-24`까지 총 주(week)수를 출력하시오.

```sql
SELECT ROUND((TO_DATE('2023-07-24', 'RRRR-MM-DD') - TO_DATE('2022-07-24', 'RRRR-MM-DD')) / 7) AS "총 주수"
FROM dual;
```



🐝 `2023-07-24`부터 `2022-07-24`까지 총 월(Month)수를 출력하시오.

- 월마다 일(day) 수가 다르다는 특징 때문에 `MONTHS_BETWEEN()` 함수만으로 정확한 월수를 계산할 수 있습니다.

```sql
SELECT MONTHS_BETWEEN('2023-07-24', '2022-07-24') FROM dual;
```



### 027 개월 수 더한 날짜 출력하기(ADD_MONTHS)

🐝`2023-07-24`일부터 100일 후에 돌아오는 날짜를 출력하시오.

```sql
SELECT TO_DATE('2023-07-24', 'RRRR-MM-DD') + 100
FROM dual;
```



🐝`2023-07-24`일부터 100달 뒤의 날짜는 어떻게 되는지 출력하시오.

- 달(Month)을 기준으로 날짜를 더할 때에는 `ADD_MONTHS()` 혹은 `interval` 함수를 사용

```sql
SELECT ADD_MONTHS(TO_DATE('2023-07-24', 'RRRR-MM-DD'), 100) FROM DUAL;
SELECT TO_DATE('2023-07-24', 'RRRR-MM-DD') + interval '100' month FROM DUAL;
```

- `ADD_MONTHS('날짜', '더할 달(Month)수')` : 날짜에 달수를 더한 값을 출력
- `interval` : 섬세한 날짜 산술 연산 수



🐝`2023-07-24`부터 1년 3개월 후의 날짜를 출력하시오.

```sql
SELECT TO_DATE('2023-07-24', 'RRRR-MM-DD') + interval '1-3' year(1) to month FROM DUAL;
```

`INTERVAL '1-3' YEAR(1) TO MONTH`: 이것은 INTERVAL 리터럴입니다. 시간 간격, 특히 1년 3개월을 나타냅니다. 
형식은 `'년-월'`입니다. YEAR 뒤의 `(1)`은 YEAR 부분의 정밀도를 나타내며 연도 값에 한 자리만 허용됨을 지정합니다. 
따라서 '1-3'은 1년 3개월을 의미합니다.



1. year(1) 정밀도는 YEAR 부분이 한 자리만 가질 수 있음을 의미합니다. 이 경우 연도에 허용되는 숫자는 '1'뿐입니다.
   예제와 함께 이것이 어떻게 작동하는지 봅시다:

예 1:

```sql
sqlCopy code
INTERVAL '1-3' YEAR(1) TO MONTH
```

여기서 간격은 '1-3'으로 1년 3개월을 의미합니다.

예 2:

```
sqlCopy code
INTERVAL '12-6' YEAR(1) TO MONTH
```

이 예에서 간격은 '12-6'이며 12년 6개월을 나타냅니다. 그러나 정밀도는 연도에 대해 한 자리로 제한되므로 '12'의 첫 번째 숫자('1')만 고려하고 간격은 1년 6개월을 나타내는 '1-6'으로 해석됩니다.

예 3:

```sql
sqlCopy code
INTERVAL '25-9' YEAR(1) TO MONTH
```

이 경우 간격은 '25-9'로 25년 9개월을 나타냅니다. 다시 한 번 정밀도는 연도에 한 자리만 허용하므로 '25'의 첫 번째 숫자('2')만 고려하고 간격은 2년 9개월을 나타내는 '2-9'로 해석됩니다.

year(2), year(3) 등과 같은 다른 정밀도를 사용하는 경우 INTERVAL 리터럴에서 연도 부분에 두 자리, 세 자리 또는 그 이상의 숫자를 사용할 수 있습니다.

예를 들어, year(2) 정밀도를 사용하면 다음과 같습니다.

```sql
sqlCopy code
INTERVAL '12-6' YEAR(2) TO MONTH
```

이 경우 '12-6' 간격은 연도에 대해 정밀도가 두 자리를 허용하므로 12년 6개월을 나타냅니다.

요약하면, INTERVAL 데이터 유형의 연도(1) 정밀도는 연도 부분이 하나의 숫자만 가질 수 있고 정밀도의 다른 숫자(연도(2), 연도(3) 등)가 INTERVAL 리터럴의 연도 부분에 대한 각 자릿수를 허용함을 의미합니다.



🐝`2023-07-24` 부터 3년 후의 날짜를 반환하시오.

```sql
SELECT TO_DATE('2023-07-24', 'RRRR-MM-DD') + interval '3' year FROM DUAL;
```



🐝`2023-07-24` 부터 3년 5개월 후의 날짜를 출력하시오.

```SQL
SELECT TO_DATE('2023-07-24', 'RRRR-MM-DD') + TO_YMINTERVAL('03-05') as 날짜 FROM dual;
```





028 특정 날짜 뒤에 오는 요일 날짜 출력하기(NEXT_DAY)

029 특정 날짜가 있는 달의 마지막 날짜 출력하기(LAST_DAY)

030 문자형으로 데이터 유형 변환하기(TO_CHAR)

031 날짜형으로 데이터 유형 변환하기(TO_DATE)

032 암시적 형 변환 이해하기

033 NULL 값 대신 다른 데이터 출력하기(NVL, NVL2)

034 IF문을 SQL로 구현하기 ①(DECODE)

035 IF문을 SQL로 구현하기 ②(CASE)

036 최대값 출력하기(MAX)

037 최소값 출력하기(MIN)

038 평균값 출력하기(AVG)

039 토탈값 출력하기(SUM)

040 건수 출력하기(COUNT)

041 데이터 분석 함수로 순위 출력하기 ①(RANK)

042 데이터 분석 함수로 순위 출력하기 ②(DENSE_RANK)

043 데이터 분석 함수로 등급 출력하기(NTILE)

044 데이터 분석 함수로 순위의 비율 출력하기(CUME_DIST)

045 데이터 분석 함수로 데이터를 가로로 출력하기(LISTAGG)

046 데이터 분석 함수로 바로 전 행과 다음 행 출력하기(LAG, LEAD)

047 COLUMN을 ROW로 출력하기 ①(SUM+DECODE)

048 COLUMN을 ROW로 출력하기 ②(PIVOT)

049 ROW를 COLUMN으로 출력하기(UNPIVOT)

050 데이터 분석 함수로 누적 데이터 출력하기(SUM OVER)

051 데이터 분석 함수로 비율 출력하기(RATIO_TO_REPORT)

052 데이터 분석 함수로 집계 결과 출력하기 ①(ROLLUP)

053 데이터 분석 함수로 집계 결과 출력하기 ②(CUBE)

054 데이터 분석 함수로 집계 결과 출력하기 ③(GROUPING SETS)

055 데이터 분석 함수로 출력 결과 넘버링 하기(ROW_NUMBER)