package org.example.deliveryservice.service;

import org.example.deliveryservice.model.dto.EditUserBindingDto;
import org.example.deliveryservice.model.dto.UserRegistrationBindingDto;
import org.example.deliveryservice.model.dto.UserViewDto;
import org.example.deliveryservice.model.entity.CartEntity;
import org.example.deliveryservice.model.entity.UserEntity;
import org.example.deliveryservice.model.entity.UserRoleEntity;
import org.example.deliveryservice.model.enums.UserRoleEnum;
import org.example.deliveryservice.repository.UserRepository;
import org.example.deliveryservice.repository.UserRoleRepository;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.example.deliveryservice.model.enums.UserRoleEnum.USER;

@Service
public class UserService {
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final UserRoleRepository userRoleRepository;
    private final CartService cartService;

//    private UserDetailsService userDetailsService;


    public UserService(ModelMapper modelMapper,
                       PasswordEncoder passwordEncoder,
                       UserRepository userRepository,
                       UserRoleService userRoleService,
                       UserRoleRepository userRoleRepository,
                       CartService cartService) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.userRoleRepository = userRoleRepository;
        this.cartService = cartService;
//        this.userDetailsService = userDetailsService;
    }

    public void registerUser(UserRegistrationBindingDto userToRegister) {

        UserEntity userToSave = this.mapToUserEntity(userToRegister);

        final UserRoleEntity userRole = this.userRoleService.getRole(USER);
        final CartEntity shoppingCart = this.cartService.getNewCart();

        userToSave
                .setPassword(passwordEncoder.encode(userToSave.getPassword()))
                .setRoles(new ArrayList<>(Collections.singletonList(userRole)))
                .setCart(shoppingCart);

        this.userRepository.saveAndFlush(userToSave);
    }

    public UserEntity getUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public UserViewDto getUserViewByUsername(String username) {
        return mapToUserView(this.userRepository.findByUsername(username));
    }

    public UserViewDto getUserById(Long id) {
        final UserEntity userById = this.userRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, "user"));

        return this.mapToUserView(userById);
    }

    public UserEntity mapToUserEntity(UserRegistrationBindingDto modelDto) {
        return this.modelMapper.map(modelDto, UserEntity.class);
    }

    public UserViewDto mapToUserView(UserEntity userEntity) {
        return this.modelMapper.map(userEntity, UserViewDto.class);
    }

    public List<UserViewDto> getAllUsers() {
        return this.userRepository.findAll().stream().map(this::mapToUserView).toList();
    }


    public void editUser(Long id,
                         EditUserBindingDto editedUser) {

        UserEntity user = this.userRepository.findUserEntityById(id);

        user.setFirstName(editedUser.getFirstName()).setLastName(editedUser.getLastName());
        this.userRepository.saveAndFlush(user);
    }

}
