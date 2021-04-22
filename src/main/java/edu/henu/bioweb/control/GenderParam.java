package edu.henu.bioweb.control;

import javax.servlet.ServletException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.util.List;

public class GenderParam extends ControlParam{
    public GenderParam(List<String> colList) {
        super(colList);
    }

    @Override
    public void render(PageContext pageContext) throws ServletException, IOException {
        pageContext.getRequest().setAttribute("param",this);
        pageContext.include("/control/gender.jsp",true);
    }

    @Override
    public String getTagName() {
        return "gender";
    }
}
