package com.springbootexpert.vendas.purchase;

import com.springbootexpert.vendas.purchase.dto.PurchaseDto;
import com.springbootexpert.vendas.purchase.enums.PurchaseStatus;

import java.util.Optional;
import java.util.UUID;

interface PurchaseService {
    Purchase save(PurchaseDto purchaseDto);
    Optional<Purchase> getCompletePurchase(UUID id);
    void updateStatus(UUID id, PurchaseStatus purchaseStatus);
}
