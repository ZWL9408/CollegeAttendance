package com.attendance.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.attendance.conn.MysqlConnect;

public class pwdmodifyAction {
	MysqlConnect mysqlconnect=new MysqlConnect();
    //学生密码修改
    public void stuupdate(HttpServletRequest request, HttpServletResponse response) 
    throws ClassNotFoundException, SQLException, ServletException, IOException{
    	Connection conn=null;
    	Statement stat=null;
		String student_name=request.getParameter("student_name");
		String password=request.getParameter("password");
    	conn=mysqlconnect.connect();
 		stat=conn.createStatement();
 		stat.execute("update students set password='"+password+"'where student_name='"+student_name+"'");
 		request.getRequestDispatcher("index.jsp").forward(request, response);
    } 
    //教师密码修改
    public void teaupdate(HttpServletRequest request, HttpServletResponse response) 
    throws ClassNotFoundException, SQLException, ServletException, IOException{
    	Connection conn=null;
    	Statement stat=null;
		String name=request.getParameter("name");
		String password=request.getParameter("password");
    	conn=mysqlconnect.connect();
 		stat=conn.createStatement();
 		stat.execute("update teacher set password='"+password+"'where name='"+name+"'");
 		request.getRequestDispatcher("index.jsp").forward(request, response);
    } 
    public void adminupdate(HttpServletRequest request, HttpServletResponse response) 
    throws ClassNotFoundException, SQLException, ServletException, IOException{
    	Connection conn=null;
    	Statement stat=null;
		String username=request.getParameter("username");
		String password=request.getParameter("password");
    	conn=mysqlconnect.connect();
 		stat=conn.createStatement();
 		stat.execute("update user set password='"+password+"'where username='"+username+"'");
 		request.getRequestDispatcher("index.jsp").forward(request, response);
    } 
}
