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

import com.attendance.bean.course;
import com.attendance.bean.page;
import com.attendance.conn.MysqlConnect;

public class clazzchooseAction {
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
		ArrayList<course> result=selectStu(name);
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
		 ArrayList<course> result=selectStu(name); //返回查询的结果集
		 page pager=new page();
		 pager=setpage(request,response);
		 List<course> subResult=null;
		 int currentRecord=pager.getCurrentRecord();
		 if(currentRecord==0){
		 	if(pager.getTotalRecord()<8){
		 		subResult=(List<course>) result.subList(0,pager.getTotalRecord());
		 	}
		 	else{
		 		subResult=(List<course>) result.subList(0,pager.getPageSize());
		 	}         
		 }
		 else if(pager.getCurrentRecord()+pager.getPageSize()<result.size())
		 {
		       subResult=(List<course>) result.subList(pager.getCurrentRecord(),pager.getCurrentRecord()+pager.getPageSize());
		 }
		 else
		 {
		      subResult=(List<course>) result.subList(pager.getCurrentRecord(),result.size());
		 }
		 request.setAttribute("pager", pager);
		 request.setAttribute("fuck", subResult);
		 request.getRequestDispatcher("/views/master/clazz_choose.jsp").forward(request, response);
	}
	public ArrayList<course> selectStu(String name)throws ClassNotFoundException, SQLException{
		Connection conn=null;
		Statement stat=null;
	    ResultSet rs=null;
	    conn=mysqlconnect.connect();
		stat=conn.createStatement();
		ArrayList<course> result=new ArrayList<course>();
		if(name!=""){
			rs=stat.executeQuery("select * from course where name='"+name+"'");
		}
		while(rs.next()){
				course Course = new course(); 
				Course.setCourse_name(rs.getString("course_name"));
				Course.setName(rs.getString("name"));
				Course.setClazz(rs.getString("clazz"));
				result.add(Course);
			}
			if(rs!=null){
				rs.close();
			}
			mysqlconnect.close(stat, conn);
			return result;
		}
}
