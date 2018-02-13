package configurator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
	
	private static PropertiesManager instance = null;
	private static Properties properties = null;

	private static File PROP_FILE = new File("data.prop");
	
	private PropertiesManager() {
		properties = new Properties();
		
		if(!PROP_FILE.exists()) {
			try {
				PROP_FILE.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			properties.load(new FileInputStream(PROP_FILE));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void put(String name, String value) {
		properties.put(name, value);

		try {
			properties.store(new FileWriter(PROP_FILE), "UniverzalniInstalatorProps v1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String get(String name, String defaultValue) {
		return properties.getProperty(name, defaultValue);
	}
	
	public String get(String name) {
		return get(name, null);
	}
	
	public static PropertiesManager getInstance() {
		if(instance == null)
			instance = new PropertiesManager();
		
		return instance;
	}
	
}
