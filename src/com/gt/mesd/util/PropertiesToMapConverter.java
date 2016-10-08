package com.gt.mesd.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesToMapConverter {

	private Properties prop = null;

	public void getConfigProperties(String fileName) {

		InputStream is = null;
		try {
			this.prop = new Properties();
			is = this.getClass().getResourceAsStream(fileName);
			prop.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<String, String> getPropertiesMap(String fileName) throws IOException {
		getConfigProperties(fileName);
		Map<String, String> map = new HashMap<String, String>();
		Set<Object> keys = getAllKeys();
		for (Object k : keys) {
			String key = (String) k;
			//System.out.println(key + ": " + getPropertyValue(key));
			map.put(key, getPropertyValue(key));
		}
		return map;
	}

	public Set<Object> getAllKeys() {
		Set<Object> keys = prop.keySet();
		return keys;
	}

	public String getPropertyValue(String key) {
		return this.prop.getProperty(key);
	}

}