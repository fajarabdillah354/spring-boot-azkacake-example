package com.azka_cake_project.service;


import com.azka_cake_project.dto.CustomerDto;
import com.azka_cake_project.entity.Customer;
import com.azka_cake_project.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    /**
     * @Mock digunakan untuk membuat object tiruan yang nantinya akan berinteraksi dengan database
     * @InjectMock digunakan untuk masukan object tiruan ke object baru. disini menguji logic dari service layer
     */
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ModelMapper modelMapper;


    @InjectMocks
    private CustomerService customerService;


    /**
     * class entity yang akan di uji di service layer
     */
    private Customer customer;


    private CustomerDto customerDto;





    /**
     * setUp will be execude each every method in test Classes
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);


        customer = new Customer();
        customer.setId("232d88bb-9cb4-488d-a2ea-bd8476dd5523");
        customer.setNamaCustomer("ujeh");
        customer.setEmail("ujehhhhh@gmail.com");
        customer.setNomorTelepon(6212412);
        customer.setUmur(19);


        customerDto = new CustomerDto();
        customerDto.setNamaCustomer("fajar abdillah ahmad");
        customerDto.setEmail("fajardillah25@gmail.com");
    }


    @Test
    public void test_success_customer(){

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);


        Customer response = customerService.createCustomer(customer).getBody();

        assertNotNull(response);
        assertEquals("232d88bb-9cb4-488d-a2ea-bd8476dd5523", response.getId());
        assertEquals("ujeh", response.getNamaCustomer());
        assertEquals("ujehhhhh@gmail.com", response.getEmail());
        assertEquals(6212412, response.getNomorTelepon());
        assertEquals(19, response.getUmur());

        verify(customerRepository, times(1)).save(any(Customer.class));

    }

    @Test
    public void test_failed_customer() {
        when(customerRepository.save(any(Customer.class))).thenThrow(new RuntimeException("failed to save customer"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            customerService.createCustomer(customer);
        });

        assertEquals("failed to save customer", exception.getMessage());

        verify(customerRepository, times(1)).save(any(Customer.class));

    }


    //UPDATE TEST
    @Test
    void test_update_customer() {

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        when(customerRepository.findById("232d88bb-9cb4-488d-a2ea-bd8476dd5523")).thenReturn(Optional.of(customer));
        when(modelMapper.map(customer, CustomerDto.class)).thenReturn(customerDto);

        ResponseEntity<Customer> response = customerService.updateCustomer("232d88bb-9cb4-488d-a2ea-bd8476dd5523", customerDto);

//        assertEquals(200, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("232d88bb-9cb4-488d-a2ea-bd8476dd5523", response.getBody().getId());
        assertEquals("fajar abdillah ahmad", response.getBody().getNamaCustomer());
        assertEquals("fajardillah25@gmail.com", response.getBody().getEmail());


        verify(customerRepository, times(1)).findById("232d88bb-9cb4-488d-a2ea-bd8476dd5523");
        verify(customerRepository, times(1)).save(any(Customer.class));

    }


    //DELETE TEST
    @Test
    void test_delete_customer() {

        when(customerRepository.findByNamaCustomer("ujeh")).thenReturn(Optional.of(customer));
        doNothing().when(customerRepository).delete(customer);

        ResponseEntity<String> ujeh = customerService.deteleCustomer("ujeh");



        assertEquals("customer dengan nama ujeh telah terhapus", ujeh.getBody());


        verify(customerRepository, times(1)).findByNamaCustomer("ujeh");
        verify(customerRepository, times(1)).delete(customer);


    }
}