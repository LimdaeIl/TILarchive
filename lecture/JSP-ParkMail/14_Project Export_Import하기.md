# 14_Project Export_Import하기

오늘은 프로젝트를 외부로 빼서 불러오는 방법에 대해 학습합니다. 프로젝트를 우클릭하고 Export - WAR file 을 선택합니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/fcb111ed-9b69-4bfd-b9a1-ad29f4ca4a9e)




Browse... 를 선택하고 압축된 파일을 어디에 저장할지 정합니다. Export source files 는 소스 파일도 함께 압축합니다. Overwrite existing file 은 동일한 압축된 파일이 존재할 때 덮어 쓰기를 수행할지 여부를 확인합니다. 지정한 경로에 이동해서 MVC01.war 파일을 확인합니다. 

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/69b401ac-b500-4885-84d7-8093c0720744)


![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/f42c2bb5-cb37-4269-bf25-d2e7f16e3e9b)




추출한 MVC01.war 파일 불러오기 위해 이전의 MVC01 프로젝트를 삭제합니다. 프로젝트에서 우클리하고 delete 를 클릭합니다. 프로젝트 탐색기에서만 삭제하는 것이 아 물리적 폴더 MVC01가 삭제된 상태여야 합니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/ad619b7e-ea87-49c5-98f9-f5c26c7c6705)




프로젝트 탐색기에서 빈 공간에 우클릭하고 Import - WAR file 을 선택합니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/2ed4ee04-dd5b-44b0-82ca-66abe586355c)




Browse... 를 클릭하고 추출한 MVC01.war 파일을 선택하고 Finish 를 클릭합니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/0082283b-12dc-455a-8bc4-127da03ccc0c)




WAR Import 를 성공적으로 끝낸 상태에서 톰캣 서버에 다시 등록해야 합니다. 지난 시간에 학습한 컨텍스트 경로를 등록하는 방법과 동일 합니다. Server 안의 Tomcat 9.0v ... 를 우클릭하고 Add and Remove... 를 클릭합니다.  

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/f7413ad6-c50a-4823-b62c-1e3e0d8ab18f)




Available: 안에 있는 MVC01을 클릭하고 Add > 버튼을 클릭합니다. Configured: 로 이동했다면, Finish 를 클릭합니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/87efec2e-35dc-4807-aa21-8b2388ff126a)




톰캣 서버를 종료하고 재시작합니다. 정상적으로 MVC01 프로젝트가 컨텍스트 경로에 알맞게 톰캣 컨테이너에 의해 실행되는 것을 확인할 수 있습니다.

![image](https://github.com/LimdaeIl/TILarchive/assets/131642334/79e88021-6a6c-4fbb-a9d2-0e09584ddb78)


WAR Import 를 통해서 다른 사람의 파일을 열어볼 수도 있고, 나의 파일을 누군가에게 압축해서 전달할 수 있습니다. 
