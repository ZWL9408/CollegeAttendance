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
		<div class="container">
			<div class="header"><h1>出勤记录如下</h1></div>
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
			  <table class="table4_2" align="center" width="98%" border="100" cellSpacing=1 style="border:1pt;font-size:15pt;"height="31">
			  	<tr><td>学生学号</td>
				  	<td>学生姓名</td>
				  	<td>课程名称</td>
				  	<td>考勤时间</td>
				  	<td>出席情况</td>
					</tr>
			    <%
			    		response.setCharacterEncoding("UTF-8");
						request.setCharacterEncoding("UTF-8");
						page pager = (page) request.getAttribute("pager");
						List<att_student> subResult = (List<att_student>) request.getAttribute("fuck");
						if (!subResult.isEmpty()) {
							for (int i = 0; i < subResult.size(); i++) {
							     att_student Absence=subResult.get(i);
					  			 out.print("<tr>");
					  			 out.print("<td>"+Absence.getStudent_id()+"</td>");
					  			 out.print("<td>"+Absence.getStudent_name()+"</td>");
					  			 out.print("<td>"+Absence.getCourse_name()+"</td>");
					  			 out.print("<td>"+Absence.getAtt_time()+"</td>");
					  			 out.print("<td>"+Absence.getOperation()+"</td>");
			    		out.print("</tr>");
			    		}}
			    %>
			    
			  </table>  
			</div>
			<div class="right1">
			<span style="width:100%; text-align:center; display:block;">
				<font size="3pt">
					总<%=pager.getTotalRecord()%>条记录
					|总<%=pager.getTotalPage()%>页
					|当前<%=pager.getCurrentPage() + 1%>页
					|每页<%=pager.getPageSize()%>条| 
					<%  int last = pager.getCurrentRecord() - pager.getPageSize();
						int next = pager.getCurrentRecord() + pager.getPageSize();
						int currentRecord;
						if (last < 0) {
							out.println("首页|");
						} else {
							out.print("<a href='leaveServlet?currentRecord="+last+"&methodName=1'>上一页</a>|");
						}
						if (next >= pager.getTotalRecord()) {
							out.println("尾页|");
						} else {
							out.print("<a href='leaveServlet?currentRecord="+next+"&methodName=1'>下一页</a>|");
						}
					%> 
				</font> 
				</span>
			</div>
		</div>
		<jsp:include page="/views/foot.jsp"/>
  </body>
</html>
