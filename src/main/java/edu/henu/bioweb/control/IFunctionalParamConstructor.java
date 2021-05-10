package edu.henu.bioweb.control;

import java.util.List;

public interface IFunctionalParamConstructor {
    ControlParam constructor(String dbName,List<String> colList);
}
