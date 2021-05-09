package edu.henu.bioweb.control;

import javax.servlet.ServletException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ControlParam {
    private static Map<String, IFunctionalParamConstructor> paramConstructorMap;
    protected String jspTemplate;
    public ControlParam(List<String> colList, String jspTemplate) {
        this.jspTemplate = jspTemplate;
    }
    static {
        paramConstructorMap = new HashMap<>();
        paramConstructorMap.put("gender", new IFunctionalParamConstructor() {
            @Override
            public ControlParam constructor(List<String> colList) {
                return new GenderParam(colList, "/control/gender.jsp");
            }
        });
        paramConstructorMap.put("histological_type", new IFunctionalParamConstructor() {
            @Override
            public ControlParam constructor(List<String> colList) {
                return  new HistologicalTypeParam(colList, "/control/histological_type.jsp");
            }
        });
        paramConstructorMap.put("survival", new IFunctionalParamConstructor() {
            @Override
            public ControlParam constructor(List<String> colList) {
                return new SurvivalParam(colList, "/control/survival.jsp");
            }
        });
        paramConstructorMap.put("lymph", new IFunctionalParamConstructor() {
            @Override
            public ControlParam constructor(List<String> colList) {
                return new LymphParam(colList, "/control/lymph.jsp");
            }
        });
        paramConstructorMap.put("race", new IFunctionalParamConstructor() {
            @Override
            public ControlParam constructor(List<String> colList) {
                return new RaceParam(colList, "/control/race.jsp");
            }
        });
        paramConstructorMap.put("smoking", new IFunctionalParamConstructor() {
            @Override
            public ControlParam constructor(List<String> colList) {
                return new SmokingParam(colList, "/control/smoking.jsp");
            }
        } );
        paramConstructorMap.put("split", new IFunctionalParamConstructor() {
            @Override
            public ControlParam constructor(List<String> colList) {
                return new SplitParam(colList, "/control/split.jsp");
            }
        });
        paramConstructorMap.put("tnm", new IFunctionalParamConstructor() {
            @Override
            public ControlParam constructor(List<String> colList) {
                return new TNMParam(colList, "/control/tnm.jsp");
            }
        });
    }
    public static ControlParam fromCol(String tagName, List<String> colList) {
        return paramConstructorMap.get(tagName).constructor(colList);
    }

    public abstract String getTagName();

    public void render(PageContext pageContext) throws ServletException, IOException {
        pageContext.getRequest().setAttribute("param", this);
        pageContext.include(jspTemplate, false);
    }
}


