<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@page import = "java.sql.Connection" %>
<%@page import = "java.sql.ResultSet" %>
<%@page import = "java.sql.DriverManager" %>
<%@page import = "java.sql.SQLException" %>
<%@page import = "java.sql.Statement" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
          	Visits:<span style="color: #337ab7;  font-weight: 800;">
          	
          	<%		
          	String strIP = "";
    		if (request.getHeader("x-forwarded-for") == null) { 
    			strIP = request.getRemoteAddr(); 
    		} 
    		else
    		{
    		    strIP = request.getHeader("x-forwarded-for"); 
    	    }
    		String dbDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    		Class.forName(dbDriver).newInstance();     		
    		Connection conn;
    	    Statement stmt;
    	    ResultSet rs;	
    	    String strValue="";
    	    String sql1 ="select count(*) as cc from userAccess";
    	    try {
    			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databasename=BIOTCGA;user=sa;password=gxqktzwq");
    			stmt = conn.createStatement();
    			rs = stmt.executeQuery(sql1);    			
    			while (rs.next()) {
    				strValue = rs.getString("cc");	
    			}
    			if (stmt != null) {
    				stmt.close();
    				stmt = null;
    			}
    			if (conn != null) {
    				conn.close();
    				conn = null;
    			}
    		} 
    	    catch (SQLException e) 
    		{	
    	    	strIP=e.toString();
    		}
    		
		
		%> 
    <label id="xstuNo" name="xstuNo" ><%=strValue%></label>
    </span>
    </div> 
</body>
</html>