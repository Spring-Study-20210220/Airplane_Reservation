package com.example.demo.user;

import com.example.demo.user.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    //save find updateMileage

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse.Save save(User user) {
        //이메일 유효성 검사
        emailDuplicate(user.getEmail());
        User savedUser = userRepository.save(user);
        return new UserResponse.Save(savedUser.getId());
    }

    private void emailDuplicate(String email) {
        if(!userRepository.findByEmail(email).isEmpty()) {
            throw new IllegalStateException("이메일 중복이 존재합니다");
        }
    }

    public UserResponse.UserInfo findByEmail(String email) {
        User user= userRepository.findByEmail(email).orElseThrow(IllegalStateException::new);

        return UserResponse.UserInfo.of(user);
    }
}
