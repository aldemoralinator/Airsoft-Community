package com.aldem.simpleapp.security;

import com.aldem.simpleapp.model.User;
import com.aldem.simpleapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser; 
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter
{
    @Autowired
    UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
            .authorizeRequests(a -> a
                .antMatchers(
                    "/", "/error", "/webjars/**", "/static/**"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                .loginPage("/oauth2/authorization/google")
                .userInfoEndpoint()
                .oidcUserService(this.oidcUserService())
            )
            .logout(l -> l
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/").permitAll()
            )
            .csrf(c -> c
                .csrfTokenRepository(
                    CookieCsrfTokenRepository.withHttpOnlyFalse()
                )
            );
    }

    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService()
    {
        final OidcUserService delegate = new OidcUserService();

        return (userRequest -> {
            OidcUser oidcUser = delegate.loadUser(userRequest);
            
            String openId = oidcUser.getName();
            
            if (!userRepository.existsByOpenId(openId)) {
                String email = oidcUser.getEmail();
                String username = email.split("@")[0];
                User newUser = new User(openId, username, oidcUser.getEmail());
                newUser.setAvatarlink(oidcUser.getAttribute("picture"));
                userRepository.save(newUser);
            }
                
            return oidcUser;
        });
    }
}
