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
import com.attendance.bean.teacher;

import com.attendance.conn.MysqlConnect;
public class teacherAction {
	MysqlConnect mysqlconnect=new MysqlConnect();
	//设置分页相关参数方法
	public page setpage(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException{
		String crd=request.getParameter("currentRecord");
    	ArrayList<teacher> result=select("");
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
		 ArrayList<teacher> result=select(""); //返回查询的结果集
		 page pager=new page();
		 pager=setpage(request,response);
  	     List<teacher> subteacher=null;
  	     int currentRecord=pager.getCurrentRecord();
         if(currentRecord==0){
         	if(pager.getTotalRecord()<8){
         		subteacher=(List<teacher>) result.subList(0,pager.getTotalRecord());
         	}
         	else{
         		subteacher=(List<teacher>) result.subList(0,pager.getPageSize());
         	}         
         }
         else if(pager.getCurrentRecord()+pager.getPageSize()<result.size())
         {
        	 subteacher=(List<teacher>) result.subList(pager.getCurrentRecord(),pager.getCurrentRecord()+pager.getPageSize());
         }
         else
         {
        	 subteacher=(List<teacher>) result.subList(pager.getCurrentRecord(),result.size());
         }
         request.setAttribute("pager", pager);
	     request.setAttribute("subteacher", subteacher);
		 request.getRequestDispatcher("/views/admin/teacher_manage.jsp").forward(request, response);
     }
	//查询方法
		public ArrayList<teacher> select(String name) throws ClassNotFoundException, SQLException{
			Connection conn=null;
	    	Statement stat=null;
		    ResultSet rs=null;
		    conn=mysqlconnect.connect();
			stat=conn.createStatement();
			ArrayList<teacher> result=new ArrayList<teacher>();
			if(name==""){
				rs=stat.executeQuery("select * from teacher");
			}
			if(name!=""){
				rs=stat.executeQuery("select * from teacher where name='"+name+"'");
			}
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
			mysqlconnect.close(stat, conn);
			return result;
		}
	//插入方法
		public void insert(HttpServletRequest request,HttpServletResponse response)
		throws ClassNotFoundException,SQLException,IOException,ServletException{
			Connection conn =null;
			Statement stat=null;
			String name=request.getParameter("name");
			String gender=request.getParameter("gender");
			String password=request.getParameter("password");
			conn=mysqlconnect.connect();
			stat=conn.createStatement();
			//int型不需要单引号，string型加上单引号
			stat.execute("insert into teacher values('"+name+"','"+gender+"','"+password+"')");
			mysqlconnect.close(stat, conn);
			difpage(request, response);
		}
		
	 //删除方法
	 public void delete(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
		 Connection conn=null;
		 Statement stat=null;
		 conn=mysqlconnect.connect();
		 stat=conn.createStatement();
		 String name2=request.getParameter("name");
		 stat.execute("delete from teacher where name="+name2+"");
		 difpage(request, response);
	 }
	 //修改
	 public void update(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
		 Connection conn=null;
	     Statement stat=null;
	     String name3=request.getParameter("name");
	     String gender3=request.getParameter("gender");
	     String password3=request.getParameter("password");
	     conn=mysqlconnect.connect();
	 	 stat=conn.createStatement();
	 	 stat.execute("update teacher set name='"+name3+"', gender='"+gender3+"',password='"+password3+"' where name='"+name3+"'");
	 	 request.setAttribute("teacherresult", select(name3));
	 	 difpage(request, response);
//	 	 request.getRequestDispatcher("/views/admin/teacher_manage.jsp").forward(request, response);
	 }
	 //信息修改后方法
	 public void update1(HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
		 String name4=request.getParameter("name");
		 request.setAttribute("teacherresult", select(name4));
		 request.getRequestDispatcher("/views/admin/teacher_modify.jsp").forward(request, response);
	 }
}
