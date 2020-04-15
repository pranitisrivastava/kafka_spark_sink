package com.cv.polaris.mer.stream.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyHandler {
    
    static Properties prop = new Properties();
    public PropertyHandler(String fPath)
    {
        InputStream input = PropertyHandler.class.getClassLoader().getResourceAsStream("config.properties");
        System.out.println(fPath);
        if (input == null)
        {
            System.out.println("Property file is empty.");
            System.exit(-1);
        }
        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getPropValue(String p)
    {
        return prop.getProperty(p.toString());
    }
    public Map<String ,String> getSparkProperties()
    {
        return getAllPropertiesStartingWith("spark");
    }

    public Map<String ,String> getKafkaProperties()
    {
        return getAllPropertiesStartingWith("kafka");
    }

    //method to find specific properties - for example kafka specific properties
    protected Map<String,String> getAllPropertiesStartingWith(String startsWith)
    {
        Map<String,String > configurations = new HashMap<String,String>();
        for(Enumeration<Object> e = prop.keys();e.hasMoreElements();)
        {
            String name = (String)e.nextElement();
            if(name.startsWith(startsWith))
            {
                String value = prop.getProperty(name);
                configurations.put(name,value);
            }
        }
        return configurations;
    }
}
