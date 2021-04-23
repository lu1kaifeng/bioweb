package edu.henu.bioweb.BLCA;

import edu.henu.bioweb.Common.BLCACom;
import edu.henu.bioweb.Common.ComFun;
import edu.henu.bioweb.GetPathReturn;
import edu.henu.bioweb.RPlot;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class BLCACombined extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String strTip = " ";
    String strTipTCGA = " ";
    String strTip13507 = " ";
    String strTip19915 = " ";
    String strTip31684 = " ";
    String strTip32548 = " ";
    String strTip48075 = " ";
    String strTip48276 = " ";
    public BLCACombined() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    String GetFileName() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String he = dateFormat.format(now);
        Random ra = new Random();
        return he + ra.nextInt(1000);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String strTCGA = "";
        RPlot strTCGA_2 = new RPlot();
        String strGSE13507 = "";
        RPlot strGSE13507_2 = new RPlot();
        String strGSE19915 = "";
        RPlot strGSE19915_2 = new RPlot();
        String strGSE31684 = "";
        RPlot strGSE31684_2 = new RPlot();
        String strGSE32548 = "";
        RPlot strGSE32548_2 = new RPlot();
        String strGSE48075 = "";
        RPlot strGSE48075_2 = new RPlot();
        String strGSE48276 = "";
        RPlot strGSE48276_2 = new RPlot();
        String strPath = "";
        RPlot strPath_2 = new RPlot();
        String fileName = "";
        String fileTCGA = "";
        String file13507 = "";
        String file19915 = "";
        String file31684 = "";
        String file32548 = "";
        String file48075 = "";
        String file48276 = "";


        String strData = "";
        String strSurvival = "";
        String strlymph = "";
        String strtnm = "";
        String strSplit = "";
        String strSmoking = "";
        String strGender = "";
        String strGen = "";
        String strHistological = "";
        String strRace = "";
        boolean isPic = false;
        boolean isPic13507 = true;
        boolean isPic19915 = true;
        boolean isPic31684 = true;
        boolean isPic32548 = true;
        boolean isPic48075 = true;
        boolean isPic48276 = true;
        ComFun fun = new ComFun();
        BLCACom com = new BLCACom();

        request.setCharacterEncoding("UTF-8");
        try {
            strSurvival = request.getParameter("survival");
            strtnm = request.getParameter("tnm");
            strSplit = request.getParameter("split");
            strSmoking = request.getParameter("smoking");
            strGen = request.getParameter("gene");
            strlymph = request.getParameter("lymph");
            strGender = request.getParameter("gender");
            strRace = request.getParameter("race");
            strHistological = request.getParameter("histological_type");
            //strGenList=request.getParameter("mulGen");//��ȡ������
            if (!strGen.equals("") && strGen != null) {
                strGen = strGen.toUpperCase();
            }
            if (!fun.isGetgenName(strGen)) {
                strTip = fun.GetTip(4, "BLCA", strGen, "BLCA");
                strTipTCGA = " ";
                strTip13507 = " ";
                strTip19915 = " ";
                strTip31684 = " ";
                strTip32548 = " ";
                strTip48075 = " ";
                strTip48276 = " ";
                request.setAttribute("gene", strGen);
                request.setAttribute("survival", strSurvival);
                request.setAttribute("split", strSplit);
                request.setAttribute("tnm", strtnm);
                request.setAttribute("smoking", strSmoking);
                request.setAttribute("gender", strGender);
                request.setAttribute("lymph", strlymph);
                request.setAttribute("race", strRace);
                request.setAttribute("tip", strTip);
                request.setAttribute("tiptcga", strTipTCGA);
                request.setAttribute("tip13507", strTip13507);
                request.setAttribute("tip19915", strTip19915);
                request.setAttribute("tip31684", strTip31684);
                request.setAttribute("tip32548", strTip32548);
                request.setAttribute("tip48075", strTip48075);
                request.setAttribute("tip48276", strTip48276);
                request.getRequestDispatcher("/BLCA/BLCA_Combined.jsp").forward(request, response);
                return;
            }

            String strSqlTCGA = "";
            String strSql13507 = "";
            String strSql19915 = "";
            String strSql31684 = "";
            String strSql32548 = "";
            String strSql48075 = "";
            String strSql48276 = "";
            fileTCGA = GetFileName() + "Combindtcga" + strGen + strSurvival + strSplit;
            file13507 = GetFileName() + "Combind13507" + strGen + strSurvival + strSplit;
            file19915 = GetFileName() + "Combind19915" + strGen + strSurvival + strSplit;
            file31684 = GetFileName() + "Combind31684" + strGen + strSurvival + strSplit;
            file32548 = GetFileName() + "Combind32548" + strGen + strSurvival + strSplit;
            file48075 = GetFileName() + "Combind48075" + strGen + strSurvival + strSplit;
            file48276 = GetFileName() + "Combind48276" + strGen + strSurvival + strSplit;
            fileName = GetFileName() + "Combind" + strGen + strSurvival + strSplit;

            String strIndexTCGA = "";
            String strIndex13507 = "";
            String strIndex31684 = "";
            String strIndex32548 = "";
            String strIndex48075 = "";
            String strIndex48276 = "";
            boolean bl19915 = false;
            if (!com.isExistByGene(strGen, "GSE19915")) {
                isPic19915 = false;
                bl19915 = false;
            } else {
                bl19915 = true;
            }
            strIndexTCGA = fun.getGenIndex(strGen, "gen_TCGA", "jdbc/SSBLCA");// getGenIndex(strGen, "gen_TCGA");
            strIndex13507 = fun.getGenIndex(strGen, "gen_GSE13507", "jdbc/SSBLCA");//getGenIndex(strGen, "gen_GSE13507");
            strIndex31684 = fun.getGenIndex(strGen, "gen_GSE31684", "jdbc/SSBLCA");//getGenIndex(strGen, "gen_GSE31684");
            strIndex32548 = fun.getGenIndex(strGen, "gen_GSE32548", "jdbc/SSBLCA");//getGenIndex(strGen, "gen_GSE32548");
            strIndex48075 = fun.getGenIndex(strGen, "gen_GSE48075", "jdbc/SSBLCA");//getGenIndex(strGen, "gen_GSE48075");
            strIndex48276 = fun.getGenIndex(strGen, "gen_GSE48276", "jdbc/SSBLCA");//getGenIndex(strGen, "gen_GSE48276");

            if (strSurvival.equals("0")) {
                strSqlTCGA = "select Round(OS/30.0,2) as OS,OS_Event,genValue from gen_TCGA_" + strIndexTCGA + ",sample_TCGA where genName='" + strGen + "' and gen_TCGA_" + strIndexTCGA + ".genGSM = sample_TCGA.GSM and OS_Event <> '' ";
                strSql13507 = "select OS,OS_Event,avg(genValue) as genValue from sample_GSE13507,gen_GSE13507_" + strIndex13507 + " where genName='" + strGen + "' and gen_GSE13507_" + strIndex13507 + ".genGSM = sample_GSE13507.GSM and OS_Event <> '' ";
                isPic19915 = false;
                strSql31684 = "select OS,OS_Event,avg(genValue) as genValue from sample_GSE31684,gen_GSE31684_" + strIndex31684 + " where genName='" + strGen + "' and gen_GSE31684_" + strIndex31684 + ".genGSM = sample_GSE31684.GSM and OS_Event <> '' ";
                strSql32548 = "select OS,OS_Event,avg(genValue) as genValue from sample_GSE32548,gen_GSE32548_" + strIndex32548 + " where genName='" + strGen + "' and gen_GSE32548_" + strIndex32548 + ".genGSM = sample_GSE32548.GSM and OS_Event <> '' ";
                strSql48075 = "select OS,OS_Event,avg(genValue) as genValue from sample_GSE48075,gen_GSE48075_" + strIndex48075 + " where genName='" + strGen + "' and gen_GSE48075_" + strIndex48075 + ".genGSM = sample_GSE48075.GSM and OS_Event <> '' ";
                strSql48276 = "select OS,OS_Event,avg(genValue) as genValue from sample_GSE48276,gen_GSE48276_" + strIndex48276 + " where genName='" + strGen + "' and gen_GSE48276_" + strIndex48276 + ".genGSM = sample_GSE48276.GSM and OS_Event <> '' ";
            } else if (strSurvival.equals("1")) {
                strSqlTCGA = "select Round(DFI/30.0,2) as DFI,DFI_Event,genValue from gen_TCGA_" + strIndexTCGA + ",sample_TCGA where genName='" + strGen + "' and gen_TCGA_" + strIndexTCGA + ".genGSM = sample_TCGA.GSM and DFI_Event <> '' ";
                isPic13507 = false;
                isPic19915 = false;
                strSql31684 = "select DFI,DFI_Event,avg(genValue) as genValue from sample_GSE31684,gen_GSE31684_" + strIndex31684 + " where genName='" + strGen + "' and gen_GSE31684_" + strIndex31684 + ".genGSM = sample_GSE31684.GSM and DFI_Event <> '' ";
                isPic32548 = false;
                isPic48276 = false;
                isPic48075 = false;
            } else if (strSurvival.equals("2")) {
                strSqlTCGA = "select Round(PFI/30.0,2) as PFI,PFI_Event,genValue from gen_TCGA_" + strIndexTCGA + ",sample_TCGA where genName='" + strGen + "' and gen_TCGA_" + strIndexTCGA + ".genGSM = sample_TCGA.GSM and PFI_Event <> '' ";
                isPic13507 = false;
                isPic19915 = false;
                strSql31684 = "select PFI,PFI_Event,avg(genValue) as genValue from sample_GSE31684,gen_GSE31684_" + strIndex31684 + " where genName='" + strGen + "' and gen_GSE31684_" + strIndex31684 + ".genGSM = sample_GSE31684.GSM and PFI_Event <> '' ";
                isPic32548 = false;
                isPic48276 = false;
                isPic48075 = false;
            } else if (strSurvival.equals("3")) {
                strSqlTCGA = "select Round(DSS/30.0,2) as DSS,DSS_Event,genValue from gen_TCGA_" + strIndexTCGA + ",sample_TCGA where genName='" + strGen + "' and gen_TCGA_" + strIndexTCGA + ".genGSM = sample_TCGA.GSM and DSS_Event <> '' ";
                isPic13507 = false;
                strSql19915 = "select DSS,DSS_Event,avg(genValue) as genValue from sample_GSE19915,gen_GSE19915 where genName='" + strGen + "' and gen_GSE19915.genGSM = sample_GSE19915.GSM and DSS_Event <> '' ";
                strSql31684 = "select DSS,DSS_Event,avg(genValue) as genValue from sample_GSE31684,gen_GSE31684_" + strIndex31684 + " where genName='" + strGen + "' and gen_GSE31684_" + strIndex31684 + ".genGSM = sample_GSE31684.GSM and DSS_Event <> '' ";
                isPic32548 = false;
                strSql48276 = "select DSS,DSS_Event,avg(genValue) as genValue from sample_GSE48276,gen_GSE48276_" + strIndex48276 + " where genName='" + strGen + "' and gen_GSE48276_" + strIndex48276 + ".genGSM = sample_GSE48276.GSM and DSS_Event <> '' ";
                isPic48075 = false;
            }
            if (!strlymph.equals("2")) {
                strSqlTCGA += " and lymph='" + strlymph + "'";
                fileTCGA += strlymph;
                fileName += strlymph;
                isPic13507 = false;
                isPic19915 = false;
                isPic31684 = false;
                isPic32548 = false;
                isPic48075 = false;
                isPic48276 = false;
            }
            if (!strtnm.equals("0")) {
                strSqlTCGA += " and tnm='" + strtnm + "'";
                strSql13507 += " and tnm='" + strtnm + "'";
                strSql19915 += " and tnm='" + strtnm + "'";
                strSql31684 += " and tnm='" + strtnm + "'";
                strSql48276 += " and tnm='" + strtnm + "'";
                fileTCGA += strtnm;
                fileName += strtnm;
                file13507 += strtnm;
                file19915 += strtnm;
                file31684 += strtnm;
                file48276 += strtnm;
                isPic48075 = false;
            }
            if (!strSmoking.equals("5")) {
                strSqlTCGA += " and smoking='" + strSmoking + "'";
                strSql31684 += " and smoking='" + strSmoking + "'";
                strSql48276 += " and smoking='" + strSmoking + "'";
                fileTCGA += strSmoking;
                fileName += strSmoking;
                file31684 += strSmoking;
                isPic19915 = false;
                file48276 += strSmoking;
                isPic13507 = false;
                isPic32548 = false;
                isPic48075 = false;
            }
            if (!strGender.equals("2")) {
                strSqlTCGA += " and gender='" + strGender + "'";
                strSql13507 += " and gender='" + strGender + "'";
                strSql31684 += " and gender='" + strGender + "'";
                strSql32548 += " and gender='" + strGender + "'";
                strSql48276 += " and gender='" + strGender + "'";
                fileTCGA += strGender;
                fileName += strGender;
                file31684 += strGender;
                file32548 += strGender;
                file13507 += strGender;
                file48276 += strGender;
                isPic48075 = false;
                isPic19915 = false;
            }
            if (!strRace.equals("3")) {
                strSqlTCGA += " and race='" + strRace + "'";
                strSql48276 += " and race='" + strRace + "'";
                fileTCGA += strRace;
                fileName += strRace;
                file48276 += strRace;
                isPic13507 = false;
                isPic19915 = false;
                isPic31684 = false;
                isPic32548 = false;
                isPic48075 = false;
            }
            if (!strHistological.equals("0")) {
                strSqlTCGA += " and histological_type='" + strHistological + "' ";
                strSql19915 += " and histological_type='" + strHistological + "' ";
                strSql13507 += " and histological_type='" + strHistological + "' ";
                isPic31684 = false;
                strSql32548 += " and histological_type='" + strHistological + "' ";
                strSql48075 += " and histological_type='" + strHistological + "' ";
                strSql48276 += " and histological_type='" + strHistological + "' ";
                fileName += strHistological;
            }
            if (strSurvival.equals("0")) {
                strSql31684 += " group by sample_GSE31684.GSM,OS_Event,OS";
                strSql13507 += " group by sample_GSE13507.GSM,OS_Event,OS";
                strSql32548 += " group by sample_GSE32548.GSM,OS_Event,OS";
                strSql48075 += " group by sample_GSE48075.GSM,OS_Event,OS";
                strSql48276 += " group by sample_GSE48276.GSM,OS_Event,OS";
                isPic19915 = false;
            } else if (strSurvival.equals("1")) {
                strSql31684 += " group by sample_GSE31684.GSM,DFI_Event,DFI";
                isPic13507 = false;
                isPic32548 = false;
                isPic48075 = false;
                isPic48276 = false;
                isPic19915 = false;
            } else if (strSurvival.equals("2")) {
                strSql31684 += " group by sample_GSE31684.GSM,PFI_Event,PFI";
                isPic13507 = false;
                isPic19915 = false;
                isPic32548 = false;
                isPic48075 = false;
                isPic48276 = false;
            } else if (strSurvival.equals("3")) {
                strSql19915 += " group by sample_GSE19915.GSM,DSS_Event,DSS";
                strSql31684 += " group by sample_GSE31684.GSM,DSS_Event,DSS";
                isPic13507 = false;
                isPic32548 = false;
                isPic48075 = false;
                strSql48276 += " group by sample_GSE48276.GSM,DSS_Event,DSS";
            }

            strPath = "d:/picture/Combined" + fileName + ".jpg";
            //strPath_2 = "/picture/Combined"+fileName+".jpg";
            strGSE13507 = "d:/picture/GSE13507" + file13507 + ".jpg";
            //strGSE13507_2 = "/picture/GSE13507"+file13507+".jpg";
            strGSE19915 = "d:/picture/GSE19915" + file19915 + ".jpg";
            //strGSE19915_2 = "/picture/GSE19915"+file19915+".jpg";
            strGSE31684 = "d:/picture/GSE31684" + file31684 + ".jpg";
            //strGSE31684_2 = "/picture/GSE31684"+file31684+".jpg";
            strGSE32548 = "d:/picture/GSE32548" + file32548 + ".jpg";
            //strGSE32548_2 = "/picture/GSE32548"+file32548+".jpg";
            strGSE48075 = "d:/picture/GSE48075" + file48075 + ".jpg";
            //strGSE48075_2 = "/picture/GSE48075"+file48075+".jpg";
            strGSE48276 = "d:/picture/GSE48276" + file48276 + ".jpg";
            //strGSE48276_2 = "/picture/GSE48276"+file48276+".jpg";
            strTCGA = "d:/picture/TCGA" + fileTCGA + ".jpg";
            //strTCGA_2 = "/picture/TCGA"+fileTCGA+".jpg";

            if (!isPic13507) {
                strSql13507 = "0";
            }
            if (!isPic19915) {
                strSql19915 = "0";
            }
            if (!isPic31684) {
                strSql31684 = "0";
            }
            if (!isPic32548) {
                strSql32548 = "0";
            }
            if (!isPic48075) {
                strSql48075 = "0";
            }
            if (!isPic48276) {
                strSql48276 = "0";
            }
            if (isPic13507) {
                GetPathReturn pathReturn13507 = com.GetPath(strSql13507, strSplit, strGSE13507, strGen.toUpperCase(), "GSE13507:" + strGen.toUpperCase(), strSurvival, "jdbc/SSBLCA", "OSblca", "GSE13507");
                strTip13507 = pathReturn13507.getStrTip();
                strGSE13507_2 = pathReturn13507.getPlotBytes();
                //GetGSE13507(strSql13507, strSplit, strGSE13507, strGen,strSurvival);
            } else {
                if (strIndex13507.equals("-1")) {
                    strTip13507 = fun.GetTip(3, "GSE13507", strGen, "BLCA");
                } else {
                    strTip13507 = fun.GetTip(2, "GSE13507", strGen, "BLCA");
                }
            }
            if (isPic19915) {
                GetPathReturn pathReturn19915 = com.GetPath(strSql19915, strSplit, strGSE19915, strGen.toUpperCase(), "GSE19915:" + strGen.toUpperCase(), strSurvival, "jdbc/SSBLCA", "OSblca", "GSE19915");
                strTip19915 = pathReturn19915.getStrTip();
                strGSE19915_2 = pathReturn19915.getPlotBytes();
            } else {
                if (!bl19915) {
                    strTip19915 = fun.GetTip(3, "GSE19915", strGen, "BLCA");
                } else {
                    strTip19915 = fun.GetTip(2, "GSE19915", strGen, "BLCA");
                }
            }
            if (isPic31684) {
                GetPathReturn pathReturn31684 = com.GetPath(strSql31684, strSplit, strGSE31684, strGen.toUpperCase(), "GSE31684:" + strGen.toUpperCase(), strSurvival, "jdbc/SSBLCA", "OSblca", "GSE31684");
                strTip31684 = pathReturn31684.getStrTip();
                strGSE31684_2 = pathReturn31684.getPlotBytes();
            } else {
                if (strIndex31684.equals("-1")) {
                    strTip31684 = fun.GetTip(3, "GSE31684", strGen, "BLCA");
                } else {
                    strTip31684 = fun.GetTip(2, "GSE31684", strGen, "BLCA");
                }
            }
            if (isPic32548) {
                GetPathReturn pathReturn32548 = com.GetPath(strSql32548, strSplit, strGSE32548, strGen.toUpperCase(), "GSE32548:" + strGen.toUpperCase(), strSurvival, "jdbc/SSBLCA", "OSblca", "GSE32548");
                strTip32548 = pathReturn32548.getStrTip();
                strGSE32548_2 = pathReturn32548.getPlotBytes();
            } else {
                if (strIndex32548.equals("-1")) {
                    strTip32548 = fun.GetTip(3, "GSE32548", strGen, "BLCA");
                } else {
                    strTip32548 = fun.GetTip(2, "GSE32548", strGen, "BLCA");
                }
            }
            if (isPic48075) {
                GetPathReturn pathReturn48075 = com.GetPath(strSql48075, strSplit, strGSE48075, strGen.toUpperCase(), "GSE48075:" + strGen.toUpperCase(), strSurvival, "jdbc/SSBLCA", "OSblca", "GSE48075");
                strTip48075 = pathReturn48075.getStrTip();
                strGSE48075_2 = pathReturn48075.getPlotBytes();
            } else {
                if (strIndex48075.equals("-1")) {
                    strTip48075 = fun.GetTip(3, "GSE48075", strGen, "BLCA");
                } else {
                    strTip48075 = fun.GetTip(2, "GSE48075", strGen, "BLCA");
                }
            }
            if (isPic48276) {
                GetPathReturn pathReturn48276 = com.GetPath(strSql48276, strSplit, strGSE48276, strGen.toUpperCase(), "GSE48276:" + strGen.toUpperCase(), strSurvival, "jdbc/SSBLCA", "OSblca", "GSE48276");
                strTip48276 = pathReturn48276.getStrTip();
                strGSE48276_2 = pathReturn48276.getPlotBytes();
            } else {
                if (strIndex48276.equals("-1")) {
                    strTip48276 = fun.GetTip(3, "GSE48276", strGen, "BLCA");
                } else {
                    strTip48276 = fun.GetTip(2, "GSE48276", strGen, "BLCA");
                }
            }
            if (!strIndexTCGA.equals("-1")) {
                GetPathReturn pathReturnTCGA = com.GetPath(strSqlTCGA, strSplit, strTCGA, strGen.toUpperCase(), "TCGA:" + strGen.toUpperCase(), strSurvival, "jdbc/SSBLCA", "OSblca", "TCGA");
                strTipTCGA = pathReturnTCGA.getStrTip();
                strTCGA_2 = pathReturnTCGA.getPlotBytes();
            } else {
                if (strIndexTCGA.equals("-1")) {
                    strTipTCGA = fun.GetTip(3, "TCGA", strGen, "BLCA");
                } else {
                    strTipTCGA = fun.GetTip(2, "TCGA", strGen, "BLCA");
                }
            }
            strPath_2 = GetPath(strSqlTCGA, strSplit, strPath, strGen, strSql13507, strSql19915, strSql31684, strSql32548, strSql48075, strSql48276, strSurvival);

        } catch (RserveException | REXPMismatchException e) {
            e.printStackTrace();
        }
        String error = "";

        if (error != "") {
            request.setAttribute("error", error);
            request.getRequestDispatcher("/BLCA/BLCA_Combined.jsp").forward(request, response);
        } else {
            //if(isPic)
            //{
            //request.setAttribute("myimg", strPath2.toString());//����
            //request.setAttribute("myimg", strPath);//����
            //}
            //else
            //{
            //request.setAttribute("myimg", "");//����
            //}
            request.setAttribute("gene", strGen);
            request.setAttribute("survival", strSurvival);
            request.setAttribute("split", strSplit);
            request.setAttribute("tnm", strtnm);
            request.setAttribute("smoking", strSmoking);
            request.setAttribute("gender", strGender);
            request.setAttribute("lymph", strlymph);
            request.setAttribute("race", strRace);
		/*request.setAttribute("tcga", strTCGA);//����
		request.setAttribute("gse13507", strGSE13507);//����
		request.setAttribute("gse19915", strGSE19915);//����
		request.setAttribute("gse31684", strGSE31684);//����
		request.setAttribute("gse32548", strGSE32548);//����
		request.setAttribute("gse48075", strGSE48075);//����
		request.setAttribute("gse48276", strGSE48276);//����
		request.setAttribute("combined", strPath);//����*/

            //plot bytes
            request.setAttribute("tcga", strTCGA_2.toString());//����
            request.setAttribute("gse13507", strGSE13507_2.toString());//����
            request.setAttribute("gse19915", strGSE19915_2.toString());//����
            request.setAttribute("gse31684", strGSE31684_2.toString());//����
            request.setAttribute("gse32548", strGSE32548_2.toString());//����
            request.setAttribute("gse48075", strGSE48075_2.toString());//����
            request.setAttribute("gse48276", strGSE48276_2.toString());//����
            request.setAttribute("combined", strPath_2.toString());//����


            request.setAttribute("tip", strTip);
            request.setAttribute("tiptcga", strTipTCGA);
            request.setAttribute("tip13507", strTip13507);
            request.setAttribute("tip19915", strTip19915);
            request.setAttribute("tip31684", strTip31684);
            request.setAttribute("tip32548", strTip32548);
            request.setAttribute("tip48075", strTip48075);
            request.setAttribute("tip48276", strTip48276);

            request.getRequestDispatcher("/BLCA/BLCA_Combined.jsp").forward(request, response);
        }
    }

    public RPlot GetPath(String strSql, String strSplit, String strPath, String strGen, String strSql11, String strSql12, String strSql21, String strSql22, String strSql31, String strSql32, String strSurvival) throws RserveException, REXPMismatchException {
        BLCACom com = new BLCACom();
        byte[] bytes = null;
        strSql = com.GetRString(strSql, strSurvival, "res", "jdbc/SSBLCA");
        RConnection re = new RConnection("127.0.0.1");
        int j13507 = 0;
        int j19915 = 0;
        int j31684 = 0;
        int j32548 = 0;
        int j48075 = 0;
        int j48276 = 0;
        int k = 0;
        try {
            re.eval("library(survival)");
            re.eval("library(ggplot2)");
            re.eval("library(magrittr)");
            re.eval("library(ggpubr)");
            re.eval("library(survminer)");
            re.eval("library(Cairo)");
            re.eval("library(png)");
            re.eval("Cairo(file='/dev/null')");
            if (!strSql.equals("-1")) {
                REXP x = re.eval("r<-nrow(res)");
                k = x.asInteger();
                re.eval(strSql);
                re.eval("attach(res)");
            }

            if (!strSql11.equals("0")) {
                strSql11 = com.GetRString(strSql11, strSurvival, "res13507", "jdbc/SSBLCA");

                if (!strSql11.equals("-1")) {
                    re.eval(strSql11);
                    //re.eval("res13507<-sqlQuery(db,\""+strSql11+"\")");
                    REXP x13507 = re.eval("r13507<-nrow(res13507)");
                    j13507 = x13507.asInteger();
                    re.eval("attach(res13507)");
                }
            }
            if (!strSql12.equals("0")) {
                strSql12 = com.GetRString(strSql12, strSurvival, "res19915", "jdbc/SSBLCA");
                if (!strSql12.equals("-1")) {
                    re.eval(strSql12);
                    REXP x19915 = re.eval("r19915<-nrow(res19915)");
                    j19915 = x19915.asInteger();
                    re.eval("attach(res19915)");
                }
            }
            if (!strSql21.equals("0")) {
                strSql21 = com.GetRString(strSql21, strSurvival, "res31684", "jdbc/SSBLCA");
                if (!strSql21.equals("-1")) {
                    re.eval(strSql21);
                    REXP x31684 = re.eval("r31684<-nrow(res31684)");
                    j31684 = x31684.asInteger();
                    re.eval("attach(res31684)");
                }
            }
            if (!strSql22.equals("0")) {
                strSql22 = com.GetRString(strSql22, strSurvival, "res32548", "jdbc/SSBLCA");
                if (!strSql22.equals("-1")) {
                    re.eval(strSql22);
                    REXP x32548 = re.eval("r32548<-nrow(res32548)");
                    j32548 = x32548.asInteger();
                    re.eval("attach(res32548)");
                }
            }
            if (!strSql31.equals("0")) {
                strSql31 = com.GetRString(strSql31, strSurvival, "res48075", "jdbc/SSBLCA");
                if (!strSql31.equals("-1")) {
                    re.eval(strSql31);
                    REXP x48075 = re.eval("r48075<-nrow(res48075)");
                    j48075 = x48075.asInteger();
                    re.eval("attach(res48075)");
                }
            }
            if (!strSql32.equals("0")) {
                strSql32 = com.GetRString(strSql32, strSurvival, "res48276", "jdbc/SSBLCA");
                if (!strSql32.equals("-1")) {
                    re.eval(strSql32);
                    REXP x48276 = re.eval("r48276<-nrow(res48276)");
                    j48276 = x48276.asInteger();
                    re.eval("attach(res48276)");
                }
            }
            ComFun fun = new ComFun();
            int i = k + j13507 + j19915 + j31684 + j32548 + j48075 + j48276;
            if (i < 4) {
                strTip = fun.GetTip(2, "Combined", strGen, "BLCA");
                return new RPlot(null);
            } else {
                strTip = " ";
            }

            //re.eval("attach(res)");//��ʱע��
            //re.eval("attach(res2)");//��ʱע��

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

            if (strSplit.equals("0") || strSplit.equals("2") || strSplit.equals("7")) {
                if (k > 3) {
                    re.eval("gen<-res[order(res[,3]),]");
                }
                if (!strSql11.equals("0")) {
                    re.eval("gen13507<-res13507[order(res13507[,3]),]");
                }
                if (!strSql12.equals("0")) {
                    re.eval("gen19915<-res19915[order(res19915[,3]),]");
                }
                if (!strSql21.equals("0")) {
                    re.eval("gen31684<-res31684[order(res31684[,3]),]");
                }
                if (!strSql22.equals("0")) {
                    re.eval("gen32548<-res32548[order(res32548[,3]),]");
                }
                if (!strSql31.equals("0")) {
                    re.eval("gen48075<-res48075[order(res48075[,3]),]");
                }
                if (!strSql32.equals("0")) {
                    re.eval("gen48276<-res48276[order(res48276[,3]),]");
                }
            } else {
                if (k > 3) {
                    re.eval("gen<-res[order(-res[,3]),]");
                }
                if (!strSql11.equals("0") && j13507 > 3) {
                    re.eval("gen13507<-res13507[order(-res13507[,3]),]");
                }
                if (!strSql12.equals("0") && j19915 > 3) {
                    re.eval("gen19915<-res19915[order(-res19915[,3]),]");
                }
                if (!strSql21.equals("0") && j31684 > 3) {
                    re.eval("gen31684<-res31684[order(-res31684[,3]),]");
                }
                if (!strSql22.equals("0") && j32548 > 3) {
                    re.eval("gen32548<-res32548[order(-res32548[,3]),]");
                }
                if (!strSql31.equals("0") && j48075 > 3) {
                    re.eval("gen48075<-res48075[order(-res48075[,3]),]");
                }
                if (!strSql32.equals("0") && j48276 > 3) {
                    re.eval("gen48276<-res48276[order(-res48276[,3]),]");
                }
            }
            if (strSplit.equals("0") || strSplit.equals("1")) {
                if (k > 3) {
                    //re.eval("r1<-round(r/4)");��֪��Ϊɶ��
                    //System.out.println("");
                    re.eval("r1<-round(r/4)");
                    re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}else{gen.up<-gen[1:(r1+1),];gen.low<-gen[(r1+2):r,]}}else{gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}");
                }
                if (!strSql11.equals("0") && j13507 > 3) {
                    re.eval("r135072<-round(r13507/4)");
                    re.eval("if(gen13507[r135072,3]==gen13507[r135072+1,3]){suba<-gen13507[r135072,3]-gen13507[r135072-1,3];subb<-gen13507[r135072+1,3]-gen13507[r135072+2,3];if(abs(suba)>abs(subb)){gen13507.up<-gen13507[1:r135072,];gen13507.low<-gen13507[(r135072+1):r13507,]}else{gen13507.up<-gen13507[1:(r135072+1),];gen13507.low<-gen13507[(r135072+2):r13507,]}}else{gen13507.up<-gen13507[1:r135072,];gen13507.low<-gen13507[(r135072+1):r13507,]}");
                }
                if (!strSql12.equals("0") && j19915 > 3) {
                    re.eval("r199152<-round(r19915/4)");
                    re.eval("if(gen19915[r199152,3]==gen19915[r199152+1,3]){suba<-gen19915[r199152,3]-gen19915[r199152-1,3];subb<-gen19915[r199152+1,3]-gen19915[r199152+2,3];if(abs(suba)>abs(subb)){gen19915.up<-gen19915[1:r199152,];gen19915.low<-gen19915[(r199152+1):r19915,]}else{gen19915.up<-gen19915[1:(r199152+1),];gen19915.low<-gen19915[(r199152+2):r19915,]}}else{gen19915.up<-gen19915[1:r199152,];gen19915.low<-gen19915[(r199152+1):r19915,]}");
                }
                if (!strSql21.equals("0") && j31684 > 3) {
                    re.eval("r316842<-round(r31684/4)");
                    re.eval("if(gen31684[r316842,3]==gen31684[r316842+1,3]){suba<-gen31684[r316842,3]-gen31684[r316842-1,3];subb<-gen31684[r316842+1,3]-gen31684[r316842+2,3];if(abs(suba)>abs(subb)){gen31684.up<-gen31684[1:r316842,];gen31684.low<-gen31684[(r316842+1):r31684,]}else{gen31684.up<-gen31684[1:(r316842+1),];gen31684.low<-gen31684[(r316842+2):r31684,]}}else{gen31684.up<-gen31684[1:r316842,];gen31684.low<-gen31684[(r316842+1):r31684,]}");
                }
                if (!strSql22.equals("0") && j32548 > 3) {
                    re.eval("r325482<-round(r32548/4)");
                    re.eval("if(gen32548[r325482,3]==gen32548[r325482+1,3]){suba<-gen32548[r325482,3]-gen32548[r325482-1,3];subb<-gen32548[r325482+1,3]-gen32548[r325482+2,3];if(abs(suba)>abs(subb)){gen32548.up<-gen32548[1:r325482,];gen32548.low<-gen32548[(r325482+1):r32548,]}else{gen32548.up<-gen32548[1:(r325482+1),];gen32548.low<-gen32548[(r325482+2):r32548,]}}else{gen32548.up<-gen32548[1:r325482,];gen32548.low<-gen32548[(r325482+1):r32548,]}");
                }
                if (!strSql31.equals("0") && j48075 > 3) {
                    re.eval("r480752<-round(r48075/4)");
                    re.eval("if(gen48075[r480752,3]==gen48075[r480752+1,3]){suba<-gen48075[r480752,3]-gen48075[r480752-1,3];subb<-gen48075[r480752+1,3]-gen48075[r480752+2,3];if(abs(suba)>abs(subb)){gen48075.up<-gen48075[1:r480752,];gen48075.low<-gen48075[(r480752+1):r48075,]}else{gen48075.up<-gen48075[1:(r480752+1),];gen48075.low<-gen48075[(r480752+2):r48075,]}}else{gen48075.up<-gen48075[1:r480752,];gen48075.low<-gen48075[(r480752+1):r48075,]}");
                }
                if (!strSql32.equals("0") && j48276 > 3) {
                    re.eval("r482762<-round(r48276/4)");
                    re.eval("if(gen48276[r482762,3]==gen48276[r482762+1,3]){suba<-gen48276[r482762,3]-gen48276[r482762-1,3];subb<-gen48276[r482762+1,3]-gen48276[r482762+2,3];if(abs(suba)>abs(subb)){gen48276.up<-gen48276[1:r482762,];gen48276.low<-gen48276[(r482762+1):r48276,]}else{gen48276.up<-gen48276[1:(r482762+1),];gen48276.low<-gen48276[(r482762+2):r48276,]}}else{gen48276.up<-gen48276[1:r482762,];gen48276.low<-gen48276[(r482762+1):r48276,]}");
                }
            } else if (strSplit.equals("2") || strSplit.equals("3")) {
                if (k > 3) {
                    re.eval("r1<-round(r*0.3)");
                    re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}else{gen.up<-gen[1:(r1+1),];gen.low<-gen[(r1+2):r,]}}else{gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}");
                }
                if (!strSql11.equals("0") && j13507 > 3) {
                    re.eval("r135072<-round(r13507*0.3)");
                    re.eval("if(gen13507[r135072,3]==gen13507[r135072+1,3]){suba<-gen13507[r135072,3]-gen13507[r135072-1,3];subb<-gen13507[r135072+1,3]-gen13507[r135072+2,3];if(abs(suba)>abs(subb)){gen13507.up<-gen13507[1:r135072,];gen13507.low<-gen13507[(r135072+1):r13507,]}else{gen13507.up<-gen13507[1:(r135072+1),];gen13507.low<-gen13507[(r135072+2):r13507,]}}else{gen13507.up<-gen13507[1:r135072,];gen13507.low<-gen13507[(r135072+1):r13507,]}");
                }
                if (!strSql12.equals("0") && j19915 > 3) {
                    re.eval("r199152<-round(r19915*0.3)");
                    re.eval("if(gen19915[r199152,3]==gen19915[r199152+1,3]){suba<-gen19915[r199152,3]-gen19915[r199152-1,3];subb<-gen19915[r199152+1,3]-gen19915[r199152+2,3];if(abs(suba)>abs(subb)){gen19915.up<-gen19915[1:r199152,];gen19915.low<-gen19915[(r199152+1):r19915,]}else{gen19915.up<-gen19915[1:(r199152+1),];gen19915.low<-gen19915[(r199152+2):r19915,]}}else{gen19915.up<-gen19915[1:r199152,];gen19915.low<-gen19915[(r199152+1):r19915,]}");
                }
                if (!strSql21.equals("0") && j31684 > 3) {
                    re.eval("r316842<-round(r31684*0.3)");
                    re.eval("if(gen31684[r316842,3]==gen31684[r316842+1,3]){suba<-gen31684[r316842,3]-gen31684[r316842-1,3];subb<-gen31684[r316842+1,3]-gen31684[r316842+2,3];if(abs(suba)>abs(subb)){gen31684.up<-gen31684[1:r316842,];gen31684.low<-gen31684[(r316842+1):r31684,]}else{gen31684.up<-gen31684[1:(r316842+1),];gen31684.low<-gen31684[(r316842+2):r31684,]}}else{gen31684.up<-gen31684[1:r316842,];gen31684.low<-gen31684[(r316842+1):r31684,]}");
                }
                if (!strSql22.equals("0") && j32548 > 3) {
                    re.eval("r325482<-round(r32548*0.3)");
                    re.eval("if(gen32548[r325482,3]==gen32548[r325482+1,3]){suba<-gen32548[r325482,3]-gen32548[r325482-1,3];subb<-gen32548[r325482+1,3]-gen32548[r325482+2,3];if(abs(suba)>abs(subb)){gen32548.up<-gen32548[1:r325482,];gen32548.low<-gen32548[(r325482+1):r32548,]}else{gen32548.up<-gen32548[1:(r325482+1),];gen32548.low<-gen32548[(r325482+2):r32548,]}}else{gen32548.up<-gen32548[1:r325482,];gen32548.low<-gen32548[(r325482+1):r32548,]}");
                }
                if (!strSql31.equals("0") && j48075 > 3) {
                    re.eval("r480752<-round(r48075*0.3)");
                    re.eval("if(gen48075[r480752,3]==gen48075[r480752+1,3]){suba<-gen48075[r480752,3]-gen48075[r480752-1,3];subb<-gen48075[r480752+1,3]-gen48075[r480752+2,3];if(abs(suba)>abs(subb)){gen48075.up<-gen48075[1:r480752,];gen48075.low<-gen48075[(r480752+1):r48075,]}else{gen48075.up<-gen48075[1:(r480752+1),];gen48075.low<-gen48075[(r480752+2):r48075,]}}else{gen48075.up<-gen48075[1:r480752,];gen48075.low<-gen48075[(r480752+1):r48075,]}");
                }
                if (!strSql32.equals("0") && j48276 > 3) {
                    re.eval("r482762<-round(r48276*0.3)");
                    re.eval("if(gen48276[r482762,3]==gen48276[r482762+1,3]){suba<-gen48276[r482762,3]-gen48276[r482762-1,3];subb<-gen48276[r482762+1,3]-gen48276[r482762+2,3];if(abs(suba)>abs(subb)){gen48276.up<-gen48276[1:r482762,];gen48276.low<-gen48276[(r482762+1):r48276,]}else{gen48276.up<-gen48276[1:(r482762+1),];gen48276.low<-gen48276[(r482762+2):r48276,]}}else{gen48276.up<-gen48276[1:r482762,];gen48276.low<-gen48276[(r482762+1):r48276,]}");
                }
            } else if (strSplit.equals("4") || strSplit.equals("7")) {
                if (k > 3) {
                    re.eval("r1<-round(r/2)");
                    re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}else{gen.up<-gen[1:(r1+1),];gen.low<-gen[(r1+2):r,]}}else{gen.up<-gen[1:r1,];gen.low<-gen[(r1+1):r,]}");
                }
                if (!strSql11.equals("0") && j13507 > 3) {
                    re.eval("r135072<-round(r13507/2)");
                    re.eval("if(gen13507[r135072,3]==gen13507[r135072+1,3]){suba<-gen13507[r135072,3]-gen13507[r135072-1,3];subb<-gen13507[r135072+1,3]-gen13507[r135072+2,3];if(abs(suba)>abs(subb)){gen13507.up<-gen13507[1:r135072,];gen13507.low<-gen13507[(r135072+1):r13507,]}else{gen13507.up<-gen13507[1:(r135072+1),];gen13507.low<-gen13507[(r135072+2):r13507,]}}else{gen13507.up<-gen13507[1:r135072,];gen13507.low<-gen13507[(r135072+1):r13507,]}");
                }
                if (!strSql12.equals("0") && j19915 > 3) {
                    re.eval("r199152<-round(r19915/2)");
                    re.eval("if(gen19915[r199152,3]==gen19915[r199152+1,3]){suba<-gen19915[r199152,3]-gen19915[r199152-1,3];subb<-gen19915[r199152+1,3]-gen19915[r199152+2,3];if(abs(suba)>abs(subb)){gen19915.up<-gen19915[1:r199152,];gen19915.low<-gen19915[(r199152+1):r19915,]}else{gen19915.up<-gen19915[1:(r199152+1),];gen19915.low<-gen19915[(r199152+2):r19915,]}}else{gen19915.up<-gen19915[1:r199152,];gen19915.low<-gen19915[(r199152+1):r19915,]}");
                }
                if (!strSql21.equals("0") && j31684 > 3) {
                    re.eval("r316842<-round(r31684/2)");
                    re.eval("if(gen31684[r316842,3]==gen31684[r316842+1,3]){suba<-gen31684[r316842,3]-gen31684[r316842-1,3];subb<-gen31684[r316842+1,3]-gen31684[r316842+2,3];if(abs(suba)>abs(subb)){gen31684.up<-gen31684[1:r316842,];gen31684.low<-gen31684[(r316842+1):r31684,]}else{gen31684.up<-gen31684[1:(r316842+1),];gen31684.low<-gen31684[(r316842+2):r31684,]}}else{gen31684.up<-gen31684[1:r316842,];gen31684.low<-gen31684[(r316842+1):r31684,]}");
                }
                if (!strSql22.equals("0") && j32548 > 3) {
                    re.eval("r325482<-round(r32548/2)");
                    re.eval("if(gen32548[r325482,3]==gen32548[r325482+1,3]){suba<-gen32548[r325482,3]-gen32548[r325482-1,3];subb<-gen32548[r325482+1,3]-gen32548[r325482+2,3];if(abs(suba)>abs(subb)){gen32548.up<-gen32548[1:r325482,];gen32548.low<-gen32548[(r325482+1):r32548,]}else{gen32548.up<-gen32548[1:(r325482+1),];gen32548.low<-gen32548[(r325482+2):r32548,]}}else{gen32548.up<-gen32548[1:r325482,];gen32548.low<-gen32548[(r325482+1):r32548,]}");
                }
                if (!strSql31.equals("0") && j48075 > 3) {
                    re.eval("r480752<-round(r48075/2)");
                    re.eval("if(gen48075[r480752,3]==gen48075[r480752+1,3]){suba<-gen48075[r480752,3]-gen48075[r480752-1,3];subb<-gen48075[r480752+1,3]-gen48075[r480752+2,3];if(abs(suba)>abs(subb)){gen48075.up<-gen48075[1:r480752,];gen48075.low<-gen48075[(r480752+1):r48075,]}else{gen48075.up<-gen48075[1:(r480752+1),];gen48075.low<-gen48075[(r480752+2):r48075,]}}else{gen48075.up<-gen48075[1:r480752,];gen48075.low<-gen48075[(r480752+1):r48075,]}");
                }
                if (!strSql32.equals("0") && j48276 > 3) {
                    re.eval("r482762<-round(r48276/2)");
                    re.eval("if(gen48276[r482762,3]==gen48276[r482762+1,3]){suba<-gen48276[r482762,3]-gen48276[r482762-1,3];subb<-gen48276[r482762+1,3]-gen48276[r482762+2,3];if(abs(suba)>abs(subb)){gen48276.up<-gen48276[1:r482762,];gen48276.low<-gen48276[(r482762+1):r48276,]}else{gen48276.up<-gen48276[1:(r482762+1),];gen48276.low<-gen48276[(r482762+2):r48276,]}}else{gen48276.up<-gen48276[1:r482762,];gen48276.low<-gen48276[(r482762+1):r48276,]}");
                }
            } else if (strSplit.equals("5")) {
                if (k > 3) {
                    re.eval("r1<-round(r/4)");
                    re.eval("r2<-r-r1");
                    re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];}else{gen.up<-gen[1:(r1+1),];}}else{gen.up<-gen[1:r1,];}");
                    re.eval("if(gen[r2+1,3]==gen[r2,3]){suba<-gen[r2+1,3]-gen[r2+2,3];subb<-gen[r2,3]-gen[r2-1,3];if(abs(suba)>abs(subb)){gen.low<-gen[(r2+2):r,];}else{gen.low<-gen[(r2+1):r,];}}else{gen.low<-gen[(r2+1):r,];}");
                }
                if (!strSql11.equals("0") && j13507 > 3) {
                    re.eval("r135072<-round(r13507/4)");
                    re.eval("r135073<-r13507-r135072");
                    re.eval("if(gen13507[r135072,3]==gen13507[r135072+1,3]){suba<-gen13507[r135072,3]-gen13507[r135072-1,3];subb<-gen13507[r135072+1,3]-gen13507[r135072+2,3];if(abs(suba)>abs(subb)){gen13507.up<-gen13507[1:r135072,];}else{gen13507.up<-gen13507[1:(r135072+1),];}}else{gen13507.up<-gen13507[1:r135072,];}");
                    re.eval("if(gen13507[r135073+1,3]==gen13507[r135073,3]){suba<-gen13507[r135073+1,3]-gen13507[r135073+2,3];subb<-gen13507[r135073,3]-gen13507[r135073-1,3];if(abs(suba)>abs(subb)){gen13507.low<-gen13507[(r135073+2):r13507,];}else{gen13507.low<-gen13507[(r135073+1):r13507,];}}else{gen13507.low<-gen13507[(r135073+1):r13507,];}");
                }
                if (!strSql12.equals("0") && j19915 > 3) {
                    re.eval("r199152<-round(r19915/4)");
                    re.eval("r199153<-r19915-r199152");
                    re.eval("if(gen19915[r199152,3]==gen19915[r199152+1,3]){suba<-gen19915[r199152,3]-gen19915[r199152-1,3];subb<-gen19915[r199152+1,3]-gen19915[r199152+2,3];if(abs(suba)>abs(subb)){gen19915.up<-gen19915[1:r199152,];}else{gen19915.up<-gen19915[1:(r199152+1),];}}else{gen19915.up<-gen19915[1:r199152,];}");
                    re.eval("if(gen19915[r199153+1,3]==gen19915[r199153,3]){suba<-gen19915[r199153+1,3]-gen19915[r199153+2,3];subb<-gen19915[r199153,3]-gen19915[r199153-1,3];if(abs(suba)>abs(subb)){gen19915.low<-gen19915[(r199153+2):r19915,];}else{gen19915.low<-gen19915[(r199153+1):r19915,];}}else{gen19915.low<-gen19915[(r199153+1):r19915,];}");
                }
                if (!strSql21.equals("0") && j31684 > 3) {
                    re.eval("r316842<-round(r31684/4)");
                    re.eval("r316843<-r31684-r316842");
                    re.eval("if(gen31684[r316842,3]==gen31684[r316842+1,3]){suba<-gen31684[r316842,3]-gen31684[r316842-1,3];subb<-gen31684[r316842+1,3]-gen31684[r316842+2,3];if(abs(suba)>abs(subb)){gen31684.up<-gen31684[1:r316842,];}else{gen31684.up<-gen31684[1:(r316842+1),];}}else{gen31684.up<-gen31684[1:r316842,];}");
                    re.eval("if(gen31684[r316843+1,3]==gen31684[r316843,3]){suba<-gen31684[r316843+1,3]-gen31684[r316843+2,3];subb<-gen31684[r316843,3]-gen31684[r316843-1,3];if(abs(suba)>abs(subb)){gen31684.low<-gen31684[(r316843+2):r31684,];}else{gen31684.low<-gen31684[(r316843+1):r31684,];}}else{gen31684.low<-gen31684[(r316843+1):r31684,];}");
                }
                if (!strSql22.equals("0") && j32548 > 3) {
                    re.eval("r325482<-round(r32548/4)");
                    re.eval("r325483<-r32548-r325482");
                    re.eval("if(gen32548[r325482,3]==gen32548[r325482+1,3]){suba<-gen32548[r325482,3]-gen32548[r325482-1,3];subb<-gen32548[r325482+1,3]-gen32548[r325482+2,3];if(abs(suba)>abs(subb)){gen32548.up<-gen32548[1:r325482,];}else{gen32548.up<-gen32548[1:(r325482+1),];}}else{gen32548.up<-gen32548[1:r325482,];}");
                    re.eval("if(gen32548[r325483+1,3]==gen32548[r325483,3]){suba<-gen32548[r325483+1,3]-gen32548[r325483+2,3];subb<-gen32548[r325483,3]-gen32548[r325483-1,3];if(abs(suba)>abs(subb)){gen32548.low<-gen32548[(r325483+2):r32548,];}else{gen32548.low<-gen32548[(r325483+1):r32548,];}}else{gen32548.low<-gen32548[(r325483+1):r32548,];}");
                }
                if (!strSql31.equals("0") && j48075 > 3) {
                    re.eval("r480752<-round(r48075/4)");
                    re.eval("r480753<-r48075-r480752");
                    re.eval("if(gen48075[r480752,3]==gen48075[r480752+1,3]){suba<-gen48075[r480752,3]-gen48075[r480752-1,3];subb<-gen48075[r480752+1,3]-gen48075[r480752+2,3];if(abs(suba)>abs(subb)){gen48075.up<-gen48075[1:r480752,];}else{gen48075.up<-gen48075[1:(r480752+1),];}}else{gen48075.up<-gen48075[1:r480752,];}");
                    re.eval("if(gen48075[r480753+1,3]==gen48075[r480753,3]){suba<-gen48075[r480753+1,3]-gen48075[r480753+2,3];subb<-gen48075[r480753,3]-gen48075[r480753-1,3];if(abs(suba)>abs(subb)){gen48075.low<-gen48075[(r480753+2):r48075,];}else{gen48075.low<-gen48075[(r480753+1):r48075,];}}else{gen48075.low<-gen48075[(r480753+1):r48075,];}");
                }
                if (!strSql32.equals("0") && j48276 > 3) {
                    re.eval("r482762<-round(r48276/4)");
                    re.eval("r482763<-r48276-r482762");
                    re.eval("if(gen48276[r482762,3]==gen48276[r482762+1,3]){suba<-gen48276[r482762,3]-gen48276[r482762-1,3];subb<-gen48276[r482762+1,3]-gen48276[r482762+2,3];if(abs(suba)>abs(subb)){gen48276.up<-gen48276[1:r482762,];}else{gen48276.up<-gen48276[1:(r482762+1),];}}else{gen48276.up<-gen48276[1:r482762,];}");
                    re.eval("if(gen48276[r482763+1,3]==gen48276[r482763,3]){suba<-gen48276[r482763+1,3]-gen48276[r482763+2,3];subb<-gen48276[r482763,3]-gen48276[r482763-1,3];if(abs(suba)>abs(subb)){gen48276.low<-gen48276[(r482763+2):r48276,];}else{gen48276.low<-gen48276[(r482763+1):r48276,];}}else{gen48276.low<-gen48276[(r482763+1):r48276,];}");
                }
            } else if (strSplit.equals("6")) {
                if (k > 3) {
                    re.eval("r1<-round(r*0.3)");
                    re.eval("r2<-r-r1");
                    re.eval("if(gen[r1,3]==gen[r1+1,3]){suba<-gen[r1,3]-gen[r1-1,3];subb<-gen[r1+1,3]-gen[r1+2,3];if(abs(suba)>abs(subb)){gen.up<-gen[1:r1,];}else{gen.up<-gen[1:(r1+1),];}}else{gen.up<-gen[1:r1,];}");
                    re.eval("if(gen[r2+1,3]==gen[r2,3]){suba<-gen[r2+1,3]-gen[r2+2,3];subb<-gen[r2,3]-gen[r2-1,3];if(abs(suba)>abs(subb)){gen.low<-gen[(r2+2):r,];}else{gen.low<-gen[(r2+1):r,];}}else{gen.low<-gen[(r2+1):r,];}");
                }
                if (!strSql11.equals("0") && j13507 > 3) {
                    re.eval("r135072<-round(r13507*0.3)");
                    re.eval("r135073<-r13507-r135072");
                    re.eval("if(gen13507[r135072,3]==gen13507[r135072+1,3]){suba<-gen13507[r135072,3]-gen13507[r135072-1,3];subb<-gen13507[r135072+1,3]-gen13507[r135072+2,3];if(abs(suba)>abs(subb)){gen13507.up<-gen13507[1:r135072,];}else{gen13507.up<-gen13507[1:(r135072+1),];}}else{gen13507.up<-gen13507[1:r135072,];}");
                    re.eval("if(gen13507[r135073+1,3]==gen13507[r135073,3]){suba<-gen13507[r135073+1,3]-gen13507[r135073+2,3];subb<-gen13507[r135073,3]-gen13507[r135073-1,3];if(abs(suba)>abs(subb)){gen13507.low<-gen13507[(r135073+2):r13507,];}else{gen13507.low<-gen13507[(r135073+1):r13507,];}}else{gen13507.low<-gen13507[(r135073+1):r13507,];}");
                }
                if (!strSql12.equals("0") && j19915 > 3) {
                    re.eval("r199152<-round(r19915*0.3)");
                    re.eval("r199153<-r19915-r199152");
                    re.eval("if(gen19915[r199152,3]==gen19915[r199152+1,3]){suba<-gen19915[r199152,3]-gen19915[r199152-1,3];subb<-gen19915[r199152+1,3]-gen19915[r199152+2,3];if(abs(suba)>abs(subb)){gen19915.up<-gen19915[1:r199152,];}else{gen19915.up<-gen19915[1:(r199152+1),];}}else{gen19915.up<-gen19915[1:r199152,];}");
                    re.eval("if(gen19915[r199153+1,3]==gen19915[r199153,3]){suba<-gen19915[r199153+1,3]-gen19915[r199153+2,3];subb<-gen19915[r199153,3]-gen19915[r199153-1,3];if(abs(suba)>abs(subb)){gen19915.low<-gen19915[(r199153+2):r19915,];}else{gen19915.low<-gen19915[(r199153+1):r19915,];}}else{gen19915.low<-gen19915[(r199153+1):r19915,];}");
                }
                if (!strSql21.equals("0") && j31684 > 3) {
                    re.eval("r316842<-round(r31684*0.3)");
                    re.eval("r316843<-r31684-r316842");
                    re.eval("if(gen31684[r316842,3]==gen31684[r316842+1,3]){suba<-gen31684[r316842,3]-gen31684[r316842-1,3];subb<-gen31684[r316842+1,3]-gen31684[r316842+2,3];if(abs(suba)>abs(subb)){gen31684.up<-gen31684[1:r316842,];}else{gen31684.up<-gen31684[1:(r316842+1),];}}else{gen31684.up<-gen31684[1:r316842,];}");
                    re.eval("if(gen31684[r316843+1,3]==gen31684[r316843,3]){suba<-gen31684[r316843+1,3]-gen31684[r316843+2,3];subb<-gen31684[r316843,3]-gen31684[r316843-1,3];if(abs(suba)>abs(subb)){gen31684.low<-gen31684[(r316843+2):r31684,];}else{gen31684.low<-gen31684[(r316843+1):r31684,];}}else{gen31684.low<-gen31684[(r316843+1):r31684,];}");
                }
                if (!strSql22.equals("0") && j32548 > 3) {
                    re.eval("r325482<-round(r32548*0.3)");
                    re.eval("r325483<-r32548-r325482");
                    re.eval("if(gen32548[r325482,3]==gen32548[r325482+1,3]){suba<-gen32548[r325482,3]-gen32548[r325482-1,3];subb<-gen32548[r325482+1,3]-gen32548[r325482+2,3];if(abs(suba)>abs(subb)){gen32548.up<-gen32548[1:r325482,];}else{gen32548.up<-gen32548[1:(r325482+1),];}}else{gen32548.up<-gen32548[1:r325482,];}");
                    re.eval("if(gen32548[r325483+1,3]==gen32548[r325483,3]){suba<-gen32548[r325483+1,3]-gen32548[r325483+2,3];subb<-gen32548[r325483,3]-gen32548[r325483-1,3];if(abs(suba)>abs(subb)){gen32548.low<-gen32548[(r325483+2):r32548,];}else{gen32548.low<-gen32548[(r325483+1):r32548,];}}else{gen32548.low<-gen32548[(r325483+1):r32548,];}");
                }
                if (!strSql31.equals("0") && j48075 > 3) {
                    re.eval("r480752<-round(r48075*0.3)");
                    re.eval("r480753<-r48075-r480752");
                    re.eval("if(gen48075[r480752,3]==gen48075[r480752+1,3]){suba<-gen48075[r480752,3]-gen48075[r480752-1,3];subb<-gen48075[r480752+1,3]-gen48075[r480752+2,3];if(abs(suba)>abs(subb)){gen48075.up<-gen48075[1:r480752,];}else{gen48075.up<-gen48075[1:(r480752+1),];}}else{gen48075.up<-gen48075[1:r480752,];}");
                    re.eval("if(gen48075[r480753+1,3]==gen48075[r480753,3]){suba<-gen48075[r480753+1,3]-gen48075[r480753+2,3];subb<-gen48075[r480753,3]-gen48075[r480753-1,3];if(abs(suba)>abs(subb)){gen48075.low<-gen48075[(r480753+2):r48075,];}else{gen48075.low<-gen48075[(r480753+1):r48075,];}}else{gen48075.low<-gen48075[(r480753+1):r48075,];}");
                }
                if (!strSql32.equals("0") && j48276 > 3) {
                    re.eval("r482762<-round(r48276*0.3)");
                    re.eval("r482763<-r48276-r482762");
                    re.eval("if(gen48276[r482762,3]==gen48276[r482762+1,3]){suba<-gen48276[r482762,3]-gen48276[r482762-1,3];subb<-gen48276[r482762+1,3]-gen48276[r482762+2,3];if(abs(suba)>abs(subb)){gen48276.up<-gen48276[1:r482762,];}else{gen48276.up<-gen48276[1:(r482762+1),];}}else{gen48276.up<-gen48276[1:r482762,];}");
                    re.eval("if(gen48276[r482763+1,3]==gen48276[r482763,3]){suba<-gen48276[r482763+1,3]-gen48276[r482763+2,3];subb<-gen48276[r482763,3]-gen48276[r482763-1,3];if(abs(suba)>abs(subb)){gen48276.low<-gen48276[(r482763+2):r48276,];}else{gen48276.low<-gen48276[(r482763+1):r48276,];}}else{gen48276.low<-gen48276[(r482763+1):r48276,];}");
                }

            } else if (strSplit.equals("8")) {
                if (k > 3) {
                    re.eval("r1<-round(r/3)");
                    re.eval("r2<-r1*2");
                    re.eval("r3<-r-r2");
                    re.eval("gen.up<-gen[1:r1,];gen.mdi<-gen[(r1+1):r2,];gen.low<-gen[(r2+1):r3,]");
                }
                if (!strSql11.equals("0") && j13507 > 3) {
                    re.eval("r135071<-round(r13507/3)");
                    re.eval("r135072<-r135071*2");
                    re.eval("r135073<-r13507-r135072");
                    re.eval("gen13507.up<-gen13507[1:r135071,];gen13507.mdi<-gen13507[(r135071+1):r135072,];gen13507.low<-gen13507[(r135072+1):r135073,]");
                }
                if (!strSql12.equals("0") && j19915 > 3) {
                    re.eval("r199151<-round(r19915/3)");
                    re.eval("r199152<-r199151*2");
                    re.eval("r199153<-r19915-r199152");
                    re.eval("gen19915.up<-gen19915[1:r199151,];gen19915.mdi<-gen19915[(r199151+1):r199152,];gen19915.low<-gen19915[(r199152+1):r199153,]");
                }
                if (!strSql21.equals("0") && j31684 > 3) {
                    re.eval("r316841<-round(r31684/3)");
                    re.eval("r316842<-r316841*2");
                    re.eval("r316843<-r31684-r316842");
                    re.eval("gen31684.up<-gen31684[1:r316841,];gen31684.mdi<-gen31684[(r316841+1):r316842,];gen31684.low<-gen31684[(r316842+1):r316843,]");
                }
                if (!strSql22.equals("0") && j32548 > 3) {
                    re.eval("r325481<-round(r32548/3)");
                    re.eval("r325482<-r325481*2");
                    re.eval("r325483<-r32548-r325482");
                    re.eval("gen32548.up<-gen32548[1:r325481,];gen32548.mdi<-gen32548[(r325481+1):r325482,];gen32548.low<-gen32548[(r325482+1):r325483,]");
                }
                if (!strSql31.equals("0") && j48075 > 3) {
                    re.eval("r480751<-round(r48075/3)");
                    re.eval("r480752<-r480751*2");
                    re.eval("r480753<-r48075-r480752");
                    re.eval("gen48075.up<-gen48075[1:r480751,];gen48075.mdi<-gen48075[(r480751+1):r480752,];gen48075.low<-gen48075[(r480752+1):r480753,]");
                }
                if (!strSql32.equals("0") && j48276 > 3) {
                    re.eval("r482761<-round(r48276/3)");
                    re.eval("r482762<-r482761*2");
                    re.eval("r482763<-r48276-r482762");
                    re.eval("gen48276.up<-gen48276[1:r482761,];gen48276.mdi<-gen48276[(r482761+1):r482762,];gen48276.low<-gen48276[(r482762+1):r482763,]");
                }
            } else if (strSplit.equals("9")) {
                if (k > 3) {
                    re.eval("r1<-round(r/4)");
                    re.eval("r2<-r1*2");
                    re.eval("r3<-r1*3");
                    re.eval("r4<-r-r3");
                    re.eval("gen.up<-gen[1:r1,];gen.mu<-gen[(r1+1):r2,];gen.ml<-gen[(r2+1):r3,];gen.low<-gen[(r3+1):r4,]");
                }
                if (!strSql11.equals("0") && j13507 > 3) {
                    re.eval("r135071<-round(r13507/4)");
                    re.eval("r135072<-r135071*2");
                    re.eval("r135073<-r135071*3");
                    re.eval("r135074<-r13507-r135073");
                    re.eval("gen13507.up<-gen13507[1:r135071,];gen13507.mu<-gen13507[(r135071+1):r135072,];gen13507.ml<-gen13507[(r135072+1):r135073,];gen13507.low<-gen13507[(r135073+1):r135074,]");
                }
                if (!strSql12.equals("0") && j19915 > 3) {
                    re.eval("r199151<-round(r19915/4)");
                    re.eval("r199152<-r199151*2");
                    re.eval("r199153<-r199151*3");
                    re.eval("r199154<-r19915-r3");
                    re.eval("gen19915.up<-gen19915[1:r199151,];gen19915.mu<-gen19915[(r199151+1):r199152,];gen19915.ml<-gen19915[(r199152+1):r199153,];gen19915.low<-gen19915[(r199153+1):r199154,]");
                }
                if (!strSql21.equals("0") && j31684 > 3) {
                    re.eval("r316841<-round(r31684/4)");
                    re.eval("r316842<-r316841*2");
                    re.eval("r316843<-r316841*3");
                    re.eval("r316844<-r31684-r3");
                    re.eval("gen31684.up<-gen31684[1:r316841,];gen31684.mu<-gen31684[(r316841+1):r316842,];gen31684.ml<-gen31684[(r316842+1):r316843,];gen31684.low<-gen31684[(r316843+1):r316844,]");
                }
                if (!strSql22.equals("0") && j32548 > 3) {
                    re.eval("r325481<-round(r32548/4)");
                    re.eval("r325482<-r325481*2");
                    re.eval("r325483<-r325481*3");
                    re.eval("r325484<-r32548-r3");
                    re.eval("gen32548.up<-gen32548[1:r325481,];gen32548.mu<-gen32548[(r325481+1):r325482,];gen32548.ml<-gen32548[(r325482+1):r325483,];gen32548.low<-gen32548[(r325483+1):r325484,]");
                }
                if (!strSql31.equals("0") && j48075 > 3) {
                    re.eval("r480751<-round(r48075/4)");
                    re.eval("r480752<-r480751*2");
                    re.eval("r480753<-r480751*3");
                    re.eval("r480754<-r48075-r3");
                    re.eval("gen48075.up<-gen48075[1:r480751,];gen48075.mu<-gen48075[(r480751+1):r480752,];gen48075.ml<-gen48075[(r480752+1):r480753,];gen48075.low<-gen48075[(r480753+1):r480754,]");
                }
                if (!strSql32.equals("0") && j48276 > 3) {
                    re.eval("r482761<-round(r48276/4)");
                    re.eval("r482762<-r482761*2");
                    re.eval("r482763<-r482761*3");
                    re.eval("r482764<-r48276-r3");
                    re.eval("gen48276.up<-gen48276[1:r482761,];gen48276.mu<-gen48276[(r482761+1):r482762,];gen48276.ml<-gen48276[(r482762+1):r482763,];gen48276.low<-gen48276[(r482763+1):r482764,]");
                }
            }


            if (strSplit.equals("8")) {
                if (k > 3) {
                    re.eval("gen.up[,3]=0");
                    re.eval("gen.mdi[,3]=1");
                    re.eval("gen.low[,3]=2");
                    re.eval("gen.up<-rbind(gen.up,gen.up)");
                    re.eval("gen.mdi<-rbind(gen.mdi,gen.mdi)");
                    re.eval("gen.low<-rbind(gen.low,gen.low)");
                }

                if (!strSql11.equals("0") && j13507 > 3) {
                    re.eval("gen13507.up[,3]=0");
                    re.eval("gen13507.mdi[,3]=1");
                    re.eval("gen13507.low[,3]=2");
                    re.eval("gen.up<-gen13507.up");
                    re.eval("gen.mdi<-gen13507.mdi");
                    re.eval("gen.low<-gen13507.low");
                }
                if (!strSql12.equals("0") && j19915 > 3) {
                    re.eval("gen19915.up[,3]=0");
                    re.eval("gen19915.mdi[,3]=1");
                    re.eval("gen19915.low[,3]=2");
                    re.eval("gen.up<-rbind(gen.up,gen19915.up)");
                    re.eval("gen.mdi<-rbind(gen.mdi,gen19915.mdi)");
                    re.eval("gen.low<-rbind(gen.low,gen19915.low)");
                }
                if (!strSql21.equals("0") && j31684 > 3) {
                    re.eval("gen31684.up[,3]=0");
                    re.eval("gen31684.mdi[,3]=1");
                    re.eval("gen31684.low[,3]=2");
                    re.eval("gen.up<-rbind(gen.up,gen31684.up)");
                    re.eval("gen.mdi<-rbind(gen.mdi,gen31684.mdi)");
                    re.eval("gen.low<-rbind(gen.low,gen31684.low)");
                }
                if (!strSql22.equals("0") && j32548 > 3) {
                    re.eval("gen32548.up[,3]=0");
                    re.eval("gen32548.mdi[,3]=1");
                    re.eval("gen32548.low[,3]=2");
                    re.eval("gen.up<-rbind(gen.up,gen32548.up)");
                    re.eval("gen.mdi<-rbind(gen.mdi,gen32548.mdi)");
                    re.eval("gen.low<-rbind(gen.low,gen32548.low)");
                }
                if (!strSql31.equals("0") && j48075 > 3) {
                    re.eval("gen48075.up[,3]=0");
                    re.eval("gen48075.mdi[,3]=1");
                    re.eval("gen48075.low[,3]=2");
                    re.eval("gen.up<-rbind(gen.up,gen48075.up)");
                    re.eval("gen.mdi<-rbind(gen.mdi,gen48075.mdi)");
                    re.eval("gen.low<-rbind(gen.low,gen48075.low)");
                }
                if (!strSql32.equals("0") && j48276 > 3) {
                    re.eval("gen48276.up[,3]=0");
                    re.eval("gen48276.mdi[,3]=1");
                    re.eval("gen48276.low[,3]=2");
                    re.eval("gen.up<-rbind(gen.up,gen48276.up)");
                    re.eval("gen.mdi<-rbind(gen.mdi,gen48276.mdi)");
                    re.eval("gen.low<-rbind(gen.low,gen48276.low)");
                }
                re.eval("gen<-rbind(gen.up,gen.mdi)");
                re.eval("gen<-rbind(gen,gen.low)");
            } else if (strSplit.equals("9")) {
                if (k > 3) {
                    re.eval("gen.up[,3]=0");
                    re.eval("gen.mu[,3]=1");
                    re.eval("gen.ml[,3]=2");
                    re.eval("gen.low[,3]=3");
                    re.eval("gen.up<-rbind(gen.up,gen.up)");
                    re.eval("gen.mu<-rbind(gen.mu,gen.mu)");
                    re.eval("gen.ml<-rbind(gen.ml,gen.ml)");
                    re.eval("gen.low<-rbind(gen.low,gen.low)");
                }

                if (!strSql11.equals("0") && j13507 > 3) {
                    re.eval("gen13507.up[,3]=0");
                    re.eval("gen13507.mu[,3]=1");
                    re.eval("gen13507.ml[,3]=2");
                    re.eval("gen13507.low[,3]=3");
                    re.eval("gen.up<-rbind(gen.up,gen13507.up)");
                    re.eval("gen.mu<-rbind(gen.mu,gen13507.mu)");
                    re.eval("gen.ml<-rbind(gen.ml,gen13507.ml)");
                    re.eval("gen.low<-rbind(gen.low,gen13507.low)");
                }
                if (!strSql12.equals("0") && j19915 > 3) {
                    re.eval("gen19915.up[,3]=0");
                    re.eval("gen19915.mu[,3]=1");
                    re.eval("gen19915.ml[,3]=2");
                    re.eval("gen19915.low[,3]=3");
                    re.eval("gen.up<-rbind(gen.up,gen19915.up)");
                    re.eval("gen.mu<-rbind(gen.mu,gen19915.mu)");
                    re.eval("gen.ml<-rbind(gen.ml,gen19915.ml)");
                    re.eval("gen.low<-rbind(gen.low,gen19915.low)");
                }
                if (!strSql21.equals("0") && j31684 > 3) {
                    re.eval("gen31684.up[,3]=0");
                    re.eval("gen31684.mu[,3]=1");
                    re.eval("gen31684.ml[,3]=2");
                    re.eval("gen31684.low[,3]=3");
                    re.eval("gen.up<-rbind(gen.up,gen31684.up)");
                    re.eval("gen.mu<-rbind(gen.mu,gen31684.mu)");
                    re.eval("gen.ml<-rbind(gen.ml,gen31684.ml)");
                    re.eval("gen.low<-rbind(gen.low,gen31684.low)");
                }
                if (!strSql22.equals("0") && j32548 > 3) {
                    re.eval("gen32548.up[,3]=0");
                    re.eval("gen32548.mu[,3]=1");
                    re.eval("gen32548.ml[,3]=2");
                    re.eval("gen32548.low[,3]=3");
                    re.eval("gen.up<-rbind(gen.up,gen32548.up)");
                    re.eval("gen.mu<-rbind(gen.mu,gen32548.mu)");
                    re.eval("gen.ml<-rbind(gen.ml,gen32548.ml)");
                    re.eval("gen.low<-rbind(gen.low,gen32548.low)");
                }
                if (!strSql31.equals("0") && j48075 > 3) {
                    re.eval("gen48075.up[,3]=0");
                    re.eval("gen48075.mu[,3]=1");
                    re.eval("gen48075.ml[,3]=2");
                    re.eval("gen48075.low[,3]=3");
                    re.eval("gen.up<-rbind(gen.up,gen48075.up)");
                    re.eval("gen.mu<-rbind(gen.mu,gen48075.mu)");
                    re.eval("gen.ml<-rbind(gen.ml,gen48075.ml)");
                    re.eval("gen.low<-rbind(gen.low,gen48075.low)");
                }
                if (!strSql32.equals("0") && j48276 > 3) {
                    re.eval("gen48276.up[,3]=0");
                    re.eval("gen48276.mu[,3]=1");
                    re.eval("gen48276.ml[,3]=2");
                    re.eval("gen48276.low[,3]=3");
                    re.eval("gen.up<-rbind(gen.up,gen48276.up)");
                    re.eval("gen.mu<-rbind(gen.mu,gen48276.mu)");
                    re.eval("gen.ml<-rbind(gen.ml,gen48276.ml)");
                    re.eval("gen.low<-rbind(gen.low,gen48276.low)");
                }

                re.eval("gen<-rbind(gen.up,gen.mu)");
                re.eval("gen<-rbind(gen,gen.ml)");
                re.eval("gen<-rbind(gen,gen.low)");
            } else {
                if (k > 3) {
                    re.eval("gen.up[,3]=1");
                    re.eval("gen.low[,3]=0");
                }

                if (!strSql11.equals("0") && j13507 > 3) {
                    re.eval("gen13507.up[,3]=1");
                    re.eval("gen13507.low[,3]=0");
                }
                if (!strSql12.equals("0") && j19915 > 3) {
                    re.eval("gen19915.up[,3]=1");
                    re.eval("gen19915.low[,3]=0");
                }
                if (!strSql21.equals("0") && j31684 > 3) {
                    re.eval("gen31684.up[,3]=1");
                    re.eval("gen31684.low[,3]=0");
                }
                if (!strSql22.equals("0") && j32548 > 3) {
                    re.eval("gen32548.up[,3]=1");
                    re.eval("gen32548.low[,3]=0");
                }
                if (!strSql31.equals("0") && j48075 > 3) {
                    re.eval("gen48075.up[,3]=1");
                    re.eval("gen48075.low[,3]=0");
                }
                if (!strSql32.equals("0") && j48276 > 3) {
                    re.eval("gen48276.up[,3]=1");
                    re.eval("gen48276.low[,3]=0");
                }

                if (!strSql11.equals("0") && j13507 > 3) {
                    re.eval("gen.up<-rbind(gen.up,gen13507.up)");
                    re.eval("gen.low<-rbind(gen.low,gen13507.low)");
                }
                if (!strSql12.equals("0") && j19915 > 3) {
                    re.eval("gen.up<-rbind(gen.up,gen19915.up)");
                    re.eval("gen.low<-rbind(gen.low,gen19915.low)");
                }
                if (!strSql21.equals("0") && j31684 > 3) {
                    re.eval("gen.up<-rbind(gen.up,gen31684.up)");
                    re.eval("gen.low<-rbind(gen.low,gen31684.low)");
                }
                if (!strSql22.equals("0") && j32548 > 3) {
                    re.eval("gen.up<-rbind(gen.up,gen32548.up)");
                    re.eval("gen.low<-rbind(gen.low,gen32548.low)");
                }
                if (!strSql31.equals("0") && j48075 > 3) {
                    re.eval("gen.up<-rbind(gen.up,gen48075.up)");
                    re.eval("gen.low<-rbind(gen.low,gen48075.low)");
                }
                if (!strSql32.equals("0") && j48276 > 3) {
                    re.eval("gen.up<-rbind(gen.up,gen48276.up)");
                    re.eval("gen.low<-rbind(gen.low,gen48276.low)");
                }
                re.eval("gen<-rbind(gen.up,gen.low)");
            }
            re.eval("resmax<-gen");
            re.eval("gmax<-resmax[order(-resmax[,1]),]");
            re.eval("gen.surv<-survfit(Surv(gen[,1],gen[,2])~gen[,3])");
            re.eval("gen.dif<-survdiff(Surv(gen[,1],gen[,2])~gen[,3])");
            re.eval("p.cox<-summary(coxph(Surv(gen[,1],gen[,2])~gen[,3]))");
            re.eval("p.val<-p.cox$coefficients[5]");
            re.eval("p.hr<-p.cox$coefficients[2]");
            re.eval("p.l<-p.cox$conf.int[3]");
            re.eval("p.t<-p.cox$conf.int[4]");
            if (strSplit.equals("1") || strSplit.equals("3") || strSplit.equals("4") || strSplit.equals("5") || strSplit.equals("6")) {
                //re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"green\",\"red\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \"), legend.labs = c(\""+strLab2+"\", \""+strLab1+"\"),legend = c(0.85, 0.9))");
                re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"green\",\"red\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\"" + strLab2 + "\", \"" + strLab1 + "\"),legend = \"none\")");//legend = c(0.85, 0.9)
                re.eval("ggsurv$table <- ggsurv$table + theme(axis.text.y = element_text(colour='black'))");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.9, y = 0.97,label = \"" + strLab1 + "\", size = 4,color='red')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.9, y = 0.92,label = \"" + strLab2 + "\", size = 4,color='green')");
            } else if (strSplit.equals("8")) {
                re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"red\",\"black\",\"green\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\"" + strLab1 + "\", \"" + strLab2 + "\",\"" + strLab3 + "\"),legend = 'none')");
                re.eval("ggsurv$table <- ggsurv$table + theme(axis.text.y = element_text(colour='black'))");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.9+1, y = 0.97,label = \"" + strLab1 + "\", size = 4,color='red')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.9+4, y = 0.92,label = \"" + strLab2 + "\", size = 4,color='black')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.9, y = 0.87,label = \"" + strLab3 + "\", size = 4,color='green')");
            } else if (strSplit.equals("9")) {
                re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"red\",\"black\",\"#E7B800\",\"green\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\"" + strLab1 + "\", \"" + strLab2 + "\",\"" + strLab3 + "\",\"" + strLab4 + "\"),legend = 'none')");
                re.eval("ggsurv$table <- ggsurv$table + theme(axis.text.y = element_text(colour='black'))");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.9, y = 0.97,label = \"" + strLab1 + "\", size = 4,color='red')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.9+4, y = 0.92,label = \"" + strLab2 + "\", size = 4,color='black')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.9, y = 0.87,label = \"" + strLab3 + "\", size = 4,color='#E7B800')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.9+3, y = 0.82,label = \"" + strLab4 + "\", size = 4,color='green')");
            } else {
                //re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"green\",\"red\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\""+strLab2+"\", \""+strLab1+"\"),legend = c(0.85, 0.9))");
                re.eval("ggsurv<-ggsurvplot(gen.surv,data=gen,risk.table=TRUE,xlab=\"Months\",palette=c(\"red\",\"green\"),main=\"Survival curve\",font.main = c(16, \"bold\",\"darkblue\"),font.x = c(16, \"bold\", \"black\"),font.y = c(16, \"bold\", \"black\"),font.tickslab = c(16, \"plain\", \"black\"),legend.title=\" \", legend.labs = c(\"" + strLab1 + "\", \"" + strLab2 + "\"),legend = \"none\")");
                re.eval("ggsurv$table <- ggsurv$table + theme(axis.text.y = element_text(colour='black'))");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.9, y = 0.97,label = \"" + strLab1 + "\", size = 4,color='green')");
                re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.9, y = 0.92,label = \"" + strLab2 + "\", size = 4,color='red')");
            }
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12, y = 0.15,label = paste(\"p=\",round(p.val,4)), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.12+2, y = 0.09,label = paste(\"HR=\",round(p.hr,4)), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.26, y = 0.03,label = paste('( 95%CI,',round(p.l,4),'-',round(p.t,4),')'), size = 5)");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.9, y = 0.05,label = \"OSblca\", size = 5,fontface = 'italic')");
            re.eval("ggsurv$plot <- ggsurv$plot+ ggplot2::annotate(\"text\", x = gmax[1,1]*0.5, y = 0.95,label = \"Combined:" + strGen + "\", size = 5)");
            re.eval("print(ggsurv)");
            re.eval("i = Cairo:::.image(dev.cur())");
            re.eval("r = Cairo:::.ptr.to.raw(i$ref, 0, i$width * i$height * 4)");
            re.eval("dim(r) = c(4, i$width, i$height)");
            re.eval("r[c(1,3),,] = r[c(3,1),,]");
            bytes = re.eval("writePNG(r, raw())").asBytes();
            //re.eval("ggsave(file = \""+strPath+"\", print(ggsurv))");
        } catch (REngineException e) {
            //e.printStackTrace();
        } finally {
            re.close();
        }
        return new RPlot(bytes);
    }
}