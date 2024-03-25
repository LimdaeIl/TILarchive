package com.hodolog.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * {
 * "code" : "400",
 * "message" : "잘못된 요청입니다.",
 * "validation" : {
 * "title" : "값을 입력해주세요."
 * }
 * }
 */
// @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
// 널이나 비어있는 값을 뺄 때 이 한 줄을 넣어서 응답 클래스에서 해결이 가능. -> 단, 개인적으로 선호하지 않음. 비어있는 것도 정보임.
@Getter
public class ErrorResponse {

    private final String code;
    private final String message;
    private final Map<String, String> validation;

    @Builder
    public ErrorResponse(String code, String message, Map<String, String> validation) {
        this.code = code;
        this.message = message;
        this.validation = validation != null ? validation : new HashMap<>();
    }

    public void addValidation(String fieldName, String errorMessage) {
        this.validation.put(fieldName, errorMessage);
    }
}
