package com.azka_cake_project.service;


import com.azka_cake_project.dto.CustomerDto;
import com.azka_cake_project.entity.Customer;
import com.azka_cake_project.entity.Payment;
import com.azka_cake_project.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Get all Customers
    public List<CustomerDto> customers(){
        return customerRepository.findAll().stream().map(customer -> modelMapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());

    }


    //Create Customers
    public ResponseEntity<Customer> createCustomer(Customer customer){
        return ResponseEntity.ok(customerRepository.save(customer));
    }


    // UPDATE CUSTOMERS
    public ResponseEntity<Customer> updateCustomer(String id, CustomerDto dto){
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setNamaCustomer(dto.getNamaCustomer());
                    customer.setEmail(dto.getEmail());

                    modelMapper.map(customer, CustomerDto.class);
                    Customer save = customerRepository.save(customer);
                    return ResponseEntity.ok(save);
                }).orElse(ResponseEntity.notFound().build());
    }


    //DELETE CUSTOMER
    public ResponseEntity<String> deteleCustomer(String name){
        Optional<Customer> optional = customerRepository.findByNamaCustomer(name);
        if (optional.isPresent()){
            customerRepository.delete(optional.get());
            return ResponseEntity.ok("customer dengan nama "+ name +" telah terhapus");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("customer dengan nama "+name+" tidak ditemukan");
        }
    }



}
