package com.profnaya.fruitshop.controller;

import com.profnaya.fruitshop.api.v1.model.CategoryDTO;
import com.profnaya.fruitshop.controller.v1.CategoryController;
import com.profnaya.fruitshop.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryControllerTest {

    public static final String NAME = "Nuts";
    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    void testListCategories() throws Exception {
        CategoryDTO category1 = new CategoryDTO();
        category1.setId(1L);
        category1.setName("John");

        CategoryDTO category2 = new CategoryDTO();
        category2.setId(2L);
        category2.setName("Doe");

        List<CategoryDTO> categories = Arrays.asList(category1, category2);

        when(categoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));

        verify(categoryService, times(1)).getAllCategories();
    }


    @Test
    void testGetCategoryByName() throws Exception{
        CategoryDTO category = new CategoryDTO();
        category.setId(1L);
        category.setName(NAME);

        when(categoryService.getCategoryByName(anyString())).thenReturn(category);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories/Nuts")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }
}