package com.hanpeng.service;

import java.util.List;
import java.util.Map;

public interface RegistService {

    // 新增用户，保存信息
    public boolean saveUser(String username, String password) throws Exception;

    //手机号码唯一性检测
    public boolean validatePhone(String username);

    //忘记密码
    public boolean updatePassword(String tel, String password) throws Exception;

}
