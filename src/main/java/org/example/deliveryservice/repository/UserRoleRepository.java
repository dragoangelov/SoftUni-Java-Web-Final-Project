package org.example.deliveryservice.repository;

import org.example.deliveryservice.model.entity.UserRoleEntity;
import org.example.deliveryservice.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    UserRoleEntity findByRole(UserRoleEnum role);
}

