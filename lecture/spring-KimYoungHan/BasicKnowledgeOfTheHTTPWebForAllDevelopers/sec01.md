# 섹션1. 인터넷 네트워크



## **1. 인터넷 통신**

#### **인터넷망에서 컴퓨터들은 어떻게 통신할까?**

예를 들어서 클라이언트 옆에 서버가 있으면 간단하게 케이블을 연결해서 'Hello, world!'라는 메시지를 보내면 통신이 된다. 그런데 클라이언트와 서버 사이에 인터넷망이라는 중간에 있으면 어떻게 될까? 클라이언트가 한국에 있고 서버가 미국에 있다면 한국에 있는 클라이언트가 'Hello, world'라는 메시지를 보내야 하는데 중간에 해저광 케이블이든 인공위성으로 보내든지 이런 수많은 중간 노드라고 하는 서버들을 걸쳐서 미국에 있는 서버에게 안전하게 메시지를 도착해야 한다. 어떤 규칙으로 미국에 있는 서버에게 안전하게 도착할 수 있는지 이해를 하려면 IP 프로토콜을 알아야 한다.

![img](https://blog.kakaocdn.net/dn/Jcc2D/btrupH43VLR/fb2G1YOI9rIkPtGGJ7QqPk/img.png)

------

## **2. IP (인터넷 프로토콜)**

#### **IP 주소 부여**



![img](https://blog.kakaocdn.net/dn/psyav/btruiRVEJ6v/ILjqnbR1XZc1x4mzadlB91/img.png)



#### **IP(인터넷 프로토콜) 역할**

- **지정한 IP 주소(Address)에 데이터 전달** 
- **패킷(Packet)이라는 통신 단위로 데이터 전달**

 

클라이언트에게 IP 주소, 서버에게도 IP 주소가 있어야 하고 서버에게 'Hello, world!' 메시지를 보낼 때 최소한의 규칙이 있어야 보낼 수 있는데 패킷이라는 규칙으로 보내야 한다.

#### **IP 패킷 정보**



![img](https://blog.kakaocdn.net/dn/bKPI6H/btrurTqrA6q/hQ321xSSf2NfH2KdOMIcH0/img.png)



메시지에 패킷의 정보인 출발지 IP와 목적지 IP 등등을 넣고 보내는데 IP 프로토콜에 의해서 서버들이 규약을 따르고 있다. 

#### **클라이언트 패킷 전달**



![img](https://blog.kakaocdn.net/dn/cei5gS/btruk41lbH6/1Pp1MufvH7XK7Jy38WrLn0/img.png)



인터넷망에서 내가 보낸 패킷을 서로 노드들끼리 출발지가 뭐고 목적지가 뭔지 이해를 하면서 서버 IP 주소인 200.200.200.2 까지 정확하게 도착한다.

#### **서버 패킷 전달**



![img](https://blog.kakaocdn.net/dn/cLkMdR/btruc5l8ygr/F8EgRqaXJ83k1UKHwQtOb0/img.png)



서버도 마찬가지로 새로 메시지를 만들어서 메시지에 패킷의 정보인 출발지 IP와 목적지 IP 등등을 넣고 클라이언트에게 보내는데 클라이언트가 보낼 때랑 서버가 보낼 때랑 인터넷 망이 복잡하기 때문에 서로 다른 곳으로 전달 될 수 있다. 그래서 IP라는 패킷에 담는 방식으로는 한계가 있다. 

#### **IP 프로토콜의 한계**

- **비연결성**



![img](https://blog.kakaocdn.net/dn/esbHDr/btrusOJdlJQ/audGN40QkKuewGeOjEbyK0/img.png)



미국에 있는 서버가 PC가 꺼져 있는 것도 모르고 클라이언트는 계속 메시지를 보내게 된다. 그래서 패킷을 받을 대상이 없거나 서비스불능 상태여도 패킷은 계속 전송한다.

- **비신뢰성** 



![img](https://blog.kakaocdn.net/dn/ci3x04/btrupHqvN50/FZtIihYKBO0gxexKuyIxe0/img.png)



서버들이 전달하는 사이에서 한 서버가 문제가 생겨서 패킷이 유실이 된다.



![img](https://blog.kakaocdn.net/dn/cX9ysw/btruk3VFWm8/zVbkHpiIIYFgeBgkYXGEB0/img.png)



메시지를 한 번에 보낼 때 보통 1500byte가 넘어가게 되면 부담스러워서 나눠서 보낸다.

- 'Hello,' : 1500byte
- 'World!' : 1500byte 

 

두 개의 패킷을 나눠서 보내는데 인터넷 망안에 있는 노드들을 통해서 패킷마다 노드를 각각 선택을 해서 따로 따로 보내게 되는 상황이 오다보니 클라이언트가 보낸 메시지 순서가 서버가 받은 메시지 순서와 상이하게 된다.

- **같은 IP 내에 프로그램 미 구분**

 

클라이언트에서 게임도 하고 음악도 듣고 여러가지 프로그램으로 같은 IP로 쓰고 있는데 어떤 방식으로 구분되지 않아 한계가 있다.

------

## **3. TCP/ UDP**

#### **프로토콜 4계층**



![img](https://blog.kakaocdn.net/dn/bdZnc4/btrurTc1BuN/cH7HoevkQoWqKUgJG9FUk1/img.png)



IP 프로토콜에서 중간에 패킷이 손실되고 순서가 상이한 문제들을 TCP 프로토콜이 해결해준다. 'Hello world!' 라는 메시지를 보낼 때 SOCKET 라이브러리를 통해서 OS계층에서 TCP 정보를 감싼다. 그 밑에 IP 패킷이 생성되서 IP와 관련된 정보도 있고 그 안에 TCP와 관련된 정보가 있다. 이 메시지를 랜카드를 통해서 나갈 때 Ethernet frame을 포함해서 나가게 된다. 

\* Ethernet frame : 램 카드에 등록된 맥주소의 물리적인 정보

#### **TCP/IP 패킷 정보**



![img](https://blog.kakaocdn.net/dn/cd8RUC/btrulR1awJM/LEZ8oIcuCdtmOgYR8CaYWK/img.png)



TCP와 관련된 정보에는 출발지 PORT, 목적지 PORT, 전송 제어, 순서, 검증 정보 등이 들어 간다. IP 프로토콜에서 해결이 안된 순서 제어 문제들이 TCP 프로토콜이 해결이 되고 전송 데이터를 넣게 된다.

#### **TCP(Transmission Control Protocol : 전송 제어 프로토콜)의 특징**

- **TCP 3 way handshake (가상 연결)**

  ![img](https://blog.kakaocdn.net/dn/bfv747/btrurTKQGnC/sxrFLKsb2XD6TPu324eOQK/img.png)

- SYN(synchronize) : 접속 요청
- ACK(acknowledge) : 요청 수락

 

1. 클라이언트에서 먼저 SYN 메시지를 서버에게 접속을 허락해달라고 요청한다.
2. 서버는 접속을 수락하고 ACK 메시지를 클라이언트 한테 보낼 때 서버도 접속을 허락해달라고 SYN 메시지와 함께 보낸다.
3. 클라이언트가 접속을 수락하고 서버에서 ACK 메시지를 보낸다.

  👉🏻 참고로 요즘 최적화가 잘 되서 마지막 ACK를 보낼 때 데이터도 전송한다.

4. 3단계를 거쳐 연결이 되고나면 데이터를 전송한다. 

 

위에 TCP 연결이 되었다고 연결이 된 게 아니라 개념적으로 연결되어 있을 뿐이다. 인터넷 망에 있는 수 많은 서버들이 연결되어 있는 건지 잘 모른다. 

 

- **데이터 전달 보증**



![img](https://blog.kakaocdn.net/dn/KayGf/btruc5zHyCW/BCCL4I1haBQmkxVQhtkWQ0/img.png)



메시지에 TCP 프로토콜이 포함되어 있으면 메시지를 전송할 때 서버에서 잘 받았다고 다시 보내는데 클라이언트가 메시지를 잘 전달 됐는지 안됐는지 이해를 할 수 있다. 

- **순서보장**



![img](https://blog.kakaocdn.net/dn/bvsK0D/btrunOX4v2R/zce4eW5ImJxTFrK5H2XtSk/img.png)



메시지가 1500byte가 넘어서 패킷을 1번, 2번, 3번 순서로 나눠서 보냈다. 서버에서 1번, 3번, 2번 순서로 도착을 하면 내부적으로 최적화하는 로직에 따라서 2번부터 다시 보내라고 클라이언트에 보낸다. 그래서 순서가 보장이 된다. 순서대로 보낼 수 있었거는 TCP 정보 안에는 전송 제어, 순서, 검증 정보가 있어서 TCP 프로토콜이 신뢰할 수 있는 프로토콜이라고 한다.

#### **UDP(User Datagram Protocol : 사용자 데이터그램 프로토콜)의 특징**

- **TCP 3 way handshake X**
- **데이터 전달 보증 X**
- **순서 보장 X**  

 

TCP는 데이터 양도 많고 3 way hands 때문에 전송 속도가 느린 반면에 UDP는 아무것도 없기 때문에 상대적으로 전송 속도가 빠르다. 클라이언트 PC에서 IP가 한 개만 할당되어 있어 있는데 게임용, 음악용 등 구분하기위해 PORT를 사용하고 메시지에 대해서 맞는지 검증해 주는 체크섬 특징이 있다.

------

## **4. PORT**

#### **한 번에 둘 이상 연결해야 하면?**



![img](https://blog.kakaocdn.net/dn/pvzxB/btrunORxgWr/Kl6kWPD4RbKcJbafliQXLk/img.png)



클라이언트에서 게임도 하고 화상통화도 하고 웹 브라우저도 하고 있으면 여러 개의 서버랑 통신 해야 된다. 클라이언트 IP에서 패킷이 올텐데 이게 게임에서 온 패킷인지 화상통화에서 온 패킷인지 웹 브라우저에서 온 패킷인지 알 수가 없다. 반대로도 마찬가지이다. 이러한 문제로 어떻게 구분할 수 있을까? 

#### **TCP/IP 패킷 정보**



![img](https://blog.kakaocdn.net/dn/cd8RUC/btrulR1awJM/LEZ8oIcuCdtmOgYR8CaYWK/img.png)



위에 봤던 TCP/IP 패킷 정보에 TCP와 UDP에서 출발지 PORT와 목적지 PORT가 있다. IP는 목적지 서버를 찾는 용도이고 서버 안에서 돌아가는 애플리케이션들을 구분하는게 PORT이다. 

#### **같은 IP 내에서 프로세스 구분**



![img](https://blog.kakaocdn.net/dn/xjIZv/btrulRUuCY2/SGGkliiZS2lZrQVQkK9miK/img.png)



같은 IP 내에서 프로세스를 구분하는 게 PORT 이다. 

- [클라이언트] 게임 : 8090 ↔ [서버] 게임 : 11200
- [클라이언트] 화상통화 : 21000 ↔ [서버] 화상통화 : 32202
- [클라이언트] 웹 브라우저 : 10010 ↔ [서버] 웹 브라우저 : 80

 

위와 같이 예시처럼 각각 클라이언트와 서버 안에 맞는 PORT 번호를 찾아서 연결하면 된다. 여기서 패킷을 보낼 때 IP와 PORT를 포함해서 보낸다.

#### **PORT 번호**

- 0 ~ 65535 : 할당 가능
- 0 ~ 1023 : 잘 알려진 포트라 사용하지 않는 것이 좋음
- FTP : 20, 21 / TELNET : 23 / HTTP : 80 / HTTPS : 443 

 

------

## **5. DNS**

#### **IP 주소의 문제점**



![img](https://blog.kakaocdn.net/dn/bKhGow/btrurSZKjn4/BRF2kaS9C9ILmrcrStekK1/img.png)![img](https://blog.kakaocdn.net/dn/bd4SEV/btrupJbcD23/h243tujxxb3M6y2Z1sl1J0/img.png)



IP를 가지고 서로 통신을 할 수 있지만 IP가 숫자로 되어 있어서 다 기억하기가 어렵다. 그리고 IP가 바뀌는 일이 많아져서 접속이 안되는 경우가 생긴다.

#### **DNS (Domain Name System : 도메인 네임 시스템)**



![img](https://blog.kakaocdn.net/dn/T556Y/btruqEOcbra/Ki154WmTWBlRnuOk4cbqKk/img.png)

이러한 문제를 해결하기 위해 DNS 가 생겼다. 도메인을 사기 위해서 DNS 서버에 도메인을 등록을 할 수 있다. 클라이언트가 DNS 서버에다가 도메인에 맞는 IP를 달라고 요청을 하면 DNS 서버가 응답을 하고 클라이언트는 서버에 도메인으로 접속할 수 있다. 나중에 IP가 변경 되면 DNS 서버에 등록된 도메인에 IP를 변경을 할 수있다.
