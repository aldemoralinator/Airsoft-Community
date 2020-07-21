package com.aldem.simpleapp.controller;

import com.aldem.simpleapp.model.Event;
import com.aldem.simpleapp.model.User;
import com.aldem.simpleapp.model.EventSubscription;
import com.aldem.simpleapp.repository.UserRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;
import com.aldem.simpleapp.repository.EventRepository;
import com.aldem.simpleapp.repository.EventSubscriptionRepository;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DashboardController 
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private EventSubscriptionRepository eventSubscriptionRepository;

    @GetMapping("/dashboard")
    public String showDashboard (
        @AuthenticationPrincipal OAuth2User principal, 
        @RequestParam(name="id", required=false) Long id,
        Model model
    ) {
        System.out.println("eventSlug: " + id);
        
        if (id != null) {
            List<EventSubscription> eventSubs = eventSubscriptionRepository.findByEventId(3l);
            
            if (eventSubs != null) {
                model.addAttribute("eventSubs", eventSubs);
            }

        }

        // TODO :: sort by timestamp
        List<Event> events = eventRepository.findAll();
        
        model.addAttribute("events", events);

        return "dashboard";

    }
    
}
