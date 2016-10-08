package com.gt.mesd.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.gt.mesd.tranformer.DataTransformer;

public class BaseTest {
	@Test
	public void loadProperties() throws IOException {

		PropertiesToMapConverter conf = new PropertiesToMapConverter();
		Map<String, String> propertiesMap = conf.getPropertiesMap("/request-mapping-gt-to-lt.properties");
	}

	@Test
	public void runTest() throws JSONException, IOException {
		String json = JsonToMapConverter.getJsonObject("/gt-request.json");

		JSONObject info = new JSONObject(json);

		Map<String, String> out = new HashMap<String, String>();

		Map<String, String> jsonMap = JsonToMapConverter.parse(info, out);

		PropertiesToMapConverter conf = new PropertiesToMapConverter();

		Map<String, String> propertiesMap = conf.getPropertiesMap("/request-mapping-gt-to-lt.properties");

		Map<String, Object> map3 = new HashMap<String, Object>();

		for (Object key : jsonMap.keySet()) {
			String value2 = propertiesMap.get(key);
			if (value2 != null) {
				Object value1 = jsonMap.get(key);
				map3.put(value2, value1);
			}
		}
		JSONObject jsonObject = MapToJsonConvertor.getJsonFromMap(map3);
		System.out.println(jsonObject);
	}
	
	@Test
	public void JsonTransformTest() throws JSONException, IOException{
		PropertiesToMapConverter conf = new PropertiesToMapConverter();
		JSONObject inputJson = new JSONObject(JsonToMapConverter.getJsonObject("/gt-request.json"));
		Map<String, String> propertiesMap = conf.getPropertiesMap("/request-mapping-gt-to-lt.properties");
		
		JSONObject outJson = DataTransformer.transformJsonToJson(inputJson, propertiesMap);
		
		System.out.println();
		System.out.println("JSON Transform Test: ");
		System.out.println(outJson);
	}
	
	@Test
	public void NestedJsonTransformTest() throws JSONException, IOException{
		PropertiesToMapConverter conf = new PropertiesToMapConverter();
		JSONObject inputJson = new JSONObject(JsonToMapConverter.getJsonObject("/gt-request-nestedjson.json"));
		Map<String, String> propertiesMap = conf.getPropertiesMap("/request-mapping-gt-to-lt.properties");
		
		JSONObject outJson = DataTransformer.transformJsonToJson(inputJson, propertiesMap);
		
		System.out.println();
		System.out.println("Nested JSON Transform Test: ");
		System.out.println(outJson);
	}

	@Test
	public void XmlTransformTest() throws JSONException, IOException{
		PropertiesToMapConverter conf = new PropertiesToMapConverter();
		JSONObject inputJson = new JSONObject(JsonToMapConverter.getJsonObject("/gt-request.json"));
		Map<String, String> propertiesMap = conf.getPropertiesMap("/request-mapping-gt-to-lt.properties");
		
		String xml = DataTransformer.transformJsonToXml(inputJson, propertiesMap);
		
		System.out.println();
		System.out.println("XML Transform Test: ");
		System.out.println(xml);
	}
	
	@Test
	public void NestedXmlTransformTest() throws JSONException, IOException{
		PropertiesToMapConverter conf = new PropertiesToMapConverter();
		JSONObject inputJson = new JSONObject(JsonToMapConverter.getJsonObject("/gt-request-nestedjson.json"));
		Map<String, String> propertiesMap = conf.getPropertiesMap("/request-mapping-gt-to-lt.properties");
		
		String xml = DataTransformer.transformJsonToXml(inputJson, propertiesMap);
		
		System.out.println();
		System.out.println("Nested XML Transform Test: ");
		System.out.println(xml);
	}
}
