package org.soalr.model;

public class User {
	private String uName;
	private String uAge;
	private String uStatus;
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuAge() {
		return uAge;
	}
	public void setuAge(String uAge) {
		this.uAge = uAge;
	}
	public String getuStatus() {
		return uStatus;
	}
	public void setuStatus(String uStatus) {
		this.uStatus = uStatus;
	}
	
	public void intro() {
		System.out.println("Name: " + this.uName);
		System.out.println("Age: " + this.uAge);
		System.out.println("Status: " + this.uStatus);
	}
	
}
