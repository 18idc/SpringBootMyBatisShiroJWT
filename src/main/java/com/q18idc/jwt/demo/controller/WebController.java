package com.q18idc.jwt.demo.controller;

import com.q18idc.jwt.demo.bean.ActiveUser;
import com.q18idc.jwt.demo.bean.ResponseBean;
import com.q18idc.jwt.demo.service.UserService;
import com.q18idc.jwt.demo.utils.JWTUtil;
import com.q18idc.jwt.demo.utils.RedisUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequestMapping("/")
@CrossOrigin
@RestController
public class WebController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public ResponseBean login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {

        ActiveUser activeUser = userService.getUser(username);

        //将用户输入的密码进行加密
        SimpleHash md5 = new SimpleHash("md5", password, activeUser.getSalt(), 1024);
        password = md5.toString();

        if (activeUser == null) {
            log.error("用户名或密码错误");
            return new ResponseBean(201, "用户名或密码错误", null);
        }

        if (activeUser.getPassword().equals(password)) {
            log.info("用户：" + username + " 登录成功");
            String token = JWTUtil.sign(username, password);
//            redisUtils.set(RedisUtils.KEY + username, token);
            return new ResponseBean(200, "登录成功", token);
        } else {
            log.error("用户名或密码错误");
            return new ResponseBean(201, "用户名或密码错误", null);
        }
    }

    /**
     * 退出
     *
     * @return
     */
    @ApiOperation("退出登录")
    @GetMapping("logout")
    public ResponseBean logout(HttpServletRequest request) {
        String head_token = request.getHeader("Authorization");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        String username = JWTUtil.getUsername(head_token);
        if (username != null) {
//            redisUtils.set(RedisUtils.KEY + username, "");
        }
        return new ResponseBean(200, "退出成功", null);
    }

    @ApiOperation("401")
    @ApiIgnore
    @RequestMapping(path = "/401", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean unauthorized(String msg) {
        return new ResponseBean(401, "" + msg, null);
    }

}
