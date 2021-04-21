package edu.henu.bioweb.control;

import java.util.List;

public class HistologicalTypeParam extends ControlParam{
    public HistologicalTypeParam(List<String> colList) {
        super(colList);
    }

    @Override
    public String getTagName() {
        return "histological_type";
    }
}
