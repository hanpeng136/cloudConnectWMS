package com.hanpeng.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanpeng.utils.Base64;
import com.hanpeng.service.LoginService;
import com.hanpeng.service.RegistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;


@Controller
public class RegistController {
	@Resource
	RegistService service;


	// 产品名称:云通信短信API产品,开发者无需替换
	static final String product = "Dysmsapi";
	// 产品域名,开发者无需替换
	static final String domain = "dysmsapi.aliyuncs.com";

	// TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
	static final String accessKeyId = "LTAIedlGtyRSrxt2";
	static final String accessKeySecret = "WZxkO4m1GhiUHfJBVM6iTBxkAqctln";

	public static SendSmsResponse sendSms(String tel, String number) throws ClientException {

		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号
		request.setPhoneNumbers(tel);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName("爱唱音乐共享平台");
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode("SMS_125117879");
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		request.setTemplateParam(number);
		// "{\"name\":\"Tom\", \"code\":\"888888\"}"
		// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");

		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId("yourOutId");

		// hint 此处可能会抛出异常，注意catch
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

		return sendSmsResponse;
	}

	// 发送验证码
	@RequestMapping("sentCode")
	void sentCode(HttpServletRequest request, HttpServletResponse response, String username) throws IOException {
		System.out.println(request.getParameter("tel"));
		System.out.println(username);
		try {
			int number;
			PrintWriter out = response.getWriter();
			// 生成随机数
			double num = 1 + (int) (Math.random() * 999999);
			number = (int) num;
			// 转成json格式
			String telNumber = "{" + "\"" + "name" + "\"" + ":" + "\"" + "hanfeng" + "\"" + "," + " " + "\"" + "code"
					+ "\"" + ":" + "\"" + number + "\"" + "}";
			// 调用阿里云接口发送短信
			SendSmsResponse mySend = sendSms(username, telNumber);
			System.out.println("短信接口返回的数据----------------");
			System.out.println("Code=" + mySend.getCode());
			System.out.println("Message=" + mySend.getMessage());
			System.out.println("RequestId=" + mySend.getRequestId());
			System.out.println("BizId=" + mySend.getBizId());
			// 验证是否发送成功，如果发送成功则返回true并存入session，否则返回false
			if (mySend.getCode().equals("OK")) {
				out.write("{\"flag\":\"true\"}");
				HttpSession hs = request.getSession();
				hs.setAttribute("number", number);
				System.out.println(number);
			} else {
				out.write("{\"flag\":\"false\"}");
			}
			out.flush();
			out.close();
		} catch (Exception e) {

		}
	}

	// 验证码校验
	@RequestMapping("validateCode")
	void validateCode(HttpServletRequest request, HttpServletResponse response, String code) {
		try {
			boolean isDigit = true;
			int number2;
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			// 验证码校验
			// 检验用户输入的验证码是否合法
			int number = (Integer) session.getAttribute("number");
			for (int i = 0; i < code.length(); i++) {
				if (!Character.isDigit(code.charAt(i))) {
					isDigit = false;
				}
			}
			// 如果合法便将其转化为Int型
			if (isDigit) {
				number2 = Integer.parseInt(code);
			} else {
				number2 = 0;
			}
			// 验证码校验
			if (number == number2) {
				out.write("{\"flag\":\"true\"}");
			} else {
				out.write("{\"flag\":\"false\"}");
			}
			out.flush();
			out.close();
		} catch (Exception e) {

		}
	}

	// 手机号码唯一性检验
	@RequestMapping("validatePhone")
	void validatePhone(HttpServletRequest request, HttpServletResponse response, String username) {
		try {
			PrintWriter out = response.getWriter();
			if (service.validatePhone(username)) {
				out.write("{\"flag\":\"true\"}");
			} else {
				out.write("{\"flag\":\"false\"}");
			}
			out.flush();
			out.close();
		} catch (Exception e) {

		}
	}

	// 保存注册用户
	@RequestMapping("saveUser")
	void saveUser(HttpServletRequest request, HttpServletResponse response, String username, String password) {
		try {
			PrintWriter out = response.getWriter();
			if (service.saveUser(username, password)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				out.write("{\"flag\":\"true\"}");
			} else {
				out.write("{\"flag\":\"false\"}");
			}
			out.flush();
			out.close();
		} catch (Exception e) {

		}
	}

	//忘记密码
	@RequestMapping("updateUser")
	void updateUser(HttpServletRequest request, HttpServletResponse response, String username, String password) {
		System.out.println("已执行忘记密码");
		try {
			PrintWriter out = response.getWriter();
			if (service.updatePassword(username, password)) {
				out.write("{\"flag\":\"true\"}");
			} else {
				out.write("{\"flag\":\"false\"}");
			}
			out.flush();
			out.close();
		} catch (Exception e) {

		}
	}

}
