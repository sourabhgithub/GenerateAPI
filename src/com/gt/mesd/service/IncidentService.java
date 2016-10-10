package com.gt.mesd.service;

import java.io.IOException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public interface IncidentService {

	public JSONObject transformJsonToJson(JSONObject json, Map<String, String> propMap) throws JSONException, IOException;

	public String transformJsonToXml(JSONObject json, Map<String, String> propMap) throws JSONException, IOException;
}
