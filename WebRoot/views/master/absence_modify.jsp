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
</script>
  </head>
  <body>
  <jsp:include page="/views/head.jsp"/>
		<div class="container">
			
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
			<div style="height:20px;  float:right" ></div>  
			<h3><center>要修改的学生信息如下</center></h3>
   <table class="table4_2" align="center" width="800" border="100" cellSpacing=1 style="border: 1pt dashed ; font-size: 15pt;" height="31">
    <tr>
  	<td>编号</td>
    <td>请假人</td>
    <td>请假时间</td>
    <td>开始时间</td>
    <td>请假原因</td>
    <td>请假天数</td>
    <td>状态</td>
    <td>处理时间</td>
    <td>处理人</td>
    </tr>
    <%
       int id=0;
       ArrayList<absence> result=new ArrayList<absence>();
       result=(ArrayList<absence>)request.getAttribute("result");
       if(!result.isEmpty()){
       for(int i=0;i<result.size();i++){
            absence ab=result.get(i);
    		id=ab.getStudent_id();
    		out.print("<tr>");
    		out.print("<td>"+ab.getStudent_id()+"</td>");
    		out.print("<td>"+ab.getLeave_person()+"</td>");
    		out.print("<td>"+ab.getLeave_time()+"</td>");
    		out.print("<td>"+ab.getStart_time()+"</td>");
    		out.print("<td>"+ab.getLeave_reason()+"</td>");
    		out.print("<td>"+ab.getStatus()+"</td>");
    		out.print("<td>"+ab.getDeal_time()+"</td>");
    		out.print("<td>"+ab.getDeal_person()+"</td>");
    		out.print("<td>"+ab.getDeal_result()+"</td>");
    		out.print("</tr>");
            }
            }
     %>
    </table>
    <h3><center>将学生信息更改为：</center></h3>
 <form action="absenceServlet" method="post" id="form" onSubmit="return validate()">
 <table class="table4_2" align="center" width="800" border="100" cellSpacing=1 style="border:1pt;font-size:15pt;"height="31">
 <input type="hidden" name="methodName" value="3"/>
 <h4><tr><td>编号：</td><td><input type="text" name="student_id" value="<%=id %>"/></td>
	     <td>请假人：</td><td><input type="text" name="leave_person" placeholder="请输入请假人" /></td></tr></h4>
 <h4><tr><td>请假时间：</td><td><input type="date" name="leave_time"  /></td>
	  	 <td>开始时间：</td><td><input type="date" name="start_time" /></td></tr></h4>
 <h4><tr><td>请假原因：</td><td colspan="3"><input type="text" style="width:500px" name="leave_reason" /></td></tr>
	  <tr><td>状态：</td><td colspan="3"><input type="radio" name="status" value="已审核" style="margin-left: 20px" />是
			            	 <input type="radio" name="status" value="null" style="margin-left: 20px"/>否</td></tr></h4>
 <h4><tr><td>处理时间：</td><td><input type="date" name="deal_time"/></td>
	  	 <td>处理人：</td><td><input type="text" name="deal_person" placeholder="请输入处理人"/></td></tr></h4>
 <h4><tr><td>处理结果：</td><td colspan="3"><input type="text" style="width:500px" name="deal_result"/></td></tr></h4>
 <tr><td colspan="4" align="center">
 <input class="button blue" type="submit" value="批准"/>
 <input class="button pink" type="reset" value="重置"/></td></tr>
  </table>
  </form>
			</div>
		</div>
		<jsp:include page="/views/foot.jsp"/>
  </body>
</html>
