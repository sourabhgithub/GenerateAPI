package com.gt.mesd.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.XML;

public class HttpUtil {
	private static final Logger LOGGER = LoggerSetup.getLogger();
	private static String contentData = null;

	public int authenticateEndpoint(String url, String username, String password) throws IOException, JSONException {
		HttpClient client = getHttpClient(url, username, password);
		HttpPost post = new HttpPost(url);

		post.setHeader("Content-Type", "application/x-www-form-urlencoded");

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("sUserId", username));
		urlParameters.add(new BasicNameValuePair("sPassword", password));

		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		LOGGER.info("Attempting POST Request with params: username=" + username);

		HttpResponse response = client.execute(post);
		int responseCode = response.getStatusLine().getStatusCode();

		LOGGER.info("authenticateEndpoint - Response Code : " + response.getStatusLine().getStatusCode());

		String result = getResponseContent(response);
		
		LOGGER.info(result);

		return responseCode;
	}

	public int processRequestWithPayload(String url, String username, String payload, String productId) throws IOException, JSONException {
		HttpClient client = getHttpClient(url, username, payload, productId);
		HttpPost post = new HttpPost(url);

		post.setHeader("Content-Type", "application/x-www-form-urlencoded");

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("sUserId", username));
		urlParameters.add(new BasicNameValuePair("sSessionId", contentData));
		urlParameters.add(new BasicNameValuePair("sProductId", productId));
		urlParameters.add(new BasicNameValuePair("sQuestionValueXml", payload));

		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		LOGGER.info("processRequestWithPayload - Attempting POST Request with params: username=" + username + "&sessionId=" + contentData);
		
		HttpResponse response = client.execute(post);
		int responseCode = response.getStatusLine().getStatusCode();

		LOGGER.info("processRequestWithPayload Response Code : " + response.getStatusLine().getStatusCode());

		String result = getResponseContent(response);

		LOGGER.info("processRequestWithPayload - Response Body :" + result.toString());

		return responseCode;
	}

	public static HttpClient getHttpClient(String... details) throws JSONException {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		httpClientBuilder.setRetryHandler(getRetryHandler(details));
		HttpClient client = httpClientBuilder.build();

		return client;
	}

	// retry handler -> attempts request 5 times
	public static HttpRequestRetryHandler getRetryHandler(final String... details) throws JSONException {
		return new HttpRequestRetryHandler() {
			@Override
			public boolean retryRequest(IOException exception, int executionCount, HttpContext httpContext) {
				LOGGER.info("Request Execution no: " + executionCount);
				// System.out.println("Request Execution no: "+executionCount);
				if (executionCount >= 5) {
					LOGGER.info("Aborting Request retry using Job Schedular");
					System.out.println(details);
					return false;
				}
				LOGGER.info("Retrying request...");
				// System.out.println("Retrying request...");
				return true;
			}
		};
	}

	private String getResponseContent(HttpResponse response) throws IOException, JSONException {

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		contentData = XML.toJSONObject(result.toString()).getJSONObject("string").get("content").toString();
		// JSONObject jsonObject = soapDatainJsonObject.getJSONObject("string");
		System.out.println(contentData);
		return contentData;
	}
}
