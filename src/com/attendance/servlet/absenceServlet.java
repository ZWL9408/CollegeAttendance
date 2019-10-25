package com.attendance.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.attendance.action.absenceAction;
import com.attendance.conn.*;

import com.attendance.conn.MysqlConnect;

public class absenceServlet extends HttpServlet {
	MysqlConnect mysqlconnect=new MysqlConnect();
	absenceAction absenceaction=new absenceAction();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    request.setCharacterEncoding("UTF-8");
   		    response.setCharacterEncoding("UTF-8");
			String absence=request.getParameter("methodName");
			int method=Integer.parseInt(absence);
			try {
				switch(method){
				case 0:absenceaction.insert(request,response);break;
				case 1:absenceaction.difpage(request,response);break;
				case 2:absenceaction.delete(request,response);break;
				case 3:absenceaction.update(request,response);break;
				case 4:absenceaction.update1(request,response);break;
//				case 5:absenceaction.dispatch(request,response);break;
				}
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			
	}

}
