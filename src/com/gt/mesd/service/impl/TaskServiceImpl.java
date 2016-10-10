package com.gt.mesd.service.impl;

import java.io.IOException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.gt.mesd.service.TaskService;
import com.gt.mesd.util.DataTransformer;

public class TaskServiceImpl implements TaskService {

	@Override
	public JSONObject transformJsonToJson(JSONObject json, Map<String, String> propMap) throws JSONException, IOException {
		return DataTransformer.transformJsonToJson(json, propMap);
	}

	@Override
	public String transformJsonToXml(JSONObject json, Map<String, String> propMap) throws JSONException, IOException {
		return DataTransformer.transformJsonToXml(json, propMap);
	}

}
