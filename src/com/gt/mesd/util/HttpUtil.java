package com.gt.mesd.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public class HttpUtil {
	private static final Logger LOGGER = Logger.getLogger(HttpUtil.class.getName());
	
	public int postRequest1(String url, String username, String password) throws IOException{
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		
		HttpClient client = httpClientBuilder.build();
		HttpPost post = new HttpPost(url);

		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("username", username));
		urlParameters.add(new BasicNameValuePair("password", password));
		
		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		LOGGER.log(Priority.INFO, "PostRequest1 - Requesting with params: username="+username+"&password="
				+password);
		
		HttpResponse response = client.execute(post);
		int rc = response.getStatusLine().getStatusCode();
		
		LOGGER.log(Priority.INFO, "PostRequest1 - Response Code : " + response.getStatusLine().getStatusCode());
		
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		
		LOGGER.log(Priority.INFO, "PostRequest1 - Response Body :" + result.toString());
		
		return rc;
	}
	
	public int postRequest2(String url, String username, String sessionId) throws IOException{
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		
		HttpClient client = httpClientBuilder.build();
		HttpPost post = new HttpPost(url);

		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("username", username));
		urlParameters.add(new BasicNameValuePair("sessionId", sessionId));
		
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		LOGGER.log(Priority.INFO, "PostRequest2 - Requesting with params: username="+username+"&sessionId="
				+sessionId);
		HttpResponse response = client.execute(post);
		int rc = response.getStatusLine().getStatusCode();
		
		LOGGER.log(Priority.INFO, "PostRequest2 Response Code : " + response.getStatusLine().getStatusCode());

		
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		
		LOGGER.log(Priority.INFO, "PostRequest2 - Response Body :" + result.toString());
		
		return rc;
	}
}
