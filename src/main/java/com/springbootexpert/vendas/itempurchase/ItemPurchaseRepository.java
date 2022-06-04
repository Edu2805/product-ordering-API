package com.springbootexpert.vendas.itempurchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemPurchaseRepository extends JpaRepository<ItemPurchase, UUID> {
}
