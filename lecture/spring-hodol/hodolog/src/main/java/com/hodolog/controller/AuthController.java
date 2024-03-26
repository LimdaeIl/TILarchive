package com.hodolog.controller;

import com.hodolog.domain.User;
import com.hodolog.exception.InvalidSignInInformation;
import com.hodolog.repository.UserRepository;
import com.hodolog.request.Login;
import com.hodolog.response.SessionResponse;
import com.hodolog.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/login")
    public SessionResponse login(@RequestBody Login login) {
        String accessToken = authService.signIn(login);

        return new SessionResponse(accessToken);
    }
}
