package com.ads.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "serial_number")
    private UUID serialNumber;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "category", nullable = false)
    private String category;
    @Column(name = "price", nullable = false)
    private double price;


    public Product(String title, String category, double price) {
        this.title = title;
        this.category = category;
        this.price = price;
    }


}
