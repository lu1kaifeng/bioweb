<%@ page import="edu.henu.bioweb.control.ControlParam" %><%
    ControlParam param =(ControlParam) request.getAttribute("param");
    out.print(param.getTagName());
%>