package com.agenagn.project;

import java.io.IOException;
import java.util.List;

import com.agenagn.project.security.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    private ItemService service;
    @Autowired
    private ItemRepository repo;

    @GetMapping("/")
    public String home(Model model ,@AuthenticationPrincipal User user) {
        List<Items> listitem = service.listAll();
        model.addAttribute("listitem", listitem);
        if(!(user == null)){
            model.addAttribute("currentUser", user);
        }
        return "home";
    }
    // for search
    @RequestMapping("/search")
    public String home(Items items, Model model, @AuthenticationPrincipal User user, String keyword) {
        if(!(user == null)){
            model.addAttribute("currentUser", user);
        }
     if(keyword!=null) {
      List<Items> listitem = service.getByKeyword(keyword);
      model.addAttribute("listitem", listitem);
     }else {
     List<Items> listitem = service.listAll();
     model.addAttribute("listitem", listitem);}
     return "home";
    }
   
    @GetMapping("/itemsform")
    public String sell(Model model ,@AuthenticationPrincipal User user){
        model.addAttribute("item", new Items());
        if(!(user == null)){
            model.addAttribute("currentUser", user);
        }
        return "itemsform";
    }
    @GetMapping("/profile")
    public String profile(Model model ,@AuthenticationPrincipal User user){
        if(!(user == null)){
            model.addAttribute("currentUser", user);
        }
        return "profile";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveItems(@Valid @ModelAttribute("item") Items items, Errors errors, @RequestParam("image") MultipartFile multipartFile, @AuthenticationPrincipal User user, Model model) throws IOException{

        if (errors.hasErrors()) {
            if(!(user == null)){
                model.addAttribute("currentUser", user);
            }
            return "itemsform";
        }
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        items.setPhotos(fileName);
        items.setUser(user);


        Items savedItems = repo.save(items);

        String uploadDir = "user-photos/" + savedItems.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        service.save(items);
        return "redirect:/";
    }
    
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditItemsPage(@PathVariable(name = "id") int id ,@AuthenticationPrincipal User user) {
        ModelAndView mav = new ModelAndView("itemsform");
        Items items = service.get(id);
        mav.addObject("item", items);
        if(!(user == null)){
            mav.addObject("currentUser", user);
        }
        return mav;
        
    }
    @RequestMapping("/delete/{id}")
    public String deleteitem(@PathVariable(name = "id") int id) {
        String filepath= service.get(id).getPhotosImagePath();
        service.delete(id);
        FileUploadUtil.deleteFile(filepath);
        return "redirect:/";
    }

    //for catagory serch
    @RequestMapping("/accessories")
    public String accessories(Items items, Model model, @AuthenticationPrincipal User user) {
        List<Items> listitem = repo.findByType("Accessories");
        model.addAttribute("listitem", listitem);
        
        if(!(user == null)){
            model.addAttribute("currentUser", user);
        }
        return "home";
    }
    @RequestMapping("/electronics")
    public String electronics(Items items, Model model, @AuthenticationPrincipal User user) {
        List<Items> listitem = repo.findByType("Electronics");
        model.addAttribute("listitem", listitem);
        
        if(!(user == null)){
            model.addAttribute("currentUser", user);
        }
        return "home";
    }
    @RequestMapping("/furniture")
    public String furniture(Items items, Model model, @AuthenticationPrincipal User user) {
        List<Items> listitem = repo.findByType("Furniture");
        model.addAttribute("listitem", listitem);
        
        if(!(user == null)){
            model.addAttribute("currentUser", user);
        }
        return "home";
    }
    @RequestMapping("/phone")
    public String phone(Items items, Model model, @AuthenticationPrincipal User user) {
        List<Items> listitem = repo.findByType("Phone and Tablets");
        model.addAttribute("listitem", listitem);
        
        if(!(user == null)){
            model.addAttribute("currentUser", user);
        }
        return "home";
    }
    @RequestMapping("/property")
    public String property(Items items, Model model, @AuthenticationPrincipal User user) {
        List<Items> listitem = repo.findByType("Property");
        model.addAttribute("listitem", listitem);
        
        if(!(user == null)){
            model.addAttribute("currentUser", user);
        }
        return "home";
    }
    @RequestMapping("/vehicle")
    public String vehicle(Items items, Model model, @AuthenticationPrincipal User user) {
        List<Items> listitem = repo.findByType("VehicleS");
        model.addAttribute("listitem", listitem);
        
        if(!(user == null)){
            model.addAttribute("currentUser", user);
        }
        return "home";
    }
}
