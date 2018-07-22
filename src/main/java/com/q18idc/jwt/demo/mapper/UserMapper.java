package com.q18idc.jwt.demo.mapper;

import com.q18idc.jwt.demo.model.User;
import com.q18idc.jwt.demo.utils.MyMapper;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.springframework.stereotype.Repository;

@Repository
@CacheNamespaceRef(UserMapper.class)
public interface UserMapper extends MyMapper<User> {
}