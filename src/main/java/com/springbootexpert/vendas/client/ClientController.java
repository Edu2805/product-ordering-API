package com.springbootexpert.vendas.client;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor

@RestController
@RequestMapping("client")
public class ClientController {

    private ClientService clientService;

    @GetMapping("/{id}")
    public Client getClientById ( @PathVariable UUID id){
        return clientService.getClientById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Client save (@RequestBody @Valid Client client){
        return clientService.save(client);//
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable UUID id){
        clientService.delete(id);//
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@RequestBody @Valid Client client, @PathVariable UUID id){
        clientService.update(client, id);//
    }

    @GetMapping("/filter")
    public List<Client> filterClients (Client filter){
        return clientService.filterClients(filter);//
    }
}
