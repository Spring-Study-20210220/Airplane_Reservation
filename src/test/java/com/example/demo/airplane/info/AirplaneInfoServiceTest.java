package com.example.demo.airplane.info;

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
public class AirplaneInfoServiceTest {

    @Mock
    private AirplaneInfoRepository airplaneInfoRepository;

    @InjectMocks
    private AirplaneInfoService airplaneInfoService;

    @Test
    @DisplayName("비행기 정보가 제대로 저장되는지 테스트")
    void saveAirplaneInfoTest() {

        AirplaneInfo airplaneInfo = AirplaneInfo
                .builder()
                .id(1L)
                .departure("서울")
                .arrival("워싱턴")
                .takeOffDate(LocalDate.now())
                .takeOffTime(LocalTime.now())
                .build();

        given(airplaneInfoRepository.save(any())).willReturn(airplaneInfo);

        Long result = airplaneInfoService.save(airplaneInfo);

        Assertions.assertThat(result).isEqualTo(airplaneInfo.getId());
        verify(airplaneInfoRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("비행기 정보 가져오는 테스트")
    void getAirplaneInfoTest() {

        given(airplaneInfoRepository.findById()).willReturn();
    }
}
