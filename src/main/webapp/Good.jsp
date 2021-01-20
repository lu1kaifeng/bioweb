<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" 
    pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet" />
        <script src="static/js/jquery.min.js"></script>
        <script src="static/js/bootstrap.min.js"></script>
</head>
<body>
<%
try
{
	String str = session.getAttribute("username").toString();
	if(!str.equals("admin"))
	{
		out.println("<script>window.location.href='Login.jsp'</script>");
	}
}
catch(Exception e)
{
	out.println("<script>window.location.href='Login.jsp'</script>");
}
%>
<div class="panel panel-default">                
                <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-sm-6 col-md-4">
                                <div class="thumbnail" style="height:150px;">
                                    <p class="text-center">
                                        <a href="http://ac.scmor.com"><img src="Images/google.png" width="200" height="80" /></a>
                                    </p>
                                    <div class="caption">
                                        <h3 class="text-center"><a href="http://ac.scmor.com">谷歌镜像</a></h3>                                        
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-4">
                                <div class="thumbnail" style="height:150px;">
                                    <p class="text-center">
                                        <a href="http://sci-hub.tw"><img src="Images/sci.jpg" width="200" height="80" /></a>
                                    </p>
                                    <div class="caption">
                                        <h3 class="text-center"><a href="http://sci-hub.tw">SCI-HUB</a></h3>                                        
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-4">
                                <div class="thumbnail" style="height:150px;">
                                    <p class="text-center">
                                        <a href="http://www.letpub.com.cn"><img src="Images/letpub.jpg" width="200" height="80" /></a>
                                    </p>
                                    <div class="caption">
                                        <h3 class="text-center"><a href="http://www.letpub.com.cn">LetPub</a></h3>
                                    </div>
                                </div>
                            </div>
                        </div>   
                        <div class="row">
                            <div class="col-sm-6 col-md-4">
                                <div class="thumbnail" style="height:150px;">
                                    <p class="text-center">
                                        <a href="http://www.medsci.cn"><img src="Images/medsci.jpg" width="200" height="80" /></a>
                                    </p>
                                    <div class="caption">
                                        <h3 class="text-center"><a href="http://www.medsci.cn">Medsci</a></h3>                                        
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-4">
                                <div class="thumbnail" style="height:150px;">
                                    <p class="text-center">
                                        <a href="http://www.4243.net"><img src="Images/mmc.jpg" width="200" height="80" /></a>
                                    </p>
                                    <div class="caption">
                                        <h3 class="text-center"><a href="http://www.4243.net">木木虫学术导航</a></h3>                                        
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-4">
                                <div class="thumbnail" style="height:150px;">
                                    <p class="text-center">
                                        <a href="http://asia.ensembl.org/Homo_sapiens/Info/Index"><img src="Images/ense.png" width="200" height="80" /></a>
                                    </p>
                                    <div class="caption">
                                        <h3 class="text-center"><a href="http://asia.ensembl.org/Homo_sapiens/Info/Index">Ensembl genome browser</a></h3>                                        
                                    </div>
                                </div>
                            </div>
                        </div>  
                        <div class="row">
                            <div class="col-sm-6 col-md-4">
                                <div class="thumbnail" style="height:150px;">
                                    <p class="text-center">
                                        <a href="http://gepia.cancer-pku.cn/"><img src="Images/gepia.jpg" width="200" height="80" /></a>
                                    </p>
                                    <div class="caption">
                                        <h3 class="text-center"><a href="http://gepia.cancer-pku.cn/">GEPIA</a></h3>                                        
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-4">
                                <div class="thumbnail" style="height:150px;">
                                    <p class="text-center">
                                        <a href="https://biit.cs.ut.ee/methsurv/"><img src="Images/MethSurv.png" width="200" height="80" /></a>
                                    </p>
                                    <div class="caption">
                                        <h3 class="text-center"><a href="https://biit.cs.ut.ee/methsurv/">MethSurv</a></h3>                                        
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-4">
                                <div class="thumbnail" style="height:150px;">
                                    <p class="text-center">
                                        <a href="https://biit.cs.ut.ee/methsurv/"><img src="Images/thpa.jpg" width="200" height="80" /></a>
                                    </p>
                                    <div class="caption">
                                        <h3 class="text-center"><a href="https://www.proteinatlas.org/search/protein_class:FDA+approved+drug+targets">The Human Protein Atlas</a></h3>                                        
                                    </div>
                                </div>
                            </div>
                        </div>                        
                        <div class="row">
                            <div class="col-sm-6 col-md-4">
                                <div class="thumbnail" style="height:150px;">
                                    <p class="text-center">
                                        <a href="https://usegalaxy.org/"><img src="Images/galaxy1.png" width="200" height="80" /></a>
                                    </p>
                                    <div class="caption">
                                        <h3 class="text-center"><a href="https://usegalaxy.org/">Galaxy</a></h3>                                        
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-4">
                                <div class="thumbnail" style="height:150px;">
                                    <p class="text-center">
                                        <a href="https://www.galaxyproject.org/"><img src="Images/galaxy2.png" width="200" height="60" /></a>
                                    </p>
                                    <div class="caption" style="padding-top:0px;">
                                        <h3 class="text-center"><a href="https://www.galaxyproject.org/">Breast Cancer Gene-Expression Miner v4.3</a></h3>                                        
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-4">
                                <div class="thumbnail" style="height:150px;">
                                    <p class="text-center">
                                        <a href="http://bcgenex.centregauducheau.fr/BC-GEM/GEM-Requete.php?mode=1"></a>
                                    </p>
                                    <div class="caption" style="padding-top:0px;">
                                        <h3 class="text-center"><a href="http://bcgenex.centregauducheau.fr/BC-GEM/GEM-Requete.php?mode=1">Galaxy Community Hub</a></h3>                                        
                                    </div>
                                </div>
                            </div>
                        </div> 
                        
                        
                        
                        <div class="row">
                            <div class="col-sm-6 col-md-4">
                                <div class="thumbnail" style="height:150px;">
                                    <p class="text-center">
                                        <a href="https://plagiarisma.net/"><img src="Images/plagiarisma.jpg" width="200" height="80" /></a>
                                    </p>
                                    <div class="caption">
                                        <h3 class="text-center"><a href="https://plagiarisma.net/">plagiarisma</a></h3>                                        
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-4">
                                <div class="thumbnail" style="height:150px;">
                                    <p class="text-center">
                                        <a href="https://www.talkmed.com"><img src="Images/med.jpg" width="200" height="60" /></a>
                                    </p>
                                    <div class="caption" style="padding-top:0px;">
                                        <h3 class="text-center"><a href="https://www.talkmed.com">talkmed</a></h3>                                        
                                    </div>
                                </div>
                            </div>
                        </div>                       
                    </div>
                </div>
            </div>
</body>
</html>