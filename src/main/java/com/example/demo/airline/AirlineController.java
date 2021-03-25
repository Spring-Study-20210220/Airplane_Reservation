package com.example.demo.airline;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Airline")
@RequiredArgsConstructor
public class AirlineController {
    private final AirlineService airlineService;

}
