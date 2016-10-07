package com.gt.mesd.integration;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class TestCall {

	public static void main(String[] args){
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpGet httpGet = new HttpGet("http://10.1.1.210:8080/spring/rest/emps");
		HttpResponse response;
		try {
			response = client.execute(httpGet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
