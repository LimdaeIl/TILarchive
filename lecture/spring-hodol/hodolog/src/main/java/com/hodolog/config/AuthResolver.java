package com.hodolog.config;

import com.hodolog.config.data.UserSession;
import com.hodolog.domain.Session;
import com.hodolog.exception.Unauthorized;
import com.hodolog.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
public class AuthResolver implements HandlerMethodArgumentResolver {

    private final SessionRepository sessionRepository;

    // 요청에 대한 라우터가 넘어왔을 때 원하는 DTO 인지 물어보는 것
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserSession.class); // UserSession DTO 맞는지 확인
    }

    // 실제로 DTO 에 값을 세팅해주는 것
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String accessToken = webRequest.getHeader("Authorization"); // 인증 정보는 헤더로 가져오도록 변경하기 -> 파라미터 충돌 발생할 수 있기 때문에
        if (accessToken == null || accessToken.equals("")) {
            throw new Unauthorized();
        }

        Session session = sessionRepository.findByAccessToken(accessToken)
                .orElseThrow(Unauthorized::new);

        return new UserSession(session.getUser().getId());
    }
}
