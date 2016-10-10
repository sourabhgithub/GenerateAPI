// $Id$
package com.gt.mesd.integration;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.manageengine.servicedesk.actionplugin.executor.DefaultActionInterface;
import com.manageengine.servicedesk.actionplugin.executor.ExecutorData;
import com.manageengine.servicedesk.utils.CommonUtil;

public class TMKIntegration extends DefaultActionInterface {

	private static final Logger LOGGER = Logger.getLogger(TMKIntegration.class.getName());

	@Override
	public JSONObject execute(ExecutorData data) throws Exception {

		// JSONObject dataJSON2 = data.getDataJSON();
		JSONObject resultJson = data.getDataJSON();

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
		return resultJson;
	}

	public JSONObject getXMLConfigurations(Document doc, String xmlQuery) throws JSONException {

		JSONObject configurationJson = new JSONObject();
		try {
			Node node = CommonUtil.getInstance().findElement(doc, xmlQuery);
			Node childnode = node.getFirstChild();

			while (childnode != null) {
				if (childnode.getNodeType() == Node.ELEMENT_NODE) {
					if (childnode.getNodeName().equals("url")) {
						configurationJson.put("url", childnode.getTextContent().trim());
					} else if (childnode.getNodeName().equals("apikey")) {
						configurationJson.put("apikey", childnode.getTextContent().trim());
					}
				}
				childnode = childnode.getNextSibling();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return configurationJson;
	}

}
