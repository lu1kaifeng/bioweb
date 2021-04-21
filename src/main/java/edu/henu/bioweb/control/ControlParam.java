package edu.henu.bioweb.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ControlParam {
    public abstract String getTagName();
    private static Map<String, IFunctionalParamConstructor> paramConstructorMap;
    public ControlParam(List<String> colList){

    }
    public static ControlParam fromCol(String tagName, List<String> colList){
        if(paramConstructorMap == null) {
            paramConstructorMap = new HashMap<>();
            paramConstructorMap.put("gender", GenderParam::new);
            paramConstructorMap.put("histological_type", HistologicalTypeParam::new);
            paramConstructorMap.put("survival", SurvivalParam::new);
            paramConstructorMap.put("tnm", SurvivalParam::new);
        }
        return paramConstructorMap.get(tagName).constructor(colList);
    }
}


