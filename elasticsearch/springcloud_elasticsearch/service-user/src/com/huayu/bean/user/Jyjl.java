package com.huayu.bean.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 教育经历
 * @author huzhi
 *
 */
public class Jyjl implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String CId;
	private String CUserId;
	private Date DStart;
	private Date DEnd;
	//文化程度
	private String CWhcd;
	
	private String CDz;

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

	public Date getDStart() {
		return DStart;
	}

	public void setDStart(Date dStart) {
		DStart = dStart;
	}

	public Date getDEnd() {
		return DEnd;
	}

	public void setDEnd(Date dEnd) {
		DEnd = dEnd;
	}

	public String getCWhcd() {
		return CWhcd;
	}

	public void setCWhcd(String cWhcd) {
		CWhcd = cWhcd;
	}

	public String getCDz() {
		return CDz;
	}

	public void setCDz(String cDz) {
		CDz = cDz;
	}

	@Override
	public String toString() {
		return "Jyjl [CId=" + CId + ", CUserId=" + CUserId + ", DStart=" + DStart + ", DEnd=" + DEnd + ", CWhcd="
				+ CWhcd + ", CDz=" + CDz + "]";
	}
	
	
}
