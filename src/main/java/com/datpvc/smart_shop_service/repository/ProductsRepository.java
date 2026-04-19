package com.datpvc.smart_shop_service.repository;

import com.datpvc.smart_shop_service.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductsRepository
        extends JpaRepository<Products, String>, JpaSpecificationExecutor<Products> {

    @Query("""
    SELECT p FROM Products p
    WHERE (:text IS NULL OR :text = '' OR
           (LOWER(p.name) LIKE LOWER(CONCAT('%', :text, '%'))
            OR LOWER(p.description) LIKE LOWER(CONCAT('%', :text, '%'))))
""")
    Page<Products> search(@Param("text") String text, Pageable pageable);
}

