package com.q18idc.jwt.demo.service;

import com.q18idc.jwt.demo.bean.ActiveUser;

/**
 * @author q18idc.com QQ993143799
 * @date 2018/5/17 15:18
 */
public interface UserService {
    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    ActiveUser getUser(String username);
}
