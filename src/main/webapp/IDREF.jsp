<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="Css/common.css" />
    <link rel="stylesheet" href="Css/probe.css" />
    <script type="text/JavaScript" src="Js/jquery-1.7.2.min.js"></script>
    <script type="text/JavaScript" src="Js/common.js"></script>
</head>
<body>
<div class='top-search-wrap' id="top1">
        <div class='top-search'>
            <a class='logo'>
				郭向前课题组
		    </a>
            <a class='logo2'>
			    生物信息实验室（筹）
		    </a>
        </div>
    </div>
    <div class='top-nav-wrap'>
        <ul class='nav-lv1'>
			<li class='nav-lv1-li'>
				<a href="Index.html" class='top-cate'>Home</a>
			</li>
			<li class='nav-lv1-li'>
				<a href="DatabaseList.jsp" class='top-cate'>Database</a>
			</li>
            <li class='nav-lv1-li'>
				<a href="PersonList.html" class='top-cate'>Member</a>
			</li>
			<li class='nav-lv1-li'>
				<a href="PublicationList.html" class='top-cate'>Publication</a>
			</li>
		</ul>
    </div>    
	<form id="loginForm" method="post" action="GetIDREF">
	<div class='main'>
    <div class="main_data">
        <div class="main_data1">
            <table class="form1">
                <tr>
                    <td><span class="ziti">Gene symbol:</span></td>
                    <td>
                    	<input type="text" class="inputText" name="txtGen" value="<%=(String)request.getAttribute("txtGen")!=null?(String)request.getAttribute("txtGen"):""%>" />
                    	<input type="submit" value="IDREF" style="width:50px;height:25px;font-size:12px;" class="plotBut" />
                    </td>
                </tr>
                <tr>
                    <td><span class="ziti">Data Source:</span></td>
                    <td>
                    	<select id="ddldata" name="ddldata" onChange="window.location=this.value" class="sec1">
                    		<option value="DBGES.jsp">GSE53625</option>
                    		<option value="DBList.jsp">TCGA</option>
                    		<option value="Combind.jsp">Combind</option>
                    	</select>
                     </td>
                </tr>
                <tr>
                    <td><span class="ziti">Mode:</span></td>
                    <td>
                    	<select id="ddlMode" name="ddlMode" class="sec1" onChange="window.location=this.value">
                    		<option value="IDREF.jsp">ID REF</option>
                    		<option value="DBGES.jsp">Average</option>                    		
                    	</select>
                    </td>
                </tr>
                <tr>
                    <td><span class="ziti">ID REF:</span></td>
                    <td>
                    	<select id="ddlIDREF" name="ddlIDREF" class="sec1">
                    		  <% 
                    		     String[] str = (String[])request.getAttribute("idref");
                    		     if(str!=null && str.length > 0){
                    		     for (int i=0;i<str.length;i++){%>       								
      								<option value="<%=str[i]%>" ><%=str[i]%></option>
								 <%}}
							  %>         		
                    	</select>
                    </td>
                </tr>
                <tr>
                    <td><span class="ziti">Survival:</span></td>
                    <td>
                    	<select id="ddlSurvival" name="ddlSurvival" class="sec1">
                    		<option value="0">OS</option>
                    	</select>
                     </td>
                </tr>
            </table>
        </div>
        <div class="main_data2">
            <table class="form1">
            	<tr>
                    <td><span class="ziti">Spilt patients by:</span></td>
                    <td>
                    	<select id="ddlSplit" name="ddlSplit" class="sec1">
                    		<option value="0">Lower 25%</option>
                    		<option value="1">Upper 25%</option>
                    	</select>
                    </td>
                </tr>
            	<tr>
                    <td><span class="ziti">TNM:</span></td>
                    <td>
                    	<select id="tnmstage" name="tnmstage" class="sec1">
                    		<option value="0">All</option>
                    		<option value="1">I</option>
                    		<option value="2">II</option>
                    		<option value="3">III</option>
                    	</select>
                    </td>
                </tr>   
                <tr>
                    <td><span class="ziti">Smoking:</span></td>
                    <td>
                    	<select name="ddlTobacco" class="sec1" id="ddlTobacco">
                    		<option value="2">All</option>                    		
                    		<option value="1">Yes</option>
                    		<option value="0">No</option>
                    	</select>
                    </td>
                </tr>
                <tr>
                    <td><span class="ziti">Alcohol:</span></td>
                    <td>
                    	<select name="ddlAlcohol" class="sec1" id="ddlAlcohol">
                    		<option value="2">All</option>
                    		<option value="1">Yes</option>
                    		<option value="0">No</option>
                    	</select>
                    </td>
                </tr>
            </table>            
        </div>
    </div>
    
    		<%
            	String strtnm = (String)request.getAttribute("tnmstage");
            	String strAlcohol = (String)request.getAttribute("ddlAlcohol");
            	String strTobacco = (String)request.getAttribute("ddlTobacco");
            	String strSplit = (String)request.getAttribute("ddlSplit");
            	String strSurvival = (String)request.getAttribute("ddlSurvival");
            	String strData = (String)request.getAttribute("ddldata");            	
            %>
			<script type="text/javascript">
			     var otnm=document.getElementById("tnmstage");
			     var ocountry=document.getElementById("ddlCountry");
			     var oymph=document.getElementById("ddllymph");
			     var oalcolhol=document.getElementById("ddlAlcohol");
			     var osmoking=document.getElementById("ddlTobacco");
			     var osplit=document.getElementById("ddlSplit");
			     var osurvival=document.getElementById("ddlSurvival");
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
    
    <input type="submit" value="Plot" class="plotBut" />
    <div class="main_plot">
    
        <!-- <img src="<%=(String)request.getAttribute("myimg")%>" name="myimg" /> -->
        <% 
              String[] strs = (String[])request.getAttribute("probe");
              if(str!=null && str.length > 0){
              for (int i=0;i<str.length;i++){%>Probe:<%=str[i]%>
              	
              	  <div style="width:500px;height:500px;"><img src="<%=strs[i]%>"></div>
			  <%}}
        %>
    </div>
</div>       
	</form>
	<div class='bottom'>
		<div>©2016&nbsp;&nbsp;&nbsp;郭向前课题组 </div>
	</div>
</body>
</html>