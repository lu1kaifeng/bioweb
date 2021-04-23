package edu.henu.bioweb.control;

import java.util.List;

public class TNMParam extends ControlParam {
    public TNMParam(List<String> colList, String jspTemplate) {
        super(colList, jspTemplate);
    }

    @Override
    public String getTagName() {
        return "tnm";
    }
}
