<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>welcome</title>
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/selectpick.css">
  </head> 
  <body>

    <form action="loginServlet" method="post"><tr>
   	<div style="background-image:url(img/bg.jpg);">
		<div class="login-box">
			<div class="login-container" style="background-color:#F0FFFF; ">
				<p>高校点名考勤系统</p>
				<div class="input-row">
					<label class="username" for="username"></label>
					<input id="username" type="text" name="username" placeholder="请输入用户名">
				</div>
				<div class="input-row">
					<label class="password" for="password"></label>
					<input id="password" type="password" name="password" placeholder="请输入密码">
				</div>
				<div class="input-row select-row">
					<select id="role-select" name="role">
						<option value="default" selected>请选择您的角色</option>
						<option value="2">学生</option>
						<option value="1">任课教师</option>
						<option value="0">系统管理员</option>
					</select>
				</div>
				<div class="input-row btn-row">
					<input type="submit" class="submit btn" name="Submit" value="登陆">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" class="reset btn" name="Reset" value="重置">
				</div>
			</div>
		</div>
	</div>
	</form>
    
  </body>
</html>
