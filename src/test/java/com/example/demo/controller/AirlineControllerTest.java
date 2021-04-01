package com.example.demo.controller;

import com.example.demo.Error.ErrorResponse;
import com.example.demo.airline.dto.AirlineDto;
import com.example.demo.user.dto.AuthDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureWebTestClient
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirlineControllerTest {
    private static final String TEST_AUTH_LOGIN_ID = "testerId";
    private static final String TEST_AUTH_NAME = "testerName";
    private static final String TEST_AUTH_PASSWORD = "testerPW";

    private static final String TEST_AIRLINE_NAME = "testAirlineName";
    private static final String TEST_AIRLINE_COUNTRY = "testAirlineCountry";

    @Autowired
    private WebTestClient webTestClient;

    private Long testAdminId;
    private Long testAirlineId;
    private Long testDeleteAirlineId;

    @BeforeAll
    void 항공사및관리자생성() {
        AuthDto.Request authReqDto = AuthDto.Request.builder()
                .login_id(TEST_AUTH_LOGIN_ID)
                .name(TEST_AUTH_NAME)
                .password(TEST_AUTH_PASSWORD)
                .build();
        AuthDto.Response adminResDto = webTestClient.post()
                .uri("/api/Auth/SignUp/Admin")
                .body(Mono.just(authReqDto), AuthDto.Request.class)
                .exchange()
                .expectBody(AuthDto.Response.class)
                .returnResult()
                .getResponseBody();

        testAdminId = adminResDto.getId();

        AirlineDto.Request airlineReqDto = AirlineDto.Request.builder()
                .name(TEST_AIRLINE_NAME)
                .country(TEST_AIRLINE_COUNTRY)
                .build();
        AirlineDto.Request airlineReqDto2 = AirlineDto.Request.builder()
                .name("테스트항공사")
                .country(TEST_AIRLINE_COUNTRY)
                .build();

        AirlineDto.Response airlineResDto = webTestClient.post()
                .uri("/api/Airline")
                .header("Authorization", String.valueOf(testAdminId))
                .body(Mono.just(airlineReqDto), AirlineDto.Request.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(AirlineDto.Response.class)
                .returnResult()
                .getResponseBody();
        AirlineDto.Response airlineResDto2 = webTestClient.post()
                .uri("/api/Airline")
                .header("Authorization", String.valueOf(testAdminId))
                .body(Mono.just(airlineReqDto2), AirlineDto.Request.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(AirlineDto.Response.class)
                .returnResult()
                .getResponseBody();
        testAirlineId = airlineResDto.getId();
        testDeleteAirlineId= airlineResDto2.getId();
    }

    @Test
    void 항공사생성() {
        //given
        AirlineDto.Request airlineReqDto = AirlineDto.Request.builder()
                .name("대한항공")
                .country(TEST_AIRLINE_COUNTRY)
                .build();
        //when
        AirlineDto.Response airlineResDto = webTestClient.post()
                .uri("/api/Airline")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", String.valueOf(testAdminId))
                .body(Mono.just(airlineReqDto), AirlineDto.Request.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(AirlineDto.Response.class)
                .returnResult()
                .getResponseBody();
        //then
        assertThat(airlineResDto.getName()).isEqualTo("대한항공");
        assertThat(airlineResDto.getCountry()).isEqualTo(airlineReqDto.getCountry());
    }
    @Test
    void 항공사중복생성(){
        //given
        AirlineDto.Request airlineReqDto = AirlineDto.Request.builder()
                .name("DuplicatedName")
                .country(TEST_AIRLINE_COUNTRY)
                .build();
        //when
        webTestClient.post()
                .uri("/api/Airline")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", String.valueOf(testAdminId))
                .body(Mono.just(airlineReqDto), AirlineDto.Request.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(AirlineDto.Response.class)
                .returnResult()
                .getResponseBody();

        ErrorResponse errRes = webTestClient.post()
                .uri("/api/Airline")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", String.valueOf(testAdminId))
                .body(Mono.just(airlineReqDto), AirlineDto.Request.class)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(ErrorResponse.class)
                .returnResult()
                .getResponseBody();
        //then
        assertThat(errRes.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(errRes.getCode()).isEqualTo("C005");
    }

    @Test
    void 비인가() {

        AirlineDto.Request airlineReqDto = AirlineDto.Request.builder()
                .name(TEST_AIRLINE_NAME)
                .country(TEST_AIRLINE_COUNTRY)
                .build();

        ErrorResponse errRes = webTestClient.post()
                .uri("/api/Airline")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", String.valueOf("12345"))
                .body(Mono.just(airlineReqDto), AirlineDto.Request.class)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(errRes.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(errRes.getCode()).isEqualTo("C003");
    }


    @Test
    void 항공사제거() {
        //given
        //when
        webTestClient.delete()
                .uri("/api/Airline/" + testDeleteAirlineId)
                .header("Authorization", String.valueOf(testAdminId))
                .exchange()
                .expectStatus().isOk();
        //then
    }

    @Test
    void 항공사조회() {
        //given

        //when
        AirlineDto.Response airlineResDto = webTestClient.get()
                .uri("/api/Airline/" + testAirlineId)
                .header("Authorization", String.valueOf(testAdminId))
                .exchange()
                .expectStatus().isOk()
                .expectBody(AirlineDto.Response.class)
                .returnResult()
                .getResponseBody();
        //then
        assertThat(airlineResDto.getId()).isEqualTo(testAirlineId);
    }

    @Test
    void 항공사수정() {
        //given
        AirlineDto.Request airlineReqDto = AirlineDto.Request.builder()
                .name("modifiedName")
                .country("modifiedCountry")
                .build();
        //when
        AirlineDto.Response airlineResDto = webTestClient.patch()
                .uri("/api/Airline/" + testAirlineId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", String.valueOf(testAdminId))
                .body(Mono.just(airlineReqDto), AirlineDto.Request.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AirlineDto.Response.class)
                .returnResult()
                .getResponseBody();
        //then
        assertThat(airlineResDto.getName()).isEqualTo(airlineReqDto.getName());
        assertThat(airlineResDto.getCountry()).isEqualTo(airlineReqDto.getCountry());
    }
}
