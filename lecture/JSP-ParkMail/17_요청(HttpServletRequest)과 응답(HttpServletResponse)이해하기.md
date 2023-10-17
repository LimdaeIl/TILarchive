# 17_요청(HttpServletRequest)과 응답(HttpServletResponse)이해하기

지난 시간에 학습한 폼 태그로 컨트롤러에게 요청 및 응답을 그림과 함께 복습합니다.



**클라이언트에게 보이는 화면**

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/2b8aeac1-1b2e-4a93-a724-b0cdea2f0cad)


![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/de48d0fd-0bf2-4c9b-9d70-6a5dbd2d2adb)


클라이언트는 두 수를 입력하고 전송 버튼을 클릭합니다. 폼 태그의 속성명에 따라 'calc.do' 으로 입력값을 컨트롤러로 전송합니다. 여러 자바 서블릿 파일 안에 @subServlet 어노테이션의 매개변수가 폼 태그 속성명과 동일한 '/calc.do' 을 찾고, 입력값이 포함된 패킷(요청 정보)를 전달합니다. 패킷 안에는 크게 헤더와 바디로 구성되고 다음과 같이 전송됩니다.

**패킷(요청 정보)**

| header           | body   |
| ---------------- | ------ |
| ip:192.211.12.42 | su1=12 |
| port:8081        | su2=12 |



**클라이언트의 요청을 처리하는 서버**

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/8065f920-2da6-4e55-90ba-723378a4d950)




정상적으로 패킷을 받은 서버(컨트롤러, 자바 서블릿 파일)는 요청을 처리하기 위해 톰캣 컨테이너가 HttpServletRequest, HttpResponse 객체 안에 패킷을 저장합니다. HttpServletRequest 객체 안에는 헤더와 바디가 저장되고, HttpResponse 객체 안에는 헤더를 저장합니다. 따라서 요청으로부터 전달받은 값을 처리할 때 HttpServletRequest 객체를 다음과 같이 사용합니다. 

```java
@WebServlet("/calc.do")
// Controller역할
public class CalcController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			                               throws ServletException, IOException {

		// 1.클라이언트에서 넘어오는 폼 파라메터를 받기(파라메터수집, su1, su2)
		int su1=Integer.parseInt(request.getParameter("su1"));
		int su2=Integer.parseInt(request.getParameter("su2"));
        ...
    }
}
```



HttpResponse 객체에 저장된 '/calc.do'으로 응답을 수행합니다. 

```java
@WebServlet("/calc.do")
// Controller역할
public class CalcController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			                               throws ServletException, IOException {
...
		
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

