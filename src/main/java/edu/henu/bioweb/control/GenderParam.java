package edu.henu.bioweb.control;

import java.util.List;

public class GenderParam extends ControlParam {
    public GenderParam(List<String> colList, String jspTemplate) {
        super(colList, jspTemplate);
    }


    @Override
    public String getTagName() {
        return "gender";
    }
}
