package com.datpvc.smart_shop_service.service;

import com.datpvc.smart_shop_service.dto.request.CheckoutRequest;
import com.datpvc.smart_shop_service.entity.Products;
import com.datpvc.smart_shop_service.exception.AppException;
import com.datpvc.smart_shop_service.exception.ErrorCode;
import com.datpvc.smart_shop_service.mapper.ProductsMapper;
import com.datpvc.smart_shop_service.repository.ProductsRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CheckoutService {
    ProductsRepository productsRepository;
    ProductsMapper productsMapper;

    @Transactional
    public void checkout(String userId, List<CheckoutRequest> requests) {
        for (CheckoutRequest request : requests) {
            Products product = productsRepository
                    .findById(request.getProductId()).orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

            if (product.getStock() < request.getQuantity()) {
                throw new RuntimeException("Not enough stock for product " + product.getName());
            }

            product.setStock(product.getStock() - request.getQuantity());
            productsRepository.save(product);
        }
    }
}
