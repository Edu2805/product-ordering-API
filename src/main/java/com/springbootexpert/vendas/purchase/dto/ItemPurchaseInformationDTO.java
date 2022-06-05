package com.springbootexpert.vendas.purchase.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ItemPurchaseInformationDTO {

    private String productDescription;
    private BigDecimal unitPrice;
    private Integer quantity;
}
