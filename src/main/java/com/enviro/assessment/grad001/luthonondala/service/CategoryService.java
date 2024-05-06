package com.enviro.assessment.grad001.luthonondala.service;

import com.enviro.assessment.grad001.luthonondala.entity.Category;
import com.enviro.assessment.grad001.luthonondala.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public Category create(Category category){
        return repository.save(category);
    }

    public Category read(long id){
        return repository.findById(id).get();
    }

    public Category update(Category category){
        return repository.save(category);
    }

    public void delete(long id){
        repository.deleteById(id);
    }

    public List<Category> getAll(){
        return repository.findAll();
    }
}
