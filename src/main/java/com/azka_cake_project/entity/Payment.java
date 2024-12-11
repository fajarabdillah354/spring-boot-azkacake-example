package com.azka_cake_project.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "metode_pembayaran")
    private String metodePembayaran;


    @Column(name = "tanggal_pembayaran")
    private Timestamp tanggalPembayaran;


    @Column(name = "jumlah_pembayaran")
    private Long jumlahPembayaran;





}
