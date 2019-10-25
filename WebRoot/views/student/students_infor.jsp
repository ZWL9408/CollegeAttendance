<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.attendance.bean.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <base href="<%=basePath%>">
    <title>My JSP 'head.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/kaoqin.css">
<script src="js/jquery.js"></script>
<script> 
$(document).ready(function(){
$(".flip").click(function(){
$(".panel").toggle();
});
});
$(document).ready(function(){
$(".flip1").click(function(){
$(".panel1").toggle();
});
});
$(document).ready(function(){
$(".flip2").click(function(){
$(".panel2").toggle();
});
});
$(document).ready(function(){
$(".flip3").click(function(){
$(".panel3").toggle();
});
});
</script>
  </head>
  <body>
  <jsp:include page="/views/head.jsp"/>
		<div class="container">
			<div class="left" style="background-color:#F0FFFF;">
			<table>
				<tr>
				<p class="flip" align="center" style="height:28px;background-image:url(img/celian.gif);background-repeat: no-repeat;background-size:cover;">用户信息管理</p>
				<div class="panel" style="display:none" align="center">
                <a class="button blue" href="javascript:window.location.href='personalServlet?personal=<%=1 %>';">查看个人信息</a><br/><br/>
                <a class="button blue" href="javascript:window.location.href='views/student/stu_pwd.jsp';">用户密码修改</a>
				</div>
				</tr>
				<tr>
                <p class="flip2" align="center" style="height:28px;background-image:url(img/celian.gif);background-repeat: no-repeat;background-size:cover;">请假申请管理</p>
                <div class="panel2" style="display:none" align="center">
                <a class="button blue" href="javascript:window.location.href='leaveServlet?methodName=<%=1 %>';">查看请假申请</a><br/><br/>
                <a class="button blue" href="javascript:window.location.href='leavedoneServlet?methodName=<%=1 %>';">查看已审核申请</a><br/><br/>
                <a class="button blue" href="javascript:window.location.href='views/student/leave_add.jsp'">进行请假申请</a>
				</div>
				</tr>
				<tr>
                <p class="flip3" align="center" style="height:28px;background-image:url(img/celian.gif);background-repeat: no-repeat;background-size:cover;">查看考勤信息</p>
                <div class="panel3" style="display:none" align="center">
                <a class="button blue" href="javascript:window.location.href='att_studentServlet?Att_student=<%=1 %>';">查看个人考勤信息</a>
				</div>
				</tr>
            </table>
			</div>
			<div class="right">
			<div style="height:20px;  float:right"></div>
				<h1><center>用户基本信息如下：</center></h1>
			  <table class="table4_2" align="center" width="396" border="100" cellSpacing=1 style="border:1pt;font-size:15pt;"height="31">
			  	
			    <%
			    	int id=0;
			    	ArrayList<students> result=new ArrayList<students>();
			    	result=(ArrayList<students>)request.getAttribute("sturesult");
			    	if(!result.isEmpty()){
			    	for(int i=0;i<result.size();i++){
			    		students stu=result.get(i);
			    		id=stu.getStudent_id();
			    		out.print("<tr>");
			    		out.print("<td width=\"126\">学号：</td>"+"<td>"+stu.getStudent_id()+"</td>");out.print("<tr>");
			    		out.print("<td width=\"126\">名字：</td>"+"<td>"+stu.getStudent_name()+"</td>");out.print("<tr>");
			    		out.print("<td width=\"126\">性别：</td>"+"<td>"+stu.getGender()+"</td>");out.print("<tr>");
			    		out.print("<td width=\"126\">班级：</td>"+"<td>"+stu.getClazz()+"</td>");out.print("<tr>");
			    		out.print("<td width=\"126\">密码：</td>"+"<td>"+stu.getPassword()+"</td>");out.print("<tr>");
			    		out.print("</tr>");
			    	}
			    	}
			    %>
			  </table>  
			</div>
		</div>
		<jsp:include page="/views/foot.jsp"/>
  </body>
</html>
