package com.huayu.bean.user;

import java.io.Serializable;

public class Shgx implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String CId;
	private String CUserId;
	private int DLb;
	private String CName;
	private String CSfzh;
	private String CSex;
	private int DAge;
	private String CAddress;
	public String getCId() {
		return CId;
	}
	public void setCId(String cId) {
		CId = cId;
	}
	public String getCUserId() {
		return CUserId;
	}
	public void setCUserId(String cUserId) {
		CUserId = cUserId;
	}
	public int getDLb() {
		return DLb;
	}
	public void setDLb(int dLb) {
		DLb = dLb;
	}
	public String getCName() {
		return CName;
	}
	public void setCName(String cName) {
		CName = cName;
	}
	public String getCSfzh() {
		return CSfzh;
	}
	public void setCSfzh(String cSfzh) {
		CSfzh = cSfzh;
	}
	public String getCSex() {
		return CSex;
	}
	public void setCSex(String cSex) {
		CSex = cSex;
	}
	public int getDAge() {
		return DAge;
	}
	public void setDAge(int dAge) {
		DAge = dAge;
	}
	public String getCAddress() {
		return CAddress;
	}
	public void setCAddress(String cAddress) {
		CAddress = cAddress;
	}
	@Override
	public String toString() {
		return "Shgx [CId=" + CId + ", CUserId=" + CUserId + ", DLb=" + DLb + ", CName=" + CName + ", CSfzh=" + CSfzh
				+ ", CSex=" + CSex + ", DAge=" + DAge + ", CAddress=" + CAddress + "]";
	}
	
	
}
