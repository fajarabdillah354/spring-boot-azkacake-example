package com.azka_cake_project.dto;

import com.azka_cake_project.util.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class AppUserDto {

    @NotNull(message = "fullname cannot be null")
    @NotBlank(message = "fullname cannot blank")
    private String fullname;

    @NotNull(message = "username cannot be null")
    private String username;

    @NotNull(message = "password cannot be null")
    private String password;


    private UserRole userRole;


}
