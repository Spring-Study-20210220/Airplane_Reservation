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
    public ResponseEntity<?> save(@RequestBody @Valid Request.Save saveDto) {

        Long result = airplaneInfoService.save(saveDto.toEntity());

        return ResponseEntity.ok(new Response.Save(result));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid Request.Update updateDto) {

        Long result = airplaneInfoService.update(updateDto);

        return ResponseEntity.ok(new Response.Update(result));
    }
}

/*
post - save
patch - update
get - info
get - infolist
 */