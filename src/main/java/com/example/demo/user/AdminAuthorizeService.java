package com.example.demo.user;

import com.example.demo.Error.Exception.UnAuthorizedUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminAuthorizeService {
    private final AdminRepository adminRepository;

    public void authorize(String adminIdString){
        Long adminId = Long.parseLong(adminIdString);
        if(adminRepository.findById(adminId).equals(Optional.empty())){
            throw new UnAuthorizedUserException();
        }
    }
}
