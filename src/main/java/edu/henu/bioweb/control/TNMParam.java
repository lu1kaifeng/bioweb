package edu.henu.bioweb.control;

import java.util.List;

public class TNMParam extends ControlParam {
    public TNMParam(String dbName,List<String> colList, String jspTemplate) {
        super(dbName,colList, jspTemplate);
    }

    @Override
    public String getTagName() {
        return "tnm";
    }
}
