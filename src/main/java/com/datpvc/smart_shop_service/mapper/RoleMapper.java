package com.datpvc.smart_shop_service.mapper;

import com.datpvc.smart_shop_service.dto.request.RoleRequest;
import com.datpvc.smart_shop_service.dto.response.RoleResponse;
import com.datpvc.smart_shop_service.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest roleRequest);

    RoleResponse toRoleResponse(Role role);
}
