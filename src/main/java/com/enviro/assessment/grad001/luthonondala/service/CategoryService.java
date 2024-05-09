package com.enviro.assessment.grad001.luthonondala.service;

import com.enviro.assessment.grad001.luthonondala.entity.Category;
import com.enviro.assessment.grad001.luthonondala.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category create(Category category){
        return categoryRepository.save(category);
    }

    public Category read(long id){
        return categoryRepository.findById(id).get();
    }

    public Category update(Category category){
        return categoryRepository.save(category);
    }

    public void delete(long id){
        categoryRepository.deleteById(id);
    }

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }
}
