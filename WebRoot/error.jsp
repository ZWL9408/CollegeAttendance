<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'error.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript"> 
var t = 5; 
function countDown(){ 
var time = document.getElementById("time"); 
t--; 
time.value=t; 
if (t<=0) { 
location.href="index.jsp"; 
clearInterval(inter); 
}; 
} 
var inter = setInterval("countDown()",1000); 
//window.onload=countDown; 
</script> 
  </head>
  
 <body onload="countDown()"> 
	登陆错误，5s后返回登陆页面<input type="text" readonly="true" value="5" id="time"/> 
  </body>
</html>
