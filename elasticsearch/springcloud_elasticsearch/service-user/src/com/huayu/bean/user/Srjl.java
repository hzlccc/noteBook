package com.huayu.bean.user;

import java.io.Serializable;

public class Srjl implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String CID;
	private String CUserID;
	private double DSrje;
	public String getCID() {
		return CID;
	}
	public void setCID(String cID) {
		CID = cID;
	}
	public String getCUserID() {
		return CUserID;
	}
	public void setCUserID(String cUserID) {
		CUserID = cUserID;
	}
	public double getDSrje() {
		return DSrje;
	}
	public void setDSrje(double dSrje) {
		DSrje = dSrje;
	}
	@Override
	public String toString() {
		return "Srjl [CID=" + CID + ", CUserID=" + CUserID + ", DSrje=" + DSrje + "]";
	}
	
	
	
}
