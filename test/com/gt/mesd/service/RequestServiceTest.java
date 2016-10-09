package com.gt.mesd.service;

import java.io.IOException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.gt.mesd.service.impl.RequestServiceImpl;
import com.gt.mesd.util.JsonToMapConverter;
import com.gt.mesd.util.PropertiesToMapConverter;

public class RequestServiceTest {
	@Test
	public void RequestServiceTest() throws JSONException, IOException{
		PropertiesToMapConverter conf = new PropertiesToMapConverter();
		JSONObject inputJson = new JSONObject(JsonToMapConverter.getJsonObject("/gt-request.json"));
		Map<String, String> propertiesMap = conf.getPropertiesMap("/request-mapping-gt-to-lt.properties");
		
		RequestService requestService = new RequestServiceImpl();
		
		JSONObject transformedJson = requestService.transformJsonToJson(inputJson, propertiesMap);
		String transformedXml = requestService.transformJsonToXml(inputJson, propertiesMap);
		
		System.out.println();
		System.out.println("RequestService Test: ");
		System.out.println(transformedJson);
		System.out.println(transformedXml);
	}
}
