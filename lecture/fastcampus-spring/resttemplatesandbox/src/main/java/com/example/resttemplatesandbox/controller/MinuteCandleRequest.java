package com.example.resttemplatesandbox.controller;

import lombok.Getter;

@Getter
public class MinuteCandleRequest {
    private String market;
    private int count;
}
