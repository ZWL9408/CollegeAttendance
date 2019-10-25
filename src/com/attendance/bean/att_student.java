package com.attendance.bean;
//学生考勤表
public class att_student {
private int student_id;
private String student_name;//课程名称
private String course_name;
private String att_time;//上课时间
private String operation;//出勤情况
public att_student(){
	
}
public att_student(int student_id,String student_name,String course_name,String att_time,String operation){
	this.student_id=student_id;
	this.student_name=student_name;
	this.course_name=course_name;
	this.att_time=att_time;
	this.operation=operation;
}

public String getCourse_name() {
	return course_name;
}
public void setCourse_name(String course_name) {
	this.course_name = course_name;
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

public String getAtt_time() {
	return att_time;
}
public void setAtt_time(String att_time) {
	this.att_time = att_time;
}
public String getOperation() {
	return operation;
}
public void setOperation(String operation) {
	this.operation = operation;
}


}
