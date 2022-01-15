package com.project.agenagn;

import lombok.Data;

@Data
public class Item {
    private String id;
    private String name;
    private Double price;
    private String description;
    private Type kind;


    public enum Type{
        CAR, PHONE, CHAIR, TABLE, CLOTH, COMPUTER
    }
}
