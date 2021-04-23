package edu.henu.bioweb.control;

import javax.servlet.ServletException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ControlParam {
    public abstract String getTagName();
    private static Map<String, IFunctionalParamConstructor> paramConstructorMap;
    protected String jspTemplate;
    public ControlParam(List<String> colList,String jspTemplate){
        this.jspTemplate = jspTemplate;
    }
    public void render(PageContext pageContext) throws ServletException, IOException {
        pageContext.getRequest().setAttribute("param",this);
        pageContext.include(jspTemplate,false);
    }
    public static ControlParam fromCol(String tagName, List<String> colList){
        if(paramConstructorMap == null) {
            paramConstructorMap = new HashMap<>();
            paramConstructorMap.put("gender",(c)-> new GenderParam(c,"/control/gender.jsp"));
            paramConstructorMap.put("histological_type",(c)->  new HistologicalTypeParam(c,"/control/histological_type.jsp"));
            paramConstructorMap.put("survival",(c)->  new SurvivalParam(c,"/control/survival.jsp"));
            paramConstructorMap.put("lymph",(c)->  new LymphParam(c,"/control/lymph.jsp"));
            paramConstructorMap.put("race",(c)->  new RaceParam(c,"/control/race.jsp"));
            paramConstructorMap.put("smoking",(c)->  new SmokingParam(c,"/control/smoking.jsp"));
            paramConstructorMap.put("split",(c)->  new SplitParam(c,"/control/split.jsp"));
            paramConstructorMap.put("tnm",(c)->  new TNMParam(c,"/control/tnm.jsp"));
        }
        return paramConstructorMap.get(tagName).constructor(colList);
    }
}


