package com.datpvc.smart_shop_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ProductSearchResponse {
    List<ProductRepone> products;
    long totalProducts;
    int totalPages;
    int page;
    int size;
}
