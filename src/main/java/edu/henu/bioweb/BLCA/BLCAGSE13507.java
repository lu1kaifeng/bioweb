package edu.henu.bioweb.BLCA;

import edu.henu.bioweb.Common.BLCACom;
import edu.henu.bioweb.Common.ComFun;
import edu.henu.bioweb.GetPathReturn;
import edu.henu.bioweb.RPlot;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RserveException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class BLCAGSE13507 extends HttpServlet {
    String strTip = " ";

    public BLCAGSE13507() {
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
        GetPathReturn pathReturn = null;
        String strPath = "";
        RPlot strPath2 = new RPlot();
        String strSurvival = "";
        String strtnm = "";
        String strSplit = "";
        String strGender = "";
        String strGen = "";
        String strData = "";
        String strHistological = "";
        String fileName = "";
        boolean isPic = false;
        request.setCharacterEncoding("UTF-8");


        strSurvival = request.getParameter("survival");
        //strGenList=request.getParameter("mulGen");//��ȡ������
        strtnm = request.getParameter("tnm");
        strSplit = request.getParameter("split");
        strGender = request.getParameter("gender");
        strHistological = request.getParameter("histological_type");
        strGen = request.getParameter("gene");
        String strSql = "";
        ComFun fun = new ComFun();
        BLCACom com = new BLCACom();
        if (!strGen.equals("") && strGen != null) {
            strGen = strGen.toUpperCase();
        }
        if (!fun.isGetgenName(strGen)) {
            strTip = fun.GetTip(4, "GSE13507", strGen, "BLCA");
            request.setAttribute("gene", strGen);
            request.setAttribute("survival", strSurvival);
            request.setAttribute("split", strSplit);
            request.setAttribute("tnm", strtnm);
            request.setAttribute("gender", strGender);
            request.setAttribute("histological_type", strHistological);
            request.setAttribute("tip", strTip);
            request.getRequestDispatcher("/BLCA/BLCA_GSE13507.jsp").forward(request, response);
            return;
        }
        String strSql2 = "";
        String[] stridrefs = null;//
        String[] strPaths = null;//new String[stridrefs.length];
        RPlot[] strPaths2 = null;//new String[stridrefs.length];
        String[] stridrefs2 = null;
        String[] strPaths11 = null;
        String[] strPaths22 = null;
        String strGenIndex = "";
        strGenIndex = fun.getGenIndex(strGen, "gen_GSE13507", "jdbc/SSBLCA");
        if (strGenIndex.equals("-1")) {
            strTip = fun.GetTip(3, "GSE13507", strGen, "BLCA");
            request.setAttribute("gene", strGen);
            request.setAttribute("survival", strSurvival);
            request.setAttribute("split", strSplit);
            request.setAttribute("tnm", strtnm);
            request.setAttribute("gender", strGender);
            request.setAttribute("histological_type", strHistological);
            request.setAttribute("tip", strTip);
            request.getRequestDispatcher("/BLCA/BLCA_GSE13507.jsp").forward(request, response);
            return;
        }
        stridrefs = fun.GetIDREFByName(strGen, strGenIndex, "gen_GSE13507", "jdbc/SSBLCA");
        strPaths = new String[stridrefs.length];
        strPaths2 = new RPlot[stridrefs.length];

        try {
            strSql2 = "";
            fileName = GetFileName() + "GSE13507" + strGen + strSurvival + strSplit;

            if (strSurvival.equals("0")) {
                //strSql = "select survival,death,avg(genValue) as genValue from gen_GES53625,sample_GES53625 where genName='"+strGen+"' and gen_GES53625.genGSM=sample_GES53625.GSM ";
                strSql = "select OS,OS_Event,avg(genValue) as genvalue from sample_GSE13507,gen_GSE13507_" + strGenIndex + " where genName='" + strGen + "' and gen_GSE13507_" + strGenIndex + ".genGSM = sample_GSE13507.GSM";
            }
            if (!strtnm.equals("0")) {
                strSql += " and tnm='" + strtnm + "'";
                fileName += strtnm;
            }
            if (!strGender.equals("2")) {
                strSql += " and gender='" + strGender + "'";
                fileName += strGender;
            }
            if (!strHistological.equals("0")) {
                strSql += " and histological_type='" + strHistological + "'";
                fileName += strHistological;
            }

            strSql += " group by sample_GSE13507.GSM,OS_Event,OS";

            strPath = "d:/picture/" + fileName + ".jpg";
            //strPath2="/picture/"+fileName+".jpg";

            pathReturn = com.GetPath(strSql, strSplit, strPath, strGen, strGen.toUpperCase() + ":Average", strSurvival, "jdbc/SSBLCA", "OSblca", "GSE13507");
            strTip = pathReturn.getStrTip();
            strPath2 = pathReturn.getPlotBytes();
            for (int i = 0; i < stridrefs.length; i++) {

                if (stridrefs[i] != null && !stridrefs[i].equals("")) {
                    fileName = GetFileName() + "GSE13507" + strGen + strSurvival + strSplit;

                    if (strSurvival.equals("0")) {
                        strSql2 = "select OS,OS_Event,genValue from gen_GSE13507_" + strGenIndex + ",sample_GSE13507 where idref='" + stridrefs[i] + "' and gen_GSE13507_" + strGenIndex + ".genGSM=sample_GSE13507.GSM ";
                        //strSql = "select os,death,genValue from sample_GSE39612,gen_GSE39612 where genName='"+strGen+"' and gen_GSE39612.genGSM = sample_GES53625.GSM";
                    }
                    if (!strtnm.equals("0")) {
                        strSql2 += " and tnm='" + strtnm + "'";
                        fileName += strtnm;
                    }
                    if (!strGender.equals("2")) {
                        strSql2 += " and gender='" + strGender + "'";
                        fileName += strGender;
                    }
                    if (!strHistological.equals("0")) {
                        strSql2 += " and histological_type='" + strHistological + "'";
                        fileName += strHistological;
                    }

                    strSql2 += " group by sample_GSE13507.GSM,OS_Event,OS,genValue";

                    strPaths[i] = "d:/picture/" + fileName + stridrefs[i] + ".jpg";
                    //strPaths2[i]="/picture/"+fileName+stridrefs[i]+".jpg";
                    pathReturn = com.GetPath(strSql2, strSplit, strPaths[i], strGen, strGen.toUpperCase() + " Probe:" + stridrefs[i], strSurvival, "jdbc/SSBLCA", "OSblca", "GSE13507");
                    strTip = pathReturn.getStrTip();
                    strPaths2[i] = pathReturn.getPlotBytes();

                }
            }
        } catch (RserveException | REXPMismatchException e) {
            e.printStackTrace();
        }
        //fileName = "GEO39612" + strGen + strSurvival + strSplit;

        String error = "";
        if (error != "") {
            request.setAttribute("error", error);
            request.getRequestDispatcher("/BLCA/BLCA_GSE13507.jsp").forward(request, response);
        } else {
            //if(isPic)
            //{
            request.setAttribute("myimg", strPath2.toString());//����
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
            request.setAttribute("gender", strGender);
            request.setAttribute("histological_type", strHistological);
            request.setAttribute("idref", stridrefs);
            //request.setAttribute("probe", strPaths);//����
            request.setAttribute("probe", strPaths2);//����
            request.setAttribute("tip", strTip);
            request.getRequestDispatcher("/BLCA/BLCA_GSE13507.jsp").forward(request, response);
        }
    }
}
