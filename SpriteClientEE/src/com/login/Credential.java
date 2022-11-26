package com.login;

public class Credential {
	private String usrpwd;
	private String credential;
	private boolean goodToGo = false;
	
	public boolean isGoodToGo() {
		return goodToGo;
	}
	public void setGoodToGo(boolean goodToGo) {
		this.goodToGo = goodToGo;
	}
	public String getUsrpwd() {
		return usrpwd;
	}
	public void setUsrpwd(String usrpwd) {
		this.usrpwd = usrpwd;
	}
	public String getCredential() {
		return credential;
	}
	public void setCredential(String credential) {
		this.credential = credential;
	}
	
}
