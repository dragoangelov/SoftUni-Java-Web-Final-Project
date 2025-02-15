package org.example.deliveryservice.util;

import org.example.deliveryservice.model.dto.OrderViewDto;
import org.example.deliveryservice.model.entity.*;
import org.example.deliveryservice.model.enums.GenderEnum;
import org.example.deliveryservice.model.enums.OrderStatusEnum;
import org.example.deliveryservice.model.enums.ProductCategoryEnum;
import org.example.deliveryservice.model.enums.UserRoleEnum;
import org.example.deliveryservice.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class TestDataUtils {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public TestDataUtils(UserRoleRepository userRoleRepository,
                         UserRepository userRepository,
                         CartRepository cartRepository,
                         ProductRepository productRepository,
                         OrderRepository orderRepository,
                         ModelMapper modelMapper) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(userRole);
        }
    }

    public UserEntity createTestAdmin(String email,
                                      String username) {

        initRoles();

        UserEntity admin = new UserEntity()
                .setEmail(email)
                .setUsername(username)
                .setFirstName("Admin")
                .setLastName("Admin")
                .setPassword("123123")
                .setGender(GenderEnum.FEMALE)
                .setAge(25)
                .setCart(createCart())
                .setOrders(new ArrayList<>())
                .setPhoneNumber("0789632145")
                .setRoles(userRoleRepository.findAll());

        return userRepository.save(admin);
    }

    public void addProduct(UserEntity user,
                           ProductEntity product) {
        user.getCart().addProduct(product);
    }


    public UserEntity createTestUser(String email,
                                     String username) {

        initRoles();

        UserEntity user = new UserEntity()
                .setEmail(email)
                .setUsername(username)
                .setFirstName("User")
                .setLastName("Userov")
                .setPassword("topsecret")
                .setGender(GenderEnum.FEMALE)
                .setAge(25)
                .setCart(createCart())
                .setOrders(new ArrayList<>())
                .setPhoneNumber("0789632145")
                .setRoles(userRoleRepository.
                        findAll().stream().
                        filter(r -> r.getRole() == UserRoleEnum.USER).
                        toList());

        return userRepository.save(user);
    }

    public UserEntity createTestWorker(String email,
                                       String username) {

        initRoles();

        UserEntity user = new UserEntity()
                .setEmail(email)
                .setUsername(username)
                .setFirstName("Worker")
                .setLastName("Workerov")
                .setPassword("topsecret")
                .setGender(GenderEnum.FEMALE)
                .setAge(25)
                .setCart(createCart())
                .setOrders(new ArrayList<>())
                .setPhoneNumber("0789632145")
                .setRoles(userRoleRepository.
                        findAll().stream().
                        filter(r -> r.getRole() != UserRoleEnum.ADMIN).
                        toList());

        return userRepository.save(user);
    }

    public OrderViewDto createOrderDetailViewDto(String ownerEmail,
                                                 String ownerName) {

        OrderEntity entity = new OrderEntity()
                .setOwner(createTestUser(ownerEmail, ownerName))
                .setPrice(BigDecimal.TEN)
                .setAddress("orderAddress")
                .setCreatedOn(LocalDateTime.now())
                .setContactNumber("orderContactNumber")
                .setStatus(OrderStatusEnum.IN_PROGRESS);

        return this.modelMapper.map(entity, OrderViewDto.class);
    }


    public CartEntity createCart() {

        CartEntity cart = new CartEntity();

        return cartRepository.save(cart);

    }

    public ProductEntity createProductBurger(String name) {

        ProductEntity product = new ProductEntity()
                .setPrice(BigDecimal.TEN)
                .setName(name)
                .setCategory(ProductCategoryEnum.PIZZA)
                .setDescription("description product");

        return productRepository.saveAndFlush(product);
    }

    public ProductEntity createProductPizza(String name) {

        ProductEntity product = new ProductEntity()
                .setPrice(BigDecimal.TEN)
                .setName(name)
                .setCategory(ProductCategoryEnum.DESSERT)
                .setDescription("description product");

        return productRepository.saveAndFlush(product);
    }

    public OrderEntity createOrder(UserEntity owner) {

        OrderEntity order = new OrderEntity()
                .setPrice(BigDecimal.TEN)
                .setCreatedOn(LocalDateTime.now())
                .setAddress("orderAddress")
                .setContactNumber("0789654466")
                .setComment("orderComment")
                .setOwner(owner)
                .setStatus(OrderStatusEnum.IN_PROGRESS);

        return orderRepository.save(order);
    }


    public void cleanUpDatabase() {
        orderRepository.deleteAll();
        userRepository.deleteAll();
        cartRepository.deleteAll();
        productRepository.deleteAll();
        userRoleRepository.deleteAll();
    }

}
