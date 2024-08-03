package org.example.deliveryservice.service;

import org.example.deliveryservice.model.entity.CartEntity;
import org.example.deliveryservice.repository.CartRepository;
import org.example.deliveryservice.repository.ProductRepository;
import org.example.deliveryservice.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private ProductRepository mockProductRepository;

    @Mock
    private CartRepository mockCartRepository;

    private CartService serviceToTest;

    @BeforeEach
    void setUp() {
        serviceToTest = new CartService(mockUserRepository, mockProductRepository, mockCartRepository);
    }

    @Test
    void testSaveInvoked() {

        serviceToTest.getNewCart();

        verify(mockCartRepository).saveAndFlush(any());

    }

    @Test
    void testReturnedCart() {

        CartEntity newCart = serviceToTest.getNewCart();

        Assertions.assertEquals(0, newCart.getProducts().size());
        Assertions.assertEquals(BigDecimal.ZERO, newCart.getProductsSum());
    }

}
