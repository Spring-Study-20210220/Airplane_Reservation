package com.example.demo.controller;

import com.example.demo.airline.Airline;
import com.example.demo.airline.dto.AirlineDto;
import com.example.demo.airplain.Airplane;
import com.example.demo.airplain.AirplaneType;
import com.example.demo.airplain.Place;
import com.example.demo.airplain.dto.AirplaneDto;
import com.example.demo.user.dto.AuthDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.internal.junit.JUnitRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureWebTestClient
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirplaneControllerTest {
    private static final String TEST_AIRPLAIN_NAME="testAirplaneName";
    private static LocalDateTime testLandingTime;
    private static LocalDateTime testTakeOffTime;

    private static final String TEST_AUTH_LOGIN_ID = "testerId";
    private static final String TEST_AUTH_NAME = "testerName";
    private static final String TEST_AUTH_PASSWORD = "testerPW";

    private static final String TEST_AIRLINE_NAME = "testAirlineName2";
    private static final String TEST_AIRLINE_COUNTRY = "testAirlineCountry";

    private static final String TEST_AIRPLANE_NAME = "testName";
    private static final LocalDateTime TEST_TAKEOFFTIME_NAME = LocalDateTime.of(2021,3,26,12,0);
    private static final LocalDateTime TEST_LANDINGTIME_NAME = LocalDateTime.of(2021,3,27,12,0);
    private static final Place TEST_TAKEOFF = Place.CANADA;
    private static final Place TEST_LANDING = Place.RUSSIA;
    private static final AirplaneType TEST_TYPE = AirplaneType.A220;



    @Autowired
    private WebTestClient webTestClient;

    private Long testAdminId;
    private Long testAirlineId;
    private Long testAirplaneId;

    @BeforeAll
    void setup(){
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

        testLandingTime = LocalDateTime.of(2020,03,30,12,30);
        testTakeOffTime = LocalDateTime.of(2020,03,28,12,30);
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

        AirplaneDto.Response resDto = webTestClient.post()
                .uri("/api/Airline/"+testAirlineId+"/Airplane")
                .header("Authorization", String.valueOf(testAdminId))
                .body(Mono.just(reqDto), AirplaneDto.Request.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(AirplaneDto.Response.class)
                .returnResult()
                .getResponseBody();

        assertThat(resDto.getName()).isEqualTo(reqDto.getName());
        assertThat(resDto.getAirline().getName()).isEqualTo(TEST_AIRLINE_NAME);
        assertThat(resDto.getAirplaneType()).isEqualTo(reqDto.getAirplaneType());
    }

    @Test
    void 항공기조회() {

        AirplaneDto.Request reqDto = AirplaneDto.Request.builder()
                .name(TEST_AIRPLAIN_NAME+ 2)
                .landing(Place.JAPAN)
                .landingTime(testLandingTime)
                .takeOff(Place.SOUTH_KOREA)
                .takeOffTime(testTakeOffTime)
                .airplaneType(AirplaneType.A220)
                .build();

        AirplaneDto.Response resDto = webTestClient.post()
                .uri("/api/Airline/"+testAirlineId+"/Airplane")
                .header("Authorization", String.valueOf(testAdminId))
                .body(Mono.just(reqDto), AirplaneDto.Request.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(AirplaneDto.Response.class)
                .returnResult()
                .getResponseBody();

        testAirplaneId = resDto.getId();

        AirplaneDto.Response resDto2 = webTestClient.get()
                .uri("/api/Airline/"+testAirlineId+"/Airplane/"+testAirplaneId)
                .header("Authorization", String.valueOf(testAdminId))
                .exchange()
                .expectStatus().isOk()
                .expectBody(AirplaneDto.Response.class)
                .returnResult()
                .getResponseBody();

        assertThat(resDto2.getName()).isEqualTo(TEST_AIRPLAIN_NAME+ 2);

    }


}
