package com.hanpeng.service;

import com.hanpeng.bean.Admin;
import com.hanpeng.bean.FaceRecognition;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface LoginService {

    public List<Admin> login(String username, String password);

    public List<Map<String, ?>> userInfo(String userName);

    public void upDate(String date, String userName);

    public void modifyPassWord(String userName, String passWord);
}
