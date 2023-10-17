# 07_JSP만들고 구동하기(동적인페이지)

JSP를 사용하기 위해 웹 API 연결이 필요합니다. 톰캣 폴더 안에는 웹과 관련된 다양한 API가 .jar 형태로 존재합니다.  `C:\eGovFrame-4.1.0\bin\apache-tomcat-9.0.69\lib` 하위 폴더 안에 `servlet-api.jar ` 파일이 JSP를 사용하기 위해 톰캣에서 제공하는 API입니다. 

![image-20231012153403412](C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231012153403412.png)

`servlet-api.jar` 폴더를 복사하고 이전에 생성한 `WEB-INF/lib` 폴더 안에 저장합니다. 단, 지금까지 수동으로 웹 프로젝트를 구성했기 때문에 단순히 lib 폴더 안에 저장해서는 JSP를 사용할 수 없습니다. Java Build Path 으로 `servlet-api.jar` 를 사용할 수 있도록 라이브러리를 추가합니다.

 `WEB` 폴더에서 우클릭하고 `Build Path-Configure Build Path... ` 를 클릭합니다.

![image-20231012153741110](C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231012153741110.png)



`Add External JARs...` 를 클릭합니다.

![image-20231012153908570](C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231012153908570.png)



톰캣 폴더 안에서 `lib/servlet-api.jar` 파일을 선택하고 확인합니다.

![image-20231012154030357](C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231012154030357.png)



`Applay and Close` 를 클릭하고 창을 닫으면 JSP 파일을 사용할 수 있습니다.

![image-20231012154018158](C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231012154018158.png)



바디 태그 안에 시간을 출력할 수 있는 내용을 추가합니다. HTML 파일의 정적인 페이지가 아니라 시간에 따라서 웹 브라우저에 출력되는 시간이 동적으로 다르게 출력된다는 점을 확인할 수 있습니다.

![image-20231012154621634](C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231012154621634.png)



JSP 파일 실행 결과를 웹 브라우저에서 확인합니다. 새로고침할 때 마다 시간이 다르게 출력되는 것을 확인할 수 있습니다.

![image-20231012154610982](C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231012154610982.png)



렌더링은 웹 애플리케이션에서 다음과 같은 것들을 의미합니다:

1. **화면에 내용 표시**: 웹 애플리케이션에서 "렌더링"은 데이터나 콘텐츠를 사용자의 웹 브라우저에 나타내는 과정을 의미합니다.
2. **템플릿과 데이터 결합**: JSP나 다른 웹 프레임워크에서, 렌더링은 웹 페이지의 템플릿(또는 JSP 페이지)과 데이터를 결합하여 최종 웹 페이지를 만드는 것을 나타냅니다.
3. **동적 컨텐츠 생성**: 렌더링은 요청에 따라 웹 페이지 내용을 동적으로 생성하는 과정을 의미합니다. 사용자의 요청 또는 서버 이벤트에 따라 웹 페이지를 업데이트합니다.
4. **페이지 생성 및 업데이트**: 렌더링은 웹 페이지를 생성하거나 업데이트하는 작업을 의미합니다. 이는 웹 애플리케이션이 사용자의 요청과 데이터에 따라 웹 페이지를 변경하거나 업데이트하는 것을 포함합니다.

요약하면, 렌더링은 웹 애플리케이션에서 페이지를 만들고 화면에 나타내는 프로세스를 나타내며, 이로써 웹 페이지의 내용은 사용자 요청 및 데이터에 따라 동적으로 생성 및 업데이트됩니다.