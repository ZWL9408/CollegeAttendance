package com.attendance.bean;
//学生基本信息
public class students {
private int student_id;					//学号
private String student_name; 	//姓名
private String  gender;			//性别
private String clazz;			//班级
private String password;
public students(){
	
}
public students(int student_id,String student_name,String  gender,String clazz,String password){
	this.student_id=student_id;
	this.student_name=student_name;
	this.gender=gender;
	this.clazz=clazz;
	this.password=password;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

public int getStudent_id() {
	return student_id;
}
public void setStudent_id(int student_id) {
	this.student_id = student_id;
}

public String getStudent_name() {
	return student_name;
}
public void setStudent_name(String student_name) {
	this.student_name = student_name;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getClazz() {
	return clazz;
}
public void setClazz(String clazz) {
	this.clazz = clazz;
}

}
