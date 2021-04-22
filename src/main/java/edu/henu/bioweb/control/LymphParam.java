package edu.henu.bioweb.control;

import java.util.List;

public class LymphParam extends ControlParam{
    public LymphParam(List<String> colList, String jspTemplate) {
        super(colList, jspTemplate);
    }

    @Override
    public String getTagName() {
        return "lymph";
    }
}
