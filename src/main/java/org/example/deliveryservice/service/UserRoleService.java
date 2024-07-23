package org.example.deliveryservice.service;

import org.example.deliveryservice.model.entity.UserRoleEntity;
import org.example.deliveryservice.model.enums.UserRoleEnum;
import org.example.deliveryservice.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public UserRoleEntity getRole(UserRoleEnum role){
        return this.userRoleRepository.findByRole(role);
    }

}
