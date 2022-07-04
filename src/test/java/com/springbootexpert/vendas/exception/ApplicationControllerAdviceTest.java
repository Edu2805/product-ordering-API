package com.springbootexpert.vendas.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationControllerAdviceTest {

    @InjectMocks
    private ApplicationControllerAdvice applicationControllerAdviceMock;

    @Mock
    ApiErros apiErrosMock;

    public static final String PEDIDO_NAO_ENCONTRADO = "Pedido não encontrado";
    public static final String SENHA_INVALIDA = "Senha inválida";
    public static final String MENSAGEM_TESTE = "Teste";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handlerExceptionRuleBusiness() {
        ApiErros response = applicationControllerAdviceMock
                .handlerExceptionRuleBusiness(new Exception("Teste"));

        assertNotNull(response);
        assertNotNull(response.getClass());
        assertEquals(ApiErros.class, response.getClass());
        assertEquals(MENSAGEM_TESTE, response.getErrors().get(0));
    }

    @Test
    void handlerPurchaseNotFoundExceptiom() {
        ApiErros response = applicationControllerAdviceMock
                .handlerPurchaseNotFoundExceptiom(
                        new PurchaseNotFoundException());

        assertNotNull(response);
        assertNotNull(response.getClass());
        assertEquals(ApiErros.class, response.getClass());
        assertEquals(PEDIDO_NAO_ENCONTRADO, response.getErrors().get(0));
    }

    @Test
    void handlerMethodNotValidException() {

    }
}