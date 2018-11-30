package com.hanpeng.bean;

import java.io.Serializable;

/**
 * @program: cloudConnectWMS
 * @description: 管理员
 * @author: by hanpeng
 * @create: 2018-11-26 17:25
 **/
public class Admin implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String realname;

    public Admin() {
    }

    public Admin(Integer id, String username, String password, String realname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realname = realname;
    }

    public Admin(String username, String password, String realname) {
        this.username = username;
        this.password = password;
        this.realname = realname;
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }



}
