package com.attendance.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.attendance.action.pwdmodifyAction;
import com.attendance.conn.MysqlConnect;

public class pwdmodifyServlet extends HttpServlet {
	MysqlConnect mysqlconnect=new MysqlConnect();
	pwdmodifyAction pwdmodifyaction=new pwdmodifyAction();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String pwdmofify=request.getParameter("pwdmofify");
		int method=Integer.parseInt(pwdmofify);
		try {
			switch(method){
			case 0:pwdmodifyaction.stuupdate(request,response);break;
			case 1:pwdmodifyaction.teaupdate(request,response);break;
			case 2:pwdmodifyaction.adminupdate(request,response);break;
			}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

}
