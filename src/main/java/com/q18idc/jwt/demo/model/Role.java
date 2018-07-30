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
 * @date 2018/7/18 22:48
*/

@Data
@Table(name = "tb_role")
public class Role implements Serializable {
    private static final long serialVersionUID = 3487257621698460001L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 是否可用 1可用 0禁用
     */
    private Integer available;

    /**
     * 是否删除 1删除 0显示
     */
    private Integer delete;

}