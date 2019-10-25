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
	<script type="text/javascript">
    function confirmdialog(){
       if(window.confirm("您确定要删除此条信息？")){
       return true;
       }
       else{
     //  alert("取消删除！");
       return false;
       }      
    }
	 </script>
  </head>
  <body>
  <jsp:include page="/views/head.jsp"/>
		<div class="container">
			<div class="header"><h1>老师信息如下：</h1></div>
			<div class="left" style="background-color:#F0FFFF;">
			<table>
				<br/>
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
	  		<table class="table4_2" align="center" width="70%" border="100" cellspacing="1" style="border:1pt dashed; font-size:15pt;" height="31">
	  		<tr>
	  			<td>姓名</td>
	  			<td>性别 </td>
	  			<td>密码</td>
	  			<td>操作</td>
	  		</tr>
	  		<%
	  			response.setCharacterEncoding("UTF-8");
	  			request.setCharacterEncoding("UTF-8");
	  			page pager = (page)request.getAttribute("pager");
	  			List<teacher> subteacher=(List<teacher>)request.getAttribute("subteacher");
	  			if(!subteacher.isEmpty()){
	  				for(int i=0;i<subteacher.size();i++){
	  			 		teacher Teacher=subteacher.get(i);
	  			 		out.print("<tr>");
	  			 		out.print("<td>"+Teacher.getName()+"</td>");
	  			 		out.print("<td>"+Teacher.getGender()+"</td>");
	  			 		out.print("<td>"+Teacher.getPassword()+"</td>");
	  	 	%>
	  	 		<td>
	  	 			<a class="button red" href="teacherServlet?name=<%=Teacher.getName()%>&Teacher=<%=2%>"
	  	 	 			onclick="return confirmdialog()"> 删除</a>
	  	 			<a class="button blue" href="teacherServlet?name=<%=Teacher.getName()%>&Teacher=<%=4%>">
	  	 				修改</a>
	  	 		</td>
	  	 <%out.print("</tr>");}}%>
	  	 </table>
	  	 
		</div>
		<div class="right1">
		<center><span>
	  		<font>
			总<%=pager.getTotalRecord()%>条记录
			|总<%=pager.getTotalPage()%>页
				|当前<%=pager.getCurrentPage() + 1%>页|每页<%=pager.getPageSize()%>条| <%
			int last = pager.getCurrentRecord() - pager.getPageSize();
			int next = pager.getCurrentRecord() + pager.getPageSize();
			int currentRecord;
			if (last < 0) {
				out.println("首页|");
			} else {
				out.print("<a href='teacherServlet?currentRecord=" + last
						+ "&Teacher=1'>上一页</a>|");
			}
			if (next >= pager.getTotalRecord()) {
				out.println("尾页|");
			} else {
				out.print("<a href='teacherServlet?currentRecord=" + next
						+ "&Teacher=1'>下一页</a>|");
			}
			%> 
	  		</font>
	  	</span></center>
	 	</div>
	 	</div>
	<jsp:include page="/views/foot.jsp"/>
  </body>
</html>
