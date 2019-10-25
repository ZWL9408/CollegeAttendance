package com.attendance.bean;
//课程
public class course {
private String course_name; 		//课程名称
private String name;				//老师姓名
private String clazz;			//班级名称
public course(){
	
}
public course(String course_name,String name,String clazz){
	this.course_name=course_name;
	this.name=name;
	this.clazz=clazz;
}
public String getCourse_name() {
	return course_name;
}
public void setCourse_name(String course_name) {
	this.course_name = course_name;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getClazz() {
	return clazz;
}
public void setClazz(String clazz) {
	this.clazz = clazz;
}

}
