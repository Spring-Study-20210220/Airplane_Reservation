package com.example.demo.airplane;

import com.example.demo.airplane.dto.Request;
import com.example.demo.airplane.dto.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirplaneAcceptanceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers;

    @BeforeEach
    void setUp() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("비행기 정보 저장")
    void saveAirplaneInfo() {
        Request.SaveDto body = Request.SaveDto.builder()
                .departure("서울")
                .arrival("워싱턴")
                .time(LocalTime.now())
                .date(LocalDate.now())
                .build();

        HttpEntity<Request.SaveDto> request = new HttpEntity<>(body ,headers);

        ResponseEntity<Response.SaveDto> responseEntity = restTemplate
                .postForEntity("/airplaneinfo/save", request, Response.SaveDto.class);

        Response.SaveDto response = responseEntity.getBody();

        Assertions.assertThat(response.getId()).isEqualTo(5L);
    }

    @Test
    @DisplayName("비행기 정보 목록 가져오기")
    void getAirplaneInfoList() {
        ResponseEntity<Response.AirplaneListDto> responseEntity = restTemplate
                .getForEntity("/airplaneinfo/list", Response.AirplaneListDto.class);

        Response.AirplaneListDto response = responseEntity.getBody();
    }

    @Test
    @DisplayName("비행기 정보 가져오기")
    void getAirplaneInfo() {

    }

}