package com.eroom.web.entity.vo.wechat;

import java.util.Arrays;

public class PhotoVo {
    private String fileName;

    private byte[] file;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "PhotoVo [fileName=" + fileName + ", file=" + Arrays.toString(file) + "]";
    }

}
