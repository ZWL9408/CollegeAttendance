package com.attendance.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.attendance.action.studentsAction;

import com.attendance.conn.MysqlConnect;

public class studentsServlet extends HttpServlet {
	MysqlConnect mysqlconnect=new MysqlConnect();
	studentsAction studentsaction=new studentsAction();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 	request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");
			String students=request.getParameter("Students");
			int method=Integer.parseInt(students);
			try {
				switch(method){
				case 0:studentsaction.insert(request,response);break;
				case 1:studentsaction.difpage(request,response);break;
				case 2:studentsaction.delete(request,response);break;
				case 3:studentsaction.update(request,response);break;
				case 4:studentsaction.update1(request,response);break;
//				case 5:studentsaction.difpage1(request,response);break;
				}
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
	}

}
