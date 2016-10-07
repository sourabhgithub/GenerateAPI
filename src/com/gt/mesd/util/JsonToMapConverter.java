package com.gt.mesd.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonToMapConverter {

	public static Map<String, String> parse(JSONObject json, Map<String, String> out) throws JSONException {
		@SuppressWarnings("unchecked")
		Iterator<String> keys = json.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			String val = null;
			try {
				JSONObject value = json.getJSONObject(key);
				parse(value, out);
			} catch (Exception e) {
				val = json.getString(key);
			}

			if (val != null) {
				out.put(key, val);
			}
		}
		return out;
	}

}
