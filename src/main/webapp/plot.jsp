<%@ page import="edu.henu.bioweb.control.ControlParam" %>
<%@ taglib uri="/WEB-INF/control.tld" prefix="m" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lu
  Date: 4/22/2021
  Time: 1:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Biomedical Informatics Institute</title>
    <link rel="stylesheet" href="/Css/common.css" />
    <link rel="stylesheet" href="/Css/data.css" />
    <script type="text/JavaScript" src="/Js/jquery-1.7.2.min.js"></script>
    <script type="text/JavaScript" src="/Js/common.js"></script>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet" />
    <link href="/Css/menuStyle.css" rel="stylesheet" />
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
                workerId = window.open("/BLCA/geneList.jsp", "", paramers);
                //workerId = window.open("geneList.jsp", "", paramers);
            }
            else{
                var strurl="/BLCA/geneList.jsp?id="+sel;
                //var strurl="geneList.jsp?id="+sel;
                workerId = window.open(strurl, "", paramers);
            }
        }
        function lyno(){
            var ddlstage = document.getElementById('tnm');
            var ddlly = document.getElementById('lymph');
            for(i=0;i<ddlstage.options.length;i++){
                if(ddlstage.options[i].value == '1')
                {
                    if(ddlstage.options[i].selected){
                        for(i=0;i<ddlly.options.length;i++){
                            if(ddlly.options[i].value == '0')
                            {
                                ddlly.options[i].selected = true;

                            }
                        }
                    }
                }
            }
        }
        function init() {
            document.getElementById('plot_upload_form').onsubmit=function() {
                document.getElementById('plot_upload_form').target = 'upload_target'; //'upload_target' is the name of the iframe
            }
        }
        init()
    </script>
</head>
<body>

    <div class="container" style="padding:0;margin-left:0px;width:1200px;background-color:#747070;">
        <div class="navbar-header" style="background-color:#747070;float:left;margin-left:0px;min-width:380px;padding:0;">
            <a class="navbar-brand">
                <img src="/img/research.png" alt="Institute of Biotechnology" width="50" style="margin-top: -15px;" />
            </a>
            <div style="color:white;line-height:50px;font-size:20px;"><nobr>Biomedical Informatics Institute</nobr></div>
        </div>
        <div style="min-width:780px;float:left;">
            <div style="min-width:780px;margin:auto;font-size:22px;background-color:#747070;padding-left:50px;">
                <a class="colorNavulli" href="/Index.html" style="color:white">Home</a>
                <a class="colorNavulli" href="/members.html" style="color:white">Member</a>
                <a class="colorNavulli" href="/DatabaseList.jsp" style="color:white;background-color:black">LOGpc Database</a>
                <a class="colorNavulli" href="/results.html" style="color:white">Publication</a>
                <a class="colorNavulli" href="/NewsList.html" style="color:white">News</a>
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
    <form id="plot_upload_form" method="post" action='<%="/"+((String)request.getAttribute("dbName")).toUpperCase()+((String) request.getAttribute("sample")).toUpperCase()%>' target="upload_target">
<table style="width:925px;border-collapse:separate; border-spacing:10px 10px;margin:auto;">
    <tbody>
    <tr>
        <td style="width:50%;border:black 1px solid;padding:10px">
            <table class="form1" style="border-collapse:separate; border-spacing:0px 10px;">
                <tbody>
                <tr>
                    <td><span class="text-left lead">Gene symbol:</span></td>
                    <td>
                        <input type="text" class="form-control" id="gene" style="width:150px;display:inline"
                               name="gene" value="">


                    </td>
                </tr>
                <tr>
                    <td>
                        <h:sample_select/>
                    </td>
                </tr>
                </tbody>
            </table>
        </td>
        <td style="width:50%;border:black 1px solid;padding:10px">
            <table class="form1" style="border-collapse:separate; border-spacing:0px 10px;">
                <tbody>
                <%
                    List<ControlParam> params = (List<ControlParam>) request.getAttribute("controls");
                    for (ControlParam cp : params) {
                %>
                <div style="padding: 0.5em"><m:control param='<%=cp%>'/></div>

                <%
                    }%>
                </tbody>
            </table>
        </td>
    </tr>
    </tbody>
</table>
        <input type="submit" value="Kaplan-Meier plot" style="width:180px;height:50px;" class="btn btn-info btn-lg" />
    </form>

</div>
    <div class="embed-responsive embed-responsive-16by9">
    <iframe class="embed-responsive-item" id="upload_target" name="upload_target" src='<%="/"+((String)request.getAttribute("dbName")).toUpperCase()+((String) request.getAttribute("sample")).toUpperCase()%>' style="border:0px solid #fff;width: 100%"></iframe>
    </div>
</body>
</html>
