package com.example.demo.service;

import com.example.demo.Error.Exception.AirlineNameDuplicationException;
import com.example.demo.Error.Exception.UnAuthorizedUserException;
import com.example.demo.airline.Airline;
import com.example.demo.airline.AirlineRepository;
import com.example.demo.airline.AirlineService;
import com.example.demo.airline.dto.AirlineDto;
import com.example.demo.user.AdminAuthorizeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class AirlineServiceTest {
    private static final String TEST_AUTH_LOGIN_ID = "testerId";
    private static final String TEST_AUTH_NAME = "testerName";
    private static final String TEST_AUTH_PASSWORD = "testerPW";
    private static final String TEST_AIRLINE_NAME = "testName";
    private static final String TEST_AIRLINE_COUNTRY = "testCountry";
    private static final Long TEST_AIRLINE_ID = 1L;
    private static final String TEST_ADMIN_ID = "1";
    @InjectMocks
    private AirlineService airlineService;
    @Mock
    private AirlineRepository airlineRepository;
    @Mock
    private AdminAuthorizeService adminAuthorizeService;

    private static Airline airline;
    private static AirlineDto.Request reqDto;

    @BeforeAll
    static void setUp() {
        reqDto = AirlineDto.Request.builder()
                .name(TEST_AIRLINE_NAME)
                .country(TEST_AIRLINE_COUNTRY)
                .build();
        airline = Airline.builder()
                .name(TEST_AIRLINE_NAME)
                .country(TEST_AIRLINE_COUNTRY)
                .build();
    }

    @Test
    void 항공사생성_정상() {
        given(airlineRepository.save(any())).willReturn(airline);
        given(adminAuthorizeService.authorize(any())).willReturn(true);
        given(airlineRepository.findByName(any())).willReturn(Optional.empty());

        AirlineDto.Response resDto = airlineService.airlineCreate(reqDto,TEST_ADMIN_ID);

        assertThat(resDto.getName()).isEqualTo(reqDto.getName());
        assertThat(resDto.getCountry()).isEqualTo(resDto.getCountry());
    }

    @Test
    void 항공사중복생성() {
        given(airlineRepository.findByName(any())).willReturn(Optional.of(airline));
        given(adminAuthorizeService.authorize(any())).willReturn(true);
        Assertions.assertThatThrownBy(
                ()->airlineService.airlineCreate(reqDto,TEST_ADMIN_ID)
        ).isInstanceOf(AirlineNameDuplicationException.class);
    }

    @Test
    void 권한없음() {
        given(adminAuthorizeService.authorize(any())).willReturn(false);
        Assertions.assertThatThrownBy(
                ()->airlineService.airlineCreate(reqDto,TEST_ADMIN_ID)
        ).isInstanceOf(UnAuthorizedUserException.class);
    }

    @Test
    void 항공사삭제_정상() {
        given(airlineRepository.findById(any())).willReturn(Optional.of(airline));
        given(adminAuthorizeService.authorize(any())).willReturn(true);

        airlineService.airlineDelete(TEST_AIRLINE_ID,TEST_ADMIN_ID);

        verify(airlineRepository,times(1)).delete(any());
    }

    @Test
    void 항공사수정_정상() {
        Airline testAirline = Airline.builder()
                .name(TEST_AIRLINE_NAME)
                .country(TEST_AIRLINE_COUNTRY)
                .build();
        AirlineDto.Request updateReqDto = AirlineDto.Request.builder()
                .name("updateName")
                .country("updateCountry")
                .build();
        given(airlineRepository.findById(any())).willReturn(Optional.of(testAirline));
        given(adminAuthorizeService.authorize(any())).willReturn(true);

        AirlineDto.Response resDto = airlineService.airlineUpdate(TEST_AIRLINE_ID,updateReqDto,TEST_ADMIN_ID);

        assertThat(resDto.getName()).isEqualTo(updateReqDto.getName());
        assertThat(resDto.getCountry()).isEqualTo(updateReqDto.getCountry());
    }

    @Test
    void 항공사조회_정상() {
        given(airlineRepository.findById(any())).willReturn(Optional.of(airline));
        given(adminAuthorizeService.authorize(any())).willReturn(true);

        AirlineDto.Response resDto = airlineService.airlineFind(TEST_AIRLINE_ID,TEST_ADMIN_ID);

        assertThat(resDto.getName()).isEqualTo(TEST_AIRLINE_NAME);
        assertThat(resDto.getCountry()).isEqualTo(TEST_AIRLINE_COUNTRY);
    }
}
