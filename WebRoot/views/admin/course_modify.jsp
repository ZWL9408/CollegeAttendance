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
    <title>My JSP 'attendant_manage.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/kaoqin.css" rel="stylesheet" type="text/css"/>
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
			<div class="header"><h1>要修改的课程信息如下</h1></div>
			<div class="left" style="background-color:#F0FFFF;">
			<table>
				<br>
				<tr>
				<p class="flip" align="center" style="height:28px;background-image:url(img/celian.gif);background-repeat: no-repeat;background-size:cover;">用户信息管理</p>
                <div class="panel" style="display:none" align="center">
                <a class="button gray" href="javascript:window.location.href='personalServlet?personal=<%=2 %>';">个人基本信息</a><br/><br/>
                <a class="button gray" href="javascript:window.location.href='views/admin/user_pwd.jsp';">密码修改</a>
				</div>
				</tr>
                <tr>
				<p class="flip1" align="center" style="height:28px;background-image:url(img/celian.gif);background-repeat: no-repeat;background-size:cover;">课程表管理</p>
                <div class="panel1" style="display:none" align="center">
				<a class="button gray" href="javascript:window.location.href='courseServlet?Course=<%=1 %>';">查看与管理</a><br/><br/>
				<a class="button gray" href="javascript:window.location.href='views/admin/course_add.jsp'">课程信息输入</a>
				</div>
				</tr>
                <tr>
				<p class="flip2" align="center" style="height:28px;background-image:url(img/celian.gif);background-repeat: no-repeat;background-size:cover;">学生信息管理</p>
                <div class="panel2" style="display:none" align="center">
				<a class="button gray" href="javascript:window.location.href='studentsServlet?Students=<%=1 %>';">查看与管理</a><br/><br/>
				<a class="button gray" href="javascript:window.location.href='views/admin/students_add.jsp'">学生信息输入</a>
				</div>
				</tr>
				<tr>
				<p class="flip3" align="center" style="height:28px;background-image:url(img/celian.gif);background-repeat: no-repeat;background-size:cover;">老师信息管理</p>
				<div class="panel3" style="display:none" align="center">
				<a class="button gray" href="javascript:window.location.href='teacherServlet?Teacher=<%=1 %>';">查看与管理</a><br/><br/>
				<a class="button gray" href="javascript:window.location.href='views/admin/teacher_add.jsp'">老师信息输入</a>
				</div>
				</tr>
            </table>
			</div>
			<div class="right">
			<div style="height:20px;  float:right" >

			</div>
		<table class="table4_2" align="center" width="496" border="100" cellSpacing=1 style="border:1pt;font-size:15pt;"height="31">
  			<tr bgcolor="#c4e1ff">
  			<td>课程名称 </td>
    		<td>任课老师</td>
    		<td>班级名称</td>
    		</tr>
    		<%
    		String course_name=null;
    		ArrayList<course> result=new ArrayList<course>();
    		result=(ArrayList<course>)request.getAttribute("courseresult");
    		if(!result.isEmpty()){
    		for(int i=0;i<result.size();i++){
    			course cou=result.get(i);
    			course_name=cou.getCourse_name();
    			out.print("<tr>");
    			out.print("<td>"+cou.getCourse_name()+"</td>");
    			out.print("<td>"+cou.getName()+"</td>");
    			out.print("<td>"+cou.getClazz()+"</td>");
    			out.print("</tr>");
    			}
    		}
    		%>
		</table>  
  		<br/><br/>
  		<form action="courseServlet" method="post">
  		<table class="table4_2" align="center" width="496" border="1" cellspacing="1" style=" font-size: 12pt;" height="31px">
  		<input type="hidden" name="Course" value="3"/>
  		<tr>
		<td>课程名称</td>
  		<td><input type="text" name="course_name" value="<%=course_name %>" title="课程名称不能为变" /></td></tr>
  		<tr><td>任课老师</td><td><input type="text" name="name" title="任课老师不能为空"/></td></tr>
  		<tr><td>班级名称</td><td><input type="text" name="clazz" title="班级名称不能为空"/></td></tr>
  		<tr><td colspan="2" align="center">
  		<input class="button blue" type="submit" value="修改"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		<input class="button red" type="reset" value="重置"/></td></tr>
  		</table>
  		</form>
		</div>
		</div>
		<jsp:include page="/views/foot.jsp"/>
  </body>
</html>
