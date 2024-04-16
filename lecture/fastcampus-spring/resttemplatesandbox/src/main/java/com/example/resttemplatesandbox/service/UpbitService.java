package com.example.resttemplatesandbox.service;

import com.example.resttemplatesandbox.controller.MinuteCandleRequest;
import com.example.resttemplatesandbox.data.MinuteCandle;
import com.example.resttemplatesandbox.http.HttpClient;
import com.example.resttemplatesandbox.http.UpbitFeignClient;
import com.example.resttemplatesandbox.http.UpbitMinuteCandle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UpbitService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final UpbitFeignClient upbitFeignClient;

    UpbitService(HttpClient httpClient, UpbitFeignClient upbitFeignClient) {
        this.httpClient = httpClient;
        this.objectMapper = new ObjectMapper();
        this.upbitFeignClient = upbitFeignClient;
    }

    public List<MinuteCandle> getCandles(int unit, MinuteCandleRequest request) throws JsonProcessingException {
        // uri
        String uri = UriComponentsBuilder.fromUriString("https://api.upbit.com/")
                .path("v1/candles/minutes/" + unit)
                .queryParam("market", request.getMarket())
                .queryParam("count", request.getCount())
                .build()
                .toUriString();
        String url = "http://apu/upbit.com/v1/candles/minutes/" + unit;

        // header
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);

        // call
        String result = httpClient.getData(uri, HttpMethod.GET, httpHeaders);

        // feign
        String feignResult = upbitFeignClient.getMinuteCandle(unit, request.getMarket(), request.getCount());

        List<UpbitMinuteCandle> upbitMinuteCandles = objectMapper.readValue(result, new TypeReference<List<UpbitMinuteCandle>>() {});

        return upbitMinuteCandles.stream().map(it -> MinuteCandle.builder()
                .market(it.getMarket())
                .build()).collect(Collectors.toList());
    }

}
