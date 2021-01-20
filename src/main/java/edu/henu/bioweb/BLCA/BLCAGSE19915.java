package edu.henu.bioweb.BLCA;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.henu.bioweb.GetPathReturn;
import edu.henu.bioweb.RPlot;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import edu.henu.bioweb.Common.BLCACom;
import edu.henu.bioweb.Common.ComFun;

public class BLCAGSE19915   extends HttpServlet{

	public BLCAGSE19915()
	{
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	String strTip = " ";
	String GetFileName()
	{
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String he = dateFormat.format(now); 
		Random ra =new Random();
		return he+ ra.nextInt(1000);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GetPathReturn pathReturn = null;
		String strPath = "";
		RPlot strPath2 = new RPlot();
		String strSurvival = "";
		String strtnm = "";
		String strSplit = "";
		String strGen = "";
		String fileName = "";
		String strHistological="";
		String strGenList = "-1";
		boolean isPic = false;
		String[] strPaths;
		RPlot[] strPaths2 = null;
		request.setCharacterEncoding("UTF-8");
		BLCACom com = new BLCACom();
		ComFun fun = new ComFun();
		try 
		{
			strSurvival = request.getParameter("ddlSurvival");
			//strGenList=request.getParameter("mulGen");
			strtnm = request.getParameter("tnmstage");
			strSplit = request.getParameter("ddlSplit");
			strGen = request.getParameter("txtGen");
			strHistological=  request.getParameter("ddlHistological");
			if(!strGen.equals("") && strGen != null){
				strGen = strGen.toUpperCase();
			}
			if(!fun.isGetgenName(strGen))
			{
				strTip=fun.GetTip(4, "GSE19915", strGen, "BLCA");
				request.setAttribute("txtGen", strGen);
				request.setAttribute("ddlSurvival", strSurvival);
				request.setAttribute("ddlSplit", strSplit);
				request.setAttribute("tnmstage", strtnm);
				request.setAttribute("ddlHistological", strHistological);
				request.setAttribute("tip", strTip);
				request.getRequestDispatcher("/BLCA/BLCA_GSE19915.jsp").forward(request, response);
				return;
			}
			if(!com.isExistByGene(strGen, "GSE19915")){
				strTip = fun.GetTip(3, "GSE19915", strGen, "BLCA");
			}
			String strSql = "";
			if(strGenList.equals("-1")){
				fileName = GetFileName() + "GSE19915" + strGen + strSurvival + strSplit;
				
				if(strSurvival.equals("3"))
				{
					strSql = "select DSS,DSS_Event,genValue from gen_GSE19915,sample_GSE19915 where genName='"+strGen+"' and gen_GSE19915.genGSM = sample_GSE19915.GSM and DSS_Event <>'' ";
				}
				if(!strtnm.equals("0"))
				{
					strSql += " and tnm='"+strtnm+"'";
					fileName += strtnm;
				}
				if(!strHistological.equals("0"))
				{
					strSql += " and histological_type='"+strHistological+"'";
					fileName += strHistological;
				}
				strPath = "d:/picture/" +fileName+".jpg";
				//strPath2="/picture/"+fileName+".jpg";
				pathReturn = com.GetPath(strSql, strSplit, strPath, strGen, strGen, strSurvival, "jdbc/SSBLCA", "OSblca","GSE19915");
				strTip=pathReturn.getStrTip();
				strPath2 = pathReturn.getPlotBytes();
				//isPic = GetPath(strSql, strSplit, strPath, strGen, strSurvival);		
	            
			}
			else{
				String[] arrGen = strGenList.split("\\;");
				strPaths = new String[arrGen.length];
				strPaths2 = new RPlot[arrGen.length];
				String strSql2="";
				for(int i=0;i<arrGen.length;i++){
					arrGen[i] = arrGen[i].replaceAll("\n", "");
					arrGen[i] = arrGen[i].replaceAll("\r", "");
					//strGenIndex = getGenIndex(arrGen[i]);
					//strGenIndex = fun.getGenIndex(arrGen[i], "gen_GSE19915", "jdbc/SSBLCA");
					fileName = GetFileName() + "GSE19915" + arrGen[i] + strSurvival + strSplit;
					strPaths[i]="d:/picture/" +fileName+".jpg";
					//strPaths2[i]="/picture/"+fileName+".jpg";
					if(strSurvival.equals("3"))
					{
						strSql2 = "select DSS,DSS_Event,genValue from gen_GSE19915,sample_GSE19915 where genName='"+arrGen[i]+"' and gen_GSE19915.genGSM = sample_GSE19915.GSM and DSS_Event <>'' ";
					}
					if(!strtnm.equals("0"))
					{
						strSql2 += " and tnm='"+strtnm+"'";
						fileName += strtnm;
					}
					if(!strHistological.equals("0"))
					{
						strSql2 += " and histological_type='"+strHistological+"'";
						fileName += strHistological;
					}
					pathReturn = com.GetPath(strSql2, strSplit, strPaths[i], arrGen[i], arrGen[i], strSurvival, "jdbc/SSBLCA", "OSblca","GSE19915");
					strTip=pathReturn.getStrTip();
					strPaths2[i]= pathReturn.getPlotBytes();
					//GetPath(strSql2, strSplit, strPaths[i], arrGen[i], strSurvival);
				}
				request.setAttribute("probe", strPaths2);
			}			
		}
		catch (RserveException | REXPMismatchException e) {
			e.printStackTrace();
		}
		String error="";
		
		if(error!=""){			
			request.setAttribute("error", error);
			request.getRequestDispatcher("/BLCA/BLCA_GSE19915.jsp").forward(request, response);
		}else{						
			//if(isPic && strGenList.equals("-1"))
			//{
				request.setAttribute("myimg", strPath2.toString());//����
				//request.setAttribute("myimg", strPath);//����
			//}
			//else
			//{
				//request.setAttribute("myimg", "");//����
			//}
			request.setAttribute("txtGen", strGen);
			request.setAttribute("ddlSurvival", strSurvival);
			request.setAttribute("ddlSplit", strSplit);
			request.setAttribute("tnmstage", strtnm);
			request.setAttribute("ddlHistological", strHistological);
			request.setAttribute("tip", strTip);
			request.getRequestDispatcher("/BLCA/BLCA_GSE19915.jsp").forward(request, response);
		}
	}
}
