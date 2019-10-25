package com.attendance.bean;

public class co_time {
private String course_name;
private String att_time;
public co_time(){}
public co_time(String course_name,String att_time){
	this.course_name=course_name;
	this.att_time=att_time;
}
public String getCourse_name() {
	return course_name;
}
public void setCourse_name(String course_name) {
	this.course_name = course_name;
}
public String getAtt_time() {
	return att_time;
}
public void setAtt_time(String att_time) {
	this.att_time = att_time;
}

}
