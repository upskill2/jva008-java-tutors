package com.luxoft.jva008.module01;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.luxoft.jva008.Logger.log;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PropertiesTutor {

    /**
     * Returns the value of the system property java.version
     */
    public String getJavaVersion() {

        Runtime.Version runtimeVersion = Runtime.version();

        return runtimeVersion.toString();
    }

    @Test
    public void testJavaVersion() {
        String version = getJavaVersion();
        log(getJavaVersion());
        assertTrue(version.startsWith("18."));
    }

    /**
     * Loads the properties file from the files / props.properties folder
     * And returns the downloaded properties
     *
     * @return
     */
    public Properties getProperties() {

        Properties prop = new Properties();
        try (InputStream inStream = new
                FileInputStream("files/props.properties")) {
            prop.load(inStream);
            System.out.println(prop.getProperty("color"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return prop;
    }

    @Test
    public void testGetProperties() {
        Properties props = getProperties();
        log("country=" + props.getProperty("country"));
        log("color=" + props.getProperty("color"));
        assertEquals("Australia", props.getProperty("country"));
        assertEquals("red", props.getProperty("color"));
    }

}
