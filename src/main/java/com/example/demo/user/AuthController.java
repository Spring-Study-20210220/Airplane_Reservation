package com.example.demo.user;

import com.example.demo.user.dto.AuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Auth")
@RequiredArgsConstructor
public class AuthController {
    private final Authservice authservice;

    @PostMapping("/SignUp/Member")
    ResponseEntity<AuthDto.Response> memberSignUp(@RequestBody AuthDto.Request reqDto){
        AuthDto.Response resDto = authservice.memberJoin(reqDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resDto);
    }
    @PostMapping("/SignUp/Admin")
    ResponseEntity<AuthDto.Response> adminSignUp(@RequestBody AuthDto.Request reqDto){
        AuthDto.Response resDto = authservice.adminJoin(reqDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resDto);
    }
}
