package com.azka_cake_project.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class RegisterDto {

    @NotBlank(message = "nama depan tidak boleh kosong")
    @NotNull(message = "nama depan tidak boleh null")
    private String namaDepan;

    @NotBlank(message = "nama tengah tidak boleh kosong")
    @NotNull(message = "nama tengah tidak boleh null")
    private String namaTengah;


    private String namaBelakang;

    @Email
    @NotNull(message = "email tidak boleh kosong")
    private String email;

}
