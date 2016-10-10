// $Id$
package com.gt.mesd.integration;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.gt.mesd.controller.IncidentController;
import com.gt.mesd.controller.RequestController;
import com.gt.mesd.controller.TaskController;
import com.gt.mesd.util.Constants;
import com.gt.mesd.util.PropertiesToMapConverter;
import com.manageengine.servicedesk.actionplugin.executor.DefaultActionInterface;
import com.manageengine.servicedesk.actionplugin.executor.ExecutorData;

public class TMKIntegration extends DefaultActionInterface {

	private static final Logger LOGGER = Logger.getLogger(TMKIntegration.class.getName());

	Map<String, String> propertiesMap = null;
	PropertiesToMapConverter conf = new PropertiesToMapConverter();

	@Override
	public JSONObject execute(ExecutorData data) throws Exception {

		LOGGER.info("START : execute : " + TMKIntegration.class.getName());

		JSONObject resultJson = data.getDataJSON();

		String level = resultJson.getString(Constants.LEVEL);
		switch (level) {
		case Constants.REQUEST:
			propertiesMap = conf.getPropertiesMap("/request-mapping-gt-to-lt.properties");
			RequestController requestController = new RequestController();
			requestController.makeRequestWithJson(resultJson, propertiesMap);
			break;
		case Constants.INCIDENT:
			propertiesMap = conf.getPropertiesMap("/incident-mapping-gt-to-lt.properties");
			IncidentController incidentController = new IncidentController();
			incidentController.makeRequestWithJson(resultJson, propertiesMap);
			break;
		case Constants.TASK:
			propertiesMap = conf.getPropertiesMap("/incident-mapping-gt-to-lt.properties");
			TaskController taskController = new TaskController();
			taskController.makeRequestWithJson(resultJson, propertiesMap);
			break;
		}

		HttpClient client = HttpClientBuilder.create().build();

		HttpGet httpGet = new HttpGet("http://10.1.1.210:8080/spring/rest/emps");
		HttpResponse response;
		try {
			response = client.execute(httpGet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("HELLO######################" + resultJson);
		LOGGER.info("START : execute : " + TMKIntegration.class.getName() + "With JSON REQUEST : " + resultJson);
		return resultJson;
	}

}
