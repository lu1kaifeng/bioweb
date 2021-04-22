package edu.henu.bioweb.control;

import javax.servlet.ServletException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.util.List;

public class HistologicalTypeParam extends ControlParam{
    public HistologicalTypeParam(List<String> colList,String jspTemplate) {
        super(colList,jspTemplate);
    }


    @Override
    public String getTagName() {
        return "histological_type";
    }
}
