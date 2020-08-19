package eon.qa.testutils;
/**
	 * @author Praveen Kalliyath
	 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.PropertiesConfigurationLayout;

public class PropertiesUtil {

	private static Properties properties;
	public static boolean propertiesLoaded = false;

	/**
	 * Loading Properties Files for Framework & Application
	 * 
	 * @exception FileNotFoundException
	 * 
	 * @exception IOException
	 */
	public static void loadConfig() {
		try {
			// FRAMEWORK CONFIG FILE
			System.out.println("Loading Framework Configuration");
			FileInputStream frameworkFile = new FileInputStream(Data.PROPERTIESFILES_FOLDER + "data.properties");
			properties = new Properties();
			properties.load(frameworkFile);
			System.out.println("Loaded Framework Properties File");
			System.out.println("Configuration File Data Map: " + properties);
		} catch (FileNotFoundException e) {
			System.err.println("Configuration file not found. Error Message:" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Failed to load configuration file. Error Message:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Method to write 'Key' & 'Value' to Properties Files
	 * 
	 * @param key
	 * 
	 * @param value
	 * 
	 * @exception ConfigurationException
	 * 
	 * @exception FileNotFoundException
	 * 
	 * @exception IOException
	 */
	public static void writeToConfigFile(String key, String value) {
		try {
			File file = new File(Data.PROPERTIESFILES_FOLDER + "data.properties");
			PropertiesConfiguration configuration = new PropertiesConfiguration();
			PropertiesConfigurationLayout configurationLayout = new PropertiesConfigurationLayout(configuration);
			configurationLayout.load(new InputStreamReader(new FileInputStream(file)));
			configuration.setProperty(key, value);
			configurationLayout.save(new FileWriter(file));
			System.out.println("Saved data to properties file.");
			System.out.println("Key '[" + key + "]' : Value '[" + value + "]'");
		} catch (ConfigurationException e) {
			System.err.println("Exception Occured. Error Message:" + e.getMessage());
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.err.println("Configuration file not found. Error Message:" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Failed to write data to configuration file. Error Message:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Method to get property value
	 * 
	 * @param key
	 * 
	 * @return Property Value
	 */
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
