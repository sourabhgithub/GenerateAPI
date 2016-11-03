package com.gt.mesd.tranformer;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class DataTransformer {

	public static String transformJsonToXml(JSONObject inputJson, Map<String, String> propMap) throws JSONException, IOException {
		JSONObject outJson = transformJsonToJson(inputJson, propMap);

		return XML.toString(outJson);
	}

	// transform JSONObject to JSONObject by replacing keys from given propsFile
	public static JSONObject transformJsonToJson(JSONObject inputJson, Map<String, String> propMap) throws JSONException, IOException {

		JSONObject outJson = new JSONObject();

		@SuppressWarnings("unchecked")
		Iterator<String> keys = inputJson.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			try {
				JSONObject value = inputJson.getJSONObject(key);
				outJson.put(replaceIfExists(propMap, key), transformJsonToJson(value, propMap));
			} catch (Exception e) {
				outJson.put(replaceIfExists(propMap, key), inputJson.get(key));
			}
		}

		return outJson;
	}

	// looks for given key in props and returns transformed key if exists, same
	// key if not
	public static String replaceIfExists(Map<String, String> map, String key) {
		for (Map.Entry<String, String> mapEntry : map.entrySet()) {
			if (mapEntry.getKey().equals(key)) {
				return mapEntry.getValue();
			}
		}
		return key;
	}

}
