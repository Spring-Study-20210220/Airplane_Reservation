package com.example.demo.airplain;

import com.example.demo.airline.dto.AirlineDto;
import com.example.demo.airplain.dto.AirplaneDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AirplaneController {
    private final AirplainService airplainService;

    @PostMapping("/Airplane")
    ResponseEntity<AirplaneDto.Response> register(@RequestBody AirplaneDto.Request reqDto
            , @RequestHeader(value = "Authorization") String authorization) {
        AirlineDto.Response resDto = airplainService.airplaneCreate(reqDto, authorization);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resDto);
    }


}
