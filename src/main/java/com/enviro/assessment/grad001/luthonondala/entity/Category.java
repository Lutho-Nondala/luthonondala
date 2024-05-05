package com.enviro.assessment.grad001.luthonondala.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

import java.util.Set;

@Entity
@Setter
@Getter
@ToString
public class Category {
    @Id
    @GeneratedValue
    private long id;
    private String category;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Waste> wastes;

    public Category(){}

    public Category(Builder builder){
        this.id = builder.id;
        this.category = builder.category;
        this.wastes = builder.wastes;
    }

    public static class Builder{
        private long id;
        private String category;
        private Set<Waste> wastes;

        public Builder setId(long id){
            this.id = id;
            return this;
        }

        public Builder setCategory(String category){
            this.category = category;
            return this;
        }

        public Builder setWastes(Set<Waste> wastes){
            this.wastes = wastes;
            return this;
        }

        public Builder copy(Category category){
            this.id = category.id;
            this.category = category.category;
            this.wastes = category.wastes;
            return this;
        }

        public Category build(){
            return new Category(this);
        }
    }
}
