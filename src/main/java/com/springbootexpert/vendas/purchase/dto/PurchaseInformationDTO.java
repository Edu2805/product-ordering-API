package com.springbootexpert.vendas.purchase.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PurchaseInformationDTO {

    private UUID code;
    private String cpf;
    private String clientName;
    private BigDecimal total;
    private String purchaseDate;
    private String purchaseStatus;
    private List<ItemPurchaseInformationDTO> items;
}
