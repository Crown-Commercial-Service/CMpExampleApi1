package uk.gov.crowncommercial.examples.api1;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Configfuration class the builds a data source definition from the configuration
 * information obtained from the environment variables.
 *
 */
@Configuration
public class AppConfig {

    @Bean
    public DataSource primaryDataSource() {

        DataSource primaryDataSource = null;

        // Extract the database configuration information from the
        // environment variables passed to the application container
        ApiConfig config = ApiConfig.getApiConfig();
        if ( config.isDefaultDatabaseAvailable() ) {
            primaryDataSource = DataSourceBuilder.create()
                    .username(config.getDefaultDatabaseUsername())
                    .password(config.getDefaultDatabasePassword())
                    .url(config.getDefaultDatabaseURL())
                    .build();
        }


        return primaryDataSource;
    }
}
