package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    // Method to get a string value
    public static String getProperty(String key) {
        // Make sure this path points to the actual file (not just directory)
        String filePath = System.getProperty("user.dir") + "/Configrations/config1.properties";
        try {
            FileInputStream file = new FileInputStream(filePath);
            prop = new Properties();
            prop.load(file);
        } catch (IOException e) {
            System.err.println("❌ Unable to load config.properties: " + e.getMessage());
        }
        return prop.getProperty(key);
    }
}
