package com.gt.mesd.util;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class MapToJsonConvertor {

	@SuppressWarnings("unchecked")
	public static JSONObject getJsonFromMap(Map<String, Object> map) throws JSONException {
		JSONObject jsonData = new JSONObject();
		for (String key : map.keySet()) {
			Object value = map.get(key);
			if (value instanceof Map<?, ?>) {
				value = getJsonFromMap((Map<String, Object>) value);
			}
			jsonData.put(key, value);
		}
		return jsonData;
	}
}
