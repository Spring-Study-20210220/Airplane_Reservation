package com.example.demo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminAuthorizeService {
    private final AdminRepository adminRepository;

    public Boolean authorize(String adminIdString){
        Long adminId = Long.parseLong(adminIdString);
        if(adminRepository.findById(adminId).equals(Optional.empty())){
            return false;
        }
        return true;
    }
}
