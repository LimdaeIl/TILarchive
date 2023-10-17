# 16_Controller(Servlet)의 역할



웹 브라우저로부터 값을 입력하는 대표적인 태그는 HTML 문법 요소인 폼 태그입니다. 폼 태그로 입력한 값의 처리는 일반적으로 자바 서블릿에서 수행됩니다. 이때 웹 브라우저는 뷰(View), 입력값은 데이터(Data), 입력값을 처리하는 자바 서블릿은 컨트롤러(Controller)에 해당됩니다. 뷰에서 컨트롤러로 값을 전송하기 위해 폼 태그의 action 속성명과 메서드를 지정합니다. 다음 예제는 action 속성명을 'calc.do', 메서드는 post 형식으로 전송 버튼을 클릭하면 모델에 전송합니다. post 형식의 특징으로 URL 에 전송되는 파라미터가 노출되는 것을 확인할 수 있습니다.



**WebContent/su.html**

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<form action="calc.do" method="post">
<table class="table table-bordered">
  <tr>
    <td>수1</td>
    <td><input type="text" name="su1"/></td>
  </tr>
  <tr>
    <td>수2</td>
    <td><input type="text" name="su2"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center">
      <input type="submit" value="전송" class="btn btn-primary"/>
      <input type="reset" value="취소" class="btn btn-warning"/>
    </td>
  </tr>
</table>
</form>
</body>
</html>
```



**kr.bit.controller.CalcController.java**

```java
package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MyCalc;
@WebServlet("/calc.do")
// Controller역할
public class CalcController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			                               throws ServletException, IOException {

		// 1.클라이언트에서 넘어오는 폼 파라메터를 받기(파라메터수집, su1, su2)
		int su1=Integer.parseInt(request.getParameter("su1"));
		int su2=Integer.parseInt(request.getParameter("su2"));
		
		// su1~su2=?
		// 비즈니스로직->Model로 분리하기
		/*int sum=0;
		for(int i=su1;i<=su2;i++) {
			sum+=i;
		}*/
		
		MyCalc my=new MyCalc();
		int sum=my.hap(su1, su2);
		
		// 응답하는 부분(프리젠테이션로직=View=JSP)
		PrintWriter out=response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<table border='1'>");
		out.println("<tr>");
		out.println("<td>TOTAL</td>");
		out.println("<td>");
		out.println(sum);
		out.println("</td>");		
		out.println("</tr>");
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}
}

```



16장의 학습을 정리하자면 다음과 같습니다.

1. 클라이언트는 웹 브라우저에서 폼 태그를 통해 값을 입력합니다.
2. 모든 입력을 마치고 전송 버튼을 클릭하면 폼 태그 속성인 액션명과 동일한 컨트롤러에 전달 됩니다.
3. 컨트롤러는 전달받은 입력값인 파라미터를 비즈니스 로직에 따라 처리하고 클라이언트로 응답합니다.