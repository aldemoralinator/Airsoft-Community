package com.aldem.simpleapp.controller;

import com.aldem.simpleapp.model.Project; 
import com.aldem.simpleapp.model.Subscription;
import com.aldem.simpleapp.model.User;
import com.aldem.simpleapp.repository.ProjectRepository;
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

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Controller
public class ProjectController
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @GetMapping("/projects")
    public String all(
        @AuthenticationPrincipal OAuth2User principal,
        Model model
    ) {
        try {

            User currentUser = 
                    userRepository.findByEmail(principal.getAttribute("email"));
            model.addAttribute("currentUser", currentUser);

            List<Project> projects = projectRepository.findAll();

            if (projects != null) model.addAttribute("projects", projects);

            return "projects/showall";

        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }
    }
    
    @GetMapping("/projects/create")
    public String create(
        @AuthenticationPrincipal OAuth2User principal,
        Model model
    ) {
        return "projects/create";
    }
    
    @PostMapping("/projects")
    public ModelAndView store(
        @AuthenticationPrincipal OAuth2User principal,
        @RequestParam(name="name", required=true) String name,
        @RequestParam(name="description", required=false) String description,
        Model model
    ) {
        try {
            
            User currentUser = 
                    userRepository.findByEmail(principal.getAttribute("email"));
            model.addAttribute("currentUser", currentUser);
                
            boolean projectExists = projectRepository.existsByName(name);
            
            Project newProject = new Project(name, description);
            projectRepository.save(newProject);
            
            if (projectExists) 
                newProject.setSlug(newProject.getName() + newProject.getId());
            else 
                newProject.setSlug(newProject.getName());
            
            projectRepository.save(newProject);
            
            Subscription newSubscription = 
                    new Subscription(currentUser, newProject, true);
            
            subscriptionRepository.save(newSubscription);
            
            return new ModelAndView(
                    "redirect:/projects/" + newProject.getSlug());

        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }
    }

    @GetMapping("/projects/{slug}")
    public String aProject(
        @AuthenticationPrincipal OAuth2User principal,
        @PathVariable("slug") String slug,
        Model model
    ) {
        System.out.println("################################## executed alasdlfk");
        try {

            User currentUser = 
                    userRepository.findByEmail(principal.getAttribute("email"));
            model.addAttribute("currentUser", currentUser);
            
            Project project = projectRepository.findBySlug(slug);
//            System.out.println(currentUser.toString());
//            System.out.println(project.toString());
            
            model.addAttribute("project", project);

            return "projects/show";

        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }
    }
    
    @GetMapping("/{projectId}/{userId}")
    public void asdf(
        @AuthenticationPrincipal OAuth2User principal, 
            @PathVariable("userId") Long userId,
            @PathVariable("projectId") Long projectId,
//        @RequestParam(name="userId", required=true) Long userId,
//        @RequestParam(name="projectId", required=true) Long projectId,
        Model model
    ) {
        System.out.println("################################## executed alasdlfk");
        
        System.out.println(
            subscriptionRepository.existsByUserIdAndProjectId(userId, projectId)
        );
        
//            Project project1 = projectRepository.findByName("secondProj");
//            Project project2 = projectRepository.findBySlug("asdf");
//            boolean project3 = projectRepository.existsByName("secondProj");
//            boolean project4 = projectRepository.existsBySlug("secondProj");
            
//            System.out.println(project1.toString());
//            System.out.println(project2);
//            System.out.println(project3);
//            System.out.println(project4);
    }
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

