package com.example.demo.airplain.seat.dto;

import com.example.demo.airplain.seat.SeatClass;
import com.example.demo.airplain.seat.SeatInfo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SeatInsertRequestDto {
    private SeatInfo seatInfo;
    private SeatClass seatClass;

    @Builder
    SeatInsertRequestDto(SeatInfo seatInfo, SeatClass seatClass) {
        this.seatClass = seatClass;
        this.seatInfo = seatInfo;
    }
}
