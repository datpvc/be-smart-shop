package com.datpvc.smart_shop_service.controller;

import com.datpvc.smart_shop_service.dto.request.ProductRequest;
import com.datpvc.smart_shop_service.dto.response.ApiResponse;
import com.datpvc.smart_shop_service.dto.response.ProductRepone;
import com.datpvc.smart_shop_service.service.ProductsService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductsController {
    ProductsService productsService;

    @PostMapping
    ApiResponse<ProductRepone> createProduct(@RequestBody @Valid ProductRequest request) {
        return ApiResponse.<ProductRepone>builder()
                .result(productsService.createProduct(request))
                .message("Product created successfully")
                .build();
    }

    @GetMapping
    ApiResponse<List<ProductRepone>> getAllProducts() {
        return ApiResponse.<List<ProductRepone>>builder()
                .result(productsService.getAllProducts())
                .message("All products successfully")
                .build();
    }

    @GetMapping("/{productId}")
    ApiResponse<ProductRepone> getUserById(@PathVariable("productId") String productId) {
        return ApiResponse.<ProductRepone>builder()
                .result(productsService.getProductById(productId))
                .message("Product successfully")
                .build();
    }

    @PutMapping("/{productId}")
    ApiResponse<ProductRepone> updateUser(@PathVariable String productId, @RequestBody @Valid ProductRequest request) {
        return ApiResponse.<ProductRepone>builder()
                .result(productsService.updateProduct(productId, request))
                .message("Product updated successfully")
                .build();
    }

    @DeleteMapping("/{productId}")
    ApiResponse deleteProduct(@PathVariable String productId) {
        productsService.deleteProduct(productId);
        return ApiResponse.builder().message("Successfully deleted product").build();
    }
}
