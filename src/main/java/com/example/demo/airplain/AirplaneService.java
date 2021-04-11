package com.example.demo.airplain;

import com.example.demo.Error.Exception.AirplaneNameDuplicationException;
import com.example.demo.Error.Exception.AirplaneNotFoundException;
import com.example.demo.airline.Airline;
import com.example.demo.airline.AirlineService;
import com.example.demo.airplain.dto.AirplaneDto;
import com.example.demo.user.AdminAuthorizeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AirplaneService {
    private final AirplaneRepository airplaneRepository;
    private final AirlineService airlineService;
    private final AdminAuthorizeService adminAuthorizeService;

    @Transactional
    public AirplaneDto.Response airplaneCreate(Long airlineID, AirplaneDto.Request reqDto, String authorization) {
        adminAuthorizeService.authorize(authorization);
        duplicationCheck(reqDto);

        Airline airline = airlineService.findById(airlineID);
        Airplane airplane = airplaneRepository.save(reqDto.toEntity());
        //airline.registerAirplane(airplane);
        airplane.registerAirline(airline);
        return airplane.toResponseDto();
    }

    private void duplicationCheck(AirplaneDto.Request reqDto){
        String name = reqDto.getName();
        Optional<Airplane> result = airplaneRepository.findByName(name);
        if(!result.equals(Optional.empty())){
            throw new AirplaneNameDuplicationException();
        }
    }

    @Transactional
    public AirplaneDto.Response airplaneFind(Long airlineID, Long airplaneID, String authorization) {
        adminAuthorizeService.authorize(authorization);
        Airline airline = airlineService.findById(airlineID);
        Airplane airplane = airline.findAirplaneById(airplaneID);

        return airplane.toResponseDto();
    }

    public Airplane findById(Long airplaneID){
        return airplaneRepository.findById(airplaneID).orElseThrow(
                AirplaneNotFoundException::new
        );
    }
}
