package com.azka_cake_project.controller;


import com.azka_cake_project.dto.AppUserDto;
import com.azka_cake_project.entity.AppUser;
import com.azka_cake_project.service.AppUserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private AppUserService appUserService;


    @Autowired
    private ModelMapper modelMapper;


    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody AppUserDto userAppDto){
        AppUser user = new AppUser();
        user.setFullname(userAppDto.getFullname());
        user.setUsername(userAppDto.getUsername());
        user.setPassword(userAppDto.getPassword());
        user.setUserRole(userAppDto.getUserRole());

        modelMapper.map(userAppDto, AppUser.class);

        appUserService.register(user);

        return new ResponseEntity<>("User Registered Success", HttpStatus.CREATED);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> errorHandle(MethodArgumentNotValidException exception){

        var errors = new HashMap<String, String>();
        exception.getBindingResult().getAllErrors()
                .forEach(objectError -> {

                    String fieldname = ((FieldError) objectError).getField();
                    String message = objectError.getDefaultMessage();

                    errors.put(fieldname, message);

                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }






}
