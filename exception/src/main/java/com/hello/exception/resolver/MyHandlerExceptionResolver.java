package com.hello.exception.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        try {
            if (ex instanceof IllegalArgumentException) {
                log.info("IllegalArgumentException resolver to 400");
                // IllegalArgumentException 예외가 터지면, 400으로 처리하도록 설정
                // WAS는 sendError를 확인하고 400으로 처리
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                return new ModelAndView();  // 특정 뷰를 지정하지 않으면 그냥 리턴
            }
        } catch (IOException e) {
            log.error("resolver ex", e);
            e.printStackTrace();
        }

        return null;
    }
}
