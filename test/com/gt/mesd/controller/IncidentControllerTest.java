package com.gt.mesd.controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.gt.mesd.util.PropertiesToMapConverter;
import com.gt.mesd.util.JsonToMapConverter;


public class IncidentControllerTest {

	@Test
	public void IncidentControllerTest() throws JSONException,IOException{
		final String KEY_LEVEL = "LEVEL";
		final String REQUEST = "Request";
		final String INCIDENT = "Incident";
		final String TASK = "Task";
		
		PropertiesToMapConverter conf = new PropertiesToMapConverter();
		JSONObject inputJson = new JSONObject(JsonToMapConverter.getJsonObject("/gt-incident.json"));
		Map<String, String> propertiesMap = conf.getPropertiesMap("/incident-mapping-gt-to-lt.properties");
		
		System.out.println();
		System.out.println("IncidentController Test:");
		
		String level = inputJson.getString(KEY_LEVEL);
        switch(level){
        	case REQUEST:
        		RequestController requestController = new RequestController();
        		requestController.makeRequestWithJson(inputJson,propertiesMap);
        		requestController.makeRequestWithXml(inputJson,propertiesMap);
        		break;
        	case INCIDENT:
        		IncidentController incidentController = new IncidentController();
        		incidentController.makeRequestWithJson(inputJson,propertiesMap);
        		incidentController.makeRequestWithXml(inputJson,propertiesMap);
        		break;
        	case TASK:
        		TaskController taskController = new TaskController();
        		taskController.makeRequestWithJson(inputJson, propertiesMap);
        		taskController.makeRequestWithJson(inputJson, propertiesMap);
        		break;
        }        
	}
	

}
