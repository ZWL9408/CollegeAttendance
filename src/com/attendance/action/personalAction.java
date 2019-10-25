package com.attendance.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.attendance.bean.students;
import com.attendance.bean.teacher;
import com.attendance.bean.user;
import com.attendance.conn.MysqlConnect;

public class personalAction {
	MysqlConnect mysqlconnect=new MysqlConnect();
	public ArrayList<teacher> selectTea(String name)throws ClassNotFoundException, SQLException{
    	Connection conn=null;
    	Statement stat=null;
	    ResultSet rs=null;
	    conn=mysqlconnect.connect();
		stat=conn.createStatement();
		ArrayList<teacher> result=new ArrayList<teacher>();
		rs=stat.executeQuery("select * from teacher where name='"+name+"'");
		while(rs.next()){
			teacher Teacher = new teacher(); 
			Teacher.setName(rs.getString("name"));
			Teacher.setGender(rs.getString("gender"));
			Teacher.setPassword(rs.getString("password"));
			result.add(Teacher);
		}
		if(rs!=null){
			rs.close();
		}
		mysqlconnect.close(stat,conn);
    	return result;
    	
    }
    
    public void findTea(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
    	Connection conn=null;
	    Statement stat=null;
	    conn=mysqlconnect.connect();
	 	stat=conn.createStatement();
	 	HttpSession session = request.getSession(false);
		String name = (String)session.getAttribute("user");
//	 	String name=request.getParameter("username");
	 	request.setAttribute("tearesult", selectTea(name));
	 	request.getRequestDispatcher("/views/master/teacher_infor.jsp").forward(request, response);
    }
    
    public ArrayList<students> selectStu(String student_name)throws ClassNotFoundException, SQLException{
		Connection conn=null;
    	Statement stat=null;
	    ResultSet rs=null;
	    conn=mysqlconnect.connect();
		stat=conn.createStatement();
		ArrayList<students> result=new ArrayList<students>();
		if(student_name!=""){
			rs=stat.executeQuery("select * from students where student_name='"+student_name+"'");
		}
		while(rs.next()){
			students Students = new students(); 
			Students.setStudent_id(rs.getInt("student_id"));
			Students.setStudent_name(rs.getString("student_name"));
			Students.setGender(rs.getString("gender"));
			Students.setClazz(rs.getString("clazz"));
			Students.setPassword(rs.getString("password"));
			result.add(Students);
		}
		if(rs!=null){
			rs.close();
		}
		mysqlconnect.close(stat, conn);
		return result;
    }
    public void findStu(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
    	Connection conn=null;
	    Statement stat=null;
	    conn=mysqlconnect.connect();
	 	stat=conn.createStatement();
	 	HttpSession session = request.getSession(false);
		String student_name = (String)session.getAttribute("user");
//	 	String student_name=request.getParameter("username");
	 	request.setAttribute("sturesult", selectStu(student_name));
	 	request.getRequestDispatcher("/views/student/students_infor.jsp").forward(request, response);
    }
    
    public ArrayList<user> selectUser(String username)throws ClassNotFoundException, SQLException{
		Connection conn=null;
    	Statement stat=null;
	    ResultSet rs=null;
	    conn=mysqlconnect.connect();
		stat=conn.createStatement();
		ArrayList<user> result=new ArrayList<user>();
		if(username!=""){
			rs=stat.executeQuery("select * from user where username='"+username+"'");
		}
		while(rs.next()){
			user User = new user(); 
			User.setUsername(rs.getString("username"));
			User.setPassword(rs.getString("password"));
			result.add(User);
		}
		if(rs!=null){
			rs.close();
		}
		mysqlconnect.close(stat, conn);
		return result;
    }
    public void findUser(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
    	Connection conn=null;
	    Statement stat=null;
	    conn=mysqlconnect.connect();
	 	stat=conn.createStatement();
	 	HttpSession session = request.getSession(false);
		String username = (String)session.getAttribute("user");
//	 	String student_name=request.getParameter("username");
	 	request.setAttribute("userresult", selectUser(username));
	 	request.getRequestDispatcher("/views/admin/admin_infor.jsp").forward(request, response);
    }
    
}
