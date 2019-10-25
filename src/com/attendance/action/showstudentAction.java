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

public class showstudentAction {
	MysqlConnect mysqlconnect=new MysqlConnect();
	//设置分页相关参数方法
	public page setpage(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException{
		Connection conn=null;
		Statement stat=null;
		conn=mysqlconnect.connect();
		stat=conn.createStatement();
		HttpSession session = request.getSession(false);
		String name = (String)session.getAttribute("user");
		String crd=request.getParameter("currentRecord");
		String course_name=request.getParameter("course_name");
		String att_time=request.getParameter("att_time");
		ArrayList<att_student> result=selectStu(course_name,att_time);
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
	 public void findStu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException{
		 Connection conn=null;
		 Statement stat=null;
		 conn=mysqlconnect.connect();
		 stat=conn.createStatement();
		 HttpSession session = request.getSession(false);
		 String name = (String)session.getAttribute("user");
		 String course_name=request.getParameter("course_name");
		 String att_time=request.getParameter("att_time");
		 ArrayList<att_student> result=selectStu(course_name,att_time); //返回查询的结果集
		 page pager=new page();
		 pager=setpage(request,response);
		 List<att_student> subResult=null;
		 subResult=(List<att_student>) result;
//		 int currentRecord=pager.getCurrentRecord();
//		 if(currentRecord==0){
//		 	if(pager.getTotalRecord()<8){
//		 		subResult=(List<att_student>) result.subList(0,pager.getTotalRecord());
//		 	}
//		 	else{
//		 		subResult=(List<att_student>) result.subList(0,pager.getPageSize());
//		 	}         
//		 }
//		 else if(pager.getCurrentRecord()+pager.getPageSize()<result.size())
//		 {
//		       subResult=(List<att_student>) result.subList(pager.getCurrentRecord(),pager.getCurrentRecord()+pager.getPageSize());
//		 }
//		 else
//		 {
//		      subResult=(List<att_student>) result.subList(pager.getCurrentRecord(),result.size());
//		 }
		 request.setAttribute("pager", pager);
		 request.setAttribute("fuck", subResult);
		 request.getRequestDispatcher("/views/master/showclazz_att.jsp").forward(request, response);
	}
	public ArrayList<att_student> selectStu(String course_name,String att_time)throws ClassNotFoundException, SQLException{
		Connection conn=null;
		Statement stat=null;
	    ResultSet rs=null;
	    conn=mysqlconnect.connect();
		stat=conn.createStatement();
		ArrayList<att_student> result=new ArrayList<att_student>();
		if(course_name!=""){
			rs=stat.executeQuery("select * from att_student where course_name='"+course_name+"' and att_time='"+att_time+"' ");
		}
		while(rs.next()){
				att_student Course = new att_student(); 
				Course.setStudent_id(rs.getInt("student_id"));
				Course.setStudent_name(rs.getString("student_name"));
				Course.setCourse_name(rs.getString("course_name"));
				Course.setAtt_time(rs.getString("att_time"));
				Course.setOperation(rs.getString("operation"));
				result.add(Course);
			}
			if(rs!=null){
				rs.close();
			}
			mysqlconnect.close(stat, conn);
			return result;
		}
}
