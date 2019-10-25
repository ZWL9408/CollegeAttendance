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
			<div class="header"><h1>进行请假申请</h1></div>
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
				     <br>
					 <center>
					 <form action="leaveServlet" method="post" id="form" onSubmit="return validate5()" >
					 <table class="table4_2" align="center" width="496" border="100" cellSpacing=1 style="border:1pt;font-size:15pt;"height="31">
					 <input type="hidden" name="methodName" value="0"/><!-- absenceServlet使用insert方法 -->
					 <h4><tr><td>编号 ：</td><td><input type="text" name="student_id" class="{required:true}" placeholder="请输入学号"></input></td></tr></h4>
					 <h4><tr><td>请假人：</td><td><input type="text" name="leave_person" placeholder="请输入请假人" /></td></tr></h4>
					 <h4><tr><td>请假时间：</td><td><input type="date" name="leave_time" /></td></tr></h4>
					 <h4><tr><td>开始时间: </td><td><input type="date" name="start_time"/></td></tr></h4>
					 <h4><tr><td >请假原因 ：</td><td><textarea rows="3" cols="20" name="leave_reason"></textarea></td></tr></h4>
					 <tr><td colspan="2" align="center">
					 <input class="button gray" type="submit" value="申请" style="margin-left: 30px"/>
					 <input class="button red" type="button" value="取消" style="margin-left: 30px" onclick="javascript:window.location.href='leaveServlet?methodName=<%=1%>';"/></td></tr>
					 </table>
					 </form>
					 <br>  										
					 </center>
				</div>
			</div>
		<jsp:include page="/views/foot.jsp"/>
  </body>
</html>
