package com.azka_cake_project.service;


import com.azka_cake_project.dto.CategoryDto;
import com.azka_cake_project.entity.Category;
import com.azka_cake_project.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    //GET_ALL
    public List<Category> categories(){
        return categoryRepository.findAll();
    }


    //CREATE
    public ResponseEntity<Category> createCategory(Category category){
        return ResponseEntity.ok(categoryRepository.save(category));

    }


    //UPDATE
    public ResponseEntity<Category> updateCategory(String name, CategoryDto categoryDto){
        return categoryRepository.findByCategoryName(name)
                .map(category -> {

                    category.setCategoryName(categoryDto.getCategoryName());

                    modelMapper.map(category, CategoryDto.class);

                    Category save = categoryRepository.save(category);
                    return ResponseEntity.ok(save);

                }).orElse(ResponseEntity.notFound().build());
    }


    //DELETE
    public ResponseEntity<String> deleteCategory(String name){//generic type String karna id menggunakan tipe String UUID
        Optional<Category> optional = categoryRepository.findByCategoryName(name);
        if (optional.isPresent()){
            categoryRepository.delete(optional.get());
            return ResponseEntity.ok("Category dengan nama "+ name + " berhasil dihapus");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category dengan nama " + name + " tidak ditemukan.");
        }
    }




}
