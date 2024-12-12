package com.azka_cake_project.service;

import com.azka_cake_project.dto.LoginDto;
import com.azka_cake_project.entity.Login;
import com.azka_cake_project.repository.LoginRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoginService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LoginRepository loginRepository;

    public List<LoginDto> getAllLogin(){
        return loginRepository.findAll()
                .stream()
                .map(login -> modelMapper.map(login, LoginDto.class))
                .collect(Collectors.toList());
    }


    public Login createLogin(Login login){
        return loginRepository.save(login);
    }

    public ResponseEntity<Login> updateLogin(String id, LoginDto dto){
        return loginRepository.findById(id)
                .map(login -> {
                    login.setUsername(dto.getUsername());

                    modelMapper.map(login, LoginDto.class);
                    Login save = loginRepository.save(login);

                    return ResponseEntity.ok(save);
                }).orElse(ResponseEntity.notFound().build());
    }


    public ResponseEntity<String> deleteLogin(String id){
        Optional<Login> optional = loginRepository.findById(id);
        if (optional.isPresent()){
            loginRepository.delete(optional.get());
            return ResponseEntity.ok("login dengan id : "+id+" telah terhapus");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
