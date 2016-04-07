package com.jjortega.cuapi.requestcore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {
	
	public static String getPropValue(String key) {
		String value = null;
		String configFileName = System.getProperty("user.dir") + "/resources/config.properties";
		Properties properties = new Properties();
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(configFileName);
			properties.load(inputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		value = properties.getProperty(key);
		return value;		
	}

}
