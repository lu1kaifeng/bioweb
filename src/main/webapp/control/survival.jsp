<%@ page import="edu.henu.bioweb.control.ControlParam" %>
<%@ page import="edu.henu.bioweb.control.SurvivalParam" %><%--
  Created by IntelliJ IDEA.
  User: lu
  Date: 4/22/2021
  Time: 1:32 PM
  To change this template use File | Settings | File Templates.
--%>
<link href="/Css/menuStyle.css" rel="stylesheet" />
<tr>
    <td><span class="text-left lead">Survival:</span></td>
<select id="survival" name="survival" class="form-control" style="width:auto;">
<%
    SurvivalParam param =(SurvivalParam) request.getAttribute("param");
    out.print(param.getTagName());
    int i =0;
    for(String opt : param.options){
%>

    <option value='<%=i%>'><%=opt%></option>

<%
    i++;
    }%>
</select>
</tr>