package edu.henu.bioweb.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SurvivalParam extends ControlParam{
    public final ArrayList<String> options;
    private static Map<String,String> eventMap;
    public SurvivalParam(List<String> colList){
        super(colList);
        if(eventMap== null){
            eventMap = new HashMap<>();
            eventMap.put("OS","OS_Event");
            eventMap.put("DFI","DFI_Event");
            eventMap.put("PFI","PFI_Event");
            eventMap.put("DSS","DSS_Event");
            eventMap.put("MFS","MFS_Event");
            eventMap.put("RFS","RFS_Event");
            eventMap.put("PFS","PFS_Event");
            eventMap.put("DMFS","DMFS_Event");
            eventMap.put("DFS","DFS_Event");
            eventMap.put("DRFS","DRFS_Event");
            eventMap.put("LMFS","LMFS_Event");
            eventMap.put("BMFS","BMFS_Event");
            eventMap.put("DMS","DMS_Event");
            eventMap.put("EFS","EFS_Event");
        }
        this.options = colList.stream().filter((String i) ->
            eventMap.containsKey(i)
        ).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public final String getTagName() {
        return "survival";
    }
}
