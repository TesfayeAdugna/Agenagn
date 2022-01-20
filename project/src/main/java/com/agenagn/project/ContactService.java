package com.agenagn.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    
    @Autowired
    private ContactRepository repo;

    public List<Contact>listAll(){
        return repo.findAll();
    }
    public void save(Contact contact){
        repo.save(contact);
    }
}
