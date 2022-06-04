package com.springbootexpert.vendas.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springbootexpert.vendas.purchase.Purchase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "tb_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 60)
    private String name;
    @Column(nullable = false, length = 11)
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Purchase> purchases;
}
