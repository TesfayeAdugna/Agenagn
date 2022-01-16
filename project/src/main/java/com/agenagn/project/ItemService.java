package com.agenagn.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repo;

    public List<Items>listAll(){
        return repo.findAll();
    }

    public void save(Items items){
        repo.save(items);
    }
    public Items get(long id){
        return repo.findById(id).get();
    }
    public void delete(long id){
        repo.deleteById(id);
    }
}
