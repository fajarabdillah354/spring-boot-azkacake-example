package com.azka_cake_project.repository;

import com.azka_cake_project.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<Register, String> {


}