<%@ page import="edu.henu.bioweb.RPlot" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Biomedical Informatics Institute</title>
	<link rel="stylesheet" href="../Css/common.css" />
    <link rel="stylesheet" href="../Css/data.css" />
    <script type="text/JavaScript" src="../Js/jquery-1.7.2.min.js"></script>
    <script type="text/JavaScript" src="../Js/common.js"></script>    
    <link href="../Css/menuStyle.css" rel="stylesheet" />
    <link href="../static/css/bootstrap.min.css" rel="stylesheet" />
    <style type="text/css">
    a:hover
	{
		text-decoration:none ;
	}
    </style>
    <script type="text/JavaScript">
    function whenError(a){
    	a.style.display="none";
        a.onerror=null;  
    }
    function imgClick(a)
    {
    	//var srcvalue = $("#img").attr("src")
    	//if(srcvalue!=null & srcvalue!=''){
    	  if(a!=null & a!=''){	
    	      //$("#img").show();
    		a.style.display="block";
    	}else{
    	      //$("#img").hide();
    		a.style.display="none";
    	}
    }
    function openDialog()
    {
    	var sel = document.getElementById("mulGen").value;
    	var paramers="Width=180px,Height=350px,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no";
    	if(sel=="-1"){    		
    		workerId = window.open("/BRCA/geneList.jsp", "", paramers);
    		//workerId = window.open("geneList.jsp", "", paramers);    		
    	}
    	else{
    		var strurl="/BRCA/geneList.jsp?id="+sel;
    		//var strurl="geneList.jsp?id="+sel;
    		workerId = window.open(strurl, "", paramers);
    	}    	    
    }
    </script>  
    
</head>
<body><div  style="font-size: 18px;background:#747070;">
        <div class="container" style="padding:0;margin-left:0px;width:1200px;background-color:#747070;">
            <div class="navbar-header" style="background-color:#747070;float:left;margin-left:0px;min-width:380px;padding:0;">                
                <a class="navbar-brand">
                    <img src="../static/img/research.png" alt="Institute of Biotechnology" width="50" style="margin-top: -15px;" />
                </a>
                <div style="color:white;line-height:50px;font-size:20px;"><nobr>Biomedical Informatics Institute</nobr></div>
            </div>
            <div style="min-width:780px;float:left;">
            <div style="min-width:780px;margin:auto;font-size:22px;background-color:#747070;padding-left:50px;">
                 <a class="colorNavulli" href="../Index.html" style="color:white">Home</a>
                 <a class="colorNavulli" href="../members.html" style="color:white">Member</a>                    
                 <a class="colorNavulli" href="../DatabaseList.jsp" style="color:white;background-color:black">LOGpc Database</a>                    
                 <a class="colorNavulli" href="../results.html" style="color:white">Publication</a>                   
                 <a class="colorNavulli" href="../NewsList.html" style="color:white">News</a>                
            </div>
        </div>
        </div>
    </div>
    <div class="panel panel-default">
          <div class="panel-heading" role="tab" id="headingOne">
               <h4 class="panel-title">
                  <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne" style="font-size:25px">
                      OSblca
                  </a>
               </h4>
         </div>
         <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
             <div class="panel-body">             	
             
             <form id="BLCA19915Form" method="post" action="../BLCAGSE19915">
             <table style="width:925px;border-collapse:separate; border-spacing:10px 10px;margin:auto;">
             	<tr>
             		<td style="width:50%;border:black 1px solid;padding:10px">
             		<table class="form1" style="border-collapse:separate; border-spacing:0px 10px;">
                <tr>
                    <td><span class="text-left lead">Gene symbol:</span></td>
                    <td>
                    	<input type="text" class="form-control" style="width:150px;display:inline" id="txtGen" name="txtGen" value="<%=(String)request.getAttribute("txtGen")!=null?(String)request.getAttribute("txtGen"):""%>" />
                    	
                    	<span id="leader" style="display:block"></span> 
                    	<input type="hidden" id="mulGen" name="mulGen" value="-1" />
                    </td>
                </tr>
                <tr>
                    <td><span class="text-left lead">Data Source:</span></td>
                    <td>
                    	<select id="ddldata" style="width:auto;" name="ddldata" onchange="window.location=this.value" class="form-control">
                    		<option value="/BLCA/BLCA_GSE13507.jsp">GSE13507</option>
                    		<option value="/BLCA/BLCA_GSE19915.jsp" selected="selected">GSE19915</option>
                    		<option value="/BLCA/BLCA_GSE31684.jsp">GSE31684</option>
                    		<option value="/BLCA/BLCA_GSE32548.jsp">GSE32548</option>
                    		<option value="/BLCA/BLCA_GSE48075.jsp">GSE48075</option>
                    		<option value="/BLCA/BLCA_GSE48276.jsp">GSE48276</option>
                    		<option value="/BLCA/BLCAList.jsp">TCGA</option>
                    		<option value="/BLCA/BLCA_Combined.jsp">Combined</option>
                    	</select>
                     </td>
                </tr>
                <tr>
                    <td><span class="text-left lead">Survival:</span></td>
                    <td>
                    	<select id="ddlSurvival" name="ddlSurvival" class="form-control" style="width:auto;">
                    		<option value="3">DSS</option>
                    	</select>
                     </td>
                </tr>
                <tr>
                    <td><span class="text-left lead">Split patients by:</span></td>
                    <td>
                    	<select id="ddlSplit" name="ddlSplit" class="form-control" style="width:auto;">                    		
                    		<option value="1">Upper 25%</option>
                    		<option value="3">Upper 30%</option>
                    		<option value="4">Upper 50%</option>
                    		<option value="5">Upper25% VS Lower 25%</option>
                    		<option value="6">Upper30% VS Lower 30%</option>
                    		<option value="0">Lower 25%</option>
                    		<option value="2">Lower 30%</option>                    		
                    		<option value="7">Lower 50%</option>
                    		<option value="8">Trichotomy</option>
                    		<option value="9">Quartile</option>
                    	</select>
                    </td>
                </tr>
            </table>
             		</td>
             		<td style="border:black 1px solid;padding:10px">
             		<table class="form1" style="border-collapse:separate; border-spacing:0px 10px;">            	 
                <tr>
                    <td><span class="text-left lead">TNM:</span></td>
                    <td>
                    	<select id="tnmstage" name="tnmstage" style="width:auto;" class="form-control">
                    		<option value="0">All</option>
                    		<option value="1">Stage I</option>
                    		<option value="2">Stage II</option>
                    		<option value="3">Stage III</option>
                    		<option value="4">Stage IV</option>
                    	</select>
                    </td>
                </tr>
                <tr>
                    <td><span class="text-left lead">Histological type:</span></td>
                    <td>
                    	<select name="ddlHistological" class="form-control" style="width:auto;" id="ddlHistological">
                    		<option value="0">All</option>
                    		<option value="1">MIBC</option>
                    		<option value="2">NMIBC</option>
                    	</select>
                    </td>
                </tr>
            </table>
             		</td>
             	</tr>
             </table>
            <%
            	String strtnm = (String)request.getAttribute("tnmstage");
            	String strSplit = (String)request.getAttribute("ddlSplit");
            	String strSurvival = (String)request.getAttribute("ddlSurvival");
            	String strData = (String)request.getAttribute("ddldata");
            	String strHistological = (String)request.getAttribute("ddlHistological");
            %>
			<script type="text/javascript">
			     var otnm=document.getElementById("tnmstage");
			     var osplit=document.getElementById("ddlSplit");
			     var osurvival=document.getElementById("ddlSurvival");
			     var odata = document.getElementById("ddldata");
			     var oHistological=document.getElementById("ddlHistological");
			     var temptnm = "<%=strtnm%>";
			     var tempsplit ="<%=strSplit%>";
			     var tempsuv="<%=strSurvival%>";	
			     var tempdata="<%=strData%>";
			     var tempHistological="<%=strHistological%>";
			     var i = 0;
			     for(i=0;i<otnm.options.length;i++){
				       if(otnm.options[i].value == temptnm)
				       {
				    	   otnm.options[i].selected = true;
				       }
				  }
			     for(i=0;i<osplit.options.length;i++){
				       if(osplit.options[i].value == tempsplit)
				       {
				    	   osplit.options[i].selected = true;
				       }
				  }	
			     for(i=0;i<oHistological.options.length;i++){
				       if(oHistological.options[i].value == tempHistological)
				       {
				    	   oHistological.options[i].selected = true;
				       }
				  }	
			     for(i=0;i<osurvival.options.length;i++){
				       if(osurvival.options[i].value == tempsuv)
				       {
				    	   osurvival.options[i].selected = true;
				       }
				  }	
			     for(i=0;i<odata.options.length;i++){
			    	 if(odata.options[i].value==tempdata)
			    	 {
			    		 odata.options[i].selected=true;
			    	 }
		    	 }
			</script> 
    <div style="margin:auto;width:1000px;font-size:18px;text-align:center">
    Please keep in mind:<br />
    More categorical variables you select would <span style="font-weight:bolder;">decrease</span> the number of utilizable patients for prognosis analysis.
    </div>
    <div class="row text-center" style="padding-top: 22px;">
    	<input type="submit" value="Kaplan-Meier plot" style="width:180px;height:50px;" class="btn btn-info btn-lg" />
    </div>
    
    <%
         String strTip = " ";
         strTip = (String)request.getAttribute("tip");
    	 if(strTip == null)
    	 {
    		 strTip = " ";
    	 }
    %>
    <label id="xstuNo" name="xstuNo" style="color:red" ><%=strTip%></label>
    <br />
        
    <div class="container" style="text-shadow: none; padding-bottom: 22px;min-height:350px; width:900px; border: 1px solid #303030; margin-top: 25px;">    	
        <table>
    		<tr>
    			<td style="width:900px;">
    				<div style="width:500px;margin:auto;padding-top:10px;">
    					<img src="<%=(String)request.getAttribute("myimg")%>" name="myimg" style="width:500px;height:500px;border:none;" onerror="whenError(this)" />
    				</div>
    			</td>
    		</tr>
			<%
              RPlot[] strs = (RPlot[])request.getAttribute("probe");
              if(strs!=null && strs.length > 0){
              for (int i=0;i<strs.length;i++){%>              	              	
              	  <tr>
              	  	<td style="width:900px;">
              	  		<div style="width:500px;margin:auto;">
              	  			<img src="<%=strs[i].toString()%>" style="width:500px;height:500px;" />
              	  		</div>
              	  	</td>
              	  </tr>
			  <%}}
            %>    		
    	</table>  
    </div>  
    <div style="margin:auto;margin-top:20px;width:1000px;font-size:16px;">
        <span style="font-weight:bold;">Please Cite OSblca in your works:</span> Zhang G, Wang Q, Yang M, Yuan Q, Dang Y, Sun X, An Y, Dong H, Xie L, Zhu W, Wang Y and Guo X (2019) OSblca: A Web Server for Investigating Prognostic Biomarkers of Bladder Cancer Patients. Front. Oncol. 9:466. doi: 10.3389/fonc.2019.00466<br />
    </div>
    <div style="margin:auto;margin-top:20px;width:1000px">
        <a style="font-weight:bold;cursor:pointer"  onclick="window.open('../otherWeb/DFI.html','newwindow','width=600,height=200,top='+(window.screen.height-30-200)/2+',left='+(window.screen.width-10-600)/2+'')">DFI</a> and <a style="font-weight:bold;cursor:pointer" onclick="window.open('../otherWeb/PFI.html','newwindow','width=600,height=200,top='+(window.screen.height-30-200)/2+',left='+(window.screen.width-10-600)/2+'')">PFI</a> were defined by Liu et al. [Liu, J., Lichtenberg, T., Hoadley, K. A. et al. (2018). An integrated TCGA pan-cancer clinical data resource to drive high-quality survival outcome analytics. Cell,173(2), 400-416.]<br />
    </div>
	</form>  
             </div>
         </div>
     </div>    	
	<div class="container">
        <p class="text-center">Copyright &copy; 2018 Biomedical Informatics Institute</p>
    </div>
</body>
</html>