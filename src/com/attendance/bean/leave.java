package com.attendance.bean;

public class leave {
	private int id;					//编号
	private String leave_person;	//请假人
	private String leave_time;		//请假时间
	private String start_time;		//开始时间
	private String leave_reason;	//请假理由
	public leave(int id,String lealeave_personve,String leave_time,String start_time,String leave_reason){
		this.id=id;
		this.leave_person=leave_person;
		this.leave_time=leave_time;
		this.start_time=start_time;
		this.leave_reason=leave_reason;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
}
