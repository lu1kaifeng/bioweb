package edu.henu.bioweb.control;

import javax.servlet.ServletException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.util.List;

public class TNMParam extends ControlParam{
    public TNMParam(List<String> colList,String jspTemplate) {
        super(colList,jspTemplate);
    }

    @Override
    public String getTagName() {
        return "tnm";
    }
}
