package com.springbootexpert.vendas.controller;

import com.springbootexpert.vendas.product.Product;
import com.springbootexpert.vendas.product.ProductService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor

@RestController
@RequestMapping("product")
@Api("Api Produtos")
public class ProductController {

    private ProductService productService;

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Cadastrar um produto")
    @ApiResponses({
            @ApiResponse(code= 201, message ="Produto cadastrado com sucesso"),
            @ApiResponse(code=400, message = "Erro de validação")
    })
    public Product save (@RequestBody @Valid Product product){//
        return productService.save(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Altera um produto")
    @ApiResponses({
            @ApiResponse(code= 204, message = "Produto alterado com sucesso"),
            @ApiResponse(code=404, message = "Produto não encontrado para o id informado")
    })
    public void update (@RequestBody @Valid Product product, @PathVariable @ApiParam("Id do produto") UUID id){//
        productService.update(product, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Deleta um produto")
    @ApiResponses({
            @ApiResponse(code= 204, message = "Produto deletado com sucesso"),
            @ApiResponse(code=404, message = "Produto não encontrado para o id informado")
    })
    public void delete (@PathVariable @ApiParam("Id do produto") UUID id){
        productService.delete((id));
    }

    @GetMapping("/{id}")
    @ApiOperation("Encontra um produto")
    @ApiResponses({
            @ApiResponse(code= 200, message = "Produto encontrado"),
            @ApiResponse(code=404, message = "Produto não encontrado para o id informado")
    })
    public Product getById(@PathVariable UUID id){
        return productService.getById(id);
    }

    @GetMapping
    @ApiOperation("Lista todos os produto")
    @ApiResponses({
            @ApiResponse(code= 200, message = "Produto listados"),
    })
    public List<Product> findAll(Product productFilter){
        
        return productService.findAll(productFilter);
    }
}
