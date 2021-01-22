package edu.henu.bioweb.Common;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GBMCom {
	public boolean isExistByGene(String strGene, String strGSE){
		boolean result=false;
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception ex)
		{
			
		}
		Connection conn;
		Statement stmt;
		ResultSet rs;
		String strSql="select count(*) as num from gen_"+strGSE+" where genName='"+strGene+"'"; 
		Context initCtx;
		Context ctx;
		Object  obj;
		DataSource ds = null;		
        try {
        	initCtx = new InitialContext();
        	ctx = (Context)initCtx.lookup("java:comp/env");
        	obj = (Object)ctx.lookup("jdbc/SSGBM");
            ds = (DataSource)obj;
            
        } catch (NamingException e) {
            e.printStackTrace();
        }
				
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(strSql);
				int i = 0;
				while (rs.next()) {
					String strnum = rs.getString("num");
					if(strnum.equals("0")){
						result=false;
					}
					else{
						result=true;
					}
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
			} catch (SQLException e) {
		}
		return result;
	}
}
