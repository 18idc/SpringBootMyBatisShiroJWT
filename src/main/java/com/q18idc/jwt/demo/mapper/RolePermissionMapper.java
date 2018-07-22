package com.q18idc.jwt.demo.mapper;

import com.q18idc.jwt.demo.model.RolePermission;
import com.q18idc.jwt.demo.utils.MyMapper;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.springframework.stereotype.Repository;

@Repository
@CacheNamespaceRef(RolePermissionMapper.class)
public interface RolePermissionMapper extends MyMapper<RolePermission> {
}