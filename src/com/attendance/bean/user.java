package com.attendance.bean;

public class user {
private String username;		//姓名
private String password;	//密码
public user(){
	
}
public user(String username,String password){
	this.username=username;
	this.password=password;
}

public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
}
