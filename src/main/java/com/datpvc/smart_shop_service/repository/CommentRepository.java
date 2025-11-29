package com.datpvc.smart_shop_service.repository;

import com.datpvc.smart_shop_service.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findByProductId(String productId);
    List<Comment> findByUserId(String userId);
}
