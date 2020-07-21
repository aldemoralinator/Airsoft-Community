package com.aldem.simpleapp.interceptor;

import com.aldem.simpleapp.model.User;
import com.aldem.simpleapp.repository.UserRepository;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AddCurrentUserInterceptor implements HandlerInterceptor
{
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public void postHandle(
        HttpServletRequest request, 
        HttpServletResponse response, 
        Object handler, 
        ModelAndView modelAndView
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


                    String openId = principal.getName();

                    User currentUser = 
                        userRepository.findByOpenId(openId);

                    modelAndView.addObject("currentUser", currentUser);               

                }
            }     
        }   
    } 
}
