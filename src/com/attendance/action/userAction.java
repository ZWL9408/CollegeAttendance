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
import com.attendance.bean.user;

import com.attendance.conn.MysqlConnect;
public class userAction {
	MysqlConnect mysqlconnect=new MysqlConnect();
	//设置分页相关参数方法
	public page setpage(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException{
		String crd=request.getParameter("currentRecord");
    	ArrayList<user> result=select("");
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
		 ArrayList<user> result=select(""); //返回查询的结果集
		 page pager=new page();
		 pager=setpage(request,response);
  	     List<user> subuser=null;
  	     int currentRecord=pager.getCurrentRecord();
         if(currentRecord==0){
         	if(pager.getTotalRecord()<8){
         		subuser=(List<user>) result.subList(0,pager.getTotalRecord());
         	}
         	else{
         		subuser=(List<user>) result.subList(0,pager.getPageSize());
         	}         
         }
         else if(pager.getCurrentRecord()+pager.getPageSize()<result.size())
         {
        	 subuser=(List<user>) result.subList(pager.getCurrentRecord(),pager.getCurrentRecord()+pager.getPageSize());
         }
         else
         {
        	 subuser=(List<user>) result.subList(pager.getCurrentRecord(),result.size());
         }
         request.setAttribute("pager", pager);
	     request.setAttribute("subuser", subuser);
		 request.getRequestDispatcher("/views/user/user_manage.jsp").forward(request, response);
     }
	
	//查询方法
	public ArrayList<user> select(String username) throws ClassNotFoundException, SQLException{
		Connection conn=null;
    	Statement stat=null;
	    ResultSet rs=null;
	    conn=mysqlconnect.connect();
		stat=conn.createStatement();
		ArrayList<user> result=new ArrayList<user>();
		if(username==""){
			rs=stat.executeQuery("select * from user");
		}
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
//插入方法
		public void insert(HttpServletRequest request,HttpServletResponse response)
		throws ClassNotFoundException,SQLException,IOException,ServletException{
			Connection conn =null;
			Statement stat=null;
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			conn=mysqlconnect.connect();
			stat=conn.createStatement();
			//int型不需要单引号，string型加上单引号
			stat.execute("insert into user values('"+username+"','"+password+"')");
			mysqlconnect.close(stat, conn);
			difpage(request, response);
		}
	 //删除方法
	 public void delete(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
		 Connection conn=null;
		 Statement stat=null;
		 conn=mysqlconnect.connect();
		 stat=conn.createStatement();
		 String username2=request.getParameter("username");
		 stat.execute("delete from user where username='"+username2+"'");
		 difpage(request, response);
//		 request.getRequestDispatcher("/jsp/views/user_main.jsp").forward(request, response);
	 }
	 //修改
	 public void update(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
		 Connection conn=null;
	     Statement stat=null;
	     String username3=request.getParameter("username");
	     String password3=request.getParameter("password");
	     conn=mysqlconnect.connect();
	 	 stat=conn.createStatement();
	 	 stat.execute("update user set password='"+password3+"',username='"+username3+"'where username='"+username3+"'");
	 	 request.setAttribute("userresult", select(username3));
	 	 difpage(request, response);
//	 	 request.getRequestDispatcher("/jsp/views/user_main.jsp").forward(request, response);
	 }
	 //信息修改后方法
	 public void update1(HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
		 String username4=request.getParameter("username");
		 request.setAttribute("userresult", select(username4));
		 request.getRequestDispatcher("/views/user/user_modify.jsp").forward(request, response);
	 }
}
