package com.huayu.eureka.bean;

import java.util.List;

public class User {

	private String CId;
	private String UserName;
	private String PassWord;
	private String RealName;
	private String Sex;
	private String Desc;
	private List<Shgx> shgx;
	public String getCId() {
		return CId;
	}
	public void setCId(String cId) {
		CId = cId;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassWord() {
		return PassWord;
	}
	public void setPassWord(String passWord) {
		PassWord = passWord;
	}
	public String getRealName() {
		return RealName;
	}
	public void setRealName(String realName) {
		RealName = realName;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getDesc() {
		return Desc;
	}
	public void setDesc(String desc) {
		Desc = desc;
	}
	public List<Shgx> getShgx() {
		return shgx;
	}
	public void setShgx(List<Shgx> shgx) {
		this.shgx = shgx;
	}
	@Override
	public String toString() {
		return "User [CId=" + CId + ", UserName=" + UserName + ", PassWord=" + PassWord + ", RealName=" + RealName
				+ ", Sex=" + Sex + ", Desc=" + Desc + ", shgx=" + shgx + "]";
	}
	
	
}
