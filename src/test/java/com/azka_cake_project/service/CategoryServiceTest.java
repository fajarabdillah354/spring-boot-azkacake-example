package com.azka_cake_project.service;

import com.azka_cake_project.dto.CategoryDto;
import com.azka_cake_project.entity.Category;
import com.azka_cake_project.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryServiceTest {


    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ModelMapper modelMapper;


    @InjectMocks
    private CategoryService categoryService;


    private Category category;

    private CategoryDto categoryDto;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        category = new Category();
        category.setId("41k1d12-gja4f3-1dl1f");
        category.setCategoryName("Bakery");
        category.setDeskripsi("Special Bakery edision");

        categoryDto = new CategoryDto();
        categoryDto.setCategoryName("Bread");

    }


    //CREATE TEST
    @Test
    void test_createCategory() {

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        ResponseEntity<Category> response = categoryService.createCategory(category);

        assertEquals("41k1d12-gja4f3-1dl1f", response.getBody().getId());
        assertEquals("Bakery", response.getBody().getCategoryName());
        assertEquals("Special Bakery edision", response.getBody().getDeskripsi());

        verify(categoryRepository, times(1)).save(category);

    }


    //UPDATE TEST
    @Test
    void test_updateCategory() {

        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        when(categoryRepository.findByCategoryName("Bread")).thenReturn(Optional.of(category));
        when(modelMapper.map(category, CategoryDto.class)).thenReturn(categoryDto);


        ResponseEntity<Category> response = categoryService.updateCategory("Bread", categoryDto);

        assertEquals("Bread", response.getBody().getCategoryName());

        verify(categoryRepository, times(1)).findByCategoryName("Bread");
        verify(categoryRepository, times(1)).save(any(Category.class));

    }


    // DELETE TEST
    @Test
    void test_deleteCategory() {

        when(categoryRepository.findByCategoryName("Bread")).thenReturn(Optional.of(category));
        doNothing().when(categoryRepository).delete(category);

        ResponseEntity<String> response = categoryService.deleteCategory("Bread");
        assertEquals("Category dengan nama Bread berhasil dihapus", response.getBody());

        verify(categoryRepository, times(1)).findByCategoryName("Bread");
        verify(categoryRepository, times(1)).delete(category);


    }
}