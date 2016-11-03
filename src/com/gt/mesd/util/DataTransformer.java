package com.gt.mesd.util;

import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.trustmarkins.mesd.exception.TMKException;

public class DataTransformer {

	public static String transformJsonToXml(JSONObject inputJson, Map<String, String> propMap) throws TMKException {
		try {
			JSONObject outJson = transformJsonToJson(inputJson, propMap);
			return XML.toString(outJson);
		} catch (JSONException jsonException) {
			throw new TMKException("Exception in transform Json To XML");
		}
	}

	// transform JSONObject to JSONObject by replacing keys from given propsFile
	public static JSONObject transformJsonToJson(JSONObject inputJson, Map<String, String> propMap) throws TMKException, JSONException {
		JSONObject outJson = new JSONObject();

		@SuppressWarnings("unchecked")
		Iterator<String> keys = inputJson.keys();
		String replaceIfExists = null;

		while (keys.hasNext()) {
			String key = keys.next();
			try {
				JSONObject value = inputJson.getJSONObject(key);
				replaceIfExists = replaceIfExists(propMap, key);
				if (replaceIfExists != null && replaceIfExists != "") {
					outJson.put(replaceIfExists, transformJsonToJson(value, propMap));
				}
			} catch (JSONException e) {
				replaceIfExists = replaceIfExists(propMap, key);
				if (replaceIfExists != null && replaceIfExists != "") {
					outJson.put(replaceIfExists, inputJson.get(key));
				}
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
		return null;
	}

}
