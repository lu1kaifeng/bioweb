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
    public ControlParam(List<String> colList){

    }
    public abstract void render(PageContext pageContext) throws ServletException, IOException;
    public static ControlParam fromCol(String tagName, List<String> colList){
        if(paramConstructorMap == null) {
            paramConstructorMap = new HashMap<>();
            paramConstructorMap.put("gender", GenderParam::new);
            paramConstructorMap.put("histological_type", HistologicalTypeParam::new);
            paramConstructorMap.put("survival", SurvivalParam::new);
            paramConstructorMap.put("tnm", TNMParam::new);
        }
        return paramConstructorMap.get(tagName).constructor(colList);
    }
}


