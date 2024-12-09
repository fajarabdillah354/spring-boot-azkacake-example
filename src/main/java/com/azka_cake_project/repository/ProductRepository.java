package com.azka_cake_project.repository;

import com.azka_cake_project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findByNamaProduct(String name);

}
