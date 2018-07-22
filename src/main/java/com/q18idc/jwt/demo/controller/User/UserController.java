package com.q18idc.jwt.demo.controller.User;

import com.q18idc.jwt.demo.bean.ResponseBean;
import com.q18idc.jwt.demo.utils.JWTUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author q18idc.com QQ993143799
 * @date 2018/5/17 16:12
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 必须要登录才能访问
     * @return
     */
    @ApiOperation(value = "必须登录具有user:del权限才能看到这个页面")
    @PostMapping("index")
    @RequiresAuthentication
    @RequiresPermissions(value = {"user:del"})
    public ResponseBean index(){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setStatus(200);
        responseBean.setMsg("需要登录,且有user:del权限才能看到这个页面");
        return responseBean;
    }


    /**
     * 获取用户信息
     * @return
     */
    @ApiOperation("获取用户信息")
    @GetMapping("info")
    public ResponseBean publics(){
        Subject subject = SecurityUtils.getSubject();
        ResponseBean responseBean = new ResponseBean();
        if (subject.isAuthenticated()) {
            Object principal = subject.getPrincipal();
            String token = principal.toString();
            String username = JWTUtil.getUsername(token);
            responseBean.setStatus(200);
            responseBean.setMsg("登录成功"+username);
            Map<String ,Object> map = new HashMap<>();
            List<String> list = new ArrayList<>();
            list.add("admin");
            map.put("name", username);
            map.put("roles", list);
            responseBean.setData(map);
        }else {
            responseBean.setStatus(201);
            responseBean.setMsg("未知错误");
        }
        return responseBean;
    }
}
