package com.example.demo.airplane.info;

import com.example.demo.airplane.info.dto.Response;
import com.example.demo.airplane.info.dto.ServiceRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AirplaneInfoService {

    @Transactional
    public Response.Save save(ServiceRequest.Save saveDto) {

        return null;
    }
}
