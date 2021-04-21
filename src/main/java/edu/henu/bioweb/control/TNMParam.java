package edu.henu.bioweb.control;

import java.util.List;

public class TNMParam extends ControlParam{
    public TNMParam(List<String> colList) {
        super(colList);
    }

    @Override
    public String getTagName() {
        return "tnm";
    }
}
