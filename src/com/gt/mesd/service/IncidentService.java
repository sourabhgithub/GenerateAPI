package com.gt.mesd.service;

import java.io.IOException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.trustmarkins.mesd.exception.TMKException;

public interface IncidentService {

	public JSONObject transformJsonToJson(JSONObject json, Map<String, String> propMap) throws TMKException, JSONException;

	public String transformJsonToXml(JSONObject json, Map<String, String> propMap) throws TMKException;
}
