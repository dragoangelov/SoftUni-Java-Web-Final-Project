package org.example.deliveryservice.service;

import org.example.deliveryservice.exception.NotFoundException;
import org.example.deliveryservice.model.dto.OrderBindingDto;
import org.example.deliveryservice.model.dto.OrderViewDto;
import org.example.deliveryservice.model.dto.ProductViewDto;
import org.example.deliveryservice.model.entity.OrderEntity;
import org.example.deliveryservice.model.entity.ProductEntity;
import org.example.deliveryservice.model.entity.UserEntity;
import org.example.deliveryservice.model.enums.OrderStatusEnum;
import org.example.deliveryservice.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final UserService userService;

    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;

    public OrderService(UserService userService,
                        OrderRepository orderRepository,
                        ModelMapper modelMapper) {
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<ProductViewDto> getProductsInTheCart(String username) {

        final UserEntity user = this.userService.getUserByUsername(username);

        return user
                .getCart()
                .getProducts()
                .stream()
                .map(this::mapToProductViewDto)
                .collect(Collectors.toList());
    }

    private ProductViewDto mapToProductViewDto(ProductEntity productEntity) {
        return this.modelMapper.map(productEntity, ProductViewDto.class);
    }

    public BigDecimal getProductsPrice(String username) {

        final UserEntity user = this.userService.getUserByUsername(username);

        return user.getCart().getProductsSum();

    }

    @Transactional
    public void makeOrder(OrderBindingDto orderDto,
                          String username) {

        OrderEntity order = new OrderEntity();

        final UserEntity user = this.userService.getUserByUsername(username);

        buildOrder(orderDto, order, user);

        this.orderRepository.saveAndFlush(order);

        user.getCart().setProducts(new ArrayList<>()).setProductsSum(BigDecimal.ZERO);
        user.getCart().setCountProducts(0L);
    }

    private static void buildOrder(OrderBindingDto orderDto,
                                   OrderEntity order,
                                   UserEntity user) {

        BigDecimal price = user.getCart().getProductsSum()
                .add(BigDecimal.valueOf(user.getCart().getCountProducts() * 0.5))
                .add(BigDecimal.valueOf(3.50));

        order
                .setOwner(user)
                .setPrice(price)
                .setCreatedOn(LocalDateTime.now())
                .setComment(orderDto.getComment() != null ? orderDto.getComment() : "No comment")
                .setAddress(orderDto.getAddress())
                .setContactNumber(orderDto.getContactNumber())
                .setStatus(OrderStatusEnum.IN_PROGRESS);
    }

    @Transactional
    public List<OrderViewDto> getOrdersByUser(String username) {

        final UserEntity user = this.userService.getUserByUsername(username);

        return this.orderRepository
                .findAllByOwner_Id(user.getId())
                .stream()
                .map(this::mapToOrderViewDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<OrderViewDto> getInProgressOrdersByUser(UserEntity userEntity) {

        return this.orderRepository
                .findAllByStatusAndOwner_Id(OrderStatusEnum.IN_PROGRESS, userEntity.getId())
                .stream()
                .map(this::mapToOrderViewDto)
                .collect(Collectors.toList());
    }

    private OrderViewDto mapToOrderViewDto(OrderEntity orderEntity) {

        OrderViewDto orderDetail = this.modelMapper.map(orderEntity, OrderViewDto.class);

        orderDetail.setClient(orderEntity.getOwner().getUsername());

        return orderDetail;
    }

    public OrderViewDto getOrderById(Long id) {

        OrderEntity order = this.orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, "Order"));

        if (order.getComment().equals("")) {
            order.setComment("There is no comment on this order");
        }

        return mapToOrderViewDto(order);
    }

    public List<OrderViewDto> getAllOrders() {
        return this.orderRepository
                .findAll()
                .stream()
                .map(this::mapToOrderViewDto)
                .collect(Collectors.toList());
    }

    public void finishOrder(Long orderId) {

        OrderEntity orderEntity = this.orderRepository
                .findById(orderId)
                .orElseThrow(() -> new NotFoundException(orderId, "Order"));

        orderEntity.setStatus(OrderStatusEnum.DELIVERED);
        orderEntity.setDeliveredOn(LocalDateTime.now());

        this.orderRepository.saveAndFlush(orderEntity);
    }

    public void cancelOrder(Long orderId) {

        OrderEntity orderEntity = this.orderRepository
                .findById(orderId)
                .orElseThrow(() -> new NotFoundException(orderId, "Order"));

        orderEntity.setStatus(OrderStatusEnum.CANCELLED);

        this.orderRepository.saveAndFlush(orderEntity);
    }

}