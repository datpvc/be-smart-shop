package com.datpvc.smart_shop_service.controller;

import com.datpvc.smart_shop_service.dto.request.CommentRequest;
import com.datpvc.smart_shop_service.dto.response.ApiResponse;
import com.datpvc.smart_shop_service.dto.response.CommentResponse;
import com.datpvc.smart_shop_service.service.CommentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentController {
    CommentService commentService;

    @PostMapping
    ApiResponse<CommentResponse> createComment(@RequestBody @Valid CommentRequest request) {
        return ApiResponse.<CommentResponse>builder()
                .result(commentService.createComment(request))
                .message("Comment created successfully")
                .build();
    }

    @GetMapping("/{productId}")
    ApiResponse<List<CommentResponse>> getCommentByProductId(@PathVariable("productId") String productId) {
        return ApiResponse.<List<CommentResponse>>builder()
                .result(commentService.getCommentByProductId(productId))
                .message("Comments successfully")
                .build();
    }

    @DeleteMapping("/{commentId}")
    ApiResponse deleteComment(@PathVariable String commentId) {
        commentService.deleteComment(commentId);
        return ApiResponse.builder().message("Successfully deleted comment").build();
    }
}
