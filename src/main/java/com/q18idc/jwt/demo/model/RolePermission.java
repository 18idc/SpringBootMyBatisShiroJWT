package com.q18idc.jwt.demo.model;

import lombok.Data;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author q18idc.com QQ993143799
 * @date 2018/7/18 22:49
*/

@Data
@Table(name = "tb_role_permission")
public class RolePermission implements Serializable {
    private static final long serialVersionUID = -23760620891712135L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色id
     */
    private Integer roleid;

    /**
     * 权限id
     */
    private Integer perid;

}