package com.datpvc.smart_shop_service.service;

import com.datpvc.smart_shop_service.dto.request.ProductRequest;
import com.datpvc.smart_shop_service.dto.request.ProductSearchRequest;
import com.datpvc.smart_shop_service.dto.response.ProductRepone;
import com.datpvc.smart_shop_service.dto.response.ProductSearchResponse;
import com.datpvc.smart_shop_service.entity.Products;
import com.datpvc.smart_shop_service.exception.AppException;
import com.datpvc.smart_shop_service.exception.ErrorCode;
import com.datpvc.smart_shop_service.mapper.ProductsMapper;
import com.datpvc.smart_shop_service.repository.ProductsRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProduct(String id) {
        if (!productsRepository.existsById(id)) {
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        productsRepository.deleteById(id);
    }

    public List<ProductRepone> getAllProducts() {
        return productsRepository.findAll().stream().map(productsMapper::toProductRepone).toList();
    }

    public ProductRepone getProductById(String id) {
        return productsMapper.toProductRepone(
                productsRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND)));
    }

    public ProductSearchResponse productSearch (ProductSearchRequest request) {
        int page = request.getPage() - 1;
        if (page < 0) page = 0;

        Pageable pageable = PageRequest.of(page, request.getSize());

        Specification<Products> spec = (root, query, cb) -> {
            if (request.getTextSearch() == null || request.getTextSearch().isEmpty()) {
                return cb.conjunction();
            }
            String like = "%" + request.getTextSearch().toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(root.get("name")), like),
                    cb.like(cb.lower(root.get("description")), like)
            );
        };

        Page<Products> pageData = productsRepository.findAll(spec, pageable);

        return productsMapper.toSearchResponse(pageData, request.getPage(), request.getSize());
    }
}
