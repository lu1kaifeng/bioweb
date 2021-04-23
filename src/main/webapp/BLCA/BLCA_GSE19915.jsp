<%@ page import="edu.henu.bioweb.RPlot" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Biomedical Informatics Institute</title>
</head>
<body>
        
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

</body>
</html>