package com.hanpeng.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.hanpeng.bean.Admin;
import com.hanpeng.bean.FaceRecognition;
import org.apache.ibatis.annotations.Param;

public interface LoginDao {
	List<Admin> queryByNameAndPassword(@Param("username") String username, @Param("password") String password);

	List<FaceRecognition> getBaseList();

	List<Map<String, ?>> queryUserInfo(@Param("userName") String userName);

	void upDate(@Param("date") String date, @Param("userName") String userName);

	void modifyPassWord(@Param("userName") String userName, @Param("passWord") String passWord);
}
