package com.hodolog.exception;

/**
 * status -> 401
 */
public class Unauthorized extends HodologException {
    private static final String MESSAGE = "존재하지 않는 글입니다.";

    public Unauthorized() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }
}
