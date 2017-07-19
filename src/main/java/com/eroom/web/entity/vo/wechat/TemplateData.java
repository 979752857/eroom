package com.eroom.web.entity.vo.wechat;

public class TemplateData {
    private String value;

    private String color;

    public TemplateData() {
        this.color = "#173177";
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "TemplateData [value=" + value + ", color=" + color + "]";
    }
}
