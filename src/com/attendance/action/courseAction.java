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

import com.attendance.bean.course;
import com.attendance.bean.page;

import com.attendance.conn.MysqlConnect;

public class courseAction {
	MysqlConnect mysqlconnect=new MysqlConnect();
	//设置分页相关参数方法
	public page setpage(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException{
		String crd=request.getParameter("currentRecord");
    	ArrayList<course> result=select("");
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
		 ArrayList<course> result=select(""); //返回查询的结果集
		 page pager=new page();
		 pager=setpage(request,response);
  	     List<course> subcourse=null;
  	     int currentRecord=pager.getCurrentRecord();
         if(currentRecord==0){
         	if(pager.getTotalRecord()<8){
         		subcourse=(List<course>) result.subList(0,pager.getTotalRecord());
         	}
         	else{
         		subcourse=(List<course>) result.subList(0,pager.getPageSize());
         	}         
         }
         else if(pager.getCurrentRecord()+pager.getPageSize()<result.size())
         {
        	 subcourse=(List<course>) result.subList(pager.getCurrentRecord(),pager.getCurrentRecord()+pager.getPageSize());
         }
         else
         {
        	 subcourse=(List<course>) result.subList(pager.getCurrentRecord(),result.size());
         }
         request.setAttribute("pager", pager);
	     request.setAttribute("subcourse", subcourse);
		 request.getRequestDispatcher("/views/admin/course_manage.jsp").forward(request, response);
     }
	//查询方法
		public ArrayList<course> select(String course_name) throws ClassNotFoundException, SQLException{
			Connection conn=null;
	    	Statement stat=null;
		    ResultSet rs=null;
		    conn=mysqlconnect.connect();
			stat=conn.createStatement();
			ArrayList<course> result=new ArrayList<course>();
			if(course_name==""){
				rs=stat.executeQuery("select * from course");
			}
			if(course_name!=""){
				rs=stat.executeQuery("select * from course where course_name='"+course_name+"'");
			}
			while(rs.next()){
				course Course = new course(); 
				Course.setCourse_name(rs.getString("course_name"));
				Course.setClazz(rs.getString("clazz"));
				Course.setName(rs.getString("name"));
				result.add(Course);
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
			String course_name=request.getParameter("course_name");
			String clazz=request.getParameter("clazz");
			String name=request.getParameter("name");
			conn=mysqlconnect.connect();
			stat=conn.createStatement();
			stat.execute("insert into course values('"+course_name+"','"+name+"','"+clazz+"')");
			mysqlconnect.close(stat, conn);
			difpage(request, response);
		}
		
	 //删除方法
	 public void delete(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
		 Connection conn=null;
		 Statement stat=null;
		 conn=mysqlconnect.connect();
		 stat=conn.createStatement();
		 String course_name2=request.getParameter("course_name");
		 stat.execute("delete from course where course_name='"+course_name2+"'");
		 difpage(request, response);
	 }
	 //修改
	 public void update(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
		 Connection conn=null;
	     Statement stat=null;
	     String course_name3=request.getParameter("course_name");
	     String clazz3=request.getParameter("clazz");
	     String name3=request.getParameter("name");
	     conn=mysqlconnect.connect();
	 	 stat=conn.createStatement();
	 	 stat.execute("update course set course_name='"+course_name3+"', clazz='"+clazz3+"',name='"+name3+"'where course_name='"+course_name3+"'");
	 	 request.setAttribute("courseresult", select(course_name3));
	 	 difpage(request, response);
	 }
	 //信息修改后方法
	 public void update1(HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
		 String course_name4=request.getParameter("course_name");
		 request.setAttribute("courseresult", select(course_name4));
		 request.getRequestDispatcher("/views/admin/course_modify.jsp").forward(request, response);
	 }
}
