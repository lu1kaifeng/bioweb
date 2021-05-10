package edu.henu.bioweb.control;

import java.util.List;

public class LymphParam extends ControlParam {
    public LymphParam(String dbName,List<String> colList, String jspTemplate) {
        super(dbName,colList, jspTemplate);
    }

    @Override
    public String getTagName() {
        return "lymph";
    }
}
