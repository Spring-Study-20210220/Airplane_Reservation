package com.example.demo.airplain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirplainService {
    private final AirplainRepository airplainRepository;


}
