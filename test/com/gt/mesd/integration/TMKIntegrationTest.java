package com.gt.mesd.integration;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.gt.mesd.controller.IncidentController;
import com.gt.mesd.controller.RequestController;
import com.gt.mesd.controller.TaskController;
import com.gt.mesd.util.Constants;
import com.gt.mesd.util.JsonToMapConverter;
import com.gt.mesd.util.PropertiesToMapConverter;
import com.manageengine.servicedesk.actionplugin.executor.ExecutorData;

public class TMKIntegrationTest {
	
	private static Logger LOGGER = Logger.getLogger(TMKIntegration.class.getName());
	
	@Test
	public void TMKIntegrationRealTest() throws Exception{

		LOGGER.info("START Function : TMKIntegrationTest");
		
		PropertiesToMapConverter conf = new PropertiesToMapConverter();
		JSONObject inputJson = new JSONObject(JsonToMapConverter.getJsonObject("/gt-incident.json"));
		ExecutorData data = new ExecutorData(null, inputJson, null, null, null);
		TMKIntegration tmkIntegration = new TMKIntegration();
		tmkIntegration.execute(data);
		
        LOGGER.info("End Function : TMKIntegrationTest");
	}
	
	
	@Test
	public void TMKIntegrationSelfTest() throws JSONException,IOException{

		LOGGER.info("START Function : TMKIntegrationTest");
		
		PropertiesToMapConverter conf = new PropertiesToMapConverter();
		JSONObject inputJson = new JSONObject(JsonToMapConverter.getJsonObject("/gt-incident.json"));
		Map<String, String> propertiesMap = conf.getPropertiesMap("/incident-mapping-gt-to-lt.properties");
		
		String level = inputJson.getString(Constants.LEVEL);
        switch(level){
        	case Constants.REQUEST:
        		RequestController requestController = new RequestController();
        		requestController.makeRequestWithJson(inputJson,propertiesMap);
        		requestController.makeRequestWithXml(inputJson,propertiesMap);
        		break;
        	case Constants.INCIDENT:
        		IncidentController incidentController = new IncidentController();
        		incidentController.makeRequestWithJson(inputJson,propertiesMap);
        		incidentController.makeRequestWithXml(inputJson,propertiesMap);
        		break;
        	case Constants.TASK:
        		TaskController taskController = new TaskController();
        		taskController.makeRequestWithJson(inputJson, propertiesMap);
        		taskController.makeRequestWithJson(inputJson, propertiesMap);
        		break;
        }        
        
        LOGGER.info("End Function : TMKIntegrationTest");
	}

}
