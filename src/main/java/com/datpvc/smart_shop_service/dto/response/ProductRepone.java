package com.datpvc.smart_shop_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRepone {
    String id;
    String name;
    String description;
    String image;
    String sku;
    int price;
    int stock;
}
