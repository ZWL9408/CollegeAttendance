package com.attendance.bean;
//老师
public class teacher {
private String name;		//姓名 
private String gender;		//性别
private String password;	//密码
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public teacher(){
	
}
public teacher(String name,String gender, String password){
	this.name=name;
	this.gender=gender;
	this.password=password;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
}
