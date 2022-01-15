package com.project.agenagn;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItemOrder {
    private String name;
    private String pNumber;
    private String email;

    private List<Agenagn> itemList = new ArrayList<>();

    public void addAgenagn(Agenagn item){
        this.itemList.add(item);
    }
}
