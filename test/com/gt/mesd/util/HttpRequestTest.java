package com.gt.mesd.util;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import org.json.JSONException;
import org.junit.Test;
import org.mockito.Mockito;

import com.trustmarkins.mesd.exception.TMKException;

import junit.framework.Assert;

public class HttpRequestTest extends Mockito {

	@Test
	public void authenticateEndpointWithURL() throws TMKException, JSONException, IOException {
		Logger LOGGER = LoggerSetup.getConfiguration(String.valueOf("100"));
		LOGGER.info("Test");
		PropertiesToMapConverter mapConverter = new PropertiesToMapConverter();
		Map<String, String> propertiesMap = mapConverter.getPropertiesMap("config.properties");
		final String endPointURL = propertiesMap.get("endPointURL");
		final String userName = propertiesMap.get("userName");
		final String password = propertiesMap.get("password");
		HttpUtil httpUtil = new HttpUtil();

		int response = httpUtil.authenticateEndpoint(endPointURL + "/Login", userName, password);
		Assert.assertEquals(200, response);
	}

	@Test(expected = IOException.class)
	public void endPointURLWithBadURL() throws IOException, JSONException {
		final String URL = "http://api.mockUrl/";
		HttpUtil httpUtil = new HttpUtil();
		httpUtil.authenticateEndpoint(URL, Mockito.anyString(), Mockito.anyString());
	}

}
