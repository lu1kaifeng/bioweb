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

public class DLBCLCom {

    public boolean isCopyNum(String strGene) {
        boolean result = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ex) {

        }
        Connection conn;
        Statement stmt;
        ResultSet rs;
        String strSql = "select COUNT(*) as num from sample_TCGA where GSM in (select genGSM from gen_TCGACopyNo where genName='" + strGene + "')";
        Context initCtx;
        Context ctx;
        Object obj;
        DataSource ds = null;
        try {
            initCtx = new InitialContext();
            ctx = (Context) initCtx.lookup("java:comp/env");
            obj = (Object) ctx.lookup("jdbc/SSDLBCL");
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

    public boolean isMW(String strGene) {
        boolean result = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ex) {

        }
        Connection conn;
        Statement stmt;
        ResultSet rs;
        //select count(*) as num from sample_TCGA where GSM in (select GSM from sample_TCGAChange where genName='"+strGene+"') and OS_Event='1'
        String strSql = "select count(*) as num from sample_TCGAChange where genName='" + strGene + "'";
        //String strSql="select count(*) as num from sample_TCGA where GSM in (select GSM from sample_TCGAChange where genName='"+strGene+"') and OS_Event='1'";
        Context initCtx;
        Context ctx;
        Object obj;
        DataSource ds = null;
        try {
            initCtx = new InitialContext();
            ctx = (Context) initCtx.lookup("java:comp/env");
            obj = (Object) ctx.lookup("jdbc/SSDLBCL");
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

    public boolean isBox(String strGene) {
        boolean result = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ex) {

        }
        Connection conn;
        Statement stmt;
        ResultSet rs;
        String strSql = "select count(*) as num from sample_TCGAChange where genName='" + strGene + "'";
        Context initCtx;
        Context ctx;
        Object obj;
        DataSource ds = null;
        try {
            initCtx = new InitialContext();
            ctx = (Context) initCtx.lookup("java:comp/env");
            obj = (Object) ctx.lookup("jdbc/SSDLBCL");
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
                int num = Integer.valueOf(strnum);
                if (num < 1) {
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

    public boolean isExistByGene(String strGene, String strGSE) {
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
            obj = (Object) ctx.lookup("jdbc/SSDLBCL");
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

    public void GetCopyNum(String strSql1, String strPath, String strGen, String strAvg, String strSurvival, String strCancer, String tip) throws RserveException, REXPMismatchException {
        String strTip = "";
        RConnection re = new RConnection("127.0.0.1");
        try {
            re.eval("library(survival)");
            re.eval("library(ggplot2)");
            re.eval("library(magrittr)");
            re.eval("library(ggpubr)");
            re.eval("library(survminer)");
            re.eval("library(RODBC)");
            re.eval("db<-odbcDriverConnect('driver={SQL Server};server=(local);database=DLBCLDB;user=sa;pwd=gxqktzwq')");
            re.eval("res1<-sqlQuery(db,\"" + strSql1 + " and genValue<0\")");
            re.eval("res2<-sqlQuery(db,\"" + strSql1 + " and genValue=0\")");
            re.eval("res3<-sqlQuery(db,\"" + strSql1 + " and genValue>0\")");
            //REXP x = re.eval("r<-nrow(res1)");
            //int i = x.asInteger();   
            re.eval("attach(res1)");
            re.eval("attach(res2)");
            re.eval("attach(res3)");
            re.eval("r1<-nrow(res1)");
            re.eval("r2<-nrow(res2)");
            re.eval("r3<-nrow(res3)");
            re.eval("if(r1>0){res1[,3]=0}");
            re.eval("if(r2>0){res2[,3]=1}");
            re.eval("if(r3>0){res3[,3]=2}");
            re.eval("gen<-rbind(res1,res2)");
            re.eval("gen<-rbind(gen,res3)");

            String strLab1 = "Deletion";
            String strLab2 = "Normal";
            String strLab3 = "Amplification";

            re.eval("gmax<-gen[order(-gen[,1]),]");

            re.eval("gen.surv<-survfit(Surv(gen[,1],gen[,2])~gen[,3])");
            re.eval("gen.dif<-survdiff(Surv(gen[,1],gen[,2])~gen[,3])");
            re.eval("p.cox<-summary(coxph(Surv(gen[,1],gen[,2])~gen[,3]))");
            re.eval("p.val<-p.cox$coefficients[5]");
            re.eval("p.hr<-p.cox$coefficients[2]");
            re.eval("p.l<-p.cox$conf.int[3]");
            re.eval("p.t<-p.cox$conf.int[4]");

            String ss = "if(r1>0 && r2>0 && r3>0){ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"red\",\"black\",\"green\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\"" + strLab1 + "\", \"" + strLab2 + "\",\"" + strLab3 + "\"),legend = 'none')}";
            ss += "else if(r1>0 && r2>0){ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"green\",\"red\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\"" + strLab2 + "\", \"" + strLab1 + "\"),legend = \"none\")}";
            ss += "else if(r1>0 && r3>0){ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"green\",\"red\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\"" + strLab3 + "\", \"" + strLab1 + "\"),legend = \"none\")}";
            ss += "else if(r2>0 && r3>0){ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"green\",\"red\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\"" + strLab3 + "\", \"" + strLab2 + "\"),legend = \"none\")}";
            re.eval(ss);
            //re.eval("if(r1>0 && r2>0 && r3>0){ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"red\",\"black\",\"green\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\""+strLab1+"\", \""+strLab2+"\",\""+strLab3+"\"),legend = 'none')}");
            //re.eval("else if(r1>0 && r2>0){ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"green\",\"red\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\""+strLab2+"\", \""+strLab1+"\"),legend = \"none\")}");
            //re.eval("else if(r1>0 && r3>0){ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"green\",\"red\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\""+strLab3+"\", \""+strLab1+"\"),legend = \"none\")}");
            //re.eval("else if(r2>0 && r3>0){ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"green\",\"red\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\""+strLab3+"\", \""+strLab2+"\"),legend = \"none\")}");
            re.eval("ggsurv$table <- ggsurv$table + theme(axis.text.y = element_text(colour='black'))");
            re.eval("if(r1>0){ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.80-2, y = 0.97,label = \"" + strLab1 + "\", size = 4,color='red')}");
            re.eval("if(r2>0){ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.80, y = 0.92,label = \"" + strLab2 + "\", size = 4,color='black')}");
            re.eval("if(r3>0){ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.80-2, y = 0.87,label = \"" + strLab3 + "\", size = 4,color='green')}");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12, y = 0.15,label = paste(\"p=\",round(p.val,4)), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12+2, y = 0.09,label = paste(\"HR=\",round(p.hr,4)), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.26, y = 0.03,label = paste('( 95%CI,',round(p.l,4),'-',round(p.t,4),')'), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.88, y = 0.05,label = \"" + strCancer + "\", size = 5,fontface = 'italic')");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.5, y = 0.95,label = \"" + strAvg + "\", size = 5)");
            re.eval("ggsurv");
            re.eval("ggsave(file = \"" + strPath + "\", print(ggsurv))");
            re.eval("odbcClose(db)");
        } catch (REngineException e) {
            e.printStackTrace();
        } finally {
            re.close();
            //re.shutdown();
        }
        //return strTip;
    }

    public String GetWMPath(String strSql1, String strSql2, String strPath, String strGen, String strAvg, String strSurvival, String strCancer, String tip) throws RserveException, REXPMismatchException {
        String strTip = "";
        RConnection re = new RConnection("127.0.0.1");
        try {
            re.eval("library(survival)");
            re.eval("library(ggplot2)");
            re.eval("library(magrittr)");
            re.eval("library(ggpubr)");
            re.eval("library(survminer)");
            re.eval("library(RODBC)");
            re.eval("db<-odbcDriverConnect('driver={SQL Server};server=(local);database=DLBCLDB;user=sa;pwd=gxqktzwq')");
            re.eval("res1<-sqlQuery(db,\"" + strSql1 + "\")");
            re.eval("res2<-sqlQuery(db,\"" + strSql2 + "\")");
            //REXP x = re.eval("r<-nrow(res1)");
            //int i = x.asInteger();   
            re.eval("attach(res1)");
            re.eval("attach(res2)");
            re.eval("res2[,3]=0");
            re.eval("res1[,3]=1");
            re.eval("gen<-rbind(res1,res2)");

            String strLab1 = "Wt";
            String strLab2 = "Mut";

            re.eval("gmax<-gen[order(-gen[,1]),]");

            re.eval("gen.surv<-survfit(Surv(gen[,1],gen[,2])~gen[,3])");
            re.eval("gen.dif<-survdiff(Surv(gen[,1],gen[,2])~gen[,3])");
            re.eval("p.cox<-summary(coxph(Surv(gen[,1],gen[,2])~gen[,3]))");
            re.eval("p.val<-p.cox$coefficients[5]");
            re.eval("p.hr<-p.cox$coefficients[2]");
            re.eval("p.l<-p.cox$conf.int[3]");
            re.eval("p.t<-p.cox$conf.int[4]");
            re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"green\",\"red\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\"" + strLab2 + "\", \"" + strLab1 + "\"),legend = \"none\")");//legend = c(0.85, 0.9)
            re.eval("ggsurv$table <- ggsurv$table + theme(axis.text.y = element_text(colour='black'))");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.88, y = 0.97,label = \"" + strLab1 + "\", size = 4,color='red')");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.88, y = 0.92,label = \"" + strLab2 + "\", size = 4,color='green')");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12, y = 0.15,label = paste(\"p=\",round(p.val,4)), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12+2, y = 0.09,label = paste(\"HR=\",round(p.hr,4)), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.26, y = 0.03,label = paste('( 95%CI,',round(p.l,4),'-',round(p.t,4),')'), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.88, y = 0.05,label = \"" + strCancer + "\", size = 5,fontface = 'italic')");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.5, y = 0.95,label = \"" + strAvg + "\", size = 5)");
            re.eval("ggsurv");
            re.eval("ggsave(file = \"" + strPath + "\", print(ggsurv))");
            re.eval("odbcClose(db)");
        } catch (REngineException e) {
            e.printStackTrace();
        } finally {
            re.close();
            //re.shutdown();
        }
        return strTip;
    }

    public String GetBoxCopy(String strSql1, String strSql2, String strSql3, String strPath, String strGen) throws RserveException, REXPMismatchException {
        //0 Normal,1 Deletion,2 Amplification
        String strTip = "";
        RConnection re = new RConnection("127.0.0.1");
        try {
            re.eval("library(ggplot2)");
            re.eval("library(RODBC)");
            re.eval("db<-odbcDriverConnect('driver={SQL Server};server=(local);database=DLBCLDB;user=sa;pwd=gxqktzwq')");
            re.eval("res1<-sqlQuery(db,\"" + strSql1 + "\")");
            re.eval("res2<-sqlQuery(db,\"" + strSql2 + "\")");
            re.eval("res3<-sqlQuery(db,\"" + strSql3 + "\")");
            re.eval("r1<-nrow(res1)");
            re.eval("r2<-nrow(res2)");
            re.eval("r3<-nrow(res3)");
            re.eval("ra<-c(r1,r2,r3)");
            re.eval("list1<-list()");
            re.eval("list2<-list()");
            re.eval("list3<-list()");
            re.eval("list4<-list()");
            re.eval("list5<-list()");
            re.eval("list6<-list()");
            re.eval("list7<-list()");
            re.eval("list8<-list()");
            re.eval("list9<-list()");
            re.eval("list<-list()");
            re.eval("list1<-res1");
            re.eval("list2<-res2");
            re.eval("list3<-res3");
            re.eval("list5<-res1");
            re.eval("list6<-res2");
            re.eval("list7<-res3");
            re.eval("list<-ra");
            re.eval("rmax<-max(list)");
            re.eval("if(rmax>nrow(res1)){for(i in 1:(rmax-nrow(res1))){list1[i+nrow(res1),1]<-NA}}");
            re.eval("if(rmax>nrow(res2)){for(i in 1:(rmax-nrow(res2))){list2[i+nrow(res2),1]<-NA}}");
            re.eval("if(rmax>nrow(res3)){for(i in 1:(rmax-nrow(res3))){list3[i+nrow(res3),1]<-NA}}");

            re.eval("if(r1>0 && r2>0 && r3>0){rbox<-cbind(list2,list1);rbox<-cbind(rbox,list3);}");
            re.eval("if(r1>0 && r2>0 && r3<=0){rbox<-cbind(list2,list1);}");
            re.eval("if(r1>0 && r2<=0 && r3>0){rbox<-cbind(list1,list3);}");
            re.eval("if(r1<=0 && r2>0 && r3>0){rbox<-cbind(list2,list3);}");

            re.eval("if(r1>0){list5[,2]=0}");
            re.eval("if(r2>0){list6[,2]=1}");
            re.eval("if(r3>0){list7[,2]=2}");
            //���鶼����
            //1nor 2del 3amp
            String strR = "if(r1>0 && r2>0 && r3>0){";
            strR += "list4<-rbind(list6,list5);";
            strR += "list8<-rbind(list5,list7);";
            strR += "names(list4)<-c('expression','type');";
            strR += "p<-wilcox.test(expression~type,data=list4);";
            strR += "pv<-p$p.value;";
            strR += "names(list8)<-c('expression','type');";
            strR += "p1<-wilcox.test(expression~type,data=list8);";
            strR += "pv1<-p1$p.value;";
            strR += "a1<-max(list4[,1]);";
            strR += "a2<-max(list8[,1]);";
            strR += "a<-max(c(a1,a2));";
            strR += "jpeg('" + strPath + "',width=1000,height=1000);";
            strR += "boxplot(rbox,cex.axis=0.8,xlim=c(0,6),at=c(1,3,5),ylab='Expression ( log2 ( norm_count+1 ) )',cex.lab=1.4,names=c(paste(' ','\r\n','Deletion','\r\n','( n=',as.character(r2),')'),paste(' ','\r\n','Normal','\r\n','( n=',as.character(r1),')'),paste(' ','\r\n','Amplification','\r\n','( n=',as.character(r3),')')));";
            strR += "text(2,a-0.1,paste('Nor/Del','\r\n', 'p =',round(pv,4)));";
            strR += "text(4,a-0.1,paste('Nor/Amp','\r\n', 'p =',round(pv1,4)));}";
            re.eval(strR);
            //nor&&del
            strR = "if(r1>0 && r2>0 && r3<=0){";
            strR += "list4<-rbind(list6,list5);";
            strR += "names(list4)<-c('expression','type');";
            strR += "p<-wilcox.test(expression~type,data=list4);";
            strR += "pv<-p$p.value;";
            strR += "a<-max(list4[,1]);";
            strR += "jpeg('" + strPath + "',width=1000,height=1000);";
            strR += "boxplot(rbox,cex.axis=1.2,xlim=c(0,5),at=c(1,3),ylab='Expression ( log2 ( norm_count+1 ) )',cex.lab=1.4,names=c(paste(' ','\r\n','Deletion','\r\n','( n=',as.character(r2),')'),paste(' ','\r\n','Normal','\r\n','( n=',as.character(r1),')')));";
            strR += "text(2,a-0.1,paste('Del/Nor','\r\n', 'p =',round(pv,4)));";
            strR += "}";
            re.eval(strR);
            //del && amp
            strR = "if(r2>0 && r3>0 && r1<=0){";
            strR += "list4<-rbind(list6,list7);";
            strR += "names(list4)<-c('expression','type');";
            strR += "p<-wilcox.test(expression~type,data=list4);";
            strR += "pv<-p$p.value;";
            strR += "a<-max(list4[,1]);";
            strR += "jpeg('" + strPath + "',width=1000,height=1000);";
            strR += "boxplot(rbox,cex.axis=1.2,xlim=c(0,5),at=c(1,3),ylab='Expression ( log2 ( norm_count+1 ) )',cex.lab=1.4,names=c(paste(' ','\r\n','Deletion','\r\n','( n=',as.character(r2),')'),paste(' ','\r\n','Amplification','\r\n','( n=',as.character(r3),')')));";
            strR += "text(2,a-0.1,paste('Del/Amp','\r\n', 'p =',round(pv,4)));";
            strR += "}";
            re.eval(strR);
            //nor && amp
            strR = "if(r1>0 && r3>0 && r2<=0){";
            strR += "list4<-rbind(list5,list7);";
            strR += "names(list4)<-c('expression','type');";
            strR += "p<-wilcox.test(expression~type,data=list4);";
            strR += "pv<-p$p.value;";
            strR += "a<-max(list4[,1]);";
            strR += "jpeg('" + strPath + "',width=1000,height=1000);";
            strR += "boxplot(rbox,cex.axis=1.2,xlim=c(0,5),at=c(1,3),ylab='Expression ( log2 ( norm_count+1 ) )',cex.lab=1.4,names=c(paste(' ','\r\n','Normal','\r\n','( n=',as.character(r1),')'),paste(' ','\r\n','Amplification','\r\n','( n=',as.character(r3),')')));";
            strR += "text(2,a-0.1,paste('Nor/Amp','\r\n', 'p =',round(pv,4)));";
            strR += "}";
            re.eval(strR);
            //re.eval("list9<-rbind(list4,list7)");
            //0 Normal,1 Deletion,2 Amplification
            //re.eval("boxplot(rbox,cex.axis=1.2,xlim=c(0,4),at=NULL,ylab='Expression(log2(norm_count+1))',cex.lab=1.4,names=c(paste('Normal(n=',as.character(r1),')'),paste('Deletion'(n=',as.character(r2),')'),paste('Amplification(n=',as.character(r3),')')))");
            //re.eval("boxplot(rbox,cex.axis=1.2,xlim=c(0,5),at=c(1,4),ylab='Survival time (Months)',cex.lab=1.4,names=c(paste('Mut ( n=',as.character(nrow(res1)),')'),paste('Wt ( n=',as.character(nrow(res2)),')')))");
            //re.eval("a<-max(list4[,1])");

            re.eval("dev.off()");
            re.eval("odbcClose(db)");
        } catch (REngineException e) {
            e.printStackTrace();
        } finally {
            re.close();
        }
        return strTip;
    }


    public String GetBoxPath(String strSql1, String strSql2, String strPath) throws RserveException, REXPMismatchException {
        String strTip = "";
        RConnection re = new RConnection("127.0.0.1");
        try {
            re.eval("library(ggplot2)");
            re.eval("library(RODBC)");
            re.eval("db<-odbcDriverConnect('driver={SQL Server};server=(local);database=DLBCLDB;user=sa;pwd=gxqktzwq')");
            re.eval("res1<-sqlQuery(db,\"" + strSql1 + "\")");
            re.eval("res2<-sqlQuery(db,\"" + strSql2 + "\")");
            re.eval("list<-list()");
            re.eval("list<-res1");
            re.eval("r<-nrow(res1)");
            re.eval("for(i in 1:(nrow(res2)-r)){list[i+r,1]<-NA}");
            re.eval("rbox<-cbind(list,res2)");

            //�ȼ���
            re.eval("list2<-res1");
            re.eval("for(i in 1:(nrow(res1))){list2[i,2]<-'Mut'}");
            re.eval("for(i in 1:(nrow(res2))){list2[(i+nrow(res1)),1]<-res2[i,1]}");
            re.eval("for(i in 1:(nrow(res2))){list2[(i+nrow(res1)),2]<-'Wt'}");
            re.eval("names(list2)<-c('expression','type')");
            re.eval("p<-wilcox.test(expression~type,data=list2)");
            re.eval("pv<-p$p.value");


            re.eval("jpeg('" + strPath + "',width=1000,height=1000)");
            re.eval("boxplot(rbox,cex.axis=1.2,xlim=c(0,5),at=c(1,4),ylab='Survival time (Months)',cex.lab=1.4,names=c(paste('Mut ( n=',as.character(nrow(res1)),')'),paste('Wt ( n=',as.character(nrow(res2)),')')))");
            re.eval("a<-max(list2[,1])");
            re.eval("text(2,a,paste('p =',round(pv,4)))");
            //re.eval("boxplot(rbox,ylab='Survival time (Months)',cex.lab=1.4)");
            //list<-res1
            //for(i in 1:(nrow(res2)-4)){list[i+4,1]<-NA}
            //re.eval("ggsave(file = \""+strPath+"\", print(bp))");
            re.eval("dev.off()");
            re.eval("odbcClose(db)");
        } catch (REngineException e) {
            e.printStackTrace();
        } finally {
            re.close();
        }
        return strTip;
    }

    public String GetGenBoxPath(String strSql1, String strSql2, String strPath, String strGen) throws RserveException, REXPMismatchException {
        String strTip = "";
        RConnection re = new RConnection("127.0.0.1");
        try {
            re.eval("library(ggplot2)");
            re.eval("library(RODBC)");
            re.eval("db<-odbcDriverConnect('driver={SQL Server};server=(local);database=DLBCLDB;user=sa;pwd=gxqktzwq')");
            re.eval("res1<-sqlQuery(db,\"" + strSql1 + "\")");
            re.eval("res2<-sqlQuery(db,\"" + strSql2 + "\")");
            re.eval("list<-list()");
            re.eval("list<-res1");
            re.eval("r<-nrow(res1)");
            re.eval("for(i in 1:(nrow(res2)-r)){list[i+r,1]<-NA}");
            re.eval("rbox<-cbind(list,res2)");
            re.eval("list2<-res1");
            re.eval("for(i in 1:(nrow(res1))){list2[i,2]<-'Mut'}");
            re.eval("for(i in 1:(nrow(res2))){list2[(i+nrow(res1)),1]<-res2[i,1]}");
            re.eval("for(i in 1:(nrow(res2))){list2[(i+nrow(res1)),2]<-'Wt'}");
            re.eval("names(list2)<-c('expression','type')");
            re.eval("p<-wilcox.test(expression~type,data=list2)");
            re.eval("pv<-p$p.value");
            re.eval("jpeg('" + strPath + "',width=600,height=600)");
            re.eval("boxplot(rbox,cex.axis=1.5,xlim=c(0,5),at=c(1,4),ylab='Expression ( log2 ( norm_count+1 ) )',cex.lab=1.5,names=c(paste('Mut ( n=',as.character(nrow(res1)),')'),paste('Wt ( n=',as.character(nrow(res2)),')')))");
            re.eval("a<-max(list2[,1])");
            re.eval("text(2,a,paste('p =',round(pv,4)),cex=1.6)");
            re.eval("dev.off()");
            re.eval("odbcClose(db)");
        } catch (REngineException e) {
            e.printStackTrace();
        } finally {
            re.close();
        }
        return strTip;
    }

    public String GetPath(String strSql, String strSplit, String strPath, String strGen, String strAvg, String strSurvival, String strDB, String strCancer, String tip) throws RserveException, REXPMismatchException {
        String strTip = "";
        strSql = GetRString(strSql, strSurvival, "res", strDB);
        if (strSql.equals("-1")) {
            //strTip = "invalid input, please check Gene Symbol at https://www.ncbi.nlm.nih.gov/gene/.";
            strTip = "Number of DLBCL patients was less than 4 in " + tip + ", No meaningful output.";
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
            if (i < 4 && i > 0) {
                strTip = "Number of DLBCL patients was less than 4 in " + tip + ", No meaningful output.";
                return strTip;
            } else if (i == 0) {
                strTip += "invalid input for " + strGen + ", please check Gene Symbol at https://www.ncbi.nlm.nih.gov/gene/.<br />";
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
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12, y = 0.15,label = paste(\"p=\",round(p.val,4)), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12+2, y = 0.09,label = paste(\"HR=\",round(p.hr,4)), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.26, y = 0.03,label = paste('( 95%CI,',round(p.l,4),'-',round(p.t,4),')'), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.88, y = 0.05,label = \"" + strCancer + "\", size = 5,fontface = 'italic')");
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