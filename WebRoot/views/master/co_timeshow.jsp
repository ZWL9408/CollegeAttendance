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
</script>
  </head>
  <body>
  <jsp:include page="/views/head.jsp"/>
		<div class="container">
			<div class="header"><h1>选择班级查看考勤</h1></div>
			<div class="left" style="background-color:#F0FFFF;">
			<table>
				<tr>
				<p class="flip" align="center" style="height:28px;background-image:url(img/celian.gif);background-repeat: no-repeat;background-size:cover;">个人信息管理</p>
				<div class="panel" style="display:none" align="center">
				<a class="button gray" href="javascript:window.location.href='personalServlet?personal=<%=0 %>';">查看个人信息</a><br/><br/>
				<a class="button gray" href="views/master/tea_pwd.jsp">修改密码</a>
				</div>
				</tr>
				<tr>
				<p class="flip1" align="center" style="height:28px;background-image:url(img/celian.gif);background-repeat: no-repeat;background-size:cover;">请假信息审核</p>
				<div class="panel1" style="display:none" align="center">
				<a class="button gray" href="javascript:window.location.href='absencecheckServlet?methodName=<%=1 %>';">管理学生请假申请</a><br/><br/>
				<a class="button gray" href="javascript:window.location.href='absenceServlet?methodName=<%=1 %>';">管理已审核请假申请</a><br/><br/>
				<a class="button gray" href="views/master/absence_add.jsp">补充学生请假</a><br/>
				</div>
				</tr>
				<tr>
				<p class="flip2" align="center" style="height:28px;background-image:url(img/celian.gif);background-repeat: no-repeat;background-size:cover;">班级考勤管理</p>
				<div class="panel2" style="display:none" align="center">
				<a class="button gray" href="javascript:window.location.href='clazzChooseServlet?clazzchoose=<%=1 %>';">查看班级</a><br/>
				</div>
				</tr>
            </table>
			</div>
			<div class="right">
			<div style="height:20px;  float:right"></div>
			  <table class="table4_2"  align="center" width="98%" border="100" cellSpacing=1 style="border:1pt;font-size:15pt;"height="31">
			  	<tr><td>课程名称</td>
				  	<td>考勤时间</td>
				  	<td>操作</td>
					</tr>
			    <%
			    		response.setCharacterEncoding("UTF-8");
						request.setCharacterEncoding("UTF-8");
						page pager = (page) request.getAttribute("pager");
						List<co_time> subResult = (List<co_time>) request.getAttribute("fuck");
						if (!subResult.isEmpty()) {
							for (int i = 0; i < subResult.size(); i++) {
							     co_time Absence=subResult.get(i);
					  			 out.print("<tr>");
					  			 out.print("<td>"+Absence.getCourse_name()+"</td>");
					  			 out.print("<td>"+Absence.getAtt_time()+"</td>");%>
					  	<td><a class="button blue" href="showstudentServlet?course_name=<%=Absence.getCourse_name() %>&att_time=<%=Absence.getAtt_time() %>&showstu=<%=2%>">
					  		查看该班考勤信息</a>
					  	</td>		 
					  			 
				<%
			    		out.print("</tr>");}}
			    %>
			    
			  </table>  
			  <br>
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
			  <br>
			</div>
		</div>
		<jsp:include page="/views/foot.jsp"/>
  </body>
</html>
