package com.example.demo.service;

import com.example.demo.user.*;
import com.example.demo.user.dto.AuthDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    private static final String TEST_AUTH_LOGIN_ID = "testerId";
    private static final String TEST_AUTH_NAME = "testerName";
    private static final String TEST_AUTH_PASSWORD = "testerPW";

    @Mock
    private MemberRepository memberRepository;
    @Mock
    private AdminRepository adminRepository;
    @InjectMocks
    private Authservice authservice;

    @Test
    void 관리자회원가입_정상(){
        AuthDto.Request reqDto = AuthDto.Request.builder()
                .login_id(TEST_AUTH_LOGIN_ID)
                .name(TEST_AUTH_NAME)
                .password(TEST_AUTH_PASSWORD)
                .build();
        Admin admin = Admin.builder()
                .login_id(reqDto.getLogin_id())
                .name(reqDto.getName())
                .password(reqDto.getPassword())
                .build();

        given(adminRepository.save(any())).willReturn(admin);

        AuthDto.Response resDto = authservice.adminJoin(reqDto);

        assertThat(resDto.getName()).isEqualTo(reqDto.getName());
        assertThat(resDto.getLogin_id()).isEqualTo(reqDto.getLogin_id());
    }
    @Test
    void 멤버회원가입_정상(){
        AuthDto.Request reqDto = AuthDto.Request.builder()
                .login_id(TEST_AUTH_LOGIN_ID)
                .name(TEST_AUTH_NAME)
                .password(TEST_AUTH_PASSWORD)
                .build();
        Member member = Member.builder()
                .login_id(reqDto.getLogin_id())
                .name(reqDto.getName())
                .password(reqDto.getPassword())
                .build();

        given(memberRepository.save(any())).willReturn(member);

        AuthDto.Response resDto = authservice.memberJoin(reqDto);

        assertThat(resDto.getName()).isEqualTo(reqDto.getName());
        assertThat(resDto.getLogin_id()).isEqualTo(reqDto.getLogin_id());
    }
    @Test
    void 멤버_탈퇴_정상(){
        Member member = Member.builder()
                .login_id(TEST_AUTH_LOGIN_ID)
                .name(TEST_AUTH_NAME)
                .password(TEST_AUTH_PASSWORD)
                .level(Level.IRON)
                .build();

        given(memberRepository.findById(any())).willReturn(Optional.of(member));

        authservice.memberWithdraw(1L);

        assertThat(member.isEnable()).isEqualTo(false);
    }

    @Test
    void 어드민_탈퇴_정상() {
        Admin admin = Admin.builder()
                .login_id(TEST_AUTH_LOGIN_ID)
                .name(TEST_AUTH_NAME)
                .password(TEST_AUTH_PASSWORD)
                .build();

        given(adminRepository.findById(any())).willReturn(Optional.of(admin));

        authservice.adminWithdraw(1L);

        assertThat(admin.isEnable()).isEqualTo(false);
    }
}
