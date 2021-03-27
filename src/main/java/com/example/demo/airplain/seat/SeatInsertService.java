package com.example.demo.airplain.seat;

import com.example.demo.airplain.seat.dto.SeatInsertRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatInsertService {
    private final SeatRepository seatRepository;

    public void insertSeat(SeatInsertRequestDto reqDto){
        Seat seat = Seat.builder()
                .seatClass(reqDto.getSeatClass())
                .seatInfo(reqDto.getSeatInfo())
                .build();
        seatRepository.save(seat);
    }
}
