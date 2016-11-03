package com.gt.mesd.controller;

import java.io.IOException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.gt.mesd.util.Constants;
import com.gt.mesd.util.JsonToMapConverter;
import com.gt.mesd.util.PropertiesToMapConverter;

public class ProblemControllerTest {

	@Test
	public void RequestControllerTest() throws JSONException, IOException {

		PropertiesToMapConverter conf = new PropertiesToMapConverter();
		JSONObject inputJson = new JSONObject(JsonToMapConverter.getJsonObject("/gt-request.json"));
		//Map<String, String> propertiesMap = conf.getPropertiesMap("/request-mapping-gt-to-lt.properties");

		if (Constants.PROBLEM.equalsIgnoreCase(inputJson.getString(Constants.LEVEL))) {
			ProblemController taskController = new ProblemController();
			//taskController.makeRequestWithJson(inputJson, propertiesMap);
		//taskController.makeRequestWithJson(inputJson, propertiesMap);
		}
	}
}
