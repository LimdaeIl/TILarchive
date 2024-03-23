package com.hodolog.exception;

import lombok.Getter;

/**
 * status -> 400 이상적 정책
 */
@Getter
public class InvalidRequest extends HodologException {

    private static final String MESSAGE = "잘못된 요청입니다.";


    public InvalidRequest() {
        super(MESSAGE);
    }

    public InvalidRequest(String fieldName, String message) {
        super(MESSAGE);
        addValidation(fieldName, message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
