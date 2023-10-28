# 섹션5. HTTP 메서드 활용



## **1. 클라이언트에서 서버로 데이터 전송**

#### **쿼리 파라미터를 통한 데이터 전송**

주로 GET 방식으로 많이 사용하고 검색어로 검색할 때, 게시판 리스트에 정렬 조건을 넣을 때 쿼리 파라미터를 이용해서 많이 사용한다.

 

#### **메시지 바디를 통한 데이터 전송**

클라이언트에서 서버로 전송할 때 HTTP 메시지 바디를 통해서 데이터를 전송한다. POST, PUT, PATCH 방식으로 주로 사용한다. 회원가입을 하려면 클라이언트에서 데이터를 서버로 전송해야 한다. 그 다음에 상품을 주문하거나 새로운 리소스를 등록하거나 리소스를 변경할 때 사용한다. 

 

#### **클라이언트에서 서버로 데이터 전송할 때 4가지 상황**

- 정적 데이터 조회 : 이미지, 정적 텍스트 문서
- 동적 데이터 조회 : 주로 검색, 게시판 목록에서 정렬 필더
- HTML Form을 통한 데이터 전송 : 회원가입, 상품 주문, 데이터 변경
- HTTP API를 통한 데이터 전송 : 회원 가입, 상품 주문, 데이터 변경, 서버 to 서버, 앱 클라이언트 웹 클라이언트(Ajax)

 

#### **정적 데이터 조회 - 쿼리 파라미터 미사용**



![img](https://blog.kakaocdn.net/dn/E1pG0/btruFfNQU8p/LvO1pmsmKfgPl1gqnToey0/img.png)



클라이언트에서 /static/star.jpg 를 요청 메시지를 보내면 서버에서 star 이미지를 클라이언트에게 응답해준다. 정적 데이터를 조회할 때는 이미지나 정적 텍스트 문서로 조회하기 때문에 GET으로 사용한다. 일반적으로 쿼리 파라미터 없이 단순한 리소스 경로로 조회가 가능하다.

 

#### **동적 데이터 조회 - 쿼리 파라미터 사용**



![img](https://blog.kakaocdn.net/dn/A1sIJ/btruAjbwPS3/vym1Sg0LkdjnPeZDQUCN91/img.png)



구글에서 검색할 때 검색어나 추가 조건을 데이터를 전달 할 때 쿼리 파라미터들을 사용해서 서버에게 요청한다. 쿼리 파라미터를 가지고 서버에서 key와 value를 볼 수 있다. 그거에 대한 결과를 클라이언트에게 응답한다. 주로 검색을 하거나 게시판 목록에서 정렬하거나 필터할 때 추가 데이터들이 쿼리 파라미터로 요청한다. 조회 조건을 줄여주는 필터 다음에 조회 결과를 정렬하는 정렬 조건에 주로 사용한다. 이것도 조회라 GET 방식으로 이용해서 쿼리 파라미터를 사용해서 데이터를 클라이언에서 서버로 전달한다.

 

#### **HTML Form을 통한 데이터 전송 - POST 전송의 저장**



![img](https://blog.kakaocdn.net/dn/vEYHU/btruyNY34zm/Dnt9qOhx8RtCCF5pKz5kW0/img.png)



form 태그에서 action은 /save, POST 메서드, username 폼과 age 폼, 전송 버튼을 만들어서 전송 버튼을 누르면 데이터를 읽어서 HTTP 메시지를 생성한다. 쿼리 파라미터랑 유사하게 key와 value 스타일로 데이터를 만들어서 HTTP 바디 메시지에 넣고 POST 방식으로 서버에 전송을 한다. 그러면 서버에서 데이터를 저장을 한다. 

 

#### ***\*HTML Form을 통한 데이터 전송\** - GET 전송의 저장**



![img](https://blog.kakaocdn.net/dn/bERaPw/btruDDIeAoe/1YJXiHJW3wfXcBvk0wAeAK/img.png)



form을 통해서 데이터 전송할 때 GET 메서드로 변경하면 GET은 메시지 바디를 사용하지 않으므로 쿼리 파라미터 형식으로 서버에 전달한다. save 나 리소스를 변경할 때 GET 메서드를 사용하면 안된다. GET은 조회할 때 사용한다. 

 

#### **HTML Form을 통한 데이터 전송 - GET전송의 조회**



![img](https://blog.kakaocdn.net/dn/cBatmx/btruHx8C4Dv/SEI5kxSuYdBFKwtZm9nmPK/img.png)



form을 통해서 GET 방식으로 HTTP 메시지에 username이 김이고 age가 20살 사람을 리스트에서 필터링을 담아서 서버에 보낸다. 

 

#### **HTML From을 통해서 데이터 전송 - multipart/form-data**



![img](https://blog.kakaocdn.net/dn/ddTYyh/btruwJopGrs/maZqkCd4ZNfXwk0Lf542sK/img.png)



메시디 바디에 username과 age뿐만 아니라 바이트로 되어 있는 파일까지 POST 메서드로 전송한다. Content-type은 multipart/form-data 데이터로 들어가고 boundary는 웹으로 자동 생성해서 경계로 나눠진다. 

 

#### **HTML Form을 통한 데이터 전송 정리**

- HTML Form을 통해 POST 방식 전송으로 회원가입하거나 상품을 주문하거나 데이터를 변경할 때 주로 사용한다. Content-type으로 application/x-www-form-urlencoded 방식이다. form의 내용을 HTTP 메시지 바디에 key와 value 형식으로 쿼리 파라미터 형식으로 보낸다. 전송 데이터를 url encoding으로 처리한다. 
- HTML Form은 GET 방식 전송으로로 가능하다. GET은 조회할 때만 가능하다. 쿼리 파라미터 형식으로 GET 으로 전송한다. Content-type으로 multipart/form-data 으로 파일 업로드 같은 바이너리 데이터를 같이 전송한다. 여러 종류 파일과 form의 내용을 함께 전송하기 때문에 이름이 multpart이다. 
- HTML Form은 GET과 POST으로 지원한다. 

 

#### **HTTP API 데이터 전송**



![img](https://blog.kakaocdn.net/dn/nSBYc/btruDDvnugs/ig0IDMWAbaW6swIKr6iCu1/img.png)



클라이언트에서 HTTP API로 서버로 데이터를 전송하는데 직접 만들어서 전송하면 된다. 클라이언트에 /members가 있고 Content-Type에 application/json으로 데이터 전송하면 된다. 

 

 

#### **HTTP API 데이터 전송**

- 서버 to 서버로 백엔드 서버끼리 통신할 때 많이 사용한다. 서버끼리 통신할 때는 HTML 같은 게 없어서 기계끼리 통신하는 거다. 
- 앱 클라이언트는 아이폰이나 안드로이드로 사용한다.
- 웹 클라이언트는 HTML Form이 아니라 자바스크립트를 통해서 Ajax로 통신한다. 
- HTTP API는 메시지 바디를 통해 POST, PUT, PATCH로 데이터 전송한다. 
- GET도 마찬가지로 HTTP API로 쓸수 있지만 조회할 때는 항상 쿼리 파라미터 형식으로 전달해야 된다. 
- Content-Type 으로 application/json을 주로 사용한다. 

 

------

## **2. HTTP API 설계 예시**

#### **API 설계 - POST 기반 등록**



<br />



### 회원 관리 시스템

1. **회원** 목록 : /members ➡️ **GET**
2. **회원** 등록 : /members ➡️ **POST**
3. **회원** 조회 : /members/{id} ➡️ **GET**
4. **회원** 수정 : /members/{id} ➡️ **PATCH, PUT, POST**
5. **회원** 삭제 : /members/{id} ➡️ **DELETE** 

- 클라이언트는 등록될 리소스의 URI를 모른다. 회원 데이터를 서버에 요청하고 서버가 알아서 회원을 식별해서 URI를 만들어준다.
  - **회원** 등록 : /members ➡️ **POST**
    POST /memebers

- 클라이언트가 결정하는 게 아니라 서버가 새롭게 등록된 리소스의 URI를 생성한다. 
  - HTTP/1.1 201 Created
    Location : **/members/100**

- 컬렉션(Collection)은 **서버가 관리하는 리소스 디렉토리**이다. **리소스의 URI를 생성하고 관리**한다. 
  - 컬렉션 예시: **/members**

####  

<br />



#### **API 설계 - PUT 기반 등록**

### **파일 관리 시스템**

1. **파일** 목록 : /files ➡️ **GET**
2. **파일** 조회 : /files/{filename} ➡️ **GET**
3. **파일** 등록 : /files/{filename} ➡️ **PUT**
4. **파일** 삭제 : /files/{filename} ➡️ **DELETE**
5. **파일** 대량 등록 : /files ➡️ **POST**

- 클라이언트가 리소스 URI를 알고 있어야 한다. 클라이언트가 직접 리소스의 URI를 지정해서 생성된 리소스를 관리해야 한다. 
  - **파일** 등록 : /file/{filename} ➡️ **POST**
    PUT **/files/star.jpg**

- 스토어(Store)는 클라이언트가 관리하는 리소스 저장소이다. 
  - **/files**

 

<br />



#### **HTML Form 사용**

### **회원 관리 시스템**

1. **회원** 목록 : /members ➡️ **GET**
2. **회원** 등록 폼 : /members/new ➡️ **GET**
3. **회원** 등록 : /members/new, /members ➡️ **POST**
4. **회원** 조회 : /members/{id} ➡️ **GET**
5. **회원** 수정 폼 : /members/{id}/edit ➡️ **GET**
6. **회원** 수정 : /members/{id}/edit, /members/{id} ➡️ **POST**
7. **회원** 삭제 : /members/{id}/delete ➡️ **POST**

-  순수한 HTML과 HTML Form은 GET, POST만 지원하기 때문에 제약이 있다.
- 제약을 해결하기 위해 동사로 된 리소스 경로를 사용을 하는데 이걸 컨트롤 URI이라 한다. HTTP 메서드로 해결하기 애매한 경우에 컨트롤 URI를 사용한다.

> **/members/new
> /members/{id}/edit**
> **/members/{id}delete**

 

#### **참고하면 좋은 URI 설계 개념**

- 문서(document) : 파일 하나, 객체 인스턴스, 데이터베이스 row 같은게 단일 개념이다.

> **/members/100**
> **/files/star.jpg**

- 컬렉션(Collection) : 서버가 관리하는 리소스 디렉토리이다. 클라이언트는 요청만 하고 서버가 리소스의 URI를 생성하고 관리한다.

> **/members**

- 스토어(Store) : 클라이언트가 관리하는 자원 저장소이다. 클라이언트가 리소스의 URI를 알고 관리한다.

> **/files**

- 컨트롤러(Controller), 컨트롤 URI : 문서, 컬렉션, 스토어로 해결하기 어려운 추가 프로세스 실행할 때 동사를 직접 사용한다.

> **/members/{id}/delete**