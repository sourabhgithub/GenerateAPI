package com.gt.mesd.service;

import java.io.IOException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.gt.mesd.service.impl.IncidentServiceImpl;
import com.gt.mesd.util.JsonToMapConverter;
import com.gt.mesd.util.PropertiesToMapConverter;

public class IncidentServiceTest {
	@Test
	public void IncidentServiceTest() throws JSONException, IOException{
		PropertiesToMapConverter conf = new PropertiesToMapConverter();
		JSONObject inputJson = new JSONObject(JsonToMapConverter.getJsonObject("/gt-incident.json"));
		Map<String, String> propertiesMap = conf.getPropertiesMap("/incident-mapping-gt-to-lt.properties");
		
		IncidentService incidentService = new IncidentServiceImpl();
		
		JSONObject transformedJson = incidentService.transformJsonToJson(inputJson, propertiesMap);
		String transformedXml = incidentService.transformJsonToXml(inputJson, propertiesMap);
		
		System.out.println();
		System.out.println("IncidentService Test: ");
		System.out.println(transformedJson);
		System.out.println(transformedXml);
	}
}
