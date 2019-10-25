package com.attendance.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.attendance.action.att_studentAction;

import com.attendance.conn.MysqlConnect;

public class att_studentServlet extends HttpServlet {
	MysqlConnect mysqlconnect=new MysqlConnect();
	att_studentAction att_studentaction=new att_studentAction();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String att_student=request.getParameter("Att_student");
		int method=Integer.parseInt(att_student);
		try {
			switch(method){
			case 0:att_studentaction.insert(request,response);break;
			case 1:att_studentaction.difpage(request,response);break;
			case 2:att_studentaction.delete(request,response);break;
			case 3:att_studentaction.update(request,response);break;
			case 4:att_studentaction.update1(request,response);break;
//			case 5:absenceaction.dispatch(request,response);break;
			}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
