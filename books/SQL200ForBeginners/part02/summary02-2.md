# PART 2 〈초급〉 SQL 기초 다지기[31-55]



### 031 날짜형으로 데이터 유형 변환하기(TO_DATE)

🐤81년 11월 17일에 입사한 사원의 이름과 입사일을 출력하시오.

```sql
SELECT ename, hiredate
FROM emp
WHERE hiredate = TO_DATE('81/11/17', 'RR/MM/DD');
```

- `TO_DATE('날짜', '날짜 형식')` : 날짜 형식에 따라 출력



🐤현재 접속한 세션의 날짜 형식을 확인하시오.

```sql
SELECT *
FROM NLS_SESSION_PARAMETERS
WHERE parameter = 'NLS_DATE_FORMAT';
```



🐤현재 접속한 세션의 날짜 형식에 따라 81년 11월 17일에 입사한 사원의 이름과 입사일을 출력하시오.

```SQL
SELECT ename, hiredate
FROM emp
WHERE hiredate = '81/11/17';
```



🐤현재 접속한 세션의 날짜 형식을 `'DD/MM/RR'`으로 변경하고 81년 11월 17일에 입사한 사원의 이름과 입사일을 출력하시오.

- `ALTER SESSION SET` 명령어로 변경한 파라미터 설정은 지금 접속한 세션에서만 유효합니다.

```SQL
ALTER SESSION SET NLS_DATE_FORMAT='DD/MM/RR';

SELECT ename, hiredate
FROM emp
WHERE hiredate = '17/11/81';
```



### 032 암시적 형 변환 이해하기

```sql
SELECT ename, sal
FROM emp
WHERE sal = '3000';
```

- `sql` :arrow_right: 숫자형 데이터 컬럼
- `'3000'` :arrow_right: 문자형 데이터

**`숫자형=문자형`**으로 비교하여 데이터 검색을 수행하면 암시적으로 형 변환하기 때문에 에러가 발생하지 않고 검색됩니다. 
오라클은 문자형과 숫자형 두 개를 비교할 때는 문자형을 숫자형으로 변환합니다. 



```sql
SELECT ename, sal
FROM emp
WHERE TO_NUMBER(sal) = 3000;
```

- `TO_NUMBER('문자열')` :  문자열을 숫자로 변경



### 033 NULL 값 대신 다른 데이터 출력하기(NVL, NVL2)

🐤커미션이 NULL인 사원들은 0으로 출력하시오.

```sql
SELECT ename, comm, NVL(comm, 0)
FROM emp;
```

- `NVL('데이터', '대체값')` : 데이터 안에 NULL 인 경우 대체값으로 변경



🐤이름, 월급, 커미션, 월급+커미션을 출력하시오.

```SQL
SELECT ename, sal, comm, sal+comm
FROM emp
WHERE job IN ('SALESMAN', 'ANALYST');
```



🐤커미션 NULL을 0으로 치환하여 월급+커미션을 출력하시오.

```sql
SELECT ename, sal, comm, NVL(comm, 0), sal+NVL(comm, 0)
FROM emp
WHERE job IN ('SALESMAN', 'ANALYST');
```



🐤NVL2 함수를 이용하여 커미션이 NULL이 아닌 사원들은 sal+comm을 출력하고, NULL인 사원들은 sal을 출력하시오.

```sql
SELECT ename, sal, comm, NVL2(comm, sal+comm, sal)
FROM emp
WHERE job IN ('SALESMAN', 'ANALYST');
```



### 034 IF문을 SQL로 구현하기 ①(DECODE)

🐤부서 번호가 10번이면 300, 부서 번호가 20번이면 400 하고 그 이외는 0 으로 보너스를 출력하시오.

```sql
SELECT ename, deptno, DECODE(deptno, 10, 300, 20, 400, 0) as 보너스 FROM emp;
```



🐤 사원 번호가 짝수 또는 홀수인지 출력하시오.

```SQL
SELECT empno, mod(empno,2), DECODE(mod(empno, 2), 0, '짝수', 1, '홀수') as 보너스 FROM emp;
```



🐤 이름과 작업과 보너스를 출력하는데 직업이 SALESMAN이면 보너스 5000을 출력하고 나머지 작업은 보너스 2000을 출력하시오.

```sql
SELECT ename, job, DECODE(job, 'SALESMAN', 5000, 2000) as 보너스
FROM emp;
```



### 035 IF문을 SQL로 구현하기 ②(CASE)

🐤 이름, 직업, 월급, 보너스를 출력하시오.
      보너스는 월급이 3000 이상이면 500, 2000 이상이면 300, 1000 이상이면 200, 나머지 사원들은 0 을 출력하시오.

```sql
SELECT ename, job, sal, CASE WHEN sal >= 3000 THEN 500
                            WHEN sal >= 2000 THEN 300
                            WHEN sal >= 1000 THEN 200
                            ELSE 0 END AS BONUS
FROM emp
WHERE job IN ('SALESMAN', 'ANALYST');
```

- `CASE`문이 `DECODE`와 차이점
  `DECODE`는 등호(`=`) 비교만 가능하지만, `CASE`는 등호 (`=`) 비교와 부등호(`>=, <=, >, <`) 둘 다 가능합니다.



🐤이름, 직업, 커미션, 보너스를 출력하시오. 보너스는 커미션이 NULL이면 500을 출력하고 NULL이 아니면 0을 출력하시오.

```sql
SELECT ename, job, comm, CASE WHEN comm is null THEN 500
								ELSE 0 END BONUS
FROM emp
WHERE job IN ('SALESMAN', 'ANALYST');
```



🐤 보너스를 출력할 때 직업이 SALESMAN, ANALYST이면 500을 출력하고, 직업이 CLERK, MANAGER이면 400을 출력하고, 
     나머지 직업은 0을 출력하시오.

```sql
SELECT ename, job, CASE WHEN job in ('SALESMAN', 'ANALYST') THEN 500
						WHEN job in ('CLERK', 'MANAGER') THEN 400
					ELSE 0 END as 보너스
FROM emp;
```



### 036 최대값 출력하기(MAX)

🐤 사원 테이블에서 최대 월급을 출력하시오.

```sql
SELECT MAX(sal)
FROM emp;
```



 🐤 직업이 SALESMAN인 사원들 중 최대 월급을 출력하시오.

```sql
SELECT MAX(sal)
FROM emp
WHERE job='SALESMAN';
```



🐤직업과 직업이 SALESMAN인 사원들 중 최대 월급을 출력하시오.(오류 발생)

```sql
SELECT job, MAX(sal)
FROM emp
WHERE job='SALESMAN';

/* ERROR 발생합니다.(단일행 함수 오류) */
```

에러가 발생한 이유는 job 컬럼의 값은 여러 개의 행이 출력되려고 하는데 MAX(SAL) 값은 하나가 출력되려 하기 때문입니다. 그래서 `GROUP BY`절이 필요합니다. `GROUP BY`절은 데이터를 `GROUPING`합니다. `GROUP BY JOB`으로 직업을 그룹핑하면 결과가 잘 출력됩니다.



🐤직업과 직업이 SALESMAN인 사원들 중 최대 월급을 출력하시오.

```sql
SELECT job, MAX(sal)
FROM emp
WHERE job='SALESMAN'
GROUP BY job;
```



🐤 부서 번호와 부서 번호별 최대 월급을 출력하시오.

```sql
SELECT deptno, MAX(sal)
FROM emp
GROUP BY deptno;
```







### 037 최소값 출력하기(MIN)

🐤 직업이 SALESMAN인 사원들 중 최소 월급을 출력하시오.

```SQL
SELECT MIN(sal)
FROM emp
WHERE job='SALESMAN';
```



🐤 직업과 직업별 최소 월급을 출력하는데, `ORDER BY`절 사용하여 최소 월급이 높은 것부터 출력하시오.

```sql
SELECT job, MIN(sal) 최소값
FROM emp
GROUP BY job
ORDER BY 최소값 DESC;
```



🐤 직업, 직업별 최소 월급을 출력하는데, 직업에서 SAELSMAN은 제외하고 출력하고 직업별 최소 월급이 높은것부터 출력하시오.

```sql
select job, MIN(sal)
FROM emp
WHERE job != 'SALESMAN'
GROUP BY job
ORDER BY MIN(sal) DESC;
```



### 038 평균값 출력하기(AVG)

🐤사원 테이블의 평균 월급을 출력하시오.(NULL 튜플은 제외)

- `AVG()` 함수는 NULL 값은 무시하고 평균을 구합니다.

```sql
SELECT AVG(comm)
FROM emp;
```



🐤사원 테이블의 평균 월급을 출력하시오.(NULL 튜플을 0 으로 치환)

```sql
SELECT ROUND(AVG(NVL(comm, 0)))
FROM emp;
```



### 039 토탈값 출력하기(SUM)

🐤부서 번호와 부서 번호별 토탈 월급을 출력하시오.

```sql
SELECT deptno, SUM(sal)
FROM emp
GROUP BY deptno;
```



🐤 직업과 직업별 토탈 월급을 출력하는데, 직업별 토탈 월급이 4000 이상인 것만 출력하시오.(오류 발생)

- 그룹 함수로 조건을 줄 때는 WHERE절 대신 HAVING절을 사용해야 합니다.

```sql
SELECT job, SUM(sal)
FROM emp
WHERE sum(sal) >= 4000
GROUP BY job;

/* ERROR 발생합니다.(단일행 함수 오류) */
```



🐤 직업과 직업별 토탈 월급을 출력하는데, 직업별 토탈 월급이 4000 이상인 것만 출력하시오.

```sql
SELECT job, SUM(sal)
FROM emp
GROUP BY job
HAVING SUM(sal) >= 4000;
```



🐤 직업과 직업별 토탈 월급을 출력하는데 직업에서 SALESMAN은 제외하고, 직업별 토탈 월급이 4000 이상인 사원들을 출력하시오.

```sql
SELECT job, SUM(sal)
FROM emp 
WHERE job != 'SALESMAN'
GROUP BY job
HAVING SUM(sal) >= 4000;
```



### 040 건수 출력하기(COUNT)

 🐤 사원 테이블 전체 사원수를 출력하시오.

- `COUNT()` 함수는 NULL 값은 무시하고 카운트합니다.

```sql
SELECT COUNT(empno)
FROM emp;

SELECT COUNT(*)
FROM emp;
```



### 041 데이터 분석 함수로 순위 출력하기 ①(RANK)

🐤 직업이 ANALYST, MANAGER인 사원들의 이름, 직업, 월급, 월급의 순위를 출력해보시오.

- `RANK() OVER (ORDER BY sal DESC)` 
  1. 순위를 출력하는 데이터 분석 함수
  2. `RANK()` 뒤에 `OVER` 다음에 나오는 괄호 안에 출력하고 싶은 데이터를 정렬하는 SQL 문장을 삽입하면 데이터 순위가 출력
  3. 순위에 대한 중복을 허용

```SQL
SELECT ename, job, sal, RANK() OVER (ORDER BY sal DESC) 순위
FROM emp
WHERE job IN ('ANALYST', 'MANAGER');
```



🐤 직업별로 월급이 높은 순서대로 순위를 부여해서 출력하시오.

- `RANK() OVER (PARTITION BY job ORDER BY sal DESC)`

```sql
SELECT ename, sal, job, RANK() OVER (PARTITION BY job ORDER BY sal DESC) as 순위
FROM emp;
```



### 042 데이터 분석 함수로 순위 출력하기 ②(DENSE_RANK)

🐤 직업이 ANALYST, MANAGER인 사원들의 이름, 직업, 월급, 월급의 순위를 출력하는데 순위 1위인 사원이 두 명이 있을 경우 
      다음 순위가 3위로 출력되지 않고 2위로 출력하시오.

```sql
SELECT ename, job, sal, 
						RANK() OVER (ORDER BY sal DESC) AS RANK, 
						DENSE_RANK() OVER (ORDER BY sal DESC) AS DENSE_RANK
FROM emp
WHERE job IN ('ANALYST', 'MANAGER');
```



🐤 81년도에 입사한 사원들의 직업, 이름, 월급, 순위를 출력하는데, 직업별로 월급이 높은 순서대로 순위를 부여하시오.

```sql
SELECT job, ename, sal, DENSE_RANK() OVER (PARTITION BY job ORDER BY sal DESC) 순위
FROM emp
WHERE hiredate BETWEEN to_date('1981/01/01', 'RRRR/MM/DD')
                    AND to_date('1981/12/31', 'RRRR/MM/DD'); 
```



🐤 `DENSE_RANK` 바로 다음에 나오는 괄호 안에 2975 데이터를 넣고 사용하기.

- 월급이 2975인 사원은 사원 테이블에서 월급의 순위가 어떻게 되는지 출력하는 쿼리입니다.

```sql
SELECT DENSE_RANK(2975) WITHIN GROUP (ORDER BY sal DESC) 순위
FROM emp;
```



🐤 입사일 81년 11월 17일인 사원 테이블 전체 사원들 중 몇 번째로 입사한 것인지 출력하시오.

```sql
SELECT DENSE_RANK('81/11/17') WITHIN GROUP (ORDER BY hiredate ASC) 순위
FROM emp;
```



### 043 데이터 분석 함수로 등급 출력하기(NTILE)

🐤 이름과 월급, 직업, 월급의 등급을 출력하시오. 
      월급의 등급은 4등급으로 나눠 1등급(0~25%), 2등급 (25~50%), 3등급(50~75%), 4등급(75~100%)으로 출력하시오.

```sql
SELECT ename, job, sal, NTILE(4) OVER (ORDER BY sal DESC NULLS LAST) 등급
FROM emp
WHERE job IN ('ANALYST', 'MANAGER', 'CLERK');
```

- 월급이 높은 순서대로 정렬한 결과로 4등분하여 등급을 부여합니다. 
- `ORDER BY sal DESC`에서 `NULLS LAST`는 월급을 높은 것부터 출력되도록 정렬하는데, NULL을 맨 아래에 출력하겠다는 의미입니다.



### 044 데이터 분석 함수로 순위의 비율 출력하기(CUME_DIST)

🐤 이름과 월급, 월급의 순위, 월급의 순위 비율을 출력하시오.

```sql
SELECT ename, sal, RANK() OVER (ORDER BY sal DESC) as RANK,
                   DENSE_RANK() OVER (ORDER BY sal DESC) as DENSE_RANK,
                   CUME_DIST() OVER (ORDER BY sal DESC) as CUM_DIST
FROM emp;
```



🐤 `PARTITION BY` 적용하기.

```sql
SELECT job, ename, sal, RANK() OVER (PARTITION BY job ORDER BY sal DESC) as RANK,
                        CUME_DIST() OVER (PARTITION BY job ORDER BY sal DESC) as CUM_DIST
FROM emp;
```



### 045 데이터 분석 함수로 데이터를 가로로 출력하기(LISTAGG)

🐤 부서 번호를 출력하고, 부서 번호 옆에 해당 부서에 속하는 사원들의 이름을 가로로 출력하시오.

```sql
SELECT deptno, LISTAGG(ename, ',') WITHIN GROUP (ORDER BY ename) AS EMPLOYEE
FROM emp
GROUP BY deptno;
```

- 데이터를 가로로 출력하는 함수입니다. 



🐤 직업과 그 직업에 속한 사원들의 이름을 가로로 출력하시오.

```sql
SELECT job, LISTAGG(ename, ',') WITHIN GROUP (ORDER BY ename ASC) AS EMPLOYEE
FROM emp
GROUP BY job;
```



🐤 이름 옆에 월급도 같이 출력하기 위해 연결 연산자를 작성한다. 각 직업별로 월급의 분포가 어떻게 되는지 한 눈에 출력하시오.

```sql
SELECT job, LISTAGG(ename || '('||sal||')',',') WITHIN GROUP (ORDER BY ename ASC) AS EMPLOYEE
FROM emp
GROUP BY job;
```



### 046 데이터 분석 함수로 바로 전 행과 다음 행 출력하기(LAG, LEAD)

🐤 사원 번호, 이름, 월급을 출력하고 그 옆에 바로 전 행의 월급을 출력하고, 또 옆에 바로 다음 행의 월급을 출력하시오.

```sql
SELECT empno, ename, sal, LAG(sal, 1) OVER (ORDER BY sal ASC) "전 행",
						  LEAD(sal, 1) OVER (ORDER BY sal ASC) "다음 행"
FROM emp
WHERE job IN ('ANALYST', 'MANAGER');
```



### 047 COLUMN을 ROW로 출력하기 ①(SUM+DECODE)

🐤 부서 번호, 부서 번호별 토탈 월급을 출력하는데, 다음과 같이 가로로 출력하시오.

```sql
SELECT SUM(DECODE(deptno, 10, sal)) as "10",
		SUM(DECODE(deptno, 20, sal)) as "20",
		SUM(DECODE(deptno, 30, sal)) as "30"
FROM emp;
```



🐤 직업, 직업별 토탈 월급을 출력하는데 아래처럼 가로로 출력하시오.

- 사원 테이블에 직업이 ANALYST, CLERK, MANAGER, SALESMAN이 있다는 것을 안다는 가정 하에 작성된 쿼리입니다.

```sql
SELECT SUM(DECODE(job, 'ANALYST', sal)) as "ANALYST",
		SUM(DECODE(job, 'CLERK', sal)) as "CLERK",
		SUM(DECODE(job, 'MANAGER', sal)) as "MANAGER",
		SUM(DECODE(job, 'SALESMAN', sal)) as "SALESMAN"
FROM emp;
```



🐤 직업, 직업별 토탈 월급을 출력하는데 아래처럼 가로로 출력하시오.

```SQL
SELECT DEPTNO, SUM(DECODE(job, 'ANALYST', sal)) as "ANALYST",
				SUM(DECODE(job, 'CLERK', sal)) as "CLERK",
				SUM(DECODE(job, 'MANAGER', sal)) as "MANAGER",
				SUM(DECODE(job, 'SALESMAN', sal)) as "SALESMAN"
FROM emp
GROUP BY DEPTNO;
```



## 048 COLUMN을 ROW로 출력하기 ②(PIVOT)

🐤  부서 번호, 부서 번호별 토탈 월급을 PIVOT 문을 사용하여 가로로 출력하시오.

```sql
SELECT *
FROM (SELECT deptno, sal FROM emp)
PIVOT (sum(sal) FOR deptno IN (10, 20, 30));
```



🐤  PIVOT문을 이용해서 직업과 직업별 토탈 월급을 가로로 출력하시오.

```sql
SELECT *
FROM (SELECT job, sal FROM emp)
PIVOT (SUM(sal) FOR job IN ('ANALYST', 'CLERK', 'MANAGER', 'SALESMAN'));
```



### 049 ROW를 COLUMN으로 출력하기(UNPIVOT)

- UNPIVOT문은 PIVOT문과는 반대로 열을 행으로 출력합니다.

```sql
SELECT *
FROM ORDER2
UNPIVOT (건수 FOR 아이템 IN (BICYCLE, CAMERA, NOTEBOOK));
```



### 050 데이터 분석 함수로 누적 데이터 출력하기(SUM OVER)

- `BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW`  는 제일 첫 번째 현재 행까지의 값을 의미합니다.

```sql
SELECT empno, ename, sal, SUM(sal) OVER (ORDER BY empno ROWS 
										 BETWEEN UNBOUNDED PRECEDING
										 AND CURRENT ROW) 누적치
FROM emp
WHERE job IN ('ANALYST', 'MANAGER');
```



### 051 데이터 분석 함수로 비율 출력하기(RATIO_TO_REPORT)

🐤 부서 번호 20번인 사원들의 사원 번호, 이름, 월급을 출력하고, 20번 부서 번호 내에서 자신의 월급 비율이 어떻게 되는지 출력하시오.

```SQL
SELECT empno, ename, sal, RATIO_TO_REPORT(sal) OVER () as 비율
FROM emp
WHERE deptno = 20;
```

```sql
SELECT empno, ename, sal, RATIO_TO_REPORT(sal) OVER () as 비율,
                          SAL/SUM(sal) OVER () as "비교 비율"
FROM emp
WHERE deptno = 20;
```



### 052 데이터 분석 함수로 집계 결과 출력하기 ①(ROLLUP)

🐤 직업과 직업별 토탈 월급을 출력하는데, 맨 마지막 행에 토탈 월급을 출력하시오.

- `ROLLUP()` 을 사용하면 job 컬럼의 토탈 월급과 데이터도 오름차순 되어 가장 아래에 출력됩니다.

```sql
SELECT job, SUM(sal)
FROM emp
GROUP BY ROLLUP(job);
```



### 053 데이터 분석 함수로 집계 결과 출력하기 ②(CUBE)

🐤 직업과 직업별 토탈 월급을 출력하는데, 첫 번째 행에 토탈 월급을 출력하시오.

- `CUBE()`를 job 컬럼의 토탈 우러급과 데이터도 오름차순 되어 가장 위쪽에 출력됩니다.

```sql
SELECT job, SUM(sal)
FROM emp
GROUP BY CUBE(job);
```



### 054 데이터 분석 함수로 집계 결과 출력하기 ③(GROUPING SETS)

🐤 부서 번호와 직업, 부서 번호별 토탈 월급과 직업별 토탈 월급, 전체 토탈 월급을 출력하시오.

```sql
SELECT deptno, job, SUM(sal)
FROM emp
GROUP BY GROUPING SETS((deptno), (job), ());
```



### 055 데이터 분석 함수로 출력 결과 넘버링 하기(ROW_NUMBER)

- `ROW_NUMBER()`는 출력되는 각 행에 고유한 숫자 값을 부여하는 데이터 분석 함수입니다.
  반드시 `OVER` 다음 괄호 안에 반드시 `ORDER BY`절을 기술해야 합니다.

```sql
SELECT empno, ename, sal, RANK() OVER (ORDER BY sal DESC) RANK,
						  DENSE_RANK() OVER (ORDER BY sal DESC) DENSE_RANK,
						  ROW_NUMBER() OVER (ORDER BY sal DESC) 번호
FROM emp
WHERE deptno = 20;
```

