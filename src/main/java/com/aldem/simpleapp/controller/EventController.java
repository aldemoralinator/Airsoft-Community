package com.aldem.simpleapp.controller;

import com.aldem.simpleapp.model.Event;
import com.aldem.simpleapp.model.EventSubscription;
import com.aldem.simpleapp.model.User;
import com.aldem.simpleapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.aldem.simpleapp.repository.EventRepository;
import com.aldem.simpleapp.repository.EventSubscriptionRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventController
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private EventSubscriptionRepository eventSubscriptionRepository;
    
    @GetMapping("/events/create")
    public String create(
        @AuthenticationPrincipal OAuth2User principal,
        Model model
    ) {
        return "events/create";
    }
 
    @PostMapping("/events")
    public ModelAndView store(
        @AuthenticationPrincipal OAuth2User principal,
        @RequestParam(name="name", required=true) String name,
        @RequestParam(name="location", required=true) String location,
        @RequestParam(name="description", required=false) String description,
        Model model
    ) {
        // TODO :: MAKE THIS IN TRANSACTION
        
        User currentUser = userRepository.findByOpenId(principal.getName());
        
        Event newEvent = new Event(name, location, description, currentUser);
        
        eventRepository.save(newEvent);
        
        if (eventRepository.existsByName(name)) 
            newEvent.setSlug(newEvent.getName() + newEvent.getId());
        else 
            newEvent.setSlug(newEvent.getName());
        
        eventRepository.save(newEvent);
        
        EventSubscription newEventSubscription = 
                new EventSubscription(currentUser, newEvent, true, true);
        
        eventSubscriptionRepository.save(newEventSubscription);

        // TODO :: MAKE THIS IN TRANSACTION
        
        return new ModelAndView("redirect:/dashboard");

    }

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
//            Event guild = eventRepository.findBySlug(slug); 
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
//            eventSubscriptionRepository.existsByUserIdAndProjectId(userId, projectId)
//        );
        
//            Event project1 = eventRepository.findByName("secondProj");
//            Event project2 = eventRepository.findBySlug("asdf");
//            boolean project3 = eventRepository.existsByName("secondProj");
//            boolean project4 = eventRepository.existsBySlug("secondProj");
            
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

