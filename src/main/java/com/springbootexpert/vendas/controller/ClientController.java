package com.springbootexpert.vendas.controller;

import com.springbootexpert.vendas.client.Client;
import com.springbootexpert.vendas.client.ClientService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor

@RestController
@RequestMapping("client")
@Api("Api Clientes")
public class ClientController {

    private ClientService clientService;

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de um determinado cliente")
    @ApiResponses({
            @ApiResponse(code= 200, message ="Cliente encontrado"),
            @ApiResponse(code=404, message = "Cliente não encontrado para o id informado")
    })
    public Client getClientById (@PathVariable @ApiParam("Id do cliente") UUID id){//
        return clientService.getClientById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Salva um novo cliente")
    @ApiResponses({
            @ApiResponse(code= 201, message = "Cliente salvo com sucesso"),
            @ApiResponse(code=400, message = "Erro de validação")
    })
    public Client save (@RequestBody @Valid Client client){//
        return clientService.save(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Deleta um cliente")
    @ApiResponses({
            @ApiResponse(code= 204, message = "Cliente deletado com sucesso"),
            @ApiResponse(code=404, message = "Cliente não encontrado para o id informado")
    })
    public void delete(@PathVariable @ApiParam("Id do cliente") UUID id){//
        clientService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Altera um cliente")
    @ApiResponses({
            @ApiResponse(code= 204, message = "Cliente alterado com sucesso"),
            @ApiResponse(code=404, message = "Cliente não encontrado para o id informado")
    })
    public void update(@RequestBody @Valid Client client, @PathVariable @ApiParam("Id do cliente") UUID id){//
        clientService.update(client, id);
    }

    @GetMapping("/filter")
    @ApiOperation("Filtra cliente")
    public List<Client> filterClients (Client filter){//
        return clientService.filterClients(filter);
    }
}
