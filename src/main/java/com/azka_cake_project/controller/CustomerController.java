package com.azka_cake_project.controller;

import com.azka_cake_project.dto.CustomerDto;
import com.azka_cake_project.entity.Category;
import com.azka_cake_project.entity.Customer;
import com.azka_cake_project.repository.CustomerRepository;
import com.azka_cake_project.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {



    @Autowired
    private CustomerService customerService;


    @GetMapping("/customers")
    public List<CustomerDto> customers(){
        return customerService.customers();
    }


    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer( @PathVariable String id,@Valid @RequestBody CustomerDto dto){
        return customerService.updateCustomer(id, dto);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String name){
        return customerService.deteleCustomer(name);
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException exception){
        var errors = new HashMap<String, String>();
        exception.getBindingResult().getAllErrors()
                .forEach(objectError -> {
                    var fieldName = ((FieldError) objectError).getField();
                    var errorMessage = objectError.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }




}
