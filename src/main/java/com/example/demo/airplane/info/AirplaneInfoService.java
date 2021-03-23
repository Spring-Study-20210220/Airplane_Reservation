package com.example.demo.airplane.info;

import com.example.demo.airplane.info.dto.Response;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    @Transactional
    public Long update(AirplaneInfo airplaneInfo) {

        return null;
    }
}
