package com.azka_cake_project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDto {

    @NotNull(message = "username cannot null")
    @NotBlank(message = "username cannot blank")
    private String username;

}
