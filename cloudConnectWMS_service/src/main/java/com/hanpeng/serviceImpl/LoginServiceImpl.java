package com.hanpeng.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hanpeng.bean.Admin;
import com.hanpeng.service.LoginService;
import com.hanpeng.utils.GetToken;
import com.hanpeng.utils.GsonUtils;
import com.hanpeng.utils.HttpUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanpeng.utils.MD5;
import com.hanpeng.dao.LoginDao;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginDao dao;

    //验证用户名和密码
    @Override
    public List<Admin> login(String username, String password) {
        try {
            password = MD5.getMD5String(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dao.queryByNameAndPassword(username, password);
    }


    public List<Map<String, ?>> userInfo(String userName) {
        return dao.queryUserInfo(userName);
    }

    public void upDate(String date, String userName) {
        dao.upDate(date, userName);
    }

    public void modifyPassWord(String userName, String passWord) {
        try {
            passWord = MD5.getMD5String(passWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dao.modifyPassWord(userName, passWord);
    }
}
