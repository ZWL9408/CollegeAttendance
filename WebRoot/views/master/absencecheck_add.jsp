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
$(document).ready(function(){
$(".flip3").click(function(){
$(".panel3").toggle();
});
});
$(document).ready(function(){
$(".flip4").click(function(){
$(".panel4").toggle();
});
});
$(document).ready(function(){
$(".flip5").click(function(){
$(".panel5").toggle();
});
});
$(document).ready(function(){
$(".flip6").click(function(){
$(".panel6").toggle();
});
});
</script>
  </head>
  <body>
  <jsp:include page="/views/head.jsp"/>
		<div id="container">
			<div id="header"><h1>补充学生请假信息</h1></div>
			<div id="left" style="background-color:#F0FFFF;">
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
			<div id="right">
			<div style="height:20px;  float:right" ></div>
			    <br><hr>
				<form action="absencecheckServlet" method="post" id="form" onSubmit="return validate()" >
				<table align="center" width="800" border="100" cellSpacing=1 style="border:1pt;font-size:15pt;"height="31">
				<input type="hidden" name="methodName" value="0"/><!-- absenceServlet使用insert方法 -->
				<h4><tr><td>编号 ：</td><td><input type="text" name="student_id" class="{required:true}" title="编号必须为数字"/></td>
					    <td>请假人：</td><td><input type="text" name="leave_person"/></td></tr></h4>
				<h4><tr><td>请假时间：</td><td><input type="date" name="leave_time"/></td>
					   <td> 开始时间: </td><td><input type="date" name="start_time"/></td></tr></h4>
				<h4><tr><td>请假原因 ：</td><td colspan="3"><textarea rows="3" cols="70" name="leave_reason"></textarea></td></tr></h4>
				<h4><tr><td>状态 ：</td><td colspan="3" align="center"><input type="radio" name="status" value="已审核" style="margin-left: 20px" />是
				            <input type="radio" name="status" value="null" style="margin-left: 20px"/>否</td></tr></h4>
				<h4><tr><td>处理时间 ：</td><td><input type="date" name="deal_time"/></td>
					   <td> 处理人 ：</td><td><input type="text" name="deal_person"/></td></tr></h4>
				<h4><tr><td>处理结果 ：</td><td colspan="3"><textarea rows="3" cols="70" name="deal_result"></textarea></td></tr></h4>
				<tr><td colspan="4" align="center"><input type="submit" value="提交" style="margin-left: 30px" />
				 		<input type="button" value="取消" style="margin-left: 30px"
				 		onclick="javascript:window.location.href='absencecheckServlet?methodName=<%=1 %>';"/></td></tr>
				 </table>
				 </form>
				  <br>  										
				  </center>
			</div>
		</div>
		<jsp:include page="/views/foot.jsp"/>
  </body>
</html>
