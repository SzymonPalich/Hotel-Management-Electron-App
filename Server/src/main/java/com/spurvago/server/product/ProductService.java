package com.spurvago.server.product;

import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.components.Utils;
import com.spurvago.database.Product;
import com.spurvago.server.product.models.ProductFM;
import com.spurvago.server.product.models.ProductVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public record ProductService(ProductRepository productRepository,
                             ProductMapper productMapper,
                             ProductValidator productValidator) {
    public ProductVM find(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product entity;
        if (optionalProduct.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        entity = optionalProduct.get();

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

        List<ProductVM> entitiesDTO = productMapper.mapToList(entities.getContent());
        return new ListPaginated<>(entitiesDTO, pager,
                entities.getTotalElements(), entities.getTotalPages());
    }

    public ProductVM create(ProductFM newEntity) {
        if (!productValidator().validate(newEntity)) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        }

        Product entity = productMapper.mapToEntity(newEntity);
        productRepository.save(entity);
        return productMapper.mapToVM(entity);
    }

    public ProductVM update(Long id, ProductFM newEntity) {
        if (!productValidator().validate(newEntity)) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        }

        Optional<Product> optionalProduct = productRepository.findById(id);
        Product entity;
        if (optionalProduct.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        entity = optionalProduct.get();

        productMapper.mapToEntity(entity, newEntity);
        productRepository.save(entity);
        return productMapper.mapToVM(entity);
    }

    public void delete(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product entity;
        if (optionalProduct.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        entity = optionalProduct.get();

        productRepository.delete(entity);
    }
}
