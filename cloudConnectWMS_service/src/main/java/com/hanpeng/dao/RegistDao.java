package com.hanpeng.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RegistDao {
	public int saveUser(@Param("username") String username, @Param("password")String password);
	public int validatePhone(@Param("username") String username);
	public int updatePassword(@Param("username") String username, @Param("password")String password);
}
