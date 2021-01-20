package edu.henu.bioweb.Common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import edu.henu.bioweb.GenNameArray.*;

//ggsave֧��png, jpg, pdf

public class ComFun {
   public String strConRODBC = "db<-odbcDriverConnect('driver={SQL Server};server=(local);database=LUDB;user=sa;pwd=gxqktzwq')";
   //public String strConRODBC = "db<-odbcDriverConnect('driver={SQL Server};server=(local);database=LUDB;user=sa;pwd=123')";
   public String strConJDBC = "jdbc:sqlserver://localhost:1433; databasename=LUDB;user=sa;password=gxqktzwq";
   //public String strConJDBC = "jdbc:sqlserver://localhost:1433; databasename=LUDB;user=sa;password=123";
   
   public String strConR = "db<-odbcDriverConnect('driver={SQL Server};server=(local);database=tempLUDB;user=sa;pwd=gxqktzwq')";
   //public String strConR = "db<-odbcDriverConnect('driver={SQL Server};server=(local);database=tempLUDB;user=sa;pwd=123')";
   public String strConJ = "jdbc:sqlserver://localhost:1433; databasename=tempLUDB;user=sa;password=gxqktzwq";
   //public String strConJ = "jdbc:sqlserver://localhost:1433; databasename=tempLUDB;user=sa;password=123";
   
    public String MelaRODBC="db<-odbcDriverConnect('driver={SQL Server};server=(local);database=MELADB;user=sa;pwd=gxqktzwq')";
 	//public String MelaRODBC = "db<-odbcDriverConnect('driver={SQL Server};server=(local);database=MELADB;user=sa;pwd=123')";
 	public String MelaJDBC="jdbc:sqlserver://localhost:1433; databasename=MELADB;user=sa;password=gxqktzwq";
 	//public String MelaJDBC = "jdbc:sqlserver://localhost:1433; databasename=MELADB;user=sa;password=123";
 	
 	//List<String> stringList = Arrays.asList("a", "b", "c");
 	
 	public String GetSurvival(String strSurvival){
 		String strName ="";
 		switch(strSurvival){
 		case "0":
 			strName="OS";
 			break;
 		case "1":
 			strName="DFI";
 			break;
 		case "2":
 			strName="PFI";
 			break;
 		case "3":
 			strName="DSS";
 			break;
 		case "4":
 			strName="PFS";
 			break;
 		case "5":
 			strName="DMFS";
 			break;
 		case "6":
 			strName="DFS";
 			break;
 		case "7":
 			strName="DRFS";
 			break;
 		case "8":
 			strName="MFS";
 			break;
 		case "9":
 			strName="LMFS";
 			break;
 		case "10":
 			strName="BMFS";
 			break;
 		case "11":
 			strName="RFS";
 			break;
 		case "12":
 			strName="EFS";
 			break;
 		}
 		return strName;
 	}
 	
 	public String GetTip(int i, String gse, String geneName, String type)
 	{
 		String strTip="";
 		switch(i){
 		case 1:
 			strTip=" ";
 			break;
 		case 2:
 			//Number of PC patients you analyzed in at least one of the groups is less than 4, thus NO meaningful output returns.
 			strTip="Number of "+type+" patients you analyzed in at least one of the groups is less than 4 in "+gse+", thus no meaningful output returns.";
 			break;
 		case 3:
 			strTip=geneName+" dose not exist in "+gse+", unable to output.";
 			break;
 		case 4:
 			strTip="invalid input, please check Gene Symbol at https://www.ncbi.nlm.nih.gov/gene/.";
 			break;
 		}
 		return strTip;
 		
 	}
 	
 	public String GetGeneName(String str){
 		String strGeneName = "";
 		 GeneNormal gen = new GeneNormal();
		   if(!gen.NormalGenList.contains(str))
		   {
			   strGeneName = str.toUpperCase().trim().replaceAll(" ", "");
		   }
		   else
		   {
			   strGeneName=str;
		   }
 		return strGeneName;
 	}
 	
   public String PlotTCGA(String strSql, String strSplit, String strPath, String strGen, String intLen, String ppos, String hrpos, String cipos, String logpos, String tip) throws RserveException, REXPMismatchException
   {   
	   String strTip = "";
	   RConnection re = new RConnection("127.0.0.1");	
       try {     
       	re.eval("library(survival)");
           re.eval("library(RODBC)");
           re.eval(strConRODBC);            
           re.eval("res<-sqlQuery(db,\""+strSql+"\")");
           REXP x = re.eval("r<-nrow(res)");
           int i = x.asInteger();
           
           if(i<4 && i>0)
           {            	
               re.eval("odbcClose(db)");   
               strTip = "Number of lung cancer patients was less than 4, No meaningful output.";
               return strTip;
           }
           else if(i==0)
           {
           	   re.eval("odbcClose(db)");   
               strTip = "invalid input, please check Gene Symbol at https://www.ncbi.nlm.nih.gov/gene/.";
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
           	re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];gen.low<-gen[r1+1:r,]}else{gen.up<-gen[1:r1+1,];gen.low<-gen[r1+2:r,]}}else{gen.up<-gen[1:r1,];gen.low<-gen[r1+1:r,]}");
           }
           else if(strSplit.equals("2") || strSplit.equals("3"))
           {
           	re.eval("r1<-round(r*0.3)");
           	re.eval("r2<-r-r1");
           	re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];gen.low<-gen[r1+1:r,]}else{gen.up<-gen[1:r1+1,];gen.low<-gen[r1+2:r,]}}else{gen.up<-gen[1:r1,];gen.low<-gen[r1+1:r,]}");
           }
           else if(strSplit.equals("4") || strSplit.equals("7"))
           {
           	re.eval("r1<-round(r/2)");
           	re.eval("r2<-r-r1");
           	re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];gen.low<-gen[r1+1:r,]}else{gen.up<-gen[1:r1+1,];gen.low<-gen[r1+2:r,]}}else{gen.up<-gen[1:r1,];gen.low<-gen[r1+1:r,]}");
           }
           else if(strSplit.equals("5"))
           {
           	re.eval("r1<-round(r/4)");
           	re.eval("r2<-r-r1");            	
           	re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];}else{gen.up<-gen[1:r1+1,];}}else{gen.up<-gen[1:r1,];}");            	
           	re.eval("if(gen[r2+1,3]==gen[r2,3]){suba<-gen[r2+1,3]-gen[r2+2,3];subb<-gen[r2,3]-gen[r2-1,3];if(abs(suba)>abs(subb)){gen.low<-gen[r2+2:r,];}else{gen.low<-gen[r2+1:r,];}}else{gen.low<-gen[r2+1:r,];}");
           }
           else if(strSplit.equals("6"))
           {
           	re.eval("r1<-round(r*0.3)");
           	re.eval("r2<-r-r1");
           	re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];}else{gen.up<-gen[1:r1+1,];}}else{gen.up<-gen[1:r1,];}");            	
           	re.eval("if(gen[r2+1,3]==gen[r2,3]){suba<-gen[r2+1,3]-gen[r2+2,3];subb<-gen[r2,3]-gen[r2-1,3];if(abs(suba)>abs(subb)){gen.low<-gen[r2+2:r,];}else{gen.low<-gen[r2+1:r,];}}else{gen.low<-gen[r2+1:r,];}");
           }
           re.eval("gen.up[,3]=1");
           re.eval("gen.low[,3]=0");
           re.eval("gen<-rbind(gen.up,gen.low)");
           re.eval("gen.surv<-survfit(Surv(gen[,1],gen[,2])~gen[,3])");
           re.eval("gen.dif<-survdiff(Surv(gen[,1],gen[,2])~gen[,3])");
           re.eval("jpeg('"+strPath+"',width=1000,height=1000)");            
           re.eval("par(cex=2.5,font=2,font.axis=2,cex.axis=1.3,font.lab=2,cex.lab=1.3)");
           re.eval("plot(gen.surv,xlab=\"Months\",ylab=\"Probability\",col=c(\"red\",\"green\"),xlim=c(0,"+intLen+"))");            
           re.eval("p.cox<-summary(coxph(Surv(gen[,1],gen[,2])~gen[,3]))");            
           re.eval("p.val<-p.cox$coefficients[5]");
           re.eval("p.hr<-p.cox$coefficients[2]");
           re.eval("p.l<-p.cox$conf.int[3]");
           re.eval("p.t<-p.cox$conf.int[4]");
           re.eval("legend(\"topright\",legend=c(\""+strLab1+"\",\""+strLab2+"\"),text.col=c(\"green\",\"red\"),horiz=FALSE)");
           re.eval("text("+ppos+",0.2,paste(\"p=\",round(p.val,6)))");            
           re.eval("text("+hrpos+",0.12,paste(\"HR=\",round(p.hr,6)))");
           re.eval("text("+cipos+",0.05,paste(\"( 95%CI,\",round(p.l,4),\"-\",round(p.t,4),\")\"))");
           re.eval("text("+logpos+",0.05,\"OS"+tip+"\", font=4)");
           re.eval("dev.off()");  
           re.eval("odbcClose(db)");              
       }  
       catch (REngineException e) {  
           e.printStackTrace();  
       } finally {  
           re.close();     
       } 
	   return strTip;
   }
   
   public String PlotGEO(String strSql, String strSplit, String strPath, String strGen, String strAvg, String intLen, String ppos, String hrpos, String cipos, String logpos, String titlepos, String tip) throws RserveException, REXPMismatchException 
	{
	   String strTip = "";
	   RConnection re = new RConnection("127.0.0.1");	
       try {   
       	   re.eval("library(survival)");
           re.eval("library(RODBC)");
           re.eval(strConRODBC);           
           re.eval("res<-sqlQuery(db,\""+strSql+"\")");
           
           REXP x = re.eval("r<-nrow(res)");
           int i = x.asInteger();
           
           if(i<4 && i>0)
           {            	
               re.eval("odbcClose(db)");   
               strTip = "Number of "+tip+" patients was less than 4, No meaningful output.";
               return strTip;
           }
           else if(i==0)
           {
           	re.eval("odbcClose(db)");   
               strTip = "invalid input, please check Gene Symbol at https://www.ncbi.nlm.nih.gov/gene/.";
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
           	re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];gen.low<-gen[r1+1:r,]}else{gen.up<-gen[1:r1+1,];gen.low<-gen[r1+2:r,]}}else{gen.up<-gen[1:r1,];gen.low<-gen[r1+1:r,]}");
           }
           else if(strSplit.equals("2") || strSplit.equals("3"))
           {
           	re.eval("r1<-round(r*0.3)");
           	re.eval("r2<-r-r1");
           	re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];gen.low<-gen[r1+1:r,]}else{gen.up<-gen[1:r1+1,];gen.low<-gen[r1+2:r,]}}else{gen.up<-gen[1:r1,];gen.low<-gen[r1+1:r,]}");
           }
           else if(strSplit.equals("4") || strSplit.equals("7"))
           {
           	re.eval("r1<-round(r/2)");
           	re.eval("r2<-r-r1");
           	re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];gen.low<-gen[r1+1:r,]}else{gen.up<-gen[1:r1+1,];gen.low<-gen[r1+2:r,]}}else{gen.up<-gen[1:r1,];gen.low<-gen[r1+1:r,]}");
           }
           else if(strSplit.equals("5"))
           {
           	re.eval("r1<-round(r/4)");
           	re.eval("r2<-r-r1");
           	re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];}else{gen.up<-gen[1:r1+1,];}}else{gen.up<-gen[1:r1,];}");            	
           	re.eval("if(gen[r2+1,3]==gen[r2,3]){suba<-gen[r2+1,3]-gen[r2+2,3];subb<-gen[r2,3]-gen[r2-1,3];if(abs(suba)>abs(subb)){gen.low<-gen[r2+2:r,];}else{gen.low<-gen[r2+1:r,];}}else{gen.low<-gen[r2+1:r,];}");
           }
           else if(strSplit.equals("6"))
           {
           	re.eval("r1<-round(r*0.3)");
           	re.eval("r2<-r-r1");
           	re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];}else{gen.up<-gen[1:r1+1,];}}else{gen.up<-gen[1:r1,];}");            	
           	re.eval("if(gen[r2+1,3]==gen[r2,3]){suba<-gen[r2+1,3]-gen[r2+2,3];subb<-gen[r2,3]-gen[r2-1,3];if(abs(suba)>abs(subb)){gen.low<-gen[r2+2:r,];}else{gen.low<-gen[r2+1:r,];}}else{gen.low<-gen[r2+1:r,];}");        
           }
           
           re.eval("gen.up[,3]=1");
           re.eval("gen.low[,3]=0");
           re.eval("gen<-rbind(gen.up,gen.low)");            
           re.eval("gen.surv<-survfit(Surv(gen[,1],gen[,2])~gen[,3])");
           re.eval("gen.dif<-survdiff(Surv(gen[,1],gen[,2])~gen[,3])");            
           re.eval("jpeg('"+strPath+"',width=1000,height=1000)");            
           re.eval("par(cex=2.5,font=2,font.axis=2,cex.axis=1.3,font.lab=2,cex.lab=1.3)");            
           re.eval("plot(gen.surv,xlab=\"Months\",ylab=\"Probability\",col=c(\"red\",\"green\"),xlim=c(0,"+intLen+"))");
           re.eval("p.cox<-summary(coxph(Surv(gen[,1],gen[,2])~gen[,3]))");            
           re.eval("p.val<-p.cox$coefficients[5]");
           re.eval("p.hr<-p.cox$coefficients[2]");
           re.eval("p.l<-p.cox$conf.int[3]");
           re.eval("p.t<-p.cox$conf.int[4]");
           re.eval("legend(\"topright\",legend=c(\""+strLab1+"\",\""+strLab2+"\"),text.col=c(\"green\",\"red\"),horiz=FALSE)");
           re.eval("text("+ppos+",0.2,paste(\"p=\",round(p.val,6)))");
           re.eval("text("+hrpos+",0.12,paste(\"HR=\",round(p.hr,6)))");
           re.eval("text("+cipos+",0.05,paste(\"( 95%CI,\",round(p.l,4),\"-\",round(p.t,4),\")\"))");
           re.eval("text("+titlepos+",1.0,\""+strAvg+"\")");
           re.eval("text("+logpos+",0.05,\"OS"+tip+"\",font=4)");            
           re.eval("dev.off()");  
           re.eval("odbcClose(db)");
       }
       catch (REngineException e) {  
           e.printStackTrace();  
       } finally {  
           re.close();     
       } 
       return strTip;
	}
      
   public String[] GetIDREFByName(String strName, String strIndex, String tableName)
	{
		List<String> listIDREF = GetIDREF(strName, strIndex, tableName);
		String[] str = new String[listIDREF.size()];
		for(int i = 0; i<listIDREF.size(); i++)
		{
			str[i]=listIDREF.get(i);
		}
		return str;
	}
      
   public List<String> GetIDREF(String strName, String strIndex, String tableName)
	{
		List<String> listIDREF = new ArrayList<String>();
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
		String sql = "select distinct idref from "+tableName+"_"+strIndex+" where genName='"+strName+"'";
		try {
				conn = DriverManager.getConnection(strConJDBC);
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String id = rs.getString("idref");
					listIDREF.add(String.valueOf(id));
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
				listIDREF.add(String.valueOf(e.getMessage()));
		}
		return listIDREF;
	}
   
   
   
   //lung
   public String getGenIndex(String strGen, String tableName)
	{
		String strIndex = "-1";
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
        	obj = (Object)ctx.lookup("jdbc/SQLServerDS");
            ds = (DataSource)obj;
            
        } catch (NamingException e) {
            e.printStackTrace();
        }
		
		String sql = "select distinct tableid from "+tableName+" where genName='"+strGen+"'";
		try {
				//conn = DriverManager.getConnection(strConJDBC);
				conn = ds.getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String id = rs.getString("tableid");
					strIndex = String.valueOf(id);
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
				
				//System.out.println("���ݿ�����ʧ��");				
		}
		return strIndex;
	}
   
   //BRCA1
   public String getGenIndexBRCA1(String strGen, String tableName)
	{
		String strIndex = "-1";
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
       	obj = (Object)ctx.lookup("jdbc/SSBRCA1");
           ds = (DataSource)obj;
           
       } catch (NamingException e) {
           e.printStackTrace();
       }
		
		String sql = "select distinct tableid from "+tableName+" where genName='"+strGen+"'";
		try {
				//conn = DriverManager.getConnection(strConJDBC);
				conn = ds.getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String id = rs.getString("tableid");
					strIndex = String.valueOf(id);
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
				
				//System.out.println("���ݿ�����ʧ��");				
		}
		return strIndex;
	}

   //BRCA2
   public String getGenIndexBRCA2(String strGen, String tableName)
	{
		String strIndex = "-1";
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
       	obj = (Object)ctx.lookup("jdbc/SSBRCA2");
           ds = (DataSource)obj;
           
       } catch (NamingException e) {
           e.printStackTrace();
       }
		
		String sql = "select distinct tableid from "+tableName+" where genName='"+strGen+"'";
		try {
				//conn = DriverManager.getConnection(strConJDBC);
				conn = ds.getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String id = rs.getString("tableid");
					strIndex = String.valueOf(id);
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
				
				//System.out.println("���ݿ�����ʧ��");				
		}
		return strIndex;
	}
   
   //BRCA3
   public String getGenIndexBRCA3(String strGen, String tableName)
	{
		String strIndex = "-1";
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
       	obj = (Object)ctx.lookup("jdbc/SSBRCA3");
           ds = (DataSource)obj;
           
       } catch (NamingException e) {
           e.printStackTrace();
       }
		
		String sql = "select distinct tableid from "+tableName+" where genName='"+strGen+"'";
		try {
				//conn = DriverManager.getConnection(strConJDBC);
				conn = ds.getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String id = rs.getString("tableid");
					strIndex = String.valueOf(id);
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
				
				//System.out.println("���ݿ�����ʧ��");				
		}
		return strIndex;
	}
   //BRCA4
   public String getGenIndexBRCA4(String strGen, String tableName)
	{
		String strIndex = "-1";
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
      	obj = (Object)ctx.lookup("jdbc/SSBRCA4");
          ds = (DataSource)obj;
          
      } catch (NamingException e) {
          e.printStackTrace();
      }
		
		String sql = "select distinct tableid from "+tableName+" where genName='"+strGen+"'";
		try {
				//conn = DriverManager.getConnection(strConJDBC);
				conn = ds.getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String id = rs.getString("tableid");
					strIndex = String.valueOf(id);
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
				
				//System.out.println("���ݿ�����ʧ��");				
		}
		return strIndex;
	}

   //���һ��������Ƿ����
   public boolean isGetgenName(String strName){
	   char c = strName.toUpperCase().charAt(0);
	   boolean blresult=false;
	   switch(c){
	   case 'A':
		   GenComA genA = new GenComA();
		   if(genA.AGenList.contains(strName))
			   blresult=true;
		   break;
	   case 'L':
		   GenComL genL = new GenComL();
		   if(genL.LGenList.contains(strName))
			   blresult=true;
		   break;
	   case 'M':
		   GenComM genM = new GenComM();
		   if(genM.MGenList.contains(strName))
			   blresult=true;
		   break;
	   case 'N':
		   GenComN genN = new GenComN();
		   if(genN.NGenList.contains(strName))
			   blresult=true;
		   break;
	   case 'C':
		   GenComC genC = new GenComC();
		   if(genC.CGenList.contains(strName))
			   blresult=true;
		   break;
	   case 'R':
		   GenComR genR = new GenComR();
		   if(genR.RGenList.contains(strName))
			   blresult=true;
		   break;
	   case 'S':
		   GenComS genS = new GenComS();
		   if(genS.SGenList.contains(strName))
			   blresult=true;
		   break;
	   case 'T':
		   GenComT genT = new GenComT();
		   if(genT.TGenList.contains(strName))
			   blresult=true;
		   break;
	   case 'B':
		   GenComB genB = new GenComB();
		   if(genB.BGenList.contains(strName))
			   blresult=true;
		   break;
	   case 'D':
		   GenComD genD = new GenComD();
		   if(genD.DGenList.contains(strName))
			   blresult=true;
		   break;
	   case 'E':
		   GenComE genE = new GenComE();
		   if(genE.EGenList.contains(strName))
			   blresult=true;
		   break;
	   case 'F':
		   GenComF genF = new GenComF();
		   if(genF.FGenList.contains(strName))
			   blresult=true;
		   break;
	   case 'G':
		   GenComG genG = new GenComG();
		   if(genG.GGenList.contains(strName))
			   blresult=true;
		   break;
	   case 'H':
		   GenComH genH = new GenComH();
		   if(genH.HGenList.contains(strName))
			   blresult=true;
		   break;	   
	   case 'K':
		   GenComK genK = new GenComK();
		   if(genK.KGenList.contains(strName))
			   blresult=true;
		   break;
	   case 'I':
	   case 'O':
	   case 'Q':
		   GenComIOQ genI = new GenComIOQ();
		   if(genI.IGenList.contains(strName))
			   blresult=true;
		   break;
	   case 'P':
		   GenComP genP = new GenComP();
		   if(genP.PGenList.contains(strName))
			   blresult=true;
		   break;	   
	   case 'J':
	   case 'U':
	   case 'V':
	   case 'W':
	   case 'X':
	   case 'Y':
		   GenComJUVWXY genJ = new GenComJUVWXY();
		   if(genJ.JGenList.contains(strName))
			   blresult=true;
		   break;
	   case 'Z':
		   GenComZ genZ = new GenComZ();
		   if(genZ.ZGenList.contains(strName))
			   blresult=true;
		   break;
	   }
	   /*try
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
     	obj = (Object)ctx.lookup("jdbc/SSBLCA");
         ds = (DataSource)obj;
         
     } catch (NamingException e) {
         e.printStackTrace();
     }
		
		String sql = "select genName from genNameInfo where genName='"+strName+"'";
		try {
				conn = ds.getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String id = rs.getString("genName");
					return true;
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
		return false;*/
	   return blresult;
   }
   
   
   //ͨ��
   public String getGenIndex(String strGen, String tableName, String strDB)
	{
		String strIndex = "-1";
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
      	obj = (Object)ctx.lookup(strDB);//"jdbc/SSBRCA1"
          ds = (DataSource)obj;
          
      } catch (NamingException e) {
          e.printStackTrace();
      }
		
		String sql = "select distinct tableid from "+tableName+" where genName='"+strGen+"'";
		try {
				//conn = DriverManager.getConnection(strConJDBC);
				conn = ds.getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String id = rs.getString("tableid");
					strIndex = String.valueOf(id);
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
				
				//System.out.println("���ݿ�����ʧ��");				
		}
		return strIndex;
	}
   
   public String[] GetIDREFByName2(String strName, String tableName, String strCon)
   {
		List<String> listIDREF = GetIDREF2(strName, tableName, strCon);
		String[] str = new String[listIDREF.size()];
		for(int i = 0; i<listIDREF.size(); i++)
		{
			str[i]=listIDREF.get(i);
		}
		return str;
	}
   
   public List<String> GetIDREF2(String strName, String tableName, String strCon)
	{
		List<String> listIDREF = new ArrayList<String>();
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
       	obj = (Object)ctx.lookup(strCon);
           ds = (DataSource)obj;
           
       } catch (NamingException e) {
           e.printStackTrace();
       }		
		String sql = "select distinct idref from "+tableName+" where genName='"+strName+"'";
		
		try {
				conn = ds.getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String id = rs.getString("idref");
					listIDREF.add(String.valueOf(id));
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
				listIDREF.add(String.valueOf(e.getMessage()));
		}
		return listIDREF;
	}
   
   public String[] GetIDREFByName(String strName, String strIndex, String tableName, String strCon)
   {
		List<String> listIDREF = GetIDREF(strName, strIndex, tableName, strCon);
		String[] str = new String[listIDREF.size()];
		for(int i = 0; i<listIDREF.size(); i++)
		{
			str[i]=listIDREF.get(i);
		}
		return str;
	}
   
   public List<String> GetIDREF(String strName, String strIndex, String tableName, String strCon)
	{
		List<String> listIDREF = new ArrayList<String>();
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
        	obj = (Object)ctx.lookup(strCon);
            ds = (DataSource)obj;
            
        } catch (NamingException e) {
            e.printStackTrace();
        }		
		String sql = "select distinct idref from "+tableName+"_"+strIndex+" where genName='"+strName+"'";
		
		try {
				conn = ds.getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String id = rs.getString("idref");
					listIDREF.add(String.valueOf(id));
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
				listIDREF.add(String.valueOf(e.getMessage()));
		}
		return listIDREF;
	}
}
