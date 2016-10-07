package com.gt.mesd.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ConfigProperties {

	private Properties prop = null;

	public ConfigProperties() {

		InputStream is = null;
		try {
			this.prop = new Properties();
			is = this.getClass().getResourceAsStream("/config.properties");
			prop.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<String, String> getPropertiesMap() throws IOException {
		ConfigProperties mpc = new ConfigProperties();
		Map<String, String> map = new HashMap<String, String>();
		Set<Object> keys = mpc.getAllKeys();
		for (Object k : keys) {
			String key = (String) k;
			System.out.println(key + ": " + mpc.getPropertyValue(key));
			map.put(key, mpc.getPropertyValue(key));
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