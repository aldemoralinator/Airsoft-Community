package com.aldem.simpleapp.controller;

import com.aldem.simpleapp.model.User;
import com.aldem.simpleapp.repository.GuildRepository;
import com.aldem.simpleapp.repository.UserRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class DashboardController 
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GuildRepository guildRepository;

    @GetMapping("/dashboard")
    public String showDashboard (
        @AuthenticationPrincipal OAuth2User principal, 
        Model model
    ) {
        try {
            
            User currentUser = userRepository.findByEmail(
                principal.getAttribute("email")
            );
           
            model.addAttribute("currentUser", currentUser);

            return "dashboard";

        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }
    }
    
}
