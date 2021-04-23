package edu.henu.bioweb.Common;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class OSCom {

    public String GetPath(String strSql, String strSplit, String strPath, String strGen, String strAvg, String strSurvival, String strDB, String strCancer, String tip) throws RserveException, REXPMismatchException, REngineException {
        String strTip = "";
        //strSql = GetRString(strSql, strSurvival,"res", strDB);
        ComFun fun = new ComFun();
		/*if(strSql.equals("-1"))
		{
			strTip = fun.GetTip(3, tip, strGen, "CRC");
            return strTip;
		}*/

        RConnection re = new RConnection("127.0.0.1");
        try {
            re.eval("library(survival)");
            re.eval("library(ggplot2)");
            re.eval("library(magrittr)");
            re.eval("library(ggpubr)");
            re.eval("library(survminer)");
            re.eval("library(RODBC)");
            re.eval("db<-odbcDriverConnect('driver={SQL Server};server=(local);database=" + strDB + ";user=sa;pwd=gxqktzwq')");
            //select OS,OS_Event,avg(genValue) as genvalue from sample_GSE12945,gen_GSE12945_2 where genName='ROR2' and gen_GSE12945_2.genGSM = sample_GSE12945.GSM and OS is not null and OS_Event is not null  and genValue is not null  group by sample_GSE12945.GSM,OS_Event,OS
            re.eval("res<-sqlQuery(db,\"" + strSql + "\")");
            re.eval(strSql);
            REXP x = re.eval("r<-nrow(res)");
            double i = x.asDouble();
            if (i < 4 && i > 0) {
                strTip = fun.GetTip(2, tip, strGen, "CRC");
                return strTip;
            } else if (i == 0) {
                strTip = fun.GetTip(3, tip, strGen, "CRC");
                return strTip;
            } else {
                strTip = " ";
            }
            re.eval("attach(res)");

            String strLab1 = "";
            String strLab2 = "";
            String strLab3 = "";
            String strLab4 = "";
            switch (strSplit) {
                case "0":
                    strLab1 = "Lower 25%";
                    strLab2 = "Other  75%";
                    break;
                case "1":
                    strLab1 = "Upper 25%";
                    strLab2 = "Other  75%";
                    break;
                case "2":
                    strLab1 = "Lower 30%";
                    strLab2 = "Other  70%";
                    break;
                case "3":
                    strLab1 = "Upper 30%";
                    strLab2 = "Other  70%";
                    break;
                case "4":
                    strLab1 = "Upper 50%";
                    strLab2 = "Other  50%";
                    break;
                case "5":
                    strLab1 = "Upper 25%";
                    strLab2 = "Other  25%";
                    break;
                case "6":
                    strLab1 = "Upper 30%";
                    strLab2 = "Other  30%";
                    break;
                case "7":
                    strLab1 = "Lower 50%";
                    strLab2 = "Other  50%";
                    break;
                case "8":
                    strLab1 = "High expression";
                    strLab2 = "Medium expression";
                    strLab3 = "Low expression";
                    break;
                case "9":
                    strLab1 = "First Quartile";
                    strLab2 = "Second Quartile";
                    strLab3 = "Third Quartile";
                    strLab4 = "Fourth Quartile";
                    break;
            }


            re.eval("gmax<-res[order(-res[,1]),]");


            if (strSplit.equals("0") || strSplit.equals("2") || strSplit.equals("7")) {
                re.eval("gen<-res[order(res[,3]),]");
            } else {
                re.eval("gen<-res[order(-res[,3]),]");
            }

            if (strSplit.equals("0") || strSplit.equals("1")) {
                re.eval("r1<-round(r/4)");
                re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}else{gen.up<-gen[1:(r1+1),];gen.low<-gen[(r1+2):r,]}}else{gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}");
            } else if (strSplit.equals("2") || strSplit.equals("3")) {
                re.eval("r1<-round(r*0.3)");
                re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}else{gen.up<-gen[1:(r1+1),];gen.low<-gen[(r1+2):r,]}}else{gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}");
            } else if (strSplit.equals("4") || strSplit.equals("7")) {
                re.eval("r1<-round(r/2)");
                re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}else{gen.up<-gen[1:(r1+1),];gen.low<-gen[(r1+2):r,]}}else{gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}");
            } else if (strSplit.equals("5")) {
                re.eval("r1<-round(r/4)");
                re.eval("r2<-r-r1");
                re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];}else{gen.up<-gen[1:(r1+1),];}}else{gen.up<-gen[1:r1,];}");
                re.eval("if(gen[r2+1,3]==gen[r2,3]){suba<-gen[r2+1,3]-gen[r2+2,3];subb<-gen[r2,3]-gen[r2-1,3];if(abs(suba)>abs(subb)){gen.low<-gen[(r2+2):r,];}else{gen.low<-gen[(r2+1):r,];}}else{gen.low<-gen[(r2+1):r,];}");
            } else if (strSplit.equals("6")) {
                re.eval("r1<-round(r*0.3)");
                re.eval("r2<-r-r1");
                re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];}else{gen.up<-gen[1:(r1+1),];}}else{gen.up<-gen[1:r1,];}");
                re.eval("if(gen[r2+1,3]==gen[r2,3]){suba<-gen[r2+1,3]-gen[r2+2,3];subb<-gen[r2,3]-gen[r2-1,3];if(abs(suba)>abs(subb)){gen.low<-gen[(r2+2):r,];}else{gen.low<-gen[(r2+1):r,];}}else{gen.low<-gen[(r2+1):r,];}");
            } else if (strSplit.equals("8")) {
                re.eval("r1<-round(r/3)");
                re.eval("r2<-r1*2");
                re.eval("r3<-r-r2");
                re.eval("gen.up<-gen[1:r1,];gen.mdi<-gen[(r1+1):r2,];gen.low<-gen[(r2+1):r,]");
            } else if (strSplit.equals("9")) {
                re.eval("r1<-round(r/4)");
                re.eval("r2<-r1*2");
                re.eval("r3<-r1*3");
                re.eval("r4<-r-r3");
                re.eval("gen.up<-gen[1:r1,];gen.mu<-gen[(r1+1):r2,];gen.ml<-gen[(r2+1):r3,];gen.low<-gen[(r3+1):r,]");
            }
            if (strSplit.equals("8")) {
                re.eval("gen.up[,3]=0");
                re.eval("gen.mdi[,3]=1");
                re.eval("gen.low[,3]=2");
                re.eval("gen<-rbind(gen.up,gen.mdi)");
                re.eval("gen<-rbind(gen,gen.low)");
            } else if (strSplit.equals("9")) {
                re.eval("gen.up[,3]=0");
                re.eval("gen.mu[,3]=1");
                re.eval("gen.ml[,3]=2");
                re.eval("gen.low[,3]=3");
                re.eval("gen<-rbind(gen.up,gen.mu)");
                re.eval("gen<-rbind(gen,gen.ml)");
                re.eval("gen<-rbind(gen,gen.low)");
            } else {
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

            if (strSplit.equals("1") || strSplit.equals("3") || strSplit.equals("4") || strSplit.equals("5") || strSplit.equals("6")) {
                //re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"green\",\"red\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\""+strLab2+"\", \""+strLab1+"\"),legend = c(0.85, 0.9))");
                re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"green\",\"red\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\"" + strLab2 + "\", \"" + strLab1 + "\"),legend = \"none\")");//legend = c(0.85, 0.9)
                re.eval("ggsurv$table <- ggsurv$table + theme(axis.text.y = element_text(colour='black'))");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.88, y = 0.97,label = \"" + strLab1 + "\", size = 4,color='red')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.88, y = 0.92,label = \"" + strLab2 + "\", size = 4,color='green')");
            } else if (strSplit.equals("8")) {
                re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"red\",\"black\",\"green\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\"" + strLab1 + "\", \"" + strLab2 + "\",\"" + strLab3 + "\"),legend = 'none')");
                re.eval("ggsurv$table <- ggsurv$table + theme(axis.text.y = element_text(colour='black'))");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.80-2, y = 0.97,label = \"" + strLab1 + "\", size = 4,color='red')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.80, y = 0.92,label = \"" + strLab2 + "\", size = 4,color='black')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.80-2, y = 0.87,label = \"" + strLab3 + "\", size = 4,color='green')");
            } else if (strSplit.equals("9")) {
                re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"red\",\"black\",\"#E7B800\",\"green\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\"" + strLab1 + "\", \"" + strLab2 + "\",\"" + strLab3 + "\",\"" + strLab4 + "\"),legend = 'none')");
                re.eval("ggsurv$table <- ggsurv$table + theme(axis.text.y = element_text(colour='black'))");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85-2, y = 0.97,label = \"" + strLab1 + "\", size = 4,color='red')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85, y = 0.92,label = \"" + strLab2 + "\", size = 4,color='black')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85-2, y = 0.87,label = \"" + strLab3 + "\", size = 4,color='#E7B800')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85, y = 0.82,label = \"" + strLab4 + "\", size = 4,color='green')");

            } else {
                //re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"green\",\"red\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\""+strLab2+"\", \""+strLab1+"\"),legend = c(0.85, 0.9))");
                re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"red\",\"green\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\"" + strLab1 + "\", \"" + strLab2 + "\"),legend = \"none\")");
                re.eval("ggsurv$table <- ggsurv$table + theme(axis.text.y = element_text(colour='black'))");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.88, y = 0.97,label = \"" + strLab1 + "\", size = 4,color='green')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.88, y = 0.92,label = \"" + strLab2 + "\", size = 4,color='red')");
            }
            re.eval("if(p.val<0.0001){ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12, y = 0.15,label = \"p<0.0001\", size = 5)} else{ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12, y = 0.15,label = paste(\"p=\",round(p.val,4)), size = 5)}");
            //re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12, y = 0.15,label = paste(\"p=\",round(p.val,4)), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12+2, y = 0.09,label = paste(\"HR=\",round(p.hr,4)), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.26, y = 0.03,label = paste('( 95%CI,',round(p.l,4),'-',round(p.t,4),')'), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.88, y = 0.05,label = \"" + strCancer + "\", size = 5,fontface = 'italic')");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.5, y = 0.95,label = \"" + strAvg + "\", size = 5)");
            re.eval("ggsurv");
            re.eval("ggsave(file = \"" + strPath + "\", print(ggsurv))");
            //re.eval("odbcClose(db)");
        } finally {
            re.close();
        }
        return strTip;
    }
}
