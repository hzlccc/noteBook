package com.huayu.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayu.bean.code.Code;
import com.huayu.bean.code.PCode;
import com.huayu.bean.user.Shgx;
import com.huayu.bean.user.User;
import com.huayu.user.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public List<User> getAllUsers() {
		System.out.println("请求到达user的service层");
		// TODO Auto-generated method stub
		return userMapper.getAllUsers();
	}

	public List<Shgx> getShgx(String userId) {
		// TODO Auto-generated method stub
		return userMapper.getShgx(userId);
	}

	public List<PCode> getPCode() {
		
		return userMapper.getPCode();
	}

	public List<Code> getCodeByPid(String cPid) {
		return userMapper.getCodeById(cPid);
	}

}
