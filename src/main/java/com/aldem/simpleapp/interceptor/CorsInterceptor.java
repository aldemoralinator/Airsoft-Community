package com.aldem.simpleapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor; 

@Component
public class CorsInterceptor implements HandlerInterceptor
{

    @Override
    public boolean preHandle(
        HttpServletRequest request, 
        HttpServletResponse response, 
        Object handler
    ) throws Exception 
    {
        response.setHeader("Set-Cookie", "locale=de; HttpOnly; SameSite=strict");
        
        return true;
    }
    
}
