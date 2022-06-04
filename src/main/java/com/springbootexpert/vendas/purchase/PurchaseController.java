package com.springbootexpert.vendas.purchase;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor

@RestController
@RequestMapping("purchase")
public class PurchaseController {

    PurchaseServiceImpl purchaseService;

    @PostMapping
    @ResponseStatus(CREATED)
    public UUID save (@RequestBody PurchaseDto purchaseDto){
        Purchase purchase = purchaseService.save(purchaseDto);
        return purchase.getId();
    }
}
