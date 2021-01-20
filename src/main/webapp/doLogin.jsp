<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" 
    pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%	
	String username="";
	String password="";
	request.setCharacterEncoding("utf-8");
	username=request.getParameter("txtUserName");
	password=request.getParameter("txtPwd");
	if("admin".equals(username) && "mbi".equals(password)){
		session.setAttribute("username",  "admin");
		out.println("<script>window.location.href='Good.jsp'</script>");
	}	
	else
	{
		out.println("<script>window.location.href='Login.jsp'</script>");
	}
%>
</body>
</html>