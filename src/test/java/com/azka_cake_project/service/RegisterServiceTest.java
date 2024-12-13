//package com.azka_cake_project.service;
//
//import com.azka_cake_project.entity.Register;
//import com.azka_cake_project.repository.RegisterRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Objects;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//class RegisterServiceTest {
//
//    @Mock
//    private RegisterRepository registerRepository;
//
//    @InjectMocks
//    private RegisterService registerService;
//
//    private Register register;
//    @BeforeEach
//    void setUp() {
//
//        MockitoAnnotations.openMocks(this);
//
//
//        register = new Register();
//        register.setId("fsa24-21af");
//        register.setNamaDepan("fajar");
//        register.setNamaTengah("abdillah");
//        register.setNamaBelakang("ahmad");
//        register.setEmail("fajardillah25@gmail.com");
//        register.setUsername("ujeh");
//        register.setPassword("rahasia");
//        register.setConfirmPassword("rahasia");
//
//    }
//
//    @Test
//    void test_createRegister() {
//
//        when(registerRepository.save(any(Register.class))).thenReturn(register);
//
//        Register serviceRegister = registerService.createRegister(register);
//
//        assertEquals("fsa24-21af", serviceRegister.getId());
//        assertEquals("fajar", serviceRegister.getNamaDepan());
//        assertEquals("abdillah", serviceRegister.getNamaTengah());
//        assertEquals("ahmad", serviceRegister.getNamaBelakang());
//        assertEquals("fajardillah25@gmail.com", serviceRegister.getEmail());
//        assertEquals("ujeh", serviceRegister.getUsername());
//        assertEquals("rahasia", serviceRegister.getPassword());
//        assertEquals("rahasia", serviceRegister.getConfirmPassword());
//
//
//        verify(registerRepository, times(1)).save(register);
//    }
//
//
//    @Test
//    void test_updateRegister() {
//
//        when(registerRepository.save(any(Register.class))).thenReturn(register);
//        when(registerRepository.findById("fsa24-21af")).thenReturn(Optional.of(register));
//
//
//        ResponseEntity<Register> response = registerService.updateRegister("fsa24-21af", register);
//
//        assertEquals("fsa24-21af", Objects.requireNonNull(response.getBody()).getId());
//        assertNotEquals("ipul", "fajar");
//        assertNotEquals("kepo", response.getBody().getPassword());
//        assertNotEquals("kepo", response.getBody().getConfirmPassword());
//
////        verify(registerRepository.findById("fsa24-21af"));
//        verify(registerRepository, times(1)).save(any(Register.class));
//
//    }
//
//
//    @Test
//    void test_deleteRegister() {
//
//        when(registerRepository.findById("fsa24-21af")).thenReturn(Optional.of(register));
//        doNothing().when(registerRepository).delete(register);
//
//        ResponseEntity<String> response = registerService.deleteRegister("fsa24-21af");
//        assertEquals("fsa24-21af", response.getBody());
//
//
//        verify(registerRepository).delete(register);
//
//    }
//}
//
