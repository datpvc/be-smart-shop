package com.datpvc.smart_shop_service.service;

import com.datpvc.smart_shop_service.dto.request.ProductRequest;
import com.datpvc.smart_shop_service.dto.response.ProductRepone;
import com.datpvc.smart_shop_service.entity.Products;
import com.datpvc.smart_shop_service.exception.AppException;
import com.datpvc.smart_shop_service.exception.ErrorCode;
import com.datpvc.smart_shop_service.mapper.ProductsMapper;
import com.datpvc.smart_shop_service.repository.ProductsRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductsService {
    ProductsRepository productsRepository;
    ProductsMapper productsMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public ProductRepone createProduct(ProductRequest request) {
        Products product = productsMapper.toProduct(request);
        return productsMapper.toProductRepone(productsRepository.save(product));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ProductRepone updateProduct(String id, ProductRequest request) {
        Products product = productsRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        productsMapper.updateProduct(product, request);
        return productsMapper.toProductRepone(productsRepository.save(product));
    }


    public List<ProductRepone> getAllProducts() {
        return productsRepository.findAll().stream().map(productsMapper::toProductRepone).toList();
    }

    public ProductRepone getProductById(String id) {
        return productsMapper.toProductRepone(
                productsRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProduct(String id) {
        if (!productsRepository.existsById(id)) {
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        productsRepository.deleteById(id);
    }
}
