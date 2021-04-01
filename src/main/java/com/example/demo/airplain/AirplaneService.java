package com.example.demo.airplain;

import com.example.demo.Error.Exception.AirplaneNameDuplicationException;
import com.example.demo.airline.Airline;
import com.example.demo.airline.AirlineService;
import com.example.demo.airplain.dto.AirplaneDto;
import com.example.demo.user.AdminAuthorizeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AirplaneService {
    private final AirplaneRepository airplaneRepository;
    private final AirlineService airlineService;
    private final AdminAuthorizeService adminAuthorizeService;

    public AirplaneDto.Response airplaneCreate(Long airlineID, AirplaneDto.Request reqDto, String authorization) {
        log.info("beforeAuth");
        adminAuthorizeService.authorize(authorization);
        log.info("before dup ck");
        duplicationCheck(reqDto);
        log.info("before findbyid");

        Airline airline = airlineService.findById(airlineID);
        log.info("before save");
        Airplane airplane = airplaneRepository.save(reqDto.toEntity());
        log.info("before register");
        airline.registerAirplain(airplane);

        return airplane.toResponseDto();
    }

    private void duplicationCheck(AirplaneDto.Request reqDto){
        String name = reqDto.getName();
        Optional<Airplane> result = airplaneRepository.findByName(name);
        if(!result.equals(Optional.empty())){
            throw new AirplaneNameDuplicationException();
        }
    }

}
