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
	<link rel="stylesheet" type="text/css" href="css\kaoqin.css">
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
<div id="container">
			<div class="header"><h1>用户密码修改</h1></div>
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
			<div style="height:20px;  float:right" >
			</div>
				<br>
				<form action="pwdmodifyServlet" method="post">
				<table class="table4_2" align="center" width="496" border="100" cellSpacing=1 style="border:1pt;font-size:15pt;"height="31">
		    	<input type="hidden" name="pwdmofify" value="0"/><br>
		   		<tr><td>用户名：</td><td><input type="text" name="student_name" placeholder="请输入用户名"/></td></tr>
		   		<tr><td> 密 &nbsp;&nbsp;&nbsp;码：</td><td><input type="text" name="password" placeholder="请输入密码"/></td></tr>
		    	<tr><td colspan="2" align="center">
		    	<input type="submit" value="修改" class="button gray"/>
		    	<input type="reset" value="重置"  class="button red"/></td></tr>
				</table>
				</form>
			</div>
		</div>
		<jsp:include page="/views/foot.jsp"/>
  </body>
</html>
