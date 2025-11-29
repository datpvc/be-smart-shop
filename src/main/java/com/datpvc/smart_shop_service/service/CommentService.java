package com.datpvc.smart_shop_service.service;

import com.datpvc.smart_shop_service.dto.request.CommentRequest;
import com.datpvc.smart_shop_service.dto.response.CommentResponse;
import com.datpvc.smart_shop_service.dto.response.ProductRepone;
import com.datpvc.smart_shop_service.entity.Comment;
import com.datpvc.smart_shop_service.entity.Products;
import com.datpvc.smart_shop_service.entity.User;
import com.datpvc.smart_shop_service.exception.AppException;
import com.datpvc.smart_shop_service.exception.ErrorCode;
import com.datpvc.smart_shop_service.mapper.CommentMapper;
import com.datpvc.smart_shop_service.repository.CommentRepository;
import com.datpvc.smart_shop_service.repository.ProductsRepository;
import com.datpvc.smart_shop_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CommentService {
    CommentRepository commentRepository;
    UserRepository userRepository;
    ProductsRepository ProductsRepository;
    CommentMapper commentMapper;

    public CommentResponse createComment(CommentRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Products product = ProductsRepository.findById(request.getProductId()).orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        Comment comment = commentMapper.toComment(request);
        comment.setUser(user);
        comment.setProduct(product);
        comment.setCreatedAt(LocalDate.from(LocalDateTime.now()));
        log.info("getCreatedAt {}", comment.getCreatedAt());
        commentRepository.save(comment);

        return commentMapper.toCommentResponse(comment);
    }

    public List<CommentResponse> getCommentByProductId(String productId) {
        return commentRepository.findByProductId(productId).stream().map(commentMapper::toCommentResponse).toList();
    }

    public void deleteComment(String id) {
        if (!commentRepository.existsById(id)) {
            throw new AppException(ErrorCode.COMMENT_NOT_FOUND);
        }
        commentRepository.deleteById(id);
    }
}
