package com.attendance.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.attendance.action.clazzchooseAction;
import com.attendance.conn.MysqlConnect;

public class clazzChooseServlet extends HttpServlet {
	MysqlConnect mysqlconnect=new MysqlConnect();
	clazzchooseAction clazzchooseaction=new clazzchooseAction();
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
		String clazzchoose=request.getParameter("clazzchoose");
		int method=Integer.parseInt(clazzchoose);
		 try {
	        	switch(method){
	        	case 1: clazzchooseaction.findStu(request,response);break;
	        	}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
	        
	}

}
