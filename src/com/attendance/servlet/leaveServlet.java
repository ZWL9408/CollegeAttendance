package com.attendance.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.attendance.action.leaveAction;
import com.attendance.conn.MysqlConnect;

public class leaveServlet extends HttpServlet {
	MysqlConnect mysqlconnect=new MysqlConnect();
	leaveAction leaveaction=new leaveAction();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");
		String leave=request.getParameter("methodName");
		int method=Integer.parseInt(leave);
		try {
			switch(method){
			case 0:leaveaction.insert(request,response);break;
			case 1:leaveaction.difpage(request,response);break;
			case 2:leaveaction.delete(request,response);break;
			case 3:leaveaction.update(request,response);break;
			case 4:leaveaction.update1(request,response);break;
//			case 5:absenceaction.dispatch(request,response);break;
			}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
}

}
