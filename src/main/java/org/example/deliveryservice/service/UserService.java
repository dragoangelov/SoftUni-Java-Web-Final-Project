package org.example.deliveryservice.service;

import org.example.deliveryservice.model.dto.UserRegistrationBindingDto;
import org.example.deliveryservice.model.entity.UserEntity;
import org.example.deliveryservice.model.entity.UserRoleEntity;
import org.example.deliveryservice.model.enums.UserRoleEnum;
import org.example.deliveryservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class UserService {
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleService userRoleService;



    public UserService(ModelMapper modelMapper,
                       PasswordEncoder passwordEncoder,
                       UserRepository userRepository, UserRoleService userRoleService) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
    }

    public void registerUser(UserRegistrationBindingDto userToRegister) {

        UserEntity userToSave = this.mapToUserEntity(userToRegister);

        final UserRoleEntity userRole = this.userRoleService.getRole(UserRoleEnum.USER);


        userToSave
                .setPassword(passwordEncoder.encode(userToSave.getPassword()))
                .setRoles(new ArrayList<>(Collections.singletonList(userRole)));

        this.userRepository.saveAndFlush(userToSave);
    }

    public UserEntity getUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public UserEntity mapToUserEntity(UserRegistrationBindingDto modelDto) {
        return this.modelMapper.map(modelDto, UserEntity.class);
    }

}
