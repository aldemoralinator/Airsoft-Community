package com.aldem.simpleapp.controller;

import com.aldem.simpleapp.model.Event;
import com.aldem.simpleapp.model.EventSubscription;
import com.aldem.simpleapp.model.User;
import com.aldem.simpleapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException; 
import com.aldem.simpleapp.repository.EventRepository;
import com.aldem.simpleapp.repository.EventSubscriptionRepository; 
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventSubscriptionController 
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private EventSubscriptionRepository eventSubscriptionRepository;
    

    @PostMapping("/events/{slug}/join")
    public ModelAndView applyForSubscription(
        @AuthenticationPrincipal OAuth2User principal,
        @PathVariable("slug") String slug,
        Model model
    ) {
        
        User currentUser = userRepository.findByOpenId(principal.getName());

        Event event = eventRepository.findBySlug(slug);
        
        if (event == null) 
            throw new ResponseStatusException(NOT_FOUND, "event does not exist");

        EventSubscription newEventSubscription = 
                new EventSubscription(currentUser, event, true, false);

        eventSubscriptionRepository.save(newEventSubscription); 

        return new ModelAndView("redirect:/dashboard");

    }
    
    @PostMapping("/events/{slug}/users/{openId}/approve")
    public ModelAndView all(
        @AuthenticationPrincipal OAuth2User principal,
        @RequestParam(name="isApprove", required=true) boolean isApprove,
        Model model
    ) {

        return new ModelAndView("redirect:/dashboard");

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
