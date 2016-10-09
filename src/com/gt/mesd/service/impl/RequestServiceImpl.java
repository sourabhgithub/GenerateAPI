package com.gt.mesd.service.impl;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import com.gt.mesd.service.RequestService;
import com.gt.mesd.util.DataTransformer;

public class RequestServiceImpl implements RequestService{
	@Override
	public JSONObject transformJsonToJson(JSONObject json,Map<String,String> propMap) throws JSONException,IOException{
		return DataTransformer.transformJsonToJson(json, propMap);
	}
	
	@Override
	public String transformJsonToXml(JSONObject json,Map<String,String> propMap) throws JSONException,IOException{
		return DataTransformer.transformJsonToXml(json, propMap);
	}
}
