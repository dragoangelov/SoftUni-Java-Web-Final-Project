package org.example.deliveryservice.service;

import org.example.deliveryservice.exception.WrongCategoryException;
import org.example.deliveryservice.model.dto.AddProductBindingDto;
import org.example.deliveryservice.model.dto.EditProductBindingDto;
import org.example.deliveryservice.model.dto.ProductViewDto;
import org.example.deliveryservice.model.entity.ProductEntity;
import org.example.deliveryservice.model.enums.ProductCategoryEnum;
import org.example.deliveryservice.repository.ProductRepository;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository,
                          ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public List<ProductViewDto> getAllProductsByCategory(ProductCategoryEnum category) {
        return this.productRepository
                .findAllByCategory(category)
                .stream()
                .map(this::mapToViewDto)
                .collect(Collectors.toList());
    }

    private ProductViewDto mapToViewDto(ProductEntity productEntity) {
        return this.modelMapper.map(productEntity, ProductViewDto.class);
    }

    public String getCategoryName(Long id) {
        return this.productRepository
                .findProductEntityById(id)
                .getCategory()
                .name();
    }

    public void saveProduct(AddProductBindingDto productDto) {

        ProductEntity productToSave = createProduct(productDto);

        this.productRepository.saveAndFlush(productToSave);
    }

    private static ProductEntity createProduct(AddProductBindingDto productDto) {

        ProductEntity productToSave = new ProductEntity();

        productToSave
                .setName(productDto.getName())
                .setCategory(productDto.getCategory())
                .setDescription(productDto.getDescription())
                .setPrice(productDto.getPrice());

        return productToSave;
    }

    public ProductViewDto getProductById(Long productId) {

        ProductEntity productEntity = this.productRepository
                .findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException(productId, "product"));

        return this.modelMapper.map(productEntity, ProductViewDto.class);
    }

    public void editProduct(Long productId,
                            EditProductBindingDto editedProductDto) {

        ProductEntity productEntityById = this.productRepository
                .findProductEntityById(productId);

        productEntityById
                .setDescription(editedProductDto.getDescription())
                .setPrice(editedProductDto.getPrice());

        this.productRepository.saveAndFlush(productEntityById);

    }

    public void deleteProduct(Long productId) {
        this.productRepository.deleteById(productId);
    }

    public ProductCategoryEnum findCategory(String category) {

        for (ProductCategoryEnum categoryEnum : ProductCategoryEnum.values()) {
            if (categoryEnum.name().equals(category)) {
                return categoryEnum;
            }
        }

        throw new WrongCategoryException(category);
    }

}
