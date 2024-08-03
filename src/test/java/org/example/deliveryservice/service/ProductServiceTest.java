package org.example.deliveryservice.service;

import org.example.deliveryservice.exception.NotFoundException;
import org.example.deliveryservice.exception.WrongCategoryException;
import org.example.deliveryservice.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    private ProductService serviceToTest;

    @Spy
    private ModelMapper mockModelMapper;

    @Mock
    private ProductRepository mockProductRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new ProductService(mockProductRepository, mockModelMapper);
    }


    @Test
    void wrongCategoryException() {
        Assertions.assertThrows(WrongCategoryException.class,
                () -> this.serviceToTest.findCategory("test"));
    }

    @Test
    void getProductByIdException() {
        Assertions.assertThrows(NotFoundException.class,
                () -> this.serviceToTest.getProductById(-1L));
    }

}
