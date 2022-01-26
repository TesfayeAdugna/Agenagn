package com.agenagn.project.security;

import java.util.List;

import com.agenagn.project.ItemRepository;
import com.agenagn.project.Items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AccouController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/your")
    @PreAuthorize("hasRole('USER')")
    public String yourItems(Model model ,@AuthenticationPrincipal User user) {
        List<Items> listitem = itemRepository.findByUser(user);
        model.addAttribute("listitem", listitem);
        if(!(user == null)){
            model.addAttribute("currentUser", user);
        }
        return "home";
    } 
      
}
