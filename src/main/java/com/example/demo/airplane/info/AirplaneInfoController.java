package com.example.demo.airplane.info;

import com.example.demo.airplane.info.dto.Request;
import com.example.demo.airplane.info.dto.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/airplaneinfo")
public class AirplaneInfoController {

    private final AirplaneInfoService airplaneInfoService;

    public AirplaneInfoController(AirplaneInfoService airplaneInfoService) {
        this.airplaneInfoService = airplaneInfoService;
    }

    @PostMapping("/save")
    public ResponseEntity<Response.Save> save(@RequestBody @Valid Request.Save saveDto) {

        Long result = airplaneInfoService.save(saveDto.toEntity());

        return ResponseEntity.ok(new Response.Save(result));
    }

    @GetMapping("/list")
    public ResponseEntity<Response.AirplaneInfoList> getList() {
        return ResponseEntity.ok(airplaneInfoService.findAll());
    }

    @GetMapping("")
    public ResponseEntity<Response.AirplaneInfo> getAirplane(
            Request.SearchForm searchForm
    ) {
        return ResponseEntity.ok(airplaneInfoService.getAirplaneInfo(searchForm));
    }

}

/*
post - save
patch - update
get - info
get - infolist
 */