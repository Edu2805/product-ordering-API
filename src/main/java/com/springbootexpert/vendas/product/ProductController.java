package com.springbootexpert.vendas.product;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor

@RestController
@RequestMapping("product")
public class ProductController {

    private ProductService productService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Product save (@RequestBody @Valid Product product){
        return productService.save(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update (@RequestBody @Valid Product product, @PathVariable UUID id){
        productService.update(product, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable UUID id){
        productService.delete((id));
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable UUID id){
        return productService.getById(id);
    }

    @GetMapping
    public List<Product> findAll(Product productFilter){
        return productService.findAll(productFilter);
    }
}
