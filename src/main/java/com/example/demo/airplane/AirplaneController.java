package com.example.demo.airplane;

import com.example.demo.airplane.dto.Response;
import com.example.demo.airplane.dto.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/airplane")
public class AirplaneController {

    private final AirplaneService airplaneService;

    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @PostMapping("/save")
    public ResponseEntity<Response.SaveDto> save(@RequestBody @Valid Request.SaveDto saveDto) {

        Long result = airplaneService.save(saveDto.toEntity());

        return ResponseEntity.ok(new Response.SaveDto(result));
    }

    @GetMapping("/list")
    public ResponseEntity<Response.AirplaneListDto> getList() {
        return ResponseEntity.ok(airplaneService.findAll());
    }

    @GetMapping("")
    public ResponseEntity<Response.AirplaneDto> getAirplane(
            Request.SearchFormDto searchForm
    ) {
        return ResponseEntity.ok(airplaneService.getAirplane(searchForm));
    }

}
