package uk.gov.crowncommercial.examples.api1;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the API configuration.
 *
 * The following environment variables are supported:
 *
 * CCS_API_BASE_URL
 *      Base URL for API access with no protocol or trailing /
 * CCS_API_PROTOCOL
 *      API protocol, 'http' or 'https'
 * CCS_APP_BASE_URL
 *      Base URL for APP access with no protocol or trailing /
 * CCS_APP_PROTOCOL
 *      APP protocol, 'http' or 'https'
 *
 * Connection information is also supplied for the default database
 * CCS_DEFAULT_DB_URL
 *      JDBC style URL for database connection
 * CCS_DEFAULT_DB_USER
 *      Default database user name
 * CCS_DEFAULT_DB_PASSWORD
 *      Default database password
 *
 * If no environment variables are specified the database will default to an in-memory H2 implementation
 *
 * Features swtiches can also be specified with the prefx CCS_FEATURE_
 * The value must be 'on', 'off', 'enabled', 'disabled', 'true' or 'false'.
 *
 *      For example: CCS_FEATURE_EG1=on
 *
 */
public class ApiConfig {

    /**
     * Expected environment variable names
     */
    private static final String ENV_API_BASE_URL = "CCS_API_BASE_URL";
    private static final String ENV_API_PROTOCOL = "CCS_API_PROTOCOL";
    private static final String ENV_APP_BASE_URL = "CCS_APP_BASE_URL";
    private static final String ENV_APP_PROTOCOL = "CCS_APP_PROTOCOL";
    private static final String ENV_DB_URL = "CCS_DEFAULT_DB_URL";
    private static final String ENV_DB_USERNAME = "CCS_DEFAULT_DB_USER";
    private static final String ENV_DB_PASSWORD = "CCS_DEFAULT_DB_PASSWORD";
    private static final String ENV_FEATURE_PREFIX = "CCS_FEATURE_";

    /**
     * The only instance of the API config
     */
    private static ApiConfig apiConfig = new ApiConfig();

    /**
     * Obtain a reference to the API confiuration object
     *
     * @return A reference to the API Config object
     */
    public static ApiConfig getApiConfig() {

        return apiConfig;
    }


    /**
     * Protocol used for Application URLs
     */
    private String appProtocol = "http";

    /**
     * Base application URL
     */
    private String appBaseURL = "roweitdev.co.uk";

    /**
     * Protocol used for API URLs
     */
    private String apiProtocol = "http";

    /**
     * Base API URL
     */
    private String apiBaseURL = "ccsdev-internal.org";

    /**
     * JDBC connection string style URL for connecting to the default
     * Database
     */
    private String defaultDatabaseURL = "jdbc:h2:mem:defaultdb";

    /**
     * User name for accessing the default database
     */
    private String defaultDatabaseUsername = "";

    /**
     * Password for accessing the default database
     */
    private String defaultDatabasePassword = "";

    /**
     * Map of boolean flags indicating what features are enabled
     */
    private Map<String,Boolean> featureInfo = new HashMap<String,Boolean>();

    /**
     * Private constructor to create the config object
     */
    private ApiConfig() {

        // Read what we can from the environment
        Map<String,String> env = System.getenv();

        for ( String ev : env.keySet() ) {

            String value = env.get(ev);
            if (value == null || value.equals("_")) {
                value = "";
            }

            String evTest = ev.toUpperCase().trim();
            if ( evTest.equals(ENV_API_BASE_URL) ) {
                if (value.trim().length() > 0) {
                    apiBaseURL = value.trim();
                }
            } else if ( evTest.equals(ENV_API_PROTOCOL) ) {
                if (value.trim().length() > 0) {
                    apiProtocol = value.trim();
                }
            } else if ( evTest.equals(ENV_APP_BASE_URL) ) {
                if (value.trim().length() > 0) {
                    appBaseURL = value.trim();
                }
            } else if ( evTest.equals(ENV_APP_PROTOCOL) ) {
                if (value.trim().length() > 0) {
                    appProtocol = value.trim();
                }
            } else if ( evTest.equals(ENV_DB_URL) ) {
                if (value.trim().length() > 0) {
                   defaultDatabaseURL = value.trim();
                }
            } else if ( evTest.equals(ENV_DB_USERNAME) ) {
                if (value.trim().length() > 0) {
                    defaultDatabaseUsername = value.trim();
                }
            } else if ( evTest.equals(ENV_DB_PASSWORD) ) {
                if (value.trim().length() > 0) {
                    defaultDatabasePassword = value.trim();
                }
            } else if ( evTest.startsWith(ENV_FEATURE_PREFIX) ) {

                    // Determine if the feature is enabled
                    String featureName = evTest.substring( ENV_FEATURE_PREFIX.length() );
                    String val = value.toUpperCase().trim();
                    boolean enabled = false;
                    if ( (val.equals("ON")) || (val.equals("ENABLED")) || val.equals("TRUE") || val.equals("YES") ) {
                        enabled = true;
                    }
                    featureInfo.put( featureName, Boolean.valueOf(enabled) );
            }
        }
    }


    /**
     * Obtains a complete path to the specified API
     *
     * @param apiName
     * @return API URL
     */
    public String getApiURL( String apiName ) {

        return apiProtocol + "://" + apiName + "." + apiBaseURL;
    }


    /**
     * Called to determine if a particular feature is enabled.
     *
     * @param featureName
     * @return true if the feature is enabled
     */
    public boolean isFeatureEnabled( String featureName ) {

        boolean enabled = false;

        if ( featureInfo.containsKey(featureName) ) {
            enabled = featureInfo.get(featureName).booleanValue();
        }

        return enabled;
    }

    /**
     *
     * @return Protocol used for Application URLs
     */
    public String getAppProtocol() {
        return appProtocol;
    }

    /**
     *
     * @return Base URL for application URLs
     */
    public String getAppBaseURL() {
        return appBaseURL;
    }

    /**
     *
     * @return Protocol used for API URLs
     */
    public String getApiProtocol() {
        return apiProtocol;
    }

    /**
     *
     * @return Base URL for API URLs
     */
    public String getApiBaseURL() {
        return apiBaseURL;
    }

    /**
     *
     * @return True if there appears to be valid default database connection details
     */
    boolean isDefaultDatabaseAvailable() {

        return ((defaultDatabaseURL == null) || (defaultDatabaseURL.length() == 0)) ? false : true;
    }

    /**
     *
     * @return JDBC style connection URL for the default database
     */
    public String getDefaultDatabaseURL() {
        return defaultDatabaseURL;
    }

    /**
     *
     * @return User name for the default database
     */
    public String getDefaultDatabaseUsername() {
        return defaultDatabaseUsername;
    }

    /**
     *
     * @return Password for the default database
     */
    public String getDefaultDatabasePassword() {
        return defaultDatabasePassword;
    }
}
