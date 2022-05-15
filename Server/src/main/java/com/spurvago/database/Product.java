package com.spurvago.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String productName;

    @Getter
    @Setter
    private BigDecimal retailPrice;

    @Getter
    @Setter
    private BigDecimal wholesalePrice;

    public Product(){
    }

    public void map(Product src) {
        this.setProductName(src.getProductName());
        this.setRetailPrice(src.getRetailPrice());
        this.setWholesalePrice(src.getWholesalePrice());
    }
}
