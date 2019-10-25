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

import com.attendance.bean.clazz;
import com.attendance.bean.page;
import com.attendance.bean.students;
import com.attendance.conn.MysqlConnect;

public class clazzAction {
	MysqlConnect mysqlconnect=new MysqlConnect();
	  //设置分页相关参数方法
		public page setpagecla(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException{
			String clazz=request.getParameter("clazz");
			String crd=request.getParameter("currentRecord");
	    	ArrayList<clazz> result=selectclazz(clazz);
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
		 public void difpageclazz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException{
			 String clazz=request.getParameter("clazz");
			 ArrayList<clazz> result=selectclazz(clazz); //返回查询的结果集
			 page pager=new page();
			 pager=setpagecla(request,response);
	  	     List<clazz> subResult=null;
	  	     int currentRecord=pager.getCurrentRecord();
	         if(currentRecord==0){
	         	if(pager.getTotalRecord()<8){
	         		subResult=(List<clazz>) result.subList(0,pager.getTotalRecord());
	         	}
	         	else{
	         		subResult=(List<clazz>) result.subList(0,pager.getPageSize());
	         	}         
	         }
	         else if(pager.getCurrentRecord()+pager.getPageSize()<result.size())
	         {
	               subResult=(List<clazz>) result.subList(pager.getCurrentRecord(),pager.getCurrentRecord()+pager.getPageSize());
	         }
	         else
	         {
	              subResult=(List<clazz>) result.subList(pager.getCurrentRecord(),result.size());
	         }
	         request.setAttribute("pager", pager);
		     request.setAttribute("subResult", subResult);
			 request.getRequestDispatcher("/views/master/clazz_check.jsp").forward(request, response);
		     }
		    //查询方法
		    public ArrayList<clazz> selectclazz(String clazz) throws ClassNotFoundException, SQLException{
		    	Connection conn=null;
		    	Statement stat=null;
			    ResultSet rs=null;
			    conn=mysqlconnect.connect();
				stat=conn.createStatement();
		    	ArrayList<clazz> result=new ArrayList<clazz>();
		    	if(clazz!=""){
		    	     rs=stat.executeQuery("select * from clazz where clazz='"+clazz+"'"); 
		    	     while(rs.next())
		 	        {
		 	    		clazz Clazz = new clazz(); 
		 				Clazz.setClazz_id(rs.getInt("clazz_id"));
		 				Clazz.setClazz(rs.getString("clazz"));
		 				Clazz.setClazz_number(rs.getInt("clazz_number"));
		 				result.add(Clazz);
		 	        }

		    	}
	 		    if(rs!=null){
			    	  rs.close();
			       }
			    mysqlconnect.close(stat,conn);
		    	return result;
		    }
}
