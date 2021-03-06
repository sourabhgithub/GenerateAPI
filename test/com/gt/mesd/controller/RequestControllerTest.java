package com.gt.mesd.controller;

import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;

import com.gt.mesd.util.Constants;
import com.gt.mesd.util.JsonToMapConverter;
import com.gt.mesd.util.PropertiesToMapConverter;

public class RequestControllerTest {

	@Test
	public void processRequest() throws Exception {

		PropertiesToMapConverter conf = new PropertiesToMapConverter();
		JSONObject inputJson = new JSONObject(JsonToMapConverter.getJsonObject("/gt-request.json"));
		Map<String, String> propertiesMap = conf.getPropertiesMap("/request-mapping-gt-to-lt.properties");

		if (Constants.REQUEST.equalsIgnoreCase(inputJson.getString(Constants.LEVEL))) {
			RequestController requestController = new RequestController();
			requestController.processRequest(inputJson, propertiesMap);
			// requestController.makeRequestWithXml(inputJson, propertiesMap);
		}

	}

}
