package com.datpvc.smart_shop_service.mapper;

import com.datpvc.smart_shop_service.dto.request.CommentRequest;
import com.datpvc.smart_shop_service.dto.response.CommentResponse;
import com.datpvc.smart_shop_service.entity.Comment;
import com.datpvc.smart_shop_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toComment(CommentRequest request);

    @Mapping(target = "fullName", expression = "java(mapFullName(response.getUser()))")
    @Mapping(target = "profileImage", source = "user.profileImage")
    @Mapping(target = "userId", source = "user.id")
    CommentResponse toCommentResponse(Comment response);

    default String mapFullName(User user) {
        if (user == null) return null;
        return user.getFirstName() + " " + user.getLastName();
    }
}
