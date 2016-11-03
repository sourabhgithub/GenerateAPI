package com.gt.mesd.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.trustmarkins.mesd.exception.TMKException;

public class PropertiesToMapConverter {

	private Properties prop = null;

	private void getConfigProperties(String fileName) {
		try {
			String path = System.getProperty("user.dir");
			this.prop = new Properties();
			prop.load(new FileInputStream(path + File.separator + ".." + File.separator + "integration" + File.separator + "conf" + File.separator + fileName));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<String, String> getPropertiesMap(String fileName) throws TMKException {
		getConfigProperties(fileName);
		Map<String, String> map = new HashMap<String, String>();
		Set<Object> keys = getAllKeys();
		for (Object k : keys) {
			String key = (String) k;
			// System.out.println(key + ": " + getPropertyValue(key));
			map.put(key, getPropertyValue(key));
		}
		return map;
	}

	private Set<Object> getAllKeys() {
		Set<Object> keys = prop.keySet();
		return keys;
	}

	private String getPropertyValue(String key) {
		return this.prop.getProperty(key);
	}

}