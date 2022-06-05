<p align="center"><img height="80px" src="https://github.com/Edu2805/product-ordering-API/blob/main/img/logoSpring.png" width="80px"/></p>

# Spring Boot project - Order Rest API

Advanced Spring boot project using some functional programming concepts.
This project also includes some concepts of BoundedContext

### New features
* Customer order inquiry
* Order status change
* Custom error messages
* Inclusion of a message translation key for Portuguese, English and Spanish.

Message in Portuguese
<p align="center"><img height="80px" src="https://github.com/Edu2805/product-ordering-API/blob/main/img/portugues.png" width="380px"/></p>

Message in English
<p align="center"><img height="80px" src="https://github.com/Edu2805/product-ordering-API/blob/main/img/ingles.png" width="380px"/></p>

Message in Spanish
<p align="center"><img height="80px" src="https://github.com/Edu2805/product-ordering-API/blob/main/img/espanhol.png" width="380px"/></p>

### How to use the application
* You must connect to a database, for this you must perform the necessary settings in the application.properties file
* For requests, you can use tools like Postman, Insomnia or another application
* After downloading the repository, reload the pom.xml, run the compile and start the application.
* Below are the payloads for testing

### Payloads

```bash
http://localhost:8080/client
To register a customer - Post method

{
    "name": "Client Name",
    "cpf": "00000000000"
}
########################################################

http://localhost:8080/client/{uuidClient}
To change data for a particular customer - Put method

{
    "name": "Client Name",
    "cpf": "00000000000"
}
########################################################

http://localhost:8080/product
To register a product in the database - Post method

{
    "description": "Product description",
    "price": 00.00
}
########################################################

http://localhost:8080/product/{idProduct}
To change a product in the database - Put method

{
    "description": "Product description",
    "price": 00.00
}
########################################################

http://localhost:8080/purchase
To register an order - Post method

{
    "client": "insert client UUID",
    "total": 00.00,
    "itemPurchases": [
        {
            "product": "insert product UUID",
            "quantity": 1
        }
    ]
}
########################################################

http://localhost:8080/purchase/{uuidPurchase}
To change the status of an order to DONE or CANCELED - Path method

{
    "newStatus": "CANCELED"
}

OR

{
    "newStatus": "DONE"
}
```

### Other endpoints

<h4 align="center">End-Points para testes</h4>
<p><img src="https://github.com/Edu2805/product-ordering-API/blob/main/img/Get.png" title="Readme"/>Lista completa de empresas cadastradas</p>
<p>🔗 http://localhost:8080/empresa</p>
<hr>
<p><img src="https://github.com/Edu2805/product-ordering-API/blob/main/img/Get.png" title="Readme"/>Lista de fazendas de uma empresa</p>
<p>🔗 http://localhost:8080/fazenda/listarfazendasempresa/{id}</p>
<hr>
<p><img src="https://github.com/Edu2805/product-ordering-API/blob/main/img/Get.png" title="Readme"/>Quantidade de fazendas de uma empresa</p>
<p>🔗 http://localhost:8080/fazenda/quantidadefazendas/{id}</p>
<hr>
<p><img src="https://github.com/Edu2805/product-ordering-API/blob/main/img/Get.png" title="Readme"/>Lista de fazendas de uma empresa (ID, nome, data previsão colheita, tempo médio de colheita)</p>
<p>🔗 http://localhost:8080/fazenda/listafazendasdetalhadas/{id}</p>
<hr>
<p><img src="https://github.com/Edu2805/product-ordering-API/blob/main/img/Put.png" title="Readme"/>Registro de colheita (Entrada de grãos no estoque)</p>
<p>🔗 http://localhost:8080/fazenda/registraentradacolheita/{id}</p>
<hr>
<p><img src="https://github.com/Edu2805/product-ordering-API/blob/main/img/Put.png" title="Readme"/>Registro de colheita (Saída de grãos no estoque)</p>
<p>🔗 http://localhost:8080/fazenda/registrasaidacolheita/{id}</p>
<hr>
<p><img src="https://github.com/Edu2805/product-ordering-API/blob/main/img/Get.png" title="Readme"/>Lista de grãos de uma empresa</p>
<p>🔗 http://localhost:8080/grao/listargraosempresa/{id}</p>
<hr>
<p><img src="https://github.com/Edu2805/product-ordering-API/blob/main/img/Get.png" title="Readme"/>Lista de grãos (nome do grão, quantidade de estoque em ordem ascendente)</p>
<p>🔗 http://localhost:8080/fazenda/estoquegraoscrescente/{id}</p>
<hr>
<p><img src="https://github.com/Edu2805/product-ordering-API/blob/main/img/Get.png" title="Readme"/>Lista de funcionários de uma empresa</p>
<p>🔗 http://localhost:8080/funcionario/listarfuncionariosempresa/{id}</p>
<hr>
<p><img src="https://github.com/Edu2805/product-ordering-API/blob/main/img/Get.png" title="Readme"/>Quantidade de funcionários de uma empresa</p>
<p>🔗 http://localhost:8080/funcionario/quantidadefuncionarios/{id}</p>
<hr>