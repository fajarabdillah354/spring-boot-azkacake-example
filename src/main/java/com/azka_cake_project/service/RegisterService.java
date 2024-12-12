package com.azka_cake_project.service;


import com.azka_cake_project.dto.RegisterDto;
import com.azka_cake_project.entity.Register;
import com.azka_cake_project.repository.RegisterRepository;
import jakarta.persistence.PreUpdate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private ModelMapper modelMapper;


    //CREATE
    public ResponseEntity<Register> createRegister(RegisterDto register){
        Register save = new Register();
        save.setNamaDepan(register.getNamaDepan());
        save.setNamaTengah(register.getNamaTengah());
        save.setNamaBelakang(register.getNamaBelakang());
        save.setEmail(register.getEmail());

        modelMapper.map(save, RegisterDto.class);
        return ResponseEntity.ok(registerRepository.save(save));
    }




    //GET LIST REGISTERS
    public List<RegisterDto> getAllRegister(){
        return registerRepository.findAll()
                .stream()
                .map(register -> modelMapper.map(register, RegisterDto.class))
                .collect(Collectors.toList());
    }



    //UPDATE REGISTER
    public ResponseEntity<Register> updateRegister(String id, RegisterDto registerDto){
        return registerRepository.findById(id)
                .map(register -> {
                    register.setNamaDepan(registerDto.getNamaDepan());
                    register.setNamaTengah(registerDto.getNamaTengah());
                    register.setNamaBelakang(registerDto.getNamaBelakang());
                    register.setEmail(registerDto.getEmail());

                    modelMapper.map(register, RegisterDto.class);
                    Register save = registerRepository.save(register);
                    return ResponseEntity.ok(save);


                }).orElse(ResponseEntity.notFound().build());

    }


    //DELETE REGISTER
    public ResponseEntity<String> deleteRegister(String id){
        Optional<Register> optional = registerRepository.findById(id);
        if (optional.isPresent()){
            registerRepository.delete(optional.get());
            return ResponseEntity.ok("berhasil menghapus id : "+ id);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
