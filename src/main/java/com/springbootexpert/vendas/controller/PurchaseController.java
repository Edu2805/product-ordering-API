package com.springbootexpert.vendas.controller;

import com.springbootexpert.vendas.itempurchase.ItemPurchase;
import com.springbootexpert.vendas.purchase.Purchase;
import com.springbootexpert.vendas.purchase.PurchaseServiceImpl;
import com.springbootexpert.vendas.purchase.dto.ItemPurchaseInformationDTO;
import com.springbootexpert.vendas.purchase.dto.PurchaseDto;
import com.springbootexpert.vendas.purchase.dto.PurchaseInformationDTO;
import com.springbootexpert.vendas.purchase.dto.UpdatePurchaseStatusDTO;
import com.springbootexpert.vendas.purchase.enums.PurchaseStatus;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor

@RestController
@RequestMapping("purchase")
@Api("Api Pedidos")
public class PurchaseController {

    PurchaseServiceImpl purchaseService;

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Cadastrar um pedido")
    @ApiResponses({
            @ApiResponse(code= 201, message ="Pedido cadastrado com sucesso"),
            @ApiResponse(code=400, message = "Erro de validação")
    })
    public UUID save (@RequestBody @Valid PurchaseDto purchaseDto){
        Purchase purchase = purchaseService.save(purchaseDto);
        return purchase.getId();
    }

    @GetMapping("/{id}")
    @ApiOperation("Encontra um pedido")
    @ApiResponses({
            @ApiResponse(code= 200, message = "Pedido encontrado"),
            @ApiResponse(code=404, message = "Pedido não encontrado para o id informado")
    })
    public PurchaseInformationDTO getById(@PathVariable @ApiParam("Id do pedido") UUID id){
        return purchaseService
                .getCompletePurchase(id)
                .map( p -> convert(p))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pedido não encontrado"));
    }

    private PurchaseInformationDTO convert (Purchase purchase){
        return PurchaseInformationDTO
                .builder()
                .code(purchase.getId())
                .purchaseDate(purchase.getDatePurchase().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(purchase.getClient().getCpf())
                .clientName(purchase.getClient().getName())
                .total(purchase.getTotal())
                .purchaseStatus(purchase.getPurchaseStatus().name())
                .items(convertInformation(purchase.getItemPurchases()))
                .build();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Altera um pedido")
    @ApiResponses({
            @ApiResponse(code= 204, message = "Pedido alterado com sucesso"),
            @ApiResponse(code=404, message = "Pedido não encontrado para o id informado")
    })
    public void updatePurchase(@RequestBody UpdatePurchaseStatusDTO updatePurchaseStatusDTO, @PathVariable @ApiParam("Id do pedido")UUID id){
        String newStatus = updatePurchaseStatusDTO.getNewStatus();
        purchaseService.updateStatus(id, PurchaseStatus.valueOf(newStatus));
    }

    private List<ItemPurchaseInformationDTO> convertInformation (List<ItemPurchase> itemPurchases){
        if(CollectionUtils.isEmpty(itemPurchases)){
            return Collections.emptyList();
        }

        return itemPurchases
                .stream()
                .map(item -> ItemPurchaseInformationDTO
                        .builder()
                        .productDescription(item.getProduct().getDescription())
                        .unitPrice(item.getProduct().getPrice())
                        .quantity(item.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }
}
