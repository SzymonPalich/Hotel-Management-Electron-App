package com.spurvago.server.product;

import com.spurvago.components.Validator;
import com.spurvago.server.product.models.ProductFM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Walidator magazynu
 */
@Component
public class ProductValidator extends Validator {
    private final ProductRepository productRepository;

    @Autowired
    public ProductValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean validate(ProductFM model) {
        if (!(haveLength(model.getProductName(), 50))) {
            return false;
        }
        if (!(correctDecimal(model.getRetailPrice(), 8, 2))) {
            return false;
        }
        return correctDecimal(model.getWholesalePrice(), 8, 2);
    }
}
