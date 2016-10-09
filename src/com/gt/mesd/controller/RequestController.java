package com.gt.mesd.controller;

import java.io.IOException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.gt.mesd.service.IncidentService;
import com.gt.mesd.service.RequestService;
import com.gt.mesd.service.impl.IncidentServiceImpl;
import com.gt.mesd.service.impl.RequestServiceImpl;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RequestController {
/*
	// GET network request
	public static String GET(OkHttpClient client, HttpUrl url) throws IOException {
		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

	public static String GET(OkHttpClient client, String url) throws IOException {
		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

	// POST network request
	public static String POST(OkHttpClient client, HttpUrl url, RequestBody body) throws IOException {
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

	public static String POST(OkHttpClient client, String url, RequestBody body) throws IOException {
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}*/
	
	public void makeRequestWithJson(JSONObject json, Map<String,String> propMap) throws JSONException, IOException{
		RequestService requestService = new RequestServiceImpl();
		JSONObject transformedJson = requestService.transformJsonToJson(json, propMap);
		
		//http request logic
	}
	
	public void makeRequestWithXml(JSONObject json, Map<String,String> propMap) throws JSONException,IOException{
		RequestService requestService = new RequestServiceImpl();
		String transformedXml = requestService.transformJsonToXml(json, propMap);
	
		//http request logic
	}
}
