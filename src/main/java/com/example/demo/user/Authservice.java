package com.example.demo.user;

import com.example.demo.user.dto.AuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class Authservice {
    private final MemberRepository memberRepository;
    private final AdminRepository adminRepository;

    @Transactional
    public AuthDto.Response adminJoin(AuthDto.Request reqDto) {
        Admin admin = adminRepository.save(reqDto.toEntity_Admin());
        return admin.toResponseDto();
    }
    @Transactional
    public AuthDto.Response memberJoin(AuthDto.Request reqDto) {
        Member member = memberRepository.save(reqDto.toEntity_Member());
        return member.toResponseDto();
    }
    @Transactional
    public void adminWithdraw(Long adminId) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(
                () -> new RuntimeException("해당하는 관리자가 존재하지 않습니다.")
        );
        admin.withdraw();
    }
    @Transactional
    public void memberWithdraw(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new RuntimeException("해당하는 멤버가 존재하지 않습니다.")
        );
        member.withdraw();
    }

}
