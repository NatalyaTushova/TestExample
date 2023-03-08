package environment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Environment {
    static FileInputStream fileInput;
    static Properties property = new Properties();
    /**
     * Get properties
     */
    private static Properties loadProperties() {
        try {
            fileInput = new FileInputStream("src/main/resources/configuration.properties");
            property.load(fileInput);
        } catch (IOException e) {
            System.err.println("Error! File of properties is not found");
        }
        return property;
    }

    /**
     * Get the site url
     */
    public static final String SITE_URL = getSiteUrl("baseUrl");

    private static String getSiteUrl(String param) {

        return loadProperties().getProperty(param);
    }

    /**
     * Get the API url
     */
    public static final String API_URL = getApiUrl("baseApiUrl");

    private static String getApiUrl(String param) {

        return loadProperties().getProperty(param);
    }
}
