package com.agenagn.project;

import java.util.List;

import com.agenagn.project.security.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class ContactController {
    @Autowired
    private ContactService service;
    @GetMapping("/contact")
    public String contact(Model model, @AuthenticationPrincipal User user){
        if(!(user == null)){
            model.addAttribute("currentUser", user);
        }
        model.addAttribute("contact", new Contact());
        return "contact";
    }
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String sendContact(@Valid @ModelAttribute("contact") Contact contact, Errors errors, @AuthenticationPrincipal User user){
        if(!(user == null)){
            contact.setUser(user);
        }
        if (errors.hasErrors()) {
            return "contact";
        }
        service.save(contact);
        return "redirect:/";
    }
}