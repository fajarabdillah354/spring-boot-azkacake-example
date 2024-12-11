package com.azka_cake_project.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;



    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;



    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;


    private String namaCustomer;


    private Integer umur;


    private Integer nomorTelepon;



    private String email;



}
