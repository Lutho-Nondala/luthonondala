package com.enviro.assessment.grad001.luthonondala.controller;

import com.enviro.assessment.grad001.luthonondala.entity.Category;
import com.enviro.assessment.grad001.luthonondala.entity.Waste;
import com.enviro.assessment.grad001.luthonondala.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
class CategoryControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private CategoryService categoryService;
    private  String baseUrl;
    private Category category;

    @BeforeEach
    void setUp(){
        assertNotNull(categoryService);
        this.baseUrl = "http://localhost:" + this.port + "/enviro/category/";
        this.category = new Category.Builder().setCategory("Plastic").build();
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void create() {
        String url = baseUrl + "create";
        ResponseEntity<Category> response = this.restTemplate.postForEntity(url, this.category, Category.class);
        log.info("Response: {}", response.getBody());
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () ->  assertNotNull(response.getBody())
        );
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void read() {
        String url = baseUrl + "read/1";
        ResponseEntity<Category> response = this.restTemplate.getForEntity(url, Category.class);
        log.info(response.toString());
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () ->  assertNotNull(response.getBody())
        );
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void update() {
        String url = baseUrl + "read/1";
        ResponseEntity<Category> readRresponse = this.restTemplate.getForEntity(url, Category.class);
        log.info("Read Response: {}", readRresponse.getBody());

        this.category = readRresponse.getBody();
        this.category.setCategory("Metal");

        url = baseUrl + "update";
        this.restTemplate.put(url, this.category);
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    void delete() {
        String url = baseUrl + "delete/1";
        this.restTemplate.delete(url);
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void getAll() {
        String url = baseUrl + "getAll";
        ResponseEntity<Category[]> response = this.restTemplate.getForEntity(url, Category[].class);
        log.info(response.getBody()[0].toString());
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () ->  assertNotNull(response.getBody())
        );
    }
}