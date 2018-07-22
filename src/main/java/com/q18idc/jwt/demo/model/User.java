package com.q18idc.jwt.demo.model;

import lombok.Data;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author q18idc.com QQ993143799
 * @date 2018/7/18 22:49
*/

@Data
@CacheNamespaceRef(User.class)
@Table(name = "tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = -7604408412246780362L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String salt;

    private String phone;

    private String email;

    private String sex;

    private Date birthday;

}