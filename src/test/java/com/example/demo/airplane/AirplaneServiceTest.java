package com.example.demo.airplane;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AirplaneServiceTest {

    @Mock
    private AirplaneRepository airplaneRepository;

    @InjectMocks
    private AirplaneService airplaneService;

    @Test
    @DisplayName("비행기 정보가 제대로 저장되는지 테스트")
    void saveAirplaneInfoTest() {

        Airplane airplane = Airplane
                .builder()
                .id(1L)
                .departure("서울")
                .arrival("워싱턴")
                .takeOffDate(LocalDate.now())
                .takeOffTime(LocalTime.now())
                .build();

        given(airplaneRepository.save(any())).willReturn(airplane);

        Long result = airplaneService.save(airplane);

        Assertions.assertThat(result).isEqualTo(airplane.getId());
        verify(airplaneRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("비행기 정보 가져오는 테스트")
    void getAirplaneInfoTest() {

        //given(airplaneInfoRepository.findById()).willReturn();
    }
}
