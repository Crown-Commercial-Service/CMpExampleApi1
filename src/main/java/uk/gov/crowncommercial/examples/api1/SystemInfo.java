package uk.gov.crowncommercial.examples.api1;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class SystemInfo {

    private final long id;
    private final boolean exposeProperties;
    private final boolean exposeEnv;

    public SystemInfo(long id, String details ) {
        this.id = id;

        if ( details.equalsIgnoreCase("all") ) {
            exposeProperties = true;
            exposeEnv = true;
        } else if ( details.equalsIgnoreCase("properties") ) {
            exposeProperties = true;
            exposeEnv = false;
        } else if ( details.equalsIgnoreCase("environment") ) {
            exposeEnv = true;
            exposeProperties = false;
        } else {
            exposeProperties = false;
            exposeEnv = false;
        }
    }

    public long getId() {
        return id;
    }

    public String getDateAndTime() {

        return new Date().toString();
    }

    public Properties getProperties() {
        if ( this.exposeProperties ) {
            return System.getProperties();
        } else {
            return null;
        }
    }

    public Map<String,String> getEnvironment() {

        if ( this.exposeEnv ) {
            return System.getenv();
        } else {
            return null;
        }
    }
}
