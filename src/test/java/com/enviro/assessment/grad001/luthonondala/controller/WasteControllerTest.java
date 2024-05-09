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
import org.springframework.http.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
class WasteControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private WasteController wasteController;
    @Autowired
    private CategoryService categoryService;

    private  String baseUrl;
    private Category category;
    private Waste waste;

    @BeforeEach
    void setUp(){
        assertNotNull(wasteController);
        assertNotNull(categoryService);
        this.baseUrl = "http://localhost:" + this.port + "/enviro/waste/";
    }
    @Test
    @org.junit.jupiter.api.Order(1)
    void create() {
        //This is where you create a category to add to the waste
        String urlToCreateCategories = "http://localhost:" + this.port + "/enviro/category/create";

        Category plasticCategory = new Category.Builder().setCategory("Plastic").build();

        this.category = this.restTemplate.postForEntity(
                urlToCreateCategories,
                plasticCategory,
                Category.class
        ).getBody();
        log.info("Created Category: {}", this.category);

        //Now starts the code to test the "create" method for the WasteController

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.MULTIPART_FORM_DATA_VALUE));

        this.waste = new Waste.Builder()
                .setDescription("Plastic Bottle")
                .setDisposalGuidelines("Put with other plastic items")
                .setRecyclingTips("Recycle into new materials")
                .setCategory(this.category)
                .build();

        MultiValueMap<String, Object> body= new LinkedMultiValueMap<>();
        body.add("Waste", this.waste);
        body.add("Category", 1);

        HttpEntity<?> entity = new HttpEntity<MultiValueMap<String, Object>>(body,headers);

        String url = baseUrl + "create";
        ResponseEntity<Waste> response = this.restTemplate.postForEntity(url, entity, Waste.class);
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
        ResponseEntity<Waste> response = this.restTemplate.getForEntity(url, Waste.class);
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
        ResponseEntity<Waste> readRresponse = this.restTemplate.getForEntity(url, Waste.class);
        log.info("Read Response: {}", readRresponse.getBody());

        this.waste = readRresponse.getBody();
        this.waste.setDescription("Plastic Bowl");

        url = baseUrl + "update";
        this.restTemplate.put(url, this.waste);
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void updateCategory() {

        //This is where 2 extra Categories are made for the test

        String urlToCreateCategories = "http://localhost:" + this.port + "/enviro/category/create";

        Category category1 = new Category.Builder().setCategory("Foam").build();
        Category category2 = new Category.Builder().setCategory("Fabric").build();

        ResponseEntity<Category> responseCategory1 = this.restTemplate.postForEntity(urlToCreateCategories, category1, Category.class);
        log.info("Added Category Response: {}", responseCategory1.getBody());

        ResponseEntity<Category> responseCategory2 = this.restTemplate.postForEntity(urlToCreateCategories, category2, Category.class);
        log.info("Added Category Response: {}", responseCategory2.getBody());

        //This is where the code to test the "updateCategory" method begins

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.MULTIPART_FORM_DATA_VALUE));

        MultiValueMap<String, Object> body= new LinkedMultiValueMap<>();
        body.add("Waste ID", 1);
        body.add("Category ID", 2);

        HttpEntity<?> updateEntity = new HttpEntity<MultiValueMap<String, Object>>(body,headers);

        String url = baseUrl + "updateCategory";
        this.restTemplate.put(url, updateEntity);
    }

    @Test
    @org.junit.jupiter.api.Order(6)
    void delete() {
        String url = baseUrl + "delete/1";
        this.restTemplate.delete(url);
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    void getAll() {
        String url = baseUrl + "getAll";
        ResponseEntity<Waste[]> response = this.restTemplate.getForEntity(url, Waste[].class);
        log.info(response.getBody()[0].toString());
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () ->  assertNotNull(response.getBody())
        );
    }
}