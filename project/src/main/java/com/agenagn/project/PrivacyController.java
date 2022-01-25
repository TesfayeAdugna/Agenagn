package com.agenagn.project;

import com.agenagn.project.security.User;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Controller
public class PrivacyController {
          
    @GetMapping("/privacy")
    public String privacy(Model model ,@AuthenticationPrincipal User user){
        if(!(user == null)){
            model.addAttribute("currentUser", user);
        }
        return "privacy";
    }
    
}

