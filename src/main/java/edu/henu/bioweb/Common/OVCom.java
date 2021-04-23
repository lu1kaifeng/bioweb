package edu.henu.bioweb.Common;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

public class OVCom {
    public String strAUC;
    public String strIndex;

    public boolean isExistByGene(String strGene, String strGSE, String strDB) {
        boolean result = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ex) {

        }
        Connection conn;
        Statement stmt;
        ResultSet rs;
        String strSql = "select count(*) as num from gen_" + strGSE + " where genName='" + strGene + "'";
        Context initCtx;
        Context ctx;
        Object obj;
        DataSource ds = null;
        try {
            initCtx = new InitialContext();
            ctx = (Context) initCtx.lookup("java:comp/env");
            obj = (Object) ctx.lookup(strDB);//jdbc/SSOV
            ds = (DataSource) obj;

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
                if (strnum.equals("0")) {
                    result = false;
                } else {
                    result = true;
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

    public String GetRString(String strSql, String strSurvival, String res, String strDB) {
        String strR = "-1";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ex) {

        }
        Connection conn;
        Statement stmt;
        ResultSet rs;

        Context initCtx;
        Context ctx;
        Object obj;
        DataSource ds = null;
        try {
            initCtx = new InitialContext();
            ctx = (Context) initCtx.lookup("java:comp/env");
            obj = (Object) ctx.lookup(strDB);//jdbc/SSBRCA2
            ds = (DataSource) obj;

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
                String strtnm = "";
                String strValue = rs.getString("genValue");

                if (i == 0) {
                    if (strSurvival.equals("0")) {
                        strTime = rs.getString("OS");
                        strEvent = rs.getString("OS_Event");
                        strR = "";
                        strR = res + "<-data.frame(OS=c(" + strTime + "),OS_Event=c(" + strEvent + "),genValue=c(" + strValue + "))\n";
                    } else if (strSurvival.equals("1")) {
                        strTime = rs.getString("DFI");
                        strEvent = rs.getString("DFI_Event");
                        strR = "";
                        strR = res + "<-data.frame(DFI=c(" + strTime + "),DFI_Event=c(" + strEvent + "),genValue=c(" + strValue + "))\n";
                    } else if (strSurvival.equals("2")) {
                        strTime = rs.getString("PFI");
                        strEvent = rs.getString("PFI_Event");
                        strR = "";
                        strR = res + "<-data.frame(PFI=c(" + strTime + "),PFI_Event=c(" + strEvent + "),genValue=c(" + strValue + "))\n";
                    } else if (strSurvival.equals("3")) {
                        strTime = rs.getString("DSS");
                        strEvent = rs.getString("DSS_Event");
                        strR = "";
                        strR = res + "<-data.frame(DSS=c(" + strTime + "),DSS_Event=c(" + strEvent + "),genValue=c(" + strValue + "))\n";
                    } else if (strSurvival.equals("4")) {
                        strTime = rs.getString("PFS");
                        strEvent = rs.getString("PFS_Event");
                        strR = "";
                        strR = res + "<-data.frame(PFS=c(" + strTime + "),PFS_Event=c(" + strEvent + "),genValue=c(" + strValue + "))\n";
                    } else if (strSurvival.equals("6")) {
                        strTime = rs.getString("DFS");
                        strEvent = rs.getString("DFS_Event");
                        strR = "";
                        strR = res + "<-data.frame(DFS=c(" + strTime + "),DFS_Event=c(" + strEvent + "),genValue=c(" + strValue + "))\n";
                    } else if (strSurvival.equals("8")) {
                        strTime = rs.getString("MFS");
                        strEvent = rs.getString("MFS_Event");
                        strR = "";
                        strR = res + "<-data.frame(MFS=c(" + strTime + "),MFS_Event=c(" + strEvent + "),genValue=c(" + strValue + "))\n";
                    } else if (strSurvival.equals("11")) {
                        strTime = rs.getString("RFS");
                        strEvent = rs.getString("RFS_Event");
                        strR = "";
                        strR = res + "<-data.frame(RFS=c(" + strTime + "),RFS_Event=c(" + strEvent + "),genValue=c(" + strValue + "))\n";
                    }
                } else {
                    if (strSurvival.equals("0")) {
                        strTime = rs.getString("OS");
                        strEvent = rs.getString("OS_Event");
                    }
                    if (strSurvival.equals("1")) {
                        strTime = rs.getString("DFI");
                        strEvent = rs.getString("DFI_Event");
                    }
                    if (strSurvival.equals("2")) {
                        strTime = rs.getString("PFI");
                        strEvent = rs.getString("PFI_Event");
                    }
                    if (strSurvival.equals("3")) {
                        strTime = rs.getString("DSS");
                        strEvent = rs.getString("DSS_Event");
                    }
                    if (strSurvival.equals("4")) {
                        strTime = rs.getString("PFS");
                        strEvent = rs.getString("PFS_Event");
                    }
                    if (strSurvival.equals("5")) {
                        strTime = rs.getString("DMFS");
                        strEvent = rs.getString("DMFS_Event");
                    }
                    if (strSurvival.equals("6")) {
                        strTime = rs.getString("DFS");
                        strEvent = rs.getString("DFS_Event");
                    }
                    if (strSurvival.equals("7")) {
                        strTime = rs.getString("DRFS");
                        strEvent = rs.getString("DRFS_Event");
                    }
                    if (strSurvival.equals("8")) {
                        strTime = rs.getString("MFS");
                        strEvent = rs.getString("MFS_Event");
                    }
                    if (strSurvival.equals("9")) {
                        strTime = rs.getString("LMFS");
                        strEvent = rs.getString("LMFS_Event");
                    }
                    if (strSurvival.equals("10")) {
                        strTime = rs.getString("BMFS");
                        strEvent = rs.getString("BMFS_Event");
                    }
                    if (strSurvival.equals("11")) {
                        strTime = rs.getString("RFS");
                        strEvent = rs.getString("RFS_Event");
                    }
                    strR += "res1<-c(" + strTime + "," + strEvent + "," + strValue + ")\n";
                    strR += res + "<-rbind(" + res + ",res1)\n";
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

    public String GetPath(String strSql, String strSplit, String strPath, String strGen, String strAvg, String strSurvival, String strDB, String strCancer, String tip) throws RserveException, REXPMismatchException {
        ComFun fun = new ComFun();
        String strTip = "";
        strSql = GetRString(strSql, strSurvival, "res", strDB);
        if (strSql.equals("-1")) {

            strTip = fun.GetTip(3, tip, strGen, "OV");//"Number of OV patients was less than 4 in "+tip+", No meaningful output.";
            return strTip;
        }
        RConnection re = new RConnection("127.0.0.1");
        try {
            re.eval("library(survival)");
            re.eval("library(ggplot2)");
            re.eval("library(magrittr)");
            re.eval("library(ggpubr)");
            re.eval("library(survminer)");
            re.eval("library(gplots)");
            re.eval("library(ROCR)");
            re.eval(strSql);
            REXP x = re.eval("r<-nrow(res)");
            int i = x.asInteger();
            if (i < 4 && i > 0) {
                strTip = fun.GetTip(2, tip, strGen, "OV");
                return strTip;
            } else if (i == 0) {
                strTip = fun.GetTip(3, tip, strGen, "OV");
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

            re.eval("rr<-res");

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
            // 1 - pchisq(surv_diff$chisq, length(surv_diff$n) -1)
            REXP xlg = re.eval("plg<-1-pchisq(gen.dif$chisq, length(gen.dif$n) -1)");
            re.eval("p.cox<-summary(coxph(Surv(gen[,1],gen[,2])~gen[,3]))");
            re.eval("p.val<-p.cox$coefficients[5]");
            re.eval("p.hr<-p.cox$coefficients[2]");
            re.eval("p.l<-p.cox$conf.int[3]");
            re.eval("p.t<-p.cox$conf.int[4]");

            re.eval("cindex<-p.cox$concordance");
            if (strSurvival.equals("0")) {
                re.eval("pred<-prediction(rr$genValue,rr$OS_Event)");
            } else if (strSurvival.equals("1")) {
                re.eval("pred<-prediction(rr$genValue,rr$DFI_Event)");
            } else if (strSurvival.equals("2")) {
                re.eval("pred<-prediction(rr$genValue,rr$PFI_Event)");
            } else if (strSurvival.equals("3")) {
                re.eval("pred<-prediction(rr$genValue,rr$DSS_Event)");
            } else if (strSurvival.equals("4")) {
                re.eval("pred<-prediction(rr$genValue,rr$PFS_Event)");
            } else if (strSurvival.equals("6")) {
                re.eval("pred<-prediction(rr$genValue,rr$DFS_Event)");
            }
            re.eval("auc<-performance(pred,\"auc\")@y.values");
            REXP xauc = re.eval("genauc<-round(auc[[1]],3)");
            REXP xindex = re.eval("genindex<-round(cindex[1],3)");
            strAUC = xauc.asString();
            strIndex = xindex.asString();
            String stra = "AUC " + strAUC;
            String strc = "C-index " + strIndex;
            DecimalFormat df = new DecimalFormat("###0.0000");
            //String strlg = "log-rank p " + df.format(xlg.asDouble());
            if (strSplit.equals("1") || strSplit.equals("3") || strSplit.equals("4") || strSplit.equals("5") || strSplit.equals("6")) {
                //re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"green\",\"red\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\""+strLab2+"\", \""+strLab1+"\"),legend = c(0.85, 0.9))");
                re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"green\",\"red\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\"" + strLab2 + "\", \"" + strLab1 + "\"),legend = \"none\")");//legend = c(0.85, 0.9)
                re.eval("ggsurv$table <- ggsurv$table + theme(axis.text.y = element_text(colour='black'))");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85, y = 0.97,label = \"" + strLab1 + "\", size = 4,color='red')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85, y = 0.92,label = \"" + strLab2 + "\", size = 4,color='green')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85, y = 0.87,label = \"" + stra + "\", size = 4,color='grey')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85, y = 0.82,label = \"" + strc + "\", size = 4,color='grey')");
                //re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.83, y = 0.77,label = \""+strlg+"\", size = 4,color='grey')");
            } else if (strSplit.equals("8")) {
                re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"red\",\"black\",\"green\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\"" + strLab1 + "\", \"" + strLab2 + "\",\"" + strLab3 + "\"),legend = 'none')");
                re.eval("ggsurv$table <- ggsurv$table + theme(axis.text.y = element_text(colour='black'))");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.80-2, y = 0.97,label = \"" + strLab1 + "\", size = 4,color='red')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.80, y = 0.92,label = \"" + strLab2 + "\", size = 4,color='black')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.80-2, y = 0.87,label = \"" + strLab3 + "\", size = 4,color='green')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.88, y = 0.82,label = \"" + stra + "\", size = 4,color='grey')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.88, y = 0.77,label = \"" + strc + "\", size = 4,color='grey')");
                //re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.88, y = 0.72,label = \""+strlg+"\", size = 4,color='grey')");
            } else if (strSplit.equals("9")) {
                re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"red\",\"black\",\"#E7B800\",\"green\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\"" + strLab1 + "\", \"" + strLab2 + "\",\"" + strLab3 + "\",\"" + strLab4 + "\"),legend = 'none')");
                re.eval("ggsurv$table <- ggsurv$table + theme(axis.text.y = element_text(colour='black'))");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85-2, y = 0.97,label = \"" + strLab1 + "\", size = 4,color='red')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85, y = 0.92,label = \"" + strLab2 + "\", size = 4,color='black')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85-2, y = 0.87,label = \"" + strLab3 + "\", size = 4,color='#E7B800')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85, y = 0.82,label = \"" + strLab4 + "\", size = 4,color='green')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.88, y = 0.77,label = \"" + stra + "\", size = 4,color='grey')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.88, y = 0.72,label = \"" + strc + "\", size = 4,color='grey')");
                //re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.88, y = 0.67,label = \""+strlg+"\", size = 4,color='grey')");
            } else {
                //re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"green\",\"red\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\""+strLab2+"\", \""+strLab1+"\"),legend = c(0.85, 0.9))");
                re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"red\",\"green\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\"" + strLab1 + "\", \"" + strLab2 + "\"),legend = \"none\")");
                re.eval("ggsurv$table <- ggsurv$table + theme(axis.text.y = element_text(colour='black'))");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85, y = 0.97,label = \"" + strLab1 + "\", size = 4,color='green')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85, y = 0.92,label = \"" + strLab2 + "\", size = 4,color='red')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85, y = 0.87,label = \"" + stra + "\", size = 4,color='grey')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85, y = 0.82,label = \"" + strc + "\", size = 4,color='grey')");
                //re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.83, y = 0.77,label = \""+strlg+"\", size = 4,color='grey')");
            }


            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12, y = 0.15,label = paste(\"p=\",round(p.val,4)), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12+2, y = 0.09,label = paste(\"HR=\",round(p.hr,4)), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.26, y = 0.03,label = paste('( 95%CI,',round(p.l,4),'-',round(p.t,4),')'), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85, y = 0.05,label = \"" + strCancer + "\", size = 5,fontface = 'italic')");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.5, y = 0.95,label = \"" + strAvg + "\", size = 5)");
            re.eval("ggsurv");
            re.eval("ggsave(file = \"" + strPath + "\", print(ggsurv))");
        } catch (REngineException e) {
            e.printStackTrace();
        } finally {
            re.close();
        }
        return strTip;
    }

}
