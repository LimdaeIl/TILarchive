# 05_Web Application 디렉토리구조



1. Dynamic Web Project 생성 아닙니다. Java Project 로 하나씩 전부 생성합니다.

   - src 폴더 아래에는 .java 파일이 저장됩니다.
   - bin 폴더 아래에는 .class 파일이 저장됩니다.

   ![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/5c372276-c3e0-47b8-9784-f07110ad7190)




2. 생성된 자바 프로젝트 확인합니다. 이제 웹 프로젝트로 변경을 진행합니다.

   ![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/483d3f2a-fa49-4991-935b-dcde7bac1239)


3. WEB Project(WEB Application의 디렉터리 구조)

   - webapp : 아파트
   - WEB-INF : web-information, 아파트의 경비실(중요한 폴더입니다.)
   - lib : 공동으로 사용할 수 있는 API(.jar), 아파트 내의 구비용품
   - classes : 아파트 내에 전력, 수도관, 소방 시설 등 중요 정보(.class, 자바 파일은 모두 여기에 저장됩니다.)

   ![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/c57437d3-4a28-4059-b33b-ee0feff2e3b6)


4. WEB 구조는 폴더를 생성해서 구성합니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/a3450006-c610-4ea8-9a36-76679ec0453f)


5. web.xml 안에 내용입니다.(tomcat 폴더 안의 web.xml 안에서 web-app 태그를 복사해서 붙여놓기 합니다.)

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <web-app version="4.0" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee                       http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd">
   
   </web-app>
   ```

   
