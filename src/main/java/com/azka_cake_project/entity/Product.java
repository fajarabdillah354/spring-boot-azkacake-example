package com.azka_cake_project.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;



    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @NotNull
    private String namaProduct;


    @NotNull
    private Integer hargaProduct;


    @NotNull
    private Integer quantity;

    @NotNull
    private Timestamp createAT;

}
