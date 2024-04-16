package com.example.resttemplatesandbox.http;

import com.example.resttemplatesandbox.exception.UpbitClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class HttpClient {

    private final RestTemplate restTemplate;

    public HttpClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getData(String uri, HttpMethod httpMethod, HttpHeaders headers) {
        try {
            return restTemplate.exchange(
                    uri,
                    httpMethod,
                    new HttpEntity<>(headers),
                    new ParameterizedTypeReference<String>() {
                    }
            ).getBody();
        } catch (RestClientException e) {
            log.error("에러", e);
            throw new UpbitClientException(e.getMessage());
        }
    }
}
