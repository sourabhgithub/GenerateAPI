package com.gt.mesd.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.json.JSONException;

public class LoggerSetup {

	private static final Logger LOGGER = Logger.getLogger(LoggerSetup.class.getName());
	static Path pathToFile = null;

	public static Logger getConfiguration(String ticketId) throws JSONException {
		Handler fileHandler = null;
		Formatter simpleFormatter = null;
		try {
			String postFixDate = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			String logsFolderPath = getLogFolderLocation();
			pathToFile = createFolderAndFile(ticketId, postFixDate, logsFolderPath);
			// Creating FileHandler
			fileHandler = new FileHandler(pathToFile.toString());
			// Creating SimpleFormatter
			simpleFormatter = new SimpleFormatter();
			// Assigning handler to logger
			LOGGER.addHandler(fileHandler);
			// Logging message of Level info (this should be publish in the
			// default format i.e. XMLFormat)
			LOGGER.info("Finnest message: Logger with DEFAULT FORMATTER");

			// Setting formatter to the handler
			fileHandler.setFormatter(simpleFormatter);

			// Setting Level to ALL
			fileHandler.setLevel(Level.ALL);
			LOGGER.setLevel(Level.ALL);

			// Logging message of Level finest (this should be publish in the
			// simple format)
			LOGGER.finest("Finnest message: Logger with SIMPLE FORMATTER");
			return LOGGER;
		} catch (IOException exception) {
			LOGGER.log(Level.SEVERE, "Error occur in FileHandler.", exception);
		}
		return LOGGER;
	}
	
	private static String getLogFolderLocation() {
		String path = System.getProperty("user.dir");
		String logsFolderPath = path + File.separator + ".." + File.separator + "integration" + File.separator + "conf" + File.separator + "logs" + File.separator;
		return logsFolderPath;
	}

	private static Path createFolderAndFile(String ticketId, String postFixDate, String logsFolderPath) throws IOException {
		Path pathToFile = Paths.get(logsFolderPath + ticketId + "_" + postFixDate + ".log");
		Files.createDirectories(pathToFile.getParent());
		Files.createFile(pathToFile);
		return pathToFile;
	}
	
	public static Logger getLogger() {
		return LOGGER;
	}

}
