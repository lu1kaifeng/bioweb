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
    <link href="static/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="Css/MyCss1.css" />  
    <link rel="stylesheet" href="Css/bootstrap.min.css" />
    <link rel="stylesheet" href="Css/BackColor.css" />
    <link href="Css/menuStyle.css" rel="stylesheet" />
		<script type="text/javascript" src="Js/jquery.min.js"></script>
		<script type="text/javascript" src="Js/bootstrap.min.js"></script>
		<style type="text/css">
			.heading a {
				font-size: 17px;
				color: dimgray;
			}			
			.heading a:hover,
			.heading a:focus {
				color: #74D3D3;
				text-decoration: none;
			}			
			.container {
				padding-bottom: 40px;
			}
			article
			{
			   border-radius: 10px;height:100px;
			}
		</style>
</head>
<body>
<div  style="font-size: 18px;background:#747070;">
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
		<form id="UserForm" method="post" action="GetAccess">
		<div class="wrapper row2">
			<section class="hoc container clear">
				<div class="sectiontitle" style="margin:0;">
					<h6 class="heading">Core Gastrointestinal Tumor</h6>
				</div>
				<ul class="nospace group services">
					<li class="one_quarter first bc-1x" style="border-radius: 10px;height:100px;width:250px;">
						<article style="padding:20px;">
							<h6 class="heading font-x1">
								<a href="DBList.jsp"><span style="text-decoration:underline;font-weight:bold">O</span>nline consensus <span style="text-decoration:underline;font-weight:bold">S</span>urvival for <span style="text-decoration:underline;font-weight:bold">E</span>sophageal <span style="text-decoration:underline;font-weight:bold">S</span>quamous <span style="text-decoration:underline;font-weight:bold">C</span>ell <span style="text-decoration:underline;font-weight:bold">C</span>arcinoma(<span style="font-style:italic">OSescc</span>)</a>
							</h6>
						</article>
					</li>
					<li class="one_quarter bc-2x" style="border-radius: 10px;height:100px;width:250px;">
						<article style="padding:20px;">
							<h6 class="heading font-x1">
								<a href="EAC/EACList.jsp"><span style="text-decoration:underline;font-weight:bold">O</span>nline consensus <span style="text-decoration:underline;font-weight:bold">S</span>urvival for <span style="text-decoration:underline;font-weight:bold">E</span>sophageal <span style="text-decoration:underline;font-weight:bold">A</span>deno<span style="text-decoration:underline;font-weight:bold">c</span>arcinoma(<span style="font-style:italic">OSeac</span>)</a>
							</h6>
						</article>
					</li>
					
					<li class="one_quarter bc-3x" style="border-radius: 10px;height:100px;width:250px;">
						<article style="padding:20px;">
							<h6 class="heading font-x1">
								<a href="READ/READList.jsp"><span style="text-decoration:underline;font-weight:bold">O</span>nline consensus <span style="text-decoration:underline;font-weight:bold">S</span>urvival for <span style="text-decoration:underline;font-weight:bold">Re</span>ctum <span style="text-decoration:underline;font-weight:bold">Ad</span>enocarcinoma(<span style="font-style:italic">OSread</span>)</a>
							</h6>
						</article>
					</li>
				</ul>
			</section>
		</div>	
		<div class="wrapper row3">
			<section class="hoc container clear"  style="padding:10px">
				<div class="sectiontitle" style="margin:0;">
					<h6 class="heading">Hematologic and lymphatic malignancies Tumor</h6>
				</div>
				<ul class="nospace group services">
					<li class="one_quarter first bc-15x"  style="border-radius: 10px;height:100px;width:250px;">
						<article style="padding:20px;">
							<h6 class="heading font-x1">
								<a href="DLBCL/DLBCLList.jsp"><span style="text-decoration:underline;font-weight:bold">O</span>nline consensus <span style="text-decoration:underline;font-weight:bold">S</span>urvival for <span style="text-decoration:underline;font-weight:bold">D</span>iffuse <span style="text-decoration:underline;font-weight:bold">l</span>arge <span style="text-decoration:underline;font-weight:bold">B c</span>ell <span style="text-decoration:underline;font-weight:bold">l</span>ymphoma(<span style="font-style:italic">OSdlbcl</span>)</a>
							</h6>
						</article>
					</li>
				</ul>				
			</section>
		</div>
		<div class="wrapper row3">
			<section class="hoc container clear"  style="padding:10px">
				<div class="sectiontitle" style="margin:0;">
					<h6 class="heading">Urologic Tumor</h6>
				</div>
				<ul class="nospace group services">
					<li class="one_quarter first bc-5x"  style="border-radius: 10px;height:100px;width:250px;">
						<article style="padding:20px;">
							<h6 class="heading font-x1">
								<a href="BLCA/BLCAList.jsp"><span style="text-decoration:underline;font-weight:bold">O</span>nline consensus <span style="text-decoration:underline;font-weight:bold">S</span>urvival for <span style="text-decoration:underline;font-weight:bold">Bl</span>adder Urothelial <span style="text-decoration:underline;font-weight:bold">Ca</span>rcinoma(<span style="font-style:italic">OSblca</span>)</a>
							</h6>
						</article>
					</li>
					<li class="one_quarter bc-7x"  style="border-radius: 10px;height:100px;width:250px;">
						<article style="padding:20px;">
							<h6 class="heading font-x1">
								<a href="KIRC/KIRCList.jsp"><span style="text-decoration:underline;font-weight:bold">O</span>nline consensus <span style="text-decoration:underline;font-weight:bold">S</span>urvival for <span style="text-decoration:underline;font-weight:bold">Ki</span>dney <span style="text-decoration:underline;font-weight:bold">R</span>enal <span style="text-decoration:underline;font-weight:bold">C</span>lear Cell Carcinoma(<span style="font-style:italic">OSkirc</span>)</a>
							</h6>
						</article>
					</li>
				</ul>
				
			</section>
		</div>
		<div class="wrapper row3">
			<section class="hoc container clear"  style="padding:10px">
				<div class="sectiontitle" style="margin:0;">
					<h6 class="heading">Thoracic Tumor</h6>
				</div>
				<ul class="nospace group services">
					<li class="one_quarter first bc-17x" style="border-radius: 10px;height:100px;width:250px;">
						<article style="padding:25px;">
							<h6 class="heading font-x1">
								<a href="LUCA/LUCAList.jsp"><span style="text-decoration:underline;font-weight:bold">O</span>nline consensus <span style="text-decoration:underline;font-weight:bold">S</span>urvival for <span style="text-decoration:underline;font-weight:bold">Lu</span>ng <span style="text-decoration:underline;font-weight:bold">Ca</span>ncer(<span style="font-style:italic">OSluca</span>)</a>
							</h6>
						</article>
					</li>
					<li class="one_quarter first bc-18x" style="border-radius: 10px;height:100px;width:250px;">
						<article style="padding:25px;">
							<h6 class="heading font-x1">
								<a href="HNSC/HNSCList.jsp"><span style="text-decoration:underline;font-weight:bold">O</span>nline consensus <span style="text-decoration:underline;font-weight:bold">S</span>urvival for <span style="text-decoration:underline;font-weight:bold">H</span>ead and <span style="text-decoration:underline;font-weight:bold">n</span>eck <span style="text-decoration:underline;font-weight:bold">s</span>quamous <span style="text-decoration:underline;font-weight:bold">c</span>ell carcinoma(<span style="font-style:italic">OShnsc</span>)</a>
							</h6>
						</article>
					</li>
				</ul>
			</section>
		</div>	
		
		<div class="wrapper row3">
			<section class="hoc container clear"  style="padding:10px">
				<div class="sectiontitle" style="margin:0;">
					<h6 class="heading">Developmental Gastrointestinal Tumor</h6>
				</div>
				<ul class="nospace group services">
					<li class="one_quarter first bc-6x" style="border-radius: 10px;height:100px;width:250px;">
						<article style="padding:25px;">
							<h6 class="heading font-x1">
								<a href="CHOL/CHOLList.jsp"><span style="text-decoration:underline;font-weight:bold">O</span>nline consensus <span style="text-decoration:underline;font-weight:bold">S</span>urvival for <span style="text-decoration:underline;font-weight:bold">Chol</span>angio Carcinoma(<span style="font-style:italic">OSchol</span>)</a>
							</h6>
						</article>
					</li>
					<li class="one_quarter bc-9x"  style="border-radius: 10px;height:100px;width:250px;">
						<article style="padding:20px;">
							<h6 class="heading font-x1">
								<a href="LIHC/LIHCList.jsp"><span style="text-decoration:underline;font-weight:bold">O</span>nline consensus <span style="text-decoration:underline;font-weight:bold">S</span>urvival for <span style="text-decoration:underline;font-weight:bold">Li</span>ver  <span style="text-decoration:underline;font-weight:bold">H</span>epato<span style="text-decoration:underline;font-weight:bold">c</span>ellular Carcinoma(<span style="font-style:italic">OSlihc</span>)</a>
							</h6>
						</article>
					</li>
					<li class="one_quarter bc-10x"  style="border-radius: 10px;height:100px;width:250px;">
						<article style="padding:20px;">
							<h6 class="heading font-x1">
								<a href="PAAD/PAADList.jsp"><span style="text-decoration:underline;font-weight:bold">O</span>nline consensus <span style="text-decoration:underline;font-weight:bold">S</span>urvival for <span style="text-decoration:underline;font-weight:bold">Pa</span>ncreatic <span style="text-decoration:underline;font-weight:bold">ad</span>enocarcinoma(<span style="font-style:italic">OSpaad</span>)</a>
							</h6>
						</article>
					</li>
				</ul>
			</section>
		</div>
			
		<div class="wrapper row3">
			<section class="hoc container clear"  style="padding:10px">
				<div class="sectiontitle" style="margin:0;">
					<h6 class="heading">Central Nervous System Tumor</h6>
				</div>
				<ul class="nospace group services">
					<li class="one_quarter first bc-33x" style="border-radius: 10px;height:100px;width:250px;">
						<article style="padding:25px;">
							<h6 class="heading font-x1">
								<a href="GBM/GBMList.jsp"><span style="text-decoration:underline;font-weight:bold">O</span>nline consensus <span style="text-decoration:underline;font-weight:bold">S</span>urvival for <span style="text-decoration:underline;font-weight:bold">G</span>lio<span style="text-decoration:underline;font-weight:bold">b</span>lastoma <span style="text-decoration:underline;font-weight:bold">M</span>ultiforme(<span style="font-style:italic">OSgbm</span>)</a>
							</h6>
						</article>
					</li>
				</ul>
			</section>
		</div>
		
		<div class="wrapper row3">
			<section class="hoc container clear"  style="padding:10px">
				<div class="sectiontitle" style="margin:0;">
					<h6 class="heading">Skin Tumor</h6>
				</div>
				<ul class="nospace group services">
					<li class="one_quarter first bc-17x" style="border-radius: 10px;height:100px;width:250px;">
						<article style="padding:20px;">
							<h6 class="heading font-x1">
								<a href="MCC/MCCList.jsp"><span style="text-decoration:underline;font-weight:bold">O</span>nline consensus <span style="text-decoration:underline;font-weight:bold">S</span>urvival for <span style="text-decoration:underline;font-weight:bold">M</span>erkel <span style="text-decoration:underline;font-weight:bold">C</span>ell <span style="text-decoration:underline;font-weight:bold">C</span>arcinoma(<span style="font-style:italic">OSmcc</span>)</a>
							</h6>
						</article>
					</li>
					<li class="one_quarter bc-18x" style="border-radius: 10px;height:100px;width:250px;">
						<article style="padding:20px;">
							<h6 class="heading font-x1">
								<a href="Melanoma/MelanomaList.jsp"><span style="text-decoration:underline;font-weight:bold">O</span>nline consensus <span style="text-decoration:underline;font-weight:bold">S</span>urvival for <span style="text-decoration:underline;font-weight:bold">Sk</span>in <span style="text-decoration:underline;font-weight:bold">C</span>utaneous <span style="text-decoration:underline;font-weight:bold">M</span>elanoma(<span style="font-style:italic">OSskcm</span>)</a>
							</h6>
						</article>
					</li>
				</ul>
			</section>
		</div>
		<div class="wrapper row2">
			<section class="hoc container clear"  style="padding:10px">
				<div class="sectiontitle" style="margin:0;">
					<h6 class="heading">Gynecologic Tumor</h6>
				</div>
				<ul class="nospace group services">
					<li class="one_quarter first bc-25x" style="border-radius: 10px;height:100px;width:250px;">
						<article style="padding:13px;">
							<h6 class="heading font-x1">
								<a href="UCEC/UCECList.jsp"><span style="text-decoration:underline;font-weight:bold">O</span>nline consensus <span style="text-decoration:underline;font-weight:bold">S</span>urvival for <span style="text-decoration:underline;font-weight:bold">U</span>terine <span style="text-decoration:underline;font-weight:bold">C</span>orpus <span style="text-decoration:underline;font-weight:bold">E</span>ndometrial <span style="text-decoration:underline;font-weight:bold">C</span>arcinoma(<span style="font-style:italic">OSucec</span>)</a>
							</h6>
						</article>
					</li>
					<li class="one_quarter bc-23x" style="border-radius: 10px;height:100px;width:250px;padding:0px;">
						<article style="padding-top:8px;padding-left:8px;">
							<h6 class="heading font-x1">
								<a href="CESC/CESCList.jsp"><span style="text-decoration:underline;font-weight:bold">O</span>nline consensus <span style="text-decoration:underline;font-weight:bold">S</span>urvival for <span style="text-decoration:underline;font-weight:bold">Ce</span>rvical <span style="text-decoration:underline;font-weight:bold">s</span>quamous <span style="text-decoration:underline;font-weight:bold">c</span>ell carcinoma and endocervical Adencarcinoma(<span style="font-style:italic">OScesc</span>)</a>
							</h6>
						</article>
					</li>
				</ul>
			</section>
		</div>
		<div class="wrapper row2">
			<section class="hoc container clear"  style="padding:10px">
				<div class="sectiontitle" style="margin:0;">
					<h6 class="heading">Eye Tumor</h6>
				</div>
				<ul class="nospace group services">
					<li class="one_quarter first bc-20x" style="border-radius: 10px;height:100px;width:250px;">
						<article style="padding:20px;">
							<h6 class="heading font-x1">
								<a href="UVM/UVMList.jsp"><span style="text-decoration:underline;font-weight:bold">O</span>nline consensus <span style="text-decoration:underline;font-weight:bold">S</span>urvival for <span style="text-decoration:underline;font-weight:bold">Uv</span>eal <span style="text-decoration:underline;font-weight:bold">M</span>elanoma(<span style="font-style:italic">OSuvm</span>)</a>
							</h6>
						</article>
					</li>
				</ul>
			</section>
		</div>
		<div class="wrapper row2">
			<section class="hoc container clear"  style="padding:10px">
				<div class="sectiontitle" style="margin:0;">
					<h6 class="heading">Soft Tissue Tumor</h6>
				</div>
				<ul class="nospace group services" style="width:1150px;">
					<li class="one_quarter first bc-8x" style="border-radius: 10px;height:100px;width:250px;">
						<article style="padding:20px;">
							<h6 class="heading font-x1">
								<a href="LMS/LMSList.jsp"><span style="text-decoration:underline;font-weight:bold">O</span>nline consensus <span style="text-decoration:underline;font-weight:bold">S</span>urvival for <span style="text-decoration:underline;font-weight:bold">L</span>eio<span style="text-decoration:underline;font-weight:bold">m</span>yo<span style="text-decoration:underline;font-weight:bold">s</span>arcoma(<span style="font-style:italic">OSlms</span>)</a>
							</h6>
						</article>
					</li>
					<li class="one_quarter bc-10x" style="border-radius: 10px;height:100px;width:250px;">
						<article style="padding:20px;">
							<h6 class="heading font-x1">
								<a href="UCS/UCSList.jsp"><span style="text-decoration:underline;font-weight:bold">O</span>nline consensus <span style="text-decoration:underline;font-weight:bold">S</span>urvival for <span style="text-decoration:underline;font-weight:bold">U</span>terine <span style="text-decoration:underline;font-weight:bold">C</span>arcino<span style="text-decoration:underline;font-weight:bold">s</span>arcoma(<span style="font-style:italic">OSucs</span>)</a>
							</h6>
						</article>
					</li>					
					<li class="one_quarter bc-11x" style="border-radius: 10px;height:100px;width:250px;">
						<article style="padding:25px;">
							<h6 class="heading font-x1">
								<a href="OS/OS_GSE21257.jsp"><span style="text-decoration:underline;font-weight:bold">O</span>nline consensus <span style="text-decoration:underline;font-weight:bold">S</span>urvival for <span style="text-decoration:underline;font-weight:bold">Os</span>teosarcoma(<span style="font-style:italic">OSos</span>)</a>
							</h6>
						</article>
					</li>
					<li class="one_quarter bc-13x" style="border-radius: 10px;height:100px;width:250px;padding:0px;">
						<article style="padding-top:8px;padding-left:8px;">
							<h6 class="heading font-x1">
								<a href="MFS/MFSList.jsp"><span style="text-decoration:underline;font-weight:bold">O</span>nline consensus <span style="text-decoration:underline;font-weight:bold">S</span>urvival for <span style="text-decoration:underline;font-weight:bold">M</span>yxo<span style="text-decoration:underline;font-weight:bold">f</span>ibro<span style="text-decoration:underline;font-weight:bold">s</span>arcoma(<span style="font-style:italic">OSmfs</span>)</a>
							</h6>
						</article>
					</li>
				</ul>
			</section>
		</div>
		<div class="wrapper row2">
			<section class="hoc container clear"  style="padding:10px">
				<div class="sectiontitle" style="margin:0;">
					<h6 class="heading">Endocrine Tumor</h6>
				</div>
				<ul class="nospace group services">
					<li class="one_quarter first bc-18x" style="border-radius: 10px;height:100px;width:250px;">
						<article style="padding:20px;">
							<h6 class="heading font-x1">
								<a href="ACC/ACCList.jsp"><span style="text-decoration:underline;font-weight:bold">O</span>nline consensus <span style="text-decoration:underline;font-weight:bold">S</span>urvival for <span style="text-decoration:underline;font-weight:bold">A</span>dreno<span style="text-decoration:underline;font-weight:bold">c</span>orti<span style="text-decoration:underline;font-weight:bold">c</span>al carcinoma(<span style="font-style:italic">OSacc</span>)</a>
							</h6>
						</article>
					</li>
				</ul>
			</section>
		</div>
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
	    
	    String sql = "insert into userAccess(userIP,userAddress) values('"+strIP+"','list')";
	    
	    //sql = "select userIP from userAccess";
	    try {
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databasename=BIOTCGA;user=sa;password=gxqktzwq");
			//conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databasename=BIOTCGA","sa","123");
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
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
		</form>
		<div class="footer">
			<p>Copyright &copy; 2018 Biomedical Informatics Institute</p>
		</div>
</body>
</html>