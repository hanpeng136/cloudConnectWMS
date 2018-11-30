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
            JSONObject myJson = new JSONObject(result);
            int error = myJson.getInt("error_code");
            if (error == 0) {
                JSONObject resultList = (JSONObject) myJson.get("result");
                double score = resultList.getDouble("score");
                System.out.println(score);
                if (score >= 90) {
                    flag = true;
                }
            }
                return flag;
            } catch(Exception e){
                e.printStackTrace();
            }
        return flag;
    }

    //人脸信息录入
    @Override
    public boolean getFaceResult(String image) throws IOException {
        boolean flag = false;
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", image);
            map.put("face_field", "faceshape,facetype");
            map.put("image_type", "BASE64");

            String param = GsonUtils.toJson(map);
            String accessToken = GetToken.getToken();
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            JSONObject myJson = new JSONObject(result);
            int error = myJson.getInt("error_code");
            if(error==0){
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    //新增人脸数据
    @Override
    public void insertFaceInfo(FaceRecognition faceRecognition) {
        dao.insertFaceInfo(faceRecognition);
    }

    //检测该用户的人脸信息数据是否大于N
    @Override
    public boolean queryCountByFaceInfo(String username,int n) {
        if(dao.queryCountByFaceInfo(username)<=4){
            return true;
        }
        return false;
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
