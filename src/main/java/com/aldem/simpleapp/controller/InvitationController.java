package com.aldem.simpleapp.controller;

import com.aldem.simpleapp.model.User;
import com.aldem.simpleapp.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.server.ResponseStatusException; 
import com.aldem.simpleapp.repository.GuildRepository;

@Controller
public class InvitationController 
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GuildRepository guildRepository;

    @GetMapping("/invitations")
    public String all(
        @AuthenticationPrincipal OAuth2User principal,
        Model model
    ) {
        try {
            
            User currentUser = userRepository.findByEmail(principal.getAttribute("email"));
            
            List<User> users = userRepository.findAll();

            model.addAttribute("users", users);
            
            model.addAttribute("currentUser", currentUser);

            return "profiles/showall";

        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }
    }

//    @GetMapping("/profiles/{username}")
//    public String aUser(
//        @AuthenticationPrincipal OAuth2User principal,
//        @PathVariable("username") String username,
//        Model model
//    ) {
//        try {
//
//            User user = userRepository.findByUsername(username);
//            if (user == null) throw new ResponseStatusException(NOT_FOUND, "User does not exist");
//
//            User currentUser = userRepository.findByEmail(principal.getAttribute("email"));
//
//            model.addAttribute("user", user);
//            model.addAttribute("currentUser", currentUser);
//
//            return "profiles/show";
//
//        } catch (ResponseStatusException e) {
//            throw new ResponseStatusException(e.getStatus(), e.getMessage());
//        }
//    }



//    @GetMapping("/profiles/{username}/edit")
//    public String editUser(
//        @AuthenticationPrincipal OAuth2User principal,
//        @PathVariable("username") String username,
//        Model model
//    ) {
//        try {
//
//            User user = userRepository.findByUsername(username);
//            if (user == null) throw new ResponseStatusException(NOT_FOUND, "User does not exist");
//
//            if (principal.getAttribute("email").equals(user.getEmail())) {
//                model.addAttribute("user", user);
//                return "profiles/edit";
//            } else {
//                throw new ResponseStatusException(UNAUTHORIZED, "You are not authorized");
//            }
//
//        } catch (ResponseStatusException e) {
//            throw new ResponseStatusException(e.getStatus(), e.getMessage());
//        }
//    }
//
//    @PostMapping("/profiles/{username}/update")
//    public ModelAndView updateUser(
//            @AuthenticationPrincipal OAuth2User principal,
//            @PathVariable("username") String username,
//            @RequestParam(name="introduction", required=true) String introduction,
//            Model model
//    ) {
//        try {
//
//            User user = userRepository.findByUsername(username);
//            if (user == null) throw new ResponseStatusException(NOT_FOUND, "User does not exist");
//
//            if (principal.getAttribute("email").equals(user.getEmail())) {
//                user.setIntroduction(introduction);
//                userRepository.save(user);
//                return new ModelAndView("redirect:/profiles/" + username);
//            } else {
//                throw new ResponseStatusException(UNAUTHORIZED, "You are not authorized");
//            }
//
//        } catch (ResponseStatusException e) {
//            throw new ResponseStatusException(e.getStatus(), e.getMessage());
//        }
//    }


}
