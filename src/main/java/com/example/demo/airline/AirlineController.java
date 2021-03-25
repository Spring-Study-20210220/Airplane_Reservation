package com.example.demo.airline;

import com.example.demo.airline.dto.AirlineDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AirlineController {
    private final AirlineService airlineService;

    @PostMapping("/Airline")
    ResponseEntity<AirlineDto.Response> register(@RequestBody AirlineDto.Request reqDto
        , @RequestHeader(value = "Authorization") String authorization){
        AirlineDto.Response resDto = airlineService.airlineCreate(reqDto, authorization);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resDto);
    }
    @DeleteMapping("/Airline/{AirlineID}")
    ResponseEntity<AirlineDto.Response> unRegister(@RequestHeader(value = "Authorization") String authorization,
                                                   @PathVariable("AirlineId") Long id){
        airlineService.airlineDelete(id,authorization);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
    @PatchMapping("/Airline/{AirlineID}")
    ResponseEntity<AirlineDto.Response> update(@RequestHeader(value = "Authorization") String authorization,
        @RequestBody AirlineDto.Request reqDto, @PathVariable("AirlineId") Long id){
        AirlineDto.Response resDto = airlineService.airlineUpdate(id, reqDto, authorization);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resDto);
    }
    @GetMapping("/Airline/{AirlineID}")
    ResponseEntity<AirlineDto.Response> search(@RequestHeader(value = "Authorization") String authorization,
                                               @PathVariable("AirlineId") Long id){
        AirlineDto.Response resDto = airlineService.airlineFind(id,authorization);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resDto);
    }
}
