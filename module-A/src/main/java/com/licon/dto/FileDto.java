package com.licon.dto;

import java.io.Serializable;

/**
 * @author Licon
 * @description: TODO
 * @date 2023/10/24 11:31
 */
public class FileDto implements Serializable {

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
