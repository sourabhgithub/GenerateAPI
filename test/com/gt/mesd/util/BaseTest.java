package com.gt.mesd.util;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class BaseTest {

	PropertiesToMapConverter conf = null;
	Map<String, String> propertiesMap = null;
	JSONObject inputJson = null;
	String json = null;

	@Before
	public void beforeTest() throws JSONException, IOException {
		conf = new PropertiesToMapConverter();
		inputJson = new JSONObject(JsonToMapConverter.getJsonObject("/gt-incident.json"));
		propertiesMap = conf.getPropertiesMap("/request-mapping-gt-to-lt.properties");


	}

	@Test
	public void JsonTransformTest() throws JSONException, IOException {
		
		JSONObject outJson = DataTransformer.transformJsonToJson(inputJson, propertiesMap);
		assertNotNull(outJson);
		System.out.println();
		System.out.println("Json Transform Test: ");
		System.out.println(outJson);
	}

	@Test
	public void XmlTransformTest() throws JSONException, IOException {
		String xml = DataTransformer.transformJsonToXml(inputJson, propertiesMap);
		assertNotNull(xml);
		System.out.println();
		System.out.println("XML Transform Test: ");
		System.out.println(xml);
	}

}
