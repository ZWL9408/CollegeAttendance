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
import com.attendance.bean.absence;
import com.attendance.bean.page;
import com.attendance.conn.MysqlConnect;

public class absenceAction {
	MysqlConnect mysqlconnect=new MysqlConnect();
	  //设置分页相关参数方法
		public page setpage(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException{
			String crd=request.getParameter("currentRecord");
	    	ArrayList<absence> result=select("");
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
			 ArrayList<absence> result=select(""); //返回查询的结果集
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
		     request.setAttribute("subResult", subResult);
			 request.getRequestDispatcher("/views/master/absence_manage.jsp").forward(request, response);
		     }

	    //查询方法
	    public ArrayList<absence> select(String student_id) throws ClassNotFoundException, SQLException{
	    	Connection conn=null;
	    	Statement stat=null;
		    ResultSet rs=null;
		    conn=mysqlconnect.connect();
			stat=conn.createStatement();
	    	ArrayList<absence> result=new ArrayList<absence>();
	    	if(student_id==""){
	    	     rs=stat.executeQuery("select * from absence where status != 'null'"); 
	    	}
	    	if(student_id!=""){
	   	        rs=stat.executeQuery("select * from absence where student_id="+student_id+""); 
	     	}
	    	while(rs.next())
	        {
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
		    mysqlconnect.close(stat,conn);
	    	return result;
	    }
		//插入方法
		public void insert(HttpServletRequest request, HttpServletResponse response) 
		throws ClassNotFoundException, SQLException , IOException,ServletException{
	    	Connection conn=null;
	    	Statement stat=null;
	    	String student_id=request.getParameter("student_id");
			String leave_person=request.getParameter("leave_person");
			String leave_time=request.getParameter("leave_time");
			String start_time=request.getParameter("start_time");
			String leave_reason=request.getParameter("leave_reason");
			String status=request.getParameter("status");
			String deal_time=request.getParameter("deal_time");
			String deal_person=request.getParameter("deal_person");
			String deal_result=request.getParameter("deal_result");
			conn=mysqlconnect.connect();
			stat=conn.createStatement();
	    	stat.execute("insert into absence values('"+student_id+"','"+leave_person+"','"+leave_time+"','"+start_time+"','"+leave_reason+"','"+status+"','"+deal_time+"','"+deal_person+"','"+deal_result+"')");
	    	mysqlconnect.close(stat,conn);
	    	difpage(request,response);
	    }
	    //信息删除方法
	    public void delete(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
	    	Connection conn=null;
	    	Statement stat=null;
	    	conn=mysqlconnect.connect();
	 		stat=conn.createStatement();
	 		String student_id2=request.getParameter("student_id");
			stat.execute("delete from absence where student_id="+student_id2+"");
			difpage(request,response);
	    } 
	    //修改
	    public void update(HttpServletRequest request, HttpServletResponse response) 
	    throws ClassNotFoundException, SQLException, ServletException, IOException{
	    	Connection conn=null;
	    	Statement stat=null;
	    	String student_id3=request.getParameter("student_id");
			String leave_person3=request.getParameter("leave_person");
			String leave_time3=request.getParameter("leave_time");
			String start_time3=request.getParameter("start_time");
			String leave_reason3=request.getParameter("leave_reason");
			String status3=request.getParameter("status");
			String deal_time3=request.getParameter("deal_time");
			String deal_person3=request.getParameter("deal_person");
			String deal_result3=request.getParameter("deal_result");
	    	conn=mysqlconnect.connect();
	 		stat=conn.createStatement();
	 		stat.execute("update absence set student_id="+student_id3+",leave_person='"+leave_person3+"',leave_time='"+leave_time3+"',start_time='"+start_time3+"',leave_reason='"+leave_reason3+"',status='"+status3+"',deal_time='"+deal_time3+"',deal_person='"+deal_person3+"',deal_result='"+deal_result3+"'where student_id="+student_id3+"");
			request.setAttribute("result", select(student_id3));    
			difpage(request,response);
	    } 
	    //前往修改页面
	    public void update1(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
	    	String id4=request.getParameter("student_id");
		    request.setAttribute("result", select(id4));
	        request.getRequestDispatcher("/views/master/absence_modify.jsp").forward(request, response);
	    }  
}
