package com.example.demo.config;

import com.example.demo.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet 요청 오류 발생시 에러페이지로 이동
 * 
 * */

@ControllerAdvice
@Slf4j
public class ServiceExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public void handleException(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        log.error("Exception Occurred: {}", e.getMessage());
        
        request.setAttribute("errorNo", 9999);
        request.setAttribute("errorMsg", "서비스 도중 에러가 발생하였습니다.");

        request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
    }

    @ExceptionHandler(value = ServiceException.class)
    public void handleServiceException(HttpServletRequest request, HttpServletResponse response, ServiceException e) throws Exception {
        log.debug("ServiceException Occurred: {}", e.getMessage());

        request.setAttribute("errorNo", e.getErrorNumber());
        request.setAttribute("errorMsg", e.getErrorMessage());

        if(e.getErrorNumber() < 1000){
            request.getRequestDispatcher("WEB-INF/jsp/close.jsp").forward(request, response);
        } else if (e.getErrorNumber() < 2000) {
            request.getRequestDispatcher("WEB-INF/jsp/return.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}
