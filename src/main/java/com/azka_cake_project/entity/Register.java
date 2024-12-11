package com.azka_cake_project.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    @Column(name = "nama_depan")
    private String namaDepan;

    @Column(name = "nama_tengah")
    private String namaTengah;

    @Column(name = "nama_belakang")
    private String namaBelakang;

    private String email;

    private String username;

    private String password;

    @Column(name = "confirm_password")
    private String confirmPassword;


}
