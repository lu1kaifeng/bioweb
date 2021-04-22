package edu.henu.bioweb.control;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Calendar;

public class ControlTagSupport extends TagSupport {
    private ControlParam param;
    public int doStartTag() throws JspException {
        try {
            param.render(pageContext);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    public void setParam(ControlParam param) {
        this.param = param;
    }
}
