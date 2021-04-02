package com.example.demo.airplain;

import com.example.demo.airplain.dto.AirplaneDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AirplaneController {
    private final AirplaneService airplaneService;

    @PostMapping("/Airline/{AirlineID}/Airplane")
    ResponseEntity<AirplaneDto.Response> register(@Valid @RequestBody AirplaneDto.Request reqDto
            , @PathVariable("AirlineID") Long airlineID
            , @RequestHeader(value = "Authorization") String authorization) {

        AirplaneDto.Response resDto = airplaneService.airplaneCreate(airlineID, reqDto, authorization);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resDto);
    }

    @GetMapping("/Airline/{AirlineID}/Airplane/{testAirplaneID}")
    ResponseEntity<AirplaneDto.Response> find(@PathVariable("AirlineID") Long airlineID
            , @PathVariable("testAirplaneID") Long airplaneID
            , @RequestHeader(value = "Authorization") String authorization) {

        AirplaneDto.Response resDto = airplaneService.airplaneFind(airlineID, airplaneID, authorization);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resDto);
    }


}
