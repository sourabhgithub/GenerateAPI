package com.gt.mesd.util;

import java.util.logging.Logger;

import org.json.JSONException;
import org.junit.Test;

public class LoggerSetupTest {

	@Test
	public void getLoggerConfiguration() throws JSONException {

		LoggerSetup lg = new LoggerSetup();

		Logger logger = lg.getConfiguration("100");

	}

}
