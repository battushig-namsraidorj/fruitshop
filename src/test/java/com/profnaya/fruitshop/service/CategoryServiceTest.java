package com.profnaya.fruitshop.service;

import com.profnaya.fruitshop.api.v1.mapper.CategoryMapper;
import com.profnaya.fruitshop.api.v1.model.CategoryDTO;
import com.profnaya.fruitshop.domain.Category;
import com.profnaya.fruitshop.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CategoryServiceTest {

    public static final Long ID = 2L;
    public static final String NAME = "Jimmy";

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    void getAllCategories() throws Exception {

        //given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categories);

        //when
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

        //then
        assertEquals(3, categoryDTOS.size());
    }

    @Test
    void getCategoryByName() throws Exception {

        //given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        //when
        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        //then
        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }
}