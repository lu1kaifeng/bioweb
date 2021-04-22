<%@ page import="edu.henu.bioweb.control.ControlParam" %>
<%@ taglib uri="/WEB-INF/control.tld" prefix="m" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lu
  Date: 4/22/2021
  Time: 1:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="/Css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
<%
    List<ControlParam> params = (List<ControlParam>)request.getAttribute("controls");
    for(ControlParam cp : params){
%>
<m:control param='<%=cp%>'/>
<%
    }%>
</body>
</html>
