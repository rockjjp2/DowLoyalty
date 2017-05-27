package com.dowloyalty.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PinYinUtil {
	
	public static Map<String,List<String>> getOrderByFirstWord(List<String> list)
	{
		Map<String, List<String>> map=new HashMap<String, List<String>>();  
		List<String> arraylist=new ArrayList<String>();  
		String[] alphatableb =  
            {  
               "A", "B", "C", "D", "E", "F", "G", "H", "I",  
               "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"  
              };  
            for(String a:alphatableb){  
                for(int i=0;i<list.size();i++){//为了排序都返回大写字母  
                    try {
						if(a.equals((PinYin.getGBKpy(list.get(i)).substring(0, 1)).toUpperCase())){  
						    arraylist.add(list.get(i).toString());  
						}
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
                }  
                map.put(a,arraylist);  
                arraylist=new ArrayList<String>();  
        }  
        return map;  
	}
}
