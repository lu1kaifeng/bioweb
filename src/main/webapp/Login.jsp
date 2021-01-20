<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" 
    pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<style>
td
{
	border:solid 1px #aa6688;
}
input
{
	border:solid 1px #882200;
}
</style>
</head>
<body>
<div style="width:610px;margin:auto;margin-top:200px">
<form action="doLogin.jsp" method="post">
<table style="width:600px;">
	<tr>
		<td>User name</td>
		<td>
			<input type="text" id="txtUserName" name="txtUserName" />
		</td>
	</tr>
	<tr>
		<td>Password</td>
		<td>
			<input type="password" id="txtPwd" name="txtPwd" />
		</td>
	</tr>
	<tr>
		<td colspan="2" style="text-align:center">
			<input type="submit" id="btnSubmit" value="Submit" />
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>