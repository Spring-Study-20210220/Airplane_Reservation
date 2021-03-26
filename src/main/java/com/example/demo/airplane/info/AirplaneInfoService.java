package com.example.demo.airplane.info;

import com.example.demo.airplane.info.dto.Request;
import com.example.demo.airplane.info.dto.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AirplaneInfoService {

    private final AirplaneInfoRepository airplaneInfoRepository;

    public AirplaneInfoService(AirplaneInfoRepository airplaneInfoRepository) {
        this.airplaneInfoRepository = airplaneInfoRepository;
    }

    @Transactional
    public Long save(AirplaneInfo airplaneInfo) {

        AirplaneInfo result = airplaneInfoRepository.save(airplaneInfo);

        return result.getId();
    }

    @Transactional(readOnly = true)
    public Response.AirplaneInfoList findAll() {

        return new Response.AirplaneInfoList(airplaneInfoRepository.findAll());
    }

    public Response.AirplaneInfo getAirplaneInfo(Request.SearchForm searchForm) {
        return null;
    }
}
