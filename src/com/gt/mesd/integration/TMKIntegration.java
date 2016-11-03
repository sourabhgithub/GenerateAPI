// $Id$
package com.gt.mesd.integration;

import java.util.Map;
import java.util.logging.Logger;

import org.json.JSONObject;

import com.gt.mesd.controller.RequestController;
import com.gt.mesd.util.Constants;
import com.gt.mesd.util.LoggerSetup;
import com.gt.mesd.util.PropertiesToMapConverter;
import com.manageengine.servicedesk.actionplugin.executor.DefaultActionInterface;
import com.manageengine.servicedesk.actionplugin.executor.ExecutorData;

public class TMKIntegration extends DefaultActionInterface {
	Map<String, String> propertiesMap = null;
	PropertiesToMapConverter conf = new PropertiesToMapConverter();
	JSONObject responseJson = null;

	public JSONObject execute(ExecutorData data) throws Exception {
		Logger LOGGER = LoggerSetup.getConfiguration(String.valueOf(data.getDataJSON().get("WORKORDERID")));
		JSONObject requestJson = data.getDataJSON();
		String level = requestJson.getString(Constants.LEVEL);

		switch (level) {
		case Constants.REQUEST:
			propertiesMap = conf.getPropertiesMap("request-mapping-gt-to-lt.properties");
			RequestController requestController = new RequestController();
			requestController.processRequest(requestJson, propertiesMap);
			break;

	/*	case Constants.INCIDENT:
			propertiesMap = conf.getPropertiesMap("incident-mapping-gt-to-lt.properties");
			IncidentController incidentController = new IncidentController();
			incidentController.processRequest(requestJson, propertiesMap);
			break;

		case Constants.PROBLEM:
			propertiesMap = conf.getPropertiesMap("incident-mapping-gt-to-lt.properties");
			ProblemController taskController = new ProblemController();
			taskController.makeRequestWithJson(requestJson, propertiesMap);

			break;*/
		}

		return responseJson;
	}

}
