<%@ tag import="java.sql.Connection" %>
<%@ tag import="java.sql.Statement" %>
<%@ tag import="java.sql.ResultSet" %>
<%@ tag import="javax.naming.Context" %>
<%@ tag import="javax.sql.DataSource" %>
<%@ tag import="javax.naming.InitialContext" %>
<%@ tag import="javax.naming.NamingException" %>
<%@ tag import="java.util.ArrayList" %>
<%@ tag import="edu.henu.bioweb.control.ControlParam" %>
<%@ tag import="java.util.stream.Collectors" %>
<%@ tag import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: lu
  Date: 4/22/2021
  Time: 10:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ tag language="java" %>
<%
    try {
        Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    Connection conn;
    Statement stmt;
    ResultSet rs;

    Context initCtx;
    Context ctx;
    Object  obj;
    DataSource ds = null;
    try {
        initCtx = new InitialContext();
        ctx = (Context)initCtx.lookup("java:comp/env");
        obj = (Object)ctx.lookup("jdbc/SS"+((String)request.getAttribute("dbName")).toUpperCase());//jdbc/SSBRCA2
        ds = (DataSource)obj;

    } catch (NamingException e) {
        e.printStackTrace();
    }

    try {
        conn = ds.getConnection();
        stmt = conn.createStatement();
        ArrayList<String> tables = new ArrayList<>();
        rs = stmt.executeQuery("show tables");
        while(rs.next()){
            if(rs.getString(1).matches("sample_(.*)"))
                tables.add(rs.getString(1).substring(7));
        }

        %><div>
    <div  style="display: inline-block"><span class="text-left lead">Data Source:</span></div><div  style="display: inline-block"><select onchange="location = this.value;" name="sample"  class="form-control" >
<%for(String s : tables){
            %>
    <option <%=s.equals(request.getAttribute("sample"))?"selected":""%> value='<%="/plot/"+((String)request.getAttribute("dbName"))+"/"+s%>'><%=s%></option>
<%
        }%>
</select></div></div>
    <%
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }

%>


