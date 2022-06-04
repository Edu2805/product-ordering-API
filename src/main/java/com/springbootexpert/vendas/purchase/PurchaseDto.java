package com.springbootexpert.vendas.purchase;
import com.springbootexpert.vendas.itempurchase.ItemPurchaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PurchaseDto {

    private UUID client;
    private BigDecimal total;
    private List<ItemPurchaseDto> itemPurchases;
}
