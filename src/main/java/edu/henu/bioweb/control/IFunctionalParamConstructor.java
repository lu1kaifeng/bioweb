package edu.henu.bioweb.control;

import java.util.List;

@FunctionalInterface
public interface IFunctionalParamConstructor {
    ControlParam constructor(List<String> colList);
}
