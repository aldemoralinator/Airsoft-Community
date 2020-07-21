package com.aldem.simpleapp.security;

import com.aldem.simpleapp.interceptor.AddCurrentUserInterceptor;
import com.aldem.simpleapp.interceptor.CorsInterceptor;
import com.aldem.simpleapp.interceptor.HomeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry; 
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfigurerImpl implements WebMvcConfigurer
{
    
    @Autowired
    HomeInterceptor homeInterceptor;
    
    @Autowired
    CorsInterceptor corsInterceptor;
    
    @Autowired
    AddCurrentUserInterceptor addCurrentUserInterceptor;
    
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) 
    {
        registry.addInterceptor(homeInterceptor).addPathPatterns("/");
        
        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
        
        registry.addInterceptor(addCurrentUserInterceptor)
                .addPathPatterns("/")
                .addPathPatterns("/dashboard/**")
                .addPathPatterns("/events/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) 
    {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST", "PATCH", "DELETE")
            .allowedHeaders("*")
            .allowCredentials(true)
            .maxAge(60);
    }
    
}
