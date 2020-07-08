package com.example.demo.config;

import com.example.demo.model.LoginSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 웹 요청을 가로채서 로그인 세션정보가 없으면 로그인 페이지로 강제 이동시킨다.
 * */

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    
    // 컨트롤러 요청 전에 Interceptor 작동
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        LoginSession loginSession = (LoginSession) session.getAttribute(LoginSession.KEY);

        // 로그인 세션이 없는 경우 로그인 세션에 저장
        if (loginSession == null) {
            session.invalidate();
            
            // ajax요청인 경우 에러리턴
            if (request.getRequestURI().contains("/ajax")) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            
            // 아닌 경우 로그인 페이지로 이동
            } else {
                response.sendRedirect(request.getContextPath()+"/login");

                // 컨트롤러에 요청이 되지 않도록 중단
                return false;
            }
        }
        return true;
    }
}
