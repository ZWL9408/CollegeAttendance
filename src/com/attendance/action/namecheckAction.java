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
import javax.servlet.http.HttpSession;

import com.attendance.bean.att_student;
import com.attendance.bean.page;
import com.attendance.bean.students;
import com.attendance.conn.MysqlConnect;

public class namecheckAction {
	MysqlConnect mysqlconnect=new MysqlConnect();
	//设置分页相关参数方法
	public page setpage(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException{
		String clazz=request.getParameter("clazz");
//		HttpSession session = request.getSession(false);
//		String clazz = (String)session.getAttribute("clazz");
		String crd=request.getParameter("currentRecord");
    	ArrayList<students> result=select(clazz);
//    	ArrayList<students> result=select("");
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
		 String clazz=request.getParameter("clazz");
//		 HttpSession session = request.getSession(false);
//		 String clazz = (String)session.getAttribute("clazz");
		 ArrayList<students> result=select(clazz); //返回查询的结果集
//		 ArrayList<students> result=select(""); //返回查询的结果集
		 page pager=new page();
		 pager=setpage(request,response);
  	     List<students> substudents=null;
  	   substudents=(List<students>) result;
//  	     int currentRecord=pager.getCurrentRecord();
//         if(currentRecord==0){
//         	if(pager.getTotalRecord()<8){
//         		substudents=(List<students>) result.subList(0,pager.getTotalRecord());
//         	}
//         	else{
//         		substudents=(List<students>) result.subList(0,pager.getPageSize());
//         	}         
//         }
//         else if(pager.getCurrentRecord()+pager.getPageSize()<result.size())
//         {
//        	 substudents=(List<students>) result.subList(pager.getCurrentRecord(),pager.getCurrentRecord()+pager.getPageSize());
//         }
//         else
//         {
//        	 substudents=(List<students>) result.subList(pager.getCurrentRecord(),result.size());
//         }
         request.setAttribute("pager", pager);
	     request.setAttribute("subattresult", substudents);
	     HttpSession session = request.getSession();  
	     session.setAttribute("subattresult", substudents);
		 request.getRequestDispatcher("/views/master/name_check.jsp").forward(request, response);
     }
	//查询方法
	public ArrayList<students> select(String clazz) throws ClassNotFoundException, SQLException{
		Connection conn=null;
    	Statement stat=null;
	    ResultSet rs=null;
	    conn=mysqlconnect.connect();
		stat=conn.createStatement();
		ArrayList<students> result=new ArrayList<students>();
//		if(clazz==""){
//			rs=stat.executeQuery("select * from students");
//		}
		if(clazz!=""){
			rs=stat.executeQuery("select * from students where clazz='"+clazz+"'");
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
	public void insert(HttpServletRequest request, HttpServletResponse response) 
	throws ClassNotFoundException, SQLException , IOException,ServletException{
    	Connection conn=null;
    	Statement stat=null;
//    	response.setCharacterEncoding("UTF-8");
//  		request.setCharacterEncoding("UTF-8");
    	String course_name=request.getParameter("course_name");
  		String att_time=request.getParameter("att_time");
  		conn=mysqlconnect.connect();
		stat=conn.createStatement();
		stat.execute("insert into co_time values('"+course_name+"','"+att_time+"')");
//  		List<att_student> subatts=(List<att_student>)request.getAttribute("namecheck");
		HttpSession session = request.getSession(false);
//		String name = (String)session.getAttribute("user");
  		List<students> substudents=(List<students>)session.getAttribute("subattresult");
  			if(!substudents.isEmpty()){
	  			for(int i=0;i<substudents.size();i++){
//		  			 att_student att_Student=new att_student();
	  				 students Students=substudents.get(i);
		  			 int student_id=Students.getStudent_id();
		  			 String student_name=Students.getStudent_name();
		  			 String oper=request.getParameter("operation"+i);
  	  				 stat.execute("insert into att_student values('"+student_id+"','"+student_name+"','"+course_name+"','"+att_time+"','"+oper+"')");
  	  			}
  	  		}
    	mysqlconnect.close(stat,conn);
    	personalAction personalaction=new personalAction();
		personalaction.findTea(request, response);
    }
}