package com.example.demo.airline;

import com.example.demo.airline.dto.AirlineDto;
import com.example.demo.user.AdminAuthorizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AirlineService {
    private final AirlineRepository airlineRepository;
    private final AdminAuthorizeService adminAuthorizeService;

    @Transactional
    public AirlineDto.Response airlineCreate(AirlineDto.Request reqDto, String authorization) {
        AuthorizeAdmin(authorization);
        Airline airline = airlineRepository.save(reqDto.toEntity());
        return airline.toResponseDto();
    }

    @Transactional
    public void airlineDelete(Long id, String authorization) {
        AuthorizeAdmin(authorization);
        Airline airline = findById(id);
        airlineRepository.delete(airline);
    }

    @Transactional
    public AirlineDto.Response airlineUpdate(Long id, AirlineDto.Request reqDto, String authorization) {
        AuthorizeAdmin(authorization);
        Airline airline = findById(id);
        airline.update(reqDto.getName(), reqDto.getCountry());
        return airline.toResponseDto();
    }

    public AirlineDto.Response airlineFind(Long id, String authorization) {
        AuthorizeAdmin(authorization);
        Airline airline = findById(id);
        return airline.toResponseDto();
    }

    private void AuthorizeAdmin(String Authorization) {
        if (!adminAuthorizeService.authorize(Authorization)) {
            throw new RuntimeException("인가되지 않은 사용자입니다.");
        }
    }

    @Transactional
    private Airline findById(Long id) {
        return airlineRepository.findById(id).orElseThrow(
                () -> new RuntimeException("해당하는 항공사가 존재하지 않습니다.")
        );
    }
}
