
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import = "java.sql.Connection" %>
<%@page import = "java.sql.ResultSet" %>
<%@page import = "java.sql.DriverManager" %>
<%@page import = "java.sql.SQLException" %>
<%@page import = "java.sql.Statement" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Biomedical Informatics Institute</title>
	<link rel="stylesheet" href="Css/common.css" />
    <link rel="stylesheet" href="Css/data.css" />
    <script type="text/JavaScript" src="Js/jquery-1.7.2.min.js"></script>
    <script type="text/JavaScript" src="Js/common.js"></script>
    <link href="Css/menuStyle.css" rel="stylesheet" />
    <style type="text/css">
    a:hover
	{
		text-decoration:none;
	}
	a:active
	{
		text-decoration:none;
	}
	a:visited
	{
		text-decoration:none;
	}
	a:link
	{
		text-decoration:none;
	}
    </style>
    <link href="static/css/bootstrap.min.css" rel="stylesheet" />
    <script type="text/javascript">
      function gotourl(){    	  
    	  var objS = document.getElementById("ddltype");
          var type = objS.options[objS.selectedIndex].value;
          var obca = document.getElementById("ddlcancer");
          var cancer = obca.options[obca.selectedIndex].value;
          
          switch(type)
          {
          	case "0":
          		if(cancer=="0"){
          			document.location.href="DBList.jsp";
          		}
          		else if(cancer=="1"){
          			document.location.href="EAC/EACList.jsp";
          		}
          		else if(cancer=="2"){
          			document.location.href="CRC/CRCList.jsp";
          		}
				else if(cancer=="3"){
					document.location.href="GC/GCList.jsp";
          		}
        	  break;
          	case "1":
          		if(cancer=="0"){
          			document.location.href="BLCA/BLCAList.jsp";
          		}
          		else if(cancer=="1"){
          			document.location.href="KIRC/KIRCList.jsp";
          		}
          		else if(cancer=="2"){
          			document.location.href="KIRP/KIRPList.jsp";
          		}
          	  break;
          	case "2":
          		if(cancer=="0"){
          			document.location.href="MCC/MCCList.jsp";
          		}
          		else if(cancer=="1"){
          			document.location.href="Melanoma/MelanomaList.jsp";
          		}
          	  break;
          	case "3":
          		if(cancer=="0"){
          			document.location.href="BRCA/BRCAList.jsp";
          		}
          		else if(cancer=="1"){
          			document.location.href="UCEC/UCECList.jsp";
          		}
          		else if(cancer=="2"){
          			document.location.href="CESC/CESCList.jsp";
          		}
          	  break;
          	case "4":
          		if(cancer=="0"){
          			document.location.href="GBM/GBMList.jsp";
          		}
          		else if(cancer=="1"){
          			document.location.href="LGG/LGGList.jsp";
          		}
          	  break;
          	case "5":
          		if(cancer=="0"){
          			document.location.href="ACC/ACCList.jsp";
          		}
          	  break;
          	case "6":
          		if(cancer=="0"){
          			document.location.href="DLBCL/DLBCLList.jsp";
          		}
          	  break;
          	case "7":
          		if(cancer=="0"){
          			document.location.href="LMS/LMSList.jsp";
          		}
          		else if(cancer=="1"){
          			document.location.href="UCS/UCSList.jsp";
          		}
          		else if(cancer=="2"){
          			document.location.href="OS/OS_GSE21257.jsp";
          		}
				else if(cancer=="3"){
					document.location.href="MFS/MFSList.jsp";
          		}
          	  break;
          	case "8":
          		if(cancer=="0"){
          			document.location.href="LUCA/LUCAList.jsp";
          		}
          		else if(cancer=="1"){
          			document.location.href="HNSC/HNSCList.jsp";
          		}
          	  break;
          	case "9":
          		if(cancer=="0"){
          			document.location.href="UVM/UVMList.jsp";
          		}
          	  break;
          	case "10":
          		if(cancer=="0"){
          			document.location.href="CHOL/CHOLList.jsp";
          		}
          		else if(cancer=="1"){
          			document.location.href="LIHC/LIHCList.jsp";
          		}
          		else if(cancer=="2"){
          			document.location.href="PAAD/PAADList.jsp";
          		}
          	  break;
          }          
      }
      function gradeChange(){    	
      	var objS = document.getElementById("ddltype");
      	var type = objS.options[objS.selectedIndex].value;
      	var cancer = document.getElementById("ddlcancer");
      	switch(type){
      		case "0":
      			cancer.options.length=0;
      			cancer.options.add(new Option("Esophageal Squamous Cell Carcinoma","0"));
      			cancer.options.add(new Option("Esophageal Adenocarcinoma","1"));
      			cancer.options.add(new Option("Colorectal Cancer","2"));
      			cancer.options.add(new Option("Gastric Cancer","3"));
      			break;
      		case "3":
      			cancer.options.length=0;
      			cancer.options.add(new Option("Breast invasive Carcinoma","0"));
      			cancer.options.add(new Option("Uterine Corpus Endometrial Carcinoma","1"));
      			cancer.options.add(new Option("Cervical Squamous Cell Carcinoma and Endocervical Adencarcinoma","2"));
      			break;
      		case "4":
      			cancer.options.length=0;
      			cancer.options.add(new Option("Glioblastoma Multiforme","0"));
      			cancer.options.add(new Option("Brain Lower-Grade Glioma","1"));
      			break;
      		case "1":
      			cancer.options.length=0;
      			cancer.options.add(new Option("Bladder Urothelial Carcinoma","0"));
      			cancer.options.add(new Option("Kidney Renal Clear Cell Carcinoma","1"));
      			cancer.options.add(new Option("Kidney Renal Papillary Cell Carcinoma","2"));
      			break;
      		case "5":
      			cancer.options.length=0;
      			cancer.options.add(new Option("Adrenocortical Carcinoma","0"));
      			break;
      		case "6":
      			cancer.options.length=0;
      			cancer.options.add(new Option("Diffuse Large B Cell Lymphoma","0"));
      			break;
      		case "7":
      			cancer.options.length=0;
      			cancer.options.add(new Option("Leiomyosarcoma","0"));
      			cancer.options.add(new Option("Uterine Carcinosarcoma","1"));
      			cancer.options.add(new Option("Osteosarcoma","2"));
      			cancer.options.add(new Option("Myxofibrosarcoma","3"));
      			break;
      		case "2":
      			cancer.options.length=0;
      			cancer.options.add(new Option("Merkel Cell Carcinoma","0"));
      			cancer.options.add(new Option("Skin Cutaneous Melanoma","1"));
      			break;
      		case "8":
      			cancer.options.length=0;
      			cancer.options.add(new Option("Lung Cancer","0"));
      			cancer.options.add(new Option("Head and Neck Squamous Cell Carcinoma","1"));
      			break;
      		case "9":
      			cancer.options.length=0;
      			cancer.options.add(new Option("Uveal Melanoma","0"));
      			break;
      		case "10":
      			cancer.options.length=0;
      			cancer.options.add(new Option("Cholangiocarcinoma","0"));
      			cancer.options.add(new Option("Liver  Hepatocellular Carcinoma","1"));
      			cancer.options.add(new Option("Pancreatic Adenocarcinoma","2"));
      			break;
      	}
      }
    </script>
</head>
<body onload="gradeChange()"><div  style="font-size: 18px;background:#747070;">
        <div class="container" style="padding:0;margin-left:0px;width:1200px;background-color:#747070;">
            <div class="navbar-header" style="background-color:#747070;float:left;margin-left:0px;min-width:380px;padding:0;">                
                <a class="navbar-brand">
                    <img src="static/img/research.png" alt="Institute of Biotechnology" width="50" style="margin-top: -15px;" />
                </a>
                <div style="color:white;line-height:50px;font-size:20px;"><nobr>Biomedical Informatics Institute</nobr></div>
            </div>
            <div style="min-width:780px;float:left;">
            <div style="min-width:780px;margin:auto;font-size:22px;background-color:#747070;padding-left:50px;">
                 <a class="colorNavulli" href="Index.html" style="color:white">Home</a>
                 <a class="colorNavulli" href="members.html" style="color:white">Member</a>                    
                 <a class="colorNavulli" href="DatabaseList.jsp" style="color:white;background-color:black">LOGpc Database</a>                    
                 <a class="colorNavulli" href="results.html" style="color:white">Publication</a>                   
                 <a class="colorNavulli" href="NewsList.html" style="color:white">News</a>                 
            </div>
        </div>
        </div>
    </div>
    <div style="float: right; margin-top: 13px; margin-right: 20px;">
          	Visits:<span style="color: #337ab7;  font-weight: 800;">
          	
          	<%		
          	String strIP = "";
    		if (request.getHeader("x-forwarded-for") == null) { 
    			strIP = request.getRemoteAddr(); 
    		} 
    		else
    		{
    		    strIP = request.getHeader("x-forwarded-for"); 
    	    }
    		String dbDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    		Class.forName(dbDriver).newInstance();     		
    		Connection conn;
    	    Statement stmt;
    	    ResultSet rs;	
    	    String strValue="";
    	    String sql = "insert into userAccess(userIP,userAddress) values('"+strIP+"','list')";
    	    String sql1 ="select count(*) as cc from userAccess";
    	    try {
    			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databasename=BIOTCGA;user=sa;password=gxqktzwq");
    			stmt = conn.createStatement();
    			stmt.executeUpdate(sql);
    			rs = stmt.executeQuery(sql1);    			
    			while (rs.next()) {
    				strValue = rs.getString("cc");	
    			}
    			if (stmt != null) {
    				stmt.close();
    				stmt = null;
    			}
    			if (conn != null) {
    				conn.close();
    				conn = null;
    			}
    		} 
    	    catch (SQLException e) 
    		{	
    	    	strIP=e.toString();
    		}
    		
		
		%> 
    <label id="xstuNo" name="xstuNo" ><%=strValue%></label>
    <a href="help.html" style="text-decoration:none;margin-left:40px;color:black">Help</a>
    </span>
    </div> 
    <div style="width:90%;clear:both;margin:auto;">
       	<div style="margin:auto;width:100%;font-size:100px;text-align:center;font-weight:bold">
       		LOGpc
       	</div>
       	<div style="margin:auto;width:900px;font-size:20px;text-align:left;font-weight:bold;margin-top:50px;">
       		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;LOGpc ( <span style="text-decoration:underline;font-weight:bold">L</span>ong-term <span style="text-decoration:underline;font-weight:bold">O</span>utcome and <span style="text-decoration:underline;font-weight:bold">G</span>ene Expression Profiling Database of <span style="text-decoration:underline;font-weight:bold">p</span>an-<span style="text-decoration:underline;font-weight:bold">c</span>ancers ) encompasses 171 expression datasets, provides 13 types of survival terms for 27625 patients of 26 distinct malignancies.
       	</div>
       	<div style="margin:auto;width:100%;text-align:center;margin-top:30px;">
       		<table style="width:1200px;margin:auto;">
       			<tr>
       				<td style="width:200px;font-weight:bold;font-size:20px">System and organ:</td>
       				<td style="width:360px;">
       					<select id="ddltype" style="width:350px;margin-right:10px;" name="ddldata" onchange="gradeChange()" class="form-control">
            				<option value="0">Core Gastrointestinal Tumor</option>
                			<option value="1">Urologic Tumor</option>
                			<option value="2">Skin Tumor</option>
                			<option value="3">Gynecologic Tumor</option>
                			<option value="4">Central Nervous System Tumor</option>
                			<option value="5">Endocrine Tumor</option>
                			<option value="6">Hematologic and lymphatic malignancies Tumor</option>
                			<option value="7">Soft Tissue Tumor</option>
                			<option value="8">Thoracic Tumor</option>
                			<option value="9">Eye Tumor</option>
                			<option value="10">Developmental Gastrointestinal Tumor</option>
            			</select>
       				</td>
       				<td style="width:140px;font-weight:bold;font-size:20px">Cancer type:</td>
       				<td style="width:500px">
       					<select id="ddlcancer" style="width:490px;margin-right:10px;" name="ddldata" class="form-control">
            				<option value="0">Esophageal Squamous Cell Carcinoma</option>
                			<option value="1">Esophageal Adenocarcinoma</option>
                			<option value="2">Colorectal Cancer</option>
                			<option value="3">Gastric Cancer</option>
            			</select>
       				</td>
       			</tr>
       			<tr>
       				<td colspan="4" style="text-align:center;padding-top:20px;">
       					<input type="button" value="Go" style="width:100px;height:35px;line-height:16px;" class="btn btn-info btn-lg" onclick="gotourl()" />
       				</td>
       			</tr>
       		</table>
       	</div>
    </div>
</body>
</html>