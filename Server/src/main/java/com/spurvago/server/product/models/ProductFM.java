package com.spurvago.server.product.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Model formularza magazynu
 */
@Getter
@Setter
public class ProductFM {
    private String productName;
    private BigDecimal retailPrice;
    private BigDecimal wholesalePrice;
    private int productAmount;
}
