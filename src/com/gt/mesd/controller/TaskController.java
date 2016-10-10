package com.gt.mesd.controller;

import java.io.IOException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.gt.mesd.service.TaskService;
import com.gt.mesd.service.impl.TaskServiceImpl;

public class TaskController {

	public void makeRequestWithJson(JSONObject json, Map<String, String> propMap) throws JSONException, IOException {
		System.out.println("TaskController: makeRequestWithJson()");

		TaskService taskService = new TaskServiceImpl();
		JSONObject transformedJson = taskService.transformJsonToJson(json, propMap);

		// http request logic
	}

	public void makeRequestWithXml(JSONObject json, Map<String, String> propMap) throws JSONException, IOException {
		System.out.println("TaskController: makeRequestWithXml()");

		TaskService taskService = new TaskServiceImpl();
		String transformedXml = taskService.transformJsonToXml(json, propMap);

		// http request logic
	}
}
