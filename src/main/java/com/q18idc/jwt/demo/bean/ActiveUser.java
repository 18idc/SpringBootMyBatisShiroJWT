package com.q18idc.jwt.demo.bean;

import com.q18idc.jwt.demo.model.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

/**
 * @author q18idc.com QQ993143799
 * @date 2018/5/4 17:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveUser implements Serializable {
    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 用户名 用户账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 用户具有权限的菜单
     */
    private List<Permission> menuList;

    /**
     * 用户具有的权限列表
     */
    private List<Permission> permissionList;
}
