package com.hanpeng.controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanpeng.bean.Admin;
import com.hanpeng.bean.FaceRecognition;
import com.hanpeng.utils.*;
import com.hanpeng.service.LoginService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes(value = {"userInfo", "menus"})
public class LoginController {

    @Resource
    private LoginService service;

    //百度人脸识别Token
    private static String accessToken;

    // 获取用户信息
    @RequestMapping("userInfo")
    String getUserInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession setHs = request.getSession();
        String userName = (String) setHs.getAttribute("username");
        List<Map<String, ?>> userInfo = service.userInfo(userName);
        setHs.setAttribute("date", userInfo.get(0).get("date"));
        setHs.setAttribute("birthday", userInfo.get(0).get("birthday"));
        setHs.setAttribute("qq", userInfo.get(0).get("qq"));
        setHs.setAttribute("city", userInfo.get(0).get("city"));
        setHs.setAttribute("sex", userInfo.get(0).get("sex"));
        setHs.setAttribute("name", userInfo.get(0).get("name"));
        setHs.setAttribute("email", userInfo.get(0).get("email"));
        setHs.setAttribute("tel", userInfo.get(0).get("tel"));
        return "personal";
    }

    // 忘记密码，修改密码
    @RequestMapping("modifyPassWord")
    void modifyPassWord(HttpServletRequest request, HttpServletResponse response, String userName, String passWord,
                        Model model) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            service.modifyPassWord(userName, passWord);
            out.write("{\"flag\":\"true\"}");
        } catch (Exception e) {
            out.write("{\"flag\":\"false\"}");
        }
    }

    // 退出登录
    @RequestMapping("logout")
    String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);// 防止创建Session
        session.removeAttribute("username");
        session.invalidate();
        return "index";
    }

}
