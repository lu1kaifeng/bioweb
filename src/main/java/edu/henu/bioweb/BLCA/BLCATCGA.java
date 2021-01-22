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

public class BLCATCGA  extends HttpServlet{

	public BLCATCGA()
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
		String strPath = "";
		RPlot strPath2 = new RPlot();
		GetPathReturn pathReturn= null;
		String strSurvival = "";
		String strlymph = "";
		String strtnm = "";
		String strSplit = "";
		String strSmoking = "";
		String strGender = "";
		String strGen = "";
		String fileName = "";
		String strRace= "";
		String strHistological="";
		String strGenList = "-1";
		boolean isPic = false;
		String[] strPaths;
		RPlot[] strPaths2 = null;
		String strGenIndex = "";
		request.setCharacterEncoding("UTF-8");
		BLCACom com = new BLCACom();
		ComFun fun = new ComFun();
		try 
		{
			strSurvival = request.getParameter("ddlSurvival");
			strlymph = request.getParameter("ddllymph");
			//strGenList=request.getParameter("mulGen");
			strtnm = request.getParameter("tnmstage");
			strSplit = request.getParameter("ddlSplit");
			strSmoking = request.getParameter("ddlTobacco");
			strGender = request.getParameter("ddlGender");
			strGen = request.getParameter("txtGen");
			strRace =  request.getParameter("ddlRace");
			strHistological=  request.getParameter("ddlHistological");
			if(!strGen.equals("") && strGen != null){
				strGen = strGen.toUpperCase();
			}
			if(!fun.isGetgenName(strGen))
			{
				strTip=fun.GetTip(4, "TCGA", strGen, "BLCA");
				request.setAttribute("txtGen", strGen);
				request.setAttribute("ddlSurvival", strSurvival);
				request.setAttribute("ddlSplit", strSplit);
				request.setAttribute("tnmstage", strtnm);
				request.setAttribute("ddlTobacco", strSmoking);
				request.setAttribute("ddlGender", strGender);
				request.setAttribute("ddllymph", strlymph);
				request.setAttribute("ddlHistological", strHistological);
				request.setAttribute("ddlRace", strRace);
				request.setAttribute("tip", strTip);
				request.getRequestDispatcher("/BLCA/BLCAList.jsp").forward(request, response);
	        	return;
			}
			
			String strSql = "";
			if(strGenList.equals("-1")){
				fileName = GetFileName() + "TCGA" + strGen + strSurvival + strSplit;
				strGenIndex = fun.getGenIndex(strGen, "gen_TCGA", "jdbc/SSBLCA");// getGenIndex(strGen);
				if(strGenIndex.equals("-1"))
	            {
					strTip=fun.GetTip(3, "TCGA", strGen, "BLCA");
					request.setAttribute("txtGen", strGen);
					request.setAttribute("ddlSurvival", strSurvival);
					request.setAttribute("ddlSplit", strSplit);
					request.setAttribute("tnmstage", strtnm);
					request.setAttribute("ddlTobacco", strSmoking);
					request.setAttribute("ddlGender", strGender);
					request.setAttribute("ddllymph", strlymph);
					request.setAttribute("ddlHistological", strHistological);
					request.setAttribute("ddlRace", strRace);
					request.setAttribute("tip", strTip);
					request.getRequestDispatcher("/BLCA/BLCAList.jsp").forward(request, response);
		        	return;
	            }
	            else
	            {
				if(strSurvival.equals("0"))
				{
					strSql = "select Round(OS/30.0,2) as OS,OS_Event,genValue from gen_TCGA_"+strGenIndex+",sample_TCGA where genName='"+strGen+"' and gen_TCGA_"+strGenIndex+".genGSM = sample_TCGA.GSM ";
				}
				else if(strSurvival.equals("1"))
				{
					strSql = "select Round(DFI/30.0,2) as DFI,DFI_Event,genValue from gen_TCGA_"+strGenIndex+",sample_TCGA where genName='"+strGen+"' and gen_TCGA_"+strGenIndex+".genGSM = sample_TCGA.GSM and DFI_Event <>'' ";
				}
				else if(strSurvival.equals("2"))
				{
					strSql = "select Round(PFI/30.0,2) as PFI,PFI_Event,genValue from gen_TCGA_"+strGenIndex+",sample_TCGA where genName='"+strGen+"' and gen_TCGA_"+strGenIndex+".genGSM = sample_TCGA.GSM and PFI_Event <>'' ";
				}
				else if(strSurvival.equals("3"))
				{
					strSql = "select Round(DSS/30.0,2) as DSS,DSS_Event,genValue from gen_TCGA_"+strGenIndex+",sample_TCGA where genName='"+strGen+"' and gen_TCGA_"+strGenIndex+".genGSM = sample_TCGA.GSM and DSS_Event <>'' ";
				}
				if(!strlymph.equals("2"))
				{
					strSql += " and lymph='"+strlymph+"'";
					fileName += strlymph;
				}
				if(!strtnm.equals("0"))
				{
					strSql += " and tnm='"+strtnm+"'";
					fileName += strtnm;
				}
				if(!strSmoking.equals("5"))
				{
					strSql += " and smoking='"+strSmoking+"'";
					fileName += strSmoking;
				}
				if(!strGender.equals("2"))
				{
					strSql += " and gender='"+strGender+"'";
					fileName += strGender;
				}
				if(!strHistological.equals("0"))
				{
					strSql += " and histological_type='"+strHistological+"'";
					fileName += strHistological;
				}
				if(!strRace.equals("3"))
				{
					strSql += " and race='"+strRace+"'";
					fileName += strRace;
				}
				strPath = "e:/picture/" +fileName+".jpg";
				//strPath2="/picture/"+fileName+".jpg";
				pathReturn = com.GetPath(strSql, strSplit, strPath, strGen, strGen, strSurvival, "jdbc/SSBLCA", "OSblca","TCGA");
				strTip= pathReturn.getStrTip();
				strPath2 = pathReturn.getPlotBytes();
				//isPic = GetPath(strSql, strSplit, strPath, strGen, strSurvival);		
	            }
			}
			else{
				//new []{"\r\n"},RemoveEmpty
				String[] arrGen = strGenList.split("\\;");
				strPaths = new String[arrGen.length];
				strPaths2 = new RPlot[arrGen.length];
				String strSql2="";
				for(int i=0;i<arrGen.length;i++){
					arrGen[i] = arrGen[i].replaceAll("\n", "");
					arrGen[i] = arrGen[i].replaceAll("\r", "");
					//strGenIndex = getGenIndex(arrGen[i]);
					strGenIndex = fun.getGenIndex(arrGen[i], "gen_TCGA", "jdbc/SSBLCA");
					fileName = GetFileName() + "TCGA" + arrGen[i] + strSurvival + strSplit;
					strPaths[i]="d:/picture/" +fileName+".jpg";
					//strPaths2[i]="/picture/"+fileName+".jpg";
					if(strSurvival.equals("0"))
					{
						strSql2 = "select Round(OS/30.0,2) as OS,OS_Event,genValue from gen_TCGA_"+strGenIndex+",sample_TCGA where genName='"+arrGen[i]+"' and gen_TCGA_"+strGenIndex+".genGSM = sample_TCGA.GSM ";
					}
					else if(strSurvival.equals("1"))
					{
						strSql2 = "select Round(DFI/30.0,2) as DFI,DFI_Event,genValue from gen_TCGA_"+strGenIndex+",sample_TCGA where genName='"+arrGen[i]+"' and gen_TCGA_"+strGenIndex+".genGSM = sample_TCGA.GSM and DFI_Event <>'' ";
					}
					else if(strSurvival.equals("2"))
					{
						strSql2 = "select Round(PFI/30.0,2) as PFI,PFI_Event,genValue from gen_TCGA_"+strGenIndex+",sample_TCGA where genName='"+arrGen[i]+"' and gen_TCGA_"+strGenIndex+".genGSM = sample_TCGA.GSM and PFI_Event <>'' ";
					}
					else if(strSurvival.equals("3"))
					{
						strSql2 = "select Round(DSS/30.0,2) as DSS,DSS_Event,genValue from gen_TCGA_"+strGenIndex+",sample_TCGA where genName='"+arrGen[i]+"' and gen_TCGA_"+strGenIndex+".genGSM = sample_TCGA.GSM and DSS_Event <>'' ";
					}
					if(!strlymph.equals("2"))
					{
						strSql2 += " and lymph='"+strlymph+"'";
						fileName += strlymph;
					}
					if(!strtnm.equals("0"))
					{
						strSql2 += " and tnm='"+strtnm+"'";
						fileName += strtnm;
					}
					if(!strSmoking.equals("5"))
					{
						strSql2 += " and smoking='"+strSmoking+"'";
						fileName += strSmoking;
					}
					if(!strGender.equals("2"))
					{
						strSql2 += " and gender='"+strGender+"'";
						fileName += strGender;
					}
					if(!strHistological.equals("0"))
					{
						strSql2 += " and histological_type='"+strHistological+"'";
						fileName += strHistological;
					}
					if(!strRace.equals("3"))
					{
						strSql2 += " and race='"+strRace+"'";
						fileName += strRace;
					}
					pathReturn = com.GetPath(strSql2, strSplit, strPaths[i], arrGen[i], arrGen[i], strSurvival, "jdbc/SSBLCA", "OSblca","TCGA");
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
			request.getRequestDispatcher("/BLCA/BLCAList.jsp").forward(request, response);
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
			request.setAttribute("ddlTobacco", strSmoking);
			request.setAttribute("ddlGender", strGender);
			request.setAttribute("ddllymph", strlymph);
			request.setAttribute("ddlHistological", strHistological);
			request.setAttribute("ddlRace", strRace);
			request.setAttribute("tip", strTip);
			request.getRequestDispatcher("/BLCA/BLCAList.jsp").forward(request, response);
		}
	}
}
