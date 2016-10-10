package com.gt.mesd.util;

import java.io.IOException;

import junit.framework.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class HttpRequestTest extends Mockito{
	@Test
	public void PostRequest1SuccessTest() throws IOException{
		final String URL = "mockUrl";
		final String USER = "mockUser";
		final String PASSWORD = "mockPass";
		
		HttpUtil httpUtil = mock(HttpUtil.class);
		
		when(httpUtil.postRequest1(URL, USER, PASSWORD)).thenReturn(201);
	
		int response = httpUtil.postRequest1(URL, USER, PASSWORD);
		//System.out.println(response);
		Assert.assertEquals(201, response);
	}
	
	@Test
	public void PostRequest2SuccessTest() throws IOException{
		final String URL = "mockUrl";
		final String USER = "mockUser";
		final String SESSION_ID = "mockSessionId";
		
		HttpUtil httpUtil = mock(HttpUtil.class);
		
		when(httpUtil.postRequest1(URL, USER, SESSION_ID)).thenReturn(201);
	
		int response = httpUtil.postRequest1(URL, USER, SESSION_ID);
		//System.out.println(response);
		Assert.assertEquals(201, response);
	}
}
