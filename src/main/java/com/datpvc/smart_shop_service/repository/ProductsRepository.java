package com.datpvc.smart_shop_service.repository;

import com.datpvc.smart_shop_service.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductsRepository
        extends JpaRepository<Products, String>, JpaSpecificationExecutor<Products> {
}

