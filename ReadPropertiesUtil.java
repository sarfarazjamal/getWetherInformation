package com.data.weather.util.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ReadPropertiesUtil {

	static InputStream inputStream;

	public static Map<String, String> getCitiesValues() throws IOException {

		Map<String, String> cityMap = new HashMap<String, String>();
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";

			inputStream = ReadPropertiesUtil.class.getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			for (String key : prop.stringPropertyNames()) {
				String value = prop.getProperty(key);
				cityMap.put(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inputStream.close();
		}
		return cityMap;
	}

	public static String getSericeURL() throws IOException {

		String serviceURL = null;
		try {
			Properties prop = new Properties();
			String propFileName = "serviceURL.properties";

			inputStream = ReadPropertiesUtil.class.getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			// get the property value
			serviceURL = prop.getProperty("url");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inputStream.close();
		}
		return serviceURL;
	}

}
