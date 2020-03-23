package com.huayu.eureka.bean;

public class Shgx {

	private String CId;
	private String CUserId;
	private String CName;
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
	public String getCName() {
		return CName;
	}
	public void setCName(String cName) {
		CName = cName;
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
		return "Shgx [CId=" + CId + ", CUserId=" + CUserId + ", CName=" + CName + ", CSex=" + CSex + ", DAge=" + DAge
				+ ", CAddress=" + CAddress + "]";
	}
	
}
