package edu.henu.bioweb.Common;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class ImmuneCom {
	
	public String GetLinear(String strSql1, String strSql2, String strPath, String strDB) throws REXPMismatchException, RserveException{
		String strTip="";
		RConnection re = new RConnection("127.0.0.1");	
		try {
					re.eval("library(ggplot2)");
					re.eval("library(gcookbook)");
					re.eval("library(RODBC)");
					re.eval("library(magrittr)");
					re.eval("library(ggpubr)");		            
		            re.eval("db<-odbcDriverConnect('driver={SQL Server};server=(local);database=IMUNEDB;user=sa;pwd=gxqktzwq')");		            
		            re.eval("rlin<-sqlQuery(db,\""+strSql1+"\")");
		            re.eval("db2<-odbcDriverConnect('driver={SQL Server};server=(local);database="+strDB+";user=sa;pwd=gxqktzwq')");
		            re.eval("rlin2<-sqlQuery(db2,\""+strSql2+"\")");
		            re.eval("rlin<-cbind(rlin,rlin2)");
		            re.eval("ggsurv<-ggplot(rlin,aes(x=mRNA,y=Protein))+geom_point()+labs(x = \"mRNA(log2(norm_count+1))\", y = \"Protein(RPPA(replicate-base normalization))\")+geom_smooth(method=lm)+stat_cor(data=rlin, method = \"pearson\",size=5)+ theme(plot.title = element_text(size = 20),axis.text=element_text(size=12,face = \"bold\"),axis.title.x=element_text(size=14),axis.title.y=element_text(size=14))");		            
		            re.eval("ggsave(file = \""+strPath+"\", width=6,height=6, type = \"cairo\",print(ggsurv))");		            
		            re.eval("odbcClose(db)");
			}
		catch(REngineException e1){
			e1.printStackTrace();
		}		
		finally{
			re.close();
		}
		return strTip;
	}
	
	public String GetAnalysis(String strSql, String strPath, String sql2, int num, String strPdf) throws RserveException, REXPMismatchException 
	{	
		ComFun fun = new ComFun();
		String strTip = "";
		RConnection re = new RConnection("127.0.0.1");	
        try {   
        	re.eval("library(survival)");
        	re.eval("library(RODBC)");
            re.eval("library(ggplot2)");
            re.eval("db<-odbcDriverConnect('driver={SQL Server};server=(local);database=IMUNDB;user=sa;pwd=gxqktzwq')");
            re.eval("gn<-sqlQuery(db,\""+strSql+"\")");
            re.eval("res<-sqlQuery(db,\""+sql2+"\")");
            REXP x = re.eval("n<-nrow(gn)");
            int i = x.asInteger();
            if(i<4 && i>0)
            {            	
            	//strTip = fun.GetTip(2, tip, strGen, "BLCA");
                return strTip;
            }
            else if(i==0)
            {
            	//strTip = fun.GetTip(3, tip, strGen, "BLCA");
                return strTip;
            }
            else
            {
            	strTip = " ";
            }
            re.eval("attach(gn)");
            REXP y = re.eval("c<-nrow(res)");
            re.eval("p<-ggplot(gn,aes(genName,genGSM))+xlab('')+ylab('')+geom_tile(aes(fill=genValue),colour='white')+scale_fill_gradient(low='white',high='steelblue',name = 'Expression') + theme(axis.text=element_text(size=1))");
            //re.eval("");
            //re.eval("p<-p+geom_violin()+ggplot2::annotate('text', x = 3, y = gmax[1,1]+0.02,label = paste('F value = ',rf), size = 5)");
            //re.eval("p<-p+geom_violin()+ggplot2::annotate('text', x = 3, y = gmax[1,1]+0.01,label = paste('Pr(>F) = ',rp), size = 5)");            
            //re.eval("pdf(file=\""+strPath+"\",height=0.2*c,width=2*"+num+")");
            //re.eval("p");
            //,units = 'cm'
            //re.eval("ggsave(file = \""+strPath+"\", height=0.6*c,width=1.5*"+num+",print(p))");
            re.eval("ggsave(plot=p,file = \""+strPath+"\",height=7,width=12)");
            re.eval("ggsave(plot=p,file = \""+strPdf+"\",height=7,width=12)");
            //re.eval("dev.off()");
        }
        catch (REngineException e) {  
            e.printStackTrace();  
        } finally {  
            re.close();     
        } 
        return strTip;
	}	
	
	public String GetDifferential(String strSql, String strPath, String strXlab, String strType, String strAlg) throws RserveException, REXPMismatchException 
	{
		ComFun fun = new ComFun();
		String strTip = "";
		RConnection re = new RConnection("127.0.0.1");	
        try {   
        	re.eval("library(survival)");
        	re.eval("library(RODBC)");
            re.eval("library(ggplot2)");
            re.eval("db<-odbcDriverConnect('driver={SQL Server};server=(local);database=IMUNDB;user=sa;pwd=gxqktzwq')");
            re.eval("gn<-sqlQuery(db,\""+strSql+"\")");
            REXP x = re.eval("n<-nrow(gn)");
            int i = x.asInteger();            
            if(i<4 && i>0)
            {            	
            	//strTip = fun.GetTip(2, tip, strGen, "BLCA");
                return strTip;
            }
            else if(i==0)
            {
            	//strTip = fun.GetTip(3, tip, strGen, "BLCA");
                return strTip;
            }
            else
            {
            	strTip = " ";
            }
            re.eval("attach(gn)");
            re.eval("gmax<-gn[order(-gn[,1]),]");
            re.eval("gn$"+strType+"<-as.factor(gn$"+strType+")");
            re.eval("result<-aov(genValue~"+strType+",data=gn)");
            re.eval("r<-summary(result)");
            re.eval("rf<-round(r[[1]][[\"F value\"]][1],4)");
            re.eval("rp<-round(r[[1]][[\"Pr(>F)\"]][1],4)");
            //geom_boxplot(position = position_dodge(width = 1), outlier.size = 0.7, width = 0.2, show.legend = FALSE) + labs(x = '', y = 'Chao1')
            re.eval("p<-ggplot(gn,aes(x="+strType+",y=genValue,fill="+strType+"))+xlab('"+strXlab+"')+ylab('Cell fractions')+geom_violin(trim=TRUE,color='white') + geom_boxplot(position = position_dodge(width = 1), outlier.size = 0.7, width = 0.2, show.legend = FALSE)+labs(fill='"+strXlab+"')+theme(axis.title=element_text(size=15),axis.text=element_text(size=12))");
            //re.eval("p<-ggplot(gn,aes(x="+strType+",y=genValue))+xlab('Cell fractions')+ylab('')+guides(fill=F)");
            //legend.title=element_text(face="italic", family="Times", colour="black",size=18)
            //geom_violin() + geom_boxplot(width=.1, fill="black", outlier.colour = NA) +
            //stat_summary(fun.y = median, geom="point", fill="white", shape=21, size=2.5)  
            
            re.eval("p<-p+ggplot2::annotate('text', x = 1.5, y = gmax[1,1]+gmax[1,1]*0.18,label = paste('F value = ',rf), size = 5)");
            re.eval("p<-p+ggplot2::annotate('text', x = 1.5, y = gmax[1,1]+gmax[1,1]*0.1,label = paste('Pr(>F) = ',rp), size = 5)");            
            re.eval("p");
            re.eval("ggsave(file = \""+strPath+"\", print(p))");
        }
        catch (REngineException e) {  
            e.printStackTrace();  
        } finally {  
            re.close();     
        } 
        return strTip;
	}	
	
	public String GetPath(String strSql, String strSplit, String strPath, String strGen, String strAvg, String strSurvival, String strDB, String strCancer, String tip) throws RserveException, REXPMismatchException 
	{	
		ComFun fun = new ComFun();
		String strTip = "";
		RConnection re = new RConnection("127.0.0.1");	
        try {   
        	re.eval("library(survival)");
        	re.eval("library(RODBC)");
            re.eval("library(ggplot2)");
            re.eval("library(magrittr)");
            re.eval("library(ggpubr)");
            re.eval("library(survminer)");
            re.eval("db<-odbcDriverConnect('driver={SQL Server};server=(local);database=IMUNDB;user=sa;pwd=gxqktzwq')");
            re.eval("res<-sqlQuery(db,\""+strSql+"\")");
            REXP x = re.eval("r<-nrow(res)");
            int i = x.asInteger();            
            if(i<4 && i>0)
            {            	
            	strTip = fun.GetTip(2, tip, strGen, "BLCA");
                return strTip;
            }
            else if(i==0)
            {
            	strTip = fun.GetTip(3, tip, strGen, "BLCA");
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
            		strLab1="Upper 50%";
            		strLab2="Other  50%";
            		break;
            	case "1":
            		strLab1="Upper 30%";
            		strLab2="Other  70%";
            		break;
            	case "2":
            		strLab1="Upper 25%";
            		strLab2="Other  75%";
            		break;
            }
            
            re.eval("gmax<-res[order(-res[,1]),]");            
            re.eval("gen<-res[order(-res[,3]),]");       
            if(strSplit.equals("0"))
            {
            	re.eval("r1<-round(r/2)");
            	re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}else{gen.up<-gen[1:(r1+1),];gen.low<-gen[(r1+2):r,]}}else{gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}");
            }            
            else if(strSplit.equals("1"))
            {
            	re.eval("r1<-round(r*0.3)");
            	re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}else{gen.up<-gen[1:(r1+1),];gen.low<-gen[(r1+2):r,]}}else{gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}");
            }
            else if(strSplit.equals("2"))
            {
            	re.eval("r1<-round(r/4)");
            	re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}else{gen.up<-gen[1:(r1+1),];gen.low<-gen[(r1+2):r,]}}else{gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}");
            }
            re.eval("gen.up[,3]=1");
        	re.eval("gen.low[,3]=0");
            re.eval("gen<-rbind(gen.up,gen.low)");
            re.eval("gen.surv<-survfit(Surv(gen[,1],gen[,2])~gen[,3])");
            re.eval("gen.dif<-survdiff(Surv(gen[,1],gen[,2])~gen[,3])");  
            re.eval("p.cox<-summary(coxph(Surv(gen[,1],gen[,2])~gen[,3]))");            
            re.eval("p.val<-p.cox$coefficients[5]");
            re.eval("p.hr<-p.cox$coefficients[2]");
            re.eval("p.l<-p.cox$conf.int[3]");
            re.eval("p.t<-p.cox$conf.int[4]");
            re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"green\",\"red\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\""+strLab2+"\", \""+strLab1+"\"),legend = \"none\")");//legend = c(0.85, 0.9)
        	re.eval("ggsurv$table <- ggsurv$table + theme(axis.text.y = element_text(colour='black'))");
        	re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.88, y = 0.97,label = \""+strLab1+"\", size = 4,color='red')");
        	re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.88, y = 0.92,label = \""+strLab2+"\", size = 4,color='green')");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12, y = 0.15,label = paste(\"p=\",round(p.val,4)), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12+2, y = 0.09,label = paste(\"HR=\",round(p.hr,4)), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.26, y = 0.03,label = paste('( 95%CI,',round(p.l,4),'-',round(p.t,4),')'), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85, y = 0.05,label = \""+strCancer+"\", size = 5,fontface = 'italic')");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.5, y = 0.95,label = \""+strAvg+"\", size = 5)");
            re.eval("ggsurv");
        	re.eval("ggsave(file = \""+strPath+"\", print(ggsurv))");
        }
        catch (REngineException e) {  
            e.printStackTrace();  
        } finally {  
            re.close();     
        } 
        return strTip;
	}
}
