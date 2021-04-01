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

    @PostMapping("/Airplane")
    ResponseEntity<AirplaneDto.Response> register(@RequestBody AirplaneDto.Request reqDto
            , @RequestHeader(value = "Authorization") String authorization) {
        AirlineDto.Response resDto = airplainService.airplaneCreate(reqDto, authorization);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resDto);
    }


}
