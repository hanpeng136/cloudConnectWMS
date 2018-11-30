package com.hanpeng.serviceImpl;

import java.util.List;
import java.util.Map;

import com.hanpeng.service.RegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanpeng.utils.MD5;
import com.hanpeng.dao.RegistDao;

@Service
public class RegistServiceImpl implements RegistService {
	@Autowired
	RegistDao dao;

	@Override
	public boolean saveUser(String username, String password) throws Exception {
		if (dao.saveUser(username,MD5.getMD5String(password))==1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean validatePhone(String username) {
			if (dao.validatePhone(username) == 0) {
				return true;
			} else {
				return false;
			}
	}

	@Override
	public boolean updatePassword(String tel, String password) throws Exception {
		if (dao.updatePassword(tel,MD5.getMD5String(password))==1) {
			return true;
		} else {
			return false;
		}
	}

}
