package com.q18idc.jwt.demo.model;

import lombok.Data;

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
@Table(name = "tb_permission")
public class Permission implements Serializable {
    private static final long serialVersionUID = -1205867062587349285L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 权限名
     */
    private String name;

    /**
     * 权限类型 菜单menu 或单条权限permission
     */
    private String type;

    /**
     * 权限URL
     */
    private String url;

    /**
     * 权限代码
     */
    private String percode;

    /**
     * 父级ID
     */
    private Integer parentid;

    /**
     * 是否可用 1可用 0禁用
     */
    private Integer available;

    /**
     * 是否删除 1删除 0显示
     */
    private Integer delete;

}