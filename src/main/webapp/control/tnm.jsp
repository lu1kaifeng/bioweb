<%@ page import="edu.henu.bioweb.control.ControlParam" %><%--
  Created by IntelliJ IDEA.
  User: lu
  Date: 4/22/2021
  Time: 1:33 PM
  To change this template use File | Settings | File Templates.
--%>

<%
//    ControlParam param =(ControlParam) request.getAttribute("param");
//    out.print(param.getTagName());
%>
<tr>
    <td><span class="text-left lead">TNM:</span></td>
<link href="/Css/menuStyle.css" rel="stylesheet" />
<select id="tnm" name="tnm" style="width:auto;" class="form-control">
    <option value="0">All</option>
    <option value="1">Stage I</option>
    <option value="2">Stage II</option>
    <option value="3">Stage III</option>
    <option value="4">Stage IV</option>
</select>
</tr>
