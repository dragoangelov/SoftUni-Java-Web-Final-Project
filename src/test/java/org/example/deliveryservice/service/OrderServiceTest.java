package org.example.deliveryservice.service;

import org.example.deliveryservice.exception.NotFoundException;
import org.example.deliveryservice.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private UserService userService;
    @Mock
    private OrderRepository mockOrderRepository;
    @Mock
    private ModelMapper modelMapper;
    private OrderService serviceToTest;


    @BeforeEach
    void setUp() {
        serviceToTest = new OrderService(userService, mockOrderRepository, modelMapper);
    }

    @Test
    void findOrderByIdNotSuccessful() {
        Assertions.assertThrows(NotFoundException.class,
                () -> this.serviceToTest.getOrderById(-1L));
    }

    @Test
    void finishOrderByIdNotSuccessful() {
        Assertions.assertThrows(NotFoundException.class,
                () -> this.serviceToTest.finishOrder(-1L));
    }

}
