package com.datpvc.smart_shop_service.controller;

import com.datpvc.smart_shop_service.dto.request.CheckoutRequest;
import com.datpvc.smart_shop_service.dto.request.ProductRequest;
import com.datpvc.smart_shop_service.dto.response.ApiResponse;
import com.datpvc.smart_shop_service.dto.response.ProductRepone;
import com.datpvc.smart_shop_service.service.CheckoutService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkout")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CheckoutController {
    CheckoutService checkoutService;

    @PutMapping("/{userId}")
    ApiResponse checkout(@PathVariable String userId, @RequestBody @Valid List<CheckoutRequest> requests) {
        checkoutService.checkout(userId, requests);
        return ApiResponse.builder().message("Checkout successfully").build();
    }
}
