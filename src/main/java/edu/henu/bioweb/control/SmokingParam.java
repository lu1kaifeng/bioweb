package edu.henu.bioweb.control;

import java.util.List;

public class SmokingParam extends ControlParam {
    public SmokingParam(List<String> colList, String jspTemplate) {
        super(colList, jspTemplate);
    }

    @Override
    public String getTagName() {
        return "smoking";
    }
}
