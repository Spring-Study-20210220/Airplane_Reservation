package com.example.demo.service;

import com.example.demo.airline.Airline;
import com.example.demo.airline.AirlineRepository;
import com.example.demo.airline.AirlineService;
import com.example.demo.airline.dto.AirlineDto;
import com.example.demo.user.Admin;
import com.example.demo.user.AdminAuthorizeService;
import com.example.demo.user.AdminRepository;
import com.example.demo.user.Authservice;
import com.example.demo.user.dto.AuthDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AirlineServiceTest {
    private static final String TEST_AUTH_LOGIN_ID = "testerId";
    private static final String TEST_AUTH_NAME = "testerName";
    private static final String TEST_AUTH_PASSWORD = "testerPW";
    private static final String TEST_AIRLINE_NAME = "testName";
    private static final String TEST_AIRLINE_COUNTRY = "testCountry";
    private static final String TEST_ADMIN_ID = "1";
    @InjectMocks
    private AirlineService airlineService;
    @Mock
    private AirlineRepository airlineRepository;
    @Mock
    private AdminAuthorizeService adminAuthorizeService;

    @BeforeAll
    void setUp() {
        AirlineDto.Request reqDto = AirlineDto.Request.builder()
                .name(TEST_AIRLINE_NAME)
                .country(TEST_AIRLINE_COUNTRY)
                .build();

        Airline airline = Airline.builder()
                .name(reqDto.getName())
                .country(reqDto.getCountry())
                .build();

        given(airlineRepository.save(any())).willReturn(airline);
        given(adminAuthorizeService.authorize(any())).willReturn(true);

        AirlineDto.Response resDto = airlineService.airlineCreate(reqDto,TEST_ADMIN_ID);
    }

    @Test
    void 항공사생성_정상() {
        AirlineDto.Request reqDto = AirlineDto.Request.builder()
                .name(TEST_AIRLINE_NAME)
                .country(TEST_AIRLINE_COUNTRY)
                .build();

        Airline airline = Airline.builder()
                .name(reqDto.getName())
                .country(reqDto.getCountry())
                .build();

        given(airlineRepository.save(any())).willReturn(airline);

        AirlineDto.Response resDto = airlineService.airlineCreate(reqDto,TEST_ADMIN_ID);

        assertThat(resDto.getName()).isEqualTo(reqDto.getName());
        assertThat(resDto.getCountry()).isEqualTo(resDto.getCountry());
    }

    @Test
    void 항공사삭제_정상() {
        airlineService.airlineDelete(1L,"1");
        verify(airlineRepository,times(1)).delete(any());
    }

    @Test
    void 항공사수정_정상() {
        AirlineDto.Request reqDto = AirlineDto.Request.builder()
                .name("updateName")
                .country("updateCountry")
                .build();
        AirlineDto.Response resDto =  airlineService.airlineUpdate(1L,reqDto,"1");

        assertThat(resDto.getName()).isEqualTo(reqDto.getName());
        assertThat(resDto.getCountry()).isEqualTo(reqDto.getCountry());
    }

    @Test
    void 항공사조회_정상() {
        AirlineDto.Response resDto = airlineService.airlineFind(1L,"1");

        assertThat(resDto.getName()).isEqualTo(TEST_AIRLINE_NAME);
        assertThat(resDto.getCountry()).isEqualTo(TEST_AIRLINE_COUNTRY);
    }
}
