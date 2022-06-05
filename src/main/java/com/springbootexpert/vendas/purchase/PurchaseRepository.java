package com.springbootexpert.vendas.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface PurchaseRepository extends JpaRepository<Purchase, UUID> {

    @Query("select p from Purchase p left join fetch p.itemPurchases where p.id = :id ")
    Optional<Purchase> findByIdFetchIAndItemPurchases(@Param("id") UUID id);
}
