# PART 3 〈중급〉 SQL 실력 다지기[71-80]

### 071 서브 쿼리 사용하기 ①(단일행 서브쿼리)

```sql
SELECT ename, sal
FROM emp
WHERE sal > (SELECT sal
            FROM EMP
            WHERE ename ='JONES');
```





072 서브 쿼리 사용하기 ②(다중 행 서브쿼리)
073 서브 쿼리 사용하기 ③(NOT IN)
074 서브 쿼리 사용하기 ④(EXISTS와 NOT EXISTS)
075 서브 쿼리 사용하기 ⑤(HAVING절의 서브 쿼리)
076 서브 쿼리 사용하기 ⑥(FROM절의 서브 쿼리)
077 서브 쿼리 사용하기 ⑦(SELECT절의 서브 쿼리)
078 데이터 입력하기(INSERT)
079 데이터 수정하기(UPDATE)
080 데이터 삭제하기(DELETE, TRUNCATE, DROP)