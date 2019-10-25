package com.attendance.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;

import com.attendance.action.namecheckAction;
import com.attendance.conn.MysqlConnect;

public class namecheckServlet extends HttpServlet {
	MysqlConnect mysqlconnect=new MysqlConnect();
	namecheckAction namecheckaction=new namecheckAction();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		 response.setCharacterEncoding("UTF-8");
//		 HttpSession session = request.getSession();
//		 String clazz=request.getParameter("clazz");
//		 session.setAttribute("clazz",clazz);
		 String showstu=request.getParameter("check");
		 int met=Integer.parseInt(showstu);
		 try {
				switch(met){
//				case 1:clazzaction.difpageclazz(request,response);break;
				case 2:namecheckaction.difpage(request, response);break;
				case 3:namecheckaction.insert(request, response);break;
				}
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
	}
}
