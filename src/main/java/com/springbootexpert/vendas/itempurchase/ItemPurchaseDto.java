package com.springbootexpert.vendas.itempurchase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemPurchaseDto {

    private UUID product;
    private Integer quantity;
}
