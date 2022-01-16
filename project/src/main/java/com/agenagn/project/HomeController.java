package com.agenagn.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    private ItemService service;

    @GetMapping("/")
    public String home(Model model) {
        List<Items> listitem = service.listAll();
        model.addAttribute("listitem", listitem);
        System.out.print("Get /");
        return "home";
    }
    @GetMapping("/itemsform")
    public String add(Model model){
        model.addAttribute("item", new Items());
        return "itemsform";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveItems(@ModelAttribute("item") Items items){
        service.save(items);
        return "redirect:/";
    }
    
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditStudentPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("itemsform");
        Items items = service.get(id);
        mav.addObject("item", items);
        return mav;
        
    }
    @RequestMapping("/delete/{id}")
    public String deleteitem(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }
}
