package com.springbootexpert.vendas.client;

import com.springbootexpert.geradorcpfcnpj.GeraCpfCnpj;
import com.springbootexpert.vendas.purchase.Purchase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @InjectMocks
    private GeraCpfCnpj geraCpfCnpjMock;

    @Mock
    private ClientRepository clientRepositoryMock;

    @Mock
    private ExampleMatcher exampleMatcherMock;

    private Client client;
    private Optional<Client> optionalClient;

    private List<Purchase> purchaseList;

    public static final UUID UUID = java.util.UUID.randomUUID();

    public static final String NAME = "Joel";

    private void startUser(){
        purchaseList = List.of();
        client = new Client(UUID, NAME, geraCpfCnpjMock.cpf(false), purchaseList);
        optionalClient = Optional.of(new Client(UUID, NAME, geraCpfCnpjMock.cpf(false), purchaseList));
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFilterByIdThenReturnAnClientInstance() {
        when(clientRepositoryMock.findById(UUID)).thenReturn(optionalClient);

        Client response = clientService.getClientById(UUID);

        assertNotNull(response);
        assertEquals(Client.class, response.getClass());
        assertEquals(UUID, response.getId());
        assertEquals(NAME, response.getName());
    }

    @Test
    void whenFilterByIdThenReturnAnMessageErrorAndHttpStatus() {
        String uuid = "bcf9634c-ab40-47c7-8f5f-45e93735afbf";
        String errorMenssage = "Cliente não encontrado";
        HttpStatus statusMessage = HttpStatus.NOT_FOUND;

        String message = assertThrows(ResponseStatusException.class, () -> {
            clientService.getClientById(java.util.UUID.fromString(uuid));
        }).getReason();

        HttpStatus status = assertThrows(ResponseStatusException.class, () -> {
            clientService.getClientById(java.util.UUID.fromString(uuid));
        }).getStatus();

        assertEquals(errorMenssage, message);
        assertEquals(statusMessage, status);
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