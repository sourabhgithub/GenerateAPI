package com.gt.mesd.integration;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.gt.mesd.controller.IncidentController;
import com.gt.mesd.controller.RequestController;
import com.gt.mesd.controller.ProblemController;
import com.gt.mesd.util.Constants;
import com.gt.mesd.util.JsonToMapConverter;
import com.gt.mesd.util.LoggerSetup;
import com.gt.mesd.util.PropertiesToMapConverter;
import com.manageengine.servicedesk.actionplugin.executor.ExecutorData;

public class TMKIntegrationTest {

	@Test
	public void TMKIntegrationRealTestWhenDiffJsonIsNull() throws Exception {
		JSONObject dataJSON = new JSONObject(JsonToMapConverter.getJsonObject("gt-incident.json"));
		JSONObject diffJSON = null;
		ExecutorData data = new ExecutorData(null, dataJSON, diffJSON, null, null);
		TMKIntegration tmkIntegration = new TMKIntegration();
		tmkIntegration.execute(data);

	}

	@Test
	public void TMKIntegrationRealTestWhenDiffJsonIsNotNull() throws Exception {

		JSONObject dataJSON = new JSONObject(JsonToMapConverter.getJsonObject("gt-request.json"));
		ExecutorData data = new ExecutorData(null, dataJSON, null, null, null);
		TMKIntegration tmkIntegration = new TMKIntegration();
		tmkIntegration.execute(data);

	}

	@Test
	public void TMKIntegrationSelfTest() throws Exception {
		Logger LOGGER = LoggerSetup.getConfiguration(String.valueOf("100"));
		PropertiesToMapConverter conf = new PropertiesToMapConverter();
		JSONObject inputJson = new JSONObject(JsonToMapConverter.getJsonObject("gt-request.json"));
		Map<String, String> propertiesMap = conf.getPropertiesMap("request-mapping-gt-to-lt.properties");

		String level = inputJson.getString(Constants.LEVEL);
		switch (level) {
		case Constants.REQUEST:
			RequestController requestController = new RequestController();
			requestController.processRequest(inputJson, propertiesMap);
			/*requestController.makeRequestWithXml(inputJson, propertiesMap);*/
			break;
		/*case Constants.INCIDENT:
			IncidentController incidentController = new IncidentController();
			incidentController.processRequest(inputJson, propertiesMap);
			incidentController.makeIncidentWithXml(inputJson, propertiesMap);
			break;
		case Constants.PROBLEM:
			ProblemController problemController = new ProblemController();
			problemController.makeRequestWithJson(inputJson, propertiesMap);
			problemController.makeRequestWithJson(inputJson, propertiesMap);
			break;*/
		}

	}

}
