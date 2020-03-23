package com.huayu.mq;

import java.io.Serializable;

class User implements Serializable{
        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String loginName;
        private String pwd;
 
        public String getLoginName() {
            return loginName;
        }
 
        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }
 
        public String getPwd() {
            return pwd;
        }
 
        public void setPwd(String pwd) {
            this.pwd = pwd;
        }
    }
