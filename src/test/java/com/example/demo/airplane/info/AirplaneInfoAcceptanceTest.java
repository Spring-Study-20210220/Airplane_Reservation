package com.example.demo.airplane.info;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirplaneInfoAcceptanceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("비행기 정보 저장")
    void saveAirplaneInfo() {

    }

    @Test
    @DisplayName("비행기 정보 업데이트")
    void updateAirplaneInfo() {

    }

    @Test
    @DisplayName("비행기 정보 가져오기")
    void getAirplaneInfo() {

    }

    @Test
    @DisplayName("비행기 정보 목록 가져오기")
    void getAirplaneInfoList() {

    }

}
/*
post - save
update - update
get - info
get - infolist
 */