package org.example.deliveryservice.repository;

import org.example.deliveryservice.model.entity.ProductEntity;
import org.example.deliveryservice.model.enums.ProductCategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAllByCategory(ProductCategoryEnum category);

    ProductEntity findProductEntityById(Long id);

    ProductEntity findByName(String name);
}
