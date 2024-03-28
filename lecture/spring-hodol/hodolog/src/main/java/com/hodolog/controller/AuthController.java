package com.hodolog.controller;

import com.hodolog.domain.User;
import com.hodolog.exception.InvalidSignInInformation;
import com.hodolog.repository.UserRepository;
import com.hodolog.request.Login;
import com.hodolog.response.SessionResponse;
import com.hodolog.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/login")
    public ResponseEntity<Object> login(@RequestBody Login login) {
        String accessToken = authService.signIn(login); // 인증 받은 토큰은 재사용하여 헤더 쿠키에 넣으면 됩니다.

        // 쿠키 이름은 "SESSION" 국룰. 스프링 5.0 부터 추가된 기능으로 직관적으로 쿠키 생성이 가능합니다.
        // 도메인은 운영, 서버, 스테이징 도메인 마다 변경됩니다. 예를 들어 "myservice.com" 으로 변경되고, yml 에 세팅해서 수정가능합니다.
        ResponseCookie cookie = ResponseCookie.from("SESSION", accessToken)
                .domain("localhost") // todo : 서버 환경에 따른 분리 필요
                .path("/") // 경로
                .httpOnly(true)
                .secure(false)
                .maxAge(Duration.ofDays(30)) // 쿠키 만료 시간 3600 : 1시간 Duration 도 가능 쿠키는 30일이 국룰
                .sameSite("Strict")
                .build();

        log.info(">>>>>>>> cookie {}", cookie.toString());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString()) // 쿠키는 헤더에 내려야 합니다. 입력한 여러 옵션이 쿠키 형식에 맞는 스트링으로 변환합니다.
                .build();
    }


}
