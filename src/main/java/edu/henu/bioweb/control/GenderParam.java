package edu.henu.bioweb.control;

import java.util.List;

public class GenderParam extends ControlParam {
    public GenderParam(String dbName,List<String> colList, String jspTemplate) {
        super(dbName,colList, jspTemplate);
    }


    @Override
    public String getTagName() {
        return "gender";
    }
}
