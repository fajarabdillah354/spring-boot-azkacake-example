package com.azka_cake_project.controller;


import com.azka_cake_project.dto.RegisterDto;
import com.azka_cake_project.entity.Register;
import com.azka_cake_project.service.RegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/registers")
    public List<RegisterDto> getAllRegister(){
        return registerService.getAllRegister();
    }


    @PostMapping("/create")
    public ResponseEntity<Register> createRegister(@Valid @RequestBody RegisterDto registerDto){
        return registerService.createRegister(registerDto);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Register> updateRegister(@PathVariable String id, @RequestBody RegisterDto registerDto){
        return registerService.updateRegister(id, registerDto);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRegister(@PathVariable String id){
        return registerService.deleteRegister(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleError(MethodArgumentNotValidException exception){

        var errors = new HashMap<String, String>();
        exception.getBindingResult().getAllErrors()
                .forEach(objectError -> {

                    String fieldName = ((FieldError) objectError).getField();
                    String messageError = objectError.getDefaultMessage();

                    errors.put(fieldName, messageError);

                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


}
