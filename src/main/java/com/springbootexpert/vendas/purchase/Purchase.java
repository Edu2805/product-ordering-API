package com.springbootexpert.vendas.purchase;

import com.springbootexpert.vendas.client.Client;
import com.springbootexpert.vendas.itempurchase.ItemPurchase;
import com.springbootexpert.vendas.purchase.enums.PurchaseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "tb_purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "purchase_date")
    private LocalDate datePurchase;
    @Column(name = "prurchase_total", precision = 20, scale = 2)
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido")
    private PurchaseStatus purchaseStatus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @OneToMany(mappedBy = "purchase")
    private List<ItemPurchase> itemPurchases;
}
