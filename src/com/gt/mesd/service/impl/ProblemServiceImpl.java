package com.gt.mesd.service.impl;

import java.io.IOException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.gt.mesd.service.ProblemService;
import com.gt.mesd.util.DataTransformer;
import com.trustmarkins.mesd.exception.TMKException;

public class ProblemServiceImpl implements ProblemService {

	@Override
	public JSONObject transformJsonToJson(JSONObject json, Map<String, String> propMap) throws TMKException, JSONException {
		return DataTransformer.transformJsonToJson(json, propMap);
	}

	@Override
	public String transformJsonToXml(JSONObject json, Map<String, String> propMap) throws TMKException {
		return DataTransformer.transformJsonToXml(json, propMap);
	}

}
