package edu.henu.bioweb.control;

import edu.henu.bioweb.Functional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurvivalParam extends ControlParam {
    private static Map<String, String> eventMap;
    public final List<String> options;

    public SurvivalParam(String dbName,List<String> colList, String jspTemplate) {
        super(dbName,colList, jspTemplate);
        if (eventMap == null) {
            eventMap = new HashMap<>();
            eventMap.put("OS", "OS_Event");
            eventMap.put("DFI", "DFI_Event");
            eventMap.put("PFI", "PFI_Event");
            eventMap.put("DSS", "DSS_Event");
            eventMap.put("MFS", "MFS_Event");
            eventMap.put("RFS", "RFS_Event");
            eventMap.put("PFS", "PFS_Event");
            eventMap.put("DMFS", "DMFS_Event");
            eventMap.put("DFS", "DFS_Event");
            eventMap.put("DRFS", "DRFS_Event");
            eventMap.put("LMFS", "LMFS_Event");
            eventMap.put("BMFS", "BMFS_Event");
            eventMap.put("DMS", "DMS_Event");
            eventMap.put("EFS", "EFS_Event");
        }
        this.options = Functional.<String>filter(colList, new Functional.Condition<String>() {
            @Override
            public boolean ifKeep(String elem) {
                return eventMap.containsKey(elem);
            }
        });
    }

    @Override
    public final String getTagName() {
        return "survival";
    }
}
