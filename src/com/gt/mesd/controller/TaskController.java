package com.gt.mesd.controller;

import java.io.IOException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.gt.mesd.service.RequestService;
import com.gt.mesd.service.TaskService;
import com.gt.mesd.service.impl.RequestServiceImpl;
import com.gt.mesd.service.impl.TaskServiceImpl;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TaskController {/*
	public static String GET(OkHttpClient client, HttpUrl url) throws IOException {
		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

	public static String POST(OkHttpClient client, HttpUrl url, RequestBody body) throws IOException {
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}*/
	
	public void makeRequestWithJson(JSONObject json, Map<String,String> propMap) throws JSONException, IOException{
		TaskService taskService = new TaskServiceImpl();
		JSONObject transformedJson = taskService.transformJsonToJson(json, propMap);
		
		//http request logic
	}
	
	public void makeRequestWithXml(JSONObject json, Map<String,String> propMap) throws JSONException,IOException{
		TaskService taskService = new TaskServiceImpl();
		String transformedXml = taskService.transformJsonToXml(json, propMap);
	
		//http request logic
	}
}
