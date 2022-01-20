package com.agenagn.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContactController {
    @Autowired
    private ContactService service;
    @GetMapping("/contact")
    public String contact(Model model){
        model.addAttribute("contact", new Contact());
        return "contact";
    }
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String sendContact(@ModelAttribute("contact") Contact contact){
        service.save(contact);
        return "redirect:/";
    }
}