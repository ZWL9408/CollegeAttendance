package com.attendance.bean;

public class clazz {
private int clazz_id;
private String clazz;
private int clazz_number;
public clazz(){}

public clazz(int clazz_id,String clazz,int clazz_number){
	this.clazz_id=clazz_id;
	this.clazz=clazz;
	this.clazz_number=clazz_number;
}

public int getClazz_id() {
	return clazz_id;
}

public void setClazz_id(int clazz_id) {
	this.clazz_id = clazz_id;
}

public int getClazz_number() {
	return clazz_number;
}

public void setClazz_number(int clazz_number) {
	this.clazz_number = clazz_number;
}

public String getClazz() {
	return clazz;
}

public void setClazz(String clazz) {
	this.clazz = clazz;
}



}
