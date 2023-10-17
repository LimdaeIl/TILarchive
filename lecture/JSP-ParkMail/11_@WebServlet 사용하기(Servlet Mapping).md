# 11_@WebServlet 사용하기(Servlet Mapping)

서블릿을 실행할 때 마다 계속 매핑하는 것은 부적절합니다. 쉽게 패밍하는 방법이 있는데, 서블릿의 어노테이션인 @WebServlet("경로") 을 클래스 위에 작성하면 됩니다. 지금까지 web.xml 안에 작성한 서블릿 태그들을 작성할 필요 없이 @WebServlet("경로") 작성으로 간단하게 실제 경로와 매핑을 할 수 있습니다. 단, web.xml 안에 서블릿 태그와 @WebServlet("경로") 를 중복으로 작성하면 안됩니다.

```java
package kr.web.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import kr.web.util.MyUtil;

// JavaEE -> Servlet(서블릿)의 골격
@WebServlet("/hs.do") // <- web.xml
public class HelloServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1~100까지의 총합=?
		MyUtil my = new MyUtil();
		int sum = my.hap();
		
		// 요청한 클라이언트에게 응답하기?
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println(sum); // 5050
		out.println("</body>");
		out.println("</html>");
	}
}
```



지금부터 학습 방법은 MVC 패턴을 중심으로 진행합니다.