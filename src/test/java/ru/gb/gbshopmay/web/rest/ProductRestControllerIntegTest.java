package ru.gb.gbshopmay.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import ru.gb.gbapimay.common.enums.Status;
import ru.gb.gbapimay.product.dto.ProductDto;
import ru.gb.gbshopmay.dao.CategoryDao;
import ru.gb.gbshopmay.dao.ManufacturerDao;
import ru.gb.gbshopmay.dao.ProductDao;
import ru.gb.gbshopmay.entity.Category;
import ru.gb.gbshopmay.entity.Manufacturer;
import ru.gb.gbshopmay.web.dto.mapper.CategoryMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class ProductRestControllerIntegTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductDao productDao;
    @Autowired
    CategoryDao categoryDao;
    @Autowired
    ManufacturerDao manufacturerDao;
    @Autowired
    CategoryMapper categoryMapper;


    @Autowired
    ObjectMapper objectMapper;

    public static final String APPLE_COMPANY_NAME = "Apple";
    public static final String ELECTRONIC_CATEGORY_NAME = "Apple";

    @Test
    @Order(1)
    public void saveTest() throws Exception {

        // given
        Manufacturer savedManufacturer = manufacturerDao.save(Manufacturer.builder()
                .name(APPLE_COMPANY_NAME)
                .build());
        Category savedCategory = categoryDao.save(Category.builder()
                .title(ELECTRONIC_CATEGORY_NAME)
                .build());


        mockMvc.perform(post("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(ProductDto.builder()
                                        .title(APPLE_COMPANY_NAME)
                                        .cost(new BigDecimal("100.00"))
                                        .status(Status.ACTIVE)
                                        .manufactureDate(LocalDate.now())
                                        .manufacturer(APPLE_COMPANY_NAME)
                                        .categories(Set.of(categoryMapper.toCategoryDto(savedCategory)))
                                        .build())))
                .andExpect(status().isCreated());

        assertEquals(1, productDao.findAll().size());
    }

    @Test
    @Order(2)
    public void findAllTest() throws Exception {

        mockMvc.perform(get("/api/v1/product"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("id")))
                .andExpect(jsonPath("$.[0].id").value("1"))
                .andExpect(jsonPath("$.[0].title").value(APPLE_COMPANY_NAME));
    }

    @Test
    @Order(3)
    public void deleteTest() throws Exception {

        mockMvc.perform(delete("/api/v1/product/1"))
                .andExpect(status().isNoContent());
    }
}