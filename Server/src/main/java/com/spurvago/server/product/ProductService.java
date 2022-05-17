package com.spurvago.server.product;

import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.components.Utils;
import com.spurvago.database.Product;
import com.spurvago.server.product.models.ProductVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public record ProductService(ProductRepository productRepository, ProductMapper productMapper) {
    public ProductVM find(long id) {
        var entity = productRepository.findById(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        return productMapper.mapToVM(entity);
    }

    public ListPaginated<ProductVM> getList(Pager pager, String search) {
        Pageable pageable = pager.makePageable();
        Page<Product> entities;
        if (Utils.isNullOrBlank(search)) {
            entities = productRepository.findAll(pageable);
        } else {
            List<String> words = List.of(search.split("\\s"));
            Specification<Product> specification = ProductRepository.search(words);
            entities = productRepository.findAll(specification, pageable);
        }

        Page<ProductVM> entitiesDTO = entities.map(ProductVM::new);

        return new ListPaginated<>(entitiesDTO, pager);
    }

    public ProductVM create(Product newEntity) {
        productRepository.save(newEntity);
        return productMapper.mapToVM(newEntity);
    }

    public ProductVM update(long id, Product newEntity) {
        Product entity = productRepository.findById(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        productMapper.mapToEntity(entity, newEntity);
        productRepository.save(entity);
        return new ProductVM(entity);
    }

    public void delete(long id) {
        var entity = productRepository.findById(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        productRepository.delete(entity);
    }
}
