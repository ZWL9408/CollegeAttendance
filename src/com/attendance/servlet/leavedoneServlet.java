package com.attendance.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.attendance.action.leavedoneAction;
import com.attendance.conn.MysqlConnect;

public class leavedoneServlet extends HttpServlet {
	MysqlConnect mysqlconnect=new MysqlConnect();
	leavedoneAction leavedoneaction=new leavedoneAction();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	String leave=request.getParameter("methodName");
	int method=Integer.parseInt(leave);
	try {
		switch(method){
		case 1:leavedoneaction.difpage(request,response);break;
		}
	}catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	} 
	}

}
