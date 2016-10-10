package com.gt.mesd.controller;

import java.io.IOException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.gt.mesd.service.RequestService;
import com.gt.mesd.service.impl.RequestServiceImpl;

public class RequestController {
	public void makeRequestWithJson(JSONObject json, Map<String, String> propMap) throws JSONException, IOException {
		System.out.println("RequestController: makeRequestWithJson()");

		RequestService requestService = new RequestServiceImpl();
		JSONObject transformedJson = requestService.transformJsonToJson(json, propMap);

		// http request logic
	}

	public void makeRequestWithXml(JSONObject json, Map<String, String> propMap) throws JSONException, IOException {
		System.out.println("RequestController: makeRequestWithXml()");

		RequestService requestService = new RequestServiceImpl();
		String transformedXml = requestService.transformJsonToXml(json, propMap);

		// http request logic
	}
}
