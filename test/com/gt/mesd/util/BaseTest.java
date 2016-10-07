package com.gt.mesd.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

public class BaseTest {
	@Test
	public void loadProperties() throws IOException {

		ConfigProperties conf = new ConfigProperties();
		Map<String, String> propertiesMap = conf.getPropertiesMap("/request-mapping-gt-to-lt.properties");
	}

	@Test
	public void runTest() throws JSONException, IOException {
		// String json = "{'ipinfo': {'baseUrl':
		// 'http://www.google.com','ip_type': 'Mapped','Location': {'continent':
		// 'north america','latitude': 30.1,'longitude': -81.714,'CountryData':
		// {'country': 'united states','country_code': 'us'},'region':
		// 'southeast','StateData': {'state': 'florida','state_code':
		// 'fl'},'CityData': {'city': 'fleming island','postal_code':
		// '32003','time_zone': -5}}}}";
		String json = JsonToMapConverter.getJsonObject("/gt-request.json");

		JSONObject info = new JSONObject(json);

		Map<String, String> out = new HashMap<String, String>();

		Map<String, String> jsonMap = JsonToMapConverter.parse(info, out);

		ConfigProperties conf = new ConfigProperties();

		Map<String, String> propertiesMap = conf.getPropertiesMap("/request-mapping-gt-to-lt.properties");

		Map<String, String> map3 = new HashMap<String, String>();
		/*
		 * for (Object key : propertiesMap.keySet()) { if (jsonMap.get(key) !=
		 * null) { String value1 = jsonMap.get(key); map3.put(key.toString(),
		 * value1); } }
		 */
		for (Object key : jsonMap.keySet()) {
			String value2 = propertiesMap.get(key);
			if (value2 != null) {
				String value1 = jsonMap.get(key);
				map3.put(value2, value1);
			}
		}
		System.out.println(map3.toString());
	}

}
