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

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.data.domain.ExampleMatcher.matching;

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
    public static final Integer INDEX = 0;

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
    void whenGetClientByIdThenReturnSuccess() {
        when(clientService.getClientById(any())).thenReturn(client);

        var clientById = clientController.getClientById(UUID);

        assertNotNull(clientById);
        assertNotNull(clientById.getClass());
        assertEquals(Client.class, clientById.getClass());
        assertEquals(UUID, clientById.getId());
        assertEquals(NAME, clientById.getName());
    }

    @Test
    void whenSaveAndThenReturnWithSuccess() {
        Client clientSave = new Client();
        clientSave.setId(UUID);
        clientSave.setName(NAME);
        clientSave.setCpf(client.getCpf());
        clientSave.setPurchases(purchaseList);
        when(clientService.save(clientSave)).thenReturn(client);

        var save = clientController.save(clientSave);

        assertEquals(save, client);
        assertEquals(save.getId(), clientSave.getId());
        assertEquals(save.getName(), clientSave.getName());
        assertEquals(save.getCpf(), clientSave.getCpf());
        assertEquals(save.getPurchases(), clientSave.getPurchases());
        assertEquals(save.getClass(), clientSave.getClass());
        assertNotNull(save);
    }

    @Test
    void whenFindByIdAndDeleteWithSuccess() {
        doNothing().when(clientService).delete(any());

        clientController.delete(UUID);

        verify(clientService, times(1)).delete(any());
        assertNotNull(UUID);
    }

    @Test
    void whenFindByIdAndUpdateWithSuccess() {
        doNothing().when(clientService).update(any(), any());

        clientController.update(client, UUID);

        verify(clientService, times(1)).update(any(), any());
        assertNotNull(client);
    }

    @Test
    void whenFilterClientsForCharacterWithSuccess() {
        Example<Client> example = Example.of(client, matching().withIgnoreCase("o"));
        when(clientService.filterClients(example.getProbe())).thenReturn(List.of(client));

        var clients = clientController.filterClients(example.getProbe());

        assertEquals(List.of(client), clients);
        assertEquals(UUID, clients.get(INDEX).getId());
        assertEquals(NAME, clients.get(INDEX).getName());
        assertNotNull(List.of(client));
    }

    @Test
    void whenFilterClientsForCharacterAndReturnEmptyList() {

        Example<Client> example = Example.of(client, matching().withIgnoreCase("x"));
        when(clientService.filterClients(example.getProbe())).thenReturn(List.of());

        var clients = clientController.filterClients(example.getProbe());

        assertEquals(List.of(), clients);
        assertNotNull(List.of(client));
    }
}