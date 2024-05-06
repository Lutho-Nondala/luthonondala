package com.enviro.assessment.grad001.luthonondala.controller;

import com.enviro.assessment.grad001.luthonondala.entity.Category;
import com.enviro.assessment.grad001.luthonondala.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("enviro/category/")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @PostMapping("create")
    public Category create(@RequestBody Category category){
        try {
            return this.service.create(category);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("read/{id}")
    public Category read(@PathVariable long id){
        try {
            return this.service.read(id);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PutMapping("update")
    public Category update(@RequestBody Category category){
        try {
            return this.service.update(category);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable long id){
        try {
            this.service.delete(id);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("getAll")
    public List<Category> getAll(){
        try {
            return this.service.getAll();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
