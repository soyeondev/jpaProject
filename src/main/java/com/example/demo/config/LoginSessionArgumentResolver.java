package com.example.demo.config;

import com.example.demo.model.LoginSession;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 컨트롤러에 LoginSession 파라미터를 바인딩
 * */

public class LoginSessionArgumentResolver implements HandlerMethodArgumentResolver {

    // 현재 파라미터를 resolver가 지원하는지 확인
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return LoginSession.class.isAssignableFrom(parameter.getParameterType());
    }

    // 실제로 바인딩 할 객체를 리턴
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Object objSession = webRequest.getAttribute(LoginSession.KEY, webRequest.SCOPE_SESSION);

        if(objSession != null) {
            return (LoginSession)objSession;
        }

        return null;
    }
}
