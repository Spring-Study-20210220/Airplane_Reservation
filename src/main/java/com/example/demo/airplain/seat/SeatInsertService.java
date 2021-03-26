package com.example.demo.airplain.seat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatInsertService {
    private final SeatRepository seatRepository;


}
