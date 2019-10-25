package com.attendance.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.attendance.action.userAction;

import com.attendance.conn.MysqlConnect;

public class userServlet extends HttpServlet {
	MysqlConnect mysqlconnect=new MysqlConnect();
	userAction useraction=new userAction();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");
		String user=request.getParameter("User");
		int method=Integer.parseInt(user);
		try {
			switch(method){
			case 0:useraction.insert(request,response);break;
			case 1:useraction.difpage(request,response);break;
			case 2:useraction.delete(request,response);break;
			case 3:useraction.update(request,response);break;
			case 4:useraction.update1(request,response);break;
//			case 5:absenceaction.dispatch(request,response);break;
			}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

}
