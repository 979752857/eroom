package com.eroom.web.utils.exception;

import java.io.Serializable;

/**
 * 系统配置类异常封装
 * 
 * @author tendy
 *
 */
public class SystemException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1087739284146377793L;

    private String message;

    private String code;

    private String detail;

    public SystemException(String message) {
        super(message);
        this.message = message;
    }

    public SystemException(String code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public SystemException(String code, String message, String detail) {
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