package com.q18idc.jwt.demo.mapper;

import com.q18idc.jwt.demo.model.Role;
import com.q18idc.jwt.demo.utils.MyMapper;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.springframework.stereotype.Repository;

@Repository
@CacheNamespaceRef(RoleMapper.class)
public interface RoleMapper extends MyMapper<Role> {
}