package com.attendance.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.attendance.action.clazzAction;
import com.attendance.conn.MysqlConnect;

public class clazzServlet extends HttpServlet {
	MysqlConnect mysqlconnect=new MysqlConnect();
	clazzAction clazzaction=new clazzAction();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		 response.setCharacterEncoding("UTF-8");
		 String clazz=request.getParameter("clazz_method");
		 int method=Integer.parseInt(clazz);
		 try {
				switch(method){
				case 1:clazzaction.difpageclazz(request,response);break;
//				case 2:clazzaction.difpagestudent(request,response);break;
				}
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		 
	}

}
