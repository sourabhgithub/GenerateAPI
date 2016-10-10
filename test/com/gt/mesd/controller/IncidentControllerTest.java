package com.gt.mesd.controller;

import java.io.IOException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.gt.mesd.util.Constants;
import com.gt.mesd.util.JsonToMapConverter;
import com.gt.mesd.util.PropertiesToMapConverter;

public class IncidentControllerTest {

	@Test
	public void IncidentControllerTest() throws JSONException, IOException {
		PropertiesToMapConverter conf = new PropertiesToMapConverter();
		JSONObject inputJson = new JSONObject(JsonToMapConverter.getJsonObject("/gt-incident.json"));
		Map<String, String> propertiesMap = conf.getPropertiesMap("/incident-mapping-gt-to-lt.properties");

		if (Constants.INCIDENT.equalsIgnoreCase(inputJson.getString(Constants.LEVEL))) {
			IncidentController incidentController = new IncidentController();
			incidentController.makeRequestWithJson(inputJson, propertiesMap);
			incidentController.makeRequestWithXml(inputJson, propertiesMap);
		}
	}

}
