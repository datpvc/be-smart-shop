package com.datpvc.smart_shop_service.mapper;

import com.datpvc.smart_shop_service.dto.request.ProductRequest;
import com.datpvc.smart_shop_service.dto.response.ProductRepone;
import com.datpvc.smart_shop_service.entity.Products;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductsMapper {
    Products toProduct (ProductRequest request);
    ProductRepone toProductRepone (Products products);

    void updateProduct (@MappingTarget Products products, ProductRequest request);
}
