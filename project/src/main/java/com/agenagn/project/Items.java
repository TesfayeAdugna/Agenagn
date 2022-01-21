package com.agenagn.project;

import java.beans.Transient;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.agenagn.project.security.User;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity
public class Items {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 125)
    @Length(min = 3, max = 125, message = "You have to write at least 3 words sentence for delail description")
    private String shortDescription;
    @Column(length = 500)
    @Length(min = 10, max = 500, message = "You have to write at least 10 words sentence for delail description")
    private String detailDescription;
    private int price;
    private String contact;
    private String type;
    @Column(nullable = true, length = 64)
    private String photos;
    @Temporal(TemporalType.DATE)
    private Date created_Date = new Date(System.currentTimeMillis());

    @ManyToOne
    private User user;

    @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;
         
        return "/user-photos/" + id + "/" + photos;
    }
}
