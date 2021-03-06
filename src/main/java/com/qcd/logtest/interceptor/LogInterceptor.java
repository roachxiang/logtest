package com.qcd.logtest.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class LogInterceptor implements HandlerInterceptor {

    private final static String REQUEST_ID = "requestId";
    private static final Logger LOGGER = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String xForwardedForHeader = httpServletRequest.getHeader("X-Forwarded-For");
        String remoteIp = httpServletRequest.getRemoteAddr();
        String uuid = UUID.randomUUID().toString();
        LOGGER.info("put requestId ({}) to logger", uuid);
        LOGGER.info("request id:{}, client ip:{}, X-Forwarded-For:{}", uuid, remoteIp, xForwardedForHeader);
        MDC.put(REQUEST_ID, uuid);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        String uuid = MDC.get(REQUEST_ID);
        LOGGER.info("remove requestId ({}) from logger", uuid);
        MDC.remove(REQUEST_ID);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
