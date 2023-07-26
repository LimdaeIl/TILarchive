# PART 3 〈중급〉 SQL 실력 다지기[56-70]

### 056 출력되는 행 제한하기 ①(ROWNUM)

🐇 사원 테이블에서 사원 번호, 이름, 직업, 월급을 상단 5개의 행만 출력하시오.

```sql
SELECT ROWNUM, empno, ename, job, sal
FROM emp
WHERE ROWNUM <= 5;
```



### 057 출력되는 행 제한하기 ②(Simple TOP-n Queries)

🐇 월급이 높은 사원순으로 사원 번호, 이름, 직업, 월급을 4개의 행으로 제한해서 출력하시오.

- 아래의 SQL을 TOP-N Query라고 합니다. 정렬된 결과로부터 위쪽 또는 아래쪽의 N 개의 행을 반환하는 쿼리입니다.  

```sql
SELECT empno, ename, job, sal
FROM emp
ORDER BY sal DESC FETCH FIRST 4 ROWS ONLY;
```



🐇 월급이 높은 사원들 중 20%에 해당하는 사원들만 출력하시오.

```sql
SELECT empno, ename, job, sal
FROM emp
ORDER BY sal DESC
FETCH FIRST 20 PERCENT ROWS ONLY;
```



🐇 `WITH TIES` 옵션을 이용하면 여러 행이 N번째 행의 값과 동일하다면 같이 출력합니다.
      `2 ROWS`를 사용해서 2개의 행이 출력될 것으로 예상되지만, 실제로는 3개의 행이 출력됩니다. 그 이유는 월급 3000이 다른 행과 
	  동일하게 3000 이기 때문입니다.

```sql
SELECT empno, ename, job, sal
FROM emp
ORDER BY sal DESC FETCH FIRST 2 ROWS WITH TIES;
```



🐇 OFFSET 옵션을 이용하면 출력이 시작되는 행의 위치를 지정할 수 있습니다.

```SQL
SELECT empno, ename, job, sal
FROM emp
ORDER BY sal DESC OFFSET 9 ROWS;
```



🐇 `OFFSET`과 `FETCH`를 다음과 같이 서로 조합해서 사용할 수 있습니다.

- `OFFEST` 9로 출력된 5개의 행 중에서 2개의 행만 출력하고 있습니다.

```sql
SELECT empno, ename, job, sal
FROM emp
ORDER BY sal DESC OFFSET 9 ROWS
FETCH FIRST 2 ROWS ONLY;
```



### 058 여러 테이블의 데이터를 조인해서 출력하기 ①(EQUI JOIN)

🐇사원 테이블과 부서 테이블을 조인하여 이름과 부서 위치를 출력하시오.

- 조인 조건이 이퀼(`=`)이면 EQUI JOIN입니다. 

```sql
SELECT ename, loc
FROM emp, dept
WHERE emp.deptno = dept.deptno;
```



🐇 직업이 ANALYST인 사원들만 출력하시오.

- `emp.deptno = dept.deptno`는 조인 조건이며 `emp.job='ANALYST'` 는 검색 조건입니다.

```sql
SELECT ename, loc
FROM emp, dept
WHERE emp.deptno = dept.deptno AND emp.job='ANALYST';
```



### 059 여러 테이블의 데이터를 조인해서 출력하기 ②(NON EQUI JOIN)

🐇 사원 테이블과 급여 등급 테이블을 조인하여 이름, 월급, 급여 등급을 출력하시오.

- emp 테이블과 salgrade 테이블 사이에 동일한 컬럼이 존재하지 않습니다. 
  동일한 컬럼이 없기 때문에 EQUI JOIN을 할 수 없는 경우에는 NON EQUI JOIN 을 사용합니다.
- 비슷한 컬럼인 emp 테이블의 sal 컬럼과 salgrade 테이블의 losal과 hisal 컬럼이 존재합니다.
  emp 테이블의 월급은 salgrade 테이블의 losal과 hisal 사이에 있습니다. 

```sql
SELECT e.ename, e.sal, s.grade
FROM emp e, salgrade s
WHERE e.sal BETWEEN s.losal AND s.hisal;
```





### 060 여러 테이블의 데이터를 조인해서 출력하기 ③(OUTER JOIN)

- BOSTON이 출력되지 않은 이유는 EMP 테이블에 40번 번호 부서가 없어서 DEPT 테이블과 조인이 되지 않았기 때문입니다.
  "BOSTON에는 사원이 배치되지 않았다"는 정보를 한 눈에 확인하려면 OUTER JOIN을 사용해야 합니다. OUTER JOIN은 기존 EQUI JOIN 문법에 OUTER 조인 사인 (+)만 추가한 것입니다.

```sql
SELECT e.ename, d.loc
FROM emp e, dept d
WHERE e.deptno (+) = d.deptno;
```



### 061 여러 테이블의 데이터를 조인해서 출력하기 ④(SELF JOIN)

🐇 사원 테이블 자기 자신의 테이블과 조인하여 이름, 직업, 해당 사원의 관리자 이름과 관리자의 직업을 출력하시오.

```sql
SELECT e.ename as 사원, e.job as 직업, m.ename as 관리자, m.job as 직업
FROM emp e, emp m
WHERE e.mgr = m.empno AND e.job='SALESMAN';
```



### 062 여러 테이블의 데이터를 조인해서 출력하기 ⑤(ON절)

🐇 ON절을 사용한 조인 방법으로 이름과 직업, 월급, 부서 위치를 출력하시오.

```sql
SELECT e.ename as 이름, e.job as 직업, e.sal as 월급, d.loc as 부서_위치
FROM emp e JOIN dept d
ON (e.deptno = d.deptno)
WHERE e.job = 'SALESMAN';
```



- 오라클 EQUI JOIN과 ON절을 사용한 아래의 두 조인은 동일한 결과입니다.

```sql
/* 오라클 EQUI JOIN */
SELECT e.ename, d.loc
FROM emp e, dept d
WHERE e.deptno = d.deptno;

/* ON절을 사용한 조인 */
SELECT e.ename, d.loc
FROM emp e JOIN dept d
ON (e.deptno = d.deptno);
```



- 여러 개의 테이블을 조인할 때 조인 작성법의 차이는 아래와 같습니다.

```sqlite
/* 오라클 EQUI JOIN 그리고 WHERE절 */
SELECT e.ename, d.loc
FROM emp e, dept d, salgrade s
WHERE e.deptno = d.deptno
AND e.sal BETWEEN s.losal AND s.hisal;

/* WHERE절에 작성했던 조인 조건을 ON절에 작성 */
SELECT e.ename, d.loc
FROM emp e JOIN dept d ON (e.deptno = d.deptno)
           JOIN salgrade s ON (e.sal BETWEEN s.losal AND s.hisal);
```



### 063 여러 테이블의 데이터를 조인해서 출력하기 ⑤(USING절)

- WHERE절 대신 USING 절을 사용하여 EMP와 DEPT 테이블을 조인하는 쿼리입니다.
  USING절에는 조인 조건 대신 두 테이블을 연결할 때 사용할 컬럼인 DEPTNO만 기술하면 됩니다. 

```sql
SELECT e.ename as 이름, e.job as 직업, e.sal as 월급, d.loc as 부서_위치
FROM emp e JOIN dept d
USING (deptno)
WHERE e.job='SALESMAN';
```

- deptno 앞에는 테이블명이나 테이블 별칭을 사용할 수 없습니다. 



- EQUI JOIN과 USING절을 사용한 작성법의 차이는 아래와 같습니다.

  ```sql
  /* 오라클 EQUI JOIN */
  SELECT e.ename, d.loc
  FROM emp e, dept d
  WHERE e.deptno = d.deptno;
  
  /* USING절을 사용한 조인 */
  SELECT e.ename, d.loc
  FROM emp e JOIN dept d
  USING (deptno);
  ```

- USING절에는 반드시 괄호를 사용해야 합니다.



- USING절을 사용하여 여러 개의 테이블을 조인하려면 다음과 같이 emp와 조인하는 테이블명 다음에 USING절을 사용하면 됩니다.

  ```sql
  /* 오라클 EQUI JOIN */
  SELECT e.ename, d.loc
  FROM emp e, dept d, salgrade s
  WHERE e.deptno = d.deptno
  AND e.sal BETWEEN s.losal AND s.hisal;
  
  /* USING 절을 사용한 조인 */
  SELECT e.ename, d.loc, s.grade
  FROM emp e JOIN dept d USING (deptno)
             JOIN salgrade s ON (e.sal BETWEEN s.losal AND s.hisal);
  ```

  

### 064 여러 테이블의 데이터를 조인해서 출력하기 ⑥(NATURAL JOIN)

🐇 NATURAL 조인 방법으로 이름, 직업, 월급과 부서 위치를 출력하시오.

- 아래의 예제는 조인 조건을 명시적으로 작성하지 않아도 FROM절에 EMP와 DEPT 사이에 NATURAL JOIN하겠다고 기술하면 조인이 되는 쿼리입니다. 두 테이블에 둘 다 존재하는 동일한 컬럼을 기반으로 암시적인 조인을 수행합니다.
- 둘 다 존재하는 동일한 컬럼인 DEPTNO를 오라클이 알아서 찾아 이를 이용하여 조인을 수행합니다. 이때 다음과 같이 WHERE절에 조건을 기술할 때 조인의 연결고리가 되는 컬럼인 DEPTNO는 테이블명을 테이블 별칭 없이 기술해야 합니다.

```sql
SELECT e.ename as 이름, e.job as 직업, e.sal as 월급, d.loc as 부서_위치
FROM emp e NATURAL JOIN dept d
WHERE e.job = 'SALESMAN';
```



```sql
SELECT e.ename as 이름, e.job as 직업, e.sal as 월급, d.loc as 부서_위치
FROM emp e NATURAL JOIN dept d
WHERE e.job = 'SALESMAN' AND deptno = 30;
```



### 065 여러 테이블의 데이터를 조인해서 출력하기 ⑦(LEFT/RIGHT OUTER JOIN)

🐇 RIGHT OUTER 조인 방법으로 이름, 직업, 월급, 부서 위치를 출력하시오.

- `RIGHT OUTER JOIN`은 EMP와 DPET를 조인할 때 오른쪽의 DEPT 테이블의 데이터는 모두 출력합니다.

```SQL
SELECT e.ename as 이름, e.job as 직업, e.sal as 월급, d.loc as 부서_위치
FROM emp e RIGHT OUTER JOIN dept d
ON (e.deptno = d.deptno);
```



- 오라클의 아우터 조인과 1999 ANSI/ISO 조인의 차이

  ```SQL
  SELECT e.ename, d.loc
  FROM emp e, dept d
  WHERE e.deptno(+) = d.deptno;
  
  SELECT e.ename, d.loc
  FROM emp e RIGHT OUTER JOIN dept d
  ON (e.deptno = d.deptno);
  ```



- 레프트 아우터 조인.

```sql
SELECT e.ename as 이름, e.job as 직업, e.sal as 월급, d.loc as 부서_위치
FROM emp e LEFT OUTER JOIN dept d
ON (e.deptno = d.deptno);
```



### 066 여러 테이블의 데이터를 조인해서 출력하기 ⑧(FULL OUTER JOIN)

```sql
SELECT e.ename as 이름, e.job as 직업, e.sal as 월급, d.loc as 부서_위치
FROM emp e FULL OUTER JOIN dept d
ON (e.deptno = d.deptno);

SELECT e.ename as 이름, e.job as 직업, e.sal as 월급, d.loc as 부서_위치
FROM emp e LEFT OUTER JOIN dept d
ON (e.deptno = d.deptno)
UNION
SELECT e.ename as 이름, e.job as 직업, e.sal as 월급, d.loc as 부서_위치
FROM emp e RIGHT OUTER JOIN dept d
ON (e.deptno = d.deptno);
```



### 집합 연산자는 나중에 정리합니다!

067 집합 연산자로 데이터를 위아래로 연결하기 ①(UNION ALL)
068 집합 연산자로 데이터를 위아래로 연결하기 ②(UNION)
069 집합 연산자로 데이터의 교집합을 출력하기(INTERSECT)
070 집합 연산자로 데이터의 차이를 출력하기(MINUS)



071 서브 쿼리 사용하기 ①(단일행 서브쿼리)
072 서브 쿼리 사용하기 ②(다중 행 서브쿼리)
073 서브 쿼리 사용하기 ③(NOT IN)
074 서브 쿼리 사용하기 ④(EXISTS와 NOT EXISTS)
075 서브 쿼리 사용하기 ⑤(HAVING절의 서브 쿼리)
076 서브 쿼리 사용하기 ⑥(FROM절의 서브 쿼리)
077 서브 쿼리 사용하기 ⑦(SELECT절의 서브 쿼리)
078 데이터 입력하기(INSERT)
079 데이터 수정하기(UPDATE)
080 데이터 삭제하기(DELETE, TRUNCATE, DROP