package com.agenagn.project;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TermsController {
    @GetMapping("/terms")
    public String about(){
        return "terms";
    }
    
}