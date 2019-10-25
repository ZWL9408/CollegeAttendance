package com.attendance.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.attendance.action.personalAction;
import com.attendance.conn.MysqlConnect;

public class personalServlet extends HttpServlet {
	MysqlConnect mysqlconnect=new MysqlConnect();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");  
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8"); 
		HttpSession session = request.getSession(false);
		personalAction personalaction=new personalAction();
		String personal=request.getParameter("personal");
		int method=Integer.parseInt(personal);
        try {
        	switch(method){
        	case 0: personalaction.findTea(request,response);break;
        	case 1: personalaction.findStu(request,response);break;
        	case 2: personalaction.findUser(request, response);break;
        	}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
        
	}
}
