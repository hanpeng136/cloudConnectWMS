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
@SessionAttributes(value = { "userInfo", "menus" })
public class LoginController {

    @Resource
    private LoginService service;

    //百度人脸识别Token
    private static String accessToken;


	//登录验证
	@RequestMapping("login")
	void Login(HttpServletRequest request, HttpServletResponse response, String username, String password,
			Model model) {
		try {
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			List<Admin> list = service.Login(username, password);
			if (list.isEmpty()) {
				out.write("{\"flag\":\"false\"}");
			} else {
				out.write("{\"flag\":\"true\"}");
//				String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//				System.out.println(date);
//				service.upDate(date, username);
//				HttpSession hs = request.getSession();
				session.setAttribute("username", username);
//				System.out.println("username已传送");
//				List<Map<String, ?>> userInfo = service.userInfo(username);
//				hs.setAttribute("imgstr", userInfo.get(0).get("imgstr"));
//				System.out.println("图片地址已传送");
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
		List<FaceRecognition> list = service.getBaseList();
        for(FaceRecognition faceRecognition:list){
			String image2 = faceRecognition.getFace_base64();
			if(service.getResult(baseData, image2)){
				flag = true;
				session.setAttribute("username",faceRecognition.getUsername());
				break;
			}
		}
		writer.print(flag);
        writer.close();
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
