package com.aldem.simpleapp.controller;

import com.aldem.simpleapp.model.User;
import com.aldem.simpleapp.repository.UserRepository;  
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.server.ResponseStatusException;
import com.aldem.simpleapp.repository.GuildRepository;

@Controller 
public class HomeController {
 
    @GetMapping("/") 
    public String index(
        @AuthenticationPrincipal OAuth2User principal, 
        Model model,
        HttpServletResponse response
    ) {
        try {
            
            return "home";

        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }
    }
}
