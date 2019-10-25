package com.attendance.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.attendance.action.teacherAction;

import com.attendance.conn.MysqlConnect;

public class teacherServlet extends HttpServlet {
	MysqlConnect mysqlconnect=new MysqlConnect();
	teacherAction teacheraction=new teacherAction();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String teacher=request.getParameter("Teacher");
		int method=Integer.parseInt(teacher);
		try {
			switch(method){
			case 0:teacheraction.insert(request,response);break;
			case 1:teacheraction.difpage(request,response);break;
			case 2:teacheraction.delete(request,response);break;
			case 3:teacheraction.update(request,response);break;
			case 4:teacheraction.update1(request,response);break;
//			case 5:absenceaction.dispatch(request,response);break;
			}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

}
