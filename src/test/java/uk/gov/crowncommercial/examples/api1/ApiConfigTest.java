package uk.gov.crowncommercial.examples.api1;

import org.junit.Test;

import static org.junit.Assert.*;

public class ApiConfigTest {

    @Test
    public void getApiConfig() {

        ApiConfig apiConfig = ApiConfig.getApiConfig();

        assertNotNull( "APIConfig factory", apiConfig );
    }

    @Test
    public void getApiURL() {

        String apiURL = ApiConfig.getApiConfig().getApiURL("api1");
        assertEquals( "Correct API Url", "http://api1.ccsdev-internal.org", apiURL );
    }

    @Test
    public void isFeatureEnabled() {

        boolean nonExistantFeature = ApiConfig.getApiConfig().isFeatureEnabled("THIS_WONT_EXIST");

        assertFalse("No-existant feature", nonExistantFeature );
    }

    @Test
    public void getAppProtocol() {

        String protocol = ApiConfig.getApiConfig().getAppProtocol();

        assertEquals( "App protocol should be http", "http", protocol);
    }

    @Test
    public void getAppBaseURL() {

        String baseURL = ApiConfig.getApiConfig().getAppBaseURL();

        assertEquals( "Base app URL", "roweitdev.co.uk", baseURL );
    }

    @Test
    public void getApiProtocol() {

        String protocol = ApiConfig.getApiConfig().getApiProtocol();

        assertEquals( "Api protocol should be http", "http", protocol);
    }

    @Test
    public void getApiBaseURL() {

        String baseURL = ApiConfig.getApiConfig().getApiBaseURL();

        assertEquals( "Base api URL", "ccsdev-internal.org", baseURL );
    }
}