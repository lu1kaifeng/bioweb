package edu.henu.bioweb.BLCA;

import com.mysql.cj.protocol.a.result.TextBufferRow;
import edu.henu.bioweb.control.ControlParam;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BLCAFront extends HttpServlet {
    List<String> listCol =  new ArrayList<>();
    Map<String,String> eventMap = new HashMap<>();
    public BLCAFront(){
        super();
        listCol.add("perid");
        listCol.add("GSM");
        listCol.add("family_history");
        listCol.add("OS");
        listCol.add("OS_Event");
        listCol.add("DFI");
        listCol.add("DFI_Event");
        listCol.add("PFI");
        listCol.add("PFI_Event");
        listCol.add("DSS");
        listCol.add("DSS_Event");
        listCol.add("MFS");
        listCol.add("MFS_Event");
        listCol.add("RFS");
        listCol.add("RFS_Event");
        listCol.add("PFS");
        listCol.add("PFS_Event");
        listCol.add("DMFS");
        listCol.add("DMFS_Event");
        listCol.add("DFS");
        listCol.add("DFS_Event");
        listCol.add("DRFS");
        listCol.add("DRFS_Event");
        listCol.add("LMFS");
        listCol.add("LMFS_Event");
        listCol.add("BMFS");
        listCol.add("BMFS_Event");
        listCol.add("DMS");
        listCol.add("DMS_Event");
        listCol.add("EFS");
        listCol.add("EFS_Event");

    }
    private List<ControlParam> getCols(String dbName, String sampleName){
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
            obj = (Object)ctx.lookup("jdbc/SS"+dbName.toUpperCase());//jdbc/SSBRCA2
            ds = (DataSource)obj;

        } catch (NamingException e) {
            e.printStackTrace();
        }

        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
            ArrayList<String> cols = new ArrayList<>();
            rs = stmt.executeQuery("describe sample_"+sampleName.toLowerCase());
            while(rs.next()){
                cols.add(rs.getString(1));
            }
            cols.add("survival");
            cols.add("split");
            return cols.stream().filter(s -> !listCol.contains(s)).map((String s)->ControlParam.fromCol(s,cols)).collect(Collectors.toList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        String pathInfo = req.getPathInfo(); // /{value}/test
        String[] pathParts = pathInfo.split("/");
        String dbName = pathParts[1]; // {value}
        if(pathParts.length > 2) {
            String sample = pathParts[2]; // test
            List<ControlParam> cols = getCols(dbName,sample);
            req.setAttribute("controls",cols);
            req.setAttribute("dbName",dbName);
            req.setAttribute("sample",sample);
            req.getRequestDispatcher("/plot.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
