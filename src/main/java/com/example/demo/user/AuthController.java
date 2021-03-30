package com.example.demo.user;

import com.example.demo.user.dto.AuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/Auth")
@RequiredArgsConstructor
public class AuthController {
    private final Authservice authservice;

    @PostMapping("/SignUp/Member")
    ResponseEntity<AuthDto.Response> memberSignUp(@Valid @RequestBody AuthDto.Request reqDto){
        AuthDto.Response resDto = authservice.memberJoin(reqDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resDto);
    }
    @PostMapping("/SignUp/Admin")
    ResponseEntity<AuthDto.Response> adminSignUp(@Valid @RequestBody AuthDto.Request reqDto){
        AuthDto.Response resDto = authservice.adminJoin(reqDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resDto);
    }
    @DeleteMapping("/Withdrawal/Admin/{adminId}")
    ResponseEntity<AuthDto.Response> adminWithdraw(@PathVariable("adminId") Long adminId) {
        authservice.adminWithdraw(adminId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
    @DeleteMapping("/Withdrawal/Member/{memberId}")
    ResponseEntity<AuthDto.Response> memberWithdraw(@PathVariable("memberId") Long memberId) {
        authservice.memberWithdraw(memberId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }


}
