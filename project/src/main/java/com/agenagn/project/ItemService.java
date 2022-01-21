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
        // search trial
        public List<Items> getAllItems(){
            List<Items> list =  (List<Items>)repo.findAll();
            return list;
           }
        public List<Items> getByKeyword(String keyword){
            return repo.findByKeyword(keyword);
           }
           // search trial
    public Items get(long id){
        return repo.findById(id).get();
    }
    public void delete(long id){
        repo.deleteById(id);
    }
}
