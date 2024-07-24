package org.example.deliveryservice.service;

import org.example.deliveryservice.model.entity.CartEntity;
import org.example.deliveryservice.model.entity.ProductEntity;
import org.example.deliveryservice.model.entity.UserEntity;
import org.example.deliveryservice.repository.CartRepository;
import org.example.deliveryservice.repository.ProductRepository;
import org.example.deliveryservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository shoppingCartRepository;

    public CartService(UserRepository userRepository,
                       ProductRepository productRepository,
                       CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.shoppingCartRepository = cartRepository;
    }

    public CartEntity getNewCart() {

        CartEntity shoppingCart = new CartEntity();

        this.shoppingCartRepository.saveAndFlush(shoppingCart);

        return shoppingCart;
    }

    @Transactional
    public void addProductToTheCart(Long id,
                                    String username) {

        UserEntity user = this.userRepository.findByUsername(username);
        final ProductEntity product = this.productRepository.findProductEntityById(id);

        user.getCart().addProduct(product);
        user.getCart().increaseProductsSum(product.getPrice());
    }

    @Transactional
    public void removeProductFromTheCart(Long id,
                                         String username) {

        UserEntity user = this.userRepository.findByUsername(username);
        final ProductEntity product = this.productRepository.findProductEntityById(id);

        user.getCart().getProducts().remove(product);
        user.getCart().decreaseProductsSum(product.getPrice());

    }

}
