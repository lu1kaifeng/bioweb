package edu.henu.bioweb.Common;

import java.sql.Connection;
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

public class DRMCom {
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
		String strSql="select count(*) as num from "+strGSE+" where geneName='"+strGene+"'"; 
		Context initCtx;
		Context ctx;
		Object  obj;
		DataSource ds = null;		
        try {
        	initCtx = new InitialContext();
        	ctx = (Context)initCtx.lookup("java:comp/env");
        	obj = (Object)ctx.lookup("jdbc/SSDRM");
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
	
	public List<MRDIF> GetDifferential(String[] strSql) 
	{
		List<MRDIF> list = new ArrayList();
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
        	obj = (Object)ctx.lookup("jdbc/SSDRM");
            ds = (DataSource)obj;
            
        } catch (NamingException e) {
            e.printStackTrace();
        }
				
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();			
			for(int i=0;i<strSql.length;i++) {
				int k = 0;
				rs = stmt.executeQuery(strSql[i]);
				while (rs.next()) {
					double dbfc = rs.getDouble("foldchange");
					double dbValue = rs.getDouble("pvalue");
					MRDIF mr = new MRDIF();
					mr.dbFC=dbfc;
					mr.dbPvalue=dbValue;
					list.add(mr);
					k++;
				}
				if(k==0) {
					MRDIF mr = new MRDIF();
					mr.dbFC=-100000;
					mr.dbPvalue=-100000;
					list.add(mr);
				}
				if (rs != null) {
					rs.close();
					rs = null;
				}
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
	
	public void GetHeatmap(String strSql, String strPath, int n) throws RserveException {
		RConnection re = new RConnection("127.0.0.1");	
        try {
        	re.eval("library(RODBC)");
        	re.eval("library(pheatmap)");
        	re.eval("db<-odbcDriverConnect('driver={SQL Server};server=(local);database=DRMDB;user=sa;pwd=gxqktzwq')");
        	re.eval("gn<-sqlQuery(db,\""+strSql+"\")");
        	re.eval("gn<-aggregate(x=gn,by=list(gn$geneName),FUN=mean,na.rm=T)"); 
        	re.eval("rownames(gn)<-gn[,1]");
        	re.eval("gn<-gn[,4:ncol(gn)]");
        	re.eval("gn<-t(gn)");
        	re.eval("pheatmap(gn,file='"+strPath+"')");
        	re.eval("odbcClose(db)");
        }
        catch (REngineException e) {  
            e.printStackTrace();  
        } 
        finally {  
            re.close();     
        }
	}
	
	public void GetVolcano(String strSql, String strPath) throws RserveException {
		RConnection re = new RConnection("127.0.0.1");	
        try {   
            re.eval("library(ggplot2)");
            re.eval("library(RODBC)");
            re.eval("db<-odbcDriverConnect('driver={SQL Server};server=(local);database=DRMDB;user=sa;pwd=gxqktzwq')");
            re.eval("gn<-sqlQuery(db,\""+strSql+"\")");
            re.eval("gn<-gn[,-6]");
            re.eval("gn<-gn[,-4]");
            re.eval("gn$genid = factor(ifelse(gn$pvalue < 0.05 & abs(gn$foldchange) >= 1, ifelse(gn$foldchange>= 1 ,'Up','Down'),'NoSignifi'),levels=c('Up','Down','NoSignifi'))");
            re.eval("jpeg('"+strPath+"')");
            re.eval("ggplot(gn,aes(x=foldchange,y=-log10(pvalue),color=genid))+geom_point()+scale_color_manual(values=c(\"#DC143C\",\"#00008B\",\"#808080\"))+ylab('-log10 (P value)')+xlab('log2 (FoldChange)')+geom_vline(xintercept=c(-1,1),lty=3,col=\"black\",lwd=0.5)+geom_hline(yintercept = -log10(0.05),lty=3,col=\"black\",lwd=0.5) +theme(plot.title = element_text(hjust = 0.5), legend.position=\"right\", legend.title = element_blank())");            
            re.eval("dev.off();");
        	re.eval("odbcClose(db)");
        }
        catch (REngineException e) {  
            e.printStackTrace();  
        } finally {  
            re.close();     
        } 
	}
	
	public void GetBox(String strSql, String strGene, String strData, String strPath) throws RserveException, REXPMismatchException {
		String strTip = "";
		RConnection re = new RConnection("127.0.0.1");	
        try {   
            re.eval("library(ggplot2)");
            re.eval("library(ggpubr)");
            re.eval("library(ggsci)");
            re.eval("library(RODBC)");
            re.eval("db<-odbcDriverConnect('driver={SQL Server};server=(local);database=DRMDB;user=sa;pwd=gxqktzwq')");
            re.eval("dd<-sqlQuery(db,\""+strSql+"\")");
            re.eval("dd<-aggregate(x=dd,by=list(dd$geneName),FUN=mean,na.rm=T)");
            re.eval("dd<-dd[,4:ncol(dd)]");
            re.eval("a<-data.frame(t(dd))");
            re.eval("k<-0");
            re.eval("if('"+strGene+"'>'MFSType'){k<-1} else {k<-2}" );
            
            re.eval("for(i in 1:nrow(a)){if(a[i,k]==0){a[i,k]='Metastasis'} else if(a[i,k]==1){a[i,k]='Primary'} else if(a[i,k]==2){a[i,k]='Primary tumor without metastasis'}" + 
            		" else if(a[i,k]==3){a[i,k]='Primary tumor with metastasis'}" + 
            		" else if(a[i,k]==4){a[i,k]='Normal'}" + 
            		" else if(a[i,k]==5){a[i,k]='Lymph node metastasis'}" + 
            		" else if(a[i,k]==6){a[i,k]='Primary melanoma without metastasis'}" + 
            		" else if(a[i,k]==7){a[i,k]='Primary melanoma with metastasis'}" + 
            		" else if(a[i,k]==8){a[i,k]='Normal skin'}" + 
            		" else if(a[i,k]==9){a[i,k]='Primary melanoma'}" + 
            		" else if(a[i,k]==10){a[i,k]='Melanoma metastasis'}" + 
            		" else if(a[i,k]==11){a[i,k]='Lung metastasis'}" + 
            		" else if(a[i,k]==12){a[i,k]='Primary Tumor'}" + 
            		" else if(a[i,k]==13){a[i,k]='Omental Metastases'}" + 
            		" else if(a[i,k]==14){a[i,k]='Intraperitoneal metastasis'}" + 
            		" else if(a[i,k]==15){a[i,k]='Liver metastasis'}" + 
            		" else if(a[i,k]==16){a[i,k]='Primary PDAC'}" + 
            		" else if(a[i,k]==17){a[i,k]='Bone metastasis'}" + 
            		" else if(a[i,k]==18){a[i,k]='Normal bone marrow'}}");
            
            re.eval("if(k==2){colnames(a)<-c('Gene Expression','Group')} else {colnames(a)<-c('Group','Gene Expression')}");
            re.eval("jpeg('"+strPath+"')");
            re.eval("p <- ggboxplot(a, x = 'Group', y = 'Gene Expression',  color = 'Group', palette = 'jama', add = 'jitter')+ stat_compare_means()");
            //re.eval("ggsurv<-p + stat_compare_means()");
            re.eval("print(p)");
            //re.eval("ggsave(file = "+strPath+")"); 
            re.eval("dev.off();");
        	re.eval("odbcClose(db)");
        }
        catch (REngineException e) {  
            e.printStackTrace();  
        } finally {  
            re.close();     
        } 
	}
}
