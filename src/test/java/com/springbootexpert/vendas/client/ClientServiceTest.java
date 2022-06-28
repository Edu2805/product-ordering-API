package com.springbootexpert.vendas.client;

import com.springbootexpert.geradorcpfcnpj.GeraCpfCnpj;
import com.springbootexpert.vendas.purchase.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.UUID.fromString;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ClientServiceTest {

    @InjectMocks
    private ClientService clientServiceMock;

    @InjectMocks
    private GeraCpfCnpj geraCpfCnpjMock;

    @Mock
    private ClientRepository clientRepositoryMock;

    @Mock
    private ExampleMatcher exampleMatcherMock;

    private Client client;
    private Optional<Client> optionalClient;
    private List<Purchase> purchaseList;
    public static final UUID UUID = randomUUID();
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
        HttpStatus statusMessage = HttpStatus.NOT_FOUND;

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
        when(clientRepositoryMock.findById(UUID)).thenReturn(optionalClient);

        optionalClient.get().setId(UUID);
        var id = optionalClient.get().getId();
        clientServiceMock.delete(id);

        assertEquals(optionalClient.get().getId(), client.getId());
    }

    @Test
    void whenFindByIdAndClientDoNotExistsForDelete() {
        String uuid = "bcf9634c-ab40-47c7-8f5f-45e93735afbf";
        String errorMenssage = "Cliente não encontrado";
        HttpStatus statusMessage = HttpStatus.NOT_FOUND;

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
        HttpStatus statusMessage = HttpStatus.NOT_FOUND;

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
    void filterClients() {
    }
}