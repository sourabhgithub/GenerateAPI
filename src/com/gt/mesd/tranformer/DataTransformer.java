package com.gt.mesd.tranformer;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.gt.mesd.util.PropertiesToMapConverter;
//json in, output -> json/xml configurable
//json function(String json,String propFile(use config file),boolean isXml)
//json can be nested
//json -> prop lookup in file and replace by value

public class DataTransformer {
	
	public static String transformJsonToXml(JSONObject inputJson, Map<String,String> propMap) throws JSONException, IOException{
		JSONObject outJson = transformJsonToJson(inputJson, propMap);
				
		return XML.toString(outJson);
	}
	
	//transform JSONObject to JSONObject by replacing keys from given propsFile
	public static JSONObject transformJsonToJson(JSONObject inputJson, Map<String,String> propMap) throws JSONException, IOException{
		//JSONObject json = new JSONObject(input);
		PropertiesToMapConverter propertiesToMapConverter = new PropertiesToMapConverter();
		
	    JSONObject outJson = new JSONObject();
	    
	    Iterator<String> keys = inputJson.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			try {
				JSONObject value = inputJson.getJSONObject(key);
				outJson.put(replaceIfExists(propMap,key), transformJsonToJson(value, propMap));
			} catch (Exception e) {
				outJson.put(replaceIfExists(propMap,key), inputJson.get(key));
			}
		}
	    
		return outJson;
	}	
	
	//looks for given key in props and returns transformed key if exists, same key if not 
	public static String replaceIfExists(Map<String,String> map,String key){
		for(Map.Entry<String, String> mapEntry : map.entrySet()){
			if(mapEntry.getKey().equals(key)){
				return mapEntry.getValue();
			}
		}
		return key;
	}

}
