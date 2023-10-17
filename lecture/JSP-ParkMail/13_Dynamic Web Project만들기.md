# 13_Dynamic Web Project만들기

지금까지 자바 웹 프로젝트에서 직접 하나씩 전부 생성해서 웹 프로젝트를 구성했습니다. 지금부터는 직접 만들지 않고 다이나믹 웹 프로젝트를 선택하여 생성합니다. 새롭게 웹 프로젝트를 생성하기 위해 `File-New-Dynamic Web Project` 를 선택합니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/0d5c154f-77f9-43c5-a2bf-cbb9e7506a84)





프로젝트 이름은 MVC01 으로 작성하고 [Next] 를 클릭합니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/a61d8814-143d-4f64-9eaf-9c4062a56f3b)





지난 학습 때 직접 빌드 경로를 classes 폴더로 변경했습니다. 다이나믹 웹 프로젝트로 생성하면 자동으로 설정해주는 것을 확인할 수 있습니다. 해당 창에서는 변경하지 않고 [Next] 를 클릭합니다. 

![image-20231013132750513](C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20231013132750513.png)



지난 시간에 톰캣 폴더 안에 Server.xml 에 직접 컨텍스트 경로를 작성해서 서블릿 컨테이너가 작성된 모든 파일을 처리할 수 있도록 컨텍스트 태그를 추가하였습니다. 다이나믹 웹 프로젝트 생성에서는 컨텍스트 태그를 직접 추가하지 않고, 다음 그림처럼 컨텍스트 루트를 지정할 수 있고, 배포 서술자인 web.xml 을 선택하면 생성할 수 있습니다. 그림과 동일하게 진행하고 [Finish]를 클릭합니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/ba4fcc0c-615a-4239-82d8-61e980d4f675)





생성된 다이나믹 웹 프로젝트의 구조입니다. 

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/a3c98027-6b46-4da9-9861-3bff93382da3)




이제 톰캣 서버에 생성된 다이나믹 웹 프로젝트를 등록해야 합니다. 다음 그림과 같이 Server 에서 Tomcat v9.0 ... 을 우클릭하고 Add and Remove... 을 클릭합니다. 

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/75faa101-6870-45fe-a31d-7afced22d044)


Available 에 MVC01 을 선택하고 Add > 버튼을 클릭해서  Finish 를 클릭합니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/036f28c0-c33d-4174-a4f8-c4696e387349)


톰캣 파일 안에 server.xml 파일로 이동합니다. `</Host>` 태그 윗부분에 `<Context>` 태그 안에 MVC01 프로젝트가 성공적으로 컨텍스트 경로가 등록된 것을 확인할 수 있습니다.   

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/b6028339-dd1d-4034-ba93-91b0f3529660)


톰캣 서버를 재시작하고 MVC01 프로젝트가 성공적으로 동작하는지 확인하기 위해 index.html 파일을 생성하고 웹 브라우저에서 확인합니다. 컨텍스트 루트는 MVC01입니다. 따라서 URL에 ...MVC01/index.html 로 작성해야 합니다. 

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/786ec5cf-ca72-4b72-a23c-4d8e2cba770d)


![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/00d150ec-7a7e-461b-b2fa-7da72f86cbe6)




JSP 파일도 정상적으로 동작하는지 확인합니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/162afee8-1482-4a1a-8ed2-4fef07022140)


![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/632a5907-d7bb-4ed8-8564-3b497e87ecb7)




Servlet 생성도 직접 선언하는 것이 아니라 Servlet 으로 간단하게 생성합니다. kr.web.controller 를 미리 생성하고 안에 서블릿을 생성해야 합니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/5b65cdda-5362-4e6c-bfe1-7e9dd539264a)




클래스 이름을 작성하고 `Next>` 를 클릭합니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/172fc0db-412d-404c-80d9-0d0a4565baed)




URL mappings: 에서 URL 또한 변경이 가능합니다. 기본으로 설정되어 있는 /HelloStarts 를 더블 클릭하고 /h.do 로 변경합니다. 그리고 `Next >` 를 클릭합니다. 

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/95a16d0b-44ec-44c3-9747-eb06fbcbe98b)


![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/c94bd322-8af0-4e04-b00d-3327e70f60e7)


기본 선택 되어 있는 doGet, doPost 선택 해제하고, service 메서드를 선택합니다. 그리고 Finish 를 클릭하면 서블릿 파일이 완성됩니다. 서블릿을 상속 받아서 사용하기 때문에 .java 파일 형식이라는 것을 잊지 마세요!

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/f81ed8d4-c3f7-4e95-bb31-02b972cbfc17)




불필요한 내용을 지우고 다음 그림과 같이 @WebServlet 어노테이션과 service 메서드만 남기고 모두 지우도록 합니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/3e97b213-011d-4a1c-bf95-f3f78dc5c2ac)




다시 1부터 100까지의 합을 출력하는 코드를 작성합니다. 정상적으로 출력되는 것을 웹 브라우저로 확인합니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/33b75283-299e-4e68-94a2-162c4bf3a550)


![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/d236f963-2bc7-4043-ad8d-e2430242ef01)




지금까지 자바 웹 프로젝트로 직접 하나씩 생성하고, 다이나믹 웹 프로젝트로 자동 생성하여 컨텍스트 루트를 등록했습니다. 그리고 HTML, JSP, Servlet 파일을 생성해서 실습했습니다. 
