<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link rel="stylesheet" type="text/css" href="/css/kaoqin.css">
<script type="text/javascript" src="js/pc.js"></script>
  <div style="width:100%; height:100px; background-image:url(img/header.png); background-repeat:no-repeat;">
  <table><tr><br></tr><tr><br></tr><tr>
  <p align="right"><font color="black">
       当前时间：<span id="div1"></span></font></p></tr>
  </table> 
  </div>
 <div style="width:100%; height:40px; background-color:#A9E2F3">
<table width="100%";height="40px"; border="0";>
<tr>
 <td  align="center">
 当前登录用户：<%=session.getAttribute("user")%></td>
   <td align="center" valign="middle">
   <table width="500px";border="0";>
   <tr>
   <td><img src="img/zhuye.jpg" onclick="javascript:window.location.href='index.jsp';" style="width:25px; height:25px;background-repeat: no-repeat;background-size:cover;"></td>
   <td><img src="img/houtui.jpg" style="width:25px; height:25px;background-repeat: no-repeat;background-size:cover;"  onclick="window.history.back()"/> </td>
   <td><img src="img/qianjin.jpg" style="width:25px; height:25px;background-repeat: no-repeat;background-size:cover;" onclick="window.history.forward()"/></td>
   <td><img src="img/shuaxin.jpg" style="width:25px; height:25px;background-repeat: no-repeat;background-size:cover;" onclick="window.location.reload()"/></td>
   </tr>
   </table>
   </td>
 <td align="right" valign="middle"><a class="button black" href="javascript:window.location.href='index.jsp';">退出系统</a></td>
 </tr>
 </table>
 </div>