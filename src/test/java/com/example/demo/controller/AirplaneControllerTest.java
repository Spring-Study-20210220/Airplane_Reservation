package com.example.demo.controller;

import com.example.demo.airline.dto.AirlineDto;
import com.example.demo.airplain.AirplaneType;
import com.example.demo.airplain.Place;
import com.example.demo.airplain.dto.AirplaneDto;
import com.example.demo.user.dto.AuthDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@AutoConfigureWebTestClient
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirplaneControllerTest {
    private static final String TEST_AIRPLAIN_NAME="testAirplaniName";
    private static LocalDateTime testLandingTime;
    private static LocalDateTime testTakeOffTime;
    @Autowired
    private WebTestClient webTestClient;

    @BeforeAll
    static void setup(){
//        AuthDto.Request authReqDto = AuthDto.Request.builder()
//                .login_id(TEST_AUTH_LOGIN_ID)
//                .name(TEST_AUTH_NAME)
//                .password(TEST_AUTH_PASSWORD)
//                .build();
//        AuthDto.Response adminResDto = webTestClient.post()
//                .uri("/api/Auth/SignUp/Admin")
//                .body(Mono.just(authReqDto), AuthDto.Request.class)
//                .exchange()
//                .expectBody(AuthDto.Response.class)
//                .returnResult()
//                .getResponseBody();
//
//        testAdminId = adminResDto.getId();
//
//        AirlineDto.Request airlineReqDto = AirlineDto.Request.builder()
//                .name(TEST_AIRLINE_NAME)
//                .country(TEST_AIRLINE_COUNTRY)
//                .build();
//
//        AirlineDto.Response airlineResDto = webTestClient.post()
//                .uri("/api/Airline")
//                .header("Authorization", String.valueOf(testAdminId))
//                .body(Mono.just(airlineReqDto), AirlineDto.Request.class)
//                .exchange()
//                .expectStatus().isCreated()
//                .expectBody(AirlineDto.Response.class)
//                .returnResult()
//                .getResponseBody();
//        testAirlineId = airlineResDto.getId();

        LocalDateTime testLandingTime = LocalDateTime.of(2020,03,30,12,30);
        LocalDateTime testTakeOffTime = LocalDateTime.of(2020,03,28,12,30);
    }

    @Test
    void 항공기생성(){
        AirplaneDto.Request reqDto = AirplaneDto.Request.builder()
                .name(TEST_AIRPLAIN_NAME)
                .landing(Place.JAPAN)
                .landingTime(testLandingTime)
                .takeOff(Place.SOUTH_KOREA)
                .takeOffTime(testTakeOffTime)
                .airplaneType(AirplaneType.A220)
                .build();

        webTestClient.post().uri("/api/Airline/ "+ +"/Airplane")
    }


}
