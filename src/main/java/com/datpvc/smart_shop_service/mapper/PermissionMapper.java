package com.datpvc.smart_shop_service.mapper;

import com.datpvc.smart_shop_service.dto.request.PermissionRequest;
import com.datpvc.smart_shop_service.dto.response.PermissionResponse;
import com.datpvc.smart_shop_service.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
