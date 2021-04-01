package com.example.demo.airplain;

import com.example.demo.airline.Airline;
import com.example.demo.airline.AirlineService;
import com.example.demo.airline.dto.AirlineDto;
import com.example.demo.user.AdminAuthorizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirplainService {
    private final AirplainRepository airplainRepository;
    private final AdminAuthorizeService adminAuthorizeService;

    public AirlineDto.Response airplaneCreate(AirlineDto.Request reqDto, String authorization) {
        adminAuthorizeService.authorize(authorization);
        duplicationCheck(reqDto);
        Airline airline = airlineRepository.save(reqDto.toEntity());
        return airline.toResponseDto();
    }
}
