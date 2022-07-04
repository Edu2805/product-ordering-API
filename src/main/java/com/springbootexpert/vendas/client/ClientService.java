package com.springbootexpert.vendas.client;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
@Service
public
class ClientService {

    ClientRepository clientRepository;

    public Client getClientById(UUID id){
        return clientRepository
                .findById(id)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(NOT_FOUND,
                            "Cliente não encontrado");
                });
    }

    public Client save(Client client){
        clientRepository.findAll().forEach(find -> {
            if(Objects.equals(find.getId(), client.getId())){
                throw new ResponseStatusException(CONFLICT,
                        "ID já existente na base de dados");
            }

            if(Objects.equals(find.getCpf(), client.getCpf())){
                throw new ResponseStatusException(CONFLICT,
                        "CPF já existente na base de dados");
            }
        });
        return clientRepository.save(client);
    }

    public void delete(UUID id){
        clientRepository.findById(id)
                .map(client -> {
                    clientRepository.delete(client);
                    return client;
                })
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Cliente não encontrado"));
    }

    public void update(Client client,UUID id){
        clientRepository
                .findById(id)
                .map(existingClient -> {
                    client.setId(existingClient.getId());
                    clientRepository.save(client);
                    return existingClient;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Cliente não encontrado"));
    }

    public List<Client> filterClients (Client filter){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, matcher);
        return clientRepository.findAll(example);
    }
}
