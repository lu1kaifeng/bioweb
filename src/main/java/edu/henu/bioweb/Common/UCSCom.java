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
import java.util.List;

public class UCSCom {
    public String strAUC = " ";
    public String strIndex = " ";

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
                    } else if (strSurvival.equals("5")) {
                        strTime = rs.getString("DMFS");
                        strEvent = rs.getString("DMFS_Event");
                        strR = "";
                        strR = res + "<-data.frame(DMFS=c(" + strTime + "),DMFS_Event=c(" + strEvent + "),genValue=c(" + strValue + "))\n";
                    } else if (strSurvival.equals("6")) {
                        strTime = rs.getString("DFS");
                        strEvent = rs.getString("DFS_Event");
                        strR = "";
                        strR = res + "<-data.frame(DFS=c(" + strTime + "),DFS_Event=c(" + strEvent + "),genValue=c(" + strValue + "))\n";
                    } else if (strSurvival.equals("7")) {
                        strTime = rs.getString("DRFS");
                        strEvent = rs.getString("DRFS_Event");
                        strR = "";
                        strR = res + "<-data.frame(DRFS=c(" + strTime + "),DRFS_Event=c(" + strEvent + "),genValue=c(" + strValue + "))\n";
                    } else if (strSurvival.equals("8")) {
                        strTime = rs.getString("MFS");
                        strEvent = rs.getString("MFS_Event");
                        strR = "";
                        strR = res + "<-data.frame(MFS=c(" + strTime + "),MFS_Event=c(" + strEvent + "),genValue=c(" + strValue + "))\n";
                    } else if (strSurvival.equals("9")) {
                        strTime = rs.getString("LMFS");
                        strEvent = rs.getString("LMFS_Event");
                        strR = "";
                        strR = res + "<-data.frame(LMFS=c(" + strTime + "),LMFS_Event=c(" + strEvent + "),genValue=c(" + strValue + "))\n";
                    } else if (strSurvival.equals("10")) {
                        strTime = rs.getString("BMFS");
                        strEvent = rs.getString("BMFS_Event");
                        strR = "";
                        strR = res + "<-data.frame(BMFS=c(" + strTime + "),BMFS_Event=c(" + strEvent + "),genValue=c(" + strValue + "))\n";
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
            strTip = fun.GetTip(3, tip, strGen, "UCS");
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


            if (strSplit.equals("0") || strSplit.equals("1") || strSplit.equals("9")) {
                if (i < 12 && i > 0) {
                    strTip = "Number of UCS patients you analyzed in at least one of the groups is less than 12 in OSucs, thus no meaningful output returns.";
                    return strTip;
                } else if (i == 0) {
                    strTip = fun.GetTip(3, tip, strGen, "UCS");
                    return strTip;
                } else {
                    strTip = " ";
                }
            } else if (strSplit.equals("2") || strSplit.equals("3")) {
                if (i < 10 && i > 0) {
                    strTip = "Number of UCS patients you analyzed in at least one of the groups is less than 10 in OSucs, thus no meaningful output returns.";
                    return strTip;
                } else if (i == 0) {
                    strTip = fun.GetTip(3, tip, strGen, "UCS");
                    return strTip;
                } else {
                    strTip = " ";
                }
            } else if (strSplit.equals("4") || strSplit.equals("5") || strSplit.equals("6") || strSplit.equals("7")) {
                if (i < 6 && i > 0) {
                    strTip = "Number of UCS patients you analyzed in at least one of the groups is less than 6 in OSucs, thus no meaningful output returns.";
                    return strTip;
                } else if (i == 0) {
                    strTip = fun.GetTip(3, tip, strGen, "UCS");
                    return strTip;
                } else {
                    strTip = " ";
                }
            } else {
                if (i < 9 && i > 0) {
                    strTip = "Number of UCS patients you analyzed in at least one of the groups is less than 9 in OSucs, thus no meaningful output returns.";
                    return strTip;
                } else if (i == 0) {
                    strTip = fun.GetTip(3, tip, strGen, "UCS");
                    return strTip;
                } else {
                    strTip = " ";
                }
            }
            String strLab1 = "";
            String strLab2 = "";
            String strLab3 = "";
            String strLab4 = "";
            switch (strSplit) {
                case "0":
                    strLab1 = "Lower 25%";
                    strLab2 = "Upper  75%";
                    break;
                case "1":
                    strLab1 = "Upper 25%";
                    strLab2 = "Lower  75%";
                    break;
                case "2":
                    strLab1 = "Lower 30%";
                    strLab2 = "Upper  70%";
                    break;
                case "3":
                    strLab1 = "Upper 30%";
                    strLab2 = "Lower  70%";
                    break;
                case "4":
                    strLab1 = "Upper 50%";
                    strLab2 = "Lower  50%";
                    break;
                case "5":
                    strLab1 = "Upper 25%";
                    strLab2 = "Lower  25%";
                    break;
                case "6":
                    strLab1 = "Upper 30%";
                    strLab2 = "Lower  30%";
                    break;
                case "7":
                    strLab1 = "Lower 50%";
                    strLab2 = "Upper  50%";
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
            re.eval("attach(res)");
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
            re.eval("p.cox<-summary(coxph(Surv(gen[,1],gen[,2])~gen[,3]))");
            re.eval("p.val<-p.cox$coefficients[5]");
            re.eval("p.hr<-p.cox$coefficients[2]");
            re.eval("p.l<-p.cox$conf.int[3]");
            re.eval("p.t<-p.cox$conf.int[4]");
            re.eval("cindex<-p.cox$concordance");
            String strEvent = "";
            switch (strSurvival) {
                case "0":
                    strEvent = "OS_Event";
                    break;
                case "1":
                    strEvent = "DFI_Event";
                    break;
                case "2":
                    strEvent = "PFI_Event";
                    break;
                case "3":
                    strEvent = "DSS_Event";
                    break;
            }
            re.eval("pred<-prediction(rr$genValue,rr$" + strEvent + ")");

            re.eval("auc<-performance(pred,\"auc\")@y.values");
            REXP xauc = re.eval("genauc<-round(auc[[1]],3)");
            REXP xindex = re.eval("genindex<-round(cindex[1],3)");
            strAUC = xauc.asString();
            strIndex = xindex.asString();

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
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12, y = 0.15,label = paste(\"p=\",round(p.val,3)), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12+2, y = 0.09,label = paste(\"HR=\",round(p.hr,3)), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.26, y = 0.03,label = paste('( 95%CI,',round(p.l,3),'-',round(p.t,3),')'), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85, y = 0.05,label = \"" + strCancer + "\", size = 5,fontface = 'italic')");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.5, y = 0.95,label = \"" + strAvg + "\", size = 5)");
            re.eval("ggsurv");
            re.eval("ggsave(file = \"" + strPath + ".jpg\", print(ggsurv))");
            re.eval("ggsave(file = \"" + strPath + ".pdf\", print(ggsurv))");
            re.eval("ggsave(file = \"" + strPath + ".tiff\", print(ggsurv))");
        } catch (REngineException e) {
            e.printStackTrace();
        } finally {
            re.close();
        }
        return strTip;
    }

    public Cox GetSVariable(String strSql) throws RserveException, REXPMismatchException {
        Cox cox = new Cox();
        ComFun fun = new ComFun();
        String strTip = "";

        RConnection re = new RConnection("127.0.0.1");
        try {
            re.eval("library(survival)");
            re.eval("library(RODBC)");
            re.eval("db<-odbcDriverConnect('driver={SQL Server};server=(local);database=UCSDB;user=sa;pwd=gxqktzwq')");
            re.eval("gen<-sqlQuery(db,\"" + strSql + "\")");
            REXP x = re.eval("r<-nrow(gen)");
            int i = x.asInteger();
            if (i < 4 && i > 0) {
                cox = null;
                return cox;
            } else if (i == 0) {
                cox = null;
                return cox;
            }
            re.eval("attach(gen)");
            re.eval("p.cox<-summary(coxph(Surv(gen[,1],gen[,2])~gen[,3]))");
            REXP xsp = re.eval("p.val<-p.cox$coefficients[5]");
            cox.pvalue = xsp.asDouble();
            REXP xshr = re.eval("p.hr<-p.cox$coefficients[2]");
            cox.hr = xshr.asDouble();
            REXP xsl = re.eval("p.l<-p.cox$conf.int[3]");
            cox.low = xsl.asDouble();
            REXP xsu = re.eval("p.t<-p.cox$conf.int[4]");
            cox.upper = xsu.asDouble();
            re.eval("odbcClose(db)");
        } catch (REngineException e) {
            e.printStackTrace();
        } finally {
            re.close();
        }
        return cox;
    }


    int GetVarIndex(String str) {
        int index = 0;
        switch (str) {
            case "tnm":
                index = 1;
                break;
            case "Therapy_outcome":
                index = 2;
                break;
            case "Molecular_subtype":
                index = 3;
                break;
            case "History_of_hormone_therapy":
                index = 4;
                break;
            case "Pregnancies":
                index = 5;
                break;
            case "Hypertension":
                index = 6;
                break;
            case "Histological_type":
                index = 7;
                break;
        }
        return index;
    }

    public Cox[] GetFactor(String strSql, String strSplit, String strGen, List list) throws RserveException, REXPMismatchException {
        Cox[] cox = new Cox[9];
        for (int k = 0; k < 9; k++) {
            cox[k] = new Cox();
            cox[k].factor = "0";
        }
        ComFun fun = new ComFun();
        String strTip = "";

        RConnection re = new RConnection("127.0.0.1");
        try {
            re.eval("library(survival)");
            re.eval("library(RODBC)");
            re.eval("db<-odbcDriverConnect('driver={SQL Server};server=(local);database=UCSDB;user=sa;pwd=gxqktzwq')");
            re.eval("res<-sqlQuery(db,\"" + strSql + "\")");
            REXP x = re.eval("r<-nrow(res)");
            int i = x.asInteger();
            if (i < 4 && i > 0) {
                cox = null;
                return cox;
            } else if (i == 0) {
                cox = null;
                return cox;
            }
            re.eval("attach(res)");
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
                re.eval("gen.up<-gen[1:r1,];gen.mdi<-gen[(r1+1):r2,];gen.low<-gen[(r2+1):r3,]");
            } else if (strSplit.equals("9")) {
                re.eval("r1<-round(r/4)");
                re.eval("r2<-r1*2");
                re.eval("r3<-r1*3");
                re.eval("r4<-r-r3");
                re.eval("gen.up<-gen[1:r1,];gen.mu<-gen[(r1+1):r2,];gen.ml<-gen[(r2+1):r3,];gen.low<-gen[(r3+1):r4,]");
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
            re.eval("p.cox<-summary(coxph(Surv(gen[,1],gen[,2])~gen[,3]))");
            REXP xsp = re.eval("p.val<-p.cox$coefficients[5]");
            cox[0].pvalue = xsp.asDouble();
            xsp = re.eval("p.hr<-p.cox$coefficients[2]");
            cox[0].hr = xsp.asDouble();
            xsp = re.eval("p.l<-p.cox$conf.int[3]");
            cox[0].low = xsp.asDouble();
            xsp = re.eval("p.t<-p.cox$conf.int[4]");
            cox[0].upper = xsp.asDouble();
            cox[0].factor = "1";
            String streval = "";
            for (int k = 0; k < list.size(); k++) {
                streval += "+gen[," + String.valueOf(4 + k) + "]";
            }
            int n = list.size() + 1;//��һ����������
            re.eval("p.cox<-summary(coxph(Surv(gen[,1],gen[,2])~gen[,3]" + streval + "))");
            //n�����أ�gene_pvalue coefficients[4n+1]�� gene_HR coefficients[n+1],gene_low conf.int[2n+1],gene_upper conf.int[3n+1]
            //��i������  pvalue coefficients[4n+1+i],HR coefficients[n+1+i],gene_low conf.int[2n+1+i],gene_upper conf.int[3n+1+i]
            REXP xp = re.eval("p.val<-p.cox$coefficients[" + String.valueOf(4 * n + 1) + "]");
            cox[1].pvalue = xp.asDouble();
            xp = re.eval("p.hr<-p.cox$coefficients[" + String.valueOf(n + 1) + "]");
            cox[1].hr = xp.asDouble();
            xp = re.eval("p.l<-p.cox$conf.int[" + String.valueOf(2 * n + 1) + "]");
            cox[1].low = xp.asDouble();
            xp = re.eval("p.t<-p.cox$conf.int[" + String.valueOf(3 * n + 1) + "]");
            cox[1].upper = xp.asDouble();
            cox[1].factor = "1";
            for (int k = 0; k < list.size(); k++) {
                int j = GetVarIndex(String.valueOf(list.get(k)));
                REXP xmp = re.eval("p.val2<-p.cox$coefficients[" + String.valueOf(4 * n + 2 + k) + "]");
                cox[1 + j].pvalue = xmp.asDouble();
                REXP xmhr = re.eval("p.hr2<-p.cox$coefficients[" + String.valueOf(n + 2 + k) + "]");
                cox[1 + j].hr = xmhr.asDouble();
                REXP xml = re.eval("p.l2<-p.cox$conf.int[" + String.valueOf(2 * n + 2 + k) + "]");
                cox[1 + j].low = xml.asDouble();
                REXP xmu = re.eval("p.t2<-p.cox$conf.int[" + String.valueOf(3 * n + 2 + k) + "]");
                cox[1 + j].upper = xmu.asDouble();
                cox[1 + j].factor = "1";
            }
            re.eval("odbcClose(db)");
        } catch (REngineException e) {
            e.printStackTrace();
        } finally {
            re.close();
        }
        return cox;
    }

    public Cox[] GetMul(String[] strSql, String strSplit, String[] strGen, String strSurvival, String strPath) throws RserveException, REXPMismatchException {
        ComFun fun = new ComFun();
        String strTip = "";
        Cox[] cox = new Cox[strSql.length];
        for (int k = 0; k < strSql.length; k++) {
            cox[k] = new Cox();
            cox[k].factor = "0";
        }
        RConnection re = new RConnection("127.0.0.1");
        try {
            re.eval("library(survival)");
            re.eval("library(RODBC)");
            re.eval("library(ggplot2)");
            re.eval("library(magrittr)");
            re.eval("library(ggpubr)");
            re.eval("library(survminer)");
            re.eval("db<-odbcDriverConnect('driver={SQL Server};server=(local);database=UCSDB;user=sa;pwd=gxqktzwq')");
            int i = 0;

            String strLab1 = "";
            String strLab2 = "";
            String strLab3 = "";
            String strLab4 = "";
            switch (strSplit) {
                case "0":
                    strLab1 = "Lower 25%";
                    strLab2 = "Upper  75%";
                    break;
                case "1":
                    strLab1 = "Upper 25%";
                    strLab2 = "Lower  75%";
                    break;
                case "2":
                    strLab1 = "Lower 30%";
                    strLab2 = "Upper  70%";
                    break;
                case "3":
                    strLab1 = "Upper 30%";
                    strLab2 = "Lower  70%";
                    break;
                case "4":
                    strLab1 = "Upper 50%";
                    strLab2 = "Lower  50%";
                    break;
                case "5":
                    strLab1 = "Upper 25%";
                    strLab2 = "Lower  25%";
                    break;
                case "6":
                    strLab1 = "Upper 30%";
                    strLab2 = "Lower  30%";
                    break;
                case "7":
                    strLab1 = "Lower 50%";
                    strLab2 = "Upper  50%";
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
            for (int n = 0; n < strSql.length; n++) {
                re.eval("res" + String.valueOf(n) + "<-sqlQuery(db,\"" + strSql[n] + "\")");
                REXP x = re.eval("r" + String.valueOf(n) + "<-nrow(res" + String.valueOf(n) + ")");
                i = x.asInteger();
                if (i < 4) {
                    continue;
                }
                cox[n].factor = "1";
                if (strSplit.equals("0") || strSplit.equals("2") || strSplit.equals("7")) {
                    re.eval("gen" + String.valueOf(n) + "<-res" + String.valueOf(n) + "[order(res" + String.valueOf(n) + "[,3]),]");
                } else {
                    re.eval("gen" + String.valueOf(n) + "<-res" + String.valueOf(n) + "[order(-res" + String.valueOf(n) + "[,3]),]");
                }
                //gen"+String.valueOf(n)+"            
                if (strSplit.equals("0") || strSplit.equals("1")) {
                    re.eval("rr<-round(r" + String.valueOf(n) + "/4)");
                    re.eval("if(gen" + String.valueOf(n) + "[rr,3]==gen" + String.valueOf(n) + "[rr+1,3]){suba<-gen" + String.valueOf(n) + "[rr,3]-gen" + String.valueOf(n) + "[rr-1,3];subb<-gen" + String.valueOf(n) + "[rr+1,3]-gen" + String.valueOf(n) + "[rr+2,3];if(abs(suba)>abs(subb)){gen" + String.valueOf(n) + ".up<-gen" + String.valueOf(n) + "[1:rr,];gen" + String.valueOf(n) + ".low<-gen" + String.valueOf(n) + "[(rr+1):r" + String.valueOf(n) + ",]}else{gen" + String.valueOf(n) + ".up<-gen" + String.valueOf(n) + "[1:(rr+1),];gen" + String.valueOf(n) + ".low<-gen" + String.valueOf(n) + "[(rr+2):r" + String.valueOf(n) + ",]}}else{gen" + String.valueOf(n) + ".up<-gen" + String.valueOf(n) + "[1:rr,];gen" + String.valueOf(n) + ".low<-gen" + String.valueOf(n) + "[(rr+1):r" + String.valueOf(n) + ",]}");
                } else if (strSplit.equals("2") || strSplit.equals("3")) {
                    re.eval("rr<-round(r" + String.valueOf(n) + "*0.3)");
                    re.eval("if(gen" + String.valueOf(n) + "[rr,3]==gen" + String.valueOf(n) + "[rr+1,3]){suba<-gen" + String.valueOf(n) + "[rr,3]-gen" + String.valueOf(n) + "[rr-1,3];subb<-gen" + String.valueOf(n) + "[rr+1,3]-gen" + String.valueOf(n) + "[rr+2,3];if(abs(suba)>abs(subb)){gen" + String.valueOf(n) + ".up<-gen" + String.valueOf(n) + "[1:rr,];gen" + String.valueOf(n) + ".low<-gen" + String.valueOf(n) + "[(rr+1):r" + String.valueOf(n) + ",]}else{gen" + String.valueOf(n) + ".up<-gen" + String.valueOf(n) + "[1:(rr+1),];gen" + String.valueOf(n) + ".low<-gen" + String.valueOf(n) + "[(rr+2):r" + String.valueOf(n) + ",]}}else{gen" + String.valueOf(n) + ".up<-gen" + String.valueOf(n) + "[1:rr,];gen" + String.valueOf(n) + ".low<-gen" + String.valueOf(n) + "[(rr+1):r" + String.valueOf(n) + ",]}");
                } else if (strSplit.equals("4") || strSplit.equals("7")) {
                    re.eval("rr<-round(r" + String.valueOf(n) + "/2)");
                    re.eval("if(gen" + String.valueOf(n) + "[rr,3]==gen" + String.valueOf(n) + "[rr+1,3]){suba<-gen" + String.valueOf(n) + "[rr,3]-gen" + String.valueOf(n) + "[rr-1,3];subb<-gen" + String.valueOf(n) + "[rr+1,3]-gen" + String.valueOf(n) + "[rr+2,3];if(abs(suba)>abs(subb)){gen" + String.valueOf(n) + ".up<-gen" + String.valueOf(n) + "[1:rr,];gen" + String.valueOf(n) + ".low<-gen" + String.valueOf(n) + "[(rr+1):r" + String.valueOf(n) + ",]}else{gen" + String.valueOf(n) + ".up<-gen" + String.valueOf(n) + "[1:(rr+1),];gen" + String.valueOf(n) + ".low<-gen" + String.valueOf(n) + "[(rr+2):r" + String.valueOf(n) + ",]}}else{gen" + String.valueOf(n) + ".up<-gen" + String.valueOf(n) + "[1:rr,];gen" + String.valueOf(n) + ".low<-gen" + String.valueOf(n) + "[(rr+1):r" + String.valueOf(n) + ",]}");
                } else if (strSplit.equals("5")) {
                    re.eval("rr<-round(r" + String.valueOf(n) + "/4)");
                    re.eval("rr2<-r" + String.valueOf(n) + "-rr");
                    re.eval("if(gen" + String.valueOf(n) + "[rr,3]==gen" + String.valueOf(n) + "[rr+1,3]){suba<-gen" + String.valueOf(n) + "[rr,3]-gen" + String.valueOf(n) + "[rr-1,3];subb<-gen" + String.valueOf(n) + "[rr+1,3]-gen" + String.valueOf(n) + "[rr+2,3];if(abs(suba)>abs(subb)){gen" + String.valueOf(n) + ".up<-gen" + String.valueOf(n) + "[1:rr,];}else{gen" + String.valueOf(n) + ".up<-gen" + String.valueOf(n) + "[1:(rr+1),];}}else{gen" + String.valueOf(n) + ".up<-gen" + String.valueOf(n) + "[1:rr,];}");
                    re.eval("if(gen" + String.valueOf(n) + "[rr2+1,3]==gen" + String.valueOf(n) + "[rr2,3]){suba<-gen" + String.valueOf(n) + "[rr2+1,3]-gen" + String.valueOf(n) + "[rr2+2,3];subb<-gen" + String.valueOf(n) + "[rr2,3]-gen" + String.valueOf(n) + "[rr2-1,3];if(abs(suba)>abs(subb)){gen" + String.valueOf(n) + ".low<-gen" + String.valueOf(n) + "[(rr2+2):r" + String.valueOf(n) + ",];}else{gen" + String.valueOf(n) + ".low<-gen" + String.valueOf(n) + "[(rr2+1):r" + String.valueOf(n) + ",];}}else{gen" + String.valueOf(n) + ".low<-gen" + String.valueOf(n) + "[(rr2+1):r" + String.valueOf(n) + ",];}");
                } else if (strSplit.equals("6")) {
                    re.eval("rr<-round(r" + String.valueOf(n) + "*0.3)");
                    re.eval("rr2<-r" + String.valueOf(n) + "-rr");
                    re.eval("if(gen" + String.valueOf(n) + "[rr,3]==gen" + String.valueOf(n) + "[rr+1,3]){suba<-gen" + String.valueOf(n) + "[rr,3]-gen" + String.valueOf(n) + "[rr-1,3];subb<-gen" + String.valueOf(n) + "[rr+1,3]-gen" + String.valueOf(n) + "[rr+2,3];if(abs(suba)>abs(subb)){gen" + String.valueOf(n) + ".up<-gen" + String.valueOf(n) + "[1:rr,];}else{gen" + String.valueOf(n) + ".up<-gen" + String.valueOf(n) + "[1:(rr+1),];}}else{gen" + String.valueOf(n) + ".up<-gen" + String.valueOf(n) + "[1:rr,];}");
                    re.eval("if(gen" + String.valueOf(n) + "[rr2+1,3]==gen" + String.valueOf(n) + "[rr2,3]){suba<-gen" + String.valueOf(n) + "[rr2+1,3]-gen" + String.valueOf(n) + "[rr2+2,3];subb<-gen" + String.valueOf(n) + "[rr2,3]-gen" + String.valueOf(n) + "[rr2-1,3];if(abs(suba)>abs(subb)){gen" + String.valueOf(n) + ".low<-gen" + String.valueOf(n) + "[(rr2+2):r" + String.valueOf(n) + ",];}else{gen" + String.valueOf(n) + ".low<-gen" + String.valueOf(n) + "[(rr2+1):r" + String.valueOf(n) + ",];}}else{gen" + String.valueOf(n) + ".low<-gen" + String.valueOf(n) + "[(rr2+1):r" + String.valueOf(n) + ",];}");
                } else if (strSplit.equals("8")) {
                    re.eval("rr<-round(r" + String.valueOf(n) + "/3)");
                    re.eval("rr2<-rr*2");
                    re.eval("rr3<-r" + String.valueOf(n) + "-rr2");
                    re.eval("gen" + String.valueOf(n) + ".up<-gen" + String.valueOf(n) + "[1:rr,];gen" + String.valueOf(n) + ".mdi<-gen" + String.valueOf(n) + "[(rr+1):rr2,];gen" + String.valueOf(n) + ".low<-gen" + String.valueOf(n) + "[(rr2+1):rr3,]");
                } else if (strSplit.equals("9")) {
                    re.eval("rr<-round(r" + String.valueOf(n) + "/4)");
                    re.eval("rr2<-rr*2");
                    re.eval("rr3<-rr*3");
                    re.eval("rr4<-r" + String.valueOf(n) + "-rr3");
                    re.eval("gen" + String.valueOf(n) + ".up<-gen" + String.valueOf(n) + "[1:rr,];gen" + String.valueOf(n) + ".mu<-gen" + String.valueOf(n) + "[(rr+1):rr2,];gen" + String.valueOf(n) + ".ml<-gen" + String.valueOf(n) + "[(rr2+1):rr3,];gen" + String.valueOf(n) + ".low<-gen" + String.valueOf(n) + "[(rr3+1):rr4,]");
                }
                if (strSplit.equals("8")) {
                    re.eval("gen" + String.valueOf(n) + ".up[,3]=0");
                    re.eval("gen" + String.valueOf(n) + ".mdi[,3]=1");
                    re.eval("gen" + String.valueOf(n) + ".low[,3]=2");
                    re.eval("gen" + String.valueOf(n) + "<-rbind(gen" + String.valueOf(n) + ".up,gen" + String.valueOf(n) + ".mdi)");
                    re.eval("gen" + String.valueOf(n) + "<-rbind(gen" + String.valueOf(n) + ",gen" + String.valueOf(n) + ".low)");
                } else if (strSplit.equals("9")) {
                    re.eval("gen" + String.valueOf(n) + ".up[,3]=0");
                    re.eval("gen" + String.valueOf(n) + ".mu[,3]=1");
                    re.eval("gen" + String.valueOf(n) + ".ml[,3]=2");
                    re.eval("gen" + String.valueOf(n) + ".low[,3]=3");
                    re.eval("gen" + String.valueOf(n) + "<-rbind(gen" + String.valueOf(n) + ".up,gen" + String.valueOf(n) + ".mu)");
                    re.eval("gen" + String.valueOf(n) + "<-rbind(gen" + String.valueOf(n) + ",gen" + String.valueOf(n) + ".ml)");
                    re.eval("gen" + String.valueOf(n) + "<-rbind(gen" + String.valueOf(n) + ",gen" + String.valueOf(n) + ".low)");
                } else {
                    re.eval("gen" + String.valueOf(n) + ".up[,3]=1");
                    re.eval("gen" + String.valueOf(n) + ".low[,3]=0");
                    re.eval("gen" + String.valueOf(n) + "<-rbind(gen" + String.valueOf(n) + ".up,gen" + String.valueOf(n) + ".low)");
                }
                re.eval("p.cox<-summary(coxph(Surv(gen" + String.valueOf(n) + "[,1],gen" + String.valueOf(n) + "[,2])~gen" + String.valueOf(n) + "[,3]))");
                REXP reindex = re.eval("p.ind<-p.cox$coefficients[4]");
                cox[n].beta = reindex.asDouble();
                reindex = re.eval("p.val<-p.cox$coefficients[5]");
                cox[n].pvalue = reindex.asDouble();
                reindex = re.eval("p.hr<-p.cox$coefficients[2]");
                cox[n].hr = reindex.asDouble();
                reindex = re.eval("p.l<-p.cox$conf.int[3]");
                cox[n].low = reindex.asDouble();
                reindex = re.eval("p.t<-p.cox$conf.int[4]");
                cox[n].upper = reindex.asDouble();
                re.eval("res" + String.valueOf(n) + "<-cbind(res" + String.valueOf(n) + "[,1:2],res" + String.valueOf(n) + "[,3]*p.ind,res" + String.valueOf(n) + "[,4])");
            }

            int start = 0;

            for (int n = 0; n < strSql.length; n++) {
                if (cox[n].factor.equals("1")) {
                    start = n;
                    //re.eval("names(gen"+String.valueOf(start)+")[3] <-\""+strGen[n]+"\"");
                    break;
                }
            }

            for (int n = 0; n < strSql.length; n++) {
                cox[n].datasource = strGen[n];
                if (cox[n].factor.equals("1")) {
                    re.eval("gen" + String.valueOf(n) + "<-res" + String.valueOf(n) + "[order(res" + String.valueOf(n) + "[,4]),]");
                }
            }

            int t = 0;
            //for(int n=start+1;n<strSql.length;n++){
            //if(cox[n].factor.equals("1")){
            //re.eval("gen"+String.valueOf(start)+"<-cbind(gen"+String.valueOf(start)+",gen"+String.valueOf(n)+"[,3])");
            //t++;
            //re.eval("names(gen"+String.valueOf(start)+")[4+"+String.valueOf(t)+"] <-\""+strGen[n]+"\"");
            //}
            //}
            String ss = "";
            ss = "res<-cbind(gen" + String.valueOf(start) + "[,1:2],(gen" + String.valueOf(start) + "[,3]";
            for (int n = start + 1; n < strSql.length; n++) {
                if (cox[n].factor.equals("1")) {
                    ss += "+gen" + String.valueOf(n) + "[,3]";
                }
            }
            ss += "))";
            re.eval(ss);
            re.eval("gmax<-res[order(-res[,1]),]");
            re.eval("r<-nrow(res)");
            if (strSplit.equals("0") || strSplit.equals("2") || strSplit.equals("7")) {
                re.eval("gen<-res[order(res[,3]),]");
            } else {
                re.eval("gen<-res[order(-res[,3]),]");
            }
            if (strSplit.equals("0") || strSplit.equals("1")) {
                re.eval("rr<-round(r/4)");
                re.eval("if(gen[rr,3]==gen[rr+1,3]){suba<-gen[rr,3]-gen[rr-1,3];subb<-gen[rr+1,3]-gen[rr+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:rr,];gen.low<-gen[(rr+1):r,]}else{gen.up<-gen[1:(rr+1),];gen.low<-gen[(rr+2):r,]}}else{gen.up<-gen[1:rr,];gen.low<-gen[(rr+1):r,]}");
            } else if (strSplit.equals("2") || strSplit.equals("3")) {
                re.eval("rr<-round(r*0.3)");
                re.eval("if(gen[rr,3]==gen[rr+1,3]){suba<-gen[rr,3]-gen[rr-1,3];subb<-gen[rr+1,3]-gen[rr+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:rr,];gen.low<-gen[(rr+1):r,]}else{gen.up<-gen[1:(rr+1),];gen.low<-gen[(rr+2):r,]}}else{gen.up<-gen[1:rr,];gen.low<-gen[(rr+1):r,]}");
            } else if (strSplit.equals("4") || strSplit.equals("7")) {
                re.eval("rr<-round(r/2)");
                re.eval("if(gen[rr,3]==gen[rr+1,3]){suba<-gen[rr,3]-gen[rr-1,3];subb<-gen[rr+1,3]-gen[rr+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:rr,];gen.low<-gen[(rr+1):r,]}else{gen.up<-gen[1:(rr+1),];gen.low<-gen[(rr+2):r,]}}else{gen.up<-gen[1:rr,];gen.low<-gen[(rr+1):r,]}");
            } else if (strSplit.equals("5")) {
                re.eval("rr<-round(r/4)");
                re.eval("rr2<-r-rr");
                re.eval("if(gen[rr,3]==gen[rr+1,3]){suba<-gen[rr,3]-gen[rr-1,3];subb<-gen[rr+1,3]-gen[rr+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:rr,];}else{gen.up<-gen[1:(rr+1),];}}else{gen.up<-gen[1:rr,];}");
                re.eval("if(gen[rr2+1,3]==gen[rr2,3]){suba<-gen[rr2+1,3]-gen[rr2+2,3];subb<-gen[rr2,3]-gen[rr2-1,3];if(abs(suba)>abs(subb)){gen.low<-gen[(rr2+2):r,];}else{gen.low<-gen[(rr2+1):r,];}}else{gen.low<-gen[(rr2+1):r,];}");
            } else if (strSplit.equals("6")) {
                re.eval("rr<-round(r*0.3)");
                re.eval("rr2<-r-rr");
                re.eval("if(gen[rr,3]==gen[rr+1,3]){suba<-gen[rr,3]-gen[rr-1,3];subb<-gen[rr+1,3]-gen[rr+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:rr,];}else{gen.up<-gen[1:(rr+1),];}}else{gen.up<-gen[1:rr,];}");
                re.eval("if(gen[rr2+1,3]==gen[rr2,3]){suba<-gen[rr2+1,3]-gen[rr2+2,3];subb<-gen[rr2,3]-gen[rr2-1,3];if(abs(suba)>abs(subb)){gen.low<-gen[(rr2+2):r,];}else{gen.low<-gen[(rr2+1):r,];}}else{gen.low<-gen[(rr2+1):r,];}");
            } else if (strSplit.equals("8")) {
                re.eval("rr<-round(r/3)");
                re.eval("rr2<-rr*2");
                re.eval("rr3<-r-rr2");
                re.eval("gen.up<-gen[1:rr,];gen.mdi<-gen[(rr+1):rr2,];gen.low<-gen[(rr2+1):rr3,]");
            } else if (strSplit.equals("9")) {
                re.eval("rr<-round(r/4)");
                re.eval("rr2<-rr*2");
                re.eval("rr3<-rr*3");
                re.eval("rr4<-r-rr3");
                re.eval("gen.up<-gen[1:rr,];gen.mu<-gen[(rr+1):rr2,];gen.ml<-gen[(rr2+1):rr3,];gen.low<-gen[(rr3+1):rr4,]");
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
            //ss="";
            //if(t>0){
            //re.eval("gen<-cbind(gen"+String.valueOf(start)+"[,1:3],gen"+String.valueOf(start)+"[,5:(4+"+String.valueOf(t)+")])");
            //}
            //else{
            //re.eval("gen<-gen"+String.valueOf(start)+"[,1:3]");
            //}
            //re.eval("gen<-gen"+String.valueOf(start)+"[,1:3]");

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
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12, y = 0.15,label = paste(\"p=\",round(p.val,3)), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12+2, y = 0.09,label = paste(\"HR=\",round(p.hr,3)), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.26, y = 0.03,label = paste('( 95%CI,',round(p.l,3),'-',round(p.t,3),')'), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.85, y = 0.05,label = \"OSucs\", size = 5,fontface = 'italic')");
            re.eval("ggsurv");
            re.eval("ggsave(file = \"" + strPath + ".jpg\", print(ggsurv))");
            re.eval("ggsave(file = \"" + strPath + ".pdf\", print(ggsurv))");
            re.eval("ggsave(file = \"" + strPath + ".tiff\", print(ggsurv))");
            re.eval("odbcClose(db)");
            //if(t>0){
            //ss += "p.cox<-summary(coxph(Surv(gen[,1],gen[,2])~gen[,3]";
            //for(int n=0;n<t;n++){
            //ss += "+gen[,4+"+String.valueOf(n)+"]";
            //}
            //ss+="))";
            //re.eval(ss);
            //}
            //else{
            //re.eval("p.cox<-summary(coxph(Surv(gen[,1],gen[,2])~gen[,3]))");
            //}  
            //if(t>0){
            //int j=1;
            //for(int n=start;n<strSql.length;n++){
            //if(cox[n].factor.equals("1")){
            //REXP xmp = re.eval("p.val2<-p.cox$coefficients["+String.valueOf(4*(t+1)+j)+"]");
            //cox[n].pvalue=xmp.asDouble();
            //REXP xmhr = re.eval("p.hr2<-p.cox$coefficients["+String.valueOf((t+1)+j)+"]");
            //cox[n].hr=xmhr.asDouble();
            //REXP xml = re.eval("p.l2<-p.cox$conf.int["+String.valueOf(2*(t+1)+j)+"]");
            //cox[n].low=xml.asDouble();
            //REXP xmu = re.eval("p.t2<-p.cox$conf.int["+String.valueOf(3*(t+1)+j)+"]");
            //cox[n].upper=xmu.asDouble();
            //cox[n].factor="1";
            //cox[n].datasource=strGen[n];
            //j++;
            //}
            //}
            //}
            //��������            
            //int n = strSql.length+1;//��һ����������             
            //n�����أ�gene_pvalue coefficients[4n+1]�� gene_HR coefficients[n+1],gene_low conf.int[2n+1],gene_upper conf.int[3n+1]
            //��i������  pvalue coefficients[4n+1+i],HR coefficients[n+1+i],gene_low conf.int[2n+1+i],gene_upper conf.int[3n+1+i]

        } catch (REngineException e) {
            e.printStackTrace();
        } finally {
            re.close();
        }
        return cox;
    }

    public void GetHeatmap(String[] strGen, String strPath) throws RserveException, REXPMismatchException {
        RConnection re = new RConnection("127.0.0.1");
        try {
            re.eval("library(survival)");
            re.eval("library(RODBC)");
            re.eval("library(pheatmap)");
            re.eval("library(ggplot2)");
            re.eval("db<-odbcDriverConnect('driver={SQL Server};server=(local);database=UCSDB;user=sa;pwd=gxqktzwq')");
            for (int i = 0; i < strGen.length; i++) {
                re.eval("gen" + String.valueOf(i) + "<-sqlQuery(db,\"select genValue,genGSm from gen_TCGA where genName='" + strGen[i] + "' order by genGSM \")");
            }
            for (int i = 1; i < strGen.length; i++) {
                re.eval("gen0<-cbind(gen0,gen" + String.valueOf(i) + "[,1])");
            }
            re.eval("gen<-cbind(gen0[,1],gen0[,3:(3+" + (strGen.length - 2) + ")])");
            for (int i = 0; i < strGen.length; i++) {
                re.eval("names(gen)[" + String.valueOf(i + 1) + "] <-\"" + strGen[i] + "\"");
            }
            re.eval("m<-cor(gen,method='spearman')");
            re.eval("jpeg('" + strPath + ".jpg',width=1000,height=1000)");
            //re.eval("p<-pheatmap(m,cluster_rows=F,cluster_cols = F,display_numbers = F,fontsize = 12) ");            
            re.eval("pheatmap(m,cluster_rows=F,cluster_cols = F,display_numbers = F,fontsize = 12) ");
            //re.eval("ggsave(file = \""+strPath+".jpg\", print(p))");
            //re.eval("ggsave(file = \""+strPath+".pdf\", print(p))");
            //re.eval("ggsave(file = \""+strPath+".tiff\", print(p))");
            //re.eval("save_pheatmap_pdf(p, "+strPath+".pdf)");
            re.eval("dev.off()");
            re.eval("odbcClose(db)");
        } catch (REngineException e) {
            e.printStackTrace();
        } finally {
            re.close();
        }
        return;
    }
}
