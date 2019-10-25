package com.attendance.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.attendance.bean.page;
import com.attendance.bean.students;

import com.attendance.conn.MysqlConnect;

public class studentsAction {
	MysqlConnect mysqlconnect=new MysqlConnect();
	//设置分页相关参数方法
	public page setpage(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException{
		String crd=request.getParameter("currentRecord");
    	ArrayList<students> result=select("");
    	page pager=new page();
    	pager.setTotalRecord(result.size()); 
    	pager.setTotalPage(result.size(),pager.getPageSize());
    	if(crd!=null)
        {
    		int currentRecord=Integer.parseInt(crd);
            pager.setCurrentRecord(currentRecord);
            pager.setCurrentPage(currentRecord,pager.getPageSize());
        }
    	return pager;
	}
	//获得分页显示的子集
	 public void difpage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException{
		 ArrayList<students> result=select(""); //返回查询的结果集
		 page pager=new page();
		 pager=setpage(request,response);
  	     List<students> substudents=null;
  	     int currentRecord=pager.getCurrentRecord();
         if(currentRecord==0){
         	if(pager.getTotalRecord()<8){
         		substudents=(List<students>) result.subList(0,pager.getTotalRecord());
         	}
         	else{
         		substudents=(List<students>) result.subList(0,pager.getPageSize());
         	}         
         }
         else if(pager.getCurrentRecord()+pager.getPageSize()<result.size())
         {
        	 substudents=(List<students>) result.subList(pager.getCurrentRecord(),pager.getCurrentRecord()+pager.getPageSize());
         }
         else
         {
        	 substudents=(List<students>) result.subList(pager.getCurrentRecord(),result.size());
         }
         request.setAttribute("pager", pager);
	     request.setAttribute("substudents", substudents);
		 request.getRequestDispatcher("/views/admin/students_manage.jsp").forward(request, response);
     }
	//查询方法
	public ArrayList<students> select(String student_id) throws ClassNotFoundException, SQLException{
		Connection conn=null;
    	Statement stat=null;
	    ResultSet rs=null;
	    conn=mysqlconnect.connect();
		stat=conn.createStatement();
		ArrayList<students> result=new ArrayList<students>();
		if(student_id==""){
			rs=stat.executeQuery("select * from students");
		}
		if(student_id!=""){
			rs=stat.executeQuery("select * from students where student_id="+student_id+"");
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
	//插入方法
		public void insert(HttpServletRequest request,HttpServletResponse response)
		throws ClassNotFoundException,SQLException,IOException,ServletException{
			Connection conn =null;
			Statement stat=null;
			String student_id=request.getParameter("student_id");
			String student_name=request.getParameter("student_name");
			String gender=request.getParameter("gender");
			String clazz=request.getParameter("clazz");
			String password=request.getParameter("password");
			conn=mysqlconnect.connect();
			stat=conn.createStatement();
			stat.execute("insert into students values("+student_id+",'"+student_name+"','"+gender+"','"+clazz+"','"+password+"')");
			mysqlconnect.close(stat, conn);
			difpage(request, response);
		}
	 //删除方法
	 public void delete(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
		 Connection conn=null;
		 Statement stat=null;
		 conn=mysqlconnect.connect();
		 stat=conn.createStatement();
		 String student_id2=request.getParameter("student_id");
		 stat.execute("delete from students where student_id="+student_id2+"");
		 difpage(request, response);
	 }
	 //修改
	 public void update(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
		 Connection conn=null;
	     Statement stat=null;
	     String student_id3=request.getParameter("student_id");
	     String student_name3=request.getParameter("student_name");
	     String gender3=request.getParameter("gender");
	     String clazz3=request.getParameter("clazz");
	     String password3=request.getParameter("password");
	     conn=mysqlconnect.connect();
	 	 stat=conn.createStatement();
	 	 stat.execute("update students set student_id="+student_id3+",student_name='"+student_name3+"',gender='"+gender3+"',clazz='"+clazz3+"',password='"+password3+"'where student_id="+student_id3+"");
	 	 request.setAttribute("studentsresult", select(student_id3));
	 	 difpage(request, response);
//	 	 request.getRequestDispatcher("/jsp/views/students_main.jsp").forward(request, response);
	 }
	 //信息修改后方法
	 public void update1(HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
		 String student_id4=request.getParameter("student_id");
		 request.setAttribute("studentsresult", select(student_id4));
		 request.getRequestDispatcher("/views/admin/students_modify.jsp").forward(request, response);
	 }
}
