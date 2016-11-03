package com.gt.mesd.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import com.gt.mesd.service.ProblemService;
import com.gt.mesd.service.RequestService;
import com.gt.mesd.service.impl.ProblemServiceImpl;
import com.gt.mesd.service.impl.RequestServiceImpl;
import com.gt.mesd.util.HttpUtil;
import com.gt.mesd.util.LoggerSetup;
import com.gt.mesd.util.PropertiesToMapConverter;
import com.trustmarkins.mesd.exception.TMKException;

public class ProblemController {

	private static final Logger LOGGER = LoggerSetup.getLogger();

	public void processRequest(JSONObject json, Map<String, String> propMap) {

		LOGGER.info("---------------START----------------------");
		ProblemService requestService = new ProblemServiceImpl();
		try {
			PropertiesToMapConverter mapConverter = new PropertiesToMapConverter();
			Map<String, String> propertiesMap = mapConverter.getPropertiesMap("config.properties");

			String endPointURL = propertiesMap.get("endPointURL");
			String userName = propertiesMap.get("userName");
			String password = propertiesMap.get("password");
			String productId = propertiesMap.get("sProductId");

			JSONObject payload = requestService.transformJsonToJson(json, propMap);

			HttpUtil httpRequest = new HttpUtil();
			int isOK = httpRequest.authenticateEndpoint(endPointURL + "/Login", userName, password);
			if (isOK == HttpStatus.SC_OK) {

				StringBuffer finalPayload = new StringBuffer();
				finalPayload.append(
						"<form_elements xml:space='preserve'><form_element index='0' required='0' hidden='0' type='textfield' adv_copy_target=''><display_name>JSON</display_name><user_value>");
				finalPayload.append(payload);
				finalPayload.append("</user_value></form_element></form_elements>");
				LOGGER.info("sQuestionValueXml :::: \n" + finalPayload);
				String urlEncodedFinalString = URLEncoder.encode(finalPayload.toString(), "UTF-8");
				httpRequest.processRequestWithPayload(endPointURL + "/OrderProduct", userName, String.valueOf(urlEncodedFinalString), productId);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TMKException e) {
			e.printStackTrace();
		}
		LOGGER.info("----End---------------makeRequestWithJson------------------");
	}

	public void makeRequestWithXml(JSONObject json, Map<String, String> propMap) throws TMKException {
		LOGGER.info("--------------------START-----------------");
		RequestService requestService = new RequestServiceImpl();
		String transformedXml = requestService.transformJsonToXml(json, propMap);
		System.out.println(transformedXml);
		LOGGER.info("--------------------END-----------------");
		// http request logic
	}
}
