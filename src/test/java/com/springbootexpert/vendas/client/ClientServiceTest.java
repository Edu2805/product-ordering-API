package com.springbootexpert.vendas.client;

import com.springbootexpert.geradorcpfcnpj.GeraCpfCnpj;
import com.springbootexpert.vendas.purchase.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static java.util.UUID.fromString;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.data.domain.ExampleMatcher.matching;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@SpringBootTest
class ClientServiceTest {

    @InjectMocks
    private ClientService clientServiceMock;

    @InjectMocks
    private GeraCpfCnpj geraCpfCnpjMock;

    @Mock
    private ClientRepository clientRepositoryMock;

    private Client client;
    private Optional<Client> optionalClient;
    private Example<Client> clientExample;
    private List<Purchase> purchaseList;
    public static final UUID UUID = randomUUID();
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
    void whenFilterByIdThenReturnAnClientInstance() {
        when(clientRepositoryMock.findById(UUID)).thenReturn(optionalClient);

        Client response = clientServiceMock.getClientById(UUID);

        assertNotNull(response);
        assertEquals(Client.class, response.getClass());
        assertEquals(UUID, response.getId());
        assertEquals(NAME, response.getName());
    }

    @Test
    void whenFilterByIdThenReturnAnMessageErrorAndHttpStatus() {
        String uuid = "bcf9634c-ab40-47c7-8f5f-45e93735afbf";
        String errorMenssage = "Cliente não encontrado";
        HttpStatus statusMessage = NOT_FOUND;

        String message = assertThrows(ResponseStatusException.class, () -> {
            clientServiceMock.getClientById(fromString(uuid));
        }).getReason();

        HttpStatus status = assertThrows(ResponseStatusException.class, () -> {
            clientServiceMock.getClientById(fromString(uuid));
        }).getStatus();

        assertEquals(errorMenssage, message);
        assertEquals(statusMessage, status);
    }

    @Test
    void whenSaveThenReturnSucess() {
        when(clientRepositoryMock.save(any())).thenReturn(client);

        Client response = clientServiceMock.save(client);

        assertNotNull(response);
        assertEquals(Client.class, response.getClass());
        assertEquals(UUID, response.getId());
        assertEquals(NAME, response.getName());
    }

    @Test
    void whenSaveThenReturnDataIntegrityViolationException() {
        String uuid = "bcf9634c-ab40-47c7-8f5f-45e93735afbf";
        when(clientRepositoryMock.findById(UUID)).thenReturn(optionalClient);

        try{
            optionalClient.get().setId(fromString(uuid));
            Client response = clientServiceMock.save(client);
        } catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
        }
    }// fazer validação no método save para não permitir dois ids e dois cpfs

    @Test
    void whenFindByIdWithSucessAndDelete() {
        when(clientRepositoryMock.findById(any())).thenReturn(optionalClient);
        doNothing().when(clientRepositoryMock).delete(any());

        clientServiceMock.delete(UUID);

        verify(clientRepositoryMock, times(1)).delete(any());
    }

    @Test
    void whenFindByIdAndClientDoNotExistsForDelete() {
        String uuid = "bcf9634c-ab40-47c7-8f5f-45e93735afbf";
        String errorMenssage = "Cliente não encontrado";
        HttpStatus statusMessage = NOT_FOUND;

        String message = assertThrows(ResponseStatusException.class, () -> {
            clientServiceMock.delete(fromString(uuid));
        }).getReason();

        HttpStatus status = assertThrows(ResponseStatusException.class, () -> {
            clientServiceMock.getClientById(fromString(uuid));
        }).getStatus();

        assertEquals(errorMenssage, message);
        assertEquals(statusMessage, status);
    }

    @Test
    void whenFindByIdWithSucessAndUpdate() {
        when(clientRepositoryMock.findById(UUID)).thenReturn(optionalClient);

        optionalClient.get().setId(UUID);
        var id = optionalClient.get().getId();
        clientServiceMock.update(optionalClient.get(), id);

        assertEquals(optionalClient.get().getId(), client.getId());
    }

    @Test
    void whenFindByIdAndClientDoNotExistsForUpdate() {
        String uuid = "bcf9634c-ab40-47c7-8f5f-45e93735afbf";
        String errorMenssage = "Cliente não encontrado";
        HttpStatus statusMessage = NOT_FOUND;

        String message = assertThrows(ResponseStatusException.class, () -> {
            clientServiceMock.update(client, fromString(uuid));
        }).getReason();

        HttpStatus status = assertThrows(ResponseStatusException.class, () -> {
            clientServiceMock.getClientById(fromString(uuid));
        }).getStatus();

        assertEquals(errorMenssage, message);
        assertEquals(statusMessage, status);
    }

    @Test
    void shouldfilterClientsWithSameHashExample() {
        Example<Client> example = Example.of(client, matching().withIgnoreCase("name"));
        Example<Client> sameExample = Example.of(client, matching().withIgnoreCase("name"));
        when(clientRepositoryMock.findAll()).thenReturn(List.of(example.getProbe()));

        clientServiceMock.filterClients(example.getProbe());

        assertEquals(example.hashCode(), sameExample.hashCode());
    }

    @Test
    void shouldNotfilterClientsWithSameHashExample() {
        Example<Client> example = Example.of(client, matching().withIgnoreCase("name"));
        when(clientRepositoryMock.findAll()).thenReturn(List.of(example.getProbe()));

        Example<Client> different = Example.of(client, matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains()));

        clientServiceMock.filterClients(example.getProbe());

        assertNotEquals(example.hashCode(), different.hashCode());
    }

    @Test
    void shouldfilterClientsWithSameExample() {
        Example<Client> example = Example.of(client, matching().withIgnoreCase("name"));
        Example<Client> sameExample = Example.of(client, matching().withIgnoreCase("name"));
        when(clientRepositoryMock.findAll()).thenReturn(List.of(example.getProbe()));

        clientServiceMock.filterClients(example.getProbe());

        assertEquals(example, sameExample);
//        assertNotEquals(example, different);
    }

    @Test
    void shouldNotfilterClientsWithSameExample() {
        Example<Client> example = Example.of(client, matching().withIgnoreCase("name"));
        when(clientRepositoryMock.findAll()).thenReturn(List.of(example.getProbe()));

        Example<Client> different = Example.of(client, matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains()));

        clientServiceMock.filterClients(example.getProbe());

        assertNotEquals(example, different);
    }
}