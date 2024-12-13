package com.azka_cake_project.controller;


import com.azka_cake_project.dto.CategoryDto;
import com.azka_cake_project.entity.Category;
import com.azka_cake_project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/categories")
    public List<Category> categories(){
        return categoryService.categories();
    }


    @PostMapping("/create")
    public ResponseEntity<Category> entity(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> update(@PathVariable String id, @RequestBody CategoryDto categoryDto){
        return categoryService.updateCategory(id, categoryDto);
    }


    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> delete(@PathVariable String name){
        return categoryService.deleteCategory(name);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleArgumentError(MethodArgumentNotValidException exp){

        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors()
                .forEach(objectError -> {
                    var fieldname = ((FieldError) objectError).getField();
                    var errorMessage = objectError.getDefaultMessage();
                    errors.put(fieldname, errorMessage);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
