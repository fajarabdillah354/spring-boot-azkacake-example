package com.azka_cake_project.repository;

import com.azka_cake_project.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, String> {

    Optional<AppUser> findByUsername(String username);

}
