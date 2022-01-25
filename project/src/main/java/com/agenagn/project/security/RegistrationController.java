package com.agenagn.project.security;


import java.io.IOException;

import com.agenagn.project.FileUploadUtil;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute("form", new RegistrationForm());
        return "registration";
    }
    @PostMapping
    public String processRegistration(@Valid @ModelAttribute("form") RegistrationForm form, Errors errors, BindingResult bind , @RequestParam("image") MultipartFile multipartFile) {
        if (userRepository.findByUsername(form.getUsername()) != null){
            bind.addError( new FieldError("form","username","User name already exist"));
        }
        if (userRepository.findByEmail(form.getEmail()) != null){
            bind.addError( new FieldError("form","email","This email is used"));
        }

        if (errors.hasErrors()) {
            return "registration";
        }
        
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if(fileName.isEmpty()){form.setPhoto(null);}
         else{form.setPhoto(fileName);}

        userRepository.save(form.toUser(passwordEncoder));
    //save file
    String uploadDir = "user-profile-photo/" + form.getUsername();
    
    try {
    FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    } catch (IOException e) {
    System.out.print("teshome it looks like you have an error");
    }

    return "redirect:/login";

    }
}

