package com.attendance.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.attendance.action.absencecheckAction;
import com.attendance.conn.MysqlConnect;

public class absencecheckServlet extends HttpServlet{
	MysqlConnect mysqlconnect=new MysqlConnect();
	absencecheckAction absencecheckaction=new absencecheckAction();
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
				case 0:absencecheckaction.insert(request,response);break;
				case 1:absencecheckaction.difpage(request,response);break;
				case 2:absencecheckaction.delete(request,response);break;
				case 3:absencecheckaction.update(request,response);break;
				case 4:absencecheckaction.update1(request,response);break;
//				case 5:absenceaction.dispatch(request,response);break;
				}
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			
	}
}
