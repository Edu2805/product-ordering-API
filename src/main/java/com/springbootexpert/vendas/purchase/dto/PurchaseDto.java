package com.springbootexpert.vendas.purchase.dto;
import com.springbootexpert.vendas.itempurchase.ItemPurchaseDto;
import com.springbootexpert.vendas.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PurchaseDto {

    @NotNull(message = "{client.code-field-required}")
    private UUID client;
    @NotNull(message = "{total.order-field-required}")
    private BigDecimal total;
    @NotEmptyList(message = "{order.items-field-required}")
    private List<ItemPurchaseDto> itemPurchases;
}
