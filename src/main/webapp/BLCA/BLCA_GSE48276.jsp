<%@ page import="edu.henu.bioweb.RPlot" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Biomedical Informatics Institute</title>
    
</head>
<body>
    <div class="container" style="text-shadow: none; padding-bottom: 22px;min-height:350px; width: 900px;margin:auto; border: 1px solid #303030; margin-top: 25px;">
    	<table>
    		<tr>
    			<td style="width:900px;">
    				<div style="width:500px;margin:auto;padding-top:10px;">
    					<img src="<%=(String)request.getAttribute("myimg")%>" name="myimg" style="width:500px;height:500px;border:none;" onerror="whenError(this)" />
    				</div>
    			</td>
    		</tr>
			<%
              String[] str = (String[])request.getAttribute("idref");
                RPlot[] strs = (RPlot[])request.getAttribute("probe");
              if(str!=null && str.length > 0){
              for (int i=0;i<str.length;i++){%>              	              	
              	  <tr>
              	  	<td style="width:900px;">
              	  		<div style="width:500px;margin:auto;">
              	  			<img src="<%=strs[i].toString()%>" style="width:500px;height:500px;" />
              	  		</div>
              	  	</td>
              	  </tr>
			  <%}}
            %>
            <%
              String[] str2 = (String[])request.getAttribute("idref2");
                RPlot[] strs2 = (RPlot[])request.getAttribute("probe2");
              if(str2!=null && str2.length > 0){
              for (int i=0;i<str2.length;i++){%>              	              	
              	  <tr>
              	  	<td style="width:900px;">
              	  		<div style="width:500px;margin:auto;">
              	  			<img src="<%=strs2[i].toString()%>" style="width:500px;height:500px;" />
              	  		</div>
              	  	</td>
              	  </tr>
			  <%}}
            %>    		
    	</table>        
    </div>
</body>
</html>