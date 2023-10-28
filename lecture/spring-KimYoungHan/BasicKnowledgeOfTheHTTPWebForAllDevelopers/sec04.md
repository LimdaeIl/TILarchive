# 섹션4. HTTP 메서드

## **1. HTTP API를 만들어보자**

#### **요구사항 및 API URI 설계**

> **회원 정보 관리 API 설계**
>
> 1. 회원 목록 조회 : /read-member-list
> 2. 회원 조회 : /read-member-by-id
> 3. 회원 등록 : /create-member
> 4. 회원 수정 : /update-member
> 5. 회원 삭제 : /delete-member

요구사항 기반으로 API를 만들게 되는게 위와 같이 현업에서 잘못된 API URI 설계를 한다. 

 

#### **API URI 설계 분리**

> **리소스** : 회원
> **행위** : 조회, 등록, 수정, 삭제

API URI 설계를 할 때 리소스와 해당 리소스를 대상으로 하는 행위를 분리해야 한다. 회원이라는 리소스만 식별하고 회원 리소스를 URI에 매핑을 하면 된다. 

 

#### **API URI 재설계**

> **회원 정보 관리 API 재설계**
>
> 1. **회원** 목록 조회 : /members
> 2. **회원** 조회 : /members/{id} 
> 3. **회원** 등록 : /members/{id}
> 4. **회원** 수정 : /members/{id}
> 5. **회원** 삭제 : /members/{id}

API URI 재설계를 했지만 행위는 구분이 되지 않는다. 구분하는 방법은 URI 리소스만 식별해 놓으면 HTTP 메서드인 GET, POST, PUT, DELETE 이런 것들이 조회, 등록, 수정, 삭제 역할을 대신해준다. 

\* 계층 구조상 상위를 컬렉션으로 보고 복수 단어 사용 권장(member ➡️ members)

------

## **2. HTTP 메서드 - GET, POST**

#### **HTTP 메서드 종류**

- GET : 리소스를 조회
- POST : 요청 데이터를 담아서 처리 
- PUT : 리소스를 대체, 해당 리소스가 없으면 생성
- PATCH : 리소스 부분 변경
- DELETE : 리소스 삭제
- HEAD : GET과 동일하지만 메시지 부분을 제외하고 상태 줄과 헤더만 반환
- OPTIONS : 대상 리소스에 대한 통신 가능 옵션(메서드)를 설명 (주로 CORS에서 사용)
- TRACE : 대상 리소스에 대한 경로를 따라 메시지 루프백 테스트를 수행

GET과 POST는 클라이언트가 서버에 뭔가 요청을 할 때 기대하는 행동이다. 

 

#### **GET**

리소스를 조회한다. 서버에 전달하고 싶은 데이터는 쿼리 파라미터 또는 쿼리 스트링을 통해서 전달한다. GET은 메시지 바디를 전달할 수 있지만 실무에서는 바디에 보통 데이터를 넣지 않는다. 지원하지 않는 서버들이 많아서 권장하지 않는다. 

 



![img](https://blog.kakaocdn.net/dn/tSbiQ/btruqF1MEMP/VYpev9vOQoSUAVMkFnJWs0/img.png)![img](https://blog.kakaocdn.net/dn/bz4qKT/btrurUj4hQn/uar9o5tjByvgZRLl8YwM4k/img.png)

GET 요청과 응답



클라이언트가 /members/100 GET하고 요청하면 서버에서 GET 메시지가 도착한다. 서버에서는 /members/100 에서 데이터베이스를 조회해서 응답 메시지를 만들어서 클라이언트에게 보낸다. 

 

#### **POST**

클라이언트에서 메시지 바디를 통해서 서버로 요청해서 서버가 데이터를 처리하는 모든 기능을 수행한다. 주로 전달된 데이터로 신규 리소스 등록하거나 변경된 프로세스를 바꿀 때 많이 이용한다. 

 



![img](https://blog.kakaocdn.net/dn/bFkT2C/btruqFna0aG/X51H1bizN8DWnK8EoNCkJk/img.png)![img](https://blog.kakaocdn.net/dn/8WTBd/btrurx9fDCz/mQJ1nKevRv7PRQJpI6diC0/img.png)![img](https://blog.kakaocdn.net/dn/BnDrx/btrukgndRfP/eLxJbjwSqPVnNkzl8MRf6k/img.png)

POST 요청과 응답



리소스를 /members POST로 전달하면 서버 입장에서는 그 데이터를 내부적으로 어떻게 쓸꺼야라고 미리 서로 약속을 해놓은다. 클라이언트는 필요한 데이터를 전달한다. 그러면 서버에서는 신규로 등록한다고 /members에서 100 신규 리소스 식별자를 생성한다. 신규로 자원이 생산 된 경로를 응답메시지로 보낸다.  

 

#### **POST 예시, 요청 데이터를 어떻게 처리한다는 뜻일까?**

1. HTML 양식에 입력된 필드와 같은 데이터 블록을 데이터 처리 프로세스에 제공 

- HTML 폼에 입력한 정보로 회원가입하거나 주문 문 등을 처리

 

2. 게시판, 뉴스 그룹, 메일링 리스트, 블로그 또는 유사한 기사 그룹에 메시지 게시

- 게시판에 글쓰거나 댓글 달기
- 게시판에 글을 쓰고 Submit 누르면 POST로 글이 서버로 전달하고 서버가 게시판에 글을 저장한다.

 

3. 서버가 아직 식별하지 않은 새 리소스 생성

- 신규 주문을 생성하거나 신규 회원을 생성할 때 

 

4. 기존 자원의 끝에 데이터를 추가

- 한 문서 끝에 내용 추가하기 

 

#### **POST 정리**

1. 새 리소스 생성(등록)

- 서버가 아직 식별하지 않은 새 리소스 생성

 

2. 요청 데이터 처리

- 단순히 데이터를 생성하거나 변경하는 것을 넘어서 프로세스를 처리할 때 서버에서 큰 변화가 일어남
- 주문에서 결제완료 ➡️ 배달 시작 ➡️ 배달완료 처럼 단순히 값 변경을 넘어 프로세스의 상태가 변경되는 경우
- POST의 결과로 새로운 리소스가 생성되지 않을 수 있음
- 컨트롤 URI : POST /orders/{orderld}/start-delivery 
- 실무에서 어쩔 수 없이 리소스만으로 URI 설계 안 될 경우도 있음

 

3. 다른 메서드로 처리하기 애매한 경우

- JSON으로 조회 데이터를 넘겨야 하는데 GET 메서드를 지원하지 않는 서버가 있어서 사용하기 어려운 경우

\* 애매하면 POST 사용

 

------

## **3. HTTP 메서드 - PUT, PATCH, DELETE**

#### **PUT**

리소스가 있으면 대체하고 리소스가 없으면 생성한다. POST와 다른 점은 PUT은 클라이언트가 구체적인 리소스의 전체 위치를 알고 URI를 지정해서 서버에게 전달한다. 

 

#### **PUT - 리소스가 있을 경우**



![img](https://blog.kakaocdn.net/dn/dWPfos/btrusPCSoCS/WBMCrRuCmKLuirBi2w6fTk/img.png)![img](https://blog.kakaocdn.net/dn/tGX6W/btrusNSDb8w/HtVuUfft2QX1Ln2KHcLRWK/img.png)

PUT 리소스가 있을 경우



클라이언트가 /members/100 리소스 지정해서 데이터를 서버에게 보내면 서버도 /members/100 리소스가 있다. 그러면 클라이언트가 보낸 리소스로 대체해버린다. 

#### **PUT - 리소스가 없을 경우**



![img](https://blog.kakaocdn.net/dn/ElMM0/btrup9t7eCu/VyjcLHfWdKFkpAhedxoVZ0/img.png)![img](https://blog.kakaocdn.net/dn/pQjRk/btrusOD2LDM/ColBGBntAjaKZvBkVFXNik/img.png)

PUT 리소스가 없는 경우



클라이언트가 members/100 리소스 지정해서 데이터를 서버에게 보냈는데 서버에서 해당 리소스가 없어서 신규 리소스로 생성이 된다.

 

#### **PUT 주의사항**



![img](https://blog.kakaocdn.net/dn/b8xyEa/btruvtMTauC/eCTzlrMOHNejMIzgVX7NSK/img.png)![img](https://blog.kakaocdn.net/dn/vehIx/btruArguk9i/8dUUQuapx8yukAqXiIKXAk/img.png)

PUT 주의사항



클라이언트가 /members/100 데이터에 username이 없고 age로 지정해서 보내면 서버에서는 age를 업데이트를 하는데 username 자체가 날아가버린다. 기존 리소스를 새로운 리소스로 완전히 대체한다. 이렇게 되면 리소스를 수정하기 어렵다. 

 

#### **PATCH**



![img](https://blog.kakaocdn.net/dn/cKhCdR/btruuix35bt/4HAO1ScGawOdz6qjWqoUz1/img.png)![img](https://blog.kakaocdn.net/dn/cCK0VP/btruvuSBeGT/M92wvfldQraKI6SHZUtrVk/img.png)

PATCH 요청과 응답



리소스를 부분 변경한다. 클라이언트가 /members/100 데이터에 username이 없고 age로 지정해서 보내면 서버에서는 username은 남아있고 age만 변경한거다. 

 

#### **DELETE**



![img](https://blog.kakaocdn.net/dn/cx74MI/btruvt7cIpx/SG1uCNNNhuCnRuoOHFJXAk/img.png)![img](https://blog.kakaocdn.net/dn/byCSdU/btruArOj6GW/kEdEcPakJuWagvtIHoTei0/img.png)

DELETE 요청과 응답



리소스를 삭제한다. 클라이언트가 /members/100 를 삭제해달라고 요청하면 서버에서 리소스를 삭제한다. 

------

## **4. HTTP 메서드의 속성**



![img](https://blog.kakaocdn.net/dn/KL6eo/btrutk9I4fy/kKro9lrgoBEykWBrakcbU0/img.png)HTTP 메서드의 속성



#### **안전(Safe Methods)**

호출해도 리소스가 변경하지 않는다.

- GET은 단순히 조회만 하기 때문에 안전하다. 한번 호출해도 여러번 호출해도 변경이 일어나지 않아서 안전하다.
- POST, PUT, PATCH, DELETE는 안전하지 않다.
- 만약에 그래도 계속 호출해서 서버에서 로그가 계속 쌓게되서 서버 장애가 일어날 때는 안전은 그런 부분까지 고려하지 않는다. 안전은 해당 리소스만 고려하기 때문이다. 

 

#### **멱등(Idempotent Methods)**

한 번 호출해도 두 번 호출해도 100번 호출해도 결과는 동일하다. 

- GET은 한 번 조회하든 두 번 조회하든 같은 결과로 조회된다.
- PUT은 결과를 대체하기 때문에 같은 요청을 여러 번 해도 최종 결과는 동일하다.
- DELETE는 결과를 삭제하기 때문에 같은 요청을 여러 번 해도 삭제된 결과는 동일하다.
- PUT은 멱등이 아니다. 두 번 호출하면 같은 결제가 중복해서 발생해서 새로운 리소스로 구별이 된다.

> 사용자 1 : GET ➡️ username: A, age: 20
> 사용자 2 : PUT ➡️ username: A, age: 30
> 사용자 3 : GET ➡️ username: A, age: 30

멱등은 외부 요인으로 중간에 리소스가 변경되는 것까지 고려하지 않는다. 클라이언트가 동일한 요청을 똑같은 클라이언트가 동일한 요청했을 때만 멱등한다. 즉 멱등은 동시성 문제까지 고려하지 않는다. 

 

#### **멱등의 활용** 

자동 복구 매커니즘로 활용할 수 있다. 클라이언트가 자동 DELETE를 호출했는데 서버에서 잘 되고 있는지 안 되고 있는지 응답이 없다. 클라이언트가 다시 자동 DELETE를 재시도 해도 멱등한다. 실무에서 이런 전반적으로 자동 복구 매커니즘을 많이 사용한다. 

 

#### **캐시가능(Cacheable Methods)**

웹 브라우저에 용량이 큰 이미지를 한번 요청을 하면 그 다음에 똑같이 용량이 큰 이미지를 요청할 필요없다. 똑같은 이미지를 서버에서 다운로드 받으면 오래 걸린다. 그래서 로컬 PC에 웹 브라우저 저장을 하고 있을 때 캐시라고 한다. 캐시는 GET, HEAD, POST, PATCH 가능 하지만 실제로는 GET, HEAD 정도만 캐시로 사용한다. POST, PATCH는 캐시를 하려면 본문 내용으로 리소스랑 캐시 키가 맞아아야 되는데 복잡해서 구현이 쉽지 않다. GET, HEAD는 URI만 캐시 키로 캐시해서 간단하다. 