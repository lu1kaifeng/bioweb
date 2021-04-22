<%@ page import="edu.henu.bioweb.control.ControlParam" %><%--
  Created by IntelliJ IDEA.
  User: lu
  Date: 4/22/2021
  Time: 1:32 PM
  To change this template use File | Settings | File Templates.
--%>

    <%
        ControlParam param =(ControlParam) request.getAttribute("param");
        out.print(param.getTagName());
    %>

