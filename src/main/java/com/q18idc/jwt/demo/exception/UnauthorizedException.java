package com.q18idc.jwt.demo.exception;

/**
 * @author q18idc.com QQ993143799
 * @date 2018/7/18 22:52
*/
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }
}
