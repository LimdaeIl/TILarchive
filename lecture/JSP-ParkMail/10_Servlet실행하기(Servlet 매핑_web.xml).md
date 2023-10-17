# 10_Servlet실행하기(Servlet 매핑_web.xml)

WEB-INF 폴더 안에는 더 이상 보이지 않는 classes 폴더가 있습니다. classes 폴더 안에는 src 폴더 안의 자바 파일들이 컴파일에 의해 clsss 파일로 변환된 상태로 저장되어 있습니다. 컴파일로 인해 비즈니스 로직이 노출될 위험이 있다고 생각할 수 있지만, WEB-INF는 보안 디렉터리입니다. WEB-INF 폴더 안의 모든 파일들은 웹 브라우저에서 URL을 통해 직접 접근이 불가능합니다. 다음의 URL 형식으로 클라이언트(웹 브라우저)는 요청이 불가능합니다. 

```rst
localhost:8081/web/WEB-INF/classes/kr.web.controller.HelloServlet
```



클라이언트(웹 브라우저)가 URL을 통해 WEB-INF 폴더 안의 경로에 접근하기 위해서는 매핑(Mapping)을 해야 합니다. 매핑하기 위해 Servlet에서는 web.xml 파일 안에 서블릿 매핑 태그 선언으로 URL 경로 지정이 필요합니다. 지난 시간에 작성한 내용으로 진행하면 서블릿 매핑 태그에서 오류가 발생합니다. web.xml 파일 안에 작성된 모든 내용을 지우고 다음 코드를 복사하고 붙여야 합니다. 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="4.0" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd">

	<servlet>
		<servlet-name>A</servlet-name>
		<servlet-class>kr.web.controller.HelloServlet</servlet-class>
	</servlet>
	
    <servlet-mapping>
        <servlet-name>A</servlet-name>
        <url-pattern>/hs.do</url-pattern>
    </servlet-mapping>
    
</web-app>
```

서블릿 매핑을 위해서는 서블릿 태그와 서블릿 매핑 태그 안에 서블릿 네임 태그가 서로 동일해야 합니다. 서블릿 태그 안의 서블릿 클래스 태그는 실제 경로에 해당되고, 서블릿 매핑 태그의 URL 패턴 태그가 가짜 경로에 해당됩니다. 클라이언트는 가짜 경로를 통해 실제 경로에 접근할 수 있습니다. 클라이언트에게 실제 경로를 노출시키지 않고 접근할 수 있도록 매핑하는 방법입니다.