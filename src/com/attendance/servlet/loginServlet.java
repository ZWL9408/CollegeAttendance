package com.attendance.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.attendance.action.personalAction;
import com.attendance.action.teacherAction;
import com.attendance.conn.MysqlConnect;

public class loginServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");  
        request.setCharacterEncoding("utf-8");  
        HttpSession session = request.getSession();  
        PrintWriter out = response.getWriter(); 
        String flag=null;
        boolean validated=false;
        String usr=request.getParameter("username");//获取用户名
        String pwd=request.getParameter("password");//获取密码
        String role=request.getParameter("role");//获取角色，0代表管理员，1代表老师，2代表学生
        MysqlConnect conn=new MysqlConnect();//打开数据库连接
        try {
        	if(role.equals("0")){
			String sql = "select * from user";
        	conn.getConnection();
			ResultSet rs=conn.executeQuery(sql);
			while(rs.next()){
				if((rs.getString("username").compareTo(usr)==0)&&(rs.getString("password").compareTo(pwd)==0)){
					validated=true;
					flag="0";
				}
			}
			}else if(role.equals("1")){
				String sql="select name,password from teacher";
				conn.getConnection();
				ResultSet rs=conn.executeQuery(sql);
				while(rs.next()){
					if((rs.getString("name").compareTo(usr)==0)&&(rs.getString("password").compareTo(pwd)==0)){
						validated=true;
						flag="1";
					}
				}
			}else if(role.equals("2")){
				String sql = "select student_name,password from students";
				conn.getConnection();
				ResultSet rs = conn.executeQuery(sql);
				while(rs.next()){
					if((rs.getString("student_name").compareTo(usr)==0)&&(rs.getString("password").compareTo(pwd)==0)){
						validated=true;
						flag="2";
					}
				}
			} 
          }catch (Exception e) {
			e.printStackTrace();
		}finally{
			conn.closeAll();
		}
        
		if (validated&&flag.equals("0")) {
			try {
//				courseAction courseaction=new courseAction();
//				session.setAttribute("user",usr);
//				teacherAction teacheraction=new teacherAction();
//				teacheraction.difpage(request, response);
				personalAction personalaction=new personalAction();
				session.setAttribute("user",usr);
				personalaction.findUser(request, response);
//				courseaction.difpage(request, response);//管理员登陆成功，跳转到管理员界面
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else if(validated&&flag.equals("1")){
			try {
				personalAction personalaction=new personalAction();
				session.setAttribute("user",usr);
				personalaction.findTea(request, response);//老师登陆成功，跳转到老师界面
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if(validated&&flag.equals("2")){
			try {				
				session.setAttribute("user",usr);
				personalAction personalaction=new personalAction();
				personalaction.findStu(request, response);//学生登陆成功，跳转到学生界面
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else{
			request.getRequestDispatcher("error.jsp").forward(request, response);//登陆失败，跳转到error.jsp页面
		}

	}



}
