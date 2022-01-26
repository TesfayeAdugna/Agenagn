package com.agenagn.project.security;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class RegistrationForm {
    @NotNull
    @Size(min = 3, message = "Username must contain at least 3 characters")
    private String username;
    @NotNull
    @Size(min = 5, message = "Password must contain at least 5 characters")
    private String password;
    @NotNull
    @Size(min = 3, message = "First name must contain at least 3 characters")
    private String firstName;
    @NotNull
    @Size(min = 3, message = "Last name must contain at least 3 characters")
    private String lastName;

    @Length(min = 10,max = 10, message = "Phone number must contain 10 digits." )
    @Digits(integer = 10, fraction = 0, message = "Phone number must be digits.")
    private String phone;
    @NotBlank
    @Email(message = "Please enter a valid e-mail address")
    private String email;

    private String photo;

    User toUser(PasswordEncoder encoder) {
        User user = new User();
        user.setUsername(this.username);
        user.setPassword(encoder.encode(this.password));
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setPhone(this.phone);
        user.setEmail(this.email);
        user.setPhoto(this.photo);
        user.setRole("ROLE_USER");
        return user;
    }
}

