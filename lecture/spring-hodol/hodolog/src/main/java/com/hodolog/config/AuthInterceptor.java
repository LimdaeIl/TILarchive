package com.hodolog.config;

import com.hodolog.exception.Unauthorized;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    // 핸들러 실행되기 전에 무조건 실행 주로 구현해야 할 부분
    // 인터셉터는 특정 경로를 제외하거나 특정 경로일 때 무조건 인터셉터를 수행하게 만들었는데, 여기에서 해야하나 생각듭니다.
    // api 만들다보면 어떤 라우터는 인증이 필요하고, 없을 수 있고, 프로젝트가 커질수록 늘어납니다.
    // 제외할 패턴이나 인증을 세워야 할 라우터 패턴을 계속 다 입력 해주고 관리해야 합니다.
    // 그래서 dto 가 달려 있으면 인증이 필요한 메서드라고 나타내면 어떨까 싶습니다.
    // 인증이 필요없는 컨트롤러 메서드는 굳이 dto 달 이유가 없으니까요. 그래서 Auth 리졸버에서 처리합니다.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(">> preHandle");

        String accessToken = request.getParameter("accesstoken");
        if (accessToken != null && !accessToken.equals("")) {
            request.setAttribute("userName", accessToken);
            return true;
        }
        throw new Unauthorized() ;
    }

    // 핸들러 실행 후에 무조건 실행
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    // 뷰까지 실행 후에 무조건 실행
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info(">> afterCompletion");
    }
}
