package com.attendance.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.attendance.action.showstudentAction;
import com.attendance.conn.MysqlConnect;

public class showstudentServlet extends HttpServlet {
	MysqlConnect mysqlconnect=new MysqlConnect();
	showstudentAction showstudentaction=new showstudentAction();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		 response.setCharacterEncoding("UTF-8");
		 String showstu=request.getParameter("showstu");
		 int method=Integer.parseInt(showstu);
		 try {
				switch(method){
//				case 1:clazzaction.difpageclazz(request,response);break;
				case 2:showstudentaction.findStu(request,response);break;
				}
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
	}
}
