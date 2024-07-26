package org.example.deliveryservice.service;

import jakarta.annotation.PostConstruct;
import org.example.deliveryservice.model.entity.UserEntity;
import org.example.deliveryservice.model.entity.UserRoleEntity;
import org.example.deliveryservice.model.enums.GenderEnum;
import org.example.deliveryservice.model.enums.UserRoleEnum;
import org.example.deliveryservice.repository.UserRepository;
import org.example.deliveryservice.repository.UserRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DatabaseInitializationService {

    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartService cartService;
    private final UserRepository userRepository;

    public DatabaseInitializationService(UserRoleRepository userRoleRepository,
                                         PasswordEncoder passwordEncoder,
                                         CartService cartService,
                                         UserRepository userRepository) {
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.cartService = cartService;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initAdmin();
        initUsers();
    }

    public void initRoles() {
        if (this.userRoleRepository.count() == 0) {
            this.userRoleRepository.saveAllAndFlush(getUserRoles());
        }
    }

    private static List<UserRoleEntity> getUserRoles() {
        List<UserRoleEntity> roles = new ArrayList<>();

        roles.add(new UserRoleEntity().setRole(UserRoleEnum.ADMIN));
        roles.add(new UserRoleEntity().setRole(UserRoleEnum.USER));

        return roles;
    }

    public void initAdmin() {
        if (this.userRepository.count() == 0) {
            UserEntity user = new UserEntity()
                    .setFirstName("Admin")
                    .setLastName("Admin")
                    .setAge(25)
                    .setEmail("admin@abv.com")
                    .setPassword(passwordEncoder.encode("123123"))
                    .setGender(GenderEnum.MALE)
                    .setUsername("admin")
                    .setPhoneNumber("0888444111")
                    .setRoles(userRoleRepository.findAll())
                    .setCart(this.cartService.getNewCart());

            this.userRepository.saveAndFlush(user);
        }
    }


    private void initUsers() {
        if (this.userRepository.count() == 1) {

            UserEntity userOne = new UserEntity()
                    .setFirstName("Drago")
                    .setLastName("Angelov")
                    .setAge(25)
                    .setEmail("drago@abv.com")
                    .setPassword(passwordEncoder.encode("123123"))
                    .setGender(GenderEnum.MALE)
                    .setUsername("user")
                    .setPhoneNumber("0888444999")
                    .setRoles(userRoleRepository.findAll()
                            .stream()
                            .filter(r -> r.getRole() == UserRoleEnum.USER)
                            .collect(Collectors.toList()))
                    .setCart(this.cartService.getNewCart());

            UserEntity userTwo = new UserEntity()
                    .setFirstName("Denitsa")
                    .setLastName("Angelova")
                    .setAge(29)
                    .setEmail("deni@abv.com")
                    .setPassword(passwordEncoder.encode("123123"))
                    .setGender(GenderEnum.FEMALE)
                    .setUsername("deni")
                    .setPhoneNumber("0888999777")
                    .setRoles(userRoleRepository.findAll()
                            .stream()
                            .filter(r -> r.getRole() == UserRoleEnum.USER)
                            .collect(Collectors.toList()))
                    .setCart(this.cartService.getNewCart());


            this.userRepository.saveAndFlush(userOne);
            this.userRepository.saveAndFlush(userTwo);
        }
    }

}
