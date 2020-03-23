package com.huayu.bean.user;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String CId;
	private String UserName;
	private String PassWord;
	private String RealName;
	private String Sex;
	private String CDesc;
	private String CProvince;
	private String CCity;
	private String CRegion;
	private String CAddress;
	private int DAge;
	private List<Shgx> shgx;
	private List<Srjl> srjl;
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
	public String getCDesc() {
		return CDesc;
	}
	public void setCDesc(String cDesc) {
		CDesc = cDesc;
	}
	public String getCProvince() {
		return CProvince;
	}
	public void setCProvince(String cProvince) {
		CProvince = cProvince;
	}
	public String getCCity() {
		return CCity;
	}
	public void setCCity(String cCity) {
		CCity = cCity;
	}
	public String getCRegion() {
		return CRegion;
	}
	public void setCRegion(String cRegion) {
		CRegion = cRegion;
	}
	public String getCAddress() {
		return CAddress;
	}
	public void setCAddress(String cAddress) {
		CAddress = cAddress;
	}
	public int getDAge() {
		return DAge;
	}
	public void setDAge(int dAge) {
		DAge = dAge;
	}
	public List<Shgx> getShgx() {
		return shgx;
	}
	public void setShgx(List<Shgx> shgx) {
		this.shgx = shgx;
	}
	public List<Srjl> getSrjl() {
		return srjl;
	}
	public void setSrjl(List<Srjl> srjl) {
		this.srjl = srjl;
	}
	@Override
	public String toString() {
		return "User [CId=" + CId + ", UserName=" + UserName + ", PassWord=" + PassWord + ", RealName=" + RealName
				+ ", Sex=" + Sex + ", CDesc=" + CDesc + ", CProvince=" + CProvince + ", CCity=" + CCity + ", CRegion="
				+ CRegion + ", CAddress=" + CAddress + ", DAge=" + DAge + ", shgx=" + shgx + ", srjl=" + srjl + "]";
	}
	
	
}
