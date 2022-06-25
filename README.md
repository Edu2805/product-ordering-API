<p align="center"><img height="80px" src="https://github.com/Edu2805/product-ordering-API/blob/main/img/logoSpring.png" width="80px"/></p>

<h1 align="center">🛒  Spring Boot project - Order Rest API 🛒</h1>
<h2 align="center"> 💻 Advanced Spring boot project using some functional programming concepts. This project also includes some concepts of BoundedContext 💻</h2>

### New features
* Customer order inquiry
* Order status change
* Custom error messages
* Inclusion of a message translation key for Portuguese, English and Spanish.
* Authentication application using JWT
* Application of unit tests with Mockito JUnit5 (in process)
* Swagger documentation APIs

Message in Portuguese
<p align="center"><img height="80px" src="https://github.com/Edu2805/product-ordering-API/blob/main/img/portugues.png" width="380px"/></p>

Message in English
<p align="center"><img height="80px" src="https://github.com/Edu2805/product-ordering-API/blob/main/img/ingles.png" width="380px"/></p>

Message in Spanish
<p align="center"><img height="80px" src="https://github.com/Edu2805/product-ordering-API/blob/main/img/espanhol.png" width="380px"/></p>

Swagger documentation
<p align="center"><img src="https://github.com/Edu2805/product-ordering-API/blob/main/img/swagger.png"/></p>

### How to use the application
* You must connect to a database, for this you must perform the necessary settings in the application.properties file
* For requests, you can use tools like Postman, Insomnia or access http://localhost:8080/swagger-ui.html for use API by Swagger
* After downloading the repository, reload the pom.xml, run the compile and start the application.
* Below are the payloads for testing

### Payloads

```bash
http://localhost:8080/user
To register a user - Post method

{
    "login": "userName",
    "password": "password"
}
########################################################

http://localhost:8080/user/auth
To generate the token after user registration - Post method

{
    "login": "userNameAlreadyRegisteredInTheDatabase",
    "password": "passwordAlreadyRegisteredInTheDatabase"
}

########################################################

http://localhost:8080/client
To register a client - Post method

{
    "name": "Client Name",
    "cpf": "00000000000"
}
########################################################

http://localhost:8080/client/{uuidClient}
To change data for a particular client - Put method

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

<h4 align="center">Endpoints for tests</h4>
<p><img src="https://github.com/Edu2805/product-ordering-API/blob/main/img/Get.png" title="Readme"/>Consult a client</p>
<p>🔗 http://localhost:8080/client/{uuidClient}</p>
<hr>
<p><img src="https://github.com/Edu2805/product-ordering-API/blob/main/img/Del.png" title="Readme"/>Delete a client</p>
<p>🔗 http://localhost:8080/client/{uuidClient}</p>
<hr>
<p><img src="https://github.com/Edu2805/product-ordering-API/blob/main/img/Get.png" title="Readme"/>Consult a product</p>
<p>🔗 http://localhost:8080/product/{uuidProduct}</p>
<hr>
<p><img src="https://github.com/Edu2805/product-ordering-API/blob/main/img/Del.png" title="Readme"/>Delete a product</p>
<p>🔗 http://localhost:8080/product/{uuidProduct}</p>
<hr>
<p><img src="https://github.com/Edu2805/product-ordering-API/blob/main/img/Get.png" title="Readme"/>Consult an purchase</p>
<p>🔗 http://localhost:8080/purchase/{uuidPurchase}</p>
<hr>
<p>NOTE: It is possible to use the params feature to perform queries/filters by CLIENT name and PRODUCT name by typing any letter, for that, use the endpoints:  🔗 http://localhost:8080/client and 🔗 http://localhost:8080/product</p>

<h4 align="center"> 🚧 Project under construction 🚧</h4>
