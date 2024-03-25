package com.hodolog.config;

import com.hodolog.config.data.UserSession;
import com.hodolog.exception.Unauthorized;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthResolver implements HandlerMethodArgumentResolver {

    // 요청에 대한 라우터가 넘어왔을 때 원하는 DTO 인지 물어보는 것
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserSession.class); // UserSession DTO 맞는지 확인
    }

    // 실제로 DTO 에 값을 세팅해주는 것
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String accessToken = webRequest.getParameter("accesstoken");
        if (accessToken == null || accessToken.equals("")) {
            throw new Unauthorized();
        }

        UserSession userSession = new UserSession();
        userSession.name = accessToken;
        return userSession;
    }
}
