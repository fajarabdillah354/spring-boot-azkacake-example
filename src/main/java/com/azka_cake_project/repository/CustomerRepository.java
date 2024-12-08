package com.azka_cake_project.repository;

import com.azka_cake_project.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    Optional<Customer> findByNamaCustomer(String name);

}
