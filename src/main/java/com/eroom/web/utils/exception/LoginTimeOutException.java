package com.eroom.web.utils.exception;

import java.io.Serializable;

/**
 * 登录超时异常封装
 * 
 * @author caiyt
 *
 */
public class LoginTimeOutException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 3787730660315875183L;

    private String message;

    private String code;

    private String detail;

    public LoginTimeOutException() {
    }

    public LoginTimeOutException(String message) {
        super(message);
        this.message = message;
    }

    public LoginTimeOutException(String code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public LoginTimeOutException(String code, String message, String detail) {
        super(message);
        this.message = message;
        this.code = code;
        this.detail = detail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}
