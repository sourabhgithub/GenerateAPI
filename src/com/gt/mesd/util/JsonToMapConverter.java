package com.gt.mesd.util;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

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

	@SuppressWarnings("resource")
	public static String getJsonObject(String fileName) {
		InputStream is = JsonToMapConverter.class.getClass().getResourceAsStream(fileName);
		Scanner s = new Scanner(is).useDelimiter("\\A");
		String result = s.hasNext() ? s.next() : "";
		return result;
	}

}
