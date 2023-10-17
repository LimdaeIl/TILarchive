# 08_JSP에서 비즈니스로직 분리

지금까지 학습한 내용을 정리하자면, `Servers/Tomcat9.0/server.xml` 파일의 `</Host>`  태그의 윗부분에 Context 태그를 추가했습니다.  Context 태그 내용은 다음과 같습니다.

```xml
<Context path="web" docBase="C:\eGovFrame-4.1.0\workspace.edu\WEB\webapp" />
```

컨텍스트 경로 설정을 성공적으로 마치고, 웹 브라우저로 time.xml 안에 Date 클래스의 인스턴스를 생성하여 웹 브라우저에서 현재 시간을 출력하는 동적인 웹 페이지를 구현하고 마무리했습니다. 요약하자면, 자바 프로젝트에서 웹 프로젝트로 직접 하나씩 생성하고 설정하여 웹 프로젝트의 구조에 대해 이해하는 것을 핵심으로 마무리했습니다. 그 외에 JSP API 라이브러리 등록, HTML, 지시어, 컨텍스트 경로 설정 방법에 대해 학습해습니다.

이번 장에서는 지금까지 JSP 파일 안에 작성한 자바 코드를 `src` 폴더 안에 자바 파일로 분리하는 방법에 대해 학습합니다. 이 과정을 **JSP에서 비즈니스 로직을 분리**한다는 것을 의미합니다. 다시 `WEB` 폴더를 우클릭해서 `Build Path-Configure Build Path...` 에 들어갑니다. 

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/40b2541c-a0c6-4070-8b83-6856d4e2d4ab)










`Source` 탭에서 `Browse...` 를 클릭하고 `WEB/bin` 으로 설정되어 있는 빌드 출력 경로를 `webapp/WEB-INF-classes` 로 변경합니다. 그 이유는 톰캣의 웹 프로젝트 컴파일을 수행하면 `webapp` 하위 폴더의 파일은 `bin` 폴더가 아닌 `classes` 폴더 안에 `class` 파일을 바탕으로 컴파일이 수행되기 때문에 `src` 폴더 안에 컴파일된 `class` 파일을 알 수 없기 때문입니다. 그래서 `classes` 폴더로 변경하면 됩니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/8cac73fd-bd4d-4fcd-a087-a057e6da7c7f)



성공적으로 적용이 된다면, 폴더 구조에서 classes 폴더가 사라진 것을 확인할 수 있습니다. 이제 톰캣을 다시 시작하면 성공적으로 웹 브라우저에서 결과를 볼 수 있습니다. 요약하자면, JSP 파일에서 비즈니스 로직을 임포트하고 지시어를 통해 웹 브라우저로 1부터 10까지의 합의 결과를 출력합니다. JSP 파일과 분리된 비즈니스 로직인 sum.jsp 파일과 MyUtil.java 파일은 아래에 첨부합니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/a7d8a718-5640-435b-ab9d-35be0cfbd6f0)


**sum.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.web.util.*" %>
<%
	MyUtil my = new MyUtil();
	int sum = my.hap();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
<tr>
<td>1~10까지의 총합</td>
<td><%= sum %> </td>
</tr>
</table>

</body>
</html>
```



**MyUtil.java**

```java
package kr.web.util;

public class MyUtil {
	
	public int hap() {
		int sum = 0;
		for(int i=0;i<=10;i++) {
			sum += i;
		}
		return sum;
	}
}
```



