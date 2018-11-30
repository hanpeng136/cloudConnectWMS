package com.hanpeng.bean;

import java.io.Serializable;

/**
 * @program: cloudConnectWMS
 * @description: 人脸识别
 * @author: by hanpeng
 * @create: 2018-11-27 13:31
 **/
public class FaceRecognition implements Serializable {
    private Integer id;
    private String username;
    private String face_base64;

    public FaceRecognition() {
    }

    public FaceRecognition(String username, String face_base64) {
        this.username = username;
        this.face_base64 = face_base64;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFace_base64() {
        return face_base64;
    }

    public void setFace_base64(String face_base64) {
        this.face_base64 = face_base64;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
