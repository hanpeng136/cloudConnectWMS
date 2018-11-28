package com.hanpeng.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hanpeng.bean.Admin;
import com.hanpeng.bean.FaceRecognition;
import com.hanpeng.service.LoginService;
import com.hanpeng.utils.GetToken;
import com.hanpeng.utils.GsonUtils;
import com.hanpeng.utils.HttpUtil;
import org.json.JSONArray;
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
    public List<Admin> Login(String username, String password) {
        try {
            password = MD5.getMD5String(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("password:" + password);
        return dao.queryByNameAndPassword(username, password);
    }

    //人脸识别数据获取
    @Override
    public List<FaceRecognition> getBaseList() {
        List<FaceRecognition> list = new ArrayList<FaceRecognition>();
        list = dao.getBaseList();
        return list;
    }

    //人脸识别检测
    @Override
    public boolean getResult(String image1, String image2) throws IOException {
        boolean flag = false;
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
//		byte[] bytes1 = FileUtil.readFileByBytes("C:/Users/hanpeng42/Desktop/爱唱音乐共享平台/IMG_20181115_191540 (2).jpg");
//		byte[] bytes2 = FileUtil.readFileByBytes("C:/Users/hanpeng42/Desktop/1536975445658.jpg");
//		String image1 = Base64Util.encode(bytes1);
//		String image2 = Base64Util.encode(bytes2);
//
//		System.out.println(image1);
//		System.out.println(image2);
        try {
            List<Map<String, Object>> images = new ArrayList<>();
            Map<String, Object> map1 = new HashMap<>();
            map1.put("image", image1);
            map1.put("image_type", "BASE64");
            map1.put("face_type", "LIVE");
            map1.put("quality_control", "LOW");
            map1.put("liveness_control", "NORMAL");

            Map<String, Object> map2 = new HashMap<>();
            map2.put("image", image2);
            map2.put("image_type", "BASE64");
            map2.put("face_type", "LIVE");
            map2.put("quality_control", "LOW");
            map2.put("liveness_control", "NORMAL");

            images.add(map1);
            images.add(map2);

            String param = GsonUtils.toJson(images);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = GetToken.getToken();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            //用json提取result中的有效数据
//			JSONObject fromObject = JSONObject.fromObject(result);
////			JSONArray jsonArray = fromObject.getJSONArray("result");
//			JSONArray json = JSONArray.fromObject(fromObject);
//			JSONObject object = (JSONObject) json.get(0);
//			System.out.println(object.getString("result"));
//			for (int i = 0; i < jsonArray.size(); i++) {
//				JSONObject object = (JSONObject) jsonArray.get(i);
//				double resultList = object.getDouble("score");
//				if (resultList >= 90) {
//					flag = true;
//				}
//			}
            JSONObject myJson = new JSONObject(result);
            JSONObject resultList = (JSONObject) myJson.get("result");
            double score = resultList.getDouble("score");
            System.out.println(score);
            if (score >= 90) {
                flag = true;
            }
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
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
