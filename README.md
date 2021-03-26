# Airplane_Reservation

## 요구사항
- 항공사와 사용자가 존재한다 (o)
- 유저는 아이디 비밀번호 유저등급 권한을 가지고 있어야한다 (-)
- 비행기는 목적지 출발지 남은 좌석 좌석 등급을 가격 포함해야한다 (o)
- 사용자는 특정 비행기의 특정 좌석을 예약할 수 있다 (o)
- 유저는 예약을 취소할 수 있다 (o)
- 결제한 목록을 가지고 특정 조건을 만족한다면 유저의 등급을 정해줄 수 있다 (o)
- 유저는 특정 항공사의 항공편을 조회할 수 있다 (o)
- 관리자는 특정 항공사의 항공편을 등록 삭제 수정이 가능하다 (?)

## 기술 스택
- Spring boot
- Spring MVC
- Spring Data JPA

## ER Diagram
![Airplane_Reservation](./img/Airplane_Reservation(1).png)
<br></br>
<br></br>
![Airplane_Reservation](./img/Airplane_Reservation(2)%20(1).png)

## API 명세

### AirplaneInfo

|HttpMethod|url|parameters|
|---|---|---|
|POST|airplane-info| departure : String, arrival : String, take_off_date : Date|
|GET|airplane-info/list||
|GET|airplane-info|id : Long, departure : String, arrival : String, take_off_date : Date|  

### AirplaneTicket

|HttpMethod|url|parameters|
|---|---|---|
|POST|airplane-ticket|airplane_info_id : Long, class : String|
|GET|airplane-ticket/{airplane-info-id}/list||

### Reservation

|HttpMethod|url|parameters|
|---|---|---|
|POST|reservation|user_id : Long, ticket_id : Long|
|GET|reservation/{user-id}/list||
|PATCH|reservation/{reservation-id}|status : String|

### User

|HttpMethod|url|parameters|
|---|---|---|
|POST|user|email : String, password : String|

### Authentication

|HttpMethod|url|parameters|
|---|---|---|
|POST|sign-in|email : String, password : String|
|GET|sign-out|

## 오류사항 및 기술도입
- DB 초기화를 위한 data.sql 도입기
- EventListener 도입기
- user와 ticket사이의 관계성 설정 (다대다, 일대다)
