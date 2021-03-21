package com.example.demo.controller;

import com.example.demo.user.dto.AuthDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {
    private static final String TEST_AUTH_LOGIN_ID = "testerId";
    private static final String TEST_AUTH_NAME = "testerName";
    private static final String TEST_AUTH_PASSWORD = "testerPW";

    @Autowired
    private WebTestClient webTestClient;
//
//    @BeforeAll
//    void setUp() {
//
//    }

    @Test
    void 관리자회원가입_정상(){
        AuthDto.Request authReqDto = AuthDto.Request.builder()
                .login_id(TEST_AUTH_LOGIN_ID)
                .name(TEST_AUTH_NAME)
                .password(TEST_AUTH_PASSWORD)
                .build();

        AuthDto.Response authResDto= webTestClient.post()
                .uri("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(authReqDto), AuthDto.Request.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(AuthDto.Response.class)
                .returnResult()
                .getResponseBody();
        assertThat(authResDto.getLogin_id(),is(TEST_AUTH_LOGIN_ID));
        assertThat(authResDto.getName(),is(TEST_AUTH_NAME));
        assertThat(authResDto.getPassword(),is(TEST_AUTH_PASSWORD));
    }
    @Test
    void 회원회원가입_정상(){

    }
}
