package edu.henu.bioweb.control;

import java.util.List;

public class SmokingParam extends ControlParam {
    public SmokingParam(String dbName,List<String> colList, String jspTemplate) {
        super(dbName,colList, jspTemplate);
    }

    @Override
    public String getTagName() {
        return "smoking";
    }
}
