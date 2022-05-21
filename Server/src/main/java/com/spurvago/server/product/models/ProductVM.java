package com.spurvago.server.product.models;

import com.spurvago.database.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductVM {
    private Long id;
    private String productName;
    private BigDecimal retailPrice;
    private BigDecimal wholesalePrice;

    public ProductVM() {
    }

    public ProductVM(Product entity) {
        this.id = entity.getId();
        this.productName = entity.getProductName();
        this.retailPrice = entity.getRetailPrice();
        this.wholesalePrice = entity.getWholesalePrice();
    }
}
