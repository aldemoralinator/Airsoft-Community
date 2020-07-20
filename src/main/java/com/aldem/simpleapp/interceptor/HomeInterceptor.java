package com.aldem.simpleapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class HomeInterceptor implements HandlerInterceptor
{

    @Override
    public boolean preHandle(
        HttpServletRequest request, 
        HttpServletResponse response, 
        Object handler
    ) throws Exception 
    {

        if (request.getMethod().equals("GET")) {
            
            HttpServletRequest req = (HttpServletRequest) request;
            
            HttpSession session = req.getSession(false);
            
            if (session != null) {
                
                SecurityContextImpl ctx = 
                (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");

                if (ctx != null) {

                    OAuth2AuthenticatedPrincipal principal = 
                    (OAuth2AuthenticatedPrincipal) ctx.getAuthentication().getPrincipal();

                    if (principal != null) {

                        HttpServletResponse httpResponse = (HttpServletResponse) response;

                        httpResponse.sendRedirect("/dashboard");

                    }
                    
                }

            }
            
        }       
        
        return true;
    }
    
    
}
