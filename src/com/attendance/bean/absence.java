package com.attendance.bean;
//请假
public class absence {
private int student_id;			//编号
private String leave_person;	//请假人
private String leave_time;		//请假时间
private String start_time;		//开始时间
private String leave_reason;	//请假理由
private String status;			//状态
private String deal_time;		//处理时间
private String deal_person;		//处理人	
private String deal_result;		//处理结果
public absence(){
	
}
public absence(int student_id,String leave_person,String leave_time,String start_time,String leave_reason,String status,String deal_time,String deal_person,String deal_result){
	this.student_id=student_id;
	this.leave_person=leave_person;
	this.leave_time=leave_time;
	this.start_time=start_time;
	this.leave_reason=leave_reason;
	this.status=status;
	this.deal_time=deal_time;
	this.deal_person=deal_person;
	this.deal_result=deal_result;
}

public int getStudent_id() {
	return student_id;
}
public void setStudent_id(int student_id) {
	this.student_id = student_id;
}
public String getLeave_person() {
	return leave_person;
}
public void setLeave_person(String leave_person) {
	this.leave_person = leave_person;
}
public String getLeave_time() {
	return leave_time;
}
public void setLeave_time(String leave_time) {
	this.leave_time = leave_time;
}
public String getStart_time() {
	return start_time;
}
public void setStart_time(String start_time) {
	this.start_time = start_time;
}
public String getLeave_reason() {
	return leave_reason;
}
public void setLeave_reason(String leave_reason) {
	this.leave_reason = leave_reason;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getDeal_time() {
	return deal_time;
}
public void setDeal_time(String deal_time) {
	this.deal_time = deal_time;
}
public String getDeal_person() {
	return deal_person;
}
public void setDeal_person(String deal_person) {
	this.deal_person = deal_person;
}
public String getDeal_result() {
	return deal_result;
}
public void setDeal_result(String deal_result) {
	this.deal_result = deal_result;
}

}
