package com.project.agenagn;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    private Double price;
    private String description;
    
    @Enumerated(EnumType.STRING)
    private Type kind;


    public enum Type{
        CAR, PHONE, CHAIR, TABLE, CLOTH, COMPUTER
    }
}
