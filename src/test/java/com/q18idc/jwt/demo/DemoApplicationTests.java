package com.q18idc.jwt.demo;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        // password 123456 e10adc3949ba59abbe56e057f20f883e
        SimpleHash md5 = new SimpleHash("md5", "123456", "q18idc.com", 1024);
        System.out.println(md5);
    }

}
