package com.q18idc.jwt.demo.mapper;

import com.q18idc.jwt.demo.model.Permission;
import com.q18idc.jwt.demo.utils.MyMapper;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@CacheNamespaceRef(PermissionMapper.class)
public interface PermissionMapper extends MyMapper<Permission> {
    /**
     * 获取用户所属权限菜单列表
     *
     * @param username
     * @return
     */
    List<Permission> getUserPermissionMenuListByUserName(String username);

    /**
     * 获取用户所属权限列表
     *
     * @param username
     * @return
     */
    List<Permission> getUserPermissionListByUserName(String username);
}