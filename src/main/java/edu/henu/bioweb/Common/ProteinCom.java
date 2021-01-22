package edu.henu.bioweb.Common;

import edu.henu.bioweb.GenNameArray.ProteinName;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProteinCom {
	//����1Ϊ���ף�����2Ϊ���򣬷���0Ϊ������
	public int isExitName(String strName){
		ProteinName pn = new ProteinName();
		if(!pn.ProteinList.contains(strName))
		{
			if(!pn.GenList.contains(strName))
			{
				return 0;
			}
			else
			{
				return 2;
			}			
		}
		return 1;
	}
	
	public List<String> GetAntibody(String strName, String tableName)
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
        	obj = (Object)ctx.lookup("jdbc/SSPROTEIN");
            ds = (DataSource)obj;
            
        } catch (NamingException e) {
            e.printStackTrace();
        }		
		String sql = "select distinct antibody from "+tableName+" where protName='"+strName+"'";
		
		try {
				conn = ds.getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String id = rs.getString("antibody");
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
	
	public boolean IsProName(String strProtein, String strCancer){
		boolean result = false;
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
		String strSql = "select count(*) as num from "+strCancer+"_Protein where protName='"+strProtein+"'";
		Context initCtx;
		Context ctx;
		Object  obj;
		DataSource ds = null;		
        try {
        	initCtx = new InitialContext();
        	ctx = (Context)initCtx.lookup("java:comp/env");
        	obj = (Object)ctx.lookup("jdbc/SSPROTEIN");
            ds = (DataSource)obj;
            
        } catch (NamingException e) {
            e.printStackTrace();
        }
				
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(strSql);
				while (rs.next()) {
					int str = rs.getInt("num");
					if(str<=0){
						result=false;
					}
					else if(str==1){
						result=true;
					}
					else if(str>1){
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
			}	
		catch (SQLException e) {
		}
		return result;
	}
	
	public List GetGeneName(String strProtein, String strCancer, String strAnti){
		List<String> list = new ArrayList<String>();
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
		String strSql = "select distinct geneName from "+strCancer+"_Protein where antibody='"+strAnti+"' and protName ='"+strProtein+"'";
		Context initCtx;
		Context ctx;
		Object  obj;
		DataSource ds = null;		
        try {
        	initCtx = new InitialContext();
        	ctx = (Context)initCtx.lookup("java:comp/env");
        	obj = (Object)ctx.lookup("jdbc/SSPROTEIN");
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
					String str = rs.getString("geneName");
					String[] ss = str.split(",");
					for(int j=0;j<ss.length;j++){
						list.add(ss[j]);	
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
			}	
		catch (SQLException e) {
		}
		return list;
	}
	
	public List GetGeneName(String strProtein, String strCancer){
		List<String> list = new ArrayList<String>();
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
		String strSql = "select distinct geneName from "+strCancer+"_Protein where antibody='"+strProtein+"'";
		Context initCtx;
		Context ctx;
		Object  obj;
		DataSource ds = null;		
        try {
        	initCtx = new InitialContext();
        	ctx = (Context)initCtx.lookup("java:comp/env");
        	obj = (Object)ctx.lookup("jdbc/SSPROTEIN");
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
					String str = rs.getString("geneName");
					String[] ss = str.split(",");
					for(int j=0;j<ss.length;j++){
						list.add(ss[j]);	
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
			}	
		catch (SQLException e) {
		}
		return list;
	}
	
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
							strTime = rs.getString("RFS");
							strEvent = rs.getString("RFS_Event");
							strR = "";
							strR = res + "<-data.frame(RFS=c("+strTime+"),RFS_Event=c("+strEvent+"),genValue=c("+strValue+"))\n";
						}
						else if(strSurvival.equals("2"))
						{
							strTime = rs.getString("DFI");
							strEvent = rs.getString("DFI_Event");
							strR = "";
							strR = res + "<-data.frame(DFI=c("+strTime+"),DFI_Event=c("+strEvent+"),genValue=c("+strValue+"))\n";
						}
						else if(strSurvival.equals("3"))
						{
							strTime = rs.getString("PFI");
							strEvent = rs.getString("PFI_Event");
							strR = "";
							strR = res + "<-data.frame(PFI=c("+strTime+"),PFI_Event=c("+strEvent+"),genValue=c("+strValue+"))\n";
						}
						else if(strSurvival.equals("4"))
						{
							strTime = rs.getString("DSS");
							strEvent = rs.getString("DSS_Event");
							strR = "";
							strR = res + "<-data.frame(DSS=c("+strTime+"),DSS_Event=c("+strEvent+"),genValue=c("+strValue+"))\n";
						}
					}
					else
					{
						if(strSurvival.equals("0"))
						{
							strTime = rs.getString("OS");
							strEvent = rs.getString("OS_Event");
						}
						else if(strSurvival.equals("1"))
						{
							strTime = rs.getString("RFS");
							strEvent = rs.getString("RFS_Event");
						}
						else if(strSurvival.equals("2"))
						{
							strTime = rs.getString("DFI");
							strEvent = rs.getString("DFI_Event");
						}
						else if(strSurvival.equals("3"))
						{
							strTime = rs.getString("PFI");
							strEvent = rs.getString("PFI_Event");
						}
						else if(strSurvival.equals("4"))
						{
							strTime = rs.getString("DSS");
							strEvent = rs.getString("DSS_Event");
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
		
	public String GetLinear(String strSql, String strSurvival, String strPath) throws REXPMismatchException, RserveException{
		String strTip="";
		/*
		Double[] dbProtein,dbmRNA;
		
		List<Double> listProtein = new ArrayList<Double>();
		List<Double> listmRNA = new ArrayList<Double>();
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
        	obj = (Object)ctx.lookup("jdbc/SSPROTEIN");//jdbc/SSBRCA2
            ds = (DataSource)obj;
            
        } catch (NamingException e) {
            e.printStackTrace();
        }*/
		RConnection re = new RConnection("127.0.0.1");	
		try {
			/*conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(strSql);
				int i = 0;
				while (rs.next()) {
					double dbRValue = rs.getDouble("mRNA");
					double dbPValue = rs.getDouble("Protein");
					listProtein.add(dbPValue);
					listmRNA.add(dbRValue);
					i++;
				}
				dbProtein = new Double[listProtein.size()];
				dbmRNA=new Double[listmRNA.size()];
				listProtein.toArray(dbProtein);
				listmRNA.toArray(dbmRNA);
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
				
				if(dbProtein.length != 0 && dbmRNA.length != 0){
					double[] ddProtein = new double[dbProtein.length];
					double[] ddmRNA = new double[dbmRNA.length];
					int j = 0;
					for(j=0;j<dbProtein.length;j++){
						ddProtein[j]=dbProtein[j];
					}
					for(j=0;j<dbmRNA.length;j++){
						ddmRNA[j]=dbmRNA[j];
					}*/
					
					//re.assign("Protein", ddProtein);
					//re.assign("mRNA", ddmRNA);
					re.eval("library(ggplot2)");
					re.eval("library(gcookbook)");
					re.eval("library(RODBC)");
					re.eval("library(magrittr)");
					re.eval("library(ggpubr)");
		            //re.eval("rlin<-rbind(Protein,mRNA)");
		            //re.eval("rlin<-t(rlin)");
		            
		            re.eval("db<-odbcDriverConnect('driver={SQL Server};server=(local);database=PROTEINDB;user=sa;pwd=gxqktzwq')");
		            //REXP x=re.eval("rlin<-sqlQuery(db,\"select ACC_Protein.genValue as Protein,ACC_Gene.genValue as mRNA from ACC_Protein,ACC_Gene where protName='X1433EPSILON' and genName='YWHAE' and genGSM=GSM\")"); 
		            re.eval("rlin<-sqlQuery(db,\""+strSql+"\")"); 
		            //List li = x.asList();		            
		            //re.eval("jpeg('"+strPath+"',width=1000,height=1000)");
		            re.eval("ggsurv<-ggplot(rlin,aes(x=mRNA,y=Protein))+geom_point()+labs(x = \"mRNA(log2(norm_count+1))\", y = \"Protein(RPPA(replicate-base normalization))\")+geom_smooth(method=lm)+stat_cor(data=rlin, method = \"pearson\",size=5)+ theme(plot.title = element_text(size = 20),axis.text=element_text(size=12,face = \"bold\"),axis.title.x=element_text(size=14),axis.title.y=element_text(size=14))");
		            //re.eval("ggsurv + labs(x= \"log2(norm_count+1)\",y = \"RPPA(replicate-base normalization)\")");
		            re.eval("ggsave(file = \""+strPath+"\", width=6,height=6, type = \"cairo\",print(ggsurv))");
		            //re.eval("dev.off()");
		            re.eval("odbcClose(db)");
				//}
			}
		catch(REngineException e1){
			e1.printStackTrace();
		}		
		finally{
			re.close();
		}
		//catch (SQLException e) {
		//}
		
		/*library(ggplot2)
		library(gcookbook)
		library(RODBC)
		db<-odbcDriverConnect('driver={SQL Server};server=(local);database=PROTEINDB;user=sa;pwd=gxqktzwq')
		res<-sqlQuery(db,paste("select protvalue as Protein,genvalue as mRNA from ACC_Protein,ACC_Gene where protName='X1433EPSILON' and genName='YWHAE' and genGSM=GSM order by protValue",sep='')) 
		ggplot(res,aes(x=mRNA,y=Protein))+geom_point()+geom_smooth(method=lm)

		double[] dataX, dataY;
		RConnection c = new RConnection();
		c.assign("x", dataX);
		c.assign("y", dataY);
		RList l = c.eval("lowess(x,y)").asList();
		double[] lx = l.at("x").asDoubles();
		double[] ly = l.at("y").asDouble();
		*/
		
		
		return strTip;
	}
	
	public void GetForest(String strProtein, List<String> listGen, List<String> listHR, List<String> listL, List<String> listU, List<String> listPvalue, String strPath, int k) throws RserveException{
		/*rhr<-c(1,2,3)
		rlow<-c(4,5,6)
		rup<-c(7,8,9)
		rt<-c("gen1","gen2","gen3")
		jpeg(file = "d:\\1.jpg",width =2000,height = 1800,units = "px",res =300)
		forestplot(rt,rhr,rlow,rup,zero=1)
		dev.off()*/
		RConnection re = new RConnection("127.0.0.1");
		try {   
        	re.eval("library(grid)");
            re.eval("library(magrittr)");
            re.eval("library(checkmate)");
            re.eval("library(forestplot)");
            String strGen="";
            String strHR="";
            String strLow="";
            String strUp="";         
            if(listPvalue.size()<=0){
            	return;
            }
            String strMax = Collections.max(listU);
            String strRJ = "";
            strRJ="cochrane_from_rmeta <- structure(list(HR  = c(NA,";
            for(int i=0;i<listHR.size();i++){
            	strRJ+= listHR.get(i) + ",";
            }
            strRJ=strRJ.substring(0, strRJ.length()-1);
            strRJ +="),lower = c(NA,"; 
            for(int i=0;i<listL.size();i++){
            	strRJ+=listL.get(i)+",";
            }
            strRJ=strRJ.substring(0, strRJ.length()-1);
            strRJ+="),upper = c(NA,";
            for(int i=0;i<listU.size();i++){
            	strRJ+=listU.get(i)+",";
            }
            strRJ=strRJ.substring(0, strRJ.length()-1);
            int len = listHR.size()+1;
            strRJ+=")),.Names = c(\"HR\", \"lower\", \"upper\"), row.names = c(NA, -"+len+"L), class = \"data.frame\") ";            
            
            re.eval(strRJ);
            /*cochrane_from_rmeta <- structure(list(HR  = c(NA,0.578, 0.165, 0.246), 
   	     lower = c(NA,0.372, 0.018, 0.072),upper = c(NA,0.898, 1.517, 0.833)),            	     
   	     .Names = c("HR", "lower", "upper"), row.names = c(NA, -4L), class = "data.frame") 
   	 tabletext<-cbind(c("Gene Symbol", "Auckland", "Block", "Doran"),c("HR(95% CI)", "0.578(0.372,0.898)", 
   	 "0.165(0.018,1.517)", "0.246(0.072,0.833)"))
   	 forestplot(tabletext, cochrane_from_rmeta,boxsize=0.1)*/
            strRJ="tabletext<-cbind(c(\"  \",";
            for(int i=0;i<listGen.size();i++){
            	if(i<k){
            		strRJ+="\"Protein Name:\r\n"+ strProtein +"\r\n("+ listGen.get(i)+")\",";
            	}
            	else{
            		strRJ+="\"Gene symbol:\r\n"+listGen.get(i)+"\",";
            	}
            }
            strRJ=strRJ.substring(0, strRJ.length()-1);
            strRJ+="),c(\"HR(95% CI)\",";
            for(int i=0;i<listHR.size();i++){
            	strRJ+= "paste(round("+listHR.get(i)+",4),\"(\",round("+listL.get(i)+",4),\",\",round("+listU.get(i)+",4),\")\"),";
            }
            strRJ=strRJ.substring(0, strRJ.length()-1);
            strRJ+="))";
            re.eval(strRJ);
            strRJ="jpeg('"+strPath+"',width =2000,height = 1000)";
            re.eval(strRJ);
            
            double[] db = new double[5];
            int[] ni = new int[5];
            	
            if(Double.parseDouble(strMax)>10){
            	int d1 = (int)Math.ceil(Double.parseDouble(strMax));
                while(true){
                	if(d1%5!=0 && d1%10!=0){
                    	d1++;
                    }
                	else{
                		break;
                	}
                }
            	for(int n=0;n<5;n++){
            		ni[n]=(n+1)*d1/5;
            		//ni[n]=(int)db[n];
            	}
            	//ni[4]+=1;
            	strRJ="forestplot(tabletext, xticks=c(0,"+ni[0]+","+ni[1]+","+ni[2]+","+ni[3]+","+ni[4]+"), cochrane_from_rmeta,graph.pos=2,boxsize=0.1,zero=1,txt_gp = fpTxtGp(cex=3.3,ticks=gpar(cex=3.3)),col=fpColors(line=\"grey0\"))";
            }
            else{
            	strRJ="forestplot(tabletext, cochrane_from_rmeta,graph.pos=2,boxsize=0.1,zero=1,txt_gp = fpTxtGp(cex=3.3,ticks=gpar(cex=3.3)),col=fpColors(line=\"grey0\"))";
            }
            
            re.eval(strRJ);
            strRJ="dev.off()";
            re.eval(strRJ);
            
            
            /*for(int i=0;i<listGen.size();i++){
            	strGen+="\""+listGen.get(i)+"\",";
            }
            strGen= "rt<-c("+strGen.substring(0, strGen.length()-1)+")";
            for(int i=0;i<listHR.size();i++){
            	strHR+=listHR.get(i)+",";
            }
            strHR = "rhr<-c("+strHR.substring(0,strHR.length()-1)+")";
            
            for(int i=0;i<listL.size();i++){
            	strLow+=listL.get(i)+",";
            }
            strLow = "rlow<-c("+strLow.substring(0,strLow.length()-1)+")";
            
            for(int i=0;i<listU.size();i++){
            	strUp+=listU.get(i)+",";
            }
            strUp = "rup<-c("+strUp.substring(0,strUp.length()-1)+")";
            re.eval(strGen);
            re.eval(strLow);
            re.eval(strHR);
            re.eval(strUp);
            re.eval("jpeg('"+strPath+"',width =2000,height = 1800)");
            re.eval("forestplot(rt,rhr,rlow,rup,zero=1,lty.ci=3,txt_gp = fpTxtGp(cex=5,ticks=gpar(cex=5)),,col=fpColors(box=\"#000000\",line=\"#000000\"))");
            re.eval("dev.off()");*/
        }
        catch (REngineException e) {  
            e.printStackTrace();  
        } finally {  
            re.close();     
        } 
	}

	public String GetPath(String strSql, String strSplit, String strPath, String strGen, String strAvg, String strSurvival, String strDB, String strCancer, String tip) throws RserveException, REXPMismatchException 
	{	
		ComFun fun = new ComFun();
		String strTip = "";
		strSql = GetRString(strSql, strSurvival,"res", strDB);
		if(strSql.equals("-1"))
		{			
			strTip = fun.GetTip(3, tip, strGen, strCancer);
            return strTip;
		}
		RConnection re = new RConnection("127.0.0.1");
        try {   
        	re.eval("library(survival)");
            re.eval("library(ggplot2)");
            re.eval("library(magrittr)");
            re.eval("library(ggpubr)");
            re.eval("library(survminer)");
            re.eval(strSql);
            REXP x = re.eval("r<-nrow(res)");
            int i = x.asInteger();            
            if(i<4 && i>0)
            {            	
            	strTip = fun.GetTip(2, tip, strGen, strCancer);
                return strTip;
            }
            else if(i==0)
            {
            	strTip = fun.GetTip(3, tip, strGen, strCancer);
                return strTip;
            }
            else
            {
            	strTip = " ";
            }
            re.eval("attach(res)");
            
            String strLab1="";
            String strLab2="";    
            String strLab3="";
            String strLab4="";           
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
            	case "8":
            		strLab1="High expression";
            		strLab2="Medium expression";
            		strLab3="Low expression";
            		break;
            	case "9":
            		strLab1="First Quartile";
            		strLab2="Second Quartile";
            		strLab3="Third Quartile";
            		strLab4="Fourth Quartile";
            		break;
            }
            
            
            re.eval("gmax<-res[order(-res[,1]),]");
            
            
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
            	re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}else{gen.up<-gen[1:(r1+1),];gen.low<-gen[(r1+2):r,]}}else{gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}");
            }
            else if(strSplit.equals("2") || strSplit.equals("3"))
            {
            	re.eval("r1<-round(r*0.3)");
            	re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}else{gen.up<-gen[1:(r1+1),];gen.low<-gen[(r1+2):r,]}}else{gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}");
            }
            else if(strSplit.equals("4") || strSplit.equals("7"))
            {
            	re.eval("r1<-round(r/2)");
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
            else if(strSplit.equals("8"))
            {
            	re.eval("r1<-round(r/3)");
            	re.eval("r2<-r1*2");
            	re.eval("r3<-r-r2");
            	re.eval("gen.up<-gen[1:r1,];gen.mdi<-gen[(r1+1):r2,];gen.low<-gen[(r2+1):r3,]");
            }
            else if(strSplit.equals("9"))
            {
            	re.eval("r1<-round(r/4)");
            	re.eval("r2<-r1*2");
            	re.eval("r3<-r1*3");
            	re.eval("r4<-r-r3");
            	re.eval("gen.up<-gen[1:r1,];gen.mu<-gen[(r1+1):r2,];gen.ml<-gen[(r2+1):r3,];gen.low<-gen[(r3+1):r4,]");
            }
            if(strSplit.equals("8"))
            {
            	re.eval("gen.up[,3]=0");
            	re.eval("gen.mdi[,3]=1");
            	re.eval("gen.low[,3]=2");
                re.eval("gen<-rbind(gen.up,gen.mdi)");
                re.eval("gen<-rbind(gen,gen.low)");
            }
            else if(strSplit.equals("9"))
            {
            	re.eval("gen.up[,3]=0");
            	re.eval("gen.mu[,3]=1");
            	re.eval("gen.ml[,3]=2");
            	re.eval("gen.low[,3]=3");
                re.eval("gen<-rbind(gen.up,gen.mu)");
                re.eval("gen<-rbind(gen,gen.ml)");
                re.eval("gen<-rbind(gen,gen.low)");
            }
            else
            {
            	re.eval("gen.up[,3]=1");
            	re.eval("gen.low[,3]=0");
                re.eval("gen<-rbind(gen.up,gen.low)");
            }           
            re.eval("gen.surv<-survfit(Surv(gen[,1],gen[,2])~gen[,3])");
            re.eval("gen.dif<-survdiff(Surv(gen[,1],gen[,2])~gen[,3])");  
            re.eval("p.cox<-summary(coxph(Surv(gen[,1],gen[,2])~gen[,3]))");            
            re.eval("p.val<-p.cox$coefficients[5]");
            re.eval("p.hr<-p.cox$coefficients[2]");
            re.eval("p.l<-p.cox$conf.int[3]");
            re.eval("p.t<-p.cox$conf.int[4]");
            // legend.labs =    c("Male", "Female"),    # Change legend labels 
            if(strSplit.equals("1") || strSplit.equals("3") || strSplit.equals("4") || strSplit.equals("5") || strSplit.equals("6"))
            {	
            	//re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"green\",\"red\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\""+strLab2+"\", \""+strLab1+"\"),legend = c(0.85, 0.9))");
            	re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,title='abcd',risk.table=TRUE,xlab=\"Months\",palette=c(\"green\",\"red\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\""+strLab2+"\", \""+strLab1+"\"),legend = \"none\")");//legend = c(0.85, 0.9)
            	re.eval("ggsurv$table <- ggsurv$table + theme(axis.text.y = element_text(colour='black'))");
            	re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.80, y = 0.97,label = \""+strLab1+"\", size = 4,color='red')");
            	re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.80, y = 0.92,label = \""+strLab2+"\", size = 4,color='green')");
            }            
            else if(strSplit.equals("8"))
            {
            	re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"red\",\"black\",\"green\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\""+strLab1+"\", \""+strLab2+"\",\""+strLab3+"\"),legend = 'none')");
            	re.eval("ggsurv$table <- ggsurv$table + theme(axis.text.y = element_text(colour='black'))");
            	re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.80-2, y = 0.97,label = \""+strLab1+"\", size = 4,color='red')");
             	re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.80, y = 0.92,label = \""+strLab2+"\", size = 4,color='black')");
             	re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.80-2, y = 0.87,label = \""+strLab3+"\", size = 4,color='green')");
            }
            else if(strSplit.equals("9"))
            {
            	re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"red\",\"black\",\"#E7B800\",\"green\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\""+strLab1+"\", \""+strLab2+"\",\""+strLab3+"\",\""+strLab4+"\"),legend = 'none')");
            	re.eval("ggsurv$table <- ggsurv$table + theme(axis.text.y = element_text(colour='black'))");
            	re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85-2, y = 0.97,label = \""+strLab1+"\", size = 4,color='red')");
             	re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85, y = 0.92,label = \""+strLab2+"\", size = 4,color='black')");
             	re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85-2, y = 0.87,label = \""+strLab3+"\", size = 4,color='#E7B800')");
             	re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85, y = 0.82,label = \""+strLab4+"\", size = 4,color='green')");
            	
            }
            else
            {
            	//re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"green\",\"red\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\""+strLab2+"\", \""+strLab1+"\"),legend = c(0.85, 0.9))");
            	re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"red\",\"green\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\""+strLab1+"\", \""+strLab2+"\"),legend = \"none\")");
            	re.eval("ggsurv$table <- ggsurv$table + theme(axis.text.y = element_text(colour='black'))");            	
            	re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.80, y = 0.97,label = \""+strLab1+"\", size = 4,color='green')");
            	re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.80, y = 0.92,label = \""+strLab2+"\", size = 4,color='red')");
            }
            //re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12, y = 0.15,label = paste(\"p=\",round(p.val,4)), size = 5)");
            re.eval("if(p.val<0.0001){ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12, y = 0.15,label = \"p<0.0001\", size = 5)} else{ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12, y = 0.15,label = paste(\"p=\",round(p.val,4)), size = 5)}");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12+2, y = 0.09,label = paste(\"HR=\",round(p.hr,4)), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.26, y = 0.03,label = paste('( 95%CI,',round(p.l,4),'-',round(p.t,4),')'), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85, y = 0.05,label = \"osppc\", size = 5,fontface = 'italic')");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.5, y = 0.95,label = \""+strAvg+"\", size = 5)");
            re.eval("ggsurv");
        	//re.eval("ggsave(file = \""+strPath+"\", print(ggsurv))");
            re.eval("ggsave(file = \""+strPath+"\", width=6,height=6, type = \"cairo\",print(ggsurv))");
        }
        catch (REngineException e) {  
            e.printStackTrace();  
        } finally {  
            re.close();     
        } 
        return strTip;
	}
	
	public String GetPath(String strSql, String strSplit, String strPath, String strGen, String strAvg, String strSurvival, String strDB, String strCancer, String tip, List<String> listHR, List<String> listL, List<String> listU, List<String> listPvalue) throws RserveException, REXPMismatchException 
	{	
		ComFun fun = new ComFun();
		String strTip = "";
		strSql = GetRString(strSql, strSurvival,"res", strDB);
		if(strSql.equals("-1"))
		{			
			strTip = fun.GetTip(3, tip, strGen, strCancer);
            return strTip;
		}
		RConnection re = new RConnection("127.0.0.1");
        try {   
        	re.eval("library(survival)");
            re.eval("library(ggplot2)");
            re.eval("library(magrittr)");
            re.eval("library(ggpubr)");
            re.eval("library(survminer)");
            re.eval(strSql);
            REXP x = re.eval("r<-nrow(res)");
            int i = x.asInteger();            
            if(i<4 && i>0)
            {            	
            	strTip = fun.GetTip(2, tip, strGen, strCancer);
                return strTip;
            }
            else if(i==0)
            {
            	strTip = fun.GetTip(3, tip, strGen, strCancer);
                return strTip;
            }
            else
            {
            	strTip = " ";
            }
            re.eval("attach(res)");
            
            String strLab1="";
            String strLab2="";    
            String strLab3="";
            String strLab4="";           
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
            	case "8":
            		strLab1="High expression";
            		strLab2="Medium expression";
            		strLab3="Low expression";
            		break;
            	case "9":
            		strLab1="First Quartile";
            		strLab2="Second Quartile";
            		strLab3="Third Quartile";
            		strLab4="Fourth Quartile";
            		break;
            }
            
            
            re.eval("gmax<-res[order(-res[,1]),]");
            
            
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
            	re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}else{gen.up<-gen[1:(r1+1),];gen.low<-gen[(r1+2):r,]}}else{gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}");
            }
            else if(strSplit.equals("2") || strSplit.equals("3"))
            {
            	re.eval("r1<-round(r*0.3)");
            	re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}else{gen.up<-gen[1:(r1+1),];gen.low<-gen[(r1+2):r,]}}else{gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}");
            }
            else if(strSplit.equals("4") || strSplit.equals("7"))
            {
            	re.eval("r1<-round(r/2)");
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
            else if(strSplit.equals("8"))
            {
            	re.eval("r1<-round(r/3)");
            	re.eval("r2<-r1*2");
            	re.eval("r3<-r-r2");
            	re.eval("gen.up<-gen[1:r1,];gen.mdi<-gen[(r1+1):r2,];gen.low<-gen[(r2+1):r3,]");
            }
            else if(strSplit.equals("9"))
            {
            	re.eval("r1<-round(r/4)");
            	re.eval("r2<-r1*2");
            	re.eval("r3<-r1*3");
            	re.eval("r4<-r-r3");
            	re.eval("gen.up<-gen[1:r1,];gen.mu<-gen[(r1+1):r2,];gen.ml<-gen[(r2+1):r3,];gen.low<-gen[(r3+1):r4,]");
            }
            if(strSplit.equals("8"))
            {
            	re.eval("gen.up[,3]=0");
            	re.eval("gen.mdi[,3]=1");
            	re.eval("gen.low[,3]=2");
                re.eval("gen<-rbind(gen.up,gen.mdi)");
                re.eval("gen<-rbind(gen,gen.low)");
            }
            else if(strSplit.equals("9"))
            {
            	re.eval("gen.up[,3]=0");
            	re.eval("gen.mu[,3]=1");
            	re.eval("gen.ml[,3]=2");
            	re.eval("gen.low[,3]=3");
                re.eval("gen<-rbind(gen.up,gen.mu)");
                re.eval("gen<-rbind(gen,gen.ml)");
                re.eval("gen<-rbind(gen,gen.low)");
            }
            else
            {
            	re.eval("gen.up[,3]=1");
            	re.eval("gen.low[,3]=0");
                re.eval("gen<-rbind(gen.up,gen.low)");
            }           
            re.eval("gen.surv<-survfit(Surv(gen[,1],gen[,2])~gen[,3])");
            re.eval("gen.dif<-survdiff(Surv(gen[,1],gen[,2])~gen[,3])");  
            re.eval("p.cox<-summary(coxph(Surv(gen[,1],gen[,2])~gen[,3]))");
            REXP rp = re.eval("p.val<-p.cox$coefficients[5]");            
            REXP rh = re.eval("p.hr<-p.cox$coefficients[2]");
            REXP rl = re.eval("p.l<-p.cox$conf.int[3]");
            REXP ru = re.eval("p.t<-p.cox$conf.int[4]");
            
            double dp = rp.asDouble();            
            if(dp<0.0001){
            	listPvalue.add("<0.0001");
            }
            else{
            	BigDecimal bd1 = new BigDecimal(dp);
    	    	listPvalue.add(bd1.setScale(4, BigDecimal.ROUND_HALF_UP).toPlainString());
            }
            double dh = rh.asDouble();
            if(dh<0.0001){
            	listPvalue.add("<0.0001");
            }
            else{
            	BigDecimal bd1 = new BigDecimal(dh);
    	    	listHR.add(bd1.setScale(4, BigDecimal.ROUND_HALF_UP).toPlainString());
            }
            double dl = rl.asDouble();
            if(dl<0.0001){
            	listL.add("<0.0001");
            }
            else{
            	BigDecimal bd1 = new BigDecimal(dl);
            	listL.add(bd1.setScale(4, BigDecimal.ROUND_HALF_UP).toPlainString());
            }
            double du = ru.asDouble();
            if(du<0.0001){
            	listU.add("<0.0001");
            }
            else{
            	BigDecimal bd1 = new BigDecimal(du);
            	listU.add(bd1.setScale(4, BigDecimal.ROUND_HALF_UP).toPlainString());
            }
            if(strSplit.equals("1") || strSplit.equals("3") || strSplit.equals("4") || strSplit.equals("5") || strSplit.equals("6"))
            {	
            	//re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"green\",\"red\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\""+strLab2+"\", \""+strLab1+"\"),legend = c(0.85, 0.9))");
            	re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,title='"+strAvg+"',risk.table=TRUE,xlab=\"Months\",palette=c(\"green\",\"red\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\""+strLab2+"\", \""+strLab1+"\"),legend = \"none\")");//legend = c(0.85, 0.9)
            	re.eval("ggsurv$table <- ggsurv$table + theme(axis.text.y = element_text(colour='black'))");
            	re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.80, y = 0.97,label = \""+strLab1+"\", size = 4,color='red')");
            	re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.80, y = 0.92,label = \""+strLab2+"\", size = 4,color='green')");
            }            
            else if(strSplit.equals("8"))
            {
            	re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"red\",\"black\",\"green\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\""+strLab1+"\", \""+strLab2+"\",\""+strLab3+"\"),legend = 'none')");
            	re.eval("ggsurv$table <- ggsurv$table + theme(axis.text.y = element_text(colour='black'))");
            	re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.80-2, y = 0.97,label = \""+strLab1+"\", size = 4,color='red')");
             	re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.80, y = 0.92,label = \""+strLab2+"\", size = 4,color='black')");
             	re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.80-2, y = 0.87,label = \""+strLab3+"\", size = 4,color='green')");
            }
            else if(strSplit.equals("9"))
            {
            	re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"red\",\"black\",\"#E7B800\",\"green\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\""+strLab1+"\", \""+strLab2+"\",\""+strLab3+"\",\""+strLab4+"\"),legend = 'none')");
            	re.eval("ggsurv$table <- ggsurv$table + theme(axis.text.y = element_text(colour='black'))");
            	re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85-2, y = 0.97,label = \""+strLab1+"\", size = 4,color='red')");
             	re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85, y = 0.92,label = \""+strLab2+"\", size = 4,color='black')");
             	re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85-2, y = 0.87,label = \""+strLab3+"\", size = 4,color='#E7B800')");
             	re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85, y = 0.82,label = \""+strLab4+"\", size = 4,color='green')");
            	
            }
            else
            {
            	//re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"green\",\"red\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\""+strLab2+"\", \""+strLab1+"\"),legend = c(0.85, 0.9))");
            	re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,legend.labs=c('"+strLab1+"','"+strLab2+"'), legend.title=\"Treatment\",risk.table=TRUE,xlab=\"Months\",palette=c(\"red\",\"green\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\""+strLab1+"\", \""+strLab2+"\"),legend = \"none\")");
            	re.eval("ggsurv$table <- ggsurv$table + theme(axis.text.y = element_text(colour='black'))");            	
            	re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.80, y = 0.97,label = \""+strLab1+"\", size = 4,color='green')");
            	re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.80, y = 0.92,label = \""+strLab2+"\", size = 4,color='red')");
            }
            //re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12, y = 0.15,label = paste(\"p=\",round(p.val,4)), size = 5)");
            re.eval("if(p.val<0.0001){ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12, y = 0.15,label = \"p<0.0001\", size = 5)} else{ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12, y = 0.15,label = paste(\"p=\",round(p.val,4)), size = 5)}");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12+2, y = 0.09,label = paste(\"HR=\",round(p.hr,4)), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.26, y = 0.03,label = paste('( 95%CI,',round(p.l,4),'-',round(p.t,4),')'), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85, y = 0.05,label = \"OSppc\", size = 5,fontface = 'italic')");
            //re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.5, y = 0.95,label = \""+strAvg+"\", size = 5)");
            re.eval("ggsurv");
        	//re.eval("ggsave(file = \""+strPath+"\", print(ggsurv))");
            re.eval("ggsave(file = \""+strPath+"\", width=6,height=6, type = \"cairo\",print(ggsurv))");
        }
        catch (REngineException e) {  
            e.printStackTrace();  
        } finally {  
            re.close();     
        } 
        return strTip;
	}
}
