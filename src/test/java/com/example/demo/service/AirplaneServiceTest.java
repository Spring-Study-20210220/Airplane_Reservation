package com.example.demo.service;

import com.example.demo.airplain.*;
import com.example.demo.airplain.dto.AirplaneDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.TestComponent;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class AirplaneServiceTest {

    private static final String TEST_AIRPLANE_NAME = "testName";
    private static final LocalDateTime TEST_TAKEOFFTIME_NAME = LocalDateTime.of(2021,3,26,12,0);
    private static final LocalDateTime TEST_LANDINGTIME_NAME = LocalDateTime.of(2021,3,27,12,0);
    private static final Place TEST_TAKEOFF = Place.CANADA;
    private static final Place TEST_LANDING = Place.RUSSIA;
    private static final AirplaneType TEST_TYPE = AirplaneType.A220;

    @InjectMocks
    private AirplainService airplainService;
    @Mock
    private AirplainRepository airplainRepository;


    private static Airplain airplain;
    private static AirplaneDto.Request reqDto;


    @BeforeAll
    void setUp() {
        reqDto = AirplaneDto.Request.builder()
                .name(TEST_AIRPLANE_NAME)
                .takeOffTime(TEST_TAKEOFFTIME_NAME)
                .landingTime(TEST_LANDINGTIME_NAME)
                .takeOff(TEST_TAKEOFF)
                .landing(TEST_LANDING)
                .airplaneType(TEST_TYPE)
                .build();
    }

    @Test
    void 항공기생성_정상() {

    }

}
