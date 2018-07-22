package com.q18idc.jwt.demo.shiro;

import com.q18idc.jwt.demo.bean.ActiveUser;
import com.q18idc.jwt.demo.model.Permission;
import com.q18idc.jwt.demo.service.UserService;
import com.q18idc.jwt.demo.utils.JWTUtil;
import com.q18idc.jwt.demo.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }


    /**
     * 设置Realm的名称
     *
     * @param name
     */
    @Override
    public void setName(String name) {
        super.setName(MyRealm.class.getName());
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JWTUtil.getUsername(principals.toString());
        ActiveUser user = userService.getUser(username);
        if (user.getPermissionList() != null) {
            //定义的权限集合
            List<String> permissions = new ArrayList<>();
            if (user.getPermissionList().size() > 0) {
                for (Permission permission : user.getPermissionList()) {
                    //将数据库中的权限标识符放入集合
                    permissions.add(permission.getPercode());
                }
            }
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addStringPermissions(permissions);
            return simpleAuthorizationInfo;
        }
        return null;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);

        if (username == null) {
            log.error("Token 失效");
            throw new AuthenticationException("Token 失效");
        }

        ActiveUser activeUser = userService.getUser(username);


        if (activeUser == null) {
            log.error("用户不存在");
            throw new AuthenticationException("用户不存在");
        }

        if (!JWTUtil.verify(token, username, activeUser.getPassword())) {
            log.error("Token 失效");
            throw new AuthenticationException("Token 失效");
        }

        return new SimpleAuthenticationInfo(token, token, getName());
    }


    /**
     * 清空缓存
     */
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }

}
