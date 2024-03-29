package com.hodolog.controller;

import com.hodolog.request.Login;
import com.hodolog.response.SessionResponse;
import com.hodolog.service.AuthService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.util.Base64;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private static final String KEY = "1lwItElB4ceTGKXcPhB6Pynodfz9qt/z0DHbRlLx0lY=";
    @PostMapping("/auth/login")
    public SessionResponse login(@RequestBody Login login) {
        Long userId = authService.signIn(login);

        SecretKey key = hmacShaKeyFor(Base64.getDecoder().decode(KEY));
        String jws = Jwts.builder().subject(String.valueOf(userId)).signWith(key).compact();

        return new SessionResponse(jws);
    }
}
