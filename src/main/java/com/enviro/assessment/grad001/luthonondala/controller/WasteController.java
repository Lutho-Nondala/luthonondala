package com.enviro.assessment.grad001.luthonondala.controller;

import com.enviro.assessment.grad001.luthonondala.entity.Waste;
import com.enviro.assessment.grad001.luthonondala.service.WasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("enviro/waste/")
public class WasteController {
    @Autowired
    private WasteService service;

    @PostMapping("create")
    public Waste create(@RequestBody Waste waste){
        try {
            return this.service.create(waste);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("read/{id}")
    public Waste read(@PathVariable long id){
        try {
            return this.service.read(id);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PutMapping("update")
    public Waste update(@RequestBody Waste waste){
        try {
            return this.service.update(waste);
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
    public List<Waste> getAll(){
        try {
            return this.service.getAll();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
