package edu.henu.bioweb.control;

import java.util.List;

public class RaceParam extends ControlParam {
    public RaceParam(List<String> colList, String jspTemplate) {
        super(colList, jspTemplate);
    }

    @Override
    public String getTagName() {
        return "race";
    }
}
