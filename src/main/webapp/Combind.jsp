<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="Css/common.css" />
    <link rel="stylesheet" href="Css/probe.css" />
    <script type="text/JavaScript" src="Js/jquery-1.7.2.min.js"></script>
    <script type="text/JavaScript" src="Js/common.js"></script>
    <link href="static/css/bootstrap.min.css" rel="stylesheet" />
    <link href="Css/menuStyle.css" rel="stylesheet" />
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
    </script>
</head>
<body><div  style="font-size: 18px;background:#747070;">
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
    <div class="panel panel-default">
          <div class="panel-heading" role="tab" id="headingOne">
               <h4 class="panel-title">
                  <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne" style="font-size:25px">
                      OSescc
                  </a>
               </h4>
         </div>
         <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
             <div class="panel-body">  
                     <form id="loginForm" method="post" action="GetCombind">
                     	<table style="width:925px;margin:auto;border-collapse:separate; border-spacing:10px 10px;">
                     		<tr>
                     			<td style="width:50%;border:black 1px solid;padding:10px">
                     			<table class="form1" style="border-collapse:separate; border-spacing:0px 10px;">
                <tr>
                    <td><span class="text-left lead">Gene symbol:</span></td>
                    <td>
                    	<input type="text" class="form-control" name="gene" value="<%=(String)request.getAttribute("gene")!=null?(String)request.getAttribute("gene"):""%>" />
                    </td>
                </tr>
                <tr>
                    <td><span class="text-left lead">Data Source:</span></td>
                    <td>
                    	<select id="ddldata" style="width:auto;" name="ddldata" onChange="window.location=this.value" class="form-control">
                    		<option value="DBGES.jsp">GSE53625</option>
                    		<option value="DBList.jsp">TCGA</option>
                    		<option value="Combind.jsp" selected="selected">Combined</option>
                    	</select>
                     </td>
                </tr>
                <tr>
                    <td><span class="text-left lead">Survival:</span></td>
                    <td>
                    	<select id="survival" style="width:auto;" name="survival" class="form-control">
                    		<option value="0">OS</option>
                    	</select>
                     </td>
                </tr>
            	<tr>
                    <td><span class="text-left lead">Split patients by:</span></td>
                    <td>
                    	<select id="split" style="width:auto;" name="split" class="form-control">
                    		<option value="1">Upper 25%</option>
                    		<option value="3">Upper 30%</option>
                    		<option value="4">Upper 50%</option>
                    		<option value="5">Upper25% VS Lower 25%</option>
                    		<option value="6">Upper30% VS Lower 30%</option>
                    		<option value="0">Lower 25%</option>
                    		<option value="2">Lower 30%</option>                    		
                    		<option value="7">Lower 50%</option>
                    	</select>
                    </td>
                </tr>
            </table>
                     			</td>
                     			<td style="width:50%;border:black 1px solid;padding:10px">
                     			<table class="form1" style="border-collapse:separate; border-spacing:0px 10px;">
            	<tr>
                    <td><span class="text-left lead">TNM:</span></td>
                    <td>
                    	<select id="tnm" name="tnm" class="form-control">
                    		<option value="0">All</option>
                    		<option value="1">Stage I</option>
                    		<option value="2">Stage II</option>
                    		<option value="3">Stage III</option>
                    	</select>
                    </td>
                </tr>   
                <tr>
                    <td><span class="text-left lead">Smoking:</span></td>
                    <td>
                    	<select name="smoking" class="form-control" id="smoking">
                    		<option value="2">All</option>                    		
                    		<option value="1">Yes</option>
                    		<option value="0">No</option>
                    	</select>
                    </td>
                </tr>
                <tr>
                    <td><span class="text-left lead">Alcohol:</span></td>
                    <td>
                    	<select name="ddlAlcohol" class="form-control" id="ddlAlcohol">
                    		<option value="2">All</option>
                    		<option value="1">Yes</option>
                    		<option value="0">No</option>
                    	</select>
                    </td>
                </tr>
            </table> 
                     			</td>
                     		</tr>
                     	</table>
                     
            
            
    
    		<%
            	String strtnm = (String)request.getAttribute("tnm");
            	String strAlcohol = (String)request.getAttribute("ddlAlcohol");
            	String strTobacco = (String)request.getAttribute("smoking");
            	String strSplit = (String)request.getAttribute("split");
            	String strSurvival = (String)request.getAttribute("survival");
            	String strData = (String)request.getAttribute("ddldata");
            %>
			<script type="text/javascript">
			     var otnm=document.getElementById("tnm");
			     var ocountry=document.getElementById("ddlCountry");
			     var oymph=document.getElementById("lymph");
			     var oalcolhol=document.getElementById("ddlAlcohol");
			     var osmoking=document.getElementById("smoking");
			     var osplit=document.getElementById("split");
			     var osurvival=document.getElementById("survival");
			     var odata = document.getElementById("ddldata");
			     var temptnm = "<%=strtnm%>";
			     var tempal ="<%=strAlcohol%>";
			     var tempsmok="<%=strTobacco%>";
			     var tempsplit ="<%=strSplit%>";
			     var tempsuv="<%=strSurvival%>";	
			     var tempdata="<%=strData%>";
			     var i = 0;
			     for(i=0;i<otnm.options.length;i++){
				       if(otnm.options[i].value == temptnm)
				       {
				    	   otnm.options[i].selected = true;
				       }
				  }
			     for(i=0;i<oalcolhol.options.length;i++){
				       if(oalcolhol.options[i].value == tempal)
				       {
				    	   oalcolhol.options[i].selected = true;
				       }
				  }	
			     for(i=0;i<osmoking.options.length;i++){
				       if(osmoking.options[i].value == tempsmok)
				       {
				    	   osmoking.options[i].selected = true;
				       }
				  }	
			     for(i=0;i<osplit.options.length;i++){
				       if(osplit.options[i].value == tempsplit)
				       {
				    	   osplit.options[i].selected = true;
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
    
    <div class="row text-center" style="padding-top: 22px;">
    	<input type="submit" value="Kaplan-Meier plot" style="width:180px;height:50px;" class="btn btn-info btn-lg" />
    </div>    
    
    <br />
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
    
    <div class="container" style="text-shadow: none; padding-bottom: 22px;min-height:350px; width:900px;margin:auto; border: 1px solid #303030; margin-top: 25px;">
          
        <%
              String str1 = (String)request.getAttribute("geo");
              String str2 = (String)request.getAttribute("tcga");
              String str3 = (String)request.getAttribute("combined");
         %>
        <table>
        	<tr>
        		<td style="width:900px;">        			
        			<div style="width:500px;margin:auto;">
        				<img  src="<%=str1%>" style="width:500px;height:500px;" onerror="whenError(this)" />
        			</div>        			
        		</td>
        	</tr>
        	<tr>
        		<td style="width:900px;">        			
        			<div style="width:500px;margin:auto;">
        				<img  src="<%=str2%>" style="width:500px;height:500px;" onerror="whenError(this)" />
        			</div>
        		</td>
        	</tr>
        	<tr>
        		<td style="width:900px;">        			
        			<div style="width:500px;margin:auto;">
        				<img  src="<%=str3%>" style="width:500px;height:500px;" onerror="whenError(this)" />
        			</div>
        		</td>
        	</tr>
        </table>
    </div>
    <div style="margin:auto;margin-top:20px;width:1000px;font-size:16px;">
        <span style="font-weight:bold;">Please Cite OSescc in your works:</span>Wang Q, Wang F, Lv J, Xin J, Xie L, Zhu W, Tang Y, Li Y, Zhao X, Wang Y, Li X, Guo X. Interactive online consensus survival tool for esophageal squamous cell carcinoma prognosis analysis. Oncol Lett. 2019 Aug;18(2):1199-1206. doi: 10.3892/ol.2019.10440. Epub 2019 Jun 5. PMID: 31423180<br />
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