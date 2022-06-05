package com.springbootexpert.vendas.product;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor

@Service
class ProductService {

    ProductRepository productRepository;

    Product save (Product product){
        return productRepository.save(product);
    }

    void update (Product product, UUID id){
        productRepository
                .findById(id)
                .map( p -> {
                    product.setId(p.getId());
                    productRepository.save(product);
                    return product;
                }).orElseThrow( () ->
                        new ResponseStatusException(NOT_FOUND, "Produto não encontrado"));
    }

    void delete (UUID id){
        productRepository
                .findById(id)
                .map( p -> {
                    productRepository.delete(p);
                    return Void.TYPE;
                }).orElseThrow( () ->
                        new ResponseStatusException(NOT_FOUND, "Produto não encontrado"));
    }

    Product getById(UUID id){
        return productRepository
                .findById(id)
                .orElseThrow( () ->
                        new ResponseStatusException(NOT_FOUND, "Produto não encontrado"));
    }

    List<Product> findAll(Product productFilter){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(productFilter, matcher);
        return productRepository.findAll(example);
    }
}
