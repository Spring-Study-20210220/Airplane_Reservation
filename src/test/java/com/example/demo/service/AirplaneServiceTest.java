package com.example.demo.service;

import com.example.demo.airline.Airline;
import com.example.demo.airline.AirlineService;
import com.example.demo.airline.dto.AirlineDto;
import com.example.demo.airplain.*;
import com.example.demo.airplain.dto.AirplaneDto;
import com.example.demo.user.AdminAuthorizeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AirplaneServiceTest {
    private static final String TEST_AIRLINE_NAME = "testName";
    private static final String TEST_AIRLINE_COUNTRY = "testCountry";
    private static final Long TEST_AIRLINE_ID = 1L;
    private static final String TEST_ADMIN_ID = "1";

    private static final String TEST_AIRPLANE_NAME = "testName";
    private static final LocalDateTime TEST_TAKEOFFTIME_NAME = LocalDateTime.of(2021,3,26,12,0);
    private static final LocalDateTime TEST_LANDINGTIME_NAME = LocalDateTime.of(2021,3,27,12,0);
    private static final Place TEST_TAKEOFF = Place.CANADA;
    private static final Place TEST_LANDING = Place.RUSSIA;
    private static final AirplaneType TEST_TYPE = AirplaneType.A220;

    @InjectMocks
    private AirplaneService airplaneService;
    @Mock
    private AirplaneRepository airplaneRepository;
    @Mock
    private AirlineService airlineService;
    @Mock
    private AdminAuthorizeService adminAuthorizeService;
    @Spy
    private Airline mockAirline;

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
        Airline airline = Airline.builder()
                .name(TEST_AIRLINE_NAME)
                .country(TEST_AIRLINE_COUNTRY)
                .build();
        // 항공사 id & Authorization id > 항공기 생성
        // reqDto -> 항공기 생성, 항공사 id를 통한 항공사와 항공기간 연관관계 맺어줌
        //given(airlineService.findById(any()))
        given(airplaneRepository.findByName(any())).willReturn(Optional.empty());
        given(airplaneRepository.save(any())).willReturn(reqDto.toEntity());
        given(airlineService.findById(any())).willReturn(airline);

        AirplaneDto.Response resDto = airplaneService.airplaneCreate(1L,reqDto,"1");

        assertThat(resDto.getName()).isEqualTo(reqDto.getName());
        verify(airplaneRepository, times(1)).findByName(any());
        verify(airplaneRepository, times(1)).save(any());
    }
    @Test
    void 항공기조회_정상(){
        Airplane airplane = reqDto.toEntity();
        //제공된 항공사 id & Authorization id & 항공기 id
        given(airlineService.findById(any())).willReturn(mockAirline);
        given(mockAirline.findAirplaneById(any() ) ).willReturn(airplane);

        AirplaneDto.Response resDto = airplaneService.airplaneFind(1L,1L,"1");

        assertThat(resDto.getName()).isEqualTo(airplane.getName());
    }
}
