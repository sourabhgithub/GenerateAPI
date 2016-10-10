package com.gt.mesd.controller;

import java.io.IOException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.gt.mesd.service.IncidentService;
import com.gt.mesd.service.impl.IncidentServiceImpl;
import com.gt.mesd.util.DataTransformer;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class IncidentController {

	public void makeRequestWithJson(JSONObject json, Map<String,String> propMap) throws JSONException, IOException{
		System.out.println("IncidentController: makeRequestWithJson()");
		
		IncidentService incidentService = new IncidentServiceImpl();
		JSONObject transformedJson = incidentService.transformJsonToJson(json, propMap);
		
		//http request logic
	}
	
	public void makeRequestWithXml(JSONObject json, Map<String,String> propMap) throws JSONException,IOException{
		System.out.println("IncidentController: makeRequestWithXml()");
		
		IncidentService incidentService = new IncidentServiceImpl();
		String transformedXml = incidentService.transformJsonToXml(json, propMap);

		//http request logic
	}
	
}
