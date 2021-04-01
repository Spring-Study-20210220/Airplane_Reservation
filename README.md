## ✈ 항공 예약 시스템
(2021.03.20 ~ 03.27)  
이용자가 항공편의 좌석을 예약할 수 있는 시스템


### Team1 Member
| 🙍‍♀️우혜진 | 🙍‍♂️이용우 |
| :----: | :----: |
| [@HJ-Woo](https://github.com/HJ-Woo) | [@timel2ss](https://github.com/timel2ss)  |

### ✔ 요구사항
- 항공사와 사용자가 존재한다
- 유저는 아이디 비밀번호 유저등급 권한을 가지고 있어야한다
- 비행기는 목적지 출발지 ~남은 좌석~ 좌석 등급을 가격 포함해야한다
- 사용자는 특정 비행기의 특정 좌석을 예약할 수 있다
- 유저는 예약을 취소할 수 있다
- 예약한 표에서 결제를 진행할 수 있다
- 결제한 목록을 가지고 특정 조건을 만족한다면 유저의 등급을 정해줄 수 있다
- 유저는 특정 항공사의 항공편을 조회할 수 있다
- 관리자는 특정 항공사의 항공편을 등록 삭제 수정이 가능하다

### 🛠 기술스택
- **Spring Boot** (2.4.3), **Java** (1.8), **Gradle**
- **Spring Data JPA**
- **H2 Database**
- **Lombok**
- **Test**: Mockito, TestRestTemplate
> 💁‍♀️ 기술 스택 선정 이유
> - Java 기반으로 MVC 패턴을 적용한 API를 개발하기 위해 Spring Boot를 선택
>
> - OOP에 집중하고자 Database로의 접근은 ORM을 이용한 Spring JPA를, DB는 embedded H2를 사용
>
> - Unit test를 위해 Mock와 RestTemplate을 활용
### Entity 분석
![image](https://user-images.githubusercontent.com/59992230/112981593-63c66380-9196-11eb-8cfe-565995a716b0.png)

### API document
**User**
|Function|URL|Method|
|--------|---|------|
|회원가입|/api/user/new|POST|
|회원조회|/api/user/{id}|GET|

**Airline**
|Function|URL|Method|
|--------|---|------|
|항공사 등록|/api/airline/new|POST|
|특정 항공사 조회|/api/airline/{id}|GET|
|항공사 전체 조회|/api/airlines|GET|

### ✍ issue
- ``@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)`` 환경에서
  ``Transaction Rollback``이 이루어지지 않는 현상
  [참고](https://stackoverflow.com/questions/46729849/transactions-in-spring-boot-testing-not-rolled-back)
  <details>
    <summary>자세히</summary>

  > If your test is @Transactional, it rolls back the transaction at the end of each test method by default. However, as using this arrangement with either RANDOM_PORT or DEFINED_PORT implicitly provides a real servlet environment, the HTTP client and server run in separate threads and, thus, in separate transactions. Any transaction initiated on the server does not roll back in this case.

  > 이처럼 공식문서에 기재된 바에 의하면, 실제 환경에서 running 시키는 *26.3.6. Testing with a running server* 와 같은 환경, 즉 우리가 사용했떤 ``@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT``의 설정은 실제 서블릿 환경인 HTTP 클라이언트와 서버가 각각의 별도 스레드에서 실행된다. 이때 서버에서 시작된 트랜잭션은 롤백되지 않으므로 테스트 수행 코드가 반영됨을 기억해야한다.

  </details>


- ``@TestInstance``? ``@BeforeAll static``? [참고](https://www.baeldung.com/junit-testinstance-annotation) [참고2](https://stackoverflow.com/questions/52551718/what-use-is-testinstance-annotation-in-junit-5)

  <details>
    <summary>자세히</summary>

  > 기본적으로 Junit4와 5는 **각 테스트 메서드를 실행하기 전**에 테스트 클래스의 **새 인스턴스**를 만든다. 이렇게하면 **테스트간에 상태가 명확하게 분리**된다.

  > 여러 테스트 메서드에 걸쳐서 객체가 필요한 경우, Junit5에서는 ``@BeforeAll``을
  > - 클래스의 **static 메서드**에 사용하면, 클래스의 **static member와 함께** 사용 가능
  > - **Test Instance의 Life cycle이 per-class**로 변경되면 (``@TestInstance (Lifecycle.PER_CLASS)``), ``@BeforeAll``을 **instance 메서드에 사용하여 instance member**들을 함꼐 사용 가능

  > 💥 **Problem** 💥 [참고 with Mockito git issue](https://github.com/mockito/mockito/issues/1437)
  > 
  > 기본적으로는 ``@TestInstance`` 의 사용을 권장하나, 다음과 같은 Mockito 문제가 발생한 경우에 감당하지 못하는 문제가 생긴다. 
  >![image](https://user-images.githubusercontent.com/59992230/112656304-9ae5fd80-8e94-11eb-9633-80049c0deb72.png)
  ![image](https://user-images.githubusercontent.com/59992230/112656333-a46f6580-8e94-11eb-87f4-8d0f4545af3a.png)
  >
  > 둘은 같은 life cycle을 공유중인 UserServiceTest에서 ``유저정보를조회한다()`` 메소드와 ``유저를저장한다()`` 메서드 호출시의 Mock 객체 인스턴스 값 변화를 보여주고 있다.
  > 
  > 자세히 살펴보자면 중간에 Mock 객체들의 address가 변경되는데, 반면에 ``@InjectMocks`` 객체에게는 변화가 일어나지 않는다.  
  > 즉, ``@InjectMocks`` ``UserService``는 이전의 Mock 객체들을 주입받았는데, 이후 메소드에서는 새로운 Mock 객체에서 ``willReturn()``을 정의하였으므로, 원하는 return을 받지 못하고 계속 null로 반환하여 NPE가 발생한다.
  > 
  > 해당 문제에 대하여 Mockito issue란에서 2020.12.12까지 개발자간의 토론이 이루어졌으나, Mockito 측의 답변은 없다.
  > 
  > 고로 우리는 ``static @BeforeAll``을 사용하여 해당 문제를 해결하였다.

  </details>

