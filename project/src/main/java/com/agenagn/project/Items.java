package com.agenagn.project;

import java.beans.Transient;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.agenagn.project.security.User;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity
public class Items {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank(message="Please enter your name")
    private String name;
    @Column(length = 70)
    @Length(min = 10, message = "You have to write at least 10 characters for short description.")
    @Length(max = 70, message = "You have to write less than 70 characters for short description.")
    private String shortDescription;
    @Column(length = 500)
    @Length(min = 50, message = "You have to write at least 50 characters for detail description.")
    private String detailDescription;
    private double price;
    @NotNull
    @Length(min = 10,max = 10, message = "Phone number must contain 10 digits." )
    @Digits(integer = 10, fraction = 0, message = "Phone number must be digits.")
    private String contact;
    @NotBlank(message="Type is required")
    private String type;
    @Column(nullable = true, length = 64)
    private String photos;
    @Temporal(TemporalType.DATE)
    private Date created_Date = new Date(System.currentTimeMillis());

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;
         
        return "/user-photos/" + id + "/" + photos;
    }
}
