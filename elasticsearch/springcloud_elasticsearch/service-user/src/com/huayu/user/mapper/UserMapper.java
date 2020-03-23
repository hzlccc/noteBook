package com.huayu.user.mapper;

import java.util.List;

import com.huayu.bean.code.Code;
import com.huayu.bean.code.PCode;
import com.huayu.bean.user.Shgx;
import com.huayu.bean.user.Srjl;
import com.huayu.bean.user.User;

public interface UserMapper {

	List<User> getAllUsers();

	List<Shgx> getShgx(String userId);


	void insertUser(List<User> users);

	void insertShgx(List<Shgx> shgxs);

	List<PCode> getPCode();

	List<Code> getCodeById(String pid);

	void insertSrjl(List<Srjl> srjls);

	List<Srjl> getSrjl(String cId);

	User getUserById(String userId);

	List<Shgx> getAllShgx();

	List<Srjl> getAllSrjl();

}
