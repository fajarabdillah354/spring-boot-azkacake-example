package com.azka_cake_project.controller;


import com.azka_cake_project.dto.LoginDto;
import com.azka_cake_project.entity.Login;
import com.azka_cake_project.entity.Register;
import com.azka_cake_project.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/logins")
    public List<LoginDto> getAllLogin(){
        return loginService.getAllLogin();
    }

    @PostMapping("/create")
    public Login createLogin(@PathVariable Login login){
        return loginService.createLogin(login);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Login> updateLogin(@PathVariable String id, @RequestBody LoginDto dto){
        return loginService.updateLogin(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLogin(@PathVariable String id){
        return loginService.deleteLogin(id);
    }


}
