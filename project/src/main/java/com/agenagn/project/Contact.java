package com.agenagn.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.agenagn.project.security.User;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 3, message = "name must contain at least 3 characters")
    private String name;
    @NotBlank
    @Email(message = "Please enter a valid e-mail address")
    private String email;
    private String subject;
    @Length(min = 30, message = "You have to write at least 30 characters for message.")
    private String message;
    @ManyToOne
    private User user;
}
