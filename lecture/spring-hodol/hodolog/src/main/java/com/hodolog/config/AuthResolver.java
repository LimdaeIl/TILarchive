package com.hodolog.config;

import com.hodolog.config.data.UserSession;
import com.hodolog.exception.Unauthorized;
import com.hodolog.repository.SessionRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.crypto.SecretKey;
import java.util.Base64;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;

@Slf4j
@RequiredArgsConstructor
public class AuthResolver implements HandlerMethodArgumentResolver {

    private final SessionRepository sessionRepository;

    private final AppConfig appConfig;

    private static final String KEY = "1lwItElB4ceTGKXcPhB6Pynodfz9qt/z0DHbRlLx0lY=";

    // 요청에 대한 라우터가 넘어왔을 때 원하는 DTO 인지 물어보는 것
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserSession.class); // UserSession DTO 맞는지 확인
    }

    // 실제로 DTO 에 값을 세팅해주는 것
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        log.info(">>>{}", appConfig.toString());

        String jws = webRequest.getHeader("Authorization");
        if (jws == null || jws.equals("")) {
            throw new Unauthorized();
        }

        SecretKey key = hmacShaKeyFor(Base64.getDecoder().decode(KEY));

        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(jws);

            String userId = claimsJws.getPayload().getSubject();
            //OK, we can trust this JWT

            return new UserSession(Long.parseLong(userId));

        } catch (JwtException e) {
            throw new Unauthorized();
            //don't trust the JWT!
        }
    }
}
