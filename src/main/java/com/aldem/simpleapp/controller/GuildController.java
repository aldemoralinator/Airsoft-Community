package com.aldem.simpleapp.controller;

import com.aldem.simpleapp.model.Guild; 
import com.aldem.simpleapp.model.Subscription;
import com.aldem.simpleapp.model.User;
import com.aldem.simpleapp.repository.SubscriptionRepository;
import com.aldem.simpleapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import com.aldem.simpleapp.repository.GuildRepository;
 

@Controller
public class GuildController
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GuildRepository guildRepository;
    
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @GetMapping("/guild")
    public String all(
        @AuthenticationPrincipal OAuth2User principal,
        Model model
    ) {
        try {

            User currentUser = 
                    userRepository.findByEmail(principal.getAttribute("email"));
            model.addAttribute("currentUser", currentUser);

            List<Guild> guilds = guildRepository.findAll();

            if (guilds != null) model.addAttribute("projects", guilds);

            return "guilds/showall";

        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }
    }
    
//    @GetMapping("/guilds/create")
//    public String create(
//        @AuthenticationPrincipal OAuth2User principal,
//        Model model
//    ) {
//        return "guilds/create";
//    }
//    
//    @PostMapping("/guilds")
//    public ModelAndView store(
//        @AuthenticationPrincipal OAuth2User principal,
//        @RequestParam(name="name", required=true) String name,
//        @RequestParam(name="description", required=false) String description,
//        Model model
//    ) {
//        try {
//            
//            User currentUser = 
//                    userRepository.findByEmail(principal.getAttribute("email"));
//            model.addAttribute("currentUser", currentUser);
//                
//            boolean guildExists = guildRepository.existsByName(name);
//            
//            Guild newGuild = new Guild(name, description);
//            guildRepository.save(newGuild);
//            
//            if (guildExists) 
//                newGuild.setSlug(newGuild.getName() + newGuild.getId());
//            else 
//                newGuild.setSlug(newGuild.getName());
//            
//            guildRepository.save(newGuild);
//            
//            Subscription newSubscription = 
//                    new Subscription(currentUser, newGuild, true);
//            
//            subscriptionRepository.save(newSubscription);
//            
//            return new ModelAndView(
//                    "redirect:/guilds/" + newGuild.getSlug());
//
//        } catch (ResponseStatusException e) {
//            throw new ResponseStatusException(e.getStatus(), e.getMessage());
//        }
//    }

//    @GetMapping("/guilds/{slug}")
//    public String aGuild(
//        @AuthenticationPrincipal OAuth2User principal,
//        @PathVariable("slug") String slug,
//        Model model
//    ) { 
//        try {
//
//            User currentUser = 
//                    userRepository.findByEmail(principal.getAttribute("email"));
//            model.addAttribute("currentUser", currentUser);
//            
//            Guild guild = guildRepository.findBySlug(slug); 
//            
//            model.addAttribute("guild", guild);
//
//            return "guild/show";
//
//        } catch (ResponseStatusException e) {
//            throw new ResponseStatusException(e.getStatus(), e.getMessage());
//        }
//    }
    
//    @GetMapping("/{guildId}/{userId}")
//    public void asdf(
//        @AuthenticationPrincipal OAuth2User principal, 
//            @PathVariable("userId") Long userId,
//            @PathVariable("projectId") Long projectId,
////        @RequestParam(name="userId", required=true) Long userId,
////        @RequestParam(name="projectId", required=true) Long projectId,
//        Model model
//    ) {
//        System.out.println("################################## executed alasdlfk");
//        
//        System.out.println(
//            subscriptionRepository.existsByUserIdAndProjectId(userId, projectId)
//        );
        
//            Guild project1 = guildRepository.findByName("secondProj");
//            Guild project2 = guildRepository.findBySlug("asdf");
//            boolean project3 = guildRepository.existsByName("secondProj");
//            boolean project4 = guildRepository.existsBySlug("secondProj");
            
//            System.out.println(project1.toString());
//            System.out.println(project2);
//            System.out.println(project3);
//            System.out.println(project4);
//    }
//
//
//
//    @GetMapping("/projects/{slug}/edit")
//    public String editProject(
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
//    @PostMapping("/projects/{slug}/update")
//    public ModelAndView updateProject(
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

