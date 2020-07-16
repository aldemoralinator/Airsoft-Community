package com.aldem.simpleapp.controller;

import com.aldem.simpleapp.model.User;
import com.aldem.simpleapp.repository.ProjectRepository;
import com.aldem.simpleapp.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/")
    public String index(@AuthenticationPrincipal OAuth2User principal, Model model) {
        try {
            if (principal == null) return "home";
            

            User user = userRepository.findByEmail(principal.getAttribute("email"));

            model.addAttribute("user", user);

            return "home";

        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }
    }

    @GetMapping("/test")
    public String test(
        @AuthenticationPrincipal OAuth2User principal,
        @RequestParam(name="food", required=false, defaultValue="World") String food,
        Model model
    ) {
        model.addAttribute("name", food);
        model.addAttribute("email", principal.getAttribute("email"));
        return "test";
    }

}
