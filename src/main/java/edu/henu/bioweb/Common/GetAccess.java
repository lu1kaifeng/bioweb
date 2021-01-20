package edu.henu.bioweb.Common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAccess extends HttpServlet{
public GetAccess() {
 super();
}
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
	String strIP = "";
	if (request.getHeader("x-forwarded-for") == null) { 
		strIP = request.getRemoteAddr(); 
	} 
	else
	{
	    strIP = request.getHeader("x-forwarded-for"); 
    }
    Connection conn;
    Statement stmt;
    ResultSet rs;	
    
    String sql = "insert into userAccess(userIP) values('"+strIP+"')";
    
    sql = "select userIP from userAccess";
    try {
		//conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databasename=BIOTCGA;user=sa;password=gxqktzwq");
		conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databasename=BIOTCGA;user=sa;password=123");
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		sql = "select userIP from userAccess";		
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			String id = rs.getString("userIP");
			
		}
		if (rs != null) {
			rs.close();
			rs = null;
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
	}
    String error="";
    if(error!=""){			
		request.setAttribute("error", error);
		request.getRequestDispatcher("/DatabaseList.jsp").forward(request, response);
	}else{
		request.setAttribute("IPList", strIP);
		request.getRequestDispatcher("/DatabaseList.jsp").forward(request, response);
	}
  }
}

