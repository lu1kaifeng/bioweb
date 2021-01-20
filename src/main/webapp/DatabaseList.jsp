
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
    <link rel="stylesheet" href="bootstrap-3.3.7/dist/css/bootstrap.min.css" />
    <style type="text/css">
    #body{
    		text-align: center;
    		min-width: 1200px;
    		height: 750px;
    	}
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
	div {
    	position:relative;
    	}
    </style>
    <link href="static/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="mcss/meun.css"/>
</head>
<body>
<div class="topMenu" style="font-size: 18px;background:#747070;min-width:1300px;">
        <div  style="padding:0;margin-left:0px;min-width:1300px;background-color:#747070;">
            <div class="navbar-header" style="background-color:#747070;margin-left:0px;min-width:420px;padding:0;float:left">                
                <a class="navbar-brand">
                    <img src="static/img/research.png" alt="Biomedical Informatics Institute" width="50" style="margin-top: -15px;" />
                </a>
                <div style="color:white;line-height:50px;font-size:20px;"><nobr>Biomedical Informatics Institute</nobr></div>
            </div>
            <div style="min-width:780px;float:left">
                 <div style="min-width:780px;margin:auto;font-size:22px;background-color:#747070;padding-left:50px;">
                 	<ul style="float: left;" >
						<li class="dropdown" style="width: 90px;">
							<a class="colorNavulli" href="Index.html">Home</a>
						</li>
						<li class="dropdown" style="width: 105px;">
							<a class="colorNavulli" href="members.html" >Member</a>
						</li>
						<!-- 二级菜单:哔哩哔哩动画 -->
						<li class="dropdown" style="width:100px;">
							<a class="colorNavulli">Tools</a>
							<ul class="dropdown-content" style="position:absolute;">
								<li style="width:100px;">
									<a href="DatabaseList.jsp" style="text-decoration:none;margin-left:10px;">LOGpc</a>
								</li>
								<li style="width:100px;">
									<a href="PPCList.jsp" style="text-decoration:none;margin-left:10px;">OSppc</a>
								</li>
							</ul>
						</li>
						<!-- 右浮动的一级菜单选项:百度 -->
						<li style="width: 130px">
							<a class="colorNavulli" href="results.html" style="font-family:微软雅黑;" >Publication</a>
						</li>
						<li style="width: 90px;">
							<a class="colorNavulli" href="NewsList.html" >News</a>     
						</li>
					</ul>                 
            	 </div>
            </div>
        </div>
    </div>   
    <div id="body" class="container" style="min-width:1300px;width:100%;margin:auto;padding:0;">
		<div class="card-body">    					
    	    <div class="alert alert-warning" role="alert" style="font-size:22px;min-width:1300px;width:100%;">
    	        <div class="card-header" style="font-size: 40px;">
    	           LOGpc
  		        </div>
  		        <span style="font-size:18px;">LOGpc ( Long-term Outcome and Gene Expression Profiling Database of pan-cancers ) <br>encompasses 209 expression datasets, provides 13 types of survival terms for 31310 patients of 27 distinct malignancies.</span>
		    </div>
  		</div>
        <div style="width:1400px;margin:auto">
            <div role="tabpanel" class="tab-pane active" id="sreach">    	
		        <div class="card text-left" >
                    <div style="width:1060px;margin-left:5px;">
                        <iframe id="iframe_con" class="center-block" scrolling="no" style="width: 500px; height:600px; border: none; float:left" src="body_Male.html" frameborder="0"></iframe>
                        <iframe id="iframe_con" class="center-block" scrolling="no" style="width: 550px; height:600px; border: none; float:left" src="body_Female.html" frameborder="0"></iframe>
                    </div>
		        </div>					
            </div>
        </div>
    </div>
</body>
</html>