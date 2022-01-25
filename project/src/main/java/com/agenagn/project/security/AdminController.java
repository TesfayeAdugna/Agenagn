package com.agenagn.project.security;

import java.util.List;

import com.agenagn.project.Contact;
import com.agenagn.project.ContactService;
import com.agenagn.project.FileUploadUtil;
import com.agenagn.project.ItemRepository;
import com.agenagn.project.Items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Autowired
    private ContactService service;


    @GetMapping("/admin")
    public String admin() {
      return "adminLogin";
    } 


    @GetMapping("/user")
    public String showUser() {
        List<User>users=(List<User>) userRepository.findAll();
      return "registration";
    }    

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
   public String deleteUser(@PathVariable(name = "id") Long id) {
       User user=userRepository.findById(id).get();
		   List<Items>items = itemRepository.findByUser(user);
		   for(Items item:items){
        // String filepath= item.getPhotosImagePath();
				itemRepository.delete(item);
        // FileUploadUtil.deleteFile(filepath);
		   }
      //  if(user.hasPhoto()){
      //   FileUploadUtil.deleteFile(filepath);
      //  }
		   userRepository.delete(user);
       return "getmessage"; 
   }
   
   @GetMapping("/getmessage")
   @PreAuthorize("hasRole('ADMIN')")
    public String message(Model model ,@AuthenticationPrincipal User user){
        List<Contact> listcontact = service.listAll();
        List<User> users=(List<User>) userRepository.findAll();
        model.addAttribute("listcontact", listcontact);
        model.addAttribute("listuser", users);
        if(!(user == null)){
          model.addAttribute("currentUser", user);
      }
        return "getmessage";
    }
}
