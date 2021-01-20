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

public class BLCAGSE48075    extends HttpServlet{
	public BLCAGSE48075()
	{
		super();
	}
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	GetPathReturn pathReturn  = null;
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
		String strSurvival = "";
		String strSplit = "";
		String strGen = "";
		String strData="";
		String strHistological="";
		String fileName = "";
		String strGenList = "-1";//������
		boolean isPic = false;
		request.setCharacterEncoding("UTF-8");
		
		
		strSurvival = request.getParameter("ddlSurvival");
		//strGenList=request.getParameter("mulGen");//��ȡ������
		strSplit = request.getParameter("ddlSplit");
		strHistological=  request.getParameter("ddlHistological");
		strGen = request.getParameter("txtGen");
		String strSql = "";
		ComFun fun = new ComFun();
		BLCACom com = new BLCACom();
		if(!strGen.equals("") && strGen != null){
			strGen = strGen.toUpperCase();
		}
		if(!fun.isGetgenName(strGen))
		{
			strTip=fun.GetTip(4, "GSE48075", strGen, "BLCA");
			request.setAttribute("txtGen", strGen);
			request.setAttribute("ddlSurvival", strSurvival);
			request.setAttribute("ddlSplit", strSplit);
			request.setAttribute("ddlHistological", strHistological);
			request.setAttribute("tip", strTip);
			request.getRequestDispatcher("/BLCA/BLCA_GSE48075.jsp").forward(request, response);
			return;
		}
		String strSql2 = "";
		String[] stridrefs = null;//
		String[] strPaths = null;//new String[stridrefs.length];
		RPlot[] strPaths2 = null;//new String[stridrefs.length];
		String[] stridrefs2=null;
		String[] strPaths11=null;
		String[] strPaths22=null;
		String strGenIndex = "";
		if(strGenList.equals("-1")){
			strGenIndex = fun.getGenIndex(strGen, "gen_GSE48075", "jdbc/SSBLCA");
			if(strGenIndex.equals("-1"))
	        {
				strTip=fun.GetTip(3, "GSE48075", strGen, "BLCA");
				request.setAttribute("txtGen", strGen);
				request.setAttribute("ddlSurvival", strSurvival);
				request.setAttribute("ddlSplit", strSplit);
				request.setAttribute("ddlHistological", strHistological);
				request.setAttribute("tip", strTip);
				request.getRequestDispatcher("/BLCA/BLCA_GSE48075.jsp").forward(request, response);
	        	return;
	        }
			stridrefs = fun.GetIDREFByName(strGen, strGenIndex, "gen_GSE48075", "jdbc/SSBLCA");
			strPaths = new String[stridrefs.length];
			strPaths2 = new RPlot[stridrefs.length];
			
			try 
			{
				strSql2 = "";
				fileName = GetFileName() + "GSE48075" + strGen + strSurvival + strSplit;
				
				if(strSurvival.equals("0"))
				{
					//strSql = "select survival,death,avg(genValue) as genValue from gen_GES53625,sample_GES53625 where genName='"+strGen+"' and gen_GES53625.genGSM=sample_GES53625.GSM ";
					strSql = "select OS,OS_Event,avg(genValue) as genvalue from sample_GSE48075,gen_GSE48075_"+strGenIndex+" where genName='"+strGen+"' and gen_GSE48075_"+strGenIndex+".genGSM = sample_GSE48075.GSM";
				}
				if(!strHistological.equals("0"))
				{
					strSql += " and histological_type='"+strHistological+"'";
					fileName += strHistological;
				}
				
				strSql += " group by sample_GSE48075.GSM,OS_Event,OS";
				
				strPath = "d:/picture/" +fileName+".jpg";
				//strPath2="/picture/"+fileName+".jpg";
				
				pathReturn = com.GetPath(strSql, strSplit, strPath, strGen, strGen.toUpperCase() + ":Average", strSurvival, "jdbc/SSBLCA", "OSblca","GSE48075");
				strTip=pathReturn.getStrTip();
				strPath2 = pathReturn.getPlotBytes();
				for(int i=0;i<stridrefs.length;i++)
				{
							
					if(stridrefs[i] != null && !stridrefs[i].equals(""))
					{
						fileName = GetFileName() + "GSE48075" + strGen + strSurvival + strSplit;
						
						if(strSurvival.equals("0"))
						{
							strSql2 = "select OS,OS_Event,genValue from gen_GSE48075_"+strGenIndex+",sample_GSE48075 where idref='"+stridrefs[i]+"' and gen_GSE48075_"+strGenIndex+".genGSM=sample_GSE48075.GSM ";						
							//strSql = "select os,death,genValue from sample_GSE39612,gen_GSE39612 where genName='"+strGen+"' and gen_GSE39612.genGSM = sample_GES53625.GSM";
						}
						if(!strHistological.equals("0"))
						{
							strSql2 += " and histological_type='"+strHistological+"'";
							fileName += strHistological;
						}
						
						strSql2 += " group by sample_GSE48075.GSM,OS_Event,OS,genValue";
						
						strPaths[i]="d:/picture/" +fileName+stridrefs[i]+".jpg";
						//strPaths2[i]="/picture/"+fileName+stridrefs[i]+".jpg";
						pathReturn = com.GetPath(strSql2, strSplit, strPaths[i], strGen, strGen.toUpperCase() + " Probe:"+stridrefs[i], strSurvival, "jdbc/SSBLCA", "OSblca","GSE48075");
						strTip=pathReturn.getStrTip();
						strPaths2[i]= pathReturn.getPlotBytes();
					}
				}		
			}
			catch (RserveException | REXPMismatchException e) {
				e.printStackTrace();
			}	
		}
		else{
			String[] arrGen = strGenList.split("\\;");
			List<String> listGen = new ArrayList();
			List<String> listPaths = new ArrayList();
			List<String> listPaths2 = new ArrayList();
			String[] strID = null;
			for(int i=0;i<arrGen.length;i++)
			{
				arrGen[i] = arrGen[i].replaceAll("\n", "");
				arrGen[i] = arrGen[i].replaceAll("\r", "");
				strGenIndex = fun.getGenIndex(arrGen[i], "gen_GSE48075", "jdbc/SSBLCA");
				strID = fun.GetIDREFByName(arrGen[i],strGenIndex, "gen_48075", "jdbc/SSBRCA1");//�û�������̽��
				//for(int j=0;j<sg.length;j++)//�û�������̽��ѭ��
				{
						
					try 
					{				
						fileName = GetFileName() + "GSE48075" + arrGen[i] + strSurvival + strSplit;
						
						if(strSurvival.equals("0"))
						{
							//strSql = "select survival,death,avg(genValue) as genValue from gen_GES53625,sample_GES53625 where genName='"+strGen+"' and gen_GES53625.genGSM=sample_GES53625.GSM ";
							strSql = "select OS,OS_Event,avg(genValue) as genvalue from sample_GSE48075,gen_GSE48075_"+strGenIndex+" where genName='"+arrGen[i]+"' and gen_GSE48075_"+strGenIndex+".genGSM = sample_GSE48075.GSM";
						}
						if(!strHistological.equals("0"))
						{
							strSql += " and histological_type='"+strHistological+"'";
							fileName += strHistological;
						}
						
						strSql += " group by sample_GSE48075.GSM,OS_Event,OS";
						
						strPath = "d:/picture/" +fileName+".jpg";
						//strPath2="/picture/"+fileName+".jpg";
						listPaths.add("d:/picture/" +fileName+".jpg");
						listPaths2.add("/picture/"+fileName+".jpg");
						
						pathReturn = com.GetPath(strSql, strSplit, strPath, arrGen[i], arrGen[i].toUpperCase() + ":Average", strSurvival,"jdbc/SSBLCA", "OSblca","GSE48075");
						strTip=pathReturn.getStrTip();
						strPath2 = pathReturn.getPlotBytes();
						for(int n=0;n<strID.length;n++)
						{									
							if(strID[n] != null && !strID[n].equals(""))
							{
								listGen.add(strID[n]);//��¼����̽��
								fileName = GetFileName() + "GSE48075" + strGen + strSurvival + strSplit;
								
								if(strSurvival.equals("0"))
								{
									strSql2 = "select OS,OS_Event,genValue from gen_GSE48075_"+strGenIndex+",sample_GSE48075 where idref='"+strID[n]+"' and gen_GSE48075_"+strGenIndex+".genGSM=sample_GSE48075.GSM ";						
									//strSql = "select os,death,genValue from sample_GSE39612,gen_GSE39612 where genName='"+strGen+"' and gen_GSE39612.genGSM = sample_GES53625.GSM";
								}
								if(!strHistological.equals("0"))
								{
									strSql2 += " and histological_type='"+strHistological+"'";
									fileName += strHistological;
								}
								
								strSql2 += " group by sample_GSE48075.GSM,OS_Event,OS,genValue";
								strPath = "d:/picture/" +fileName+strID[n]+".jpg";
								//strPath2="/picture/"+fileName+strID[n]+".jpg";
								listPaths.add("d:/picture/" +fileName+strID[n]+".jpg");
								listPaths2.add("/picture/"+fileName+strID[n]+".jpg");
								pathReturn = com.GetPath(strSql2, strSplit, strPath, arrGen[i], arrGen[i].toUpperCase() + " Probe:"+strID[n], strSurvival,"jdbc/SSBLCA", "OSblca","GSE48075");
								strTip=pathReturn.getStrTip();
								strPath2 = pathReturn.getPlotBytes();
							}
						}	
					}
					catch (RserveException | REXPMismatchException e) {
						e.printStackTrace();
					}
					
				}
			}
			int k = listGen.size()+arrGen.length;
			stridrefs2 = new String[k];
			strPaths11 = new String[k];
			strPaths22 = new String[k];
			int j =0;
			for(j=0;j<arrGen.length;j++)
			{
				stridrefs2[j]=arrGen[j];
				strPaths22[j]=listPaths2.get(j);
			}
			for(j=arrGen.length;j<k;j++)
			{
				stridrefs2[j]=listGen.get(j-arrGen.length);
				strPaths22[j]=listPaths2.get(j);
			}
		}
		
		//fileName = "GEO39612" + strGen + strSurvival + strSplit;
			
		String error="";
		if(error!=""){			
			request.setAttribute("error", error);
			request.getRequestDispatcher("/BLCA/BLCA_GSE48075.jsp").forward(request, response);
		}else{						
			//if(isPic)
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
			request.setAttribute("ddlHistological", strHistological);
			request.setAttribute("idref", stridrefs);
			request.setAttribute("idref2", stridrefs2);
			request.setAttribute("probe2", strPaths22);
			//request.setAttribute("probe", strPaths);//����
			request.setAttribute("probe", strPaths2);//����
			request.setAttribute("tip", strTip);
			request.getRequestDispatcher("/BLCA/BLCA_GSE48075.jsp").forward(request, response);
		}
	}
}