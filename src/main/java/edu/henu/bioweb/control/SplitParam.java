package edu.henu.bioweb.control;

import java.util.List;

public class SplitParam extends ControlParam {
    public SplitParam(String dbName,List<String> colList, String jspTemplate) {
        super(dbName,colList, jspTemplate);
    }

    @Override
    public String getTagName() {
        return "split";
    }
}
