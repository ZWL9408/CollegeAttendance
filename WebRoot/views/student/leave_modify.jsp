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
				    <center><h2>要修改的请假信息如下：<h2/></center>
				    <table class="table4_2" align="center" width="60%" border="100" cellSpacing=1 style="border: 1px dashed ; font-size: 14pt;" height="31">
				    <tr>
				  	<td>编号</td>
				    <td>请假人</td>
				    <td>请假时间</td>
				    <td>开始时间</td>
				    <td>请假原因</td>
				    </tr>
				    <%
				    	int student_id=0;
				    	String leave_person=null;
				        ArrayList<absence> result=new ArrayList<absence>();
				        result=(ArrayList<absence>)request.getAttribute("result");
				        if(!result.isEmpty()){
				        	for(int i=0;i<result.size();i++){
				            	absence ab=result.get(i);
				    			student_id=ab.getStudent_id();
				    			leave_person=ab.getLeave_person();
				    			out.print("<tr>");
				    			out.print("<td>"+ab.getStudent_id()+"</td>");
				    			out.print("<td>"+ab.getLeave_person()+"</td>");
				    			out.print("<td>"+ab.getLeave_time()+"</td>");
				    			out.print("<td>"+ab.getStart_time()+"</td>");
				    			out.print("<td>"+ab.getLeave_reason()+"</td>");
				    			out.print("</tr>");
				            }
				         }
				     %>
				    </table>
				    <center><h2>将请假信息更改为：</h2></center>
				 	<form action="leaveServlet" method="post" onSubmit="return validate5()">
				 	<table class="table4_2" align="center" width="60%" border="100" cellSpacing=1 style="border:1pt;font-size:14pt;"height="31">
				 	<input type="hidden" name="methodName" value="3"/>
				 	<h4><tr><td>编号：</td><td><input type="text" name="student_id" value="<%=student_id %>"/></td><td>
				                请假人：</td><td><input type="text" name="leave_person" value="<%=leave_person %>" /></td></tr></h4>
				 	<h4><tr><td>请假时间：</td><td><input type="date" name="leave_time"/></td><td>
				 	        开始时间：</td><td><input type="date" name="start_time"/></td></tr></h4>
				 	<h4><tr><td>请假原因：</td><td colspan="3"><textarea rows="3" cols="40" name="leave_reason" placeholder="请输入请假理由"></textarea></td></tr></h4>
				 	<tr><td colspan="4" align="center">
				 	<input class="button gary" type="submit" value="修改"/>&nbsp;&nbsp;&nbsp;&nbsp;
				 	<input class="button red" type="reset" value="重置"/></tr></td>
				 	</table>
				 	</form>
			</div>
			</div>
		<jsp:include page="/views/foot.jsp"/>
  </body>
</html>
