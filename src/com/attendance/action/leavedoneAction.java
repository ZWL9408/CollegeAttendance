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

import com.attendance.bean.absence;
import com.attendance.bean.page;
import com.attendance.conn.MysqlConnect;

public class leavedoneAction {
	MysqlConnect mysqlconnect=new MysqlConnect();
	  //设置分页相关参数方法
		public page setpage(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException{
			Connection conn=null;
			Statement stat=null;
			conn=mysqlconnect.connect();
			stat=conn.createStatement();
			HttpSession session = request.getSession(false);
			String leave_person = (String)session.getAttribute("user");
			String crd=request.getParameter("currentRecord");
	    	ArrayList<absence> result=select(leave_person);
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
			 Connection conn=null;
			 Statement stat=null;
			 conn=mysqlconnect.connect();
			 stat=conn.createStatement();
			 HttpSession session = request.getSession(false);
			 String leave_person = (String)session.getAttribute("user");
			 ArrayList<absence> result=select(leave_person); //返回查询的结果集
			 page pager=new page();
			 pager=setpage(request,response);
	  	     List<absence> subResult=null;
	  	     int currentRecord=pager.getCurrentRecord();
	         if(currentRecord==0){
	         	if(pager.getTotalRecord()<8){
	         		subResult=(List<absence>) result.subList(0,pager.getTotalRecord());
	         	}
	         	else{
	         		subResult=(List<absence>) result.subList(0,pager.getPageSize());
	         	}         
	         }
	         else if(pager.getCurrentRecord()+pager.getPageSize()<result.size())
	         {
	               subResult=(List<absence>) result.subList(pager.getCurrentRecord(),pager.getCurrentRecord()+pager.getPageSize());
	         }
	         else
	         {
	              subResult=(List<absence>) result.subList(pager.getCurrentRecord(),result.size());
	         }
	         request.setAttribute("pager", pager);
	         request.setAttribute("subResult", subResult);;
			 request.getRequestDispatcher("/views/student/leave_done.jsp").forward(request, response);
		     }
	    //查询方法
	    public ArrayList<absence> select(String leave_person) throws ClassNotFoundException, SQLException{
			Connection conn=null;
	    	Statement stat=null;
		    ResultSet rs=null;
		    conn=mysqlconnect.connect();
			stat=conn.createStatement();
			ArrayList<absence> result=new ArrayList<absence>();
			if(leave_person!=""){
				rs=stat.executeQuery("select * from absence where leave_person='"+leave_person+"' and status = '已审核'");
			}
			while(rs.next()){
				absence Absence = new absence(); 
				Absence.setStudent_id(rs.getInt("student_id"));
				Absence.setLeave_person(rs.getString("leave_person"));
				Absence.setLeave_time(rs.getString("leave_time"));
				Absence.setStart_time(rs.getString("start_time"));
				Absence.setLeave_reason(rs.getString("leave_reason"));
				Absence.setStatus(rs.getString("status"));
				Absence.setDeal_time(rs.getString("deal_time"));
				Absence.setDeal_person(rs.getString("deal_person"));
				Absence.setDeal_result(rs.getString("deal_result"));
				result.add(Absence);
			}
			if(rs!=null){
				rs.close();
			}
			mysqlconnect.close(stat, conn);
			return result;
	    }
}
