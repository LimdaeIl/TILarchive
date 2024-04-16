package com.example.resttemplatesandbox.controller;

import com.example.resttemplatesandbox.data.MinuteCandle;
import com.example.resttemplatesandbox.service.UpbitService;
import com.example.resttemplatesandbox.validation.MinuteCandleValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UpbitController {

    private final MinuteCandleValidator minuteCandleValidator;
    private final UpbitService upbitService;

    public UpbitController(MinuteCandleValidator minuteCandleValidator, UpbitService upbitService) {
        this.minuteCandleValidator = minuteCandleValidator;
        this.upbitService = upbitService;
    }

    @GetMapping("/api/v1/candle/minute/{unit}")
    public List<MinuteCandle> getMinuteCandles(
            @PathVariable int unit,
            @RequestBody MinuteCandleRequest request,
            BindingResult bindingResult
    ) throws JsonProcessingException {
        minuteCandleValidator.validate(unit, bindingResult);

        if (bindingResult.hasErrors()) {
            return new ArrayList<>();
        }

        return upbitService.getCandles(unit, request);
    }
}
