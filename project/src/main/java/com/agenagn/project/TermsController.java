package com.agenagn.project;

import com.agenagn.project.security.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Controller
public class TermsController {
    @GetMapping("/terms")
    public String about(Model model ,@AuthenticationPrincipal User user){
        return "terms";
    }
    
}