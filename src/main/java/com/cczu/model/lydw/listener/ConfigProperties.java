package com.cczu.model.lydw.listener;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {

    private static transient final Logger LOGGER = LoggerFactory.getLogger(ConfigProperties.class);
    private static Properties properties = new Properties();

    static {
        try {
          //  PropertiesLoader propertiesLoader = new PropertiesLoader("config.properties");
            InputStream inputStream = ConfigProperties.class.getClassLoader().getResourceAsStream("/config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(String.format("读取配置文件失败。 ErrorMsg:[%s]", e.getMessage()));
        }
    }

    public static String getString(String propertiesKey) {
        return getString(propertiesKey, null);
    }

    public static int getInt(String propertiesKey) {
        return getInt(propertiesKey, 0);
    }

    public static boolean getBoolean(String propertiesKey) {
        String v = properties.getProperty(propertiesKey);
        return Boolean.parseBoolean(v);
    }

    public static String getString(String propertiesKey, String defaultValue) {
        String value = properties.getProperty(propertiesKey);

        if (StringUtils.isNotBlank(value)) {
            try {
                value = new String(value.getBytes("ISO8859-1"), System.getProperty("file.encoding"));
            } catch (Exception e) {
                value = defaultValue;
            }
        } else {
            value = defaultValue;
        }

        return value;
    }

    public static int getInt(String propertiesKey, int defaultValue) {
        String str = properties.getProperty(propertiesKey);
        int v = defaultValue;
        if (StringUtils.isBlank(str)) {
            v = defaultValue;
        } else {
            try {
                v = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                v = defaultValue;
            }
        }

        return v;
    }

    public static double getDouble(String propertiesKey, double defaultValue) {
        String str = properties.getProperty(propertiesKey);
        double v = defaultValue;
        if (StringUtils.isBlank(str)) {
            v = defaultValue;
        } else {
            try {
                v = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                v = defaultValue;
            }
        }

        return v;
    }

    public static Properties getProperties() {
        return properties;
    }
}
