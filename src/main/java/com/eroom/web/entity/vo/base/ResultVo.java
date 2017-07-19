package com.eroom.web.entity.vo.base;

public class ResultVo implements java.io.Serializable {
    private static final long serialVersionUID = -5217559924599826389L;

    private Boolean success;

    private String message;

    private Integer total;

    private Object datas;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ResultVo() {
        this.success = true;
    }

    public Object getDatas() {
        return datas;
    }

    public void setDatas(Object datas) {
        this.datas = datas;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("{");
        sb.append("\"success\":").append(success);
        sb.append(",\"message\":\"").append(message).append("\"");
        if (total != null) {
            sb.append(",\"total\":").append(total).append("");
        }
        if (datas != null) {
            sb.append(",\"datas\":").append(datas.toString());
        }
        sb.append("}");
        return sb.toString();
    }
}