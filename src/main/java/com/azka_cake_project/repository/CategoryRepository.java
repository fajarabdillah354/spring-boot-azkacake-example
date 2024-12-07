package com.azka_cake_project.repository;

import com.azka_cake_project.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findByCategoryName(String name);

}
