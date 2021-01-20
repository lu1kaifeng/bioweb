package edu.henu.bioweb.Common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class MELACon {
	public String MelaRODBC="db<-odbcDriverConnect('driver={SQL Server};server=(local);database=MELADB;user=sa;pwd=gxqktzwq')";
	//public String MelaRODBC = "db<-odbcDriverConnect('driver={SQL Server};server=(local);database=MELADB;user=sa;pwd=123')";
	public String MelaJDBC="jdbc:sqlserver://localhost:1433; databasename=MELADB;user=sa;password=gxqktzwq";
	//public String MelaJDBC = "jdbc:sqlserver://localhost:1433; databasename=MELADB;user=sa;password=123";
	 
	
	public String GetRString(String strSql, String strSurvival, String res, String strDB)
	{
		String strR="-1";
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
		
		Context initCtx;
		Context ctx;
		Object  obj;
		DataSource ds = null;		
        try {
        	initCtx = new InitialContext();
        	ctx = (Context)initCtx.lookup("java:comp/env");
        	obj = (Object)ctx.lookup(strDB);//jdbc/SSBRCA2
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
					String strTime = "";
					String strEvent = "";
					String strValue = rs.getString("genValue");
					if(i==0)
					{	
						if(strSurvival.equals("0"))
						{
							strTime = rs.getString("OS");
							strEvent = rs.getString("OS_Event");
							strR = "";
							strR = res + "<-data.frame(OS=c("+strTime+"),OS_Event=c("+strEvent+"),genValue=c("+strValue+"))\n";
						}					
						else if(strSurvival.equals("1"))
						{
							strTime = rs.getString("DFI");
							strEvent = rs.getString("DFI_Event");
							strR = "";
							strR = res + "<-data.frame(DFI=c("+strTime+"),DFI_Event=c("+strEvent+"),genValue=c("+strValue+"))\n";
						}
						else if(strSurvival.equals("2"))
						{
							strTime = rs.getString("PFI");
							strEvent = rs.getString("PFI_Event");
							strR = "";
							strR = res + "<-data.frame(PFI=c("+strTime+"),PFI_Event=c("+strEvent+"),genValue=c("+strValue+"))\n";
						}
						else if(strSurvival.equals("3"))
						{
							strTime = rs.getString("DSS");
							strEvent = rs.getString("DSS_Event");
							strR = "";
							strR = res + "<-data.frame(DSS=c("+strTime+"),DSS_Event=c("+strEvent+"),genValue=c("+strValue+"))\n";
						}
						else if(strSurvival.equals("4"))
						{
							strTime = rs.getString("PFS");
							strEvent = rs.getString("PFS_Event");
							strR = "";
							strR = res + "<-data.frame(PFS=c("+strTime+"),PFS_Event=c("+strEvent+"),genValue=c("+strValue+"))\n";
						}
						else if(strSurvival.equals("5"))
						{
							strTime = rs.getString("DMFS");
							strEvent = rs.getString("DMFS_Event");
							strR = "";
							strR = res + "<-data.frame(DMFS=c("+strTime+"),DMFS_Event=c("+strEvent+"),genValue=c("+strValue+"))\n";
						}
						else if(strSurvival.equals("6"))
						{
							strTime = rs.getString("DFS");
							strEvent = rs.getString("DFS_Event");
							strR = "";
							strR = res + "<-data.frame(DFS=c("+strTime+"),DFS_Event=c("+strEvent+"),genValue=c("+strValue+"))\n";
						}
						else if(strSurvival.equals("7"))
						{
							strTime = rs.getString("DRFS");
							strEvent = rs.getString("DRFS_Event");
							strR = "";
							strR = res + "<-data.frame(DRFS=c("+strTime+"),DRFS_Event=c("+strEvent+"),genValue=c("+strValue+"))\n";
						}
						else if(strSurvival.equals("8"))
						{
							strTime = rs.getString("MFS");
							strEvent = rs.getString("MFS_Event");
							strR = "";
							strR = res + "<-data.frame(MFS=c("+strTime+"),MFS_Event=c("+strEvent+"),genValue=c("+strValue+"))\n";
						}
						else if(strSurvival.equals("9"))
						{
							strTime = rs.getString("LMFS");
							strEvent = rs.getString("LMFS_Event");
							strR = "";
							strR = res + "<-data.frame(LMFS=c("+strTime+"),LMFS_Event=c("+strEvent+"),genValue=c("+strValue+"))\n";
						}
						else if(strSurvival.equals("10"))
						{
							strTime = rs.getString("BMFS");
							strEvent = rs.getString("BMFS_Event");
							strR = "";
							strR = res + "<-data.frame(BMFS=c("+strTime+"),BMFS_Event=c("+strEvent+"),genValue=c("+strValue+"))\n";
						}
						else if(strSurvival.equals("11"))
						{
							strTime = rs.getString("RFS");
							strEvent = rs.getString("RFS_Event");
							strR = "";
							strR = res + "<-data.frame(RFS=c("+strTime+"),RFS_Event=c("+strEvent+"),genValue=c("+strValue+"))\n";
						}
					}
					else
					{
						if(strSurvival.equals("0"))
						{
							strTime = rs.getString("OS");
							strEvent = rs.getString("OS_Event");
						}
						if(strSurvival.equals("1"))
						{
							strTime = rs.getString("DFI");
							strEvent = rs.getString("DFI_Event");
						}
						if(strSurvival.equals("2"))
						{
							strTime = rs.getString("PFI");
							strEvent = rs.getString("PFI_Event");
						}
						if(strSurvival.equals("3"))
						{
							strTime = rs.getString("DSS");
							strEvent = rs.getString("DSS_Event");
						}
						if(strSurvival.equals("4"))
						{
							strTime = rs.getString("PFS");
							strEvent = rs.getString("PFS_Event");
						}
						if(strSurvival.equals("5"))
						{
							strTime = rs.getString("DMFS");
							strEvent = rs.getString("DMFS_Event");
						}
						if(strSurvival.equals("6"))
						{
							strTime = rs.getString("DFS");
							strEvent = rs.getString("DFS_Event");
						}
						if(strSurvival.equals("7"))
						{
							strTime = rs.getString("DRFS");
							strEvent = rs.getString("DRFS_Event");
						}
						if(strSurvival.equals("8"))
						{
							strTime = rs.getString("MFS");
							strEvent = rs.getString("MFS_Event");
						}
						if(strSurvival.equals("9"))
						{
							strTime = rs.getString("LMFS");
							strEvent = rs.getString("LMFS_Event");
						}
						if(strSurvival.equals("10"))
						{
							strTime = rs.getString("BMFS");
							strEvent = rs.getString("BMFS_Event");
						}
						if(strSurvival.equals("11"))
						{
							strTime = rs.getString("RFS");
							strEvent = rs.getString("RFS_Event");
						}
						strR += "res1<-c("+strTime+","+strEvent+","+strValue+")\n";
						strR +=res + "<-rbind("+res+",res1)\n";
					}					
					i++;
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
		return strR;
	}	
	public String GetPath(String strSql, String strSplit, String strPath, String strGen, String strAvg, String strSurvival, String strmax, String tip) throws RserveException, REXPMismatchException 
	{		
		String strTip = "";
		//String strSql, String strSurvival, String res, String strDB
		strSql = GetRString(strSql, strSurvival,"res", "jdbc/SSSKCM");
		if(strSql.equals("-1"))
		{
			strTip = "Number of SKCM patients was less than 4 in "+tip+", No meaningful output.";
            return strTip;
		}
		RConnection re = new RConnection("127.0.0.1");	
        try {   
        	re.eval("library(survival)");
            re.eval(strSql);
            REXP x = re.eval("r<-nrow(res)");
            int i = x.asInteger();
            
            if(i<4 && i>0)
            {            	 
                strTip = "Number of SKCM patients was less than 4, No meaningful output.";
                return strTip;
            }
            else if(i==0)
            {  
                strTip = "Number of SKCM patients was less than 4, No meaningful output.";
                return strTip;
            }
            else
            {
            	strTip = " ";
            }
            re.eval("attach(res)");
            
            String strLab1="";
            String strLab2="";            
            switch(strSplit)
            {
            	case "0":
            		strLab1="Lower 25%";
            		strLab2="Other  75%";
            		break;
            	case "1":
            		strLab1="Upper 25%";
            		strLab2="Other  75%";
            		break;
            	case "2":
            		strLab1="Lower 30%";
            		strLab2="Other  70%";
            		break;
            	case "3":
            		strLab1="Upper 30%";
            		strLab2="Other  70%";
            		break;
            	case "4":
            		strLab1="Upper 50%";
            		strLab2="Other  50%";
            		break;
            	case "5":
            		strLab1="Upper 25%";
            		strLab2="Other  25%";
            		break;
            	case "6":
            		strLab1="Upper 30%";
            		strLab2="Other  30%";
            		break;
            	case "7":
            		strLab1="Lower 50%";
            		strLab2="Other  50%";
            		break;
            }
            
            
            if(strSplit.equals("0") || strSplit.equals("2") || strSplit.equals("7"))
            {
            	re.eval("gen<-res[order(res[,3]),]");
            }
            else
            {
            	re.eval("gen<-res[order(-res[,3]),]");
            }
                        
            if(strSplit.equals("0") || strSplit.equals("1"))
            {
            	re.eval("r1<-round(r/4)");
            	re.eval("r2<-r-r1");
            	re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}else{gen.up<-gen[1:(r1+1),];gen.low<-gen[(r1+2):r,]}}else{gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}");
            }
            else if(strSplit.equals("2") || strSplit.equals("3"))
            {
            	re.eval("r1<-round(r*0.3)");
            	re.eval("r2<-r-r1");
            	re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}else{gen.up<-gen[1:(r1+1),];gen.low<-gen[(r1+2):r,]}}else{gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}");
            }
            else if(strSplit.equals("4") || strSplit.equals("7"))
            {
            	re.eval("r1<-round(r/2)");
            	re.eval("r2<-r-r1");
            	re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}else{gen.up<-gen[1:(r1+1),];gen.low<-gen[(r1+2):r,]}}else{gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}");
            }
            else if(strSplit.equals("5"))
            {
            	re.eval("r1<-round(r/4)");
            	re.eval("r2<-r-r1");
            	re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];}else{gen.up<-gen[1:(r1+1),];}}else{gen.up<-gen[1:r1,];}");            	
            	re.eval("if(gen[r2+1,3]==gen[r2,3]){suba<-gen[r2+1,3]-gen[r2+2,3];subb<-gen[r2,3]-gen[r2-1,3];if(abs(suba)>abs(subb)){gen.low<-gen[(r2+2):r,];}else{gen.low<-gen[(r2+1):r,];}}else{gen.low<-gen[(r2+1):r,];}");
            }
            else if(strSplit.equals("6"))
            {
            	re.eval("r1<-round(r*0.3)");
            	re.eval("r2<-r-r1");
            	re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];}else{gen.up<-gen[1:(r1+1),];}}else{gen.up<-gen[1:r1,];}");            	
            	re.eval("if(gen[r2+1,3]==gen[r2,3]){suba<-gen[r2+1,3]-gen[r2+2,3];subb<-gen[r2,3]-gen[r2-1,3];if(abs(suba)>abs(subb)){gen.low<-gen[(r2+2):r,];}else{gen.low<-gen[(r2+1):r,];}}else{gen.low<-gen[(r2+1):r,];}");        
            }
            
            re.eval("gen.up[,3]=1");
            re.eval("gen.low[,3]=0");
            re.eval("gen<-rbind(gen.up,gen.low)");            
            re.eval("gen.surv<-survfit(Surv(gen[,1],gen[,2])~gen[,3])");
            re.eval("gen.dif<-survdiff(Surv(gen[,1],gen[,2])~gen[,3])");            
            re.eval("jpeg('"+strPath+"',width=1000,height=1000)");            
            re.eval("par(cex=2.5,font=2,font.axis=2,cex.axis=1.3,font.lab=2,cex.lab=1.3)");            
            re.eval("plot(gen.surv,xlab=\"Months(OS)\",ylab=\"Probability\",col=c(\"red\",\"green\"),xlim=c(0,"+strmax+"))");
            re.eval("p.cox<-summary(coxph(Surv(gen[,1],gen[,2])~gen[,3]))");            
            re.eval("p.val<-p.cox$coefficients[5]");
            re.eval("p.hr<-p.cox$coefficients[2]");
            re.eval("p.l<-p.cox$conf.int[3]");
            re.eval("p.t<-p.cox$conf.int[4]");
            re.eval("legend(\"topright\",legend=c(\""+strLab1+"\",\""+strLab2+"\"),text.col=c(\"green\",\"red\"),horiz=FALSE)");
            re.eval("if(p.val<0.0001){text("+strmax+"*0.16,0.2,\"p < 0.0001\")} else{text("+strmax+"*0.16,0.2,paste(\"p =\",round(p.val,4)))}");
            re.eval("text("+strmax+"*0.17,0.12,paste(\"HR =\",round(p.hr,4)))");
            re.eval("text("+strmax+"*0.35,0.05,paste(\"( 95%CI,\",round(p.l,4),\"-\",round(p.t,4),\")\"))");
            re.eval("text("+strmax+"*0.4,1.0,\""+strAvg+"\")");
            re.eval("text("+strmax+"*0.8,0.05,\"OSskcm\",font=4)");             
            re.eval("dev.off()");  
        }
        catch (REngineException e) {  
            e.printStackTrace();  
        } finally {  
            re.close();     
        } 
        return strTip;
	}
	
}
