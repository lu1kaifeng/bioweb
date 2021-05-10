package edu.henu.bioweb.control;

import java.util.List;

public class RaceParam extends ControlParam {
    public RaceParam(String dbName,List<String> colList, String jspTemplate) {
        super(dbName,colList, jspTemplate);
    }

    @Override
    public String getTagName() {
        return "race";
    }
}
