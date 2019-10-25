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

import com.attendance.bean.co_time;
import com.attendance.bean.page;
import com.attendance.conn.MysqlConnect;

public class co_timeAction {
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
		ArrayList<co_time> result=selectStu(course_name);
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
		 ArrayList<co_time> result=selectStu(course_name); //返回查询的结果集
		 page pager=new page();
		 pager=setpage(request,response);
		 List<co_time> subResult=null;
		 int currentRecord=pager.getCurrentRecord();
		 if(currentRecord==0){
		 	if(pager.getTotalRecord()<8){
		 		subResult=(List<co_time>) result.subList(0,pager.getTotalRecord());
		 	}
		 	else{
		 		subResult=(List<co_time>) result.subList(0,pager.getPageSize());
		 	}         
		 }
		 else if(pager.getCurrentRecord()+pager.getPageSize()<result.size())
		 {
		       subResult=(List<co_time>) result.subList(pager.getCurrentRecord(),pager.getCurrentRecord()+pager.getPageSize());
		 }
		 else
		 {
		      subResult=(List<co_time>) result.subList(pager.getCurrentRecord(),result.size());
		 }
		 request.setAttribute("pager", pager);
		 request.setAttribute("fuck", subResult);
		 request.getRequestDispatcher("/views/master/co_timeshow.jsp").forward(request, response);
	}
	public ArrayList<co_time> selectStu(String course_name)throws ClassNotFoundException, SQLException{
		Connection conn=null;
		Statement stat=null;
	    ResultSet rs=null;
	    conn=mysqlconnect.connect();
		stat=conn.createStatement();
		ArrayList<co_time> result=new ArrayList<co_time>();
		if(course_name!=""){
			rs=stat.executeQuery("select * from co_time where course_name='"+course_name+"'");
		}
		while(rs.next()){
				co_time Course = new co_time(); 
				Course.setCourse_name(rs.getString("course_name"));
				Course.setAtt_time(rs.getString("att_time"));
				result.add(Course);
			}
			if(rs!=null){
				rs.close();
			}
			mysqlconnect.close(stat, conn);
			return result;
		}
}
