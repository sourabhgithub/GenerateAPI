package com.gt.mesd.service;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.trustmarkins.mesd.exception.TMKException;

public interface RequestService {

	public JSONObject transformJsonToJson(JSONObject json, Map<String, String> propMap) throws TMKException, JSONException;

	public String transformJsonToXml(JSONObject json, Map<String, String> propMap) throws TMKException;
}
