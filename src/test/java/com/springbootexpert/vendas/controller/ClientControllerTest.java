package com.springbootexpert.vendas.controller;

import com.springbootexpert.geradorcpfcnpj.GeraCpfCnpj;
import com.springbootexpert.vendas.client.Client;
import com.springbootexpert.vendas.client.ClientService;
import com.springbootexpert.vendas.purchase.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientControllerTest {

    @InjectMocks
    ClientController clientController;

    @InjectMocks
    private GeraCpfCnpj geraCpfCnpjMock;

    @Mock
    ClientService clientService;

    private Client client;
    private Optional<Client> optionalClient;
    private Example<Client> clientExample;
    private List<Purchase> purchaseList;
    public static final java.util.UUID UUID = randomUUID();
    public static final String NAME = "Joel";

    private void startUser(){
        purchaseList = List.of();
        client = new Client(UUID, NAME, geraCpfCnpjMock.cpf(false), purchaseList);
        optionalClient = Optional.of(new Client(UUID, NAME, geraCpfCnpjMock.cpf(false), purchaseList));
        clientExample = Example.of(client);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void getClientById() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void filterClients() {
    }
}