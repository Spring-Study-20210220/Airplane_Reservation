package com.example.demo.airline;

import com.example.demo.Error.Exception.AirlineNameDuplicationException;
import com.example.demo.Error.Exception.AirlineNotFoundException;
import com.example.demo.Error.Exception.UnAuthorizedUserException;
import com.example.demo.airline.dto.AirlineDto;
import com.example.demo.user.AdminAuthorizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirlineService {
    private final AirlineRepository airlineRepository;
    private final AdminAuthorizeService adminAuthorizeService;

    @Transactional
    public AirlineDto.Response airlineCreate(AirlineDto.Request reqDto, String authorization) {
        adminAuthorizeService.authorize(authorization);
        duplicationCheck(reqDto);
        Airline airline = airlineRepository.save(reqDto.toEntity());
        return airline.toResponseDto();
    }

    @Transactional
    public void airlineDelete(Long id, String authorization) {
        adminAuthorizeService.authorize(authorization);
        Airline airline = findById(id);
        airlineRepository.delete(airline);
    }

    @Transactional
    public AirlineDto.Response airlineUpdate(Long id, AirlineDto.Request reqDto, String authorization) {
        adminAuthorizeService.authorize(authorization);
        Airline airline = findById(id);
        airline.update(reqDto.getName(), reqDto.getCountry());
        return airline.toResponseDto();
    }

    public AirlineDto.Response airlineFind(Long id, String authorization) {
        adminAuthorizeService.authorize(authorization);
        Airline airline = findById(id);
        return airline.toResponseDto();
    }

    private void duplicationCheck(AirlineDto.Request reqDto){
        String name = reqDto.getName();
        Optional<Airline> result = airlineRepository.findByName(name);
        if(!result.equals(Optional.empty())){
            throw new AirlineNameDuplicationException();
        }
    }

    @Transactional
    public Airline findById(Long id) {
        return airlineRepository.findById(id).orElseThrow(
                AirlineNotFoundException::new
        );
    }
}
