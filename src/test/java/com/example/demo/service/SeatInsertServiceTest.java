package com.example.demo.service;

import com.example.demo.airplain.seat.SeatClass;
import com.example.demo.airplain.seat.SeatInfo;
import com.example.demo.airplain.seat.SeatInsertService;
import com.example.demo.airplain.seat.SeatRepository;
import com.example.demo.airplain.seat.dto.SeatInsertRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class SeatInsertServiceTest {
    @Mock
    private SeatRepository seatRepository;
    @InjectMocks
    private SeatInsertService seatInsertService;

    @Test
    void 좌석삽입테스트(){

        SeatInfo seatInfo = SeatInfo.builder()
                .alphabet("A")
                .row(13)
                .build();

        SeatInsertRequestDto seatInsertRequestDto = SeatInsertRequestDto.builder()
                .seatClass(SeatClass.BUSINESS)
                .seatInfo(seatInfo)
                .build();

        seatInsertService.insertSeat(seatInsertRequestDto);

        verify(seatRepository,times(1)).save(any());
    }
}
