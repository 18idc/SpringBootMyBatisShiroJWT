package com.q18idc.jwt.demo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.q18idc.jwt.demo.bean.ActiveUser;
import com.q18idc.jwt.demo.model.Permission;
import com.q18idc.jwt.demo.model.User;
import com.q18idc.jwt.demo.mapper.PermissionMapper;
import com.q18idc.jwt.demo.mapper.UserMapper;
import com.q18idc.jwt.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

/**
 * @author q18idc.com QQ993143799
 * @date 2018/5/17 15:19
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    @Override
    public ActiveUser getUser(String username) {
        if (!StringUtils.isEmpty(username)) {
            Example example = new Example(User.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("username", username);
            User user = userMapper.selectOneByExample(example);
            if (user != null) {
                ActiveUser activeUser = new ActiveUser();
                activeUser.setId(user.getId());
                activeUser.setUsername(user.getUsername());
                activeUser.setPassword(user.getPassword());
                activeUser.setSalt(user.getSalt());

                //根据用户名获取权限和菜单
                List<Permission> permissionList = permissionMapper.getUserPermissionListByUserName(username);
                List<Permission> menuList = permissionMapper.getUserPermissionMenuListByUserName(username);
                activeUser.setMenuList(menuList);
                activeUser.setPermissionList(permissionList);

                return activeUser;
            }
        }
        return null;
    }
}
