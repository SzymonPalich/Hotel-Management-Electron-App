package com.spurvago.server.product;

import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.database.Product;
import com.spurvago.server.product.models.ProductFM;
import com.spurvago.server.product.models.ProductVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductVM find(@PathVariable Long id) {
        return productService.find(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ListPaginated<ProductVM> getList(Pager pager, String search) {
        return productService.getList(pager, search);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ProductVM create(ProductFM newEntity) {
        return productService.create(newEntity);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductVM update(@PathVariable Long id, @RequestBody ProductFM newEntity) {
        return productService.update(id, newEntity);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
