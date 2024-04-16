package com.example.resttemplatesandbox.controller;

import com.example.resttemplatesandbox.exception.UpbitClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @GetMapping("/api/v1/test")
    public String helloWorld() {
        try {
            return testService.getTest();
        } catch (UpbitClientException e) {
            //
        }
    }
}
