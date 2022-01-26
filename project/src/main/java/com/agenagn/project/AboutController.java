package com.agenagn.project;
import com.agenagn.project.security.User;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController { 
        
    @GetMapping("/about")
    public String about(Model model, @AuthenticationPrincipal User user){
        if(!(user == null)){
            model.addAttribute("currentUser", user);
        }
        return "about";
    }
}