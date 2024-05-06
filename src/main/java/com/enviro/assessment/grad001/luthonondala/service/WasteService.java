package com.enviro.assessment.grad001.luthonondala.service;

import com.enviro.assessment.grad001.luthonondala.entity.Waste;
import com.enviro.assessment.grad001.luthonondala.repository.WasteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WasteService {
    @Autowired
    private WasteRepository repository;

    public Waste create(Waste waste){
        return repository.save(waste);
    }

    public Waste read(long id){
        return repository.findById(id).get();
    }

    public Waste update(Waste waste){
        return repository.save(waste);
    }

    public void delete(long id){
        repository.deleteById(id);
    }

    public List<Waste> getAll(){
        return repository.findAll();
    }
}
