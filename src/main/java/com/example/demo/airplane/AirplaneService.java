package com.example.demo.airplane;

import com.example.demo.airplane.dto.Response;
import com.example.demo.airplane.dto.Request;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class AirplaneService {

    private final AirplaneRepository airplaneRepository;

    public AirplaneService(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    @Transactional
    public Long save(Airplane airplane) {

        Airplane result = airplaneRepository.save(airplane);

        return result.getId();
    }

    @Transactional(readOnly = true)
    public Response.AirplaneListDto findAll() {
        return new Response.AirplaneListDto(
                airplaneRepository.findAll()
                        .stream()
                        .map(Response.AirplaneDto::of)
                        .collect(Collectors.toList())
        );
    }

    public Response.AirplaneDto getAirplane(Request.SearchFormDto searchForm) {
        return null;
    }
}
