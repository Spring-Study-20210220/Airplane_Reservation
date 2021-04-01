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

        log.info("before");
        AirplaneDto.Response resDto = airplaneService.airplaneCreate(airlineID, reqDto, authorization);
        log.info("after");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resDto);
    }


}
