package org.example.deliveryservice.repository;

import org.example.deliveryservice.model.entity.OrderEntity;
import org.example.deliveryservice.model.enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByOwner_Id(Long id);

    List<OrderEntity> findAllByStatusAndOwner_Id(OrderStatusEnum orderStatusEnum, Long id);
}
