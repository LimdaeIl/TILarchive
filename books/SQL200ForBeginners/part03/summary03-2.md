# PART 3 〈중급〉 SQL 실력 다지기[71-80]

### 071 서브 쿼리 사용하기 ①(단일행 서브쿼리)

🌱 JONES보다 더 많은 월급을 받는 사원들의 이름과 월급을 출력하시오.

```sql
SELECT ename, sal
FROM emp
WHERE sal > (SELECT sal
            FROM EMP
            WHERE ename ='JONES');
```



🌱 SCOTT과 같은 월급을 받는 사원들의 이름과 월급을 출력하시오.

```sql
SELECT ename, sal
FROM emp
WHERE sal = (SELECT sal
             FROM emp
             WHERE ename = 'SCOTT');
```



🌱 SCOTT이 출력되지 않게 출력하시오.

```sql
SELECT ename, sal
FROM emp
WHERE sal = (SELECT sal
             FROM emp
             WHERE ename = 'SCOTT')
AND ename != 'SCOTT';
```



### 072 서브 쿼리 사용하기 ②(다중 행 서브쿼리)

🌱 SALESMAN인 사원들과 같은 월급을 받는 사원들의 이름과 월급을 출력하시오.

```sql
SELECT ename, sal
FROM emp
WHERE sal IN (SELECT sal
              FROM emp
              WHERE job='SALESMAN');
```



- 서브 쿼리에서 메인 쿼리로 하나의 값이 아니라 여러 개의 값이 반환되는 것을 다중 행 서브 쿼리라고 합니다.

| 종류                | 설명                                                 |
| ------------------- | ---------------------------------------------------- |
| 단일 행 서브 쿼리   | 서브 쿼리에서 메인 쿼리로 하나의 값이 반환됨         |
| 다중 행 서브 쿼리   | 서브 쿼리에서 메인 쿼리로 여러 개의 값이 반환됨      |
| 다중 컬럼 서브 쿼리 | 서브 쿼리에서 메인 쿼리로 여러 개의 컬럼 값이 반환됨 |

- 서브 쿼리의 종류에 따라 사용되는 연산자는 다릅니다.

| 종류              | 연산자                               |
| ----------------- | ------------------------------------ |
| 단일 행 서브 쿼리 | `=, !=, >, <. >=, <=`                |
| 다중 행 서브 쿼리 | `In, Not in, >any, <any, >all, <all` |

- 다중행 서브 쿼리의 연산자에 대한 상세한 정의는 다음과 같습니다.

| 연산자   | 설명                              |
| -------- | --------------------------------- |
| `In`     | 리스트의 값과 동일하다.           |
| `Not in` | 리스트의 값과 동일 하지 않다.     |
| `>all`   | 리스트의 가장 큰 값보다 크다.     |
| `>any`   | 리스트에서 가장 작은 값보다 크다. |
| `<all`   | 리스트에서 가장 작은 값보다 작다. |
| `<any`   | 리스트에서 가장 큰 값보다 작다.   |



### 073 서브 쿼리 사용하기 ③(NOT IN)

🌱 관리자가 아닌 사원들의 이름과 월급과 직업을 출력하시오.

```SQL
SELECT ename, sal, job
FROM emp
WHERE empno not in (SELECT mgr
                   	FROM emp
                    WHERE mgr is not null);
```

- 자기 밑에 직속 부하 사원이 한 명도 없는 사원들을 출력하는 쿼리입니다.



### 074 서브 쿼리 사용하기 ④(EXISTS와 NOT EXISTS)

🌱 부서 테이블에 있는 부선 번호 중에서 사원 테이블에도 존재하는 부서 번호의 부서번호, 부서명, 부서 위치를 출력하시오.

```sql
SELECT *
FROM dept d
WHERE EXISTS (SELECT *
              FROM emp e
              WHERE e.deptno = d.deptno);
```

- 테이블 A에 존재하는 데이터가 테이블 B에 존재하는지 여부를 확인할 때 `EXISTS` 또는 `NOT EXISTS` 를 사용합니다. 위의 예제는 `DEPT` 테이블에 존재하는 부서 번호가 EMP 테이블에도 존재하는지 검색하는 쿼리입니다.
- `WHERE`절 바로 다음에 `EXISTS`문을 작성합니다. 따로 컬럼명을 기술하지 않습니다.
- `e.deptno = d.deptno` 조건에 의하여 emp 테이블의 부서 번호가 dept 테이블에도 존재하는지 검색하게 됩니다.



🌱 DEPT 테이블에는 존재하는 부서 번호인데 EMP 테이블에 존재하지 않는 데이터를 검색할 때는 다음과 같이 NOT EXISTS문을 사용합니다.

```sql
SELECT *
FROM dept d
WHERE NOT EXISTS (SELECT *
                  FROM emp e
                  WHERE e.deptno = d.deptno);
```



### 075 서브 쿼리 사용하기 ⑤(HAVING절의 서브 쿼리)

🌱 직업과 직업별 토탈 월급을 출력하는데, 직업이 SALESMAN인 사원들의 토탈 월급보다 더 큰 값들만 출력하시오.

```sql
SELECT job, SUM(sal)
FROM emp
GROUP BY job
HAVING SUM(sal) > (SELECT SUM(sal)
                   FROM emp
                   WHERE job='SALESMAN');
```

- 직업과 직업별 토탈 월급을 출력하는 메인 쿼리문입니다.
- 그룹 함수로 검색 조건을 작성할 때는 WHERE절을 사용할 수 없어 HAVING절을 사용했습니다.
  HAVING절이 아닌 WHERE절을 사용하게 되면 다음과 같이 에러가 발생합니다.



- SELECT문에서 서브 쿼리문을 사용할 수 있는 절은 다음과 같습니다.

| SELECT문의 6가지 절 | 서브 쿼리 사용 여부 | 서브 쿼리 이름           |
| ------------------- | ------------------- | ------------------------ |
| `SELECT`            | 가능                | 스칼라(Scalar) 서브 쿼리 |
| `FROM`              | 가능                | `IN LINE VIEW`           |
| `WHERE`             | 가능                | 서브 쿼리                |
| `GROUP BY`          | 불가능              |                          |
| `HAVING`            | 가능                | 서브 쿼리                |
| `ORDER BY`          | 가능                | 스칼라(Scalar) 서브 쿼리 |

- SELECT문 6가지 절 중에서 GROUP BY절만 뺴고 전부 서브 쿼리를 사용할 수 있습니다.



### 076 서브 쿼리 사용하기 ⑥(FROM절의 서브 쿼리)

🌱 이름과 월급과 순위를 출력하는데 순위가 1위인 사원만 출력해 보겠습니다.

```sql
SELECT v.ename, v.sal, v.순위
FROM (SELECT ename, sal, RANK() OVER (ORDER BY sal DESC) 순위 FROM emp) v
WHERE v.순위=1;
```

- 위와 같이 FROM절의 서브 쿼리를 in line view 라고 합니다. FROM 절에 이름과 월급, 월급이 높은 순서로 순위를 출력하는 쿼리를 서브 쿼리로 사용했습니다. 서브 쿼리의 별칭을 v로 지정했습니다.



### 077 서브 쿼리 사용하기 ⑦(SELECT절의 서브 쿼리)

🌱 직업이 SALESMAN인 사원들의 이름과 월급을 출력하는데, 직업이 SLAESMAN인 사원들의 최대 월급과 최소 월급도 같이 출력하시오.

```sql
SELECT ename, sal, (SELECT MAX(sal) FROM emp WHERE job='SALESMAN') as 최대_월급,
					(SELECT MIN(sal) FROM emp WHERE job='SALESMAN') as 최소_월급
FROM emp
WHERE job='SALESMAN';
```

- SELECT절의 서브 쿼리는 서브 쿼리가 SELECT절로 확장되었다고 해서 스칼라(Scalar) 서브 쿼리라고 불립니다.
  스칼라 서브 쿼리는 출력되는 행 수 만큼 반복되어 실행됩니다.

| 이름     | 스칼라 서브 쿼리                                        | 최대 | 스칼라 서브 쿼리                                        | 최소 |
| -------- | ------------------------------------------------------- | ---- | ------------------------------------------------------- | ---- |
| `MARTIN` | `SELECT MAX(sal)` <br />`FROM emp WHERE job='SALESMAN'` | 1600 | `SELECT MAX(sal)` <br />`FROM emp WHERE job='SALESMAN'` | 1250 |
| `ALLEN`  | `SELECT MAX(sal)` <br />`FROM emp WHERE job='SALESMAN'` | 1600 | `SELECT MAX(sal)` <br />`FROM emp WHERE job='SALESMAN'` | 1250 |
| `TURNER` | `SELECT MAX(sal)` <br />`FROM emp WHERE job='SALESMAN'` | 1600 | `SELECT MAX(sal)` <br />`FROM emp WHERE job='SALESMAN'` | 1250 |
| `WARD`   | `SELECT MAX(sal)` <br />`FROM emp WHERE job='SALESMAN'` | 1600 | `SELECT MAX(sal)` <br />`FROM emp WHERE job='SALESMAN'` | 1250 |

같은 SQL이 반복되어 4번이나 실행되면서 같은 데이터를 반복해서 출력하므로 성능을 위해 첫번째 행인 MARTIN 행을 출력할 때 작업이 SALESMAN인 사원의 최대 월급과 최소 월급을 메모리에 올려 놓고 두 번째 행인 ALLEN부터는 메모리에 올려놓은 데이터를 출력합니다. 이것을 **서브 쿼리 캐싱(CACHING)**이라고 합니다.



### 078 데이터 입력하기(INSERT)

🌱 사원 테이블에 데이터를 입력하는데 사원 번호 2812, 사원 이름 JACK, 월급 3500, 입사일 2019년 6월 5일, 직업 ANALYST로 해보겠습니다.

```sql
INSERT INTO emp (empno, ename, sal, hiredate, job)
VALUES(2812, 'JACK', 3500, TO_DATE('2019/06/05', 'RRRR/MM/DD'), 'ANALYST')
```



### 079 데이터 수정하기(UPDATE)

🌱 SCOTT의 월급을 3200으로 수정합니다.

```sql
UPDATE emp
SET sal = 3200
WHERE ename = 'SCOTT';
```



### 080 데이터 삭제하기(DELETE, TRUNCATE, DROP)

🌱 사원 테이블에서 SCOTT의 행에서 데이터를 삭제해 보겠습니다.

```SQL
DELETE FROM emp
WHERE ename = 'SCOTT';
```



오라클에서 데이터를 삭제하는 명령어는 세 가지가 있습니다.

| 구분          | `DELETE` | `TRUNCATE` | `DROP` |
| ------------- | -------- | ---------- | ------ |
| 데이터        | 삭제     | 삭제       | 삭제   |
| 저장 공간     | 남김     | 삭제       | 삭제   |
| 저장 구조     | 남김     | 남김       | 삭제   |
| 취소 여부     | 가능     | 불가능     | 불가능 |
| 플래쉬백 여부 | 가능     | 불가능     | 가능   |

- TRUNCATE 명령어는 모든 데이터를 한 번에 삭제합니다. 데이터 삭제 후에는 취소가 불가능하기 때문에 DELETE 보다는 삭제되는 속도가 빠릅니다. 데이터를 모두 지우고 테이블 구조만 남겨두는 것이 TRUNCATE 명령어 입니다.