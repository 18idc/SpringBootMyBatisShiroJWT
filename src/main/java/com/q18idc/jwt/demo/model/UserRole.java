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
@Table(name = "tb_user_role")
public class UserRole implements Serializable {
    private static final long serialVersionUID = -84968714925671818L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 角色id
     */
    private Integer roleid;
}