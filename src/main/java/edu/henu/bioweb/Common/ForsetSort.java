package edu.henu.bioweb.Common;

import java.util.ArrayList;
import java.util.List;

public class ForsetSort {
	public String strPvalue;
 	public String strHR;
 	public String strLow;
 	public String strup;
 	public String strName;
 	
 	public List<ForsetSort> GetSortList(List<ForsetSort> list){
 		List<ForsetSort> listPoor = new ArrayList<ForsetSort>();  		
 		List<ForsetSort> listGood = new ArrayList<ForsetSort>();
 		List<ForsetSort> resultlist = new ArrayList<ForsetSort>();
 		for(int i=0;i<list.size();i++){
 			if(Double.parseDouble(list.get(i).strHR) < 1){
 				listGood.add(list.get(i));
 			}
 			else{
 				listPoor.add(list.get(i));
 			}
 		}
 		GetSort(listPoor);
 		GetSort(listGood);
 		for(int i=0;i<listPoor.size();i++){
 			resultlist.add(listPoor.get(i));
 		}
 		for(int i=0;i<listGood.size();i++){
 			resultlist.add(listGood.get(i));
 		}
 		return resultlist;
 	}
 	
 	public List<ForsetSort> GetSort(List<ForsetSort> list){
 		ForsetSort strTemp;
 		for(int i=0;i<list.size()-1;i++){
 			for(int j=0;j<list.size()-i-2;j++){ 				
 				if(Double.parseDouble(list.get(j).strPvalue) > Double.parseDouble(list.get(j+1).strPvalue)){
 	 				strTemp = list.get(j);
 	 				list.set(j, list.get(j+1));
 	 				list.set(j+1, strTemp);
 	 			}
 			}
 		}
 		return list;
 	}
}
