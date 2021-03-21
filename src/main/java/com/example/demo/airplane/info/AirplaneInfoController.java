package com.example.demo.airplane.info;

import com.example.demo.airplane.info.dto.ControllerRequest;
import com.example.demo.airplane.info.dto.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/airplaneinfo")
public class AirplaneInfoController {

    private final AirplaneInfoService airplaneInfoService;

    public AirplaneInfoController(AirplaneInfoService airplaneInfoService) {
        this.airplaneInfoService = airplaneInfoService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ControllerRequest.Save saveDto) {

        return ResponseEntity.ok(airplaneInfoService.save(saveDto.toService()));
    }
}
/*
post - save
update - update
get - info
get - infolist
 */