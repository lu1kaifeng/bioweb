package edu.henu.bioweb.control;

import java.util.List;

public class SplitParam extends ControlParam{
    public SplitParam(List<String> colList, String jspTemplate) {
        super(colList, jspTemplate);
    }

    @Override
    public String getTagName() {
        return "split";
    }
}
