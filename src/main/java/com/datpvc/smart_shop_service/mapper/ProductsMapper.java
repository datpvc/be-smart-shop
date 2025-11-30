package com.datpvc.smart_shop_service.mapper;

import com.datpvc.smart_shop_service.dto.request.ProductRequest;
import com.datpvc.smart_shop_service.dto.response.ProductRepone;
import com.datpvc.smart_shop_service.dto.response.ProductSearchResponse;
import com.datpvc.smart_shop_service.entity.Products;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface ProductsMapper {
    Products toProduct (ProductRequest request);
    ProductRepone toProductRepone (Products products);

    void updateProduct (@MappingTarget Products products, ProductRequest request);

    @Mapping(target = "products", expression = "java(page.getContent().stream().map(this::toProductRepone).toList())")
    @Mapping(target = "totalProducts", expression = "java(page.getTotalElements())")
    @Mapping(target = "totalPages", expression = "java(page.getTotalPages())")
    @Mapping(target = "page", expression = "java(pageNumber)")
    @Mapping(target = "size", expression = "java(pageSize)")
    ProductSearchResponse toSearchResponse(Page<Products> page, int pageNumber, int pageSize);
}
