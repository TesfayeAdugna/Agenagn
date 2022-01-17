package com.agenagn.project.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

@Data
public class RegistrationForm {
   private String username;
   private String password;
   private String firstName;
   private String middleName;
   private String lastName;
   private String phone;
   private String email;

   User toUser(PasswordEncoder encoder) {
       User user = new User();
       user.setUsername(this.username);
       user.setPassword(encoder.encode(this.password));
       user.setFirstName(this.firstName);
       user.setMiddleName(this.middleName);
       user.setLastName(this.lastName);
       user.setPhone(this.phone);
       user.setEmail(this.email);
       return user;
   }
}

