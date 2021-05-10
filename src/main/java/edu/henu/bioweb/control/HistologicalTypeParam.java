package edu.henu.bioweb.control;

import java.util.List;

public class HistologicalTypeParam extends ControlParam {
    public HistologicalTypeParam(String dbName,List<String> colList, String jspTemplate) {
        super(dbName,colList, jspTemplate);
    }


    @Override
    public String getTagName() {
        return "histological_type";
    }
}
