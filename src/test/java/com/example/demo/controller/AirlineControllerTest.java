package com.example.demo.controller;

import com.example.demo.airline.dto.AirlineDto;
import com.example.demo.user.dto.AuthDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureWebTestClient
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

    @BeforeEach
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

        AirlineDto.Response airlineResDto = webTestClient.post()
                .uri("/api/Airline")
                .header("Authorization", String.valueOf(testAdminId))
                .body(Mono.just(airlineReqDto), AirlineDto.Request.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(AirlineDto.Response.class)
                .returnResult()
                .getResponseBody();
        testAirlineId = airlineResDto.getId();
    }

    @Test
    void 항공사생성() {
        //given
        AirlineDto.Request airlineReqDto = AirlineDto.Request.builder()
                .name(TEST_AIRLINE_NAME)
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
        assertThat(airlineResDto.getName()).isEqualTo(airlineReqDto.getName());
        assertThat(airlineResDto.getCountry()).isEqualTo(airlineReqDto.getCountry());
    }

    @Test
    void 항공사제거() {
        //given
        //when
        webTestClient.delete()
                .uri("/api/Airline/" + testAirlineId)
                .header("Authorization", String.valueOf(testAdminId))
                .exchange()
                .expectStatus().isOk();
        //then
    }

    @Test
    void 항공사조회() {
        //given
        System.out.println(testAdminId);
        System.out.println(testAirlineId);
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
