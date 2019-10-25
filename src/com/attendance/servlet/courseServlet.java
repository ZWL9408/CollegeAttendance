package com.attendance.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.attendance.action.courseAction;

import com.attendance.conn.MysqlConnect;

public class courseServlet extends HttpServlet {
	MysqlConnect mysqlconnect=new MysqlConnect();
	courseAction courseaction=new courseAction();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		 response.setCharacterEncoding("UTF-8");
		 String course=request.getParameter("Course");
		 int method=Integer.parseInt(course);
			try {
				switch(method){
				case 0:courseaction.insert(request,response);break;
				case 1:courseaction.difpage(request,response);break;
				case 2:courseaction.delete(request,response);break;
				case 3:courseaction.update(request,response);break;
				case 4:courseaction.update1(request,response);break;
//				case 5:absenceaction.dispatch(request,response);break;
				}
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		
	}

}
