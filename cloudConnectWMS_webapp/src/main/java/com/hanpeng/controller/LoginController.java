package com.hanpeng.controller;

import java.io.*;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanpeng.bean.Admin;
import com.hanpeng.bean.FaceRecognition;
import com.hanpeng.bean.ResultRespone;
import com.hanpeng.service.FaceRecognitionService;
import com.hanpeng.service.PushService;
import com.hanpeng.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {

    @Resource
    private LoginService service;

    @Resource
    private FaceRecognitionService faceRecognitionService;

    @Resource
    private PushService adminPushService;

    //百度人脸识别Token
    private static String accessToken;



    //登录验证
    @RequestMapping("login")
    void Login(HttpServletRequest request, HttpServletResponse response, String username, String password,
               Model model) {
        try {
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();
            List<Admin> list = service.login(username, password);
            if (list.isEmpty()) {
                out.write("{\"flag\":\"false\"}");
            } else {
                out.write("{\"flag\":\"true\"}");
                session.setAttribute("username", username);
            }
        } catch (Exception e) {
            model.addAttribute("info", "登录异常");
        }
    }

    //百度人脸识别登录验证
    @RequestMapping("faceRecognition")
    void faceRecognition(HttpServletRequest request, HttpServletResponse response, String baseData) throws IOException {
        PrintWriter writer = response.getWriter();
        HttpSession session = request.getSession();
        boolean flag = false;
        List<FaceRecognition> list = faceRecognitionService.getBaseList();
        for (FaceRecognition faceRecognition : list) {
            String image2 = faceRecognition.getFace_base64();
            if (faceRecognitionService.getResult(baseData, image2)) {
                flag = true;
                session.setAttribute("username", faceRecognition.getUsername());
                break;
            }
        }
        writer.print(flag);
        writer.close();
    }

    //百度人脸信息录入(返回的状态码：0：用户人脸数据存储已达上限，1：人脸信息录入成功， 2：人脸信息录入失败（照片不合格）)
    @RequestMapping("faceEntry")
    public void faceEntry(HttpServletRequest request, HttpServletResponse response, String faceData) throws IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        PrintWriter writer = response.getWriter();
        //检测该用户下的人脸信息数据是否超过5条
        if (faceRecognitionService.queryCountByFaceInfo(username,5)) {
            //进行人脸信息录入
            if (faceRecognitionService.getFaceResult(faceData)) {
                writer.write("{\"flag\":\"1\"}");
                FaceRecognition faceRecognition = new FaceRecognition(username, faceData);
                faceRecognitionService.insertFaceInfo(faceRecognition);
            }else{
                writer.write("{\"flag\":\"2\"}");
            }
        }else {
            writer.write("{\"flag\":\"0\"}");
        }
        writer.close();
    }

    //推送消息
    @RequestMapping("userPush")
    public ResultRespone userPush(Admin info){
        ResultRespone respone = new ResultRespone();
        try {
            adminPushService.push(info);
            respone.setData(info);
        } catch (Exception e) {
            e.printStackTrace();
            respone = new ResultRespone(false, e.getMessage());
        }
        return respone;
    }

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
