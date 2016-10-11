package com.gt.mesd.util;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mockito;

public class HttpRequestTest extends Mockito{
	private static final Logger LOGGER = Logger.getLogger(HttpRequestTest.class.getName());
	
	@Test
	public void PostRequest1SuccessTest() throws IOException{
		final String URL = "https://jsonplaceholder.typicode.com/posts";
		final String USER = "mockUser";
		final String PASSWORD = "mockPass";
		
		HttpUtil httpUtil  = new HttpUtil();
		
		int response = httpUtil.postRequest1(URL, USER, PASSWORD);
		//System.out.println(response);
		Assert.assertEquals(201, response);
	}
	
	@Test
	public void PostRequest2SuccessTest() throws IOException{
		final String URL = "https://jsonplaceholder.typicode.com/posts";
		final String USER = "mockUser";
		final String SESSION_ID = "mockSessionId";
		
		HttpUtil httpUtil = new HttpUtil();
			
		int response = httpUtil.postRequest2(URL, USER, SESSION_ID);
		//System.out.println(response);
		Assert.assertEquals(201, response);
	}
	
	@Test(expected = IOException.class)
	public void PostRequest1ExceptionTest() throws IOException{
		final String URL = "http://api.mockUrl/";
		final String USER = "mockUser";
		final String PASSWORD = "mockPass";
		
		HttpUtil httpUtil = new HttpUtil();
		
		int response = httpUtil.postRequest1(URL, USER, PASSWORD);
				
		//System.out.println(response);
		
	}
	
	@Test(expected = IOException.class)
	public void PostRequest2ExceptionTest() throws IOException{
		final String URL = "http://api.mockUrl/";
		final String USER = "mockUser";
		final String SESSION_ID = "mockSessionId";
		
		HttpUtil httpUtil = new HttpUtil();
				
		int response = httpUtil.postRequest2(URL, USER, SESSION_ID);
		
		
	}
}
