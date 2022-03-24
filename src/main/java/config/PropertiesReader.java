package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    public PropertiesReader() {
    }

    public static String get(String key) {

        return PROPERTIES.getProperty(key);
    }

    public static String[] getStringArray(String key) {
        return new String[]{PROPERTIES.getProperty(key)};
    }

    public static int[] getIntArray(String key) {

        return new int[]{Integer.parseInt(PROPERTIES.getProperty(key))};
    }

    private static void loadProperties() {
        try (InputStream inputStream = PropertiesReader.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
