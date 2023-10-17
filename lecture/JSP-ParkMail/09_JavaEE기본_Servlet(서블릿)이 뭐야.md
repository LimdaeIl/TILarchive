# 09_JavaEE기본_Servlet(서블릿)이 뭐야



kr.web.controller 패키지 안에 HelloServlet.java 파일을 생성합니다.

![image-20231013115650602](C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231013115650602.png)



서블릿을 사용하기 위해 JavaEE 환경에서 진행합니다.  main 메서드를 중심으로 자바 기본 문법을 배운 환경인 JavaSE 인 점과 다르게 JavaEE는 Servlet이 중심이 됩니다. 가장 먼저 Servlet을 사용하기 위해 HelloServlet 클래스는 HttpServlet을 상속받아야 합니다.

1. HttpServlet 클래스를 상속합니다. 

```java
package kr.web.controller;

import javax.servlet.http.HttpServlet;

// JavaEE -> Servlet(서블릿)의 골격
public class HelloServlet extends HttpServlet{
	
}
```



2. 클래스 중괄호에서 우클릭합니다. `[Source]-[Override/Implement Methods...]` 을 클릭하여 상속받은 HttpServlet 클래스로부터 현재 사용 가능한 메서드를 확인합니다.

![image-20231013120456928](C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231013120456928.png)

![image-20231013120638788](C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231013120638788.png)



3. `[Source]-[Override/Implement Methods...]` 에서 직접 선택해서 메서드를 사용할 수 있지만, 창을 닫고 직접 service 메서드를 다음과 같이 작성합니다. 

```java
package kr.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// JavaEE -> Servlet(서블릿)의 골격
public class HelloServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
```

`service` 메서드는 Java Servlet API의 중요한 메서드 중 하나로, 서블릿이 클라이언트의 HTTP 요청을 처리하고 응답을 생성하는 데 사용됩니다. 이 메서드는 모든 HTTP 요청 메서드(GET, POST, PUT, DELETE 등)에 대해 호출될 수 있으며, 서블릿의 주요 엔트리 포인트입니다.

1. `HttpServletRequest req` : 이 매개변수는 클라이언트로부터의 **HTTP 요청 정보를 담고 있는 객체**입니다. 
   이 객체를 통해 다음과 같은 정보에 접근할 수 있습니다.
   - 요청 메서드(GET, POST, 등)
   - 요청 헤더(클라이언트 정보, 쿠키, 인증 등)
   - URL 및 URI 정보
   - 요청 매개변수(HTML 폼에서 전송된 데이터)
   - 세션 및 사용자 인증 정보 등
2. `HttpServletResponse resp`: 이 매개변수는 **HTTP 응답을 생성하고 클라이언트로 전송하는 데 사용되는 객체**입니다.
   이 객체를 통해 다음과 같은 작업을 수행할 수 있습니다.
   - 응답 상태 코드 및 헤더 설정
   - 응답 데이터(HTML, JSON, 이미지 등) 작성
   - 리다이렉션 및 에러 페이지 설정
   - 쿠키 설정
   - 다른 서블릿으로 포워딩
3. `throws ServletException, IOException`: `service` 메서드는 `ServletException`과 `IOException` 예외를 던질 수 있음을 선언합니다. 이 예외들은 메서드 내에서 발생할 수 있는 오류를 나타내며, 호출하는 코드에서 이러한 예외를 처리해야 합니다. `ServletException`은 주로 서블릿 컨테이너에 의한 예외를 처리할 때 사용되고, `IOException`은 입출력과 관련된 문제를 나타냅니다.

`service` 메서드는 일반적으로 서블릿 클래스에서 오버라이드(재정의)되어 구현됩니다. 서블릿은 클라이언트의 요청을 분석하고, 필요한 비즈니스 로직을 수행한 후 `HttpServletResponse` 객체를 사용하여 클라이언트에게 응답을 보냅니다. 이것은 웹 애플리케이션에서 동적 컨텐츠를 생성하고 클라이언트에게 제공하는 데 사용되며, 웹 애플리케이션의 핵심 부분 중 하나입니다.



4. 1부터 100까지 합을 웹 브라우저로 출력합니다. 가장 큰 특징은 JSP 파일이 아닌 자바 파일 내에 HTML 코드도 작성했다는 점으로 JAVA에서는 단순히 문자열이지만, 웹 브라우저에서는 HTML로 인식하여 웹 브라우저에 정상적으로 1부터 100까지 합을 출력합니다.		

   ```java
   package kr.web.controller;
   
   import java.io.*;
   
   import javax.servlet.*;
   import javax.servlet.http.*;
   
   import kr.web.util.MyUtil;
   
   // JavaEE -> Servlet(서블릿)의 골격
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


`getWriter` 메서드는 `HttpServletResponse` 객체에서 `PrintWriter`를 생성하여 응답 데이터를 클라이언트로 출력하기 위한 스트림을 제공합니다. 클라이언트(웹 브라우저)로 데이터를 출력하기 위해 `PrintWriter`를 사용합니다.