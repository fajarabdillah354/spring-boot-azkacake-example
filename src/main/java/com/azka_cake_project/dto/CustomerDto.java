package com.azka_cake_project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerDto {

    @NotNull(message = "Customer name cannot be null")
    @NotBlank(message = "Customer name cannot blank or empty")
    private String namaCustomer;



    @NotNull(message = "email cannot be null")
    @NotBlank(message = "email cannot blank or empty")
    @Email(message = "write correctly email format")
    private String email;


}
