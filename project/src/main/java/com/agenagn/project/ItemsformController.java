package com.agenagn.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemsformController {

    @GetMapping("/itemsform")
    public String items(){
        return "itemsform";
    }
    
}
