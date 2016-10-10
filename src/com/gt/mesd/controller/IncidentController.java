package com.gt.mesd.controller;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.gt.mesd.integration.TMKIntegration;
import com.gt.mesd.service.IncidentService;
import com.gt.mesd.service.impl.IncidentServiceImpl;
import com.gt.mesd.util.DataTransformer;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class IncidentController {

	private static Logger LOGGER = Logger.getLogger(IncidentController.class.getName());
	
	public void makeRequestWithJson(JSONObject json, Map<String,String> propMap) throws JSONException, IOException{
		LOGGER.info("START IncidentController: makeRequestWithJson()");
		IncidentService incidentService = new IncidentServiceImpl();
		JSONObject transformedJson = incidentService.transformJsonToJson(json, propMap);
		LOGGER.info("END IncidentController: makeRequestWithJson()");
		LOGGER.info("#################RESPONSE JSON#################");
		LOGGER.info(transformedJson);
		LOGGER.info("#################RESPONSE JSON#################");
		//http request logic
	}
	
	public void makeRequestWithXml(JSONObject json, Map<String,String> propMap) throws JSONException,IOException{
		LOGGER.info("START IncidentController: makeRequestWithXML()");
		IncidentService incidentService = new IncidentServiceImpl();
		String transformedXml = incidentService.transformJsonToXml(json, propMap);
		LOGGER.info("END IncidentController: makeRequestWithXML()");
		LOGGER.info("#################RESPONSE XML#################");
		LOGGER.info(transformedXml);
		LOGGER.info("#################RESPONSE XML#################");
		//http request logic
	}
	
}
