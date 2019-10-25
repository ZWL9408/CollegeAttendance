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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	div#container {
		width: 100%;
		heigth:100%;
		overflow: hidden;
	}
	div#header {
		float: right;
		width: 80%;
		height:80px;
		text-align: center;
	}
	div#left{
		float:left;
		backgroud-color:#D1EEEE;
		height:530px;
		width:20%;
	}
	div#right {
		float: left;
		background-color: #F0F8FF;
		height: 450px;
		width: 80%;
	}
	</style>
	<script language="javascript">
function next()
{
document.write("<a href='javascript:history.go(-1)'>后退</a>   &nbsp;&nbsp;<a href='javascript:history.go(0)'>刷新</a>&nbsp;&nbsp;<a href='javascript:history.go(1)'>前进</a>   <form>   <input name='ht' type='button' onclick='javascript:history.go(-1)' value='后退' />   <input name='sx' type='button' onclick='javascript:history.go(0)' value='刷新' />   <input name='qj' type='button' onclick='javascript:history.go(1)' value='前进' />   </form> ");
}
</script>
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
</script>
  </head>
  <body>
  <jsp:include page="/views/head.jsp"/>
		<div id="container">
			<div id="header"><h1>出勤记录如下</h1></div>
			<div id="left" style="background-color:#F0FFFF;">
			<table>
				<tr>
				<p class="flip" align="center" style="color:white;background-color:#A9E2F3;">个人信息</p>
				<div class="panel" style="display:none" align="center">
                <input type="button" value="查看" onclick="javascript:window.location.href='personalServlet?personal=<%=1 %>';"/>
				</div>
				</tr>
				<tr>
                <p class="flip1" align="center" style="color:white;background-color:#A9E2F3;">修改密码</p>
                <div class="panel1" style="display:none" align="center">
                <input type="button" value="管理" onclick="javascript:window.location.href='views/student/stu_pwd.jsp';"/>
				</div>
				</tr>
				<tr>
                <p class="flip2" align="center" style="color:white;background-color:#A9E2F3;">请假申请管理</p>
                <div class="panel2" style="display:none" align="center">
                <input type="button" value="查看" onclick="javascript:window.location.href='leaveServlet?methodName=<%=1 %>';"/><br>
				<input type="button" value="输入" onclick="javascript:window.location.href='views/student/leave_add.jsp'";/>
				</div>
				</tr>
            </table>
			</div>
			<div id="right">
			<div style="height:20px;  float:right"></div>
			  <table  align="center" width="100%" border="100" cellSpacing=1 style="border:1pt;font-size:15pt;"height="31">
			  	<tr><td>学生学号</td>
				  	<td>学生姓名</td>
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
					  			 out.print("<td>"+Absence.getAtt_time()+"</td>");
					  			 out.print("<td>"+Absence.getOperation()+"</td>");
			    		out.print("</tr>");
			    		}}
			    %>
			    
			  </table>  
			  <br>
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
			  <br>
			</div>
		</div>
		<jsp:include page="/views/foot.jsp"/>
  </body>
</html>
