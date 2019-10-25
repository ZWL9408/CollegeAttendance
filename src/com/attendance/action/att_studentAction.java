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
import com.attendance.bean.att_student;
import com.attendance.bean.page;
import com.attendance.bean.students;
import com.attendance.conn.MysqlConnect;
public class att_studentAction {
	MysqlConnect mysqlconnect=new MysqlConnect();
	//设置分页相关参数方法
	public page setpage(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException{
		Connection conn=null;
		Statement stat=null;
		conn=mysqlconnect.connect();
		stat=conn.createStatement();
		HttpSession session = request.getSession(false);
		String student_name = (String)session.getAttribute("user");
		String crd=request.getParameter("currentRecord");
		ArrayList<att_student> result=select(student_name);
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
		 String student_name = (String)session.getAttribute("user");
		 ArrayList<att_student> result=select(student_name); //返回查询的结果集
		 page pager=new page();
		 pager=setpage(request,response);
		 List<att_student> subResult=null;
		 int currentRecord=pager.getCurrentRecord();
		 if(currentRecord==0){
		 	if(pager.getTotalRecord()<8){
		 		subResult=(List<att_student>) result.subList(0,pager.getTotalRecord());
		 	}
		 	else{
		 		subResult=(List<att_student>) result.subList(0,pager.getPageSize());
		 	}         
		 }
		 else if(pager.getCurrentRecord()+pager.getPageSize()<result.size())
		 {
		       subResult=(List<att_student>) result.subList(pager.getCurrentRecord(),pager.getCurrentRecord()+pager.getPageSize());
		 }
		 else
		 {
		      subResult=(List<att_student>) result.subList(pager.getCurrentRecord(),result.size());
		 }
		 request.setAttribute("pager", pager);
		 request.setAttribute("fuck", subResult);
		 request.getRequestDispatcher("/views/student/showAtt_stu.jsp").forward(request, response);
     }
		//查询方法
		public ArrayList<att_student> select(String student_name) throws ClassNotFoundException, SQLException{
			Connection conn=null;
			Statement stat=null;
		    ResultSet rs=null;
		    conn=mysqlconnect.connect();
			stat=conn.createStatement();
			ArrayList<att_student> result=new ArrayList<att_student>();
			if(student_name!=""){
				rs=stat.executeQuery("select * from att_student where student_name='"+student_name+"'");
			}
			while(rs.next()){
				att_student att_student = new att_student(); 
				att_student.setStudent_id(rs.getInt("student_id"));
				att_student.setStudent_name(rs.getString("student_name"));
				att_student.setCourse_name(rs.getString("course_name"));
				att_student.setAtt_time(rs.getString("att_time"));
				att_student.setOperation(rs.getString("operation"));
					result.add(att_student);
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
			List<att_student> subresult = (List<att_student>) request.getAttribute("att_result");
			if (!subresult.isEmpty()) {
				for (int i = 0; i < subresult.size(); i++) {
					att_student Att_student=subresult.get(i);
					int student_id=Att_student.getStudent_id();
					String student_name=Att_student.getStudent_name();
					String att_time=Att_student.getAtt_time();
					String operation=Att_student.getOperation();
					conn=mysqlconnect.connect();
					stat=conn.createStatement();
					stat.execute("insert into att_student values("+student_id+",'"+student_name+"','"+att_time+"','"+operation+"')");
				}
			}
			String course_time=request.getParameter("course_time");
			stat.execute("insert into course_time values('"+course_time+"')");
			conn=mysqlconnect.connect();
			stat=conn.createStatement();
			//int型不需要单引号，string型加上单引号
			mysqlconnect.close(stat, conn);
			difpage(request, response);
//			request.getRequestDispatcher("/views/att_student/att_student_manage.jsp").forward(request, response);
		}

	 //删除方法
	 public void delete(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
		 Connection conn=null;
		 Statement stat=null;
		 conn=mysqlconnect.connect();
		 stat=conn.createStatement();
		 String course_name2=request.getParameter("course_name");
		 stat.execute("delete from att_student where course_name="+course_name2+"");
		 difpage(request, response);
//		 request.getRequestDispatcher("/views/att_student/att_student_main.jsp").forward(request, response);
	 }
	 //修改
	 public void update(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
		 Connection conn=null;
	     Statement stat=null;
	     String student_id3=request.getParameter("student_id");
	     String student_name3=request.getParameter("student_name");
	     String att_time3=request.getParameter("att_time");
	     String operation3=request.getParameter("operation");
	     conn=mysqlconnect.connect();
	 	 stat=conn.createStatement();
	 	 stat.execute("update att_student set student_name="+student_name3+",att_time="+att_time3+",operation="+operation3+"where student_id="+student_id3+"");
	 	 request.setAttribute("att_studentresult", select(att_time3));
	 	 difpage(request, response);
//	 	 request.getRequestDispatcher("/jsp/views/att_student__main.jsp").forward(request, response);
	 }
	 //前往修改页面
	 public void update1(HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
		 String att_time4=request.getParameter("att_student");
		 request.setAttribute("att_studentresult", select(att_time4));
		 request.getRequestDispatcher("/views/att_student/att_student_modify.jsp").forward(request, response);
	 }
}
