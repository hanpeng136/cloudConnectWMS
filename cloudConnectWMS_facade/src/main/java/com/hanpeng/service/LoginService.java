package com.hanpeng.service;

import com.hanpeng.bean.Admin;
import com.hanpeng.bean.FaceRecognition;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface LoginService {

    public List<Admin> Login(String username, String password);

    public List<FaceRecognition> getBaseList();

    public boolean getResult(String image1, String image2) throws IOException;

    public boolean getFaceResult(String image) throws IOException;

    public void insertFaceInfo(FaceRecognition faceRecognition);

    public boolean queryCountByFaceInfo(String username, int n);

    public List<Map<String, ?>> userInfo(String userName);

    public void upDate(String date, String userName);

    public void modifyPassWord(String userName, String passWord);
}
