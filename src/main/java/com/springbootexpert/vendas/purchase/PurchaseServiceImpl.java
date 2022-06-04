package com.springbootexpert.vendas.purchase;

import com.springbootexpert.vendas.client.Client;
import com.springbootexpert.vendas.client.ClientRepository;
import com.springbootexpert.vendas.exception.Exception;
import com.springbootexpert.vendas.itempurchase.ItemPurchase;
import com.springbootexpert.vendas.itempurchase.ItemPurchaseDto;
import com.springbootexpert.vendas.itempurchase.ItemPurchaseRepository;
import com.springbootexpert.vendas.product.Product;
import com.springbootexpert.vendas.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
class PurchaseServiceImpl implements PurchaseService{

    final PurchaseRepository purchaseRepository;
    final ClientRepository clientRepository;
    final ProductRepository productRepository;
    final ItemPurchaseRepository itemPurchaseRepository;

    @Override
    @Transactional
    public Purchase save(PurchaseDto purchaseDto) {
        UUID clientId = purchaseDto.getClient();
        Client client = clientRepository
                .findById(clientId)
                .orElseThrow(() -> new Exception("Código de cliente inválido"));

        Purchase purchase = new Purchase();
        purchase.setTotal(purchaseDto.getTotal());
        purchase.setDatePurchase(LocalDate.now());
        purchase.setClient(client);

        var purchaseList = convertItems(purchase, purchaseDto.getItemPurchases());
        purchaseRepository.save(purchase);
        itemPurchaseRepository.saveAll(purchaseList);
        purchase.setItemPurchases(purchaseList);
        return purchase;
    }

    private List<ItemPurchase> convertItems(Purchase purchase, List<ItemPurchaseDto> itemPurchaseDtos){
        if(itemPurchaseDtos.isEmpty()){
            throw new Exception("Não é possível realizar pedidos sem itens");
        }

        return itemPurchaseDtos
                .stream()
                .map(dto -> {
                    UUID productId = dto.getProduct();
                    Product product = productRepository
                            .findById(productId)
                            .orElseThrow(() -> new Exception("Código de produto inválido " + productId));

                    ItemPurchase itemPurchase = new ItemPurchase();
                    itemPurchase.setQuantity(dto.getQuantity());
                    itemPurchase.setPurchase(purchase);
                    itemPurchase.setProduct(product);
                    return itemPurchase;

                }).collect(Collectors.toList());
    }
}
