package com.spurvago.server.product;

import com.spurvago.database.Product;
import com.spurvago.server.product.models.ProductFM;
import com.spurvago.server.product.models.ProductVM;
import org.springframework.stereotype.Component;

@Component
public record ProductMapper() {
    ProductVM mapToVM(Product src) {
        ProductVM dest = new ProductVM();

        dest.setId(src.getId());
        dest.setProductName(src.getProductName());
        dest.setRetailPrice(src.getRetailPrice());
        dest.setWholesalePrice(src.getWholesalePrice());

        return dest;
    }

    Product mapToEntity(ProductFM src) {
        Product dest = new Product();
        dest.setProductName(src.getProductName());
        dest.setRetailPrice(src.getRetailPrice());
        dest.setWholesalePrice(src.getWholesalePrice());
        return dest;
    }

    Product mapToEntity(Product dest, ProductFM src) {
        dest.setProductName(src.getProductName());
        dest.setRetailPrice(src.getRetailPrice());
        dest.setWholesalePrice(src.getWholesalePrice());

        return dest;
    }
}
