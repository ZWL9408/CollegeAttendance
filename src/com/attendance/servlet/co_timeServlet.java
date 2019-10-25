package com.attendance.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.attendance.action.co_timeAction;
import com.attendance.conn.MysqlConnect;

public class co_timeServlet extends HttpServlet {
	MysqlConnect mysqlconnect=new MysqlConnect();
	co_timeAction Test=new co_timeAction();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");  
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8"); 
		HttpSession session = request.getSession(false);
		String co_time=request.getParameter("co_time");
		int method=Integer.parseInt(co_time);
		 try {
	        	switch(method){
	        	case 1: Test.findStu(request,response);break;
	        	}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
	        
	}
}
